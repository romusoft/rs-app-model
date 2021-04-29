package net.romusoft.rsapp.mvvm;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Extends to be able to access a registered module. The extending class may
 * contain all services used in the system contains all services
 * 
 * @author Emmanuel Romulus
 *
 */
public abstract class RsAbstractController {

	@Autowired
	protected IRsModuleMain mainModule;

	/**
	 * Get a module from from the registered modules
	 * 
	 * @param <T>   the generic target type
	 * @param clazz the target data type
	 * @return return the module that was registered
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
