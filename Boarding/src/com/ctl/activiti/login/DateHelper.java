package com.ctl.activiti.login;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class DateHelper {
	public static void main(String[] args) throws ParseException {
		String string = "22/02/2015 08:00:00";
		DateFormat format = new SimpleDateFormat("dd/MM/yy hh:mm:ss", Locale.ENGLISH);
		Date endDate = format.parse(string);
		
		 Calendar calendar = Calendar.getInstance();
	     calendar.setTime(endDate) ;
	     int i=calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(i);
	}
	
}
;