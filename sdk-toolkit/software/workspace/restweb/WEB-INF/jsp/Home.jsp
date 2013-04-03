<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter"%>
<%@ page
	import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.acegisecurity.AuthenticationException"%>
<%@ page import="org.acegisecurity.context.SecurityContextHolder"%>
<%@ page import="org.acegisecurity.userdetails.UserDetails"%>
<%@ page import="gov.nih.nci.system.web.util.JSPUtils"%>
<%
			String lastUserKey = (String) session
			.getAttribute(AuthenticationProcessingFilter.ACEGI_SECURITY_LAST_USERNAME_KEY);
	if (lastUserKey == null || lastUserKey.equalsIgnoreCase("null")) {
		lastUserKey = "";
	}
	//out.println("lastUserKey: " + lastUserKey);

	JSPUtils jspUtils= JSPUtils.getJSPUtils(config.getServletContext());
	boolean isSecurityEnabled = jspUtils.isSecurityEnabled();
	boolean passwordReset = false;

	boolean isAuthenticated = false;
	String userName = "";
	if (isSecurityEnabled){
		Object obj = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	
		//out.println("obj: " + obj);
		if (obj instanceof UserDetails) {
			userName = ((UserDetails) obj).getUsername();
		} else {
			userName = obj.toString();
		}
	
		if (userName != null
				&& !(userName.equalsIgnoreCase("anonymousUser"))) {
			isAuthenticated = true;
		}
	}
	//out.println("userName: " + userName);
	boolean webinterfaceDisabled=jspUtils.isWebInterfaceDisabled();
	
	String loginErrorStr = request.getParameter("login_error");
	//System.out.println("loginErrorStr "+loginErrorStr);
	String passwordResetError = (String)request.getAttribute("password_reset_error");
	//System.out.println("passwordResetError "+passwordResetError);
	String passwordResetSuccessful = (String)request.getAttribute("password_reset_successful"); 
	boolean isLoginError = false;
	boolean isPasswordResetError = false;
	boolean isPasswordResetSuccessful = false;
	if (loginErrorStr != null && loginErrorStr.length() > 0) {
		isLoginError = true;
	}
	if (passwordResetError != null && passwordResetError.length() > 0) {
		isPasswordResetError = true;
		passwordReset = true;
	}
	if (passwordResetSuccessful != null && passwordResetSuccessful.length() > 0) {
		isPasswordResetSuccessful = true;
	}


	//out.println("isLoginError2: " + isLoginError);
	
	AuthenticationException authException = (AuthenticationException)session.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY);
	//System.out.println("authException "+authException);
	//System.out.println("isLoginError2: " + isLoginError);
	if(authException != null)
	{
		if(authException instanceof org.acegisecurity.BadCredentialsException && isLoginError)
		{
			Throwable t = authException.getCause();
			//System.out.println("t: "+t);
			if(t instanceof gov.nih.nci.security.exceptions.CSCredentialExpiredException)
				passwordReset = true;
			else if(t instanceof gov.nih.nci.security.exceptions.CSFirstTimeLoginException)
				passwordReset = true;
		}else if(authException instanceof org.acegisecurity.AccountExpiredException && isLoginError)
			passwordReset = true;
		
	}	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title><s:text name="home.title" />
		</title>
		<link rel="stylesheet" type="text/css" href="styleSheet.css" />
		<script src="script.js" type="text/javascript"></script>
	</head>
	<body>
		<table summary="" cellpadding="0" cellspacing="0" border="0"
			width="100%" height="100%">

			<%@ include file="include/header.inc"%>

			<tr>
				<td height="100%" align="center" valign="top">
					<table summary="" cellpadding="0" cellspacing="0" border="0"
						height="100%" width="771">

						<%@ include file="include/applicationHeader.inc"%>

						<tr>
							<td valign="top">
								<table summary="" cellpadding="0" cellspacing="0" border="0"
									height="100%" width="100%">
									<tr>
										<td height="20" class="mainMenu">

											<%@ include file="include/mainMenu.inc"%>

										</td>
									</tr>

									<!--_____ main content begins _____-->
									<tr>
										<td valign="top">
											<!-- target of anchor to skip menus -->
											<a name="content" />
												<table summary="" cellpadding="0" cellspacing="0" border="0"
													class="contentPage" width="100%" height="100%">

													<!-- banner begins -->
													<tr>
														<td class="bannerHome">
															<img src="images/bannerHome.gif" alt="caCORE SDK Logo">
														</td>
													</tr>
													<!-- banner begins -->

													<tr>
														<td height="100%">

															<!-- target of anchor to skip menus -->
															<a name="content" />

																<table summary="" cellpadding="0" cellspacing="0"
																	border="0" height="100%">
																	<tr>
																		<td width="70%">

																			<!-- welcome begins -->
																			<table summary="" cellpadding="0" cellspacing="0"
																				border="0" height="100%">
																				<tr>
																					<td class="welcomeTitle" height="20">
																						<s:text name="home.welcome.title" />
																					</td>
																				</tr>
																				<tr>
																					<td class="welcomeContent" valign="top">
																						<%@ include file="include/welcomeContent.inc"%>
																					</td>
																				</tr>
																			</table>
																			<!-- welcome ends -->

																		</td>
																		<td valign="top" width="30%">

																			<!-- sidebar begins -->
																			<table summary="" cellpadding="0" cellspacing="0"
																				border="0" height="100%">

																				<!-- login/continue form begins -->
																				<tr>
																					<td valign="top">
																						<%
																						if (webinterfaceDisabled) {
																						%>
																						<table summary="" cellpadding="2" cellspacing="0"
																							border="0" width="100%" class="sidebarSection">
																							<tr>
																								<td class="sidebarTitle" height="20">
																									SELECT CRITERIA
																								</td>
																							</tr>
																							<tr>
																								<td class="sidebarContent" align="center">
																									WebInterface is Disabled
																								</td>
																							</tr>
																						</table>
																						<%
																						} else if (isSecurityEnabled && !isAuthenticated) {
																						%>

																						<table summary="" cellpadding="2" cellspacing="0"
																							border="0" width="100%" class="sidebarSection">
																							<tr>
																								<td class="sidebarTitle" height="20">
																									<s:text name="home.login" />
																								</td>
																							</tr>
																							<%if(!passwordReset)
																							{%>
																							<tr>
																								<td class="sidebarContent">
																									<s:form method="post"
																										action="j_acegi_security_check"
																										name="loginForm" theme="css_xhtml">
																										<table cellpadding="2" cellspacing="0"
																											border="0">
																											<%
																											if (isPasswordResetSuccessful) {
																											%>
																											<tr>
																												<td class="sidebarLogin" align="left"
																													colspan="2">
																													<font color="red"> Your password is reset. Please try login with your new password.
																														</font>
																												</td>
																											</tr>
																											<%
																											}
																											%>
																											
																											<%
																											if (isLoginError) {
																											%>
																											<tr>
																												<td class="sidebarLogin" align="left"
																													colspan="2">
																													<font color="red"> Your login
																														attempt was not successful; please try
																														again.<BR> <BR> Reason: <%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(((AuthenticationException) session
												.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY))
												.getMessage())%> <BR> <BR> </font>
																												</td>
																											</tr>
																											<%
																											}
																											%>

																											<tr>
																												<td class="sidebarLogin" align="left">
																													<s:text name="home.loginID" />
																												</td>
																												<td class="formFieldLogin">
																													<s:textfield name="j_username"
																														value="%{lastUserKey}"
																														cssClass="formField" size="14" />
																												</td>
																											</tr>
																											<tr>
																												<td class="sidebarLogin" align="left">
																													<s:text name="home.password" />
																												</td>
																												<td class="formFieldLogin">
																													<s:password name="j_password"
																														cssClass="formField" size="14" />
																												</td>
																											</tr>
																											<tr>
																												<td>
																													&nbsp;
																												</td>
																												<td>
																													<s:submit cssClass="actionButton"
																														type="submit" value="Login" />
																												</td>
																											</tr>
																										</table>
																									</s:form>
																								</td>
																							</tr>
																							<%}else{%>
						<tr>
																								<td class="sidebarContent">
																									<s:form method="post"
																										action="reset"
																										name="loginForm2" theme="css_xhtml">
																										<table cellpadding="2" cellspacing="0"
																											border="0">
																											<%
																											if (isLoginError) {
																											%>
																											<tr>
																												<td class="sidebarLogin" align="left"
																													colspan="2">
																													<font color="red"> Your login
																														attempt was not successful; please reset your password.
																														<BR> <BR> Reason: <%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(((AuthenticationException) session
																														.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY))
																														.getMessage())%> <BR> <BR> </font>
																												</td>
																											</tr>
																											<%
																											}
																											%>
																											<%
																											if (isPasswordResetError) {
																											%>
																											<tr>
																												<td class="sidebarLogin" align="left"
																													colspan="2">
																													<font color="red"> Your 
																														attempt to reset password was not successful; please try
																														again.<BR> <BR> Reason: <%=org.apache.commons.lang.StringEscapeUtils.escapeHtml(passwordResetError)%> <BR> <BR> </font>
																												</td>
																											</tr>
																											<%
																											}
																											%>

																											<tr>
																												<td class="sidebarLogin" align="left">
																													<s:text name="home.loginID" />
																												</td>
																												<td class="formFieldLogin">
																													<s:textfield name="userName"
																														value="%{lastUserKey}"
																														cssClass="formField" size="14" />
																												</td>
																											</tr>
																											<tr>
																												<td class="sidebarLogin" align="left">
																													<s:text name="oldPassword" />
																												</td>
																												<td class="formFieldLogin">
																													<s:password name="oldPassword"
																														cssClass="formField" size="14" />
																												</td>
																											</tr>
