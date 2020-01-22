package cm.genie6.risehope.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idaccount;
	private String cname;
	private String ccity;
	private String ccountry;
	private String cpassword;
	private String ccreatingdate;
	private int cphone;
	private String cvalidation;
	private String pnationality;
	private String pfirstname;
	private String plastname;
	private int pphone;
	private String psex;
	private int pcni;
	private String status;
	private String story;
	private int know;
	private String photo;
	private String orgrole;
	private boolean orgstatut;

	@ManyToOne
	@JoinColumn(name = "organizationmember", referencedColumnName = "idorganization")
	private Organization organizationmember;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerarticle")
	private List<Article> articles = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sendermails")
	private List<Mail> sendermails = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerplanning")
	private List<Planning> plannings = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "suggestionowner")
	private List<Suggestion> suggestion = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerchildren")
	private List<Child> children = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userowner")
	private List<Comment> comments = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receivermails")
	private List<Mail> receivermails = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerstatus")
	private List<Status> statuss = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owneractivity")
	private List<Activity> activities = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owneropportunity")
	private List<Opportunity> opportunity = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owneraskhelp")
	private List<AskHelp> askhelps = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ownernecessity")
	private List<Necessity> necessities = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "centervisited")
	private List<Visit> visits = new ArrayList<>();

	@ManyToMany(mappedBy = "usermember")
	private List<Organization> organizations = new ArrayList<>();

	@ManyToMany(mappedBy = "userlike")
	private List<Status> statuslike = new ArrayList<>();

	@ManyToMany(mappedBy = "userlike")
	private List<Article> articlelike = new ArrayList<>();

	@ManyToMany(mappedBy = "usertransaction")
	private List<AskHelp> transactionaskhelp = new ArrayList<>();

	@ManyToMany(mappedBy = "userlike")
	private List<Activity> activitiesLikes = new ArrayList<>();

	@ManyToMany(mappedBy = "userview")
	private List<Activity> activitiesView = new ArrayList<>();

	public Account() {
	}

	public Status getMyLastStatus() {
		return statuss.stream().sorted(Status.SORT_BY_DATE_DESC).findFirst().orElse(null);
	}

	public Activity getMyLastActivity() {
		return activities.stream().sorted(Activity.SORT_BY_ID_DESC).findFirst().orElse(null);
	}

	public Account(Integer idaccount, String cname, String ccity, String ccountry, String cpassword,
			String ccreatingdate, int cphone, String cvalidation, String pnationality, String pfirstname,
			String plastname, int pphone, String psex, int pcni, String status, String story, int know, String photo,
			String orgrole, boolean orgstatut, Organization organizationmember) {
		super();
		this.idaccount = idaccount;
		this.cname = cname;
		this.ccity = ccity;
		this.ccountry = ccountry;
		this.cpassword = cpassword;
		this.ccreatingdate = ccreatingdate;
		this.cphone = cphone;
		this.cvalidation = cvalidation;
		this.pnationality = pnationality;
		this.pfirstname = pfirstname;
		this.plastname = plastname;
		this.pphone = pphone;
		this.psex = psex;
		this.pcni = pcni;
		this.status = status;
		this.story = story;
		this.know = know;
		this.photo = photo;
		this.orgrole = orgrole;
		this.orgstatut = orgstatut;
		this.organizationmember = organizationmember;
	}

	public Integer getIdaccount() {
		return idaccount;
	}

	public void setIdaccount(Integer idaccount) {
		this.idaccount = idaccount;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCcity() {
		return ccity;
	}

	public void setCcity(String ccity) {
		this.ccity = ccity;
	}

	public String getCcountry() {
		return ccountry;
	}

	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getCcreatingdate() {
		return ccreatingdate;
	}

	public void setCcreatingdate(String ccreatingdate) {
		this.ccreatingdate = ccreatingdate;
	}

	public int getCphone() {
		return cphone;
	}

	public void setCphone(int cphone) {
		this.cphone = cphone;
	}

	public String getCvalidation() {
		return cvalidation;
	}

	public void setCvalidation(String cvalidation) {
		this.cvalidation = cvalidation;
	}

	public String getPnationality() {
		return pnationality;
	}

	public void setPnationality(String pnationality) {
		this.pnationality = pnationality;
	}

	public String getPfirstname() {
		return pfirstname;
	}

	public void setPfirstname(String pfirstname) {
		this.pfirstname = pfirstname;
	}

	public String getPlastname() {
		return plastname;
	}

	public void setPlastname(String plastname) {
		this.plastname = plastname;
	}

	public int getPphone() {
		return pphone;
	}

	public void setPphone(int pphone) {
		this.pphone = pphone;
	}

	public String getPsex() {
		return psex;
	}

	public void setPsex(String psex) {
		this.psex = psex;
	}

	public int getPcni() {
		return pcni;
	}

	public void setPcni(int pcni) {
		this.pcni = pcni;
	}

	public String getStatus() {
		return status;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public Organization getMembers() {
		return organizationmember;
	}

	public void setMembers(Organization members) {
		this.organizationmember = members;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public int getKnow() {
		return know;
	}

	public void setKnow(int know) {
		this.know = know;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Mail> getSendermails() {
		return sendermails;
	}

	public void setSendermails(List<Mail> sendermails) {
		this.sendermails = sendermails;
	}

	public List<Planning> getPlannings() {
		return plannings;
	}

	public void setPlannings(List<Planning> plannings) {
		this.plannings = plannings;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public List<Suggestion> getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(List<Suggestion> suggestion) {
		this.suggestion = suggestion;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Mail> getReceivermails() {
		return receivermails;
	}

	public List<Necessity> getNecessities() {
		return necessities;
	}

	public void setNecessities(List<Necessity> necessities) {
		this.necessities = necessities;
	}

	public void setReceivermails(List<Mail> receivermails) {
		this.receivermails = receivermails;
	}

	public List<Visit> getVisits() {
		return visits;
	}

	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}

	public List<Status> getStatuss() {
		return statuss;
	}

	public void setStatuss(List<Status> statuss) {
		this.statuss = statuss;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Opportunity> getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(List<Opportunity> opportunity) {
		this.opportunity = opportunity;
	}

	public Organization getOrganizationmember() {
		return organizationmember;
	}

	public void setOrganizationmember(Organization organizationmember) {
		this.organizationmember = organizationmember;
	}

	public String getOrgrole() {
		return orgrole;
	}

	public void setOrgrole(String orgrole) {
		this.orgrole = orgrole;
	}

	public boolean isOrgstatut() {
		return orgstatut;
	}

	public void setOrgstatut(boolean orgstatut) {
		this.orgstatut = orgstatut;
	}

	public List<Status> getStatuslike() {
		return statuslike;
	}

	public void setStatuslike(List<Status> statuslike) {
		this.statuslike = statuslike;
	}

	public List<Article> getArticlelike() {
		return articlelike;
	}

	public void setArticlelike(List<Article> articlelikes) {
		this.articlelike = articlelikes;
	}

	public List<Activity> getActivitiesLikes() {
		return activitiesLikes;
	}

	public void setActivitiesLikes(List<Activity> activitiesLikes) {
		this.activitiesLikes = activitiesLikes;
	}

	public List<Activity> getActivitiesView() {
		return activitiesView;
	}

	public void setActivitiesView(List<Activity> activitiesView) {
		this.activitiesView = activitiesView;
	}

	public List<AskHelp> getTransactionaskhelp() {
		return transactionaskhelp;
	}

	public void setTransactionaskhelp(List<AskHelp> transactionaskhelp) {
		this.transactionaskhelp = transactionaskhelp;
	}
	
	public List<AskHelp> getAskhelps() {
		return askhelps;
	}

	public void setAskhelps(List<AskHelp> askhelps) {
		this.askhelps = askhelps;
	}

	public int countChildren() {
		return children.size();
	}
}