package net.romusoft.rsapp.mvvm;

import java.util.List;

/**
 * 
 * @author Emmanuel Romulus
 *
 */
public interface IRsModuleMain extends IRsModule {
	/**
	 * Register a module object
	 * 
	 * @param module module to register
	 */
	public void RegisterModule(IRsModule module);

	/**
	 * Remove a module from the list of registered modules
	 * 
	 * @param module the module to unregister
	 */
	public void UnRegisterModule(IRsModule module);

	/**
	 * the list of registered modules
	 * 
	 * @return the list of registered modules
	 */
	public List<IRsModule> getRegisteredModules();
}
