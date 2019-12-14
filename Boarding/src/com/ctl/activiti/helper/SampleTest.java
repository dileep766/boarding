package com.ctl.activiti.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import com.ctl.activiti.form.ResourceRequestProcessIntiationForm;

public class SampleTest {
public static void main(String[] args) throws ParseException {
	int i=10;
	int j=15;
	float f=j/(float)i;
	System.out.println(f);
	System.out.println("inside EndDateEventHelper===============@@@@@@@@@@@@@@@@@@");
	
	String startDate="05/03/2015"+" 08:00:00";
	
	DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.ENGLISH);
	DateFormat format2 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
	Date startDateObj=format.parse(startDate);
	Date endDateObj=  new DateTime(startDateObj).plusYears(1).toDate();
	
	//Date date = new Date(); // Or where ever you get it from
	Date noticeTriggerDateObj = new DateTime(endDateObj).minusDays(15).toDate();
	Date JoingTriggerDateObj = new DateTime(startDateObj).minusDays(1).toDate();
	Date JoinDateeveningObj = new DateTime(startDateObj).plusHours(9).toDate();
	Calendar cal = Calendar.getInstance();
	String currentDate=format.format(cal.getTime());
	 Calendar calendar = Calendar.getInstance();
     calendar.setTime(JoingTriggerDateObj) ;
     int dayofweek=calendar.get(Calendar.DAY_OF_WEEK);
     if(dayofweek==7)
     {
    	 JoingTriggerDateObj = new DateTime(JoingTriggerDateObj).minusDays(1).toDate(); 
     }
     else if(dayofweek==1)
     {
    	 JoingTriggerDateObj = new DateTime(JoingTriggerDateObj).minusDays(2).toDate();  
     }
	Date currentDateobj = format.parse(currentDate);
	long enddatesecs = (endDateObj.getTime()-currentDateobj.getTime()) / 1000;
	long startdatesecs = (startDateObj.getTime()-currentDateobj.getTime()) / 1000;
	long startdateevngsecs = (JoinDateeveningObj.getTime()-currentDateobj.getTime()) / 1000;
	long npsecs = (noticeTriggerDateObj.getTime()-currentDateobj.getTime()) / 1000;
	long joiningsecs = (JoingTriggerDateObj.getTime()-currentDateobj.getTime()) / 1000;
	System.out.println("start date  seconds==="+startdatesecs);
	System.out.println("start date evng seconds=="+startdateevngsecs);
	System.out.println("Joining seconds==="+joiningsecs);

}
}
