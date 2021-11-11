
/*********************************************************************************************
* @COPYRIGHT  (c) 2021 WAPA, BILLINGS,MONTANA ALL RIGHTS RESERVED 
 *            THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
*             ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
* 
 * @DESCRIPTION : Used for supporting a flat RsDTO object objects
* 
 * @PROGRAM : application template :  05/01/2021 FUNCTION :
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
import java.io.Reader;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import net.romusoft.rsapp.util.RsGeneralUtilities;

/**
 * Used for supporting a flat RsDTO object objects
 * 
 * @author Emmanuel Romulus
 *
 */
@Repository
public class RsDTORepository {
	/**
	 * string
	 */
	public final static String STRING_TYPE = "string";
	/**
	 * array
	 */
	public final static String ARRAY_TYPE = "array";
	/**
	 * datetime
	 */
	public final static String DATETIME_TYPE = "datetime";
	/**
	 * y_n
	 */
	public final static String BOOLEAN_Y_N_TYPE = "y_n";
	/**
	 * 1_0
	 */
	public final static String BOOLEAN_1_0_TYPE = "1_0";
	/**
	 * t_f
	 */
	public final static String BOOLEAN_T_F_TYPE = "t_f";
	/**
	 * true_false
	 */
	public final static String BOOLEAN_TRUE_FALSE_TYPE = "true_false";
	/**
	 * metadata_field
	 */
	public final static String METADATA_FIELD = "metadata_field";
	/**
	 * nested_object_field
	 */
	public final static String NESTED_OBJECT_FIELD = "nested_object_field";

	// get the entity manager to create queries;
	@Autowired
	private EntityManager entityManager;

	/**
	 * get data objects
	 * 
	 * @param <T>             the generic type argument to build the list with
	 * @param nativeSqlString nativeSqlString
	 * @param propertyInfos   propertyInfos
	 * @param clazz           clazz
	 * @return list of objects
	 * @throws Exception exception
	 */
	public <T> List<T> getDataObjects(String nativeSqlString, RsDTORepositoryMapper propertyInfos, Class<T> clazz)
			throws Exception {

		return getDataObjects(nativeSqlString, null, propertyInfos, clazz);
	}

	/**
	 * get data objects
	 * 
	 * @param <T>             the generic type argument to build the list with
	 * @param nativeSqlString sql
	 * @param parameterMap    param
	 * @param propertyInfos   info
	 * @param clazz           clazz
	 * @return list of objects
	 * @throws Exception exception
	 */
	public <T> List<T> getDataObjects(String nativeSqlString, Map<String, Object> parameterMap,
			RsDTORepositoryMapper propertyInfos, Class<T> clazz) throws Exception {
		List<T> list = null;

		try {
			String jsonData = getJsonData(nativeSqlString, parameterMap, propertyInfos);

			if (jsonData != null && jsonData.isEmpty() == false) {

				list = convertJsonToPOJO(jsonData, clazz);
			}

		} catch (Exception e) {

			System.out.println("Error during query execution. \n" + e.getMessage());
			throw e;
		}

		return list;
	}

