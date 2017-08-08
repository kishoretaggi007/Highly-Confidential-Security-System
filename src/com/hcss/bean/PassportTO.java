package com.hcss.bean;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class PassportTO extends ActionForm {
	private int passportid;
	private String passportno;
	private int useridref;
	private String issueddate;
	private String validitydate;
	private Date issued;
	private Date validdate;

	public int getPassportid() {
		return passportid;
	}

	public void setPassportid(int passportid) {
		this.passportid = passportid;
	}

	public String getPassportno() {
		return passportno;
	}

	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}

	public int getUseridref() {
		return useridref;
	}

	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}

	public String getIssueddate() {
		return issueddate;
	}

	public void setIssueddate(String issueddate) {
		this.issueddate = issueddate;
	}

	public String getValiditydate() {
		return validitydate;
	}

	public void setValiditydate(String validitydate) {
		this.validitydate = validitydate;
	}

	public Date getIssued() {
		return issued;
	}

	public void setIssued(Date issued) {
		this.issued = issued;
	}

	public Date getValiddate() {
		return validdate;
	}

	public void setValiddate(Date validdate) {
		this.validdate = validdate;
	}
}
