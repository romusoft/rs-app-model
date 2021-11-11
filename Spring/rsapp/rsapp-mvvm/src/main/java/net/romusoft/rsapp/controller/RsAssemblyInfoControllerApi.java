package net.romusoft.rsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.romusoft.rsapp.enums.AssemblyInfoOptions;
import net.romusoft.rsapp.model.RsAssemblyInfo;
import net.romusoft.rsapp.mvvm.RsViewModelItem;

/**
 * api to get information about an application
 * 
 * @author Emmanuel Romulus
 *
 */
@RestController
@RequestMapping("api/rsapp/assembly-info")
public class RsAssemblyInfoControllerApi {

	@Autowired
	private RsAssemblyInfo assemblyInfo;

	/**
	 * get information about the application
	 * 
	 * @param option which information to return
	 * @return information about the application
	 */
	@RequestMapping
	public RsViewModelItem getAssemblyInfoData(@RequestParam(value = "option", required = false) String option) {
		RsViewModelItem item = new RsViewModelItem();

		if (option == null)
			option = "ALL";
		else {
			option = option.toUpperCase();
		}

		AssemblyInfoOptions targetOption = AssemblyInfoOptions.getValue(option);
		switch (targetOption) {
		case ALL:
			item.addMetadataValue(targetOption.getMetadataKey(), assemblyInfo);
			break;
		case VERSION:
			item.addMetadataValue(targetOption.getMetadataKey(), assemblyInfo.getVersion());
			break;
		case ENVIRONMENT:
			item.addMetadataValue(targetOption.getMetadataKey(), assemblyInfo.getEnvironment());
			break;
		default:
			item.addMetadataValue(targetOption.getMetadataKey(), "");
			break;
		}

		return item;
	}
}
