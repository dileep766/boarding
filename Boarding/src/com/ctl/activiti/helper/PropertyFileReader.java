package com.ctl.activiti.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

public class PropertyFileReader {
public static void main(String[] args) {
	Properties prop = new Properties();

	getHRPMOinfo();
}
	public static HashMap<String,String> getHRPMOinfo()
	{
		
		
		HashMap<String,String> hrpmomap=new HashMap<String,String>();
		Properties prop = new Properties();
		InputStream input = null;
		String filepath = "";
		try {
			
			input = PropertyFileReader.class.getClassLoader().getResourceAsStream("resources/hrpmo.properties");
	 
			prop.load(input);
			hrpmomap.put("hr",prop.getProperty("hr"));
			hrpmomap.put("pmo",prop.getProperty("pmo"));
			System.out.println(prop.toString());
			return hrpmomap;
	 
		} catch (IOException ex) {
			ex.printStackTrace();
			return hrpmomap;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	
}
}
