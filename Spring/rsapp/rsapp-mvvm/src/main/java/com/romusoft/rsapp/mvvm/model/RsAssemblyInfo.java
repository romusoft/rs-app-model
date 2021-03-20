package com.romusoft.rsapp.mvvm.model;

public class RsAssemblyInfo {

	public static final String RS_ASSEMBLY_INFO_SESSION_ATTRIBUTE_NAME = "rsAssemblyInfo";
	/*
	 * deployment environment name of the app: staging, dev, prod, etc.
	 */
	private String environment;

	/*
	 * version deployed to the web server
	 */
	private String version;

	/*
	 * friendly or display name of the application
	 */
	private String applicationTitle;

	/*
	 * name of the application based on which the war file is created
	 */
	private String applicationName;

	/*
	 * the directory where the appconfig.properties file is located. the
	 * appconfig.properties file must be located there and accessible for the app to
	 * run
	 */
	private String propertyFileDirectory;
	/*
	 * the application file name deployed on the server
	 */
	private String applicationFilename;

	/*
	 * the application file title deployed on the server. This name is used to stop
	 * or un-deploy the app it is the name of the module seen on a weblogic server
	 * after an app is deployed
	 */
	private String applicationFileTitle;

	private String applicationVersion = "";
	private String applicationEnvironment = "";

	/**
	 * deployment environment name of the app: staging, dev, prod, etc.
	 * 
	 * @return
	 */
	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * version deployed to the web server
	 * 
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * friendly or display name of the application
	 * 
	 * @return
	 */
	public String getApplicationTitle() {
		return applicationTitle;
	}

	public void setApplicationTitle(String applicationTitle) {
		this.applicationTitle = applicationTitle;
	}

	/**
	 * name of the application based on which the war file is created
	 * 
	 * @return
	 */
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * the directory where the appconfig.properties file is located. the
	 * appconfig.properties file must be located there and accessible for the app to
	 * 
	 * @return
	 */
	public String getPropertyFileDirectory() {
		return propertyFileDirectory;
	}

	public void setPropertyFileDirectory(String propertyFileDirectory) {
		this.propertyFileDirectory = propertyFileDirectory;
	}

	/**
	 * the application file name deployed on the server
	 * 
	 * @return
	 */
	public String getApplicationFilename() {
		return applicationFilename;
	}

	public void setApplicationFilename(String applicationFilename) {
		this.applicationFilename = applicationFilename;
	}

	/**
	 * the application file title deployed on the server. This name is used to stop
	 * or un-deploy the app it is the name of the module seen on a weblogic server
	 * after an app is deployed
	 * 
	 * @return
	 */
	public String getApplicationFileTitle() {
		return applicationFileTitle;
	}

	public void setApplicationFileTitle(String applicationFileTitle) {
		this.applicationFileTitle = applicationFileTitle;
	}

	/**
	 * 
	 * @return
	 */
	public String getApplicationVersion() {
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	/**
	 * 
	 * @return
	 */
	public String getApplicationEnvironment() {
		return applicationEnvironment;
	}

	public void setApplicationEnvironment(String applicationEnvironment) {
		this.applicationEnvironment = applicationEnvironment;
	}

	/**
	 * 
	 */
	public void initialize() {
		environment = environment.toUpperCase();
		switch (environment) {
		case "DEV":
			applicationVersion = applicationTitle + " Version: " + version + "-dev";
			applicationEnvironment = "DEVELOPMENT";
			break;
		case "PROD":
			applicationVersion = applicationTitle + " Version: " + version + "-prod";
			applicationEnvironment = "PRODUCTION";
			break;
		case "STAGING":
			applicationVersion = applicationTitle + " Version: " + version + "-staging";
			applicationEnvironment = "STAGING";
			break;
		case "QA":
			applicationVersion = applicationTitle + " Version: " + version + "-qa";
			applicationEnvironment = "QA";
			break;
		default:
			applicationVersion = applicationTitle + " Version: " + version + "-dev";
			applicationEnvironment = environment.toUpperCase();
			break;
		}
	}
}
