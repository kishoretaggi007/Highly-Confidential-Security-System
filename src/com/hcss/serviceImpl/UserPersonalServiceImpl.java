package com.hcss.serviceImpl;

import java.util.Vector;

import com.hcss.bean.BankTO;
import com.hcss.bean.DrivingLicenseTO;
import com.hcss.bean.EducationTO;
import com.hcss.bean.InsuranceTO;
import com.hcss.bean.MailsTO;
import com.hcss.bean.PancardTO;
import com.hcss.bean.PassportTO;
import com.hcss.bean.UserImpFilesTO;
import com.hcss.daoI.UserPersonalDaoI;
import com.hcss.daoImpl.UserPersonalDaoImpl;
import com.hcss.exception.ConnectionException;
import com.hcss.servicei.UserPersonalServiceI;

public class UserPersonalServiceImpl implements UserPersonalServiceI {

	UserPersonalDaoI userPersonalDaoI = new UserPersonalDaoImpl();

	public boolean addEducationDetails(EducationTO educationTO)
			throws ConnectionException {
		String[] extension = educationTO.getScanedmemo1().split("\\.");
		int i;
		for (i = 0; i < extension.length; i++) {
			educationTO.setMemotype(extension[i]);
		}
		return userPersonalDaoI.addEducationDetails(educationTO);
	}

	public boolean addBankDetails(BankTO bankTO) throws ConnectionException {
		return userPersonalDaoI.addBankDetails(bankTO);
	}

	public boolean addUserImpFiles(UserImpFilesTO userImpFilesTO)
			throws ConnectionException {
		String[] extension = userImpFilesTO.getFiledatapath().split("\\.");
		int i;
		for (i = 0; i < extension.length; i++) {
			userImpFilesTO.setFiletype(extension[i]);
		}
		return userPersonalDaoI.addUserImpFiles(userImpFilesTO);
	}

	public boolean addInsuranceDetails(InsuranceTO insuranceTO)
			throws ConnectionException {
		return userPersonalDaoI.addInsuranceDetails(insuranceTO);
	}

	public boolean addLicenseDetails(DrivingLicenseTO drivingLicenseTO)
			throws ConnectionException {
		return userPersonalDaoI.addLicenseDetails(drivingLicenseTO);
	}

	public boolean addMailsDetails(MailsTO mailsTO) throws ConnectionException {
		return userPersonalDaoI.addMailsDetails(mailsTO);
	}

	public boolean addPancardDetails(PancardTO pancardTO)
			throws ConnectionException {
		return userPersonalDaoI.addPancardDetails(pancardTO);
	}

	public boolean addPassportDetails(PassportTO pancardTO)
			throws ConnectionException {
		return userPersonalDaoI.addPassportDetails(pancardTO);
	}

	public Vector<UserImpFilesTO> viewUserImpFiles(String path, int userid)
			throws ConnectionException {
		return userPersonalDaoI.viewUserImpFiles(path, userid);
	}

	public Vector<PancardTO> viewPancardDetails(int userid)
			throws ConnectionException {
		return userPersonalDaoI.viewPancardDetails(userid);
	}

	public Vector<PassportTO> viewPassportDetails(int userid)
			throws ConnectionException {
		return userPersonalDaoI.viewPassportDetails(userid);
	}

	public Vector<MailsTO> viewMailsDetails(int userid)
			throws ConnectionException {
		return userPersonalDaoI.viewMailsDetails(userid);
	}

	public Vector<DrivingLicenseTO> viewLicenseDetails(int userid)
			throws ConnectionException {
		return userPersonalDaoI.viewLicenseDetails(userid);
	}

	public Vector<InsuranceTO> viewInsurenceDetails(int userid)
			throws ConnectionException {
		return userPersonalDaoI.viewInsurenceDetails(userid);
	}

	public Vector<BankTO> viewBankDetails(int userid)
			throws ConnectionException {
		return userPersonalDaoI.viewBankDetails(userid);
	}

	public Vector<EducationTO> viewEducationDetails(String path, int userid)
			throws ConnectionException {
		return userPersonalDaoI.viewEducationDetails(path, userid);
	}

	public boolean deleteItem(int record, String delete)
			throws ConnectionException {
		return userPersonalDaoI.deleteItem(record, delete);
	}

	public EducationTO updateViewEducationDetails(String path, int educationid)
			throws ConnectionException {
		return userPersonalDaoI.updateViewEducationDetails(path, educationid);
	}

	public boolean updateEducationDetails(EducationTO educationTO)
			throws ConnectionException {
		System.out.println(educationTO.getScanedmemo1());
		String[] extension = educationTO.getScanedmemo1().split("\\.");
		int i;
		for (i = 0; i < extension.length; i++) {
			System.out.println(extension[i]);
			educationTO.setMemotype(extension[i]);
		}
		return userPersonalDaoI.updateEducationDetails(educationTO);
	}

	public BankTO updateViewBankDetails(int bankid) throws ConnectionException {
		return userPersonalDaoI.updateViewBankDetails(bankid);
	}

	public boolean updateBankDetails(BankTO bankTO) throws ConnectionException {
		return userPersonalDaoI.updateBankDetails(bankTO);
	}

	public MailsTO viewUpdateMailsDetails(int mailid)
			throws ConnectionException {
		return userPersonalDaoI.viewUpdateMailsDetails(mailid);
	}

	public boolean updateMailsDetails(MailsTO mailsTO)
			throws ConnectionException {
		return userPersonalDaoI.updateMailsDetails(mailsTO);
	}

	public PassportTO viewUpdatePassPortDetails(int passportid)
			throws ConnectionException {
		return userPersonalDaoI.viewUpdatePassPortDetails(passportid);
	}

	public boolean updatePassportDetails(PassportTO passportTO)
			throws ConnectionException {
		return userPersonalDaoI.updatePassportDetails(passportTO);
	}

	public InsuranceTO viewUpdateInsurenceDetails(int insuranceid)
			throws ConnectionException {
		return userPersonalDaoI.viewUpdateInsurenceDetails(insuranceid);
	}

	public UserImpFilesTO viewUpdateUserFiles(String path, int fileid)
			throws ConnectionException {
		// TODO Auto-generated method stub
		return userPersonalDaoI.viewUpdateUserFiles(path, fileid);
	}

	public PancardTO viewUpdatePanDetails(int panid) throws ConnectionException {
		return userPersonalDaoI.viewUpdatePanDetails(panid);
	}

	public DrivingLicenseTO viewUpdateLicenseDetails(int licenseid)
			throws ConnectionException {
		return userPersonalDaoI.viewUpdateLicenseDetails(licenseid);
	}

	public boolean updatePancardDetails(PancardTO pancardTO)
			throws ConnectionException {
		return userPersonalDaoI.updatePancardDetails(pancardTO);
	}

	public boolean updateLicenseDetails(DrivingLicenseTO drivingLicenseTO)
			throws ConnectionException {
		return userPersonalDaoI.updateLicenseDetails(drivingLicenseTO);

	}

	public boolean updateInsurenceDetails(InsuranceTO insuranceTO)
			throws ConnectionException {
		return userPersonalDaoI.updateInsurenceDetails(insuranceTO);

	}

	public boolean updateUserFilesDetails(UserImpFilesTO userImpFilesTO)
			throws ConnectionException {
		String[] extension = userImpFilesTO.getFiledatapath().split("\\.");
		int i;
		for (i = 0; i < extension.length; i++) {
				userImpFilesTO.setFiletype(extension[i]);
		}
		return userPersonalDaoI.addUserImpFiles(userImpFilesTO);
	}
}
