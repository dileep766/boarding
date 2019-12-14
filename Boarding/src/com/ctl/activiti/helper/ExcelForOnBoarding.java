package com.ctl.activiti.helper;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.ctl.activiti.form.ResourcDetailsForm;


public class ExcelForOnBoarding {
	static Logger logger=Logger.getLogger(ExcelForOnBoarding.class);
	public static void main(String[] args) throws Exception, IOException {
		 
		
	}
	public  static void finalReport(File file, List<ResourcDetailsForm> resourcelist) throws Exception
	{
		try {
			FileInputStream fsIP= new FileInputStream(file); //Read the spreadsheet that needs to be updated
			HSSFWorkbook wb = new HSSFWorkbook(fsIP); //Access the workbook
			  
			HSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
			Cell cell = null; // declare a Cell object
			/*
			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext())
			{
			    Row row = rowIterator.next();
			    //For each row, iterate through all the columns
			    Iterator<Cell> cellIterator = row.cellIterator();
			     
			    while (cellIterator.hasNext())
			    {
			         cell = cellIterator.next();
			        //Check the cell type and format accordingly
			        switch (cell.getCellType())
			        {
			            case Cell.CELL_TYPE_NUMERIC:
			           	 logger.info("***********"+cell.getColumnIndex());
			           	 logger.info("***********"+cell.getRowIndex()+"\n");
			                System.out.print(cell.getNumericCellValue()+"\n");
			                break;
			            case Cell.CELL_TYPE_STRING:
			           	 logger.info("***********"+cell.getColumnIndex());
			           	 logger.info("***********"+cell.getRowIndex() +"\n");
			                System.out.print(cell.getStringCellValue()+"\n");
			                break;
			        }
			    }
			  
			}*/
			int lastIndex = worksheet.getLastRowNum();

		    for (int i=1; i<=lastIndex; i++)
		    {
	
		     
		      worksheet.removeRow(worksheet.getRow(i));
		          
		        
		    }
		    for (int j=1; j<=resourcelist.size(); j++)
		    {
		    	 Row dataRow = worksheet.createRow(j);
		    	ResourcDetailsForm resourceform=resourcelist.get(j-1);
		    	System.out.println(resourceform.toString());
		    	
		    	 dataRow.createCell(0).setCellValue(resourceform.getProcessid());
		
		    	 dataRow.createCell(1).setCellValue(resourceform.getStatusofprocess());  // Get current cell value value and overwrite the value
		    	 dataRow.createCell(2).setCellValue(resourceform.getCtlimanager()); 
		    	 dataRow.createCell(3).setCellValue(resourceform.getCtlidivision()); 
			
				dataRow.createCell(4).setCellValue(resourceform.getCtlidirector()); 
			
				dataRow.createCell(5).setCellValue(resourceform.getVendor()); 
			
				dataRow.createCell(6).setCellValue(resourceform.getVendormail()); 
				
				dataRow.createCell(7).setCellValue(resourceform.getStartDate()); 
			
				dataRow.createCell(8).setCellValue(resourceform.getEnddate()); 
				
				
				dataRow.createCell(9).setCellValue(resourceform.getResourceName()); 
				
				dataRow.createCell(10).setCellValue(resourceform.getFirstname()); 
				
				dataRow.createCell(11).setCellValue(resourceform.getLastname());
				
				dataRow.createCell(12).setCellValue(resourceform.getMiddlename()); 
			
				dataRow.createCell(13).setCellValue(resourceform.getSkills()); 
				dataRow.createCell(14).setCellValue(resourceform.getDesignation()); 
				dataRow.createCell(15).setCellValue(resourceform.getCompany()); 
			
				dataRow.createCell(16).setCellValue(resourceform.getDateofbirth());
				
				dataRow.createCell(17).setCellValue(resourceform.getGender()); 
				
				dataRow.createCell(18).setCellValue(resourceform.getMobilenumber()); 
			
						
				dataRow.createCell(19).setCellValue(resourceform.getPreviousorganization()); 
				
				dataRow.createCell(20).setCellValue(resourceform.getTotalyearsofexperience()); 
			
				dataRow.createCell(21).setCellValue(resourceform.getDegree());
			
				dataRow.createCell(22).setCellValue(resourceform.getSpecialization()); 
				
				dataRow.createCell(23).setCellValue(resourceform.getInstitute()); 
			
				dataRow.createCell(24).setCellValue(resourceform.getYearofpassing()); 
		    }
			
			fsIP.close(); //Close the InputStream
			 
			FileOutputStream output_file =new FileOutputStream(file);  //Open FileOutputStream to write updates
			  
			wb.write(output_file); //write changes
			  
			output_file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
		}
	}
	public  static void writeXls(File file,Map<String,Object> data) throws Exception
	{
		try {
			FileInputStream fsIP= new FileInputStream(file); //Read the spreadsheet that needs to be updated
			HSSFWorkbook wb = new HSSFWorkbook(fsIP); //Access the workbook
			  
			HSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
			Cell cell = null; // declare a Cell object
			
			Iterator<Row> rowIterator = worksheet.iterator();
			while (rowIterator.hasNext())
			{
			    Row row = rowIterator.next();
			    //For each row, iterate through all the columns
			    Iterator<Cell> cellIterator = row.cellIterator();
			     
			    while (cellIterator.hasNext())
			    {
			         cell = cellIterator.next();
			        //Check the cell type and format accordingly
			        switch (cell.getCellType())
			        {
			            case Cell.CELL_TYPE_NUMERIC:
			           	 logger.info("***********"+cell.getColumnIndex());
			           	 logger.info("***********"+cell.getRowIndex()+"\n");
			                System.out.print(cell.getNumericCellValue()+"\n");
			                break;
			            case Cell.CELL_TYPE_STRING:
			           	 logger.info("***********"+cell.getColumnIndex());
			           	 logger.info("***********"+cell.getRowIndex() +"\n");
			                System.out.print(cell.getStringCellValue()+"\n");
			                break;
			        }
			    }
			  
			}
     
			cell = worksheet.getRow(5).getCell(0);
			cell.setCellValue(1);
			cell = worksheet.getRow(5).getCell(1);   // Access the second cell in second row to update the value
			cell.setCellValue((String)data.get("ResourceName"));  // Get current cell value value and overwrite the value
			cell = worksheet.getRow(5).getCell(2);
			cell.setCellValue((String)data.get("firstname")); 
			cell = worksheet.getRow(5).getCell(3);
			cell.setCellValue((String)data.get("middlename")); 
			cell = worksheet.getRow(5).getCell(4);
			cell.setCellValue((String)data.get("lastname")); 
			cell = worksheet.getRow(5).getCell(5);
			cell.setCellValue((String)data.get("designation")); 
			cell = worksheet.getRow(5).getCell(6);
			cell.setCellValue((String)data.get("skills")); 
			cell = worksheet.getRow(5).getCell(7);
			cell.setCellValue((String)data.get("project")); 
			cell = worksheet.getRow(5).getCell(8);
			cell.setCellValue((String)data.get("projectstatus")); 
			cell = worksheet.getRow(5).getCell(9);
			
			cell.setCellValue((String)data.get("division")); 
			cell = worksheet.getRow(5).getCell(10);
			cell.setCellValue((String)data.get("manager")); 
			cell = worksheet.getRow(5).getCell(11);
			cell.setCellValue((String)data.get("company"));
			cell = worksheet.getRow(5).getCell(12);
			cell.setCellValue((String)data.get("worklocation")); 
			cell = worksheet.getRow(5).getCell(13);
			cell.setCellValue((String)data.get("comments")); 
			fsIP.close(); //Close the InputStream
			 
			FileOutputStream output_file =new FileOutputStream(file);  //Open FileOutputStream to write updates
			  
			wb.write(output_file); //write changes
			  
			output_file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
logger.error("exception==="+e);
		}
	}
        
       
	}
	
	

