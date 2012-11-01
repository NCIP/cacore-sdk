package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.web.util.SDKRESTContentHandler;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;
import org.jdom.Element;
import org.apache.struts2.dispatcher.SessionMap;
import org.acegisecurity.Authentication;
import javax.ws.rs.core.Response.Status;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.codec.binary.Base64;

public class UpdateAction extends RestQuery {

	private static final long serialVersionUID = 1234567890L;

	public static Logger log = Logger.getLogger(UpdateAction.class.getName());
	String target;
	String id;

	public String execute() throws Exception {
		try
		{
			init();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		target = request.getParameter("target");
		if (target == null || target.trim().length() == 0) {
			request.setAttribute("Message", "Invalid target");
			return null;
		}

		String idCol = null;

		try {
			idCol = classCache.getClassIdName(Class.forName(target));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (idCol == null) {
			request.setAttribute("Message", "Invalid target");
			return null;
		}
		
		request.setAttribute("idCol", idCol);
		String idColValue = request.getParameter(idCol);
		if (idColValue == null || idColValue.trim().length() == 0) {
			request.setAttribute("Message", "Invalid target identifier");
			return null;
		}
		String submitted = request.getParameter("submitForm");
		SessionMap session = (SessionMap) ActionContext.getContext().get(
				ActionContext.SESSION.toString());
		org.acegisecurity.context.SecurityContext context = (org.acegisecurity.context.SecurityContext) session
				.get("ACEGI_SECURITY_CONTEXT");
		if (submitted != null && submitted.equals("true")) {
			try {
				String base64encodedUsernameAndPassword = null;
				Object instance = prepareObject(request);
				String url = request.getRequestURL().toString();
				String restURL = url.substring(0, url.indexOf("Update.action"));
				WebClient client = WebClient.create(restURL);
				client.path("rest/"
						+ target.substring(
								target.lastIndexOf(".") + 1,
								target.length()));
				client.type("application/xml").accept("application/xml");
				if (context != null) {
					Authentication authentication = context.getAuthentication();
					// authentication.getCredentials();
					String userName = ((org.acegisecurity.userdetails.User) authentication
							.getPrincipal()).getUsername();
					String password = authentication.getCredentials()
							.toString();
					base64encodedUsernameAndPassword = new String(
							Base64.encodeBase64((userName + ":" + password)
									.getBytes()));
					client.header("Authorization", "Basic "
							+ base64encodedUsernameAndPassword);
				} else {
					if (secured) {
						request.setAttribute("message",
								"Invalid authentication");
						return SUCCESS;
					}

				}

				try {
					prepareAssociations(request, instance, target, base64encodedUsernameAndPassword);
					Response r = client.post(instance);

					if (r.getStatus() != Status.OK.getStatusCode() && r.getStatus() != Status.NO_CONTENT.getStatusCode()) {
						InputStream is = (InputStream) r.getEntity();

						org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
								false);
						org.jdom.Document jDoc = builder.build(is);
						Element root = jDoc.getRootElement();
						Element message = root.getChild("message");
						String error = root.getText();
						if(message != null)
							error = message.getText();

						request.setAttribute("message", "Unsuccessful update: "
								+ error);
						//return SUCCESS;
					} else {
						String message = "Updated Successfully";
						request.setAttribute("message", message);

					}
				} catch (WebApplicationException e) {
					request.setAttribute("Message", "Failed to update: "+e.getMessage());
					//return SUCCESS;
					//e.printStackTrace();
				}
			} catch (Exception e) {
				String message = "Failed to update: " + e.getMessage();
				request.setAttribute("message", message);
				//return SUCCESS;
			}
		}
		String url = request.getRequestURL().toString();
		String restURL = url.substring(0, url.lastIndexOf("/"));
		WebClient client = WebClient.create(restURL);
		client.path("rest/"
				+ target.substring(target.lastIndexOf(".") + 1,
						target.length()) + "/" + idColValue);

		client.type("application/xml").accept("application/xml");
		if (context != null) {
			Authentication authentication = context.getAuthentication();
			// authentication.getCredentials();
			String userName = ((org.acegisecurity.userdetails.User) authentication
					.getPrincipal()).getUsername();
			String password = authentication.getCredentials().toString();
			String base64encodedUsernameAndPassword = new String(
					Base64.encodeBase64((userName + ":" + password).getBytes()));
			client.header("Authorization", "Basic "
					+ base64encodedUsernameAndPassword);
		} else {
			if (secured) {
				request.setAttribute("message", "Invalid authentication");
				return SUCCESS;
			}

		}
		Response r = client.get();

		InputStream is = (InputStream) r.getEntity();

		org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(false);
		org.jdom.Document jDoc = builder.build(is);
		request.setAttribute("jDoc", jDoc);
		return SUCCESS;
	}

	private Object prepareObject(HttpServletRequest request) throws Exception {

		StringBuilder sb = new StringBuilder();
		Enumeration<String> parameters = request.getParameterNames();

		Map<String, Map<String, List<Object>>> isoDataTypeNodes = new HashMap<String, Map<String, List<Object>>>();
		Object instance = null;
		Class klass = Class.forName(target);
		instance = klass.newInstance();
		while (parameters.hasMoreElements()) {
			String parameterName = (String) parameters.nextElement();
			if (!parameterName.equals("klassName")
					&& !parameterName.equals("searchObj")
					&& !parameterName.equals("BtnSearch")
					&& !parameterName.equals("username")
					&& !parameterName.equals("password")
					&& !parameterName.equals("selectedDomain")) {
				String parameterValue = (request.getParameter(parameterName))
						.trim();
				setParameterValue(klass, instance, parameterName,
						parameterValue);
			}
		}
		return instance;
	}

	private void setParameterValue(Class klass, Object instance, String name,
			String value) throws Exception {
		if (value != null && value.trim().length() == 0)
			return;

		String paramName = name.substring(0, 1).toUpperCase()
				+ name.substring(1);
		Method[] allMethods = klass.getMethods();
		for (Method m : allMethods) {
			String mname = m.getName();
			if (mname.equals("get" + paramName)) {
				Class type = m.getReturnType();
				Class[] argTypes = new Class[] { type };

				Method method = null;
				while (klass != Object.class) {
					try {
						Method setMethod = klass.getDeclaredMethod("set"
								+ paramName, argTypes);
						setMethod.setAccessible(true);
						setMethod.invoke(instance, convertValue(type, value));
						break;
					} catch (NoSuchMethodException ex) {
						klass = klass.getSuperclass();
					}
				}
			}
		}
	}

	public Object convertValue(Class klass, Object value) throws Exception {

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
		} catch (NumberFormatException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} catch (Exception ex) {
			log.error("ERROR : " + ex.getMessage());
			throw ex;
		}
		return convertedValue;
	}

}
