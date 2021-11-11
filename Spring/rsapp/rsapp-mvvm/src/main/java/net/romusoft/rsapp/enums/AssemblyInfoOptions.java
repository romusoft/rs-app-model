package net.romusoft.rsapp.enums;

/**
 * options when retrieving assembly information.
 * 
 * @author Emmanuel Romulus
 *
 */
public enum AssemblyInfoOptions {

	/**
	 * everything
	 */
	ALL(1, "ALL", "get all values or the full assembly info object", "assemblyInfo"),
	/**
	 * version
	 */
	VERSION(2, "VERSION", "get the version value", "version"),
	/**
	 * environment
	 */
	ENVIRONMENT(3, "ENVIRONMENT", "get the environment value", "environment"),
	/**
	 * default
	 */
	DEFAULT(4, "DEFAULT", "Default", "default");

	private final Integer id;
	private final String name;
	private final String description;
	private final String metadataKey;

	/**
	 * constructor for the assembly info options
	 * 
	 * @param id          the id for the this object
	 * @param value       the value for this object
	 * @param description the description of the object
	 * @param metadataKey the metadatakey to use for metadata values
	 */
	AssemblyInfoOptions(Integer id, String name, String description, String metadataKey) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.metadataKey = metadataKey;
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
	 * get the metadatakey for this enum object. When the value is to be set in a
	 * metadata, use this key
	 * 
	 * @return the metadata key for this enum object.
	 */
	public String getMetadataKey() {
		return metadataKey;
	}

	/**
	 * return the enum object that matches the name
	 * 
	 * @param name the target enum name to find
	 * @return the found object or default
	 */
	public static final AssemblyInfoOptions getValue(String name) {
		AssemblyInfoOptions[] values = AssemblyInfoOptions.values();
		for (AssemblyInfoOptions value : values) {
			if (name.equals(value.getName()))
				return value;
		}
		return AssemblyInfoOptions.DEFAULT;
	}

	/**
	 * return the enum object that matches the name
	 * 
	 * @param id the target enum id to find
	 * @return the found object or default
	 */
	public static final AssemblyInfoOptions getValue(Integer id) {
		AssemblyInfoOptions[] values = AssemblyInfoOptions.values();
		for (AssemblyInfoOptions value : values) {
			if (value.getId().equals(id))
				return value;
		}
		return AssemblyInfoOptions.DEFAULT;
	}

}
