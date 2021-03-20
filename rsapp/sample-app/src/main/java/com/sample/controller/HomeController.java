package com.sample.controller;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.rsapp.mvvm.io.DTORepositoryUtil;
import com.rsapp.mvvm.model.RsTokenInfo;

@Controller
public class HomeController {

	@Autowired
	OAuth2AuthorizedClientService service;

	@Value("${azure.activedirectory.b2c.client-id}")
	String clientId;
	@Value("${azure.activedirectory.b2c.client-secret}")
	String clientSecret;

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

	@RequestMapping("/login/oauth2/code")
	public String authorizationCode(@RequestParam String code, Model model, OAuth2AuthenticationToken token) {
//		RestTemplate restTemplate = new RestTemplate();
//		String credentials = clientId + ":" + clientSecret;
//		String encodedCredentials = new String(Base64.getEncoder().encode(credentials.getBytes()));
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		headers.add("Authorization", "Basic " + encodedCredentials);
//
//		HttpEntity<String> request = new HttpEntity<String>(headers);
//		String access_token_url = "https://rscms.b2clogin.com/rscms.onmicrosoft.com/B2C_1_signupsignin1/oauth2/v2.0/token";
//		access_token_url += "?code=" + code;
//		access_token_url += "&grant_type=authorization_code";
//		access_token_url += "&redirect_uri=" + "https://localhost:5000/cms/login/oauth2/token";
//
//		ResponseEntity<String> response = restTemplate.exchange(access_token_url, HttpMethod.POST, request,
//				String.class);
//		String jsonData = response.getBody();
//
//		RsTokenInfo info = DTORepositoryUtil.convertJsonToPOJO(jsonData, RsTokenInfo.class).get(0);
//		service.loadAuthorizedClient(clientId, "romulus");
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if(auth!=null) {
//			
//		}

		OAuth2User user = token != null ? token.getPrincipal() : null;

		System.out.println("Access Token Response ------------------------ " + user.getName());
		return "redirect:home";
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public String authorizationCodexxxx(@RequestParam String code) {
		RestTemplate restTemplate = new RestTemplate();
		String credentials = clientId + ":" + clientSecret;
		String encodedCredentials = new String(Base64.getEncoder().encode(credentials.getBytes()));
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		String access_token_url = "https://rscms.b2clogin.com/rscms.onmicrosoft.com/B2C_1_signupsignin1/oauth2/v2.0/token";
		access_token_url += "?code=" + code;
		access_token_url += "&grant_type=authorization_code";
		access_token_url += "&redirect_uri=" + "https://localhost:5000/cms/login/oauth2/token";

		ResponseEntity<String> response = restTemplate.exchange(access_token_url, HttpMethod.POST, request,
				String.class);
		String jsonData = response.getBody();

		RsTokenInfo info = DTORepositoryUtil.convertJsonToPOJO(jsonData, RsTokenInfo.class).get(0);
		service.loadAuthorizedClient(clientId, "romulus");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {

		}

		System.out.println("Access Token Response ------------------------ " + info.getId_token());
		return "redirect:home";
	}

}
