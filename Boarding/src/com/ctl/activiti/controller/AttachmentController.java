package com.ctl.activiti.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.FileUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ctl.activiti.dao.ProcessStatusDAO;
import com.ctl.activiti.form.ResourcDetailsForm;
import com.ctl.activiti.helper.ExcelForOnBoarding;
import com.ctl.activiti.helper.JDBCTemplateHelper;
import com.ctl.activiti.helper.UserInfo;
import com.ctl.activiti.login.DefaultLoginHandler;
import com.ctl.activiti.login.LoggedInUser;

@Controller
public class AttachmentController {

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
	ServletContext servletContext;
	@Autowired
	ProcessStatusDAO processStatusDAO;
	/*@ExceptionHandler(Exception.class)
    public ModelAndView handleEmployeeNotFoundException(Exception exception,HttpSession session) throws Exception{
		ModelAndView model = new ModelAndView("login");
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");

		defaultLoginHandler.logout(user);
		session.removeAttribute("user");
		session.invalidate();
		UserInfo userinfo = new UserInfo();
		model.addObject("userForm", userinfo);
		return model;
    }   */
	private static final Logger logger = Logger.getLogger(AttachmentController.class);
	@RequestMapping(value = "/attachmentData/{id}", method = RequestMethod.GET)
	public void getAttachmentByID(@PathVariable("id") String id,
			HttpServletResponse response, HttpSession session)  throws Exception{
		try {

			logger.info("file id==" + id);
			Attachment attachment = taskService.getAttachment(id);
								List<Map<String, Object>> info=getVendorAndresource(attachment.getProcessInstanceId());
			Map<String, Object> mapobjresource=info.get(0);
			Map<String, Object> mapobjvendor=info.get(1);
			String resourcename=(String) mapobjresource.get("TEXT_");
			String vendor=(String) mapobjvendor.get("TEXT_");
			logger.info(attachment.getName());
			LoggedInUser user = (LoggedInUser) session.getAttribute("user");
			
			identityService.setAuthenticatedUserId(user.getId());
			if(attachment.getName().contains(vendor+"_"+resourcename+"_"))
			response.setHeader("Content-Disposition", "inline;filename=\""+
					attachment.getName() + "\"");
			else
				response.setHeader("Content-Disposition", "inline;filename=\""
						+vendor+"_"+resourcename+"_"+attachment.getName() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType(attachment.getType());

			IOUtils.copy(taskService.getAttachmentContent(id), out);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
logger.error("exception==="+e);
		} catch (Exception e) {
			e.printStackTrace();
logger.error("exception==="+e);
		}

	}
	@RequestMapping(value = "/reportsDownload/{id}", method = RequestMethod.GET)
	public void reportsDownload(@PathVariable("id") String id,HttpServletRequest request,
			HttpServletResponse response, HttpSession session)  throws Exception{
		try {
			LoggedInUser user = (LoggedInUser) session.getAttribute("user");
			identityService.setAuthenticatedUserId(user.getId());
			Task task = taskService.createTaskQuery().taskId(id).singleResult();
			
			ArrayList<String> al_list=new ArrayList<String>();
			al_list.add("ResourceName");
			al_list.add("firstname");
			al_list.add("middlename");
			al_list.add("lastname");
			al_list.add("skills");
			al_list.add("designation");
			al_list.add("project");
			al_list.add("projectstatus");
			al_list.add("division");
			al_list.add("manager");
			al_list.add("company");
			al_list.add("worklocation");
			al_list.add("comments");
			Map<String,Object> reportavars=runtimeService.getVariables(task.getExecutionId(),al_list);
			File file = new File(servletContext.getRealPath("resource/CONTRACT JOINING INFO_20120829_V1.xls"));
			ExcelForOnBoarding.writeXls(file,reportavars);
			file = new File(servletContext.getRealPath("resource/CONTRACT JOINING INFO_20120829_V1.xls"));
			List<Map<String, Object>> info=getVendorAndresource(task.getProcessInstanceId());
			Map<String, Object> mapobjresource=info.get(0);
			Map<String, Object> mapobjvendor=info.get(1);
			String resourcename=(String) mapobjresource.get("TEXT_");
			String vendor=(String) mapobjvendor.get("TEXT_");
			response.setHeader("Content-Disposition", "inline;filename=\""
					+vendor+"_"+resourcename+"_"+file.getName() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType("application/xls");
			 InputStream targetStream = new FileInputStream(file);
			IOUtils.copy(targetStream, out);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
logger.error("exception==="+e);
		} catch (Exception e) {
			e.printStackTrace();
logger.error("exception==="+e);
		}

	}
	@RequestMapping(value = "/finalReport", method = RequestMethod.GET)
	public void finalReport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)  throws Exception{
		try {
			LoggedInUser user = (LoggedInUser) session.getAttribute("user");
			identityService.setAuthenticatedUserId(user.getId());
			
			File file = new File(servletContext.getRealPath("resource/Report.xls"));
			 List<ResourcDetailsForm> resourcedetailslist=processStatusDAO.getTotalReport();
			ExcelForOnBoarding.finalReport(file,resourcedetailslist);
			response.setHeader("Content-Disposition", "inline;filename=\""+
					file.getName() + "\"");
			OutputStream out = response.getOutputStream();
			response.setContentType("application/xls");
			 InputStream targetStream = new FileInputStream(file);
			IOUtils.copy(targetStream, out);
			out.flush();
			out.close();

		} catch (IOException e) {
			e.printStackTrace();
logger.error("exception==="+e);
		} catch (Exception e) {
			e.printStackTrace();
logger.error("exception==="+e);
		}

	}
	 
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody
	String handleFileUpload(@RequestParam("file") MultipartFile[] file,
			@RequestParam("id") String id, HttpSession session)  throws Exception{
		String fileName = null;
		logger.info("the id of the task====" + id);
		Task task = taskService.createTaskQuery().taskId(id).singleResult();
		logger.info("file list size=======" + file.length);
		/*
		 * if(task.getName().equalsIgnoreCase("Resource Status Approved")) {
		 * List<Attachment> attachments =
		 * (taskService.getProcessInstanceAttachments(task
		 * .getProcessInstanceId())); for(Attachment attach: attachments)
		 * taskService.deleteAttachment(attach.getId());
		 * 
		 * }
		 */
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());
		String msg = "";
		if (file != null && file.length > 0) {
			for (int i = 0; i < file.length; i++) {
				try {
					fileName = file[i].getOriginalFilename();
					byte[] bytes = file[i].getBytes();
					taskService.createAttachment(file[i].getContentType(), id,
							task.getProcessInstanceId(),
							file[i].getOriginalFilename(), "attachment",
							file[i].getInputStream());
					logger.info(fileName);

					msg += "You have successfully uploaded " + fileName
							+ "<br/>";
				} catch (Exception e) {
					return "You failed to upload " + fileName + ": "
							+ e.getMessage() + "<br/>";
				}
			}
			return msg;
		} else {
			return "Unable to upload. File is empty.";
		}
	}

