package net.romusoft.rsapp.mvvm;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import net.romusoft.rsapp.mvvm.io.RsTimestampCustomJsonDeserializer;

/**
 * based class to be inherited by all entity models This base model provides a
 * entity model the ability to be used as a DTO. The toString method will return
 * a json representation of the current instance of this class. Use the mapper
 * to dynamically add necessary fields for a dtp without have to extends your
 * class any further or create a new dto class.
 * 
 * @author romulus
 *
 */
@JsonInclude(Include.ALWAYS)
@MappedSuperclass
public abstract class RsAbstractEntityModel extends RsAbstractModel {

	@NotNull
	@JsonDeserialize(using = RsTimestampCustomJsonDeserializer.class)
	@Column(name = "DATE_CREATED")
	private Timestamp dateCreated;

	@JsonDeserialize(using = RsTimestampCustomJsonDeserializer.class)
	@Column(name = "DATE_MODIFIED")
	private Timestamp dateModified;

	@NotNull
	@Column(name = "AUDIT_USER")
	private String auditUser;

	@Column(name = "INACTIVE")
	private boolean inactive;

	@Column(name = "COMMENT", length = 2000)
	private String comment;

	/****************************************************************************************/
	/**
	 * the primary key of a persistent model object
	 * 
	 * @return the primary key of a persistent model object
	 */
	public abstract long getId();

	/**
	 * the primary key of a persistent model object
	 * 
	 * @param id the primary key of a persistent model object
	 */
	public abstract void setId(long id);

	/**
	 * the date the record was created
	 * 
	 * @return the date the record was created
	 */
	public Timestamp getDateCreated() {
		return dateCreated;
	}

	/**
	 * the date the record was created
	 * 
	 * @param dateCreated the date the record was created
	 */
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * timestamp of the event
	 * 
	 * @param dateModified timestamp of the event
	 */
	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * the user id that performed the action
	 * 
	 * @return the user id that performed the action
	 */
	public String getAuditUser() {
		return auditUser;
	}

	/**
	 * the user id that performed the action
	 * 
	 * @param auditUser the user id that performed the action
	 */
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	/**
	 * Whether the record is inactive
	 * 
	 * @return Whether the record is inactive
	 */
	public boolean isInactive() {
		return inactive;
	}

	/**
	 * Whether the record is inactive
	 * 
	 * @param inactive Whether the record is inactive
	 */
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	/**
	 * the tostring representation of the object as json
	 */
	@Override
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String str = objectMapper.writeValueAsString(this);
			return str;
		} catch (JsonProcessingException e) {
			return this.getClass().getName();
		}
	}

}
