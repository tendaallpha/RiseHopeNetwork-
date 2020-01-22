package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Suggestion;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
	List<Suggestion> findBySuggestionowner(Account account);
}
