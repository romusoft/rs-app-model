package net.romusoft.rsapp.model;

/**
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsNotificationMessage {
	private String message;
	private String messageSource;

	/**
	 * the message
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * the message
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * The source of the message. The module that sent the message
	 * @return The source of the message
	 */
	public String getMessageSource() {
		return messageSource;
	}

	/**
	 * The source of the message
	 * @param messageSource The source of the message
	 */
	public void setMessageSource(String messageSource) {
		this.messageSource = messageSource;
	}

}
