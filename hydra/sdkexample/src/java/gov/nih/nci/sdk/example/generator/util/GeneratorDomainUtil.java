package gov.nih.nci.sdk.example.generator.util;

import gov.nih.nci.sdk.core.generator.ECOREAttribute;
import gov.nih.nci.sdk.core.generator.ECOREDomain;
import gov.nih.nci.sdk.core.generator.ECOREElement;
import gov.nih.nci.sdk.core.generator.ECOREOperation;

import java.util.ArrayList;
import java.util.List;

public class GeneratorDomainUtil {

	public static ECOREElement[] getAttributeList(ECOREDomain domain)
	{
		List<ECOREElement> list = new ArrayList<ECOREElement>(10);
		
		List<ECOREAttribute> attributes = domain.getAttributes();
		for(ECOREAttribute attribute : attributes)
		{
			list.add(new ECOREElement(attribute.getType(), attribute.getName()));
			
		}
		return list.toArray(new ECOREElement[list.size()]);
	}
	
	public static ECOREElement[] getServiceOperationsList(ECOREDomain domain)
	{
		List<ECOREElement> list = new ArrayList<ECOREElement>(10);
		
		if(domain.getOperations() == null || domain.getOperations().size() == 0)
		{
			List<ECOREAttribute> attributes = domain.getAttributes();
			String upperName = domain.getName().substring(0,1).toUpperCase() + domain.getName().substring(1, domain.getName().length()).toLowerCase();
			String lowerName = domain.getName().substring(0,1).toLowerCase() + domain.getName().substring(1, domain.getName().length()).toLowerCase();
				
			String getDomainOperation = "get" + upperName;
			String getParams = domain.getName() + " " + lowerName;
			String returnType = domain.getName();
			list.add(new ECOREElement(returnType, getDomainOperation, null, getParams));
				
		}
		else
		{
			List<ECOREOperation> operations = domain.getOperations();
			for(ECOREOperation operation : operations)
				list.add(new ECOREElement(operation.getReturnType(), operation.getName(), operation.getAnnotations(), operation.getParams()));
		}
		return list.toArray(new ECOREElement[list.size()]);
	}
	
}
