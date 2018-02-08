package ro.sv.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Phone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "type", columnDefinition = "VARCHAR(20)")
	private String type;

	@Column(name = "number", columnDefinition = "VARCHAR(20)")
	private String number;

	public Phone() {

	}

	public Phone(String type, String name) {
		this.type = type;
		this.number = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return number;
	}

	public void setName(String name) {
		this.number = name;
	}

	@Override
	public String toString() {
		return "Type: " + this.type + " Number: " + this.number;
	}

}
