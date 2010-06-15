/*
 * Created on Jul 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.system.dao;

import gov.nih.nci.system.dao.impl.orm.ORMConnection;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * 
 * @author Ekagra Software Technologies Ltd.
 */
public class WritableDAO {
	static final Logger log = Logger.getLogger(WritableDAO.class.getName());

	public Object createObject(Object obj) throws DAOException {
		Session session = null;
		Transaction transaction = null;
		String objName = obj.getClass().getName();
		
		try {
			ORMConnection.getInstance();
			session = ORMConnection.openSession(objName);
		} catch (Exception ex) {
			log.error("Could not obtain a session! Could not create " + objName);
			throw new SessionException("Could not obtain a session! Could not create " + objName, ex);
		}
		if (session == null) {
			log.error("Could not obtain a session");
			throw new SessionException("Could not obtain a session");
		}
		try {
			transaction = session.beginTransaction();
			session.save(obj);
			transaction.commit();
		} catch (Exception ex) {
			try {
				transaction.rollback();
			} catch (Exception ex3) {
				log.error("Error while rolling back transaction: "
						+ ex3.getMessage() + " for original Exception: "
						+ ex.getMessage());
				throw new RollbackException(
						"Error while rolling back transaction", ex);
			}

			log.error("Error while creating object " + objName + ": " + ex.getMessage());
			throw new CreateException("An error occured while creating object "	+ objName, ex);
		} finally {
			try {
				session.close();
			} catch (Exception ex2) {
				log.error("Error while closing the Session: "
						+ ex2.getMessage());
				throw new SessionException("Error while closing the Session",
						ex2);
			}
		}
		log.debug("Successful in creating " + objName);

		return obj;

	}

	public Object updateObject(Object obj) throws DAOException {
		Session session = null;
		Transaction transaction = null;
		String objName = obj.getClass().getName();
		try {
			ORMConnection.getInstance();
			session = ORMConnection.openSession(objName);
		} catch (Exception ex) {
			log.error("Could not obtain a session");
			throw new SessionException(
					"Could not obtain a session! Could not update "	+ objName, ex);
		}
		if (session == null) {
			log.error("Could not obtain a session");
			throw new SessionException(
					"Could not obtain a session! Could not update "	+ objName);
		}
		try {
			transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
		} catch (Exception ex) {
			try {
				transaction.rollback();
			} catch (Exception ex3) {
				log.error("Error while rolling back transaction: "
						+ ex3.getMessage() + " for original Exception: "
						+ ex.getMessage());
				throw new RollbackException(
						"Error while rolling back transaction: "
								+ ex3.getMessage()
								+ " for original Exception: " + ex.getMessage(),
						ex3);
			}

			log.error("Error while updating " + objName);
			throw new UpdateException("An error occured while updating " + objName, ex);
		} finally {
			try {
				session.close();
			} catch (Exception ex2) {
				log.error("Error while closing the Session: "
						+ ex2.getMessage());
				throw new SessionException("Error while closing the Session",
						ex2);
			}
		}

		log.debug("Successful in updating " + objName);
		return obj;
	}

	public void removeObject(Object obj) throws DAOException {
		Session session = null;
		Transaction transaction = null;
		String objName = obj.getClass().getName();
		try {
			ORMConnection.getInstance();
			session = ORMConnection.openSession(objName);
		} catch (Exception ex) {
			log.error("Could not obtain a session! Could not delete " + objName);
			throw new SessionException(
					"Could not obtain a session! Could not delete " + objName, ex);
		}

		if (session == null) {
			log.error("Could not obtain a session! Could not delete " + objName);
			throw new SessionException(
					"Could not obtain a session! Could not delete " + objName);
		}
		try {
			transaction = session.beginTransaction();
			session.delete(obj);
			transaction.commit();
		} catch (Exception ex) {
			try {
				transaction.rollback();
			} catch (Exception ex3) {
				log.error("Error while rolling back transaction: "
						+ ex3.getMessage());
				throw new RollbackException(
						"An error occured rolling back transaction while deleting " + objName, ex);
			}

			log.error("Error while deleting " + objName + ": " + ex.getMessage());
			throw new DeleteException("An error occured while deleting " + objName, ex);
		} finally {
			try {
				session.close();
			} catch (Exception ex2) {
				log.error("Error closing the session while deleting " + objName + ex2.getMessage());
				throw new SessionException(
						"Error while closing the Session while removing object " + objName, ex2);
			}
		}
		log.debug("Successful in deleting " + objName);

	}

	public List getObjects(Object obj) throws DAOException {
		Session session = null;
		String objName = obj.getClass().getName();
		log.debug("objName: " + objName);
		
		try {
			ORMConnection.getInstance();
			session = ORMConnection.openSession(objName);
		} catch (Exception ex) {
			log.error("Could not obtain a session! Could not get " + objName + " objects");
			throw new QueryException(
					"Could not obtain a session! Could not get " + objName + " objects", ex);
		}
		if (session == null) {
			log.error("Could not obtain a session! Could not get " + objName + " objects");
			throw new QueryException(
					"Could not obtain a session! Could not get " + objName + " objects");
		}
		List list = null;
		try {
			Criteria criteria = session.createCriteria(obj.getClass());
			criteria.add(Example.create(obj));
			list = criteria.list();
		} catch (Exception ex) {
			log.error("Error while getting objects: " + ex.getMessage());
			throw new QueryException("An error occured while getting objects: "
					+ ex.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception ex2) {
				log.error("Error while closing the Session: "
						+ ex2.getMessage());
				throw new SessionException("Error while closing the Session",
						ex2);
			}
		}
		log.debug("Successful in getting " + objName + " objects");

		return list;
	}

}
