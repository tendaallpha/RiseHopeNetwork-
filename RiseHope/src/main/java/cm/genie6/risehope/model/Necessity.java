package cm.genie6.risehope.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "necessity")
public class Necessity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idnecessity;
	private String necessitytitle;
	private String description;
	private Date dates;
	@ManyToOne
	@JoinColumn(name = "ownernecessity", referencedColumnName = "idaccount")
	private Account ownernecessity;

	public Necessity() {
	}

	public Necessity(Integer idnecessity, String necessitytitle, String description, Date date,
			Account ownernecessity) {
		super();
		this.idnecessity = idnecessity;
		this.necessitytitle = necessitytitle;
		this.description = description;
		this.dates = date;
		this.ownernecessity = ownernecessity;
	}

	public Integer getIdnecessity() {
		return idnecessity;
	}

	public void setIdnecessity(Integer idnecessity) {
		this.idnecessity = idnecessity;
	}

	public String getNecessitytitle() {
		return necessitytitle;
	}

	public void setNecessitytitle(String necessitytitle) {
		this.necessitytitle = necessitytitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

	public Account getOwnernecessity() {
		return ownernecessity;
	}

	public void setOwnernecessity(Account ownernecessity) {
		this.ownernecessity = ownernecessity;
	}

}
