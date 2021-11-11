package net.romusoft.rsapp.viewmodel;

import org.springframework.ui.Model;

import net.romusoft.rsapp.mvvm.IRsView;
import net.romusoft.rsapp.mvvm.RsViewModelBase;
import net.romusoft.rsapp.view.RsDefaultView;

/**
 * A generic default viewmodel with a default view. The default view can be
 * overwritten in two ways: use the construct that take clazz that implements
 * IRsView or call generateViewInstance after the default viewmodel is
 * initialized
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsGenericViewModel extends RsViewModelBase<RsDefaultView> {


	/**
	 * initialize with a view
	 * 
	 * @param <TView> the view to return
	 * @param model   the spring mvc model
	 * @param clazz   the target class to use when instantiating the view
	 */
	public <TView extends IRsView> RsGenericViewModel(Model model, Class<TView> clazz) {
		super(model, clazz);
	}

	/**
	 * initialize with a view
	 * 
	 * @param <TView> the view to return
	 * @param clazz   the target class to use when instantiating the view
	 */
	public <TView extends IRsView> RsGenericViewModel(Class<TView> clazz) {
		super(clazz);
	}

	@Override
	public void updateLocalData() {

	}

}
