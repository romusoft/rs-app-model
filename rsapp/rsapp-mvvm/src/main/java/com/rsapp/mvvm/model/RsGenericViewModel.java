package com.rsapp.mvvm.model;

import org.springframework.ui.Model;

import com.rsapp.mvvm.RsAbstractViewModel;

/**
 * 
 * @author eromu_000
 *
 */
public class RsGenericViewModel extends RsAbstractViewModel {

	public RsGenericViewModel() {
	}

	/**
	 * 
	 * @param model
	 */
	public RsGenericViewModel(Model model) {
		super(model);
	}
}
