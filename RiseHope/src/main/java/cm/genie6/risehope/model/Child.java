package cm.genie6.risehope.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "child")
public class Child {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idchild;
	private String firstname;
	private String lastname;
	private String birthday;
	private String sex;
	private String sponsored;
	private boolean adopted;
	private String description;

	@ManyToOne
	@JoinColumn(referencedColumnName = "idaccount", name = "ownerchildren")
	private Account ownerchildren;

	public Child() {
	}

	public Child(Integer idchild, String firstname, String lastname, String birthday, String sex, String sponsored,
			boolean adopted, String description, Account ownerchildren) {
		super();
		this.idchild = idchild;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.sex = sex;
		this.sponsored = sponsored;
		this.adopted = adopted;
		this.description = description;
		this.ownerchildren = ownerchildren;
	}

	public Integer getIdchild() {
		return idchild;
	}

	public void setIdchild(Integer idchild) {
		this.idchild = idchild;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSponsored() {
		return sponsored;
	}

	public void setSponsored(String sponsored) {
		this.sponsored = sponsored;
	}

	public boolean isAdopted() {
		return adopted;
	}

	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getOwnerchildren() {
		return ownerchildren;
	}

	public void setOwnerchildren(Account ownerchildren) {
		this.ownerchildren = ownerchildren;
	}

}
