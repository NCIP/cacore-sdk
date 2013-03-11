	@GET
	$PathParamPath$
	@Produces("application/xml")
	public $ReturnType$ get$MethodName$($PathParam$ @Context UriInfo uriInfo)
	{
		try
		{
			List<PathSegment> pathSegments = uriInfo.getPathSegments();
			Map matrixParams = pathSegments.get(1).getMatrixParameters();
			String[] paramNames = {$ParamNames$};
			//validateCriteria("$ResourceName$", matrixParams, paramNames);

			gov.nih.nci.restgen.generated.client.$ServiceName$ service = new gov.nih.nci.restgen.generated.client.$ServiceName$();
			gov.nih.nci.restgen.generated.client.$PortType$ port = service.get$PortName$();
			
			$if(ReturnTypePrimitive)$
			String resultType = "$ReturnType$";
			if(resultType.startsWith("java.lang"))
			{
				StringBuffer buffer = new StringBuffer();
				buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
				buffer.append("<result>");
				buffer.append(result.toString());
				buffer.append("</result>");
				return buffer.toString();
			}
			$endif$
			$if(!ReturnTypePrimitive)$
				return port.$OperationName$($OperationParameters$);
			$endif$			
		}
		$ResourceException; separator="\n"$
		catch(WebApplicationException e)
		{
			e.printStackTrace();
			throw e;
		}
	}