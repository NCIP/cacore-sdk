package gov.nih.nci.system.web.struts.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <code>Set welcome message.</code>
 */
public class BaseActionSupport extends ActionSupport implements
		ServletContextAware, SessionAware {

	private static final long serialVersionUID = 1234567890L;

	private static Logger log = Logger.getLogger(BaseActionSupport.class
			.getName());

	protected ServletContext servletContext;
	private Map session;

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	public void setSession(Map session) {
		this.session = session;
	}
	
	protected static void debugSessionAttributes(SessionMap session){
		
		System.out.println("Debugging Session Attributes");
		
		Set entrySet = session.entrySet();
		Iterator entryIter = entrySet.iterator();
		
		Set keySet = session.keySet();
		Iterator keyIter = keySet.iterator();
		
		String key;
		Object value;
		while (keyIter.hasNext()){
			key = (String)keyIter.next();
			value = session.get(key);
			System.out.println("Session Key: " + key + "; value = " + value );
		}
		
		Object next;
		while (entryIter.hasNext()){		
			next = entryIter.next();
			System.out.println(next.getClass().getName() + ": " + next );
		}
	}	
}
