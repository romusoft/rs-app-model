package net.romusoft.rsapp.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * convert boolean to t and f
 * 
 * @author Emmanuel Romulus
 *
 */
@Converter
public class BooleanToLower_t_and_f_converter implements AttributeConverter<Boolean, String> {

	/**
	 * 
	 */
	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return (attribute != null && attribute) ? "t" : "f";
	}

	/**
	 * 
	 */
	@Override
	public Boolean convertToEntityAttribute(String dbData) {

		return dbData == null || dbData.equalsIgnoreCase("t");
	}

}