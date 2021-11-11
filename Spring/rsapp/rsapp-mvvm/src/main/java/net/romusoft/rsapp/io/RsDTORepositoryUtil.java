/*********************************************************************************************
 * @COPYRIGHT (c) 2018 WAPA, BILLINGS,MONTANA ALL RIGHTS RESERVED THIS SOFTWARE
 *            IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION
 *            OF THE ABOVE COPYRIGHT NOTICE. THIS SOFTWARE OR ANY OTHER COPIES
 *            THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY
 *            OTHER PERSON. NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY
 *            TRANSFERRED.
 * 
 * @DESCRIPTION : Used for supporting a flat dto object objects
 * 
 * @PROGRAM : application template : 11/15/2018 FUNCTION :
 * 
 * @ENVIRONMENT : java
 * 
 * 
 * @MODIFICATION HISTORY:
 *
 * 
 * @author Emmanuel Romulus
 *
 ***********************************************************************************************/

package net.romusoft.rsapp.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

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

import net.romusoft.rsapp.util.RsGeneralUtilities;

/**
 * Used for supporting a flat dto object objects
 * 
 * @author Emmanuel Romulus
 *
 **/
@Repository
public class RsDTORepositoryUtil {
	/**
	 * Mapped json objects to pojos using the target class
	 * 
	 * @param <T>      the target data type to return
	 * @param jsonData jsonData the json string to convert
	 * @param clazz    the target data type to return
	 * @return a list of records for the target data type
	 */
	public static <T> List<T> convertJsonToPOJO(String jsonData, Class<T> clazz) {
		//
		// decode query string from url and remove models that we normally send in a
		// post request from kendo data
		//

		String workingSet = RsGeneralUtilities.decodeValue(jsonData).replace("models=", "").trim();

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
		List<T> datalist = null;

		ObjectMapper mapper = new ObjectMapper(); // Create a mapper configure it to not fail on unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.setDateFormat(new StdDateFormat());
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

		return datalist;
	}

}
