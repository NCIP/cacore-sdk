package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.ClassCache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

public class RestQuery extends BaseActionSupport {

	private static final long serialVersionUID = 1234567890L;

	private static Logger log = Logger.getLogger(Result.class.getName());

	protected ClassCache classCache;
	WebApplicationContext ctx;
	protected String selectedSearchDomain;
	boolean secured=false;

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

					if (parameterName.indexOf('.') > 0) { // ISO data type
															// parameter
						saveIsoNode(isoDataTypeNodes, parameterName,
								parameterValue);
					} else { // non-ISO data type parameter
						criteriaList.add(parameterName + "=" + parameterValue);
					}
				}
			}
		}

		Set<String> isoDataTypeNodeNames = isoDataTypeNodes.keySet();
		Iterator iter = isoDataTypeNodeNames.iterator();
		String nodeName = null;
		while (iter.hasNext()) {
			nodeName = (String) iter.next();
			sb.append("[@").append(nodeName).append("=");
			generateIsoQuery(isoDataTypeNodes.get(nodeName), sb);
			sb.append("]");
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

	protected void saveIsoNode(
			Map<String, Map<String, List<Object>>> isoDataTypeNodes,
			String parameterName, String parameterValue) {

		String isoParamPrefix = parameterName.substring(0,
				parameterName.lastIndexOf('.'));
		String[] isoParentNodes = isoParamPrefix.split("\\.");

		Object childNode = null;
		Object parentNode = isoDataTypeNodes.get(isoParentNodes[0]);
		if (parentNode == null) { // initialize
			Map<String, List<Object>> map = new HashMap<String, List<Object>>();
			isoDataTypeNodes.put(isoParentNodes[0], map);
			parentNode = isoDataTypeNodes.get(isoParentNodes[0]);
		}
		for (int i = 1; i < isoParentNodes.length; i++) {

			String isoParentNodeName = isoParentNodes[i];
			childNode = ((Map<String, List<Object>>) parentNode)
					.get(isoParentNodeName);

			if (childNode == null) {
				Map<String, List<Object>> map = new HashMap<String, List<Object>>();
				ArrayList<Object> tempList = new ArrayList<Object>();
				tempList.add(map);
				((Map<String, List<Object>>) parentNode).put(isoParentNodeName,
						tempList);
				parentNode = map;
			} else {
				parentNode = ((ArrayList<Object>) childNode).get(0);
			}
		}

		String isoParamKey = parameterName.substring(parameterName
				.lastIndexOf('.') + 1);
		List<Object> nodeList = new ArrayList<Object>();
		nodeList.add(parameterValue);
		((Map<String, List<Object>>) parentNode).put(isoParamKey, nodeList);
	}

	protected void generateIsoQuery(Map<String, List<Object>> isoDataTypeNode,
			StringBuilder query) {
		String parentNodeName = null;
		Set<String> isoParentNodeNames = isoDataTypeNode.keySet();
		Iterator iter = isoParentNodeNames.iterator();
		while (iter.hasNext()) {
			parentNodeName = (String) iter.next();

			query.append("[@").append(parentNodeName).append("=");

			List<Object> valueList = isoDataTypeNode.get(parentNodeName);
			for (Object nodeElement : valueList) {
				if (nodeElement instanceof String) {
					query.append((String) nodeElement).append("]");
				} else if (nodeElement instanceof java.util.HashMap) {
					generateIsoQuery((Map<String, List<Object>>) nodeElement,
							query);
					query.append("]");
				}
			}
		}
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

				buffer.append("<tr>");
				buffer.append("<td>");
				buffer.append("<table summary=\"Data Summary\" cellpadding=\"3\" cellspacing=\"0\" border=\"0\" class=\"dataTable\" width=\"100%\">");
				// buffer.append("<tr>");
				//
				//String eleName = classEleName.substring(
				//		classEleName.lastIndexOf(".") + 1,
				//		classEleName.length());

				//For each sub element to the root:
				//if it is link, skip it. This is link elements for collections representing paging, self
				//
				
				List<Element> tableRows = root.getChildren();
				List columns = new ArrayList();
				for (Element child : tableRows) {
					StringBuffer headerBuffer = new StringBuffer();
					StringBuffer bodyBuffer = new StringBuffer();

//					StringBuffer recHeaderBuffer = new StringBuffer();
//					StringBuffer recBodyBuffer = new StringBuffer();
					
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
						System.out.println(assocName);
						boolean foundLink = false;
						if(assocName.indexOf("(") == -1)
							continue;
						else
						{
							refNameList.add(assocName.substring(0, assocName.indexOf("(")).trim());
						}
					}
					
					String idColName = classCache.getClassIdName(klass);

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
						Attribute attr = child.getAttribute(idColName);
						bodyBuffer.append(attr.getValue());
						bodyBuffer.append("</td>");
						idColValue = attr.getValue();
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
						Attribute attr = child.getAttribute(field.getName());
						if (attr != null)
							bodyBuffer.append(attr.getValue());
						else
							bodyBuffer.append("&nbsp;");
						bodyBuffer.append("</td>");
					}


					//List<String> assocNames = classCache.getAssociations(fullClassName);
					List<Element> linkChild = getChildren(child, "link");
					System.out.println("linkChild: "+linkChild);
					for (String linkName : refNameList) {
						System.out.println(linkName);
						boolean foundLink = false;
						
						for (Element link : linkChild) {
							if(link.getAttribute("ref").getValue().equals(linkName))
							{
								System.out.println("found....");
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
							System.out.println("not found....");
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

	protected Element getChild(Element root, String name) {
		List<Element> children = root.getChildren();
		for (Element child : children) {
			if (child.getName().equals(name))
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
}
