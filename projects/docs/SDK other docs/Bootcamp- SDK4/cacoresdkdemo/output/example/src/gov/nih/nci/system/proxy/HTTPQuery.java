package gov.nih.nci.system.proxy;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.swing.text.html.HTMLDocument;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.log4j.Logger;
import java.util.*;
import java.net.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import gov.nih.nci.common.util.*;
import gov.nih.nci.system.server.mgmt.SecurityEnabler;
import gov.nih.nci.system.webservice.*;
import gov.nih.nci.common.util.*;

import org.apache.log4j.Logger;
import org.dom4j.dom.DOMDocument;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import gov.nih.nci.system.applicationservice.*;

/**
 * <!-- LICENSE_TEXT_START -->
 */
/**
 * @author Shaziya Muhsin
 * @version 3.1
 */


/**
 * The HTTPQuery servlet interpretes a query request and makes appropriate calls to the caCORE Server.  
 * The results are sent back to the user as an XML or HTML document based on the type of request made.  
 * XQuery like syntax is used to generate the query.
 * Syntax:
 * http://server:port/servlet/queryType?query=targetClassName&criteriaClassName[@attribute=value][association[@attribute=value]]
 * Please refer to the cacore documentation for more information on generating a query request.
 */
public class HTTPQuery extends HttpServlet{

    private static Logger log= Logger.getLogger(HTTPQuery.class.getName());
    private static Properties properties = new Properties();
    private String cacoreStyleSheet;
    private int pageSize = 1000;
    private SecurityEnabler securityEnabler = new SecurityEnabler(SecurityConfiguration.getApplicationName());
    /**
     * Initialize the servlet
     */
    public void init(ServletConfig config)
    throws ServletException
  {
     super.init(config);  
     ServletContext context = config.getServletContext();
     String beanFiles = context.getInitParameter ("cacoreBeans.Properties");     
     cacoreStyleSheet = context.getInitParameter ("cacoreFormat.xsl");
     try{
         String pageCount = context.getInitParameter("rowCounter");
         if( pageCount != null){
             pageSize = Integer.parseInt(pageCount);
         }
     }
     catch(Exception ex){
    	 log.error("Exception: ", ex);
     }
     
     try{
         loadProperties(beanFiles);         
     }catch (Exception ex){
    	 log.error("Exception: ", ex);
         throw new ServletException(ex.getMessage());
     }
  }
    
  
    /**
     * Handls Post requests
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }
    /**
     * Unload servlet
     */
    public void destroy(){
        super.destroy();
    }
    
