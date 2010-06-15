package gov.nih.nci.common.util;

import java.util.ArrayList;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author Ekagra Software Technologes Limited ('Ekagra')
 * 
 * This class manages a ThreadLocal variable
 * 
 */
public class ClientInfoThreadVariable
{
	// The next serial number to be assigned

	private static ThreadLocal clientInfoThreadLocal = new ThreadLocal();

	public static void setClientInfo(ClientInfo clientInfo)
	{
		if (null != clientInfo)
			clientInfoThreadLocal.set(clientInfo);
	}

	public static int getRecordsCount()
	{
		ClientInfo clientInfo = (ClientInfo)clientInfoThreadLocal.get();
		if (clientInfo != null)
		{
			return clientInfo.getRecordsCount();
		}
		else
			return 0;
	}

	public static boolean getSearchCaseSensitivity()
	{
		ClientInfo clientInfo = (ClientInfo)clientInfoThreadLocal.get();
		if (clientInfo != null)
		{
			return clientInfo.getCaseSensitivity();
		}
		else
			return false;
	}
	
	public static boolean isClientRequest()
	{
		ClientInfo clientInfo = (ClientInfo)clientInfoThreadLocal.get();
		if (clientInfo != null)
			return true;
		else
			return false;
	}
	
	public static String getUserName()
	{
		ClientInfo clientInfo = (ClientInfo)clientInfoThreadLocal.get();
		if (clientInfo != null)
		{
			return clientInfo.getUserName();
		}
		else
			return null;
	}

	public static String getPassword()
	{
		ClientInfo clientInfo = (ClientInfo)clientInfoThreadLocal.get();
		if (clientInfo != null)
		{
			return clientInfo.getPassword();
		}
		else
			return null;
	}

}
