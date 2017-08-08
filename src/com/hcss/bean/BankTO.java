package com.hcss.bean;

import org.apache.struts.action.ActionForm;


public class BankTO extends ActionForm{
	
	
	private int useridref;
	private int  bankid;
	private String bankName;
	private String branchName;
	private String accnumber;
	private String username;
	private String password;
	private String atmcardno;
	private String atmpin;
	private String siteurl;
	public int getUseridref() {
		return useridref;
	}
	public void setUseridref(int useridref) {
		this.useridref = useridref;
	}
	
	public int getBankid() {
		return bankid;
	}
	public void setBankid(int bankid) {
		this.bankid = bankid;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAccnumber() {
		return accnumber;
	}
	public void setAccnumber(String accnumber) {
		this.accnumber = accnumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAtmcardno() {
		return atmcardno;
	}
	public void setAtmcardno(String atmcardno) {
		this.atmcardno = atmcardno;
	}
	public String getAtmpin() {
		return atmpin;
	}
	public void setAtmpin(String atmpin) {
		this.atmpin = atmpin;
	}
	public String getSiteurl() {
		return siteurl;
	}
	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl;
	}
	
	
	
	
}