    /**
     * Handls Get requests
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        ServletOutputStream out = response.getOutputStream();
        out = response.getOutputStream();
        
        int resultCounter = 1000;
        int startIndex =0 ;
        Object[] resultSet = null;
        HashMap resultMap = new HashMap();        
        int pageNumber = 1;
        String packageName = null;
        try{
            String query = null;
           
            if(URLDecoder.decode(request.getQueryString(),"ISO-8859-1")!= null){
                query = URLDecoder.decode(request.getQueryString(),"ISO-8859-1");
            }
            else{
                throw new Exception("Query not defined"+ getQuerySyntax());
            }
            if (query.indexOf("&username") > 0 )
            	query = query.substring(0,query.indexOf("&username"));
            
            HTTPUtils httpUtils = new HTTPUtils(); 
            if(properties != null){
                httpUtils.setProperties(properties);
            }    
            String queryType = httpUtils.getQueryType(request.getRequestURL().toString());            
            validateQuery(query);             
            httpUtils.setQueryArguments(query);
           
            if(httpUtils.getPageNumber()!= null){
                pageNumber = Integer.parseInt(httpUtils.getPageNumber());
            } 
            httpUtils.setServletName(request.getRequestURL().toString());
            
            if(httpUtils.getPageSize() != null){
                pageSize = Integer.parseInt(httpUtils.getPageSize());           
            }
            else{
                httpUtils.setPageSize(pageSize);
            }
            if(httpUtils.getStartIndex()!=null || !httpUtils.getStartIndex().equals("0")){
                startIndex = Integer.parseInt(httpUtils.getStartIndex());
            }
           
            
            boolean match = false;
            
            HttpSession session = request.getSession();
            HTTPUtils prop = (HTTPUtils)session.getAttribute("properties");
            
			// The following lines causes results to be cached, which can cause a query
			// to be out of sync with the database if the database is modified directly
			// (external to the application)
//          if(!session.isNew() && prop != null){
//             match = httpUtils.getMatch(prop);                
//          }
            
         
            try{ 
                if(match){
                    List results = prop.getResults();
                    httpUtils.setResults(results);        
                    resultSet = httpUtils.getCachedResultSet();
                    
                }
                else{
                    resultSet = httpUtils.getResultSet();
                }
                 
                                    
            }catch(Exception ex){
                log.error("Get ResultSet Exception: " + ex.getMessage());
                throw ex;
            }
           try{
                             
                session.setAttribute("properties",httpUtils);
                XMLOutputter xout = new XMLOutputter();
                org.jdom.Document domDoc = httpUtils.getXMLDocument(resultSet, pageNumber);
                    
                if(queryType.endsWith("XML")){
                    response.setContentType("text/xml");                
                    xout.output(domDoc,out);                  
                }
                else{
                    response.setContentType("text/html");            
                    Document htmlDoc = null;
                    if(httpUtils.getTargetPackageName()!= null){
                     
                    	if(cacoreStyleSheet != null){                          
                            htmlDoc = getHTMLDocument(domDoc, cacoreStyleSheet);
                        }
                         
                          if(htmlDoc != null){                          
                                xout = new XMLOutputter();
                                xout.output(htmlDoc,out);              
                            }
                            else{                           
                                httpUtils.printResults(response);
                            }
                        }
                    }

            }catch(Exception ex){
                log.error("Print Results Exception: " + ex.getMessage()); 
                throw ex;
                }
            if (securityEnabler.getSecurityLevel() > 0)
            {
            	session = request.getSession(true);
            	session.setAttribute(Constant.USER_NAME,ClientInfoThreadVariable.getUserName());
            }
        }catch(Exception ex){
            log.error("Exception: ", ex);
            String msg = "<font size=6><b>caCORE HTTP Servlet Error:<hr></font>";

            out.println(msg);
            
    		out.println("<pre class=\"autoOverflow\">");
    	    out.println("<br><br><font size=4 color=red>");        		
    		
    		msg = ex.getMessage();
    		Throwable tempEx = ex.getCause();
    		while (tempEx != null) {
    			msg += "<br><br>Caused by: " + tempEx.getMessage(); 
    			tempEx = tempEx.getCause();
    		}
    		
    		out.println(msg);

    	    out.println("</b></font><br><br>");
    	    
    	    log.debug("CoreSystemProperty.DEBUG_STACKTRACE: " + CoreSystemProperty.DEBUG_STACKTRACE);
    	    
    	    if (CoreSystemProperty.DEBUG_STACKTRACE){
	    	    out.println("<br><br><font size=4 color=black>");
	    	    out.println("########################  BEGIN StackTrace Debugging #########################");
	    	    out.println("<br><br>");
	
	    	    ByteArrayOutputStream ostr = new ByteArrayOutputStream();
	    	    ex.printStackTrace(new PrintStream(ostr));
	    	    out.println(ostr.toString());
	    	    
	    	    out.println("</font>"); 
    	    }
    	    out.println("</pre>");        	    
        	    
        }
        
            }


    /**
     * Loads Class and Package information based on the UML Model
     * @param file1 Specifies Property file
     * @param file2 Specifies property file
     * @throws Exception
     */
    private void loadProperties(String files) throws Exception{
        
        List fileList = new ArrayList();
        fileList = this.getFileList(files);
        String fileName = null;
        for(int i=0; i<fileList.size(); i++){
            try{
                fileName = (String)fileList.get(i);
                if(fileName != null || fileName.length()>0){
                    properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
                }                
            }catch(Exception ex){
                log.error("Cannot locate file - "+ fileName + "\t"+ ex.getMessage());                
            }
        }
        
        
        
       
    }
    
