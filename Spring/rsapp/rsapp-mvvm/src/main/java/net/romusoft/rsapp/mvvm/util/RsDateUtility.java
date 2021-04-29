package net.romusoft.rsapp.mvvm.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author eromu_000
 *
 */
public class RsDateUtility {
	/**
	 * pacific time
	 */
	public static final String GMT_PLUS_7 = "Etc/GMT+7";
	/**
	 * 
	 */
	public static final String LOS_ANGELES = "America/Los_Angeles"; // pacific
	/**
	 * mountain time
	 */
	public static final String DENVER = "America/Denver"; // mountain
	/**
	 * central time
	 */
	public static final String RANKIN_INLET = "America/Rankin_Inlet"; // central
	/**
	 * eastern time
	 */
	public static final String NEW_YORK = "America/New_York"; // eastern

	/**
	 * convert a java.util date to a java.sql date
	 * 
	 * @param dateToConvert java.util date
	 * @return java.sql date
	 */
	public static java.sql.Date convertUtilDateToSQLDate(java.util.Date dateToConvert) {
		java.sql.Date convertedDate = null;
		if (dateToConvert != null) {
			java.util.Calendar cal = Calendar.getInstance();
			cal.setTime(dateToConvert);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			convertedDate = new java.sql.Date(cal.getTime().getTime());
		}
		return convertedDate;
	}

	/**
	 * compare date strings by month, day, and year
	 * 
	 * @param firstDate  first date
	 * @param secondDate second date
	 * @return int value such as: 1,0,-1
	 */
	public static int compareDatesByMonthDayAndYear(Date firstDate, Date secondDate) {
		// Handle cases where one of the dates is null
		if (firstDate == null) {
			if (secondDate == null) {
				return 0;
			} else {
				return -1;
			}
		} else if (secondDate == null) {
			return 1;
		}

		Calendar firstCalendar = Calendar.getInstance();
		firstCalendar.setTime(firstDate);
		Calendar secondCalendar = Calendar.getInstance();
		secondCalendar.setTime(secondDate);
		int firstYear = firstCalendar.get(Calendar.YEAR);
		int secondYear = secondCalendar.get(Calendar.YEAR);
		int firstDay = firstCalendar.get(Calendar.DAY_OF_YEAR);
		int secondDay = secondCalendar.get(Calendar.DAY_OF_YEAR);
		if (firstYear == secondYear) {
			return firstDay - secondDay;
		} else {
			return firstYear - secondYear;
		}
	}

	/**
	 * compare dates by month, day, and year
	 * 
	 * @param firstDateString  first date
	 * @param secondDateString second date
	 * @return int value such as: 1,0,-1
	 */
	public static int compareDatesByMonthDayAndYear(String firstDateString, String secondDateString) {
		// Handle cases where one of the dates is null
		if (firstDateString == null || firstDateString.equals("")) {
			if (secondDateString == null || secondDateString.equals("")) {
				return 0;
			} else {
				return -1;
			}
		} else if (secondDateString == null || secondDateString.equals("")) {
			return 1;
		}

		Date firstDate = getDateFromString(firstDateString);
		Date secondDate = getDateFromString(secondDateString);

		return compareDatesByMonthDayAndYear(firstDate, secondDate);
	}

	/**
	 * compare dates by timestamps
	 * 
	 * @param firstDate  first date
	 * @param secondDate second date
	 * @return int value such as: 1,0,-1
	 */
	public static int compareDatesByTimestamp(Date firstDate, Date secondDate) {
		// Handle cases where one of the dates is null
		if (firstDate == null) {
			if (secondDate == null) {
				return 0;
			} else {
				return -1;
			}
		} else if (secondDate == null) {
			return 1;
		}

		long firstTime = firstDate.getTime();
		long secondTime = secondDate.getTime();
		if (firstTime > secondTime) {
			return 1;
		} else if (firstTime < secondTime) {
			return -1;
		} else {
			return 0;
		}
	}

