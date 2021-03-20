package com.rsapp.mvvm.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource("classpath:mvvm-configuration.properties") })
public class RsAppConfiguration {

	
}
