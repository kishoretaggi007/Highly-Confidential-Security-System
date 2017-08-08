package com.hcss.servicei;

import java.util.Vector;

import com.hcss.bean.BankTO;
import com.hcss.bean.DrivingLicenseTO;
import com.hcss.bean.EducationTO;
import com.hcss.bean.InsuranceTO;
import com.hcss.bean.MailsTO;
import com.hcss.bean.PancardTO;
import com.hcss.bean.PassportTO;
import com.hcss.bean.UserImpFilesTO;
import com.hcss.bean.UserProfileFormBean;
import com.hcss.exception.ConnectionException;

public interface UserPersonalServiceI {

	public boolean addEducationDetails(EducationTO educationTO)
			throws ConnectionException;

	public boolean addBankDetails(BankTO bankTO) throws ConnectionException;

	public boolean addUserImpFiles(UserImpFilesTO userImpFilesTO)
			throws ConnectionException;

	public boolean addInsuranceDetails(InsuranceTO insuranceTO)
			throws ConnectionException;

	public boolean addLicenseDetails(DrivingLicenseTO drivingLicenseTO)
			throws ConnectionException;

	public boolean addMailsDetails(MailsTO mailsTO) throws ConnectionException;

	public boolean addPancardDetails(PancardTO pancardTO)
			throws ConnectionException;

	public boolean addPassportDetails(PassportTO pancardTO)
			throws ConnectionException;

	public Vector<UserImpFilesTO> viewUserImpFiles(String path, int userid)
			throws ConnectionException;

	public Vector<PancardTO> viewPancardDetails(int userid)
			throws ConnectionException;

	public Vector<PassportTO> viewPassportDetails(int userid)
			throws ConnectionException;

	public Vector<MailsTO> viewMailsDetails(int userid)
			throws ConnectionException;

	public Vector<DrivingLicenseTO> viewLicenseDetails(int userid)
			throws ConnectionException;

	public Vector<InsuranceTO> viewInsurenceDetails(int userid)
			throws ConnectionException;

	public Vector<BankTO> viewBankDetails(int userid)
			throws ConnectionException;

	public Vector<EducationTO> viewEducationDetails(String path, int userid)
			throws ConnectionException;

	public boolean deleteItem(int record, String delete)
			throws ConnectionException;

	public EducationTO updateViewEducationDetails(String path, int educationid)
			throws ConnectionException;

	public boolean updateEducationDetails(EducationTO educationTO)
			throws ConnectionException;

	public BankTO updateViewBankDetails(int bankid) throws ConnectionException;

	public boolean updateBankDetails(BankTO bankTO) throws ConnectionException;

	public MailsTO viewUpdateMailsDetails(int mailid)
			throws ConnectionException;

	public boolean updateMailsDetails(MailsTO mailsTO)
			throws ConnectionException;

	public PassportTO viewUpdatePassPortDetails(int passportid)
			throws ConnectionException;

	public boolean updatePassportDetails(PassportTO passportTO)
			throws ConnectionException;

	public UserImpFilesTO viewUpdateUserFiles(String path, int fileid)
			throws ConnectionException;

	public InsuranceTO viewUpdateInsurenceDetails(int insuranceid)
			throws ConnectionException;

	public PancardTO viewUpdatePanDetails(int panid) throws ConnectionException;

	public DrivingLicenseTO viewUpdateLicenseDetails(int licenseid)
			throws ConnectionException;

	public boolean updatePancardDetails(PancardTO pancardTO)
			throws ConnectionException;

	public boolean updateLicenseDetails(DrivingLicenseTO drivingLicenseTO)
			throws ConnectionException;

	public boolean updateInsurenceDetails(InsuranceTO insuranceTO)
			throws ConnectionException;

	public boolean updateUserFilesDetails(UserImpFilesTO userImpFilesTO)
			throws ConnectionException;

}
