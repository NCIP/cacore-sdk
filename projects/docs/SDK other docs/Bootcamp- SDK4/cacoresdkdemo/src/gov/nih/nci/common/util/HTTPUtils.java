package gov.nih.nci.common.util;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jdom.Namespace;


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
 * HTTPUtils presents various methods to generate search criteria from xquery like syntax.
 * This class also provides functionality to generate XML result.
 *
 * @author Shaziya Muhsin
 * @version 1.1
 */

public class HTTPUtils implements Serializable{

    private static Logger log= Logger.getLogger(HTTPUtils.class.getName());
    private  Properties properties = new Properties();
    private String query;
    private String startIndex = "0";
    private String resultCounter = "1000";
    private String pageNumber;
    private String pageSize;
    private String criteria;
    private String targetClassName;
    private String servletName;
    private String targetPackageName;
    private List results = new ArrayList();
    private Namespace namespace = Namespace.getNamespace("xlink",Constant.XLINK_URL);


    /**
     * Sets property values
     * @param properties
     */
public void setProperties(Properties properties){
    this.properties = properties;
}

/**
 * Sets the http Servlet name
 * @param name
 */
public void setServletName(String name){
    servletName = name;
}
/**
 * Returns http servlet name
 * @return
 */
public String getServletName(){
    return servletName;
}
/**
 * Returns startIndex value
 * @return
 */
public String getStartIndex(){
    return startIndex;
}
/**
 * Sets the startIndex value
 * @param index
 */
public void setStartIndex(String index){
    startIndex = index;
}
/**
 * Returns a list of result objects
 * @return
 */
public List getResults(){
    return results;
}
/**
 * Sets the results value
 * @param resultList
 */
public void setResults(List resultList){
    results = resultList;
}

/**
 * Sets argument values based on a given queryTest
 * @param queryText - http query
 * @throws Exception
 */
public void setQueryArguments(String queryText) throws Exception {
	this.query = queryText;
    try{
        if (query != null && !"".equals(query.trim())) {
               if(query.indexOf(Constant.AMPERSAND)<0 && query.indexOf(Constant.EQUAL)>0){
                   if(query.indexOf(Constant.LEFT_BRACKET)>0){
                       String crit = query.substring(6);
                       query = query.substring(0,query.indexOf(Constant.LEFT_BRACKET))+ Constant.AMPERSAND + crit;
                   }
                   else{
                       query += Constant.AMPERSAND + query.substring(query.indexOf(Constant.EQUAL)+1);
                   }

               }
                StringTokenizer st = new StringTokenizer(query, Constant.AMPERSAND_STR);
                while (st.hasMoreTokens()) {
                    String param = st.nextToken();

                    if(param.startsWith("query")){
                        targetClassName = param.substring("query=".length());
                        }
                    else if(param.toLowerCase().startsWith("startindex")){
                        startIndex = param.substring("startIndex=".length());
                        }
                    else if(param.toLowerCase().startsWith("resultcounter")){
                        resultCounter = param.substring("resultCounter=".length());
                        }
                    else if(param.toLowerCase().startsWith("pagenumber=")){
                        pageNumber = param.substring("pageNumber=".length());
                        }
                    else if(param.toLowerCase().startsWith("pagesize=")){
                        pageSize = param.substring("pageSize=".length());
                        }
                    else if(param.toLowerCase().startsWith("page=")){
                        pageSize = param.substring("page=".length());
                        }

                    else{
                        if(criteria == null){
                            criteria = param;
                        }

                        }
                     String target = targetClassName;

                     if(target.indexOf(Constant.COMMA)>0){
                         target = target.substring(0, target.indexOf(Constant.COMMA));
                     }
                     isClassNameValid(target);
                     if(target.indexOf(Constant.DOT)>0){
                         if(isPackageNameValid(target.substring(0,target.lastIndexOf(Constant.DOT)))){
                             targetPackageName = target.substring(0,target.lastIndexOf(Constant.DOT));
                         }
                         }

                         if(targetPackageName == null){
                             try{
                                 targetPackageName = getPackageName(target);
                             }catch(Exception ex){
                            	 log.error("Exception: ", ex);
                                 throw ex;
                             }
                         }


                }

            }
    }catch(Exception ex){
        log.error("Exception: ", ex);
        throw ex;
    }

}

/**
 * Returns a query
 * @return
 */
public String getQuery(){
    return query;
}
/**
 * Returns target class name
 * @return
 */
public String getTargetClassName(){
    return targetClassName;
}
/**
 * Returns the criteria value
 * @return
 */
public String getCriteria(){
    return criteria;
}
/**
 * Returns the pageNumber
 * @return
 */
public String getPageNumber(){
    return pageNumber;
}
/**
 * Sets the page size value
 * @param size
 */
public void setPageSize(int size){
    pageSize = String.valueOf(size);
}
/**
 * Returns the page size
 * @return
 */
public String getPageSize(){
    return pageSize;
}
/**
 * Returns the resultCounter value
 * @return
 */
public String getResultCounter(){
    return resultCounter;
}

/**
 * Returns a query type value based on a given string.
 * @param url
 * @return
 */
public String getQueryType(String url){
	String queryType = "HTTPQuery";
	if(url.indexOf("Get")>0){
	            queryType = url.substring(url.lastIndexOf("Get"));
        }
    return queryType;
	}
/**
 * Returns the target package name
 * @return
 */
public String getTargetPackageName(){
    return targetPackageName;
}

/**
 * Generates a search criteria from a criteria list
 * @param packageName - specifies the package name
 * @param criteriaList - specifies a list of criteria instances.
 * @return
 * @throws Exception
 */
private Object buildSearchCriteria(String packageName, List criteriaList ) throws Exception{
    SearchUtils searchUtils = new SearchUtils();
    return searchUtils.buildSearchCriteria(packageName, criteriaList);
}

/**
 * Returns fully qualified search class names
 * @param searchClasses - specifies the search class names
 * @param packageName - specifies the package name
 * @return
 */
private String getSearchClassNames(String searchClasses, String packageName){
    String path = "";

    /**
    if(packageName != null && !(targetClassName.indexOf(Constant.DOT)>0) && (targetClassName.indexOf(",")<0)){
        targetClassName = packageName +Constant.DOT+targetClassName;
    }
    **/
        String delimiter = null;
        if(searchClasses.indexOf(Constant.FORWARD_SLASH)>0){
            delimiter = Constant.FORWARD_SLASH_STR;
            }
        else {
            delimiter = Constant.COMMA_STR;
            }
        StringTokenizer st = new StringTokenizer(searchClasses, delimiter);
        String className = st.nextToken();
        if(className.indexOf(Constant.DOT)>0){
            path = className;
        }
        else{
            path = packageName + Constant.DOT + className;
        }


        if(st.countTokens()>0){
            while(st.hasMoreElements()){
                className = st.nextToken().trim();
                if(className.indexOf(Constant.DOT)>0){
                  path += Constant.COMMA +  className;
                }
                path += Constant.COMMA + packageName + Constant.DOT + className;
                }
            }



    return path;
    }

/**
 * Returns a search criteria list from a criteria
 * @param criteria - specifies the criteria string
 * @return
 */

public List getSearchCriteriaList(String criteria){
    List criteriaList = new ArrayList();
    String delimiter = null;
    if(criteria.indexOf(Constant.FORWARD_SLASH)>0){
        delimiter = Constant.FORWARD_SLASH_STR;
        }
    else {
        delimiter = Constant.BACK_SLASH;
        }
    for(StringTokenizer st = new StringTokenizer(criteria, delimiter); st.hasMoreElements();){
        String crit = st.nextToken().trim();
        criteriaList.add(crit);
        }
    return criteriaList;
    }

/**
 * Gets all the fields for a given class
 * @param resultClass - Specifies the class name
 * @return - returns all the fields of a class
 */
public Field[] getAllFields(Class resultClass){
    List fieldList = new ArrayList();
    try{
	  	getAllFields(resultClass, fieldList);
    }catch(Exception ex){
    	log.error("Exception: ", ex);
    }

    Field[] fields = new Field[fieldList.size()];
    for(int i=0;i<fieldList.size(); i++){
        fields[i]= (Field)fieldList.get(i);
        }
        return fields;
    }

private void getAllFields(Class resultClass, List fieldList){
	
	if ( resultClass == null || 
			resultClass.getName().equalsIgnoreCase("java.lang.Object") ||
			resultClass.isInterface() ||
			resultClass.isPrimitive()) {
		return; // end of processing
	} 
	
	getAllFields(resultClass.getSuperclass(), fieldList);
	
    Field[] fields = resultClass.getDeclaredFields();
    for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                String fieldName = fields[i].getName();
                if(fieldName.indexOf('$')==-1)
                	fieldList.add(fields[i]);
    }	
}

