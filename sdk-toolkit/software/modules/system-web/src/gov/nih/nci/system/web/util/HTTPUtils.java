package gov.nih.nci.system.web.util;

import gov.nih.nci.iso21090.Any;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.client.proxy.ListProxy;
import gov.nih.nci.system.client.util.xml.JAXBISOAdapter;
import gov.nih.nci.system.client.util.xml.JAXBISODsetAdAdapter;
import gov.nih.nci.system.client.util.xml.JAXBISODsetCdAdapter;
import gov.nih.nci.system.client.util.xml.JAXBISODsetIiAdapter;
import gov.nih.nci.system.client.util.xml.JAXBISODsetTelAdapter;
import gov.nih.nci.system.client.util.xml.JAXBISOIvlIntAdapter;
import gov.nih.nci.system.client.util.xml.JAXBISOIvlPqAdapter;
import gov.nih.nci.system.client.util.xml.JAXBISOIvlRealAdapter;
import gov.nih.nci.system.client.util.xml.JAXBISOIvlTsAdapter;
import gov.nih.nci.system.dao.orm.HibernateConfigurationHolder;
import gov.nih.nci.system.dao.orm.translator.NestedCriteria2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.query.nestedcriteria.NestedCriteria;
import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.iso._21090.ANY;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.DOMBuilder;
import org.jdom.transform.JDOMSource;

/**
 * HTTPUtils presents various methods to generate search criteria from xquery like syntax.
 * This class also provides functionality to generate XML result.
 *
 * @author Shaziya Muhsin, Dan Dumitru
 * @version 1.1
 */

public class HTTPUtils implements Serializable{

	private static final long serialVersionUID = 1234567890L;

	private static Logger log= Logger.getLogger(HTTPUtils.class.getName());

	private ApplicationService applicationService;
	private ClassCache classCache;
	private HibernateConfigurationHolder configurationHolder;

	private String query;
	private String startIndex = "0";
	private String resultCounter = "1000";
	private String pageNumber;
	private String pageSize;
	private String criteria;
	private String targetClassName;
	private String servletName;
	private String targetPackageName;
	private String roleName;
	private List results = new ArrayList();
	private Namespace namespace = Namespace.getNamespace("xlink", SystemConstant.XLINK_URL);

	private Map<String , XmlAdapter<ANY,Any>> jaxbISOAdapterMap = new HashMap<String, XmlAdapter<ANY,Any>>() {
		private static final long serialVersionUID = 1L;

		{
			put("defaultAdapter",new JAXBISOAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.DSet<gov.nih.nci.iso21090.Ad>",new JAXBISODsetAdAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.DSet<gov.nih.nci.iso21090.Cd>",new JAXBISODsetCdAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.DSet<gov.nih.nci.iso21090.Ii>",new JAXBISODsetIiAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.DSet<gov.nih.nci.iso21090.Tel>",new JAXBISODsetTelAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.DSet<gov.nih.nci.iso21090.Pq>",new JAXBISOIvlPqAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.Ivl<gov.nih.nci.iso21090.Real>",new JAXBISOIvlRealAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.Ivl<gov.nih.nci.iso21090.Ts>",new JAXBISOIvlTsAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.Ivl<gov.nih.nci.iso21090.Int>",new JAXBISOIvlIntAdapter<ANY, Any>());
			put("gov.nih.nci.iso21090.Ivl<gov.nih.nci.iso21090.Pq>",new JAXBISOIvlPqAdapter<ANY, Any>());
		}
	};

	private static Marshaller marshaller=null;

	static{
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.iso._21090");
			marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		} catch (JAXBException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}

	public HTTPUtils(ApplicationService applicationService,ClassCache classCache,int rowCounter, HibernateConfigurationHolder configurationHolder) {
		log.debug("rowCounter: " + rowCounter);
		this.applicationService=applicationService;
		this.classCache=classCache;
		this.resultCounter = rowCounter+"";
		this.configurationHolder = configurationHolder;
	}

	/**
	 * Sets the http Servlet name
	 * @param name
	 */
	public void setServletName(String name){
		servletName = name;
	}
	/**
	 * Returns http servlet name
	 * @return
	 */
	public String getServletName(){
		return servletName;
	}
	/**
	 * Returns startIndex value
	 * @return
	 */
	public String getStartIndex(){
		return startIndex;
	}
	/**
	 * Sets the startIndex value
	 * @param index
	 */
	public void setStartIndex(String index){
		startIndex = index;
	}
	/**
	 * Returns a list of result objects
	 * @return
	 */
	public List getResults(){
		return results;
	}
	/**
	 * Sets the results value
	 * @param resultList
	 */
	public void setResults(List resultList){
		results = resultList;
	}

