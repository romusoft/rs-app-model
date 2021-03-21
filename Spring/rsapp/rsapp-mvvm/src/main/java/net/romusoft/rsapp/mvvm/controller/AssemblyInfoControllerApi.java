package net.romusoft.rsapp.mvvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.romusoft.rsapp.mvvm.model.RsAssemblyInfo;
import net.romusoft.rsapp.mvvm.model.RsViewModelItem;

@RestController
@RequestMapping("api/rsapp/assembly-info")
public class AssemblyInfoControllerApi {

	@Autowired
	private RsAssemblyInfo assemblyInfo;

	@RequestMapping
	public RsViewModelItem getAssemblyInfoData(@RequestParam(value = "option", required = false) String option) {
		RsViewModelItem item = new RsViewModelItem();

		if (option == null)
			option = "ALL";
		else {
			option = option.toUpperCase();
		}

		switch (option) {
		case "ALL":
			item.addMetadataValue("assemblyInfo", assemblyInfo);
			break;
		case "VERSION":
			item.addMetadataValue("version", assemblyInfo.getVersion());
			break;
		case "ENVIRONMENT":
			item.addMetadataValue("environment", assemblyInfo.getEnvironment());
			break;
		default:
			item.addMetadataValue("default", "");
			break;
		}

		return item;
	}
}
