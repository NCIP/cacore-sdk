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
			determineGeneratorScripts(_generatorContext);
			level1Validate(_generatorContext);
			
			if (_generatorContext.hasErrors() == false)
			{
				conduct(_generatorContext);
			}
		}
	}

	private void conduct(GeneratorContext _generatorContext)
	{
		java.util.List<EClassifier> eClassifierList = new java.util.ArrayList<EClassifier>();
		gov.nih.nci.sdk.util.EcoreUtil.findAllEClassModelElements(eClassifierList, _generatorContext.getEPackage());

		for (EClassifier eClassifier: eClassifierList)
		{			
			if (_generatorContext.getDomainSet().contains(gov.nih.nci.sdk.util.EcoreUtil.determineFullyQualifiedName(eClassifier)) == true)
			{
				for (java.io.File script: _generatorContext.getGeneratorScriptFileList())
				{
					if (isValidationScript(script) == false)
					{
						ScriptContext scriptContext = determineScriptContext(script, eClassifier.getName(), _generatorContext);
						executeScript(script, scriptContext);
						processScriptContext(scriptContext, _generatorContext);

						//BREAK LOOP condition: execute script set
						//generator context to abort operation
						if (_generatorContext.isAborted() == true) { break; }
					}
				}
			}

			//BREAK LOOP condition: execute script set
			//generator context to abort operation
			if (_generatorContext.isAborted() == true) { break; }
		}
	}

	public void executeScript(java.io.File _script, ScriptContext _scriptContext)
	{
		ScriptingUtil scriptingUtil = new OmniScriptingUtil(determineFileExtension(_script));

		scriptingUtil.bind("SCRIPT_UTIL", scriptingUtil);
		scriptingUtil.bind("SCRIPT_CONTEXT", _scriptContext);
		
		String[] scriptResources = new String[1];

		scriptResources[0] = _script.getAbsolutePath();

		try
		{
			scriptingUtil.executeScript(scriptResources);
		}
		catch(Throwable t)
		{
			//TODO log throwable to console ...
			_scriptContext.getErrorManager().add(_script.getAbsolutePath(), "SEVERE", t.toString());
		}
	}

	public String determineFileExtension(java.io.File _script)
	{
		String extension = "";
		String fileName = _script.getName();

		if (fileName.contains(".") == true)
		{
			String[] tokenArray = fileName.split("\\.");
			extension = tokenArray[tokenArray.length - 1];
		}

		return extension;
	}
	
	public void processScriptContext(ScriptContext _scriptContext, GeneratorContext _generatorContext)
	{
		_generatorContext.getErrorManager().add(_scriptContext.getErrorManager());
		_generatorContext.getWarningManager().add(_scriptContext.getWarningManager());
		
		_scriptContext.reset();
	}

	public ScriptContext determineScriptContext(java.io.File _script, String _focusDomain, GeneratorContext _generatorContext)
	{
		ScriptContext scriptContext = getScriptContextMap().get(_script.getAbsolutePath());

		if (scriptContext == null)
		{
			scriptContext = new ScriptContext(_script.getAbsolutePath(), _generatorContext);			
			getScriptContextMap().put(_script.getAbsolutePath(), scriptContext);
		}

		scriptContext.setFocusDomain(_focusDomain);
		scriptContext.setGeneratorContext(_generatorContext);
		
		return scriptContext;
	}

	private java.io.File findValidateScriptFile(List<java.io.File> _fileList)
	{
		java.io.File validateScriptFile = null;

		for (java.io.File file: _fileList)
		{
			if (isValidationScript(file) == true)
			{
					validateScriptFile = file;
					//BREAK: choosing first file that satisfies the
					//validation rule criteria.
					break;
			}
		}

		return validateScriptFile;
	}

	public boolean isValidationScript(java.io.File _file)
	{
		return (_file.exists() == true &&
				_file.isDirectory() == false &&
				_file.getName().startsWith("level1validate.") == true &&
				OmniScriptingUtil.hasScriptingUtil(determineFileExtension(_file)) == true);
	}

	public void level1Validate(GeneratorContext _generatorContext)
	{
		java.io.File validateScriptFile = findValidateScriptFile(_generatorContext.getGeneratorScriptFileList());

		if (validateScriptFile != null)
		{
			ScriptContext scriptContext = determineScriptContext(validateScriptFile, "", _generatorContext);	
			executeScript(validateScriptFile, scriptContext);
		}
	}

	public void level0Validate(GeneratorContext _generatorContext)
	{
		//TODO
	}

	public void determineGeneratorScripts(GeneratorContext _generatorContext)
	{
		List<java.io.File> generatorScriptList = new java.util.ArrayList<java.io.File>();
		
		java.io.File baseDirectory = new java.io.File(_generatorContext.getGeneratorBase());

		if (baseDirectory.isDirectory() == true)
		{
			java.io.File[] directoryFiles = baseDirectory.listFiles();

			for (int i = 0; i < directoryFiles.length; i++)
			{
				if (directoryFiles[i].isDirectory() == false &&
					  OmniScriptingUtil.hasScriptingUtil(determineFileExtension(directoryFiles[i])) == true)
				{
					generatorScriptList.add(directoryFiles[i]);
				}
			}
		}
		else
		{
			throw new RuntimeException("Generator base URI: " + _generatorContext.getGeneratorBase() + " is not a directory.");
		}
		
		_generatorContext.setGeneratorScriptFileList(generatorScriptList);
	}
}