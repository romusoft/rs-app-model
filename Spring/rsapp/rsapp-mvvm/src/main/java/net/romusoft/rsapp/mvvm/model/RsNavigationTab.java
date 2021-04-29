package net.romusoft.rsapp.mvvm.model;

/*********************************************************************************************
 * @COPYRIGHT 					(c) 2019 ROMUSOFT, LLC., BILLINGS,MONTANA ALL RIGHTS RESERVED 
 * 				THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            	ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            	THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            	NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * @DESCRIPTION : model class for an tab used in the tab navigation service.
 *					This class is designed for tab navigation tab navigation normally involves a
 * 					list of categories. Under each category, there is a list of navigation items.
 * 					When a category is hovered/clicked, the list should display all the available items.
 * 					Clicking on an item will take you to to the item, then display the active
 * 					category and all the available items for that category.
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

import java.util.ArrayList;
import java.util.List;

/**
 * This class is designed for tab navigation tab navigation normally involves a
 * list of categories. Under each category, there is a list of navigation items.
 * When a category is hovered/clicked, the list should display all the available
 * items. Clicking on an item will take you to to the item, then display the
 * active category and all the available items for that category.
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsNavigationTab {

	private long id;
	private String name;
	private String title;
	private String description;
	private String cssActive;
	private boolean active;

	private String defaultUri;

	private List<RsNavigationTabItem> itemList = new ArrayList<RsNavigationTabItem>();
	private RsNavigationTabItem activeTabItem = null;
	private boolean tabItemsInitialized;
	private String[] roles = null;

	/**
	 * constructors
	 */
	public RsNavigationTab() {
	}

	/**
	 * tab initialization
	 * 
	 * @param id    the unique id of the tab
	 * @param name  the unique name of the tab
	 * @param title title for the tab
	 * @param roles - associated with this category if a user does not have the
	 *              role, it will not display for the user
	 */
	public RsNavigationTab(long id, String name, String title, String... roles) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.roles = roles;

	}

	/**
	 * the id of the tab
	 * 
	 * @return the id of the tab
	 */
	public long getId() {
		return id;
	}

	/**
	 * the id of the tab
	 * 
	 * @param id the id of the tab
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * the name of the tab
	 * 
	 * @return the name of the tab
	 */
	public String getName() {
		return name;
	}

	/**
	 * the name of the tab
	 * 
	 * @param name the name of the tab
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * the title of the tab
	 * 
	 * @return the title of the tab
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * the title of the tab
	 * 
	 * @param title the title of the tab
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * the description of the title. Normally a tooltip that tells what the tab is
	 * about
	 * 
	 * @return the description of the title
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * the description of the title
	 * 
	 * @param description the description of the title
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * list of roles for the tab access
	 * 
	 * @return list of roles associated with the tab
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * list of roles for the tab access
	 * 
	 * @param roles list of roles for the tab access
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	/**
	 * CSS active for the tab
	 * 
	 * @return CSS active for the tab
	 */
	public String getCssActive() {
		cssActive = active ? "active" : "";
		return cssActive;
	}

	/**
	 * whether the tab is active
	 * 
	 * @return whether the tab is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * whether the tab is active
	 * 
	 * @param active whether the tab is active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * default uri for the tab
	 * 
	 * @return default uri for the tab
	 */
	public String getDefaultUri() {
		/*
		 * get the first item's uri in the tab default uri
		 */
		activeTabItem = itemList.stream().filter(temp -> temp.isActive()).findAny().orElse(null);
		if (activeTabItem == null) {
			activeTabItem = itemList.size() > 0 ? itemList.get(0) : null;
		}

		if (activeTabItem != null)
			defaultUri = activeTabItem.getUri();

		return defaultUri;
	}

	/**
	 * default uri for the tab
	 * 
	 * @param defaultUri default uri for the tab
	 */
	public void setDefaultUri(String defaultUri) {
		this.defaultUri = defaultUri;
	}

	/**
	 * List of sub-tabs
	 * 
	 * @return List of sub-tabs
	 */
	public List<RsNavigationTabItem> getItemList() {
		return itemList;
	}

	/**
	 * List of sub-tabs
	 * 
	 * @param itemList List of sub-tabs
	 */
	public void setItemList(List<RsNavigationTabItem> itemList) {
		this.itemList = itemList;
		tabItemsInitialized = true;
	}

	/**
	 * an item to this tab
	 * 
	 * @param item tab item to add
	 */
	public void addItem(RsNavigationTabItem item) {
		this.itemList.add(item);

	}

	/**
	 * whether tab items have been initialized based on the user's role. That needs
	 * to happen once
	 * 
	 * @return whether the tab items are initialized
	 */
	public boolean isTabItemsInitialized() {
		return tabItemsInitialized;
	}

	/**
	 * whether the tab items are initialized
	 * @param tabItemsInitialized whether the tab items are initialized
	 */
	public void setTabItemsInitialized(boolean tabItemsInitialized) {
		this.tabItemsInitialized = tabItemsInitialized;
	}

	/**
	 * the active tab item
	 * @return the active tab item
	 */
	public RsNavigationTabItem getActiveTabItem() {

		/*
		 * if the active tab is not null set the active tab item
		 */
		if (activeTabItem == null) {
			activeTabItem = itemList.stream().filter(temp -> temp.isActive()).findAny().orElse(null);
			if (activeTabItem == null) {
				activeTabItem = itemList.size() > 0 ? itemList.get(0) : null;
				activeTabItem.setActive(true);
			}
		}

		return activeTabItem;
	}

	/**
	 * the active tab item
	 * @param activeTabItem the active tab item
	 */
	public void setActiveTabItem(RsNavigationTabItem activeTabItem) {

		this.activeTabItem = activeTabItem;
		if (this.activeTabItem != null)
			this.activeTabItem.setActive(true);

	}

}