	/**
	 * get a date from string
	 * 
	 * @param dateString the string to parse
	 * @return a new date object
	 */
	public static Date getDateFromString(String dateString) {
		Date newDate = null;

		if (dateString != null && !dateString.equals("")) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy"); // yyyy-mm-dd 06/12/2015
				java.util.Date tempDate = dateFormat.parse(dateString);
				newDate = new Date(tempDate.getTime());
			} catch (Exception e) {
				newDate = null;
			}
		}

		return newDate;
	}

	/**
	 * get a calendar from string based on a dateformat
	 * 
	 * @param dateString date string
	 * @param dateFormat date format
	 * @return calendar
	 */
	public static Calendar getCalendarFromString(String dateString, SimpleDateFormat dateFormat) {
		Calendar tempCalendar = null;
		dateFormat.setLenient(false);

		if (dateString != null && !dateString.equals("")) {
			java.util.Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(dateString);
				tempCalendar = Calendar.getInstance();
				tempCalendar.setTime(parsedDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Calendar cal = new GregorianCalendar(tempCalendar.get(Calendar.YEAR), tempCalendar.get(Calendar.MONTH),
				tempCalendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(cal.toString());
		return cal;
	}

	/**
	 * parse an sql string date - dd-MMM-yyyy
	 * 
	 * @param dateString date string
	 * @return date
	 */
	public static Date getDateFromSQLString(String dateString) {
		Date newDate = null;

		if (dateString != null && !dateString.equals("")) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
				java.util.Date tempDate = dateFormat.parse(dateString);
				newDate = new Date(tempDate.getTime());
			} catch (Exception e) {
				newDate = null;
			}
		}

		return newDate;
	}

	/**
	 * whether a date string is valid
	 * 
	 * @param dateString date string
	 * @return whether a date string is valid
	 */
	public static boolean isValidDateString(String dateString) {
		boolean isValidDate = true;

		if (dateString != null) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				@SuppressWarnings("unused")
				java.util.Date testDate = dateFormat.parse(dateString);
			} catch (Exception e) {
				isValidDate = false;
			}
		} else {
			isValidDate = false;
		}

		return isValidDate;
	}

	/**
	 * whether a date string is valid
	 * 
	 * @param dateString date string
	 * @return whether a date string is valid
	 */
	public static boolean isValidDateStringIgnoreNull(String dateString) {
		if (dateString == null || dateString.equals("")) {
			return true;
		} else {
			return isValidDateString(dateString);
		}
	}

	/**
	 * date formatted as yyyy/MM/dd
	 * 
	 * @param date date
	 * @return date formatted as yyyy/MM/dd
	 */
	public static String renderDateAsSortableString(Date date) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			return dateFormat.format(date);
		}
	}

	/**
	 * date formatted as dd-MMM-yyyy
	 * 
	 * @param date date
	 * @return date formatted as dd-MMM-yyyy
	 */
	public static String renderDateAsSqlDateString(Date date) {
		if (date == null) {
			return "";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String dateString = dateFormat.format(date);
		return dateString;

	}

	/**
	 * date formatted as MM/dd/yyyy
	 * 
	 * @param date date
	 * @return date formatted as MM/dd/yyyy
	 */
	public static String renderDateAsString(Date date) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			return dateFormat.format(date);
		}
	}

	/**
	 * date formatted as MM-dd-yyyy
	 * 
	 * @param date date
	 * @return date formatted as MM-dd-yyyy
	 */
	public static String renderDateAsStringWithDashes(Date date) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
			return dateFormat.format(date);
		}
	}

	/**
	 * date formatted as MM/dd/yyyy hh:mm:ss a
	 * 
	 * @param date date
	 * @return date formatted as MM/dd/yyyy hh:mm:ss a
	 */
	public static String renderDateAsStringWithHoursAndMinutes(Timestamp date) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
			return dateFormat.format(date);
		}
	}

	/**
	 * get previous date
	 * 
	 * @param currentDate current date
	 * @return date
	 */
	public static Date getPreviousDate(Date currentDate) {
		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date previousDate = new Date(calendar.getTimeInMillis());

		return previousDate;
	}

	/**
	 * get next date
	 * 
	 * @param currentDate current date
	 * @return date
	 */
	public static Date getNextDate(Date currentDate) {
		// Use the Calendar class to add one day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.add(Calendar.DAY_OF_YEAR, +1);
		Date nextDate = new Date(calendar.getTimeInMillis());

		return nextDate;
	}

	/**
	 * whether two dates are equal
	 * 
	 * @param firstDate  first date
	 * @param secondDate second date
	 * @return whether two dates are equal
	 */
	public static boolean datesAreEqual(Date firstDate, Date secondDate) {
		Calendar firstCalendar = Calendar.getInstance();
		firstCalendar.setTime(firstDate);
		Calendar secondCalendar = Calendar.getInstance();
		secondCalendar.setTime(secondDate);
		if (firstCalendar.get(Calendar.YEAR) == secondCalendar.get(Calendar.YEAR)) {
			if (firstCalendar.get(Calendar.DAY_OF_YEAR) == secondCalendar.get(Calendar.DAY_OF_YEAR)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Whether a number of days for a date is in the future
	 * 
	 * @param testDate target date
	 * @param numDays  number of days
	 * @return whether a number of days for a date is in the future
	 */
	static public boolean isDateXDaysOrMoreInFuture(Date testDate, int numDays) {
		if (numDays < 1) {
			throw new RuntimeException(
					"The value of numDays passed to DateUtil.isDateXDaysOrMoreInFuture() must be greater than 0.");
		}
		Date currentDate = new Date(System.currentTimeMillis());

		if (numDays == 1) {
			return currentDate.before(testDate);
		}

		if (RsDateUtility.datesAreEqual(testDate, currentDate)) {
			return false;
		}

		long ONE_DAY = 1000 * 60 * 60 * 24;
		Date futureDate = new Date(System.currentTimeMillis() + (numDays * ONE_DAY));

		if (RsDateUtility.datesAreEqual(testDate, futureDate)) {
			return true;
		} else {
			return futureDate.before(testDate);
		}
	}

	/**
	 * Whether a number of days for a date is in the past
	 * 
	 * @param testDate target date
	 * @param numDays  number of days
	 * @return whether a number of days for a date is in the past
	 */
	public static boolean isDateXDaysOrMoreInPast(Date testDate, int numDays) {
		if (numDays < 1) {
			throw new RuntimeException(
					"The value of numDays passed to DateUtil.isDateXDaysOrMoreInPast() must be greater than 0.");
		}
		Date currentDate = new Date(System.currentTimeMillis());

		if (numDays == 1) {
			return currentDate.after(testDate);
		}

		if (RsDateUtility.datesAreEqual(testDate, currentDate)) {
			return false;
		}

		long ONE_DAY = 1000 * 60 * 60 * 24;
		Date pastDate = new Date(System.currentTimeMillis() - (numDays * ONE_DAY));

		if (RsDateUtility.datesAreEqual(testDate, pastDate)) {
			return true;
		} else {
			return testDate.before(pastDate);
		}
	}

	/**
	 * convert to ZonedDateTime a target time zone
	 * 
	 * @param zonedDateTime zoneddatetime
	 * @param targetZoneId  zone id
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToTimeZone(ZonedDateTime zonedDateTime, ZoneId targetZoneId) {
		/*
		 * get the instant of moment in the utc time line
		 */
		Instant instant = zonedDateTime.toInstant();
		ZonedDateTime datetime = instant.atZone(targetZoneId);
		return datetime;
	}

	/**
	 * convert to ZonedDateTime a target time zone
	 * 
	 * @param zonedDateTime  zoneddatetime
	 * @param targetZoneName target zone name
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToTimeZone(ZonedDateTime zonedDateTime, String targetZoneName) {
		/*
		 * get the instant of moment in the utc time line
		 */
		Instant instant = zonedDateTime.toInstant();
		ZoneId targetZoneId = ZoneId.of(targetZoneName);
		ZonedDateTime datetime = instant.atZone(targetZoneId);
		return datetime;
	}

	/**
	 * convert o calendar to a target time zone
	 * 
	 * @param calendar calendar
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToTimeZone(Calendar calendar) {

		ZoneId tempZoneId = calendar.getTimeZone().toZoneId();
		ZonedDateTime zonedDateTime = ZonedDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND), 0, tempZoneId);
		/*
		 * get the instant of moment in the utc time line
		 */
		Instant instant = zonedDateTime.toInstant();
		ZonedDateTime datetime = instant.atZone(tempZoneId);
		return datetime;
	}

	/**
	 * convert o calendar to a target time zone
	 * 
	 * @param calendar     calendar
	 * @param targetZoneId target zone id
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToTimeZone(Calendar calendar, ZoneId targetZoneId) {

		ZoneId tempZoneId = calendar.getTimeZone().toZoneId();
		ZonedDateTime zonedDateTime = ZonedDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE),
				calendar.get(Calendar.SECOND), 0, tempZoneId);
		/*
		 * get the instant of moment in the utc time line
		 */
		Instant instant = zonedDateTime.toInstant();
		ZonedDateTime datetime = instant.atZone(targetZoneId);
		return datetime;
	}

	/**
	 * convert a calendar to a target time zone
	 * 
	 * @param calendar       calendar
	 * @param targetZoneName target zone name
	 * @return zoneddatetime object
	 */
	public static ZonedDateTime convertToTimeZone(Calendar calendar, String targetZoneName) {

		ZoneId tempZoneId = calendar.getTimeZone().toZoneId();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1; // months start at 0 in calendar
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		ZonedDateTime zonedDateTime = ZonedDateTime.of(year, month, day, hour, minute, second, 0, tempZoneId);
		/*
		 * get the instant of moment in the utc time line
		 */
		Instant instant = zonedDateTime.toInstant();
		ZoneId targetZoneId = ZoneId.of(targetZoneName);
		ZonedDateTime datetime = instant.atZone(targetZoneId);
		return datetime;
	}

	/**
	 * convert to pacific time
	 * 
	 * @param zonedDateTime zoneddatetime
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToPacificTime(ZonedDateTime zonedDateTime) {

		ZoneId zoneId = ZoneId.of(LOS_ANGELES);
		return convertToTimeZone(zonedDateTime, zoneId);
	}

	/**
	 * convert to pacific time
	 * 
	 * @param calendar calendar
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToPacificTime(Calendar calendar) {
		/*
		 * convert to a pacific time zone
		 */
		ZoneId zoneId = ZoneId.of(GMT_PLUS_7);
		return convertToTimeZone(calendar, zoneId);
	}

	/**
	 * convert to mountain time
	 * 
	 * @param zonedDateTime zoned date time
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToMountainTime(ZonedDateTime zonedDateTime) {

		ZoneId zoneId = ZoneId.of(DENVER);
		return convertToTimeZone(zonedDateTime, zoneId);
	}

	/**
	 * convert to mountain time
	 * 
	 * @param calendar calendar
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToMountainTime(Calendar calendar) {
		ZoneId zoneId = ZoneId.of(DENVER);
		return convertToTimeZone(calendar, zoneId);
	}

	/**
	 * convert to central time
	 * 
	 * @param zonedDateTime zoneddatetime
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToCentralTime(ZonedDateTime zonedDateTime) {

		ZoneId zoneId = ZoneId.of(RANKIN_INLET);
		return convertToTimeZone(zonedDateTime, zoneId);
	}

	/**
	 * convert to central time
	 * 
	 * @param calendar calendar
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToCentralTime(Calendar calendar) {
		ZoneId zoneId = ZoneId.of(RANKIN_INLET);
		return convertToTimeZone(calendar, zoneId);
	}

	/**
	 * 
	 * convert zoneddatetime to eastern time zone date time
	 * 
	 * @param zonedDateTime zoneddatetime
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToEasternTime(ZonedDateTime zonedDateTime) {

		ZoneId zoneId = ZoneId.of(NEW_YORK);
		return convertToTimeZone(zonedDateTime, zoneId);
	}

	/**
	 * convert calendar to eastern time zone date time
	 * 
	 * @param calendar the calendar to convert
	 * @return zoneddatetime
	 */
	public static ZonedDateTime convertToEasternTime(Calendar calendar) {
		ZoneId zoneId = ZoneId.of(NEW_YORK);
		return convertToTimeZone(calendar, zoneId);
	}
}
