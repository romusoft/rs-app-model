package com.company.product.module.main.feature.activity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.romusoft.rsapp.mvvm.model.RsDefaultViewModel;

@Controller
public class ActivityController {

	@GetMapping("activity-list")
	public String activityList(Model model) {

		RsDefaultViewModel viewModel = new RsDefaultViewModel(model, VActivityList.class);
		return viewModel.getViewPath();

	}

	@GetMapping("activity-detail")
	public String activityDetail(Model model) {

		ActivityViewModel viewModel = new ActivityViewModel(model, VActivityDetail.class);
		return viewModel.getViewPath();

	}

}
