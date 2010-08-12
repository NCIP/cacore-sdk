package gov.nih.nci.system.dao.orm.translator;
import gov.nih.nci.iso21090.AddressPartType;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.hibernate.node.ComplexNode;
import gov.nih.nci.iso21090.hibernate.node.ConstantNode;
import gov.nih.nci.iso21090.hibernate.node.Node;
import gov.nih.nci.iso21090.hibernate.tuple.IsoConstantTuplizerHelper;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.query.nestedcriteria.NestedCriteria;
import gov.nih.nci.system.util.SystemConstant;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Value;

public class NestedCriteria2HQL {
	private NestedCriteria criteria;
	private Configuration cfg;
	private boolean caseSensitive;
	private HQLCriteria hqlCriteria;
	private static Logger log = Logger.getLogger(NestedCriteria2HQL.class);

	private List<Object> paramList = new ArrayList<Object>();
	String isoprefix = "gov.nih.nci.iso21090.";
	private Integer aliasCount = 0;

	private final Map<String, String> partTypes = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("gov.nih.nci.iso21090.EntityNamePartType",
					"gov.nih.nci.iso21090.Enxp");
			put("gov.nih.nci.iso21090.AddressPartType",
					"gov.nih.nci.iso21090.Adxp");
		}
	};
	public NestedCriteria2HQL(NestedCriteria crit, Configuration cfg,
			boolean caseSensitive) {
		this.criteria = crit;
		this.cfg = cfg;
		this.caseSensitive = caseSensitive;
	}

	public HQLCriteria translate() throws Exception {
		StringBuffer hql = new StringBuffer();
		processNestedCriteria(hql, criteria);
		hqlCriteria = prepareQuery(hql);
		log.debug("HQL Query :" + hqlCriteria.getHqlString());
		System.out.println("HQL query: " + hql.toString());
		return hqlCriteria;
	}

	private void processNestedCriteria(StringBuffer hql, NestedCriteria criteria)
			throws Exception {
		if (condition1(criteria)) {
			log.debug("Processing Scenario1:  src=dest and internal nested criteria=null");
			solveScenario1(hql, criteria);
		} else if (condition2(criteria)) {
			log.debug("Processing Scenario2:  src!=dest and internal nested criteria=null");
			solveScenario2(hql, criteria);
		} else if (condition3(criteria)) {
			log.debug("Processing Scenario3:  nested criteria!=null");
			solveScenario3(hql, criteria);
		} else {
			// should never happen
			log.error("Unexpected NestedCriteria condition found for criteria: "
					+ criteria);
			throw new Exception(
					"Unexpected NestedCriteria condition found for criteria: "
							+ criteria);
		}
	}

	private boolean condition1(NestedCriteria criteria) {
		if (criteria.getSourceName().equals(criteria.getTargetObjectName())
				&& criteria.getInternalNestedCriteria() == null)
			return true;
		return false;
	}

	private boolean condition2(NestedCriteria criteria) {
		if (!criteria.getSourceName().equals(criteria.getTargetObjectName())
				&& criteria.getInternalNestedCriteria() == null)
			return true;
		return false;
	}

	private boolean condition3(NestedCriteria criteria) {
		if (criteria.getInternalNestedCriteria() != null)
			return true;
		return false;
	}

	// Single Object with query by example
	@SuppressWarnings("rawtypes")
	private void solveScenario1(StringBuffer hql, NestedCriteria criteria)
			throws Exception {
		Collection sourceObjectList = criteria.getSourceObjectList();
		if (sourceObjectList == null) {
			log.error("Scenario1: Source object list is unexpectedly null");
			throw new Exception("Source Object List is unexpectedly null");
		}
		String targetObjectName = criteria.getTargetObjectName();
		String destAlias = getAlias(targetObjectName, 1);

		if (sourceObjectList.size() == 1) {
			log.debug("Scenario1: Processing single object in source object list");
			Object sourceObject = sourceObjectList.iterator().next();
			String select = getHQLQueryFromSourceObjectWithCriterion(
					sourceObject, cfg, false);
			hql.append(select);
			log.debug("Scenario1: Single object HQL sub-select: " + select);
		} else {
			log.debug("Scenario1: Processing multiple objects in source object list");
			hql.append("select " + destAlias + " from " + targetObjectName
					+ " " + destAlias + " where ");
			for (Iterator i = sourceObjectList.iterator(); i.hasNext();) {
				Object obj = i.next();
				hql.append(destAlias
						+ " in ("
						+ getHQLQueryFromSourceObjectWithCriterion(obj, cfg,
								false) + " )");
				if (i.hasNext())
					hql.append(" or ");
			}
		}
	}

	// Getting association for the query by example
	@SuppressWarnings("rawtypes")
	private void solveScenario2(StringBuffer hql, NestedCriteria criteria)
			throws Exception {
		Collection sourceObjectList = criteria.getSourceObjectList();
		if (sourceObjectList == null) {
			log.error("Scenario2: Source object list is unexpectedly null");
			throw new Exception(
					"Scenario2: Source Object List is unexpectedly null");
		}
		String targetObjectName = criteria.getTargetObjectName();
		String sourceObjectName = criteria.getSourceName();
		String srcAlias = getAlias(criteria.getSourceName(), 1);
		String destAlias = getAlias(targetObjectName, 1);
		log.debug("Scenario2: targetObjectName: " + targetObjectName);
		log.debug("Scenario2: sourceObjectName: " + sourceObjectName);
		log.debug("Scenario2: srcAlias: " + srcAlias);
		log.debug("Scenario2: destAlias: " + destAlias);

		if (sourceObjectList.size() == 1) {
			log.debug("Scenario2: Processing single object in source object list");

			StringBuffer selectBuffer = new StringBuffer();
			String roleName = criteria.getRoleName();
			if (roleName == null) {
				selectBuffer.append("select ").append(destAlias)
						.append(" from ").append(targetObjectName).append(" ")
						.append(destAlias).append(", ")
						.append(sourceObjectName).append(" ").append(srcAlias)
						.append(" where ");

				log.debug("Scenario2: roleName: " + roleName);
				selectBuffer.append(destAlias).append("=").append(srcAlias);

				if (isObjectEmpty(sourceObjectList.iterator().next())) {
					selectBuffer.append(" and ").append(srcAlias)
							.append(".id is not null ");
				} else if (isObjectAssociationEmpty(sourceObjectList.iterator()
						.next())) {
					selectBuffer.append(" and ").append(
							getHQLQueryFromSourceObjectWithCriterion(
									sourceObjectList.iterator().next(), cfg,
									true));
				} else {
					selectBuffer
							.append(" and ")
							.append(srcAlias)
							.append(" in (")
							.append(getHQLQueryFromSourceObjectWithCriterion(
									sourceObjectList.iterator().next(), cfg,
									false)).append(")");
				}
			} else {
				if (criteria.isTargetCollection()) {
					// No attributes or associations populated
					if (isObjectEmpty(sourceObjectList.iterator().next())) {
						if (criteria.getSourceRoleName() != null
								&& !criteria.isSourceCollection()) {
							selectBuffer.append("select ").append(destAlias)
									.append(" from ").append(targetObjectName)
									.append(" ").append(destAlias)
									.append(" where ");
							selectBuffer.append(destAlias).append(".")
									.append(criteria.getSourceRoleName())
									.append(".id is not null ");
						} else {
							selectBuffer.append("select ").append(destAlias)
									.append(" from ").append(sourceObjectName)
									.append(" ").append(srcAlias)
									.append(" inner join ").append(srcAlias)
									.append(".").append(roleName).append(" ")
									.append(destAlias).append(" where ")
									.append(srcAlias)
									.append(".id is not null ");
						}
					}
					// Only some of the attributes populated
					else if (isObjectAssociationEmpty(sourceObjectList
							.iterator().next())) {
						if (criteria.getSourceRoleName() != null
								&& !criteria.isSourceCollection()) {
							selectBuffer
									.append("select ")
									.append(destAlias)
									.append(" from ")
									.append(targetObjectName)
									.append(" ")
									.append(destAlias)
									.append(", ")
									.append(sourceObjectName)
									.append(" ")
									.append(srcAlias)
									.append(" where ")
									.append(destAlias)
									.append(".")
									.append(criteria.getSourceRoleName())
									.append(".id")
									.append("=")
									.append(srcAlias)
									.append(".id")
									.append(" and ")
									.append(getHQLQueryFromSourceObjectWithCriterion(
											sourceObjectList.iterator().next(),
											cfg, true));
						} else {
							selectBuffer
									.append("select ")
									.append(destAlias)
									.append(" from ")
									.append(sourceObjectName)
									.append(" ")
									.append(srcAlias)
									.append(" inner join ")
									.append(srcAlias)
									.append(".")
									.append(roleName)
									.append(" ")
									.append(destAlias)
									.append(" where ")
									.append(getHQLQueryFromSourceObjectWithCriterion(
											sourceObjectList.iterator().next(),
											cfg, true));
						}
					}
					// Attributes and associtions populated
					else {
						selectBuffer
								.append("select ")
								.append(destAlias)
								.append(" from ")
								.append(sourceObjectName)
								.append(" ")
								.append(srcAlias)
								.append(" inner join ")
								.append(srcAlias)
								.append(".")
								.append(roleName)
								.append(" ")
								.append(destAlias)
								.append(" where ")
								.append(srcAlias)
								.append(" in (")
								.append(getHQLQueryFromSourceObjectWithCriterion(
										sourceObjectList.iterator().next(),
										cfg, false)).append(")");

					}
				} else // Target is not collection
				{
					// No attributes or associations populated
					if (isObjectEmpty(sourceObjectList.iterator().next())) {
						selectBuffer.append("select ").append(destAlias)
								.append(" from ").append(sourceObjectName)
								.append(" ").append(srcAlias)
								.append(" inner join ").append(srcAlias)
								.append(".").append(roleName).append(" ")
								.append(destAlias).append(" where ")
								.append(srcAlias).append(".id is not null ");
					}
					// Only some of the attributes populated
					else if (isObjectAssociationEmpty(sourceObjectList
							.iterator().next())) {
						selectBuffer
								.append("select ")
								.append(destAlias)
								.append(" from ")
								.append(targetObjectName)
								.append(" ")
								.append(destAlias)
								.append(", ")
								.append(sourceObjectName)
								.append(" ")
								.append(srcAlias)
								.append(" where ")
								.append(srcAlias)
								.append(".")
								.append(roleName)
								.append(".id")
								.append("=")
								.append(destAlias)
								.append(".id")
								.append(" and ")
								.append(getHQLQueryFromSourceObjectWithCriterion(
										sourceObjectList.iterator().next(),
										cfg, true));
					}
					// Attributes and associtions populated
					else {
						selectBuffer
								.append("select ")
								.append(destAlias)
								.append(" from ")
								.append(targetObjectName)
								.append(" ")
								.append(destAlias)
								.append(", ")
								.append(sourceObjectName)
								.append(" ")
								.append(srcAlias)
								.append(" where ")
								.append(destAlias)
								.append(".id")
								.append("=")
								.append(srcAlias)
								.append(".")
								.append(roleName)
								.append(".id")
								.append(" and ")
								.append(srcAlias)
								.append(" in (")
								.append(getHQLQueryFromSourceObjectWithCriterion(
										sourceObjectList.iterator().next(),
										cfg, false)).append(")");
					}
				}
			}
			log.debug("Scenario2: single object HQL sub-select: "
					+ selectBuffer.toString());

			hql.append(selectBuffer.toString());

		} else // More than one example objects present
		{
			log.debug("Scenario2: Processing multiple objects in source object list");
			String roleName = criteria.getRoleName();
			if (roleName == null) {
				hql.append("select ").append(destAlias).append(" from ")
						.append(targetObjectName).append(" ").append(destAlias)
						.append(", ").append(sourceObjectName).append(" ")
						.append(srcAlias).append(" where ");
				hql.append(destAlias).append(".id").append("=")
						.append(srcAlias).append(".id");
				hql.append(" and ");
			} else {
				if (criteria.isTargetCollection()) {
					if (criteria.getSourceRoleName() != null
							&& !criteria.isSourceCollection()) {
						hql.append("select ").append(destAlias)
								.append(" from ").append(targetObjectName)
								.append(" ").append(destAlias).append(", ")
								.append(sourceObjectName).append(" ")
								.append(srcAlias).append(" where ");
						hql.append(destAlias).append(".")
								.append(criteria.getSourceRoleName())
								.append(".id").append("=").append(srcAlias)
								.append(".id");
						hql.append(" and ");
					} else {
						hql.append("select ").append(destAlias)
								.append(" from ").append(sourceObjectName)
								.append(" ").append(srcAlias)
								.append(" inner join ").append(srcAlias)
								.append(".").append(roleName).append(" ")
								.append(destAlias).append(" where ")
								.append("1=1 and ");
					}
				} else {
					hql.append("select ").append(destAlias).append(" from ")
							.append(targetObjectName).append(" ")
							.append(destAlias).append(", ")
							.append(sourceObjectName).append(" ")
							.append(srcAlias).append(" where ");
					hql.append(destAlias).append(".id").append("=")
							.append(srcAlias).append(".").append(roleName)
							.append(".id");
					hql.append(" and ");
				}
			}

			for (Iterator i = sourceObjectList.iterator(); i.hasNext();) {
				Object obj = i.next();
				hql.append(
						srcAlias
								+ " in ("
								+ getHQLQueryFromSourceObjectWithCriterion(obj,
										cfg, false)).append(")");
				if (i.hasNext())
					hql.append(" or ");
			}
		}
	}

	// Traversing the path of the nested search criteria
	private void solveScenario3(StringBuffer hql, NestedCriteria criteria)
			throws Exception {
		String targetObjectName = criteria.getTargetObjectName();
		String sourceObjectName = criteria.getSourceName();
		String srcAlias = getAlias(criteria.getSourceName(), 2);
		String destAlias = getAlias(targetObjectName, 1);

		log.debug("Scenario3: targetObjectName: " + targetObjectName);
		log.debug("Scenario3: sourceObjectName: " + sourceObjectName);
		log.debug("Scenario3: srcAlias: " + srcAlias);
		log.debug("Scenario3: destAlias: " + destAlias);

		String roleName = criteria.getRoleName();
		log.debug("Scenario2: roleName: " + roleName);

		if (roleName == null) {
			hql.append("select ").append(destAlias).append(" from ")
					.append(targetObjectName).append(" ").append(destAlias)
					.append(", ").append(sourceObjectName).append(" ")
					.append(srcAlias).append(" where ");
			hql.append(destAlias).append(".id").append("=").append(srcAlias)
					.append(".id");
			StringBuffer internalNestedCriteriaBuffer = new StringBuffer();
			processNestedCriteria(internalNestedCriteriaBuffer,
					criteria.getInternalNestedCriteria());

			hql.append(" and ").append(srcAlias).append(" in (")
					.append(internalNestedCriteriaBuffer).append(")");
		} else {
			if (criteria.isTargetCollection()) {
				if (criteria.getSourceRoleName() != null
						&& !criteria.isSourceCollection()) {
					hql.append("select ").append(destAlias).append(" from ")
							.append(targetObjectName).append(" ")
							.append(destAlias).append(", ")
							.append(sourceObjectName).append(" ")
							.append(srcAlias).append(" where ");
					hql.append(destAlias).append(".")
							.append(criteria.getSourceRoleName()).append(".id")
							.append("=").append(srcAlias).append(".id");
					hql.append(" and ");
				} else {
					hql.append("select ").append(destAlias).append(" from ")
							.append(targetObjectName).append(" ")
							.append(destAlias).append(", ")
							.append(sourceObjectName).append(" ")
							.append(srcAlias).append(" where ");
					hql.append(destAlias).append(" in elements(")
							.append(srcAlias).append(".")
							.append(criteria.getRoleName()).append(")");
					hql.append(" and ");
				}
			} else {
				hql.append("select ").append(destAlias).append(" from ")
						.append(targetObjectName).append(" ").append(destAlias)
						.append(", ").append(sourceObjectName).append(" ")
						.append(srcAlias).append(" where ");
				hql.append(destAlias).append(".id").append("=")
						.append(srcAlias).append(".").append(roleName)
						.append(".id");
				hql.append(" and ");
			}
			StringBuffer internalNestedCriteriaBuffer = new StringBuffer();
			processNestedCriteria(internalNestedCriteriaBuffer,
					criteria.getInternalNestedCriteria());
			hql.append(srcAlias).append(" in (")
					.append(internalNestedCriteriaBuffer).append(")");
		}
		log.debug("Scenario3: HQL select: " + hql.toString());
	}

	public static String getCountQuery(String hql) {
		String upperHQL = hql.toUpperCase();
		String modifiedHQL = "";
		int firstSelectIndex = upperHQL.indexOf("SELECT");
		int firstFromIndex = upperHQL.indexOf("FROM");
		if ((firstSelectIndex >= 0) && (firstSelectIndex < firstFromIndex)) {
			String projections = hql.substring(
					firstSelectIndex + "SELECT".length(), firstFromIndex);
			String[] tokens = projections.split(",");
			modifiedHQL = hql
					.substring(0, firstSelectIndex + "SELECT".length())
					+ " count("
					+ tokens[0].trim()
					+ ") "
					+ hql.substring(firstFromIndex);
		} else {
			modifiedHQL = hql.substring(0, firstFromIndex)
					+ " select count(*) " + hql.substring(firstFromIndex);
		}
		return modifiedHQL;
	}

	@SuppressWarnings("rawtypes")
	private boolean checkClobAttribute(String objClassName) {
		PersistentClass pclass = cfg.getClassMapping(objClassName);
		Iterator properties = pclass.getPropertyIterator();
		while (properties.hasNext()) {
			Property prop = (Property) properties.next();
			if (!prop.getType().isAssociationType())
				if (prop.getType().getName().equals("text"))
					return true;
		}
		return false;
	}

	@SuppressWarnings("unused")
	private boolean distinctRequired() {
		boolean distinct = true;
		boolean containsCLOB = checkClobAttribute(criteria
				.getTargetObjectName());
		boolean condition1 = condition1(criteria);

		if (condition1 || containsCLOB)
			distinct = false;

		return distinct;
	}

	@SuppressWarnings("rawtypes")
	private boolean inRequired() {
		boolean condition2 = condition2(criteria);
		boolean condition3 = condition3(criteria);
		boolean condition1 = false;
		// Verify if it is condition1() and it contains one object in the list
		// with no associations
		if (condition1(criteria)) {
			condition1 = true;
			List objects = criteria.getSourceObjectList();
			if (objects == null) {
				condition1 = false;
			} else if (objects.size() == 1) {
				try {
					Object object = objects.get(0);
					Map<String, Object> map = getAssociatedObjectCriterion(
							object, cfg, null, null);
					if (map == null || map.size() == 0) {
						condition1 = false;
					}
					if (!condition1) {
						condition1 = isComponentSetCriterion(object);
					}
				} catch (Exception e) {
					log.error(" Ignore this error ", e);
				}
			}
		}
		if (condition2) {
			try {
				if (criteria.isTargetCollection()
						&& !criteria.isSourceCollection()
						&& criteria.getSourceRoleName() != null
						&& criteria.getSourceObjectList().size() == 1
						&& isObjectAssociationEmpty(criteria
								.getSourceObjectList().iterator().next()))
					condition2 = false;
			} catch (Exception e) {
				log.error(" Ignore this error ", e);
			}
		}
		return condition1 || condition2 || condition3;
	}

	private HQLCriteria prepareQuery(StringBuffer hql) {
		// Check if the target contains any CLOB. If yes then do a subselect
		// with distinct else do plain distinct
		//
		String destalias = getAlias(criteria.getTargetObjectName(), 1);
		String countQ = "";
		String normalQ = "";
		String originalQ = hql.toString();
		boolean distinctRequired = false; // indistinctRequired();
		// never comes to else code, as a part of legacy code,not removed.
		if (!distinctRequired) {
			if (inRequired()) {
				String modifiedQ = originalQ.replaceFirst(destalias, destalias
						+ ".id");
				String suffix = "from " + criteria.getTargetObjectName()
						+ " as " + destalias + " where " + destalias
						+ ".id in (" + modifiedQ + ")";
				normalQ = "select " + destalias + " " + suffix;
				countQ = "select count(*) " + suffix;
			} else {
				normalQ = originalQ;
				countQ = getCountQuery(originalQ);
			}
		} else {
			normalQ = originalQ.replaceFirst("select " + destalias,
					"select distinct (" + destalias + ") ");
			countQ = originalQ.replaceFirst("select " + destalias,
					"select count(distinct " + destalias + ".id) ");
		}
		log.debug("****** NormalQ: " + normalQ);
		log.debug("****** CountQ: " + countQ);
		HQLCriteria hCriteria = new HQLCriteria(normalQ, countQ, paramList);
		return hCriteria;
	}

	private String getObjectAttributeCriterion(String sourceAlias, Object obj,
			Configuration cfg, String parentClassName, String parentRoleName,
			StringBuffer aliasSetBuffer) throws Exception {
		StringBuffer whereClause = new StringBuffer();
		Map<String, Object> criterionMap = getObjAttrCriterion(obj, cfg,
				parentClassName, parentRoleName);
		String rootKlass = obj.getClass().getName();
		PersistentClass pclass = getPersistentClass(rootKlass, parentClassName,
				parentRoleName);

		if (criterionMap != null) {
			Iterator<String> keys = criterionMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = criterionMap.get(key);
				boolean isStringObject = !key.equals("id")
						&& (value instanceof String);

				if (isStringObject) {
					if (isCaseSensitive()) {
						whereClause.append(sourceAlias + SystemConstant.DOT
								+ key + getOperator(value) + "? ");
						paramList
								.add(((String) value).replaceAll("\\*", "\\%"));
					} else {
						whereClause.append("lower(" + sourceAlias
								+ SystemConstant.DOT + key + ") "
								+ getOperator(value) + "? ");
						paramList.add(((String) value).toLowerCase()
								.replaceAll("\\*", "\\%"));
					}
				} else {
					boolean isIsoObject = !key.equals("id")
							&& (value.getClass().getName()
									.startsWith(isoprefix));
					if (isIsoObject) {
						StringBuffer tempBuffer = new StringBuffer();
						Value componentValue = locateComponent(pclass, key);
						String queryAppender = sourceAlias + SystemConstant.DOT
								+ key;
						String rootKlassWithAtt = rootKlass + "." + key;
						generateISOWhereQuery(value, tempBuffer, whereClause,
								componentValue, "", queryAppender, 0,
								aliasSetBuffer, rootKlassWithAtt);
					} else {
						whereClause.append(sourceAlias)
								.append(SystemConstant.DOT).append(key)
								.append(getOperator(value)).append("? ");
						paramList.add(value);
					}
				}
				if (keys.hasNext())
					whereClause.append(" and ");
			}
		}
		return whereClause.toString();
	}

	private Value locateComponent(Object pV, String key) {
		Property property = null;
		if (pV instanceof Component) {
			property = ((Component) pV).getProperty(key);
		} else if (pV instanceof PersistentClass) {
			property = ((PersistentClass) pV).getProperty(key);
		}
		if (property == null) {
			return null;
		}
		Value value = property.getValue();
		return value;
	}

	/**
	 * 
	 * @param obj
	 *            sourceObject
	 * @param query
	 *            tempStringBuffer to hold the state of HQL object
	 *            (i.e)value1.item in recursion
	 * @param whereQueryClause
	 *            Final Result to be appended to the HQL
	 * @param componentValue
	 *            recursively parse PersistentClass to find required Component
	 * @param parentheses
	 *            temporary value for adding ( braces and ) braces in case of
	 *            Set ISO Object
	 * @param queryAppender
	 *            temporary value to hold sourceAlias + key value
	 * @param andCount
	 *            temporary value to maintain state whether to add "and"
	 *            condition or not
	 * @param aliasSetBuffer
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes"})
	private void generateISOWhereQuery(Object obj, StringBuffer query,
			StringBuffer whereQueryClause, Value componentValue,
			String parentheses, String queryAppender, Integer andCount,
			StringBuffer aliasSetBuffer, String rootKlassAttr) throws Exception {

		Class klass = obj.getClass();
		while (!klass.getName().equals("java.lang.Object")) {
			for (Field field : klass.getDeclaredFields()) {
				int modifier = field.getModifiers();
				if (!Modifier.isStatic(modifier)) {
					field.setAccessible(true);
					Object value = field.get(obj);
					if (value != null) {
						String fieldName = field.getName();
						if (fieldName.equals("precision")
								&& value instanceof Integer
								&& ((Integer) value) == 0) {
							continue;
						} else if ((value instanceof AddressPartType || value instanceof EntityNamePartType)
								&& fieldName.equals("type")) {
							continue;
						}
						String objectClassName = value.getClass().getName();
						StringBuffer newQuery = new StringBuffer(
								query.toString());
						newQuery.append(SystemConstant.DOT).append(fieldName);
						boolean isoObject = objectClassName
								.startsWith(isoprefix);
						// parse the set and create an hql Set<CD>
						boolean isSetObject = objectClassName
								.startsWith("java.util.HashSet");
						boolean isEnumObject = value.getClass().isEnum();
						boolean isListObject = objectClassName
								.startsWith("java.util.ArrayList");
						if (isListObject) {
							if (fieldName.equals("partsInternal"))
								continue;
							processISOWhereQueryIfListObject(whereQueryClause,
									componentValue, queryAppender, andCount,
									aliasSetBuffer, rootKlassAttr, value,
									newQuery);
						} else {
							if (fieldName.equals("partRestriction"))
								continue;
							Value persistChildvalue = locateComponent(
									componentValue, fieldName);
							if (isoObject & !isEnumObject) {
								parentheses = "";
								generateISOWhereQuery(value, newQuery,
										whereQueryClause, persistChildvalue,
										parentheses, queryAppender, andCount++,
										aliasSetBuffer, rootKlassAttr);
							} else if (isSetObject) {
								Set set = (Set) value;
								int count = 0;
								for (Object object : set) {
									String setItemAlias = getAlias(object
											.getClass().getName(), aliasCount++);
									aliasSetBuffer.append(" inner join "
											+ queryAppender
											+ newQuery.toString() + " as "
											+ setItemAlias);
									parentheses = (count == 0)
											? " (( "
											: ") or ( ";
									generateISOWhereQuery(object,
											new StringBuffer(),
											whereQueryClause,
											persistChildvalue, parentheses,
											setItemAlias, andCount,
											aliasSetBuffer, rootKlassAttr);
									count++;
								}
								whereQueryClause.append(" )) ");
							} else {
								String tempQuery = null;
								if (isCaseSensitive()) {
									tempQuery = parentheses + "lower("
											+ queryAppender
											+ newQuery.toString() + " )";
								} else {
									tempQuery = parentheses + queryAppender
											+ newQuery.toString();
								}
								parentheses = "";
								if (andCount > 0)
									whereQueryClause.append(" and ");
								whereQueryClause.append(tempQuery
										+ getOperator(value) + "? ");
								if (value instanceof String) {
									paramList.add(((String) value).replaceAll(
											"\\*", "\\%"));
								} else {
									paramList.add(value);
								}
								andCount++;
							}
						}
					}
				}
			}
			klass = klass.getSuperclass();
		}
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void processISOWhereQueryIfListObject(
			StringBuffer whereQueryClause, Value componentValue,
			String queryAppender, Integer andCount,
			StringBuffer aliasSetBuffer, String rootKlassAttr, Object value,
			StringBuffer newQuery) throws Exception {
		
		List list = (List) value;
		boolean isEnDataType = list.size() > 0
				&& list.iterator().next().getClass()
						.isAssignableFrom(Enxp.class);
		if (isEnDataType) {
			resolveISOWhereQueryForEN((List) value, newQuery,
					(Component) componentValue, whereQueryClause,
					queryAppender, andCount, aliasSetBuffer, rootKlassAttr);
		} else if (componentValue.getClass().isAssignableFrom(
				org.hibernate.mapping.Set.class)) {
			org.hibernate.mapping.Set set = (org.hibernate.mapping.Set) componentValue;
			Component component = (Component) set.getElement();
			resolveISOWhereQueryForAd((List) value, newQuery, component,
					whereQueryClause, queryAppender, andCount, aliasSetBuffer,
					rootKlassAttr);
		} else {
			resolveISOWhereQueryForAd((List) value, newQuery,
					(Component) componentValue, whereQueryClause,
					queryAppender, andCount, aliasSetBuffer, rootKlassAttr);
		}
	}

	private void resolveISOWhereQueryForEN(List<Enxp> valueList,
			StringBuffer newQuery, Component componentValue,
			StringBuffer whereQueryClause, String queryAppender,
			Integer andCount, StringBuffer aliasSetBuffer, String rootKlassAttr)
			throws Exception {

		IsoConstantTuplizerHelper tuplizerHelper = new IsoConstantTuplizerHelper();
		ComplexNode complexNode = tuplizerHelper
				.getComplexNodeBean(rootKlassAttr);
		List<Node> nodes = complexNode.getInnerNodes();
		Map<String, String> map = new HashMap<String, String>();
		String value=null;
		for (Node node : nodes) {
			if(node instanceof ConstantNode){
				continue;
			}
			ComplexNode innerComplexNode = (ComplexNode) node;
			value = innerComplexNode.getName();
			String key = null;
			List<Node> innerNodes = innerComplexNode.getInnerNodes();
			for (Node innerNode : innerNodes) {
				if (innerNode instanceof ConstantNode
						&& innerNode.getName().equals("type")) {
					ConstantNode constantNode = (ConstantNode) innerNode;
					key = constantNode.getConstantValue();
					map.put(key, value);
					break;
				}
			}
		}
		if (value == null) {
			String exceptionMessage = "mapped-constant of 'type' was not specified for "
					+ rootKlassAttr + " in EA-Model.";
			log.error(exceptionMessage);
			throw new Exception(exceptionMessage);
		}
		Set<String> keySet = map.keySet();
		for (Enxp enxp : valueList) {
			StringBuffer tempNewQuery = new StringBuffer(newQuery.toString());
			EntityNamePartType entityNamePartType = enxp.getType();
			String partNameMatch = entityNamePartType.toString();
			if (keySet.contains(partNameMatch)) {
				String partName = map.get(partNameMatch);
				int index = partName.indexOf('_');
				String componentPartName = partName.substring(index,
						partName.length());
				tempNewQuery.append(componentPartName);
				String parentheses = "";
				Value persistChildvalue = locateComponent(componentValue,
						partName);
				generateISOWhereQuery(enxp, tempNewQuery, whereQueryClause,
						persistChildvalue, parentheses, queryAppender,
						andCount++, aliasSetBuffer, rootKlassAttr);
			} else {
				String message = "No matching found for the input 'type' with the mapped-constant specified in EA-Model";
				log.error(message);
				throw new Exception(message);
			}
		}
	}

	private void resolveISOWhereQueryForAd(List<? extends Object> valueList,
			StringBuffer newQuery, Component componentValue,
			StringBuffer whereQueryClause, String queryAppender,
			Integer andCount, StringBuffer aliasSetBuffer, String rootKlassAttr)
			throws Exception {
		
		Map<String, Set<String>> addressPartTypeMap = new HashMap<String, Set<String>>();
		for (Object object : valueList) {
			Field partInstanceObjectField = null;
			StringBuffer tempNewQuery = new StringBuffer(newQuery.toString());
			partInstanceObjectField = getDeclaredField(object.getClass(),
					"type");
			partInstanceObjectField.setAccessible(true);
			Object partType = partInstanceObjectField.get(object);
			Component partComponent = (Component) componentValue;
			@SuppressWarnings("unchecked")
			Iterator<Property> propertyItr = partComponent
					.getPropertyIterator();
			boolean isValidPartType = false;
			while (propertyItr.hasNext()) {
				Property property = propertyItr.next();
				String partName = property.getName();
				Component psComp = (Component) property.getValue();
				String psCompClassName = psComp.getComponentClassName();
				String partTypeClassName = partType.getClass().getName();
				String partTypeName = partTypes.get(partTypeClassName)
						+ partType;

				if (psCompClassName.equalsIgnoreCase(partTypeName)) {
					// search for matching part name (part_0,part_1,part_2 etc)
					if (addressPartTypeMap.keySet().contains(partTypeName)) {
						Set<String> parts = addressPartTypeMap
								.get(partTypeName);
						if (parts.contains(partName)) {
							continue;
						} else {
							parts.add(partName);
						}
					} else {
						Set<String> parts = new HashSet<String>();
						parts.add(partName);
						addressPartTypeMap.put(partTypeName, parts);
					}
					Value persistChildvalue = locateComponent(componentValue,
							partName);
					int index = partName.indexOf('_');
					String componentPartName = partName.substring(index,
							partName.length());
					tempNewQuery.append(componentPartName);
					String parentheses = "";
					generateISOWhereQuery(object, tempNewQuery,
							whereQueryClause, persistChildvalue, parentheses,
							queryAppender, andCount++, aliasSetBuffer,
							rootKlassAttr);
					isValidPartType = true;
					break;
				}
			}
			if (!isValidPartType) {
				throw new Exception(" Invalid Address PartType " + partType
						+ "  specified in RESTfulQuery");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private boolean isObjectPrimitive(Object value) {
		if (value instanceof Collection && ((Collection) value).size() > 0) {
			return false;
		}
		if (!(value instanceof Integer || value instanceof Float
				|| value instanceof Double || value instanceof Character
				|| value instanceof Long || value instanceof Boolean
				|| value instanceof Byte || value instanceof Short
				|| value instanceof String || value instanceof Date)) {
			return false;
		}
		return true;
	}

	private Map<String, Object> getObjAttrCriterion(Object obj,
			Configuration cfg, String parentClassName, String parentRoleName)
			throws Exception {

		Map<String, Object> criterions = new HashMap<String, Object>();
		String objClassName = obj.getClass().getName();
		PersistentClass pclass = getPersistentClass(objClassName,
				parentClassName, parentRoleName);

		if (pclass != null) {
			Map<String, Object> tempObjectCriterion = getCriterionForComponentAndAttribute(
					obj, pclass);
			if (tempObjectCriterion != null) {
				criterions.putAll(tempObjectCriterion);
			}
			while (pclass.getSuperclass() != null) {
				pclass = pclass.getSuperclass();
				if (pclass != null) {
					Map<String, Object> tempObjectCriterion2 = getCriterionForComponentAndAttribute(
							obj, pclass);
					if (tempObjectCriterion2 != null) {
						criterions.putAll(tempObjectCriterion2);
					}

				}
			}
			Property identifierProperty = pclass.getIdentifierProperty();
			// in case of iso-datatypes identifier property is hidden.
			if (identifierProperty == null) {
				return criterions;
			}
			String identifier = identifierProperty.getName();
			Field idField = getDeclaredField(pclass.getMappedClass(),
					identifier);
			idField.setAccessible(true);
			try {
				if (idField.get(obj) != null)
					criterions.put(identifier, idField.get(obj));
			} catch (Exception e) {
				// Do nothing - when dealing with implicit queries, pclass would
				// be the concrete subclass of obj,
				// and the identifier field might be in a subclass of obj
			}
		}
		return criterions;
	}

	@SuppressWarnings({"rawtypes"})
	private Map<String, Object> getCriterionForComponentAndAttribute(
			Object obj, PersistentClass pclass) throws Exception {
		
		Map<String, Object> criterions = new HashMap<String, Object>();
		Iterator properties = pclass.getPropertyIterator();
		if (!properties.hasNext()) {
			return null;
		}
		Property tempProperty = (Property) pclass.getPropertyIterator().next();
		String propertyAccessorName = tempProperty.getPropertyAccessorName();
		boolean isPropertyCollectionAccessor = propertyAccessorName
				.equals("gov.nih.nci.iso21090.hibernate.property.CollectionPropertyAccessor");

		if ((tempProperty.getType().isComponentType() && isPropertyCollectionAccessor)) {
			String tempFieldName = tempProperty.getName();
			Field field = null;
			int truncatedFieldLength = tempFieldName.length() - 2;
			String tempComFieldName = tempFieldName.substring(0,
					truncatedFieldLength);
			field = getDeclaredField(pclass.getMappedClass(), tempComFieldName);
			field.setAccessible(true);
			List parts = (List) field.get(obj);
			boolean hasFoundComponent = false;
			while (properties.hasNext()) {
				Property prop = (Property) properties.next();
				String fieldName = prop.getName();
				Component psComp = (Component) prop.getValue();
				String psCompClassName = psComp.getComponentClassName();
				Adxp adxpPart = null;
				for (Object part : parts) {
					adxpPart = (Adxp) part;
					String addressPartTypeName = "gov.nih.nci.iso21090.Adxp"
							+ adxpPart.getType().name();
					if (psCompClassName.equalsIgnoreCase(addressPartTypeName)) {
						String comFieldName = fieldName;
						criterions.put(comFieldName, adxpPart);
						hasFoundComponent = true;
						break;
					}
				}
				if (hasFoundComponent)
					parts.remove(adxpPart);
			}
		} else {
			while (properties.hasNext()) {
				Property prop = (Property) properties.next();
				if (!prop.getType().isAssociationType()) {
					String fieldName = prop.getName();
					Field field = getDeclaredField(pclass.getMappedClass(),
							fieldName);
					field.setAccessible(true);
					try {
						if (field.get(obj) != null) {
							if (prop.getType().getName().equals("text"))
								criterions.put(fieldName, field.get(obj) + "*");
							else
								criterions.put(fieldName, field.get(obj));
						}
					} catch (Exception e) {
						// Do nothing - when dealing with implicit queries,
						// pclass
						// would be the concrete subclass of obj, and thus
						// contain
						// fields that are not in obj
					}
				}
			}
		}
		return criterions;
	}

	private String getHQLQueryFromSourceObjectWithCriterion(Object obj,
			Configuration cfg, boolean skipAssociations) throws Exception {
		
		return getHQLQueryFromSourceObjectWithCriterion(obj, cfg,
				skipAssociations, null, null);
	}

	// get the result HQL String from the Object obj
	private String getHQLQueryFromSourceObjectWithCriterion(Object obj,
			Configuration cfg, boolean skipAssociations, String parentClass,
			String parentRoleName) throws Exception {
		String srcAlias = getAlias(obj.getClass().getName(), 1);

		StringBuffer hql = new StringBuffer();
		Map<String, Object> associationCritMap = null;
		if (!skipAssociations) {
			associationCritMap = getAssociatedObjectCriterion(obj, cfg,
					parentClass, parentRoleName);
			PersistentClass tempPclass = getPersistentClass(obj.getClass()
					.getName(), parentClass, parentRoleName);
			hql.append("select ");
			hql.append(srcAlias);
			hql.append(" from ").append(tempPclass.getEntityName()).append(" ")
					.append(srcAlias);

			if (associationCritMap != null && associationCritMap.size() > 0) {
				StringBuffer associationHQL = generateAssociationHQLCriteria(
						cfg, srcAlias, associationCritMap, tempPclass);
				hql.append(associationHQL);
			}
		}
		StringBuffer aliasSetBuffer = new StringBuffer();
		String attributeCriteria = getObjectAttributeCriterion(srcAlias, obj,
				cfg, parentClass, parentRoleName, aliasSetBuffer);
		hql.append(aliasSetBuffer.toString());
		if ((associationCritMap == null || associationCritMap.size() == 0
				&& attributeCriteria != null
				&& attributeCriteria.trim().length() > 0)
				&& !skipAssociations)
			hql.append(" where ");
		if (associationCritMap != null && associationCritMap.size() > 0
				&& attributeCriteria != null
				&& attributeCriteria.trim().length() > 0)
			hql.append(" ").append(" and ");

		hql.append(attributeCriteria);
		log.info("HQL query: " + hql.toString());
		return hql.toString();
	}

	@SuppressWarnings("rawtypes")
	private StringBuffer generateAssociationHQLCriteria(Configuration cfg,
			String srcAlias, Map<String, Object> associationCritMap,
			PersistentClass tempPclass) throws Exception {
		Iterator<String> associationKeys = associationCritMap.keySet()
				.iterator();
		StringBuffer hql = new StringBuffer();
		int counter = 0;
		while (associationKeys.hasNext()) {
			String roleName = associationKeys.next();
			Object roleValue = associationCritMap.get(roleName);
			if (roleValue instanceof Collection) {
				Object[] objs = ((Collection) roleValue).toArray();
				for (int i = 0; i < objs.length; i++) {
					PersistentClass tempRolePclass = getPersistentClass(objs[i]
							.getClass().getName(), tempPclass.getEntityName(),
							roleName);
					String alias = getAlias(objs[i].getClass().getName(),
							counter++);
					hql.append(",").append(tempRolePclass.getEntityName())
							.append(" ").append(alias);
				}
			} else {
				PersistentClass tempRolePclass = getPersistentClass(roleValue
						.getClass().getName(), tempPclass.getEntityName(),
						roleName);
				String alias = getAlias(roleValue.getClass().getName(),
						counter++);
				hql.append(",").append(tempRolePclass.getEntityName())
						.append(" ").append(alias);
			}
		}
		hql.append(" where ");
		associationKeys = associationCritMap.keySet().iterator();
		counter = 0;
		while (associationKeys.hasNext()) {
			String roleName = associationKeys.next();
			Object roleValue = associationCritMap.get(roleName);
			if (roleValue instanceof Collection) {
				Object[] objs = ((Collection) roleValue).toArray();
				for (int i = 0; i < objs.length; i++) {
					String alias = getAlias(objs[i].getClass().getName(),
							counter++);
					hql.append(alias).append(" in elements(").append(srcAlias)
							.append(".").append(roleName).append(")");
					hql.append(" and ");
					String objectCriterion = getHQLQueryFromSourceObjectWithCriterion(
							objs[i], cfg, false, tempPclass.getEntityName(),
							roleName);
					hql.append(alias).append(" in (").append(objectCriterion)
							.append(") ");
					if (i < objs.length - 1)
						hql.append(" and ");
				}
			} else {
				String alias = getAlias(roleValue.getClass().getName(),
						counter++);
				hql.append(alias).append(".id").append("=").append(srcAlias)
						.append(".").append(roleName).append(".id");
				hql.append(" and ");
				String objectCriterion = getHQLQueryFromSourceObjectWithCriterion(
						roleValue, cfg, false, tempPclass.getEntityName(),
						roleName);
				hql.append(alias).append(" in (").append(objectCriterion)
						.append(") ");
			}
			hql.append(" ");
			if (associationKeys.hasNext())
				hql.append(" and ");
		}
		return hql;
	}

	@SuppressWarnings({"rawtypes"})
	private Map<String, Object> getAssociatedObjectWithFieldCriterion(
			Object obj, PersistentClass pclass) throws Exception {
		Map<String, Object> criterions = new HashMap<String, Object>();
		Iterator properties = pclass.getPropertyIterator();
		while (properties.hasNext()) {
			Property prop = (Property) properties.next();
			if (prop.getType().isAssociationType()) {
				String fieldName = prop.getName();
				Field field = getDeclaredField(pclass.getMappedClass(),
						fieldName);
				field.setAccessible(true);
				try {
					Object value = field.get(obj);
					if (value != null)
						if ((value instanceof Collection && ((Collection) value)
								.size() > 0) || !(value instanceof Collection))
							criterions.put(fieldName, field.get(obj));
				} catch (Exception e) {
					// Do nothing - when dealing with implicit queries, pclass
					// would be the concrete subclass of obj, and thus contain
					// fields that are not in obj
				}
			}
		}
		return criterions;
	}

	// object--->has component---> and it has set then add distinct to it. As
	// component set don't have primary key
	// avoid duplication of result set.
	@SuppressWarnings("rawtypes")
	private boolean isComponentSetCriterion(Object obj) throws Exception {
		PersistentClass pclass = getPersistentClass(obj.getClass().getName(),
				null, null);
		Iterator properties = pclass.getPropertyIterator();
		while (properties.hasNext()) {
			Property prop = (Property) properties.next();
			// is component
			if (prop.getType().isComponentType()) {
				String fieldName = prop.getName();
				Field field = getDeclaredField(pclass.getMappedClass(),
						fieldName);
				field.setAccessible(true);
				Object value = field.get(obj);
				if (value != null) {
					Field[] fields = value.getClass().getDeclaredFields();
					for (Field componentField : fields) {
						componentField.setAccessible(true);
						Object comFieldObject = componentField.get(value);
						// is of Type Set
						if (comFieldObject != null
								&& (comFieldObject instanceof Collection && ((Collection) comFieldObject)
										.size() > 0)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private Map<String, Object> getAssociatedObjectCriterion(Object obj,
			Configuration cfg, String parentClassName, String parentRoleName)
			throws Exception {
		Map<String, Object> criterions = null;
		Map<String, Object> superClassCriterions = null;
		String objClassName = obj.getClass().getName();

		PersistentClass pclass = getPersistentClass(objClassName,
				parentClassName, parentRoleName);
		if (pclass != null) {
			criterions = getAssociatedObjectWithFieldCriterion(obj, pclass);

			pclass = pclass.getSuperclass();
			while (pclass != null) {
				superClassCriterions = getAssociatedObjectWithFieldCriterion(
						obj, pclass);
				pclass = pclass.getSuperclass();
			}
		}
		if (superClassCriterions != null)
			criterions.putAll(superClassCriterions);
		return criterions;
	}

	private String getAlias(String sourceName, int count) {
		String alias = sourceName.substring(sourceName
				.lastIndexOf(SystemConstant.DOT) + 1);
		alias = alias.substring(0, 1).toLowerCase() + alias.substring(1);
		return alias + "_" + count;
	}

	private String getOperator(Object valueObj) {
		if (valueObj instanceof java.lang.String) {
			String value = (String) valueObj;
			if (value.indexOf('*') >= 0) {
				return " like ";
			}
		}
		return "=";
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	@SuppressWarnings("rawtypes")
	private Field getDeclaredField(Class klass, String fieldName)
			throws NoSuchFieldException {
		Field field = null;
		do {
			try {
				field = klass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				field = null;
				klass = klass.getSuperclass();
			}
		} while (!klass.getName().equals("java.lang.Object") && field == null);
		if (field == null)
			throw new NoSuchFieldException("fieldName: " + fieldName);
		return field;
	}

	private PersistentClass getPersistentClass(String objClassName,
			String parentObjectClassName, String parentRoleName)
			throws Exception {
		List<PersistentClass> pClasses = getPersistentClassList(objClassName);
		if (pClasses.size() == 1) {
			return pClasses.iterator().next();
		}
		if (parentObjectClassName == null || parentRoleName == null
				&& pClasses.size() > 1) {
			log.error("Object Class name "
					+ objClassName
					+ "is a 'parent class' and also has multiple persistent classes");
			throw new Exception("NestedCriteria Object to HQL conversion error");
		}
		for (PersistentClass persistentClass : pClasses) {
			parentObjectClassName = parentObjectClassName.replace('.', '_');
			String tempSubString = parentObjectClassName + "_" + parentRoleName;
			String childPClassName = persistentClass.getEntityName();
			if (childPClassName.contains(tempSubString)) {
				return persistentClass;
			}
		}
		return null;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private List<PersistentClass> getPersistentClassList(String objClassName) {
		List<PersistentClass> pClasses = new ArrayList<PersistentClass>();
		PersistentClass pclass = cfg.getClassMapping(objClassName);
		if (pclass == null) {// might be dealing with an implicit class. Check
								// to see if we have a persistent subclass
								// mapping we can use instead
			Iterator iter = cfg.getClassMappings();
			Class objClass = null;
			try {
				objClass = Class.forName(objClassName);
			} catch (ClassNotFoundException e1) {
				log.error("Class not found for " + objClassName);
				return null;
			}
			while (iter.hasNext()) {
				pclass = (PersistentClass) iter.next();
				try {
					(pclass.getMappedClass()).asSubclass(objClass);
					log.debug("Searching for persistent subclass of "
							+ objClassName + "; found " + pclass.getClassName());
					pClasses.add(pclass);
				} catch (Exception e) {
					// do nothing
				}
			}// while
		} else {
			pClasses.add(pclass);
		}
		return pClasses;
	}

	@SuppressWarnings("rawtypes")
	private boolean isObjectEmpty(Object obj) {
		Class klass = obj.getClass();
		while (!klass.getName().equals("java.lang.Object")) {
			for (Field field : klass.getDeclaredFields()) {
				int modifier = field.getModifiers();
				if (!Modifier.isStatic(modifier)) {
					try {
						field.setAccessible(true);
						Object value = field.get(obj);
						if (value != null && value instanceof Collection
								&& ((Collection) value).size() > 0)
							return false;
						if (value != null)
							return false;
					} catch (IllegalArgumentException e) {
						// No action
					} catch (IllegalAccessException e) {
						// No action
					}
				}
			}
			klass = klass.getSuperclass();
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	private boolean isObjectAssociationEmpty(Object obj) throws Exception {
		Class klass = obj.getClass();
		while (!klass.getName().equals("java.lang.Object")) {
			for (Field field : klass.getDeclaredFields()) {
				int modifier = field.getModifiers();
				if (!Modifier.isStatic(modifier)) {
					try {
						field.setAccessible(true);
						Object value = field.get(obj);
						if (value != null) {
							isObjectPrimitive(value);
						}
					} catch (IllegalArgumentException e) {
						// No action
					} catch (IllegalAccessException e) {
						// No action
					}
				}
			}
			klass = klass.getSuperclass();
		}
		return true;
	}
}