	/**
	 * Sets argument values based on a given queryText
	 * @param queryText - http query
	 * @throws Exception
	 */
	public void setQueryArguments(String queryText) throws Exception {
		this.query = queryText;
		try{
			if (query != null && !"".equals(query.trim())) {
				if(query.indexOf(SystemConstant.AMPERSAND)<0 && query.indexOf(SystemConstant.EQUAL)>0){
					if(query.indexOf(SystemConstant.LEFT_BRACKET)>0){
						String crit = query.substring(6);
						query = query.substring(0,query.indexOf(SystemConstant.LEFT_BRACKET))+ SystemConstant.AMPERSAND + crit;
					}
					else{
						query += SystemConstant.AMPERSAND + query.substring(query.indexOf(SystemConstant.EQUAL)+1);
					}

				}
				StringTokenizer st = new StringTokenizer(query, SystemConstant.AMPERSAND_STR);
				while (st.hasMoreTokens()) {
					String param = st.nextToken();

					if(param.startsWith("query")){
						targetClassName = param.substring("query=".length());
					}
					else if(param.toLowerCase().startsWith("startindex")){
						startIndex = param.substring("startIndex=".length());
					}
					else if(param.toLowerCase().startsWith("resultcounter")){
						resultCounter = param.substring("resultCounter=".length());
					}
					else if(param.toLowerCase().startsWith("rolename=")){
						roleName = param.substring("roleName=".length());
					}
					else if(param.toLowerCase().startsWith("pagenumber=")){
						pageNumber = param.substring("pageNumber=".length());
					}
					else if(param.toLowerCase().startsWith("pagesize=")){
						pageSize = param.substring("pageSize=".length());
					}
					else if(param.toLowerCase().startsWith("page=")){
						pageSize = param.substring("page=".length());
					}

					else{
						if(criteria == null){
							criteria = param;
						}

					}
					String target = targetClassName;

					if(target.indexOf(SystemConstant.COMMA)>0){
						target = target.substring(0, target.indexOf(SystemConstant.COMMA));
					}
					classCache.isClassNameValid(target);
					if(target.indexOf(SystemConstant.DOT)>0){
						if(classCache.isPackageNameValid(target.substring(0,target.lastIndexOf(SystemConstant.DOT)))){
							targetPackageName = target.substring(0,target.lastIndexOf(SystemConstant.DOT));
						}
					}

					if(targetPackageName == null){
						try{
							targetPackageName = getPackageName(target);
						}catch(Exception ex){
							log.error("Exception: ", ex);
							throw ex;
						}
					}
				}
			}
		}catch(Exception ex){
			log.error("Exception: ", ex);
			throw ex;
		}
	}

	/**
	 * Returns a query
	 * @return
	 */
	public String getQuery(){
		return query;
	}
	/**
	 * Returns target class name
	 * @return
	 */
	public String getTargetClassName(){
		return targetClassName;
	}

	/**
	 * Returns target class name
	 * @return
	 */
	public void setTargetClassName(String targetClassName){
		this.targetClassName = targetClassName;
	}

	/**
	 * Returns the criteria value
	 * @return
	 */
	public String getCriteria(){
		return criteria;
	}
	/**
	 * Returns the pageNumber
	 * @return
	 */
	public String getPageNumber(){
		return pageNumber;
	}
	/**
	 * Sets the page size value
	 * @param size
	 */
	public void setPageSize(int size){
		pageSize = String.valueOf(size);
	}
	/**
	 * Returns the page size
	 * @return
	 */
	public String getPageSize(){
		return pageSize;
	}
	/**
	 * Returns the resultCounter value
	 * @return
	 */
	public String getResultCounter(){
		return resultCounter;
	}

	/**
	 * Returns a query type value based on a given string.
	 * @param url
	 * @return
	 */
	public String getQueryType(String url){
		String queryType = "HTTPQuery";
		if(url.indexOf("Get")>0){
			queryType = url.substring(url.lastIndexOf("Get"));
		}
		return queryType;
	}
	/**
	 * Returns the target package name
	 * @return
	 */
	public String getTargetPackageName(){
		return targetPackageName;
	}

	/**
	 * Generates a search criteria from a criteria list
	 * @param packageName - specifies the package name
	 * @param criteriaList - specifies a list of criteria instances.
	 * @return
	 * @throws Exception
	 */
	private Object buildSearchCriteria(String packageName, List criteriaList ) throws Exception{
		SearchUtils searchUtils = new SearchUtils(classCache);
		return searchUtils.buildSearchCriteria(packageName, criteriaList);
	}

	/**
	 * Returns fully qualified search class names
	 * @param searchClasses - specifies the search class names
	 * @param packageName - specifies the package name
	 * @return
	 */
	private String getSearchClassNames(String searchClasses, String packageName){
		String path = "";
		String delimiter = null;
		if(searchClasses.indexOf(SystemConstant.FORWARD_SLASH)>0){
			delimiter = SystemConstant.FORWARD_SLASH_STR;
		}
		else {
			delimiter = SystemConstant.COMMA_STR;
		}
		StringTokenizer st = new StringTokenizer(searchClasses, delimiter);
		String className = st.nextToken();
		if(className.indexOf(SystemConstant.DOT)>0){
			path = className;
		}
		else{
			path = packageName + SystemConstant.DOT + className;
		}


		if(st.countTokens()>0){
			while(st.hasMoreElements()){
				className = st.nextToken().trim();
				if(className.indexOf(SystemConstant.DOT)>0){
					path += SystemConstant.COMMA +  className;
				}
				path += SystemConstant.COMMA + packageName + SystemConstant.DOT + className;
			}
		}

		return path;
	}

	/**
	 * Returns a search criteria list from a criteria
	 * @param criteria - specifies the criteria string
	 * @return
	 */
	public List<String> getSearchCriteriaList(String criteria) {
		List<String> criteriaList = new ArrayList<String>();
		String delimiter = null;

		if (criteria.indexOf(SystemConstant.FORWARD_SLASH) > 0) {
			delimiter = SystemConstant.FORWARD_SLASH_STR;
		} else {
			delimiter = SystemConstant.BACK_SLASH;
		}

		StringBuffer critString = new StringBuffer();
		for (StringTokenizer st = new StringTokenizer(criteria, delimiter); st.hasMoreElements();) {
			String crit = st.nextToken().trim();
			critString.append(crit);

			boolean valid = validateSyntax(critString.toString());
			if (valid) {
				criteriaList.add(critString.toString());
				critString = new StringBuffer();
			} else {
				int len = critString.length();
				if (criteria.length() > critString.length()) {
					for (int i = len; i < criteria.length(); i++) {
						if (criteria.charAt(i) == delimiter.charAt(0))
							critString.append(delimiter);
						else
							break;
					}
				}
			}
		}
		return criteriaList;
	}

	private boolean validateSyntax(String query) {
		boolean valid = false;
		int startCounter = 0;
		int endCounter = 0;
		for (int i = 0; i < query.length(); i++) {
			if (query.charAt(i) == '[') {
				startCounter++;
			} else if (query.charAt(i) == ']') {
				endCounter++;
			}

		}
		if (startCounter == endCounter) {
			valid = true;
		}
		return valid;
	}

