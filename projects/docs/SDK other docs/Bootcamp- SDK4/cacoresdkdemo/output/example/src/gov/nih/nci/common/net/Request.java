package gov.nih.nci.common.net;

import java.util.Hashtable;
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
 * Provides various methods to submit a query request to a specified datasource
 * @author caBIO Team 
 * @version 1.0
 */
public class Request implements java.io.Serializable
{
	
	private static final long serialVersionUID = 1234567890L;
	private Object request;
	private String domainObjectName;
	private Hashtable config;
	private Boolean isCount;
	private Boolean caseSensitivity;
	private Integer firstRow;
	private Integer recordsCount;
	
	/**
	 * Creates a Request instance
	 */
	public Request()
	{
	}	
	
	/**
	 * Creates a Request instance for a given object
	 * @param request
	 */
	public Request(Object request)
	{
		this.request=request;
	}

	/**
	 * Sets the value for this request object
	 * @param request 	Specifies the request object	 
	 */
	public void setRequest(Object request)
	{
		this.request=request;
	}
	
	/**
	 * Returns a value for this request object
	 * @return 	returns a request object
	 */
	public Object getRequest()
	{
		return request;
	}
	
	/**
	 * Sets the name for this domain object
	 * @param domainObjectName 	Specifies the domain object name
	 */
	public void setDomainObjectName(String domainObjectName)
	{
		this.domainObjectName=domainObjectName;
	}
	
	/**
	 * Returns the domain object name of this request
	 * @return returns the name of this domain object
	 */
	public String getDomainObjectName()
	{
		return domainObjectName;
	}
	
	/**
	 * Sets the configuration value
	 * @param config	Specifies the configuration values
	 */
	
	public void setConfig(Hashtable config)
	{
		this.config = config;
		
	}
	
	/**
	 * Returns the configuration values for this request
	 * @return Returns the configuration values
	 */
	public Hashtable getConfig()
	{
		return config;
	}
	/**
	 * Set firstRow value
	 * @param firstRow
	 */
	public void setFirstRow(Integer firstRow)
	{
	    this.firstRow = firstRow;
	}
	/**
	 * Return the firstRow value
	 * @return firstRow
	 */
	public Integer getFirstRow()
	{
	    return this.firstRow;
	}
	/**
	 * Set recordsCount value
	 * @param rc
	 */
	public void setRecordsCount(Integer rc)
	{
	    this.recordsCount = rc;
	}
	/**
	 * 
	 * @return recordsCount value
	 */
	public Integer getRecordsCount()
	{
	    return this.recordsCount;
	}

	/**
	 * Return isCount value
	 * @return isCount
	 */
	public Boolean getIsCount()
	{
	    return this.isCount;
	}
	/**
	 * Set isCount value
	 * @param b
	 */
	public void setIsCount(Boolean b)
	{
	    this.isCount = b;
	}

	public Boolean getCaseSensitivity()
	{
		return caseSensitivity;
	}

	public void setCaseSensitivity(Boolean caseSensitivity)
	{
		this.caseSensitivity = caseSensitivity;
	}
	
	
}
