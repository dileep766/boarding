
package com.ctl.activiti.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ctl.activiti.dao.ProcessStatusDAO;
import com.ctl.activiti.form.ProcessStatusForm;
import com.ctl.activiti.form.ResourcDetailsForm;
import com.ctl.activiti.helper.UserInfo;
import com.ctl.activiti.login.DefaultLoginHandler;
import com.ctl.activiti.login.LoggedInUser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Controller
public class ProcessStatusController {
	
	Logger logger=Logger.getLogger(ProcessStatusController.class);
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
	ProcessStatusDAO processStatusDAO;
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
	@RequestMapping(value="procesStatusList",method = RequestMethod.GET)
	public ModelAndView procesStatusList(
			HttpSession session) throws Exception {
		ModelAndView mv = null;
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());
		try {

			List<ProcessStatusForm> processlist=processStatusDAO.getActiveProcessInstancesByUser(user.getId());
			
		
			mv = new ModelAndView();

			mv.setViewName("procesStatusList");
			mv.addObject("procesStatusList", processlist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
		}
		return mv;
	}
	@RequestMapping(value="Report",method = RequestMethod.GET)
	public ModelAndView Report(
			HttpSession session) throws Exception {
		ModelAndView mv = null;
		LoggedInUser user = (LoggedInUser) session.getAttribute("user");
		identityService.setAuthenticatedUserId(user.getId());
		try {

	
		
			mv = new ModelAndView();

			mv.setViewName("Report");
		
		} catch (Exception e) {
			
			e.printStackTrace();
logger.error("exception==="+e);
		}
		return mv;
	}
	@RequestMapping(value="getTotalReport",method = RequestMethod.GET)
	public @ResponseBody List<ResourcDetailsForm> getTotalReport(
			HttpSession session) throws Exception {
		ModelAndView mv = null;
	return 	processStatusDAO.getTotalReport();
	}
	@RequestMapping(value = "/taskListForProcess/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, Object>> taskListForProcess(@PathVariable("id") String id,
			HttpSession session) throws Exception {
	return	processStatusDAO.getActiveTasksByProcessID(id);
		
	}
	
	@RequestMapping(value = "/reportOfProcess/{id}", method = RequestMethod.GET)
	public void reportOfProcess(@PathVariable("id") String id,
			HttpSession session,HttpServletResponse response) throws Exception {
	
	Document document = new Document();
	   Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
     	      Font.BOLD,BaseColor.GREEN);
     	  Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
     	      Font.NORMAL, BaseColor.RED);
      Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
     	      Font.BOLD);
     	  Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8,
     	      Font.BOLD);
	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 PdfWriter.getInstance(document,
			 baos);

         document.open();
         response.setContentType("application/pdf");

         PdfPTable table = new PdfPTable(5); // 3 columns.
         table.setWidthPercentage(500 / 5.23f);
         PdfPCell cell1 = new PdfPCell(new Paragraph("TASK NAME",catFont));
         PdfPCell cell2 = new PdfPCell(new Paragraph("ASSIGNED TO",catFont));
         PdfPCell cell3 = new PdfPCell(new Paragraph("ASSIGNED TIME",catFont));
         PdfPCell cell4 = new PdfPCell(new Paragraph("COMPLETED TIME",catFont));
         PdfPCell cell5 = new PdfPCell(new Paragraph("STATUS",catFont));
         table.addCell(cell1);
         table.addCell(cell2);
         table.addCell(cell3);
         table.addCell(cell4);
         table.addCell(cell5);
         List<Map<String, Object>> list= processStatusDAO.getActiveTasksByProcessID(id);
        for(int i=0;i<list.size();i++)
        {
        	Map<String, Object> map=list.get(i);
        	String taskname=(String) map.get("NAME_");
        	java.sql.Timestamp starttimestamp=	(Timestamp) map.get("START_TIME_");
        	java.sql.Timestamp endtimestamp=	(Timestamp) map.get("END_TIME_");
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy' 'HH:mm:ss");
        	 java.sql.Date startdate = new java.sql.Date(starttimestamp.getTime());
        	 java.sql.Date enddate=null;
        	 String startdatestr=simpleDateFormat.format(startdate);
        	 String enddatestr="";
        	 if(endtimestamp==null ||endtimestamp.equals(null))
        	 {
        		 enddatestr = "";
        	 }
        	 else
        	 {
        enddate = new java.sql.Date(endtimestamp.getTime()); 
        enddatestr=simpleDateFormat.format(enddate);
        	 }
        	 if(taskname=="Resource Status Rejected" || taskname=="Resource Information to HR Operations"|| taskname=="Conformation About DOJ")
        	{
        	table.addCell(new PdfPCell(new Paragraph((String) map.get("NAME_"),smallBold)));
        	table.addCell(new PdfPCell(new Paragraph((String) map.get("ASSIGNEE_"),smallBold)));
        	table.addCell(new PdfPCell(new Paragraph(startdatestr,smallBold)));
        	table.addCell(new PdfPCell(new Paragraph(startdatestr,smallBold)));
        	table.addCell(new PdfPCell(new Paragraph("COMPLETED",smallBold)));
        	}
        	else if(taskname.contains("Resource Information On Hold"))
        	{
        		table.addCell(new PdfPCell(new Paragraph((String) map.get("NAME_"),smallBold)));
            	table.addCell(new PdfPCell(new Paragraph((String) map.get("ASSIGNEE_"),smallBold)));
            	table.addCell(new PdfPCell(new Paragraph(startdatestr,smallBold)));
            	table.addCell(new PdfPCell(new Paragraph(enddatestr,smallBold)));
            
            	if(enddatestr.equals("") ||enddatestr==null || enddatestr=="null")
            	{
            		table.addCell(new PdfPCell(new Paragraph("ON HOLD",smallBold)));
            	}
            	else
            	{
            		table.addCell(new PdfPCell(new Paragraph("COMPLETED",smallBold)));
            	}
        	}
        	else 
        	{
        		table.addCell(new PdfPCell(new Paragraph((String) map.get("NAME_"),smallBold)));
            	table.addCell(new PdfPCell(new Paragraph((String) map.get("ASSIGNEE_"),smallBold)));
            	table.addCell(new PdfPCell(new Paragraph(startdatestr,smallBold)));
            	table.addCell(new PdfPCell(new Paragraph(enddatestr,smallBold)));
            	
            	if(enddatestr.equals("") ||enddatestr==null || enddatestr=="null")
            	{
            		table.addCell(new PdfPCell(new Paragraph("IN PROGRESS",smallBold)));
            	}
            	else
            	{
            		table.addCell(new PdfPCell(new Paragraph("COMPLETED",smallBold)));
            	}
            	
        	}
        	}
        	
      
         Paragraph preface = new Paragraph();
         // We add one empty line
         addEmptyLine(preface, 1);
         // Lets write a big header
      
         preface.add(new Paragraph("Status Report For Resource ID : CRR"+id, redFont));

         addEmptyLine(preface, 1);
         document.add(preface);
         document.add(table);
         document.close();
         response.setContentType("application/pdf");
                  response.setHeader("Content-Disposition","inline;filename=\""+"Report.pdf" + "\"");
         // setting the content type
      
         // the contentlength
         response.setContentLength(baos.size());
         // write ByteArrayOutputStream to the ServletOutputStream
         OutputStream os = response.getOutputStream();
         baos.writeTo(os);
         os.flush();
         os.close(); 
         
	}
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
	 

}
