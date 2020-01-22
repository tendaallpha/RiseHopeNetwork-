package cm.genie6.risehope.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.AskHelp;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.AskHelpService;

@Controller
public class AskedHelpController {

	private AccountService accountService;
	private AskHelpService askHelpService;

	@Autowired
	public AskedHelpController(AccountService accountService, AskHelpService askHelpService) {
		super();
		this.accountService = accountService;
		this.askHelpService = askHelpService;
	}

	@GetMapping("askedhelp")
	public String getAskHelpView(Model model, Principal principal, @RequestParam("value") Optional<Integer> value) {
		Account user = accountService.getByUsername(Integer.parseInt(principal.getName()));
		model.addAttribute("user", user);
		model.addAttribute("askedHelp", askHelpService.getAllAskHelp());
		if (value.isPresent()) {
			AskHelp askHelp = askHelpService.getIdOfAskHelp(value.get());
			model.addAttribute("askHelptodelete", askHelp);
		}
		return "askedHelp";
	}

	@GetMapping("/deleteaskedhelp/{idaskedhelp}")
	public String deleteAskedHelp(@PathVariable("idaskedhelp") Integer idAskedHelp, Principal principal) {
		Account currentUser = accountService.getByUsername(Integer.parseInt(principal.getName()));
		AskHelp askHelp = askHelpService.getIdOfAskHelp(idAskedHelp);
		askHelp.removeCurrentUser(currentUser);
		askHelpService.saveAskHelp(askHelp);
		return "redirect:/askedhelp";
	}

	@GetMapping("/warningaskedhelp/{askedhelp}")
	public String getWarningAskedHelp(@PathVariable("askedhelp") Integer idaskedhelp) {
		return "redirect:/askedhelp?" + "value=" + idaskedhelp;
	}

}