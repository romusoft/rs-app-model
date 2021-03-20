package com.company.product.core.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.azure.spring.autoconfigure.b2c.AADB2COidcLoginConfigurer;

/**
 * security configuration for azure AD B2C
 * 
 * @author eromu_000
 *
 */
@EnableWebSecurity
public class AADB2COidcLoginConfig extends WebSecurityConfigurerAdapter {

	private final AADB2COidcLoginConfigurer configurer;

	public AADB2COidcLoginConfig(AADB2COidcLoginConfigurer configurer) {
		this.configurer = configurer;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login/**").permitAll().anyRequest().authenticated().and().apply(configurer);

	}
}
