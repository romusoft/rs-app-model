package com.company.product.module.main.feature.landingpage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for the landing page
 * 
 * @author eromu_000
 *
 */
@RestController
public class HomeControllerApi {

	@RequestMapping("/")
	public String home() {
		return "Hello world";

	}

}
