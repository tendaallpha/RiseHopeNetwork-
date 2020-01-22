package cm.genie6.risehope.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Activity;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.ActivityService;

@Controller
public class ActivityController {

	private ActivityService activityService;
	private AccountService accountService;
	public static final String ACTIVITYIMAGES = System.getProperty("user.dir")
			+ "/src/main/resources/static/activityImages";

	@Autowired
	public ActivityController(ActivityService activityService, AccountService accountService) {
		super();
		this.activityService = activityService;
		this.accountService = accountService;
	}

	@GetMapping("/activity/{page}")
	public String getActivity(Model model, Principal principal, @PathVariable("page") Integer idActivity) {
		Activity activity = activityService.getActivity(idActivity);
		Account user = accountService.getByUsername(Integer.parseInt(principal.getName()));
		activity.addView(user);
		activityService.UpdateActivity(activity);

		model.addAttribute("user", user);
		model.addAttribute("page", activity);
		return "activity";
	}

	@GetMapping("/like/{activity}")
	public String likeActivity(@PathVariable("activity") Integer idActivity, Principal principal) {
		Activity activity = activityService.getActivity(idActivity);
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		activity.addLike(account);
		activityService.UpdateActivity(activity);
		return "redirect:/activity/" + idActivity;
	}

	@GetMapping("/activities")
	public String getAtivitiesList(Model model, Principal principal) {
		List<Activity> activities = activityService.getAllActivities();
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		model.addAttribute("user", account);
		model.addAttribute("initialActivity", new Activity());
		model.addAttribute("activities", activities);
		return "activities";
	}

	@PostMapping("/addActivity")
	public String addActivity(@ModelAttribute Activity activity, @RequestParam("file") MultipartFile[] files,
			@RequestParam("owner") Integer userid) throws IOException {
		Account owner = accountService.getAccountById(userid);
		activity.setOwneractivity(owner);
		activity.setDates(Activity.addDate());
		activityService.addActivity(files, activity);
		return "redirect:/home";
	}
}