	@RequestMapping(value = "/dataByTaskId/{id}", method = RequestMethod.GET)
	public void getAttachmentByprocessID(@PathVariable("id") String id,
			HttpServletResponse response, HttpSession session)
			throws Exception {
		try {
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());
		Attachment attachment = taskService.getAttachment(id);
		List<Map<String, Object>> info=getVendorAndresource(attachment.getProcessInstanceId());
		Map<String, Object> mapobjresource=info.get(0);
		Map<String, Object> mapobjvendor=info.get(1);
		String resourcename=(String) mapobjresource.get("TEXT_");
		String vendor=(String) mapobjvendor.get("TEXT_");
		response.setHeader("Content-Disposition",
				"attachment; filename="+vendor + "_" + resourcename + "_attachments.zip");

		ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
		response.setContentType("application/zip");
		
		String timeStamp=currentTimeString();
	
			List<Attachment> attachments = taskService.getTaskAttachments(attachment.getTaskId());
			logger.info("zip attachments size==="+attachments.size());
			String filesDirectory=getDirectory()+File.separator+currentTimeString();
			createFolder(filesDirectory);
			for (Attachment atachmentloop : attachments) {
				if(atachmentloop.getName().contains(vendor+"_"+resourcename+"_"))
					storeFile(filesDirectory,taskService.getAttachmentContent(atachmentloop.getId()),
							atachmentloop.getName());
					else
						storeFile(filesDirectory,taskService.getAttachmentContent(atachmentloop.getId()),
								vendor + "_" + resourcename +"_"+atachmentloop.getName());
				
			}
		
			File attachmentdir = new File(filesDirectory+File.separator);
			File list[] = attachmentdir.listFiles();

			for (File fileToSend : list) {

				ZipEntry ze = new ZipEntry(fileToSend.getName());
				zout.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(fileToSend);
				BufferedInputStream fif = new BufferedInputStream(fis);

				// Write the contents of the file
				int data = 0;
				while ((data = fif.read()) != -1) {
					zout.write(data);
				}
				fif.close();

				zout.closeEntry();

			}
			
			
			zout.close();
			delete(attachmentdir);
		} catch (IOException e) {
			e.printStackTrace();
logger.error("exception==="+e);
		} catch (Exception e) {
			e.printStackTrace();
logger.error("exception==="+e);
		}

	}
	
	@RequestMapping(value = "/exitClearenceForm", method = RequestMethod.GET)
	public void exitClearenceForm(
			HttpServletResponse response, HttpSession session)
			throws Exception {
		try {
		
		
		response.setHeader("Content-Disposition",
				"attachment; filename=exitcleareneceform.xls");

		
		response.setContentType("application/xls");
		
		
	
			
		
			File attachmentdir = new File(servletContext.getRealPath("ExitClearenceForm/exitcleareneceform.xls"));
			InputStream inputStream = new FileInputStream(attachmentdir);
	      
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
			
		} catch (IOException e) {
			e.printStackTrace();
logger.error("exception==="+e);
		} catch (Exception e) {
			e.printStackTrace();
logger.error("exception==="+e);
		}

	}
	@RequestMapping(value = "/onBoardingAttachments", method = RequestMethod.GET)
	public void onBoardingAttachments(
			HttpServletResponse response, HttpSession session)
			throws Exception {
		try {
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());
		
		response.setHeader("Content-Disposition",
				"attachment; filename=attachments.zip");

		ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
		response.setContentType("application/zip");
		
		
	
			
		
			File attachmentdir = new File(servletContext.getRealPath("onBoardingAttachments"));
			File list[] = attachmentdir.listFiles();

			for (File fileToSend : list) {

				ZipEntry ze = new ZipEntry(fileToSend.getName());
				zout.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(fileToSend);
				BufferedInputStream fif = new BufferedInputStream(fis);

				// Write the contents of the file
				int data = 0;
				while ((data = fif.read()) != -1) {
					zout.write(data);
				}
				fif.close();

				zout.closeEntry();

			}
			
			
			zout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
logger.error("exception==="+e);
		} catch (Exception e) {
			e.printStackTrace();
logger.error("exception==="+e);
		}

	}
	
	
	
	 public static void delete(File file)
		    	throws IOException{
		 
		    	if(file.isDirectory()){
		 
		    		//directory is empty, then delete it
		    		if(file.list().length==0){
		 
		    		   file.delete();
		    		   logger.info("Directory is deleted : " 
		                                                 + file.getAbsolutePath());
		 
		    		}else{
		 
		    		   //list all the directory contents
		        	   String files[] = file.list();
		 
		        	   for (String temp : files) {
		        	      //construct the file structure
		        	      File fileDelete = new File(file, temp);
		 
		        	      //recursive delete
		        	     delete(fileDelete);
		        	   }
		 
		        	   //check the directory again, if empty then delete it
		        	   if(file.list().length==0){
		           	     file.delete();
		        	     logger.info("Directory is deleted : " 
		                                                  + file.getAbsolutePath());
		        	   }
		    		}
		 
		    	}else{
		    		//if file, then delete it
		    		file.delete();
		    		logger.info("File is deleted : " + file.getAbsolutePath());
		    	}
		    }
