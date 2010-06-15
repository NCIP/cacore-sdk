package gov.nih.nci.common.util;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;

public class NestedCriteria2HQL
{

	private NestedCriteria criteria;
	private Configuration cfg;
	private Session session;
	private Query query;
	private Query countQuery;

	private static Logger log = Logger.getLogger(NestedCriteria2HQL.class);

	private List paramList = new ArrayList();

	public NestedCriteria2HQL(NestedCriteria crit, Configuration cfg, Session session)
	{
		this.criteria = crit;
		this.cfg = cfg;
		this.session = session;
	}
	


	public Query translate() throws Exception
	{
		StringBuffer hql = new StringBuffer();

		processNestedCriteria( hql, criteria);
		
		query = prepareQuery(hql);
		log.debug("HQL Query :"+query.getQueryString());
		return query;
	}

	private void processNestedCriteria(StringBuffer hql, NestedCriteria criteria) throws Exception {
		if (condition1(criteria)) {
			log.debug("Processing Scenario1:  src=dest and internal nested criteria=null");
			solveScenario1(hql, criteria);
		} else if (condition2(criteria)) {
			log.debug("Processing Scenario2:  src!=dest and internal nested criteria=null");
			solveScenario2(hql, criteria);
		} else if (condition3(criteria)){
			log.debug("Processing Scenario3:  nested criteria!=null");
			solveScenario3(hql, criteria);			
		} else {
			//should never happen
			log.error("Unexpected NestedCriteria condition found for criteria: " + criteria);
			throw new Exception("Unexpected NestedCriteria condition found for criteria: " + criteria);
		}
	}
	
	private boolean condition1(NestedCriteria criteria){
		
		if (criteria.getSourceName().equals(criteria.getTargetObjectName()) &&
		criteria.getInternalNestedCriteria() == null)
			return true;
		return false;
	}
	
	private boolean condition2(NestedCriteria criteria){
		
		if (!criteria.getSourceName().equals(criteria.getTargetObjectName()) &&
		criteria.getInternalNestedCriteria() == null)
			return true;
		return false;
	}	
	
	private boolean condition3(NestedCriteria criteria){
		
		if (criteria.getInternalNestedCriteria() != null)
			return true;
		return false;
	}
			
	private void solveScenario1(StringBuffer hql, NestedCriteria criteria) throws Exception {
		
		Collection sourceObjectList = criteria.getSourceObjectList();
		
		if (sourceObjectList == null){
			log.error("Scenario1: Source object list is unexpectedly null");
			throw new Exception("Source Object List is unexpectedly null");
		}
		
		String targetObjectName = criteria.getTargetObjectName();		
		String destAlias = getAlias(targetObjectName,1);

		if (sourceObjectList.size() == 1 ){
			log.debug("Scenario1: Processing single object in source object list");
			String select = getObjectCriterion(sourceObjectList.iterator().next(), cfg);
			hql.append(select);
			log.debug("Scenario1: Single object HQL sub-select: " + select);
		} else {
			log.debug("Scenario1: Processing multiple objects in source object list");
			
			hql.append("select " + destAlias + " from " + targetObjectName + " " + destAlias + " where ");
			for (Iterator i = sourceObjectList.iterator(); i.hasNext();)
			{
				Object obj = i.next();
				hql.append(destAlias + " in (" + getObjectCriterion(obj, cfg)+" )");
				if (i.hasNext())
					hql.append(" or ");
			}
		}
	}
	
