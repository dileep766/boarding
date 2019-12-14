package com.ctl.activiti.login;


 
import java.util.Date;
import java.util.Locale;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;

import com.ctl.activiti.form.ResourceRequestProcessIntiationForm;
 
public class GetCurrentDateTime {
  public static void main(String[] args) throws IOException,Exception {
 
	  String startDate="28/02/2015 08:00:00";
		
	
		DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.ENGLISH);
		DateFormat format2 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
		Date startDateObj=format.parse(startDate);
		Date endDateObj=  new DateTime(startDateObj).plusYears(1).toDate();
		Date onedaybefore=  new DateTime(startDateObj).minusDays(1).toDate();
		
		//Date date = new Date(); // Or where ever you get it from
		Date noticeTriggerDateObj = new DateTime(endDateObj).minusDays(15).toDate();
		Date JoingTriggerDateObj = new DateTime(startDateObj).minusDays(1).toDate();
		Calendar cal = Calendar.getInstance();
		String currentDate=format.format(cal.getTime());
	
		Date currentDateobj = format.parse(currentDate);
		System.out.println("before oneday of start date==========="+format.format(JoingTriggerDateObj));
		System.out.println("start date==========="+format.format(startDateObj));
		System.out.println("End date ======"+format.format(endDateObj));
		System.out.println("current date===="+format.format(currentDateobj));
		System.out.println(endDateObj.getTime());
		long enddatesecs = (endDateObj.getTime()-currentDateobj.getTime()) / 1000;
		long startdatesecs = (startDateObj.getTime()-currentDateobj.getTime()) / 1000;
		long npsecs = (noticeTriggerDateObj.getTime()-currentDateobj.getTime()) / 1000;
		long joiningsecs = (JoingTriggerDateObj.getTime()-currentDateobj.getTime()) / 1000;
		long hours = (npsecs / 3600);
		System.out.println("time left for triggering deboarding notfication"+hours);
 
  }
}