package com.hcss.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hcss.utill.LoggerManager;

public class AbstractDBFactory {
	static Session session = null;

	public static Session getConnection() {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			System.out.println("Connectedddddddddddd");
		} catch (HibernateException se) {
			System.out.println(se);
			LoggerManager.writeLogWarning(se);
		} catch (Exception cnfe) {
			LoggerManager.writeLogWarning(cnfe);
			System.out.println(cnfe);
		}
		return session;
	}

	public static void closeConnection(Session session) {
		if (session != null) {
			try {
				session.flush();
				session.close();
			} catch (HibernateException e) {
				LoggerManager.writeLogSevere(e);
				e.printStackTrace();
			}
		}
	}

}
