/*********************************************************************************************
 * COPYRIGHT (c) 2018 WAPA, BILLINGS,MONTANA ALL RIGHTS RESERVED THIS SOFTWARE
 * IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN ACCORDANCE
 * WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION OF THE ABOVE COPYRIGHT
 * NOTICE. THIS SOFTWARE OR ANY OTHER COPIES THEREOF MAY NOT BE PROVIDED OR
 * OTHERWISE MADE AVAILABLE TO ANY OTHER PERSON. NO TITLE TO AND OWNERSHIP OF
 * THE SOFTWARE IS HEREBY TRANSFERRED.
 * 
 * DESCRIPTION : Specify property name, column value and type to use for the
 * json. For dates that include time, set the propertyType to datatime.
 * 
 * PROGRAM : To be used with the XmlJsonUtil MODULE : XmlJsonPropertyInfo.java
 * AUTHOR : Emmanuel Romulus, at 10/24/2018 FUNCTION : Used to bind columns to
 * properties. Properties can be json or class properties
 * 
 * ENVIRONMENT : C# NOTES :
 * 
 * 
 * MODIFICATION HISTORY:
 *
 * 
 * @author Emmanuel Romulus
 *
 ***********************************************************************************************/
package com.romusoft.rsapp.mvvm.io;
public class XmlJsonPropertyInfo {

	public final  static String STRING_TYPE = "string";
	public final  static String ARRAY_TYPE = "array";
	public final  static String ARRAY_PRIMITIVE_TYPE = "array_primitive";
	public final static  String DATETIME_TYPE = "datetime";
	public final  static String BOOLEAN_Y_N_TYPE = "y_n";
	public final  static String BOOLEAN_1_0_TYPE = "1_0";
	public final  static String BOOLEAN_T_F_TYPE = "t_f";
	public final  static String BOOLEAN_TRUE_FALSE_TYPE = "true_false";
	public final  static String INTEGER_TYPE = "integer";

	private String columnName;
	private String propertyName;
	private String propertyType;
	private boolean isMapped;

	/**
	 * 
	 * @param propertyName
	 * @param columnName
	 */
	public XmlJsonPropertyInfo(String propertyName, String columnName) {
		this.propertyName = propertyName;
		this.columnName = columnName;
	}

	/**
	 * 
	 * @param propertyName
	 * @param propertyType
	 * @param columnName
	 */
	public XmlJsonPropertyInfo(String propertyName, String columnName, String propertyType) {
		this.propertyName = propertyName;
		this.propertyType = propertyType;
		this.columnName = columnName;
	}

	/**
	 * column name to as represented in the database
	 * 
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * property name to map the column to
	 * 
	 * @return
	 */
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * property type that represents the data type of the class property
	 * 
	 * @return
	 */
	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public boolean isMapped() {
		return isMapped;
	}

	public void setMapped(boolean isMapped) {
		this.isMapped = isMapped;
	}

	
}
