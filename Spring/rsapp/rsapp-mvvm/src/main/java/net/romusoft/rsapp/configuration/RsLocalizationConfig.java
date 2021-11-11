package net.romusoft.rsapp.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * configuration to that provides localization or languages that can be used in
 * an application
 * 
 * @author Emmanuel Romulus
 *
 */
@Configuration
public class RsLocalizationConfig implements WebMvcConfigurer {

	/**
	 * default locale US
	 * 
	 * @return LocaleResolver object
	 */
	@Bean
	public LocaleResolver xlocaleResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	/**
	 * bean for locale changed. The parameter is lang
	 * 
	 * @return LocaleChangeInterceptor object
	 */
	@Bean
	public LocaleChangeInterceptor xlocaleChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/**
	 * set the locale changed interceptor
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(xlocaleChangeInterceptor());
	}
}
