package cm.genie6.risehope.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.controller.ChildController;
import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Child;
import cm.genie6.risehope.persistence.ChildRepository;

@Service
public class ChildService {

	@Autowired
	private ChildRepository childRepository;

	public List<Child> getAllChildren() {
		return childRepository.findAll();
	}

	public List<Child> getAllChildrenOf(Account account) {
		return childRepository.findByOwnerchildren(account);
	}

	public void saveChild(Child child, MultipartFile[] files) throws IOException {
		child = childRepository.save(child);
		int id = child.getIdchild();
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(ChildController.CHILDRENPROFILE, id + "");
			Files.write(fileNameAndPath, files[i].getBytes());
		}
	}

	public void saveChild(Child child) {
		childRepository.save(child);
	}

	public Child getIdChild(Integer id) {
		return childRepository.findById(id).get();
	}

	public List<Child> getAllSuggetstionAdoptions() {
		boolean adopted = true;
		return childRepository.findByAdopted(adopted);
	}

	public void deleteChild(Child child) {
		childRepository.delete(child);
	}
}
