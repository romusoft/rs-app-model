package com.rsapp.mvvm;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Extends to be able to access a registered module. The extending class may
 * contain all services used in the system contains all services
 * 
 * @author eromu_000
 *
 */
public abstract class RsAbstractController {

	@Autowired
	protected IRsModuleMain mainModule;

	/**
	 * Get a module from from the registered modules
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getModule(Class<T> clazz) {

		for (IRsModule module : mainModule.getRegisteredModules()) {
			if (module.getClass() == clazz)
				return (T) module;
		}
		return null;

	}
}
