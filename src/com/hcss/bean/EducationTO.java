package com.hcss.bean;

import java.sql.Blob;

import org.apache.struts.action.ActionForm;

public class EducationTO extends ActionForm {

	private int educationid;
	private int useridref;
	private String educationname;
	private String hallticketno;
	private String yearofpass;
	private int totalmarks;
	private int aggregation;
	private float percentage;
	private String scanedmemo1;
	private Blob scanedmemo;
	private String memotype;

	public int getEducationid() {
		return educationid;
	}

	public void setEducationid(int educationid) {
		this.educationid = educationid;
	}

	public int getUseridref() {
		return useridref;
	}

	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}

	public String getEducationname() {
		return educationname;
	}

	public void setEducationname(String educationname) {
		this.educationname = educationname;
	}

	public String getHallticketno() {
		return hallticketno;
	}

	public void setHallticketno(String hallticketno) {
		this.hallticketno = hallticketno;
	}

	public String getYearofpass() {
		return yearofpass;
	}

	public void setYearofpass(String yearofpass) {
		this.yearofpass = yearofpass;
	}

	public int getTotalmarks() {
		return totalmarks;
	}

	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}

	public int getAggregation() {
		return aggregation;
	}

	public void setAggregation(int aggregation) {
		this.aggregation = aggregation;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getMemotype() {
		return memotype;
	}

	public void setMemotype(String memotype) {
		this.memotype = memotype;
	}

	public String getScanedmemo1() {
		return scanedmemo1;
	}

	public void setScanedmemo1(String scanedmemo1) {
		this.scanedmemo1 = scanedmemo1;
	}

	public Blob getScanedmemo() {
		return scanedmemo;
	}

	public void setScanedmemo(Blob scanedmemo) {
		this.scanedmemo = scanedmemo;
	}
}
