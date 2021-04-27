package net.romusoft.rsapp.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * CDN properties for javaScript resources. The base property is rsapp.cdn.js To
 * configure JQUERY for example, use rsapp.cdn.js="location"/jquery.min.js The
 * default location is from the webjar module for jquery
 * 
 * @author eromu_000
 *
 */
@ConfigurationProperties(prefix = "rsapp.cdn.js")
public class RsCdnJsProperties {
	/*
	 * Cdn for files
	 */
	/**
	 * the location of jquery. Default: https://kendo.cdn.telerik.com/2021.1.330/js/jquery.min.jsv
	 */
	private String jquery = "https://kendo.cdn.telerik.com/2021.1.330/js/jquery.min.js";
	/**
	 * location of bootstrap 3. Default: https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js
	 */
	private String bootstrap ="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js";
	/**
	 * The location of jquery datatables. Default: https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js
	 */
	private String datatables ="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js";
	/**
	 * the location of jquery. Default: https://kendo.cdn.telerik.com/2021.1.330/js/kendo.ui.core.min.js
	 */
	private String kendoui = "https://kendo.cdn.telerik.com/2021.1.330/js/kendo.ui.core.min.js";
	/**
	 * the location of rs ui core. Default: /META-INF/resources/webjars/rs-ui-core/1.0/js/rs-navigation.js
	 */
	private String rsuicore ="/META-INF/resources/webjars/rs-ui-core/1.0/js/rs-navigation.js";

	/**
	 * get jquery value
	 * 
	 * @return
	 */
	public String getJquery() {
		return jquery;
	}

	/**
	 * set jquery value
	 * 
	 * @param jquery
	 */
	public void setJquery(String jquery) {
		this.jquery = jquery;
	}

	/**
	 * get bootstrap value
	 * 
	 * @return
	 */
	public String getBootstrap() {
		return bootstrap;
	}

	/**
	 * set bootstrap value
	 * 
	 * @param bootstrap
	 */
	public void setBootstrap(String bootstrap) {
		this.bootstrap = bootstrap;
	}

	/**
	 * Get jquery datatables
	 * 
	 * @return
	 */
	public String getDatatables() {
		return datatables;
	}

	/**
	 * set jquery datatables
	 * 
	 * @param datatables
	 */
	public void setDatatables(String datatables) {
		this.datatables = datatables;
	}

	/**
	 * get kendo ui value the default is from the web jar module
	 * 
	 * @return
	 */
	public String getKendoui() {
		return kendoui;
	}

	/**
	 * set jquery datatables
	 * 
	 * @param kendoui
	 */
	public void setKendoui(String kendoui) {
		this.kendoui = kendoui;
	}

	/**
	 * get RsuiCore value. A series of css layout and theme for an application
	 * 
	 * @return
	 */
	public String getRsuicore() {
		return rsuicore;
	}

	/**
	 * set the rsuicore value
	 * 
	 * @param rsuicore
	 */
	public void setRsuicore(String rsuicore) {
		this.rsuicore = rsuicore;
	}

}