public String currentTimeString()
{
	 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
	   Date date = new Date();
	   logger.info(dateFormat.format(date));

	   //get current date time with Calendar()
	   Calendar cal = Calendar.getInstance();
	   String format=dateFormat.format(cal.getTime()).toString().replaceAll("/","_").replaceAll(":","_").replaceAll(" ","_");
return format;
}
	public void storeFile(String dir,InputStream inputStream, String filename )
		 {
		try {
			File file = new File(dir, filename);
			logger.info("parent directory======"+dir);
			logger.info("filename==========="+filename);
			file.createNewFile();
			FileUtils.copyInputStreamToFile(inputStream,file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
		}

	}
	private static boolean createFolder(String theFilePath)
	{
	    boolean result = false;

	    File directory = new File(theFilePath);

	    if (directory.exists()) {
	        logger.info("Folder already exists");
	    } else {
	        result = directory.mkdirs();
	    }

	    return result;
	}
	public String getDirectory() {
		Properties prop;
		String home;
		String fileSeparator;
		String directoryName;

		prop = System.getProperties();
		home = prop.getProperty("user.dir").toString();
		fileSeparator = prop.getProperty("file.separator").toString();
		directoryName = "FileToDownload";

		return home + fileSeparator + directoryName;
	}

	
List<Map<String, Object>> getVendorAndresource(String id) {
		
		String SQL = "SELECT * FROM activiti.act_hi_varinst where EXECUTION_ID_='"+id+"' and NAME_ in('vendor','ResourceName') order by NAME_ asc;";
		List<Map<String, Object>> vendorandresource = JDBCTemplate.getJdbcTemplate().queryForList(SQL);
		
return vendorandresource;
	}	
	
	

}
