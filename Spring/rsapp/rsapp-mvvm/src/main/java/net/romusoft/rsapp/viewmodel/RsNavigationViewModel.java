package net.romusoft.rsapp.viewmodel;

import java.io.Serializable;
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
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

import net.romusoft.rsapp.model.RsNavigationTab;
import net.romusoft.rsapp.model.RsNavigationTabItem;

/**
 * This navigation view is made available in the global advice as global
 * attribute available in every controller the tabs are filters based on the
 * roles in the security context holder for the current user
 * 
 * @author Emmanuel Romulus
 *
 */
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RsNavigationViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5269213245510759029L;

	/**
	 * cookie name: wp-pm-tools-old-tab
	 */
	public static final String COOKIE_NAME_OLD_TAB = "wp-pm-tools-old-tab";
	/**
	 * cookie name: wp-pm-tools-old-uri
	 */
	public static final String COOKIE_NAME_OLD_URI = "wp-pm-tools-old-uri";
	/**
	 * cookie name: wp-pm-tools-new-uri
	 */
	public static final String COOKIE_NAME_NEW_URI = "wp-pm-tools-new-uri";
	/**
	 * session attribute:rsNavigationViewModel
	 */
	public static final String RS_NAVIGATION_SESSION_ATTRIBUTE_NAME = "rsNavigationViewModel";

	/**
	 * css: rs-environment rs-production
	 */
	private final String CSS_ENVIRONMENT_PRODUCTION = "rs-environment rs-production";
	/**
	 * css: rs-environment rs-staging
	 */
	private final String CSS_ENVIRONMENT_STAGING = "rs-environment rs-staging";
	/**
	 * css: rs-environment rs-development
	 */
	private final String CSS_ENVIRONMENT_DEVELOPMENT = "rs-environment rs-development";
	/**
	 * css: rs-environment rs-qa
	 */
	private final String CSS_ENVIRONMENT_QA = "rs-environment rs-qa";

	/**
	 * active tab name
	 */
	private String activeTabName;
	/**
	 * active tab
	 */
	private RsNavigationTab activeTab;
	/**
	 * active tab item
	 */
	private RsNavigationTabItem activeTabItem;

	/**
	 * default to an empty list;
	 */
	private List<RsNavigationTab> authorizedTabList = new ArrayList<RsNavigationTab>(); //
	/**
	 * default to an empty list
	 */
	private List<RsNavigationTabItem> activeAuthorizedTabItemList = new ArrayList<RsNavigationTabItem>();
	/**
	 * whether the tabs are initialized for the user
	 */
	private boolean tabsInitialized;

	/**
	 * css: development environment
	 */
	private String cssEnvironment = CSS_ENVIRONMENT_DEVELOPMENT;
	/**
	 * old uri
	 */
	private String oldUri = null;
	/**
	 * old tab
	 */
	private String oldTab = null;
	/**
	 * new uri
	 */
	private String newUri = null;
	/**
	 * error tab
	 */
	private RsNavigationTab errorTab = new RsNavigationTab(-1, "Unauthorized", "Unauthorized Access");
	/**
	 * error tab item
	 */
	private RsNavigationTabItem errorTabItem = new RsNavigationTabItem("navigation-error", "Unauthorized Access",
			"Unauthorized Access Error");

	/**
	 * initialize error tab in the default constructor
	 */
	public RsNavigationViewModel() {

		this.errorTab.addItem(errorTabItem);

	}

	/**
	 * get the active tab name
	 * 
	 * @return the active tab name
	 */
	public String getActiveTabName() {
		return activeTabName;
	}

	/**
	 * Set the active tab name
	 * 
	 * @param activeTabName the active tab name
	 */
	public void setActiveTabName(String activeTabName) {
		this.activeTabName = activeTabName;
	}

	/**
	 * get the active tab. When no tab is active, the first tab in the list is
	 * selected by default.
	 * 
	 * @return the active tab
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

				if (activeTabItem != null) {
					activeTabItem.setActive(true);
				}
			}

		}

		return activeTab;
	}

	/**
	 * active tab item uri
	 * 
	 * @return active tab item uri
	 */
	public RsNavigationTabItem getActiveTabItem() {
		return activeTabItem;
	}

	/**
	 * the active tab item
	 * 
	 * @param activeTabItem the active tab item
	 */
	public void setActiveTabItem(RsNavigationTabItem activeTabItem) {
		this.activeTabItem = activeTabItem;
	}

	/**
	 * list of tabs including their tab items
	 * 
	 * @return a list of tabs including their tab items
	 */
	public List<RsNavigationTab> getAuthorizedTabList() {

		return authorizedTabList;
	}

	/**
	 * set the authorized tab list
	 * 
	 * @param authorizedTabList authorized tab list
	 */
	public void setAuthorizedTabList(List<RsNavigationTab> authorizedTabList) {
		//
		// set the authorized tab list

		this.authorizedTabList = authorizedTabList;

		this.tabsInitialized = true;
	}

	/**
	 * authorized list of tab items inside a tab
	 * 
	 * @return authorized list of tab items inside a tab
	 */
	public List<RsNavigationTabItem> getActiveAuthorizedTabItemList() {

		activeTab = this.getActiveTab();
		activeAuthorizedTabItemList = activeTab != null ? activeTab.getItemList() : null;
		return activeAuthorizedTabItemList;
	}

	/**
	 * the active authorized tab item list
	 * 
	 * @param activeAuthorizedTabItemList the active authorized tab item list
	 */
	public void setActiveAuthorizedTabItemList(List<RsNavigationTabItem> activeAuthorizedTabItemList) {

		this.activeAuthorizedTabItemList = activeAuthorizedTabItemList;
	}

	/**
	 * whether tabs have been initialized for the user
	 * 
	 * @return whether tabs have been initialized for the user
	 */
	public boolean isTabsInitialized() {
		return tabsInitialized;
	}

	/**
	 * set whether the tabs have been initialized for the current user.
	 * 
	 * @param tabsInitialized whether tabs have been initialized for the user
	 */
	public void setTabsInitialized(boolean tabsInitialized) {
		this.tabsInitialized = tabsInitialized;
	}

	/**
	 * whether to display the environment label production.
	 * 
	 * @return a css class for the environment variables
	 */
	public String getCssEnvironment() {
		return cssEnvironment;
	}

	/**
	 * set the css environment
	 * 
	 * @param cssEnvironment the environment CSS
	 */
	public void setCssEnvironment(String cssEnvironment) {
		this.cssEnvironment = cssEnvironment;
	}

	/**
	 * get the active tab item uri
	 * 
	 * @return the active tab item uri
	 */
	public String getActiveTabItemUri() {
		String activeTabItemUri = this.activeTabItem != null ? this.activeTabItem.getUri() : "#";
		return activeTabItemUri;
	}

	/**
	 * get the old uri from cookie. The old uri refers to the current uri when the
	 * user does a refresh of the browser at the root level of the application
	 * 
	 * @return the old uri
	 */
	public String getOldUri() {
		return oldUri;
	}

	/**
	 * set the old uri
	 * 
	 * @param oldUri the old uri to set
	 */
	public void setOldUri(String oldUri) {
		this.oldUri = oldUri;
	}

	/**
	 * get the old tab. See old uri
	 * 
	 * @return the old tab
	 */
	public String getOldTab() {
		return oldTab;
	}

	/**
	 * set the old tab
	 * 
	 * @param oldTab the old tab
	 */
	public void setOldTab(String oldTab) {
		this.oldTab = oldTab;
	}

	/**
	 * get the new uri. A uri the user navigates to
	 * 
	 * @return the new uri that was navigated to
	 */
	public String getNewUri() {
		return newUri;
	}

	/**
	 * set the new uri
	 * 
	 * @param newUri the new uri that ws navigated to
	 */
	public void setNewUri(String newUri) {
		this.newUri = newUri;
	}

	/**
	 * get the error tab in case of error
	 * 
	 * @return the error tab
	 */
	public RsNavigationTab getErrorTab() {
		return errorTab;
	}

	/**
	 * get the error tab item in case of a navigation error. This could be due to
	 * access violation
	 * 
	 * @return the error tab
	 */
	public RsNavigationTabItem getErrorTabItem() {
		return errorTabItem;
	}

	/**
	 * find the home uri
	 * 
	 * @return the home uri
	 */
	public String findHomeUri() {

		/*
		 * set the active tab
		 */
		activeTab = authorizedTabList.size() > 0 ? authorizedTabList.get(0) : null;

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

			return activeTabItem.getUri();

		}

		return null;
	}

	/**
	 * update the application environment
	 * 
	 * @param environment the new environment value
	 */
	public void updateApplicationEnvironment(String environment) {

		environment = environment.toUpperCase();
		switch (environment) {
		case "DEVELOPMENT":
		case "DEV":
			cssEnvironment = CSS_ENVIRONMENT_DEVELOPMENT;
			break;
		case "PRODUCTION":
		case "PROD":
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

	/**
	 * Check whether the user has one or more roles
	 * 
	 * @param roles list of roles to check
	 * @return whether the user is in the list of roles
	 */
	public static boolean isInRole(String... roles) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		for (GrantedAuthority authority : authorities) {

			for (String role : roles) {
				if (authority.getAuthority().toUpperCase().equals(role.toUpperCase())) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * find the navigation view model from the current session
	 * 
	 * @param request  the http servlet request
	 * @param response the http servlet response
	 * @return return the navigation viewmodel from the user session
	 */
	public static RsNavigationViewModel findModelView(HttpServletRequest request, HttpServletResponse response) {
		RsNavigationViewModel viewModel = null;
		HttpSession session = request.getSession();
		Object temp = session.getAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME);
		if (temp != null && temp instanceof RsNavigationViewModel) {
			viewModel = (RsNavigationViewModel) temp;
		}

		String cookieValue = RsNavigationViewModel.findCookie(request, RsNavigationViewModel.COOKIE_NAME_OLD_URI);
		String oldUri = cookieValue != null ? cookieValue : viewModel.getActiveTabItemUri();
		//
		// old tab
		cookieValue = RsNavigationViewModel.findCookie(request, RsNavigationViewModel.COOKIE_NAME_OLD_TAB);
		String oldTab = cookieValue != null ? cookieValue : viewModel.getActiveTab().getName();
		viewModel.setOldTab(oldTab);
		viewModel.setOldUri(oldUri);

		return viewModel;
	}

	/**
	 * set the same path for the cookie based on the user's agent
	 * 
	 * @param request  the http servlet request
	 * @param response the http servlet response
	 * @param name     the name of the cookie
	 * @param value    the value of the cookie
	 */
	public static void updateCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String value) {

		Cookie targetCookie = new Cookie(name, value);
		targetCookie.setPath("/wp-pm-tools");
		//
		// add/update the cookie
		response.addCookie(targetCookie);
	}

	/**
	 * get a cookie by name
	 * 
	 * @param httpRequest http servlet request to pull cookies from
	 * @param cookieName  the name of the cookie to get
	 * @return the cookie value
	 */
	public static String findCookie(HttpServletRequest httpRequest, String cookieName) {
		for (Cookie cookie : httpRequest.getCookies()) {
			if (cookie.getName().equals(cookieName)) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
