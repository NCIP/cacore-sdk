package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.ClassCache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import gov.nih.nci.system.web.util.RESTUtil;

public class RestQuery extends BaseActionSupport {

	private static final long serialVersionUID = 1234567890L;

	private static Logger log = Logger.getLogger(Result.class.getName());

	protected ClassCache classCache;
	WebApplicationContext ctx;
	protected String selectedSearchDomain;
	boolean secured=false;
	boolean isoEnabled = false;
	final String isoprefix = "gov.nih.nci.iso21090.";

	public void init() throws Exception {

		ServletContext context = ServletActionContext.getServletContext();
		ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		classCache = (ClassCache) ctx.getBean("ClassCache");
		Properties systemProperties = (Properties) ctx
					.getBean("WebSystemProperties");

		String securityEnabled = (String) systemProperties
				.getProperty("securityEnabled");
		secured = "yes".equalsIgnoreCase(securityEnabled)
				|| "true".equalsIgnoreCase(securityEnabled);
		String isoEnabledStr = (String) systemProperties
				.getProperty("enableISO21090DataTypes");
		isoEnabled = "yes".equalsIgnoreCase(isoEnabledStr)
				|| "true".equalsIgnoreCase(isoEnabledStr);
	}

	protected String getQueryString(String className, String roleName,
			HttpServletRequest request) {

		StringBuilder sb = new StringBuilder();
		Enumeration<String> parameters = request.getParameterNames();

		Map<String, Map<String, List<Object>>> isoDataTypeNodes = new HashMap<String, Map<String, List<Object>>>();
		List<String> criteriaList = new ArrayList();
		while (parameters.hasMoreElements()) {
			String parameterName = (String) parameters.nextElement();
			log.debug("param = " + parameterName);
			if (!parameterName.equals("klassName")
					&& !parameterName.equals("searchObj")
					&& !parameterName.equals("BtnSearch")
					&& !parameterName.equals("username")
					&& !parameterName.equals("password")
					&& !parameterName.equals("selectedDomain")) {
				String parameterValue = (request.getParameter(parameterName))
						.trim();
				if (parameterValue.length() > 0) {
					criteriaList.add(parameterName + "=" + parameterValue);
					/*
					if (parameterName.indexOf('.') > 0) { // ISO data type
															// parameter
						saveIsoNode(isoDataTypeNodes, parameterName,
								parameterValue);
					} else { // non-ISO data type parameter
						criteriaList.add(parameterName + "=" + parameterValue);
					}*/
				}
			}
		}

		if (criteriaList.size() > 0) {
			StringBuffer sb2 = new StringBuffer();
			for (String criteria : criteriaList) {
				sb2.append(criteria + ";");

			}
			String returnStr = sb2.toString();
			if (returnStr.endsWith(";"))
				returnStr = returnStr.substring(0, returnStr.length() - 1);

			if (roleName != null && roleName.trim().length() > 0)
				returnStr = returnStr + "/" + roleName;

			return "search;" + returnStr;
		} else {
			try {
				String colName = classCache.getClassIdName(Class
						.forName(className));
				String returnStr = "search;" + colName + "=*";
				if (roleName != null && roleName.trim().length() > 0)
					returnStr = returnStr + "/" + roleName;

				return returnStr;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getResponseHTML(Element root, String className)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Result Class: " + className);
		buffer.append("</td>");

		buffer.append("<tr>");
		buffer.append("<td class=\"dataPagingText\" align=\"left\" style=\"border:0px; border-bottom:1px; border-style:solid; border-color:#5C5C5C;\">");
		buffer.append("<br />");
		buffer.append(root.getChildText("message"));
		buffer.append("<br />");
		buffer.append("<br />");
		buffer.append("</td>");
		buffer.append("</tr>");
		return buffer.toString();

	}
	/**
	 * Generates an HTML Document for a given XML document
	 *
	 * @param doc
	 *            Specifies the XML document
	 * @param className
	 *            Resulting class names
	 * @param queryStr
	 * 			  Query string used for the query
	 * @param roleName
	 * 			  role name in the query, if used
	 * @return String
	 * 			  HTML string
	 * @throws Exception
	 */
	protected String getHTML(Document doc, String className, String queryStr,
			String roleName) throws Exception {
		try {
			StringBuffer buffer = new StringBuffer();
			Map<String, String> header = new HashMap<String, String>();
			Map<String, List<String>> body = new HashMap<String, List<String>>();
			//Main table
			buffer.append("<table border=\"0\" bordercolor=\"orange\" summary=\"\" cellpadding=\"0\" cellspacing=\"0\">");
			buffer.append("<tr>");
			buffer.append("<td class=\"dataTablePrimaryLabel\" height=\"20\" align=\"left\">");
			String criteria = null;

			if (queryStr != null && queryStr.indexOf("search;") != -1)
				criteria = queryStr.substring(queryStr.indexOf("search;") + 7,
						queryStr.length());
			else
				criteria = queryStr;
			//Criteria value to display
			buffer.append("Criteria: " + criteria);
			buffer.append("<br />");

			Element root = doc.getRootElement();
			//If resulted XML is a response, capture the message to display
			if (root.getName().equals("response")) {
				buffer.append(getResponseHTML(root, className));
			} else {
				String classEleName = className;

				//Get resulted class name
				if (selectedSearchDomain != null
						&& !selectedSearchDomain.equals("Please choose")) {
					if (!selectedSearchDomain.equalsIgnoreCase(className)) {
						classEleName = selectedSearchDomain;
					}
				}
				//If role name used
				if (roleName != null) {
					classEleName = getTargetClassName(className, roleName);
				}

				buffer.append("Result Class: " + classEleName);
				buffer.append("</td>");

				buffer.append("<td align=\"right\" class=\"dataPagingText\">");
				buffer.append(getPagingLinks(root));
				buffer.append("</td>");

				//Get Id column. This will be used to display Id column first in the results table
				String idCol = null;
				String idColValue = null;
				try {
					idCol = classCache.getClassIdName(Class
							.forName(classEleName));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				//System.out.println("idCol: "+idCol);
				buffer.append("<tr>");
				buffer.append("<td>");
				buffer.append("<table summary=\"Data Summary\" cellpadding=\"3\" cellspacing=\"0\" border=\"0\" class=\"dataTable\" width=\"100%\">");

				//For each sub element to the root:
				//if it is link, skip it. This is link elements for collections representing paging, self
				List<Element> tableRows = root.getChildren();
				//System.out.println("tableRows: "+tableRows.toString());
				//System.out.println("tableRows size: "+tableRows.size());
				List columns = new ArrayList();
				for (Element child : tableRows) {
					StringBuffer headerBuffer = new StringBuffer();
					StringBuffer bodyBuffer = new StringBuffer();

					String childName = classEleName.substring(classEleName.lastIndexOf(".")+1, classEleName.length());
					//System.out.println("childName: "+childName);
					//System.out.println("child.getName(): "+child.getName());
					//if(!child.getName().equals(childName))
					//	continue;

					if(child.getName().equals("link"))
						continue;

					//Get element class name. There could be different classes in the given XML
					//representing inheritance
					String fullClassName = classCache.getPkgNameForClass(child
							.getName()) + "." + child.getName();

					headerBuffer.append("<tr>");
					bodyBuffer.append("<tr>");

					Class klass = classCache.getClassFromCache(fullClassName);
					Field[] fields = classCache.getAllFields(klass);
					//Arrays.sort(fields);

					//Get links for child element and add link ref name to list
					List<String> refNameList = new ArrayList();
					List<String> assocNames = classCache.getAssociations(fullClassName);
					for (String assocName : assocNames) {
						boolean foundLink = false;
						if(assocName.indexOf("(") == -1)
							continue;
						else
						{
							refNameList.add(assocName.substring(0, assocName.indexOf("(")).trim());
						}
					}

					refNameList.add("self");
					String idColName = classCache.getClassIdName(klass);
					//System.out.println("getHTML: "+idColName);

					// Add id column to the table first
					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						if (!field.getName().equals(idColName))
							continue;

						headerBuffer
								.append("<th class=\"dataTableHeader\" scope=\"col\" align=\"center\">");
						headerBuffer.append(idColName);
						headerBuffer.append("</th>");

						bodyBuffer
								.append("<td class=\"dataCellText\" nowrap=\"off\">");
						//System.out.println("child "+child.toString());
						Attribute attr = child.getAttribute(idColName);
						if (attr != null)
						{
							//System.out.println("Got Attr: "+attr.getName());
							bodyBuffer.append(attr.getValue());
							idColValue = attr.getValue();
						}
						else if(field.getType().getName().startsWith(isoprefix))
						{
							//System.out.println("ISO Type*****");
							Element childElement = getChild(child, field.getName(), true);
							if(childElement == null)
								bodyBuffer.append("&nbsp;");
							else
							{
								bodyBuffer.append("<table cellpadding=\"0\" cellspacing=\"2\" width=\"100%\" border=\"0\">");
								bodyBuffer.append("<tbody><tr class=\"dataRowLight\">");
								bodyBuffer.append("<td class=\"isoDataCellText\" nowrap=\"off\">");
								bodyBuffer.append(formatISOElement(childElement));
								bodyBuffer.append("</td></tr>");
								bodyBuffer.append("</tbody></table>");
							}
						}

						bodyBuffer.append("</td>");

						break;
					}

					// Add all remaining columns, not including references
					for (int i = 0; i < fields.length; i++) {
						Field field = fields[i];
						if (field.getName().equals(idColName) || field.getName().equals("links") || field.getName().equals("serialVersionUID") || refNameList.contains(field.getName()))
							continue;

						headerBuffer
								.append("<th class=\"dataTableHeader\" scope=\"col\" align=\"center\">");
						headerBuffer.append(field.getName());
						headerBuffer.append("</th>");

						bodyBuffer
								.append("<td class=\"dataCellText\" nowrap=\"off\">");
						//System.out.println("Formatting field: "+field.getType().getName());
						//System.out.println("Formatting field: "+field.getName());
						Attribute attr = child.getAttribute(field.getName());

						if (attr != null)
						{
							//System.out.println("Got Attr: "+attr.getName());
							bodyBuffer.append(attr.getValue());
						}
						else if(field.getType().getName().startsWith(isoprefix))
						{
							//System.out.println("ISO Type*****");
							Element childElement = getChild(child, field.getName(), true);
							if(childElement == null)
								bodyBuffer.append("&nbsp;");
							else
							{
								bodyBuffer.append("<table cellpadding=\"0\" cellspacing=\"2\" width=\"100%\" border=\"0\">");
								bodyBuffer.append("<tbody><tr class=\"dataRowLight\">");
								bodyBuffer.append("<td class=\"isoDataCellText\" nowrap=\"off\">");
								bodyBuffer.append(formatISOElement(childElement));
								bodyBuffer.append("</td></tr>");
								bodyBuffer.append("</tbody></table>");

							}
						}
						else
						{
							bodyBuffer.append("&nbsp;");
						}
						bodyBuffer.append("</td>");
					}


					//List<String> assocNames = classCache.getAssociations(fullClassName);
					List<Element> linkChild = getChildren(child, "link");
					for (String linkName : refNameList) {
						//System.out.println("linkName: "+linkName);
						boolean foundLink = false;

						for (Element link : linkChild) {
							if(link.getAttribute("ref").getValue().equals(linkName))
							{
								headerBuffer
								.append("<th class=\"dataTableHeader\" scope=\"col\" align=\"center\">");
								headerBuffer.append("&nbsp;");
								headerBuffer.append("</th>");

								bodyBuffer
										.append("<td class=\"dataCellText\" nowrap=\"off\">");
								bodyBuffer.append("<A href=\"#\" onclick=\""
										+ "query('"
										+ link.getAttribute("href").getValue()
										+ "');return false;\"" + ">");
								bodyBuffer.append(link.getAttribute("ref").getValue());
								bodyBuffer.append("</A>");
								bodyBuffer.append("</td>");
								foundLink = true;
							}
						}
						if(!foundLink)
						{
							headerBuffer
							.append("<th class=\"dataTableHeader\" scope=\"col\" align=\"center\">");
							headerBuffer.append("&nbsp;");
							headerBuffer.append("</th>");

							bodyBuffer
									.append("<td class=\"dataCellText\" nowrap=\"off\">");
							bodyBuffer.append("&nbsp;");
							bodyBuffer.append("</td>");

						}
					}

/*
					for (Element link : linkChild) {
						headerBuffer
								.append("<th class=\"dataTableHeader\" scope=\"col\" align=\"center\">");
						headerBuffer.append("&nbsp;");
						headerBuffer.append("</th>");

						bodyBuffer
								.append("<td class=\"dataCellText\" nowrap=\"off\">");
						bodyBuffer.append("<A href=\"#\" onclick=\""
								+ "query('"
								+ link.getAttribute("href").getValue()
								+ "');return false;\"" + ">");
						bodyBuffer.append(link.getAttribute("ref").getValue());
						bodyBuffer.append("</A>");
						bodyBuffer.append("</td>");
					}
*/
					boolean updateLink = supportUpdateLink(classEleName);
					boolean deleteLink = supportDeleteLink(classEleName);

					if (updateLink) {
						headerBuffer
								.append("<th class=\"dataTableHeader\" scope=\"col\" align=\"center\">");
						headerBuffer.append("&nbsp;");
						headerBuffer.append("</th>");

						bodyBuffer
								.append("<td class=\"dataCellText\" nowrap=\"off\">");
						bodyBuffer
								.append("<A target=\"_blank\" href=\"Update.action?"
										+ idCol
										+ "="
										+ idColValue
										+ "&target="
										+ classEleName + "\">");
						bodyBuffer.append("Update");
						bodyBuffer.append("</A>");
						bodyBuffer.append("</td>");
					}

					if (deleteLink) {
						headerBuffer
								.append("<th class=\"dataTableHeader\" scope=\"col\" align=\"center\">");
						headerBuffer.append("&nbsp;");
						headerBuffer.append("</th>");

						bodyBuffer
								.append("<td class=\"dataCellText\" nowrap=\"off\">");
						bodyBuffer
								.append("<A target=\"_blank\" href=\"Delete.action?"
										+ idCol
										+ "="
										+ idColValue
										+ "&target="
										+ classEleName + "\">");
						bodyBuffer.append("Delete");
						bodyBuffer.append("</A>");
						bodyBuffer.append("</td>");
					}

					headerBuffer.append("</tr>");
					bodyBuffer.append("</tr>");
					String key = headerBuffer.toString();
					header.put(child.getName(), key);
					List<String> bodyList = body.get(child.getName());
					if (bodyList == null)
						bodyList = new ArrayList();

					bodyList.add(bodyBuffer.toString());
					body.put(child.getName(), bodyList);
				}

				Iterator headerIter = header.keySet().iterator();
				while (headerIter.hasNext()) {
					String headerName = (String) headerIter.next();
					String headerText = (String) header.get(headerName);
					buffer.append("<tr>");
					buffer.append("<th colspan=\"20\" align=\"left\" scope=\"col\" class=\"dataTableHeader\">");
					buffer.append(classCache.getPkgNameForClass(headerName)
							+ "." + headerName);
					buffer.append("</th>");
					buffer.append("</tr>");

					buffer.append(headerText);
					List<String> bodyList = body.get(headerName);
					for (String bodyRow : bodyList)
						buffer.append(bodyRow);

					buffer.append("<tr>");
					buffer.append("<th colspan=\"20\" align=\"left\" scope=\"col\" class=\"dataTableHeader\">");
					buffer.append("&nbsp;");
					buffer.append("</th>");
					buffer.append("</tr>");

				}
				buffer.append("</table>");
			}
			buffer.append("</td>");
			buffer.append("</tr>");
			return buffer.toString();

		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage());
			throw new ServletException(ex.getMessage());
		}
	}

	private String formatISOElement(Element element)
	{
		//System.out.println("formatISOElement: "+element.toString());
		//System.out.println("formatISOElement name: "+element.getName());
		if(element.getName().equals("link"))
			return "&nbsp;";
		List attributes = element.getAttributes();
		StringBuffer eleBuff = new StringBuffer();
		eleBuff.append("<table cellpadding=\"3\" cellspacing=\"0\" width=\"100%\" border=\"0\" class=\"isoDataTable\">");
		eleBuff.append("<tbody><tr class=\"dataRowLight\">");
		eleBuff.append("<td class=\"isoDataCellText\" nowrap=\"off\">");
		if(attributes != null)
		{
			Iterator iter = attributes.iterator();


			StringBuffer attrBuff = new StringBuffer();
			boolean dataAdded = false;
			while(iter.hasNext())
			{
				Attribute attr = (Attribute) iter.next();
				//System.out.println("attr.getName(): "+attr.getName());
				if(attr.getName().equals("xsi:type") || attr.getName().equals("type"))
					continue;
				attrBuff.append(attr.getName() + ": "+attr.getValue());
				if(iter.hasNext())
					attrBuff.append(";");
			}
			//if(!dataAdded)
			//	attrBuff.append("&nbsp;");
			eleBuff.append(attrBuff);
		}

		//System.out.println(" ele1: "+eleBuff.toString());
		List children = element.getChildren();
		if(children != null && children.size() > 0)
		{
			Iterator childrenIter = children.iterator();
			while(childrenIter.hasNext())
			{
			Element child = (Element) childrenIter.next();
			if(child.getName().equals("link"))
				return "&nbsp;";

			eleBuff.append("<table cellpadding=\"0\" cellspacing=\"2\" width=\"100%\" border=\"0\">");
			eleBuff.append("<tbody><tr class=\"dataRowLight\">");
			eleBuff.append("<td class=\"isoDataCellText\" nowrap=\"off\">");
			eleBuff.append(child.getName()+": </td>");
			eleBuff.append("<td width=\"100%\" align=\"left\" nowrap=\"off\">");
			eleBuff.append(formatISOElement(child));
			eleBuff.append("</td>");
			eleBuff.append("</tr>");
			eleBuff.append("</tbody></table>");
			}
		}

		eleBuff.append("</td>");
		eleBuff.append("</tr>");
		eleBuff.append("</tbody></table>");
		//System.out.println("Returning ele: "+eleBuff.toString());
		return eleBuff.toString();
	}
	public String getUnauthorizedHTML(String queryStr, String className, String message)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table border=\"0\" bordercolor=\"orange\" summary=\"\" cellpadding=\"0\" cellspacing=\"0\">");
		buffer.append("<tr>");
		buffer.append("<td class=\"dataTablePrimaryLabel\" height=\"20\" align=\"left\">");
		String criteria = null;

