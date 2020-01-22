package cm.genie6.risehope.controller;

import java.io.IOException;
import java.security.Principal;
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
import cm.genie6.risehope.model.Article;
import cm.genie6.risehope.service.AccountService;
import cm.genie6.risehope.service.ArticleService;

@Controller
public class ArticleController {

	private ArticleService articleService;
	private AccountService accountService;
	public static final String ARTICLESIMAGES = System.getProperty("user.dir")
			+ "/src/main/resources/static/articlesImages";
	private String message;

	@Autowired
	public ArticleController(ArticleService articleService, AccountService accountService) {
		super();
		this.articleService = articleService;
		this.accountService = accountService;
	}

	@GetMapping("addArticle")
	public String addArticle(Model model, Principal principal) {
		String username = principal.getName();
		Account user = accountService.getByUsername(Integer.parseInt(username));
		model.addAttribute("user", user);
		model.addAttribute("addArticle", new Article());
		model.addAttribute("msg", message);
		message = null;
		return "addArticle";
	}

	@GetMapping("incrementarticlelove/{idlike}")
	public String incrementLove(@PathVariable("idlike") Integer idlike, Principal principal) {
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		Article article = articleService.getIdArticle(idlike);
		article.addLike(account);
		articleService.addArticle(article);
		return "redirect:/home";
	}

	@GetMapping("loveArticle/{idlike}/{page}")
	public String loveArticle(@PathVariable("idlike") Integer idlike, @PathVariable("page") Integer page,
			Principal principal) {
		Account account = accountService.getByUsername(Integer.parseInt(principal.getName()));
		Article article = articleService.getIdArticle(idlike);
		article.addLike(account);
		articleService.addArticle(article);
		return "redirect:/selfpage/" + page;
	}

	@PostMapping("addArticle")
	public String addArticleWithImg(@ModelAttribute Article article,
			@RequestParam("file") Optional<MultipartFile[]> optionalFile, @RequestParam("userid") Integer currentUser)
			throws IOException {
		if (optionalFile.isPresent()) {
			Account owner = accountService.getAccountById(currentUser);
			article.setOwnerarticle(owner);
			article.setDate(Article.addDate());
			articleService.addArticleWithImage(optionalFile.get(), article);
		} else {
			message = "Veillez Y Joindre une Image";
		}
		return "redirect:/addArticle";
	}

	@PostMapping("/addstatus")
	public String addStatus(@RequestParam("content") String content, @RequestParam("userid") Integer currentUser,
			@RequestParam("file") Optional<MultipartFile[]> optionalFile) throws IOException {
		if (optionalFile.isPresent()) {
			Account owner = accountService.getAccountById(currentUser);
			Article article = new Article();
			article.setContent(content);
			article.setOwnerarticle(owner);
			article.setDate(Article.addDate());
			articleService.addArticleWithImage(optionalFile.get(), article);
		}
		return "redirect:/selfpage/" + currentUser;
	}

	@PostMapping("/addStatus")
	public String newStatus(@RequestParam("content") String content, @RequestParam("userid") Integer currentUser,
			@RequestParam("file") Optional<MultipartFile[]> optionalFile) throws IOException {
		if (optionalFile.isPresent()) {
			Account owner = accountService.getAccountById(currentUser);
			Article article = new Article();
			article.setContent(content);
			article.setOwnerarticle(owner);
			article.setDate(Article.addDate());
			articleService.addArticleWithImage(optionalFile.get(), article);
		}
		return "redirect:/home";
	}

}