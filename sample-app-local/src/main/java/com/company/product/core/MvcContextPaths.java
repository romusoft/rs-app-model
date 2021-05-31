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
public class MvcContextPaths {
	public static final String LIST = "/list";
	public static final String DETAIL = "/detail";
	public static final String UPDATE = "/update";
	public static final String DELETE = "/delete";
	public static final String ADD = "/add";

}