	private void solveScenario2(StringBuffer hql, NestedCriteria criteria) throws Exception {
		
		Collection sourceObjectList = criteria.getSourceObjectList();
		
		if (sourceObjectList == null){
			log.error("Scenario2: Source object list is unexpectedly null");
			throw new Exception("Scenario2: Source Object List is unexpectedly null");
		}
		
		String targetObjectName = criteria.getTargetObjectName();
		String sourceObjectName = criteria.getSourceName();
		String srcAlias = getAlias(criteria.getSourceName(),1);
		String destAlias = getAlias(targetObjectName,1);
		
		
		log.debug("Scenario2: targetObjectName: " + targetObjectName);
		log.debug("Scenario2: sourceObjectName: " + sourceObjectName);		
		log.debug("Scenario2: srcAlias: " + srcAlias);
		log.debug("Scenario2: destAlias: " + destAlias);
	

		if (sourceObjectList.size() == 1 ){
			log.debug("Scenario2: Processing single object in source object list");
			
			StringBuffer selectBuffer = new StringBuffer();
			String roleName = criteria.getRoleName();
			if(roleName==null)
			{
				selectBuffer.append("select ").append(destAlias).append(" from ")
						.append(targetObjectName).append(" ").append(destAlias)
						.append(", ").append(sourceObjectName).append(" ")
						.append(srcAlias).append(" where ");
				
				log.debug("Scenario2: roleName: " + roleName);
				selectBuffer.append(destAlias).append("=").append(srcAlias);
					
				selectBuffer.append(" and ").append(srcAlias).append(" in (")
					.append(getObjectCriterion(sourceObjectList.iterator().next(), cfg)).append(")");
			}
			else
			{
				//if(this.criteria.getInternalNestedCriteria()==null)
				//	selectBuffer.append("select ").append(srcAlias).append(".").append(roleName).append(" ").append(getObjectCriterion(sourceObjectList.iterator().next(), cfg));
				//else
				{
					selectBuffer.append("select ").append(destAlias).append(" from ")
					.append(targetObjectName).append(" ").append(destAlias)
					.append(", ").append(sourceObjectName).append(" ")
					.append(srcAlias).append(" where ");
					if (criteria.isTargetCollection())
						selectBuffer.append(destAlias).append(" in elements(").append(srcAlias).append(".").append(criteria.getRoleName()).append(")");
					else 
						selectBuffer.append(destAlias).append("=").append(srcAlias).append(".").append(roleName);
						
					selectBuffer.append(" and ").append(srcAlias).append(" in (")
						.append(getObjectCriterion(sourceObjectList.iterator().next(), cfg)).append(")");
				}
			}
			log.debug("Scenario2: single object HQL sub-select: " + selectBuffer.toString());	
			
			hql.append(selectBuffer.toString());

		} else {
			log.debug("Scenario2: Processing multiple objects in source object list");
			String roleName = criteria.getRoleName();
			if(roleName==null)
			{
				hql.append("select ").append(destAlias).append(" from ")
				.append(targetObjectName).append(" ").append(destAlias)
				.append(", ").append(sourceObjectName).append(" ")
				.append(srcAlias).append(" where ");
				hql.append(destAlias).append("=").append(srcAlias);
				hql.append(" and ");
			}
			else
			{
				//if(this.criteria.getInternalNestedCriteria()==null)
				//	hql.append("select ").append(srcAlias).append(".").append(roleName).append(" where ");
				//else
				{
					hql.append("select ").append(destAlias).append(" from ")
					.append(targetObjectName).append(" ").append(destAlias)
					.append(", ").append(sourceObjectName).append(" ")
					.append(srcAlias).append(" where ");
					if (criteria.isTargetCollection())
						hql.append(destAlias).append(" in elements(").append(srcAlias).append(".").append(criteria.getRoleName()).append(")");
					else 
						hql.append(destAlias).append("=").append(srcAlias).append(".").append(roleName);
						
					hql.append(" and ");
				}
			}

			for (Iterator i = sourceObjectList.iterator(); i.hasNext();)
			{
				Object obj = i.next();
				hql.append(srcAlias + " in (" + getObjectCriterion(obj, cfg)).append(")");
				if (i.hasNext())
					hql.append(" or ");
			}
		}
	}
	
