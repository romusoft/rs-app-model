package net.romusoft.rsapp.mvvm;

import java.util.HashMap;
import java.util.List;

/**
 * this base model provides a entity model the ability to be used as a DTO. The
 * tostring method will return a json representation of the current instance of
 * this class. Use the mapper to dynamically add necessary fields for a dtp
 * without have to extends your class any further or create a new dto class.
 * 
 * @author romulus
 *
 */
public interface IRsModel {
	/**
	 * use this hashMap to add any customization to an entity model dynamically
	 * 
	 * @return the transient hashmap object for this record.
	 */
	public HashMap<String, Object> getMetadata();

	/**
	 * add a key value pair to the hashmap object
	 * 
	 * @param key   the key
	 * @param value the value
	 */
	public void addMetadataValue(String key, Object value);

	/**
	 * search the hashmap object by key for a single object
	 * 
	 * @param <T>   the expected object to be returned
	 * @param key   the target key
	 * @param clazz the expected return type
	 * @return a value of the target type if found. An exception will be thrown if
	 *         key is not found
	 * @throws Exception exception to throw
	 */
	public <T> T searchMetadataForSingle(String key, Class<T> clazz) throws Exception;

	/**
	 * search the hashmap object by key for list of objects
	 * 
	 * @param <T>   the expected object to be returned
	 * @param key   the target key
	 * @param clazz the expected return type
	 * @return a list of values of the target type if found. An exception will be
	 *         thrown if key is not found
	 * @throws Exception exception to throw
	 */
	public <T> List<T> searchMetadataForList(String key, Class<T> clazz) throws Exception;

}
