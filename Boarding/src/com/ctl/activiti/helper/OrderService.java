package com.ctl.activiti.helper;

import org.activiti.engine.delegate.DelegateExecution;

public class OrderService {
	
	public void validate(DelegateExecution execution) {
		System.out.println("validating order for isbn " + execution.getVariable("isbn"));
	}

}
