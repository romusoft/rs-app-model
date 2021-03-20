package com.sample.module.main.scheme.activity;

import com.rsapp.mvvm.RsAbstractView;

public class VActivityDetail extends RsAbstractView {
	String htmlTitle = "Detail Screen";

	@Override
	public String getHtml() {
		return "mod-main/scheme-activity/view-activity-detail/test";
	}

	@Override
	public String getCss() {
		return "mod-main/scheme-activity/view-activity-list/test.css";
	}

	@Override
	public String getJs() {
		return "mod-main/scheme-activity/view-activity-list/test.js";
	}

	@Override
	public String getNavigationUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHtmlTitle() {
		return htmlTitle;
	}

	@Override
	public String getPageTitle() {
		return htmlTitle;
	}

	@Override
	public String getPageTitleDescription() {
		return htmlTitle;
	}

	@Override
	public String getApiBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUriRead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUriCreate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUriUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUriDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUriDetail() {
		// TODO Auto-generated method stub
		return null;
	}
}
