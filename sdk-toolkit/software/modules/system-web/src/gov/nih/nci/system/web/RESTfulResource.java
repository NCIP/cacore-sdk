package gov.nih.nci.system.web;

import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.WritableApplicationService;
import gov.nih.nci.system.dao.orm.HibernateConfigurationHolder;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.query.hql.DeleteHQLQuery;
import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;
import gov.nih.nci.system.web.util.HTTPUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
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

public class RESTfulResource {

  public static Logger log = Logger.getLogger(HTTPQuery.class.getName());

  WebApplicationContext ctx;
  private ClassCache classCache;
  ApplicationService applicationService;
  WritableApplicationService writableApplicationService;
  HTTPUtils httpUtils;
  int pageSize;
	private String cacoreStyleSheet;
    private String jsonStyleSheet;

    public RESTfulResource(@Context ServletContext context)
    {
		try
		{
			ctx =  WebApplicationContextUtils.getWebApplicationContext(context);
			Properties systemProperties = (Properties) ctx.getBean("WebSystemProperties");

			cacoreStyleSheet = systemProperties.getProperty("resultOutputFormatter");
			jsonStyleSheet = systemProperties.getProperty("jsonOutputFormatter");
			classCache= (ClassCache)ctx.getBean("ClassCache");
			writableApplicationService = (WritableApplicationService)ctx.getBean("ApplicationServiceImpl");
			try {
				String pageCount = systemProperties.getProperty("rowCounter");
				if (pageCount != null) {
					pageSize = Integer.parseInt(pageCount);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				pageSize = 10000;
			}

			HibernateConfigurationHolder configurationHolder = (HibernateConfigurationHolder) ctx.getBean("HibernateConfigHolder");
			httpUtils = new HTTPUtils(writableApplicationService,classCache,pageSize,configurationHolder);
		}
		catch(Exception e)
		{
			log.error("Error in constructing REST resource: " + e.getMessage());
			//e.printStackTrace();
		}
  }

	/**
	 * Get the list of all searchable Fields for the class
	 * @param className
	 * @return Field[] of all fields for the given class
	 */
	public List<Field> getSearchableFields(String className) {
		Field[] fields = null;
		List<Field> searchableFields = new ArrayList<Field>();

		try {
			Class klass = classCache.getClassFromCache(className);
			log.debug("Retrieved " + className + " from cache");

			fields = classCache.getAllFields(klass);
			String fieldType;

			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				fieldType = fields[i].getType().getName();

				if (classCache.isSearchable(fieldType)) {
					searchableFields.add(fields[i]);
				}

			}
		} catch (ClassNotFoundException e) {
			// Do nothing.  Abstract class names have been purposely modified 
			// with a " (abstract)" suffix on the UI, and thus are no longer found in the cache
			// This is intentional; abstract classes cannot be used as a target or 
			// search criteria object since they cannot be instantiated via Class.forName().newInstance();
			log.debug("Searchable fields not found for class: " + className + ".  This warning can be safely ignored if the class is abstract or an interface.");
		} catch (Exception e) {
			log.error("Exception caught generating a list of searchable fields for class " + className, e);
		}
		return searchableFields;
	}

    protected void query(@Context javax.servlet.http.HttpServletRequest request, @Context javax.servlet.http.HttpServletResponse response, HQLCriteria criteria, String targetClassName) throws WebApplicationException
  {
	  try
	  {
	 	  httpUtils.setServletName(request.getRequestURL().toString());
		  ServletOutputStream out = response.getOutputStream();
		  Object[] results = httpUtils.getResultSet(criteria);
	
		  if(results == null)
		  {
			  ResponseBuilder builder = Response.status(Status.NOT_FOUND);
			  builder.type("application/xml");
			  builder.entity("<error>Not found</error>");
			  throw new WebApplicationException(builder.build());
		  }
	
		  httpUtils.setTargetClassName(targetClassName);
		  executeFormatOutput(response, out, results, 1,httpUtils, "XML", false);
	  }
	  catch(IOException e)
	  {
		  log.error("Error in querying REST resource: " + e.getMessage());
		  ResponseBuilder builder = Response.status(Status. INTERNAL_SERVER_ERROR);
		  builder.type("application/xml");
		  builder.entity("<error>Failed to Query due to: "+e.getMessage()+"</error>");
		  throw new WebApplicationException(builder.build());
	  }
	  catch(Exception e)
	  {
		  log.error("Error in querying REST resource: " + e.getMessage());
		  ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		  builder.type("application/xml");
		  builder.entity("<error>Not found"+e.getMessage()+"</error>");
		  throw new WebApplicationException(builder.build());
	  }
  }

