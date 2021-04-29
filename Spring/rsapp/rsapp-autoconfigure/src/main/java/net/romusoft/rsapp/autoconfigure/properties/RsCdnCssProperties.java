package net.romusoft.rsapp.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * cdn css property only
 * 
 * @author Emmanuel Romulus
 *
 */
@ConfigurationProperties(prefix = "rsapp.cdn.css")
public class RsCdnCssProperties {

	/*
	 * Cdn for files
	 */

	/**
	 * location of bootstrap 3. Default:
	 * https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js
	 */
	private String bootstrap = "https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js";
	/**
	 * The location of jquery datatables. Default:
	 * https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js
	 */
	private String datatables = "https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.css";
	/**
	 * the location of rs ui core. Default:
	 * /META-INF/resources/webjars/rs-ui-core/1.0/js/rs-navigation.js
	 */
	private String rsuicore = "/META-INF/resources/webjars/rs-ui-core/1.0/styles/rs-all.css";
	/**
	 * the location of rs theme. Default:
	 * /META-INF/resources/webjars/rs-ui-core/1.0/theme/wp-theme-office/all.css
	 */
	private String theme = "/META-INF/resources/webjars/rs-ui-core/1.0/theme/rs-theme-office/all.css";

	/**
	 * The path or url of CSS bootstrap
	 * 
	 * @return the path or url of CSS bootstrap
	 */
	public String getBootstrap() {
		return bootstrap;
	}

	/**
	 * set the path or URL of CSS bootstrap
	 * 
	 * @param bootstrap path or URL to be set
	 */
	public void setBootstrap(String bootstrap) {
		this.bootstrap = bootstrap;
	}

	/**
	 * the path or URL of jquery datatables
	 * 
	 * @return the path or URL of jquery datatables
	 */
	public String getDatatables() {
		return datatables;
	}

	/**
	 * the path or URL of jquery datatables
	 * 
	 * @param datatables the path or URL of the datatables to be set
	 */
	public void setDatatable(String datatables) {
		this.datatables = datatables;
	}

	/**
	 * the path or URL of application theme
	 * 
	 * @return the path or URL of application theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * set the path or URL of application theme
	 * 
	 * @param theme the path or URL of the theme to be set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * the path or URL of rsuicore. A simple css library for basic ui layout using
	 * CSS flex
	 * 
	 * @return the path or URL of rsuicore
	 */
	public String getRsuicore() {
		return rsuicore;
	}

	/**
	 * set the path or URL of rsuicore.
	 * 
	 * @param rsuicore the path or URL of the rsuicore to be set
	 */
	public void setRsuicore(String rsuicore) {
		this.rsuicore = rsuicore;
	}
}
