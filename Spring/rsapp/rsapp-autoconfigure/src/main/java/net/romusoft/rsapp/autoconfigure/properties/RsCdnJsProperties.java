package net.romusoft.rsapp.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsapp.cdn.js")
public class RsCdnJsProperties {
	/*
	 * Cdn for files
	 */
	private String jquery;
	private String bootstrap;
	private String datatables;
	private String kendoui;
	private String rsuicore;

	/**
	 * 
	 * @return
	 */
	public String getJquery() {
		return jquery;
	}

	public void setJquery(String jquery) {
		this.jquery = jquery;
	}

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

	public void setDatatables(String datatables) {
		this.datatables = datatables;
	}

	/**
	 * 
	 * @return
	 */
	public String getKendoui() {
		return kendoui;
	}

	public void setKendoui(String kendoui) {
		this.kendoui = kendoui;
	}

	/**
	 * 
	 * @return
	 */
	public String getRsuicore() {
		return rsuicore;
	}

	public void setRsuicore(String rsuicore) {
		this.rsuicore = rsuicore;
	}

}
