package net.romusoft.rsapp.mvvm.service;

import java.util.List;

import net.romusoft.rsapp.mvvm.model.RsNavigationTab;
import net.romusoft.rsapp.mvvm.model.RsNavigationTabItem;

/*********************************************************************************************
 * @COPYRIGHT 					(c) 2021 ROMUSOFT, LLC., BILLINGS,MONTANA ALL RIGHTS RESERVED 
 * 				THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            	ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            	THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            	NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * @DESCRIPTION : This interface defines contracts for the tab navigation service.
 * 
 * 
 * @PROGRAM : application template :  04/07/2021 FUNCTION :
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
public interface RsNavigationService {

	/**
	 * get the tablist configuration for this application
	 * 
	 * @return the instance of the tab list
	 */
	public List<RsNavigationTab> getTabList();

	/**
	 * the current active tab
	 * 
	 * @return the current active tab
	 */
	public RsNavigationTab getActiveTab();

	/**
	 * the active tab
	 * 
	 * @param activeTab the active tab
	 */
	public void setActiveTab(RsNavigationTab activeTab);

	/**
	 * get the active tab name from the authorized tab list
	 * 
	 * @param activeTabItemUri  active tab uri
	 * @param authorizedTabList authorized tab list
	 * @return the active tab name
	 */
	public String getActiveTabName(String activeTabItemUri, List<RsNavigationTab> authorizedTabList);

	/**
	 * update the active tab, set a default active tab when no target is passed
	 * 
	 * @param authorizedTabList the authorized tab list
	 * @return tab that was set to active
	 */
	public RsNavigationTab updateActiveTab(List<RsNavigationTab> authorizedTabList);

	/**
	 * update the active tab by name and return it.
	 * 
	 * @param tabName           the target active tab name
	 * @param authorizedTabList the authorized tab list
	 * @return tab that was set to active
	 */
	public RsNavigationTab updateActiveTab(String tabName, List<RsNavigationTab> authorizedTabList);

	/**
	 * update the active tab by name and item uri
	 * 
	 * @param tabName           the target active tab name
	 * @param tabItemUri        the target uri
	 * @param authorizedTabList the authorized tab list
	 * @return tab that was set to active
	 */
	public RsNavigationTab updateActiveTab(String tabName, String tabItemUri, List<RsNavigationTab> authorizedTabList);

	/**
	 * retrieve the list of tabs the user is authorized to use
	 * 
	 * @return the list of authorized tabs
	 */
	public List<RsNavigationTab> retrieveUserAuthorizedNavigationTabList();

	/**
	 * the list of authorized tab items
	 * 
	 * @param tab        target tab
	 * @param tabItemUri target tab item uri
	 * @return the list of authorized tab items
	 */
	public List<RsNavigationTabItem> getAuthorizedNavigationTabItemList(RsNavigationTab tab, String tabItemUri);

}
