package net.romusoft.rsapp.mvvm.model;

import net.romusoft.rsapp.mvvm.RsAbstractView;

/**
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsDefaultView extends RsAbstractView {

	/**
	 * default title
	 */
	String htmlTitle = "Hello World of RSAPP!!!";

	/**
	 * 
	 */
	@Override
	public String getHtml() {
		return "mod-main/feature-activity/view-activity-list/test";
	}

	/**
	 * 
	 */
	@Override
	public String getCss() {
		return "mod-main/feature-activity/view-activity-list/test.css";
	}

	/**
	 * 
	 */
	@Override
	public String getJs() {
		return "mod-main/feature-activity/view-activity-list/test.js";
	}

	/**
	 * 
	 */
	@Override
	public String getHtmlTitle() {
		return htmlTitle;
	}

	/**
	 * 
	 */
	@Override
	public String getPageTitle() {
		return htmlTitle;
	}

	/**
	 * 
	 */
	@Override
	public String getPageTitleDescription() {
		return htmlTitle;
	}

	/**
	 * 
	 */
	@Override
	public String getApiBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public String getUriRead() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public String getUriCreate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public String getUriUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public String getUriDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public String getUriDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMvcBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMvcRedirectUrl() {
		// TODO Auto-generated method stub
		return null;
	}

}
