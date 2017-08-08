package com.hcss.serviceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import com.hcss.bean.LoginTO;
import com.hcss.bean.ProfileTO;
import com.hcss.bean.UserProfileFormBean;
import com.hcss.daoI.SecurityDaoI;
import com.hcss.daoImpl.SecurityDaoImpl;
import com.hcss.exception.ConnectionException;
import com.hcss.exception.LoginException;
import com.hcss.servicei.SecurityServiceI;

public class SecurityServiceImpl implements SecurityServiceI {

	String logintype = "";
	boolean flag = false;
	Vector<ProfileTO> vpro = null;
	SecurityDaoI securityDaoI = new SecurityDaoImpl();

	public LoginTO loginCheck(LoginTO loginTO) throws LoginException,
			ConnectionException {
		return securityDaoI.loginCheck(loginTO);
	}

	public boolean changePass(LoginTO loginTO) throws ConnectionException {
		return securityDaoI.changePass(loginTO);
	}

	public boolean passwordRecovery(ProfileTO pro) throws ConnectionException {
		return securityDaoI.passwordRecovery(pro);
	}

	public boolean newUserRegistration(UserProfileFormBean userProfileFormBean)
			throws ConnectionException, IOException {
		return securityDaoI.newUserRegistration(userProfileFormBean);
	}

	public String checkUser(String loginid) throws ConnectionException {
		return securityDaoI.checkUser(loginid);
	}

	public boolean newPassword(LoginTO loginTO) throws ConnectionException {
		return securityDaoI.newPassword(loginTO);
	}

	public UserProfileFormBean viewUserProfile(int userid, String path)
			throws ConnectionException {
		return securityDaoI.viewUserProfile(userid, path);
	}

	public Vector<UserProfileFormBean> viewUserList(String user, String path)
			throws ConnectionException {
		return securityDaoI.viewUserList(user, path);
	}

	public boolean deleteUsers(int userid) throws ConnectionException {
		return securityDaoI.deleteUsers(userid);
	}

	public boolean updateUserStatus(int userid) throws ConnectionException {
		return securityDaoI.updateUserStatus(userid);
	}

	public boolean updateUserProfile(UserProfileFormBean userProfileFormBean)
			throws ConnectionException, FileNotFoundException {
		return securityDaoI.updateUserProfile(userProfileFormBean);
	}
}
