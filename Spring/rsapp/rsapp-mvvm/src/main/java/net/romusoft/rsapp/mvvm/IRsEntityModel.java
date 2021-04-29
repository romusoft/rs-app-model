package net.romusoft.rsapp.mvvm;

import java.sql.Timestamp;

/**
 * this base model provides a entity model the ability to be used as a DTO. The
 * toString method will return a json representation of the current instance of
 * this class. Use the mapper to dynamically add necessary fields for a dtp
 * without have to extends your class any further or create a new dto class.
 * 
 * @author romulus
 *
 */
public interface IRsEntityModel extends IRsModel {
	/**
	 * user id who made changes to a record
	 * 
	 * @return the user id
	 */
	public String getUpdatedBy();

	/**
	 * set the user id who made changes to a record
	 * 
	 * @param updatedBy the user id
	 */
	public void setUpdatedBy(String updatedBy);

	/**
	 * The timestamp of the event
	 * 
	 * @return the timestamp of the event
	 */
	public Timestamp getUpdatedDatetime();

	/**
	 * Set The timestamp of the event
	 * 
	 * @param updatedDatetime The timestamp of the event
	 */
	public void setUpdatedDatetime(Timestamp updatedDatetime);

	/**
	 * The primary key of the record
	 * 
	 * @return the primary of the record
	 */
	public long getId();

	/**
	 * set the primary key of the record
	 * 
	 * @param id the primary key of the record
	 */
	public void setId(long id);

}
