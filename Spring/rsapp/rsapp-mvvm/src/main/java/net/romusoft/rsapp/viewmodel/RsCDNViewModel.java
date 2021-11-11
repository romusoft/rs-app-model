package net.romusoft.rsapp.viewmodel;

/**
 * A basic class definition for the cdn viewModel. This can be added to a
 * session attribute to control how the application loads cdn static files
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsCDNViewModel {
	/**
	 * session attribute: rsCdnViewModel
	 */
	public static final String SESSION_ATTRIBUTE_NAME = "rsCdnViewModel";

	/*
	 * Cdn for js files
	 */
	private String jqueryJs;
	private String bootstrapJs;
	private String datatablesJs;
	private String kendouiJs;
	private String rsuicoreJs;

	/*
	 * Cdn for css files
	 */
	private String bootstrapCss;
	private String datatablesCss;
	private String themeCss;
	private String rsuicoreCss;

	/**
	 * path or URL of jquery
	 * 
	 * @return path or URL of jquery
	 */
	public String getJqueryJs() {
		return jqueryJs;
	}

	/**
	 * path or URL of jquery
	 * 
	 * @param jqueryJs path or URL of jquery
	 */
	public void setJqueryJs(String jqueryJs) {
		this.jqueryJs = jqueryJs;
	}

	/**
	 * path or URL of bootstrap js
	 * 
	 * @return path or URL of bootstrapjs
	 */
	public String getBootstrapJs() {
		return bootstrapJs;
	}

	/**
	 * path or URL of bootstrap js
	 * 
	 * @param bootstrapJs path or URL of bootstrapjs
	 */
	public void setBootstrapJs(String bootstrapJs) {
		this.bootstrapJs = bootstrapJs;
	}

	/**
	 * path or URL of datatablesJs
	 * 
	 * @return path or URL of datatablesJs
	 */
	public String getDatatablesJs() {
		return datatablesJs;
	}

	/**
	 * path or URL of datatablesJs
	 * 
	 * @param datatablesJs path or URL of datatablesJs
	 */
	public void setDatatablesJs(String datatablesJs) {
		this.datatablesJs = datatablesJs;
	}

	/**
	 * path or URL of kendouiJs
	 * 
	 * @return path or URL of kendouiJs
	 */
	public String getKendouiJs() {
		return kendouiJs;
	}

	/**
	 * path or URL of kendouiJs
	 * 
	 * @param kendouiJs path or URL of kendouiJs
	 */
	public void setKendouiJs(String kendouiJs) {
		this.kendouiJs = kendouiJs;
	}

	/**
	 * path or URL of rsuicoreJs
	 * 
	 * @return path or URL of rsuicoreJs
	 */
	public String getRsuicoreJs() {
		return rsuicoreJs;
	}

	/**
	 * path or URL of rsuicoreJs
	 * 
	 * @param rsuicoreJs path or URL of rsuicoreJs
	 */
	public void setRsuicoreJs(String rsuicoreJs) {
		this.rsuicoreJs = rsuicoreJs;
	}

	/****************************************************************************/

	/**
	 * path or URL of bootstrapCss
	 * 
	 * @return path or URL of bootstrapCss
	 */
	public String getBootstrapCss() {
		return bootstrapCss;
	}

	/**
	 * path or URL of bootstrapCss
	 * 
	 * @param bootstrapCss path or URL of bootstrapCss
	 */
	public void setBootstrapCss(String bootstrapCss) {
		this.bootstrapCss = bootstrapCss;
	}

	/**
	 * path or URL of datatablesCss
	 * 
	 * @return path or URL of datatablesCss
	 */
	public String getDatatablesCss() {
		return datatablesCss;
	}

	/**
	 * path or URL of datatablesCss
	 * 
	 * @param datatablesCss path or URL of datatablesCss
	 */
	public void setDatatableCss(String datatablesCss) {
		this.datatablesCss = datatablesCss;
	}

	/**
	 * path or URL of themeCss
	 * 
	 * @return path or URL of themeCss
	 */
	public String getThemeCss() {
		return themeCss;
	}

	/**
	 * path or URL of themeCss
	 * 
	 * @param themeCss path or URL of themeCss
	 */
	public void setThemeCss(String themeCss) {
		this.themeCss = themeCss;
	}

	/**
	 * path or URL of rsuicoreCss
	 * 
	 * @return path or URL of rsuicoreCss
	 */
	public String getRsuicoreCss() {
		return rsuicoreCss;
	}

	/**
	 * path or URL of rsuicoreCss
	 * 
	 * @param rsuicoreCss path or URL of rsuicoreCss
	 */
	public void setRsuicoreCss(String rsuicoreCss) {
		this.rsuicoreCss = rsuicoreCss;
	}
}
