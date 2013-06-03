/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.SystemConstant;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Result extends BaseActionSupport {

    private static final long serialVersionUID = 1234567890L;

    private static Logger log = Logger.getLogger(Result.class.getName());

    //Query parameters
    private String query;
    private String btnSearch;
    private String searchObj;
    private String selectedDomain;

	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();

		SessionMap session = (SessionMap) ActionContext.getContext().get(ActionContext.SESSION.toString());

		debugSessionAttributes(session);

		// BEGIN - build query

		String selectedSearchDomain=null;
		String query=null;

		String submitValue = getBtnSearch();
		log.debug("submitValue: " + submitValue);

		String className = getSelectedDomain();

		log.debug("className (selectedDomain): "+ getSelectedDomain());

		if(submitValue != null && submitValue.equalsIgnoreCase("Submit"))
		{
		    query = "GetHTML?query=";

		   	selectedSearchDomain = getSearchObj();
		   	log.debug("selectedSearchDomain: "+ selectedSearchDomain);

		   	if (selectedSearchDomain != null && !selectedSearchDomain.equals("Please choose")) {
		   		String rolename=null;
		   		if (!selectedSearchDomain.equalsIgnoreCase(className)){
		   			rolename = getRolename(selectedSearchDomain);
		   			selectedSearchDomain = removeRolename(selectedSearchDomain);
		   		}

				query += selectedSearchDomain + "&";

		   		if (rolename != null && rolename.length() > 0){
		   			query += "rolename="+rolename + "&";
		   		}

				if (className != null && !className.equals("Please choose")) {
					query += className;
					log.debug("query with search object = " + query);
					query += generateQuery(request);
				}
			} else {
				if (className != null && !className.equals("Please choose")) {
					query += className + "&" + className;
					log.debug("query with no search object = " + query);
					query += generateQuery(request);
				}
			}

		   	String username = (String) session.get("username");
		   	String password = (String) session.get("password");

		   	if ((username != null) && (username.trim()).length() > 0)
		   		query = query + "&username=" + username;
		   	if ((password != null) && (password.trim()).length() > 0)
		   		query = query + "&password=" + password;

		   	log.debug("query: " + query);
		   	System.out.println("query: " + query);

		 setQuery(query);
		}
//		  END - build query

		return SUCCESS;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(String btnSearch) {
		this.btnSearch = btnSearch;
	}

	public String getSearchObj() {
		return searchObj;
	}

	public void setSearchObj(String searchObj) {
		this.searchObj = searchObj;
	}

	public String getSelectedDomain() {
		return selectedDomain;
	}

	public void setSelectedDomain(String selectedDomain) {
		this.selectedDomain = selectedDomain;
	}

	private String generateQuery(HttpServletRequest request){

		StringBuilder sb = new StringBuilder();
		Enumeration<String> parameters = request.getParameterNames();

		Map<String, Map<String, List<Object>>> isoDataTypeNodes = new HashMap<String, Map<String, List<Object>>>();

 		while(parameters.hasMoreElements())
 		{
     		String parameterName = (String)parameters.nextElement();
     		log.debug("param = " + parameterName);
     		if(!parameterName.equals("klassName") && !parameterName.equals("searchObj") && !parameterName.equals("BtnSearch") && !parameterName.equals("username") && !parameterName.equals("password") && !parameterName.equals("selectedDomain"))
     		{
     			String parameterValue = (request.getParameter(parameterName)).trim();
				if (parameterValue.length() > 0) {

					System.out.println("parameterValue: " + parameterValue);

					if (parameterName.indexOf('.') > 0) { // ISO data type parameter
						saveIsoNode(isoDataTypeNodes, parameterName, parameterValue);
					} else { // non-ISO data type parameter
						sb.append("[@").append(parameterName).append("=")
								.append(parameterValue).append("]");
					}
				}
     		}
     	}

 		Set<String> isoDataTypeNodeNames = isoDataTypeNodes.keySet();
 		Iterator iter = isoDataTypeNodeNames.iterator();
 		String nodeName = null;
 		while (iter.hasNext()){
 			nodeName = (String)iter.next();
 			sb.append("[@").append(nodeName).append("=");
 			generateIsoQuery(isoDataTypeNodes.get(nodeName), sb);
 			sb.append("]");
 		}

 		//Change 'part#' references to just 'part'
 		return sb.toString().replaceAll("part(\\d+)", "part");

	}

	private void saveIsoNode(Map<String, Map<String, List<Object>>> isoDataTypeNodes, String parameterName, String parameterValue){

		String isoParamPrefix =  parameterName.substring(0, parameterName.lastIndexOf('.'));
		System.out.println("isoParamPrefix: " + isoParamPrefix);
		String[] isoParentNodes = isoParamPrefix.split("\\.");
		System.out.println("isoParentNodes: " + isoParentNodes);

		Object childNode = null;
		Object parentNode = isoDataTypeNodes.get(isoParentNodes[0]);
		if (parentNode==null){ // initialize
			Map<String,List<Object>> map = new HashMap<String,List<Object>>();
			isoDataTypeNodes.put(isoParentNodes[0],map);
			parentNode = isoDataTypeNodes.get(isoParentNodes[0]);
		}
		for(int i=1; i < isoParentNodes.length; i++){

			String isoParentNodeName = isoParentNodes[i];
			childNode = ((Map<String, List<Object>>)parentNode).get(isoParentNodeName);

			if (childNode==null){
				Map<String,List<Object>> map = new HashMap<String,List<Object>>();
				ArrayList<Object> tempList = new ArrayList<Object>();
				tempList.add(map);
				((Map<String, List<Object>>)parentNode).put(isoParentNodeName,tempList);
				parentNode = map;
			} else {
				parentNode = ((ArrayList<Object>)childNode).get(0);
			}
		}

		String isoParamKey = parameterName.substring(parameterName.lastIndexOf('.')+1);
		System.out.println("isoParamKey: " + isoParamKey);
		System.out.println("parameterValue: " + parameterValue);

		List<Object> nodeList = new ArrayList<Object>();
		nodeList.add(parameterValue);
		((Map<String, List<Object>>)parentNode).put(isoParamKey,nodeList);
	}

	private void generateIsoQuery(Map<String, List<Object>> isoDataTypeNode, StringBuilder query){
		String parentNodeName = null;
 		Set<String> isoParentNodeNames = isoDataTypeNode.keySet();
 		Iterator iter = isoParentNodeNames.iterator();
 		while (iter.hasNext()){
 			parentNodeName = (String)iter.next();
 			System.out.println("key: " + parentNodeName);

 			query.append("[@").append(parentNodeName).append("=");

 			List<Object> valueList = isoDataTypeNode.get(parentNodeName);
 			for(Object nodeElement : valueList){
 				if (nodeElement instanceof String){
 					query.append((String)nodeElement).append("]");
 				} else if (nodeElement instanceof java.util.HashMap){
 					generateIsoQuery((Map<String, List<Object>>)nodeElement, query);
 		 			query.append("]");
 				}
 			}
 		}
	}

	private String getRolename(String selectedSearchDomain){
		return selectedSearchDomain.substring(0,selectedSearchDomain.indexOf(" "));
	}

	private String removeRolename(String selectedSearchDomain){
		int beginIndex = selectedSearchDomain.indexOf("(")+1;
		int endIndex = selectedSearchDomain.indexOf(")");

		return selectedSearchDomain.substring(beginIndex, endIndex);
	}


}


