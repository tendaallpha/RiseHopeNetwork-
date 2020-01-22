package cm.genie6.risehope.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planning")
public class Planning {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idplanning;
	private String timeb;
	private String timee;
	private String day;
	private String activity;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "ownerplanning")
	private Account ownerplanning;

	public Planning() {
	}

	public Planning(Integer idplanning, String timeb, String timee, String day, String activity,
			Account ownerplanning) {
		super();
		this.idplanning = idplanning;
		this.timeb = timeb;
		this.timee = timee;
		this.day = day;
		this.activity = activity;
		this.ownerplanning = ownerplanning;
	}

	public Integer getIdplanning() {
		return idplanning;
	}

	public void setIdplanning(Integer idplanning) {
		this.idplanning = idplanning;
	}

	public String getTimeb() {
		return timeb;
	}

	public void setTimeb(String timeb) {
		this.timeb = timeb;
	}

	public String getTimee() {
		return timee;
	}

	public void setTimee(String timee) {
		this.timee = timee;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Account getOwnerplanning() {
		return ownerplanning;
	}

	public void setOwnerplanning(Account ownerplanning) {
		this.ownerplanning = ownerplanning;
	}

}
