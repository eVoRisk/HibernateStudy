package ro.sv.hibernate.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ro.sv.hibernate.utils.BaseEntity;

@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity implements Serializable, AttributeConverter<LocalDate, Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "createdOn")
	private LocalDate createdOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personId")
	private Person person;

	// Constructors
	public Invoice() {

	}

	public Invoice(LocalDate createdOn, Person person) {
		this.createdOn = createdOn;
		this.person = person;
	}

	// Setters and Getters
	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	// Converters for Date
	public Date convertToDatabaseColumn(LocalDate locDate) {
		return (locDate == null ? null : Date.valueOf(locDate));
	}

	public LocalDate convertToEntityAttribute(Date sqlDate) {
		return (sqlDate == null ? null : sqlDate.toLocalDate());
	}
}
