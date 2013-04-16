/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.acegi.providers;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.AbstractAuthenticationToken;

public class UsernameAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;
    private Object principal;

    public UsernameAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(false);
    }

    public UsernameAuthenticationToken(Object principal, GrantedAuthority[] authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); 
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public Object getCredentials() {
        return "dummy";
    }
    
    public void setAuthenticated(boolean isAuthenticated)
        throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                "Cannot set this token to trusted - use constructor containing GrantedAuthority[]s instead");
        }
        super.setAuthenticated(false);
    }
    
    
}
