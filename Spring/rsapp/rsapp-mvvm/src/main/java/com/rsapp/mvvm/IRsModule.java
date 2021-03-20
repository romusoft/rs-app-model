package com.rsapp.mvvm;

import com.rsapp.mvvm.model.RsNotificationMessage;

/**
 * 
 * @author eromu_000
 *
 */
public interface IRsModule {

	public String getName();

	public void init();

	/**
	 * notify that something interesting happened
	 * 
	 * @param message
	 */
	public void notify(RsNotificationMessage message);

	/**
	 * target a specific module to send the message to
	 * 
	 * @param <T>
	 * @param message
	 * @param targetModule
	 */
	public <T> void notify(RsNotificationMessage message, Class<T> targetModule);

	/**
	 * receiving notification of interesting events
	 * 
	 * @param message
	 */
	public void onNotify(RsNotificationMessage message);

}
