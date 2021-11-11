package net.romusoft.rsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * We want to skip properties here so they don't get saved in the database
 * 
 * @author Emmanuel Romulus
 *
 */
public abstract class RsEntityModelMixinBase {

	@JsonIgnore
	private String cssClass = "";

	@JsonIgnore
	private String description = "";

	/**
	 * the css class
	 * @return the css class
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * the css class
	 * @param cssClass the css class
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * the description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * the description
	 * @param description the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
