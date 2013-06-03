/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

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
import gov.nih.nci.restgen.generated.client.*;

@Path("/hotel")
@Produces("application/xml")
public class HotelResource
{

	private static Logger log = Logger.getLogger(HotelResource.class);	
	
	@GET
		@Path("/restaurants/{hotelName}")
		@Produces("application/xml")
		public java.util.List<Restaurant> getHotel1(@PathParam("hotelName") java.lang.String hotelName, @Context UriInfo uriInfo)
		{
			try
			{
				List<PathSegment> pathSegments = uriInfo.getPathSegments();
				Map matrixParams = pathSegments.get(1).getMatrixParameters();
				String[] paramNames = {"hotelName"};
				//validateCriteria("Hotel", matrixParams, paramNames);

				gov.nih.nci.restgen.generated.client.HotelService service = new gov.nih.nci.restgen.generated.client.HotelService();
				gov.nih.nci.restgen.generated.client.HotelServicePortType port = service.getHotelServiceHttpEndpoint();
				return port.getRestaurants(hotelName);
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	@GET
		@Path("/chef/{restaurantName}/{chefName}")
		@Produces("application/xml")
		public gov.nih.nci.restgen.generated.client.Chef getHotel2(@PathParam("restaurantName") java.lang.String restaurantName, @PathParam("chefName") java.lang.String chefName, @Context UriInfo uriInfo)
		{
			try
			{
				List<PathSegment> pathSegments = uriInfo.getPathSegments();
				Map matrixParams = pathSegments.get(1).getMatrixParameters();
				String[] paramNames = {"restaurantName", "chefName"};
				//validateCriteria("Hotel", matrixParams, paramNames);

				gov.nih.nci.restgen.generated.client.HotelService service = new gov.nih.nci.restgen.generated.client.HotelService();
				gov.nih.nci.restgen.generated.client.HotelServicePortType port = service.getHotelServiceHttpEndpoint();
				return port.getRestaurantChef(restaurantName,chefName);
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	@GET
		@Path("/hotel/{name}")
		@Produces("application/xml")
		public gov.nih.nci.restgen.generated.client.Hotel getHotel4(@PathParam("name") java.lang.String name, @Context UriInfo uriInfo)
		{
			try
			{
				List<PathSegment> pathSegments = uriInfo.getPathSegments();
				Map matrixParams = pathSegments.get(1).getMatrixParameters();
				String[] paramNames = {"name"};
				//validateCriteria("Hotel", matrixParams, paramNames);

				gov.nih.nci.restgen.generated.client.HotelService service = new gov.nih.nci.restgen.generated.client.HotelService();
				gov.nih.nci.restgen.generated.client.HotelServicePortType port = service.getHotelServiceHttpEndpoint();
				return port.getHotel(name);
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	
	@PUT
		@Consumes("application/xml")
		public Response updateHotel(gov.nih.nci.restgen.generated.client.Chef chef, java.lang.Integer restaurantId)
		{
			try
			{
				gov.nih.nci.restgen.generated.client.HotelService service = new gov.nih.nci.restgen.generated.client.HotelService();
				gov.nih.nci.restgen.generated.client.HotelServicePortType port = service.getHotelServiceHttpEndpoint();
	 port.hireChef(chef,restaurantId);
					
				ResponseBuilder builder = Response.status(Status.OK);
				builder.type("application/xml");
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<response>");
				buffer.append("<type>MESSAGE</type>");
				buffer.append("<code>ADD</code>");
				buffer.append("<resource>Hotel</resource>");
				buffer.append("<path>PUT</path>");
				buffer.append("<message>Successfully Added Hotel</message>");
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
		@Path("/{chefId}/{restaurantId}")
		@Consumes("application/xml")
		public Response removeHotel(@PathParam("chefId") java.lang.Integer chefId, @PathParam("restaurantId") java.lang.Integer restaurantId)
		{
			try
			{
				gov.nih.nci.restgen.generated.client.HotelService service = new gov.nih.nci.restgen.generated.client.HotelService();
				gov.nih.nci.restgen.generated.client.HotelServicePortType port = service.getHotelServiceHttpEndpoint();
	 port.fireChef(chefId,restaurantId);
					
				ResponseBuilder builder = Response.status(Status.OK);
				builder.type("application/xml");
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<response>");
				buffer.append("<type>MESSAGE</type>");
				buffer.append("<code>DELETE</code>");
				buffer.append("<resource>Hotel</resource>");
				buffer.append("<path>/{chefId}/{restaurantId}</path>");
				buffer.append("<message>Successfully deleted Hotel</message>");
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