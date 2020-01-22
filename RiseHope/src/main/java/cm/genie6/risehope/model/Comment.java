package cm.genie6.risehope.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcomment;
	private String text;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idarticle", name = "propritaire")
	private Article propritaire;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "userowner")
	private Account userowner;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idstatus", name = "statusowner")
	private Status statusowner;

	public Comment() {
	}

	public Comment(Integer idcomment, String text, Article propritaire, Account userowner,
			Status statusowner) {
		super();
		this.idcomment = idcomment;
		this.text = text;
		this.propritaire = propritaire;
		this.userowner = userowner;
		this.statusowner = statusowner;
	}

	public Integer getIdcomment() {
		return idcomment;
	}

	public void setIdcomment(Integer idcomment) {
		this.idcomment = idcomment;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Article getPropritaire() {
		return propritaire;
	}

	public void setPropritaire(Article propritaire) {
		this.propritaire = propritaire;
	}

	public Account getUserowner() {
		return userowner;
	}

	public void setUserowner(Account userowner) {
		this.userowner = userowner;
	}

	public Status getStatusowner() {
		return statusowner;
	}

	public void setStatusowner(Status statusowner) {
		this.statusowner = statusowner;
	}

}
