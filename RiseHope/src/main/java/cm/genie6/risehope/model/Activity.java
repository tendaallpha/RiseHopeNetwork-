package cm.genie6.risehope.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "activity")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idactivity;
	private String title;
	private String goal;
	private String financingrole;
	private String dates;
	@ManyToOne
	@JoinColumn(name = "owneractivity", referencedColumnName = "idaccount")
	private Account owneractivity;

	@ManyToMany
	@JoinTable(name = "activitylike", joinColumns = @JoinColumn(name = "activityid", referencedColumnName = "idactivity"), inverseJoinColumns = @JoinColumn(name = "iduser", referencedColumnName = "idaccount"))
	private List<Account> userlike = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "activityview", joinColumns = @JoinColumn(name = "activityid", referencedColumnName = "idactivity"), inverseJoinColumns = @JoinColumn(name = "iduser", referencedColumnName = "idaccount"))
	private List<Account> userview = new ArrayList<>();

	public static final Comparator<Activity> SORT_BY_ID_DESC = new Comparator<Activity>() {

		@Override
		public int compare(Activity o1, Activity o2) {
			return o2.getIdactivity().compareTo(o1.getIdactivity());
		}
	};

	public Activity() {
		super();
	}

	public Activity(Integer idactivity, String title, String goal, String financingrole, String dates,
			Account owneractivity) {
		super();
		this.idactivity = idactivity;
		this.title = title;
		this.goal = goal;
		this.financingrole = financingrole;
		this.dates = dates;
		this.owneractivity = owneractivity;
	}

	public Integer getIdactivity() {
		return idactivity;
	}

	public void setIdactivity(Integer idactivity) {
		this.idactivity = idactivity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getFinancingrole() {
		return financingrole;
	}

	public void setFinancingrole(String financingrole) {
		this.financingrole = financingrole;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public Account getOwneractivity() {
		return owneractivity;
	}

	public void setOwneractivity(Account owneractivity) {
		this.owneractivity = owneractivity;
	}

	public List<Account> getUserlike() {
		return userlike;
	}

	public void setUserlike(List<Account> userlike) {
		this.userlike = userlike;
	}

	public List<Account> getUserview() {
		return userview;
	}

	public void setUserview(List<Account> userview) {
		this.userview = userview;
	}

	public void addLike(Account account) {
		if (!userlike.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount()))) {
			userlike.add(account);
		}
	}

	public boolean liked(Account account) {
		return userlike.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount()));
	}

	public int countLike() {
		return userlike.size();
	}

	public void addView(Account account) {
		if (!userview.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount()))) {
			userview.add(account);
		}
	}

	public boolean view(Account account) {
		return userview.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount()));
	}

	public int countView() {
		return userview.size();
	}

	public static String addDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return dateFormat.format(date);
	}
}