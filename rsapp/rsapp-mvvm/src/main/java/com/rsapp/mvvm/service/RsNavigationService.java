package com.rsapp.mvvm.service;

import java.util.List;

import com.rsapp.mvvm.model.RsNavigationTab;
import com.rsapp.mvvm.model.RsNavigationTabItem;

/*********************************************************************************************
 * @COPYRIGHT 					(c) 2019 ROMUSOFT, LLC., BILLINGS,MONTANA ALL RIGHTS RESERVED 
 * 				THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            	ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            	THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            	NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * @DESCRIPTION : This interface defines contracts for the tab navigation service.
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
public interface RsNavigationService {
	/**
	 * the current active tab
	 * 
	 * @return
	 */
	public RsNavigationTab getActiveTab();	

	public void setActiveTab(RsNavigationTab activeTab);
	public String getActiveTabName(String activeTabItemUri, List<RsNavigationTab> authorizedTabList);

	/**
	 * update the active tab
	 * set a default active tab when no target is passed
	 * @param tabName
	 * @param authorizedTabList
	 */
	public RsNavigationTab updateActiveTab(List<RsNavigationTab> authorizedTabList);
	public RsNavigationTab updateActiveTab(String tabName, List<RsNavigationTab> authorizedTabList);
	public RsNavigationTab updateActiveTab(String tabName, String tabItemUri, List<RsNavigationTab> authorizedTabList);

	/**
	 * retrieve the list of tabs the user is authorized to use
	 * 
	 * @return
	 */
	public List<RsNavigationTab> retrieveUserAuthorizedNavigationTabList();

	/**
	 * 
	 * @param tab
	 * @param tabItemUri
	 * @return
	 */
	public List<RsNavigationTabItem> getAuthorizedNavigationTabItemList(RsNavigationTab tab, String tabItemUri );

}
