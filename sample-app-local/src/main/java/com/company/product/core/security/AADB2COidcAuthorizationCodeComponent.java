package com.company.product.core.security;

import java.util.Arrays;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.romusoft.rsapp.mvvm.io.DTORepositoryUtil;
import net.romusoft.rsapp.mvvm.model.RsTokenInfo;

/**
 * security configuration for azure AD B2C
 * 
 * @author eromu_000
 *
 */
@Component
public class AADB2COidcAuthorizationCodeComponent {

	@Autowired
	OAuth2AuthorizedClientService service;

	@Value("${azure.activedirectory.b2c.client-id}")
	String clientId;
	@Value("${azure.activedirectory.b2c.client-secret}")
	String clientSecret;

	/**
	 * Given an authorization code from azure AD B2C, get the access token in order
	 * use to make api calls with token.
	 * 
	 * @param code
	 * @return
	 */
	public RsTokenInfo getAccessTokenFromAuthorizationCode(String code) {
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
		System.out.println("Access Token Response ------------------------ " + info.getId_token());
		return info;
	}

}
