/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.dao.orm;

import gov.nih.nci.system.dao.DAOException;
import gov.nih.nci.system.dao.Request;
import gov.nih.nci.system.dao.Response;
import gov.nih.nci.system.dao.WritableDAO;
import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.SDKQueryResult;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;
import gov.nih.nci.system.query.hql.DeleteHQLQuery;
import gov.nih.nci.system.query.hql.InsertHQLQuery;
import gov.nih.nci.system.query.hql.UpdateHQLQuery;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class WritableORMDAOImpl extends ORMDAOImpl implements WritableDAO
{
	@Override
	@SuppressWarnings("unchecked")
	public Response query(Request request) throws DAOException
	{
		//System.out.println("WritableORMDAOImpl query....");
		if(! (request.getRequest() instanceof SDKQuery))
			return super.query(request);

		SDKQueryResult result = null;

			SDKQuery q = (SDKQuery) request.getRequest();
			if (q instanceof InsertExampleQuery)
			{
				try
				{

				Object obj = ((InsertExampleQuery) q).getExample();
				//System.out.println("InsertExampleQuery *****************"+obj.toString());
				insert(obj, ((InsertExampleQuery) q).getCommit());
				result = new SDKQueryResult(obj);
				}
				catch(org.springframework.dao.DataAccessException e)
				{
					//System.out.println(e.getMessage());
					throw new DAOException(e);
				}
			}
			else if (q instanceof DeleteExampleQuery)
			{
				//System.out.println("Instance of DeleteExampleQuery");
				delete(((DeleteExampleQuery) q).getExample(), ((DeleteExampleQuery) q).getCommit());
				result = new SDKQueryResult(true);
			}
			else if (q instanceof UpdateExampleQuery)
			{
				update(((UpdateExampleQuery) q).getExample(), ((UpdateExampleQuery) q).getCommit());
				result = new SDKQueryResult(((UpdateExampleQuery) q).getExample());
			}
			else if(q instanceof InsertHQLQuery)
			{
				insert(((InsertHQLQuery)q).getHqlString(),((InsertHQLQuery)q).getParameters());
				result = new SDKQueryResult(true);
			}
			else if(q instanceof DeleteHQLQuery)
			{
				Session session = getSession();
				Transaction tx = session.beginTransaction();
				//System.out.println("instanceof DeleteHQLQuery...****..");
				delete(((DeleteHQLQuery)q).getHqlString(),((DeleteHQLQuery)q).getParameters());
				tx.commit();
				result = new SDKQueryResult(true);
				//System.out.println("instanceof DeleteHQLQuery....."+result);
			}
			else if(q instanceof UpdateHQLQuery)
			{
				update(((UpdateHQLQuery)q).getHqlString(),((UpdateHQLQuery)q).getParameters());
				result = new SDKQueryResult(true);
			}

		Response resp = new Response();
		resp.setResponse(result);
		return resp;
	}

	public void insert(Object o, boolean commit)
	{
		log.info("In the writable DAO. executing the Insert query**********");
		//System.out.println("inserting: "+o.toString());
		if(commit)
		{
			getFlushAutoHibernateTemplate().execute(getSaveHibernateCallback(o));
		}
		else
			getFlushAutoHibernateTemplate().save(o);
	}

	public void update(Object o, boolean commit)
	{
		log.info("In the writable DAO. executing the Update query*********");
		//System.out.println("updating: "+o.toString());
		if(commit)
		{
			getFlushAutoHibernateTemplate().execute(getUpdateHibernateCallback(o));
		}
		else
			getFlushAutoHibernateTemplate().update(o);
	}

	public void delete(final Object o, boolean commit)
	{
		//System.out.println("In the writable DAO. executing the Delete query***1111*******");
		//System.out.println("deleting: "+o.toString());
		if(commit)
		{
			getFlushAutoHibernateTemplate().execute(getDeleteHibernateCallback(o));
		}
		else
			getFlushAutoHibernateTemplate().delete(o);
		//System.out.println("In the writable DAO. executing the Delete query***commit*******");

	}

	protected HibernateCallback getDeleteHibernateCallback(final Object obj)
	{
		HibernateCallback callBack = new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				try
				{
					Transaction tx = session.beginTransaction();
					session.delete(obj);
					tx.commit();
					//session.flush();
					return obj;
				}
				finally
				{
					//session.close();
				}
			}
		};
		return callBack;
	}

	protected HibernateCallback getSaveHibernateCallback(final Object obj)
	{
		HibernateCallback callBack = new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				try
				{
					Transaction tx = session.beginTransaction();
					session.save(obj);
					tx.commit();
					//session.flush();
					return obj;
				}
				finally
				{
					//session.close();
				}
			}
		};
		return callBack;
	}

	protected HibernateCallback getUpdateHibernateCallback(final Object obj)
	{
		//System.out.println("Update: "+obj);
		HibernateCallback callBack = new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				try
				{
					Transaction tx = session.beginTransaction();
					session.update(obj);
					tx.commit();
					//session.flush();
					return obj;
				}
				finally
				{
					//session.close();
				}
			}
		};
		return callBack;
	}


	public void insert(final String hql, final List<Object> paramList)
	{
		log.info("In the writable DAO. executing the Insert query");
		HibernateCallback callBack = getExecuteUpdateHibernateCallback(hql,paramList);
		getFlushAutoHibernateTemplate().execute(callBack);
	}

	public void update(String hql, List<Object> paramList)
	{
		log.info("In the writable DAO. executing the Update query");
		HibernateCallback callBack = getExecuteUpdateHibernateCallback(hql,paramList);
		getFlushAutoHibernateTemplate().execute(callBack);
	}

	public void delete(String hql, List<Object> paramList)
	{
		log.info("In the writable DAO. executing the Delete query");
		//System.out.println("In the writable DAO. executing the Delete query***2222*******");
		HibernateTemplate template = getFlushAutoHibernateTemplate();
		HibernateCallback callBack = getExecuteUpdateHibernateCallback(hql,paramList);
		template.execute(callBack);
		//System.out.println("In the writable DAO. executing the Delete query***commit22222*******");

	}


	protected HibernateCallback getExecuteUpdateHibernateCallback(final String hql, final List<Object> paramList)
	{
		HibernateCallback callBack = new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				int count = 0;
				if(paramList!=null)
					for(Object obj:paramList)
						q.setParameter(count++,obj);
				int result = q.executeUpdate();
				return result;
			}
		};
		return callBack;
	}

	public HibernateTemplate getFlushAutoHibernateTemplate() {
		HibernateTemplate template = getHibernateTemplate();
		template.setFlushMode(HibernateTemplate.FLUSH_AUTO);
		return template;
	}
}