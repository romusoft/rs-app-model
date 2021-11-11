/*********************************************************************************************
 * COPYRIGHT (c) 2021 WAPA, BILLINGS,MONTANA ALL RIGHTS RESERVED THIS SOFTWARE
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
package net.romusoft.rsapp.io;
/**
 * property info
 * @author eromu
 *
 */
public class RsXmlJsonPropertyInfo {

	/**
	 * array_primitive
	 */
	public final static String ARRAY_PRIMITIVE_TYPE = "array_primitive";
	/**
	 * integer
	 */
	public final static String INTEGER_TYPE = "integer";
	
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

	private String columnName;
	private String propertyName;
	private String propertyType;
	private boolean isMapped;

	/**
	 * constructor with the name of the property and the column it is mapped to.
	 * 
	 * @param propertyName the name of the property
	 * @param columnName   the column the property is to map to
	 */
	public RsXmlJsonPropertyInfo(String propertyName, String columnName) {
		this.propertyName = propertyName;
		this.columnName = columnName;
	}

	/**
	 * constructor with the name of the property and the column it is mapped to, and
	 * property type
	 * 
	 * @param propertyName the name of the property
	 * @param propertyType the type of property
	 * @param columnName   the column the property is to map to
	 */
	public RsXmlJsonPropertyInfo(String propertyName, String columnName, String propertyType) {
		this.propertyName = propertyName;
		this.propertyType = propertyType;
		this.columnName = columnName;
	}

	/**
	 * column name to as represented in the database
	 * 
	 * @return the column name
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * set the column name
	 * 
	 * @param columnName the column name
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * property name to map the column to
	 * 
	 * @return the name of the property
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * set the property name
	 * 
	 * @param propertyName the name of the property
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * property type that represents the data type of the class property
	 * 
	 * @return the property type
	 */
	public String getPropertyType() {
		return propertyType;
	}

	/**
	 * set the property type
	 * 
	 * @param propertyType the property type
	 */
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * whether the property is mapped to the column
	 * 
	 * @return whether the property is mapped to the column
	 */
	public boolean isMapped() {
		return isMapped;
	}

	/**
	 * whether the property is mapped to the column
	 * 
	 * @param isMapped whether the property is mapped to the column
	 */
	public void setMapped(boolean isMapped) {
		this.isMapped = isMapped;
	}

}
