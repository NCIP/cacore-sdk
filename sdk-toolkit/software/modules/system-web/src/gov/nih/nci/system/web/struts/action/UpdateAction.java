package gov.nih.nci.system.web.struts.action;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;
import org.jdom.Element;

public class UpdateAction extends RestQuery {

	private static final long serialVersionUID = 1234567890L;

	public static Logger log = Logger.getLogger(CreateAction.class.getName());
	String targetClass;
	String BtnSearch;

	public String getBtnSearch() {
		return BtnSearch;
	}

	public void setBtnSearch(String btnSearch) {
		BtnSearch = btnSearch;
	}

	public String execute() throws Exception {
		init();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		targetClass = request.getParameter("target");
		System.out.println("targetClass update: " + targetClass);
		if (targetClass == null || targetClass.trim().length() == 0) {
			request.setAttribute("Message", "Invalid target");
			return null;
		}

		String idCol = null;

		try {
			System.out.println("classCache: " + classCache);
			System.out.println("Class.forName(targetClass): "
					+ Class.forName(targetClass));
			idCol = classCache.getClassIdName(Class.forName(targetClass));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("idCol: " + idCol);
		if (idCol == null) {
			request.setAttribute("Message", "Invalid target");
			return null;
		}

		String idColValue = request.getParameter(idCol);
		System.out.println("idColValue: " + idColValue);
		if (idColValue == null || idColValue.trim().length() == 0) {
			request.setAttribute("Message", "Invalid target identifier");
			return null;
		}

		if (getBtnSearch() != null && getBtnSearch().equalsIgnoreCase("Submit")) {
			Object instance = prepareObject(request);
			String url = request.getRequestURL().toString();
			System.out.println("url: " + url);
			String restURL = url.substring(0, url.indexOf("Update.action"));
			System.out.println("restURL " + restURL);
			WebClient client = WebClient.create(restURL);
			client.path("rest/"
					+ targetClass.substring(targetClass.lastIndexOf(".") + 1,
							targetClass.length()));
			client.type("application/xml").accept("application/xml");
			try
			{
			Response r = client.put(instance);

			System.out.println("r: " + r.getStatus());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			String message = "Successfully Updated";
			request.setAttribute("message", message);
		} 
		
		String url = request.getRequestURL().toString();
		System.out.println("url: " + url);
		String restURL = url.substring(0, url.lastIndexOf("/"));
		System.out.println("restURL " + restURL);
		WebClient client = WebClient.create(restURL);
		client.path("rest/"
				+ targetClass.substring(targetClass.lastIndexOf(".") + 1,
						targetClass.length()) + "/" + idColValue);

		client.type("application/xml").accept("application/xml");

		Response r = client.get();

		InputStream is = (InputStream) r.getEntity();

		org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
				false);
		org.jdom.Document jDoc = builder.build(is);
		request.setAttribute("jDoc", jDoc);
		
		return SUCCESS;
	}

	private Object prepareObject(HttpServletRequest request) {

		StringBuilder sb = new StringBuilder();
		Enumeration<String> parameters = request.getParameterNames();

		Map<String, Map<String, List<Object>>> isoDataTypeNodes = new HashMap<String, Map<String, List<Object>>>();
		Object instance = null;
		try {
			Class klass = Class.forName(targetClass);
			instance = klass.newInstance();
			while (parameters.hasMoreElements()) {
				String parameterName = (String) parameters.nextElement();
				if (!parameterName.equals("klassName")
						&& !parameterName.equals("searchObj")
						&& !parameterName.equals("BtnSearch")
						&& !parameterName.equals("username")
						&& !parameterName.equals("password")
						&& !parameterName.equals("selectedDomain")) {
					System.out.println("param = " + parameterName);
					String parameterValue = (request
							.getParameter(parameterName)).trim();
					System.out.println("parameterValue: " + parameterValue);
					setParameterValue(klass, instance, parameterName,
							parameterValue);
					System.out.println("Instance " + instance);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	private void setParameterValue(Class klass, Object instance, String name,
			String value) {
		if (value != null && value.trim().length() == 0)
			value = null;

		try {
			String paramName = name.substring(0, 1).toUpperCase()
					+ name.substring(1);
			System.out.println("paramName: " + paramName);
			Method[] allMethods = klass.getMethods();
			for (Method m : allMethods) {
				String mname = m.getName();
				if (mname.equals("get" + paramName)) {
					Class type = m.getReturnType();
					System.out.println("type.getName(): " + type.getName());
					Class[] argTypes = new Class[] { type };

					Method method = null;
					while (klass != Object.class) {
					     try {
					    	  Method setMethod = klass.getDeclaredMethod("set"+paramName, argTypes);
					    	  setMethod.setAccessible(true);
					          setMethod.invoke(instance, convertValue(type, value));
					          break;
					     } catch (NoSuchMethodException ex) {
					    	 klass = klass.getSuperclass();
					    	 System.out.println("Getting super......");
					     }
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object convertValue(Class klass, Object value)
			throws WebApplicationException {

		String fieldType = klass.getName();
		Object convertedValue = null;
		try {
			if (fieldType.equals("java.lang.Long")) {
				convertedValue = new Long((String) value);
			} else if (fieldType.equals("java.lang.Integer")) {
				convertedValue = new Integer((String) value);
			} else if (fieldType.equals("java.lang.String")) {
				convertedValue = value;
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
		System.out.println("convertedValue: " + convertedValue);
		return convertedValue;
	}

}
