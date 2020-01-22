package cm.genie6.risehope.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

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
import cm.genie6.risehope.model.Child;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.ChildService;

@Controller
public class ChildController {

	private ChildService childService;
	private AccountService accountService;
	public static final String CHILDRENPROFILE = System.getProperty("user.dir")
			+ "/src/main/resources/static/childrenprofile";

	@Autowired
	public ChildController(ChildService childService, AccountService accountService) {
		super();
		this.childService = childService;
		this.accountService = accountService;
	}

	@GetMapping("childrenlist.html")
	public String getChildren(Model model, Principal principal, @RequestParam("value") Optional<Integer> value) {
		Child child = new Child();
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("children", child);
		model.addAttribute("childrens", childService.getAllChildrenOf(user));
		if (value.isPresent()) {
			Child child2 = childService.getIdChild(value.get());
			model.addAttribute("childtodelete", child2);
		}
		return "child-list";
	}

	@GetMapping("/updatechild/{childtoupdate}")
	public String getChildUpdate(Model model, Principal principal,
			@PathVariable("childtoupdate") Integer childToUpdate) {
		Child child = childService.getIdChild(childToUpdate);
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("child", child);
		return "updatechild";
	}

	@GetMapping("/adopted/{adoption}")
	public String adopted(@PathVariable("adoption") Integer idchild) {
		Child child = childService.getIdChild(idchild);
		if (!child.isAdopted()) {
			child.setAdopted(true);
			childService.saveChild(child);
		} else {
			child.setAdopted(false);
			childService.saveChild(child);
		}
		return "redirect:/childrenlist.html";
	}

	@GetMapping("/deletechile/{idchild}")
	public String deleteChild(@PathVariable("idchild") Integer idChild) {
		Child child = childService.getIdChild(idChild);
		childService.deleteChild(child);
		return "redirect:/childrenlist.html";
	}

	@GetMapping("/warning/{child}")
	public String getWarning(@PathVariable("child") Integer idchild) {
		return "redirect:/childrenlist.html?" + "value=" + idchild;
	}

	@PostMapping("addChild")
	public String saveSomChid(@ModelAttribute Child child, @RequestParam("idorphanage") Integer id,
			@RequestParam("file") MultipartFile[] files) throws IOException {
		Account ownerchild = accountService.getAccountById(id);
		child.setOwnerchildren(ownerchild);
		childService.saveChild(child, files);
		return "redirect:/childrenlist.html";
	}

	@GetMapping("/adoptions")
	public String getSuggectAdoption(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("children", childService.getAllSuggetstionAdoptions());
		return "adoption";
	}

	@PostMapping("updatechild")
	public String updateChild(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
			@RequestParam("birthday") String birthday, @RequestParam("sex") String sex,
			@RequestParam("sponsored") String sponsored, @RequestParam("description") String description,
			@RequestParam("idchild") Integer idchild, @RequestParam("file") Optional<MultipartFile[]> optionalFiles)
			throws IOException {
		Child child = childService.getIdChild(idchild);
		child.setFirstname(firstname);
		child.setLastname(lastname);
		child.setBirthday(birthday);
		child.setSex(sex);
		child.setSponsored(sponsored);
		child.setDescription(description);
		if (!optionalFiles.isPresent()) {
			childService.saveChild(child);
			return "redirect:/childrenlist.html";
		} else {
			childService.saveChild(child, optionalFiles.get());
			return "redirect:/childrenlist.html";
		}

	}
}