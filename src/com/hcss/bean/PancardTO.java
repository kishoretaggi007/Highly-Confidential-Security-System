package com.hcss.bean;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class PancardTO extends ActionForm {
	private int panid;
	private int useridref;
	private String accname;
	private String pancardno;
	private String issuedate;
	private Date issueddate;
	public int getPanid() {
		return panid;
	}
	public void setPanid(int panid) {
		this.panid = panid;
	}
	public int getUseridref() {
		return useridref;
	}
	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public String getPancardno() {
		return pancardno;
	}
	public void setPancardno(String pancardno) {
		this.pancardno = pancardno;
	}
	public String getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}
	public Date getIssueddate() {
		return issueddate;
	}
	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}

}
