package cm.genie6.risehope.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idarticle;
	private String title;
	private String color;
	private String content;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "propritaire")
	private List<Comment> comments = new ArrayList<Comment>();
	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "ownerarticle")
	private Account ownerarticle;
	private String date;
	@ManyToMany
	@JoinTable(name = "publicationlike", joinColumns = @JoinColumn(name = "idpublication", referencedColumnName = "idarticle"), inverseJoinColumns = @JoinColumn(name = "iduser", referencedColumnName = "idaccount"))
	private List<Account> userlike = new ArrayList<>();

	public Article() {
	}

	public Article(String title, String content, Account ownerarticle) {
		super();
		this.title = title;
		this.content = content;
		this.ownerarticle = ownerarticle;
	}

	public Integer getIdarticle() {
		return idarticle;
	}

	public void setIdarticle(Integer idarticle) {
		this.idarticle = idarticle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Account getOwnerarticle() {
		return ownerarticle;
	}

	public void setOwnerarticle(Account ownerarticle) {
		this.ownerarticle = ownerarticle;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Account> getUserlike() {
		return userlike;
	}

	public void setUserlike(List<Account> userlike) {
		this.userlike = userlike;
	}

	public void addLike(Account account) {
		if (!userlike.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount()))) {
			this.userlike.add(account);
		}

	}

	public boolean liked(Account account) {
		return userlike.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount()));
	}

	public int countLike() {
		return userlike.size();
	}
	public int countArticleComment() {
		return comments.size();
	}
	public static String addDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return dateFormat.format(date);
	}
}