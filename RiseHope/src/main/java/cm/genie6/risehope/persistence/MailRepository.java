package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Mail;

@Repository
public interface MailRepository extends JpaRepository<Mail, Integer> {
	List<Mail> findBySendermailsInAndReceivermailsIn(List<Account> sender, List<Account> receiver);
}
