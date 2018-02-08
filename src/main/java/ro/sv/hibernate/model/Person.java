package ro.sv.hibernate.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

import ro.sv.hibernate.audit.IAuditLog;
import ro.sv.hibernate.utils.BaseEntity;
import ro.sv.hibernate.utils.Gender;
import ro.sv.hibernate.utils.PersonType;

@Entity()
@Table(name = "person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "personType", columnDefinition = "VARCHAR(7)")
@DiscriminatorValue("PERSON")
public class Person extends BaseEntity implements Serializable, AttributeConverter<LocalDate, Date>, IAuditLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "firstName", nullable = false, length = 25)
	private String firstName;

	@Column(name = "lastName", nullable = false, length = 25)
	private String lastName;

	@Column(name = "gender", nullable = false, length = 6)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;

	@Column(name = "personType", nullable = true, length = 7, updatable = false, insertable = false)
	@Enumerated(EnumType.STRING)
	private PersonType personType;

	@ElementCollection
	private List<Phone> phones = new ArrayList<>();

	@Version
	private Long version;

	public List<Phone> getPhones() {
		return phones;
	}

	// Constructors
	public Person() {

	}

	public Person(String firstName, String lastName, Gender gender, LocalDate birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthday = birthday;
	}

	// Setters and getters
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	// Converters for Date
	public Date convertToDatabaseColumn(LocalDate locDate) {
		return (locDate == null ? null : Date.valueOf(locDate));
	}

	public LocalDate convertToEntityAttribute(Date sqlDate) {
		return (sqlDate == null ? null : sqlDate.toLocalDate());
	}

	@Override
	public String toString() {

		StringBuilder phonesString = new StringBuilder();
		List<Phone> phones = this.getPhones();
		phones.stream().forEach(phone -> phonesString.append(phone.toString() + " "));

		return "\n\nPerson Id: " + this.getId() + "\nFirst Name: " + this.getFirstName() + "\nLast Name: "
				+ this.getLastName() + "\nGender: " + this.getGender() + "\nBirthday: " + this.getBirthday()
				+ "\nPerson Type: " + this.getPersonType() + "\nPerson Phones: " + phonesString.toString();
	}

	@Override
	public String getLogDetails() {
		return this.toString();
	}
}
