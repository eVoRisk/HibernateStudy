package ro.sv.hibernate.utils;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(generator = "my_gen")
	@GenericGenerator(name = "my_gen", strategy = "ro.sv.hibernate.persistence.MyUUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(38)")
	@Type(type = "uuid-char")
	@Access(AccessType.PROPERTY)
	private UUID id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
