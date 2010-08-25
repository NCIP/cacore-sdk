package gov.nih.nci.sdk.core;

import org.eclipse.emf.ecore.EPackage;

public interface GeneratorConductor
{
	public void compile(GeneratorContext _generatorContext);
	public void conduct(GeneratorContext _generatorContext);
	public java.util.List<Message> level0Validate(GeneratorContext _generatorContext);
}