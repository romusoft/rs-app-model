package com.rsapp.mvvm.model;
/*********************************************************************************************
 * @COPYRIGHT 					(c) 2019 ROMUSOFT, LLC., BILLINGS,MONTANA ALL RIGHTS RESERVED 
 * 				THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            	ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            	THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            	NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * @DESCRIPTION : model class for an tabitem used in the tab navigation service.
 * 
 * 
 * @PROGRAM : application template :  11/15/2018 FUNCTION :
 * 
 * @ENVIRONMENT : java
 * 
 * 
 * @MODIFICATION HISTORY:
 *
 * 
 * @author Emmanuel Romulus
 *
 ***********************************************************************************************/

/**
 * 
 * @author romulus
 *
 */
public class RsNavigationTabItem {
	private String title;
	private String description;
	private String uri;
	private String[] roles = null;

	private String cssActive;
	private boolean active;
	private boolean separator;
	private String cssSeparator;

	/**
	 * 
	 */
	public RsNavigationTabItem() {
	}

	/**
	 * @param uri
	 * @param title
	 * @param description
	 * @param roles
	 */
	public RsNavigationTabItem(String uri, String title, String description, String... roles) {
		this.uri = uri;
		this.title = title;
		this.description = description;
		this.roles = roles;
	}

	/**
	 * @param uri
	 * @param title
	 * @param description
	 * @param roles
	 */
	public RsNavigationTabItem(boolean separator, String htmlText) {
		this.separator = separator;
		this.title = htmlText;	

		this.cssSeparator = separator ? "wp-separator" : "";
		this.uri= separator ? "#" : "";
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * 
	 * @return
	 */
	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	/**
	 * 
	 * @return
	 */
	public String getCssActive() {
		cssActive = active ? "tabitem active" : "tabitem";
		return cssActive;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSeparator() {
		return separator;
	}

	public String getCssSeparator() {

		return cssSeparator;
	}

}
