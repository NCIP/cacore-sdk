/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.web.struts.action;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.acegisecurity.Authentication;
import javax.ws.rs.core.Response.Status;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.codec.binary.Base64;
import org.jdom.Element;

/**
 * Delete Action using RESTful Delete Implementation
 * Given Resource URL is used to determine the resource and its Id
 * Upon confirmation, proceed to delete
 * Upon delete, display message by hiding delete buttons section
 * @author konkapv
 *
 */
public class DeleteAction extends RestQuery {

	private static final long serialVersionUID = 1234567890L;

	private static Logger log = Logger.getLogger(Result.class.getName());

	public String execute() throws Exception {
		init();
		HttpServletRequest request = ServletActionContext.getRequest();
		String targetClass = request.getParameter("target");
		if (targetClass == null || targetClass.trim().length() == 0) {
			request.setAttribute("Message", "Invalid target");
			return null;
		}

		String idCol = null;

		try {
			idCol = classCache.getClassIdName(Class.forName(targetClass));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (idCol == null) {
			request.setAttribute("Message", "Invalid target");
			return null;
		}

		String idColValue = request.getParameter(idCol);
		if (idColValue == null || idColValue.trim().length() == 0) {
			request.setAttribute("Message", "Invalid target identifier");
			return null;
		}

		String confirm = request.getParameter("confirm");
		if (confirm == null || confirm.trim().length() == 0
				|| !confirm.equals("true"))
			return SUCCESS;

		String url = request.getRequestURL().toString();
		String restURL = url.substring(0, url.lastIndexOf("/"));
		WebClient client = WebClient.create(restURL);
		client.path("rest/"
				+ targetClass.substring(targetClass.lastIndexOf(".") + 1,
						targetClass.length()) + "/" + idColValue);
		client.type("application/xml").accept("application/xml");
		SessionMap session = (SessionMap) ActionContext.getContext().get(
				ActionContext.SESSION.toString());
		org.acegisecurity.context.SecurityContext context = (org.acegisecurity.context.SecurityContext) session
				.get("ACEGI_SECURITY_CONTEXT");
		if(context != null)
		{
			Authentication authentication = context.getAuthentication();
			// authentication.getCredentials();
			String userName = ((org.acegisecurity.userdetails.User) authentication
					.getPrincipal()).getUsername();
			String password = authentication.getCredentials().toString();
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

		Response r = client.delete();

		if (r.getStatus() == Status.OK.getStatusCode()) {
			request.removeAttribute("confirm");
			request.setAttribute("successful", "1");
			// request.removeAttribute("confirm");
			
			InputStream is = (InputStream) r.getEntity();
	
			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(false);
			org.jdom.Document jDoc = builder.build(is);
			Element root = jDoc.getRootElement();
			Element messageEle = root.getChild("message");
			request.setAttribute("message", org.apache.commons.lang.StringEscapeUtils.escapeHtml(messageEle.getText()));
		}
		else
		{
			InputStream is = (InputStream) r.getEntity();

			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);
			Element root = jDoc.getRootElement();
			Element message = root.getChild("message");
			String error = root.getText();
			if(message != null)
				error = message.getText();
			
			String messageStr = "Failed to create: "+error;
			request.setAttribute("message", org.apache.commons.lang.StringEscapeUtils.escapeHtml(messageStr));
		}
		return SUCCESS;
	}
}
