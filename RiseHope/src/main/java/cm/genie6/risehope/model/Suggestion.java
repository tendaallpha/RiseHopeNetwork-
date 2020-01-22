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
@Table(name = "suggestion")
public class Suggestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idsuggestion;
	private String content;
	private String date;
	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "suggestionowner")
	private Account suggestionowner;

	public Suggestion() {
	}

	public Suggestion(Integer idsuggestion, String content, String date, Account suggestionowner) {
		super();
		this.idsuggestion = idsuggestion;
		this.content = content;
		this.date = date;
		this.suggestionowner = suggestionowner;
	}

	public Integer getIdsuggestion() {
		return idsuggestion;
	}

	public void setIdsuggestion(Integer idsuggestion) {
		this.idsuggestion = idsuggestion;
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

	public Account getSuggestionOwner() {
		return suggestionowner;
	}

	public void setSuggestionOwner(Account suggestionowner) {
		this.suggestionowner = suggestionowner;
	}

	public static String addDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		return dateFormat.format(date);
	}
}
