package net.romusoft.rsapp.enums;

/**
 * options when retrieving assembly information.
 * 
 * @author Emmanuel Romulus
 *
 */
public enum ViewType {
	/**
	 * jsp
	 */
	JSP(1, "Jsp", "Add meta data for jsp and jstl"),
	/**
	 * thymeleaf
	 */
	THYMELEAF(2, "Thymeleaf", "Add meta data for thymeleaf"), 
	/**
	 * default or html
	 */
	DEFAULT(3, "Html", "Add meta data for html");

	private final Integer id;
	private final String name;
	private final String description;

	/**
	 * constructor for the assembly info options
	 * 
	 * @param id          the id for the this object
	 * @param value       the value for this object
	 * @param description the description of the object
	 */
	ViewType(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	/**
	 * get the id of this enum object
	 * 
	 * @return the id of this enum object
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get the value of this enum object
	 * 
	 * @return the value of this enum object
	 */
	public String getName() {
		return name;
	}

	/**
	 * get the description of this enum object
	 * 
	 * @return the description of this enum object
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * return the enum object that matches the name
	 * 
	 * @param name the target enum name to find
	 * @return the found object or default
	 */
	public static final ViewType getValue(String name) {
		ViewType[] values = ViewType.values();
		for (ViewType value : values) {
			if (name.equals(value.getName()))
				return value;
		}
		return ViewType.DEFAULT;
	}

	/**
	 * return the enum object that matches the name
	 * 
	 * @param id the target enum id to find
	 * @return the found object or default
	 */
	public static final ViewType getValue(Integer id) {
		ViewType[] values = ViewType.values();
		for (ViewType value : values) {
			if (value.getId().equals(id))
				return value;
		}
		return ViewType.DEFAULT;
	}

}
