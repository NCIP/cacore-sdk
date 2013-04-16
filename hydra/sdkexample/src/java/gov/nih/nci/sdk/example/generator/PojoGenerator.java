/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.example.generator;

import java.util.List;
import gov.nih.nci.sdk.core.ScriptContext;
import gov.nih.nci.sdk.util.EcoreUtil;
import gov.nih.nci.sdk.example.generator.util.GeneratorUtil;

import org.antlr.stringtemplate.StringTemplate;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;

public class PojoGenerator
   extends Generator
{	
	public PojoGenerator(ScriptContext _scriptContext)
	{
		super(_scriptContext);
	}

	protected void init()
	{
		getScriptContext().getLogger().info("Generating pojo...");
		// TODO Auto-generated method stub
	}

	protected void preProcess()
	{
		// TODO Auto-generated method stub
	}

	protected void validate()
	{
		// TODO Auto-generated method stub
	}

	public void runProcess()
	{
		runProcess("pojo", GeneratorUtil.getPojoPath(getScriptContext()));
	}
	
	protected void runProcess(String _pojoPackageName, String _outputDir)
	{
		StringTemplate template = getScriptContext().getTemplateGroup().getInstanceOf("pojo");
		String domain = getScriptContext().getFocusDomain();
		String packageName = EcoreUtil.determinePackageName(domain);
		String className = EcoreUtil.determineClassName(domain);
		template.setAttribute("packageName", packageName + "." + _pojoPackageName);
		template.setAttribute("className", className);
		List<EAttribute> eAttributeList = EcoreUtil.getEClass(getScriptContext().getEPackage(), domain).getEAttributes();

		for (EAttribute eAttribute: eAttributeList)
		{
			StringTemplate pojoAttributeTemplate = getScriptContext().getTemplate("pojoAttributes");
			pojoAttributeTemplate.setAttribute("name", eAttribute.getName());
			pojoAttributeTemplate.setAttribute("type", determineJavaType(EcoreUtil.determineSubstituteType(eAttribute.getName(), eAttribute.getEType())));

			template.setAttribute("pojoAttribute", pojoAttributeTemplate);
			StringTemplate pojoOperationTemplate = getScriptContext().getTemplate("pojoOperations");
			pojoOperationTemplate.setAttribute("name", eAttribute.getName());
			pojoOperationTemplate.setAttribute("type", determineJavaType(EcoreUtil.determineSubstituteType(eAttribute.getName(), eAttribute.getEType())));
			pojoOperationTemplate.setAttribute("operationName", GeneratorUtil.convertFirstCharToUpperCase(eAttribute.getName()));
			template.setAttribute("pojoOperation", pojoOperationTemplate);
		}

		/*List<EReference> eReferenceList = EcoreUtil.getEClass(getScriptContext().getEPackage(), domain).getEReferences();

		for (EReference eReference: eReferenceList)
		{
			//TODO  Should we care if this type could appear as more
			//than one?  Probably, and if so does eReference.isMany()
			//indicative of this trait?
			StringTemplate pojoAttributeTemplate = getScriptContext().getTemplate("pojoAttributes");
			pojoAttributeTemplate.setAttribute("name", eReference.getName());

			pojoAttributeTemplate.setAttribute("type", (eReference.getEReferenceType() != null) ? eReference.getEReferenceType().getInstanceClassName() : "" );

			template.setAttribute("pojoAttribute", pojoAttributeTemplate);
			StringTemplate pojoOperationTemplate = getScriptContext().getTemplate("pojoOperations");
			pojoOperationTemplate.setAttribute("name", eReference.getName());
			pojoOperationTemplate.setAttribute("type", (eReference.getEReferenceType() != null) ? eReference.getEReferenceType().getInstanceClassName() : "");
			pojoOperationTemplate.setAttribute("operationName", GeneratorUtil.convertFirstCharToUpperCase(eReference.getName()));
			template.setAttribute("pojoOperation", pojoOperationTemplate);
		}*/

		GeneratorUtil.writeFile(_outputDir, className + ".java", template.toString());
	}

	protected void postProcess()
	{
		// TODO Auto-generated method stub
	}
}