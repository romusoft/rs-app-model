package com.rsapp.mvvm;

import org.springframework.beans.factory.annotation.Autowired;

import com.rsapp.mvvm.model.RsNotificationMessage;

/**
 * the definition of a module in the system
 * 
 * @author eromu_000
 *
 */
public abstract class RsAbstractModule implements IRsModule {

	@Autowired
	private IRsModuleMain mainModule;

	/**
	 * notify the system of important messages or events
	 */
	@Override
	public void notify(RsNotificationMessage message) {
		message.setMessageSource(this.getName());
		mainModule.onNotify(message);

		for (IRsModule module : mainModule.getRegisteredModules()) {
			if (module == this)
				continue; // do not call notify on self
			module.onNotify(message);
		}
	}

	/**
	 * notify the system of important messages or events. The message is intended
	 * for a particular module
	 */
	@Override
	public <T> void notify(RsNotificationMessage message, Class<T> targetModule) {

		message.setMessageSource(this.getName());
		mainModule.onNotify(message);

		for (IRsModule module : mainModule.getRegisteredModules()) {
			if (module == this)
				continue;// do not call notify on self
			if (module.getClass() != targetModule)
				continue;

			message.setMessageSource(this.getName());
			module.onNotify(message);
		}
	}
}
