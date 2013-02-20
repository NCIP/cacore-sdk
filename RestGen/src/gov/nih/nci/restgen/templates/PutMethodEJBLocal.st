	@PUT
	@Path("$PathParamPath$")
	@Consumes("application/xml")
	public $PutReturnType$ update$ResourceName$($RequestType$)
	{
		try
		{
	        $HomeInterface$ ejbHome; 
	        $RemoteInterface$ ejbRemote;
	        javax.naming.InitialContext initContext = null; 
	
	        try { 
	            initContext = new javax.naming.InitialContext(); 
	            String JNDIName = "$JNDIName$"; 
	            $HomeInterface$ homeRef = ($HomeInterface$) initContext.lookup(JNDIName);
	            ejbRemote = ($RemoteInterface$) homeRef.create();  
					$if(ReturnTypeNotVoid)$ return $endif$ ejbRemote.$OperationName$($OperationParamNames$);
				$if(ReturnTypeResponse)$				
				ResponseBuilder builder = Response.status(Status.OK);
				builder.type("application/xml");
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<response>");
				buffer.append("<type>MESSAGE</type>");
				buffer.append("<code>ADD</code>");
				buffer.append("<resource>$ResourceName$</resource>");
				buffer.append("<path>PUT</path>");
				buffer.append("<message>Successfully Added $ResourceName$</message>");
				buffer.append("</response>");
				builder.entity(buffer.toString());
				return builder.build();
				 $endif$
					
	        }  
	        $ResourceException; separator="\n"$
	        catch(javax.ejb.CreateException e) {
	        	e.printStackTrace();
				ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
				builder.type("application/xml");
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<response>");
				buffer.append("<type>ERROR</type>");
				buffer.append("<code>GET_EJB_LOCAL_1</code>");
				buffer.append("<resource>$ResourceName$</resource>");
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
				buffer.append("<resource>$ResourceName$</resource>");
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
				buffer.append("<resource>$ResourceName$</resource>");
				buffer.append("<path>GET</path>");
				buffer.append("<message>Failed to create local client for EJB: "+ e.getMessage() + "</message>");
				buffer.append("</response>");
				builder.entity(buffer.toString());
				throw new WebApplicationException(builder.build());
	        } 
	
		}
		$ResourceException; separator="\n"$
		catch(WebApplicationException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
