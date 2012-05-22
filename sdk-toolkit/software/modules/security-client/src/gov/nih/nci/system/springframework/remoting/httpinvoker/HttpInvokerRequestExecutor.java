package gov.nih.nci.system.springframework.remoting.httpinvoker;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationCredentialsNotFoundException;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.acegisecurity.providers.x509.X509AuthenticationToken;
import org.apache.commons.codec.binary.Base64;
import org.springframework.remoting.httpinvoker.HttpInvokerClientConfiguration;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor;


public class HttpInvokerRequestExecutor extends SimpleHttpInvokerRequestExecutor{

	protected HttpURLConnection openConnection(HttpInvokerClientConfiguration config) throws IOException
	{
		String url = config.getServiceUrl();
		if(url == null )
			throw new IOException("Service URL can not be empty");

		return super.openConnection(config);
	}

    /**
     * Called every time a HTTP invocation is made.<p>Simply allows the parent to setup the connection, and
     * then adds an <code>Authorization</code> HTTP header property that will be used for BASIC authentication.</p>
     *  <p>The <code>SecurityContextHolder</code> is used to obtain the relevant principal and credentials.</p>
     *
     * @param con the HTTP connection to prepare
     * @param contentLength the length of the content to send
     *
     * @throws IOException if thrown by HttpURLConnection methods
     * @throws AuthenticationCredentialsNotFoundException if the <code>SecurityContextHolder</code> does not contain a
     *         valid <code>Authentication</code> with both its <code>principal</code> and <code>credentials</code> not
     *         <code>null</code>
     */
    protected void prepareConnection(HttpURLConnection con, int contentLength)
        throws IOException, AuthenticationCredentialsNotFoundException {
        super.prepareConnection(con, contentLength);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth!=null && auth instanceof UsernamePasswordAuthenticationToken && (auth.getName() != null) && (auth.getCredentials() != null))
        {
            String base64 = auth.getName() + ":" + auth.getCredentials().toString();
            con.setRequestProperty("Authorization", "Basic " + new String(Base64.encodeBase64(base64.getBytes())));
        }
    }

}