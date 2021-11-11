package net.romusoft.rsapp.mvvm;

import java.util.HashMap;

import org.springframework.ui.Model;

/**
 * the view definition
 * 
 * @author Emmanuel Romulus
 *
 */
public interface IRsView {
	/**
	 * attribute name for htmlTitle
	 */
	public static final String HTML_TITLE_ATTRIBUTE_NAME = "htmlTitle";
	/**
	 * attribute name for pageTitle
	 */
	public static final String PAGE_TITLE_ATTRIBUTE_NAME = "pageTitle";
	/**
	 * attribute name for pageTitleDescription
	 */
	public static final String PAGE_TITLE_DESCRIPTION_ATTRIBUTE_NAME = "pageTitleDescription";

	/**
	 * attribute name for apiBaseUrl
	 */
	public static final String API_BASE_URL_ATTRIBUTE_NAME = "apiBaseUrl";

	/**
	 * attribute name for html
	 */
	public static final String HTML_PATH_ATTRIBUTE_NAME = "html";
	/**
	 * attribute name for css
	 */
	public static final String CSS_PATH_ATTRIBUTE_NAME = "css";
	/**
	 * attribute name for js
	 */
	public static final String JS_PATH_ATTRIBUTE_NAME = "js";

	/**
	 * attribute name for mvcBaseUrl
	 */
	public static final String MVC_BASE_URL_ATTRIBUTE_NAME = "mvcBaseUrl";

	/**
	 * attribute name for startDate
	 */
	public static final String START_DATE_ATTRIBUTE_NAME = "startDate";
	/**
	 * attribute name for endDate
	 */
	public static final String END_DATE_ATTRIBUTE_NAME = "endDate";

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
	public RsViewModelUri getUriRead();

	/**
	 * uri to read/create data using get and post requests
	 * 
	 * @return the create uri
	 */
	public RsViewModelUri getUriCreate();

	/**
	 * uri to read/update data using get and post requests
	 * 
	 * @return update uri
	 */
	public RsViewModelUri getUriUpdate();

	/**
	 * uri to read/delete data using get and post requests
	 * 
	 * @return the read ui
	 */
	public RsViewModelUri getUriDelete();

	/**
	 * uri to read data for a single record
	 * 
	 * @return uri to read data for a single record
	 */
	public RsViewModelUri getUriDetail();

	/**
	 * the base url of the controller where the view end point resides
	 * 
	 * @return mvc base url
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

	/**
	 * any uri need for the view. A view may have many navigation
	 * 
	 * @return hashmap of uris
	 */
	public HashMap<String, RsViewModelUri> getUris();

	/**
	 * the path to the html file
	 * 
	 * @param html the path to the html file
	 */
	public void setHtml(String html);

	/**
	 * the path to the css file
	 * 
	 * @param css the path to the css file
	 */
	public void setCss(String css);

	/**
	 * the path to the js file
	 * 
	 * @param js the path to the js file
	 */
	public void setJs(String js);

	/**
	 * the html title
	 * 
	 * @param htmlTitle the html document title
	 */
	public void setHtmlTitle(String htmlTitle);

	/**
	 * the page title
	 * 
	 * @param pageTitle the page title
	 */
	public void setPageTitle(String pageTitle);

	/**
	 * description for the page title
	 * 
	 * @param pageTitleDescription description for the page title
	 */
	public void setPageTitleDescription(String pageTitleDescription);

	/**
	 * the mvc base url
	 * 
	 * @param mvcBaseUrl the mvc base url
	 */
	public void setMvcBaseUrl(String mvcBaseUrl);

	/**
	 * the api base url
	 * 
	 * @param apiBaseUrl the api context base url
	 */
	public void setApiBaseUrl(String apiBaseUrl);

	/**
	 * the read uri for the api where the view gets data
	 * 
	 * @param uriRead the read uri
	 */
	public void setUriRead(RsViewModelUri uriRead);

	/**
	 * the create uri to persist data
	 * 
	 * @param uriCreate create uri
	 */
	public void setUriCreate(RsViewModelUri uriCreate);

	/**
	 * the update uri
	 * 
	 * @param uriUpdate update uri
	 */
	public void setUriUpdate(RsViewModelUri uriUpdate);

	/**
	 * the delete uri
	 * 
	 * @param uriDelete delete uri
	 */
	public void setUriDelete(RsViewModelUri uriDelete);

	/**
	 * the detail uri
	 * 
	 * @param uriDetail detail uri
	 */
	public void setUriDetail(RsViewModelUri uriDetail);

	/**
	 * initialize default values in the view such uri values
	 */
	public void init();

	/**
	 * update the model attributes
	 */
	public void updateModelAttributes();

	/**
	 * add any rest uri to the view since the view may navigate to various places
	 * 
	 * @param name the name of the uri
	 * @param uri  the uri object
	 * @return return the instance of the uri for chaining
	 */
	public RsViewModelUri addUri(String name, RsViewModelUri uri);

	/**
	 * retrieve an existing uri from the view
	 * 
	 * @param name the name of the uri to find
	 * @return the existing instance or null
	 */
	public RsViewModelUri findUri(String name);
}