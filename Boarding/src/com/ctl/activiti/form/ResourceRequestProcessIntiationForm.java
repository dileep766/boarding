package com.ctl.activiti.form;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class ResourceRequestProcessIntiationForm implements Serializable{
	@Override
	public String toString() {
		return "ResourceRequestProcessIntiationForm [approver=" + approver
				+ ", numberOfPeople=" + numberOfPeople + ", vendors=" + vendors
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", yearsOfExperience=" + yearsOfExperience + ", skillSet="
				+ skillSet + ", additionalDetails=" + additionalDetails
				+ ", resourcetype=" + resourcetype + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	public ResourceRequestProcessIntiationForm() {
		
	}
	
	String approver;
	public ResourceRequestProcessIntiationForm(String approver,
			String numberOfPeople, String vendors, String startDate,
			String endDate, String yearsOfExperience, String skillSet,
			String additionalDetails, String resourcetype) {
		super();
		this.approver = approver;
		this.numberOfPeople = numberOfPeople;
		this.vendors = vendors;
		this.startDate = startDate;
		this.endDate = endDate;
		this.yearsOfExperience = yearsOfExperience;
		this.skillSet = skillSet;
		this.additionalDetails = additionalDetails;
		this.resourcetype = resourcetype;
	};
	String numberOfPeople;
	String vendors;
	String startDate;
	String endDate;
	String yearsOfExperience;
	String skillSet;
	String additionalDetails;
	String resourcetype;
	public String getResourcetype() {
		return resourcetype;
	}
	public void setResourcetype(String resourcetype) {
		this.resourcetype = resourcetype;
	}
	
	public String getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	public String getAdditionalDetails() {
		return additionalDetails;
	}
	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	
	public String getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(String numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	
	
	public String getVendors() {
		return vendors;
	}
	public void setVendors(String vendors) {
		this.vendors = vendors;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Map<String,Object> getAllData()
	{
		Map<String,Object> map=new HashMap<String,Object>();
		

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date startDateformat = dateFormat.parse(startDate);
			Date endDateformat = dateFormat.parse(endDate);
		
		
		
		
		map.put("approver", approver);
		map.put("numberOfPeople", numberOfPeople);
		map.put("vendors", vendors);
		map.put("startDate", startDateformat);
		map.put("endDate",endDateformat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
