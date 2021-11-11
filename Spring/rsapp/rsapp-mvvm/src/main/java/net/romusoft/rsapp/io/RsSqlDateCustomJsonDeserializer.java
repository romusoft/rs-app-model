package net.romusoft.rsapp.io;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * this is a custom json serializer for sql date to make sure that the date does
 * not change using a timezone
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsSqlDateCustomJsonDeserializer extends JsonDeserializer<java.sql.Date> {
	@Override
	public java.sql.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {

		String value = jsonParser.getText();
		try {

			java.sql.Date newSqlDate = null;
			long time = 0;
			String expression = "^([0-9]{10})";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(value);
			boolean matchFound = matcher.find();
			//
			// process long timestamp
			if (matchFound) {
				time = Long.valueOf(value);
				newSqlDate = new java.sql.Date(time);
				return newSqlDate;
			}

			// If there is no time
			expression = "^([0-9]{4}-[0-9]{2}-[0-9]{2})$";
			pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(value);
			matchFound = matcher.find();
			if (matchFound) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date newDate = format.parse(value);
				newSqlDate = new java.sql.Date(newDate.getTime());
				return newSqlDate;
			}

			// process sql dates with time. Otherwise, process normal dates
			expression = "^([0-9]{4}-[0-9]{2}-[0-9]{2})";
			pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(value);
			matchFound = matcher.find();
			if (matchFound) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				value = value.replace(" ", "T");
				java.util.Date newDate = format.parse(value);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(newDate.getTime());
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);

				newSqlDate = new java.sql.Date(cal.getTimeInMillis());
			} else {

				@SuppressWarnings("deprecation")
				java.util.Date newDate = new Date(value);
				newSqlDate = new java.sql.Date(newDate.getTime());
			}

			return newSqlDate;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

	}

}