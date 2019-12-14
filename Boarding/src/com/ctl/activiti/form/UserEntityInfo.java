package com.ctl.activiti.form;

import java.io.Serializable;

import org.activiti.engine.impl.persistence.entity.UserEntity;

public class UserEntityInfo extends UserEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String telephoneNumber;
	String employeeNumber;
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	

}
