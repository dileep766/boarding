package com.ctl.activiti.form;

public class ResourcDetailsForm {
	public ResourcDetailsForm()
	{
		
	}
public ResourcDetailsForm(String processid, String statusofprocess,
			String ctlimanager, String ctlidivision, String ctlidirector,
			String vendor, String vendormail, String startDate, String enddate,
			String resourceName, String firstname, String lastname,
			String middlename, String skills, String designation,
			String company, String dateofbirth, String gender,
			String mobilenumber, String previousorganization,
			String totalyearsofexperience, String degree,
			String specialization, String institute, String yearofpassing) {
		super();
		this.processid = processid;
		this.statusofprocess = statusofprocess;
		this.ctlimanager = ctlimanager;
		this.ctlidivision = ctlidivision;
		this.ctlidirector = ctlidirector;
		this.vendor = vendor;
		this.vendormail = vendormail;
		this.startDate = startDate;
		this.enddate = enddate;
		ResourceName = resourceName;
		this.firstname = firstname;
		this.lastname = lastname;
		this.middlename = middlename;
		this.skills = skills;
		this.designation = designation;
		this.company = company;
		this.dateofbirth = dateofbirth;
		this.gender = gender;
		this.mobilenumber = mobilenumber;
		this.previousorganization = previousorganization;
		this.totalyearsofexperience = totalyearsofexperience;
		this.degree = degree;
		this.specialization = specialization;
		this.institute = institute;
		this.yearofpassing = yearofpassing;
	}

String processid;
String statusofprocess;
String ctlimanager;
String ctlidivision;
String ctlidirector;
String vendor; 
String vendormail;
String startDate;
String enddate; 
String ResourceName;
String firstname; 
String lastname;
String middlename;
String skills;
String designation;
String company; 
String dateofbirth;
String gender;
String mobilenumber;
String previousorganization;
String totalyearsofexperience;
String degree;
String specialization;
String institute;
String yearofpassing;@Override
public String toString() {
	return "ResourcDetailsForm [processid=" + processid + ", statusofprocess="
			+ statusofprocess + ", ctlimanager=" + ctlimanager
			+ ", ctlidivision=" + ctlidivision + ", ctlidirector="
			+ ctlidirector + ", vendor=" + vendor + ", vendormail="
			+ vendormail + ", startDate=" + startDate + ", enddate=" + enddate
			+ ", ResourceName=" + ResourceName + ", firstname=" + firstname
			+ ", lastname=" + lastname + ", middlename=" + middlename
			+ ", skills=" + skills + ", designation=" + designation
			+ ", company=" + company + ", dateofbirth=" + dateofbirth
			+ ", gender=" + gender + ", mobilenumber=" + mobilenumber
			+ ", previousorganization=" + previousorganization
			+ ", totalyearsofexperience=" + totalyearsofexperience
			+ ", degree=" + degree + ", specialization=" + specialization
			+ ", institute=" + institute + ", yearofpassing=" + yearofpassing
			+ "]";
}

/*String initiator;
String division; */
public String getDateofbirth() {
	return dateofbirth;
}
public void setDateofbirth(String dateofbirth) {
	this.dateofbirth = dateofbirth;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getMobilenumber() {
	return mobilenumber;
}
public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
}
public String getPreviousorganization() {
	return previousorganization;
}
public void setPreviousorganization(String previousorganization) {
	this.previousorganization = previousorganization;
}
public String getTotalyearsofexperience() {
	return totalyearsofexperience;
}
public void setTotalyearsofexperience(String totalyearsofexperience) {
	this.totalyearsofexperience = totalyearsofexperience;
}
public String getDegree() {
	return degree;
}
public void setDegree(String degree) {
	this.degree = degree;
}
public String getSpecialization() {
	return specialization;
}
public void setSpecialization(String specialization) {
	this.specialization = specialization;
}
public String getInstitute() {
	return institute;
}
public void setInstitute(String institute) {
	this.institute = institute;
}
public String getYearofpassing() {
	return yearofpassing;
}
public void setYearofpassing(String yearofpassing) {
	this.yearofpassing = yearofpassing;
}
public String getEnddate() {
	return enddate;
}
public void setEnddate(String enddate) {
	this.enddate = enddate;
}
public String getStatusofprocess() {
	return statusofprocess;
}
public void setStatusofprocess(String statusofprocess) {
	this.statusofprocess = statusofprocess;
}

public String getCtlimanager() {
	return ctlimanager;
}
public void setCtlimanager(String ctlimanager) {
	this.ctlimanager = ctlimanager;
}
public String getCtlidirector() {
	return ctlidirector;
}
public void setCtlidirector(String ctlidirector) {
	this.ctlidirector = ctlidirector;
}
public String getVendormail() {
	return vendormail;
}
public void setVendormail(String vendormail) {
	this.vendormail = vendormail;
}
public String getCtlidivision() {
	return ctlidivision;
}
public void setCtlidivision(String ctlidivision) {
	this.ctlidivision = ctlidivision;
}
public String getProcessid() {
	return processid;
}
public void setProcessid(String processid) {
	this.processid = processid;
}
/*public String getInitiator() {
	return initiator;
}
public void setInitiator(String initiator) {
	this.initiator = initiator;
}*/
public String getVendor() {
	return vendor;
}
public void setVendor(String vendor) {
	this.vendor = vendor;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}

public String getResourceName() {
	return ResourceName;
}
public void setResourceName(String resourceName) {
	ResourceName = resourceName;
}
public String getFirstname() {
	return firstname;
}
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
public String getLastname() {
	return lastname;
}
public void setLastname(String lastname) {
	this.lastname = lastname;
}
public String getMiddlename() {
	return middlename;
}
public void setMiddlename(String middlename) {
	this.middlename = middlename;
}
public String getSkills() {
	return skills;
}
public void setSkills(String skills) {
	this.skills = skills;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}
/*public String getDivision() {
	return division;
}
public void setDivision(String division) {
	this.division = division;
}*/
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}


}
