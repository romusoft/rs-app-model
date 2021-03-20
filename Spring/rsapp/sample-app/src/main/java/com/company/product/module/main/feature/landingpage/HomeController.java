package com.company.product.module.main.feature.landingpage;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	/**
	 * Basic home end points that display the current user email
	 * 
	 * @param model
	 * @param token
	 * @return
	 */
	@RequestMapping("/home")
	public String home(Model model, OAuth2AuthenticationToken token) {
		OAuth2User user = token != null ? token.getPrincipal() : null;
		if (user != null) {
			model.addAttribute("grant_type", user.getAuthorities());
			model.addAllAttributes(user.getAttributes());
		}

		System.out.println("Access Token Response ------------------------ " + user.getName());
		return "test";

	}

	/**
	 * After a successful authentication the user is redirected here
	 * 
	 * @param code
	 * @param model
	 * @param token
	 * @return
	 */
	@RequestMapping("/login/oauth2/code")
	public String authorizationCode(@RequestParam String code, Model model, OAuth2AuthenticationToken token) {

		OAuth2User user = token != null ? token.getPrincipal() : null;
		System.out.println("Access Token Response ------------------------ " + user.getName());
		return "redirect:home";
	}

}
