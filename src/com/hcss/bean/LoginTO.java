package com.hcss.bean;

import org.apache.struts.action.ActionForm;

public class LoginTO extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int useridref;
	private String loginid;
	private String password;
	private String logintype;
	private String securityCode;
	private String newpassword;
	public int getUseridref() {
		return useridref;
	}
	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}
	public String getLoginid() {
		return loginid;
	}
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
}
