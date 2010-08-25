package gov.nih.nci.sdk.core;

import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.CompiledScript;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;

public class Jsr223ScriptingUtil
   implements ScriptingUtil
{
	static
	{
		Map<String, String> tempExtensionToEngineMap = new HashMap<String, String>();

		tempExtensionToEngineMap.put("bsh", "bsh.engine.BshScriptEngineFactory");

		tempExtensionToEngineMap.put("java", "com.sun.script.groovy.GroovyScriptEngineFactory");
		tempExtensionToEngineMap.put("rb", "com.sun.script.jruby.JRubyScriptEngineFactory");
		tempExtensionToEngineMap.put("ruby", "com.sun.script.jruby.JRubyScriptEngineFactory");
		tempExtensionToEngineMap.put("py", "com.sun.script.jython.JythonScriptEngineFactory");
		tempExtensionToEngineMap.put("python", "com.sun.script.jython.JythonScriptEngineFactory");
		tempExtensionToEngineMap.put("gr", "com.sun.script.groovy.GroovyScriptEngineFactory");
		tempExtensionToEngineMap.put("groovy", "com.sun.script.groovy.GroovyScriptEngineFactory");

		extensionToEngineMap = tempExtensionToEngineMap;
	}

	private static Map<String, String> extensionToEngineMap;

	private static final Map<String, CompiledScript> scriptResourceMap = new HashMap<String, CompiledScript>();
	private ScriptEngine scriptEngine;
	private Map<String, Object> bindingsMap;

	private ScriptEngine getScriptEngine() { return scriptEngine; } 
	private Map<String, CompiledScript> getScriptResourceMap() { return scriptResourceMap; }
	private Map<String, Object> getBindingsMap() { return bindingsMap; }

	private void setScriptEngine(ScriptEngine _scriptEngine) { scriptEngine = _scriptEngine; }
	public void setBindingsMap(Map<String, Object> _bindingsMap) { bindingsMap = _bindingsMap; }

	public Jsr223ScriptingUtil(String _extension)
	{
		String factoryName = null;

		try
		{
			factoryName = extensionToEngineMap.get(_extension);
			if (factoryName == null) { throw new RuntimeException("Scripting not supported for files ending in: " + _extension); }

			ScriptEngineFactory factory = (ScriptEngineFactory) Class.forName(factoryName).newInstance();                   
			ScriptEngine scriptEngine = factory.getScriptEngine();

			setScriptEngine(scriptEngine);
			setBindingsMap(new HashMap<String, Object>());
		}
		catch(ClassNotFoundException t)
		{
			throw new RuntimeException("Please make sure the appropriate jar files for factory: " +
									   factoryName + " are place in the lib/engines directory in order to run scripts of extension: " + _extension);
		}
		catch(Throwable t)
		{
			throw new RuntimeException(t);
		}
	}

	private CompiledScript compileScript(String _scriptResource)
	{
		Reader reader = null;

		try
		{
			if (getScriptResourceMap().containsKey(_scriptResource) == false
				  && getScriptEngine() instanceof javax.script.Compilable)
			{
				reader = new InputStreamReader(findScript(_scriptResource));

				CompiledScript script = ((javax.script.Compilable) getScriptEngine()).compile(reader);

				synchronized(getScriptResourceMap())
				{
					getScriptResourceMap().put(_scriptResource, script);
				}
			}
		}
		catch(Throwable t)
		{
			throw new RuntimeException(t);
		}
		finally
		{
			if (reader != null) { try { reader.close(); } catch(Throwable t) {} }
		}

		return getScriptResourceMap().get(_scriptResource);
	}

	public static boolean hasScriptingEngine(String _extension)
	{
		return (extensionToEngineMap.containsKey(_extension) == true);
	}

	public void bind(String _name, Object _value)
	{
		getBindingsMap().put(_name, _value);
	}

	public void loadScript(String _scriptResource)
	{
		executeScript(_scriptResource);
	}

	private InputStream findScript(String _scriptResource)
	{
		String rapidDeploy = "no";
		InputStream inputStream = null;

		try
		{
			java.io.File file = new java.io.File(_scriptResource);

			if (file.exists() == true)
			{
				inputStream = new java.io.FileInputStream(file);
			}
		}
		catch(Throwable t)
		{
			throw new RuntimeException(t);
		}

		if (inputStream == null)
		{
			throw new RuntimeException("Unable to find script file. Make sure that this: " + _scriptResource + " exists.");
		}

		return inputStream;
	}

	public Object executeScript(String _scriptResource)
	{
		ScriptContext scriptContext = getScriptEngine().getContext();

		Object result = null;

		try
		{
			for (String key: getBindingsMap().keySet())
			{
				scriptContext.setAttribute(key, getBindingsMap().get(key), ScriptContext.ENGINE_SCOPE);
			}

			CompiledScript script = compileScript(_scriptResource);

			if (script != null)
			{
				result = script.eval(scriptContext);
			}
			else
			{
				result = getScriptEngine().eval(new InputStreamReader(findScript(_scriptResource)), scriptContext);
			}
		}
		catch(Throwable t)
		{
			throw new RuntimeException("Unable to execute script: " + _scriptResource + " for this reason: " + t.toString(), t);
		}

		return result;
	}

	public Object executeScript(String[] _scriptResources)
	{
		ScriptContext scriptContext = getScriptEngine().getContext();

		Object result = null;

		int i = 0;

		try
		{
			for (String key: getBindingsMap().keySet())
			{
				scriptContext.setAttribute(key, getBindingsMap().get(key), ScriptContext.ENGINE_SCOPE);
			}

			for (i = 0; i < _scriptResources.length; i++)
			{
				CompiledScript script = compileScript(_scriptResources[i]);

				if (script != null)
				{
					result = script.eval(scriptContext);
				}
				else
				{
					result = getScriptEngine().eval(new InputStreamReader(findScript(_scriptResources[i])), scriptContext);
				}
			}
		}
		catch(Throwable t)
		{
			throw new RuntimeException("Unable to execute script: " + _scriptResources[i] + " for this reason: " + t.toString(), t);
		}

		return result;
	}

	public Object execute(String _script)
	{
		Object result = null;

		if (_script == null)
		{
			throw new RuntimeException("Unable to execute null script");
		}

		ScriptContext scriptContext = getScriptEngine().getContext();

		try
		{
			result = getScriptEngine().eval(_script, scriptContext);
		}
		catch (Throwable t)
		{
			throw new RuntimeException("Unable to execute script: " + _script + " for this reason: " + t.toString(), t);
		}

		return result;
	}
}