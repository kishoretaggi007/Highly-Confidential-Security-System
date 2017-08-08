package com.hcss.bean;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class InsuranceTO extends ActionForm{
	private int insurenceid;
	private int useridref;
	private String companyname;
	private String policyname;
	private String policyno;
	private String premiumamount;
	private String premiumdate;
	private Date policypremiumdate;
	
	public int getInsurenceid() {
		return insurenceid;
	}
	public void setInsurenceid(int insurenceid) {
		this.insurenceid = insurenceid;
	}
	public int getUseridref() {
		return useridref;
	}
	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getPolicyname() {
		return policyname;
	}
	public void setPolicyname(String policyname) {
		this.policyname = policyname;
	}
	public String getPolicyno() {
		return policyno;
	}
	public void setPolicyno(String policyno) {
		this.policyno = policyno;
	}
	public String getPremiumamount() {
		return premiumamount;
	}
	public void setPremiumamount(String premiumamount) {
		this.premiumamount = premiumamount;
	}
	public String getPremiumdate() {
		return premiumdate;
	}
	public void setPremiumdate(String premiumdate) {
		this.premiumdate = premiumdate;
	}
	public Date getPolicypremiumdate() {
		return policypremiumdate;
	}
	public void setPolicypremiumdate(Date policypremiumdate) {
		this.policypremiumdate = policypremiumdate;
	}

}
