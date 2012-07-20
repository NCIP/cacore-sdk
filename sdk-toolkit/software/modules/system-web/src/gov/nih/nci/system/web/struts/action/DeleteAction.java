package gov.nih.nci.system.web.struts.action;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import org.apache.struts2.ServletActionContext;

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
		System.out.println("targetClass: " + targetClass);
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
		System.out.println("idCol: " + idCol);
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
		System.out.println("confirm: " + confirm);
		if (confirm == null || confirm.trim().length() == 0
				|| !confirm.equals("true"))
			return SUCCESS;

		String url = request.getRequestURL().toString();
		System.out.println("url: " + url);
		String restURL = url.substring(0, url.lastIndexOf("/"));
		System.out.println("restURL " + restURL);
		WebClient client = WebClient.create(restURL);
		client.path("rest/"
				+ targetClass.substring(targetClass.lastIndexOf(".") + 1,
						targetClass.length()) + "/" + idColValue);
		client.type("application/xml").accept("application/xml");

		Response r = client.delete();

		if (r.getStatus() == Status.OK.getStatusCode()) {
			System.out.println("Status is OK");
			request.removeAttribute("confirm");
			request.setAttribute("successful", "1");
			// request.removeAttribute("confirm");
		}
		InputStream is = (InputStream) r.getEntity();

		org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(false);
		org.jdom.Document jDoc = builder.build(is);

		request.setAttribute("message", jDoc.getRootElement().getText());
		return SUCCESS;
	}
}
