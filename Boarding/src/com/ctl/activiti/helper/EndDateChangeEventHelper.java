package com.ctl.activiti.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.ctl.activiti.form.ResourceRequestProcessIntiationForm;

public class EndDateChangeEventHelper implements JavaDelegate {
	Logger logger=Logger.getLogger(EndDateEventHelper.class);
	@Autowired
	JDBCTemplateHelper JDBCTemplate;
	
	public void execute(DelegateExecution execution) {
		try {
			logger.info("inside EndDateEventHelper===============@@@@@@@@@@@@@@@@@@");
		
				String startDate=(String) execution.getVariable("startDate")+" 11:00:00";
				String endDate=(String) execution.getVariable("enddate")+" 11:00:00";
				ResourceRequestProcessIntiationForm  resourceform = (ResourceRequestProcessIntiationForm) execution.getVariable("resourceform");
				resourceform.setStartDate(startDate);	
				execution.setVariable("resourceform",resourceform);
				DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.ENGLISH);
				DateFormat format2 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
				Calendar cal = Calendar.getInstance();
				String currentDate=format.format(cal.getTime());
				Calendar calendar = Calendar.getInstance();
				Date startDateObj=format.parse(startDate);
				Date endDateObj= format.parse(endDate);
				Date ondedaybeforeendDateObj=  new DateTime(endDateObj).minusDays(1).toDate();
				//Date date = new Date(); // Or where ever you get it from
				Date noticeTriggerDateObj = new DateTime(endDateObj).minusDays(15).toDate();
				Date JoingTriggerDateObj = new DateTime(startDateObj).minusDays(1).toDate();
				Date JoinDateeveningObj = new DateTime(startDateObj).plusHours(6).toDate();
				
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
			     calendar.setTime(ondedaybeforeendDateObj) ;
			     dayofweek=calendar.get(Calendar.DAY_OF_WEEK);
			     if(dayofweek==7)
			     {
			    	 ondedaybeforeendDateObj = new DateTime(ondedaybeforeendDateObj).minusDays(1).toDate(); 
			     }
			     else if(dayofweek==1)
			     {
			    	 ondedaybeforeendDateObj = new DateTime(ondedaybeforeendDateObj).minusDays(2).toDate();  
			     }
				Date currentDateobj = format.parse(currentDate);
				long onedaybeforeenddatesecs = (ondedaybeforeendDateObj.getTime()-currentDateobj.getTime()) / 1000;
				long enddatesecs = (endDateObj.getTime()-currentDateobj.getTime()) / 1000;
				long startdatesecs = (startDateObj.getTime()-currentDateobj.getTime()) / 1000;
				long startdateevngsecs = (JoinDateeveningObj.getTime()-currentDateobj.getTime()) / 1000;
				long npsecs = (noticeTriggerDateObj.getTime()-currentDateobj.getTime()) / 1000;
				long joiningsecs = (JoingTriggerDateObj.getTime()-currentDateobj.getTime()) / 1000;
				if (joiningsecs<=0l)
				{
					joiningsecs=100l;	
				}
				long hours = (npsecs / 3600);
				logger.info("start date==========="+startDate);
				logger.info("End date ======"+format.format(endDateObj));
				logger.info("current date===="+format.format(currentDateobj));
				//logger.info("time left for triggering deboarding notfication"+hours);
				execution.setVariable("npsecs",""+npsecs);
				execution.setVariable("enddate",""+format2.format(endDateObj));
				execution.setVariable("joiningsecs",""+joiningsecs);
				if(npsecs<0l)
				{
					execution.setVariable("npsecs",50l);
					
				}
				if(startdateevngsecs<0l)
				{
					execution.setVariable("startdateevngsecs",""+100l);
					execution.setVariable("startdateevngsecsreminder",""+200l);
					
				}
				else
				{
					execution.setVariable("startdateevngsecs",""+startdateevngsecs);
					execution.setVariable("startdateevngsecsreminder",""+(startdateevngsecs+(18*60*60)));
				}
				if(onedaybeforeenddatesecs<=0)
				{
					onedaybeforeenddatesecs=200l;
				}
				if(enddatesecs<=0)
				{
					enddatesecs=200l;
				}
				if(startdatesecs<=0)
				{
					startdatesecs=200l;
				}
				execution.setVariable("beforeonedayofendday",""+onedaybeforeenddatesecs);
				execution.setVariable("enddatesecsreminder",""+(enddatesecs+(24*60*60)));
				execution.setVariable("startdatesecs",""+startdatesecs);
				execution.setVariable("enddatesecs",""+enddatesecs);
			
				long statuschangesec=startdatesecs-120l;
				execution.setVariable("statuschangesecs",statuschangesec);
				HashMap<String,String> hrpmomap=PropertyFileReader.getHRPMOinfo();
				String hrcuid=hrpmomap.get("hr");
				String hrmailid="";
				String hrprimary="";
				if(hrcuid.contains(","))
				{
					String hrcuidarray[]=hrcuid.split(",");
					hrprimary=hrcuidarray[0];
					for(String cuid:hrcuidarray)
					{
						hrmailid=hrmailid+cuid+"@centurylink.com,";
					}
					hrmailid=hrmailid.substring(0,hrmailid.lastIndexOf(","));
				}
				else
				{
					hrmailid=hrcuid+"@centurylink.com";
					hrprimary=hrcuid;
				}
				String pmocuid=hrpmomap.get("pmo");
				String pmomailid="";
				String pmoprimary="";
				if(pmocuid.contains(","))
				{
					String pmocuidarray[]=pmocuid.split(",");
					pmoprimary=pmocuidarray[0];
					for(String pmoid:pmocuidarray)
					{
						pmomailid=pmomailid+pmoid+"@centurylink.com,";
					}
					pmomailid=pmomailid.substring(0,pmomailid.lastIndexOf(","));
				}
				else
				{
					pmomailid=pmocuid+"@centurylink.com";
					pmoprimary=pmocuid;
				}
				logger.info("hr cuid===="+hrpmomap.get("hr"));
				logger.info("pmo cuid======="+hrpmomap.get("pmo"));
				execution.setVariable("hr",hrcuid);
				execution.setVariable("pmo",pmocuid);
				execution.setVariable("hrmailid",hrmailid);
				execution.setVariable("pmomailid",pmomailid);
				execution.setVariable("hrprimary",hrprimary);
				execution.setVariable("pmoprimary",pmoprimary);
		} catch (Exception e) {
			
			e.printStackTrace();
logger.error("exception==="+e);
		}
	
		
	}

}
