package cm.genie6.risehope.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Mail;
import cm.genie6.risehope.persistence.MailRepository;

@Service
public class MailService {

	@Autowired
	private MailRepository mailRepository;

	public List<Mail> getAllMail() {
		return mailRepository.findAll();
	}

	public Mail getIdMail(Integer id) {
		return mailRepository.findById(id).get();
	}

	public void savemails(Mail mail) {
		mailRepository.save(mail);
	}

	public List<Mail> getMailsBetween(Account sender, Account receiver) {
		List<Account> persons = Arrays.asList(sender, receiver);
		return mailRepository.findBySendermailsInAndReceivermailsIn(persons, persons);
	}

}
