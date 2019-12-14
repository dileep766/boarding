package com.ctl.activiti.helper;

import java.util.List;

import javax.sql.DataSource;

import org.activiti.engine.ManagementService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.runtime.Job;
import org.springframework.jdbc.core.JdbcTemplate;


import com.ctl.activiti.dao.ProcessStatusDAO;

public class DeleteUnnecessaryJobsHelper implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		DataSource ds=execution.getEngineServices().getProcessEngineConfiguration().getDataSource();
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(ds);
		long time = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
			String SQL = "update act_ru_job set DUEDATE_='"+timestamp+"' where PROCESS_INSTANCE_ID_='"+execution.getProcessInstanceId()+"';";
			               
			template.execute(SQL);
			 ManagementService managementService=execution.getEngineServices().getManagementService();
			/* List<Job> timerlist=managementService.createJobQuery().list();
			 for(Job timer:timerlist)
			 {
			 managementService.executeJob(timer.getId());
			
			 System.out.println("executing jobs one by one");
			 }*/
			 }

}
