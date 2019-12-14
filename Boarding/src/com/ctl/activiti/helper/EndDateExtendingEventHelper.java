package com.ctl.activiti.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.identity.User;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ctl.activiti.form.EmployeeDetails;
import com.ctl.activiti.form.Employeedata;
import com.ctl.activiti.form.ResourceRequestProcessIntiationForm;
import com.ctl.activiti.form.VendorUserInfo;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;


@Component
public class EndDateExtendingEventHelper implements JavaDelegate {
	@Autowired
	JDBCTemplateHelper JDBCTemplate;
	Logger logger=Logger.getLogger(EndDateExtendingEventHelper.class);
	public void execute(DelegateExecution execution) {
		try {
			logger.info("inside EndDateEventHelper===============@@@@@@@@@@@@@@@@@@");
		
				String endDate=(String) execution.getVariable("endDate")+" 11:00:00";
				
				DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.ENGLISH);
				DateFormat format2 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
				DateFormat format3 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
				Date endDateObj=format.parse(endDate);
			
				
				//Date date = new Date(); // Or where ever you get it from
				Date noticeTriggerDateObj = new DateTime(endDateObj).minusDays(15).toDate();
				Date ondedaybeforeendDateObj=  new DateTime(endDateObj).minusDays(1).toDate();
				Calendar cal = Calendar.getInstance();
				String currentDate=format.format(cal.getTime());
			
				Date currentDateobj = format.parse(currentDate);
				long enddatesecs = (endDateObj.getTime()-currentDateobj.getTime()) / 1000;
				
				long npsecs = (noticeTriggerDateObj.getTime()-currentDateobj.getTime()) / 1000;
				long onedaybeforeenddatesecs = (ondedaybeforeendDateObj.getTime()-currentDateobj.getTime()) / 1000;
				long hours = (npsecs / 3600);
				logger.info("End date ======"+format.format(endDateObj));
				logger.info("current date===="+format.format(currentDateobj));
				logger.info("time left for triggering deboarding notfication"+hours);
				if(npsecs<=0)
					execution.setVariable("npsecs",""+300);
				else
				execution.setVariable("npsecs",""+npsecs);
				if(onedaybeforeenddatesecs<=0)
				{
					onedaybeforeenddatesecs=200l;
				}
				if(enddatesecs<=0)
				{
					enddatesecs=200l;
				}
				execution.setVariable("flagforenddatechange","false");
				execution.setVariable("enddate",""+format3.format(endDateObj));
				execution.setVariable("endDate",""+format3.format(endDateObj));
				execution.setVariable("enddatesecs",""+enddatesecs);
				execution.setVariable("beforeonedayofendday",""+onedaybeforeenddatesecs);
				execution.setVariable("enddatesecsreminder",""+(enddatesecs+(24*60*60)));
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
