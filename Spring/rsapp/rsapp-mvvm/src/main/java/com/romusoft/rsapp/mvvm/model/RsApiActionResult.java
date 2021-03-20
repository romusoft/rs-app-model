package com.romusoft.rsapp.mvvm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author eromu_000
 *
 * @param <T>
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
	 * 
	 * @return
	 */
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isHasWarning() {
		return hasWarning;
	}

	public void setHasWarning(boolean hasWarning) {
		this.hasWarning = hasWarning;
	}

	/**
	 * 
	 * @return
	 */
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * 
	 * @return
	 */
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
