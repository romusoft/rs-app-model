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

/**
 * To be used with the XmlJsonUtil MODULE : XmlJsonPropertyInfo.java
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsDTORepositoryPropertyInfo {

	private String columnName;
	private String propertyName;
	private String propertyType;

	/**
	 * instantiate a property info
	 * 
	 * @param propertyName the name of the property
	 */
	public RsDTORepositoryPropertyInfo(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * constructor with the name of the property and the column it is mapped to.
	 * 
	 * @param propertyName the name of the property
	 * @param columnName   the column the property is to map to
	 */
	public RsDTORepositoryPropertyInfo(String propertyName, String columnName) {
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
	public RsDTORepositoryPropertyInfo(String propertyName, String columnName, String propertyType) {
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

}
