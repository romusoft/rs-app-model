package com.romusoft.rsapp.mvvm.model;

public class RsAppViewModel {
	public static final String SESSION_ATTRIBUTE_NAME = "cdnViewModel";

	/*
	 * Cdn for js files
	 */
	private String jqueryJs;
	private String bootstrapJs;
	private String datatablesJs;
	private String kendouiJs;
	private String wpuicoreJs;

	/*
	 * Cdn for css files
	 */
	private String bootstrapCss;
	private String datatablesCss;
	private String themeCss;
	private String wpuicoreCss;

	/**
	 * 
	 * @return
	 */
	public String getJqueryJs() {
		return jqueryJs;
	}

	public void setJqueryJs(String jqueryJs) {
		this.jqueryJs = jqueryJs;
	}

	/**
	 * 
	 * @return
	 */
	public String getBootstrapJs() {
		return bootstrapJs;
	}

	public void setBootstrapJs(String bootstrapJs) {
		this.bootstrapJs = bootstrapJs;
	}

	/**
	 * 
	 * @return
	 */
	public String getDatatablesJs() {
		return datatablesJs;
	}

	public void setDatatablesJs(String datatablesJs) {
		this.datatablesJs = datatablesJs;
	}

	/**
	 * 
	 * @return
	 */
	public String getKendouiJs() {
		return kendouiJs;
	}

	public void setKendouiJs(String kendouiJs) {
		this.kendouiJs = kendouiJs;
	}

	/**
	 * 
	 * @return
	 */
	public String getWpuicoreJs() {
		return wpuicoreJs;
	}

	public void setWpuicoreJs(String wpuicoreJs) {
		this.wpuicoreJs = wpuicoreJs;
	}

	/**
	 * 
	 * @return
	 */
	public String getBootstrapCss() {
		return bootstrapCss;
	}

	public void setBootstrapCss(String bootstrapCss) {
		this.bootstrapCss = bootstrapCss;
	}

	/**
	 * 
	 * @return
	 */
	public String getDatatablesCss() {
		return datatablesCss;
	}

	public void setDatatableCss(String datatablesCss) {
		this.datatablesCss = datatablesCss;
	}

	/**
	 * 
	 * @return
	 */
	public String getThemeCss() {
		return themeCss;
	}

	public void setThemeCss(String themeCss) {
		this.themeCss = themeCss;
	}

	/**
	 * 
	 * @return
	 */
	public String getWpuicoreCss() {
		return wpuicoreCss;
	}

	public void setWpuicoreCss(String wpuicoreCss) {
		this.wpuicoreCss = wpuicoreCss;
	}
}