/**
 * Generates an org.jdom.Document based on a resultSet
 * @param resultSet - specifies a list of populated domain objects
 * @param pageNumber - specifies the page number
 * @return
 * @throws Exception
 */
public org.jdom.Document getXMLDocument(Object[] resultSet, int pageNumber) throws Exception{

    org.jdom.Element httpQuery = new org.jdom.Element("httpQuery",namespace);
    org.jdom.Element queryRequest = new org.jdom.Element("queryRequest");

        Element queryString = new Element("queryString").setText(query);
        String targetResult = targetClassName;
        if(targetResult.indexOf(Constant.COMMA)>1){
            targetResult = targetResult.substring(0, targetResult.indexOf(Constant.COMMA));
        }
        if(targetResult.indexOf(Constant.DOT)<0){
            targetResult = this.getPackageName(targetResult)+ Constant.DOT +targetResult;
        }

        Element queryClass = new Element("class").setText(targetResult);
        Element queryElement = new Element("query").addContent(queryString).addContent(queryClass);
        queryRequest.addContent(queryElement);
        queryRequest.addContent(new org.jdom.Element("criteria").setText(criteria));

       httpQuery.addContent(queryRequest);

    int start = 0;
    int end = resultSet.length;
    int rowCount = 0;
    int index = 0;
    int resultCount = 0;
    int nextStartIndex = 0;
    int totalNumRecords = results.size();

    if(!(startIndex.equals("0") || startIndex == null)){
        index = Integer.valueOf(startIndex).intValue();
    }

    if(!(resultCounter.equals("0") ||resultCounter == null )){
        resultCount = Integer.valueOf(resultCounter).intValue();
    }

    Element xmlElement = new Element("queryResponse");

    String counter = String.valueOf(totalNumRecords);
    xmlElement.addContent(new Element("recordCounter").setText(counter));

    if(resultSet.length >0){

        String className = resultSet[0].getClass().getName();

        if(pageSize != null){
            rowCount = Integer.parseInt(pageSize);
        }
        int pageCounter = 1;
        int size = resultSet.length;
        if((index + resultCount)> totalNumRecords){
            size = totalNumRecords - index;
        }

        if(rowCount > 0 && rowCount < size){
            pageCounter = size/ rowCount;
            if((size % rowCount)>0){
                pageCounter++;
            }
        }
            if(pageNumber > pageCounter){
                pageNumber = 1;
            }
            if(pageNumber > 0 && pageNumber <= pageCounter){
                end = rowCount * pageNumber;
                start = end - rowCount;
                if(size < end){
                    end = size;
                }
            }




        String recordNum = "";


        Set resultClass = new HashSet();
        List classes = new ArrayList();
        for(int x=start; x<end; x++){
            resultClass.add(resultSet[x].getClass().getName());
        }


            if(resultClass.size() >1){
                Object lists[] = new Object[resultClass.size()];
                int number =0;
                for(Iterator it= resultClass.iterator(); it.hasNext();){
                    String typeName = (String)it.next();
                    classes.add(typeName);
                    List list = new ArrayList();
                    for(int i=start; i<end ; i++){
                        if(resultSet[i].getClass().getName().equals(typeName)){
                            list.add(resultSet[i]);
                        }
                    }
                    lists[number]= new Object();
                    lists[number] = list ;
                    number++;
                }

                for(int o=0; o<lists.length; o++){
                    List subResults = (List)lists[o];
                    for(int i=0; i< subResults.size(); i++){
                        Object result = subResults.get(i);
                        int recNum = index + i + 1;
                        recordNum = String.valueOf(recNum);
                        xmlElement.addContent(getElement(result, recordNum));
                    }

                }


            }
            else{

                for(int i = start; i< end; i++){
                    int recNum = index + i + 1;
                    recordNum = String.valueOf(recNum);
                    Object result = resultSet[i];
                    xmlElement.addContent(getElement(result, recordNum));

                }
            }



            if((index - resultCount)>=0){
                nextStartIndex = index - resultCount;
                String preLink = servletName +"?query="+targetClassName + Constant.AMPERSAND + criteria +"&startIndex="+nextStartIndex+"&resultCounter="+resultCounter;
                String preText = "<<< "+" PREVIOUS "+ resultCount +" RECORDS";
                Element preElement = new Element("previous").setAttribute("type","simple",namespace).setAttribute("href",preLink,namespace).setText(preText);
                xmlElement.addContent(preElement);
            }
            String pCount = String.valueOf(pageCounter);
            Element pagesElement = new Element("pages").setAttribute("count",pCount);
            if((index + resultCount)< totalNumRecords){
                nextStartIndex = index + resultCount;
                String nextLink = servletName +"?query="+targetClassName + Constant.AMPERSAND +criteria +"&startIndex="+nextStartIndex+"&resultCounter="+resultCounter;
                String nextText = "NEXT "+ resultCount+" RECORDS >>> ";
                Element nextElement = new Element("next").setAttribute("type","simple",namespace).setAttribute("href",nextLink,namespace).setText(nextText);
                xmlElement.addContent(nextElement);
            }
            Element pageNumberElement = new Element("pageNumber").setText(this.pageNumber);
            for(int i=0; i< pageCounter; i++){
                int p = i + 1;
                String pageLink = servletName +"?query="+targetClassName+Constant.AMPERSAND+criteria +"&pageNumber="+p+"&resultCounter="+resultCounter+"&startIndex="+startIndex;
                String page = String.valueOf(p);
                String pageText = Constant.SPACE + page + Constant.SPACE;
                Element pElement = new Element("page").setAttribute("number",page).setAttribute("type","simple",namespace).setAttribute("href",pageLink,namespace).setText(pageText);
                pagesElement.addContent(pElement);
            }

            xmlElement.addContent(pagesElement);

            httpQuery.addContent(xmlElement);
            xmlElement.addContent(new Element("recordCounter").setText(counter));

    }
    else{

        xmlElement.addContent(new Element("recordCounter").setText("0"));
    }





    if((pageNumber -1)> 0){
        index += ((pageNumber -1)* rowCount) + 1;
    }
    else{
        index+= 1;
    }
    int endRecordNum = rowCount + index - 1;
    if(endRecordNum > totalNumRecords){
        endRecordNum = totalNumRecords;
    }

    String startCounter = String.valueOf(index);
    String endCounter = String.valueOf(endRecordNum);
    Element startElement = new Element("start").setText(startCounter);
    Element endElement = new Element("end").setText(endCounter);
    xmlElement.addContent(startElement).addContent(endElement);

    org.jdom.Document xmlDoc = new org.jdom.Document(httpQuery);


    return xmlDoc;
}


