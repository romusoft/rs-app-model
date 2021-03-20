package com.romusoft.rsapp.mvvm;

import java.util.ArrayList;
import java.util.List;

import com.romusoft.rsapp.mvvm.model.RsNotificationMessage;

/**
 * implementation of the IRsModuleMain.
 * 
 * @Note onNotify method should be implemented in client class to receive
 *       notification.
 * 
 * @author eromu_000
 *
 */
public abstract class RsAbstractModuleMain implements IRsModuleMain {

	//
	// list of registered modules
	private final List<IRsModule> registeredModules = new ArrayList<IRsModule>();

	/**
	 * notify modules of a message. They pick it up with their onNotify method
	 */
	@Override
	public void notify(RsNotificationMessage message) {

		for (IRsModule module : this.registeredModules) {
			if (module == this)
				continue; // do not call notify on self
			module.onNotify(message);
		}
	}

	/**
	 * notify a specific mdoule of a message. They pick it up with their onNotify
	 * method
	 */
	@Override
	public <T> void notify(RsNotificationMessage message, Class<T> targetModule) {

		for (IRsModule module : this.registeredModules) {
			if (module == this)
				continue;// do not call notify on self
			if (module.getClass() != targetModule)
				continue;
			module.onNotify(message);
		}
	}

	/**
	 * Register a module. All modules should be registered in the application
	 */
	@Override
	public void RegisterModule(IRsModule module) {
		if (module.getClass() == this.getClass())
			return;
		for (IRsModule target : this.registeredModules) {
			if (target.getClass() == module.getClass())
				return;
		}
		//
		// save to add the module
		this.registeredModules.add(module);
	}

	/**
	 * unregister a module
	 */
	@Override
	public void UnRegisterModule(IRsModule module) {
		for (int i = 0; i < this.registeredModules.size(); i++) {
			IRsModule target = this.registeredModules.get(i);
			if (target.getClass() == module.getClass())
				this.registeredModules.remove(i);
		}

	}

	/**
	 * get the list of registered modules
	 */
	@Override
	public List<IRsModule> getRegisteredModules() {
		return this.registeredModules;
	}
}
