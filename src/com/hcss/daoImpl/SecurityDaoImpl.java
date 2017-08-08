package com.hcss.daoImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hcss.bean.LoginTO;
import com.hcss.bean.PancardTO;
import com.hcss.bean.ProfileTO;
import com.hcss.bean.UserProfileFormBean;
import com.hcss.cryptutil.Decryption;
import com.hcss.dao.AbstractDBFactory;
import com.hcss.dao.SqlConstants;
import com.hcss.daoI.SecurityDaoI;
import com.hcss.exception.ConnectionException;
import com.hcss.exception.LoginException;
import com.hcss.utill.DateUtil;
import com.hcss.utill.DateWrapper;

public class SecurityDaoImpl implements SecurityDaoI {

	Connection con;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	boolean flag = false;
	Session session = null;

	/**
	 * The closeConnection method of the AttendanceDaoImpl Class. <br>
	 * 
	 * This method is called when to take Employee InTime.
	 * 
	 * @throws ConnectionException
	 *             if an error occurred
	 */
	public void closeConnection() throws ConnectionException {
		try {
			if (pstmt != null)
				pstmt.close();
			if (stmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException ex) {
			throw new ConnectionException(
					"Server Busy please Try after Sometine");
		}
	}

	/**
	 * The loginCheck method of the SecurityDaoImpl Class. <br>
	 * 
	 * This method is called when to loginCheck whether the user Authorised or
	 * not.
	 * 
	 * @param passing
	 *            Employeeid as one parameter.
	 * @throws SQLException
	 *             if an error occurred
	 * @throws NullPointerException
	 *             if an error occurred
	 * @return vector with logindetails depends upon operations.
	 * @throws LoginException
	 * @throws SQLException
	 */
	public LoginTO loginCheck(LoginTO loginTO) throws ConnectionException,
			LoginException {
		LoginTO loginTO2 = null;
		try {
			String encpassword = loginTO.getPassword();
			System.out.println(encpassword);
			String[] extension = encpassword.split(",");
			int[] a = new int[extension.length];
			int j;
			for (j = 0; j < extension.length; j++) {
				int abc = Integer.parseInt(extension[j]);
				a[j] = abc;
			}
			Decryption decryption = new Decryption();
			String decpassword = decryption.decrypt(a);
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "select login from LoginTO login,ProfileTO profile where loginid=? and password=? and login.useridref=profile.userid and profile.status='Active'";
			List query = session.createQuery(HQL_QUERY).setParameter(0,
					loginTO.getLoginid()).setString(1, decpassword).list();
			if (!query.equals("")) {
				Iterator iterator = query.iterator();
				loginTO2 = (LoginTO) iterator.next();
			}
		} catch (NoSuchElementException e) {
			throw new LoginException("Invalid UserName & Password");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return loginTO2;
	}

	/**
	 * The changePass method of the SecurityDaoImpl Class. <br>
	 * 
	 * This method is called when to changePassword details.
	 * 
	 * @param Passing
	 *            Profile bean as a one parameter.
	 * 
	 * @throws SQLException
	 *             if an error occurred
	 * @throws NullPointerException
	 *             if an error occurred
	 * @return boolean value true or false depends upon operations.
	 * @throws SQLException
	 */
	public boolean changePass(LoginTO loginTO) throws ConnectionException {
		boolean flag = false;
		try {
			String encpassword = loginTO.getNewpassword();
			System.out.println(encpassword);
			String[] extension = encpassword.split(",");
			int[] a = new int[extension.length];
			int j;
			for (j = 0; j < extension.length; j++) {
				int abc = Integer.parseInt(extension[j]);
				a[j] = abc;
			}
			Decryption decryption = new Decryption();
			String decpassword = decryption.decrypt(a);
			session = AbstractDBFactory.getConnection();
			Transaction transaction = session.beginTransaction();
			String HQL_QUERY = "update LoginTO set password=:password where loginid=:loginid and password=:oldpassword";
			Query query = session.createQuery(HQL_QUERY);
			query.setString("password", decpassword);
			query.setString("loginid", loginTO.getLoginid());
			query.setString("oldpassword", loginTO.getPassword());
			int result = query.executeUpdate();
			if (result > 0) {
				flag = true;
				transaction.commit();
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"server busy please try latter.......");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	/**
	 * The passwordRecovery method of the SecurityDaoImpl Class. <br>
	 * 
	 * This method is called when to Recover password details.
	 * 
	 * @param Passing
	 *            Profile bean as a one parameter.
	 * 
	 * @throws ConnectionException
	 *             if an error occurred
	 * @return boolean value true or false depends upon operations.
	 * @throws IOException
	 */

	public boolean newUserRegistration(UserProfileFormBean userProfileFormBean)
			throws ConnectionException, IOException {
		boolean flag = false;
		System.out.println(userProfileFormBean.getGender());
		System.out.println(userProfileFormBean.getLoginid());
		String encpassword = userProfileFormBean.getPassword();
		String[] extension = encpassword.split(",");
		int[] a = new int[extension.length];
		int j;
		for (j = 0; j < extension.length; j++) {
			int abc = Integer.parseInt(extension[j]);
			a[j] = abc;
		}
		Decryption decryption = new Decryption();
		String decpassword = decryption.decrypt(a);
		File file = new File(userProfileFormBean.getPhotograph());
		FileInputStream fileInputStream = new FileInputStream(file);

		try {
			session = AbstractDBFactory.getConnection();
			Transaction transaction = session.beginTransaction();
			Connection connection = session.connection();
			CallableStatement query = connection
					.prepareCall(" { call insertprocedure(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			query.setBinaryStream(1, fileInputStream, (int) file.length());
			query.setString(2, userProfileFormBean.getFirstName());
			query.setString(3, userProfileFormBean.getLastName());
			query.setString(4, DateUtil.parseDate(userProfileFormBean
					.getBirthDate()));
			if (userProfileFormBean.getSecurityQestion() == null)
				query.setString(5, userProfileFormBean.getOwnquest());
			query.setString(5, userProfileFormBean.getSecurityQestion());
			query.setString(6, userProfileFormBean.getSecurityanswer());
			query.setString(7, userProfileFormBean.getEmailId());
			query.setString(8, userProfileFormBean.getGender());
			query.setString(9, userProfileFormBean.getLoginid());
			query.setString(10, decpassword);
			query.setString(11, userProfileFormBean.getLogintype());
			query.setString(12, userProfileFormBean.getAddresstype());
			query.setString(13, userProfileFormBean.getHouseno());
			query.setString(14, userProfileFormBean.getStreet());
			query.setString(15, userProfileFormBean.getCity());
			query.setString(16, userProfileFormBean.getDistrict());
			query.setString(17, userProfileFormBean.getState());
			query.setString(18, userProfileFormBean.getCountry());
			query.setString(19, userProfileFormBean.getPincode());
			query.setString(20, userProfileFormBean.getPhoneno());
			query.setString(21, userProfileFormBean.getPhonetype());
			query.execute();
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"server busy please try latter.......");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean passwordRecovery(ProfileTO profileTO)
			throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			String HQL_QUERY = "select  login.password from ProfileTO profile,LoginTO login where login.loginid=? and profile.securityQestion=? and profile.securityanswer=?";
			Query query = session.createQuery(HQL_QUERY);
			query.setString(0, profileTO.getLoginid());
			if (profileTO.getSecurityQestion() == null)
				query.setString(1, profileTO.getOwnquest());
			else
				query.setString(1, profileTO.getSecurityQestion());
			query.setString(2, profileTO.getSecurityanswer());
			Iterator it = query.iterate();
			if (it.hasNext()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (HibernateException e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occered pls try later");
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	/**
	 * The forgetPass method of the SecurityDaoImpl Class. <br>
	 * 
	 * 
	 * @param Passing
	 *            Profile bean as a one parameter.
	 * 
	 * @throws ConnectionException
	 *             if an error occurred
	 * @return boolean value true or false depends upon operations.
	 */
	public boolean newPassword(LoginTO loginTO) throws ConnectionException {
		boolean flag = true;
		try {
			String encpassword = loginTO.getPassword();
			System.out.println(encpassword);
			String[] extension = encpassword.split(",");
			int[] a = new int[extension.length];
			int j;
			for (j = 0; j < extension.length; j++) {
				int abc = Integer.parseInt(extension[j]);
				a[j] = abc;
			}
			Decryption decryption = new Decryption();
			String decpassword = decryption.decrypt(a);
			session = AbstractDBFactory.getConnection();
			Transaction transaction = session.beginTransaction();
			String HQL_QUERY = "update LoginTO set password=:password where loginid=:loginid";
			Query query = session.createQuery(HQL_QUERY);
			query.setString("password", decpassword);
			query.setString("loginid", loginTO.getLoginid());
			int result = query.executeUpdate();
			if (result > 0) {
				flag = true;
				transaction.commit();
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"server busy please try latter.......");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public String checkUser(String loginid) throws ConnectionException {
		String username = "";
		try {
			LoginTO loginTO = null;
			session = AbstractDBFactory.getConnection();
			Query query = session.createQuery(SqlConstants.HQL_QUERY);
			query.setString(0, loginid);
			Iterator it = query.iterate();
			if (it.hasNext()) {
				username = (String) it.next();
				System.out.println(username);
			} else {
				username = "";
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
			username = "";
		} catch (HibernateException e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return username;
	}

	public UserProfileFormBean viewUserProfile(int userid, String path)
			throws ConnectionException {
		UserProfileFormBean userProfileFormBean = null;
		try {
			session = AbstractDBFactory.getConnection();
			Connection connection = session.connection();
			// String HQL_QUERY =
			// "select profile.userid,profile.firstName,profile.lastName,profile.birthDate,profile.emailId,profile.photograph,profile.gender,address.addressid,address.addresstype,address.houseno,address.street,address.city,address.district,address.state,address.country,address.pincode,address.pincode,address.phonetype,address.phoneno from  ProfileTO profile,AddressTO address where profile.userid=address.useridref  and profile.userid=?";
			String SQL_QUERY = "select profile.userid,profile.firstName,profile.lastName,profile.dob, profile.emailId,profile.photograph,profile.gender,address.addressid,address.addresstype,address.houseno,address.street,address.city,address.district,address.state,address.country,address.pincode,address.pincode,address.phonetype,address.phoneno from  userdetails profile,addresses address where profile.userid=address.useridref  and profile.userid=?";
			PreparedStatement preparedStatement = connection
					.prepareStatement(SQL_QUERY);
			/*
			 * Query query = session.createQuery(HQL_QUERY); query.setInteger(0,
			 * userid); for (Iterator it = query.iterate(); it.hasNext();) {
			 * Object[] row = (Object[]) it.next(); userProfileFormBean = new
			 * UserProfileFormBean();
			 * userProfileFormBean.setUserid((Integer)row[0]);
			 * userProfileFormBean.setFirstName((String)row[1]);
			 * userProfileFormBean.setFirstName((String)row[2]);
			 * userProfileFormBean.setFirstName((String)row[3]);
			 * userProfileFormBean.setFirstName((String)row[4]);
			 * System.out.println((Blob)row[5]); }
			 */
			preparedStatement.setInt(1, userid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String realpath = "";
				userProfileFormBean = new UserProfileFormBean();
				userProfileFormBean.setUserid(resultSet.getInt(1));
				userProfileFormBean.setFirstName(resultSet.getString(2));
				userProfileFormBean.setLastName(resultSet.getString(3));
				userProfileFormBean.setBirthDate(DateWrapper
						.parseDate(resultSet.getDate(4)));
				userProfileFormBean.setEmailId(resultSet.getString(5));
				try {
					Blob b = resultSet.getBlob(6);
					byte b1[] = b.getBytes(1, (int) b.length());
					realpath = path + "/" + resultSet.getInt(1) + ".jpg";
					System.out.println("path  :" + path);
					OutputStream fout = new FileOutputStream(realpath);
					fout.write(b1);
				} catch (NullPointerException e) {
					realpath = path + "/abc.jpg";

				}
				userProfileFormBean.setPhotograph(realpath);

				userProfileFormBean.setGender(resultSet.getString(7));
				userProfileFormBean.setAddressid(resultSet.getInt(8));
				userProfileFormBean.setAddresstype(resultSet.getString(9));
				userProfileFormBean.setHouseno(resultSet.getString(10));
				userProfileFormBean.setStreet(resultSet.getString(11));
				userProfileFormBean.setCity(resultSet.getString(12));
				userProfileFormBean.setDistrict(resultSet.getString(13));
				userProfileFormBean.setState(resultSet.getString(14));
				userProfileFormBean.setCountry(resultSet.getString(15));
				userProfileFormBean.setPincode(resultSet.getString(16));
				userProfileFormBean.setPhonetype(resultSet.getString(17));
				userProfileFormBean.setPhoneno(resultSet.getString(19));

			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (HibernateException e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return userProfileFormBean;
	}

	public Vector<UserProfileFormBean> viewUserList(String user, String path)
			throws ConnectionException {
		Vector<UserProfileFormBean> userProfileFormBeanVector = new Vector<UserProfileFormBean>();
		UserProfileFormBean userProfileFormBean = null;
		String HQL_QUERY = "";
		try {
			session = AbstractDBFactory.getConnection();
			Connection connection = session.connection();
			System.out.println("haiiiiiiiiiii");
			if (user.equalsIgnoreCase("Active"))
				HQL_QUERY = "select profile.userid,profile.firstName,profile.lastName,profile.dob, profile.emailId,profile.photograph,profile.gender,address.addressid,address.addresstype,address.houseno,address.street,address.city,address.district,address.state,address.country,address.pincode,address.pincode,address.phonetype,address.phoneno,profile.status from  userdetails profile,addresses address where profile.userid=address.useridref  and profile.status='Active'";
			else
				HQL_QUERY = "select profile.userid,profile.firstName,profile.lastName,profile.dob, profile.emailId,profile.photograph,profile.gender,address.addressid,address.addresstype,address.houseno,address.street,address.city,address.district,address.state,address.country,address.pincode,address.pincode,address.phonetype,address.phoneno,profile.status from  userdetails profile,addresses address where profile.userid=address.useridref  and profile.status='Request'";

			PreparedStatement preparedStatement = connection
					.prepareStatement(HQL_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userProfileFormBean = new UserProfileFormBean();
				String realpath = "";
				userProfileFormBean = new UserProfileFormBean();
				userProfileFormBean.setUserid(resultSet.getInt(1));
				userProfileFormBean.setFirstName(resultSet.getString(2));
				userProfileFormBean.setLastName(resultSet.getString(3));
				userProfileFormBean.setBirthDate(DateUtil.parseDate(resultSet
						.getString(4)));
				userProfileFormBean.setEmailId(resultSet.getString(5));
				try {
					Blob b = resultSet.getBlob(6);
					byte b1[] = b.getBytes(1, (int) b.length());
					realpath = path + "/" + resultSet.getInt(1) + ".jpg";
					System.out.println("path  :" + realpath);
					OutputStream fout = new FileOutputStream(realpath);
					fout.write(b1);
				} catch (NullPointerException e) {
					realpath = path + "/abc.jpg";

				}

				userProfileFormBean.setPhotograph(realpath);

				userProfileFormBean.setGender(resultSet.getString(7));
				userProfileFormBean.setAddressid(resultSet.getInt(8));
				userProfileFormBean.setAddresstype(resultSet.getString(9));
				userProfileFormBean.setHouseno(resultSet.getString(10));
				userProfileFormBean.setStreet(resultSet.getString(11));
				userProfileFormBean.setCity(resultSet.getString(12));
				userProfileFormBean.setDistrict(resultSet.getString(13));
				userProfileFormBean.setState(resultSet.getString(14));
				userProfileFormBean.setCountry(resultSet.getString(15));
				userProfileFormBean.setPincode(resultSet.getString(16));
				userProfileFormBean.setPhonetype(resultSet.getString(17));
				userProfileFormBean.setPhoneno(resultSet.getString(18));
				userProfileFormBean.setStatus(resultSet.getString(20));
				userProfileFormBeanVector.add(userProfileFormBean);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (HibernateException e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} finally {
			AbstractDBFactory.closeConnection(session);
		}
		return userProfileFormBeanVector;
	}

	public boolean deleteUsers(int userid) throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();

			String HQL_QUERY = "update ProfileTO set status='Request' where userid=:userid";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger("userid", userid);
			int result = query.executeUpdate();
			if (result > 0) {
				flag = true;
				tx.commit();
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean updateUserStatus(int userid) throws ConnectionException {
		try {
			session = AbstractDBFactory.getConnection();
			Transaction tx = session.beginTransaction();

			String HQL_QUERY = "update ProfileTO set status='Active' where userid=:userid";
			Query query = session.createQuery(HQL_QUERY);
			query.setInteger("userid", userid);
			int result = query.executeUpdate();
			if (result > 0) {
				flag = true;
				tx.commit();
			}

		} catch (NoSuchElementException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} finally {
			// AbstractDBFactory.closeConnection(session);
		}
		return flag;
	}

	public boolean updateUserProfile(UserProfileFormBean profileTO)
			throws ConnectionException, FileNotFoundException {
		boolean flag = false;
		String photo = profileTO.getPhotograph1();
		if (photo.equals("")) {
			photo = profileTO.getPhotograph();
		}
		try {
			session = AbstractDBFactory.getConnection();
			Transaction transaction = session.beginTransaction();
			Connection connection = session.connection();
			CallableStatement cstmt = connection
					.prepareCall(" { call updateprocedure(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
			File f = new File(photo);
			FileInputStream fis = new FileInputStream(f);
			cstmt.setBinaryStream(1, fis, (int) f.length());
			cstmt.setInt(2, profileTO.getUserid());
			cstmt.setString(3, profileTO.getFirstName());
			cstmt.setString(4, profileTO.getLastName());
			cstmt.setString(5, DateWrapper.parseDate(profileTO.getBirthDate()));
			cstmt.setString(6, profileTO.getEmailId());
			cstmt.setString(7, profileTO.getGender());
			cstmt.setString(8, profileTO.getAddresstype());
			cstmt.setString(9, profileTO.getHouseno());
			cstmt.setString(10, profileTO.getStreet());
			cstmt.setString(11, profileTO.getCity());
			cstmt.setString(12, profileTO.getDistrict());
			cstmt.setString(13, profileTO.getState());
			cstmt.setString(14, profileTO.getCountry());
			cstmt.setString(15, profileTO.getPincode());
			cstmt.setString(16, profileTO.getPhoneno());
			cstmt.execute();
			transaction.commit();
			flag = true;

		} catch (SQLException e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Prablum Occured pls try later");
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"server busy please try latter.......");
		} finally {

		}
		return flag;
	}
}