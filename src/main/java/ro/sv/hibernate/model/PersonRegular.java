package ro.sv.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "person_regular")
@DiscriminatorValue("REGULAR")
public class PersonRegular extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "personalIdentificationNumber", columnDefinition = "VARCHAR(13) DEFAULT null")
	private String personalIdentificationNumber;

	public PersonRegular() {

	}

	public PersonRegular(Person person) {
		super(person.getFirstName(), person.getLastName(), person.getGender(), person.getBirthday());
	}

	public String getPersonalIdentificationNumber() {
		return personalIdentificationNumber;
	}

	public void setPersonalIdentificationNumber(String personalIdentificationNumber) {
		this.personalIdentificationNumber = personalIdentificationNumber;
	}

}
