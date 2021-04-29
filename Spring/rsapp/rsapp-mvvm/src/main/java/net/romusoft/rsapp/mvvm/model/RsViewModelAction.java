package net.romusoft.rsapp.mvvm.model;

import net.romusoft.rsapp.mvvm.RsAbstractViewModelItem;

/**
 * action object description: buttons, links, etc..
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsViewModelAction extends RsAbstractViewModelItem {
	private String name = "";
	private String url = "";
	private String message = "";

	/**
	 * Initialize an action
	 * 
	 * @param text the action text - may be a button or link text
	 */
	public RsViewModelAction(String text) {
		super();
		this.setText(text);
	}

	/**
	 * Initialize an action
	 * 
	 * @param text the action text - may be a button or link text
	 * @param url  Url for the action to navigate to
	 */
	public RsViewModelAction(String text, String url) {
		super();
		this.setText(text);
		this.url = url;
	}

	/**
	 * Initialize an action
	 * 
	 * @param text    the action text - may be a button or link text
	 * @param url     Url for the action to navigate to
	 * @param visible whether the element for the action should be visible
	 */
	public RsViewModelAction(String text, String url, boolean visible) {
		super();
		this.setText(text);
		this.url = url;
		this.getOptions().setVisible(visible);
	}

	/**
	 * Initialize an action
	 * 
	 * @param text        the action text - may be a button or link text
	 * @param url         Url for the action to navigate to
	 * @param description description or tooltip for the action
	 */
	public RsViewModelAction(String text, String url, String description) {
		super();
		this.setText(text);
		this.url = url;
		this.setDescription(description);
	}

	/**
	 * Initialize an action
	 * 
	 * @param text        the action text - may be a button or link text
	 * @param url         Url for the action to navigate to *
	 * @param description description or tooltip for the action
	 * @param visible     whether the element for the action should be visible
	 */
	public RsViewModelAction(String text, String url, String description, boolean visible) {
		super();
		this.setText(text);
		this.url = url;
		this.setDescription(description);
		this.getOptions().setVisible(visible);
	}

	/**
	 * Initialize an action
	 * 
	 * @param text    the action text - may be a button or link text
	 * @param visible whether the element for the action should be visible
	 */
	public RsViewModelAction(String text, boolean visible) {
		super();
		this.setText(text);
		this.getOptions().setVisible(visible);
	}

	/**
	 * Initialize an action
	 * 
	 * @param text     the action text - may be a button or link text
	 * @param visible  whether the element for the action should be visible
	 * @param disabled whether the element for the action should be enabled
	 */
	public RsViewModelAction(String text, boolean visible, boolean disabled) {
		super();
		this.setText(text);
		this.getOptions().setVisible(visible);
		this.getOptions().setDisable(disabled);
	}

	/**
	 * Name of the action
	 * 
	 * @return Name of the action
	 */
	public String getName() {
		return name;
	}

	/**
	 * Name of the action
	 * 
	 * @param name the Name of the action
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Url for the action to navigate to
	 * 
	 * @return Url for the action to navigate to
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Url for the action to navigate to
	 * 
	 * @param url Url for the action to navigate to
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Message confirmation for an action
	 * 
	 * @return Message confirmation for an action
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Message confirmation for an action
	 * 
	 * @param message Message confirmation for an action
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