/**
 * Returns the value of an id field from a given result
 * @param result - populated instance of a class
 * @return
 * @throws Exception
 */
private String getCriteriaIdValue(Object result) throws Exception{
    Field[] fields = this.getAllFields(result.getClass());
    Field idField = getIdField(fields);
    String id = idField.getName();
    String criteriaIdValue = Constant.AT + id + Constant.EQUAL;
    if(idField.getName().indexOf(Constant.DOT)>0){
        id = id.substring(id.lastIndexOf(Constant.DOT)+1);
    }
    if(getFieldValue(idField, result) != null){
        criteriaIdValue += String.valueOf(getFieldValue(idField, result));
    }
    return criteriaIdValue;
}

/**
 * Generates an Element for a given result object
 * @param result - an instance of a class
 * @param recordNum - specifies the record number in the result set
 * @return
 * @throws Exception
 */
private Element getElement(Object result, String recordNum) throws Exception{


    Element classElement = new Element("class").setAttribute("name",result.getClass().getName()).setAttribute("recordNumber",recordNum);
    String criteriaIdValue = getCriteriaIdValue(result);

    boolean isAssociation = true;
    boolean isCollection = false;
    String link = null;

    Field[] fields = this.getAllFields(result.getClass());
    for(int f=0; f<fields.length; f++){
        String criteriaBean = result.getClass().getName();
        Field field = fields[f];
        String fieldName = field.getName();


        if(fieldName.equalsIgnoreCase("serialVersionUID")){
            continue;
        }
        Element fieldElement = new Element("field").setAttribute("name", fieldName);

        String fieldType = field.getType().getName();
        String targetBean = null;

        if(!(fieldType.startsWith("java") && !(fieldType.endsWith("Collection")) || field.getType().isPrimitive())){
            if(field.getType().getName().endsWith("Collection") ){
                SearchUtils searchUtils = new SearchUtils();
                if(searchUtils.getTargetClassName(result.getClass().getName(),fieldName)!=null){
                    targetBean = searchUtils.getTargetClassName(result.getClass().getName(),fieldName);
                }
            }
            else if(locateClass(fieldType)){
                    targetBean = fieldType;
            }

        }

            if(targetBean != null){
                if(!isPackageNameAmbiguous(targetBean)){
                    if(result.getClass().getPackage().equals(Class.forName(targetBean).getPackage())){
                        targetBean = targetBean.substring(targetBean.lastIndexOf(Constant.DOT)+1);
                        if(criteriaBean.indexOf(Constant.DOT)>0){
                            criteriaBean = criteriaBean.substring(criteriaBean.lastIndexOf(Constant.DOT)+1);
                        }
                    }
                }
                String methodName = "get"+  fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                if((fieldName.startsWith("parent")|| fieldName.startsWith("child"))&& fieldName.indexOf("Ontology")>0){
                    link = servletName + Constant.QUESTION_MARK + this.getOntologyLink(methodName, criteriaIdValue, result.getClass().getName());
                }else{
                    //Support getSupplyingSource and getOriginalSource methods
                    if(result.getClass().getName().equals("gov.nih.nci.common.provenance.domain.Provenance")&& (fieldName.equals("supplyingSource")|| fieldName.equals("originalSource") || fieldName.equals("immediateSource"))){
                        String targetFieldName = fieldName +"Id";
                        if(getFieldByName(fields, targetFieldName) != null){
                            Field targetField = this.getFieldByName(fields, targetFieldName);
                            if(targetField.get(result)!=null){
                                String targetIdValue = "@id="+ String.valueOf(targetField.get(result));
                                link =  servletName + "?query=" +  targetBean + Constant.AMPERSAND + targetBean +Constant.LEFT_BRACKET+ targetIdValue+Constant.RIGHT_BRACKET;
                            }
                        }
                    }
                    else if(result.getClass().getName().equals("gov.nih.nci.cabio.domain.CytogeneticLocation")&& (fieldName.equals("endCytoband")|| fieldName.equals("startCytoband"))){
                        String targetFieldName = fieldName +"LocId";
                        if(getFieldByName(fields, targetFieldName) != null){
                            Field targetField = this.getFieldByName(fields, targetFieldName);
                            if(targetField.get(result)!=null){
                                String targetIdValue = "@id="+ String.valueOf(targetField.get(result));
                                link =  servletName + "?query=" +  targetBean + Constant.AMPERSAND +targetBean +Constant.LEFT_BRACKET+ targetIdValue+Constant.RIGHT_BRACKET;
                            }
                        }

                    }
                    else{
                        link =  servletName + "?query=" +  targetBean + Constant.AMPERSAND +criteriaBean +Constant.LEFT_BRACKET+ criteriaIdValue+Constant.RIGHT_BRACKET;
                    }

                }
                if(link != null){
                    fieldElement.setAttribute("type","simple",namespace).setAttribute("href",link,namespace).setText(methodName);
                }
                else{
                    fieldElement.setText("-");
                }

            }

            else{
                String fieldValue = "-";
                Object value = null;

                    try{
                        if(fieldType.indexOf("Collection")>0 || fieldType.endsWith("HashSet") || fieldType.endsWith("ArrayList") || fieldType.indexOf("Vector")>0){
                            Iterator it = ((Collection)fields[f].get(result)).iterator();
                            fieldValue = String.valueOf(it.next());
                            while(it.hasNext()){
                                fieldValue += "; "+ String.valueOf(it.next());
                            }
                            if(fieldValue != null){
                                fieldElement.setText(fieldValue);
                            }
                            else{
                                fieldElement.setText("-");
                            }

                        }
                        else{
                            if(this.getFieldValue(field,result)!= null){
                                value = getFieldValue(field,result);
                                fieldValue = String.valueOf(value);
                            }
                            fieldElement.setText(fieldValue);
                        }

                    }catch(Exception ex){
                       fieldValue = "-";
                       value = getFieldValue(field,result);
                        fieldValue = String.valueOf(value);
                        String temp = null;
                        for(int s=0; s< fieldValue.length(); s++){
                            String charValue = String.valueOf(fieldValue.charAt(s));
                            try{
                                Element tempElement = new Element("Temp").setText(charValue);
                                temp += charValue;
                            }catch(Exception e){
                                temp += " ";
                            }
                        }
                        if(temp != null){
                            fieldValue = temp;
                        }

                        fieldElement.setText(fieldValue);
                    }





            }



        classElement.addContent(fieldElement);
    }
    return classElement;
}

