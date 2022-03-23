package com.qa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 *  @author  Ojasvi Pandey
 */

public class DateUtils {
	private static Calendar cal;
	private static SimpleDateFormat dateFormatter;

	public static String getCurrentDate() {
		cal = Calendar.getInstance();
		dateFormatter = new SimpleDateFormat("MM/dd/YYYY");
		String startDate = dateFormatter.format(cal.getTime()).toString();
		return startDate;
	}

	/**
	 * @return Return current Date & Time as format
	 */
	public static Date getCurrentDateTime() {
		cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * @return Return current Date & Time as format
	 */

	public static Date getDateDifferenceFromCurrentDate(int days) {
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * @return Return current Date & Time as format
	 */
	public static String getCurrentDateTimeAsFormat(String format) {
		cal = Calendar.getInstance();
		dateFormatter = new SimpleDateFormat(format);
		String startDate = dateFormatter.format(cal.getTime());
		return startDate;
	}

	/**
	 * @return Return current Date & Time as format
	 */
	public static Date getDateTimeAsFormat(String date, String format) {
		dateFormatter = new SimpleDateFormat(format);
		try {
			Date startDate = dateFormatter.parse(date);
			return startDate;
		} catch (ParseException parseException) {
			return null;
		}
	}

	/**
	 * Method to return the 1st Jan date of current year.
	 */
	public static String getFirstDateOfYear() {
		cal = Calendar.getInstance();
		cal.set(Calendar.DATE,1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		String startDate = dateFormatter.format(cal.getTime()).toString();
		return startDate;
	}

	public static String getPastDate(Integer days) {
		cal = Calendar.getInstance();
		dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		cal.add(Calendar.DATE, days);
		String pastDate = dateFormatter.format(cal.getTime());
		return pastDate;
	}

	/**
	 * This method convert the ISO date to US date format (MM/dd/YYYY).
	 * It only returns the date not time.
	 * @param date - Date String that you want to convert
	 * @return convertedDate - US converted date
	 */
	public static String convertISOToUSDateFormat(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String convertedDate = null;
		try {
			Date parsedDate = dateFormat.parse(date);
			dateFormatter = new SimpleDateFormat("MM/dd/YYYY");
			convertedDate = dateFormatter.format(parsedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	/**
	 * Compare the two epoch date and return the result
	 * @param epochDate1 - First epochdate
	 * @param epochDate2 - Second epochdate
	 * @return int value of comparison return 0 if they are equal, 1 if
	 * epochDate1 is greater than epochDate2 and -1 if epochDate1 less
	 * than epochDate1
	 */
	public static int compareEpochDates(long epochDate1, long epochDate2) {
		Date date1 = new Date(epochDate1);
		Date date2 = new Date(epochDate2);
		return date1.compareTo(date2);
	}

	public static long getDaysDifferenceBetweenDates(String startDate, String endDate)
		throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date firstDate = sdf.parse(startDate);
		Date secondDate = sdf.parse(endDate);
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}

	/**
	 * Get day difference from today
	 * @param days - Number of days, Negative for past days
	 * @return - date
	 */
	public static String getDateWithDayDifference(Integer days) {
		cal = Calendar.getInstance();
		dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		cal.add(Calendar.DATE, days);
		String pastDate = dateFormatter.format(cal.getTime());
		return pastDate;
	}

	/**
	 * Convert the date string into date format
	 * @param dateStr - Date String to pass
	 * @return - Return the converted date
	 */
	public static Date getDateWithDateString(String dateStr) {
		Date convertedDate = null;
		dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		try {
			convertedDate = dateFormatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}

	public static String convertMilliSecToISO(Long milliSec) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date formattedDateTime = new Date(milliSec);
		return sdf.format(formattedDateTime);
	}
}
