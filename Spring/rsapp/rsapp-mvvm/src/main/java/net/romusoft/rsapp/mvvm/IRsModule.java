package net.romusoft.rsapp.mvvm;

import net.romusoft.rsapp.mvvm.model.RsNotificationMessage;

/**
 * A module is major unit in the application. At its top level, it consists of
 * models, repositories, and features. A feature a package consisting of a
 * service, an MVC controller, an API controller, a viewModel, and one or more
 * views. A module may have multiple views. A described in the IRsView
 * interface.
 * 
 * @author Emmanuel Romulus
 *
 */
public interface IRsModule {

	/**
	 * the name of the module
	 * 
	 * @return the name of the module
	 */
	public String getName();

	/**
	 * initialize a module
	 */
	public void init();

	/**
	 * notify that something interesting happened
	 * 
	 * @param message the message to notify subscribers
	 */
	public void notify(RsNotificationMessage message);

	/**
	 * target a specific module to send the message to
	 * 
	 * @param <T>          the generic type of module
	 * @param message      the message to notify
	 * @param targetModule the module that should receive the notification
	 */
	public <T> void notify(RsNotificationMessage message, Class<T> targetModule);

	/**
	 * receiving notification of interesting events
	 * 
	 * @param message the received message that was published
	 */
	public void onNotify(RsNotificationMessage message);

}
