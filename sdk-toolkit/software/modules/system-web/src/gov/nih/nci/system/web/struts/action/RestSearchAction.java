package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.ClassCache;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acegisecurity.Authentication;
import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionContext;

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
			String url = request.getRequestURL().toString();
			String restURL = url.substring(0, url.lastIndexOf("/"));
			WebClient client = WebClient.create(restURL);

			org.acegisecurity.context.SecurityContext context = (org.acegisecurity.context.SecurityContext) session
					.get("ACEGI_SECURITY_CONTEXT");
			if (context != null) {
				Authentication authentication = context.getAuthentication();
				// authentication.getCredentials();
				String userName = ((org.acegisecurity.userdetails.User) authentication
						.getPrincipal()).getUsername();
				String password = authentication.getCredentials().toString();
				String base64encodedUsernameAndPassword = new String(
						Base64.encodeBase64((userName + ":" + password)
								.getBytes()));
				client.header("Authorization", "Basic "
						+ base64encodedUsernameAndPassword);
			}
			String queryStr = getQueryString(className, roleName, request);
			log.debug("queryStr "+queryStr);
			String path = "rest/"
					+ selectedDomain.substring(
							selectedDomain.lastIndexOf(".") + 1,
							selectedDomain.length()) + "/" + queryStr;
			client.path(path);
			client.type("application/xml").accept("application/xml");
			Response r = client.get();
			if (r.getStatus() == Status.UNAUTHORIZED.getStatusCode()) {
				String html = getUnauthorizedHTML(queryStr, className,
						"Unauthorized access");
				request.setAttribute("HTMLContent", html);
			} else {
				InputStream is = (InputStream) r.getEntity();

				org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
						false);
				org.jdom.Document jDoc = builder.build(is);
				String html = getHTML(jDoc, className, queryStr, null);
				request.setAttribute("HTMLContent", html);
				request.setAttribute("targetClass", targetClass);
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
