package net.romusoft.rsapp.mvvm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import net.romusoft.rsapp.mvvm.IRsModel;

/**
 * general utility to help with string manipulation, type conversion, etc..
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsGeneralUtilities {

	/**
	 * Requires the specified pre-condition to be <code>true</code> upon entry to an
	 * interface method.
	 * 
	 * @param condition a <code>boolean</code> indicating the specified
	 *                  pre-condition
	 * @param message   is a <code>String</code> representation of the
	 *                  pre-condition, which is used to convey the failure in the
	 *                  exception that is thrown
	 * 
	 * @throws IllegalArgumentException when the pre-condition is <b>false</b> (and
	 *                                  the <code>Exception</code> message gets
	 *                                  logged), otherwise no action is taken
	 */
	public static final void requires(boolean condition, String message) {
		if (!condition) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * decode url strings
	 * 
	 * @param value value to decode
	 * @return a clean json string
	 */
	public static String decodeValue(String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}

	/**
	 * the current user
	 * 
	 * @return the current user
	 */
	public static User getCurrentUser() {

		Object obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (obj == null) {

		}
		return null;
	}

	/**
	 * true/false whether the string is empty or null
	 * 
	 * @param str value to evaluate
	 * @return true/false whether the string is empty or null
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.isEmpty() || str.equalsIgnoreCase("NULL"))
			return true;
		return false;
	}

	/**
	 * whether the list is null or empty
	 * @param list the list to check
	 * @return whether the list is null or empty
	 */
	public static boolean isNullOrEmpty(List<? extends IRsModel> list) {
		if (list == null || list.isEmpty())
			return true;
		return false;
	}

}
