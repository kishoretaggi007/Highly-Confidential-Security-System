package com.hcss.servicei;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import com.hcss.bean.LoginTO;
import com.hcss.bean.ProfileTO;
import com.hcss.bean.UserProfileFormBean;
import com.hcss.exception.ConnectionException;
import com.hcss.exception.LoginException;

public interface SecurityServiceI {

	public LoginTO loginCheck(LoginTO loginTO) throws LoginException,
			ConnectionException;

	public boolean changePass(LoginTO oginTO) throws ConnectionException;

	public boolean passwordRecovery(ProfileTO pro) throws ConnectionException;

	public boolean newUserRegistration(UserProfileFormBean userProfileFormBean)
			throws ConnectionException, FileNotFoundException, IOException;

	public String checkUser(String loginid) throws ConnectionException;

	public boolean newPassword(LoginTO loginTO) throws ConnectionException;

	public UserProfileFormBean viewUserProfile(int userid, String path)
			throws ConnectionException;

	public Vector<UserProfileFormBean> viewUserList(String user, String path)
			throws ConnectionException;

	public boolean deleteUsers(int userid) throws ConnectionException;

	public boolean updateUserStatus(int userid) throws ConnectionException;

	public boolean updateUserProfile(UserProfileFormBean userProfileFormBean)
			throws ConnectionException, FileNotFoundException;
}
