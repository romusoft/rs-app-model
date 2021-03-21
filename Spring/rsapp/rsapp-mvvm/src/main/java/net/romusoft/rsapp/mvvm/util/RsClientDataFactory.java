package net.romusoft.rsapp.mvvm.util;

import net.romusoft.rsapp.mvvm.RsAbstractAuditableModel;
import net.romusoft.rsapp.mvvm.RsAbstractModel;
import net.romusoft.rsapp.mvvm.model.RsGenericViewModel;
import net.romusoft.rsapp.mvvm.model.RsViewModelItem;

/**
 * 
 * @author eromu_000
 *
 */
public class RsClientDataFactory {
	public static String METADATA_DATA_TYPE = "dataType";

	/**
	 * 
	 * @param emailAddress
	 * @return
	 */
	public static RsViewModelItem getData(String emailAddress) {
		RsViewModelItem data = new RsViewModelItem();
		data.setEmailAddress(emailAddress);

		return data;
	}

	/**
	 * create a record return it
	 * 
	 * @param id
	 * @param emailAddress
	 * @return
	 */
	public static RsViewModelItem getRecord(String id, String emailAddress) {
		return RsClientDataFactory.getRecord(null, id, emailAddress);

	}

	/**
	 * create a record and add it to the data list
	 * 
	 * @param data
	 * @param id
	 * @param emailAddress
	 * @return
	 */
	public static RsViewModelItem getRecord(RsGenericViewModel data, String id, String emailAddress) {
		RsViewModelItem record = new RsViewModelItem(id, emailAddress);
		if (data != null) {
			data.getModelItems().add(record);
		}
		return record;

	}

	/**
	 * 
	 * @param data
	 * @param model
	 * @return
	 */
	public static RsViewModelItem getRecord(RsGenericViewModel data, RsAbstractModel model) {
		RsViewModelItem record = new RsViewModelItem(model.getId() + "", "");

		if (model instanceof RsAbstractAuditableModel) {
			record.setComment(((RsAbstractAuditableModel) model).getComment());
		}
		record.setDescription(model.getDescription());
		record.getMetadata().put(RsClientDataFactory.METADATA_DATA_TYPE, model.getClass().getSimpleName());
		if (data != null) {
			data.getModelItems().add(record);
		}
		return record;

	}

}