/**
 * Returns the package name of a given class
 * @param className - specifies the class name
 * @return
 * @throws Exception
 */
public String getPackageName(String className) throws Exception{
    className = className.trim();
    String packageName = null;
    String classBeanName = null;
    if(className.indexOf(Constant.DOT)>1){
        classBeanName = className.substring(className.lastIndexOf(Constant.DOT)+1);
    }
    else{
        classBeanName = className;
    }
    List packages = new ArrayList();

    try{
    if(properties != null){

        for(Iterator i= properties.keySet().iterator(); i.hasNext();){
            String key = (String)i.next();
            String value = (String)properties.get(key);
            String domainName = null;


            if(className.indexOf(Constant.DOT)>1){
                if(key.equalsIgnoreCase(className)){
                    packages.add(value);
                }
            }
            else{
                if(key.lastIndexOf(Constant.DOT)>1){
                    domainName = key.substring(key.lastIndexOf(Constant.DOT)+1);
                    if(domainName.equalsIgnoreCase(classBeanName)){
                        packages.add(value);
                    }
                }
            }

        }
    }

    if(packages.size()>1){
        String msg = "";
        for(int i=0; i<packages.size(); i++){
            msg += (String)packages.get(i)+"<br>";
        }
        throw new Exception("Ambiguous Exception: Multiple package names found for "+className + " please specify package name <br>"+msg);
    }else if(packages.size()<1){
        throw new Exception("Invalid class name exception: Cannot locate package for "+className);
    }
    else{
        packageName = (String)packages.get(0);
    }
    }catch(Exception ex){
        log.error("Exception: ", ex);
        throw ex;
    }

    return packageName;
}

