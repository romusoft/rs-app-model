package net.romusoft.rsapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Mvvm properties configurations
 * 
 * @author Emmanuel Romulus
 *
 */
@Configuration
@PropertySources({ @PropertySource("classpath:mvvm-configuration.properties") })
public class RsAppConfiguration {

}
