package gov.nih.nci.system.dao.impl.orm;

import gov.nih.nci.common.net.Request;
import gov.nih.nci.common.net.Response;
import gov.nih.nci.common.util.CQL2HQL;
import gov.nih.nci.common.util.Constant;
import gov.nih.nci.common.util.HQLCriteria;
import gov.nih.nci.common.util.HibernateQueryWrapper;
import gov.nih.nci.common.util.NestedCriteria;
import gov.nih.nci.common.util.NestedCriteria2HQL;
import gov.nih.nci.common.util.ObjectFactory;
import gov.nih.nci.system.dao.DAO;
import gov.nih.nci.system.dao.DAOException;
import gov.nih.nci.system.query.cql.CQLQuery;
import gov.nih.nci.system.servicelocator.ServiceLocator;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

/**
  * <!-- LICENSE_TEXT_START -->
* Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute,
* and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
* in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
* materials provided with the distribution.
* 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
* "This product includes software developed by the SAIC and the National Cancer Institute."
* If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
* wherever such third-party acknowledgments normally appear.
* 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
* 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
* the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
* 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
* SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
  * <!-- LICENSE_TEXT_END -->
  */
 /**
  * ORMDAOImpl converts a request to a hibernate query that returns results from a data source
  * @author caBIO Team
  * @version 1.0
 */
public class ORMDAOImpl implements DAO
{
	private static Logger log = Logger.getLogger(ORMDAOImpl.class.getName());
    public SessionFactory sf;
    int recordsPerQuery;
	int maxRecordsPerQuery;
    /**
     * Default Constructor
     */
    public ORMDAOImpl()
	{
        loadProperties();
	}

