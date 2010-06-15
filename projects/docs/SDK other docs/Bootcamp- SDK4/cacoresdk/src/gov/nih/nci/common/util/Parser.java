package gov.nih.nci.common.util;


import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;

import java.io.IOException;
import java.util.Hashtable;


import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.jdom.filter.*;
import org.apache.log4j.*;

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
 * Parser provides components to read and extract data from an XML file
 * @author caBIO Team
 * @version 1.0
 */

public class Parser {

public Document doc;
private static Logger log= Logger.getLogger(Parser.class.getName());
public Hashtable elemAttr = new Hashtable();


/**
 * Creates a Parser instance for a given XML file
 * @param fileName
 */
public Parser(String fileName)
{
	SAXBuilder builder = new SAXBuilder();
	try {
		 doc = builder.build(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
	}
	catch (JDOMException e) {
	    log.error("JDOMException: " + e.getMessage());
	    }
	catch (IOException e) {
	    log.error("IOException: " + e.getMessage());
	    }

}
/**
 * Returns a list of child elements stored in this document
 * @return
 */
 public List getRootElementChildren(){

 	List rootChildren = doc.getRootElement().getChildren();
  	Element domainObjects  = (Element)rootChildren.get(1);
  	domainObjects.getChild("proxy").setText("http.ncicb.nci.gov/getProtein");
 	domainObjects.getChild("urn").setText("urn.getProtein");
 	return rootChildren;
 }

 /**
  * Updates an element in the doucment
  * @param index 	Specifies the index of the element need to be updated
  * @param table	Specifies the new values for this element
  */
 	public void updateElements(int index, String[][] table)
 	{
 		List rootChildren = doc.getRootElement().getChildren();
 		Element updateElement  = (Element)rootChildren.get(index);
 		for (int i = 0; i<table.length; i++){
 			updateElement.getChild(table[i][0]).setText(table[i][1]);
 		}

 	}


	/**
	 * Generates a Hashtable for a list of elements
	 * @param list	List of elements
	 * @return		Returns a Hashtable with a list of elements
	 */
 	public Hashtable listElements(List list)
 	{

		  for (Iterator i = list.iterator(); i.hasNext();) {
		    Element e = (Element) i.next();
		    listElement(e);
		  }
		  return elemAttr;
	}

	/**
	 * Generates a Hashtable of child elements
	 * @param e		Specifies the element
	 * @return		Returns a Hashtable with a list of child elements
	 */
	public  Hashtable listElement(Element e)
	{
			elemAttr.put(e.getName(), e.getText().trim());
			List as = e.getAttributes();
			listAttributes(as);
			List c = e.getChildren();
			listElements(c);
			return elemAttr;
	}


	/**
	 * Generates a vector with the attribute name and value
	 * @param ls	List of elements
	 * @return
	 */
	public  Vector listAttributes(List ls)
	{
		Vector attributes = new Vector();

		  for (Iterator i = ls.iterator(); i.hasNext();) {
		    Attribute att = (Attribute) i.next();
		    attributes.add(att.getValue());
		    elemAttr.put(att.getName(), att.getValue());
		  }
		  return attributes;
    }



	/**
	 * returns a list of elements that matches a particular pattern
	 * @param xPathExpression	Specifies the search string
	 * return					Returns a list that matches the specified expression
	 */
	public List getList(String xPathExpression)
	{
		List list=null;
		try
		{
			XPath x = XPath.newInstance(xPathExpression);
		 	list = x.selectNodes(doc);

		} catch (JDOMException e){
		    log.error("JDOMException: " + e.getMessage());
		}

	  	return list;
	  }
	
	/**
	 * 
	 * TODO To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Style - Code Templates
	 */
	public Iterator getElements(String tagName)
	{
		Element root = doc.getRootElement();
		
		ElementFilter elementFilter = new ElementFilter(tagName);
        Iterator allElements = root.getDescendants(elementFilter);
            
        return allElements;
	}
}

