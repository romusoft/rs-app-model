//package net.romusoft.rsapp.mvvm;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import javax.validation.constraints.NotNull;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//
///**
// * based class to be inherited by all models or entities
// * 
// * @author Emmanuel Romulus
// *
// */
//@JsonInclude(Include.ALWAYS)
//@MappedSuperclass
//public abstract class RsAbstractAuditableModel extends RsAbstractModel {
//
//	@NotNull
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
//	@Column(name = "DATE_MODIFIED")
//	private Date dateModified;
//
//	@NotNull
//	@Column(name = "AUDIT_USER")
//	private String auditUser;
//
//	@Column(name = "INACTIVE")
//	private boolean inactive;
//
//	@Column(name = "COMMENT", length = 2000)
//	private String comment;
//
//	/**
//	 * default constructor
//	 */
//	public RsAbstractAuditableModel() {
//
//	}
//
//	/**
//	 * The primary key for the record
//	 * 
//	 * @return the primary key for the record
//	 */
//	public abstract Long getId();
//
//	/**
//	 * 
//	 */
//	public abstract void setId(Long id);
//
//	/**
//	 * 
//	 * @return
//	 */
//	public String getComment() {
//		return comment;
//	}
//
//	/**
//	 * 
//	 * @param comment
//	 */
//	public void setComment(String comment) {
//		this.comment = comment;
//	}
//
//	/**
//	 * 
//	 * @return
//	 */
//	public Date getDateModified() {
//		return dateModified;
//	}
//
//	/**
//	 * 
//	 * @param dateModified
//	 */
//	public void setDateModified(Date dateModified) {
//		this.dateModified = dateModified;
//	}
//
//	/**
//	 * 
//	 * @return
//	 */
//	public String getAuditUser() {
//		return auditUser;
//	}
//
//	/**
//	 * 
//	 * @param auditUser
//	 */
//	public void setAuditUser(String auditUser) {
//		this.auditUser = auditUser;
//	}
//
//	/**
//	 * 
//	 * @return
//	 */
//	public boolean isInactive() {
//		return inactive;
//	}
//
//	/**
//	 * 
//	 * @param inactive
//	 */
//	public void setInactive(boolean inactive) {
//		this.inactive = inactive;
//	}
//
//}
