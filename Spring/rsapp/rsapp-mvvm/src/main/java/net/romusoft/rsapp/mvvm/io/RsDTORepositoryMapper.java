/*********************************************************************************************
 * @COPYRIGHT 					(c) 2018 WAPA, BILLINGS,MONTANA ALL RIGHTS RESERVED 
 * 				THIS SOFTWARE IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            	ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT NOTICE. 
 *            	THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. 
 *            	NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * @DESCRIPTION : Specify property name, column value and type to use for the
 *              json. For dates that include time, set the propertyType to
 *              datatime.
 * 
 * @PROGRAM : To be used with the XmlJsonUtil MODULE : XmlJsonPropertyInfo.java
 * @FUNCTION : Used to bind columns to properties. Properties can be json or
 *           class properties
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
package net.romusoft.rsapp.mvvm.io;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to bind columns to properties. Properties can be json or class
 * properties
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsDTORepositoryMapper {

	public final static String STRING_TYPE = "string";
	public final static String ARRAY_TYPE = "array";
	public final static String DATETIME_TYPE = "datetime";
	public final static String BOOLEAN_Y_N_TYPE = "y_n";
	public final static String BOOLEAN_1_0_TYPE = "1_0";
	public final static String BOOLEAN_T_F_TYPE = "t_f";
	public final static String BOOLEAN_TRUE_FALSE_TYPE = "true_false";
	public final static String METADATA_FIELD = "metadata_field";
	public final static String NESTED_OBJECT_FIELD = "nested_object_field";

	private List<RsDTORepositoryPropertyInfo> propertyInfos = new ArrayList<RsDTORepositoryPropertyInfo>();

	/**
	 * the list of property infos
	 * 
	 * @return the list of property infos
	 */
	public List<RsDTORepositoryPropertyInfo> getPropertyInfos() {
		return propertyInfos;
	}

	/**
	 * Add property column mapper in the order the query will return the data
	 * 
	 * @param propertyInfo property to add
	 * 
	 * @return whether add was successful
	 */
	public boolean add(RsDTORepositoryPropertyInfo propertyInfo) {
		/*
		 * add a valid column duplicate column name is not allowed
		 */
		boolean valid = validatePropertyInfo(propertyInfo);
		if (valid) {
			propertyInfos.add(propertyInfo);
		}

		return valid;

	}

	/**
	 * get a property info object
	 * 
	 * @param index the index at which to retrieve the record
	 * @return target property info object
	 */
	public RsDTORepositoryPropertyInfo get(int index) {

		int size = size();
		if (size > 0 && size > index)
			return propertyInfos.get(index);
		else
			return null;
	}

	/**
	 * valid the property
	 * 
	 * @param info property info to validate
	 * @return whether the property info is valid
	 */
	private boolean validatePropertyInfo(RsDTORepositoryPropertyInfo info) {

		if (info == null || info.getPropertyName() == null || info.getPropertyName().isEmpty())
			return false;

		for (RsDTORepositoryPropertyInfo temp : propertyInfos) {
			if (temp.getPropertyName().equals(info.getPropertyName())) {
				return false;
			}
		}

		return true;

	}

	/**
	 * return size of the list of property infos
	 * 
	 * @return the size of the property info list
	 */
	public int size() {
		return propertyInfos.size();
	}

}
