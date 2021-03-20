package com.romusoft.rsapp.mvvm.model;

import com.romusoft.rsapp.mvvm.RsAbstractViewModelItem;

/**
 * action object description: buttons, links, etc..
 * 
 * @author romulus
 *
 */
public class RsViewModelAction extends RsAbstractViewModelItem {
	private String name = "";
	private String url = "";
	private String message = "";


	/**
	 * 
	 * @param text
	 */
	public RsViewModelAction(String text) {
		super();
		this.setText(text);
	}

	/**
	 * 
	 * @param text
	 * @param url
	 */
	public RsViewModelAction(String text, String url) {
		super();
		this.setText(text);
		this.url = url;
	}

	/**
	 * 
	 * @param text
	 * @param url
	 * @param description
	 * @param visible
	 */
	public RsViewModelAction(String text, String url, boolean visible) {
		super();
		this.setText(text);
		this.url = url;
		this.getOptions().setVisible(visible);
	}

	/**
	 * 
	 * @param text
	 * @param url
	 * @param description
	 */
	public RsViewModelAction(String text, String url, String description) {
		super();
		this.setText(text);
		this.url = url;
		this.setDescription(description);
	}

	/**
	 * 
	 * @param text
	 * @param url
	 * @param description
	 * @param visible
	 */
	public RsViewModelAction(String text, String url, String description, boolean visible) {
		super();
		this.setText(text);
		this.url = url;
		this.setDescription(description);
		this.getOptions().setVisible(visible);
	}

	/**
	 * 
	 * @param text
	 * @param visible
	 */
	public RsViewModelAction(String text, boolean visible) {
		super();
		this.setText(text);
		this.getOptions().setVisible(visible);
	}

	/**
	 * 
	 * @param text
	 * @param visible
	 * @param enabled
	 */
	public RsViewModelAction(String text, boolean visible, boolean disabled) {
		super();
		this.setText(text);
		this.getOptions().setVisible(visible);
		this.getOptions().setDisable(disabled);
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
