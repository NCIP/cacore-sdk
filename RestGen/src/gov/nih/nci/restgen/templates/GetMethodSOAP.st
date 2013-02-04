	@GET
	@Path("$PathParamPath$")
	@Produces("application/xml")
	public $ReturnType$ get$MethodName$($PathParam$, @Context UriInfo uriInfo)
	{
		try
		{
			List<PathSegment> pathSegments = uriInfo.getPathSegments();
			Map matrixParams = pathSegments.get(1).getMatrixParameters();
			String[] paramNames = {$ParamNames$};
			//validateCriteria("$ResourceName$", matrixParams, paramNames);

			gov.nih.nci.restgen.generated.client.$ServiceName$ service = new gov.nih.nci.restgen.generated.client.$ServiceName$();
			gov.nih.nci.restgen.generated.client.$PortType$ port = service.get$PortName$();
			return port.$OperationName$($OperationParameters$);
		}
		$ResourceException; separator="\n"$
		catch(WebApplicationException e)
		{
			e.printStackTrace();
			throw e;
		}
	}