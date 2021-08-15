package net.romusoft.rsapp.mvvm;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * View to use in a view Model
 * 
 * @author Emmanuel Romulus
 *
 */
public abstract class RsAbstractReadOnlyView implements IRsView {

	@JsonIgnore
	private Model model = null;
	//
	// file paths
	@JsonIgnore
	private String html = "";
	@JsonIgnore
	private String css = "";
	@JsonIgnore
	private String js = "";

	private String htmlTitle = "";
	private String pageTitle = "";
	private String pageTitleDescription = "";

	//
	// mvc end points
	private String mvcRedirectUrl = "";
	private String mvcBaseUrl = "";
	/*
	 * api endpoints
	 */
	private String apiBaseUrl = "";
	private String uriRead = "";
	private String uriCreate = "";
	private String uriUpdate = "";
	private String uriDelete = "";
	private String uriDetail = "";

	/**
	 * default constructor
	 */
	public RsAbstractReadOnlyView() {
		initialize(null);
	}

	/**
	 * constructor with the spring mvc model where model attribute can be stored
	 * 
	 * @param model spring mvc model where model attribute can be stored
	 */
	public RsAbstractReadOnlyView(Model model) {
		initialize(model);
	}

	/************************************************************/
	/**
	 * 
	 */
	@Override
	public Model getModel() {
		return model;
	}

	/**
	 * 
	 */
	@Override
	public void setModel(Model model) {
		this.model = model;
		initialize(model);

	}

	/**
	 * 
	 */
	@Override
	public abstract String getHtml();

	/**
	 * 
	 */
	@Override
	public abstract String getCss();

	/**
	 * 
	 */
	@Override
	public abstract String getJs();

	/**
	 * 
	 */
	@Override
	public abstract String getMvcBaseUrl();

	/**
	 * 
	 */
	@Override
	public String getMvcRedirectUrl() {
		return "";
	}

	/**
	 * 
	 */
	@Override
	public abstract String getHtmlTitle();

	/**
	 * 
	 */
	@Override
	public abstract String getPageTitle();

	/**
	 * 
	 */
	@Override
	public abstract String getPageTitleDescription();

	/**
	 * 
	 */
	@Override
	public abstract String getApiBaseUrl();

	/**
	 * 
	 */
	@Override
	public abstract String getUriRead();

	/**
	 * 
	 */
	@Override
	public String getUriCreate() {
		return "";
	}

	/**
	 * 
	 */
	@Override
	public String getUriUpdate() {
		return "";
	}

	/**
	 * 
	 */
	@Override
	public String getUriDelete() {
		return "";
	}

	/**
	 * 
	 */
	@Override
	public String getUriDetail() {
		return "";
	}

	/**
	 * initialize the view with its basic components such as html, css, javaScript
	 * path
	 * 
	 * @param model
	 */
	private void initialize(Model model) {

		this.htmlTitle = getHtmlTitle() == null ? "" : getHtmlTitle();
		this.pageTitle = getPageTitle() == null ? "" : getPageTitle();
		this.pageTitleDescription = getPageTitleDescription() == null ? "" : getPageTitleDescription();
		/*
		 * api endpoints
		 */
		this.apiBaseUrl = getApiBaseUrl() == null ? "" : getApiBaseUrl();
		this.uriRead = getUriRead() == null ? "" : getUriRead();
		this.uriCreate = getUriCreate() == null ? "" : getUriCreate();
		this.uriUpdate = getUriCreate() == null ? "" : getUriCreate();
		this.uriDelete = getUriDelete() == null ? "" : getUriDelete();
		this.uriDetail = getUriDetail() == null ? "" : getUriDetail();

		this.mvcBaseUrl = getMvcBaseUrl() == null ? "" : getMvcBaseUrl();
		this.mvcRedirectUrl = getMvcRedirectUrl() == null ? "" : getMvcRedirectUrl();
		//
		// file paths
		this.html = getHtml() == null ? "" : getHtml();
		this.css = getCss() == null ? "" : getCss();
		this.js = getJs() == null ? "" : getJs();

		if (model == null)
			return;
		model.addAttribute(HTML_TITLE_ATTRIBUTE_NAME, this.htmlTitle);
		model.addAttribute(PAGE_TITLE_ATTRIBUTE_NAME, this.pageTitle);
		model.addAttribute(PAGE_TITLE_DESCRIPTION_ATTRIBUTE_NAME, this.pageTitleDescription);

		model.addAttribute(API_BASE_URL_ATTRIBUTE_NAME, this.apiBaseUrl);

		model.addAttribute(URI_CREATE_ATTRIBUTE_NAME, this.uriCreate);
		model.addAttribute(URI_READ_ATTRIBUTE_NAME, this.uriRead);
		model.addAttribute(URI_UPDATE_ATTRIBUTE_NAME, this.uriUpdate);
		model.addAttribute(URI_DELETE_ATTRIBUTE_NAME, this.uriDelete);
		model.addAttribute(URI_DETAIL_ATTRIBUTE_NAME, this.uriDetail);
		//
		// where to navigation after an action is complete: update or cancel
		model.addAttribute(MVC_BASE_URL_ATTRIBUTE_NAME, this.mvcBaseUrl);
		model.addAttribute(MVC_REDIRECT_URL_ATTRIBUTE_NAME, this.mvcRedirectUrl);

		model.addAttribute(HTML_PATH_ATTRIBUTE_NAME, this.html);
		model.addAttribute(CSS_PATH_ATTRIBUTE_NAME, this.css);
		model.addAttribute(JS_PATH_ATTRIBUTE_NAME, this.js);

	}

}
