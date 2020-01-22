package cm.genie6.risehope.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Suggestion;
import cm.genie6.risehope.persistence.SuggestionRepository;

@Service
public class SuggestionService {
	@Autowired
	private SuggestionRepository  suggestionRepository;
	public void saveSuggestion(Suggestion suggestion) {
		suggestionRepository.save(suggestion);
	}
	public List<Suggestion> getAllSuggestionOf(Account account){
		return suggestionRepository.findBySuggestionowner(account);
	}
}
