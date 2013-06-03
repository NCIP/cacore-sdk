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
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class WritableORMDAOImpl extends ORMDAOImpl implements WritableDAO 
{
	@Override
	@SuppressWarnings("unchecked")
	public Response query(Request request) throws DAOException 
	{
		if(! (request.getRequest() instanceof SDKQuery))
			return super.query(request);

		SDKQueryResult result = null;

			SDKQuery q = (SDKQuery) request.getRequest();
			if (q instanceof InsertExampleQuery)
			{
				Object obj = ((InsertExampleQuery) q).getExample();
				insert(obj);
				result = new SDKQueryResult(obj);
			}
			else if (q instanceof DeleteExampleQuery)
			{
				delete(((DeleteExampleQuery) q).getExample());
				result = new SDKQueryResult(true);
			}
			else if (q instanceof UpdateExampleQuery) 
			{
				update(((UpdateExampleQuery) q).getExample());
				result = new SDKQueryResult(((UpdateExampleQuery) q).getExample());
			}
			else if(q instanceof InsertHQLQuery)
			{
				insert(((InsertHQLQuery)q).getHqlString(),((InsertHQLQuery)q).getParameters());
				result = new SDKQueryResult(true);
			}
			else if(q instanceof DeleteHQLQuery)
			{
				update(((DeleteHQLQuery)q).getHqlString(),((DeleteHQLQuery)q).getParameters());
				result = new SDKQueryResult(true);
			}
			else if(q instanceof UpdateHQLQuery)
			{
				delete(((UpdateHQLQuery)q).getHqlString(),((UpdateHQLQuery)q).getParameters());
				result = new SDKQueryResult(true);
			}

		Response resp = new Response();
		resp.setResponse(result);
		return resp;
	}

	public void insert(Object o)
	{
		log.info("In the writable DAO. executing the Insert query");
		getFlushAutoHibernateTemplate().save(o);	
	}

	public void update(Object o)
	{
		log.info("In the writable DAO. executing the Update query");
		getFlushAutoHibernateTemplate().update(o);
	}

	public void delete(Object o)
	{
		log.info("In the writable DAO. executing the Delete query");
		getFlushAutoHibernateTemplate().delete(o);
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
		HibernateCallback callBack = getExecuteUpdateHibernateCallback(hql,paramList);
		getFlushAutoHibernateTemplate().execute(callBack);
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