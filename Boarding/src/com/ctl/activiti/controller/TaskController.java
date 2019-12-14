package com.ctl.activiti.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
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
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
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

import com.ctl.activiti.dao.ProcessStatusDAO;
import com.ctl.activiti.form.ResourceRequestProcessIntiationForm;
import com.ctl.activiti.helper.JDBCTemplateHelper;
import com.ctl.activiti.helper.ProcessStatusHelper;
import com.ctl.activiti.helper.UserInfo;
import com.ctl.activiti.login.DefaultLoginHandler;
import com.ctl.activiti.login.LoggedInUser;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TaskController {
	Logger logger=Logger.getLogger(TaskController.class);
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	JDBCTemplateHelper JDBCTemplate;
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
		ProcessStatusDAO processStatusDAO;
	@RequestMapping(value = "/processIntiation", method = RequestMethod.GET)
	public String processIntiation(Map<String, Object> model,
			HttpSession session) throws Exception{
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());
		List<ProcessDefinition> processDefinitions = repositoryService
				.createProcessDefinitionQuery().orderByProcessDefinitionName()
				.asc().orderByProcessDefinitionVersion().asc().list();

		logger.info("process list size" + processDefinitions.size());

		ResourceRequestProcessIntiationForm processIntiation = new ResourceRequestProcessIntiationForm();
		model.put("processIntiation", processIntiation);
		return "processIntiation";
	}

	@RequestMapping(value = "/taskList", method = RequestMethod.GET)
	public ModelAndView taskList(HttpSession session) throws Exception {

		ModelAndView mv = new ModelAndView();
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());

		logger.info("user name=" + user.getId());
		logger.info("task list size = "
				+ taskService.createTaskQuery().taskAssignee(user.getId())
						.list().size());
		logger.info("task list size = "
				+ taskService.createTaskQuery().taskCandidateUser(user.getId())
						.list().size());
		logger.info("task list size = "
				+ taskService.createTaskQuery()
						.taskCandidateGroup(user.getId()).list().size());
		ArrayList<Task> tasklist = (ArrayList<Task>) taskService
				.createTaskQuery().taskCandidateOrAssigned(user.getId()).list();
		
		
		tasklist.addAll(taskService.createTaskQuery().taskCandidateGroup(user.getId()).list());
		
		mv.addObject("tasklist", tasklist);
		mv.setViewName("taskList");

		return mv;

	}

	
	
	 

	@RequestMapping(value = "/taskFormData/{id}", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> taskFormData(@PathVariable("id") String id,
			HttpSession session) throws Exception {

		HashMap<String, Object> taskinfo = null;

		try {

			Task task = taskService.createTaskQuery().taskId(id).singleResult();
		
			List<Attachment> attachments = null;
			List<Attachment> attachmentschange = new ArrayList<Attachment>();
			LoggedInUser user = (LoggedInUser) session.getAttribute("user");
			identityService.setAuthenticatedUserId(user.getId());

			String taskid = null;
			TaskFormData taskFormData = formService.getTaskFormData(id);

			List<FormProperty> lisprop = taskFormData.getFormProperties();
			taskinfo = new HashMap<String, Object>();
			ArrayList<HashMap<String, Object>> propertiesinfo = new ArrayList<HashMap<String, Object>>();
			for (FormProperty property : lisprop) {
				if (property.getName().equalsIgnoreCase("id")) {
					id = property.getValue();

				} else {
					logger.info(property.getType().getName() + "==="
							+ property.getName() + "===");
					HashMap<String, Object> propertyinfo = new HashMap<String, Object>();
					Map<String, String> values = (Map<String, String>) property
							.getType().getInformation("values");
					propertyinfo.put("property", property);
					propertyinfo.put("propertyvalues", values);
					propertiesinfo.add(propertyinfo);
				}
			}
			if (id != null) {
				String jsonstring = task.getDescription();
				/* jsonstring=jsonstring.replaceAll("/\n/g", "\\n"); */
				ObjectMapper mapper = new ObjectMapper();

				Map<String, Object> map = mapper.readValue(jsonstring,
						Map.class);
				String previoustaskid = (String) map.get("id");
				Map<String, Object> mapformdata = (Map<String, Object>) map
						.get("FormData");
				logger.info(jsonstring);
				logger.info("=======================================");
				logger.info("==================id==" + previoustaskid
						+ "=====================");
				// logger.info(property.toString());
				attachments = taskService.getTaskAttachments(previoustaskid);
				logger.info("======================================="
						+ task.getProcessInstanceId());
				String vendor = "";
				String resourcename = "";
				if (attachments.size() !=0) {
					List<Map<String, Object>> info=getVendorAndresource(task.getProcessInstanceId());
					Map<String, Object> mapobjresource=info.get(0);
					Map<String, Object> mapobjvendor=info.get(1);
					resourcename=(String) mapobjresource.get("TEXT_");
					vendor=(String) mapobjvendor.get("TEXT_");
					logger.info("vendor========="+vendor);
					logger.info("resorce name========="+resourcename);
					for (Attachment attachment : attachments) {
						String attachmentname = attachment.getName();
						if(!attachmentname.contains(vendor + "_" + resourcename + "_"))
						attachment.setName(vendor + "_" + resourcename + "_"+attachmentname);
					
						attachmentschange.add(attachment);
					}
				}
			}

			taskinfo.put("properties", propertiesinfo);
			taskinfo.put("description", task.getDescription());
			taskinfo.put("attachments", attachmentschange);
			return taskinfo;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
			return taskinfo;
		}

	}
List<Map<String, Object>> getVendorAndresource(String id) {
		
		String SQL = "SELECT * FROM activiti.act_hi_varinst where EXECUTION_ID_='"+id+"' and NAME_ in('vendor','ResourceName') order by NAME_ asc;";
		List<Map<String, Object>> vendorandresource = JDBCTemplate.getJdbcTemplate().queryForList(SQL);
		
return vendorandresource;
	}
	
	

	@RequestMapping(value = "/finishTask", method = RequestMethod.POST)
	public ModelAndView finishTask(HttpServletRequest request,HttpSession session) throws Exception {
		List<Attachment> attachments = null;
		List<Attachment> currentattachments = null;
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());
		ModelAndView modelview = new ModelAndView("sucess");
		HashMap<String, Object> data = new HashMap<String, Object>();
		Enumeration<String> list = request.getParameterNames();
		for (String key : Collections.list(list)) {
			data.put(key, request.getParameter(key));
			logger.info(key + "===" + request.getParameter(key));
		}
	
		String currentid = (String) data.remove("currentid");
	
		Task task = taskService.createTaskQuery().taskId(currentid).singleResult();
		logger.info("taskname from finish task==="+task.getName());
		if(task.getName().toString().equals("Refilling Req From PMO"))
		{                   
			logger.info("inside taskname from finish task==="+task.getName());
			task.getDescription();
			String jsonstring = task.getDescription();
			/* jsonstring=jsonstring.replaceAll("/\n/g", "\\n"); */
			ObjectMapper mapper = new ObjectMapper();

			@SuppressWarnings("unchecked")
			Map<String, Object> map = mapper.readValue(jsonstring,Map.class);
			String previousid = (String) map.get("id");   
			Map<String, Object> formdata=(Map<String, Object>) map.get("FormData");
			String previouspmocomments = (String) formdata.get("Additional Details");
			attachments = taskService.getTaskAttachments(previousid);
			
			currentattachments=taskService.getTaskAttachments(currentid);
			logger.info("taskname from finish task==="+attachments.size());
			logger.info("taskname from finish task==="+currentattachments.size());
			String currentIDs="";
			for(Attachment currentattachment:currentattachments)
			{
				currentIDs=currentIDs+currentattachment.getId()+"##";
			}
			if(currentIDs!="")
			currentIDs=currentIDs.substring(0,currentIDs.lastIndexOf("##"));
			data.put("attachmentids",currentIDs);
			data.put("previouspmocomments",previouspmocomments);
			for(Attachment attachment:attachments)
			{
				boolean flag=true;
				for(Attachment currentattachment:currentattachments)
				{
					
					if(currentattachment.getName().toString().contains("_"))
					{
					
						String strarray[]=currentattachment.getName().toString().split("_");
						if(!attachment.getName().equals(strarray[strarray.length-1]))
						{
							flag=true;
							
						}
					}
					else if(!attachment.getName().equals(currentattachment.getName()))
					{
						
						
							flag=true;
					
					}
					else
					{
						flag=false;
					}
					
					
				}
				if(flag)
				{
					taskService.createAttachment(attachment.getType(), currentid, task.getProcessInstanceId(), attachment.getName(),  "attachment",  taskService.getAttachmentContent(attachment.getId()));	
				}
			}
		}
		
		if(data.containsKey("additionalDetails"))
		{
		String additionalDetails= (String) data.remove("additionalDetails");
		additionalDetails=additionalDetails.replaceAll("\\r|\\n", "");
		data.put("additionalDetails", additionalDetails);
		}
		ProcessStatusHelper helper=new ProcessStatusHelper();
		String statusofprocess=helper.getProcessStatus(task.getName(),data);
		if(!task.getName().equalsIgnoreCase("Resource CUID") && !task.getName().equalsIgnoreCase("Resource Information to HR Operations") )
		data.put("statusofprocess", statusofprocess);
		taskService.complete(currentid, data);
		/*if(task.getName().toString().equals("Status Change"))
		{
			processStatusDAO.deleteJObs(task.getProcessInstanceId());
		}*/// logger.info("id==" + id);

		// return "redirect:/taskList";
		modelview.addObject("message", "Task Has Been Completed Sucess Fully");
		return modelview;
		/*
		 * logger.info("===============" +
		 * taskService.createTaskQuery().taskAssignee("kermit").list()
		 * .get(0).getName()); return "LoginSuccess";
		 */

	}
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

}
