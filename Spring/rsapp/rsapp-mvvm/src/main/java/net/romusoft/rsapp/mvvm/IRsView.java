package net.romusoft.rsapp.mvvm;

import org.springframework.ui.Model;

/**
 * 
 * @author Emmanuel Romulus
 *
 */
public interface IRsView {
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

	public static final String START_DATE_ATTRIBUTE_NAME = "startDate";
	public static final String END_DATE_ATTRIBUTE_NAME = "endDate";

	public static final String HTML_PATH_ATTRIBUTE_NAME = "html";
	public static final String CSS_PATH_ATTRIBUTE_NAME = "css";
	public static final String JS_PATH_ATTRIBUTE_NAME = "js";

	public static final String MVC_BASE_URL_ATTRIBUTE_NAME = "mvcBaseUrl";
	public static final String MVC_REDIRECT_URL_ATTRIBUTE_NAME = "mvcRedirectUrl";

	public static final String MVC_CREATE = "/create";
	public static final String MVC_READ = "/read";
	public static final String MVC_UPDATE = "/update";
	public static final String MVC_DELETE = "/delete";
	public static final String MVC_DETAIL = "/detail";
	public static final String MVC_LIST = "/list";

	public static final String API = "api";
	public static final String API_CREATE = "create";
	public static final String API_READ = "read";
	public static final String API_UPDATE = "update";
	public static final String API_DELETE = "delete";
	public static final String API_DETAIL = "detail";
	public static final String API_LIST = "list";

	/************************************************************/
	/**
	 * get the MVC model from spring
	 * 
	 * @return the MVC model from spring
	 */
	public Model getModel();

	/**
	 * set the spring MVC model
	 * 
	 * @param model the spring MVC model
	 */
	public void setModel(Model model);

	/**
	 * Get the html title.
	 * 
	 * @return the html title
	 */
	public String getHtmlTitle();

	/**
	 * Get the page title that displays at the top center of a page
	 * 
	 * @return the page title that displays at the top center of a page
	 */
	public String getPageTitle();

	/**
	 * set the html title that displays at the top center of a page
	 * 
	 * @return the page title to display
	 */
	public String getPageTitleDescription();

	/**
	 * the api base Url. The url context for relative path uri calls
	 * 
	 * @return the api base Url
	 */
	public String getApiBaseUrl();

	/**
	 * the uri to get json data for the page
	 * 
	 * @return the uri that reads data for the page
	 */
	public String getUriRead();

	/**
	 * uri to read/create data using get and post requests
	 * 
	 * @return the create uri
	 */
	public String getUriCreate();

	/**
	 * uri to read/update data using get and post requests
	 * 
	 * @return update uri
	 */
	public String getUriUpdate();

	/**
	 * uri to read/delete data using get and post requests
	 * 
	 * @return the read ui
	 */
	public String getUriDelete();

	/**
	 * uri to read data for a single record
	 * 
	 * @return uri to read data for a single record
	 */
	public String getUriDetail();

	/**
	 * mvc url to navigate to after an operation is performed
	 * 
	 * @return the mvc redirect url where to navigate after a post action is
	 *         performed on the view
	 */
	public String getMvcRedirectUrl();

	/**
	 * the base url of the controller where the view end point resides
	 * 
	 * @return
	 */
	public String getMvcBaseUrl();

	/**
	 * the static html file path
	 * 
	 * @return the static html path
	 */
	public String getHtml();

	/**
	 * the static CSS file path for the page
	 * 
	 * @return the static CSS file path for the page
	 */
	public String getCss();

	/**
	 * the static JS file path for the page
	 * 
	 * @return the static JS file path for the page
	 */
	public String getJs();

}