/**
 * Returns true if the given class name is found
 * @param className
 * @return
 */
public boolean locateClass(String className){
	//To make sure class is not a proxy generated by CGLIB
	if(className.indexOf("$")>1)
		className = className.substring(0, className.indexOf("$"));

    boolean found = false;
    if(properties != null){
        for(Iterator i= properties.keySet().iterator(); i.hasNext();){
            String key = (String)i.next();
             if(className.lastIndexOf(Constant.DOT)>1){
                if(key.equals(className)){
                    found=true;
                    break;
                }
            }
            else{
                if(key.substring(key.lastIndexOf(Constant.DOT)+1).equals(className)){
                    found=true;
                    break;
                }
            }
        }
        }

    return found;
}

/**
 * Returns a ontology link value for a given string
 * @param methodName - specifies the method name
 * @param criteriaIdValue - specifies the id value
 * @param currentClassName - specifies the class name
 * @return
 * @throws Exception
 */
private String getOntologyLink(String methodName, String criteriaIdValue, String currentClassName) throws Exception{
    String link = null;
    String returnClassName = null;
    String term = null;
    String roleName = null;

    if(currentClassName.indexOf(Constant.DOT)>1){
        currentClassName = currentClassName.substring(currentClassName.lastIndexOf(Constant.DOT)+1);
    }

    if(methodName.endsWith("Collection")){
        term = methodName.substring(0, methodName.indexOf("Collection"));
    }
    else{
        term = methodName;
    }

        if(methodName.startsWith("getParent")){
            returnClassName = term.substring("getParent".length());
            roleName = "child"+ currentClassName;
        }
        else if(methodName.startsWith("getChild")){
             returnClassName = term.substring("getChild".length());
             roleName = "parent"+ currentClassName;
         }
    if(currentClassName.endsWith("Relationship")){
        roleName += "Collection";
    }

    link = "query="+ returnClassName +Constant.AMPERSAND + returnClassName + Constant.LEFT_BRACKET+roleName +Constant.LEFT_BRACKET+ criteriaIdValue +"]]";
    return link;
}


 /**
  * Returns an id field from an array of fields
  * @param fields
  * @return
  * @throws Exception
  */
 private Field getIdField(Field[] fields) throws Exception{
     Field id = null;
     for(int i=0; i<fields.length;i++){
         if(fields[i].getName().equalsIgnoreCase("id")){
             if(!this.locateClass(fields[i].getType().getName())){
                 id = fields[i];
                 break;
                 }
         }
     }
     return id;
 }

 /**
  * Returns a field that matches the given String from an array of fields
  * @param fields All fields
  * @param fieldName  Field name
  * @return
  * @throws Exception
  */
 private Field getFieldByName(Field[] fields, String fieldName) throws Exception{
     Field field = null;
     for(int i=0; i<fields.length;i++){
         if(fields[i].getName().equalsIgnoreCase(fieldName)){
             if(!this.locateClass(fields[i].getType().getName())){
                 field = fields[i];
                 break;
                 }
         }
     }
     return field;
 }
 /**
  * Returns a field value
  * @param field - specifies the field
  * @param domain - specifies the object
  * @return
  * @throws Exception
  */
 private Object getFieldValue(Field field, Object domain) throws Exception{
     Object value = null;
     if(field.get(domain)!= null){
         if(field.getType().getName().equals("java.util.Date")){
             SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy");
             value = date.format((Date)field.get(domain));
             }
         else{
             value = field.get(domain);
         }
     }
     return value;
 }

 /**
  * Returns an array of result objects
  * @return
  * @throws Exception
  */
 public Object[] getResultSet()throws Exception{

     results = new ArrayList();
     int index = 0;
     int counter = 1000;

   try{
       String searchPath = getSearchClassNames(targetClassName, targetPackageName);
       List criteriaList = getSearchCriteriaList(criteria);
       Object criteria = buildSearchCriteria(targetPackageName, criteriaList );

       if(startIndex != null || !startIndex.equals("0")){
           index = Integer.parseInt(startIndex);
       }
       if(resultCounter != null){
           counter = Integer.parseInt(resultCounter);
       }
       ApplicationService appService =  ApplicationServiceProvider.getApplicationService();
       results = appService.search(searchPath, criteria);
   }catch(Exception ex){
       log.error("Exception: ", ex);
       throw ex;
    }


   if((counter + index) > results.size()){
       counter = results.size();
   }
   else{
       counter += index;
   }

   Object[] resultSet = new Object[counter];
   for(int i = index, s=0; i< counter; i++,s++){
        resultSet[s]= results.get(i);
   }

   return resultSet;
  }

 public Object[] getCachedResultSet() throws Exception{
     int counter = 1000;
     int index = 0;
     if(resultCounter != null){
         counter = Integer.parseInt(resultCounter);
     }
     if(startIndex != null){
         index = Integer.parseInt(startIndex);
     }

     int size = index + counter;

     if(size > results.size()){
         size = results.size();
         }

     Object[] resultSet = new Object[counter];
     for(int s=0, i = index; i<size; i++, s++){
         resultSet[s]= new Object();
         resultSet[s]= results.get(i);
     }
     return resultSet;
 }

 /**
  * Returns true if a match is found
  * @param prop - an instance of an HTTPUtils class
  * @return
  */
 public boolean getMatch(HTTPUtils prop){
     boolean match = false;
     String oldQuery = prop.getCriteria();
     String oldTarget = prop.getTargetClassName();
     int size = prop.getResults().size();

     if((this.targetClassName.equals(oldTarget))&& this.criteria.equals(oldQuery) && size > 0){
         match = true;
     }
     return match;
 }

 /**
  * Returns true if more than one package is found for a given class
  * @param className - specifies the class name
  * @return
  * @throws Exception
  */
 public boolean isPackageNameAmbiguous(String className) throws Exception{
     boolean ambiguous = true;
     className = className.trim();
     String classBeanName = null;
     if(className.indexOf(Constant.DOT)>1){
         classBeanName = className.substring(className.lastIndexOf(Constant.DOT)+1);
     }
     else{
         classBeanName = className;
     }
     List packages = new ArrayList();

     try{
     if(properties != null){

         for(Iterator i= properties.keySet().iterator(); i.hasNext();){
             String key = (String)i.next();
             String value = (String)properties.get(key);
             String domainName = null;

               if(key.lastIndexOf(Constant.DOT)>1){
                     domainName = key.substring(key.lastIndexOf(Constant.DOT)+1);
                     if(domainName.equalsIgnoreCase(classBeanName)){
                         packages.add(value);
                     }
                 }


         }
     }

     if(packages.size()==1){
         ambiguous = false;
     }
     }catch(Exception ex){
         log.error("Exception: ", ex);
         throw ex;

     }
     return ambiguous;
 }

 /**
  * Returns true if the package name is valid
  * @param packageName
  * @return
  * @throws Exception
  */
 private boolean isPackageNameValid(String packageName) throws Exception{
     boolean valid = false;
     try{
     if(properties != null){
         for(Iterator i= properties.keySet().iterator(); i.hasNext();){
             String key = (String)i.next();
             String value = (String)properties.get(key);
             if(value.equals(packageName)){
                 valid = true;
                 break;
             }
         }
     }

     if(!valid){
         throw new Exception("Invalid package name : "+ packageName);
     }
     }catch(Exception ex){
         log.error("Exception: ", ex);
         throw ex;

     }
     return valid;
 }

 /**
  * Returns true if the given class name is valid
  * @param targetClassName
  * @return
  * @throws Exception
  */
 private boolean isClassNameValid(String targetClassName) throws Exception{
     boolean valid = false;
     String className = null;
     if(targetClassName.indexOf(Constant.DOT)>0){
         className = targetClassName.substring(targetClassName.lastIndexOf(Constant.DOT)+1);
     }
     else{
         className = targetClassName;
     }

     try{
     if(properties != null){
         for(Iterator i= properties.keySet().iterator(); i.hasNext();){
             String key = (String)i.next();
             String keyClassName = null;
             if(key.indexOf(Constant.DOT)>0){
                 keyClassName = key.substring(key.lastIndexOf(Constant.DOT)+1);
             }
             else{
                 keyClassName = key;
             }
             if(className.equals(keyClassName)){
                valid = true;
                break;
              }
         }
     }

     if(!valid){
         throw new Exception("Invalid class name : "+ className);
     }
     }catch(Exception ex){
         log.error("Exception: ", ex);
         throw ex;

     }
     return valid;
 }
 /*
  * This method is used when an XSLT stylesheet is not found.(Test methods)
  */
 /**
  * Prints results on screen
  * @param resultList
  * @param response
  * @throws IOException
  * @throws ServletException
  */
 public void printResults(HttpServletResponse response)throws IOException, ServletException{

     response.setContentType("text/html");
     ServletOutputStream out = response.getOutputStream();
     out.println("<br><font color=purple><b>");
     out.println("<b>"+results.size() +" records found. </b><br><hr>");
     int recordNum = 1;
     out.println();
     out.println("<font size=4 color=black> Criteria : "+ this.getCriteria()+"</font>" );
     if(results.size()>0)
    	 out.println("<br><font size=4 color=black> Result Class name: "+ results.get(0).getClass().getName()+"</font>" );
     else
    	 out.println("<br><font size=4 color=black>No records found </font>" );
     out.println("<br><hr><br>");
     out.println("<TABLE BORDER=\"2\"  style=\"table-layout:AUTO\" valign=\"top\">");
     try{

	     for(int i =0; i< results.size(); i++){

	         printRecord(results.get(i), servletName, out, recordNum);

	         recordNum++;
	     }
     }catch(Exception ex){
    	 log.error("Exception: ", ex);
         throw new IOException(ex.getMessage());
     }


 }