	public org.jdom.Document getXMLDocument(Object[] resultSet, int pageNumber) throws Exception{
		return getXMLDocument(resultSet, pageNumber, true);
	}
	/**
	 * Generates an org.jdom.Document based on a resultSet
	 *
	 * @param resultSet -
	 *            specifies a list of populated domain objects
	 * @param pageNumber -
	 *            specifies the page number
	 * @return
	 * @throws Exception
	 */
	public org.jdom.Document getXMLDocument(Object[] resultSet, int pageNumber, boolean queryType) throws Exception{

		org.jdom.Element httpQuery = new org.jdom.Element("httpQuery",namespace);
		org.jdom.Element queryRequest = new org.jdom.Element("queryRequest");
		String targetResult = targetClassName;
		if(queryType)
		{
			Element queryString = new Element("queryString").setText(query);

			if(targetResult.indexOf(SystemConstant.COMMA)>1){
				targetResult = targetResult.substring(0, targetResult.indexOf(SystemConstant.COMMA));
			}
			if(targetResult.indexOf(SystemConstant.DOT)<0){
				targetResult = this.getPackageName(targetResult)+ SystemConstant.DOT +targetResult;
			}
			Element queryClass = new Element("class").setText(targetResult);
			System.out.println("targetResult: "+targetResult);
			Element queryElement = new Element("query").addContent(queryString).addContent(queryClass);
			queryRequest.addContent(queryElement);
			queryRequest.addContent(new org.jdom.Element("criteria").setText(criteria));

			httpQuery.addContent(queryRequest);
		}
		else
		{
			if(targetResult.indexOf(SystemConstant.COMMA)>1){
				targetResult = targetResult.substring(0, targetResult.indexOf(SystemConstant.COMMA));
			}
			if(targetResult.indexOf(SystemConstant.DOT)<0){
				targetResult = this.getPackageName(targetResult)+ SystemConstant.DOT +targetResult;
			}
			Element queryClass = new Element("class").setText(targetResult);
			Element queryElement = new Element("query").addContent(queryClass);
			queryRequest.addContent(queryElement);

		}
		int start = 0;
		int end = resultSet.length;
		int rowCount = 0;
		int index = 0;
		int resultCount = 0;
		int nextStartIndex = 0;
		int totalNumRecords = results.size();

		if(!(startIndex.equals("0") || startIndex == null)){
			index = Integer.valueOf(startIndex).intValue();
		}

		if(!(resultCounter.equals("0") ||resultCounter == null )){
			resultCount = Integer.valueOf(resultCounter).intValue();
		}

		Element xmlElement = new Element("queryResponse");

		String counter = String.valueOf(totalNumRecords);
		xmlElement.addContent(new Element("recordCounter").setText(counter));

		if(resultSet.length >0){
			if(pageSize != null){
				rowCount = Integer.parseInt(pageSize);
			}
			int pageCounter = 1;
			int size = resultSet.length;
			if((index + resultCount)> totalNumRecords){
				size = totalNumRecords - index;
			}
			if(rowCount > 0 && rowCount < size){
				pageCounter = size/rowCount;
				if((size % rowCount)>0){
					pageCounter++;
				}
			}
			if(pageNumber > pageCounter){
				pageNumber = 1;
			}

			if(pageNumber > 0 && pageNumber <= pageCounter){
				end = rowCount * pageNumber;
				start = end - rowCount;
				if(size < end){
					end = size;
				}
			}

			if(end == 0)
				end = resultSet.length;

			String recordNum = "";
			Set<String> resultClass = new HashSet<String>();
			List<String> classes = new ArrayList<String>();
			for(int x=start; x<end; x++){
				resultClass.add(resultSet[x].getClass().getName());
				System.out.println("resultSet[x].getClass().getName() 11 : "+resultSet[x].getClass().getName());
			}
			if(resultClass.size() >1){
				Object lists[] = new Object[resultClass.size()];
				int number =0;
				for(Iterator it= resultClass.iterator(); it.hasNext();){
					String typeName = (String)it.next();
					classes.add(typeName);
					List<Object> list = new ArrayList<Object>();
					for(int i=start; i<end ; i++){
						if(resultSet[i].getClass().getName().equals(typeName)){
							list.add(resultSet[i]);
						}
					}
					lists[number]= new Object();
					lists[number] = list ;
					number++;
				}
				for(int o=0; o<lists.length; o++){
					List subResults = (List)lists[o];
					for(int i=0; i< subResults.size(); i++){
						Object result = subResults.get(i);
						int recNum = index + i + 1;
						recordNum = String.valueOf(recNum);

						Element element = getElement(result, recordNum, queryType);
						xmlElement.addContent(element);

					}

				}
			} else{
				for(int i = start; i< end; i++){
					int recNum = index + i + 1;
					recordNum = String.valueOf(recNum);
					Object result = resultSet[i];
					xmlElement.addContent(getElement(result, recordNum, queryType));

				}
			}
			if(queryType)
			{
				if((index - resultCount)>=0){
					nextStartIndex = index - resultCount;
					String preLink = servletName +"?query="+targetClassName + SystemConstant.AMPERSAND + criteria +"&startIndex="+nextStartIndex+"&resultCounter="+resultCounter;
					String preText = "<<< "+" PREVIOUS "+ resultCount +" RECORDS";
					Element preElement = new Element("previous").setAttribute("type","simple",namespace).setAttribute("href",preLink,namespace).setText(preText);
					xmlElement.addContent(preElement);
				}
				String pCount = String.valueOf(pageCounter);
				Element pagesElement = new Element("pages").setAttribute("count",pCount);
				if((index + resultCount)< totalNumRecords){
					nextStartIndex = index + resultCount;
					String nextLink = servletName +"?query="+targetClassName + SystemConstant.AMPERSAND +criteria +"&startIndex="+nextStartIndex+"&resultCounter="+resultCounter;
					String nextText = "NEXT "+ resultCount+" RECORDS >>> ";
					Element nextElement = new Element("next").setAttribute("type","simple",namespace).setAttribute("href",nextLink,namespace).setText(nextText);
					xmlElement.addContent(nextElement);
				}

				for(int i=0; i< pageCounter; i++){
					int p = i + 1;
					String pageLink = servletName +"?query="+targetClassName+SystemConstant.AMPERSAND+criteria +"&pageNumber="+p+"&resultCounter="+resultCounter+"&startIndex="+startIndex;
					String page = String.valueOf(p);
					String pageText = SystemConstant.SPACE + page + SystemConstant.SPACE;
					Element pElement = new Element("page").setAttribute("number",page).setAttribute("type","simple",namespace).setAttribute("href",pageLink,namespace).setText(pageText);
					pagesElement.addContent(pElement);
				}
				xmlElement.addContent(pagesElement);
			}
			httpQuery.addContent(xmlElement);
			xmlElement.addContent(new Element("recordCounter").setText(counter));
		}
		else{
			xmlElement.addContent(new Element("recordCounter").setText("0"));
		}
		if((pageNumber -1)> 0){
			index += ((pageNumber -1)* rowCount) + 1;
		}
		else{
			index+= 1;
		}
		int endRecordNum = rowCount + index - 1;
		if(endRecordNum > totalNumRecords){
			endRecordNum = totalNumRecords;
		}
		String startCounter = String.valueOf(index);
		String endCounter = String.valueOf(endRecordNum);
		Element startElement = new Element("start").setText(startCounter);
		Element endElement = new Element("end").setText(endCounter);
		xmlElement.addContent(startElement).addContent(endElement);

		org.jdom.Document xmlDoc = new org.jdom.Document(httpQuery);
		return xmlDoc;
	}


