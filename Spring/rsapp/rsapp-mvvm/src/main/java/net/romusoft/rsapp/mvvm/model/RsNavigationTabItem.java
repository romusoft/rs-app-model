package net.romusoft.rsapp.mvvm.model;
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
 * @author Emmanuel Romulus
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
	 * default constructor
	 */
	public RsNavigationTabItem() {
	}

	/**
	 * initialize a tab item with roles
	 * 
	 * @param uri         the uri for the tab item
	 * @param title       the title for the tab item
	 * @param description the description for the tab item
	 * @param roles       roles that can access the tab item
	 */
	public RsNavigationTabItem(String uri, String title, String description, String... roles) {
		this.uri = uri;
		this.title = title;
		this.description = description;
		this.roles = roles;
	}

	/**
	 * initialize a tab item as a separator
	 * 
	 * @param separator whether tab item is a separator
	 * @param htmlText  html to represent the separator
	 */
	public RsNavigationTabItem(boolean separator, String htmlText) {
		this.separator = separator;
		this.title = htmlText;

		this.cssSeparator = separator ? "wp-separator" : "";
		this.uri = separator ? "#" : "";
	}

	/**
	 * the title for the tab item
	 * 
	 * @return the title for the tab item
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * the title for the tab item
	 * 
	 * @param title the title for the tab item
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * the description for the tab item
	 * 
	 * @return the description for the tab item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * the description for the tab item
	 * 
	 * @param description the description for the tab item
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * the uri for the tab item
	 * 
	 * @return the uri for the tab item
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * the uri for the tab item
	 * 
	 * @param uri the uri for the tab item
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * roles that can access the tab item
	 * 
	 * @return roles that can access the tab item
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * roles that can access the tab item
	 * 
	 * @param roles roles that can access the tab item
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	/**
	 * CSS active for the tab item
	 * 
	 * @return CSS active for the tab item
	 */
	public String getCssActive() {
		cssActive = active ? "tabitem active" : "tabitem";
		return cssActive;
	}

	/**
	 * whether the tab item is active
	 * 
	 * @return whether the tab item is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * whether the tab item is active
	 * 
	 * @param active whether the tab item is active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Whether the tab item is a separator
	 * 
	 * @return Whether the tab item is a separator
	 */
	public boolean isSeparator() {
		return separator;
	}

	/**
	 * Css for tab separator
	 * 
	 * @return Css for tab separator
	 */
	public String getCssSeparator() {

		return cssSeparator;
	}

}
