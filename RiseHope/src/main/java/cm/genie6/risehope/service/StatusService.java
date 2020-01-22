package cm.genie6.risehope.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.controller.StatusController;
import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Status;
import cm.genie6.risehope.persistence.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;

	public List<Status> getLastStatusOf(Account account) {
		return statusRepository.findByOwnerstatus(account);
	}

	public Status getLastStatus() {
		return statusRepository.findTop1ByOrderByIdstatusDesc();
	}

	public void saveStatus(Status status) {
		statusRepository.save(status);
	}

	public void saveStatusWithImg(MultipartFile[] files, Status status) throws IOException {
		status = statusRepository.save(status);
		int id = status.getIdstatus();
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(StatusController.STATUSIMAGES, id + "");
			Files.write(fileNameAndPath, files[i].getBytes());
		}
	}

	public Status getIdStatus(Integer id) {
		return statusRepository.findById(id).get();
	}

	public void deleteStatus(Integer id) {
		statusRepository.deleteById(id);
	}
	
}