	/**
	 * Generates an org.jdom.Document based on a resultSet
	 *
	 * @param resultSet -
	 *            specifies a list of populated domain objects
	 * @param pageNumber -
	 *            specifies the page number
	 * @return
	 * @throws Exception
	 */
	public org.jdom.Document getXMLDocument(Object[] resultSet, String targetClassName) throws Exception{

		org.jdom.Element httpQuery = new org.jdom.Element("httpQuery",namespace);
		org.jdom.Element queryRequest = new org.jdom.Element("queryRequest");

		String targetResult = targetClassName;

		Element queryClass = new Element("class").setText(targetResult);
		//Element queryElement = new Element("query").addContent(queryString).addContent(queryClass);
		//queryRequest.addContent(queryElement);
		queryRequest.addContent(new org.jdom.Element("criteria").setText(criteria));

		httpQuery.addContent(queryRequest);

		int start = 0;
		int end = resultSet.length;
		int rowCount = 0;
		int index = 0;
		int resultCount = 0;
		int nextStartIndex = 0;
		int totalNumRecords = results.size();

		if(!(startIndex.equals("0") || startIndex == null)){
			index = Integer.valueOf(startIndex).intValue();
		}

		if(!(resultCounter.equals("0") ||resultCounter == null )){
			resultCount = Integer.valueOf(resultCounter).intValue();
		}

		Element xmlElement = new Element("queryResponse");

		String counter = String.valueOf(totalNumRecords);
		xmlElement.addContent(new Element("recordCounter").setText(counter));

		if(resultSet.length >0){
			if(pageSize != null){
				rowCount = Integer.parseInt(pageSize);
			}
			int pageCounter = 1;
			int size = resultSet.length;
			if((index + resultCount)> totalNumRecords){
				size = totalNumRecords - index;
			}

			if(rowCount > 0 && rowCount < size){
				pageCounter = size/rowCount;
				if((size % rowCount)>0){
					pageCounter++;
				}
			}
			String recordNum = "";
			Set<String> resultClass = new HashSet<String>();
			List<String> classes = new ArrayList<String>();
			for(int x=start; x<end; x++){
				resultClass.add(resultSet[x].getClass().getName());
				System.out.println("resultSet[x].getClass().getName() "+resultSet[x].getClass().getName());
			}
			if(resultClass.size() >1){
				Object lists[] = new Object[resultClass.size()];
				int number =0;
				for(Iterator it= resultClass.iterator(); it.hasNext();){
					String typeName = (String)it.next();
					System.out.println("typeName: "+typeName);
					classes.add(typeName);
					List<Object> list = new ArrayList<Object>();
					for(int i=start; i<end ; i++){
						if(resultSet[i].getClass().getName().equals(typeName)){
							list.add(resultSet[i]);
						}
					}
					lists[number]= new Object();
					lists[number] = list ;
					number++;
				}

				for(int o=0; o<lists.length; o++){
					List subResults = (List)lists[o];
					for(int i=0; i< subResults.size(); i++){
						Object result = subResults.get(i);
						int recNum = index + i + 1;
						recordNum = String.valueOf(recNum);

						Element element = getElement(result, recordNum, true);
						xmlElement.addContent(element);

					}

				}
			} else{
				for(int i = start; i< end; i++){
					int recNum = index + i + 1;
					recordNum = String.valueOf(recNum);
					Object result = resultSet[i];
					xmlElement.addContent(getElement(result, recordNum, true));

				}
			}
			if((index - resultCount)>=0){
				nextStartIndex = index - resultCount;
				String preLink = servletName +"?query="+targetClassName + SystemConstant.AMPERSAND + criteria +"&startIndex="+nextStartIndex+"&resultCounter="+resultCounter;
				String preText = "<<< "+" PREVIOUS "+ resultCount +" RECORDS";
				Element preElement = new Element("previous").setAttribute("type","simple",namespace).setAttribute("href",preLink,namespace).setText(preText);
				xmlElement.addContent(preElement);
			}
			String pCount = String.valueOf(pageCounter);
			Element pagesElement = new Element("pages").setAttribute("count",pCount);
			if((index + resultCount)< totalNumRecords){
				nextStartIndex = index + resultCount;
				String nextLink = servletName +"?query="+targetClassName + SystemConstant.AMPERSAND +criteria +"&startIndex="+nextStartIndex+"&resultCounter="+resultCounter;
				String nextText = "NEXT "+ resultCount+" RECORDS >>> ";
				Element nextElement = new Element("next").setAttribute("type","simple",namespace).setAttribute("href",nextLink,namespace).setText(nextText);
				xmlElement.addContent(nextElement);
			}

			for(int i=0; i< pageCounter; i++){
				int p = i + 1;
				String pageLink = servletName +"?query="+targetClassName+SystemConstant.AMPERSAND+criteria +"&pageNumber="+p+"&resultCounter="+resultCounter+"&startIndex="+startIndex;
				String page = String.valueOf(p);
				String pageText = SystemConstant.SPACE + page + SystemConstant.SPACE;
				Element pElement = new Element("page").setAttribute("number",page).setAttribute("type","simple",namespace).setAttribute("href",pageLink,namespace).setText(pageText);
				pagesElement.addContent(pElement);
			}
			xmlElement.addContent(pagesElement);
			httpQuery.addContent(xmlElement);
			xmlElement.addContent(new Element("recordCounter").setText(counter));
		}
		else{
			xmlElement.addContent(new Element("recordCounter").setText("0"));
		}
		int endRecordNum = rowCount + index - 1;
		if(endRecordNum > totalNumRecords){
			endRecordNum = totalNumRecords;
		}
		String startCounter = String.valueOf(index);
		String endCounter = String.valueOf(endRecordNum);
		Element startElement = new Element("start").setText(startCounter);
		Element endElement = new Element("end").setText(endCounter);
		xmlElement.addContent(startElement).addContent(endElement);

		org.jdom.Document xmlDoc = new org.jdom.Document(httpQuery);
		return xmlDoc;
	}


