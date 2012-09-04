<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="gov.nih.nci.system.web.util.JSPUtils,
				 gov.nih.nci.system.web.util.HtmlUtils,
				 java.lang.reflect.*,
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
											Create
											</td>
										</tr>
										<tr>
										<td border=0 class="txtHighlight" align="center" height="1%">
										<%String message2 = (String)request.getAttribute("message");
										if(message2 != null){
										%>
										<%=message2%><br>
												<input type="button" name="Close" value="Close" class="actionButton" onClick="javascript:window.close()">
										
										<%}
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
String className = (String)request.getAttribute("klassName");
String validationMessage = (String)request.getAttribute("validationmessage");
System.out.println("className: " + className);
//session.setAttribute("selectedDomain", className);

if(className != null)
{
	try
	{	
		jspUtils = JSPUtils.getJSPUtils(config.getServletContext());
		fieldNames = jspUtils.getSearchableFields(className);
		domainNames = jspUtils.getAssociations(className);
		
	}
	catch(Exception ex){
		message=ex.getMessage();
	}
	
	//out.println("fieldNames:  " + fieldNames);
	if(fieldNames != null && fieldNames.size() > 0)
	{
%>
<form method="post" action="Create.action" name="Create" id="Create">
	<table summary="" cellpadding="3" cellspacing="0" border="0" align="center">
		<tr>
			<td class="formTitle" height="20" colspan="3"><a target="_blank" href="docs/<s:property value="javaDocsClassName" />"><s:property value="fullyQualClassName" /></a></td>
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
	   	
	   			//System.out.println("Field Type: "+((Field)fieldNames.get(i)).getType().getName());
	   			//System.out.println("Field Generic Type: "+((Field)fieldNames.get(i)).getGenericType());
	   	
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
				<%=HtmlUtils.getHtmlFor(attrName,attrTypeClassName)%>
			</td>
		</tr>



		  <%}%>


			<% if(domainNames != null && domainNames.size() > 0)
			   { 
			   %>
		
			   		<%for(int i=0; i<domainNames.size(); i++)
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
			   		String idName = jspUtils.getClassIdName(asscClass);
			   		String labelName = asscClass + "."+idName;
			   		if(asscRole != null)
			   			labelName = asscRole+"("+labelName+")";
			   			
			   		String idType = jspUtils.getReturnType(asscClass, idName, true);
			   		String filedName = labelName.replace(".", "-");
			   		%>
		<tr align="left" valign="top">
			<td class="formRequiredNotice" width="5px">&nbsp;</td>
			<td class="formLabel" align="right"><label for="<%=labelName%>"><%=labelName%>:</label></td>
			<td class="formField" width="90%">
				<%=HtmlUtils.getHtmlFor(filedName,idType)%>
			</td>
		</tr>			
			<%}
			
			}// end if(domainNames != null) statement%>			   
		
		
			   
		
		
		<tr>
			<td align="left" colspan="3">
				<!-- action buttons begins -->
				<table cellpadding="4" cellspacing="0" border="0">
					<tr align="left">
						<td align="left"><input type="submit" name="BtnSearch" class="actionButton" value="Submit" ></td>
						<td align="left"><input class="actionButton" type="reset" value="Reset"></td> 
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
			<td align="left"><%=validationMessage%></td>
		</tr>
	</table>

<%
}
%>
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
