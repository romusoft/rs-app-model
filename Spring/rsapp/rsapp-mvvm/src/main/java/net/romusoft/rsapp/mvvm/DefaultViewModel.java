package net.romusoft.rsapp.mvvm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

/**
 * A generic default viewmodel with a default view. The default view can be
 * overwritten in two ways: use the construct that take clazz that implements
 * IRsView or call generateViewInstance after the default viewmodel is
 * initialized
 * 
 * @author eromu_000
 *
 */
public class DefaultViewModel extends RsAbstractViewModel2<DefaultView> {

	private List<String> data = new ArrayList<String>();

	/**
	 * empty constructor. Use for API data that does not require a view or spring
	 * model
	 */
	public DefaultViewModel() {
	}

	/**
	 * 
	 * @param model
	 */
	public DefaultViewModel(Model model) {
		super(model);
	}

	/**
	 * 
	 * @param <TView>
	 * @param model
	 * @param clazz
	 */
	public <TView extends IRsView> DefaultViewModel(Model model, Class<TView> clazz) {
		super(model, clazz);
	}

	/*************************************************************/
	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}
