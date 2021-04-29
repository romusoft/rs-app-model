package net.romusoft.rsapp.mvvm;

import org.springframework.ui.Model;

/**
 * 
 * @author Emmanuel Romulus
 *
 */
public interface IRsView {

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
	 * a navigation url to navigate to after an operation is performed
	 * 
	 * @return the navigation url
	 */
	public String getNavigationUrl();

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