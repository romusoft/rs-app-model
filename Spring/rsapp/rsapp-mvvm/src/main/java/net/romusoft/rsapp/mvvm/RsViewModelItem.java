package net.romusoft.rsapp.mvvm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

/**
 * business object or item in a view model
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsViewModelItem extends RsViewModelItemBase {

	private String emailAddress;
	private String comment;

	/**
	 * default constructor
	 */
	public RsViewModelItem() {
		super();
	}

	/**
	 * Initialize with email address
	 * 
	 * @param emailAddress email address for the object
	 */
	public RsViewModelItem(String emailAddress) {
		super();
		this.emailAddress = emailAddress;
	}

	/**
	 * Initialize with object id and email address
	 * 
	 * @param id           object id
	 * @param emailAddress email address for the object
	 */
	public RsViewModelItem(String id, String emailAddress) {
		super();
		this.emailAddress = emailAddress;
	}

	/**
	 * emailAddress for the object
	 * @return emailAddress for the object
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * emailAddress for the object
	 * 
	 * @param emailAddress emailAddress for the object
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * comment for the object
	 * 
	 * @return comment for the object
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * comment for the object
	 * 
	 * @param comment comment for the object
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Mapped json objects to pojos using the target RsViewModelItem
	 * 
	 * @param jsonData json string to convert
	 * @author romulus
	 * @return an RsViewModelItem
	 */
	public static RsViewModelItem convertJsonToPOJO(String jsonData) {
		List<RsViewModelItem> items = RsViewModelItem.convertJsonToPOJOs(jsonData);
		if (items != null && items.size() > 0)
			return items.get(0);
		return null;

	}

	/**
	 * Mapped json objects to pojos using the target RsViewModelItems
	 * 
	 * @param jsonData json string to convert
	 * @author romulus
	 * @return list of RsViewModelItem
	 */
	public static List<RsViewModelItem> convertJsonToPOJOs(String jsonData) {
		//
		// decode query string from url and remove models that we normally send in a
		// post request from kendo data
		//
		String workingSet = "";
		try {
			workingSet = URLDecoder.decode(jsonData, StandardCharsets.UTF_8.toString()).replace("models=", "").trim();
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
		//
		// remove the models object coming from kendo if exists
		//
		String KENDO_MODEL_OBJECT_STRING = "{\"models\":";
		if (workingSet.startsWith(KENDO_MODEL_OBJECT_STRING)) {
			workingSet = workingSet.substring(KENDO_MODEL_OBJECT_STRING.length());
			workingSet = workingSet.substring(0, workingSet.length() - 1);

		}
		/*
		 * ensure the just is an array
		 */
		if (workingSet.trim().startsWith("[") == false) {
			workingSet = "[" + workingSet + "]";
		}
		/*
		 * Create the target list
		 */
		jsonData = workingSet;
		List<RsViewModelItem> datalist = null;

		ObjectMapper mapper = new ObjectMapper(); // Create a mapper configure it to not fail on unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.setDateFormat(new StdDateFormat());
		try {
			/*
			 * create a collectionliketype reference that will tell the mapper what class to
			 * use to deserialize those objects
			 */
			CollectionLikeType typeref = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class,
					RsViewModelItem.class);
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

		return datalist;
	}

}
