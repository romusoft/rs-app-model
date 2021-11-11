package net.romusoft.rsapp.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * convert boolean to Y and N
 * 
 * @author Emmanuel Romulus
 *
 */
@Converter
public class BooleanToUpper_y_and_n_converter implements AttributeConverter<Boolean, String> {

	/**
	 * 
	 */
	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return (attribute != null && attribute) ? "Y" : "N";
	}

	/**
	 * 
	 */
	@Override
	public Boolean convertToEntityAttribute(String dbData) {

		return dbData == null || dbData.equalsIgnoreCase("Y");
	}

}
