package gov.nih.nci.system.web.struts.action;

import gov.nih.nci.system.util.ClassCache;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.apache.struts2.ServletActionContext;

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
		System.out.println("className: " + className);
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
			System.out.println("selectedDomain: " + selectedDomain);
			System.out.println("selectedSearchDomain: " + selectedSearchDomain);
			HttpServletRequest request = ServletActionContext.getRequest();

			String url = request.getRequestURL().toString();
			System.out.println("url: " + url);
			String restURL = url.substring(0, url.lastIndexOf("/"));
			System.out.println("restURL " + restURL);
			WebClient client = WebClient.create(restURL);
			String queryStr = getQueryString(className, roleName, request);
			System.out.println("queryStr: " + queryStr);
			String path = "rest/"
					+ selectedDomain.substring(
							selectedDomain.lastIndexOf(".") + 1,
							selectedDomain.length()) + "/" + queryStr;
			System.out.println("Path: " + path);
			client.path(path);

			client.type("application/xml").accept("application/xml");
			Response r = client.get();

			InputStream is = (InputStream) r.getEntity();

			org.jdom.input.SAXBuilder builder = new org.jdom.input.SAXBuilder(
					false);
			org.jdom.Document jDoc = builder.build(is);

			// response.setContentType("text/xml");
			// ServletOutputStream out = response.getOutputStream();
			String html = getHTML(jDoc, className, queryStr, null);
			request.setAttribute("HTMLContent", html);
			request.setAttribute("targetClass", targetClass);
			// String username = (String) session.get("username");
			// String password = (String) session.get("password");

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
