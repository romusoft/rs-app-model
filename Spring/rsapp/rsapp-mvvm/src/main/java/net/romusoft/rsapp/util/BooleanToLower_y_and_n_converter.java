package net.romusoft.rsapp.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * convert boolean to y and y
 * 
 * @author Emmanuel Romulus
 *
 */
@Converter
public class BooleanToLower_y_and_n_converter implements AttributeConverter<Boolean, String> {

	/**
	 * 
	 */
	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return (attribute != null && attribute) ? "y" : "n";
	}

	/**
	 * 
	 */
	@Override
	public Boolean convertToEntityAttribute(String dbData) {

		return dbData == null || dbData.equalsIgnoreCase("y");
	}

}
