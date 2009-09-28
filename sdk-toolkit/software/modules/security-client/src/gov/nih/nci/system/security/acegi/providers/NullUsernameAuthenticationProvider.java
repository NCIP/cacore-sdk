package gov.nih.nci.system.security.acegi.authentication;

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