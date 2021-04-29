package net.romusoft.rsapp.mvvm.io;

import java.io.IOException;
import java.sql.Timestamp;
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
 * Allow accurate deserialization of timestamp
 * 
 * @author Romulus
 *
 */
public class RsTimestampCustomJsonDeserializer extends JsonDeserializer<Timestamp> {
	@SuppressWarnings("deprecation")
	@Override
	public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {

		String value = jsonParser.getText();
		try {
			Timestamp newTimestamp = null;
			long time = 0;
			String expression = "^([0-9]{10})";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(value);
			boolean matchFound = matcher.find();
			//
			// process long timestamp
			if (matchFound) {
				time = Long.valueOf(value);
				newTimestamp = new Timestamp(time);
				return newTimestamp;
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
				java.util.Date newDate = format.parse(value);
				newTimestamp = new Timestamp(newDate.getTime());
			} else {
				time = Date.parse(value);
				newTimestamp = new Timestamp(time);
			}

			return newTimestamp;

		} catch (

		ParseException e) {
			throw new RuntimeException(e);
		}

	}

}