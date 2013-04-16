/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.acegi.providers;

import java.util.Collection;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;

public class GroupNameAuthenticationToken implements Authentication {

   private static final long serialVersionUID = 1L;
   Collection<String> groups;
   boolean authenticated;

   public GroupNameAuthenticationToken(Collection<String> groups) {
	   this.groups=groups;
	   authenticated=true;
   }

   public void setAuthenticated(boolean isAuthenticated)
       throws IllegalArgumentException {
       if (isAuthenticated) {
           throw new IllegalArgumentException(
               "Cannot set this token to trusted - use constructor containing GrantedAuthority[]s instead");
       }
      this.authenticated=isAuthenticated;
   }

	public GrantedAuthority[] getAuthorities() {
		return null;
	}

	public Object getCredentials() {
		return "dummy";
	}

	public Object getDetails() {
		return null;
	}

	public Object getPrincipal() {
		return "dummy";
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public String getName() {
		return "dummy";
	}
	
	public void setGroups(Collection<String> groups) {
		this.groups = groups;
	}

	public Collection<String> getGroups() {
		return groups;
	}
}

