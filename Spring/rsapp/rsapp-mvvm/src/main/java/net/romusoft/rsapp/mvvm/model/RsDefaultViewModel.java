package net.romusoft.rsapp.mvvm.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import net.romusoft.rsapp.mvvm.IRsView;
import net.romusoft.rsapp.mvvm.RsAbstractViewModel;

/**
 * A generic default viewmodel with a default view. The default view can be
 * overwritten in two ways: use the construct that take clazz that implements
 * IRsView or call generateViewInstance after the default viewmodel is
 * initialized
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsDefaultViewModel extends RsAbstractViewModel<RsDefaultView> {

	private List<String> data = new ArrayList<String>();

	/**
	 * empty constructor. Use for API data that does not require a view or spring
	 * model
	 */
	public RsDefaultViewModel() {
	}

	/**
	 * initialize with the spring mvc model
	 * 
	 * @param model the spring mvc model
	 */
	public RsDefaultViewModel(Model model) {
		super(model);
	}

	/**
	 * initialize with a view
	 * 
	 * @param <TView> the view to return
	 * @param model   the spring mvc model
	 * @param clazz   the target class to use when instantiating the view
	 */
	public <TView extends IRsView> RsDefaultViewModel(Model model, Class<TView> clazz) {
		super(model, clazz);
	}

	/*************************************************************/

	/**
	 * a simple list of strings for the view data
	 * 
	 * @return a simple list of strings
	 */
	public List<String> getData() {
		return data;
	}

	/**
	 * a simple list of strings
	 * 
	 * @param data a simple list of strings
	 */
	public void setData(List<String> data) {
		this.data = data;
	}

}
