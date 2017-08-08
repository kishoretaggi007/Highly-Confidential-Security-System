package com.hcss.bean;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class DrivingLicenseTO extends ActionForm{
	private int liesenceid;
	private int useridref;
	private String liesenceno;
	private String vechicleType;
	private Date issuedate;
	private Date validity;
	private String vehicleno;
	private String issueddate;
	private String validdate;
	public int getLiesenceid() {
		return liesenceid;
	}
	public void setLiesenceid(int liesenceid) {
		this.liesenceid = liesenceid;
	}
	public int getUseridref() {
		return useridref;
	}
	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}
	public String getLiesenceno() {
		return liesenceno;
	}
	public void setLiesenceno(String liesenceno) {
		this.liesenceno = liesenceno;
	}
	public String getVechicleType() {
		return vechicleType;
	}
	public void setVechicleType(String vechicleType) {
		this.vechicleType = vechicleType;
	}
	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}
	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public Date getValidity() {
		return validity;
	}
	public String getIssueddate() {
		return issueddate;
	}
	public void setIssueddate(String issueddate) {
		this.issueddate = issueddate;
	}
	public String getValiddate() {
		return validdate;
	}
	public void setValiddate(String validdate) {
		this.validdate = validdate;
	}
	
}
