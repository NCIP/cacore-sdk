package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.ClassCache;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.acegisecurity.Authentication;
import javax.ws.rs.core.Response.Status;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.codec.binary.Base64;
public class RestSearchAction extends RestQuery {

	private static final long serialVersionUID = 1234567890L;

	private static Logger log = Logger.getLogger(Result.class.getName());

	// Query parameters
	private String query;
	private String btnSearch;
	private String searchObj;
	private String selectedDomain;
	private ClassCache classCache;
	WebApplicationContext ctx;
	private String resultStyleSheet;

	public String execute() throws Exception {
		init();

		String submitValue = getBtnSearch();
		log.debug("submitValue: " + submitValue);

		String className = getSelectedDomain();
		log.debug("className (selectedDomain): " + getSelectedDomain());
		String targetClass = className;

		if (submitValue != null && submitValue.equalsIgnoreCase("Submit")) {
			selectedSearchDomain = getSearchObj();
			log.debug("selectedSearchDomain: " + selectedSearchDomain);
			String roleName = null;

			if (selectedSearchDomain != null
					&& !selectedSearchDomain.equals("Please choose")) {
				if (!selectedSearchDomain.equalsIgnoreCase(className)) {
					roleName = getRolename(selectedSearchDomain);
					selectedSearchDomain = removeRolename(selectedSearchDomain);
					targetClass = selectedSearchDomain;
				}
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			SessionMap session = (SessionMap) ActionContext.getContext().get(
					ActionContext.SESSION.toString());
			debugSessionAttributes(session);
			// String username = (String) session.get("Username");
			// String password = (String) session.get("Password");

			org.acegisecurity.context.SecurityContext context = (org.acegisecurity.context.SecurityContext) session
					.get("ACEGI_SECURITY_CONTEXT");
			Authentication authentication = context.getAuthentication();
			// authentication.getCredentials();
			System.out.println("username 11 "
					+ authentication.getPrincipal().toString());
			String userName = ((org.acegisecurity.userdetails.User) authentication
					.getPrincipal()).getUsername();
			String password = authentication.getCredentials().toString();
			System.out.println("password 11 "
					+ authentication.getCredentials().toString());
			// if(username == null || username.trim().length() == 0 || password
			// == null || password.trim().length() == 0)
			// return LOGIN;

			String url = request.getRequestURL().toString();
			String restURL = url.substring(0, url.lastIndexOf("/"));
			WebClient client = WebClient.create(restURL);
			String queryStr = getQueryString(className, roleName, request);
			String path = "rest/"
					+ selectedDomain.substring(
							selectedDomain.lastIndexOf(".") + 1,
							selectedDomain.length()) + "/" + queryStr;
//					+ "?username=" + userName + "&password="
//					+ org.apache.cxf.jaxrs.utils.HttpUtils.pathEncode(password);
			System.out.println("Path: " + path);
			client.path(path);
			String base64encodedUsernameAndPassword = new String(Base64.encodeBase64((userName + ":" + password).getBytes()));
			client.header("Authorization", "Basic " + base64encodedUsernameAndPassword);

			client.type("application/xml").accept("application/xml");
			Response r = client.get();
			System.out.println("Response code: " + r.getStatus());
			if (r.getStatus() == Status.UNAUTHORIZED.getStatusCode()) {
				String html = getUnauthorizedHTML(queryStr, className, "Unauthorized access to "+userName);
				request.setAttribute("HTMLContent", html);
			} else {
				InputStream is = (InputStream) r.getEntity();

				org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
						false);
				org.jdom.Document jDoc = builder.build(is);
				System.out.println("Response: " + jDoc.toString());
				// response.setContentType("text/xml");
				// ServletOutputStream out = response.getOutputStream();
				String html = getHTML(jDoc, className, queryStr, null);
				request.setAttribute("HTMLContent", html);
				request.setAttribute("targetClass", targetClass);
				// String username = (String) session.get("username");
				// String password = (String) session.get("password");
			}
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

	private String getRolename(String selectedSearchDomain) {
		return selectedSearchDomain.substring(0,
				selectedSearchDomain.indexOf(" "));
	}

	private String removeRolename(String selectedSearchDomain) {
		int beginIndex = selectedSearchDomain.indexOf("(") + 1;
		int endIndex = selectedSearchDomain.indexOf(")");

		return selectedSearchDomain.substring(beginIndex, endIndex);
	}
}
