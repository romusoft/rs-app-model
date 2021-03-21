package net.romusoft.rsapp.mvvm;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * based class to be inherited by all models or entities
 * 
 * @author eromu_000
 *
 */
@JsonInclude(Include.NON_NULL)
@MappedSuperclass
public abstract class RsAbstractModel {

	@Column(name = "DESCRIPTION", length = 2000)
	private String description;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	/**
	 * used for adding ad hoc data attributes when need by a viewmodel. it has the
	 * same effect as a business object.
	 */
	@Transient
	private final HashMap<String, Object> metadata = new HashMap<String, Object>();


	/**
	 * The primary key for the record
	 * 
	 * @return
	 */
	public abstract Long getId();

	public abstract void setId(Long id);

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getMetadata() {
		return metadata;
	}

}