	/**
	 * Returns the value of an id field from a given result
	 * @param result - populated instance of a class
	 * @return
	 * @throws Exception
	 */
	private String getCriteriaIdValue(Object result) throws Exception{
		Field[] fields = classCache.getAllFields(result.getClass());

		String idName = classCache.getClassIdName(result.getClass());
		Field idField = getIdField(fields,idName);

		if (idField == null)
			throw new Exception("ERROR: No identifier field found for class " + result.getClass().getName());

		String id = idField.getName();

		String criteriaIdValue = SystemConstant.AT + id + SystemConstant.EQUAL;

		if(idField.getName().indexOf(SystemConstant.DOT)>0){
			id = id.substring(id.lastIndexOf(SystemConstant.DOT)+1);
		}
		String fieldValue = getFieldValueStr(idField, result);
		if(fieldValue != null){
			criteriaIdValue += fieldValue;
		}
		return criteriaIdValue;
	}

	/**
	 * Generates an Element for a given result object
	 *
	 * @param result
	 *            - an instance of a class
	 * @param recordNum
	 *            - specifies the record number in the result set
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private Element getElement(Object result, String recordNum, boolean queryType)
			throws Exception {

		Element classElement = new Element("class").setAttribute("name",
				result.getClass().getName()).setAttribute("recordNumber",
				recordNum);
		String criteriaIdValue = getCriteriaIdValue(result);

		String link = null;
		Field[] fields = classCache.getAllFields(result.getClass());
		for (int f = 0; f < fields.length; f++) {
			String criteriaBean = result.getClass().getName();
			Field field = fields[f];
			String fieldName = field.getName();
			if (fieldName.equalsIgnoreCase("serialVersionUID") ||  && fieldName.equalsIgnoreCase("links")) {
				continue;
			}
			Element fieldElement = new Element("field").setAttribute("name",
					fieldName);
			String fieldType = field.getType().getName();
			String targetBean = null;
			if (!(fieldType.startsWith("java")
					&& !(fieldType.endsWith("Collection")) || field.getType()
					.isPrimitive())) {
				if (field.getType().getName().endsWith("Collection")) {
					SearchUtils searchUtils = new SearchUtils(classCache);
					if (searchUtils.getTargetClassName(result.getClass()
							.getName(), fieldName) != null) {
						targetBean = searchUtils.getTargetClassName(result
								.getClass().getName(), fieldName);
						// handle primitive collections
						if (Class.forName(targetBean).isPrimitive()
								|| targetBean.startsWith("java")) {
							targetBean = null;
						}
					}
				} else if (locateClass(fieldType)) {
					targetBean = fieldType;
				}
			}
			if (targetBean != null) {
				if (result.getClass().getPackage()
						.equals(Class.forName(targetBean).getPackage())) {
					targetBean = targetBean.substring(targetBean
							.lastIndexOf(SystemConstant.DOT) + 1);
					if (criteriaBean.indexOf(SystemConstant.DOT) > 0) {
						criteriaBean = criteriaBean.substring(criteriaBean
								.lastIndexOf(SystemConstant.DOT) + 1);
					}
				}
				if(queryType)
				{
					String methodName = "get"
							+ fieldName.substring(0, 1).toUpperCase()
							+ fieldName.substring(1);

					link = servletName + "?query=" + targetBean
							+ SystemConstant.AMPERSAND + criteriaBean
							+ SystemConstant.LEFT_BRACKET + criteriaIdValue
							+ SystemConstant.RIGHT_BRACKET
							+ SystemConstant.AMPERSAND + "roleName=" + fieldName;

					fieldElement.setAttribute("type", "simple", namespace)
					.setAttribute("href", link, namespace)
					.setText(methodName);
				}
				else
				{
					String roleName = fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
					link = servletName
					+ SystemConstant.FORWARD_SLASH
					+ roleName;

					fieldElement.setAttribute("type", "simple", namespace)
					.setAttribute("href", link, namespace);
				}
			}else {
				String fieldValue = " ";
				Object value = null;
				try {
					if (fieldType.indexOf("Collection") > 0
							|| fieldType.endsWith("HashSet")
							|| fieldType.endsWith("ArrayList")
							|| fieldType.indexOf("Vector") > 0) {

						if (((Collection) fields[f].get(result)).size() > 0) {

							Iterator it = ((Collection) fields[f].get(result))
									.iterator();
							fieldValue = String.valueOf(it.next());
							while (it.hasNext()) {
								fieldValue += "; " + String.valueOf(it.next());
							}
						}
						if (fieldValue != null) {
							fieldElement.setText(fieldValue);
						} else {
							fieldElement.setText(" ");
						}
					} else {
						Object tempFieldValue = this.getFieldValue(field,result);
						if (tempFieldValue != null) {
							if(tempFieldValue instanceof Document){
								Document childDocument=(Document)tempFieldValue;
								fieldElement.addContent(childDocument.detachRootElement());
							}else{
								value = tempFieldValue;
								fieldValue = String.valueOf(value);
								fieldElement.addContent(fieldValue);
							}
						}
					}
				} catch (Exception ex) {
					fieldValue = " ";
					value = getFieldValue(field, result);
					fieldValue = String.valueOf(value);
					String temp = null;
					for (int s = 0; s < fieldValue.length(); s++) {
						String charValue = String.valueOf(fieldValue.charAt(s));
						try {
							temp += charValue;
						} catch (Exception e) {
							temp += " ";
						}
					}
					fieldElement.setText(fieldValue);
				}
			}
			classElement.addContent(fieldElement);
		}
		return classElement;
	}

	/**
	 * Returns the package name of a given class
	 * @param className - specifies the class name
	 * @return
	 * @throws Exception
	 */
	public String getPackageName(String className) throws Exception{
		String packageName = classCache.getPkgNameForClass(className);

		if (packageName == null){
			log.error("No package name found for class: " + className);
			throw new Exception("No package name found for class: " + className);
		}

		log.debug("Package name found for class: " + className + " is: " + packageName);
		return packageName;
	}

