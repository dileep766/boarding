package com.ctl.activiti.helper;

import java.util.HashMap;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctl.activiti.form.EmployeeDetails;
import com.ctl.activiti.form.Employeedata;
import com.ctl.activiti.form.ResourceRequestProcessIntiationForm;
import com.ctl.activiti.form.VendorUserInfo;


@Component
public class ResourceRequestHelper implements JavaDelegate {
	@Autowired
	JDBCTemplateHelper JDBCTemplate;
	Logger logger=Logger.getLogger(ResourceRequestHelper.class);
	public void execute(DelegateExecution execution) {
		try {
			
			logger.info(execution.getVariable("myname")+"inside ResourceRequestHelper===============@@@@@@@@@@@@@@@@@@"+execution.getClass());
			
				ResourceRequestProcessIntiationForm  resourceform = new ResourceRequestProcessIntiationForm();
				resourceform.setResourcetype(((String) execution.getVariable("resourcetype")));
				resourceform.setAdditionalDetails(((String) execution.getVariable("additionalDetails")));
				resourceform.setApprover(((String) execution.getVariable("vendor")));
				resourceform.setEndDate(((String) execution.getVariable("endDate")));
				resourceform.setNumberOfPeople(((String) execution.getVariable("numberOfPeople")));
				resourceform.setSkillSet(((String) execution.getVariable("skillSet")));
				resourceform.setStartDate(((String) execution.getVariable("startDate")));
				//resourceform.setVendors(((String) execution.getVariable("vendors")));
				resourceform.setYearsOfExperience(((String) execution.getVariable("yearsOfExperience")));
				execution.setVariable("resourceform", resourceform);
				EmployeeDetails details = new EmployeeDetails();
				Employeedata employeedata=  details.getEmployeeDetails(((String) execution.getVariable("initiator")));
				VendorUserInfo vendoruserinfo = JDBCHelper.getVendorInfo((String) execution.getVariable("vendor"));	
			
				execution.setVariable("processid","CRR "+execution.getProcessInstanceId());
				execution.setVariable("vendoruserinfo",vendoruserinfo);
				execution.setVariable("employeedata",employeedata);
				execution.setVariable("ctlimanager",employeedata.getName());
				execution.setVariable("ctlidirector",employeedata.getDirectorName());
				execution.setVariable("ctlidivision",employeedata.getDirectorDivision());
				execution.setVariable("vendormail",vendoruserinfo.getMail());
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
