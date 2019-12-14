package com.ctl.activiti.helper;

import java.sql.DriverManager;

import com.ctl.activiti.form.VendorUserInfo;
import java.sql.*;

import org.apache.log4j.Logger;

public class JDBCHelper {
public static VendorUserInfo getVendorInfo(String vendor)
{
 Logger logger=Logger.getLogger(ExcelForOnBoarding.class);
	VendorUserInfo vendoruserinfo=new VendorUserInfo();
	String SQL = "SELECT * FROM act_id_user where ID_ ='"+vendor+"';";
 String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	String DB_URL = "jdbc:mysql://10.140.1.90:3306/activiti";

	   //  Database credentials
 String USER = "test";
 String PASS = "password";
	   
	   
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      logger.info("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      logger.info("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      logger.info("Creating statement...");
	      stmt = conn.createStatement();

	      
	      ResultSet rs = stmt.executeQuery(SQL);
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         
	         String id = rs.getString("ID_");
	         String mail = rs.getString("EMAIL_");

	         //Display values
	      
	         System.out.print("user: " + id);
	         logger.info("mail: " + mail);
	         vendoruserinfo.setMail(mail);
	         vendoruserinfo.setUser(id);
	      }
	      rs.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   logger.info("Goodbye!");
	return vendoruserinfo;
}
public static void main(String[] args) {
	VendorUserInfo info=getVendorInfo("tcs");

}
} 
	

