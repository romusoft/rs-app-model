package com.rsapp.autoconfigure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.rsapp.autoconfigure.properties.RsAssemblyInfoProperties;
import com.rsapp.autoconfigure.properties.RsCdnCssProperties;
import com.rsapp.autoconfigure.properties.RsCdnJsProperties;
import com.rsapp.mvvm.model.RsAppViewModel;
import com.rsapp.mvvm.model.RsAssemblyInfo;

/**
 * Configure beans based on resource properties {RsCdnJsProperties,
 * RsCdnCssProperties} for class RsCdnViewModel
 * 
 * @author eromu_000
 *
 */
@Configuration
@ConditionalOnClass(RsAppViewModel.class)
@EnableConfigurationProperties(value = { RsCdnJsProperties.class, RsCdnCssProperties.class,
		RsAssemblyInfoProperties.class })

@ComponentScan({ "com.rsapp.autoconfigure", "com.rsapp.mvvm" })
public class RsAppAutoConfiguration {

	@Autowired
	private RsCdnJsProperties cdnJsProperties;
	@Autowired
	private RsCdnCssProperties cdnCssProperties;
	@Autowired
	private RsAssemblyInfoProperties assemblyInfoProperties;

	/**
	 * configure the cdn bean
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RsAppViewModel cdnViewModel() {

		RsAppViewModel viewModel = new RsAppViewModel();
		/*
		 * Cdn for js files
		 */
		String jqueryJs = cdnJsProperties.getJquery() == null ? "" : cdnJsProperties.getJquery();
		String bootstrapJs = cdnJsProperties.getBootstrap() == null ? "" : cdnJsProperties.getBootstrap();
		String datatablesJs = cdnJsProperties.getDatatables() == null ? "" : cdnJsProperties.getDatatables();
		String kendouiJs = cdnJsProperties.getKendoui() == null ? "" : cdnJsProperties.getKendoui();
		String wpuicoreJs = cdnJsProperties.getRsuicore() == null ? "" : cdnJsProperties.getRsuicore();
		viewModel.setJqueryJs(jqueryJs);
		viewModel.setBootstrapJs(bootstrapJs);
		viewModel.setDatatablesJs(datatablesJs);
		viewModel.setKendouiJs(kendouiJs);
		viewModel.setWpuicoreCss(wpuicoreJs);
		/*
		 * Cdn for css files
		 */
		String bootstrapCss = cdnCssProperties.getBootstrap() == null ? "" : cdnCssProperties.getBootstrap();
		String datatablesCss = cdnJsProperties.getDatatables() == null ? "" : cdnCssProperties.getDatatables();
		String themeCss = cdnCssProperties.getTheme() == null ? "" : cdnCssProperties.getTheme();
		String wpuicoreCss = cdnCssProperties.getWpuicore() == null ? "" : cdnCssProperties.getWpuicore();
		viewModel.setBootstrapCss(bootstrapCss);
		viewModel.setDatatableCss(datatablesCss);
		viewModel.setThemeCss(themeCss);
		viewModel.setWpuicoreCss(wpuicoreCss);

		return viewModel;
	}

	/**
	 * configure the assemblyinfo bean
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public RsAssemblyInfo rsAssemblyInfo() {
		RsAssemblyInfo assemblyInfo = new RsAssemblyInfo();

		String environment = assemblyInfoProperties.getEnvironment() == null ? ""
				: assemblyInfoProperties.getEnvironment();
		String version = assemblyInfoProperties.getVersion() == null ? "" : assemblyInfoProperties.getVersion();
		String applicationTitle = assemblyInfoProperties.getApplicationTitle() == null ? ""
				: assemblyInfoProperties.getApplicationTitle();
		String applicationName = assemblyInfoProperties.getApplicationName() == null ? ""
				: assemblyInfoProperties.getApplicationName();
		String propertyFileDirectory = assemblyInfoProperties.getPropertyFileDirectory() == null ? ""
				: assemblyInfoProperties.getPropertyFileDirectory();
		String applicationFilename = assemblyInfoProperties.getApplicationFilename() == null ? ""
				: assemblyInfoProperties.getApplicationFilename();
		String applicationFileTitle = assemblyInfoProperties.getApplicationFileTitle() == null ? ""
				: assemblyInfoProperties.getApplicationFileTitle();

		assemblyInfo.setEnvironment(environment);
		assemblyInfo.setVersion(version);
		assemblyInfo.setApplicationTitle(applicationTitle);
		assemblyInfo.setApplicationName(applicationName);
		assemblyInfo.setPropertyFileDirectory(propertyFileDirectory);
		assemblyInfo.setApplicationFilename(applicationFilename);
		assemblyInfo.setApplicationFileTitle(applicationFileTitle);

		return assemblyInfo;

	}
}
