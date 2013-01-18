<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="gov.nih.nci.system.web.util.JSPUtils,
				 gov.nih.nci.system.web.util.HtmlUtils,
				 java.lang.reflect.*,
				 gov.nih.nci.sdk.rest.SDKCascadeCache,
				 java.util.*" %> 
			 
<link href="styleSheet.css" type="text/css" rel="stylesheet" />
<html>
<head>
<title>Result Data Table</title>
<link rel="stylesheet" type="text/css" href="styleSheet.css" />

</head>
<body>
<form method="post" action="LinkResult.action" name="LinkResult" id="LinkResult">
<input type="hidden" name="linkHref"/>
<input type="hidden" name="targetClass"/>
</form>

<table summary="" cellpadding="0" cellspacing="0" border="0"
	width="100%" height="100%">

	<!-- nci hdr begins -->
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="hdrBG">
				<tr>
					<td width="283" height="37" align="left">
						<a href="http://www.cancer.gov">
							<img alt="National Cancer Institute" src="images/logotype.gif"
								width="283" height="37" border="0" />
						</a>
					</td>
					<td>&nbsp;
					</td>
					<td width="295" height="37" align="right">
						<a href="http://www.cancer.gov">
							<img alt="U.S. National Institutes of Health | www.cancer.gov"
								src="images/tagline.gif" width="295" height="37" border="0" />
						</a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<!-- nci hdr ends -->

	<tr>
		<td height="100%" align="center" valign="top">
			<table summary="" cellpadding="0" cellspacing="0" border="0"
				height="100%" width="771">
				<!-- application hdr begins -->
				<tr>
					<td height="50">
						<table width="100%" height="50" border="0" cellspacing="0"
							cellpadding="0" class="subhdrBG">
							<tr>
								<td height="50" align="left">
									<a href="#">
										<img src="images/sdkLogoSmall.gif" alt="Application Logo"
											hspace="10" border="0" />
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!-- application hdr ends -->
<%
JSPUtils jspUtils= null;
List domainNames=new ArrayList();
String message = null;
String className = request.getParameter("target");
String success = (String)request.getAttribute("successful");
				
	try
	{	
		jspUtils = JSPUtils.getJSPUtils(config.getServletContext());
		domainNames = jspUtils.getAssociations(className);
	}
	catch(Exception ex){
		message=ex.getMessage();
	}
%>

				<tr>
					<td valign="top">
						<table summary="" cellpadding="0" cellspacing="0"
							border="0" bordercolor="red" height="100%" width="100%"
							class="contentPage">

							<!--_____ main content begins _____ -->

							<tr>
								<td valign="top">
									<form method="post" action="Delete.action" name="Delete" id="Delete">
									<table cellpadding="0" cellspacing="0" border="0"
										bordercolor="blue" class="contentBegins" height="100%"
										width="100%">
										<tr>
											<td border=0 class="h2" nowrap="off">
											Delete
											</td>
										</tr>
										<tr>
										<td border=0 class="txtHighlight" align="center">
										<%message = (String)request.getAttribute("message");
										if(message == null)
											message="&nbsp;";
										%>
										<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(message)%>
										</td>
										</tr>
			<% if(domainNames != null && domainNames.size() > 0)
			   { 
			   		for(int i=0; i<domainNames.size(); i++)
			   		{
						String asscName = (String)domainNames.get(i);
						String asscRole = null;
						String asscClass = asscName;
						if(asscName.indexOf("(") != -1)
						{
							asscClass = asscName.substring(asscName.indexOf("(")+1, asscName.lastIndexOf(")"));
							asscRole = asscName.substring(0, asscName.indexOf("("));
						}

						if(asscClass.equals("Please choose") || asscClass.equals(className))
							continue;

						boolean displayNote = false;
						String note = "";
						String deleteType = SDKCascadeCache.getInstance().getDeleteAssociation(className, asscRole);
	   		%>
								<tr>
								<td border=0 class="txtHighlight" align="center" nowrap="off">
								<%
								if(success == null && deleteType != null && (deleteType.equalsIgnoreCase("delete") || deleteType.equalsIgnoreCase("all")))
								{
								%>
								All references will be deleted to <%=asscRole%>!!
								<%
								} 
								else if(success == null && deleteType != null && deleteType.equalsIgnoreCase("all-delete-orphan"))
								{
								%>
								 All children that will orphaned by the deletion of the parent will be deleted to <%=asscRole%>!!
								<%
								} else if(success == null && deleteType != null && deleteType.equalsIgnoreCase("delete-orphan"))
								{
								%>
								 Selected orphaned children will be deleted to <%=asscRole%>!!
								<%}%>
								</td>
								</tr>
			<%
					}
				}
			%>
										<tr>
											<td valign="top"  align="center">
											<table border="0" bordercolor="orange" summary="" cellpadding="0" cellspacing="0">
											<tr>
											<td class="dataTablePrimaryLabel" height="20" align="left">
											Criteria: 
											<br />
											<%
											Enumeration enums = request.getParameterNames();
											while(enums.hasMoreElements())
											{
											String name = (String)enums.nextElement();
											if(name.equals("confirm") || name.equals("submit"))
												continue;
											%>
											<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(name)%> = <%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(request.getParameter(name))%><br>
											<input type="hidden" name="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(name)%>" value="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(request.getParameter(name))%>">
											<%
											}
											%>
											</td>
											</tr>
											<%
											if(success == null)
											{
											%>
											<tr>
											<td class="txtHighlight" nowrap="off">
											Are you sure you want to delete?
											<br>
											
											<input type="submit" name="submit" value="Yes" class="actionButton" >
											<input type="button" name="close" value="No" class="actionButton"  onClick="javascript:window.close()">
											<input type="hidden" name="confirm" value="true">
											
											
											</td>
											</tr>
											<%}
											else
											{%>
											<tr>
											<td class="dataCellText" nowrap="off">
											<input type="button" name="close" value="Close" class="actionButton" onClick="javascript:window.close()">
											</td>
											</tr>
											<%}%>
											</td>
											</form>
										</tr>
													<!-- paging ends -->

										<!-- Insert details block here if needed -->
									</table>
								</td>
							</tr>
						</table>

					</td>
				</tr>
							<!--_____ main content ends _____ -->

							<tr>
								<td height="20" width="100%" class="footerMenu">
&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>

			<!-- footer begins -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="ftrTable">
				<tr>
					<td valign="top">
						<div align="center">
							<a href="http://www.cancer.gov/">
								<img src="images/footer_nci.gif" width="63" height="31"
									alt="National Cancer Institute" border="0" />
							</a>
							<a href="http://www.dhhs.gov/">
								<img src="images/footer_hhs.gif" width="39" height="31"
									alt="Department of Health and Human Services" border="0" />
							</a>
							<a href="http://www.nih.gov/">
								<img src="images/footer_nih.gif" width="46" height="31"
									alt="National Institutes of Health" border="0" />
							</a>
							<a href="http://www.firstgov.gov/">
								<img src="images/footer_firstgov.gif" width="91" height="31"
									alt="FirstGov.gov" border="0" />
							</a>
						</div>
					</td>
				</tr>
			</table>
			<!-- footer ends -->

		</td>
	</tr>
</table>
</body>
</html>
