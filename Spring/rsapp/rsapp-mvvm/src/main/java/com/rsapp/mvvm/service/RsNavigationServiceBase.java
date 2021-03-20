package com.rsapp.mvvm.service;

/*********************************************************************************************
 * @COPYRIGHT 					(c) 2019 ROMUSOFT, LLC., BILLINGS,MONTANA ALL RIGHTS RESERVED 
 * 				THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            	ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            	THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            	NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * @DESCRIPTION : abstract class that implements the tab navigation service interface.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rsapp.mvvm.model.RsNavigationTab;
import com.rsapp.mvvm.model.RsNavigationTabItem;

/**
 * 
 * @author romulus
 *
 */
public abstract class RsNavigationServiceBase implements RsNavigationService {

	private RsNavigationTab activeTab = null;
	private List<RsNavigationTab> tabList = new ArrayList<RsNavigationTab>();

	/**
	 * initialize all necessary properties for the service
	 */
	public RsNavigationServiceBase() {

		/*
		 * initialize or populate the tab list and items
		 */
		initializeTabs(tabList);
	}

	/**
	 * Return the instance of the tab list to be used for the tabs
	 * 
	 * @return
	 */
	protected abstract void initializeTabs(List<RsNavigationTab> tabList);

	/**
	 * the current active tab
	 * 
	 * @return
	 */
	@Override
	public RsNavigationTab getActiveTab() {
		return activeTab;
	}

	@Override
	public void setActiveTab(RsNavigationTab activeTab) {
		this.activeTab = activeTab;
	}

	/**
	 * set a default active tab
	 */
	@Override
	public RsNavigationTab updateActiveTab(List<RsNavigationTab> authorizedTabList) {
		int index = 0;
		for (RsNavigationTab tab : authorizedTabList) {
			tab.setActive(false);
			if (index == 0) {
				activeTab = tab;
				activeTab.setActive(true);
			}
			index++;
		}
		return activeTab;
	}

	/**
	 * update the active tab based on the tab name
	 */
	@Override
	public RsNavigationTab updateActiveTab(String tabName, List<RsNavigationTab> authorizedTabList) {

		activeTab = null;
		for (RsNavigationTab tab : authorizedTabList) {
			tab.setActive(false);
			if (tab.getName().equalsIgnoreCase(tabName)) {
				activeTab = tab;
				activeTab.setActive(true);
			}
		}

		return activeTab;
	}

	/**
	 * update the active tab based on the tab name
	 */
	@Override
	public String getActiveTabName(String activeTabItemUri, List<RsNavigationTab> authorizedTabList) {

		/*
		 * find a tab name base on a given uri
		 */
		for (RsNavigationTab tab : authorizedTabList) {
			boolean found = tab.getItemList().stream().anyMatch(temp -> temp.getUri().equals(activeTabItemUri));
			if (found)
				return tab.getName();
		}

		return null;
	}

	/**
	 * update the active tab based on the tab name and the tabitem uri
	 */
	@Override
	public RsNavigationTab updateActiveTab(String tabName, String tabItemUri, List<RsNavigationTab> authorizedTabList) {

		if (tabItemUri != null)
			tabItemUri = tabItemUri.toLowerCase();
		//
		// find the target tab and activate find the target item tab and activate it
		activeTab = null;
		for (RsNavigationTab tab : authorizedTabList) {
			tab.setActive(false);
			if (tab.getName().equalsIgnoreCase(tabName)) {
				activeTab = tab;
				activeTab.setActive(true);
			}
		}

		if (activeTab == null)
			return null;
		//
		// create a new list to return based on some predicates with the user roles
		boolean isSet = false;
		RsNavigationTabItem currentTabItem = activeTab.getActiveTabItem();
		List<RsNavigationTabItem> tabItemList = activeTab.getItemList();
		for (RsNavigationTabItem tabItem : tabItemList) {
			//
			// set the active for the current uri
			tabItem.setActive(false);
			if (tabItem.getUri().equalsIgnoreCase(tabItemUri)) {
				tabItem.setActive(true);
				activeTab.setActiveTabItem(tabItem);
				isSet = true;
			}
		}

		//
		// inactivate the previous tab item
		if (isSet == false) {
			currentTabItem.setActive(false);
		}
		return activeTab;
	}

