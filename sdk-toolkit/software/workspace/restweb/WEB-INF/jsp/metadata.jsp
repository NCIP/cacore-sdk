<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
L--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="gov.nih.nci.system.web.util.JSPUtils,
				 gov.nih.nci.system.web.util.HtmlUtils,
				 gov.nih.nci.system.metadata.*,
				 java.lang.reflect.*,
				 java.util.*" %> 
			 
<link href="styleSheet.css" type="text/css" rel="stylesheet" />
<html>
<head>
<title>Result Data Table</title>
<link rel="stylesheet" type="text/css" href="styleSheet.css" />
</head>
<body>

<table border="0" bordercolor="orange" summary="" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<tr>
			<th class="dataTableHeader" scope="col" align="center">Attribute</th>
			<th class="dataTableHeader" scope="col" align="center">Value</th>
		</tr>
		
		<%
		String context = request.getParameter("context");
		String klass = request.getParameter("target");
		String attribute = request.getParameter("attribute");
		
		if(context != null && klass != null && attribute != null)
		{
			MetadataElement mdata = MetadataCache.getInstance().getMetadata(context, klass, attribute);	
			if(mdata != null)
			{
				Map mAttributes = mdata.getAttributes();
				Iterator iter = mAttributes.keySet().iterator();
				while(iter.hasNext())
				{
				String key = (String) iter.next();
				String value = (String)mAttributes.get(key);
			%>
		<tr>
			<td class="dataCellText" nowrap="off"><%=key%></td>
			<td class="dataCellText" nowrap="off"><%=value%></td>
		</tr>
		<%
				}
			}
		}
		%>

</table>
</body>
</html>