    /**
     * Returns a list of fileNames
     * @param files
     * @return
     */
    public List getFileList(String files){
        List fileList = new ArrayList();
        
        if(files.indexOf(Constant.SEMICOLON)>0){
            StringTokenizer st = new StringTokenizer(files, ";");
            while(st.hasMoreTokens()){
                fileList.add(st.nextToken());
            }            
        }
        else if(files.indexOf(Constant.COMMA)>0){
            StringTokenizer st = new StringTokenizer(files, ",");
            while(st.hasMoreTokens()){
                fileList.add(st.nextToken());
            }
        }
        else{
            fileList.add(files);
        }
        
        return fileList;
    }
   
   /**
    * Generates an HTML Document for a given XML document with the given stylesheet specification
    * @param doc Specifies the XML document
    * @param styleSheet Specifies the stylesheet
    * @return
    * @throws Exception
    */ 
    public Document getHTMLDocument(Document doc, String styleSheet)throws Exception{
        Document htmlDoc = null;
        
        try{
            InputStream styleIn = Thread.currentThread().getContextClassLoader().getResourceAsStream(styleSheet);
            if(styleIn != null){
                htmlDoc = XSLTTransformer(doc, styleIn);                
            }
                                    
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new ServletException(ex.getMessage());
            }        
        return htmlDoc;
    }
    
    /**
     * Generates an XML or HTML document based on a given stylesheet
     * @param xmlDoc Specifies the xml document
     * @param styleIn specifies the stylesheet
     * @return
     * @throws Exception 
     */
    
    public Document XSLTTransformer(Document xmlDoc, InputStream styleIn) throws Exception{                 
        JDOMSource source = new JDOMSource(xmlDoc);        
        JDOMResult result = new JDOMResult();        
        try{
            if(styleIn != null){
                TransformerFactory tFactory = TransformerFactory.newInstance();       
                Templates stylesheet = tFactory.newTemplates(new StreamSource(styleIn));
                Transformer processor = stylesheet.newTransformer();
                processor.transform(source, result);            
            }
            
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new Exception("XSLTTransformer Exception: "+ex.getMessage());
        }        
            return result.getDocument();
        }
    /**
     * This method returns true if the query syntax is valid
     * @param query Specifies the http query 
     * @return return 
     */
    private boolean validateQuery(String query) throws Exception{
           boolean valid = true;
           try{
               if(query.indexOf("query")<0){
                   valid = false;
               }else if(query.endsWith("query=")){                   
                   valid = false;
               }else if(query.endsWith("query")){                   
                   valid = false;
               }  
               else if(query.endsWith("&") || query.endsWith("[")){                   
                   valid = false;
               }
               
           }catch(Exception ex){
               valid = false;               
           }
           if(valid){
               int startCounter =0;
               int startIndex =0;
               int endCounter =0;
               for(int i=0; i<query.length(); i++){
                   if(query.charAt(i)==Constant.LEFT_BRACKET){
                       startCounter++;
                   }
                   else if(query.charAt(i)==Constant.RIGHT_BRACKET){
                       endCounter++;
                   }
               }
               if(startCounter != endCounter){
                   throw new Exception("Invalid format: '[' parenthesis does not match number of ']' parenthesis");
               }
           }
           else{
               throw new Exception("Invalid Syntax: "+ query + getQuerySyntax() );
           }
            return valid;
    }
    
    /**
     * Returns the query syntax
     * @return
     */
    private String getQuerySyntax(){
        String syntax = "<br><br><font color=black size=4><B>Syntax: </B><br>" +
                "<font color=purple>query=</font>TargetClassName" +
                "<font color=purple>&</font>CriteriaClassName" +
                "<font color=purple>[@</font>attribute" +
                "<font color=purple>=</font>value" +
                "<font color=purple>][</font>association" +
                "<font color=purple>[@</font>attribute" +
                "<font color=purple>=</font>value" +
                "<font color=purple>]]<font color=purple></font>";
        return syntax;
                
    }
  
}
