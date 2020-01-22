package cm.genie6.risehope.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.genie6.risehope.model.AskHelp;
import cm.genie6.risehope.persistence.AskHelpRepository;

@Service
public class AskHelpService {

	@Autowired
	private AskHelpRepository askHelpRepository;

	public List<AskHelp> getAllAskHelp() {
		return askHelpRepository.findAll();
	}

	public AskHelp getIdOfAskHelp(Integer id) {
		return askHelpRepository.findById(id).get();
	}

	public void saveAskHelp(AskHelp askHelp) {
		askHelpRepository.save(askHelp);
	}


}
