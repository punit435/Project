package com.enterio.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateUtil implements Serializable 
{
	private static final long serialVersionUID = -1075183019464865511L;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat dateFormatType = new SimpleDateFormat("dd-MM-yyyy");
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat dateFormatSQL = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dateTimeFormatSQL = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static SimpleDateFormat dbDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
	private static SimpleDateFormat calendarDateTimeFormat = new SimpleDateFormat("MM/dd/yyyy KK:mm a");
	private static SimpleDateFormat calendarFormat = new SimpleDateFormat("yyyy,MM,dd");

	private static String getDoubleDigits(int inputValue)
	{
		String returnValue = "";
		
		if(inputValue < 10)
		{
			returnValue = "0" + inputValue;
		}
		else
		{
			returnValue = "" + inputValue;
		}
		
		return returnValue;
	}
	
	public static Date addDays(Date date, int noOfDays)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, noOfDays);
		
		return cal.getTime();
	}
	
	public static Date getTodaysDate()
	{
		Date currentDate = null;
		String dateStr = "";
		String day = "";
		String month = "";
		int year = 0;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			int dd = cal.get(Calendar.DAY_OF_MONTH);
			day = getDoubleDigits(dd);
			
			int mm = cal.get(Calendar.MONTH) + 1;
			month = getDoubleDigits(mm);
			
			year = cal.get(Calendar.YEAR);
		}
		
		dateStr = day + "/" + month + "/" + year;
			
		currentDate = convertToDate(dateStr);
		
		return currentDate;
	}
	
	public static Date getCurrentDateTime()
	{
		Date currentDateTime = null;
		String dateTimeStr = "";
		String day = "";
		String month = "";
		int year = 0;
		String hour = "";
		String minute = "";
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			int dd = cal.get(Calendar.DAY_OF_MONTH);
			day = getDoubleDigits(dd);
			
			int mm = cal.get(Calendar.MONTH) + 1;
			month = getDoubleDigits(mm);
			
			year = cal.get(Calendar.YEAR);
			
			int hh = cal.get(Calendar.HOUR_OF_DAY);
			hour = getDoubleDigits(hh);
			
			int min = cal.get(Calendar.MINUTE);
			minute = getDoubleDigits(min);
		}
		
		dateTimeStr = day + "/" + month + "/" + year + " " + hour + ":" + minute + ":00";
			
		currentDateTime = convertToDateTime(dateTimeStr);
		
		return currentDateTime;
	}	
	
	public static int getCurrentYear()
	{
		int year = 0;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			year = cal.get(Calendar.YEAR);
		}
		
		return year;
	}

	public static int getCurrentMonth()
	{
		int month = 0;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			month = cal.get(Calendar.MONTH);
		}
		
		return month;
	}
	public static Date convertToDate(int year, int month, int day)
	{
		Date convertedDate = null;
		try 
		{
			String dateString = getDoubleDigits(day) + "/" + getDoubleDigits(month) + "/" + year;
			convertedDate = dateFormat.parse(dateString);
		}
		catch (ParseException pe) 
		{
		}
		
		return convertedDate;
	}
	
	public static Date convertToDate(String date)
	{
		Date convertedDate = null;
		try 
		{
			convertedDate = dateFormat.parse(date);
		}
		catch (ParseException pe) 
		{
		}
		
		return convertedDate;
	}

	public static Date convertToDateFormat(String date)
	{
		Date convertedDate = null;
		try 
		{
			convertedDate = dateFormatType.parse(date);
		}
		catch (ParseException pe) 
		{
		}
		
		return convertedDate;
	}
	
	public static Date convertToDateTime(String dateTime)
	{
		Date convertedDate = null;
		try 
		{
			convertedDate = dateTimeFormat.parse(dateTime);
		}
		catch (ParseException pe) 
		{
		}
		
		return convertedDate;
	}
	
	public static String convertToDateString(Date date)
	{
		String convertedDate = null;
		
		if(date != null) 
		{
			convertedDate = dateFormat.format(date);
		}
		else 
		{
		}
		
		return convertedDate;
	}

	public static String convertDatabaseDateToString(String date)
	{
		String convertedValue = null;
		
		if(date != null && date.length() > 0) 
		{
			String dateArray[] = date.split("-");
			if(dateArray != null && dateArray.length == 3)
			{
				convertedValue = dateArray[2] + "/" + dateArray[1] + "/" + dateArray[0];
			}
		}
		
		return convertedValue;
	}
	
	public static String convertToDateTimeString(Date date)
	{
		String convertedDate = null;
		
		if(date != null) 
		{
			convertedDate = dateTimeFormat.format(date);
		}
		else 
		{
		}
		
		return convertedDate;
	}
	
	public static String convertToSQLString(Date date)
	{
		String convertedDate = null;
		
		if(date != null)
		{
			convertedDate = dateFormatSQL.format(date);
		}
		else 
		{
		}
		
		return convertedDate;
	}
	
	public static int compareDates(Date date1, Date date2)
	{
//will return 0, if date1 and date2 are equal
//will return negative value, if date1 is before date2
//will return positive value, if date1 is after date2		
		return date1.compareTo(date2);
	}
	
	public static Date getFirstDateOfNextMonth()
	{
		Date nextMonthDate = null;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DATE, 1);
			nextMonthDate = cal.getTime();
		}
		
		return nextMonthDate;
	}	
	
	public static Date getFirstDateOfMonth(int year, int month)
	{
		Date firstDate = null;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			cal.set(Calendar.DATE, 1);
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.YEAR, year);
			firstDate = cal.getTime();
		}
		
		return firstDate;
	}
	
	public static Date getLastDateOfMonth(int year, int month)
	{
		Date lastDate = null;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			cal.set(Calendar.DATE, DateUtil.getDaysInMonth(year, month));
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.YEAR, year);
			lastDate = cal.getTime();
		}
		
		return lastDate;
	}
	
	public static int getDaysInMonth(int year, int month)
	{
		int daysInMonth = 0;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			cal.set(Calendar.DATE, 1);
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.YEAR, year);
			daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}		
		
		return daysInMonth;
	}	

	public static int getWeeksInMonth(int year, int month)
	{ 
		int weeksInMonth = 0;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			cal.set(Calendar.MONTH, month-1);
			cal.set(Calendar.YEAR, year);
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			weeksInMonth = cal.getActualMaximum(Calendar.WEEK_OF_MONTH); 
		}
		
	    return weeksInMonth;
	}
	
	public static Map<Integer, List<Date>> getWeeksOfMonth(int year, int month, int week)
	{ 
		Map<Integer, List<Date>> weekDateMap = null;
		
		Calendar cal = new GregorianCalendar();
		if(cal != null)
		{
			int weeksInMonth = DateUtil.getWeeksInMonth(year, month);
			if(weeksInMonth > 0)
			{
				weekDateMap = new HashMap<Integer, List<Date>>();
				cal.set(Calendar.DATE, 1);
				cal.set(Calendar.MONTH, month-1);
				cal.set(Calendar.YEAR, year);
				Date startDate = null;
				Date endDate = null;
				
				int count = 1;
				while(count <= weeksInMonth)				
				{
					List<Date> weekDates = new ArrayList<Date>();
					if(count == 1)
					{
						startDate = cal.getTime();
					}
					weekDates.add(startDate);

					int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
					cal.add(Calendar.DATE, (7 - dayOfWeek));
					endDate = cal.getTime();
					if(endDate.after(DateUtil.getLastDateOfMonth(year, month)))
					{
						endDate = DateUtil.getLastDateOfMonth(year, month);
					}
					weekDates.add(endDate);
					if(week == 0)
					{
						weekDateMap.put(count, weekDates);
					}
					else if(week == count)
					{
						weekDateMap.put(count, weekDates);
					}
					
					startDate = DateUtil.addDays(endDate, 1);
					count++;
				}
			}
		}
		
	    return weekDateMap;
	}
	
	public static List<Date> getDaysOfWeek(List<Date> weekStartEndDates)
	{ 
		List<Date> weekList = null;
		
		if(weekStartEndDates != null && weekStartEndDates.size() > 0)
		{
			Date startDate = weekStartEndDates.get(0);
			Date endDate = weekStartEndDates.get(1);
			weekList = new ArrayList<Date>();
			weekList.add(startDate);
			
			for(int i=1; ; i++)
			{
				Date weekDate = DateUtil.addDays(startDate, i);
				if(weekDate.compareTo(endDate) == 1)
				{
					break;
				}
				weekList.add(weekDate);
			}
		}
		
	    return weekList;
	}
	
	public static List<Date> getDaysOfMonth(int year, int month)
	{ 
		List<Date> weekList = null;
		int daysInMonth = DateUtil.getDaysInMonth(year, month);
		
		if(year > 0 && month > 0 && daysInMonth > 0)
		{
			Calendar cal = new GregorianCalendar();
			if(cal != null)
			{
				cal.set(Calendar.DATE, 1);
				cal.set(Calendar.MONTH, month-1);
				cal.set(Calendar.YEAR, year);
				Date startDate = cal.getTime();
				Date endDate = DateUtil.addDays(startDate, daysInMonth-1);
				weekList = new ArrayList<Date>();
				weekList.add(startDate);
			
				for(int i=1; ; i++)
				{
					Date weekDate = DateUtil.addDays(startDate, i);
					if(weekDate.compareTo(endDate) == 1)
					{
						break;
					}
					weekList.add(weekDate);
				}
			}
		}
		
	    return weekList;
	}
	
	public static Date convertToUserTimezone(Date date, int timezoneOffset)
	{
		Date convertedDate = null;
		if(date != null)
		{
			try 
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.SECOND, timezoneOffset);				
				convertedDate = cal.getTime(); 
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		return convertedDate;
	}
	
	public static Date convertToDBDateTime(String dateTime)
	{
		Date convertedDate = null;
		try 
		{
			convertedDate = dbDateTimeFormat.parse(dateTime);
		}
		catch (ParseException pe) 
		{
		}
		
		return convertedDate;
	}
	
	public static String convertToSQLDateTime(Date date)
	{
		String convertedDate = null;
		
		if(date != null)
		{
			convertedDate = dateTimeFormatSQL.format(date);
		}
		else 
		{
		}
		
		return convertedDate;
	}
    
	public static String convertToDateTimeFormat(Date date)
	{
		String convertedDate = null;
		
		if(date != null)
		{
			convertedDate = calendarDateTimeFormat.format(date);
		}
		else 
		{
		}
		
		return convertedDate;
	}

	public static String convertToCalendarFormat(Date date)
	{
		String convertedDate = null;
		
		if(date != null) 
		{
			convertedDate = calendarFormat.format(date);
		}
		return convertedDate;
	}
	
	public static String parse(String interviewTime) {
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		 interviewTime = sdf.toString();
		    Date date = null;
		   /* try {
		    	interviewTime = sdf.toString();
//		        date = sdf.parse(interviewTime);
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }*/
			return interviewTime;
	}

}