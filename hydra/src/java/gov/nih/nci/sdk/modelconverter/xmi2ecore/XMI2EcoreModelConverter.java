package gov.nih.nci.sdk.modelconverter.xmi2ecore;

import gov.nih.nci.sdk.util.SDKUtil;

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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
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
 * TODO: This code does not load tag values of the Package level. Since this 
 * is not of the highest priority, I will add that feature later. --John
 * 
 * @author John Chen
 * 
 */
public class XMI2EcoreModelConverter extends UMLImporter {
	
	private Collection<EAnnotation> tags = new ArrayList<EAnnotation>();
	

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

		EPackage rootEPackage = null;
		Iterator<EPackage> it = ePackages.iterator();
		if (it.hasNext()) {
			rootEPackage = it.next();
		}
		
		fetchTags(preparedFile, rootEPackage);
		
		preparedFile.delete();
		
		SDKUtil.setTags(tags);

		return rootEPackage;
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
	
	void fetchTags(File file, EPackage rootEPackage) throws IOException {
		if (file == null || rootEPackage == null) return;
		
		if (!file.exists())
			throw new FileNotFoundException("File " + file	+ " does not exist.");
		
		List<String> lines = new ArrayList<String>();
		List<TagLine> tagLines = new ArrayList<TagLine>();
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = null;
		TagLine tagLine = null;
		while ((line = in.readLine()) != null) {
			lines.add(line);
			
			if (line.indexOf("<thecustomprofile:") != -1 &&
					line.indexOf(" base_") != -1) {
				tagLine = new TagLine(line);
				if (tagLine.isSDKTag()) {
					tagLines.add(tagLine);
				}
			}
		}
		in.close();
		
		if (lines.size() == 0 || tagLines.size() == 0) return;
		
		fillInSourceForTags(lines, tagLines);
		linkTagsWithModel(rootEPackage, tagLines);
	}
	
	private void fillInSourceForTags(List<String> lines, List<TagLine> tagLines) {
		Iterator<TagLine> it = tagLines.iterator();
		while(it.hasNext()) {
			TagLine tag = it.next();
			fillInSourceForTag(lines, tag);
		}
	}
	
	private void fillInSourceForTag(List<String> lines, TagLine tag) {
		String searchToken = "xmi:id=\"" + tag.baseId + "\"";
		Iterator<String> it = lines.iterator();
		int count = 0;
		while(it.hasNext()) {
			String line = it.next();
			if (line.indexOf(searchToken) != -1) {
				String source = getValueForToken(line, "name");
				tag.source = source;
				if (tag.isClassTag()) {
					tag.containerClass = source;
				}
				else if (tag.isPropTag() || tag.isOperTag()) {
					detectClassName(lines, count, tag);
				}
				else if (tag.isRelGeneralizationTag()) {
					detectClassName(lines, count, tag);
					detectRelatedClassName(lines, line, tag);
				}
				else {
					;//TODO: need to handle Package and RelAggregation tags
				}
			}
			count++;
		}
	}
	
	String detectClassName(List<String> lines, int index, TagLine tag) {
		String s = null;
		for (int i = index; i > 0; i--) {
			String line = lines.get(i).trim();
			if (line.startsWith("<packagedElement xmi:type=\"uml:Class\"")) {
				s = getValueForToken(line, "name");
				tag.containerClass = s;
				break;
			}
		}
		return s;
	}
	
	void detectRelatedClassName(List<String> lines, String tagLine, TagLine tag) {
		String general = getValueForToken(tagLine, "general");
		String searchToken = "xmi:id=\"" + general + "\"";
		Iterator<String> it = lines.iterator();
		while(it.hasNext()) {
			String line = it.next();
			if (line.indexOf(searchToken) != -1) {
				String name = getValueForToken(line, "name");
				tag.relatedClass = name;
				break;
			}
		}
	}
	
	private void linkTagsWithModel(EPackage rootPkg, List<TagLine> tagLines) {
		Iterator<TagLine> it = tagLines.iterator();
		while(it.hasNext()) {
			TagLine tag = it.next();
			linkTagWithModel(rootPkg, tag);
		}
	}
	
	private void linkTagWithModel(EPackage rootPkg, TagLine tag) {
		if (tag == null || !tag.isSDKTag()) return;
		
		if (tag.isClassTag()) {
			linkClassTagWithModel(rootPkg, tag);
		}
		else if (tag.isPropTag()) {
			linkPropTagWithModel(rootPkg, tag);
		}
		else if (tag.isOperTag()) {
			linkOperTagWithModel(rootPkg, tag);
		}
		else if (tag.isRelGeneralizationTag()) {
			linkRelGeneralizationTagWithModel(rootPkg, tag);
		}
		else if (tag.isRelAggregationTag()) {
			//TODO: linkRelAggregationTagWithModel(rootPkg, tag);
		}
		else if (tag.isPackageTag()) {
			//TODO: linkPackageTagWithModel(rootPkg, tag);
		}
	}
	
	static String getValueForToken(String line, String token) {
		if (line.endsWith("/>")) {
			line = line.substring(0, line.length() -2);
		}
		else if (line.endsWith(">")) {
			line = line.substring(0, line.length() -2);
		}
		int nameIndex = line.indexOf(token + '=');
		if (nameIndex == -1) return null;
		
		int startIndex = nameIndex + token.length() + 1;
		int endIndex = line.indexOf(' ', startIndex + 1);
		String s = (endIndex != -1)?line.substring(startIndex, endIndex):line.substring(startIndex);
		s = StringUtils.remove(s, '"');
		return s;
	}
	
