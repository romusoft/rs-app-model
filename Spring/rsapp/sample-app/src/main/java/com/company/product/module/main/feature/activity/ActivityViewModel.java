package com.company.product.module.main.feature.activity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import com.romusoft.rsapp.mvvm.IRsView;
import com.romusoft.rsapp.mvvm.RsAbstractViewModel2;

public class ActivityViewModel extends RsAbstractViewModel2<VActivityList> {

	private List<String> data = new ArrayList<String>();

	/**
	 * empty constructor. Use for API data that does not require a view or spring
	 * model
	 */
	public ActivityViewModel() {
	}

	/**
	 * 
	 * @param model
	 */
	public ActivityViewModel(Model model) {
		super(model);
	}

	/**
	 * 
	 * @param <TView>
	 * @param model
	 * @param clazz
	 */
	public <TView extends IRsView> ActivityViewModel(Model model, Class<TView> clazz) {
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
