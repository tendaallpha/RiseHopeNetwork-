package cm.genie6.risehope.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idimage;
	private String name;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "accountowner")
	private Account accountowner;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idarticle", name = "articleowner")
	private Article articleowner;

	public Image() {
	}

	public Image(Integer idimage, String name /*, Account accountowner*/, Article articleowner) {
		super();
		this.idimage = idimage;
		this.name = name;
		/*this.accountowner = accountowner;*/
		this.articleowner = articleowner;
	}

	public Integer getIdimage() {
		return idimage;
	}

	public void setIdimage(Integer idimage) {
		this.idimage = idimage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccountowner() {
		return accountowner;
	}

	public void setAccountowner(Account accountowner) {
		this.accountowner = accountowner;
	}

	public Article getArticleowner() {
		return articleowner;
	}

	public void setArticleowner(Article articleowner) {
		this.articleowner = articleowner;
	}

}
