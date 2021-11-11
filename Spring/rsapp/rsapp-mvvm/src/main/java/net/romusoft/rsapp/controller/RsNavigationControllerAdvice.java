package net.romusoft.rsapp.controller;

import java.security.Principal;
/*********************************************************************************************
 * @COPYRIGHT 					(c) 2019 ROMUSOFT, LLC., BILLINGS,MONTANA ALL RIGHTS RESERVED 
 * 				THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            	ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            	THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            	NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * @DESCRIPTION : 	controller advice for global controller attributes
 * 					every request from a controller ends up invoking this controller.
 * 					It it is also used in the tab navigation service to determines tabs and tab items 
 * 					and given user can access.
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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.romusoft.rsapp.model.RsAssemblyInfo;
import net.romusoft.rsapp.model.RsNavigationTab;
import net.romusoft.rsapp.model.RsNavigationTabItem;
import net.romusoft.rsapp.service.RsNavigationService;
import net.romusoft.rsapp.util.RsGeneralUtilities;
import net.romusoft.rsapp.viewmodel.RsNavigationViewModel;

/**
 * controller advice for global controller attributes every request from a
 * controller ends up invoking this controller. It it is also used in the tab
 * navigation service to determines tabs and tab items and given user can
 * access.
 * 
 * @author Emmanuel Romulus
 *
 */
@ControllerAdvice(annotations = { MvcController.class })
public class RsNavigationControllerAdvice {
	@Autowired
	private RsNavigationService navigationService;

	@Autowired
	private RsAssemblyInfo assemblyInfo;