	private void solveScenario3(StringBuffer hql, NestedCriteria criteria) throws Exception {
		
		String targetObjectName = criteria.getTargetObjectName();
		String sourceObjectName = criteria.getSourceName();
		String srcAlias = getAlias(criteria.getSourceName(),2);
		String destAlias = getAlias(targetObjectName,1);
		
		log.debug("Scenario3: targetObjectName: " + targetObjectName);
		log.debug("Scenario3: sourceObjectName: " + sourceObjectName);		
		log.debug("Scenario3: srcAlias: " + srcAlias);
		log.debug("Scenario3: destAlias: " + destAlias);
		
		
		String roleName = criteria.getRoleName();
		log.debug("Scenario2: roleName: " + roleName);
				
		if (roleName == null){
			hql.append("select ").append(destAlias).append(" from ")
			.append(targetObjectName).append(" ").append(destAlias)
			.append(", ").append(sourceObjectName).append(" ")
			.append(srcAlias).append(" where ");
			hql.append(destAlias).append("=").append(srcAlias);
			StringBuffer internalNestedCriteriaBuffer = new StringBuffer();
			processNestedCriteria(internalNestedCriteriaBuffer, criteria.getInternalNestedCriteria());
			
			hql.append(" and ").append(srcAlias).append(" in (")
				.append(internalNestedCriteriaBuffer).append(")");
		} else 
		{
			
			hql.append("select ").append(destAlias).append(" from ")
			.append(targetObjectName).append(" ").append(destAlias)
			.append(", ").append(sourceObjectName).append(" ")
			.append(srcAlias).append(" where ");
	
			if (criteria.isTargetCollection())
				hql.append(destAlias).append(" in elements(").append(srcAlias).append(".").append(criteria.getRoleName()).append(")");
			else 
				hql.append(destAlias).append("=").append(srcAlias).append(".").append(roleName);
				
			StringBuffer internalNestedCriteriaBuffer = new StringBuffer();
			processNestedCriteria(internalNestedCriteriaBuffer, criteria.getInternalNestedCriteria());
			
			hql.append(" and ").append(srcAlias).append(" in (")
				.append(internalNestedCriteriaBuffer).append(")");
		}
			
		
		log.debug("Scenario3: HQL select: " + hql.toString());
	}
	
	private Query prepareQuery(StringBuffer hql)
	{
		//Check if the target contains any CLOB. If yes then do a subselect with distinct else do plain distinct
		String destalias = getAlias(criteria.getTargetObjectName(), 1);
		boolean containsCLOB = checkClobAttribute(criteria.getTargetObjectName());
		String countQ="";
		String normalQ="";
		String originalQ = hql.toString();
		if(containsCLOB)
		{
			String modifiedQ = originalQ.replaceFirst(destalias,destalias+".id" );
			String suffix = "from "+criteria.getTargetObjectName()+" as "+destalias+" where "+destalias+".id in ("+modifiedQ+")";
			normalQ="select "+destalias+" "+suffix;
			countQ="select count(*) "+suffix;
		}
		else
		{
			normalQ = originalQ.replaceFirst("select "+destalias, "select distinct ("+destalias+") ");
			countQ = originalQ.replaceFirst("select "+destalias, "select count(distinct "+destalias+".id) ");
		}
		
		log.debug("****** NormalQ: " + normalQ);
		log.debug("****** CountQ: " + countQ);
		
		query = session.createQuery(normalQ);
		countQuery = session.createQuery(countQ);
		
		
		// ORIGINAL
		//countQuery = session.createQuery("select count(*) " + hql.toString());
		log.debug("Setting "+paramList.size()+" parameters in the query");
		for (int i=0;i<paramList.size();i++)
		{
			Object valueObj = paramList.get(i);
			if (valueObj instanceof String)
			{
				query.setString(i, (String)valueObj);
				countQuery.setString(i, (String)valueObj);
			}
			else if (valueObj instanceof Long)
			{
				query.setLong(i, ((Long)valueObj).longValue());
				countQuery.setLong(i, ((Long)valueObj).longValue());
			}
			else if (valueObj instanceof Date)
			{
				query.setDate(i,(Date)valueObj);
				countQuery.setDate(i,(Date)valueObj);
			}
			else if (valueObj instanceof Boolean)
			{
				query.setBoolean(i, ((Boolean)valueObj).booleanValue());
				countQuery.setBoolean(i, ((Boolean)valueObj).booleanValue());				
			}
			else if (valueObj instanceof Double)
			{
				query.setDouble(i, ((Double)valueObj).doubleValue());
				countQuery.setDouble(i, ((Double)valueObj).doubleValue());
			}
			else if (valueObj instanceof Float)
			{
				query.setFloat(i, ((Float)valueObj).floatValue());
				countQuery.setFloat(i, ((Float)valueObj).floatValue());
			}
			else if (valueObj instanceof Integer)
			{
				query.setInteger(i, ((Integer)valueObj).intValue());
				countQuery.setInteger(i, ((Integer)valueObj).intValue());
			}
			else
			{
				query.setString(i, valueObj.toString());
				countQuery.setString(i, valueObj.toString());
			}
		}
		return query;
	}

