package gov.nih.nci.sdk.core;

import java.util.Map;
import java.util.HashMap;

public class ScriptContext
{
	private String script;
	private Map globalMemory;
	private Map memory;
	private GeneratorContext generatorContext;
	private boolean isAborted;
	private MessageManager errorManager;
	private MessageManager warningManager;

	public String getScript() { return script; }
	public Map getGlobalMemory() { return globalMemory; }
	public Map getMemory() { return memory; }
	public boolean getIsAborted() { return isAborted; }
	private GeneratorContext getGeneratorContext() { return generatorContext; }
	public MessageManager getErrorManager() { return errorManager; }
	public MessageManager getWarningManager() { return warningManager; }

	private void setScript(String _script) { script = _script; }
	private void setGlobalMemory(Map _globalMemory) { globalMemory = _globalMemory; }
	private void setMemory(Map _memory) { memory = _memory; }
	private void setIsAborted(boolean _isAborted) { isAborted = _isAborted; }
	private void setGeneratorContext(GeneratorContext _generatorContext) { generatorContext = _generatorContext; }
	private void setErrorManager(MessageManager _errorManager) { errorManager = _errorManager; }
	private void setWarningManager(MessageManager _warningManager) { warningManager = _warningManager; }

	private ScriptContext () { setIsAborted(false); }

	public ScriptContext(String _script, GeneratorContext _generatorContext)
	{
		setScript(_script);
		setIsAborted(false);
		setGeneratorContext(_generatorContext);
		setGlobalMemory(_generatorContext.getMemory());
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
		getErrorManager().clearMessages();
		getWarningManager().clearMessages();
	}
}