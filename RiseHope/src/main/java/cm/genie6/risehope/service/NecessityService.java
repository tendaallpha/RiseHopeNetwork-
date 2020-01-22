package cm.genie6.risehope.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.controller.NecessityController;
import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Necessity;
import cm.genie6.risehope.persistence.NecessityRepository;

@Service
public class NecessityService {

	private NecessityRepository necessityRepository;

	@Autowired
	public NecessityService(NecessityRepository necessityRepository) {
		super();
		this.necessityRepository = necessityRepository;
	}

	public void saveNecessity(MultipartFile[] files, Necessity necessity) throws IOException {
		necessity = necessityRepository.save(necessity);
		int id = necessity.getIdnecessity();
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(NecessityController.NECESSITYIMAGES, id + "");
			Files.write(fileNameAndPath, files[i].getBytes());
		}
	}

	public void updateNecessity(Necessity necessity) {
		necessityRepository.save(necessity);
	}

	public Necessity getIdNecessity(Integer idNecessity) {
		return necessityRepository.findById(idNecessity).get();
	}

	public List<Necessity> getNecessities() {
		return necessityRepository.findAll();
	}

	public List<Necessity> getNecessityOf(Account id) {
		return necessityRepository.findByOwnernecessity(id);
	}

	public void deleteThisNecessity(Necessity necessity) {
		necessityRepository.delete(necessity);
	}
}
