	@POST
	@Consumes("application/xml")
	public $ReturnType$ add$ResourceName$($RequestType$)
	{
		try
		{
			if(param0 == null)
			{			
				ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
				builder.type("application/xml");
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				buffer.append("<response>");
				buffer.append("<type>ERROR</type>");
				buffer.append("<code>PUT_1</code>");
				buffer.append("<resource>$ResourceName$</resource>");
				buffer.append("<path>PUT</path>");
				buffer.append("<message>Object is missing to create</message>");
				buffer.append("</response>");
				builder.entity(buffer.toString());
				throw new WebApplicationException(builder.build());
			}

			gov.nih.nci.restgen.generated.client.$ServiceName$ service = new gov.nih.nci.restgen.generated.client.$ServiceName$();
			gov.nih.nci.restgen.generated.client.$PortType$ port = service.get$PortName$();
			port.$OperationName$(param0);
		}
		catch(WebApplicationException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
