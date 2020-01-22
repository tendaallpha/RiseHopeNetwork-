package cm.genie6.risehope.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Organization;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.VisitService;

@Controller
public class AccountController {

	private AccountService accountService;
	private VisitService visitService;
	public static final String PROFILEDIRECTORY = System.getProperty("user.dir")
			+ "/src/main/resources/static/profileImages";
	public static final String PROFILECENTER = System.getProperty("user.dir")
			+ "/src/main/resources/static/centerImages";
	public static final String UPDATEFORM = "redirect:/updateaccount";

	@Autowired
	public AccountController(AccountService accountService, VisitService visitService) {
		super();
		this.accountService = accountService;
		this.visitService = visitService;
	}

	@GetMapping("inscription")
	public String getNewAccount() {
		return "inscription";
	}

	@GetMapping("authentificationView")
	public String getAuthentificationView() {
		return "page-login";
	}

	@GetMapping("sponsoring.html")
	public String getSponsoringAccount(Model model) {
		model.addAttribute("sponsoring", new Account());
		return "sponsor-form";
	}

	@GetMapping("orphanInscription")
	public String getNewOrphanAccount(Model model) {
		model.addAttribute("orphanaccount", new Account());
		return "orphan-form";
	}

	@GetMapping("sponsor.html")
	public String getListSponsor(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("sponsors", accountService.getAllSponsor());
		return "sponsor";
	}

	@GetMapping("orphanageList")
	public String getListAccount(Model model, Principal principal) {
		Account user = accountService.getByUsername(Integer.parseInt(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("orphanagelist", accountService.getAllOrphanage());
		model.addAttribute("orphanages", accountService.getAllOrphanage());
		model.addAttribute("recentvisit", visitService.getVisitMoreRecent());
		return "accounts-list";
	}

	@GetMapping("completeform")
	public String getForm(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("orphanaccount", new Account());
		return "completeorhphanform";
	}

	@GetMapping("updateaccount")
	public String getFormAccount(Model model, Principal principal) {
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		model.addAttribute("user", account);
		model.addAttribute("updateaccount", account);
		return "updateAccount";
	}

	@PostMapping("updateaccount")
	public String completedForm(@RequestParam("ccreatingdate") String birthday,
			@RequestParam("cvalidation") Optional<String> validation, @RequestParam("story") Optional<String> story,
			@RequestParam("pfirstname") String firstname, @RequestParam("plastname") String lastname,
			@RequestParam("pcni") Optional<Integer> cni, @RequestParam("pnationality") String nationality,
			@RequestParam("cphone") int cphone, @RequestParam("pphone") Optional<Integer> phone,
			@RequestParam("psex") String sex, @RequestParam("idact") int id) {

		Account account = accountService.getAccountById(id);
		account.setCcreatingdate(birthday);
		account.setPfirstname(firstname);
		account.setPlastname(lastname);
		account.setPnationality(nationality);
		account.setCphone(cphone);
		account.setPsex(sex);
		account.setPhoto("on");
		if (validation.isPresent() && story.isPresent() && phone.isPresent() && cni.isPresent()) {
			account.setCvalidation(validation.get());
			account.setStory(story.get());
			account.setPphone(phone.get());
			account.setPcni(cni.get());
		}
		accountService.updateAccount(account);
		return UPDATEFORM;
	}

	@PostMapping("changepassword")
	public String changePassWord(@RequestParam("passwordToTest") String password,
			@RequestParam("newpassword") String newPassword, Principal principal) {
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));

		String passToTest = new BCryptPasswordEncoder().encode(password);
		String newPass = new BCryptPasswordEncoder().encode(newPassword);
		String currentPass = account.getCpassword();

		if (passToTest.equals(currentPass)) {
			account.setCpassword(newPass);
			accountService.updateAccount(account);
			return UPDATEFORM + "?success";
		}
		return UPDATEFORM + "?error";
	}

	@PostMapping("profileImage")
	public String setProfileImage(@RequestParam("pfile") MultipartFile[] pfiles, Principal principal)
			throws IOException {
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		Integer idAccount = account.getIdaccount();
		accountService.saveImageProfile(pfiles, idAccount);
		return UPDATEFORM + "?successprofile";
	}

	@PostMapping("BackgroundImage")
	public String setBackgroundImage(@RequestParam("cfile") MultipartFile[] cfiles, Principal principal)
			throws IOException {
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		Integer idAccount = account.getIdaccount();
		accountService.saveBackgroundImage(cfiles, idAccount);
		return UPDATEFORM + "?successprofile";
	}

	@PostMapping("completeform")
	public String completedForm(@RequestParam("ccreatingdate") String birthday,
			@RequestParam("cvalidation") String validation, @RequestParam("story") String story,
			@RequestParam("pfirstname") String firstname, @RequestParam("plastname") String lastname,
			@RequestParam("pcni") int cni, @RequestParam("pnationality") String nationality,
			@RequestParam("pphone") int phone, @RequestParam("psex") String sex, @RequestParam("idact") int id,
			@RequestParam("cfile") MultipartFile[] cfiles, @RequestParam("pfile") MultipartFile[] pfiles)
			throws IOException {

		Account account = accountService.getAccountById(id);
		account.setCcreatingdate(birthday);
		account.setCvalidation(validation);
		account.setStory(story);
		account.setPfirstname(firstname);
		account.setPlastname(lastname);
		account.setPcni(cni);
		account.setPnationality(nationality);
		account.setPphone(phone);
		account.setPsex(sex);
		account.setPhoto("on");
		accountService.completeAccountInfos(cfiles, pfiles, account);
		return "redirect:/completeform";
	}

	@PostMapping("searchingsponsor")
	public String searchSponsor(@RequestParam("search") String elements, Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("sponsors", accountService.searching(elements));
		model.addAttribute("org", new Organization());
		return "sponsor";
	}

	@PostMapping("searchingorphanage")
	public String searchAccount(@RequestParam("centername") String centerName, @RequestParam("city") String city,
			Model model, Principal principal) {
		Account user = accountService.getByUsername(Integer.parseInt(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("orphanagelist", accountService.getAllOrphanage());
		model.addAttribute("orphanages", accountService.searchOrphanage(centerName, city));
		return "accounts-list";
	}

	@PostMapping("globalsearching")
	public String globalSearchAccount(@RequestParam("search") String elements, Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("orphanages", accountService.searching(elements));
		return "page";
	}

	@PostMapping("addAccount")
	public String createAccount(@ModelAttribute Account account) {

		account.setOrganizationmember(null);
		String passinit = account.getCpassword();
		String passencrypt = new BCryptPasswordEncoder().encode(passinit);
		account.setCpassword(passencrypt);

		accountService.addAccount(account);
		return "redirect:/authentificationView";
	}

}