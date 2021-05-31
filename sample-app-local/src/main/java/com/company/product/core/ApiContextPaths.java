package com.company.product.core;

/**
 * context path for api. it includes basic request mapping and uri
 * 
 * @requestmapping request mapping contant value starts with a forward slash
 * @uri uri are resource endpoints based on the base url. They do not start with
 *      a forward slash
 * @uriAttributeName specified as attributes in an mvc controller for crud
 *                   operation
 * @author romulus
 *
 */
public class ApiContextPaths {

	public static final String API = "api";
	public static final String LIST_URI = "list";
	public static final String DETAIL_URI = "detail";
	public static final String UPDATE_URI = "update";
	public static final String DELETE_URI = "delete";
	public static final String ADD_URI = "add";

	/*
	 * uri attribute names. Used in controller model to specified end points for
	 * data crud on the view
	 * 
	 */
	public static final String API_BASE_URL_ATTRIBUTE_NAME = "apiBaseUrl";
	public static final String URI_CREATE_ATTRIBUTE_NAME = "uriCreate";
	public static final String URI_READ_ATTRIBUTE_NAME = "uriRead";
	public static final String URI_UPDATE_ATTRIBUTE_NAME = "uriUpdate";
	public static final String URI_DELETE_ATTRIBUTE_NAME = "uriDelete";
}
