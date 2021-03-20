package com.romusoft.rsapp.mvvm.model;

/**
 * 
 * @author eromu_000
 *
 */
public class RsViewModelItemOption {

	private String cssClass = "";
	private int sortOrder;
	private boolean visible = true;
	private boolean disabled;
	private boolean deletable;

	/**
	 * 
	 * @return
	 */
	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * 
	 * @return
	 */
	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisable(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

}
