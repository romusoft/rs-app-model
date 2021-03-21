package net.romusoft.rsapp.mvvm.controller;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.romusoft.rsapp.mvvm.model.RsAssemblyInfo;
import net.romusoft.rsapp.mvvm.model.RsNavigationTab;
import net.romusoft.rsapp.mvvm.model.RsNavigationTabItem;
import net.romusoft.rsapp.mvvm.service.RsNavigationService;

/**
 * controller advice for global controller attributes every request from a
 * controller ends up invoking this controller. It it is also used in the tab
 * navigation service to determines tabs and tab items and given user can
 * access.
 * 
 * @author romulus
 *
 */
@ControllerAdvice(annotations = { Controller.class })
public class RsNavigationControllerAdvice {

	/*
	 * name of the navigation mode view
	 */

	@Autowired
	private RsNavigationService navigationService;

	@Autowired
	private RsAssemblyInfo assemblyInfo;

	/**
	 * retrieve and set the list of tabs for the current user
	 * 
	 * @return
	 */
	@ModelAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME)
	public RsNavigationViewModel getNavigationTabs(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		if (response.getStatus() == 404)
			return null;
		//
		// return an empty model, if the user has not been authenticated
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return new RsNavigationViewModel();
		}
		// if no tab is specified return whatever is store in the session for navigation
		// model view
		RsNavigationViewModel viewModel = null;
		Object temp = session.getAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME);
		if (temp != null && temp instanceof RsNavigationViewModel) {
			viewModel = (RsNavigationViewModel) temp;
		} else {
			viewModel = new RsNavigationViewModel();
			viewModel.updateApplicationEnvironment(assemblyInfo.getApplicationEnvironment());
			//
			// retrieve the authorized tabs
			List<RsNavigationTab> tabList = navigationService.retrieveUserAuthorizedNavigationTabList();
			viewModel.setAuthorizedTabList(tabList);
			RsNavigationTab activeTab = navigationService.updateActiveTab(tabList);
			//
			// set the authorized items set the default item to be active
			if (activeTab != null) {
				List<RsNavigationTabItem> itemList = navigationService.getAuthorizedNavigationTabItemList(activeTab,
						null);
				activeTab.setItemList(itemList);
			}
		}
		//
		// get the request URI and remove the context path from the uri
		String activeTabItemUri = request.getRequestURI();
		activeTabItemUri = activeTabItemUri.replace(request.getContextPath() + "/", "");
		//
		// check if the uri has a corresponding tab if no tab is specified, find a tab
		// name
		RsNavigationTab activeTab = null;
		List<RsNavigationTab> authorizedTabList = viewModel.getAuthorizedTabList();
		String activeTabName = request.getParameter("tab");
		if (activeTabName == null) {
			activeTab = viewModel.getActiveTab();
		} else {

			//
			// set active tab and tab item if the tab name exist
			activeTab = activeTabItemUri != null
					? navigationService.updateActiveTab(activeTabName, activeTabItemUri, authorizedTabList)
					: navigationService.updateActiveTab(activeTabName, authorizedTabList);

			if (activeTab != null) {
				//
				// if the list of items is not initialized for the tab initialize it based on
				// the user's roles to restrict access
				if (activeTab.isTabItemsInitialized() == false) {
					List<RsNavigationTabItem> itemList = navigationService.getAuthorizedNavigationTabItemList(activeTab,
							activeTabItemUri);
					activeTab.setItemList(itemList);
				}
				//
				// set the active tab and tab item to be used in the view model
				viewModel.setActiveAuthorizedTabItemList(activeTab.getItemList());
				viewModel.setActiveTabItem(activeTab.getActiveTabItem());
			}

		}
		//
		// save the navigation view model in the session
		session.setAttribute(RsNavigationViewModel.RS_NAVIGATION_SESSION_ATTRIBUTE_NAME, viewModel);
		session.setAttribute(RsAssemblyInfo.RS_ASSEMBLY_INFO_SESSION_ATTRIBUTE_NAME, assemblyInfo);

		return viewModel;

	}

}
