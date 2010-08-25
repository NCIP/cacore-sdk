package gov.nih.nci.sdk.core;

import org.eclipse.emf.ecore.EPackage;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GeneratorContext
{
	private URI generatorBase;
	private EPackage ePackage;
	private Set<String> domainSet;
	private MessageManager errorManager;
	private MessageManager warningManager;
	private boolean isAborted;
	private Map memory;

	public URI getGeneratorBase() { return generatorBase; }
	public EPackage getEPackage() { return ePackage; }
	public Set<String> getDomainSet() { return domainSet; }
	public MessageManager getErrorManager() { return errorManager; }
	public MessageManager getWarningManager() { return warningManager; }
	private boolean getIsAborted() { return isAborted; }
	public Map getMemory() { return memory; }
	
	public void setGeneratorBase(URI _generatorBase) { generatorBase = _generatorBase; }
	public void setEPackage(EPackage _ePackage) { ePackage = _ePackage; }
	public void setDomainSet(Set<String> _domainSet) { domainSet = _domainSet; }
	private void setErrorManager(MessageManager _errorManager) { errorManager = _errorManager; }
	private void setWarningManager(MessageManager _warningManager) { warningManager = _warningManager; }
	private void setIsAborted(boolean _isAborted) { isAborted = _isAborted; }
	private void setMemory(Map _memory) { memory = _memory; }
	
	public GeneratorContext(URI _generatorBase,
							EPackage _ePackage,
							java.util.Set<String> _domainSet)
	{
		setGeneratorBase(_generatorBase);
		setEPackage(_ePackage);
		setDomainSet(_domainSet);
		setErrorManager(new MessageManager());
		setWarningManager(new MessageManager());
		setIsAborted(false);
		setMemory(new HashMap());
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
}