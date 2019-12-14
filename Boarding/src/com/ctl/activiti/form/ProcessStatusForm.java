package com.ctl.activiti.form;

import java.io.Serializable;

public class ProcessStatusForm implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ID_;
	String PROC_INST_ID_;
	String BUSINESS_KEY_;
	String PROC_DEF_ID_;
	public ProcessStatusForm(String iD_, String pROC_INST_ID_,
			String bUSINESS_KEY_, String pROC_DEF_ID_, String sTART_TIME_,
			String eND_TIME_, String dURATION_, String sTART_USER_ID_,
			String sTART_ACT_ID_, String eND_ACT_ID_,
			String sUPER_PROCESS_INSTANCE_ID_, String dELETE_REASON_,
			String tENANT_ID_, String nAME_) {
		super();
		this.ID_ = iD_;
		this.PROC_INST_ID_ = pROC_INST_ID_;
		this.BUSINESS_KEY_ = bUSINESS_KEY_;
		this.PROC_DEF_ID_ = pROC_DEF_ID_;
		this.START_TIME_ = sTART_TIME_;
		this.END_TIME_ = eND_TIME_;
		this.DURATION_ = dURATION_;
		this.START_USER_ID_ = sTART_USER_ID_;
		this.START_ACT_ID_ = sTART_ACT_ID_;
		this.END_ACT_ID_ = eND_ACT_ID_;
		this.SUPER_PROCESS_INSTANCE_ID_ = sUPER_PROCESS_INSTANCE_ID_;
		this.DELETE_REASON_ = dELETE_REASON_;
		this.TENANT_ID_ = tENANT_ID_;
		this.NAME_ = nAME_;
	}

	public String getID_() {
		return this.ID_;
	}

	public void setID_(String iD_) {
		this.ID_ = iD_;
	}

	public String getPROC_INST_ID_() {
		return this.PROC_INST_ID_;
	}

	public void setPROC_INST_ID_(String pROC_INST_ID_) {
		this.PROC_INST_ID_ = pROC_INST_ID_;
	}

	public String getBUSINESS_KEY_() {
		return this.BUSINESS_KEY_;
	}

	public void setBUSINESS_KEY_(String bUSINESS_KEY_) {
		this.BUSINESS_KEY_ = bUSINESS_KEY_;
	}

	public String getPROC_DEF_ID_() {
		return this.PROC_DEF_ID_;
	}

	public void setPROC_DEF_ID_(String pROC_DEF_ID_) {
		this.PROC_DEF_ID_ = pROC_DEF_ID_;
	}

	public String getSTART_TIME_() {
		return this.START_TIME_;
	}

	public void setSTART_TIME_(String sTART_TIME_) {
		this.START_TIME_ = sTART_TIME_;
	}

	public String getEND_TIME_() {
		return this.END_TIME_;
	}

	public void setEND_TIME_(String eND_TIME_) {
		this.END_TIME_ = eND_TIME_;
	}

	public String getDURATION_() {
		return this.DURATION_;
	}

	public void setDURATION_(String dURATION_) {
		this.DURATION_ = dURATION_;
	}

	public String getSTART_USER_ID_() {
		return this.START_USER_ID_;
	}

	public void setSTART_USER_ID_(String sTART_USER_ID_) {
		this.START_USER_ID_ = sTART_USER_ID_;
	}

	public String getSTART_ACT_ID_() {
		return this.START_ACT_ID_;
	}

	public void setSTART_ACT_ID_(String sTART_ACT_ID_) {
		this.START_ACT_ID_ = sTART_ACT_ID_;
	}

	public String getEND_ACT_ID_() {
		return this.END_ACT_ID_;
	}

	public void setEND_ACT_ID_(String eND_ACT_ID_) {
		this.END_ACT_ID_ = eND_ACT_ID_;
	}

	public String getSUPER_PROCESS_INSTANCE_ID_() {
		return this.SUPER_PROCESS_INSTANCE_ID_;
	}

	public void setSUPER_PROCESS_INSTANCE_ID_(String sUPER_PROCESS_INSTANCE_ID_) {
		this.SUPER_PROCESS_INSTANCE_ID_ = sUPER_PROCESS_INSTANCE_ID_;
	}

	public String getDELETE_REASON_() {
		return this.DELETE_REASON_;
	}

	public void setDELETE_REASON_(String dELETE_REASON_) {
		this.DELETE_REASON_ = dELETE_REASON_;
	}

	public String getTENANT_ID_() {
		return this.TENANT_ID_;
	}

	public void setTENANT_ID_(String tENANT_ID_) {
		this.TENANT_ID_ = tENANT_ID_;
	}

	public String getNAME_() {
		return this.NAME_;
	}

	public void setNAME_(String nAME_) {
		this.NAME_ = nAME_;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	String START_TIME_;
	String END_TIME_;
	String DURATION_; 
	String START_USER_ID_; 
	String START_ACT_ID_;
	String END_ACT_ID_;
	String SUPER_PROCESS_INSTANCE_ID_; 
	String DELETE_REASON_;
	String TENANT_ID_;
	String NAME_;



@Override
public String toString() {
	return "ProcessStatusForm [ID_=" + ID_ + ", PROC_INST_ID_=" + PROC_INST_ID_
			+ ", BUSINESS_KEY_=" + BUSINESS_KEY_ + ", PROC_DEF_ID_="
			+ PROC_DEF_ID_ + ", START_TIME_=" + START_TIME_ + ", END_TIME_="
			+ END_TIME_ + ", DURATION_=" + DURATION_ + ", START_USER_ID_="
			+ START_USER_ID_ + ", START_ACT_ID_=" + START_ACT_ID_
			+ ", END_ACT_ID_=" + END_ACT_ID_ + ", SUPER_PROCESS_INSTANCE_ID_="
			+ SUPER_PROCESS_INSTANCE_ID_ + ", DELETE_REASON_=" + DELETE_REASON_
			+ ", TENANT_ID_=" + TENANT_ID_ + ", NAME_=" + NAME_ + "]";
}

public ProcessStatusForm() {
	super();
	// TODO Auto-generated constructor stub
}

}
