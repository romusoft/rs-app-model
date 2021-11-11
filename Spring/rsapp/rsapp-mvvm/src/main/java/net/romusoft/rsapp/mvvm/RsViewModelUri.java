package net.romusoft.rsapp.mvvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.romusoft.rsapp.util.RsGeneralUtilities;

/**
 * Uri definition. At parameters dynamically to the uri. When the uri is
 * returned, if there is any query string in the parameter, the uri is formatted
 * accordingly
 * 
 * @author romulus
 *
 */
public class RsViewModelUri {

	private String url;
	@JsonIgnore
	private final HashMap<String, Object> parameters = new HashMap<String, Object>();
	@JsonIgnore
	private String attributeName;
	@JsonIgnore
	private Model model = null;

	/************* COSNTRUCTORS ********/

	/**
	 * default constructor
	 */
	public RsViewModelUri() {

	}

	/**
	 * a constructor with a url
	 * 
	 * @param url url to set
	 */
	public RsViewModelUri(String url) {
		this.setUrl(url);
	}

	/**
	 * mostly used for api uris
	 * 
	 * @param url           url to set
	 * @param attributeName the name of the attribute to bind the url string to
	 */
	public RsViewModelUri(String url, String attributeName) {
		this.url = url;
		this.attributeName = attributeName;
	}

	/**
	 * mostly used for mvc urls
	 * 
	 * @param model         the mvc model from spring where attributes are added
	 * @param attributeName name of the attribute
	 */
	public RsViewModelUri(Model model, String attributeName) {
		this.model = model;
		this.attributeName = attributeName;
	}

	/**********************************/

	/**
	 * retrieve a formatted uri while taking into account whether parameters are set
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return retrieveFormattedUrl();
	}

	/**
	 * set the url
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {

		this.url = url;
		//
		// update the uri attribute
		if (this.model == null || this.attributeName == null || this.attributeName.isEmpty())
			return;
		model.addAttribute(this.attributeName, url);
	}

	/**
	 * parameters for the url
	 * 
	 * @return return the parameter hashmap
	 */
	public HashMap<String, Object> getParameters() {
		return parameters;
	}

	/**
	 * get the name of the attribute to which the uri is bound
	 * 
	 * @return the attribute name
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * set the name of the attribute
	 * 
	 * @param attributeName the attribute name
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * the mvc model from spring
	 * 
	 * @return the mvc model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * set the model
	 * 
	 * @param model the model instance
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/***************************************/

	/**
	 * add parameter to the uri as key/value pair
	 * 
	 * @param key   key
	 * @param value value
	 * @return the uri object for chaining
	 */
	public RsViewModelUri addParameter(String key, Object value) {
		if (key == null || key.trim().isEmpty() || value == null || this.parameters.containsKey(key))
			return this;

		this.parameters.put(key, value);

		//
		// update the uri attribute
		if (this.model == null || this.attributeName == null || this.attributeName.isEmpty())
			return this;

		model.addAttribute(this.attributeName, this.getUrl());
		return this;

	}

	/**
	 * clear the parameter list
	 * 
	 * @return return the parameter object for chaining
	 */
	public HashMap<String, Object> clearParameters() {
		parameters.clear();
		//
		// update the uri attribute
		if (this.model == null || this.attributeName == null || this.attributeName.isEmpty())
			return parameters;

		model.addAttribute(this.attributeName, this.getUrl());
		return parameters;
	}

	/**
	 * retrieve the formatted url string
	 * 
	 * @return
	 */
	private String retrieveFormattedUrl() {
		if (url == null || url.trim().isEmpty())
			return "";

		StringBuilder builder = new StringBuilder(url);
		Set<Entry<String, Object>> entrySet = parameters.entrySet();

		boolean first = true;
		for (Entry<String, Object> entry : entrySet) {
			if (first) {
				first = false;
				builder.append("?" + entry.getKey() + "=" + formatUrlParameterValue(entry.getValue()));
			} else {
				builder.append("&" + entry.getKey() + "=" + formatUrlParameterValue(entry.getValue()));
			}
		}

		return builder.toString();
	}

	/**
	 * format the parameter value, it may need to be a comma separated string for
	 * list
	 * 
	 * @param value value
	 * @author romulus
	 * @return the formatted value
	 */
	@SuppressWarnings("rawtypes")
	private String formatUrlParameterValue(Object value) {

		Class<? extends Object> clazz = value.getClass();
		//
		// handle primitive types
		if (RsGeneralUtilities.isPrimitiveType(clazz)) {
			return value.toString();
		}
		//
		// handle array
		Object[] array = new Object[0];
		if (clazz.isArray()) {
			array = (Object[]) value;
		} else if (clazz == ArrayList.class) {
			array = ((ArrayList) value).toArray();
		}

		//
		// create comma separated list string
		boolean first = true;
		StringBuilder builder = new StringBuilder();
		for (Object obj : array) {
			if (obj == null) {
				continue;
			}
			if (first) {
				first = false;
				builder.append(obj.toString());
			} else {
				builder.append("," + obj.toString());
			}
		}

		return builder.toString();
	}

	/**********************************/

	/**
	 * the to string returns the formatted url
	 */
	@Override
	public String toString() {
		return retrieveFormattedUrl();
	}

}
