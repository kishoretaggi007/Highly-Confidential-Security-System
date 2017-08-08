package com.hcss.bean;

import org.apache.struts.action.ActionForm;

public class ProfileTO extends ActionForm{

	// personaldetails
	private int userid;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String registerDate;
	private String securityQestion;
	private String securityanswer;
	private String photograph;
	private String emailId;
	private String gender;
	private String status;
	private String ownquest;
	private String loginid;

	
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getSecurityQestion() {
		return securityQestion;
	}

	public void setSecurityQestion(String securityQestion) {
		this.securityQestion = securityQestion;
	}

	public String getSecurityanswer() {
		return securityanswer;
	}

	public void setSecurityanswer(String securityanswer) {
		this.securityanswer = securityanswer;
	}

	public String getPhotograph() {
		return photograph;
	}

	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwnquest() {
		return ownquest;
	}

	public void setOwnquest(String ownquest) {
		this.ownquest = ownquest;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

}
