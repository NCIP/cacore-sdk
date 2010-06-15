package gov.nih.nci.system.dao.impl.orm;

import gov.nih.nci.common.util.ObjectFactory;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.dao.DAOException;
import gov.nih.nci.system.servicelocator.ServiceLocator;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * ORMConnection is a Singleton object that is used to create Hibernate
 * SessionFactory object.
 * 
 * @author caBIO Team
 * @version 1.0
 */
public class ORMConnection {

	private static Logger log = Logger.getLogger(ORMConnection.class.getName());

	private static ORMConnection orm_instance;

	private static SessionFactory[] sessionFactories;

	private Configuration[] configurations;

	/**
	 * Private Constructor, Hibernate SessionFactory is instantiated here.
	 * 
	 */
	private ORMConnection() throws ApplicationException{
		// Get the total number of ORM datasource from ServiceLocator
		ServiceLocator serviceLocator = null;
		try
		{
			serviceLocator = (ServiceLocator)ObjectFactory.getObject("ServiceLocator");
		} catch (ApplicationException e1)
		{
			log.fatal("Unable to locate Service Locator :",e1);
			throw new ApplicationException("Unable to locate Service Locator :",e1);
		}
		int count = serviceLocator.getORMCount();
		sessionFactories = new SessionFactory[count];
		configurations = new Configuration[count];
		try {
			for (int i = 0; i < count; i++) {
				String cfgFileName = "orm" + (i + 1) + ".cfg.xml";
				configurations[i] = new Configuration().configure(cfgFileName);
				sessionFactories[i] = configurations[i].buildSessionFactory();
			}
		} catch (HibernateException e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	/**
	 * Return an ORMConnection object. This method guarantees there are only one
	 * object of ORMConnection in the server.
	 * 
	 * @return an object of ORMConnection
	 * @throws DAOException 
	 * @throws ApplicationException 
	 */
	synchronized public static ORMConnection getInstance() throws DAOException  {
		
		if (orm_instance == null) {
			return getNewInstance();
		} else {
			return orm_instance;
		}
	}

	/**
	 * Return an new ORMConnection object.
	 * 
	 * @return an object of ORMConnection
	 * @throws DAOException 
	 * @throws ApplicationException 
	 */
	static private ORMConnection getNewInstance() throws DAOException {
		try
		{
			orm_instance = new ORMConnection();
		} catch (ApplicationException e)
		{
			throw new DAOException(e);
		}
		return orm_instance;
	}

	/**
	 * Return a Hibernate Session object
	 * 
	 * @return an object of Hibernate Session
	 * 
	 * @throws HibernateException
	 */
	public static Session openSession(String domainObjectName) throws HibernateException, Exception {
		ServiceLocator serviceLocator = null;
		int counter = 0;
		try
		{
			serviceLocator = (ServiceLocator)ObjectFactory.getObject("ServiceLocator");
			counter = serviceLocator.getORMCounter(domainObjectName);		
		} catch (ApplicationException e1)
		{
			log.fatal("Unable to locate Service Locator :",e1);
			throw new ApplicationException("Unable to locate Service Locator :",e1);
		}
		if (sessionFactories != null) {
			return sessionFactories[counter - 1].openSession();
		} else {
			log.error("No Hibernate Session Factory ... ...");
			throw new HibernateException("No Hibernate Session Factory ... ...");
		}
	}

	/**
	 * Return a Hibernate Session object
	 * 
	 * @return an object of Hibernate Session
	 * 
	 * @throws HibernateException
	 */
	public Configuration getConfiguration(int counter)
			throws HibernateException {
		if (configurations != null) {
			return configurations[counter - 1];
		} else {
			log.error("No Hibernate Configurations ... ...");
			throw new HibernateException("No Hibernate Configurations ... ...");
		}
	}

	public SessionFactory getSessionFactory(int counter)
			throws HibernateException {
		if (sessionFactories != null) {
			return sessionFactories[counter - 1];
		} else {
			log.error("No Hibernate Configurations ... ...");
			throw new HibernateException("No Hibernate Configurations ... ...");
		}
	}
}