<tr>
																												<td class="sidebarLogin" align="left">
																													<s:text name="newPassword" />
																												</td>
																												<td class="formFieldLogin">
																													<s:password name="newPassword"
																														cssClass="formField" size="14" />
																												</td>
																											</tr><tr>
																												<td class="sidebarLogin" align="left">
																													<s:text name="repeatPassword" />
																												</td>
																												<td class="formFieldLogin">
																													<s:password name="repeatPassword"
																														cssClass="formField" size="14" />
																												</td>
																											</tr>																											<tr>
																												<td>
																													&nbsp;
																												</td>
																												<td>
																													<s:submit cssClass="actionButton"
																														type="submit" value="Submit" />
																												</td>
																											</tr>
																										</table>
																									</s:form>
																								</td>
																							</tr>																							
																							<%}%>
																						</table>
																						<%
																						} else {
																						%>

																						<table summary="" cellpadding="2" cellspacing="0"
																							border="0" width="100%" class="sidebarSection">
																							<tr>
																								<td class="sidebarTitle" height="20">
																									SELECT CRITERIA
																								</td>
																							</tr>
																							<tr>
																								<td class="sidebarContent" align="center">
																									<s:form method="post"
																										action="ShowDynamicTree.action"
																										name="continueForm" theme="simple">
																										<s:submit cssClass="actionButton"
																											value="Continue" theme="simple" />
																									</s:form>
																								</td>
																							</tr>
																						</table>
																						<%
																						}
																						%>
																					</td>
																				</tr>
																				<!-- login ends -->

																				<!-- what's new begins -->
																				<tr>
																					<td valign="top">
																						<table summary="" cellpadding="0" cellspacing="0"
																							border="0" width="100%" class="sidebarSection">
																							<tr>
																								<td class="sidebarTitle" height="20">
																									WHAT'S NEW
																								</td>
																							</tr>
																							<tr>
																								<td class="sidebarContent">
																									<ul>
																										<li>
																											508 Compliance
																										</li>

																										<li>
																											New User Interface
																										</li>
																										<li>
																											New hierarchical package/class browsing
																										</li>
																										<li>
																											New single-session authentication
																										</li>
																									</ul>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<!-- what's new ends -->

																				<!-- did you know? begins -->
																				<tr>
																					<td valign="top">
																						<table summary="" cellpadding="0" cellspacing="0"
																							border="0" width="100%" height="100%"
																							class="sidebarSection">
																							<tr>
																								<td class="sidebarTitle" height="20">
																									DID YOU KNOW?
																								</td>
																							</tr>
																							<tr>
																								<td class="sidebarContent" valign="top">
																									&nbsp;
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<!-- did you know? ends -->

																				<!-- spacer cell begins (keep for dynamic expanding) -->
																				<tr>
																					<td valign="top" height="100%">
																						<table summary="" cellpadding="0" cellspacing="0"
																							border="0" width="100%" height="100%"
																							class="sidebarSection">
																							<tr>
																								<td class="sidebarContent" valign="top">
																									&nbsp;
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<!-- spacer cell ends -->

																			</table>
																			<!-- sidebar ends -->

																		</td>
																	</tr>
																</table>
														</td>
													</tr>
												</table>
										</td>
									</tr>
									<!--_____ main content ends _____-->

									<tr>
										<td height="20" class="footerMenu">

											<!-- application ftr begins -->
											<%@ include file="include/applicationFooter.inc"%>
											<!-- application ftr ends -->

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

					<%@ include file="include/footer.inc"%>

				</td>
			</tr>
		</table>
	</body>
</html>