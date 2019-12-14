package com.ctl.activiti.form;

import java.io.Serializable;


public class Employeedata implements Serializable
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Employeedata()
	{
		
	}
	
  public Employeedata(String name, String cuid, int emp_id, String dOJ,
		String terminationDate, String jobtitle, String division,
		String empType, String immSupCuid, String immSupName,
		String immSupDesignation, String immSupDivision, String immSupEmpType,
		String managerCuid, String managerName, String managerDesignation,
		String managerDivision, String managerEmpType, String directorCuid,
		String directorName, String directorDesignation,
		String directorDivision, String directorEmpType,
		String currentLocation, String transferDate) {
	super();
	
	this.name = name;
	this.cuid = cuid;
	this.emp_id = emp_id;
	DOJ = dOJ;
	this.terminationDate = terminationDate;
	this.jobtitle = jobtitle;
	this.division = division;
	this.empType = empType;
	this.immSupCuid = immSupCuid;
	this.immSupName = immSupName;
	this.immSupDesignation = immSupDesignation;
	this.immSupDivision = immSupDivision;
	this.immSupEmpType = immSupEmpType;
	this.managerCuid = managerCuid;
	this.managerName = managerName;
	this.managerDesignation = managerDesignation;
	this.managerDivision = managerDivision;
	this.managerEmpType = managerEmpType;
	this.directorCuid = directorCuid;
	this.directorName = directorName;
	this.directorDesignation = directorDesignation;
	this.directorDivision = directorDivision;
	this.directorEmpType = directorEmpType;
	this.currentLocation = currentLocation;
	this.transferDate = transferDate;
}
  @Override
public String toString() {
	return "Employeedata [name=" + name + ", cuid=" + cuid + ", emp_id="
			+ emp_id + ", DOJ=" + DOJ + ", terminationDate=" + terminationDate
			+ ", jobtitle=" + jobtitle + ", division=" + division
			+ ", empType=" + empType + ", immSupCuid=" + immSupCuid
			+ ", immSupName=" + immSupName + ", immSupDesignation="
			+ immSupDesignation + ", immSupDivision=" + immSupDivision
			+ ", immSupEmpType=" + immSupEmpType + ", managerCuid="
			+ managerCuid + ", managerName=" + managerName
			+ ", managerDesignation=" + managerDesignation
			+ ", managerDivision=" + managerDivision + ", managerEmpType="
			+ managerEmpType + ", directorCuid=" + directorCuid
			+ ", directorName=" + directorName + ", directorDesignation="
			+ directorDesignation + ", directorDivision=" + directorDivision
			+ ", directorEmpType=" + directorEmpType + ", currentLocation="
			+ currentLocation + ", transferDate=" + transferDate + "]";
}
private String name;
private String cuid;
  private int emp_id;
  private String DOJ;
  private String terminationDate;
  private String jobtitle;
  private String division;
  private String empType;
  private String immSupCuid;
  private String immSupName;
  private String immSupDesignation;
  private String immSupDivision;
  private String immSupEmpType;
  private String managerCuid;
  private String managerName;
  private String managerDesignation;
  private String managerDivision;
  private String managerEmpType;
  private String directorCuid;
  private String directorName;
  private String directorDesignation;
  private String directorDivision;
  private String directorEmpType;
  private String currentLocation;
  private String transferDate;
  
  public String getCurrentLocation()
  {
    return this.currentLocation;
  }
  
  public void setCurrentLocation(String currentLocation)
  {
    this.currentLocation = currentLocation;
  }
  
  public String getTransferDate()
  {
    return this.transferDate;
  }
  
  public void setTransferDate(String transferDate)
  {
    this.transferDate = transferDate;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getCuid()
  {
    return this.cuid;
  }
  
  public void setCuid(String cuid)
  {
    this.cuid = cuid;
  }
  
  public int getEmp_id()
  {
    return this.emp_id;
  }
  
  public void setEmp_id(int emp_id)
  {
    this.emp_id = emp_id;
  }
  
  public String getDOJ()
  {
    return this.DOJ;
  }
  
  public void setDOJ(String doj)
  {
    this.DOJ = doj;
  }
  
  public String getTerminationDate()
  {
    return this.terminationDate;
  }
  
  public void setTerminationDate(String terminationDate)
  {
    this.terminationDate = terminationDate;
  }
  
  public String getJobtitle()
  {
    return this.jobtitle;
  }
  
  public void setJobtitle(String jobtitle)
  {
    this.jobtitle = jobtitle;
  }
  
  public String getDivision()
  {
    return this.division;
  }
  
  public void setDivision(String division)
  {
    this.division = division;
  }
  
  public String getEmpType()
  {
    return this.empType;
  }
  
  public void setEmpType(String empType)
  {
    this.empType = empType;
  }
  
  public String getImmSupCuid()
  {
    return this.immSupCuid;
  }
  
  public void setImmSupCuid(String immSupCuid)
  {
    this.immSupCuid = immSupCuid;
  }
  
  public String getImmSupName()
  {
    return this.immSupName;
  }
  
  public void setImmSupName(String immSupName)
  {
    this.immSupName = immSupName;
  }
  
  public String getImmSupDesignation()
  {
    return this.immSupDesignation;
  }
  
  public void setImmSupDesignation(String immSupDesignation)
  {
    this.immSupDesignation = immSupDesignation;
  }
  
  public String getImmSupDivision()
  {
    return this.immSupDivision;
  }
  
  public void setImmSupDivision(String immSupDivision)
  {
    this.immSupDivision = immSupDivision;
  }
  
  public String getImmSupEmpType()
  {
    return this.immSupEmpType;
  }
  
  public void setImmSupEmpType(String immSupEmpType)
  {
    this.immSupEmpType = immSupEmpType;
  }
  
  public String getManagerCuid()
  {
    return this.managerCuid;
  }
  
  public void setManagerCuid(String managerCuid)
  {
    this.managerCuid = managerCuid;
  }
  
  public String getManagerName()
  {
    return this.managerName;
  }
  
  public void setManagerName(String managerName)
  {
    this.managerName = managerName;
  }
  
  public String getManagerDesignation()
  {
    return this.managerDesignation;
  }
  
  public void setManagerDesignation(String managerDesignation)
  {
    this.managerDesignation = managerDesignation;
  }
  
  public String getManagerDivision()
  {
    return this.managerDivision;
  }
  
  public void setManagerDivision(String managerDivision)
  {
    this.managerDivision = managerDivision;
  }
  
  public String getManagerEmpType()
  {
    return this.managerEmpType;
  }
  
  public void setManagerEmpType(String managerEmpType)
  {
    this.managerEmpType = managerEmpType;
  }
  
  public String getDirectorCuid()
  {
    return this.directorCuid;
  }
  
  public void setDirectorCuid(String directorCuid)
  {
    this.directorCuid = directorCuid;
  }
  
  public String getDirectorName()
  {
    return this.directorName;
  }
  
  public void setDirectorName(String directorName)
  {
    this.directorName = directorName;
  }
  
  public String getDirectorDesignation()
  {
    return this.directorDesignation;
  }
  
  public void setDirectorDesignation(String directorDesignation)
  {
    this.directorDesignation = directorDesignation;
  }
  
  public String getDirectorDivision()
  {
    return this.directorDivision;
  }
  
  public void setDirectorDivision(String directorDivision)
  {
    this.directorDivision = directorDivision;
  }
  
  public String getDirectorEmpType()
  {
    return this.directorEmpType;
  }
  
  public void setDirectorEmpType(String directorEmpType)
  {
    this.directorEmpType = directorEmpType;
  }
}
