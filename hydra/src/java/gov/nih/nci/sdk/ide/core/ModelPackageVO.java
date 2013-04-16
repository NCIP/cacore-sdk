/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.core;

import java.util.SortedSet;
import java.util.TreeSet;

public class ModelPackageVO {
	private String packageName = "";
	private SortedSet<String> models = new TreeSet<String>();
	
	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	/**
	 * @return the models
	 */
	public SortedSet<String> getModels() {
		return models;
	}
	
	/**
	 * @param models the models to set
	 */
	public void setModels(SortedSet<String> models) {
		this.models = models;
	}
	
	public boolean hasPackage() {
		return (packageName != null && !"".equals(packageName));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("packageName=").append(packageName).append(", ");
		sb.append("models=").append(models);
		return sb.toString();
	}
}
