package cm.genie6.risehope.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Necessity;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.ChildService;
import cm.genie6.risehope.service.NecessityService;

@Controller
public class NecessityController {
	private NecessityService necessityService;
	private AccountService accountService;
	public static final String NECESSITYIMAGES = System.getProperty("user.dir")
			+ "/src/main/resources/static/necessitiesImages";
	@Autowired
	private ChildService childService;

	@Autowired
	public NecessityController(NecessityService necessityService, AccountService accountService) {
		super();
		this.necessityService = necessityService;
		this.accountService = accountService;
	}

	@GetMapping("/necessity")
	public String getNecessity(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("initialnecessity", new Necessity());
		model.addAttribute("necessities", user.getNecessities());
		return "necessity";
	}

	@GetMapping("/deleteThisNecessity/{idnecessity}")
	public String deleteThisNecessity(@PathVariable("idnecessity") Integer idNecessity) {
		Necessity necessity = necessityService.getIdNecessity(idNecessity);
		necessityService.deleteThisNecessity(necessity);
		return "redirect:/necessity";
	}

	@GetMapping("/centernecessity/{page}")
	public String getCenterNecessities(Model model, @PathVariable("page") Integer id, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		Account userpage = accountService.getAccountById(id);
		model.addAttribute("user", user);
		model.addAttribute("page", userpage);	
		model.addAttribute("necessities", necessityService.getNecessityOf(userpage));
		model.addAttribute("childrens", childService.getAllChildrenOf(userpage));
		return "centernecessity";
	}

	@PostMapping("/newnecessity")
	public String addNecessity(@ModelAttribute Necessity necessity, @RequestParam("owner") Integer owner,
			@RequestParam("file") MultipartFile[] files) throws IOException {
		Account account = accountService.getAccountById(owner);
		necessity.setOwnernecessity(account);
		necessity.setDates(new Date());
		necessityService.saveNecessity(files, necessity);
		return "redirect:/necessity";
	}
}
