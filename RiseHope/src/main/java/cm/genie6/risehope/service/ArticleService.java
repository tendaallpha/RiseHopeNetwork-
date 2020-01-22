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
import cm.genie6.risehope.controller.ArticleController;
import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Activity;
import cm.genie6.risehope.model.Article;
import cm.genie6.risehope.persistence.ArticleRepository;
import liquibase.util.file.FilenameUtils;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public List<Article> getAllArticles() {
		return articleRepository.findAllByOrderByIdarticleDesc();
	}

	public void addArticle(Article article) {
		articleRepository.save(article);
	}

	public void addArticleWithImage(MultipartFile[] files, Article article) throws IOException {
		article = articleRepository.save(article);
		int id = article.getIdarticle();
		for (int i = 0; i < 1; i++) {
			Path fileNameAndPath = Paths.get(ArticleController.ARTICLESIMAGES, id + "");
			Files.write(fileNameAndPath, files[i].getBytes());
		}
	}

	public Article getIdArticle(Integer id) {
		return articleRepository.findById(id).get();
	}

	public List<Article> getAllArticleOf(Account account) {
		return articleRepository.findByOwnerarticle(account);
	}

	// public void addArticleWithImage(MultipartFile[] files, Article article)
	// throws IOException {
	//
	// article = articleRepository.save(article);
	// int id = article.getIdarticle();
	// for (int i = 0; i < 1; i++) {
	// String extension =
	// FilenameUtils.getExtension(files[i].getOriginalFilename());
	// Path fileNameAndPath = Paths.get(ArticleController.ARTICLESIMAGES, id +
	// extension);
	// Files.write(fileNameAndPath, files[i].getBytes());
	// }
	// }

}