	/**
	 * Returns true if the given class name is found
	 * @param className
	 * @return
	 */
	public boolean locateClass(String className){
		//To make sure class is not a proxy generated by CGLIB
		if(className.indexOf("$")>1)
			className = className.substring(0, className.indexOf("$"));

		try {
			classCache.getClassFromCache(className);
		} catch(ClassNotFoundException e){
			return false;
		}

		return true;

	}

	/**
	 * Returns an id field from an array of fields
	 * @param fields
	 * @return
	 * @throws Exception
	 */
	private Field getIdField(Field[] fields, String idName) throws Exception{
		Field id = null;
		for(int i=0; i<fields.length;i++){
			if(fields[i].getName().equalsIgnoreCase(idName)){
				if(!this.locateClass(fields[i].getType().getName())){
					id = fields[i];
					break;
				}
			}
		}
		return id;
	}

	/**
	 * Returns a field that matches the given String from an array of fields
	 * @param fields All fields
	 * @param fieldName  Field name
	 * @return
	 * @throws Exception
	 */
	private Field getFieldByName(Field[] fields, String fieldName) throws Exception{
		Field field = null;
		for(int i=0; i<fields.length;i++){
			if(fields[i].getName().equalsIgnoreCase(fieldName)){
				if(!this.locateClass(fields[i].getType().getName())){
					field = fields[i];
					break;
				}
			}
		}
		return field;
	}

	/**
	 * Returns a field value
	 * @param field - specifies the field
	 * @param domain - specifies the object
	 * @return
	 * @throws Exception
	 */
	private String getFieldValueStr(Field field, Object domain) throws Exception{
		String value = null;
		Object fieldValue = field.get(domain);
		if(fieldValue != null){
			String fieldType = field.getType().getName();
			if(fieldType.equals("java.util.Date")){
				SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy");
				value = date.format((Date)fieldValue);
			} else if (fieldType.equals("java.lang.Integer")) {
				value = ((Integer)fieldValue).toString();
			} else if(fieldType.equals("gov.nih.nci.iso21090.Ii")){
				String extensionValue = ((Ii)fieldValue).getExtension();
				value = "[@extension=" + extensionValue + "]";
			} else {
				try {
					value = fieldValue.toString();
				} catch (Exception e) {
					throw new Exception("Unexpected field type found while determining criteria id value: " + fieldType );
				}
			}
		}
		return value;
	}
	/**
	 * Returns a field value
	 * @param field - specifies the field
	 * @param domain - specifies the object
	 * @return
	 * @throws Exception
	 */
	private Object getFieldValue(Field field, Object domain) throws Exception{
		Object value = null;
		if(field.get(domain)!= null){
			if(field.getType().getName().equals("java.util.Date")){
				SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy");
				value = date.format((Date)field.get(domain));
			}
			else{
				value = marshalISOObjectTOXml(field, domain);
			}
		}
		return value;
	}

	private Object marshalISOObjectTOXml(Field field, Object domain)
			throws Exception {
		Object value= field.get(domain);
		Marshaller isoMarshaller = getJaxbMarshaller(field.getType().getName());
		if (isoMarshaller != null) {
			XmlAdapter<ANY, Any> jaxbAdapter = jaxbISOAdapterMap.get(field
					.getGenericType().toString());
			if (jaxbAdapter == null) {
				jaxbAdapter = jaxbISOAdapterMap.get("defaultAdapter");
			}
			org.iso._21090.ANY anyJaxb = jaxbAdapter.marshal((Any) value);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document domDoc = db.newDocument();
			isoMarshaller.marshal(anyJaxb, domDoc);

			DOMBuilder builder = new DOMBuilder();
	        org.jdom.Document jdomDoc = builder.build(domDoc);
			value = jdomDoc;
		}
		return value;
	}

	private Marshaller getJaxbMarshaller(String fieldName) throws JAXBException,
			PropertyException {
		if(!fieldName.startsWith("gov.nih.nci.iso21090.")) return null;
		return marshaller;
	}

