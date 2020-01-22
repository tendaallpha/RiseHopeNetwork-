package cm.genie6.risehope.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.controller.ActivityController;
import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Activity;
import cm.genie6.risehope.persistence.ActivityRepository;

@Service
public class ActivityService {

	private ActivityRepository activityRepository;

	@Autowired
	public ActivityService(ActivityRepository activityRepository) {
		super();
		this.activityRepository = activityRepository;
	}

	public void UpdateActivity(Activity activity) {
		activityRepository.save(activity);
	}

	public Activity getActivity(Integer idActivity) {
		return activityRepository.findById(idActivity).get();
	}

	public List<Activity> getAllActivities() {
		return activityRepository.findAllByOrderByIdactivityDesc();
	}
	public Activity getLastActivity() {
		return activityRepository.findTop1ByOrderByIdactivityDesc();
	}
	public List<Activity> getActivitiesOf(Account account) {
		return activityRepository.findByOwneractivity(account);
	}

	public void addActivity(MultipartFile[] files, Activity activity) throws IOException {
		activity = activityRepository.save(activity);
		int id = activity.getIdactivity();
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(ActivityController.ACTIVITYIMAGES, id + "");
			Files.write(fileNameAndPath, files[i].getBytes());
		}
	}
}