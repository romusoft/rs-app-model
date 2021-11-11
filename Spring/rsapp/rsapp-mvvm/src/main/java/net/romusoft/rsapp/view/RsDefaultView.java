package net.romusoft.rsapp.view;

import net.romusoft.rsapp.enums.ViewType;
import net.romusoft.rsapp.mvvm.RsViewBase;

/**
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsDefaultView extends RsViewBase {

	/**
	 * default title
	 */
	String htmlTitle = "Hello World of RSAPP!!!";

	/**
	 * 
	 */
	@Override
	public String getHtml() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * css
	 */
	@Override
	public String getCss() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * js
	 */
	@Override
	public String getJs() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * mvc
	 */
	@Override
	public String getMvcBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * html
	 */
	@Override
	public String getHtmlTitle() {
		return htmlTitle;
	}

	/**
	 * page title
	 */
	@Override
	public String getPageTitle() {
		return htmlTitle;
	}

	/**
	 * page tile tooltip
	 */
	@Override
	public String getPageTitleDescription() {
		return htmlTitle;
	}

	/**
	 * api
	 */
	@Override
	public String getApiBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * init
	 */
	@Override
	public void init() {

	}

	/**
	 * view type
	 */
	@Override
	public ViewType getViewType() {
		return ViewType.JSP;
	}

}
