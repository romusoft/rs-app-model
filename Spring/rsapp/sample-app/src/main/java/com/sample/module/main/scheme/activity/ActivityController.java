package com.sample.module.main.scheme.activity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.romusoft.rsapp.mvvm.DefaultViewModel;

@Controller
public class ActivityController {

	@GetMapping("activity-list")
	public String activityList(Model model) {

		DefaultViewModel viewModel = new DefaultViewModel(model, VActivityList.class);
		return viewModel.getViewPath();

	}

	@GetMapping("activity-detail")
	public String activityDetail(Model model) {

		ActivityViewModel viewModel = new ActivityViewModel(model, VActivityDetail.class);
		return viewModel.getViewPath();

	}

}
