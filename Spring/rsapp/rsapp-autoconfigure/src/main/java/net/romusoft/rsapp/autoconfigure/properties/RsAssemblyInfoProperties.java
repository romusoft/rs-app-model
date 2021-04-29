package net.romusoft.rsapp.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * assembly info properties
 * 
 * @author Emmanuel Romulus
 *
 */
@ConfigurationProperties(prefix = "rsapp.assemblyinfo")
public class RsAssemblyInfoProperties {
	/**
	 * deployment environment name of the app: staging, dev, prod, etc.
	 */
	private String environment;

	/**
	 * version deployed to the web server
	 */
	private String version;

	/**
	 * friendly or display name of the application
	 */
	private String applicationTitle;

	/**
	 * name of the application based on which the war file is created
	 */
	private String applicationName;

	/**
	 * the directory where the appconfig.properties file is located. the
	 * appconfig.properties file must be located there and accessible for the app to
	 * run
	 */
	private String propertyFileDirectory;
	/**
	 * the application file name deployed on the server
	 */
	private String applicationFilename;

	/**
	 * the application file title deployed on the server. This name is used to stop
	 * or un-deploy the app it is the name of the module seen on a weblogic server
	 * after an app is deployed
	 */
	private String applicationFileTitle;

	/**
	 * deployment environment name of the app: staging, dev, prod, etc.
	 * 
	 * @return the name of the environment where the application is deployed.
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * Set the name of the environment
	 * 
	 * @param environment the environment to be set
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * version deployed to the web server
	 * 
	 * @return the current version of the application
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * set the current version of the application
	 * 
	 * @param version the version to be set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * friendly or display name of the application
	 * 
	 * @return a friendly or display name of the application
	 */
	public String getApplicationTitle() {
		return applicationTitle;
	}

	/**
	 * 
	 * @param applicationTitle the application title to be set
	 */
	public void setApplicationTitle(String applicationTitle) {
		this.applicationTitle = applicationTitle;
	}

	/**
	 * name of the application based on which the war file is created
	 * 
	 * @return the name of the application based on which the war file is created
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * set the name of the application
	 * 
	 * @param applicationName the application name to be set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * the directory where the appconfig.properties file is located. the
	 * appconfig.properties file must be located there and accessible for the app to
	 * 
	 * @return the directory path of external properties files
	 */
	public String getPropertyFileDirectory() {
		return propertyFileDirectory;
	}

	/**
	 * set the directory path of external properties files
	 * 
	 * @param propertyFileDirectory the property file directory to be set
	 */
	public void setPropertyFileDirectory(String propertyFileDirectory) {
		this.propertyFileDirectory = propertyFileDirectory;
	}

	/**
	 * the application file name deployed on the server
	 * 
	 * @return the application file name deployed on the server
	 */
	public String getApplicationFilename() {
		return applicationFilename;
	}

	/**
	 * set the application file name deployed on the server
	 * 
	 * @param applicationFilename the application file name to be set
	 */
	public void setApplicationFilename(String applicationFilename) {
		this.applicationFilename = applicationFilename;
	}

	/**
	 * the application file title deployed on the server. This name is used to stop
	 * or un-deploy the app it is the name of the module seen on a weblogic server
	 * after an app is deployed
	 * 
	 * @return the application title
	 */
	public String getApplicationFileTitle() {
		return applicationFileTitle;
	}

	/**
	 * set the application title
	 * 
	 * @param applicationFileTitle the application file title to be set
	 */
	public void setApplicationFileTitle(String applicationFileTitle) {
		this.applicationFileTitle = applicationFileTitle;
	}

}
