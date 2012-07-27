package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;
import gov.nih.nci.system.web.util.HTTPUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.util.ServletContextAware;
import org.apache.commons.codec.binary.Base64;
import org.acegisecurity.Authentication;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import org.apache.cxf.jaxrs.client.WebClient;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.jdom.transform.JDOMSource;

public class RestLinkAction extends RestQuery {

    private static final long serialVersionUID = 1234567890L;

    private static Logger log = Logger.getLogger(Result.class.getName());


	public String execute() throws Exception {
		init();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		ServletContext context = ServletActionContext.getServletContext();

		String linkHref = request.getParameter("linkHref");
		String className = request.getParameter("targetClass");
System.out.println("targetClass: "+className);

		if(linkHref != null)
		{
			String targetClass = getClassName(linkHref);
			System.out.println("formatHref(linkHref, targetClass): "+formatHref(linkHref, targetClass));
		   	WebClient client = WebClient.create(formatHref(linkHref, targetClass));

			SessionMap session = (SessionMap) ActionContext.getContext().get(
					ActionContext.SESSION.toString());
			org.acegisecurity.context.SecurityContext scontext = (org.acegisecurity.context.SecurityContext) session
					.get("ACEGI_SECURITY_CONTEXT");
			if(scontext != null)
			{
				Authentication authentication = scontext.getAuthentication();
				// authentication.getCredentials();
				System.out.println("username 11 "
						+ authentication.getPrincipal().toString());
				String userName = ((org.acegisecurity.userdetails.User) authentication
						.getPrincipal()).getUsername();
				String password = authentication.getCredentials().toString();
				System.out.println("password 11 "
						+ authentication.getCredentials().toString());
				String base64encodedUsernameAndPassword = new String(Base64.encodeBase64((userName + ":" + password).getBytes()));
				client.header("Authorization", "Basic " + base64encodedUsernameAndPassword);
			}
			else
			{
				if(secured)
				{
					request.setAttribute("message", "Invalid authentication");
					return SUCCESS;
				}

			}

		   	client.type("application/xml").accept("application/xml");
		   	Response r = client.get();


		   	InputStream is = (InputStream)r.getEntity();

		   	org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(false);
            org.jdom.Document jDoc = builder.build(is);

		   	//response.setContentType("text/xml");
		   	//ServletOutputStream out = response.getOutputStream();
		   	String roleName = getRoleName(linkHref, className);
		   	String resourceName = targetClass.substring(targetClass.lastIndexOf(".")+1);

		   	String html = getHTML(jDoc, targetClass, getCriteria(linkHref, resourceName), roleName);
		   	System.out.println("html: "+html);
		   	request.setAttribute("HTMLContent", html);
		   	request.setAttribute("targetClass", targetClass);
		}
		return SUCCESS;
	}

	private String getRoleName(String href, String className)
	{
		String resName = className.substring(className.lastIndexOf(".")+1);
		int index = href.indexOf(resName);
		if(index == -1)
			return null;

		String criteriaStr = href.substring(index+resName.length()+1);
		String idStr = null;
		//role part
		int index2 = criteriaStr.indexOf("/");
		String roleName = null;
		if(index2 != -1)
		{
			idStr = criteriaStr.substring(0, index2);
			roleName = criteriaStr.substring(index2+1, criteriaStr.length());
			return roleName;
		}
		return null;
	}

	private String formatHref(String href, String className)
	{
		String resName = className.substring(className.lastIndexOf(".")+1);
		System.out.println("resName: "+resName);
		int index = href.indexOf(resName);
		if(index == -1)
			return href;

		String preIdStr = href.substring(0, index+resName.length()+1);
		System.out.println("preIdStr: "+preIdStr);
		//id part
		String criteriaStr = href.substring(index+resName.length()+1);
		System.out.println("criteriaStr: "+criteriaStr);
		String idStr = null;
		//role part
		int index2 = criteriaStr.indexOf("/");
		String roleName = null;
		if(index2 != -1)
		{
			idStr = criteriaStr.substring(0, index2);
			roleName = criteriaStr.substring(index2+1, criteriaStr.length());
			System.out.println("roleName: "+roleName);
		}
		else
			idStr = criteriaStr;

		String idName = null;
		System.out.println("idStr: "+idStr);
		System.out.println("className: "+className);
		try
		{
			idName = classCache.getClassIdName(Class.forName(className));
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		System.out.println("idName: "+idName);
		if(idName == null)
			return href;

		String newHref = preIdStr + "search;"+idName+"="+idStr + (roleName != null?"/"+roleName:"");
		return newHref;
	}

	private String getCriteria(String linkHref, String resName)
	{
		int index = linkHref.indexOf(resName);
		if(index == -1)
			return null;
		String crStr = linkHref.substring(index+resName.length()+1);
		int index2 = crStr.indexOf("/");
		if(index2 == -1)
			return crStr;
		else
			return crStr.substring(index2+1);
	}
}


