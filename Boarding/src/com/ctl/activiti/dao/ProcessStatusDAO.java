package com.ctl.activiti.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.ctl.activiti.controller.UserInfoController;
import com.ctl.activiti.form.ProcessStatusForm;
import com.ctl.activiti.form.ResourcDetailsForm;
import com.ctl.activiti.helper.JDBCTemplateHelper;

@ Repository
public class ProcessStatusDAO {
	Logger logger=Logger.getLogger(ProcessStatusDAO.class);
	@Autowired
	JDBCTemplateHelper JDBCTemplate;
	public List<ProcessStatusForm> getActiveProcessInstancesByUser(String user) {
		
		String SQL = "SELECT * FROM act_hi_procinst where END_TIME_ is null and START_USER_ID_='"+user+"';";
		logger.info(SQL);
		List<ProcessStatusForm> processlist = JDBCTemplate.getJdbcTemplate().query(SQL,new BeanPropertyRowMapper<ProcessStatusForm>(ProcessStatusForm.class));
		logger.info("size====="+processlist.size());
return processlist;
	}
	public List<Map<String, Object>> getActiveTasksByProcessID(String ProcessID) {
	
	String SQL = "SELECT * FROM act_hi_taskinst where PROC_INST_ID_='"+ProcessID+"';";
	List<Map<String, Object>> vendorandresource = JDBCTemplate.getJdbcTemplate().queryForList(SQL);
	logger.info(SQL);
return vendorandresource;
}	

public void deleteJObs(String processid) {
	long time = System.currentTimeMillis();
	java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
		String SQL = "update act_ru_job set DUEDATE_='"+timestamp+"' where PROCESS_INSTANCE_ID_='"+processid+"';";
		logger.info(SQL);                          
 JDBCTemplate.getJdbcTemplate().execute(SQL);

	}
public List<ResourcDetailsForm> getTotalReport() {
	String sql="select max(case when `name_` = 'dateofbirth' then text_ end) as dateofbirth,max(case when `name_` = 'gender' then text_ end) as gender,max(case when `name_` = 'mobilenumber' then text_ end) as mobilenumber,max(case when `name_` = 'previousorganization' then text_ end) as previousorganization,max(case when `name_` = 'totalyearsofexperience' then text_ end) as totalyearsofexperience,max(case when `name_` = 'degree' then text_ end) as degree,max(case when `name_` = 'specialization' then text_ end) as specialization,max(case when `name_` = 'institute' then text_ end) as institute,max(case when `name_` = 'yearofpassing' then text_ end) as yearofpassing,max(case when `name_` = 'ctlimanager' then text_ end) as ctlimanager,max(case when `name_` = 'ctlidirector' then text_ end) as ctlidirector,max(case when `name_` = 'vendormail' then text_ end) as vendormail,max(case when `name_` = 'ctlidivision' then text_ end) as ctlidivision,max(case when `name_` = 'initiator' then text_ end) as initiator, max(case when `name_` = 'vendor' then text_ end) as vendor, max(case when `name_` = 'startDate' then text_ end) as startDate, max(case when `name_` = 'enddate' then text_ end) as enddate, max(case when `name_` = 'ResourceName' then text_ end) as ResourceName, max(case when `name_` = 'firstname' then text_ end) as firstname, max(case when `name_` = 'lastname' then text_ end) as lastname, max(case when `name_` = 'middlename' then text_ end) as middlename, max(case when `name_` = 'skills' then text_ end) as skills, max(case when `name_` = 'designation' then text_ end) as designation, max(case when `name_` = 'division' then text_ end) as division, max(case when `name_` = 'company' then text_ end) as company, max(case when `name_` = 'processid' then text_ end) as processid, max(case when `name_` = 'statusofprocess' then text_ end) as statusofprocess, max(case when `name_` = 'company' then text_ end) as company from activiti.act_hi_varinst t2 group by t2.PROC_INST_ID_;";
	 
                                                    
	List<ResourcDetailsForm> vendorandresource = JDBCTemplate.getJdbcTemplate().query(sql,new BeanPropertyRowMapper<ResourcDetailsForm>(ResourcDetailsForm.class));
                                                                                                       
	  return vendorandresource;
		
	}

}
