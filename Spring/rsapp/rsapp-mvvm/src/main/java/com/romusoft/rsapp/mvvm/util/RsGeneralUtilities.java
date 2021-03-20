package com.romusoft.rsapp.mvvm.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * general utility to help with string manipulation, type conversion, etc..
 * 
 * @author romulus
 *
 */
public class RsGeneralUtilities {
	/**
	 * populate the principal data. This comes from WAPA portal
	 * 
	 * @return
	 *//*
		 * @SuppressWarnings("rawtypes") public static CustomUserDetails
		 * getCustomUserDetails() {
		 * 
		 * OAuth2Authentication auth=
		 * (OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication();
		 * UsernamePasswordAuthenticationToken authentictionToken =
		 * (UsernamePasswordAuthenticationToken) auth .getUserAuthentication();
		 * 
		 * // Create a mapper configure it to not fail on unknown properties
		 * 
		 * CustomUserDetails user = null; ObjectMapper mapper = new ObjectMapper();
		 * mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 * 
		 * LinkedHashMap details = (LinkedHashMap) authentictionToken.getDetails();
		 * JsonNode jsonNode = mapper.convertValue(details, JsonNode.class); JsonNode
		 * principalNode = jsonNode.findValue("principal");
		 * 
		 * try {
		 * 
		 * // deserialize the json principal node into a custom user details JsonParser
		 * jparser = principalNode.traverse(); user = mapper.readValue(jparser,
		 * CustomUserDetails.class);
		 * 
		 * // get the roles from the principal node List<String> roles =
		 * principalNode.findValuesAsText("authority"); user.setRoles(roles);
		 * 
		 * } catch (JsonParseException e) { e.printStackTrace(); } catch
		 * (JsonMappingException e) { e.printStackTrace(); } catch (IOException e) {
		 * e.printStackTrace(); } return user; }
		 */

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

//	/**
//	 * check whether the user is logged in
//	 * 
//	 * @return
//	 */
//	public static boolean isLoggedIn() {
//		OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
//		return auth != null;
//	}

	/**
	 * decode url strings
	 * 
	 * @param value
	 * @return
	 */
	public static String decodeValue(String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}

	public User getCurrentUser() {

		Object obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (obj == null) {

		}
		return null;
	}
}