	/**
	 * Returns an array of result objects
	 *
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Object[] getResultSet(HQLCriteria hqlCriteria) throws Exception {
		results = new ArrayList();
		int index = 0;
		int counter = 1000;
		try {

			if (startIndex != null || !startIndex.equals("0")) {
				index = Integer.parseInt(startIndex);
			}
			if (resultCounter != null) {
				counter = Integer.parseInt(resultCounter);
			}
			if (roleName != null) {
				results = getAssociation(criteria, roleName);
			} else {
				results = applicationService.query(hqlCriteria);
			}

			if (results != null && (results instanceof ListProxy)) {
				((ListProxy) results).setAppService(applicationService);
			}
		} catch (Exception ex) {
			log.error("Exception: ", ex);
			throw ex;
		}

		if ((counter + index) > results.size()) {
			counter = results.size();
		} else {
			counter += index;
		}
		Object[] resultSet = new Object[counter];
		for (int i = index, s = 0; i < counter; i++, s++) {
			resultSet[s] = results.get(i);
		}
		return resultSet;
	}

	/**
	 * Returns an array of result objects
	 *
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Object[] getResultSet(String targetClassName, String targetPackageName, String criteriaStr, String roleName) throws Exception {
		results = new ArrayList();
		int index = 0;
		int counter = 1000;
		try {
			String searchPath = getSearchClassNames(targetClassName,
					targetPackageName);
			List criteriaList = getSearchCriteriaList(criteriaStr);
			Object criteria = buildSearchCriteria(targetPackageName,
					criteriaList);

			if (startIndex != null || !startIndex.equals("0")) {
				index = Integer.parseInt(startIndex);
			}
			if (resultCounter != null) {
				counter = Integer.parseInt(resultCounter);
			}
			results = getAssociation(criteria, roleName, true);

			if (results != null && (results instanceof ListProxy)) {
				((ListProxy) results).setAppService(applicationService);
			}
		} catch (Exception ex) {
			log.error("Exception: ", ex);
			throw ex;
		}

		if ((counter + index) > results.size()) {
			counter = results.size();
		} else {
			counter += index;
		}
		Object[] resultSet = new Object[counter];
		for (int i = index, s = 0; i < counter; i++, s++) {
			resultSet[s] = results.get(i);
		}
		return resultSet;
	}

	/**
	 * Returns an array of result objects
	 *
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Object[] getResultSet() throws Exception {
		results = new ArrayList();
		int index = 0;
		int counter = 1000;
		try {
			String searchPath = getSearchClassNames(targetClassName,
					targetPackageName);
			List criteriaList = getSearchCriteriaList(criteria);
			Object criteria = buildSearchCriteria(targetPackageName,
					criteriaList);

			if (startIndex != null || !startIndex.equals("0")) {
				index = Integer.parseInt(startIndex);
			}
			if (resultCounter != null) {
				counter = Integer.parseInt(resultCounter);
			}
			if (roleName != null) {
				results = getAssociation(criteria, roleName);
			} else {
				results = applicationService.search(searchPath, criteria);
			}

			if (results != null && (results instanceof ListProxy)) {
				((ListProxy) results).setAppService(applicationService);
			}
		} catch (Exception ex) {
			log.error("Exception: ", ex);
			throw ex;
		}

		if ((counter + index) > results.size()) {
			counter = results.size();
		} else {
			counter += index;
		}
		Object[] resultSet = new Object[counter];
		for (int i = index, s = 0; i < counter; i++, s++) {
			resultSet[s] = results.get(i);
		}
		return resultSet;
	}

	/**
	 * Returns true if a match is found
	 * @param prop - an instance of an HTTPUtils class
	 * @return
	 */
	public boolean getMatch(HTTPUtils prop){
		boolean match = false;
		String oldQuery = prop.getCriteria();
		String oldTarget = prop.getTargetClassName();
		int size = prop.getResults().size();

		if((this.targetClassName.equals(oldTarget))&& this.criteria.equals(oldQuery) && size > 0){
			match = true;
		}
		return match;
	}

	/**
	 * Generates an XML or HTML document based on a given stylesheet
	 * @param xmlDoc Specifies the xml document
	 * @param styleIn specifies the stylesheet
	 * @return
	 * @throws Exception
	 */

