package gov.nih.nci.sdk.core;

import org.eclipse.emf.ecore.EPackage;

import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import java.net.URI;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.StringTemplate;

public class ScriptContext
{
	private String script;
	private URI generatorBase;
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
	private StringTemplateGroup templateGroup;
	private Logger logger;
	
	public String getScript() { return script; }
	public URI getGeneratorBase() { return generatorBase; }
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
	public StringTemplateGroup getTemplateGroup() { return templateGroup; }
	public Logger getLogger() { return logger; }
	
	private void setScript(String _script) { script = _script; }
	public void setGeneratorBase(URI _generatorBase) { generatorBase = _generatorBase; }
	public void setTargetBase(URI _targetBase) { targetBase = _targetBase; }
	public void setProperties(Properties _properties) { properties = _properties; }
	private void setGlobalMemory(Map _globalMemory) { globalMemory = _globalMemory; }
	private void setMemory(Map _memory) { memory = _memory; }
	public void setFocusDomain(String _focusDomain) { focusDomain = _focusDomain; }
	private void setEPackage(EPackage _ePackage) { ePackage = _ePackage; }
	private void setIsAborted(boolean _isAborted) { isAborted = _isAborted; }
	protected void setGeneratorContext(GeneratorContext _generatorContext) { generatorContext = _generatorContext; }
	private void setErrorManager(MessageManager _errorManager) { errorManager = _errorManager; }
	private void setWarningManager(MessageManager _warningManager) { warningManager = _warningManager; }
	public void setTemplateGroup(StringTemplateGroup _templateGroup) { templateGroup = _templateGroup; }
	public void setLogger(Logger _logger) { logger = _logger; }
	
	private ScriptContext () { setIsAborted(false); }

	public ScriptContext(String _script,
						 EPackage _ePackage,
						 Map _globalMemory,
						 Properties _properties,
						 URI _targetBase,
						 URI _generatorBase,
						 Logger _logger)
	{
		setScript(_script);
		setIsAborted(false);
		setEPackage(_ePackage);
		setGlobalMemory(_globalMemory);
		setProperties(_properties);
		setTargetBase(_targetBase);
		setGeneratorBase(_generatorBase);
		setMemory(new HashMap());
		setErrorManager(new MessageManager());
		setWarningManager(new MessageManager());
		setTemplateGroup(new StringTemplateGroup(getGeneratorBase() + java.io.File.separator + "template"));
		setLogger(_logger);
	}

	public ScriptContext(String _scriptAbsolutePath, GeneratorContext _generatorContext)
	{
		setScript(_scriptAbsolutePath);
		setIsAborted(false);
		setEPackage(_generatorContext.getEPackage());
		setGlobalMemory(_generatorContext.getMemory());
		setProperties(_generatorContext.getProperties());
		setTargetBase(_generatorContext.getTargetBase());
		setGeneratorBase(_generatorContext.getGeneratorBase());
		setMemory(new HashMap());
		setErrorManager(new MessageManager());
		setWarningManager(new MessageManager());
		setTemplateGroup(new StringTemplateGroup(getGeneratorBase() + java.io.File.separator + "template"));
		setLogger(_generatorContext.getLogger());
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

	public void logError(Throwable _t)
	{
		_t.printStackTrace();
		logError(_t.toString());
	}

	public void logError(String _message)
	{
		getLogger().severe(_message);
		getErrorManager().add(getScript(), "severe", _message);
	}

	public void logWarning(String _message)
	{
		getLogger().warning(_message);
		getWarningManager().add(getScript(), "warning", _message);
	}

	public void logInfo(String _message)
	{
		getLogger().info(_message);
	}

	public void reset()
	{
		setFocusDomain(null);
		getErrorManager().clearMessages();
		getWarningManager().clearMessages();
	}

	public StringTemplate getTemplate(String _templateName)
	{
		return getTemplateGroup().getInstanceOf(_templateName);
	}
}