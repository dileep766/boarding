package com.ctl.activiti.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctl.activiti.helper.JDBCTemplateHelper;
import com.ctl.activiti.helper.UserInfo;
import com.ctl.activiti.login.DefaultLoginHandler;
import com.ctl.activiti.login.LoggedInUser;
@Controller
public class UserInfoController {
	Logger logger=Logger.getLogger(UserInfoController.class);
	@Autowired
	JDBCTemplateHelper JDBCTemplate;
	
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;
	@Autowired
	@Qualifier("processEngine")
	private ProcessEngineFactoryBean processEngine;
	@Autowired
	RepositoryService repositoryService;
	@Autowired
	@Qualifier("identityService")
	IdentityService identityService;
	@Autowired
	DefaultLoginHandler defaultLoginHandler;
	@Autowired
	FormService formService;
	/*@ExceptionHandler(Exception.class)
    public ModelAndView handleEmployeeNotFoundException(Exception exception,HttpSession session){
		ModelAndView model = new ModelAndView("login");
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");

		defaultLoginHandler.logout(user);
		session.removeAttribute("user");
		session.invalidate();
		UserInfo userinfo = new UserInfo();
		model.addObject("userForm", userinfo);
		return model;
    }   */
	@RequestMapping(value = "/FormHistory/{id}", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> taskFormHistoryData(@PathVariable("id") String id,HttpSession session)  throws Exception{
		

		 List<HistoricDetail>	historicFormProps = processEngine.getProcessEngineConfiguration()
					.getHistoryService().createHistoricDetailQuery()
					.formProperties().taskId(id).orderByVariableName().asc().list();
			 	logger.info("history size=="+historicFormProps.size());
				
		@SuppressWarnings("unused")
		HistoricFormProperty historicSummary = (HistoricFormProperty) historicFormProps.get(0);

		

		return null;

	}
	
	@RequestMapping(value = "/allVendors", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, Object>> allVendors()  throws Exception{
		
		String SQL = "SELECT * FROM act_id_user;";
		List<Map<String, Object>> userslist = JDBCTemplate.getJdbcTemplate().queryForList(SQL);
		
return userslist;
	}
	
	
	
}
