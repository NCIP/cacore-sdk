<%--L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
L--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="gov.nih.nci.system.web.util.JSPUtils,
				 gov.nih.nci.system.web.util.HtmlUtils,
				 java.lang.reflect.*,
				 org.jdom.Element,
				 gov.nih.nci.system.web.util.RESTUtil,
				 org.jdom.Document,
				 org.apache.struts2.dispatcher.SessionMap,
				 gov.nih.nci.sdk.rest.SDKCascadeCache,
				 com.opensymphony.xwork2.ActionContext,
				 org.acegisecurity.Authentication,
				 java.util.*" %> 
			 
<html>
	<head>
		<title>Result Data Table</title>
		<link rel="stylesheet" type="text/css" href="styleSheet.css" />
		<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="jquery-ui-1.8.2.custom.min.js"></script>
		<script type="text/javascript" src="iso-21090-datatype.2.1.js"></script>
	</head>

			<body>
				<table summary="" cellpadding="0" cellspacing="0" border="0"
					width="100%" height="100%">
<form method="post" action="Update.action" name="Update" id="Update">

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
								<tr>
									<td valign="top">
										<table summary="" cellpadding="0" cellspacing="0"
											border="0" bordercolor="red" height="100%" width="100%"
											class="contentPage">
										<tr>
											<td border=0 class="h2" nowrap="off" height="1%">
											Update
											</td>
										</tr>
										<tr>
										<td border=0 class="txtHighlight" align="center" height="1%">&nbsp;
										<%String message2 = (String)request.getAttribute("message");
										if(message2 != null)
										{
										%>
										<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(message2)%>
										<%
										}
										%>
										</td>
										</tr>
														<tr>
															<td valign="top">
																<table border="0" bordercolor="orange" summary=""
																	cellpadding="0" cellspacing="0">

<%
JSPUtils jspUtils= null;
List fieldNames=new ArrayList();
List domainNames=new ArrayList();
String message=null, selectedSearchDomain=null;
String className = request.getParameter("target");
if(className == null)
	className = (String)request.getAttribute("target");

String idColStr = (String)request.getAttribute("idCol");

String idStr = request.getParameter(idColStr);
if(idStr == null)
	idStr = (String)request.getAttribute(idColStr);
	
String validationMessage = (String)request.getAttribute("validationmessage");
System.out.println("className: " + className);
System.out.println("idStr: " + idStr);

//session.setAttribute("selectedDomain", className);
org.jdom.Document jDoc = (org.jdom.Document)request.getAttribute("jDoc");
Element rootElement = null;
if(jDoc != null)
	rootElement = jDoc.getRootElement();
String classIdName = null;

