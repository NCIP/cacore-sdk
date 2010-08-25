package gov.nih.nci.sdk.core;

import javax.script.CompiledScript;

public interface ScriptingUtil
{
	public void bind(String _name, Object _value);
	public void loadScript(String _scriptResource);
	public Object executeScript(String[] _scriptResources);
	public Object executeScript(String _scriptResource);
}