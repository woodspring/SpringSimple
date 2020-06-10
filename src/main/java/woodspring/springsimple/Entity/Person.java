package woodspring.springsimple.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity(name="PERSON_DATA")
@Data
public class Person {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="person_id")
	Long id;
	
	
	@Column(name="firt_name", nullable=false)
	private String firstname;
	
	@Column(name="last_name", nullable=false)
	private String lastname;
	
	public Person() {  //}
		this.firstname = "";
		this.lastname = "";
	}
	public Person(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFullname() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append( firstname + " ");
		strBuf.append( lastname);
		return strBuf.toString();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

}
