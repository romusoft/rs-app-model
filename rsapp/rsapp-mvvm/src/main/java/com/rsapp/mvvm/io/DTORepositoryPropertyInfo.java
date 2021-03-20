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
package com.rsapp.mvvm.io;

/**
 * To be used with the XmlJsonUtil MODULE : XmlJsonPropertyInfo.java
 * 
 * @author romulus
 *
 */
public class DTORepositoryPropertyInfo {

	private String columnName;
	private String propertyName;
	private String propertyType;

	/**
	 * 
	 * @param propertyName
	 * @param columnName
	 */
	public DTORepositoryPropertyInfo(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * 
	 * @param propertyName
	 * @param columnName
	 */
	public DTORepositoryPropertyInfo(String propertyName, String columnName) {
		this.propertyName = propertyName;
		this.columnName = columnName;
	}

	/**
	 * 
	 * @param propertyName
	 * @param propertyType
	 * @param columnName
	 */
	public DTORepositoryPropertyInfo(String propertyName, String columnName, String propertyType) {
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

}
