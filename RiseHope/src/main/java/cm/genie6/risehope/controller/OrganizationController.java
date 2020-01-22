package cm.genie6.risehope.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Organization;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.OrganizationService;

@Controller
public class OrganizationController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private OrganizationService organizationService;

	@GetMapping("organization")
	public String getOrg(Model model, Principal principal) {
		Organization organization = new Organization();
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("org", organization);
		List<Account> members = accountService.getAllAccount();
		members.remove(user);
		model.addAttribute("users", members);
		return "organization";
	}
	
	@GetMapping("organization/{orgs}")
public String getOrg(Model model, Principal principal, @PathVariable("orgs") Integer id) {
	String username = principal.getName();
	Account user = accountService.getByUsername(Integer.parseInt(username));
	Organization organization = organizationService.getIdOrganization(id);
	model.addAttribute("user", user);
	model.addAttribute("org", organization);
	List<Account> members = accountService.getAllAccount();
	members.remove(user);
	model.addAttribute("users",members);
	return "organization";
}

	@GetMapping("organizationlist")
	public String getOrgList(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("organizations", organizationService.getOrnizationOf(user));
		return "organizationlist";
	}

	@PostMapping("createOrganization")
	public String createOrganisation(@ModelAttribute Organization organization,
			@RequestParam("idadmin") Integer idadmin) {
		Account account = accountService.getAccountById(idadmin);
		List<Account> accounts = new ArrayList<>();
		accounts.add(account);
		organization.setUsermember(accounts);
		organizationService.saveOrganization(organization);
		return "redirect:/organizationlist";
	}

	@PostMapping("addmembers")
	public String addMembers(@RequestParam("idmember") String member, @ModelAttribute Organization organization) {

		// recuperer la liste des Iduser
		List<Integer> ids = Stream.of(member.split(",")).map(e -> Integer.parseInt(e)).collect(Collectors.toList());
		// pour chaque Id recuperer le user
		List<Account> accounts = ids.stream().map(e -> accountService.getAccountById(e)).collect(Collectors.toList());
		organization.addMembers(accounts);

		organizationService.saveOrganization(organization);
		return "redirect:/organization";
	}
}
