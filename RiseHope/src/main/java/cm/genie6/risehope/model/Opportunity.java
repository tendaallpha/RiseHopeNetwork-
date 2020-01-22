package cm.genie6.risehope.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "opportunity")
public class Opportunity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idopportunity;
	private String link;
	private String description;
	@ManyToOne
	@JoinColumn(name = "owneropportunity", referencedColumnName = "idaccount")
	private Account owneropportunity;

	public Opportunity() {
	}

	public Opportunity(Integer idopportunity, String link, String description, Account owneropportunity) {
		super();
		this.idopportunity = idopportunity;
		this.link = link;
		this.description = description;
		this.owneropportunity = owneropportunity;
	}

	public Integer getIdopportunity() {
		return idopportunity;
	}

	public void setIdopportunity(Integer idopportunity) {
		this.idopportunity = idopportunity;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getOwneropportunity() {
		return owneropportunity;
	}

	public void setOwneropportunity(Account owneropportunity) {
		this.owneropportunity = owneropportunity;
	}

}