	private boolean checkClobAttribute(String objClassName)
	{
		PersistentClass pclass = cfg.getClassMapping(objClassName);

		Iterator properties = pclass.getPropertyIterator();
		while (properties.hasNext())
		{
			Property prop = (Property) properties.next();
			if (!prop.getType().isAssociationType())
				if (prop.getType().getName().equals("gov.nih.nci.common.util.StringClobType"))
					return true;
		}
		
		return false;
	}



	private String getObjectAttributeCriterion(String sourceAlias, Object obj, Configuration cfg) throws Exception
	{
		StringBuffer whereClause = new StringBuffer();
		HashMap criterionMap = getObjAttrCriterion(obj, cfg);
		if (criterionMap != null)
		{
			Iterator keys = criterionMap.keySet().iterator();
			while (keys.hasNext())
			{
				String key = (String) keys.next();
				Object value = criterionMap.get(key);
				if (!key.equals("id") && (value instanceof String))
				{
					if (criteria.caseSensitivityFlag)
					{
						whereClause.append(sourceAlias + Constant.DOT + key + getOperator(value) + "? ");
						paramList.add(((String) value).replaceAll("\\*", "\\%"));
					} else
					{
						whereClause.append("lower(" + sourceAlias + Constant.DOT + key + ") " + getOperator(value) + "? ");
						paramList.add(((String) value).toLowerCase().replaceAll("\\*", "\\%"));
					}
				} else
				{
					whereClause.append(sourceAlias).append(Constant.DOT).append(key).append(getOperator(value)).append("? ");
					paramList.add(value);
				}
				if (keys.hasNext())
					whereClause.append(" and ");
			}
		}
		return whereClause.toString();
	}

	private HashMap getObjAttrCriterion(Object obj, Configuration cfg) throws Exception
	{
		HashMap criterions = new HashMap();
		String objClassName = obj.getClass().getName();
		PersistentClass pclass = cfg.getClassMapping(objClassName);
		setAttrCriterion(obj, pclass, criterions);

		while (pclass.getSuperclass() != null)
		{
			pclass = pclass.getSuperclass();
			setAttrCriterion(obj, pclass, criterions);
		}

		String identifier = pclass.getIdentifierProperty().getName();
		Field idField = pclass.getMappedClass().getDeclaredField(identifier);
		idField.setAccessible(true);
		if (idField.get(obj) != null)
			criterions.put(identifier, idField.get(obj));

		return criterions;
	}

	private void setAttrCriterion(Object obj, PersistentClass pclass, HashMap criterions) throws Exception
	{
		Iterator properties = pclass.getPropertyIterator();
		while (properties.hasNext())
		{
			Property prop = (Property) properties.next();
			if (!prop.getType().isAssociationType())
			{
				String fieldName = prop.getName();
				Field field = pclass.getMappedClass().getDeclaredField(fieldName);
				field.setAccessible(true);

				if (field.get(obj) != null)
				{
					if (prop.getType().getName().equals("gov.nih.nci.common.util.StringClobType"))
						criterions.put(fieldName, field.get(obj) + "*");
					else
						criterions.put(fieldName, field.get(obj));
				}
			}
		}
	}

