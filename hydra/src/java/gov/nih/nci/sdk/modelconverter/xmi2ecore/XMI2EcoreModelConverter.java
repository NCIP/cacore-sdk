package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ecore.importer.UMLImporter;
import org.eclipse.uml2.uml.internal.resource.XMI2UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.util.UMLUtil;

public class XMI2EcoreModelConverter extends UMLImporter {
	
	public Collection<EPackage> convert(String modelLocation) {
		if (modelLocation == null) throw new IllegalArgumentException("modelLocation is null.");
		
		super.setModelLocation(modelLocation);
		
		Map<String, String> options = new HashMap<String, String>();

		List<URI> locationURIs = getModelLocationURIs();
		
		Collection<org.eclipse.uml2.uml.Package> packages = new ArrayList<org.eclipse.uml2.uml.Package>();

		ResourceSet umlResourceSet = createResourceSet();
		umlResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMI2UMLResourceFactoryImpl());
		
		for (URI locationURI : locationURIs) {
			packages.addAll(EcoreUtil
				.<org.eclipse.uml2.uml.Package> getObjectsByType(
					umlResourceSet.getResource(locationURI, true)
						.getContents(), UMLPackage.Literals.PACKAGE));
		}
		
		EcoreUtil.resolveAll(umlResourceSet);
		
		BasicDiagnostic diagnostics = null;

		Map<Object, Object> context = new HashMap<Object, Object>();
		context
			.put(
				org.eclipse.uml2.common.util.UML2Util.QualifiedTextProvider.class,
				UMLUtil.QualifiedTextProvider.DEFAULT);

		@SuppressWarnings("unchecked")
		Collection<EPackage> ePackages = (Collection<EPackage>) new UMLUtil.UML2EcoreConverter() {

			@Override
			protected void processEcoreTaggedValues(EPackage ePackage,
					Element element, Map<String, String> options,
					DiagnosticChain diagnostics, Map<Object, Object> context) {

				super.processEcoreTaggedValues(ePackage, element, options,
					diagnostics, context);

				Stereotype ePackageStereotype = getAppliedEcoreStereotype(
					element, UMLUtil.STEREOTYPE__E_PACKAGE);

				if (null != ePackageStereotype) {
					ModelImporter.EPackageImportInfo ePackageInfo = getEPackageImportInfo(ePackage);

					if (element.hasValue(ePackageStereotype,
						UMLUtil.TAG_DEFINITION__BASE_PACKAGE)) {

						StringBuffer basePackage = new StringBuffer();

						for (StringTokenizer stringTokenizer = new StringTokenizer(
							(String) element.getValue(ePackageStereotype,
								UMLUtil.TAG_DEFINITION__BASE_PACKAGE), "."); stringTokenizer //$NON-NLS-1$
							.hasMoreTokens();) {

							basePackage.append(CodeGenUtil
								.safeName(stringTokenizer.nextToken()));

							if (stringTokenizer.hasMoreTokens()) {
								basePackage.append('.');
							}
						}

						ePackageInfo.setBasePackage(basePackage.toString());
					}

					if (element.hasValue(ePackageStereotype,
						UMLUtil.TAG_DEFINITION__PREFIX)) {

						ePackageInfo.setPrefix((String) element.getValue(
							ePackageStereotype,
							UMLUtil.TAG_DEFINITION__PREFIX));
					}
				}
			}
		}.convert(packages, options, diagnostics, context);
		
		return ePackages;
	}
}

