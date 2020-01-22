package cm.genie6.risehope.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Status;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.StatusService;

@Controller
public class StatusController {

	@Autowired
	private StatusService statusService;
	@Autowired
	private AccountService accountService;

	public static final String STATUSIMAGES = System.getProperty("user.dir")
			+ "/src/main/resources/static/statusimages";
	public static final String HOME = "redirect:/home";
	public static final String PAGE = "redirect:/selfpage/";

	@PostMapping("publishstatus")
	public String addStatus(@RequestParam("filests") MultipartFile[] files, @RequestParam("userid") Integer userid,
			@RequestParam("stscomment") String stscomment) throws IOException {
		Account owner = accountService.getAccountById(userid);
		Status status = new Status(null, stscomment, Status.addDate(), 0, owner);
		statusService.saveStatusWithImg(files, status);
		return PAGE + userid;
	}

	@GetMapping("incrementlovestatus/{idlike}/{currentuser}")
	public String incrementLoveSts(@PathVariable("idlike") Integer idlike,
			@PathVariable("currentuser") Integer currentuser, Principal principal) {
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		Status status = statusService.getIdStatus(idlike);
		status.addLove(account);
		statusService.saveStatus(status);
		return PAGE + currentuser;
	}

	@GetMapping("/delete/{iddelete}/{currentuser}")
	public String delete(@PathVariable("iddelete") Integer id, @PathVariable("currentuser") Integer currentuser) {
		statusService.deleteStatus(id);
		return PAGE + currentuser;
	}
}