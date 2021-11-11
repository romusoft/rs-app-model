package net.romusoft.rsapp.mvvm;

import java.util.UUID;

import net.romusoft.rsapp.model.RsModelBase;

/**
 * business object or item in a view model
 * 
 * @author Emmanuel Romulus
 *
 */
public abstract class RsViewModelItemBase extends RsModelBase {
	private String id;
	private String text = "";
	private String header;
	private String description;

	private final RsViewModelItemOption options = new RsViewModelItemOption();

	/**
	 * default constructor
	 */
	public RsViewModelItemBase() {
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * unique id for this item
	 * 
	 * @return unique id for this item
	 */
	public String getId() {
		return id;
	}

	/**
	 * unique id for this item
	 * 
	 * @param id unique id for this item
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * text for this item
	 * 
	 * @return text for this item
	 */
	public String getText() {
		return text;
	}

	/**
	 * text for this item
	 * 
	 * @param text text for this item
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * header for this item
	 * 
	 * @return header for this item
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * header for this item
	 * 
	 * @param header header for this item
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * description for this item
	 * 
	 * @return description for this item
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * description for this item
	 * 
	 * @param description description for this item
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * RsViewModelItemOption for this item
	 * 
	 * @return RsViewModelItemOption for this item
	 */
	public RsViewModelItemOption getOptions() {
		return options;
	}

}
