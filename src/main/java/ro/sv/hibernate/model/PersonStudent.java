package ro.sv.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "person_student")
@DiscriminatorValue("STUDENT")
public class PersonStudent extends Person {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "registrationNumber", columnDefinition = "VARCHAR(17) DEFAULT null")
	private String registrationNumber;
	
	public PersonStudent() {
		
	}
	
	public PersonStudent(Person person) {
		super(person.getFirstName(), person.getLastName(), person.getGender(), person.getBirthday());
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
}