	private String getObjectCriterion(Object obj, Configuration cfg) throws Exception
	{
		String srcAlias = getAlias(obj.getClass().getName(),1);
		
		StringBuffer hql = new StringBuffer();

		HashMap associationCritMap = getObjAssocCriterion(obj, cfg);

		hql.append("select ");
		hql.append(srcAlias);
		hql.append(" from ").append(obj.getClass().getName()).append(" ").append(srcAlias);

		// get association value
		if (associationCritMap != null && associationCritMap.size() > 0)
		{
			Iterator associationKeys = associationCritMap.keySet().iterator();
			int counter = 0;
			while (associationKeys.hasNext())
			{
				String roleName = (String) associationKeys.next();
				Object roleValue = associationCritMap.get(roleName);

				if (roleValue instanceof Collection)
				{
					Object[] objs = ((Collection) roleValue).toArray();
					for (int i = 0; i < objs.length; i++)
					{
						String alias = getAlias(objs[i].getClass().getName(),counter++);
						hql.append(",").append(objs[i].getClass().getName()).append(" ").append(alias);
					}
				} else
				{
					String alias = getAlias(roleValue.getClass().getName(),counter++);
					hql.append(",").append(roleValue.getClass().getName()).append(" ").append(alias);
				}
			}
			hql.append(" where ");
			associationKeys = associationCritMap.keySet().iterator();
			counter = 0;
			while (associationKeys.hasNext())
			{
				String roleName = (String) associationKeys.next();
				Object roleValue = associationCritMap.get(roleName);

				if (roleValue instanceof Collection)
				{
					Object[] objs = ((Collection) roleValue).toArray();
					for (int i = 0; i < objs.length; i++)
					{
						String alias = getAlias(objs[i].getClass().getName(),counter++);
						hql.append(alias).append(" in elements(").append(srcAlias).append(".").append(roleName).append(")");
						hql.append(" and ");
						hql.append(alias).append(" in (").append(getObjectCriterion(objs[i], cfg)).append(") ");
						if (i < objs.length-1)
							hql.append(" and ");
					}
				} else
				{
					String alias = getAlias(roleValue.getClass().getName(),counter++);
					hql.append(alias).append("=").append(srcAlias).append(".").append(roleName);
					hql.append(" and ");
					hql.append(alias).append(" in (").append(getObjectCriterion(roleValue, cfg)).append(") ");
				}
				hql.append(" ");
				if (associationKeys.hasNext())
					hql.append(" and ");
			}
		}
		String attributeCriteria = getObjectAttributeCriterion(srcAlias, obj, cfg);
		if (associationCritMap == null || associationCritMap.size() == 0 && attributeCriteria != null && attributeCriteria.trim().length() > 0)
			hql.append(" where ");
		if (associationCritMap != null && associationCritMap.size() > 0 && attributeCriteria != null && attributeCriteria.trim().length() > 0)
			hql.append(" ").append(" and ");
		hql.append(attributeCriteria);

		return hql.toString();
	}

	private void setAssoCriterion(Object obj, PersistentClass pclass, HashMap criterions) throws Exception
	{
		Iterator properties = pclass.getPropertyIterator();
		while (properties.hasNext())
		{
			Property prop = (Property) properties.next();
			if (prop.getType().isAssociationType())
			{
				String fieldName = prop.getName();
				Field field = pclass.getMappedClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				Object value = field.get(obj);
				if (value != null)
					if ((value instanceof Collection && ((Collection) value).size() > 0) || !(value instanceof Collection))
						criterions.put(fieldName, field.get(obj));
			}
		}
	}

	private HashMap getObjAssocCriterion(Object obj, Configuration cfg) throws Exception
	{
		HashMap criterions = new HashMap();
		List propertyList = new ArrayList();
		String objClassName = obj.getClass().getName();

		PersistentClass pclass = cfg.getClassMapping(objClassName);

		setAssoCriterion(obj, pclass, criterions);
		while (pclass.isJoinedSubclass())
		{
			pclass = pclass.getSuperclass();
			setAssoCriterion(obj, pclass, criterions);
		}
		return criterions;
	}

	private String getAlias(String sourceName, int count)
	{
		String alias = sourceName.substring(sourceName.lastIndexOf(Constant.DOT) + 1);
		alias = alias.substring(0, 1).toLowerCase() + alias.substring(1);
		return alias+"_"+count;
	}

	private String getOperator(Object valueObj)
	{
		if (valueObj instanceof java.lang.String)
		{
			String value = (String) valueObj;
			if (value.indexOf('*') >= 0)
			{
				return " like ";
			}
		}
		return "=";
	}

	public Query getCountQuery()
	{
		return countQuery;
	}
}