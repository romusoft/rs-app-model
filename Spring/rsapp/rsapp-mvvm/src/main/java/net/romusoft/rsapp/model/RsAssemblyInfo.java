package net.romusoft.rsapp.model;

/**
 * Holds information about an application based on where it is deployed
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsAssemblyInfo {

	/**
	 * rsAssemblyInfo
	 */
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
	 * @return the application environment
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * the applicationn environment
	 * 
	 * @param environment application environment
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * version deployed to the web server
	 * 
	 * @return the application version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * application version
	 * 
	 * @param version the application version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * friendly or display name of the application
	 * 
	 * @return the application title
	 */
	public String getApplicationTitle() {
		return applicationTitle;
	}

	/**
	 * application title
	 * 
	 * @param applicationTitle the application title
	 */
	public void setApplicationTitle(String applicationTitle) {
		this.applicationTitle = applicationTitle;
	}

	/**
	 * name of the application based on which the war file is created
	 * 
	 * @return the application name
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * the application name
	 * 
	 * @param applicationName the application name
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * the directory where the appconfig.properties file is located. the
	 * appconfig.properties file must be located there and accessible for the app to
	 * 
	 * @return the external properties file directory
	 */
	public String getPropertyFileDirectory() {
		return propertyFileDirectory;
	}

	/**
	 * 
	 * @param propertyFileDirectory application external file directory
	 */
	public void setPropertyFileDirectory(String propertyFileDirectory) {
		this.propertyFileDirectory = propertyFileDirectory;
	}

	/**
	 * the application file name deployed on the server
	 * 
	 * @return application file name
	 */
	public String getApplicationFilename() {
		return applicationFilename;
	}

	/**
	 * application file name
	 * 
	 * @param applicationFilename the application file name
	 */
	public void setApplicationFilename(String applicationFilename) {
		this.applicationFilename = applicationFilename;
	}

	/**
	 * the application file title deployed on the server. This name is used to stop
	 * or un-deploy the app it is the name of the module seen on a weblogic server
	 * after an app is deployed
	 * 
	 * @return the application file title
	 */
	public String getApplicationFileTitle() {
		return applicationFileTitle;
	}

	/**
	 * set application file title
	 * 
	 * @param applicationFileTitle application file title
	 */
	public void setApplicationFileTitle(String applicationFileTitle) {
		this.applicationFileTitle = applicationFileTitle;
	}

	/**
	 * application version
	 * 
	 * @return application version
	 */
	public String getApplicationVersion() {
		return applicationVersion;
	}

	/**
	 * set application version
	 * 
	 * @param applicationVersion application version
	 */
	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	/**
	 * application environment
	 * 
	 * @return application environment
	 */
	public String getApplicationEnvironment() {
		return applicationEnvironment;
	}

	/**
	 * application environment
	 * 
	 * @param applicationEnvironment the application environment
	 */
	public void setApplicationEnvironment(String applicationEnvironment) {
		this.applicationEnvironment = applicationEnvironment;
	}

	/**
	 * intialize the assembly info values from properties
	 */
	public void initialize() {
		environment = environment.toUpperCase();
		switch (environment) {
		case "DEVEVELOPMENT":
		case "DEV":
			applicationVersion = applicationTitle + " Version: " + version + "-dev";
			applicationEnvironment = "DEVELOPMENT";
			break;
		case "PRODUCTION":
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
