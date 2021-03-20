package com.rsapp.autoconfigure.properties;

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
	private String bootstrap;
	private String datatables;
	private String theme;
	private String wpuicore;

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
		return wpuicore;
	}

	public void setWpuicore(String wpuicore) {
		this.wpuicore = wpuicore;
	}
}
