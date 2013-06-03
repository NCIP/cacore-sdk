/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.core;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EClass;
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
		_generatorContext.getLogger().info("Level 0 validations completed");
		
		if (_generatorContext.hasErrors() == false)
		{
			_generatorContext.getLogger().info("Determinglevel 0 validations");
			determineGeneratorScripts(_generatorContext);
			level1Validate(_generatorContext);
			
			if (_generatorContext.hasErrors() == false)
			{
				conduct(_generatorContext);
			}
		}
	}
	
	private java.util.Set<String> deconstructClassName(String _fullyQualifiedClassName)
	{
		java.util.Set<String> deconstructedNameSet = new java.util.HashSet<String>();
		java.util.Stack<String> tokenStack = new java.util.Stack<String>(); 
		String[] tokenArray = _fullyQualifiedClassName.split("\\.");
		String token = "";
		for (int i = 0; i < tokenArray.length; i++) { tokenStack.push(tokenArray[i]); }
		
		while (tokenStack.isEmpty() == false)
		{
			token += tokenStack.pop();
			deconstructedNameSet.add(token);
			token += ".";
		}
		
		return deconstructedNameSet;
	}
	
	private void conduct(GeneratorContext _generatorContext)
	{
		_generatorContext.getLogger().info("Orchestrating artfact generation.");
		
		java.util.List<EClassifier> eClassifierList = new java.util.ArrayList<EClassifier>();
		gov.nih.nci.sdk.util.EcoreUtil.findAllEClassModelElements(eClassifierList, _generatorContext.getEPackage());

		for (EClassifier eClassifier: eClassifierList)
		{
			String fullyQualifiedPackageName = gov.nih.nci.sdk.util.EcoreUtil.getPackage((EClass)eClassifier);
			String fullyQualifiedClassName = 
				(fullyQualifiedPackageName != null && "".equals(fullyQualifiedPackageName) != true) ? fullyQualifiedPackageName + "." + eClassifier.getName() : eClassifier.getName();
				
			_generatorContext.getLogger().info("Considering domain:" + fullyQualifiedClassName);
			
			if (_generatorContext.getDomainSet().contains(fullyQualifiedClassName) == true)
			{
				_generatorContext.getLogger().info("Processing domain: " + fullyQualifiedClassName);
				
				for (java.io.File script: _generatorContext.getGeneratorScriptFileList())
				{
					if (isValidationScript(script) == false)
					{
						ScriptContext scriptContext = determineScriptContext(script, fullyQualifiedClassName, _generatorContext);
						executeScript(script, scriptContext);
						processScriptContext(scriptContext, _generatorContext);

						//BREAK LOOP condition: execute script set
						//generator context to abort operation
						if (_generatorContext.isAborted() == true) { break; }
					}
					else
					{
						_generatorContext.getLogger().info("Skipping validation script");
					}
				}
			}

			//BREAK LOOP condition: execute script set
			//generator context to abort operation
			if (_generatorContext.isAborted() == true)
			{
				_generatorContext.getLogger().warning("Generator execution prematurely aborted");
				break;
			}
		}
	}

	public void executeScript(java.io.File _script, ScriptContext _scriptContext)
	{
		_scriptContext.getLogger().info("Executing script: " + _script.getName());
		
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
		
		_scriptContext.getLogger().info("Script execution completed.");
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
		_generatorContext.getLogger().info("Determining script context for script: " + _script.getName());
		
		ScriptContext scriptContext = getScriptContextMap().get(_script.getAbsolutePath());

		if (scriptContext == null)
		{
			scriptContext = new ScriptContext(_script.getAbsolutePath(), _generatorContext);			
			getScriptContextMap().put(_script.getAbsolutePath(), scriptContext);
		}

		scriptContext.setFocusDomain(_focusDomain);
		scriptContext.setGeneratorContext(_generatorContext);
		
		_generatorContext.getLogger().info("Script context determined.");
		
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
		_generatorContext.getLogger().info("Performing level 1 validations");
		_generatorContext.getLogger().info("Finding level 1 validation script");	
		java.io.File validateScriptFile = findValidateScriptFile(_generatorContext.getGeneratorScriptFileList());
		
		if (validateScriptFile != null)
		{
			_generatorContext.getLogger().info("Level 1 validation script found: " + validateScriptFile.getName());		
			
			ScriptContext scriptContext = determineScriptContext(validateScriptFile, "", _generatorContext);	
			executeScript(validateScriptFile, scriptContext);
		}
		else
		{
			_generatorContext.getLogger().info("Level 1 validation script not found.");
		}
		
		_generatorContext.getLogger().info("Level 1 validations completed");
	}

	public void level0Validate(GeneratorContext _generatorContext)
	{
		//TODO
		_generatorContext.getLogger().info("Performing level 0 validations");
		_generatorContext.getLogger().info("Level 0 validations completed");
	}

	public void determineGeneratorScripts(GeneratorContext _generatorContext)
	{
		_generatorContext.getLogger().info("Finding generator scripts");
		
		List<java.io.File> generatorScriptList = new java.util.ArrayList<java.io.File>();
		
		_generatorContext.getLogger().info("Looking at base directory: " + _generatorContext.getGeneratorBase());
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
		
		_generatorContext.getLogger().info("Found: " + generatorScriptList.size() + " generator script(s).");
	}
}