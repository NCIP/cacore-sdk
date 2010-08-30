package gov.nih.nci.sdk.core;

import org.eclipse.emf.ecore.EPackage;

import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

import java.net.URI;

public class ScriptContext
{
	private String script;
	private URI targetBase;
	private Properties properties;
	private Map globalMemory;
	private Map memory;
	private String focusDomain;
	private EPackage ePackage;
	private GeneratorContext generatorContext;
	private boolean isAborted;
	private MessageManager errorManager;
	private MessageManager warningManager;

	public String getScript() { return script; }
	public URI getTargetBase() { return targetBase; }
	public Properties getProperties() { return properties; }
	public Map getGlobalMemory() { return globalMemory; }
	public Map getMemory() { return memory; }
	public String getFocusDomain() { return focusDomain; }
	public EPackage getEPackage() { return ePackage; }
	public boolean getIsAborted() { return isAborted; }
	private GeneratorContext getGeneratorContext() { return generatorContext; }
	public MessageManager getErrorManager() { return errorManager; }
	public MessageManager getWarningManager() { return warningManager; }

	private void setScript(String _script) { script = _script; }
	public void setTargetBase(URI _targetBase) { targetBase = _targetBase; }
	public void setProperties(Properties _properties) { properties = _properties; }
	private void setGlobalMemory(Map _globalMemory) { globalMemory = _globalMemory; }
	private void setMemory(Map _memory) { memory = _memory; }
	protected void setFocusDomain(String _focusDomain) { focusDomain = _focusDomain; }
	private void setEPackage(EPackage _ePackage) { ePackage = _ePackage; }
	private void setIsAborted(boolean _isAborted) { isAborted = _isAborted; }
	protected void setGeneratorContext(GeneratorContext _generatorContext) { generatorContext = _generatorContext; }
	private void setErrorManager(MessageManager _errorManager) { errorManager = _errorManager; }
	private void setWarningManager(MessageManager _warningManager) { warningManager = _warningManager; }

	private ScriptContext () { setIsAborted(false); }

	public ScriptContext(String _script,
						 EPackage _ePackage,
						 Map _globalMemory,
						 Properties _properties,
						 URI _targetBase)
	{
		setScript(_script);
		setIsAborted(false);
		setEPackage(_ePackage);
		setGlobalMemory(_globalMemory);
		setProperties(_properties);
		setTargetBase(_targetBase);
		setMemory(new HashMap());
		setErrorManager(new MessageManager());
		setWarningManager(new MessageManager());
	}

	public void abort()
	{
		setIsAborted(true);
		if (getGeneratorContext() != null) { getGeneratorContext().abort(); }
	}

	public boolean isAborted()
	{
		return (getIsAborted() == true);
	}

	public void reset()
	{
		setFocusDomain(null);
		getErrorManager().clearMessages();
		getWarningManager().clearMessages();
	}
}