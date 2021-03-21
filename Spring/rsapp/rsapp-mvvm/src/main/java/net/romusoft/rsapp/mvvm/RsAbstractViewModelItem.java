package net.romusoft.rsapp.mvvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import net.romusoft.rsapp.mvvm.model.RsViewModelItemOption;

/**
 * business object or item in a view model
 * 
 * @author romulus
 *
 */
public abstract class RsAbstractViewModelItem {
	private String id;
	private String text = "";
	private String header;
	private String description;

	private final RsViewModelItemOption options = new RsViewModelItemOption();
	private final HashMap<String, Object> metadata = new HashMap<String, Object>();

	/**
	 * 
	 */
	public RsAbstractViewModelItem() {
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 
	 */
	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public RsViewModelItemOption getOptions() {
		return options;
	}

	/**
	 * 
	 * @return
	 */
	public HashMap<String, Object> getMetadata() {
		return metadata;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addMetadataValue(String key, Object value) {

		this.metadata.put(key, value);
	}

	/**
	 * 
	 * @param <T>
	 * @param key
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T searchMetadataForSingle(String key, Class<T> clazz) throws Exception {
		if (this.metadata.containsKey(key) == false) {
			throw new Exception("Key: " + key + " does not exist in metadata.");
		}
		Object target = this.metadata.get(key);
		//
		// handle primitive types
		if (clazz.isPrimitive()) {
			String value = (String) target;
			return this.primitiveValue(value, clazz);
		}
		//
		// handle array
		if (target.getClass().isArray() || target.getClass() == ArrayList.class) {
			throw new Exception("Array not supported for this method: ");
		}

		//
		// return pojo
		ObjectMapper mapper = new ObjectMapper();
		T record = mapper.convertValue(target, clazz);
		return record;

	}

	/**
	 * 
	 * @param <T>
	 * @param key
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> searchMetadataForList(String key, Class<T> clazz) throws Exception {
		Object target = this.metadata.get(key);

		//
		// handle array
		if (target.getClass() != ArrayList.class) {
			throw new Exception("Target clazz must be an array for this method ");
		}

		List<T> datalist = null;

		ObjectMapper mapper = new ObjectMapper(); // Create a mapper configure it to not fail on unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.setDateFormat(new StdDateFormat());
		String jsonData = mapper.writeValueAsString(target);
		try {
			/*
			 * create a collectionliketype reference that will tell the mapper what class to
			 * use to deserialize those objects
			 */
			CollectionLikeType typeref = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz);
			JsonNode actualObj = mapper.readTree(jsonData);
			JsonParser jparser = actualObj.traverse();
			datalist = mapper.readValue(jparser, typeref);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//
		// get the data
		return datalist;

	}

	/**
	 * 
	 * @param <T>
	 * @param value
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T primitiveValue(String value, Class<T> clazz) {

		T retValue = null;
		Boolean booleanDefaultValue = false;
		Character CharacterDefaultValue = null;
		Byte ByteDefaultValue = null;
		Short ShortDefaultValue = 0;
		Integer IntegerDefaultValue = 0;
		Long LongDefaultValue = 0l;
		Float FloatDefaultValue = 0.0f;
		Double DoubleDefaultValue = 0d;

		switch (clazz.getSimpleName()) {
		case "boolean":
			if (value == null || value.isEmpty()) {
				value = "false";
			}
			booleanDefaultValue = Boolean.parseBoolean(value);
			retValue = (T) booleanDefaultValue;
			break;
		case "character":
			if (value == null || value.isEmpty()) {
				value = null;
			}
			CharacterDefaultValue = value.charAt(0);
			retValue = (T) CharacterDefaultValue;
			break;
		case "byte":
			if (value == null || value.isEmpty()) {
				value = "0";
			}
			ByteDefaultValue = Byte.parseByte(value);
			retValue = (T) ByteDefaultValue;
			break;
		case "short":
			if (value == null || value.isEmpty()) {
				value = "0";
			}
			ShortDefaultValue = Short.parseShort(value);
			retValue = (T) ShortDefaultValue;
			break;
		case "integer":
			if (value == null || value.isEmpty()) {
				value = "0";
			}
			IntegerDefaultValue = Integer.parseInt(value);
			retValue = (T) IntegerDefaultValue;
			break;
		case "long":
			if (value == null || value.isEmpty()) {
				value = "0l";
			}
			LongDefaultValue = Long.parseLong(value);
			retValue = (T) LongDefaultValue;
			break;
		case "float":
			if (value == null || value.isEmpty()) {
				value = "0.0f";
			}
			FloatDefaultValue = Float.parseFloat(value);
			retValue = (T) FloatDefaultValue;
			break;
		case "double":
			if (value == null || value.isEmpty()) {
				value = "0.0d";
			}
			DoubleDefaultValue = Double.parseDouble(value);
			retValue = (T) DoubleDefaultValue;
			break;
		}
		return retValue;
	}

}
