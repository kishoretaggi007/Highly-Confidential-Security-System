package com.hcss.utill;

import java.util.Date;

public class DateUtil
{

	static String month[] = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	/** Creates a new instance of DateWrapper */
	public DateUtil()
	{

	}

	public static String parseDate(Date date)
	{
		int monthid = date.getMonth();
		String newdate = date.getDate() + "-" + month[monthid] + "-" + (date.getYear() + 1900);
		System.out.println("new date==" + newdate);
		return newdate;
	}

	public static String parseDate(String date)
	{
		int monthid = Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-")));
		String newdate = date.substring(0, date.indexOf("-")) + "-" + month[monthid - 1] + "-"
			+ (date.substring(date.lastIndexOf("-") + 1, date.length()));
		return newdate;
	}

	public static String parseDate(java.sql.Date date)
	{
		String olddate = date.toString();
		String newdate = olddate.substring(olddate.lastIndexOf("-") + 1, olddate.length()) + "-"
			+ olddate.substring(olddate.indexOf("-") + 1, olddate.lastIndexOf("-")) + "-"
			+ olddate.substring(0, olddate.indexOf("-"));
		return newdate;

	}
}
