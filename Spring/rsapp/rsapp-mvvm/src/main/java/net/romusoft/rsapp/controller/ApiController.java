package net.romusoft.rsapp.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.RestController;

/**
 * Annotation to describe a REST controller
 * 
 * @author romulus
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
public @interface ApiController {
	/**
	 * api context
	 */
	public static final String API = "/api";

	/**
	 * api context
	 */
	public static final String URI_CREATE = "create";
	/**
	 * api context
	 */
	public static final String URI_READ = "read";
	/**
	 * api context
	 */
	public static final String URI_UPDATE = "update";
	/**
	 * api context
	 */
	public static final String URI_DELETE = "delete";
	/**
	 * api context
	 */
	public static final String URI_DETAIL = "detail";
	/**
	 * api context
	 */
	public static final String URI_LIST = "list";

	/**
	 * api context
	 */
	public static final String API_BASE_URL_ATTRIBUTE_NAME = "apiBaseUrl";

	/**
	 * api context
	 */
	public static final String URI_CREATE_ATTRIBUTE_NAME = "uriCreate";
	/**
	 * api context
	 */
	public static final String URI_READ_ATTRIBUTE_NAME = "uriRead";
	/**
	 * api context
	 */
	public static final String URI_UPDATE_ATTRIBUTE_NAME = "uriUpdate";
	/**
	 * api context
	 */
	public static final String URI_DELETE_ATTRIBUTE_NAME = "uriDelete";
	/**
	 * api context
	 */
	public static final String URI_DETAIL_ATTRIBUTE_NAME = "uriDetail";
	/**
	 * api context
	 */
	public static final String URI_LIST_ATTRIBUTE_NAME = "uriList";


}