		if (queryStr != null && queryStr.indexOf("search;") != -1)
			criteria = queryStr.substring(queryStr.indexOf("search;") + 7,
					queryStr.length());
		else
			criteria = queryStr;
		buffer.append("Criteria: " + criteria);
		buffer.append("<br />");

			buffer.append("Result Class: " + className);
			buffer.append("</td>");

			buffer.append("<tr>");
			buffer.append("<td class=\"dataPagingText\" align=\"left\" style=\"border:0px; border-bottom:1px; border-style:solid; border-color:#5C5C5C;\">");
			buffer.append("<br />");
			buffer.append(message);
			buffer.append("<br />");
			buffer.append("<br />");
			buffer.append("</td>");
			buffer.append("</tr>");
			buffer.append("</td>");
			buffer.append("</tr>");
			return buffer.toString();


	}
	protected String getPagingLinks(Element root) {
		List<Element> links = getChildren(root, "links");
		if (links == null || links.size() == 0)
			return "&nbsp;";

		StringBuffer retStrNext = new StringBuffer();
		StringBuffer retStrPre = new StringBuffer();
		for (Element link : links) {
			if (link.getName().equalsIgnoreCase("Next")) {
				retStrNext.append("<A href=\"#\" onclick=\"" + "query('"
						+ link.getAttribute("href").getValue()
						+ "');return false;\"" + ">");
				retStrNext.append("Next");
				retStrNext.append("</A>");
			} else if (link.getName().equalsIgnoreCase("Previous")) {
				retStrPre.append("<A href=\"#\" onclick=\"" + "query('"
						+ link.getAttribute("href").getValue()
						+ "');return false;\"" + ">");
				retStrPre.append("Previous");
				retStrPre.append("</A>");
			}
		}

		return retStrPre.toString() + "&nbsp;&nbsp;&nbsp;"
				+ retStrNext.toString();
	}

	protected String getClassName(String href) {
		int index = href.indexOf("/rest/");
		String postStr = href.substring(index + 6, href.length());
		int index2 = postStr.indexOf("/");
		String resName = postStr;
		if (index2 != -1)
			resName = postStr.substring(0, index2);

		String pkgName = classCache.getPkgNameForClass(resName);
		return pkgName + "." + resName;
	}

	protected String getTargetClassName(String className, String roleName) {
		if (roleName == null)
			return className;
		List<String> assocNames = classCache.getAssociations(className);
		for (String assocName : assocNames) {
			if (assocName.startsWith(roleName)) {
				String assocClassName = assocName.substring(
						assocName.indexOf("(") + 1, assocName.lastIndexOf(")"));
				return assocClassName.trim();
			}
		}
		return null;
	}

	protected Element getDomainElement(Element root) {
		List<Element> children = root.getChildren();
		for (Element child : children) {
			if (!child.getName().equals("link"))
				return child;
		}
		return null;
	}

	protected Element getChild(Element root, String name, boolean ignoreNS) {
		List<Element> children = root.getChildren();
		for (Element child : children) {
			String childName = child.getName();
			if(ignoreNS)
			{
				if(childName.indexOf(":") != -1)
					childName = childName.substring(childName.indexOf(":")+1, childName.length());
			}
			if (childName.equals(name))
				return child;
		}
		return null;
	}

	protected List<Element> getChildren(Element root, String name) {
		List<Element> children = root.getChildren();
		List<Element> retchildren = new ArrayList();
		for (Element child : children) {
			if (child.getName().equals(name))
				retchildren.add(child);
		}
		return retchildren;
	}

	protected boolean supportDeleteLink(String className) {
		if(isoEnabled)
			return false;

		String cName = className.substring(className.lastIndexOf(".") + 1,
				className.length());
		try {
			Class klass = Class.forName(className + "Resource");

			Method[] allMethods = klass.getDeclaredMethods();
			String addName = "delete" + cName;
			for (Method m : allMethods) {
				String mname = m.getName();
				if (addName.equals(mname))
					return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected boolean supportUpdateLink(String className) {
		if(isoEnabled)
			return false;

		String cName = className.substring(className.lastIndexOf(".") + 1,
				className.length());
		try {
			Class klass = Class.forName(className + "Resource");

			Method[] allMethods = klass.getDeclaredMethods();
			String addName = "update" + cName;
			for (Method m : allMethods) {
				String mname = m.getName();
				if (addName.equals(mname))
					return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

		protected void prepareAssociations(HttpServletRequest request, Object instance, String className, String base64encodedUsernameAndPassword) throws Exception
		{
			List<String> associations = classCache.getAssociations(className);
			if(associations != null && associations.size() > 0)
			{
				for(int i=0; i<associations.size(); i++)
				{
					String asscName = (String)associations.get(i);
					System.out.println("asscName: "+asscName);
					String asscRole = null;
					String asscClass = asscName;
					if(asscName.indexOf("(") != -1)
					{
						asscClass = asscName.substring(asscName.indexOf("(")+1, asscName.lastIndexOf(")"));
						asscRole = asscName.substring(0, asscName.indexOf("("));
					}

					System.out.println("asscClass: "+asscClass);
					System.out.println("asscRole: "+asscRole);



					if(asscClass.equals("Please choose") || asscClass.equals(className))
						continue;
					String idName = classCache.getClassIdName(asscClass);
					String idType = classCache.getReturnType(asscClass, idName, true);

					Enumeration<String> parameters = request.getParameterNames();

					try
					{
						String getMethodName = "get"+ (asscRole.charAt(0)+"").toUpperCase()+ asscRole.substring(1,asscRole.length()).trim();
						Class classType = Class.forName(className);
						Method getMethod = classType.getMethod(getMethodName.trim(), null);
						Class returnType = getMethod.getReturnType();
						System.out.println("returnType "+returnType.getName());

						while(parameters.hasMoreElements())
						{
							String parameterName = (String)parameters.nextElement();
							if(parameterName.startsWith(asscRole) && parameterName.indexOf("(") > 0)
							{
								String paramValue = (request.getParameter(parameterName)).trim();
								if(paramValue != null && paramValue.trim().length() > 0)
								{
									Object assocObj = RESTUtil.getObject(asscClass, paramValue, request, base64encodedUsernameAndPassword);
									//System.out.println("assocObj: "+assocObj);
									if(assocObj != null)
									{
										 try {
											  Class klass = instance.getClass();
											  Class type = Class.forName(asscClass);
											  Class[] argTypes = new Class[] { type };
											  String methodName = "set"+ (asscRole.charAt(0)+"").toUpperCase()+ asscRole.substring(1,asscRole.length());
											  //System.out.println("Method name looking for: "+ methodName);
											  Method[] methods = klass.getDeclaredMethods();
											  for(int k=0;  k<methods.length; k++)
											  {
												  Method method = methods[k];
												  //System.out.println("Name: "+method.getName());
												  Class[] types = method.getParameterTypes();
												  if(types != null)
												  {
													  if(method.getName().trim().equals(methodName.trim()))
													  {
														  method.invoke(instance, returnType.cast(assocObj));
														  break;
													  }
												  }
											  }
											  break;
										 } catch (Exception ex) {
											 ex.printStackTrace();
											 throw ex;
										 }

									}
									else
									{
										throw new Exception("Invalid id: "+ paramValue  + " for Class: " + className);
									}
								}
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						throw e;
					}
					//RESTUtil.printObject(instance, instance.getClass(), true);
				}
			}
		}

}
