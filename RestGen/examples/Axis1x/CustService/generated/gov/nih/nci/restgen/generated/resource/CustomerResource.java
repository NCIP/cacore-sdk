package gov.nih.nci.restgen.generated.resource;

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

@Path("/Service")
@Produces("application/xml")
public class CustomerResource
{

	private static Logger log = Logger.getLogger(CustomerResource.class);	
	
	@GET
		@Path("/ByName/{name}")
		@Produces("application/xml")
		public gov.nih.nci.restgen.generated.client.Customer getCustomer3(@PathParam("name") java.lang.String name, @Context UriInfo uriInfo)
		{
			try
			{
				List<PathSegment> pathSegments = uriInfo.getPathSegments();
				Map matrixParams = pathSegments.get(1).getMatrixParameters();
				String[] paramNames = {"name"};
				//validateCriteria("Customer", matrixParams, paramNames);

				gov.nih.nci.restgen.generated.client.CustomerServiceService service = new gov.nih.nci.restgen.generated.client.CustomerServiceService();
				gov.nih.nci.restgen.generated.client.CustomerService port = service.getCustomerServicePort();
				return port.getCustomerByName(name);
			}
			catch(gov.nih.nci.restgen.generated.client.NoSuchCustomer_Exception e)
			{
				 e.printStackTrace();
				 throw new WebApplicationException(buildResponseError("GET_METHOD", "Customer", "Customer3", "Failed to process GET method"+ e.toString()).build());
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	@GET
		@Path("/customers")
		@Produces("application/xml")
		public java.util.List<gov.nih.nci.restgen.generated.client.Customer> getCustomer4( @Context UriInfo uriInfo)
		{
			try
			{
				List<PathSegment> pathSegments = uriInfo.getPathSegments();
				Map matrixParams = pathSegments.get(1).getMatrixParameters();
				String[] paramNames = {};
				//validateCriteria("Customer", matrixParams, paramNames);

				gov.nih.nci.restgen.generated.client.CustomerServiceService service = new gov.nih.nci.restgen.generated.client.CustomerServiceService();
				gov.nih.nci.restgen.generated.client.CustomerService port = service.getCustomerServicePort();
				return port.getCustomers();
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	
	@PUT
		@Consumes("application/xml")
		public Response updateCustomer(gov.nih.nci.restgen.generated.client.Customer customer)
		{
			try
			{
				gov.nih.nci.restgen.generated.client.CustomerServiceService service = new gov.nih.nci.restgen.generated.client.CustomerServiceService();
				gov.nih.nci.restgen.generated.client.CustomerService port = service.getCustomerServicePort();
	 port.addCustomer(customer);
					
				ResponseBuilder builder = Response.status(Status.OK);
				builder.type("application/xml");
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<response>");
				buffer.append("<type>MESSAGE</type>");
				buffer.append("<code>ADD</code>");
				buffer.append("<resource>Customer</resource>");
				buffer.append("<path>PUT</path>");
				buffer.append("<message>Successfully Added Customer</message>");
				buffer.append("</response>");
				builder.entity(buffer.toString());
				return builder.build();
					
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	
	@POST
		@Consumes("application/xml")
		public Response addCustomer(gov.nih.nci.restgen.generated.client.Customer customer)
		{
			try
			{
				gov.nih.nci.restgen.generated.client.CustomerServiceService service = new gov.nih.nci.restgen.generated.client.CustomerServiceService();
				gov.nih.nci.restgen.generated.client.CustomerService port = service.getCustomerServicePort();
	 port.updateCustomer(customer);
					
					
				ResponseBuilder builder = Response.status(Status.OK);
				builder.type("application/xml");
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<response>");
				buffer.append("<type>MESSAGE</type>");
				buffer.append("<code>UPDATE</code>");
				buffer.append("<resource>Customer</resource>");
				buffer.append("<path>POST</path>");
				buffer.append("<message>Successfully updated Customer</message>");
				buffer.append("</response>");
				builder.entity(buffer.toString());
				return builder.build();
					
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	
	@DELETE
		@Path("/{name}")
		@Consumes("application/xml")
		public Response removeCustomer(@PathParam("name") java.lang.String name)
		{
			try
			{
				gov.nih.nci.restgen.generated.client.CustomerServiceService service = new gov.nih.nci.restgen.generated.client.CustomerServiceService();
				gov.nih.nci.restgen.generated.client.CustomerService port = service.getCustomerServicePort();
	 port.deleteCustomer(name);
					
				ResponseBuilder builder = Response.status(Status.OK);
				builder.type("application/xml");
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<response>");
				buffer.append("<type>MESSAGE</type>");
				buffer.append("<code>DELETE</code>");
				buffer.append("<resource>Customer</resource>");
				buffer.append("<path>/{name}</path>");
				buffer.append("<message>Successfully deleted Customer</message>");
				buffer.append("</response>");
				builder.entity(buffer.toString());
				return builder.build();
	   	        	
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	
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
}