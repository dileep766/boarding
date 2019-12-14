package com.ctl.activiti.form;


import java.io.PrintStream;
import org.apache.log4j.Logger;
import com.qwest.common.util.EmployeeDAO;
public class EmployeeDetails
{
  Logger logger;
  String[] strEmployeeData;
  String query;
  Employeedata employeeData;
  
  public EmployeeDetails()
  {
    this.logger = Logger.getLogger(EmployeeDetails.class);
    this.strEmployeeData = null;
    this.query = "";
    this.employeeData = null;
  }
  
  public Employeedata getNextInHierarchy(String empCuid)
  {
    this.query = 
    




      ("SELECT RES_NAME, RES_CUID, EMP_NO, to_char(DOJ,'DD-Mon-yyyy'), to_char(DOL,'DD-Mon-yyyy'), DESIGNATION, DIVISION, EMP_TYPE, IMM_SUP_CUID, IMM_SUP_NAME, IMM_SUP_DESIGNATION, IMM_SUP_DIVISION, IMM_SUP_EMPTYPE, MGR_CUID, MGR_NAME, MGR_DESIGNATION, MGR_DIVISION, MGR_EMPTYPE, DIR_CUID, DIR_NAME, DIR_DESIGNATION, DIR_DIVISION, DIR_EMPTYPE,CURRENT_LOCATION,TRANSFER_DATE FROM emp_details_view where upper(res_cuid)=(select imm_sup_cuid from emp_details_view where upper(RES_CUID)='" + empCuid.toUpperCase() + "'" + " and DOL is NULL" + ")");
    this.logger.info("getNextInHierarchy qry=" + this.query);
    System.out.println("getNextInHierarchy qry=" + this.query);
    EmployeeDAO employeeDAO = new EmployeeDAO();
    this.strEmployeeData = employeeDAO.getData(this.query);
    this.employeeData = setEmployeeData(this.strEmployeeData);
    return this.employeeData;
  }
  
  public Employeedata getEmployeeDetails(String empCuid)
  {
    this.query = 
    



      ("SELECT RES_NAME, RES_CUID, EMP_NO, to_char(DOJ,'DD-Mon-yyyy'), to_char(DOL,'DD-Mon-yyyy'), DESIGNATION, DIVISION, EMP_TYPE, IMM_SUP_CUID, IMM_SUP_NAME, IMM_SUP_DESIGNATION, IMM_SUP_DIVISION, IMM_SUP_EMPTYPE, MGR_CUID, MGR_NAME, MGR_DESIGNATION, MGR_DIVISION, MGR_EMPTYPE, DIR_CUID, DIR_NAME, DIR_DESIGNATION, DIR_DIVISION, DIR_EMPTYPE,CURRENT_LOCATION,TRANSFER_DATE FROM emp_details_view where upper(res_cuid)='" + empCuid.toUpperCase() + "'" + " and DOL is NULL");
    this.logger.info("getEmployeeDetails qry=" + this.query);
    System.out.println("getEmployeeDetails qry=" + this.query);
    EmployeeDAO employeeDAO = new EmployeeDAO();
    this.strEmployeeData = employeeDAO.getData(this.query);
    this.employeeData = setEmployeeData(this.strEmployeeData);
    return this.employeeData;
  }
  
  public Employeedata setEmployeeData(String[] str)
  {
    this.employeeData = new Employeedata();
    this.employeeData.setName(this.strEmployeeData[0]);
    this.employeeData.setCuid(this.strEmployeeData[1]);
    if (this.strEmployeeData[1] != null) {
      this.employeeData.setEmp_id(Integer.parseInt(this.strEmployeeData[2]));
    }
    this.employeeData.setDOJ(this.strEmployeeData[3]);
    this.employeeData.setTerminationDate(this.strEmployeeData[4]);
    this.employeeData.setJobtitle(this.strEmployeeData[5]);
    this.employeeData.setDivision(this.strEmployeeData[6]);
    this.employeeData.setEmpType(this.strEmployeeData[7]);
    this.employeeData.setImmSupCuid(this.strEmployeeData[8]);
    this.employeeData.setImmSupName(this.strEmployeeData[9]);
    this.employeeData.setImmSupDesignation(this.strEmployeeData[10]);
    this.employeeData.setImmSupDivision(this.strEmployeeData[11]);
    this.employeeData.setImmSupEmpType(this.strEmployeeData[12]);
    this.employeeData.setManagerCuid(this.strEmployeeData[13]);
    this.employeeData.setManagerName(this.strEmployeeData[14]);
    this.employeeData.setManagerDesignation(this.strEmployeeData[15]);
    this.employeeData.setManagerDivision(this.strEmployeeData[16]);
    this.employeeData.setManagerEmpType(this.strEmployeeData[17]);
    this.employeeData.setDirectorCuid(this.strEmployeeData[18]);
    this.employeeData.setDirectorName(this.strEmployeeData[19]);
    this.employeeData.setDirectorDesignation(this.strEmployeeData[20]);
    this.employeeData.setDirectorDivision(this.strEmployeeData[21]);
    this.employeeData.setDirectorEmpType(this.strEmployeeData[22]);
    this.employeeData.setCurrentLocation(this.strEmployeeData[23]);
    this.employeeData.setTransferDate(this.strEmployeeData[24]);
    return this.employeeData;
  }
}