/**
 * Displays a record on screen
 * @param result
 * @param servletName
 * @param out
 * @param recordNum
 * @throws Exception
 */
 private void printRecord(Object result, String servletName, ServletOutputStream out, int recordNum)throws Exception{

     Class resultClass = result.getClass();
     String className = resultClass.getName();
     Class superClass = resultClass.getSuperclass();

     Field[] fields      = getAllFields(resultClass);
     Field[] superFields = getAllFields(superClass);


     if(recordNum == 1){
         out.println("<TR BGCOLOR=\"#E3E4FA\">");
         for(int x=0; x<fields.length; x++){
             String fName = fields[x].getName();
             if(!fName.equalsIgnoreCase("serialVersionUID")){
                 out.println("<TD>"+ fName +"</TD>");
             }

         }
         for(int x=0; x<superFields.length; x++){
             String fName = superFields[x].getName();
             if(!fName.equalsIgnoreCase("serialVersionUID")){
                 out.println("<TD>"+ fName +"</TD>");
             }

         }
         out.println("</TR>");
         }
     out.println("<TR VALIGN=\"TOP\">");

     Field idField = null;
     String criteriaIdValue = null;


     for(int f=0;f<fields.length; f++){
         fields[f].setAccessible(true);
         String fieldClassName = fields[f].getType().getName();
         String fieldName = fields[f].getName().substring(0,1).toUpperCase()+fields[f].getName().substring(1);
         if(fields[f].getName().equalsIgnoreCase("id")){
             idField = fields[f];
             try{
             Object idValue = idField.get(result);
             String id = null;
             if(!idField.getType().getName().endsWith("String")){
                 id = String.valueOf(idValue);
             }
             criteriaIdValue = Constant.AT+idField.getName()+Constant.EQUAL+id;
             }catch(Exception ex){
                 throw new IOException(ex.getMessage());
             }
          }
         else if(fieldName.equalsIgnoreCase("serialVersionUID")){
             continue;
         }
         boolean bean = false;
         String fieldType = fields[f].getType().getName();
     	if(fieldType.indexOf("$")>1)
     		fieldType = fieldType.substring(0, fieldType.indexOf("$"));
         
         Object value = "-";
         if(fields[f].get(result)!= null){
             value = fields[f].get(result);
             fieldType = value.getClass().getName();
          	if(fieldType.indexOf("$")>1)
         		fieldType = fieldType.substring(0, fieldType.indexOf("$"));
             
             if(!fieldType.startsWith("java.")){
                 bean = locateClass(fieldType);
             }

         }


         String methName = "get"+ fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);

         String beanName = null;

         if(bean){
             beanName = fieldType.substring(fieldType.lastIndexOf(Constant.DOT)+1);
         }



        String returnObjectName = fields[f].getName();
        boolean collectionType = false;
        if(returnObjectName.endsWith("Collection")|| fieldType.endsWith("Vector")|| fieldType.endsWith("HashSet")){
            collectionType = true;
        }

        if((fieldType.startsWith("java.")&& !(collectionType)) || fields[f].getType().isPrimitive()){
            String strValue = " ";
            if(value != null){
                strValue = String.valueOf(value);
            }
             out.println("<TD>"+ strValue +"</TD>" );
        }
        else if(returnObjectName.indexOf("Ontology")>0 &&(returnObjectName.startsWith("parent")|| returnObjectName.startsWith("child"))){
            String link = this.getOntologyLink(methName, criteriaIdValue, result.getClass().getName());
            out.println("<TD><a href="+ servletName + Constant.QUESTION_MARK + link + Constant.GREATER_THAN + methName +"</a></TD>");
            }
        else if(returnObjectName.endsWith("Collection")){

            String returnClassName = returnObjectName.substring(0,returnObjectName.lastIndexOf("Collection"));
            returnClassName = returnClassName.substring(0,1).toUpperCase() + returnClassName.substring(1);
            String disp = "<TD><a href="+servletName+"?query="+ returnClassName+ Constant.AMPERSAND +className.substring(className.lastIndexOf(Constant.DOT)+1)+"[@"+idField.getName()+ Constant.EQUAL +idField.get(result)+"]>" + methName+"</a></TD>";

            out.println(disp);
        }
        else if(bean){
            String disp = "<TD><a href="+servletName+"?query="+beanName+Constant.AMPERSAND+className.substring(className.lastIndexOf(Constant.DOT)+1)+"[@"+idField.getName()+ Constant.EQUAL +idField.get(result)+"]>"+methName+"</a></TD>";

            out.println(disp);
        }


    }
     recordNum++;
     out.println("</TR>");
     }


}


