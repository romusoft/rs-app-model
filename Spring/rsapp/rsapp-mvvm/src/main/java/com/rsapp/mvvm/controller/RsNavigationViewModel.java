package com.rsapp.mvvm.controller;

/*********************************************************************************************
 * @COPYRIGHT 					(c) 2019 ROMUSOFT, LLC., BILLINGS,MONTANA ALL RIGHTS RESERVED 
 * 				THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            	ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            	THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            	NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * @DESCRIPTION : 	This navigation view is made available in the global advice as global
 * 					attribute available in every controller the tabs are filters based on the
 * 					roles in the security context holder for the current user
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
import java.util.List;

import com.rsapp.mvvm.model.RsNavigationTab;
import com.rsapp.mvvm.model.RsNavigationTabItem;

/**
 * This navigation view is made available in the global advice as global
 * attribute available in every controller the tabs are filters based on the
 * roles in the security context holder for the current user
 * 
 * @author romulus
 *
 */
public class RsNavigationViewModel {
	public static final String RS_NAVIGATION_SESSION_ATTRIBUTE_NAME = "rsNavigationViewModel";

	private final String CSS_ENVIRONMENT_PRODUCTION = "rs-environment rs-production";
	private final String CSS_ENVIRONMENT_STAGING = "rs-environment rs-staging";
	private final String CSS_ENVIRONMENT_DEVELOPMENT = "rs-environment rs-development";
	private final String CSS_ENVIRONMENT_QA = "rs-environment rs-qa";

	private String activeTabName;
	private RsNavigationTab activeTab;
	private RsNavigationTabItem activeTabItem;

	private List<RsNavigationTab> authorizedTabList = new ArrayList<RsNavigationTab>(); // default to an empty list;
	private List<RsNavigationTabItem> activeAuthorizedTabItemList = new ArrayList<RsNavigationTabItem>(); // default to an
	private boolean tabsInitialized;

	private String cssEnvironment = CSS_ENVIRONMENT_DEVELOPMENT;

	/**
	 * get the active tab name
	 * 
	 * @return
	 */
	public String getActiveTabName() {
		return activeTabName;
	}

	public void setActiveTabName(String activeTabName) {
		this.activeTabName = activeTabName;
	}

	/**
	 * 
	 * @return
	 */
	public RsNavigationTab getActiveTab() {

		/*
		 * set the active tab
		 */
		activeTab = authorizedTabList.stream().filter(temp -> temp.isActive()).findAny().orElse(null);
		if (activeTab == null) {
			activeTab = authorizedTabList.size() > 0 ? authorizedTabList.get(0) : null;
		}

		/*
		 * when the active tab is not null set the active tab item
		 */
		if (activeTab != null) {

			this.activeTabName = activeTab.getName();
			/*
			 * set active tab item
			 */
			activeTabItem = activeTab.getItemList().stream().filter(temp -> temp.isActive()).findAny().orElse(null);
			if (activeTabItem == null) {
				activeTabItem = activeTab.getItemList().size() > 0 ? activeTab.getItemList().get(0) : null;
				activeTabItem.setActive(true);
			}

		}

		return activeTab;
	}

	/**
	 * active tab item uri
	 * 
	 * @return
	 */

	public RsNavigationTabItem getActiveTabItem() {
		return activeTabItem;
	}

	public void setActiveTabItem(RsNavigationTabItem activeTabItem) {
		this.activeTabItem = activeTabItem;
	}

	/**
	 * list of tabs including their tab items
	 * 
	 * @return
	 */
	public List<RsNavigationTab> getAuthorizedTabList() {
		return authorizedTabList;
	}

	public void setAuthorizedTabList(List<RsNavigationTab> authorizedTabList) {
		this.authorizedTabList = authorizedTabList;

		this.tabsInitialized = true;
	}

	/**
	 * authorized list of tab items inside a tab
	 * 
	 * @return
	 */
	public List<RsNavigationTabItem> getActiveAuthorizedTabItemList() {

		activeTab = this.getActiveTab();
		activeAuthorizedTabItemList = activeTab != null ? activeTab.getItemList() : null;
		return activeAuthorizedTabItemList;
	}

	public void setActiveAuthorizedTabItemList(List<RsNavigationTabItem> activeAuthorizedTabItemList) {

		this.activeAuthorizedTabItemList = activeAuthorizedTabItemList;
	}

	/**
	 * where tabs have been initialized for the user
	 * 
	 * @return
	 */
	public boolean isTabsInitialized() {
		return tabsInitialized;
	}

	public void setTabsInitialized(boolean tabsInitialized) {
		this.tabsInitialized = tabsInitialized;
	}

	/**
	 * whether to display the environment label production does not display any
	 * environment label Any other environment displays their label.
	 * 
	 * @return
	 */
	public String getCssEnvironment() {
		return cssEnvironment;
	}

	public void setCssEnvironment(String cssProductionEnvironment) {
		this.cssEnvironment = cssProductionEnvironment;
	}

	/**
	 * 
	 * @param environment
	 */
	public void updateApplicationEnvironment(String environment) {

		environment = environment.toUpperCase();
		switch (environment) {
		case "DEVELOPMENT":
			cssEnvironment = CSS_ENVIRONMENT_DEVELOPMENT;
			break;
		case "PRODUCTION":
			cssEnvironment = CSS_ENVIRONMENT_PRODUCTION;
			break;
		case "STAGING":
			cssEnvironment = CSS_ENVIRONMENT_STAGING;
			break;
		case "QA":
			cssEnvironment = CSS_ENVIRONMENT_QA;
			break;
		default:
			cssEnvironment = CSS_ENVIRONMENT_DEVELOPMENT;
			break;
		}

	}
}
