package $packageName$;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Iterator;
$importStatements; separator="\n"$

@Path("/$ResourcePath$")
@Produces("application/xml")
public class $ResourceName$Resource
{

	private static Logger log = Logger.getLogger($ResourceName$Resource.class);	
	
	$GetMethods; separator="\n"$
	
	$PutMethods; separator="\n"$
	
	$PostMethods; separator="\n"$
	
	$DeleteMethods; separator="\n"$
	
	private ResponseBuilder buildResponseError(String code, String resourceName, String path, String message)
	{
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>" + code + "</code>");
			buffer.append("<resource>"+resourceName+"</resource>");
			buffer.append("<path>"+path+"</path>");
			buffer.append("<message>"+message+"</message>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			return builder;
	}
	
	protected void validateCriteria(String resourceName, Map matrixParams,
			String[] searchFields) throws WebApplicationException {
		List<String> validAttrs = new ArrayList();

		for (int i = 0; i<searchFields.length; i++)
			validAttrs.add(searchFields[i]);

		if (matrixParams == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>SEARCH_CRITERIA_1</code>");
			buffer.append("<resource>"+resourceName+"</resource>");
			buffer.append("<path>search</path>");
			buffer.append("<message>Search criteria is missing</message>");
			buffer.append("<valid>");
			for (String attrName : validAttrs) {
				buffer.append("<attribute>");
				buffer.append(attrName);
				buffer.append("</attribute>");
			}
			buffer.append("</valid>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			throw new WebApplicationException(builder.build());
		}

		Iterator iter = matrixParams.keySet().iterator();
		List<String> invalidAttrs = new ArrayList<String>();

		while (iter.hasNext()) {
			boolean found = false;
			String fullName = (String) iter.next();
			if (!validAttrs.contains(fullName))
				invalidAttrs.add(fullName);
		}

		if (invalidAttrs.size() > 0) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			String attrs = "";
			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			buffer.append("<response>");
			buffer.append("<type>ERROR</type>");
			buffer.append("<code>SEARCH_CRITERIA_2</code>");
			buffer.append("<resource>"+resourceName+"</resource>");
			buffer.append("<path>search</path>");
			buffer.append("<message>Invalid Search criteria</message>");
			buffer.append("<valid>");
			for (String attrName : validAttrs) {
				buffer.append("<attribute>");
				buffer.append(attrName);
				buffer.append("</attribute>");
			}

			buffer.append("</valid>");
			buffer.append("<invalid>");
			for (String attr : invalidAttrs) {
				attrs = attr + " " + attrs;
				buffer.append("<attribute>");
				buffer.append(attr);
				buffer.append("</attribute>");

			}
			buffer.append("</invalid>");
			buffer.append("</response>");
			builder.entity(buffer.toString());
			throw new WebApplicationException(builder.build());
		}
	}	
}