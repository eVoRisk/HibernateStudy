package ro.sv.hibernate.utils;

import java.time.LocalDate;
import java.util.UUID;

public class PersonAndPhone {

	private UUID id;
	private String firstName;
	private String lastName;
	private Gender gender;
	private LocalDate birthday;
	private PersonType personType;
	private String type;
	private String number;

	public PersonAndPhone() {

	}

	public PersonAndPhone(UUID id, String firstName, String lastName, Gender gender, LocalDate birthday,
			PersonType personType, String type, String number) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
		this.personType = personType;
		this.type = type;
		this.number = number;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	// public UUID getPerson_id() {
	// return person_id;
	// }
	//
	// public void setPerson_id(UUID person_id) {
	// this.person_id = person_id;
	// }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
