package gov.nih.nci.system.dao.orm.translator.gridCQL;

import gov.nih.nci.system.util.ClassCache;


public class ClassDiscriminatorResolver implements gov.nih.nci.cagrid.sdkquery4.processor.ClassDiscriminatorResolver
{

	ClassCache cache;
	
	public ClassDiscriminatorResolver(ClassCache cache)
	{
		this.cache = cache;
	}

	public Object getClassDiscriminatorValue(String classname) throws Exception
	{
		return cache.getDiscriminatorObject(classname);
	}
	
}