package net.romusoft.rsapp.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * cdn css property only
 * 
 * @author eromu_000
 *
 */
@ConfigurationProperties(prefix = "rsapp.cdn.css")
public class RsCdnCssProperties {

	/*
	 * Cdn for files
	 */
	
	/**
	 * location of bootstrap 3. Default: https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js
	 */
	private String bootstrap ="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js";
	/**
	 * The location of jquery datatables. Default: https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js
	 */
	private String datatables ="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.css";
	/**
	 * the location of rs ui core. Default: /META-INF/resources/webjars/rs-ui-core/1.0/js/rs-navigation.js
	 */
	private String rsuicore ="/META-INF/resources/webjars/rs-ui-core/1.0/styles/rs-all.css";
	/**
	 * the location of rs theme. Default: /META-INF/resources/webjars/rs-ui-core/1.0/theme/wp-theme-office/all.css
	 */
	private String theme ="/META-INF/resources/webjars/rs-ui-core/1.0/theme/wp-theme-office/all.css";

	/**
	 * 
	 * @return
	 */
	public String getBootstrap() {
		return bootstrap;
	}

	public void setBootstrap(String bootstrap) {
		this.bootstrap = bootstrap;
	}

	/**
	 * 
	 * @return
	 */
	public String getDatatables() {
		return datatables;
	}

	public void setDatatable(String datatables) {
		this.datatables = datatables;
	}

	/**
	 * 
	 * @return
	 */
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * 
	 * @return
	 */
	public String getWpuicore() {
		return rsuicore;
	}

	public void setWpuicore(String wpuicore) {
		this.rsuicore = wpuicore;
	}
}
