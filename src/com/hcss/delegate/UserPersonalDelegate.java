package com.hcss.delegate;

import java.util.Vector;

import com.hcss.bean.BankTO;
import com.hcss.bean.DrivingLicenseTO;
import com.hcss.bean.EducationTO;
import com.hcss.bean.InsuranceTO;
import com.hcss.bean.MailsTO;
import com.hcss.bean.PancardTO;
import com.hcss.bean.PassportTO;
import com.hcss.bean.UserImpFilesTO;
import com.hcss.exception.ConnectionException;
import com.hcss.serviceImpl.UserPersonalServiceImpl;
import com.hcss.servicei.UserPersonalServiceI;

public class UserPersonalDelegate {

	UserPersonalServiceI userPersonalServiceI = new UserPersonalServiceImpl();

	public boolean addEducationDetails(EducationTO educationTO)
			throws ConnectionException {
		return userPersonalServiceI.addEducationDetails(educationTO);
	}

	public boolean updateEducationDetails(EducationTO educationTO)
			throws ConnectionException {
		return userPersonalServiceI.updateEducationDetails(educationTO);
	}

	public boolean addBankDetails(BankTO bankTO) throws ConnectionException {
		return userPersonalServiceI.addBankDetails(bankTO);
	}

	public boolean updateBankDetails(BankTO bankTO) throws ConnectionException {
		return userPersonalServiceI.updateBankDetails(bankTO);
	}

	public boolean addUserImpFiles(UserImpFilesTO userImpFilesTO)
			throws ConnectionException {
		return userPersonalServiceI.addUserImpFiles(userImpFilesTO);
	}

	public boolean addInsuranceDetails(InsuranceTO insuranceTO)
			throws ConnectionException {
		return userPersonalServiceI.addInsuranceDetails(insuranceTO);
	}

	public boolean addLicenseDetails(DrivingLicenseTO drivingLicenseTO)
			throws ConnectionException {
		return userPersonalServiceI.addLicenseDetails(drivingLicenseTO);
	}

	public boolean addMailsDetails(MailsTO mailsTO) throws ConnectionException {
		return userPersonalServiceI.addMailsDetails(mailsTO);
	}

	public boolean addPancardDetails(PancardTO pancardTO)
			throws ConnectionException {
		return userPersonalServiceI.addPancardDetails(pancardTO);
	}

	public boolean addPassportDetails(PassportTO pancardTO)
			throws ConnectionException {
		return userPersonalServiceI.addPassportDetails(pancardTO);
	}

	public Vector<UserImpFilesTO> viewUserImpFiles(String path, int userid)
			throws ConnectionException {
		return userPersonalServiceI.viewUserImpFiles(path, userid);
	}

	public Vector<PancardTO> viewPancardDetails(int userid)
			throws ConnectionException {
		return userPersonalServiceI.viewPancardDetails(userid);
	}

	public Vector<PassportTO> viewPassportDetails(int userid)
			throws ConnectionException {
		return userPersonalServiceI.viewPassportDetails(userid);
	}

	public Vector<MailsTO> viewMailsDetails(int userid)
			throws ConnectionException {
		return userPersonalServiceI.viewMailsDetails(userid);
	}

	public Vector<DrivingLicenseTO> viewLicenseDetails(int userid)
			throws ConnectionException {
		return userPersonalServiceI.viewLicenseDetails(userid);
	}

	public Vector<InsuranceTO> viewInsurenceDetails(int userid)
			throws ConnectionException {
		return userPersonalServiceI.viewInsurenceDetails(userid);
	}

	public Vector<BankTO> viewBankDetails(int userid)
			throws ConnectionException {
		return userPersonalServiceI.viewBankDetails(userid);
	}

	public Vector<EducationTO> viewEducationDetails(String path, int userid)
			throws ConnectionException {
		return userPersonalServiceI.viewEducationDetails(path, userid);
	}

	public EducationTO updateViewEducationDetails(String path, int educationid)
			throws ConnectionException {
		return userPersonalServiceI.updateViewEducationDetails(path,
				educationid);
	}

	public MailsTO viewUpdateMailsDetails(int mailid)
			throws ConnectionException {
		return userPersonalServiceI.viewUpdateMailsDetails(mailid);
	}

	public BankTO updateViewBankDetails(int bankid) throws ConnectionException {
		return userPersonalServiceI.updateViewBankDetails(bankid);
	}

	public boolean deleteItem(int record, String delete)
			throws ConnectionException {
		return userPersonalServiceI.deleteItem(record, delete);
	}

	public boolean updateMailsDetails(MailsTO mailsTO)
			throws ConnectionException {
		return userPersonalServiceI.updateMailsDetails(mailsTO);
	}

	public PassportTO viewUpdatePassPortDetails(int passportid)
			throws ConnectionException {
		return userPersonalServiceI.viewUpdatePassPortDetails(passportid);
	}

	public boolean updatePassportDetails(PassportTO passportTO)
			throws ConnectionException {
		return userPersonalServiceI.updatePassportDetails(passportTO);
	}

	public UserImpFilesTO viewUpdateUserFiles(String path, int fileid)
			throws ConnectionException {
		return userPersonalServiceI.viewUpdateUserFiles(path, fileid);
	}

	public InsuranceTO viewUpdateInsurenceDetails(int insuranceid)
			throws ConnectionException {
		return userPersonalServiceI.viewUpdateInsurenceDetails(insuranceid);
	}

	public PancardTO viewUpdatePanDetails(int panid) throws ConnectionException {
		return userPersonalServiceI.viewUpdatePanDetails(panid);
	}

	public DrivingLicenseTO viewUpdateLicenseDetails(int licenseid)
			throws ConnectionException {
		return userPersonalServiceI.viewUpdateLicenseDetails(licenseid);
	}

	public boolean updatePancardDetails(PancardTO pancardTO)
			throws ConnectionException {
		return userPersonalServiceI.updatePancardDetails(pancardTO);
	}

	public boolean updateLicenseDetails(DrivingLicenseTO drivingLicenseTO)
			throws ConnectionException {
		return userPersonalServiceI.updateLicenseDetails(drivingLicenseTO);
	}

	public boolean updateInsurenceDetails(InsuranceTO insuranceTO)
			throws ConnectionException {
		return userPersonalServiceI.updateInsurenceDetails(insuranceTO);
	}

	public boolean updateUserFilesDetails(UserImpFilesTO userImpFilesTO)
			throws ConnectionException {
		return userPersonalServiceI.updateUserFilesDetails(userImpFilesTO);
	}

}