	/**
	 * This will return the raw query result from query.getResultList().
	 *
	 * NOTE: This method is for retrieving data which will not be mapped to JPA
	 * entities or mapped directly/one-to-one to data objects; for JPA entities
	 * please use the native JPA Repositories; and for business objects use the
	 * other getDataObjects() method in this class.
	 * 
	 * WARNING: This method will return List of type Object[] when more than one
	 * column is selected, but will return a List of type T when only a single
	 * column of type T is selected. This method has suppressed warnings, so make
	 * sure that your method is expecting the correct result back.
	 * 
	 * @param <T>      the generic type argument to build the list with
	 * @param nativeSqlString sql query, parameters are okay.
	 * @param parameterMap    parameter mapping, name to value.
	 * 
	 * @return either a List of type Object[] for multiple columns or a List of type
	 *         T for single columns - the data returned is the result of the
	 *         specified query.
	 * 
	 * @throws Exception exception
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getDataObjects(String nativeSqlString, Map<String, Object> parameterMap) throws Exception {

		Query query = entityManager.createNativeQuery(nativeSqlString);
		// Set parameters
		if (parameterMap != null) {
			parameterMap.forEach((k, v) -> query.setParameter(k, v));
		}
		List<T> data = null;
		try {

			/*
			 * execute the query and return the data set
			 */
			Object tempData = query.getResultList();
			data = (List<T>) tempData;

		} catch (Exception e) {

			System.out.println("Error during query execution. \n" + e.getMessage());
			throw e;
		}

		return data;
	}

	/**
	 * get json data an sql query result
	 * 
	 * @param nativeSqlString the native query to run
	 * @param propertyInfos   how to map properties to columns
	 * @return the json
	 * @throws Exception exception
	 */
	public String getJsonData(String nativeSqlString, RsDTORepositoryMapper propertyInfos) throws Exception {

		return getJsonData(nativeSqlString, null, propertyInfos);
	}

	/**
	 * get json data an sql query result
	 * 
	 * @param nativeSqlString the native query to run
	 * @param parameterMap    parameters used in the query
	 * @param propertyInfos   how to map properties to columns
	 * @return the json
	 * @throws Exception exception
	 */
	public String getJsonData(String nativeSqlString, Map<String, Object> parameterMap,
			RsDTORepositoryMapper propertyInfos) throws Exception {
		String jsonData = null;

		/*
		 * execute the query and store the result in the xmlClobData
		 */
		List<?> data = getDataObjects(nativeSqlString, parameterMap);
		if (data == null || data.size() == 0)
			return jsonData;
		/*
		 * if the first element in the list is an array, that indicates retrieving
		 * several columns per row Otherwise it is a single column query.
		 */
		boolean isSingleColumnQuery = data.get(0) == null || data.get(0).getClass().isArray() == false;
		if (isSingleColumnQuery) {
			/*
			 * process single column query data
			 */
			@SuppressWarnings("unchecked")
			List<Object> singleColumnData = (List<Object>) data;
			jsonData = convertSingleColumnSqlResultToJson(singleColumnData, propertyInfos);
		} else {

			/*
			 * process multiple column query data
			 */
			@SuppressWarnings("unchecked")
			List<Object[]> multipleColumnData = (List<Object[]>) data;
			jsonData = convertSqlResultToJson(multipleColumnData, propertyInfos);
		}

		return jsonData;
	}

	/**
	 * 
	 * @param results
	 * @param queryColumnNames
	 * @return
	 * @throws Exception
	 * @throws JsonProcessingException
	 */
	private String convertSqlResultToJson(List<Object[]> results, RsDTORepositoryMapper propertyInfos)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode jsonArray = mapper.createArrayNode();
		// .set("metadata", metadataNode);

		for (Object[] columns : results) {
			/*
			 * list of properties cannot be greater than the result
			 */
			if (propertyInfos.size() > columns.length) {
				throw new Exception("list of properties cannot be greater than the result");
			}

			ObjectNode node = mapper.createObjectNode();
			ObjectNode metadataNode = mapper.createObjectNode();
			node.set("metadata", metadataNode); // metadata is ready for this record

			//
			// nested or complex object
			ObjectNode nestedObjectNode = mapper.createObjectNode();

			for (int i = 0; i < propertyInfos.size(); i++) {

				String value = columns[i] == null ? null : columns[i].toString();
				Clob clobValue = columns[i] instanceof Clob ? (Clob) columns[i] : null;
				/*
				 * validate value based on type check the type to use the appropriate json here
				 * to see if date. if so, create the right format
				 */
				RsDTORepositoryPropertyInfo info = propertyInfos.get(i);
				String type = info.getPropertyType();
				if (type != null && type.isEmpty() == false && value != null) {
					switch (type) {
					case RsDTORepository.DATETIME_TYPE:
						value = value.replace(" ", "T");
						break;
					case RsDTORepository.BOOLEAN_Y_N_TYPE:
						value = value != null && value.toLowerCase().equals("Y") ? "true" : "false";
						break;
					case RsDTORepository.BOOLEAN_1_0_TYPE:
						value = value != null && value.toLowerCase().equals("1") ? "true" : "false";
						break;
					case RsDTORepository.BOOLEAN_T_F_TYPE:
						value = value != null && value.toLowerCase().equals("t") ? "true" : "false";
						break;
					case RsDTORepository.BOOLEAN_TRUE_FALSE_TYPE:
						value = value != null && value.toLowerCase().equals("true") ? "true" : "false";
						break;

					case RsDTORepository.ARRAY_TYPE:
						break;
					case RsDTORepository.METADATA_FIELD:
						break;
					case RsDTORepository.NESTED_OBJECT_FIELD:
						break;

					default:
						throw new Exception("Invalid or unsupported type specified!");
					}
				}

				/*
				 * add the value to the node, and move on to the next column
				 */
				if (clobValue != null) {
					//
					// convert the clob data to string by a buffering 2048 chars at a time
					StringBuilder sb = new StringBuilder((int) clobValue.length());
					Reader r = clobValue.getCharacterStream();
					char[] cbuffer = new char[2048];
					int n;
					while ((n = r.read(cbuffer, 0, cbuffer.length)) != -1) {
						sb.append(cbuffer, 0, n);
					}
					value = sb.toString();
				}
				if (info.getPropertyType() == RsDTORepository.METADATA_FIELD) {

					if (value == null) {
						value = "[]";
					}
					//
					// First try to use the value as json. If not a valid json, use the value as is
					ObjectMapper tempmapper = new ObjectMapper();
					try {

						JsonNode metadataValueNode = tempmapper.readTree(value);
						metadataNode.set(info.getPropertyName(), metadataValueNode);
					} catch (JsonProcessingException e) {
						//
						// not a valid json. Use the value as a scalar value
						metadataNode.put(info.getPropertyName(), value);
					}

				} else if (info.getPropertyType() == RsDTORepository.NESTED_OBJECT_FIELD) {

					if (value == null) {
						value = "[]";
					}
					//
					// First try to use the value as json. If not a valid json, use the value as is
					ObjectMapper tempmapper = new ObjectMapper();
					try {

						JsonNode nestedObjectValueNode = tempmapper.readTree(value);
						nestedObjectNode.set(info.getPropertyName(), nestedObjectValueNode);
					} catch (JsonProcessingException e) {
						//
						// not a valid json. Use the value as a scalar value
						nestedObjectNode.put(info.getPropertyName(), value);
					}

				} else if (value != null) {
					node.put(info.getPropertyName(), value);
				}
			}

			jsonArray.add(node);
		}

		/*
		 * return valid json
		 */
		String json = null;
		try {
			json = mapper.writeValueAsString(jsonArray);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * convert an sql query result to json
	 * 
	 * @param results
	 * @param propertyInfos
	 * @return
	 * @throws Exception
	 */
	private String convertSingleColumnSqlResultToJson(List<Object> results, RsDTORepositoryMapper propertyInfos)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode jsonArray = mapper.createArrayNode();

		if (+propertyInfos.size() != 1) {
			throw new Exception("RsDTORepositoryMapper must contain a single value");
		}

		RsDTORepositoryPropertyInfo info = propertyInfos.get(0);

		for (Object column : results) {
			String value = column == null ? null : column.toString();
			/*
			 * validate value based on type check the type to use the appropriate json here
			 * to see if date. if so, create the right format
			 */
			String type = info.getPropertyType();
			if (type != null && type.isEmpty() == false && value != null) {
				switch (type) {
				case RsDTORepository.DATETIME_TYPE:
					value = value.replace(" ", "T");
					break;
				case RsDTORepository.BOOLEAN_Y_N_TYPE:
					value = value != null && value.toLowerCase().equals("Y") ? "true" : "false";
					break;
				case RsDTORepository.BOOLEAN_1_0_TYPE:
					value = value != null && value.toLowerCase().equals("1") ? "true" : "false";
					break;
				case RsDTORepository.BOOLEAN_T_F_TYPE:
					value = value != null && value.toLowerCase().equals("t") ? "true" : "false";
					break;
				case RsDTORepository.BOOLEAN_TRUE_FALSE_TYPE:
					value = value != null && value.toLowerCase().equals("true") ? "true" : "false";
					break;

				case RsDTORepository.ARRAY_TYPE:
					break;

				default:
					throw new Exception("Invalid or unsupported type specified!");
				}
			}

			/*
			 * add the value to the node, and move on to the next column
			 */
			if (value != null)
				jsonArray.add(value);
		}

		/*
		 * return valid json
		 */
		String json = null;
		try {
			json = mapper.writeValueAsString(jsonArray);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * Mapped json objects to pojos using the target class
	 * 
	 * @param <T>      the generic type argument to build the list with
	 * @param jsonData the json string to deserialize
	 * @param clazz    the target class
	 * @return list of objects of the target class
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

	/**
	 * Get an expecting one record from the json string
	 * 
	 * @param <T>      the generic type argument to build the list with
	 * @param jsonData the json string to deserialize
	 * @param clazz    the target class
	 * @return list of objects of the target class
	 */
	public static <T> T convertJsonToSinglePOJO(String jsonData, Class<T> clazz) {

		List<T> data = convertJsonToPOJO(jsonData, clazz);
		if (data.size() == 1)
			return data.get(0);
		else
			return null;
	}

}