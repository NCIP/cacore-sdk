package gov.nih.nci.sdk.core;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EClassifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generator
	implements GeneratorConductor
{
	private Map<String, ScriptContext> scriptContextMap;

	private Map<String, ScriptContext> getScriptContextMap() { return scriptContextMap; }
	private void setScriptContextMap(Map<String, ScriptContext> _scriptContextMap) { scriptContextMap = _scriptContextMap; }

	public Generator()
	{
		setScriptContextMap(new HashMap<String, ScriptContext>());
	}
	
	public void compile(GeneratorContext _generatorContext)
	{
		level0Validate(_generatorContext);

		if (_generatorContext.hasErrors() == false)
		{
			conduct(_generatorContext);
		}
	}

	public void conduct(GeneratorContext _generatorContext)
	{
		List<String> scriptList = determineGeneratorScripts(_generatorContext);
		
		for (EClassifier eClassifier: _generatorContext.getEPackage().getEClassifiers())
		{
			if (_generatorContext.getDomainSet().contains(eClassifier.getName()) == true)
			{
				for (String script: scriptList)
				{
					ScriptContext scriptContext = determineScriptContext(script, _generatorContext);	
					executeScript(script, scriptContext);
					processScriptContext(scriptContext);

					//BREAK LOOP condition: execute script set
					//generator context to abort operation
					if (_generatorContext.isAborted() == true) { break; }
				}
			}

			//BREAK LOOP condition: execute script set
			//generator context to abort operation
			if (_generatorContext.isAborted() == true) { break; }
		}
	}

	public void executeScript(String _script, ScriptContext _scriptContext)
	{
		ScriptingUtil scriptingUtil = new OmniScriptingUtil(determineFileExtension(_script));

		scriptingUtil.bind("SCRIPT_UTIL", scriptingUtil);
		scriptingUtil.bind("SCRIPT_CONTEXT", _scriptContext);
		
		String[] scriptResources = new String[1];

		scriptResources[0] = _script;

		try
		{
			scriptingUtil.executeScript(scriptResources);
		}
		catch(Throwable t)
		{
			//TODO log throwable to console ...
			_scriptContext.getErrorManager().add(_script, "SEVERE", t.toString());
		}
	}

	public String determineFileExtension(String _script)
	{
		String extension = "";

		if (_script.contains(".") == true)
		{
			String[] tokenArray = _script.split("\\.");
			extension = tokenArray[tokenArray.length - 1];
		}

		return extension;
	}
	
	public void processScriptContext(ScriptContext _scriptContext)
	{
		_scriptContext.reset();
	}

	public ScriptContext determineScriptContext(String _script, GeneratorContext _generatorContext)
	{
		ScriptContext scriptContext = getScriptContextMap().get(_script);

		if (scriptContext == null)
		{
			scriptContext = new ScriptContext(_script, _generatorContext);
			getScriptContextMap().put(_script, scriptContext);
		}
		
		return scriptContext;
	}
	
	public java.util.List<Message> level0Validate(GeneratorContext _generatorContext)
	{
		//TODO
		return new java.util.ArrayList<Message>();
	}

	public List<String> determineGeneratorScripts(GeneratorContext _generatorContext)
	{
		List<String> generatorScriptList = new java.util.ArrayList<String>();
		
		java.io.File baseDirectory = new java.io.File(_generatorContext.getGeneratorBase());

		if (baseDirectory.isDirectory() == true)
		{
			java.io.File[] directoryFiles = baseDirectory.listFiles();

			for (int i = 0; i < directoryFiles.length; i++)
			{
				if (directoryFiles[i].isDirectory() == false)
				{
					if (directoryFiles[i].isDirectory() == false)
					{
						String fileName = directoryFiles[i].getName();
						
						if (OmniScriptingUtil.hasScriptingUtil(determineFileExtension(fileName)) == true)
						{
							generatorScriptList.add(directoryFiles[i].getAbsolutePath());
						}
					}
				}
			}
		}
		else
		{
			throw new RuntimeException("Generator base URI: " + _generatorContext.getGeneratorBase() + " is not a directory.");
		}
		
		return generatorScriptList;
	}
}