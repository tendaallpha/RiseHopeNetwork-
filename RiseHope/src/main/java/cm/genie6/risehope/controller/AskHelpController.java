package cm.genie6.risehope.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.AskHelp;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.AskHelpService;
import cm.genie6.risehope.service.OpportunityService;

@Controller
public class AskHelpController {

	private AccountService accountService;
	private AskHelpService askHelpService;
	private OpportunityService opportunityService;
	public static final int MAX = 5;

	@Autowired
	public AskHelpController(AccountService accountService, AskHelpService askHelpService,
			OpportunityService opportunityService) {
		super();
		this.accountService = accountService;
		this.askHelpService = askHelpService;
		this.opportunityService = opportunityService;
	}

	@GetMapping("askforhelp")
	public String getAskHelpView(Model model, Principal principal) {
		AskHelp askHelp = new AskHelp();
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("sponsors", accountService.getAllSponsor());
		model.addAttribute("askhelp", askHelp);
		model.addAttribute("opportunities", opportunityService.getOpportunities());
		return "askforhelp";
	}

	@PostMapping("/askhelp")
	public String sendAskHelp(Model model, @RequestParam("idreceiver") String idreceivers,
			@ModelAttribute AskHelp askHelp, Principal principal) {
		Account sender = accountService.getByUsername(Integer.parseInt(principal.getName()));
		// recuperer la liste des Iduser
		List<Integer> idusers = Stream.of(idreceivers.split(",")).map(e -> Integer.parseInt(e))
				.collect(Collectors.toList());
		if (idusers.size() <= MAX) {
			// pour chaque Id recuperer le user
			List<Account> accounts = idusers.stream().map(e -> accountService.getAccountById(e))
					.collect(Collectors.toList());
			askHelp.addReceivers(accounts);
			askHelp.setDate(AskHelp.addDate());
			askHelp.setOwneraskhelp(sender);
			askHelpService.saveAskHelp(askHelp);
			return "redirect:/askforhelp?" + "success";
		} else {
			return "redirect:/askforhelp?" + "warning";
		}
	}
}
