package cm.genie6.risehope.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import cm.genie6.risehope.model.Article;
import cm.genie6.risehope.model.Comment;
import cm.genie6.risehope.model.Organization;
import cm.genie6.risehope.model.Status;
import cm.genie6.risehope.model.Suggestion;
import cm.genie6.risehope.model.Visit;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.ActivityService;
import cm.genie6.risehope.service.ArticleService;
import cm.genie6.risehope.service.ChildService;
import cm.genie6.risehope.service.MailService;
import cm.genie6.risehope.service.PlanningService;
import cm.genie6.risehope.service.StatusService;
import cm.genie6.risehope.service.SuggestionService;
import cm.genie6.risehope.service.VisitService;

@Controller
public class DefaultController {

	private ArticleService articleService;
	private StatusService statusService;
	private PlanningService planningService;
	private AccountService accountService;
	private MailService mailService;
	private ChildService childService;
	private SuggestionService suggestionService;
	private ActivityService activityService;
	private VisitService visitService;

	public static final String LOADGALLERY = System.getProperty("user.dir") + "/src/main/resources/static/gallery/";
	public static final String HOME = "redirect:/home";

	@Autowired
	public DefaultController(ArticleService articleService, StatusService statusService,
			PlanningService planningService, AccountService accountService, MailService mailService,
			ChildService childService, SuggestionService suggestionService, ActivityService activityService,
			VisitService visitService) {
		super();
		this.articleService = articleService;
		this.statusService = statusService;
		this.planningService = planningService;
		this.accountService = accountService;
		this.mailService = mailService;
		this.childService = childService;
		this.suggestionService = suggestionService;
		this.activityService = activityService;
		this.visitService = visitService;
	}

	@GetMapping("/authentification")
	public String getAuthentification() {
		return "page-login";
	}

	@GetMapping("selfpage/{page}")
	public String loadAndSaveInformations(@PathVariable("page") Integer id, Principal principal) {
		Account userpage = accountService.getAccountById(id);
		// Account currentUser =
		// accountService.getByUsername(Integer.parseInt(principal.getName()));
		// if (currentUser.getIdaccount().equals(userpage.getIdaccount())) {
		Visit visit = new Visit();
		visit.setDates(Visit.addDate());
		visit.setCentervisited(userpage);
		visitService.saveVisit(visit);
		// }
		return "redirect:/selfpages/" + id;
	}

	@GetMapping("selfpages/{page}")
	public String getSelfpage(Model model, @PathVariable("page") Integer id, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		Account userpage = accountService.getAccountById(id);
		model.addAttribute("user", user);
		model.addAttribute("page", userpage);
		model.addAttribute("plannings", planningService.getPlanning());
		model.addAttribute("articles", articleService.getAllArticleOf(userpage));
		model.addAttribute("childrens", childService.getAllChildrenOf(userpage));
		model.addAttribute("visitedActivities", userpage.getActivitiesLikes());
		model.addAttribute("articleliked", userpage.getArticlelike());
		return "page";
	}

	@GetMapping("gallery.html")
	public String getGallery(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		return "gallery";
	}

	@GetMapping({ "/", "home" })
	public String getHome(Model model, Principal principal, @RequestParam("block") Optional<Integer> block) {
		String username = principal.getName();
		Account currentUser = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", currentUser);
		model.addAttribute("articles", articleService.getAllArticles());
		model.addAttribute("initialActivity", new Activity());
		model.addAttribute("activities", activityService.getLastActivity());
		model.addAttribute("askhelp", currentUser.getAskhelps().size());
		model.addAttribute("org", new Organization());
		// Integer counter = articles.stream().mapToInt(e -> e.countLike()).sum();

		List<Account> accounts = accountService.getAllAccount();
		accounts.remove(currentUser);
		model.addAttribute("accounts", accounts);
		if (block.isPresent()) {
			Account receiver = accountService.getAccountById(block.get());
			model.addAttribute("center", receiver);
			model.addAttribute("mails", mailService.getMailsBetween(currentUser, receiver));
		}
		return "home";
	}

	@GetMapping("suggestion")
	public String getSuggestion(Model model, Principal principal) {
		String userName = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(userName));
		model.addAttribute("user", user);
		model.addAttribute("suggestion", new Suggestion());
		model.addAttribute("suggestions", suggestionService.getAllSuggestionOf(user));
		return "suggestion";
	}

	@PostMapping("suggestion")
	public String saveSuggestion(@ModelAttribute Suggestion suggestion,
			@RequestParam("idowner") Integer idOwnerSuggestion) {
		Account account = accountService.getAccountById(idOwnerSuggestion);
		suggestion.setSuggestionOwner(account);
		suggestion.setDate(Suggestion.addDate());
		suggestionService.saveSuggestion(suggestion);
		return "redirect:/suggestion";
	}

	@PostMapping("commentstatus")
	public String commentStatus(@RequestParam("owner") Integer owner, @RequestParam("ideditor") Integer editor,
			@RequestParam("statusid") Integer statusid, @RequestParam("comment") String comment) {
		Account accountowner = accountService.getAccountById(owner);
		Status statusowner = statusService.getIdStatus(statusid);
		Comment addcomments = new Comment(null, comment, null, accountowner, statusowner);
		statusowner.getComments().add(addcomments);
		statusService.saveStatus(statusowner);
		return "redirect:/selfpage/" + editor;
	}

	@PostMapping("commentarticle")
	public String commentArticle(@RequestParam("owner") Integer owner, @RequestParam("articleid") Integer articleid,
			@RequestParam("comment") String comment) {
		Account ownerid = accountService.getAccountById(owner);
		Article article = articleService.getIdArticle(articleid);
		Comment addcomments = new Comment(null, comment, article, ownerid, null);
		article.getComments().add(addcomments);
		articleService.addArticle(article);
		return HOME;
	}

	@PostMapping("/commentarticle/{page}")
	public String commentArticleOnPage(@RequestParam("owner") Integer owner,
			@RequestParam("articleid") Integer articleid, @RequestParam("comment") String comment,
			@PathVariable("page") Integer page) {
		Account ownerid = accountService.getAccountById(owner);
		Article article = articleService.getIdArticle(articleid);
		Comment addcomments = new Comment(null, comment, article, ownerid, null);
		article.getComments().add(addcomments);
		articleService.addArticle(article);
		return "redirect:/selfpage/" + page;
	}

	@GetMapping("cstory/{page}")
	public String getCenterPlanning(Model model, @PathVariable("page") Integer id, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		Account userpage = accountService.getAccountById(id);
		model.addAttribute("page", userpage);
		return "cstory";
	}
	
	@GetMapping("englishOption")
	public String getEnglishLanguage() {
		return HOME+"?lang=en";
	}
	@GetMapping("frenchOption")
	public String getFrenchLanguage() {
		return HOME+"?lang=fr";
	}

	@GetMapping("getgallery")
	public String galleryLoader(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("loading", filesOfFolders());
		return "gallery";
	}

	@PostMapping("uploadgallery.html?successupload")
	public String uploadGallery(@RequestParam("file") MultipartFile[] files, Principal principal) throws IOException {
		String username = principal.getName();
		accountService.uploadGallery(files, username);
		return "redirect:/getgallery";
	}

	private static List<String> filesOfFolders() {
		File folder = new File(LOADGALLERY);
		File[] contents = folder.listFiles();
		List<String> results = new ArrayList<>();

		for (File element : contents) {
			if (element.isFile()) {
				results.add("/gallery/" + element.getName());
			}
		}
		return results;
	}

}