package cm.genie6.risehope.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@Table(name = "askhelp")
public class AskHelp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idaskhelp;
	private String content;
	private String date;
	private String accept;
	private String refuse;
	private boolean delete;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "owneraskhelp")
	private Account owneraskhelp;
	@ManyToMany
	@JoinTable(name = "askhelptransaction", joinColumns = @JoinColumn(name = "idaskhelp", referencedColumnName = "idaskhelp"), inverseJoinColumns = @JoinColumn(name = "iduser", referencedColumnName = "idaccount"))
	private List<Account> usertransaction = new ArrayList<>();

	public AskHelp() {
		super();
	}

	public AskHelp(Integer idaskhelp, String content, String date, String accept, String refuse, boolean delete,
			Account owneraskhelp) {
		super();
		this.idaskhelp = idaskhelp;
		this.content = content;
		this.date = date;
		this.accept = accept;
		this.refuse = refuse;
		this.delete = delete;
		this.owneraskhelp = owneraskhelp;
	}

	public void addTransaction(Account account) {
		usertransaction.add(account);
	}

	public void removeCurrentUser(Account account) {
		usertransaction.remove(account);
	}

	public void addReceivers(List<Account> accounts) {
		accounts.forEach(e -> addTransaction(e));
	}

	public boolean verificationAskedHelp(Account account) {
		return usertransaction.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount()));
	}

	public Integer getIdaskhelp() {
		return idaskhelp;
	}

	public void setIdaskhelp(Integer idaskhelp) {
		this.idaskhelp = idaskhelp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getRefuse() {
		return refuse;
	}

	public void setRefuse(String refuse) {
		this.refuse = refuse;
	}

	public boolean getDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public List<Account> getUsertransaction() {
		return usertransaction;
	}

	public void setUsertransaction(List<Account> usertransaction) {
		this.usertransaction = usertransaction;
	}

	public Account getOwneraskhelp() {
		return owneraskhelp;
	}

	public void setOwneraskhelp(Account owneraskhelp) {
		this.owneraskhelp = owneraskhelp;
	}

	public static String addDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return dateFormat.format(date);
	}

	public int countAskHelp() {
		return usertransaction.size();
	}

}