	public void linkClassTagWithModel(EPackage rootEPackage, TagLine tag) {
		if (rootEPackage == null || tag == null) return;
		
		EModelElement modelElement = gov.nih.nci.sdk.util.EcoreUtil.getModelElementForName(rootEPackage, tag.containerClass);
		if (modelElement == null) {
			System.out.println("linkClassTagWithModel ERROR: This should not happen.");//TODO: log the error.
		}

		addAnnotationToModelElement(modelElement, tag);
	}
	
	public void linkPropTagWithModel(EPackage rootEPackage, TagLine tag) {
		if (rootEPackage == null || tag == null) return;
		
		EModelElement modelElement = gov.nih.nci.sdk.util.EcoreUtil.getModelElementForName(rootEPackage, tag.containerClass);
		if (modelElement == null) {
			System.out.println("linkPropTagWithModel ERROR: This should not happen.");//TODO: log the error.
		}
		
		EList<EStructuralFeature> attributes = ((EClass)modelElement).getEAllStructuralFeatures();
		Iterator<EStructuralFeature> itAttr = attributes.iterator();
		while (itAttr.hasNext()) {
			EStructuralFeature attr = itAttr.next();
			
			if (tag.source.equals(attr.getName())) {
				addAnnotationToModelElement(attr, tag);
			}
		}
	}
	
	public void linkOperTagWithModel(EPackage rootEPackage, TagLine tag) {
		if (rootEPackage == null || tag == null) return;
		
		EModelElement modelElement = gov.nih.nci.sdk.util.EcoreUtil.getModelElementForName(rootEPackage, tag.containerClass);
		if (modelElement == null) {
			System.out.println("linkOperTagWithModel ERROR: This should not happen.");//TODO: log the error.
		}
		
		EList<EOperation> operations = ((EClass)modelElement).getEAllOperations();
		Iterator<EOperation> itOper = operations.iterator();
		while (itOper.hasNext()) {
			EOperation oper = itOper.next();
			
			if (tag.source.equals(oper.getName())) {
				addAnnotationToModelElement(oper, tag);
			}
		}
	}
	
	public void linkRelGeneralizationTagWithModel(EPackage rootEPackage, TagLine tag) {
		if (rootEPackage == null || tag == null) return;
		
		EModelElement modelElement = gov.nih.nci.sdk.util.EcoreUtil.getModelElementForName(rootEPackage, tag.containerClass);
		if (modelElement == null) {
			System.out.println("linkRelGeneralizationTagWithModel ERROR: This should not happen.");//TODO: log the error.
		}
		
		addAnnotationToModelElement(modelElement, tag);
	}
	
	private void addAnnotationToModelElement(EModelElement modelElement, TagLine tag) {
		EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
		ann.setSource(tag.name);
		ann.setEModelElement(modelElement);
		ann.getDetails().put(tag.name, tag.value);
		tags.add(ann);
	}
	
	class TagLine {
		String baseId;
		String baseLevel;
		String name;
		String value;
		String source;
		String containerClass;
		String relatedClass;
		
		TagLine(String line) {
			init(line);
		}
		
		boolean isSDKTag() {
			return SDKUtil.isSDKTag(name);
		}
		
		boolean isPropTag() {
			return "Attribute".equals(baseLevel);
		}
		
		boolean isClassTag() {
			return "Class".equals(baseLevel);
		}
		
		boolean isOperTag() {
			return "Operation".equals(baseLevel);
		}
		
		boolean isRelAggregationTag() {
			return "Aggregation".equals(baseLevel);
		}
		
		boolean isRelGeneralizationTag() {
			return "Generalization".equals(baseLevel);
		}
		
		boolean isPackageTag() {
			throw new UnsupportedOperationException();//TODO
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("name=").append(name).append(",");
			sb.append("value=").append(value).append(",");
			sb.append("baseLevel=").append(baseLevel).append(",");
			sb.append("baseId=").append(baseId).append(",");
			sb.append("source=").append(source).append(",");
			sb.append("containerClass=").append(containerClass).append(",");
			sb.append("relatedClass=").append(relatedClass);
			return sb.toString();
		}
		
		private void init(String line) {
			if (line == null) return;
			try {
				//parse the line
				line = line.trim();
				name = line.substring("<thecustomprofile:".length(), line.indexOf(' '));
				name = StringUtils.remove(name, '"');
				
				int valueStartIndex = line.indexOf(name + '=');
				if (valueStartIndex != -1) {
					value = line.substring(line.lastIndexOf('=') + 2, line.lastIndexOf('"'));
					
					String ln = line.substring(line.indexOf("base_") + 5, valueStartIndex -1);
					ln = StringUtils.remove(ln, '"');
					baseLevel = ln.substring(0, ln.indexOf('='));
					
					baseId = ln.substring(ln.indexOf('=') + 1);
				}
			}
			catch (Exception ex) {
				throw new IllegalArgumentException("Failed to parse line \"" + line + "\".");
			}
		}
	}

	private static final String searchStringTemplateSignature = "uml:TemplateSignature";
	private static final String replacementTemplateSignature = "uml:RedefinableTemplateSignature";

	private static final String searchStringCustomProfile = "thecustomprofile:owner";
	private static final String replacementCustomProfile = "thecustomprofile:import";

	private static final String searchStringXMIType = "<defaultValue xmi:id";
	private static final String replacementXMITypeString = "<defaultValue xmi:type=\"uml:LiteralString\" xmi:id";
}
