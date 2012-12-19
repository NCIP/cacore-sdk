

package gov.nih.nci.system.security.acegi.authentication;

import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSCredentialExpiredException;
import gov.nih.nci.security.exceptions.CSFirstTimeLoginException;
import gov.nih.nci.security.exceptions.CSUserDisabledException;
import gov.nih.nci.security.exceptions.CSInputException;
import gov.nih.nci.security.exceptions.CSInsufficientAttributesException;
import gov.nih.nci.security.exceptions.CSLoginException;

import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.AccountExpiredException;
import org.acegisecurity.CredentialsExpiredException;
import org.acegisecurity.providers.AuthenticationProvider;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;


/**
 * An {@link AuthenticationProvider} implementation that retrieves user details from an {@link UserDetailsService}.
 *
 */
public class CSMAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {   
    
    private CSMUserDetailsService userDetailsService;
    private boolean includeDetailsObject = true;

    //~ Methods ========================================================================================================

    protected void additionalAuthenticationChecks(UserDetails userDetails,
        UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {

        
    	// 	Use CSM authenticationManager to authenticate User.
        try {
			this.userDetailsService.authenticationManagerInstance().authenticate(userDetails.getUsername(), authentication.getCredentials().toString());
		}
        catch(CSCredentialExpiredException e)
        {
        	//System.out.println("CSCredentialExpiredException **********111********");
        	e.printStackTrace();
        	//throw e;
        	
			 throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Credentials expired. Need to reset the password."), e);
        }
        catch(CSFirstTimeLoginException e)
        {
			 throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "First time login. Need to reset the password."), e);
        }
        catch(CSUserDisabledException e)
        {
			 throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Account is not active."), e);
        } catch (CSLoginException e) {
			 throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), e);
		} catch (CSInputException e) {
			 throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), e);
		} catch (CSConfigurationException e) {
			 throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), e);
		} catch (CSInsufficientAttributesException e) {
			 throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), e);
		} catch (CSException e) {
			 throw new BadCredentialsException(messages.getMessage(
	                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), e);
		}
        
    }

    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
        Assert.notNull(this.userDetailsService.authorizationManagerInstance(),"Unable to initialize Authorization Manager");
    }
   
    public CSMUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        UserDetails loadedUser = null;

        try {
            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        } catch (DataAccessException repositoryProblem) {
            throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new AuthenticationServiceException(
                "UserDetailsService returned null, which is an interface contract violation");
        }

        return loadedUser;
    }


    public void setUserDetailsService(CSMUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

	public boolean isIncludeDetailsObject() {
		return includeDetailsObject;
	}

	public void setIncludeDetailsObject(boolean includeDetailsObject) {
		this.includeDetailsObject = includeDetailsObject;
	}
}
