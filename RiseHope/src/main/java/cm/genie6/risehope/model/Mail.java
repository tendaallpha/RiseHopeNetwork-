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
@Table(name = "mail")
public class Mail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmail;
	private String date;
	private String content;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "sendermails")
	private Account sendermails;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "receivermails")
	private Account receivermails;

	public Mail() {
	}

	public Mail(Integer idmail, String date, String content, Account sendermails,
			Account reseiverermails) {
		super();
		this.idmail = idmail;
		this.content = content;
		this.date = date;
		this.sendermails = sendermails;
		this.receivermails = reseiverermails;
	}

	public Integer getIdmail() {
		return idmail;
	}

	public void setIdmail(Integer idmail) {
		this.idmail = idmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Account getSendermail() {
		return sendermails;
	}

	public void setSendermail(Account sendermails) {
		this.sendermails = sendermails;
	}

	public String getDate() {
		return date;
	}

	public Account getSendermails() {
		return sendermails;
	}

	public void setSendermails(Account sendermails) {
		this.sendermails = sendermails;
	}

	public Account getReceivermails() {
		return receivermails;
	}

	public void setReceivermails(Account receivermails) {
		this.receivermails = receivermails;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setReceiverermail(Account receiverermails) {
		this.receivermails = receiverermails;
	}

	public static String addDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return dateFormat.format(date);
	}

}