package net.romusoft.rsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Results from an api call. T is the data type to return
 * 
 * @author Emmanuel Romulus
 *
 * @param <T> the data type for the result data
 */
public class RsApiActionResult<T> {

	private T data;
	private String message;
	private boolean hasWarning;

	private String error;
	private boolean hasError;
	@JsonIgnore
	private Exception exception;

	/**
	 * The data returned in the api call
	 * 
	 * @return the data from the api call
	 */
	public T getData() {
		return data;
	}

	/**
	 * set the data
	 * 
	 * @param data the data to be set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * get a message a long with the data. The message could error, success, or
	 * whatever is useful as a message
	 * 
	 * @return the message to return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * set the message
	 * 
	 * @param message the message to be set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Whether the result has warning
	 * 
	 * @return whether the result has warning
	 */
	public boolean isHasWarning() {
		return hasWarning;
	}

	/**
	 * set whether the result has warning
	 * 
	 * @param hasWarning whether the result has warning
	 */
	public void setHasWarning(boolean hasWarning) {
		this.hasWarning = hasWarning;
	}

	/**
	 * error message if any
	 * 
	 * @return error message or null
	 */
	public String getError() {
		return error;
	}

	/**
	 * set error message
	 * 
	 * @param error the error message to be set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * whether the result has error
	 * 
	 * @return whether the result has error
	 */
	public boolean isHasError() {
		return hasError;
	}

	/**
	 * whether the result has error
	 * 
	 * @param hasError whether the result has error
	 */
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * the exception that was thrown if any
	 * 
	 * @return the exception that was thrown if any
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * set the exception that was thrown
	 * 
	 * @param exception the exception to be set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
