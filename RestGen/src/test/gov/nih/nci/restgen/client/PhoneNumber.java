/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.restgen.client;

public class PhoneNumber {
	 
    private String foo;
    private String bar;
 
    public String getType() {
        return foo;
    }
 
    public void setType(String type) {
        this.foo = type;
    }
 
    public String getNumber() {
        return bar;
    }
 
    public void setNumber(String number) {
        this.bar = number;
    }
 
}