    protected void query(@Context javax.servlet.http.HttpServletRequest request, @Context javax.servlet.http.HttpServletResponse response, String targetClassName, String targetPackageName, String criteriaValue, String roleName) throws WebApplicationException
    {
  	  try
  	  {
			httpUtils.setServletName(request.getRequestURL().toString());
	  		ServletOutputStream out = response.getOutputStream();
	  		String criteriaStr = targetClassName+"[@id="+criteriaValue+"]";
	  		String roleNameLower = roleName.substring(0,1).toLowerCase()+roleName.substring(1,roleName.length());
	  		Object[]  results = httpUtils.getResultSet(targetClassName, targetPackageName, criteriaStr, roleNameLower);
	
	  		if(results == null)
	  		{
				  ResponseBuilder builder = Response.status(Status.NOT_FOUND);
				  builder.type("application/xml");
				  builder.entity("<error>Not found</error>");
				  throw new WebApplicationException(builder.build());
	  		}
	
	  	  httpUtils.setTargetClassName(targetClassName);
	
	  	  executeFormatOutput(response, out, results, 1,httpUtils, "XML", false);
  	  }
	  catch(IOException e)
	  {
		  log.error("Error in querying REST resource: " + e.getMessage());
		  ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		  builder.type("application/xml");
		  builder.entity("<error>Failed to Query due to: "+e.getMessage()+"</error>");
		  throw new WebApplicationException(builder.build());
	  }
	  catch(Exception e)
	  {
		  log.error("Error in querying REST resource: " + e.getMessage());
		  ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
		  builder.type("application/xml");
		  builder.entity("<error>Not found"+e.getMessage()+"</error>");
		  throw new WebApplicationException(builder.build());
	  }
    }

    protected ApplicationService getApplicationService()
    {
    	return writableApplicationService;
    }
    
	public void save(final Object obj) throws WebApplicationException{
		try
		{
			System.out.println("in save................");
			final InsertExampleQuery sdkQuery = new InsertExampleQuery(obj);
			writableApplicationService.executeQuery(sdkQuery);
		}
		catch(ApplicationException e)
		{
			log.error("Error in Saving REST resource: " + e.getMessage());
			ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to Save due to: "+e.getMessage()+"</error>");
			throw new WebApplicationException(builder.build());
		}
	}

	public void update(Object obj) throws WebApplicationException{
		try
		{
			System.out.println("in update................");
			final UpdateExampleQuery sdkQuery = new UpdateExampleQuery(obj);
			writableApplicationService.executeQuery(sdkQuery);
		}
		catch(ApplicationException e)
		{
			log.error("Error in Updating REST resource: " + e.getMessage());
			ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to update due to: "+e.getMessage()+"</error>");
			throw new WebApplicationException(builder.build());
		}

	}

	public void delete(Object obj) throws WebApplicationException{
		try
		{
			System.out.println("in delete................");
			final DeleteExampleQuery sdkQuery = new DeleteExampleQuery(obj);
			writableApplicationService.executeQuery(sdkQuery);
		}
		catch(ApplicationException e)
		{
			log.error("Error in Updating REST resource: " + e.getMessage());
			ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to update due to: "+e.getMessage()+"</error>");
			throw new WebApplicationException(builder.build());
		}
	}

	public void delete(DeleteHQLQuery query) throws WebApplicationException{
		try
		{
			System.out.println("in delete.......query.........");
			writableApplicationService.executeQuery(query);
		}
		catch(ApplicationException e)
		{
			log.error("Error in Updating REST resource: " + e.getMessage());
			ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
			builder.type("application/xml");
			builder.entity("<error>Failed to update due to: "+e.getMessage()+"</error>");
			throw new WebApplicationException(builder.build());
		}
	}

	  protected void executeFormatOutput(HttpServletResponse response,
				ServletOutputStream out, Object[] resultSet, int pageNumber,
				HTTPUtils httpUtils, String queryType) throws Exception {
		  executeFormatOutput(response, out, resultSet, pageNumber, httpUtils, queryType, true);
	  }

  protected void executeFormatOutput(HttpServletResponse response,
			ServletOutputStream out, Object[] resultSet, int pageNumber,
			HTTPUtils httpUtils, String queryType, boolean query) throws Exception {
		try {

			XMLOutputter xout = new XMLOutputter();
			org.jdom.Document domDoc = httpUtils.getXMLDocument(resultSet,
					pageNumber, query);

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
	protected String getXMLErrorMsg(Exception ex, String query){

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
	protected String getHTMLErrorMsg(Exception ex){

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
	protected boolean validateQuery(String query) throws Exception {
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
	protected String getQuerySyntax() {
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
