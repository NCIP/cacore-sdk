package gov.nih.nci.sdk.ide.core;

import java.util.ArrayList;
import java.util.List;

public class ModelPackageVO {
	private String packageName = "";
	private List<String> models = new ArrayList<String>();
	
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
	public List<String> getModels() {
		return models;
	}
	
	/**
	 * @param models the models to set
	 */
	public void setModels(List<String> models) {
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
