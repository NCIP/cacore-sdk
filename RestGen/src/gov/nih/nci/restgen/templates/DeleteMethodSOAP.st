	@DELETE
	@Path("$PathParamPath$")
	@Consumes("application/xml")
	public $ReturnType$ remove$ResourceName$($PathParam$)
	{
		try
		{
			gov.nih.nci.restgen.generated.client.$ServiceName$ service = new gov.nih.nci.restgen.generated.client.$ServiceName$();
			gov.nih.nci.restgen.generated.client.$PortType$ port = service.get$PortName$();
			port.$OperationName$($OperationParameters$);
		}
		catch(WebApplicationException e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	