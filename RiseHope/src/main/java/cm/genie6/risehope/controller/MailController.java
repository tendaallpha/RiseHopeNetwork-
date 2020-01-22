package cm.genie6.risehope.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Mail;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.MailService;

@Controller
public class MailController {
	@Autowired
	private MailService mailService;
	@Autowired
	private AccountService accountService;

	@GetMapping("chatwith/{idreceiver}")
	public String getReceiver(@PathVariable("idreceiver") Integer idreceiver) {
		return "redirect:/home?" + "block=" + idreceiver;
	}

	@GetMapping("/chatwith/{page}/{idreceiver}")
	public String getReceiverMail(@PathVariable("page") Integer currentUser,
			@PathVariable("idreceiver") Integer idreceiver) {
		return "redirect:/selfpage/" + currentUser + "?block=" + idreceiver;
	}

	@PostMapping("/sentmail")
	public String setViewArticle(@RequestParam("idreceiver") Integer id, @RequestParam("content") String content,
			Principal principal) {
		Account sender = accountService.getByUsername(Integer.parseInt(principal.getName()));
		Account receiver = accountService.getAccountById(id);
		Mail mail = new Mail(null, Mail.addDate(), content, sender, receiver);
		mailService.savemails(mail);
		return "redirect:/home?" + "block=" + id;
	}
}
