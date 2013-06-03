/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.query;

import java.io.Serializable;
import java.util.Collection;

public class SDKQueryResult implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Object objectResult;
	
	private Collection collectionResult;

	private Boolean voidResult;

	private Integer countResult = -1;
	
	
	public SDKQueryResult(Object objectResult) {
		this.objectResult = objectResult;
	}

	public SDKQueryResult(Collection collectionResult) {
		this.collectionResult = collectionResult;
	}

	public SDKQueryResult() {
		this.voidResult = true;
	}
	
	public SDKQueryResult(Integer countResult) {
		this.countResult = countResult;
	}

	public Collection getCollectionResult() {
		return collectionResult;
	}

	public Object getObjectResult() {
		return objectResult;
	}

	public Boolean isVoidResult() {
		return voidResult;
	}
	
	public Boolean isObjectResult() {
		return objectResult != null;
	}
	
	public Boolean isCollectionResult() {
		return collectionResult != null;
	}
	
	public Boolean isCountResult() {
		return countResult != null && countResult>=0 ;
	}
}