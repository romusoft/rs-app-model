package net.romusoft.rsapp.viewmodel;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import net.romusoft.rsapp.mvvm.RsViewModelBase;

/**
 * viewmodel registry for the current user session Automaticate generate or
 * retrieve a view model in a session
 * 
 * @author romulus
 *
 */
public class ViewModelRegistry implements Serializable {

	/**
	 * serializable
	 */
	private static final long serialVersionUID = 2064222093928843905L;
	/**
	 * 
	 */
	public static final String KEY = "rsMvvmViewModelRegistry";
	/**
	 * the storage for the registry
	 */
	@SuppressWarnings("rawtypes")
	private final List<RsViewModelBase> viewModels = new ArrayList<RsViewModelBase>();

	/**
	 * get a viewmodel from the registry
	 * 
	 * @param <VM>  the target viewmodel by type
	 * @param clazz the class
	 * @param model model attribute to use and store atttribute data for mvc
	 * @return the vm
	 */
	@SuppressWarnings("rawtypes")
	public <VM extends RsViewModelBase> VM getViewModel(Class<VM> clazz, Model model) {

		@SuppressWarnings("unchecked")
		VM viewModel = (VM) viewModels.stream().filter(temp -> temp.getClass() == clazz).findAny().orElse(null);
		if (viewModel != null) {
			viewModel.setModel(model);
			return viewModel;
		}

		//
		// create a new instance of the view model and add it
		try {
			if (model != null) {
				viewModel = clazz.getConstructor(Model.class).newInstance(model);
			} else {
				viewModel = clazz.getConstructor().newInstance();
			}
			viewModels.add(viewModel);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return viewModel;
	}

	/**
	 * get a viewmodel from the registry
	 * 
	 * @param <VM>  the target viewmodel by type
	 * @param clazz the class
	 * @return return the vm
	 */
	@SuppressWarnings("rawtypes")
	public <VM extends RsViewModelBase> VM getViewModel(Class<VM> clazz) {

		return this.getViewModel(clazz, null);

	}
}
