package ro.sv.hibernate.audit;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "audit_log")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;

	@Column(name = "action")
	private String action;

	@Column(name = "createdOn")
	private Timestamp createdOn;

	@Column(name = "className")
	private String className;

	@Column(name = "propName")
	private String propName;

	@Column(name = "oldValue")
	private String oldValue;

	@Column(name = "newValue")
	private String newValue;

	@Column(name = "entityDetails")
	private String entityDetails;

	public AuditLog() {
		
	}
	
	public AuditLog(String action, Timestamp createdOn, String className, String propName, String oldValue,
			String newValue, String entityDetails) {
		this.action = action;
		this.createdOn = createdOn;
		this.className = className;
		this.propName = propName;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.entityDetails = entityDetails;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getEntityDetails() {
		return entityDetails;
	}

	public void setEntityDetails(String entityDetails) {
		this.entityDetails = entityDetails;
	}

}
