package gov.nih.nci.common.util;

/**
  * <!-- LICENSE_TEXT_START -->
* Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute, 
* and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105. 
* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met: 
* 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions 
* in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other 
* materials provided with the distribution. 
* 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
* "This product includes software developed by the SAIC and the National Cancer Institute."
* If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself, 
* wherever such third-party acknowledgments normally appear. 
* 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software. 
* 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize 
* the recipient to use any trademarks owned by either NCI or SAIC-Frederick. 
* 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, 
* SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
  * <!-- LICENSE_TEXT_END -->
 */
/**
 * Constant assigns values to static fields 
 * @author caBIO Team 
 * @version 1.0
 */
public class Constant {
	
	public static final int ORM_DAO = 1;
	public static final int WEBSERVICE = 2;
	
	public static final String WILD_CARD_PATTERN="*";
	
	public static final char DOT='.';
	public static final char COMMA=',';
	public static final char SEMICOLON=';';	
	public static final char AMPERSAND='&';	
	public static final char AT='@';		
	public static final char LEFT_BRACKET='[';
	public static final char RIGHT_BRACKET=']';	
	public static final char EQUAL='=';
	public static final char FORWARD_SLASH='/';
	public static final char SPACE=' ';	
	public static final char QUESTION_MARK='?';
	public static final char GREATER_THAN='>';
	
	public static final String AMPERSAND_STR="&";
	public static final String FORWARD_SLASH_STR="/";
	public static final String COMMA_STR=",";
	public static final String BACK_SLASH="\\";	

	public static final String ORM_FILNAME_STARTS_WITH="orm"; 
	public static int ALIAS_ID = 1;
	public static String DELEGATE_NAME = "gov.nih.nci.system.delegator.BaseDelegate";
	public static String PACKAGE_NAME = "gov.nih.nci.cacore.domain";
	public static String serialVersionUID = "serialVersionUID";
    public static final String BIOCARTA_STRING = "BioCarta";   
    public static final String XLINK_URL = "http://www.w3.org/1999/xlink";
    
	public static final int BIDIRECTIONAL = 2;
	public static final int UNIDIRECTIONAL = 1;
	public static final int NO_ASSOCIATION = 0;
	public static final int MAX_RESULT_COUNT_PER_QUERY = 5000;
	public static final int RESULT_COUNT_PER_QUERY = 1000;
	
	public static final String REMOTE_SERVICE_FILE_NAME = "remoteService.xml";
	public static final String APPLICATION_SERVICE_FILE_NAME = "applicationService.xml";
	public static final String REMOTE_APPLICATION_SERVICE = "remoteService";
	public static final String APPLICATION_SERVICE = "applicationService";
	public static final String ORM1_DAO_FILE_NAME = "orm1Dao.xml";
	public static final String ORM1_DAO = "orm1";	
	
	public static final String CSM_SECURITY_LEVEL = ".CSM.Security.Level";
	public static final String CSM_SECURITY_SESSION_TIMEOUT = "CSM.Security.Session.Timeout";
	
	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
}
