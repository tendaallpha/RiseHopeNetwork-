package cm.genie6.risehope.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
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
@Table(name = "status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idstatus;
	private String stscontent;
	private String date;
	private int view = 0;

	public static final Comparator<Status> SORT_BY_DATE_DESC = new Comparator<Status>() {

		@Override
		public int compare(Status o1, Status o2) {
			return o2.getDate().compareTo(o1.getDate());
		}
	};

	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "ownerstatus")
	private Account ownerstatus;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "statusowner")
	private List<Comment> comments = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "publicationlike", joinColumns = @JoinColumn(name = "idpublication", referencedColumnName = "idstatus"), inverseJoinColumns = @JoinColumn(name = "iduser", referencedColumnName = "idaccount"))
	private List<Account> userlike = new ArrayList<>();

	public Status() {
	}

	public Status(Integer idstatus, String stscontent, String date, int view, Account ownerstatus) {
		super();
		this.idstatus = idstatus;
		this.stscontent = stscontent;
		this.date = date;
		this.view = view;
		this.ownerstatus = ownerstatus;
	}

	public Integer getIdstatus() {
		return idstatus;
	}

	public void setIdstatus(Integer idstatus) {
		this.idstatus = idstatus;
	}

	public String getStscontent() {
		return stscontent;
	}

	public void setStscontent(String stscontent) {
		this.stscontent = stscontent;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Account getOwnerstatus() {
		return ownerstatus;
	}

	public void setOwnerstatus(Account ownerstatus) {
		this.ownerstatus = ownerstatus;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public static String addDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return dateFormat.format(date);
	}

	public void addLove(Account account) {
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

}