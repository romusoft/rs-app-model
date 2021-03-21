package net.romusoft.rsapp.mvvm;

import java.util.List;

/**
 * 
 * @author eromu_000
 *
 */
public interface IRsModuleMain extends IRsModule {
	/**
	 * Register a module object
	 * 
	 * @param module
	 */
	public void RegisterModule(IRsModule module);

	/**
	 * 
	 * @param module
	 */
	public void UnRegisterModule(IRsModule module);

	/**
	 * 
	 * @return
	 */
	public List<IRsModule> getRegisteredModules();
}