if(className != null)
{
	try
	{	
		jspUtils = JSPUtils.getJSPUtils(config.getServletContext());
		fieldNames = jspUtils.getSearchableFields(className);
		domainNames = jspUtils.getAssociations(className);
		classIdName = jspUtils.getClassIdName(className);
		
	}
	catch(Exception ex){
		message=ex.getMessage();
	}
	
	//out.println("fieldNames:  " + fieldNames);
	if(fieldNames != null && fieldNames.size() > 0)
	{
%>
	<table summary="" cellpadding="3" cellspacing="0" border="0" align="center">
		<tr>
			<td class="formTitle" height="20" colspan="3"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(className)%></td>
		</tr>
<%
		if(fieldNames != null && fieldNames.size() > 0)
		{  
			String attrName;
			String attrNameLabel = "";
		   	String attrType;
		   	String attrGenericTypeClassName;
		   	String attrTypeClassName = "";
		   
		   	for(int i=0; i < fieldNames.size(); i++)
		   	{	
		   		attrName = ((Field)fieldNames.get(i)).getName();
	   			attrType = ((Field)fieldNames.get(i)).getType().getName();
	   			attrGenericTypeClassName = ((Field)fieldNames.get(i)).getGenericType().toString();
	   	
				int beginIndex = attrType.lastIndexOf('.');
				if (beginIndex > 0) {
					++beginIndex;
					attrTypeClassName =  attrType.substring(beginIndex).toUpperCase();
				}
			
			   		attrNameLabel = attrName;
%>


		<tr align="left" valign="top">
			<td class="formRequiredNotice" width="5px">&nbsp;</td>
			<td class="formLabel" align="right"><label for="<%=attrNameLabel%>"><%=attrNameLabel%>:</label></td>
			<td class="formField" width="90%">
			<input type="hidden" name="target" value="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(className)%>">
			<input type="hidden" name="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(idColStr)%>" value="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(idStr)%>">
			<%
			if(attrName.equals(classIdName))
			{
				
			%>
			<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(HtmlUtils.getAttributeValue(rootElement, attrName))%>
			<input type="hidden" name="<%=attrName%>" value="<%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(HtmlUtils.getAttributeValue(rootElement, attrName))%>">
			<%
			}
			else
			{
			%>
				<%=HtmlUtils.getHtmlFor(attrName,attrTypeClassName, HtmlUtils.getAttributeValue(rootElement, attrName))%>
			<%}%>
			</td>
		</tr>



		  <%}%>


			<% if(domainNames != null && domainNames.size() > 0)
			   { 
				SessionMap session1 = (SessionMap) ActionContext.getContext().get(
						ActionContext.SESSION.toString());
				org.acegisecurity.context.SecurityContext context = (org.acegisecurity.context.SecurityContext) session1
						.get("ACEGI_SECURITY_CONTEXT");
				String base64encodedUsernameAndPassword = null;
				if (context != null) {
					Authentication authentication = context.getAuthentication();
					// authentication.getCredentials();
					String userName = ((org.acegisecurity.userdetails.User) authentication
							.getPrincipal()).getUsername();
					String password = authentication.getCredentials()
							.toString();
					base64encodedUsernameAndPassword = new String(
							org.apache.commons.codec.binary.Base64.encodeBase64((userName + ":" + password)
									.getBytes()));
				}			   
			   %>
		
			   		<%for(int i=0; i<domainNames.size(); i++)
			   		{
			   		String asscName = (String)domainNames.get(i);
			   		String asscClass = asscName;
					String asscRole = null;			   		
			   		if(asscName.indexOf("(") != -1)
			   		{
			   			asscClass = asscName.substring(asscName.indexOf("(")+1, asscName.lastIndexOf(")"));
			   			asscRole = asscName.substring(0, asscName.indexOf("("));
			   		}
			   			
			   		if(asscClass.equals("Please choose") || (asscRole == null && asscClass.equals(className)))
			   			continue;
			   		String idName = jspUtils.getClassIdName(asscClass);
			   		if(idName == null || idName.trim().length() ==0)
			   			continue;
			   		String labelName = asscClass + "."+idName;
			   		if(asscRole != null)
			   			labelName = asscRole+"("+labelName+")";
			   		
			   		String filedName = labelName.replace(".", "-");
			   		String idType = jspUtils.getReturnType(asscClass, idName, true);
			   		
			   		if(SDKCascadeCache.getInstance().canSaveAssociation(className, asscRole))
			   		{
			   		%>
		<tr align="left" valign="top">
			<td class="formRequiredNotice" width="5px">&nbsp;</td>
			<td class="formLabel" align="right"><label for="<%=labelName%>"><%=labelName%>:</label></td>
			<td class="formField" width="90%">
				<%=HtmlUtils.getHtmlFor(filedName,idType, RESTUtil.getLinkIdValue(rootElement, asscRole, asscClass, idName, base64encodedUsernameAndPassword))%>
			</td>
		</tr>			
			<%}
			}
			
			}// end if(domainNames != null) statement%>			   
		
		</tr>
			   
		
		
		<tr>
			<td align="left" colspan="3">
				<!-- action buttons begins -->
				<table cellpadding="4" cellspacing="0" border="0">
					<tr align="left">
						<td align="left"><input type="submit" name="BtnSearch" class="actionButton" value="Submit" ></td>
<input type="hidden" name="submitForm" value="true">
						<td align="left"><input class="actionButton" type="reset" value="Reset"></td> 
<td align="left"><input type="button" class="actionButton" name="Close" value="Close" onClick="javascript:window.close()"></td>
					</tr>
				</table>
				<!-- action buttons end -->
			</td>	
		</tr>
	</table>
	<s:hidden name="selectedDomain" />
</form>	
<%		}
	}
	
}%> 

<%
if(validationMessage != null && validationMessage.length() > 0)
{
%>
	<table cellpadding="4" cellspacing="0" border="0">
		<tr align="left">
			<td align="left"><%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(validationMessage)%></td>
		</tr>
	</table>

<%
}
%>
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
