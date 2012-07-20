package gov.nih.nci.system.web;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.dao.orm.HibernateConfigurationHolder;
import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;
import gov.nih.nci.system.web.util.HTTPUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import org.mmbase.util.Encode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * The HTTPQuery servlet interpretes a query request and makes appropriate calls to the Application Service interface.  
 * The results are sent back to the user as an XML or HTML document based on the type of request made.  
 * XQuery like syntax is used to generate the query.
 * 
 * Syntax:
 * http://server:port/servlet/queryType?query=targetClassName&criteriaClassName[@attribute=value][association[@attribute=value]]
 * Please refer to the cacore documentation for more information on generating a query request.
 */
public class HTTPQuery extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(HTTPQuery.class.getName());

	private String cacoreStyleSheet;
    private String jsonStyleSheet;

	private int pageSize = 1000; //default

	WebApplicationContext ctx;

	/**
	 * Initialize the servlet
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = config.getServletContext();
		
		ctx =  WebApplicationContextUtils.getWebApplicationContext(context);
		Properties systemProperties = (Properties) ctx.getBean("WebSystemProperties");

		cacoreStyleSheet = systemProperties.getProperty("resultOutputFormatter");
		jsonStyleSheet = systemProperties.getProperty("jsonOutputFormatter");
		log.debug("cacoreStylesheet: " + cacoreStyleSheet);
        log.debug("jsonStyleSheet: " + jsonStyleSheet);

		try {
			String pageCount = systemProperties.getProperty("rowCounter");
			log.debug("rowCounter: " + pageCount);
			if (pageCount != null) {
				pageSize = Integer.parseInt(pageCount);
			}
		} catch (Exception ex) {
			log.error("Exception: ", ex);
		}
	}

	/**
	 * Handls Post requests
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Unload servlet
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * Handles Get requests
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletOutputStream out = response.getOutputStream();
		out = response.getOutputStream();

		Object[] resultSet = null;
		int pageNumber = 1;
		
		ApplicationService applicationService = (ApplicationService)ctx.getBean("ApplicationServiceImpl");
		ClassCache classCache= (ClassCache)ctx.getBean("ClassCache");
		HibernateConfigurationHolder configurationHolder = (HibernateConfigurationHolder) ctx.getBean("HibernateConfigHolder");
		HTTPUtils httpUtils = new HTTPUtils(applicationService,classCache,pageSize,configurationHolder);
		String queryType = httpUtils.getQueryType(request.getRequestURL().toString());
		
		String query = null;

		try {

			if (URLDecoder.decode(request.getQueryString(), "ISO-8859-1") != null) {
				query = URLDecoder.decode(request.getQueryString(),
						"ISO-8859-1");
			} else {
				throw new Exception("Query not defined" + getQuerySyntax());
			}
			if (query.indexOf("&username") > 0)
				query = query.substring(0, query.indexOf("&username"));


			validateQuery(query);
			httpUtils.setQueryArguments(query);

			if (httpUtils.getPageNumber() != null) {
				pageNumber = Integer.parseInt(httpUtils.getPageNumber());
			}
			httpUtils.setServletName(request.getRequestURL().toString());

			if (httpUtils.getPageSize() != null) {
				//FIX for pagesize being reset by the end user
				//pageSize = Integer.parseInt(httpUtils.getPageSize());
			} else {
				httpUtils.setPageSize(pageSize);
			}

			try {
				resultSet = httpUtils.getResultSet();
			} catch (Exception ex) {
				log.error("Get ResultSet Exception: " + ex.getMessage());
				throw ex;
			}
			executeFormatOutput(response, out, resultSet, pageNumber,httpUtils, queryType);
		} catch (Exception ex) {
			log.error("Exception: ", ex);
			
			if (queryType.endsWith("XML")) {
				response.setContentType("text/xml");
				
				out.println(getXMLErrorMsg(ex, query));
			} else {
				response.setContentType("text/html");
				out.println(getHTMLErrorMsg(ex));
			}
			// Need to add JSON error output???
		}

	}

	private void executeFormatOutput(HttpServletResponse response,
			ServletOutputStream out, Object[] resultSet, int pageNumber,
			HTTPUtils httpUtils, String queryType) throws Exception {
		try {

			XMLOutputter xout = new XMLOutputter();
			org.jdom.Document domDoc = httpUtils.getXMLDocument(resultSet,
					pageNumber);

			if (queryType.endsWith("XML")) {
				response.setContentType("text/xml");
				xout.output(domDoc, out);
			} else if (queryType.endsWith("JSON")) {
		        response.setContentType("application/x-javascript");
		        if (httpUtils.getTargetPackageName() != null) {
		            printDocument(domDoc, jsonStyleSheet, out,httpUtils);
		        }
			} else {
				response.setContentType("text/html");
				if (httpUtils.getTargetPackageName() != null) {
				    printDocument(domDoc, cacoreStyleSheet, out,httpUtils);
				}
			}

		} catch (Exception ex) {
			log.error("Print Results Exception: " + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * Generates an HTML Document for a given XML document with the given stylesheet specification
	 * @param doc Specifies the XML document
	 * @param styleSheet Specifies the stylesheet
	 * @return
	 * @throws Exception
	 */
	public Document getHTMLDocument(Document doc, String styleSheet)
			throws Exception {
		Document htmlDoc = null;

		try {
			InputStream styleIn = Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(styleSheet);
			if (styleIn != null) {
				htmlDoc = XSLTTransformer(doc, styleIn);
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new ServletException(ex.getMessage());
		}
		return htmlDoc;
	}
	
	/**
	 * Generates an HTML Error message based upon a given Exception
	 * @param 	Exception The exception that should be used to generate an HTML error message
	 * @return	A string-based HTML error message containing the Exception message.
	 */
	private String getXMLErrorMsg(Exception ex, String query){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
		  .append("<xlink:httpQuery xmlns:xlink=\"http://www.w3.org/1999/xlink\">")
			.append("<queryRequest>")
				.append("<query>")
					.append("<queryString>" + query + "</queryString>")
					.append("<class></class>")
				.append("</query>")
				.append("<criteria></criteria>")
			.append("</queryRequest>")
			.append("<queryResponse>");
		
		String msg = ex.getMessage();
		Throwable tempEx = ex.getCause();
		while (tempEx != null) {
			msg += "\n\nCaused by: " + tempEx.getMessage();
			tempEx = tempEx.getCause();
		}
		
		sb.append(msg);
		
				sb.append("<error>" + msg + "</error>")
			.append("</queryReponse>")
		.append("</xlink:httpQuery>");

		return sb.toString();
	}
	
	/**
	 * Generates an HTML Error message based upon a given Exception
	 * @param 	Exception The exception that should be used to generate an HTML error message
	 * @return	A string-based HTML error message containing the Exception message.
	 */
	private String getHTMLErrorMsg(Exception ex){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html>\n")
		.append("<head>\n")
		.append("<title>caCORE HTTP Servlet Error</title>\n")
		.append("</head>\n")
		.append("<body>\n")
		.append("<table height=\"100%\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n")
				.append("<tr valign=\"top\" align=\"left\">\n")
					.append("<td valign=\"top\" align=\"left\">\n")
					
		.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" >\n")
				.append("<tr valign=\"top\" align=\"left\">\n")
					.append("<td valign=\"top\" align=\"left\">\n")
						.append("<tr>\n")
							.append("<td valign=\"top\" align=\"left\">\n")
								.append("<b><font size=6>caCORE HTTP Servlet Error:</font></b>\n")
							.append("</td>\n")
						.append("</tr>\n")
						.append("<tr>\n")
							.append("<td valign=\"top\" align=\"left\">\n")
								.append("<b><hr></b>\n")
							.append("</td>\n")
						.append("</tr>\n")
						.append("<tr>\n")
							.append("<td valign=\"top\" align=\"left\">\n")
								.append("<pre class=\"autoOverflow\">\n")
									.append("<font size=4 color=red><b><br><br>\n");
		
		String msg = ex.getMessage();
		Throwable tempEx = ex.getCause();
		while (tempEx != null) {
			msg += "<br><br>Caused by: " + tempEx.getMessage();
			tempEx = tempEx.getCause();
		}
		
		sb.append(msg);
		
							sb.append("</b></font>\n")
							.append("</pre>\n")
						.append("</td>\n")
					.append("</tr>\n")
				.append("</td>\n")
			.append("</tr>\n")
		.append("</table>\n");

		return sb.toString();
	}
	
	/**
	 * Generates an HTML Document for a given XML document with the given stylesheet specification
	 * @param doc Specifies the XML document
	 * @param styleSheet Specifies the stylesheet
	 * @return
	 * @throws Exception
	 */
	public void printDocument(Document doc, String styleSheet,
			OutputStream out, HTTPUtils httpUtils) throws Exception {
		try {
			System.out.println("styleSheet: "+styleSheet);
			System.out.println("doc: "+doc.toString());
			InputStream styleIn = Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(styleSheet);
			if (styleIn != null) {
				httpUtils.transform(doc, styleIn, out);
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new ServletException(ex.getMessage());
		}
	}
	
	/**
	 * Generates an XML or HTML document based on a given stylesheet
	 * @param xmlDoc Specifies the xml document
	 * @param styleIn specifies the stylesheet
	 * @return
	 * @throws Exception 
	 */

	public Document XSLTTransformer(Document xmlDoc, InputStream styleIn)
			throws Exception {
		JDOMSource source = new JDOMSource(xmlDoc);
		JDOMResult result = new JDOMResult();
		try {
			if (styleIn != null) {
				TransformerFactory tFactory = TransformerFactory.newInstance();
				Templates stylesheet = tFactory.newTemplates(new StreamSource(
						styleIn));
				Transformer processor = stylesheet.newTransformer();
				processor.transform(source, result);
			}

		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new Exception("XSLTTransformer Exception: " + ex.getMessage());
		}
		return result.getDocument();
	}

	/**
	 * This method returns true if the query syntax is valid
	 * @param query Specifies the http query 
	 * @return return 
	 */
	private boolean validateQuery(String query) throws Exception {
		boolean valid = true;
		try {
			if (query.indexOf("query") < 0) {
				valid = false;
			} else if (query.endsWith("query=")) {
				valid = false;
			} else if (query.endsWith("query")) {
				valid = false;
			} else if (query.endsWith("&") || query.endsWith("[")) {
				valid = false;
			}

		} catch (Exception ex) {
			valid = false;
		}
		if (valid) {
			int startCounter = 0;
			int endCounter = 0;
			for (int i = 0; i < query.length(); i++) {
				if (query.charAt(i) == SystemConstant.LEFT_BRACKET) {
					startCounter++;
				} else if (query.charAt(i) == SystemConstant.RIGHT_BRACKET) {
					endCounter++;
				}
			}
			if (startCounter != endCounter) {
				throw new Exception(
						"Invalid format: '[' parenthesis does not match number of ']' parenthesis");
			}
		} else {
			Encode encoder = new Encode("ESCAPE_XML");
			throw new Exception("Invalid Syntax: " + encoder.encode(query) + getQuerySyntax());
		}
		return valid;
	}

	/**
	 * Returns the query syntax
	 * @return
	 */
	private String getQuerySyntax() {
		String syntax = "<br><br><font color=black size=4><B>Syntax: </B><br>"
				+ "<font color=purple>query=</font>TargetClassName"
				+ "<font color=purple>&</font>CriteriaClassName"
				+ "<font color=purple>[@</font>attribute"
				+ "<font color=purple>=</font>value"
				+ "<font color=purple>][</font>association"
				+ "<font color=purple>[@</font>attribute"
				+ "<font color=purple>=</font>value"
				+ "<font color=purple>]]<font color=purple></font>";
		return syntax;

	}
}
