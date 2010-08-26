package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
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

/**
 * XMI2EcoreModelConverter class converts UML model inside an XMI file to Ecore
 * model represented by <tt>EPackage</tt>.
 * 
 * @author John Chen
 * 
 */
public class XMI2EcoreModelConverter extends UMLImporter {

	/**
	 * Converts UML model inside the xmi file to Ecore model.
	 * 
	 * @param xmiFilePath
	 *            full file path to the xmi file.
	 * @return a root instance of EPackage inside the xmi file or <tt>null</tt>
	 *         if there is none.
	 * @throws Exception
	 */
	public EPackage convert(String xmiFilePath) throws Exception {
		if (xmiFilePath == null)
			throw new IllegalArgumentException("xmiFilePath cannot be null.");

		File preparedFile = prepareFile(xmiFilePath);
		URI uri = URI.createFileURI(preparedFile.getCanonicalPath());
		String modelLocation = uri.toString();
		super.setModelLocation(modelLocation);

		Map<String, String> options = new HashMap<String, String>();
		List<URI> locationURIs = getModelLocationURIs();
		Collection<org.eclipse.uml2.uml.Package> packages = new ArrayList<org.eclipse.uml2.uml.Package>();

		ResourceSet umlResourceSet = createResourceSet();
		umlResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMI2UMLResourceFactoryImpl());

		for (URI locationURI : locationURIs) {
			packages
					.addAll(EcoreUtil
							.<org.eclipse.uml2.uml.Package> getObjectsByType(
									umlResourceSet.getResource(locationURI,
											true).getContents(),
									UMLPackage.Literals.PACKAGE));
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
										UMLUtil.TAG_DEFINITION__BASE_PACKAGE),
								"."); stringTokenizer //$NON-NLS-1$
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

		preparedFile.delete();

		EPackage epkg = null;
		Iterator<EPackage> it = ePackages.iterator();
		if (it.hasNext()) {
			epkg = it.next();
		}

		return epkg;
	}
	
	public Collection<org.eclipse.uml2.uml.Package> convert2UML(String xmiFilePath) throws Exception {
		if (xmiFilePath == null)
			throw new IllegalArgumentException("xmiFilePath cannot be null.");

		File preparedFile = prepareFile(xmiFilePath);
		URI uri = URI.createFileURI(preparedFile.getCanonicalPath());
		String modelLocation = uri.toString();
		super.setModelLocation(modelLocation);

		Map<String, String> options = new HashMap<String, String>();
		List<URI> locationURIs = getModelLocationURIs();
		Collection<org.eclipse.uml2.uml.Package> packages = new ArrayList<org.eclipse.uml2.uml.Package>();

		ResourceSet umlResourceSet = createResourceSet();
		umlResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xmi", new XMI2UMLResourceFactoryImpl());

		for (URI locationURI : locationURIs) {
			packages
					.addAll(EcoreUtil
							.<org.eclipse.uml2.uml.Package> getObjectsByType(
									umlResourceSet.getResource(locationURI,
											true).getContents(),
									UMLPackage.Literals.PACKAGE));
		}

		return packages;
	}

	File prepareFile(String filePath) throws IOException {
		File f = new File(filePath);
		if (!f.exists())
			throw new FileNotFoundException("File " + filePath
					+ " does not exist.");
		
		List<String> lines = new ArrayList<String>();

		BufferedReader in = new BufferedReader(new FileReader(filePath));
		String line = null;
		while ((line = in.readLine()) != null) {

			// replace templateSignature
			if (line.indexOf(searchStringTemplateSignature) != -1) {
				line = StringUtils.replace(line, searchStringTemplateSignature,
						replacementTemplateSignature);
			}

			// replace thecustomprofile
			if (line.indexOf(searchStringCustomProfile) != -1) {
				line = StringUtils.replace(line, searchStringCustomProfile,
						replacementCustomProfile);
			}

			// add xmi:type
			if (line.indexOf(searchStringXMIType) != -1) {
				line = StringUtils.replace(line, searchStringXMIType,
						replacementXMITypeString);
			}

			lines.add(line);
			
			if (line.indexOf("</uml:Model>") != -1) {
				lines.add("</xmi:XMI>");
				break;
			}
		}
		in.close();

		String tmpDir = System.getProperty("user.dir", "");
		String tmpFileName = tmpDir + File.separator + "_sdk_" + f.getName();

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				tmpFileName)));
		Iterator<String> it = lines.iterator();
		while (it.hasNext()) {
			String l = it.next();
			out.println(l);
		}
		out.flush();
		out.close();

		return new File(tmpFileName);
	}

	private static final String searchStringTemplateSignature = "uml:TemplateSignature";
	private static final String replacementTemplateSignature = "uml:RedefinableTemplateSignature";

	private static final String searchStringCustomProfile = "thecustomprofile:owner";
	private static final String replacementCustomProfile = "thecustomprofile:import";

	private static final String searchStringXMIType = "<defaultValue xmi:id";
	private static final String replacementXMITypeString = "<defaultValue xmi:type=\"uml:LiteralString\" xmi:id";
}
