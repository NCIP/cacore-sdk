package gov.nih.nci.system.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sun.jersey.api.core.HttpRequestContext;
import com.sun.jersey.spi.resource.Singleton;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.WritableApplicationService;
import gov.nih.nci.system.client.proxy.ListProxy;
import gov.nih.nci.system.dao.DAO;
import gov.nih.nci.system.dao.orm.HibernateConfigurationHolder;
import gov.nih.nci.system.dao.orm.ORMDAOImpl;
import gov.nih.nci.system.query.SDKQueryResult;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;
import gov.nih.nci.system.query.hql.DeleteHQLQuery;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;
import gov.nih.nci.system.web.util.HTTPUtils;

import org.apache.log4j.Logger;
import org.cagrid.iso21090.sdkquery.translator.CQL2ParameterizedHQL;
import org.cagrid.iso21090.sdkquery.translator.ParameterizedHqlQuery;
import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import org.mmbase.util.Encode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLDecoder;

@Path("/facade")
public class SDKRESTfulResource extends RESTfulResource{

    public SDKRESTfulResource(@Context ServletContext context)
    {
    	super(context);
  }


  @GET
  @Consumes("plain/text")
  @Produces("application/xml")
  @Path("/XML")
  public void getRecords(@Context javax.servlet.http.HttpServletRequest request, @Context javax.servlet.http.HttpServletResponse response, @QueryParam("query") String query)
  {
	    ServletOutputStream out = null;
		Object[] resultSet = null;
		int pageNumber = 1;
		String queryType = "XML";
		try {
			out = response.getOutputStream();
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
				try
				{
				if(out != null)
					out.println(getXMLErrorMsg(ex, query));
				}
				catch(Exception e){}
			} else {
				response.setContentType("text/html");
				try
				{
					if(out != null)
						out.println(getHTMLErrorMsg(ex));
				}
				catch(Exception e){}
			}
			// Need to add JSON error output???
		}

  }

  @POST
  @Consumes("application/xml")
  @Produces("application/xml")
  @Path("/XML")
  public void getResult(@Context javax.servlet.http.HttpServletRequest request, @Context javax.servlet.http.HttpServletResponse response, SDKQuery query)
  {
	  ServletOutputStream out = null;

		Object[] resultSet = null;
		int pageNumber = 1;
		String queryType = "XML";
		try {
			out = response.getOutputStream();

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
				Collection result = applicationService.search( query.getpathString(), query.getParameters());
				resultSet = result.toArray();
			} catch (Exception ex) {
				log.error("Get ResultSet Exception: " + ex.getMessage());
				throw ex;
			}
			executeFormatOutput(response, out, resultSet, pageNumber,httpUtils, queryType);
		} catch (Exception ex) {
			log.error("Exception: ", ex);

			if (queryType.endsWith("XML")) {
				response.setContentType("text/xml");
				try
				{
					out.println(getXMLErrorMsg(ex, "Invalid HQLCriteria.."));
				}
				catch(Exception e) {}
			} else {
				response.setContentType("text/html");
				try
				{
					out.println(getHTMLErrorMsg(ex));
				}
				catch(Exception e) {}
			}
			// Need to add JSON error output???
		}

  }

}