	/**
	 * Return the resultset of the query embedded in an object of gov.nih.nci.common.net.Response
	 * @param request - a gov.nih.nci.common.net.Request object passed from client
	 * @return an object of gov.nih.nci.common.net.Response that contains the query resultset
	 * @throws DAOException
	 */
	public Response query(Request request) throws DAOException
	{
		List rs = null;
		int counter = 0;
		ORMConnection ormConn = ORMConnection.getInstance();
		org.hibernate.Session session = null;
		Criteria hCriteria = null;
		Integer rowCount = null;
		Query query = null;

		String entityName = request.getDomainObjectName();

		ServiceLocator serviceLocator = null;

		try{
			serviceLocator = (ServiceLocator)ObjectFactory.getObject("ServiceLocator");
			counter = serviceLocator.getORMCounter(entityName);
			session = ORMConnection.openSession(entityName);			
		}
		catch(Exception e)
		{
			log.error("Could not retrieve proper datasource \n " + e.getMessage());
			throw new DAOException("Could not retrieve proper datasource  " + e);
		}

		Object obj = request.getRequest();
		Integer firstRow = request.getFirstRow();
		log.debug("Integer firstRow = " + firstRow);		
		Integer resultsPerQuery = request.getRecordsCount();
		log.debug("Integer resultsPerQuery = " + resultsPerQuery);		
		Boolean isCount = request.getIsCount();
		log.debug("boolean isCount = " + isCount.booleanValue());
	
		try
		{
			if (obj instanceof DetachedCriteria) 
			{
				hCriteria = ((org.hibernate.criterion.DetachedCriteria)request.getRequest()).getExecutableCriteria(session);
				log.info("Detached Criteria Query :"+hCriteria.toString());
				if (hCriteria != null)
				{
				    if(isCount != null && isCount.booleanValue())
				    {
				        rowCount = (Integer)hCriteria.setProjection(Projections.rowCount()).uniqueResult();
						log.debug("DetachedCriteria ORMDAOImpl ===== count = " + rowCount);
						hCriteria.setResultTransformer( Criteria.ROOT_ENTITY );
						hCriteria.setProjection( null );
				    }
				    else if((isCount != null && !isCount.booleanValue()) || isCount == null)
				    {
						if(firstRow != null)
				            hCriteria.setFirstResult(firstRow.intValue());
				        if(resultsPerQuery != null)
				        {
					        if(resultsPerQuery.intValue() > maxRecordsPerQuery)
					        {
					        	String msg = "Illegal Value for RecordsPerQuery: recordsPerQuery cannot be greater than maxRecordsPerQuery. RecordsPerQuery = " +
			                    recordsPerQuery + " maxRecordsPerQuery = " + maxRecordsPerQuery;
					        	log.error(msg);
					            throw new Exception(msg);
					        }
					        else
					        {
					            hCriteria.setMaxResults(resultsPerQuery.intValue());
					        }
				        }
				        else
				        {
				            hCriteria.setMaxResults(recordsPerQuery);

				        }
//				        Set resultSet = new HashSet(hCriteria.list());
//						rs = new ArrayList((Collection)resultSet);
				        rs = hCriteria.list();
				    }
				}				
			}
			else if (obj instanceof NestedCriteria)
			{
				log.debug("ORMDAOImpl.query: it is a NestedCriteria Object ....");		
				NestedCriteria2HQL converter = new NestedCriteria2HQL((NestedCriteria)obj, ormConn.getConfiguration(counter), session);
				query = converter.translate();
				log.info("HQL Query :"+query.getQueryString());
				if (query != null)
				{
					if(isCount != null && isCount.booleanValue())
				    {			
						log.debug("ORMDAOImpl.  isCount .... .... | converter.getCountQuery() = " + converter.getCountQuery().getQueryString());
						rowCount = (Integer)converter.getCountQuery().uniqueResult();
						log.debug("ORMDAOImpl HQL ===== count = " + rowCount);					
					}
					else if((isCount != null && !isCount.booleanValue()) || isCount == null)
				    {	
				    	if(firstRow != null)
				    	{
				    		log.debug("Setting First Row to " + firstRow);
					        query.setFirstResult(firstRow.intValue());				    		
				    	}
				    	if(resultsPerQuery != null)
				    	{
					        if(resultsPerQuery.intValue() > maxRecordsPerQuery)
					        {
					        	String msg = "Illegal Value for RecordsPerQuery: recordsPerQuery cannot be greater than maxRecordsPerQuery. RecordsPerQuery = " + recordsPerQuery + " maxRecordsPerQuery = " + maxRecordsPerQuery ;
					        	log.error(msg);
					            throw new Exception(msg);
					        }
					        else
					        {
					        	log.debug("Setting Max Results to " + resultsPerQuery.intValue());
					            query.setMaxResults(resultsPerQuery.intValue());
					        }				    		
				    	}
				        else
				        {
				        	log.debug("Setting Max Results to " + recordsPerQuery);
				            query.setMaxResults(recordsPerQuery);

				        }
				    	rs = query.list();
				    }				
				}
			}
			else if (obj instanceof HQLCriteria)
			{
				Query hqlQuery = session.createQuery(((HQLCriteria)obj).getHqlString());
				log.info("HQL Criteria Query :"+hqlQuery.getQueryString());
				if(isCount != null && isCount.booleanValue())
			    {
					rowCount = new Integer(hqlQuery.list().size());
				}
				else if((isCount != null && !isCount.booleanValue()) || isCount == null)
			    {	
			    	if(firstRow != null)
			    	{
			    		hqlQuery.setFirstResult(firstRow.intValue());				    		
			    	}
			    	if(resultsPerQuery != null)
			    	{
				        if(resultsPerQuery.intValue() > maxRecordsPerQuery)
				        {
				        	String msg = "Illegal Value for RecordsPerQuery: recordsPerQuery cannot be greater than maxRecordsPerQuery. RecordsPerQuery = " + recordsPerQuery + " maxRecordsPerQuery = " + maxRecordsPerQuery ;
				        	log.error(msg);
				            throw new Exception(msg);
				        }
				        else
				        {
				        	hqlQuery.setMaxResults(resultsPerQuery.intValue());
				        }				    		
			    	}
			        else
			        {
			        	hqlQuery.setMaxResults(recordsPerQuery);
			        }
			    	rs = hqlQuery.list();
			    }				
			}
			else if (obj instanceof CQLQuery)
			{
				HibernateQueryWrapper queryWrapper = CQL2HQL.translate((CQLQuery)obj, false,request.getCaseSensitivity().booleanValue()); 
				String hql = queryWrapper.getHql();
				List params = queryWrapper.getParameters();
				log.info("CQL Query :"+hql);
				Query hqlQuery = session.createQuery(hql);
				
				for(int i = 0; i<params.size();i++)
					hqlQuery.setParameter(i,params.get(i) );
				
				if(isCount != null && isCount.booleanValue())
			    {
					rowCount = new Integer(hqlQuery.list().size());
				}
				else if((isCount != null && !isCount.booleanValue()) || isCount == null)
			    {	
			    	if(firstRow != null)
			    	{
			    		hqlQuery.setFirstResult(firstRow.intValue());				    		
			    	}
			    	if(resultsPerQuery != null)
			    	{
				        if(resultsPerQuery.intValue() > maxRecordsPerQuery)
				        {
				        	String msg = "Illegal Value for RecordsPerQuery: recordsPerQuery cannot be greater than maxRecordsPerQuery. RecordsPerQuery = " + recordsPerQuery + " maxRecordsPerQuery = " + maxRecordsPerQuery ;
				        	log.error(msg);
				            throw new Exception(msg);
				        }
				        else
				        {
				        	hqlQuery.setMaxResults(resultsPerQuery.intValue());
				        }				    		
			    	}
			        else
			        {
			        	hqlQuery.setMaxResults(recordsPerQuery);
			        }
			    	rs = hqlQuery.list();
			    }				
			}
		}
		catch (JDBCException ex)
		{
			log.error("JDBC Exception in ORMDAOImpl ", ex);
			throw new DAOException("JDBC Exception in ORMDAOImpl ", ex);
		}
		catch(org.hibernate.HibernateException hbmEx)
		{
			log.error(hbmEx.getMessage());
			throw new DAOException("Hibernate problem ", hbmEx);
		}
		catch(Exception e)
		{
			log.error("Exception ", e);
			throw new DAOException("Exception in the ORMDAOImpl ", e);
		}
		finally
		{
			try
			{
				session.clear();
				session.close();
			}
			catch (Exception eSession)
			{
				log.error("Could not close the session - "+ eSession.getMessage());
				throw new DAOException("Could not close the session  " + eSession);
			}
		}

		Response rsp = new Response();
		if(isCount != null && isCount.booleanValue())
		    rsp.setRowCount(rowCount);
		else
		    rsp.setResponse(rs);
		return rsp;
	}

	private void loadProperties(){

		try{
			Properties _properties = new Properties();

			_properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("CORESystem.properties"));
			String resultsPerQuery = _properties.getProperty("RECORDSPERQUERY");
			String maxResultsPerQuery = _properties.getProperty("MAXRECORDSPERQUERY");

			if(resultsPerQuery != null)
			{
			    recordsPerQuery = new Integer(resultsPerQuery).intValue();
			}
			else
			{
			    recordsPerQuery = Constant.MAX_RESULT_COUNT_PER_QUERY;
			}

			if(maxResultsPerQuery != null)
			{
			    maxRecordsPerQuery = new Integer(maxResultsPerQuery).intValue();
			}

		}catch(IOException e)
		{
			log.error("IOException ", e);
		}
		catch(Exception ex){
			log.error("Exception ", ex);			
		}
	}
}