	/**
	 * retrieve and set the list of tabs for the current use
	 * 
	 * @param request  http servlet request to get the session from
	 * @param response http servlet response used to add uri cookies
	 * @return the navigation viewmodel object
	 * @throws Exception the exception that was thrown
	 */
	@ModelAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME)
	public RsNavigationViewModel getNavigationTabs(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RsNavigationViewModel viewModel = this.handleNavigationTabs(request, response);
		return viewModel;

	}

	/**
	 * handle the tab navigation and determine which tab or tab item is currently
	 * active
	 * 
	 * @param request  http servlet request to get the session from
	 * @param response http servlet response used to add uri cookies
	 * @return the navigation viewmodel object
	 * @throws Exception the exception that was thrown
	 */
	public RsNavigationViewModel handleNavigationTabs(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/**
		 * application is not using the tabs feature for navigation
		 */
		if (navigationService.getTabList() == null || navigationService.getTabList().isEmpty()) {
			return null;
		}

		Principal principal = request.getUserPrincipal();
		if (principal == null) {
			return null;
		}
		HttpSession session = request.getSession(false);
		RsNavigationViewModel viewModel = null;
		Object temp = session.getAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME);
		if (temp != null && temp instanceof RsNavigationViewModel) {
			viewModel = (RsNavigationViewModel) temp;
		} else {
			viewModel = new RsNavigationViewModel();
			session.setAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME, viewModel);
			session.setAttribute(RsAssemblyInfo.RS_ASSEMBLY_INFO_SESSION_ATTRIBUTE_NAME, assemblyInfo);
		}

		//
		// if no tab is specified return whatever is stored in the session for
		// navigation
		// model view
		// get the request URI and remove the context path from the uri if invalid uri,
		// return
		RsNavigationTab activeTab = null;
		if (viewModel.isTabsInitialized() == false) {
			//
			// retrieve the authorized tabs. Then, set the authorized items set the default
			// item to be active; the authorized sub-items

			List<RsNavigationTab> tabList = navigationService.retrieveUserAuthorizedNavigationTabList();
			viewModel.setAuthorizedTabList(tabList);
			activeTab = navigationService.updateActiveTab(tabList);
			if (activeTab == null) {
				throw new Exception("CURRENT USER does is not authorized for any tab in the application");
			}

			//
			// session for the navigation view model
			viewModel.updateApplicationEnvironment(assemblyInfo.getApplicationEnvironment());
			viewModel.setTabsInitialized(true);
			session.setAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME, viewModel);
		}

		//
		// only process get request by excluding PUT, POST, AND DELETE http methods
		if (request.getMethod().equalsIgnoreCase("GET") == false) {
			return viewModel;
		}

		//
		// iframe support
		String parentParameter = request.getQueryString();
		if (parentParameter != null && parentParameter.contains("parent")) {
			return viewModel;
		}

		//
		// no need to process anything for api calls. Just return the viewmodel
		String newUri = request.getRequestURI();
		newUri = newUri.replace(request.getContextPath() + "/", "");
		if (newUri.isEmpty() || newUri.contains("api/") || newUri.contains("#/")
				|| newUri.contains("/br-portal-status-data")) {
			return viewModel; // no further processing necessary for api calls
		}

		//
		// old uri
		String cookieValue = RsNavigationViewModel.findCookie(request, RsNavigationViewModel.COOKIE_NAME_OLD_URI);
		String oldUri = cookieValue != null ? cookieValue : viewModel.getActiveTabItemUri();
		//
		// old tab
		cookieValue = RsNavigationViewModel.findCookie(request, RsNavigationViewModel.COOKIE_NAME_OLD_TAB);
		String oldTab = cookieValue != null ? cookieValue : viewModel.getActiveTab().getName();

		viewModel.setOldTab(oldTab);
		viewModel.setOldUri(oldUri);
		viewModel.setNewUri(newUri);
		//
		// check if the uri has a corresponding tab if no tab is specified, find a tab
		// name

		String targetTabName = request.getParameter("tab");
		if (targetTabName == null || targetTabName.isEmpty()) {
			targetTabName = oldTab;
		}
		if (RsGeneralUtilities.isNullOrEmpty(targetTabName)) {
			targetTabName = viewModel.getActiveTab().getName();
		}

		//
		// set active tab and tab item if the tab name exists
		List<RsNavigationTab> authorizedTabList = viewModel.getAuthorizedTabList();
		activeTab = newUri != null ? navigationService.updateActiveTab(targetTabName, newUri, authorizedTabList)
				: navigationService.updateActiveTab(targetTabName, authorizedTabList);

		//
		// if active tab is still null, get the first available tab in the user's
		// authorized list.
		// If the list is empty, user is not authorized
		if (activeTab == null) {
			activeTab = viewModel.getActiveTab();
			if (activeTab == null)
				throw new Exception("CURRENT USER  is not authorized for any tab in the application");
		}
		//
		// set the cookies
		//
		RsNavigationViewModel.updateCookie(request, response, RsNavigationViewModel.COOKIE_NAME_OLD_TAB,
				activeTab.getName());
		RsNavigationViewModel.updateCookie(request, response, RsNavigationViewModel.COOKIE_NAME_OLD_URI,
				activeTab.getActiveTabItem().getUri());
		RsNavigationViewModel.updateCookie(request, response, RsNavigationViewModel.COOKIE_NAME_NEW_URI, newUri);

		//
		// if the list of items is not initialized for the tab, initialize it based on
		// the user's roles to restrict access
		if (activeTab.isTabItemsInitialized() == false) {
			List<RsNavigationTabItem> itemList = navigationService.getAuthorizedNavigationTabItemList(activeTab, null);
			itemList = navigationService.getAuthorizedNavigationTabItemList(activeTab, newUri);
			activeTab.setItemList(itemList);
		}
		//
		// set the active tab and tab item to be used in the view model
		viewModel.setActiveAuthorizedTabItemList(activeTab.getItemList());
		viewModel.setActiveTabItem(activeTab.getActiveTabItem());
		/*
		 * save the navigation view model in the session
		 */
		session.setAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME, viewModel);
		return viewModel;
	}

}
