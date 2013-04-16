/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
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

@Path("/catalog")
@Produces("application/xml")
public class BookResource
{

	private static Logger log = Logger.getLogger(BookResource.class);	
	
	@GET
		@Path("")
		@Produces("application/xml")
		public interface java.util.List getBook3( @Context UriInfo uriInfo)
		{
			try
			{
				List<PathSegment> pathSegments = uriInfo.getPathSegments();
				Map matrixParams = pathSegments.get(1).getMatrixParameters();
				//String[] paramNames = {};
				//validateCriteria("Book", matrixParams, paramNames);

		        gov.nih.nci.ejb.CatalogHome ejbHome; 
		        gov.nih.nci.ejb.Catalog ejbRemote;
		 
		         javax.naming.InitialContext initContext = null; 
		
		        try { 
		            initContext = new javax.naming.InitialContext(); 
		            String JNDIName = "CatalogRemoteHome"; 
		            Object obj = initContext.lookup("CatalogRemoteHome");
		            gov.nih.nci.ejb.CatalogHome homeRef = (gov.nih.nci.ejb.CatalogHome) initContext.lookup("CatalogRemoteHome");
		            ejbRemote = (gov.nih.nci.ejb.Catalog) homeRef.create();  
					return ejbRemote.getBooks();
		        }  
		        catch (Exception e) { 
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        } 
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	
	@PUT
		@Path("{param1}")
		@Consumes("application/xml")
		public Response updateBook(gov.nih.nci.ejb.Book param1)
		{
			try
			{
		        gov.nih.nci.ejb.CatalogHome ejbHome; 
		        gov.nih.nci.ejb.Catalog ejbRemote;
		        javax.naming.InitialContext initContext = null; 
		
		        try { 
		            initContext = new javax.naming.InitialContext(); 
		            String JNDIName = "CatalogRemoteHome"; 
		            gov.nih.nci.ejb.CatalogHome homeRef = (gov.nih.nci.ejb.CatalogHome) initContext.lookup(JNDIName);
		            ejbRemote = (gov.nih.nci.ejb.Catalog) homeRef.create();  
	 ejbRemote.addBook(param1);
					
					ResponseBuilder builder = Response.status(Status.OK);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>MESSAGE</type>");
					buffer.append("<code>ADD</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>PUT</path>");
					buffer.append("<message>Successfully Added Book</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					return builder.build();
						
		        }  
		        catch(javax.ejb.CreateException e) {
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        }  
		        catch(java.rmi.RemoteException e) {
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        }  
		        catch (Exception e) { 
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        } 
		
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	
	@POST
		@Path("{param1}")
		@Consumes("application/xml")
		public Response addBook(gov.nih.nci.ejb.Book param1)
		{
			try
			{
		        gov.nih.nci.ejb.CatalogHome ejbHome; 
		        gov.nih.nci.ejb.Catalog ejbRemote;
		         javax.naming.InitialContext initContext = null; 
		
		        try { 
		            initContext = new javax.naming.InitialContext(); 
		            String JNDIName = "CatalogRemoteHome"; 
		            gov.nih.nci.ejb.CatalogHome homeRef = (gov.nih.nci.ejb.CatalogHome) initContext.lookup(JNDIName);
		            ejbRemote = (gov.nih.nci.ejb.Catalog) homeRef.create();
	 ejbRemote.updateBook(param1);
					
					ResponseBuilder builder = Response.status(Status.OK);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>MESSAGE</type>");
					buffer.append("<code>UPDATE</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>POST</path>");
					buffer.append("<message>Successfully updated Book</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					return builder.build();
		            	   
		        }  
		        catch(javax.ejb.CreateException e) {
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        }  
		        catch(java.rmi.RemoteException e) {
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        }  
		        catch (Exception e) { 
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        } 
			}
			catch(WebApplicationException e)
			{
				e.printStackTrace();
				throw e;
			}
		}
	
	@DELETE
		@Path("{param1}")
		@Consumes("application/xml")
		public Response removeBook(@PathParam("param1") java.lang.String param1)
		{
			try
			{
		        gov.nih.nci.ejb.CatalogHome ejbHome; 
		        gov.nih.nci.ejb.Catalog ejbRemote;
		         javax.naming.InitialContext initContext = null; 
		
		        try { 
		            initContext = new javax.naming.InitialContext(); 
		            String JNDIName = "CatalogRemoteHome"; 
		            gov.nih.nci.ejb.CatalogHome homeRef = (gov.nih.nci.ejb.CatalogHome) initContext.lookup(JNDIName);
		            ejbRemote = (gov.nih.nci.ejb.Catalog) homeRef.create();
	 ejbRemote.deleteBook(param1);
					
					ResponseBuilder builder = Response.status(Status.OK);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>MESSAGE</type>");
					buffer.append("<code>DELETE</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>{param1}</path>");
					buffer.append("<message>Successfully deleted Book</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					return builder.build();
						
		        }  
		        catch(javax.ejb.CreateException e) {
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        }  
		        catch(java.rmi.RemoteException e) {
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        }  
		        catch (Exception e) { 
		        	e.printStackTrace();
					ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
					builder.type("application/xml");
					StringBuffer buffer = new StringBuffer();
					buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
					buffer.append("<response>");
					buffer.append("<type>ERROR</type>");
					buffer.append("<code>GET_EJB_LOCAL_1</code>");
					buffer.append("<resource>Book</resource>");
					buffer.append("<path>GET</path>");
					buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
					buffer.append("</response>");
					builder.entity(buffer.toString());
					throw new WebApplicationException(builder.build());
		        } 
				
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