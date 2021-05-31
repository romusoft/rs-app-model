package net.romusoft.rsapp.mvvm;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * View to use in a view Model
 * 
 * @author Emmanuel Romulus
 *
 */
public abstract class RsAbstractView implements IRsView {
	public static final String HTML_TITLE_ATTRIBUTE_NAME = "htmlTitle";
	public static final String PAGE_TITLE_ATTRIBUTE_NAME = "pageTitle";
	public static final String PAGE_TITLE_DESCRIPTION_ATTRIBUTE_NAME = "pageTitleDescription";

	public static final String API_BASE_URL_ATTRIBUTE_NAME = "apiBaseUrl";
	public static final String URI_CREATE_ATTRIBUTE_NAME = "uriCreate";
	public static final String URI_READ_ATTRIBUTE_NAME = "uriRead";
	public static final String URI_UPDATE_ATTRIBUTE_NAME = "uriUpdate";
	public static final String URI_DELETE_ATTRIBUTE_NAME = "uriDelete";
	public static final String URI_DETAIL_ATTRIBUTE_NAME = "uriDetail";
	public static final String URI_LIST_ATTRIBUTE_NAME = "uriList";

	public static final String NAVIGATION_URL_ATTRIBUTE_NAME = "navigationUrl";

	public static final String START_DATE_ATTRIBUTE_NAME = "startDate";
	public static final String END_DATE_ATTRIBUTE_NAME = "endDate";

	public static final String HTML_PATH_ATTRIBUTE_NAME = "html";
	public static final String CSS_PATH_ATTRIBUTE_NAME = "css";
	public static final String JS_PATH_ATTRIBUTE_NAME = "js";

	public static final String MVC_CREATE = "/create";
	public static final String MVC_READ = "/read";
	public static final String MVC_UPDATE = "/update";
	public static final String MVC_DELETE = "/delete";
	public static final String MVC_DETAIL = "/detail";
	public static final String MVC_LIST = "/list";

	public static final String API = "/api";
	public static final String API_CREATE = "create";
	public static final String API_READ = "read";
	public static final String API_UPDATE = "update";
	public static final String API_DELETE = "delete";
	public static final String API_DETAIL = "detail";
	public static final String API_LIST = "list";

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

	private String navigationUrl = "";
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
	public RsAbstractView() {
		initialize(null);
	}

	/**
	 *  constructor with the spring mvc model where model attribute can be stored
	 * @param model spring mvc model where model attribute can be stored
	 */
	public RsAbstractView(Model model) {
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
	public abstract String getNavigationUrl();

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
	public abstract String getUriCreate();

	/**
	 * 
	 */
	@Override
	public abstract String getUriUpdate();

	/**
	 * 
	 */
	@Override
	public abstract String getUriDelete();

	/**
	 * 
	 */
	@Override
	public abstract String getUriDetail();

	/**
	 * initialize the view with its basic components such as html, css, javaScript
	 * path
	 * 
	 * @param model
	 */
	private void initialize(Model model) {

		this.htmlTitle = getHtmlTitle();
		this.pageTitle = getPageTitle();
		this.pageTitleDescription = getPageTitleDescription();
		/*
		 * api endpoints
		 */
		this.apiBaseUrl = getApiBaseUrl();
		this.uriRead = getUriRead();
		this.uriCreate = getUriCreate();
		this.uriUpdate = getUriCreate();
		this.uriDelete = getUriDelete();
		this.uriDetail = getUriDetail();

		this.navigationUrl = getNavigationUrl();
		//
		// file paths
		this.html = getHtml();
		this.css = getCss();
		this.js = getJs();

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
		model.addAttribute(NAVIGATION_URL_ATTRIBUTE_NAME, this.navigationUrl);
		model.addAttribute(HTML_PATH_ATTRIBUTE_NAME, this.html);
		model.addAttribute(CSS_PATH_ATTRIBUTE_NAME, this.css);
		model.addAttribute(JS_PATH_ATTRIBUTE_NAME, this.js);

	}

}
