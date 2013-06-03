/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.client.util.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CharAdapter extends XmlAdapter<String,Character>{

 @Override
 public String marshal(Character v) throws Exception {
	 if(v != null)
  		return new String(new char[]{v});
  	 else
  	 	return "";
 }

 @Override
 public Character unmarshal(String v) throws Exception {
   if(v.length()>0)
   return v.charAt(0);
  else return ' ';
 }

}