/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.example.generator;

public class SpringBean {
	String beanId;
	String beanImpl;
	String beanAddress;
	String serviceClass;
	String serviceAddress;

	public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public String getBeanImpl() {
		return beanImpl;
	}

	public void setBeanImpl(String beanImpl) {
		this.beanImpl = beanImpl;
	}

	public String getBeanAddress() {
		return beanAddress;
	}

	public void setBeanAddress(String beanAddress) {
		this.beanAddress = beanAddress;
	}
}
