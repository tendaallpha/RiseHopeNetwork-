package cm.genie6.risehope.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "organization")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idorganization;
	private String name;
	private String birthday;
	private String headoffice;
	private String country;
	private String district;
	private String purpose;

	@ManyToMany
	@JoinTable(name = "member", inverseJoinColumns = @JoinColumn(name = "account", referencedColumnName = "idaccount"), joinColumns = @JoinColumn(name = "organization", referencedColumnName = "idorganization"))
	private List<Account> usermember = new ArrayList<>();

	public Organization() {
		super();
	}

	public Organization(Integer idorganization, String name, String birthday, String headoffice, String country,
			String district, String purpose) {
		super();
		this.idorganization = idorganization;
		this.name = name;
		this.birthday = birthday;
		this.headoffice = headoffice;
		this.country = country;
		this.district = district;
		this.purpose = purpose;
	}

	public void addMember(Account account) {
		if (!usermember.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount())))
			usermember.add(account);
	}

	public void addMembers(List<Account> accounts) {
		accounts.forEach(e -> addMember(e));
	}

	public void removeMember(Account account) {
		if (usermember.stream().anyMatch(e -> e.getIdaccount().equals(account.getIdaccount())))
			usermember.remove(account);
	}

	public Integer getIdorganization() {
		return idorganization;
	}

	public void setIdorganization(Integer idorganization) {
		this.idorganization = idorganization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHeadoffice() {
		return headoffice;
	}

	public void setHeadoffice(String headoffice) {
		this.headoffice = headoffice;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public List<Account> getUsermember() {
		return usermember;
	}

	public void setUsermember(List<Account> usermember) {
		this.usermember = usermember;
	}

	

}