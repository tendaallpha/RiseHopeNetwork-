package cm.genie6.risehope.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Planning;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.PlanningService;

@Controller
public class PlanningController {

	private PlanningService planningService;
	private AccountService accountService;

	@Autowired
	public PlanningController(PlanningService planningService, AccountService accountService) {
		super();
		this.planningService = planningService;
		this.accountService = accountService;
	}

	@GetMapping("planning.html")
	public String getPlanning(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("planningEnter", new Planning());
		model.addAttribute("plannings", user.getPlannings());
		return "planning";
	}

	@GetMapping("/deleteplanningactivity/{idactivity}")
	public String deletePlanningActivity(@PathVariable("idactivity") Integer idactivity) {
		Planning planning = planningService.getIdplanning(idactivity);
		planningService.deletePlanningActivity(planning);
		return "redirect:/planning.html";
	}

	@GetMapping("cplanning/{page}")
	public String getCenterPlanning(Model model, @PathVariable("page") Integer id, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		Account userpage = accountService.getAccountById(id);
		model.addAttribute("user", user);
		model.addAttribute("page", userpage);
		model.addAttribute("plannings", planningService.getPlanningOf(userpage));
		return "cplanning";
	}

	@PostMapping("newplanning")
	public String setPlanning(@ModelAttribute Planning planning, @RequestParam("owner") Integer owner) {
		Account account = accountService.getAccountById(owner);
		planning.setOwnerplanning(account);
		planningService.savePlanning(planning);
		return "redirect:/planning.html";
	}

}
