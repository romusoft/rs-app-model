
/*********************************************************************************************
* @COPYRIGHT                                                                               (c) 2018 WAPA, BILLINGS,MONTANA ALL RIGHTS RESERVED 
 *                                                            THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
*             ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
* 
 * @DESCRIPTION : Used for supporting a flat dto object objects
* 
 * @PROGRAM : application template :  11/15/2018 FUNCTION :
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
package com.rsapp.mvvm.io;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Used for supporting a flat dto object objects
 * 
 * @author romulus
 *
 */
@Repository
public class DTORepository {
	public final static String STRING_TYPE = "string";
	public final static String ARRAY_TYPE = "array";
	public final static String DATETIME_TYPE = "datetime";
	public final static String BOOLEAN_Y_N_TYPE = "y_n";
	public final static String BOOLEAN_1_0_TYPE = "1_0";
	public final static String BOOLEAN_T_F_TYPE = "t_f";
	public final static String BOOLEAN_TRUE_FALSE_TYPE = "true_false";

	// get the entity manager to create queries;
	@Autowired
	private EntityManager entityManager;

	/**
	 * 
	 * @param nativeSqlString
	 * @param propertyInfos
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getDataObjects(String nativeSqlString, DTORepositoryMapper propertyInfos, Class<T> clazz)
			throws Exception {

		return getDataObjects(nativeSqlString, null, propertyInfos, clazz);
	}

	/**
	 * 
	 * @param nativeSqlString
	 * @param parameterMap
	 * @param propertyInfos
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getDataObjects(String nativeSqlString, Map<String, Object> parameterMap,
			DTORepositoryMapper propertyInfos, Class<T> clazz) throws Exception {
		List<T> list = null;

		try {
			String jsonData = getJsonData(nativeSqlString, parameterMap, propertyInfos);

			if (jsonData != null && jsonData.isEmpty() == false) {

				list = DTORepositoryUtil.convertJsonToPOJO(jsonData, clazz);
			}

		} catch (Exception e) {

			System.out.println("Error during query execution. \n" + e.getMessage());
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
	 * @param nativeSqlString sql query, parameters are okay.
	 * @param parameterMap    parameter mapping, name to value.
	 * 
	 * @return either a List of type Object[] for multiple columns or a List of type
	 *         T for single columns - the data returned is the result of the
	 *         specified query.
	 * 
	 * @throws Exception
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
			data = query.getResultList();

		} catch (Exception e) {

			System.out.println("Error during query execution. \n" + e.getMessage());
		}

		return data;
	}

	/**
	 * 
	 * @param nativeSqlString
	 * @param propertyInfos
	 * @return
	 * @throws Exception
	 */
	public String getJsonData(String nativeSqlString, DTORepositoryMapper propertyInfos) throws Exception {

		return getJsonData(nativeSqlString, null, propertyInfos);
	}

	/**
	 * 
	 * @param nativeSqlString
	 * @param parameterMap
	 * @param propertyInfos
	 * @return
	 * @throws Exception
	 */
	public String getJsonData(String nativeSqlString, Map<String, Object> parameterMap,
			DTORepositoryMapper propertyInfos) throws Exception {
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
	private String convertSqlResultToJson(List<Object[]> results, DTORepositoryMapper propertyInfos) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode jsonArray = mapper.createArrayNode();

		for (Object[] columns : results) {
			/*
			 * list of properties cannot be greater than the result
			 */
			if (propertyInfos.size() > columns.length) {
				throw new Exception("list of properties cannot be greater than the result");
			}

			ObjectNode node = mapper.createObjectNode();

			for (int i = 0; i < propertyInfos.size(); i++) {

				String value = columns[i] == null ? null : columns[i].toString();
				/*
				 * validate value based on type check the type to use the appropriate json here
				 * to see if date. if so, create the right format
				 */
				DTORepositoryPropertyInfo info = propertyInfos.get(i);
				String type = info.getPropertyType();
				if (type != null && type.isEmpty() == false && value != null) {
					switch (type) {
					case DTORepository.DATETIME_TYPE:
						value = value.replace(" ", "T");
						break;
					case DTORepository.BOOLEAN_Y_N_TYPE:
						value = value != null && value.toLowerCase().equals("Y") ? "true" : "false";
						break;
					case DTORepository.BOOLEAN_1_0_TYPE:
						value = value != null && value.toLowerCase().equals("1") ? "true" : "false";
						break;
					case DTORepository.BOOLEAN_T_F_TYPE:
						value = value != null && value.toLowerCase().equals("t") ? "true" : "false";
						break;
					case DTORepository.BOOLEAN_TRUE_FALSE_TYPE:
						value = value != null && value.toLowerCase().equals("true") ? "true" : "false";
						break;

					case DTORepository.ARRAY_TYPE:
						break;

					default:
						throw new Exception("Invalid or unsupported type specified!");
					}
				}

				/*
				 * add the value to the node, and move on to the next column
				 */
				if (value != null)
					node.put(info.getPropertyName(), value);
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
	 * 
	 * @param results
	 * @param queryColumnNames
	 * @return
	 * @throws Exception
	 * @throws JsonProcessingException
	 */
	private String convertSingleColumnSqlResultToJson(List<Object> results, DTORepositoryMapper propertyInfos)
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode jsonArray = mapper.createArrayNode();

		if (+propertyInfos.size() != 1) {
			throw new Exception("DTORepositoryMapper must contain a single value");
		}

		DTORepositoryPropertyInfo info = propertyInfos.get(0);

		for (Object column : results) {
			String value = column == null ? null : column.toString();
			/*
			 * validate value based on type check the type to use the appropriate json here
			 * to see if date. if so, create the right format
			 */
			String type = info.getPropertyType();
			if (type != null && type.isEmpty() == false && value != null) {
				switch (type) {
				case DTORepository.DATETIME_TYPE:
					value = value.replace(" ", "T");
					break;
				case DTORepository.BOOLEAN_Y_N_TYPE:
					value = value != null && value.toLowerCase().equals("Y") ? "true" : "false";
					break;
				case DTORepository.BOOLEAN_1_0_TYPE:
					value = value != null && value.toLowerCase().equals("1") ? "true" : "false";
					break;
				case DTORepository.BOOLEAN_T_F_TYPE:
					value = value != null && value.toLowerCase().equals("t") ? "true" : "false";
					break;
				case DTORepository.BOOLEAN_TRUE_FALSE_TYPE:
					value = value != null && value.toLowerCase().equals("true") ? "true" : "false";
					break;

				case DTORepository.ARRAY_TYPE:
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

}