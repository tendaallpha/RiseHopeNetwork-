package cm.genie6.risehope.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cm.genie6.risehope.model.Account;
import cm.genie6.risehope.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

	List<Article> findByOwnerarticle(Account idowner);
	List<Article> findAllByOrderByIdarticleDesc();
}