	public void transform(Document xmlDoc, InputStream styleIn, OutputStream out)
			throws Exception {

		if (styleIn == null)
			throw new ServletException("No stylesheet configued");

		JDOMSource source = new JDOMSource(xmlDoc);
		StreamResult result = new StreamResult(out);

		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Templates stylesheet = tFactory.newTemplates(new StreamSource(
					styleIn));
			Transformer processor = stylesheet.newTransformer();
			processor.transform(source, result);

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new Exception("XSLTTransformer Exception: " + ex.getMessage());
		}
	}

	/*
	 * This method is used when an XSLT stylesheet is not found.(Test methods)
	 */
	/**
	 * Prints results on screen
	 * @param resultList
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void printResults(HttpServletResponse response)throws IOException, ServletException{

		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		out.println("<br><font color=purple><b>");
		out.println("<b>"+results.size() +" records found. </b><br><hr>");
		int recordNum = 1;
		out.println();
		out.println("<font size=4 color=black> Criteria : "+ this.getCriteria()+"</font>" );
		if(results.size()>0)
			out.println("<br><font size=4 color=black> Result Class name: "+ results.get(0).getClass().getName()+"</font>" );
		else
			out.println("<br><font size=4 color=black>No records found </font>" );
		out.println("<br><hr><br>");
		out.println("<TABLE BORDER=\"2\"  style=\"table-layout:AUTO\" valign=\"top\">");
		try{

			for(int i =0; i< results.size(); i++){

				printRecord(results.get(i), servletName, out, recordNum);

				recordNum++;
			}
		}catch(Exception ex){
			log.error("Exception: ", ex);
			throw new IOException(ex.getMessage());
		}
	}
	/**
	 * Displays a record on screen
	 * @param result
	 * @param servletName
	 * @param out
	 * @param recordNum
	 * @throws Exception
	 */
	private void printRecord(Object result, String servletName, ServletOutputStream out, int recordNum)throws Exception{

		Class resultClass = result.getClass();
		String className = resultClass.getName();
		Class superClass = resultClass.getSuperclass();

		Field[] fields      = classCache.getAllFields(resultClass);
		Field[] superFields = classCache.getAllFields(superClass);


		if(recordNum == 1){
			out.println("<TR BGCOLOR=\"#E3E4FA\">");
			for(int x=0; x<fields.length; x++){
				String fName = fields[x].getName();
				if(!(fName.equalsIgnoreCase("serialVersionUID") && fName.equalsIgnoreCase("links"))){
					out.println("<TD>"+ fName +"</TD>");
				}

			}
			for(int x=0; x<superFields.length; x++){
				String fName = superFields[x].getName();
				if(!(fName.equalsIgnoreCase("serialVersionUID") && fName.equalsIgnoreCase("links"))){
					out.println("<TD>"+ fName +"</TD>");
				}

			}
			out.println("</TR>");
		}
		out.println("<TR VALIGN=\"TOP\">");

		Field idField = null;
		String criteriaIdValue = null;


		for(int f=0;f<fields.length; f++){
			fields[f].setAccessible(true);
			String fieldName = fields[f].getName().substring(0,1).toUpperCase()+fields[f].getName().substring(1);
			if(fields[f].getName().equalsIgnoreCase("id")){
				idField = fields[f];
				try{
					Object idValue = idField.get(result);
					String id = null;
					if(!idField.getType().getName().endsWith("String")){
						id = String.valueOf(idValue);
					}
					criteriaIdValue = SystemConstant.AT+idField.getName()+SystemConstant.EQUAL+id;
				}catch(Exception ex){
					throw new IOException(ex.getMessage());
				}
			}
			else if(fieldName.equalsIgnoreCase("serialVersionUID") ||  fName.equalsIgnoreCase("links")){
				continue;
			}
			boolean bean = false;
			String fieldType = fields[f].getType().getName();
			if(fieldType.indexOf("$")>1)
				fieldType = fieldType.substring(0, fieldType.indexOf("$"));

			Object value = "-";
			if(fields[f].get(result)!= null){
				value = fields[f].get(result);
				fieldType = value.getClass().getName();
				if(fieldType.indexOf("$")>1)
					fieldType = fieldType.substring(0, fieldType.indexOf("$"));

				if(!fieldType.startsWith("java.")){
					bean = locateClass(fieldType);
				}
			}
			String methName = "get"+ fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			String beanName = null;
			if(bean){
				beanName = fieldType.substring(fieldType.lastIndexOf(SystemConstant.DOT)+1);
			}
			String returnObjectName = fields[f].getName();
			boolean collectionType = false;
			if(returnObjectName.endsWith("Collection")|| fieldType.endsWith("Vector")|| fieldType.endsWith("HashSet")){
				collectionType = true;
			}
			if((fieldType.startsWith("java.")&& !(collectionType)) || fields[f].getType().isPrimitive()){
				String strValue = " ";
				if(value != null){
					strValue = String.valueOf(value);
				}
				out.println("<TD>"+ strValue +"</TD>" );
			}
			else if(returnObjectName.endsWith("Collection")){

				String returnClassName = returnObjectName.substring(0,returnObjectName.lastIndexOf("Collection"));
				returnClassName = returnClassName.substring(0,1).toUpperCase() + returnClassName.substring(1);
				String disp = "<TD><a href="+servletName+"?query="+ returnClassName+ SystemConstant.AMPERSAND +className.substring(className.lastIndexOf(SystemConstant.DOT)+1)+"[@"+idField.getName()+ SystemConstant.EQUAL +idField.get(result)+"]>" + methName+"</a></TD>";

				out.println(disp);
			}
			else if(bean){
				String disp = "<TD><a href="+servletName+"?query="+beanName+SystemConstant.AMPERSAND+className.substring(className.lastIndexOf(SystemConstant.DOT)+1)+"[@"+idField.getName()+ SystemConstant.EQUAL +idField.get(result)+"]>"+methName+"</a></TD>";

				out.println(disp);
			}
		}
		recordNum++;
		out.println("</TR>");
	}

	public <E> List<E> getAssociation(Object source, String associationName) throws ApplicationException {
		return getAssociation(source, associationName, false);
	}
	public <E> List<E> getAssociation(Object source, String associationName, boolean includeParent) throws ApplicationException {
		String assocType = "";
		try{
			assocType = classCache.getAssociationType(source.getClass(),associationName);
		}catch(Exception e)
		{
			throw new ApplicationException(e);
		}
		String hql = "";
		boolean isCollection = classCache.isCollection(source.getClass().getName(), associationName);
		if(isCollection)
			//hql = "select dest from "+assocType+" as dest,"+source.getClass().getName()+" as src where dest in elements(src."+associationName+") and src=?";
			hql = "select dest from "+source.getClass().getName()+" as src inner join src."+associationName+" dest";
		else
			hql = "select dest from "+assocType+" as dest,"+source.getClass().getName()+" as src where src."+associationName+".id=dest.id";


		NestedCriteria nestedCriteria = new NestedCriteria();
		nestedCriteria.setSourceObjectName(source.getClass().getName());
		nestedCriteria.setTargetObjectName(source.getClass().getName());

		List sourceObjectList = new ArrayList();
		sourceObjectList.add(source);
		nestedCriteria.setSourceObjectList(sourceObjectList);

		String srcAlias = "src";
		NestedCriteria2HQL criteria2hql = new NestedCriteria2HQL(nestedCriteria, configurationHolder.getConfiguration(), false,srcAlias);

		HQLCriteria hqlCriteria=null;
		try {
			hqlCriteria = criteria2hql.translate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int beginIndex = hqlCriteria.getHqlString().lastIndexOf("where");
		if (beginIndex > 0){
			if (isCollection)
				hql += " where ";
			else
				hql += " and ";

			hql += hqlCriteria.getHqlString().substring(beginIndex + 6);
		}

		hqlCriteria.setHqlString(hql);

		return applicationService.query(hqlCriteria);
	}
}