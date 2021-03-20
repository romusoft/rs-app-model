package com.rsapp.mvvm;

import org.springframework.ui.Model;

public interface IRsView {

	/************************************************************/
	Model getModel();

	void setModel(Model model);

	String getHtmlTitle();

	String getPageTitle();

	String getPageTitleDescription();

	String getApiBaseUrl();

	String getUriRead();

	String getUriCreate();

	String getUriUpdate();

	String getUriDelete();

	String getUriDetail();

	String getNavigationUrl();

	String getHtml();

	String getCss();

	String getJs();

}