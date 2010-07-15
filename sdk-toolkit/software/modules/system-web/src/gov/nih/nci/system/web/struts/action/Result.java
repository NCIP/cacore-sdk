package gov.nih.nci.system.web.struts.action;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		
		SessionMap session = (SessionMap) ActionContext.getContext().get(ActionContext.SESSION);
		
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
				query += selectedSearchDomain + "&";

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
		Enumeration parameters = request.getParameterNames();
		
 		while(parameters.hasMoreElements())
 		{
     		String parameterName = (String)parameters.nextElement();
     		log.debug("param = " + parameterName);
     		if(!parameterName.equals("klassName") && !parameterName.equals("searchObj") && !parameterName.equals("BtnSearch") && !parameterName.equals("username") && !parameterName.equals("password") && !parameterName.equals("selectedDomain"))
     		{
     			String parameterValue = (request.getParameter(parameterName)).trim();
				if (parameterValue.length() > 0) {
					System.out.println("parameterValue: "
							+ parameterValue);

					if (parameterName.indexOf('.') > 0) { // ISO Data Type
						String[] isoDataTypeParameters = parameterName
								.split("\\.");
						
						System.out.println("isoDataTypeParameters: " + isoDataTypeParameters);
						
						for(String isoDataTypeParameter : isoDataTypeParameters){
							sb.append("[@").append(isoDataTypeParameter).append("=");
						}
						
						sb.append(parameterValue);
						
						for(int i=0;i<isoDataTypeParameters.length;i++){
							sb.append("]");
						}

//						sb.append("[@").append(isoDataTypeParameters[0])
//						.append("=[@").append(isoDataTypeParameters[1])
//						.append("=").append(parameterValue)
//								.append("]]");

					} else {
						sb.append("[@").append(parameterName).append("=")
								.append(parameterValue).append("]");
					}
				}
     		}    
     	}
 		
 		return sb.toString();
	}
	
}


