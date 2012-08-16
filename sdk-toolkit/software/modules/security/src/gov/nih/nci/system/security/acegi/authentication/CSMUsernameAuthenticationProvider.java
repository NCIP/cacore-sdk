package gov.nih.nci.system.security.acegi.authentication;

import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSInputException;
import gov.nih.nci.security.exceptions.CSInsufficientAttributesException;
import gov.nih.nci.security.exceptions.CSLoginException;

import org.acegisecurity.AccountExpiredException;
import org.acegisecurity.AcegiMessageSource;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationServiceException;
import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.CredentialsExpiredException;
import org.acegisecurity.DisabledException;
import org.acegisecurity.LockedException;
import org.acegisecurity.providers.AuthenticationProvider;


import gov.nih.nci.system.security.acegi.providers.UsernameAuthenticationToken;
import org.acegisecurity.providers.dao.UserCache;
import org.acegisecurity.providers.dao.cache.NullUserCache;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import org.springframework.beans.factory.InitializingBean;
import org.acegisecurity.providers.AuthenticationProvider;

/**
 * An {@link AuthenticationProvider} implementation that retrieves user details from an {@link UserDetailsService}.
 *
 */
public class CSMUsernameAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware
{

	protected MessageSourceAccessor messages = AcegiMessageSource.getAccessor();
	private UserCache userCache = new NullUserCache();

	private CSMUserDetailsService userDetailsService;
	private boolean includeDetailsObject = true;

	//~ Methods ========================================================================================================


	public final void afterPropertiesSet() throws Exception
	{
		Assert.notNull(this.userCache, "A user cache must be set");
		Assert.notNull(this.messages, "A message source must be set");
		Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
		Assert.notNull(this.userDetailsService.authorizationManagerInstance(), "Unable to initialize Authorization Manager");
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		Assert.isInstanceOf(UsernameAuthenticationToken.class, authentication, messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports", "Only UsernameAuthenticationToken is supported"));

		// Determine username
		String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

		boolean cacheWasUsed = true;
		UserDetails user = this.userCache.getUserFromCache(username);

		if (user == null)
		{
			cacheWasUsed = false;

			try
			{
				user = retrieveUser(username, (UsernameAuthenticationToken) authentication);
			} 
			catch (UsernameNotFoundException notFound)
			{
				throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
			}
			Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
		}

		if (!user.isAccountNonLocked())
		{
			throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"));
		}

		if (!user.isEnabled())
		{
			throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"));
		}

		if (!user.isAccountNonExpired())
		{
			throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"));
		}

		// This check must come here, as we don't want to tell users
		// about account status unless they presented the correct credentials
		try
		{
			additionalAuthenticationChecks(user, (UsernameAuthenticationToken) authentication);
		} catch (AuthenticationException exception)
		{
			if (cacheWasUsed)
			{
				// There was a problem, so try again after checking
				// we're using latest data (ie not from the cache)
				cacheWasUsed = false;
				user = retrieveUser(username, (UsernameAuthenticationToken) authentication);
				additionalAuthenticationChecks(user, (UsernameAuthenticationToken) authentication);
			} else
			{
				throw exception;
			}
		}

		if (!user.isCredentialsNonExpired())
		{
			throw new CredentialsExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
		}

		if (!cacheWasUsed)
		{
			this.userCache.putUserInCache(user);
		}

		return createSuccessAuthentication(user, authentication, user);
	}

	protected final UserDetails retrieveUser(String username, UsernameAuthenticationToken authentication) throws AuthenticationException
	{
		UserDetails loadedUser;

		try
		{
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);
		} catch (DataAccessException repositoryProblem)
		{
			throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null)
		{
			throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}


	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernameAuthenticationToken authentication) throws AuthenticationException
	{
		// 	Use CSM authenticationManager to authenticate User.
		if (userDetails.getUsername() == null)
			throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"), includeDetailsObject ? userDetails : null);
	}
	
	
	/**
	 * Creates a successful {@link Authentication} object.<p>Protected so subclasses can override.</p>
	 *  <p>Subclasses will usually store the original credentials the user supplied (not salted or encoded
	 * passwords) in the returned <code>Authentication</code> object.</p>
	 *
	 * @param principal that should be the principal in the returned object 
	 * @param authentication that was presented to the provider for validation
	 * @param user that was loaded by the implementation
	 *
	 * @return the successful authentication token
	 */
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user)
	{
		UsernameAuthenticationToken result = new UsernameAuthenticationToken(principal, user.getAuthorities());
		result.setDetails(authentication.getDetails());
		return result;
	}


	public void setMessageSource(MessageSource messageSource)
	{
		this.messages = new MessageSourceAccessor(messageSource);
	}

	public UserCache getUserCache()
	{
		return userCache;
	}

	public void setUserCache(UserCache userCache)
	{
		this.userCache = userCache;
	}
	
	public CSMUserDetailsService getUserDetailsService()
	{
		return userDetailsService;
	}

	public void setUserDetailsService(CSMUserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

	public boolean isIncludeDetailsObject()
	{
		return includeDetailsObject;
	}

	public void setIncludeDetailsObject(boolean includeDetailsObject)
	{
		this.includeDetailsObject = includeDetailsObject;
	}
	

	public boolean supports(Class authentication)
	{
		System.out.println("CSMUsernameAuthenticationProvider*******"+authentication);
		System.out.println("CSMUsernameAuthenticationProvider*******"+authentication.getName());
		boolean flag = false;
		try
		{
		flag = (UsernameAuthenticationToken.class.isAssignableFrom(authentication));
		System.out.println("CSMUsernameAuthenticationProvider*******"+flag);
		//throw new Exception();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}

}
