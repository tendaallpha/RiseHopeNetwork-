package cm.genie6.risehope.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visit")
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idvisit;
	private String dates;
	@ManyToOne
	@JoinColumn(name = "centervisited", referencedColumnName = "idaccount")
	private Account centervisited;

	public Visit() {
		super();
	}

	public Visit(Integer idvisit, String dates, Account centervisited) {
		super();
		this.idvisit = idvisit;
		this.dates = dates;
		this.centervisited = centervisited;
	}

	public Integer getIdvisit() {
		return idvisit;
	}

	public void setIdvisit(Integer idvisit) {
		this.idvisit = idvisit;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Account getCentervisited() {
		return centervisited;
	}

	public void setCentervisited(Account centervisited) {
		this.centervisited = centervisited;
	}

	public static String addDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return dateFormat.format(date);
	}

}
