/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.core;

import java.util.Map;
import java.util.HashMap;

public class OmniScriptingUtil
   implements ScriptingUtil
{
	static
	{
		Map<String, String> tempExtensionToScriptingUtilMap = new HashMap<String, String>();

		//We use a special scripting util for JavaScript in order to
		//get the advanced engine features of the latest versions of
		//JavaScript.  
		tempExtensionToScriptingUtilMap.put("js", "gov.nih.nci.sdk.core.JsScriptingUtil");

		//All other scripting engines are loaded via the standard
		//JSR-223 scripting support in java 1.5 and 1.6 packages.
		tempExtensionToScriptingUtilMap.put("bsh", "gov.nih.nci.sdk.core.Jsr223ScriptingUtil");
		tempExtensionToScriptingUtilMap.put("java", "gov.nih.nci.sdk.core.Jsr223ScriptingUtil");
		tempExtensionToScriptingUtilMap.put("rb", "gov.nih.nci.sdk.core.Jsr223ScriptingUtil");
		tempExtensionToScriptingUtilMap.put("ruby", "gov.nih.nci.sdk.core.Jsr223ScriptingUtil");
		tempExtensionToScriptingUtilMap.put("py", "gov.nih.nci.sdk.core.Jsr223ScriptingUtil");
		tempExtensionToScriptingUtilMap.put("python", "gov.nih.nci.sdk.core.Jsr223ScriptingUtil");
		tempExtensionToScriptingUtilMap.put("gr", "gov.nih.nci.sdk.core.Jsr223ScriptingUtil");
		tempExtensionToScriptingUtilMap.put("groovy", "gov.nih.nci.sdk.core.Jsr223ScriptingUtil");

		extensionToScriptingUtilMap = tempExtensionToScriptingUtilMap;
	}

	private ScriptingUtil scriptingUtil;
	private static Map<String, String> extensionToScriptingUtilMap;

	private ScriptingUtil getScriptingUtil() { return scriptingUtil; }

	private void setScriptingUtil(ScriptingUtil _scriptingUtil) { scriptingUtil = _scriptingUtil; }

	private OmniScriptingUtil() {}
	
	public OmniScriptingUtil(String _extension)
	{
		String scriptingUtilName = null;

		try
		{
			scriptingUtilName = extensionToScriptingUtilMap.get(_extension);
			if (scriptingUtilName == null) { throw new RuntimeException("Scripting not supported for files ending in: " + _extension); }

			setScriptingUtil((ScriptingUtil) Class.forName(scriptingUtilName).newInstance());                   
		}
		catch(ClassNotFoundException t)
		{
			throw new RuntimeException("Please make sure the appropriate jar files for scripting utility: " +
									   scriptingUtilName + " are place in the lib/engines directory in order to run scripts of extension: " + _extension);
		}
		catch(Throwable t)
		{
			throw new RuntimeException(t);
		}
	}

	public static boolean hasScriptingUtil(String _extension)
	{
		return (extensionToScriptingUtilMap.containsKey(_extension) == true);
	}

	public void bind(String _name, Object _value)
	{
		getScriptingUtil().bind(_name, _value);
	}

	public void loadScript(String _scriptResource)
	{
		getScriptingUtil().loadScript(_scriptResource);
	}

	public Object executeScript(String _scriptResource)
	{
		return getScriptingUtil().executeScript(_scriptResource);
	}

	public Object executeScript(String[] _scriptResources)
	{
		return getScriptingUtil().executeScript(_scriptResources);
	}
}