	/**
	 * retrieve the list of tabs the user is authorized to use
	 * 
	 * @return
	 */
	@Override
	public List<RsNavigationTab> retrieveUserAuthorizedNavigationTabList() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String[] roles = null;
		if (auth != null) {
			roles = convertToArray(auth.getAuthorities());
			System.out.println("******  - 1 - Retrieve authorized navigation item list ****************");
		} else {
			roles = new String[] { "ROLE_NOT_AUTHENTICATED" };
			System.out.println(
					"******  - 1 - NOT_AUTHENTICATED - Retrieve authorized navigation item list ****************");
		}

		List<RsNavigationTab> authorizedTabList = new ArrayList<RsNavigationTab>();
		for (RsNavigationTab tab : tabList) {
			String[] tabRoles = tab.getRoles();
			/*
			 * check if the user roles have access to this category
			 */
			if (tabRoles == null || tabRoles.length == 0) {
				authorizedTabList.add(tab);
			} else {
				for (String tabRole : tabRoles) {
					if (isInRole(roles, tabRole)) {
						authorizedTabList.add(tab);
						break;
					}
				}
			}
		}
		return authorizedTabList;
	}

	/**
	 * get authorized tab items for this tab
	 * 
	 * @param tab
	 * @return
	 */
	@Override
	public List<RsNavigationTabItem> getAuthorizedNavigationTabItemList(RsNavigationTab tab, String tabItemUri) {
		if (tab == null)
			return new ArrayList<RsNavigationTabItem>();

		tabItemUri = tabItemUri == null ? "" : tabItemUri.toLowerCase();

		List<RsNavigationTabItem> tabItemList = tab.getItemList();
		/*
		 * make the user is authenticated
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String[] roles = null;
		if (auth != null) {
			roles = convertToArray(auth.getAuthorities());
			System.out.println("******  - 2 - Retrieve authorized navigation item list ****************");
		} else {
			roles = new String[] { "ROLE_NOT_AUTHENTICATED" };
			System.out.println(
					"******  - 2 - NOT_AUTHENTICATED - Retrieve authorized navigation item list ****************");
		}

		List<RsNavigationTabItem> authorizedTabItemList = new ArrayList<RsNavigationTabItem>();
		for (RsNavigationTabItem tabItem : tabItemList) {
			tabItem.setActive(false);
			String[] tabItemRoles = tabItem.getRoles();
			if (tabItemRoles == null || tabItemRoles.length == 0) {
				authorizedTabItemList.add(tabItem);
			} else {

				for (String tabItemRole : tabItemRoles)
					if (isInRole(roles, tabItemRole)) {
						authorizedTabItemList.add(tabItem);
						break;
					}
			}
			/*
			 * check if the tab item should be active
			 */
			if (tabItem.getUri().toLowerCase().equals(tabItemUri))
				tabItem.setActive(true);

		}

		return authorizedTabItemList;
	}

	/**
	 * Check in a role coming from the category is in a user's list of roles
	 * 
	 * @param roles
	 * @param role
	 * @return
	 */
	private boolean isInRole(String[] roles, String role) {

		if (roles == null || roles.length == 0 || role == null)
			return false;
		role = "ROLE_" + role;
		for (String temp : roles) {
			if (temp.equalsIgnoreCase(role))
				return true;
		}
		return false;
	}

	/**
	 * convert a list of authorities to an array
	 * 
	 * @param authorities
	 * @return
	 */
	private String[] convertToArray(Collection<? extends GrantedAuthority> authorities) {
		String[] list = new String[authorities.size()];
		int index = 0;
		for (GrantedAuthority authority : authorities) {
			list[index] = authority.getAuthority();
			index++;
		}
		return list;

	}

}
