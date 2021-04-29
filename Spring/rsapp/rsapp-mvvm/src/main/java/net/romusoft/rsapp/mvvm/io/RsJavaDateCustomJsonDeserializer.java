package net.romusoft.rsapp.mvvm.io;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Deserialize a java data from json
 * It could be in various format depending on how it was serialize.
 * this method capture most of the serialization formats.
 * @author Emmanuel Romulus
 *
 */
public class RsJavaDateCustomJsonDeserializer extends JsonDeserializer<java.util.Date> {
	@SuppressWarnings("deprecation")
	@Override
	public java.util.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		String value = jsonParser.getText();
		try {

			java.util.Date newDate = null;
			long time = 0;
			String expression = "^([0-9]{10})";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(value);
			boolean matchFound = matcher.find();
			//
			// process long timestamp
			if (matchFound) {
				time = Long.valueOf(value);
				newDate = new Date(time);
				return newDate;
			}

			//
			// process sql dates. Otherwise, process normal dates
			expression = "^([0-9]{4}-[0-9]{2}-[0-9]{2})";
			pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(value);
			matchFound = matcher.find();
			if (matchFound) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				value = value.replace(" ", "T");
				newDate = format.parse(value);
			} else {
				newDate = new Date(value);
			}

			return newDate;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

}