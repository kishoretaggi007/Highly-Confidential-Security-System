package com.hcss.daoImpl;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Date;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hcss.bean.BankTO;
import com.hcss.bean.DrivingLicenseTO;
import com.hcss.bean.EducationTO;
import com.hcss.bean.InsuranceTO;
import com.hcss.bean.MailsTO;
import com.hcss.bean.PancardTO;
import com.hcss.bean.PassportTO;
import com.hcss.bean.UserImpFilesTO;
import com.hcss.dao.AbstractDBFactory;
import com.hcss.daoI.UserPersonalDaoI;
import com.hcss.exception.ConnectionException;
import com.hcss.utill.DateWrapper;

public class UserPersonalDaoImpl implements UserPersonalDaoI {
	boolean flag = false;
	Session session = null;

	public boolean addEducationDetails(EducationTO educationTo)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			System.out.println(educationTo.getScanedmemo1());
			File f = new File(educationTo.getScanedmemo1());
			byte[] personByteArray = new byte[(int) f.length()];
			Blob b = Hibernate.createBlob(personByteArray);

			FileInputStream fileInputStream = new FileInputStream(f);
			fileInputStream.read(personByteArray);
			fileInputStream.close();

			educationTo.setScanedmemo(b);
			session.save(educationTo);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean updateEducationDetails(EducationTO educationTo)
			throws ConnectionException {
		try {
			System.out.println("haiiiiiii");
			System.out.println(educationTo.getScanedmemo1());
			System.out.println(educationTo.getMemotype());
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			File f = new File(educationTo.getScanedmemo1());
			byte[] personByteArray = new byte[(int) f.length()];
			Blob b = Hibernate.createBlob(personByteArray);

			FileInputStream fileInputStream = new FileInputStream(f);
			fileInputStream.read(personByteArray);
			fileInputStream.close();

			educationTo.setScanedmemo(b);
			EducationTO educationTO2 = (EducationTO) session.get(
					EducationTO.class,
					new Integer(educationTo.getEducationid()));

			educationTO2.setEducationname(educationTo.getEducationname());
			educationTO2.setHallticketno(educationTo.getHallticketno());
			educationTO2.setYearofpass(educationTo.getYearofpass());
			educationTO2.setAggregation(educationTo.getAggregation());
			educationTO2.setPercentage(educationTo.getPercentage());
			educationTO2.setTotalmarks(educationTo.getTotalmarks());
			educationTO2.setScanedmemo(educationTo.getScanedmemo());
			educationTO2.setMemotype(educationTo.getMemotype());

			session.update(educationTO2);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean addBankDetails(BankTO bankTO) throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			bankTO.setBankid(1);
			session.save(bankTO);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean updateBankDetails(BankTO bankTO) throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			BankTO bankTO2 = (BankTO) session.get(BankTO.class, new Integer(
					bankTO.getBankid()));

			bankTO2.setBankName(bankTO.getBankName());
			bankTO2.setBranchName(bankTO.getBranchName());
			bankTO2.setAccnumber(bankTO.getAccnumber());
			bankTO2.setUsername(bankTO.getUsername());
			bankTO2.setPassword(bankTO.getPassword());
			bankTO2.setAtmcardno(bankTO.getAtmcardno());
			bankTO2.setAtmpin(bankTO.getAtmpin());
			bankTO2.setSiteurl(bankTO.getSiteurl());

			session.update(bankTO2);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean addUserImpFiles(UserImpFilesTO userImpFilesTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			File f = new File(userImpFilesTO.getFiledatapath());
			byte[] personByteArray = new byte[(int) f.length()];
			Blob b = Hibernate.createBlob(personByteArray);

			FileInputStream fileInputStream = new FileInputStream(f);
			fileInputStream.read(personByteArray);
			fileInputStream.close();
			userImpFilesTO.setFiledata(b);
			session.save(userImpFilesTO);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean addInsuranceDetails(InsuranceTO insuranceTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction transaction = session.beginTransaction();
			insuranceTO.setInsurenceid(1);
			insuranceTO.setPremiumdate(DateWrapper.parseDate(insuranceTO
					.getPremiumdate()));
			session.save(insuranceTO);
			transaction.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean addLicenseDetails(DrivingLicenseTO drivingLicenseTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction transaction = session.beginTransaction();
			drivingLicenseTO.setLiesenceid(1);
			drivingLicenseTO.setIssueddate(DateWrapper
					.parseDate(drivingLicenseTO.getIssueddate()));
			drivingLicenseTO.setValiddate(DateWrapper
					.parseDate(drivingLicenseTO.getValiddate()));
			session.save(drivingLicenseTO);
			transaction.commit();
			flag = true;
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean addMailsDetails(MailsTO mailsTO) throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			mailsTO.setMailid(1);
			session.save(mailsTO);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean addPancardDetails(PancardTO pancardTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			pancardTO.setPanid(1);
			System.out.println(pancardTO.getIssuedate());
			pancardTO.setIssuedate(DateWrapper.parseDate(pancardTO
					.getIssuedate()));
			session.save(pancardTO);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean addPassportDetails(PassportTO passportTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			passportTO.setPassportid(1);
			passportTO.setIssueddate(DateWrapper.parseDate(passportTO
					.getIssueddate()));
			passportTO.setValiditydate(DateWrapper.parseDate(passportTO
					.getValiditydate()));
			session.save(passportTO);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public Vector<UserImpFilesTO> viewUserImpFiles(String path, int userid)
			throws ConnectionException {
		UserImpFilesTO userImpFilesTO = null;
		Vector<UserImpFilesTO> vUserImpFilesTOs = new Vector<UserImpFilesTO>();
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from UserImpFilesTO userImpFiles where userImpFiles.useridref=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, userid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				String realpath = "";
				userImpFilesTO = (UserImpFilesTO) it.next();
				Blob b = userImpFilesTO.getFiledata();
				byte b1[] = b.getBytes(1, (int) b.length());
				realpath = path + "/" + userImpFilesTO.getFileid() + "."
						+ userImpFilesTO.getFiletype();
				OutputStream fout = new FileOutputStream(realpath);
				fout.write(b1);
				userImpFilesTO.setFiledatapath(realpath);
				vUserImpFilesTOs.add(userImpFilesTO);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return vUserImpFilesTOs;
	}

	public Vector<PancardTO> viewPancardDetails(int userid)
			throws ConnectionException {
		PancardTO pancardTO = null;
		Vector<PancardTO> vPancardTOs = new Vector<PancardTO>();
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from PancardTO pancard where pancard.useridref=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, userid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				pancardTO = (PancardTO) it.next();
				pancardTO.setIssuedate(pancardTO.getIssuedate()
						.substring(0, 10));
				vPancardTOs.add(pancardTO);
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return vPancardTOs;
	}

	public Vector<PassportTO> viewPassportDetails(int userid)
			throws ConnectionException {
		PassportTO passportTO = null;
		Vector<PassportTO> vPassportTOs = new Vector<PassportTO>();
		try {
			System.out.println("hsiiiiiii");
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from PassportTO passport where passport.useridref=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, userid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				passportTO = (PassportTO) it.next();
				passportTO.setIssueddate(DateWrapper.parseDate(passportTO
						.getIssueddate().substring(0, 10)));
				passportTO.setValiditydate(DateWrapper.parseDate(passportTO
						.getValiditydate().substring(0, 10)));
				vPassportTOs.add(passportTO);
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return vPassportTOs;
	}

	public Vector<MailsTO> viewMailsDetails(int userid)
			throws ConnectionException {
		MailsTO mailsTO = null;
		Vector<MailsTO> vmailsTOs = new Vector<MailsTO>();
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from MailsTO mail where mail.useridref=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, userid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				mailsTO = (MailsTO) it.next();
				vmailsTOs.add(mailsTO);
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return vmailsTOs;
	}

	public Vector<DrivingLicenseTO> viewLicenseDetails(int userid)
			throws ConnectionException {
		DrivingLicenseTO drivingLicenseTO = null;
		Vector<DrivingLicenseTO> vDrivingLicenseTOs = new Vector<DrivingLicenseTO>();
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from DrivingLicenseTO drivingLicense where drivingLicense.useridref=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, userid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				drivingLicenseTO = (DrivingLicenseTO) it.next();
				drivingLicenseTO.setIssueddate(DateWrapper
						.parseDate(drivingLicenseTO.getIssueddate().substring(
								0, 10)));
				drivingLicenseTO.setValiddate(DateWrapper
						.parseDate(drivingLicenseTO.getValiddate().substring(0,
								10)));
				vDrivingLicenseTOs.add(drivingLicenseTO);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return vDrivingLicenseTOs;
	}

	public Vector<InsuranceTO> viewInsurenceDetails(int userid)
			throws ConnectionException {
		InsuranceTO insuranceTO = null;
		Vector<InsuranceTO> vInsuranceTOs = new Vector<InsuranceTO>();
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from InsuranceTO insurance where insurance.useridref=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, userid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				insuranceTO = (InsuranceTO) it.next();
				insuranceTO.setPremiumdate(insuranceTO.getPremiumdate()
						.substring(0, 10));
				vInsuranceTOs.add(insuranceTO);
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return vInsuranceTOs;
	}

	public Vector<BankTO> viewBankDetails(int userid)
			throws ConnectionException {
		BankTO bankTO = null;
		Vector<BankTO> vBankTOs = new Vector<BankTO>();
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from BankTO bank where bank.useridref=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, userid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				bankTO = (BankTO) it.next();
				vBankTOs.add(bankTO);
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return vBankTOs;
	}

	public Vector<EducationTO> viewEducationDetails(String path, int userid)
			throws ConnectionException {
		EducationTO educationTO = null;
		Vector<EducationTO> vEducationTOs = new Vector<EducationTO>();
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from EducationTO education where education.useridref=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, userid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				String realpath = "";
				educationTO = (EducationTO) it.next();
				Blob b = educationTO.getScanedmemo();
				byte b1[] = b.getBytes(1, (int) b.length());
				realpath = path + "/" + educationTO.getEducationid() + "."
						+ educationTO.getMemotype();
				OutputStream fout = new FileOutputStream(realpath);
				fout.write(b1);
				educationTO.setScanedmemo1(realpath);
				vEducationTOs.add(educationTO);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return vEducationTOs;
	}

	public boolean deleteItem(int record, String delete)
			throws ConnectionException {
		boolean flag = false;
		String _HQL_DELETE = "";
		try {
			System.out.println(delete);
			session = AbstractDBFactory.getConnection();
			Transaction transaction = session.beginTransaction();
			if (delete.equalsIgnoreCase("education"))
				_HQL_DELETE = "delete from EducationTO where educationid = ?";
			else if (delete.equalsIgnoreCase("bank"))
				_HQL_DELETE = "delete from BankTO  where bankid = ?";
			else if (delete.equalsIgnoreCase("mails"))
				_HQL_DELETE = "delete from MailsTO  where mailid = ?";
			else if (delete.equalsIgnoreCase("passport"))
				_HQL_DELETE = "delete from PassportTO  where passportid = ?";
			else if (delete.equalsIgnoreCase("pancard"))
				_HQL_DELETE = "delete from PancardTO  where panid = ?";
			else if (delete.equalsIgnoreCase("insurance"))
				_HQL_DELETE = "delete from InsuranceTO  where insurenceid = ?";
			else if (delete.equalsIgnoreCase("license"))
				_HQL_DELETE = "delete from DrivingLicenseTO  where liesenceid = ?";
			else if (delete.equalsIgnoreCase("impfiles"))
				_HQL_DELETE = "delete from UserImpFilesTO  where fileid = ?";
			else {
			}
			Query query = session.createQuery(_HQL_DELETE);
			query.setInteger(0, record);
			int row = query.executeUpdate();
			transaction.commit();
			if (row > 0)
				flag = true;
			else
				flag = false;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public EducationTO updateViewEducationDetails(String path, int educationid)
			throws ConnectionException {
		EducationTO educationTO = null;
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from EducationTO education where education.educationid=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, educationid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				String realpath = "";
				educationTO = (EducationTO) it.next();
				Blob b = educationTO.getScanedmemo();
				byte b1[] = b.getBytes(1, (int) b.length());
				realpath = path + "/" + educationTO.getEducationid() + "."
						+ educationTO.getMemotype();
				OutputStream fout = new FileOutputStream(realpath);
				fout.write(b1);
				educationTO.setScanedmemo1(realpath);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return educationTO;
	}

	public BankTO updateViewBankDetails(int bankid) throws ConnectionException {
		BankTO bankTO = null;
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from BankTO bank where bank.bankid=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, bankid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				bankTO = (BankTO) it.next();
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return bankTO;
	}

	public MailsTO viewUpdateMailsDetails(int mailid)
			throws ConnectionException {
		MailsTO mailsTO = null;
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from MailsTO mail where mail.mailid=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, mailid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				mailsTO = (MailsTO) it.next();
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return mailsTO;
	}

	public boolean updateMailsDetails(MailsTO mailsTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			MailsTO mailsTO2 = (MailsTO) session.get(MailsTO.class,
					new Integer(mailsTO.getMailid()));
			mailsTO2.setSitename(mailsTO.getSitename());
			mailsTO2.setSiteurl(mailsTO.getSiteurl());
			mailsTO2.setMailidstring(mailsTO.getMailidstring());
			mailsTO2.setPassword(mailsTO.getPassword());
			session.update(mailsTO2);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public PassportTO viewUpdatePassPortDetails(int passportid)
			throws ConnectionException {
		PassportTO passportTO = null;
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from PassportTO passport where passport.passportid=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, passportid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				passportTO = (PassportTO) it.next();
				passportTO.setIssueddate(passportTO.getIssueddate().substring(
						0, 10));
				passportTO.setValiditydate(passportTO.getValiditydate()
						.substring(0, 10));
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return passportTO;
	}

	public boolean updatePassportDetails(PassportTO passportTO)
			throws ConnectionException {
		try {
			System.out.println("haiiiiiiiiiiiii");
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			PassportTO passportTO2 = (PassportTO) session.get(PassportTO.class,
					new Integer(passportTO.getPassportid()));
			passportTO2.setPassportno(passportTO.getPassportno());
			passportTO2.setIssued(passportTO.getIssued());
			passportTO2.setValiddate(passportTO.getValiddate());
			session.update(passportTO2);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public UserImpFilesTO viewUpdateUserFiles(String path, int fileid)
			throws ConnectionException {
		UserImpFilesTO userImpFilesTO = null;
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from UserImpFilesTO userImpFiles where userImpFiles.fileid=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, fileid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				String realpath = "";
				userImpFilesTO = (UserImpFilesTO) it.next();
				Blob b = userImpFilesTO.getFiledata();
				byte b1[] = b.getBytes(1, (int) b.length());
				realpath = path + "/" + userImpFilesTO.getFileid() + "."
						+ userImpFilesTO.getFiletype();
				OutputStream fout = new FileOutputStream(realpath);
				fout.write(b1);
				userImpFilesTO.setFiledatapath(realpath);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return userImpFilesTO;
	}

	public InsuranceTO viewUpdateInsurenceDetails(int insuranceid)
			throws ConnectionException {
		InsuranceTO insuranceTO = null;
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from InsuranceTO insurance where insurance.insurenceid=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, insuranceid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				insuranceTO = (InsuranceTO) it.next();
				insuranceTO.setPremiumdate(insuranceTO.getPremiumdate()
						.substring(0, 10));
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return insuranceTO;
	}

	public PancardTO viewUpdatePanDetails(int panid) throws ConnectionException {
		PancardTO pancardTO = null;
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from PancardTO pancard where pancard.panid=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, panid);

			for (Iterator it = query.iterate(); it.hasNext();) {
				pancardTO = (PancardTO) it.next();
				pancardTO.setIssuedate(pancardTO.getIssuedate()
						.substring(0, 10));
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return pancardTO;
	}

	public DrivingLicenseTO viewUpdateLicenseDetails(int licenseid)
			throws ConnectionException {
		DrivingLicenseTO drivingLicenseTO = null;
		Vector<DrivingLicenseTO> vDrivingLicenseTOs = new Vector<DrivingLicenseTO>();
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "from DrivingLicenseTO drivingLicense where drivingLicense.liesenceid=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger(0, licenseid);
			for (Iterator it = query.iterate(); it.hasNext();) {
				drivingLicenseTO = (DrivingLicenseTO) it.next();
				drivingLicenseTO.setIssueddate(DateWrapper
						.parseDate(drivingLicenseTO.getIssueddate().substring(
								0, 10)));
				drivingLicenseTO.setValiddate(DateWrapper
						.parseDate(drivingLicenseTO.getValiddate().substring(0,
								10)));

			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return drivingLicenseTO;
	}

	public boolean updatePancardDetails(PancardTO pancardTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			System.out.println(pancardTO.getIssuedate());
			PancardTO pancardTO2 = (PancardTO) session.get(PancardTO.class,
					new Integer(pancardTO.getPanid()));
			pancardTO2.setAccname(pancardTO.getAccname());
			pancardTO2.setPancardno(pancardTO.getPancardno());
			pancardTO2.setIssuedate(pancardTO.getIssuedate().substring(0, 10));

			session.save(pancardTO2);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean updateLicenseDetails(DrivingLicenseTO drivingLicenseTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			System.out.println(drivingLicenseTO.getValiddate());
			Transaction transaction = session.beginTransaction();
			DrivingLicenseTO drivingLicenseTO2 = (DrivingLicenseTO) session
					.get(DrivingLicenseTO.class, new Integer(drivingLicenseTO
							.getLiesenceid()));

			drivingLicenseTO2.setLiesenceno(drivingLicenseTO.getLiesenceno());
			drivingLicenseTO2.setVechicleType(drivingLicenseTO
					.getVechicleType());
			drivingLicenseTO2.setIssueddate(DateWrapper
					.parseDate(drivingLicenseTO.getIssueddate()));
			drivingLicenseTO2.setValiddate(DateWrapper
					.parseDate(drivingLicenseTO.getValiddate()));
			drivingLicenseTO2.setVehicleno(drivingLicenseTO.getVehicleno());

			session.update(drivingLicenseTO2);
			transaction.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean updateInsurenceDetails(InsuranceTO insuranceTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction transaction = session.beginTransaction();
			InsuranceTO insuranceTO2 = (InsuranceTO) session.get(
					InsuranceTO.class,
					new Integer(insuranceTO.getInsurenceid()));
			System.out.println();
			insuranceTO2.setCompanyname(insuranceTO.getCompanyname());
			insuranceTO2.setPolicyname(insuranceTO.getPolicyname());
			insuranceTO2.setPolicyno(insuranceTO.getPolicyno());
			insuranceTO2.setPremiumamount(insuranceTO.getPremiumamount());
			insuranceTO2.setPremiumdate(DateWrapper.parseDate(insuranceTO
					.getPremiumdate()));
			session.update(insuranceTO2);
			transaction.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;

	}

	public boolean updateUserFilesDetails(UserImpFilesTO userImpFilesTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();
			File f = new File(userImpFilesTO.getFiledatapath());
			byte[] personByteArray = new byte[(int) f.length()];
			Blob b = Hibernate.createBlob(personByteArray);

			FileInputStream fileInputStream = new FileInputStream(f);
			fileInputStream.read(personByteArray);
			fileInputStream.close();
			userImpFilesTO.setFiledata(b);

			UserImpFilesTO userImpFilesTO2 = (UserImpFilesTO) session.get(
					UserImpFilesTO.class, new Integer(userImpFilesTO
							.getFileid()));

			userImpFilesTO2.setFilename(userImpFilesTO.getFilename());
			userImpFilesTO2.setFiledescription(userImpFilesTO
					.getFiledescription());
			userImpFilesTO2.setFiledata(userImpFilesTO.getFiledata());
			session.update(userImpFilesTO2);
			tx.commit();
			flag = true;

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical problem occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

}
