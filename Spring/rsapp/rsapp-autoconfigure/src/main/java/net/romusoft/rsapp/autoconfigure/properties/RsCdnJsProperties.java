package net.romusoft.rsapp.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CDN properties for javaScript resources. The base property is rsapp.cdn.js To
 * configure JQUERY for example, use rsapp.cdn.js="location"/jquery.min.js The
 * default location is from the webjar module for jquery
 * 
 * @author Emmanuel Romulus
 *
 */
@ConfigurationProperties(prefix = "rsapp.cdn.js")
public class RsCdnJsProperties {
	/*
	 * Cdn for files
	 */
	/**
	 * the location of jquery. Default:
	 * https://kendo.cdn.telerik.com/2021.1.330/js/jquery.min.jsv
	 */
	private String jquery = "https://kendo.cdn.telerik.com/2021.1.330/js/jquery.min.js";
	/**
	 * location of bootstrap 3. Default:
	 * https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js
	 */
	private String bootstrap = "https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js";
	/**
	 * The location of jquery datatables. Default:
	 * https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js
	 */
	private String datatables = "https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js";
	/**
	 * the location of jquery. Default:
	 * https://kendo.cdn.telerik.com/2021.1.330/js/kendo.ui.core.min.js
	 */
	private String kendoui = "https://kendo.cdn.telerik.com/2021.1.330/js/kendo.ui.core.min.js";
	/**
	 * the location of rs ui core. Default:
	 * /META-INF/resources/webjars/rs-ui-core/1.0/js/rs-navigation.js
	 */
	private String rsuicore = "/META-INF/resources/webjars/rs-ui-core/1.0/js/rs-navigation.js";

	/**
	 * get the path or URL of jquery
	 * 
	 * @return the path or URL of jquery
	 */
	public String getJquery() {
		return jquery;
	}

	/**
	 * set the path or URL of jquery
	 * 
	 * @param jquery the path or URL of jquery to be set
	 */
	public void setJquery(String jquery) {
		this.jquery = jquery;
	}

	/**
	 * the path or URL of bootstrap js
	 * 
	 * @return the path or URL of bootstrap
	 */
	public String getBootstrap() {
		return bootstrap;
	}

	/**
	 * set the path or URL of bootstrap
	 * 
	 * @param bootstrap the path or URL of bootstrap to be set
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
	 * set the path or URL of jquery datatables
	 * 
	 * @param datatables the path or URL of jquery datatables to be set
	 */
	public void setDatatables(String datatables) {
		this.datatables = datatables;
	}

	/**
	 * get the path or URL of jquery kendo ui. Default is a cdn
	 * 
	 * @return the path or URL of jquery
	 */
	public String getKendoui() {
		return kendoui;
	}

	/**
	 * set the path or URL of kendoui
	 * 
	 * @param kendoui the path or URL of jquery kendo ui to set
	 */
	public void setKendoui(String kendoui) {
		this.kendoui = kendoui;
	}

	/**
	 * get the path or URL of RsuiCore. A series of css layout for an application
	 * 
	 * @return the path or URL of rsuicore
	 */
	public String getRsuicore() {
		return rsuicore;
	}

	/**
	 * set the path or URL of rsuicore
	 * 
	 * @param rsuicore the path or URL of rsuicore to be set
	 */
	public void setRsuicore(String rsuicore) {
		this.rsuicore = rsuicore;
	}

}
