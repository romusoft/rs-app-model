package com.company.product.module.main.feature.activity;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.product.module.main.MainModule;

@RequestMapping(ActivityControllerApi.BASE_URL)
@RestController
public class ActivityControllerApi {

	public static final String BASE_URL = MainModule.API_CONTEXT_PATH + "/activities";
	
	@RequestMapping
	public ActivityViewModel home(Model model) {
		
		ActivityViewModel viewModel =new ActivityViewModel(model, VActivityDetail.class);
		
		return viewModel;
	}
}
