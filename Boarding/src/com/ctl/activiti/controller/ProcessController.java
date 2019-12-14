package com.ctl.activiti.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.query.QueryProperty;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.ldap.LDAPUserManager;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctl.activiti.form.UserEntityInfo;
import com.ctl.activiti.helper.UserInfo;
import com.ctl.activiti.login.DefaultLoginHandler;
import com.ctl.activiti.login.LoggedInUser;


@Controller
public class ProcessController {
	Logger logger=Logger.getLogger(ProcessController.class);
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
	@Autowired
	 LDAPUserManager LdapUserManager;
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
    }  */ 
	@RequestMapping(value="/procesList",method = RequestMethod.GET)
	public ModelAndView procesList(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws Exception {
		ModelAndView mv = null;
		Map<String, QueryProperty> properties = new HashMap<String, QueryProperty>();
		try {

			List<ProcessDefinition> processDefinitions = repositoryService
					.createProcessDefinitionQuery()
					.orderByProcessDefinitionName().asc()
					.orderByProcessDefinitionVersion().asc().list();
			
		
			mv = new ModelAndView();

			mv.setViewName("procesList");
			mv.addObject("procesList", processDefinitions);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
		}
		return mv;
	}
	@RequestMapping(value = "/processFormData/{id}", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> taskFormData(@PathVariable("id") String id,HttpSession session)  throws Exception {
		
		

		
		
		List<Attachment> attachments = null;
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());
	
		StartFormData  formdata= formService.getStartFormData(id);
		
	
		List<FormProperty> lisprop = formdata.getFormProperties();
		HashMap<String, Object> taskinfo = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> propertiesinfo = new ArrayList<HashMap<String, Object>>();
		for (FormProperty property : lisprop) {
			logger.info(property.getType().getName() + "==="
					+ property.getName() + "===");
			HashMap<String, Object> propertyinfo = new HashMap<String, Object>();
			Map<String, String> values = (Map<String, String>) property
					.getType().getInformation("values");
			propertyinfo.put("property", property);
			propertyinfo.put("propertyvalues", values);
			propertiesinfo.add(propertyinfo);
		}
		
		
		taskinfo.put("properties", propertiesinfo);
		
		taskinfo.put("attachments", attachments);
		if(id.contains("HelpDesk"))
		{
			HashMap<String,String> userinfo_map=new HashMap<String,String>();
			logger.info("inside if block");
		UserEntityInfo userentityinfo=LdapUserManager.findUserById(user.getId());
		
		userinfo_map.put("employeeid", userentityinfo.getEmployeeNumber());
		userinfo_map.put("employeename", userentityinfo.getFirstName()+" "+userentityinfo.getLastName());
		userinfo_map.put("phonenumber",userentityinfo.getTelephoneNumber());
		
		taskinfo.put("propertyvalues", userinfo_map);
		}
		return taskinfo;
	}
	@RequestMapping(value = "/processIntiation", method = RequestMethod.POST)
	public ModelAndView processIntiation(HttpServletRequest request,
			HttpSession session) throws Exception {
		ModelAndView modelview=new ModelAndView("sucess");
		try {
			String uri = request.getScheme() + "://" +   // "http" + "://
		             request.getServerName() +       // "myhost"
		             ":" +                           // ":"
		             request.getServerPort() +       // "8080"
		             request.getContextPath();
			HashMap<String, Object> data = new HashMap<String, Object>();
			Enumeration<String> list = request.getParameterNames();
			for (String key : Collections.list(list)) {
				data.put(key, request.getParameter(key));
				//log.info(key+"===process data===="+request.getParameter(key));
				logger.info(key + "===process data====" + request.getParameter(key));
			}

			String id = (String) data.remove("id");
			String peoplestr=(String) data.get("numberOfPeople");
			int People=1;
			if(peoplestr!=null)
			{
			People= Integer.parseInt(peoplestr);
			}
			
			LoggedInUser user = (LoggedInUser) session.getAttribute("user");
			UserEntityInfo userentityinfo=LdapUserManager.findUserById(user.getId());
			identityService.setAuthenticatedUserId(user.getId());
			data.put("url",uri);
			data.put("previouspmocomments","");
			data.put("attachmentids","");
			
			
			data.put("userinfo",userentityinfo);
			List<ProcessDefinition> processDefinitions = repositoryService
					.createProcessDefinitionQuery()
					.orderByProcessDefinitionName().asc()
					.orderByProcessDefinitionVersion().asc().list();
			for(int i=0;i<People;i++)
			{
				logger.info("starting process==="+i);
				ProcessInstance instance= runtimeService
					.startProcessInstanceByKey(id,data);
				ExecutionEntity executionEntity =(ExecutionEntity) instance;
				
			}                           
			} catch (Exception e) {
			
			e.printStackTrace();
logger.error("exception==="+e);
		}
		
		
		modelview.addObject("message", "Process Has Been Initiated Sucess Fully");
		return modelview;
		//return "redirect:/taskList";

	}
	
	@RequestMapping(value = "/processDef/{id}", method = RequestMethod.GET)
	public void processDef(@PathVariable("id") String id,
			HttpServletResponse response,HttpSession session) throws Exception {
		try {
			logger.info("inside process Def");
			LoggedInUser user = (LoggedInUser) session.getAttribute("user");
			identityService.setAuthenticatedUserId(user.getId());
			
			
			Object form = processEngine.getProcessEngineConfiguration().getFormService().getRenderedStartForm(id);
			InputStream is = null;
			if (form != null && form instanceof String) {
			  is = new ByteArrayInputStream(((String) form).getBytes());
			}
			else if (form != null && form instanceof InputStream) {
			  is = (InputStream) form;
   
			}
			if (is != null) {
				response.setHeader("Content-Disposition", "inline;filename=diagram.png");
				OutputStream out = response.getOutputStream();
				response.setContentType("image/png");

				IOUtils.copy(is, out);
				out.flush();
				out.close();
			
			} else if (form != null){
			  throw new ActivitiException("The form for process definition '" + id + "' failed to render.");
			
			} else {
			  throw new ActivitiException("The form for process definition '" + id + "' cannot be rendered using the rest api.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
		}
		
		

	}
    
	
}
