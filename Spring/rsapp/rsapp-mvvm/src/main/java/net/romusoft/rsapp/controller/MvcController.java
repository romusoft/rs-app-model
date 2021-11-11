package net.romusoft.rsapp.controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Controller;

/**
 * Annotation to describe an mvc controller
 * 
 * @author romulus
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Controller
public @interface MvcController {
	/**
	 * api context
	 */
	public static final String CREATE = "/create";
	/**
	 * api context
	 */
	public static final String READ = "/read";
	/**
	 * api context
	 */
	public static final String UPDATE = "/update";
	/**
	 * api context
	 */
	public static final String DELETE = "/delete";
	/**
	 * api context
	 */
	public static final String DETAIL = "/detail";
	/**
	 * api context
	 */
	public static final String LIST = "/list";

	/**
	 * api context
	 */
	public static final String PAGE_TITLE_ATTRIBUTE_NAME = "pageTitle";
	/**
	 * api context
	 */
	public static final String END_DATE_ATTRIBUTE_NAME = "endDate";
	/**
	 * api context
	 */
	public static final String START_DATE_ATTRIBUTE_NAME = "startDate";
}
