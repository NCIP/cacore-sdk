/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.system.security.acegi.providers;

import gov.nih.nci.system.security.acegi.providers.UsernameAuthenticationToken;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.providers.AuthenticationProvider;

public class NullUsernameAuthenticationProvider implements AuthenticationProvider
{

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		return authentication;
	}

	public boolean supports(Class authentication) {
		return UsernameAuthenticationToken.class.isAssignableFrom(authentication);
	}

}