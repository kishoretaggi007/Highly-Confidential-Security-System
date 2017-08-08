package com.hcss.bean;

import org.apache.struts.action.ActionForm;

public class MailsTO extends ActionForm{
	private int mailid;
	private int useridref;
	private String sitename;
	private String siteurl;
	private String mailidstring;
	private String password;
	private String securityquestion;
	private String securityanswer;
	private String status;
	public int getMailid() {
		return mailid;
	}
	public void setMailid(int mailid) {
		this.mailid = mailid;
	}
	public int getUseridref() {
		return useridref;
	}
	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getSiteurl() {
		return siteurl;
	}
	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl;
	}
	public String getMailidstring() {
		return mailidstring;
	}
	public void setMailidstring(String mailidstring) {
		this.mailidstring = mailidstring;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecurityquestion() {
		return securityquestion;
	}
	public void setSecurityquestion(String securityquestion) {
		this.securityquestion = securityquestion;
	}
	public String getSecurityanswer() {
		return securityanswer;
	}
	public void setSecurityanswer(String securityanswer) {
		this.securityanswer = securityanswer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
