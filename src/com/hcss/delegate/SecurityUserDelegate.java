package com.hcss.delegate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import com.hcss.bean.LoginTO;
import com.hcss.bean.ProfileTO;
import com.hcss.bean.UserProfileFormBean;
import com.hcss.exception.ConnectionException;
import com.hcss.exception.LoginException;
import com.hcss.serviceImpl.SecurityServiceImpl;
import com.hcss.servicei.SecurityServiceI;

public class SecurityUserDelegate {
	SecurityServiceI securityServiceI = new SecurityServiceImpl();
	Vector<ProfileTO> vpro = null;

	public LoginTO loginCheck(LoginTO loginTO) throws LoginException,
			ConnectionException {
		return securityServiceI.loginCheck(loginTO);
	}

	public boolean changePass(LoginTO loginTO) throws ConnectionException {
		return securityServiceI.changePass(loginTO);
	}

	public boolean passwordRecovery(ProfileTO profileTO)
			throws ConnectionException {
		return securityServiceI.passwordRecovery(profileTO);
	}

	public boolean newPassword(LoginTO loginTO) throws ConnectionException {
		return securityServiceI.newPassword(loginTO);
	}

	public boolean newUserRegistration(UserProfileFormBean userProfileFormBean)
			throws ConnectionException, IOException {
		return securityServiceI.newUserRegistration(userProfileFormBean);
	}

	public String checkUser(String loginid) throws ConnectionException {
		return securityServiceI.checkUser(loginid);
	}

	public UserProfileFormBean viewUserProfile(int userid, String path)
			throws ConnectionException {
		return securityServiceI.viewUserProfile(userid, path);
	}

	public Vector<UserProfileFormBean> viewUserList(String user, String path)
			throws ConnectionException {
		return securityServiceI.viewUserList(user, path);
	}

	public boolean deleteUsers(int userid) throws ConnectionException {
		return securityServiceI.deleteUsers(userid);
	}

	public boolean updateUserStatus(int userid) throws ConnectionException {
		return securityServiceI.updateUserStatus(userid);
	}
	public boolean updateUserProfile(UserProfileFormBean userProfileFormBean) throws ConnectionException, FileNotFoundException {
		return securityServiceI.updateUserProfile(userProfileFormBean);
	}
	
}
