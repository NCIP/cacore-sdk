package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;
import gov.nih.nci.system.web.RESTfulResource;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.lang.reflect.*;
import java.net.URI;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class CreateAction extends BaseActionSupport {

    private static final long serialVersionUID = 1234567890L;

    public static Logger log = Logger.getLogger(CreateAction.class.getName());
    
    //Query parameters
    private String query;
    private String btnSearch;
    private String searchObj;
    private String selectedDomain;
    private ClassCache classCache;
	WebApplicationContext ctx;

	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext context = ServletActionContext.getServletContext();
		ctx =  WebApplicationContextUtils.getWebApplicationContext(context);
		this.classCache = (ClassCache) ctx.getBean("ClassCache");
		
		SessionMap session = (SessionMap) ActionContext.getContext().get(ActionContext.SESSION.toString());

		debugSessionAttributes(session);

		// BEGIN - build query

		String selectedSearchDomain=null;
		String query=null;

		String submitValue = getBtnSearch();
		log.debug("submitValue: " + submitValue);

		String className = getSelectedDomain();

		log.debug("className (selectedDomain): "+ getSelectedDomain());
		
		System.out.println("className (selectedDomain): "+ getSelectedDomain());

		if(submitValue != null && submitValue.equalsIgnoreCase("Submit"))
		{
		    query = "GetHTML?query=";

		   	selectedSearchDomain = getSearchObj();
		   	log.debug("selectedSearchDomain: "+ selectedSearchDomain);

		   	Object instance = prepareObject(request);
		   	String url = request.getRequestURL().toString();
		   	System.out.println("url: "+url);
		   	String restURL = url.substring(0, url.indexOf("Create.action"));
		   	System.out.println("restURL "+restURL);
		   	WebClient client = WebClient.create(restURL);
		   	client.path("rest/"+selectedDomain.substring(selectedDomain.lastIndexOf(".")+1, selectedDomain.length()));
		   	client.type("application/xml").accept("application/xml");
		   	Response r = client.post(instance);

		      System.out.println("r: " + r.getStatus());

		    Enumeration headerNames = request.getHeaderNames();
		    while(headerNames.hasMoreElements()) {
		      String headerName = (String)headerNames.nextElement();
		      System.out.println("headerName: " + headerName);
		      System.out.println("header: " + request.getHeader(headerName));
		    }
		   	
		   	String username = (String) session.get("username");
		   	String password = (String) session.get("password");

		   	if ((username != null) && (username.trim()).length() > 0)
		   		query = query + "&username=" + username;
		   	if ((password != null) && (password.trim()).length() > 0)
		   		query = query + "&password=" + password;

		}

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

	private Object prepareObject(HttpServletRequest request){

		StringBuilder sb = new StringBuilder();
		Enumeration<String> parameters = request.getParameterNames();

		Map<String, Map<String, List<Object>>> isoDataTypeNodes = new HashMap<String, Map<String, List<Object>>>();
		Object instance = null;
		try
		{
			Class klass = Class.forName(selectedDomain);
			instance = klass.newInstance();
	 		while(parameters.hasMoreElements())
	 		{
	     		String parameterName = (String)parameters.nextElement();
	     		if(!parameterName.equals("klassName") && !parameterName.equals("searchObj") && !parameterName.equals("BtnSearch") && !parameterName.equals("username") && !parameterName.equals("password") && !parameterName.equals("selectedDomain"))
	     		{
	     			System.out.println("param = " + parameterName);	
	     			String parameterValue = (request.getParameter(parameterName)).trim();
					System.out.println("parameterValue: " + parameterValue);
	     			setParameterValue(klass, instance, parameterName, parameterValue);
	     			System.out.println("Instance "+instance);
	     		}
	     	}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return instance;
	}
	
	
	private void setParameterValue(Class klass, Object instance, String name, String value)
	{
		if(value != null && value.trim().length()==0)
			value = null;
		
		try
		{
			String paramName = name.substring(0,1).toUpperCase()+name.substring(1);
			System.out.println("paramName: "+paramName);
			Method[] allMethods = klass.getDeclaredMethods();
		    for (Method m : allMethods) {
				String mname = m.getName();
				if(mname.equals("get"+paramName))
				{
					Class type = m.getReturnType();
					System.out.println("type.getName(): "+type.getName());
					Class[] argTypes = new Class[] { type };
				
					Method setMethod = klass.getDeclaredMethod("set"+paramName, argTypes);
				    setMethod.invoke(instance, convertValue(type, value));
				}
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public Object convertValue(Class klass, Object value) throws WebApplicationException {

		String fieldType = klass.getName();
		Object convertedValue = null;
		try {
			if (fieldType.equals("java.lang.Long")) {
					convertedValue = new Long((String) value);
			} else if (fieldType.equals("java.lang.Integer")) {
					convertedValue = new Integer((String) value);
			} else if (fieldType.equals("java.lang.String")) {
				convertedValue =  value;
			} else if (fieldType.equals("java.lang.Float")) {
					convertedValue = new Float((String) value);
			} else if (fieldType.equals("java.lang.Double")) {
					convertedValue = new Double((String) value);
			} else if (fieldType.equals("java.lang.Boolean")) {
					convertedValue = new Boolean((String) value);
			} else if (fieldType.equals("java.util.Date")) {
				SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
				convertedValue = format.parse((String) value);
			} else if (fieldType.equals("java.net.URI")) {
					convertedValue = new URI((String) value);
			} else if (fieldType.equals("java.lang.Character")) {
					convertedValue = new Character(((String) value).charAt(0));
			} else if (klass.isEnum()) {
					Class enumKlass = Class.forName(fieldType);
					convertedValue = Enum.valueOf(enumKlass, (String) value);
			} else {
				throw new Exception("type mismatch - " + fieldType);
			}

		} catch (Exception ex) {
			 ex.printStackTrace();
			 log.error("ERROR : " + ex.getMessage());
		}
		System.out.println("convertedValue: "+convertedValue);
		return convertedValue;
	}
	
}


