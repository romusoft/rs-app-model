package net.romusoft.rsapp.mvvm.model;

/**
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsViewModelItemOption {

	private String cssClass = "";
	private int sortOrder;
	private boolean visible = true;
	private boolean disabled;
	private boolean deletable;

	/**
	 * CSS class
	 * 
	 * @return CSS class
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * CSS class
	 * 
	 * @param cssClass CSS class
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * sort index
	 * 
	 * @return sort index
	 */
	public int getSortOrder() {
		return sortOrder;
	}

	/**
	 * sort index
	 * 
	 * @param sortOrder sort index
	 */
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * Whether an element is visible
	 * 
	 * @return Whether an element is visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Whether an element is visible
	 * 
	 * @param visible Whether an element is visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Whether an element is disabled
	 * 
	 * @return Whether an element is disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * Whether an element is visible
	 * 
	 * @param disabled Whether an element is visible
	 */
	public void setDisable(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * Whether a delete action can be performed
	 * 
	 * @return Whether a delete action can be performed
	 */
	public boolean isDeletable() {
		return deletable;
	}

	/**
	 * Whether a delete action can be performed
	 * 
	 * @param deletable Whether a delete action can be performed
	 */
	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

}
