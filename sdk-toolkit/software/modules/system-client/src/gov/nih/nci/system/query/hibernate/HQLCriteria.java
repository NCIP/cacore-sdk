/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.query.hibernate;

import java.io.Serializable;
import java.util.List;

/**
 * @author Satish Patel
 *
 */
public class HQLCriteria implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hqlString;
	private String countHqlString;
	private List parameters;	
	private int firstRow = -1;
	private int numberOfRows = -1;
	
	public HQLCriteria(String hqlString)
	{
		this.hqlString = hqlString;
	}
	
	public HQLCriteria(String hqlString, List parameters)
	{
		this.hqlString = hqlString;
		this.parameters = parameters;
	}	

	public HQLCriteria(String hqlString, String countHqlString, List parameters)
	{
		this.hqlString = hqlString;
		this.parameters = parameters;
		this.countHqlString = countHqlString;
	}	
	
	public HQLCriteria(String hqlString, List parameters, int firstRow) {
		this.hqlString = hqlString;
		this.parameters = parameters;
		this.firstRow = firstRow;
	}
	
	public HQLCriteria(String hqlString, List parameters, int firstRow, int numberOfRows) {
		this.hqlString = hqlString;
		this.parameters = parameters;
		this.firstRow = firstRow;
		this.numberOfRows = numberOfRows;
	}

	public String getHqlString()
	{
		return this.hqlString;
	}

	public List getParameters() {
		return parameters;
	}

	public void setHqlString(String hqlString)
	{
		this.hqlString = hqlString;
	}

	public void setParameters(List parameters) {
		this.parameters = parameters;
	}

	public String getCountHqlString() {
		return countHqlString;
	}

	public void setCountHqlString(String countHqlString) {
		this.countHqlString = countHqlString;
	}
	
	public int getFirstRow() {
		return firstRow;
	}
	
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	
	public int getNumberOfRows() {
		return numberOfRows;
	}
	
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	
}
