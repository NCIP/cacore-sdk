/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.core;

import org.eclipse.emf.ecore.EPackage;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

public class GeneratorContext
{
	private URI generatorBase;
	private URI targetBase;
	private EPackage ePackage;
	private Set<String> domainSet;
	private MessageManager errorManager;
	private MessageManager warningManager;
	private boolean isAborted;
	private Map memory;
	private List<java.io.File> generatorScriptFileList;
	private Properties properties;
	private Logger logger;
	
	public URI getGeneratorBase() { return generatorBase; }
	public URI getTargetBase() { return targetBase; }
	public EPackage getEPackage() { return ePackage; }
	public Set<String> getDomainSet() { return domainSet; }
	public MessageManager getErrorManager() { return errorManager; }
	public MessageManager getWarningManager() { return warningManager; }
	private boolean getIsAborted() { return isAborted; }
	public Map getMemory() { return memory; }
	protected List<java.io.File> getGeneratorScriptFileList() { return generatorScriptFileList; }
	public Properties getProperties() { return properties; }
	public Logger getLogger() { return logger; }
	
	public void setGeneratorBase(URI _generatorBase) { generatorBase = _generatorBase; }
	public void setTargetBase(URI _targetBase) { targetBase = _targetBase; }
	public void setEPackage(EPackage _ePackage) { ePackage = _ePackage; }
	public void setDomainSet(Set<String> _domainSet) { domainSet = _domainSet; }
	private void setErrorManager(MessageManager _errorManager) { errorManager = _errorManager; }
	private void setWarningManager(MessageManager _warningManager) { warningManager = _warningManager; }
	private void setIsAborted(boolean _isAborted) { isAborted = _isAborted; }
	private void setMemory(Map _memory) { memory = _memory; }
	protected void setGeneratorScriptFileList(List<java.io.File> _generatorScriptFileList) { generatorScriptFileList = _generatorScriptFileList; }
	public void setProperties(Properties _properties) { properties = _properties; }
	public void setLogger(Logger _logger) { logger = _logger; }

	public GeneratorContext() {}
	
	public GeneratorContext(URI _generatorBase,
							URI _targetBase,
							Properties _properties,
							EPackage _ePackage,
							java.util.Set<String> _domainSet,
						    Logger _logger)
	{
		setGeneratorBase(_generatorBase);
		setTargetBase(_targetBase);
		setEPackage(_ePackage);
		setDomainSet(_domainSet);
		setProperties(_properties);
		setErrorManager(new MessageManager());
		setWarningManager(new MessageManager());
		setIsAborted(false);
		setMemory(new HashMap());
		setLogger(_logger);
	}

	public boolean hasErrors()
	{
		return getErrorManager().hasMessages();
	}

	public boolean hasWarnings()
	{
		return getWarningManager().hasMessages();
	}

	public void abort()
	{
		setIsAborted(true);
	}

	public boolean isAborted()
	{
		return (getIsAborted() == true);
	}

	public List<java.io.File> copyGeneratorScriptFileList()
	{
		List<java.io.File> generatorScriptFileList = new ArrayList<java.io.File>();
		generatorScriptFileList.addAll(getGeneratorScriptFileList());
		
		return generatorScriptFileList;
	}
}