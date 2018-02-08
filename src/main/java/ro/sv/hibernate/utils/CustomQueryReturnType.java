package ro.sv.hibernate.utils;

import java.util.UUID;

public class CustomQueryReturnType {

	private UUID invoiceId;
	private UUID personId;
	private String personFirstName;
	private String personLastName;

	public CustomQueryReturnType() {

	}

	public CustomQueryReturnType(UUID invoiceId, UUID personId, String personFirstName, String personLastName) {
		this.invoiceId = invoiceId;
		this.personId = personId;
		this.personFirstName = personFirstName;
		this.personLastName = personLastName;
	}

	public UUID getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(UUID invoiceId) {
		this.invoiceId = invoiceId;
	}

	public UUID getPersonId() {
		return personId;
	}

	public void setPersonId(UUID personId) {
		this.personId = personId;
	}

	public String getPersonFirstName() {
		return personFirstName;
	}

	public void setPersonFirstName(String personFirstName) {
		this.personFirstName = personFirstName;
	}

	public String getPersonLastName() {
		return personLastName;
	}

	public void setPersonLastName(String personLastName) {
		this.personLastName = personLastName;
	}

	@Override
	public String toString() {
		return "\n\nInvoice Id: " + this.invoiceId + "\nPerson Id: " + this.personId + "\nPerson First Name: "
				+ this.personFirstName + "\nPerson Last Name: " + this.personLastName;
	}
}
