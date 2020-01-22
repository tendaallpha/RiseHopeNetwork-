package cm.genie6.risehope.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Opportunity;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.OpportunityService;

@Controller
public class OpportunityController {

	private OpportunityService opportunityService;
	private AccountService accountService;

	@Autowired
	public OpportunityController(OpportunityService opportunityService, AccountService accountService) {
		super();
		this.opportunityService = opportunityService;
		this.accountService = accountService;
	}

	@PostMapping("addopportunity")
	public String saveOpportunity(@RequestParam("link") String link, @RequestParam("description") String description,
			Principal principal) {
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		Opportunity opportunity = new Opportunity();
		opportunity.setLink(link);
		opportunity.setDescription(description);
		opportunity.setOwneropportunity(account);
		opportunityService.saveOpportunity(opportunity);
		return "redirect:/home";
	}

}
