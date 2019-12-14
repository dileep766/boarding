package com.ctl.activiti.helper;

import java.util.Map;

public class ProcessStatusHelper {

	public String getProcessStatus(String taksname, Map<String, Object> data) {
		String status="";
		switch (taksname) {
		case "Resource Request Form":
			status="IN PROGRESS";
			break;
		case "Resource Details":
		{
		String statusvalue=(String) data.get("status");	
		
		if(statusvalue.equals("rejected"))
		{
			status="REJECTED";
		}
		else
		{
			status="IN PROGRESS";
		}
		}
			System.out.println("Choice2 selected");
			break;
		case "Resource Status Approved":
			status="IN PROGRESS";
			break;
		case "Resource Information to PMO":
			status="IN PROGRESS";
			break;
		case "Refilling Req From PMO":
			status="IN PROGRESS";
			break;
		case "Resource Information":
			
		{
			String statusvalue=(String) data.get("statusbymanager");	
			
			if(statusvalue.equals("rejected"))
			{
				status="REJECTED";
			}
			else if(statusvalue.equals("approved"))
			{
				status="IN PROGRESS";
			}
			else 
			{
				status="ON-HOLD";
			}
			}
			break;
		case "Resource Information On Hold":
		{
			String statusvalue=(String) data.get("statusbymanager");	
			
			if(statusvalue.equals("rejected"))
			{
				status="REJECTED";
			}
			else if(statusvalue.equals("approved"))
			{
				status="IN PROGRESS";
			}
			
			}
			break;
		case "Status Change":
		{
			String statusvalue=(String) data.get("statusbymanager");	
			
			if(statusvalue.equals("rejected"))
			{
				status="REJECTED";
			}
			else if(statusvalue.equals("approved"))
			{
				status="IN PROGRESS";
			}
			else 
			{
				status="ON-HOLD";
			}
			}
			break;
		case "Date Of Joining":
		{
			
			String statusvalue=(String) data.get("joiningconfirmation");	
			
			if(statusvalue.equals("yes"))
			{
				status="ON BOARDED";
			}
			else if(statusvalue.equals("no"))
			{
				status="NO SHOW";
			}
		
			}
			break;
		case "Contract Closing Notification":
			{
			//contractstatus,yes,no
			String statusvalue=(String) data.get("contractstatus");	
			
			if(statusvalue.equals("yes"))
			{
				status="ON BOARDED";
			}
			else if(statusvalue.equals("no"))
			{
				status="CLOSED";
			}
		
			}
			break;
		}
		return status;

	}
}
