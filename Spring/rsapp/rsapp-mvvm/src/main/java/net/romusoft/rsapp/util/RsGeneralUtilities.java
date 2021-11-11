package net.romusoft.rsapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import net.romusoft.rsapp.io.RsDTORepository;

/**
 * general utility to help with string manipulation, type conversion, etc..
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsGeneralUtilities {
	/**
	 * a list to store the types
	 */
	protected static final Map<Class<?>, Class<?>> WRAPPER_TYPE_MAP;
	static {
		WRAPPER_TYPE_MAP = new HashMap<Class<?>, Class<?>>(16);
		WRAPPER_TYPE_MAP.put(Integer.class, int.class);
		WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
		WRAPPER_TYPE_MAP.put(Character.class, char.class);
		WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
		WRAPPER_TYPE_MAP.put(Double.class, double.class);
		WRAPPER_TYPE_MAP.put(Float.class, float.class);
		WRAPPER_TYPE_MAP.put(Long.class, long.class);
		WRAPPER_TYPE_MAP.put(Short.class, short.class);
		WRAPPER_TYPE_MAP.put(String.class, String.class);
		WRAPPER_TYPE_MAP.put(Void.class, void.class);
	}

	/**
	 * to check for primitive types
	 * 
	 * @param source the value for which to check the type
	 * @return whether the value is primitive
	 */
	public static boolean isPrimitiveType(Object source) {
		return WRAPPER_TYPE_MAP.containsKey(source.getClass());
	}

	/**
	 * check for primitive type
	 * 
	 * @param clazz the class possibly a wrapped primitive type (Long) to check for
	 *              primitive
	 * @return whether the value is primitive
	 */
	public static boolean isPrimitiveType(Class<? extends Object> clazz) {
		return WRAPPER_TYPE_MAP.containsKey(clazz);
	}

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
	 * @param value the value to decode
	 * @return the new decode string
	 */
	public static String decodeValue(String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}

	/**
	 * return an empty list if the passed list is null
	 * 
	 * @param <T>      the target type
	 * @param iterable the list to check for null and return an empty one
	 * @return an empty list if the passed in list is null
	 */
	public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
		return iterable == null ? Collections.<T>emptyList() : iterable;
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
	 * Create a deep copy of a given object. This is needed to fix the tab switching
	 * issue that made be using shared navigation objects
	 * 
	 * @param <T>   the type of copy to generate
	 * @param value the instance for which to generate a deep copy
	 * @return return a new instance copy
	 * @throws Exception exception if the method fail to generate the copy
	 */

	@SuppressWarnings("unchecked")
	public static <T> T generateCopy(T value) throws Exception {

		ObjectMapper mapper = new ObjectMapper(); // Create a mapper configure it to not fail on unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.setDateFormat(new StdDateFormat());
		String jsonData;
		try {
			jsonData = mapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new Exception("Cannot object copy");
		}

		//
		// covert the value back

		T returnValue = (T) RsDTORepository.convertJsonToSinglePOJO(jsonData, value.getClass());
		return returnValue;
	}

	/**
	 * convert string value to long
	 * 
	 * @param value the string value as long
	 * @return the long value from the string
	 */
	public static long longValue(String value) {
		if (value == null)
			return 0;
		value = value.trim();
		long retValue = 0;
		String expression = "\\d+";
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(value);
		boolean matchFound = matcher.find();
		if (matchFound) {
			retValue = Long.parseLong(value);
		}

		return retValue;
	}
}
