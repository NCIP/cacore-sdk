/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.codegen.util;

import gov.nih.nci.codegen.GenerationException;
import gov.nih.nci.codegen.validator.ValidatorAttribute;
import gov.nih.nci.codegen.validator.ValidatorClass;
import gov.nih.nci.codegen.validator.ValidatorModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociation;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAssociationEnd;
import gov.nih.nci.ncicb.xmiinout.domain.UMLAttribute;
import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLDatatype;
import gov.nih.nci.ncicb.xmiinout.domain.UMLDependency;
import gov.nih.nci.ncicb.xmiinout.domain.UMLGeneralization;
import gov.nih.nci.ncicb.xmiinout.domain.UMLInterface;
import gov.nih.nci.ncicb.xmiinout.domain.UMLModel;
import gov.nih.nci.ncicb.xmiinout.domain.UMLOperationParameter;
import gov.nih.nci.ncicb.xmiinout.domain.UMLPackage;
import gov.nih.nci.ncicb.xmiinout.domain.UMLOperation;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggableElement;
import gov.nih.nci.ncicb.xmiinout.domain.UMLTaggedValue;
import gov.nih.nci.ncicb.xmiinout.domain.bean.UMLAssociationEndBean;
import gov.nih.nci.ncicb.xmiinout.util.ModelUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jdom.Element;

import gov.nih.nci.iso21090.hibernate.node.*;

public class TransformerUtils
{
	private static Logger log = Logger.getLogger(TransformerUtils.class);

	private String BASE_PKG_LOGICAL_MODEL;
	private String BASE_PKG_DATA_MODEL;
	private String INCLUDE_PACKAGE;
	private String EXCLUDE_PACKAGE;
	private String EXCLUDE_REST_PACKAGE;
	private String INCLUDE_REST_PACKAGE;
	private String EXCLUDE_REST_NAME;
	private String EXCLUDE_NAME;
	private String EXCLUDE_NAMESPACE;

	private String IDENTITY_GENERATOR_TAG;
	private Set<String> INCLUDE_PACKAGE_PATTERNS = new HashSet<String>();
	private Set<String> EXCLUDE_PACKAGE_PATTERNS = new HashSet<String>();
	private Set<String> EXCLUDE_CLASS_PATTERNS = new HashSet<String>();
	private Set<String> INCLUDE_REST_PACKAGE_PATTERNS = new HashSet<String>();
	private Set<String> EXCLUDE_REST_PACKAGE_PATTERNS = new HashSet<String>();
	private Set<String> EXCLUDE_REST_CLASS_PATTERNS = new HashSet<String>();
	private Set<String> EXCLUDE_NAMESPACE_PATTERNS = new HashSet<String>();
	private String DATABASE_TYPE;
	private Map<String,String> CASCADE_STYLES = new HashMap<String,String>();
	private ValidatorModel vModel;
	private ValidatorModel vModelExtension;

	private String namespaceUriPrefix;
	private boolean useGMETags = false;
	private boolean isJaxbEnabled = false;
	private boolean isISO21090Enabled = false;
	private Map<UMLClass, String> restResourceNames = new HashMap<UMLClass, String>();

	private UMLPackage isoDataTypeRootPackage;
	/**
	 * UMLModel from which the code is to be generated
	 */
	private UMLModel model;

	public static final String TV_ID_ATTR_COLUMN = "id-attribute";
	public static final String TV_MAPPED_ATTR_COLUMN = "mapped-attributes";
	public static final String TV_MAPPED_ATTR_CONSTANT = "mapped-constant";
	public static final String TV_ASSOC_COLUMN = "implements-association";
	public static final String TV_INVERSE_ASSOC_COLUMN = "inverse-of";
	public static final String TV_DISCR_COLUMN = "discriminator";
	public static final String TV_CORRELATION_TABLE = "correlation-table";
	public static final String TV_DOCUMENTATION = "documentation";
	public static final String TV_DESCRIPTION = "description";
	public static final String TV_LAZY_LOAD = "lazy-load";
	public static final String TV_TYPE="type";
	public static final String TV_MAPPED_COLLECTION_TABLE = "mapped-collection-table";
	public static final String TV_MAPPED_ELEMENT_COLUMN = "mapped-element";
	public static final String TV_CADSR_PUBLICID = "CADSR_ConceptualDomainPublicID";
	public static final String TV_CADSR_VERSION = "CADSR_ConceptualDomainVersion";
	public static final String TV_NCI_CASCADE_ASSOCIATION = "NCI_CASCADE_ASSOCIATION";
	public static final String TV_NCI_EAGER_LOAD = "NCI_EAGER_LOAD";
	public static final String TV_PK_GENERATOR = "NCI_GENERATOR.";
	public static final String TV_PK_GENERATOR_PROPERTY = "NCI_GENERATOR_PROPERTY";
	public static final String TV_MAPPED_COLLECTION_ELEMENT_TYPE = "mapped-collection-element-type";
	public static final String TV_NOT_NULL_PROPERTY = "nci-not-null";
	public static final String RESTFUL_RESOURCE = "RESTFUL_RESOURCE";

	//Global Model Exchange (GME) Project Tag Value Constants; see: https://wiki.nci.nih.gov/display/caCORE/GME+Namespace
	public static final String TV_NCI_GME_XML_NAMESPACE = "NCI_GME_XML_NAMESPACE"; //Used for projects, Packages, Classes
	public static final String TV_NCI_GME_XML_ELEMENT = "NCI_GME_XML_ELEMENT"; //Used for Classes
	public static final String TV_NCI_GME_XML_LOC_REF = "NCI_GME_XML_LOC_REF"; //Used for Attributes
	public static final String TV_NCI_GME_SOURCE_XML_LOC_REF = "NCI_GME_SOURCE_XML_LOC_REF"; //Used for Associations
	public static final String TV_NCI_GME_TARGET_XML_LOC_REF = "NCI_GME_TARGET_XML_LOC_REF"; //Used for Associations

	public static final String STEREO_TYPE_TABLE = "table";
	public static final String STEREO_TYPE_DATASOURCE_DEPENDENCY = "DataSource";

	public static final String PK_GENERATOR_SYSTEMWIDE = "NCI_GENERATOR_SYSTEMWIDE.";

	public static final String ISO_ROOT_PACKAGE_NAME = "gov.nih.nci.iso21090";



	public static final Map<String, String> javaDatatypeMap = new HashMap<String, String>()
	{
		{
			put("int", "Integer");
			put("integer", "Integer");
			put("double", "Double");
			put("float", "Float");
			put("long", "Long");
			put("string", "String");
			put("char", "Character");
			put("character", "Character");
			put("boolean", "Boolean");
			put("byte", "Byte");
			put("byte[]", "Byte[]");
			put("short", "Short");
			put("date", "java.util.Date");
			put("java.util.date", "java.util.Date");
			put("collection<int>", "Collection<Integer>");
			put("collection<integer>", "Collection<Integer>");
			put("collection<double>", "Collection<Double>");
			put("collection<float>", "Collection<Float>");
			put("collection<long>", "Collection<Long>");
			put("collection<string>", "Collection<String>");
			put("collection<boolean>", "Collection<Boolean>");
			put("collection<byte>", "Collection<Byte>");
			put("collection<short>", "Collection<Short>");
			put("collection<char>", "Collection<Character>");
			put("collection<character>", "Collection<Character>");
		}
	};

	public static final Map<String, String> javaPrimitiveDatatypeMap = new HashMap<String, String>()
	{
		{
			put("int", "Integer");
			put("integer", "Integer");
			put("double", "Double");
			put("float", "Float");
			put("long", "Long");
			put("string", "String");
			put("char", "Character");
			put("character", "Character");
			put("boolean", "Boolean");
			put("byte", "Byte");
			put("short", "Short");
			put("date", "java.util.Date");
			put("Integer", "Integer");
			put("integer", "Integer");
			put("Double", "Double");
			put("Float", "Float");
			put("Long", "Long");
			put("String", "String");
			put("Character", "Character");
			put("character", "Character");
			put("Boolean", "Boolean");
			put("Byte", "Byte");
			put("Short", "Short");
			put("java.util.Date", "java.util.Date");
		}
	};

	public static final Map<String, String> isoDatatypeMap = new HashMap<String, String>()
	{
		{
			put("BL", "Bl");
			put("BL.NONNULL", "BlNonNull");
			put("ST", "St");
			put("ST.NT", "StNt");
			put("II", "Ii");
			put("TEL", "Tel");
			put("TEL.PERSON", "TelPerson");
			put("TEL.URL", "TelUrl");
			put("TEL.PHONE", "TelPhone");
			put("TEL.EMAIL", "TelEmail");
			put("ED", "Ed");
			put("ED.TEXT", "EdText");
			put("CD", "Cd");
			put("SC", "Sc");
			put("INT", "Int");
			put("REAL", "Real");
			put("TS", "Ts");
			put("PQV", "Pqv");
			put("PQ", "Pq");
			put("IVL<INT>", "Ivl<Int>");
			put("IVL<REAL>", "Ivl<Real>");
			put("IVL<TS>", "Ivl<Ts>");
			put("IVL<PQV>", "Ivl<Pqv>");
			put("IVL<PQ>", "Ivl<Pq>");
			put("EN", "En");
			put("EN.PN", "EnPn");
			put("EN.ON", "EnOn");
			put("DSET<II>", "DSet<Ii>");
			put("DSET<TEL>", "DSet<Tel>");
			put("DSET<CD>", "DSet<Cd>");
			put("DSET<AD>", "DSet<Ad>");
			put("AD", "Ad");
		}
	};

	public static final Map<String, String> isoDatatypeCompleteMap = new HashMap<String, String>()
	{
		{
			put("BL", "Bl");
			put("BL.NONNULL", "BlNonNull");
			put("ST", "St");
			put("ST.NT", "StNt");
			put("II", "Ii");
			put("TEL", "Tel");
			put("TEL.PERSON", "TelPerson");
			put("TEL.URL", "TelUrl");
			put("TEL.PHONE", "TelPhone");
			put("TEL.EMAIL", "TelEmail");
			put("ED", "Ed");
			put("ED.TEXT", "EdText");
			put("CD", "Cd");
			put("SC", "Sc");
			put("INT", "Int");
			put("REAL", "Real");
			put("TS", "Ts");
			put("PQV", "Pqv");
			put("PQ", "Pq");
			put("IVL<INT>", "Ivl<Int>");
			put("IVL<REAL>", "Ivl<Real>");
			put("IVL<TS>", "Ivl<Ts>");
			put("IVL<PQV>", "Ivl<Pqv>");
			put("IVL<PQ>", "Ivl<Pq>");
			put("EN", "En");
			put("EN.PN", "EnPn");
			put("EN.ON", "EnOn");
			put("DSET<II>", "DSet<Ii>");
			put("DSET<TEL>", "DSet<Tel>");
			put("DSET<CD>", "DSet<Cd>");
			put("DSET<AD>", "DSet<Ad>");
			put("AD", "Ad");
			put("ADXP.AL", "AdxpAl");
			put("ADXP.ADL", "AdxpAdl");
			put("ADXP.UNID", "AdxpUnid");
			put("ADXP.UNIT", "AdxpUnit");
			put("ADXP.DAL", "AdxpDal");
			put("ADXP.DINST", "AdxpDinst");
			put("ADXP.DINSTA", "AdxpDinsta");
			put("ADXP.DINSTQ", "AdxpDinstq");
			put("ADXP.DMOD", "AdxpDmod");
			put("ADXP.DMODID", "AdxpDmodid");
			put("ADXP.SAL", "AdxpSal");
			put("ADXP.BNR", "AdxpBnr");
			put("ADXP.BNN", "AdxpBnn");
			put("ADXP.BNS", "AdxpBns");
			put("ADXP.STR", "AdxpStr");
			put("ADXP.STB", "AdxpStb");
			put("ADXP.STTYP", "AdxpSttyp");
			put("ADXP.DIR", "AdxpDir");
			put("ADXP.CAR", "AdxpCar");
			put("ADXP.CEN", "AdxpCen");
			put("ADXP.CNT", "AdxpCnt");
			put("ADXP.CPA", "AdxpCpa");
			put("ADXP.CTY", "AdxpCty");
			put("ADXP.DEL", "AdxpDel");
			put("ADXP.POB", "AdxpPob");
			put("ADXP.PRE", "AdxpPre");
			put("ADXP.STA", "AdxpSta");
			put("ADXP.ZIP", "AdxpZip");
			put("ADXP", "Adxp");
			put("ENXP", "Enxp");
			put("DSET", "DSet");
			put("IVL", "Ivl");
		}
	};


	public TransformerUtils(Properties umlModelFileProperties,Properties transformerProperties,List cascadeStyles, ValidatorModel vModel, ValidatorModel vModelExtension, UMLModel model) {
			BASE_PKG_LOGICAL_MODEL = umlModelFileProperties.getProperty("Logical Model") == null ? "" :umlModelFileProperties.getProperty("Logical Model").trim();
			BASE_PKG_DATA_MODEL = umlModelFileProperties.getProperty("Data Model")==null ? "" : umlModelFileProperties.getProperty("Data Model").trim();

			EXCLUDE_PACKAGE = umlModelFileProperties.getProperty("Exclude Package")==null ? "" : umlModelFileProperties.getProperty("Exclude Package").trim();
			INCLUDE_PACKAGE = umlModelFileProperties.getProperty("Include Package")==null ? "" : umlModelFileProperties.getProperty("Include Package").trim();
			EXCLUDE_NAME = umlModelFileProperties.getProperty("Exclude Name")==null ? "" : umlModelFileProperties.getProperty("Exclude Name").trim();
			EXCLUDE_NAMESPACE = umlModelFileProperties.getProperty("Exclude Namespace")==null ? "" : umlModelFileProperties.getProperty("Exclude Namespace").trim();

			EXCLUDE_REST_PACKAGE = umlModelFileProperties.getProperty("Exclude REST Package")==null ? "" : umlModelFileProperties.getProperty("Exclude REST Package").trim();
			INCLUDE_REST_PACKAGE = umlModelFileProperties.getProperty("Include REST Package")==null ? "" : umlModelFileProperties.getProperty("Include REST Package").trim();
			EXCLUDE_REST_NAME = umlModelFileProperties.getProperty("Exclude REST Name")==null ? "" : umlModelFileProperties.getProperty("Exclude REST Name").trim();

			namespaceUriPrefix = transformerProperties.getProperty("namespaceUriPrefix")==null ? "" : transformerProperties.getProperty("namespaceUriPrefix").trim().replace(" ", "_");
			useGMETags = transformerProperties.getProperty("useGMETags")==null ? false : Boolean.parseBoolean(transformerProperties.getProperty("useGMETags"));
			isJaxbEnabled = transformerProperties.getProperty("isJaxbEnabled")==null ? false : Boolean.parseBoolean(transformerProperties.getProperty("isJaxbEnabled"));
			isISO21090Enabled = transformerProperties.getProperty("isISO21090Enabled")==null ? false : Boolean.parseBoolean(transformerProperties.getProperty("isISO21090Enabled"));

			this.model = model;

			if (useGMETags){
				setModelNamespace(model,this.getBasePkgLogicalModel());
			}

			for(String excludeToken:EXCLUDE_PACKAGE.split(","))
				EXCLUDE_PACKAGE_PATTERNS.add(excludeToken.trim());
			for(String includeToken:INCLUDE_PACKAGE.split(","))
				INCLUDE_PACKAGE_PATTERNS.add(includeToken.trim());
			for(String excludeToken:EXCLUDE_NAME.split(","))
				EXCLUDE_CLASS_PATTERNS.add(excludeToken.trim());
			for(String excludeToken:EXCLUDE_NAMESPACE.split(","))
				EXCLUDE_NAMESPACE_PATTERNS.add(excludeToken.trim());


			for(String excludeToken:EXCLUDE_REST_PACKAGE.split(","))
				EXCLUDE_REST_PACKAGE_PATTERNS.add(excludeToken.trim());
			for(String includeToken:INCLUDE_REST_PACKAGE.split(","))
				INCLUDE_REST_PACKAGE_PATTERNS.add(includeToken.trim());
			for(String excludeToken:EXCLUDE_REST_NAME.split(","))
				EXCLUDE_REST_CLASS_PATTERNS.add(excludeToken.trim());

			IDENTITY_GENERATOR_TAG = umlModelFileProperties.getProperty("Identity Generator Tag") == null ? "": umlModelFileProperties.getProperty("Identity Generator Tag").trim();
			DATABASE_TYPE = umlModelFileProperties.getProperty("Database Type") == null ? "": umlModelFileProperties.getProperty("Database Type").trim();

			for (Object cascadeStyle : cascadeStyles){
				CASCADE_STYLES.put((String) cascadeStyle, (String)cascadeStyle);
			}

			this.vModel = vModel;
			log.debug("ValidatorModel: " + vModel);

			this.vModelExtension = vModelExtension;
			log.debug("ValidatorModel Extension: " + vModelExtension);
			buildRESTFulResourceNames();

		}

	public boolean isValidRestFulResource(UMLClass klass)
	{
		String action = restResourceNames.get(klass);
		if(action == null || action.trim().length() == 0)
			return false;

		action = action.toUpperCase();

		if(action.indexOf("C") != -1 || action.indexOf("R") != -1 || action.indexOf("U") != -1 || action.indexOf("D") != -1)
			return true;
		return false;
	}

	public String getRestFulResourceAction(UMLClass klass)
	{
		return restResourceNames.get(klass);
	}

	private void buildRESTFulResourceNames()
	{
		Collection<UMLClass> classes = null;
		try {
			Collection<UMLPackage> pkgCollection = getAllPackages(model);
			Map<String, String> restPackages = new HashMap<String, String>();

			for(UMLPackage pkg : pkgCollection)
			{
				if(!isIncluded(pkg) || !isIncludedForREST(pkg))
					continue;

				UMLTaggedValue tValue = pkg.getTaggedValue(RESTFUL_RESOURCE);
				if(tValue == null)
					continue;

				String pkgName = getFullPackageName(pkg);
				restPackages.put(pkgName, tValue.getValue());
			}

			Collection<UMLClass> classCollection = getAllClasses(model);
			for(UMLClass klass : classCollection )
			{
				if(!isIncluded(klass) || !isIncludedForREST(klass) || isAbstract(klass))
					continue;

				//String klassName = getFQCN(klass);
				String restAction = getRESTTagValue(klass, restPackages);
				//System.out.println("klassName "+klassName + " restAction "+restAction);
				if(restAction != null && restAction.length() > 0)
					restResourceNames.put(klass, restAction);
			}

		} catch(Exception e){
			log.error("Error while building RESTFul resources: ", e);
			//throw new GenerationException("Error while building RESTFul resources: ", e);
		}
	}

	private String getRESTTagValue(UMLClass klass, Map<String, String> restPackages)
	{
		String action = "";
		int start = 0;
		String klassName = getFQCN(klass);
		UMLTaggedValue tValue = klass.getTaggedValue(RESTFUL_RESOURCE);
		if(tValue != null)
			action = tValue.getValue();

		while(true)
		{
			int index = klassName.indexOf(".", start);
			if(index == -1)
				break;
			String pkg = klassName.substring(0, index);
			start = start + pkg.length() + 1;
			String actionValue = restPackages.get(pkg);
			if(actionValue != null && actionValue.trim().length() > 0)
			{
				action += actionValue.trim();
			}
		}

		String returnAction = "";
		if(action != null && action.indexOf("C") != -1)
			returnAction += "C";
		if(action != null && action.indexOf("R") != -1)
			returnAction += "R";
		if(action != null && action.indexOf("U") != -1)
			returnAction += "U";
		if(action != null && action.indexOf("D") != -1)
			returnAction += "D";

		return returnAction;
	}

	public boolean isValidRestResource(UMLModel model, UMLClass klass)
	{
		if(restResourceNames.get(klass) == null)
			return false;
		else
			return true;
	}

	public Collection<UMLClass> getAllRESTResources()
	{
		Collection<UMLClass> classes = new HashSet<UMLClass>();
		for(UMLClass klass : restResourceNames.keySet())
		{
			classes.add(klass);
		}
		return classes;
	}
	/**
	 * Returns all the classes (not the tables) in the XMI file which do not belong to java.lang or java.util package
	 * @param model
	 * @return
	 */
	public Collection<UMLClass> getAllRESTResources(UMLModel model) throws GenerationException
	{
		Collection<UMLClass> classes = null;
		try {
			classes = new HashSet<UMLClass>();
			getAllRESTResources(model.getPackages(),classes);
		} catch(Exception e){
			log.error("Unable to retrieve classes from model: ", e);
			throw new GenerationException("Unable to retrieve classes from model: ", e);
		}
		return classes;
	}

	private void getAllRESTResources(Collection<UMLPackage> pkgCollection,Collection<UMLClass> classes)throws GenerationException
	{
		for(UMLPackage pkg:pkgCollection)
			getAllRESTResources(pkg,classes);
	}

	private void getAllRESTResources(UMLPackage rootPkg,Collection<UMLClass> classes) throws GenerationException
	{
		if(isIncluded(rootPkg) && isIncludedForREST(rootPkg))
		{
			for(UMLClass klass:rootPkg.getClasses())
			{
				if(!STEREO_TYPE_TABLE.equalsIgnoreCase(klass.getStereotype()) && isIncluded(klass) && isIncludedForREST(klass))
					classes.add(klass);
			}
		}
		//getAllRESTResources(rootPkg.getPackages(),classes);
	}

	public boolean isIncludedForREST(UMLClass klass) throws GenerationException
	{
		String fqcn = getFQCN(klass);

		return isIncludedForREST(fqcn);
	}

	public boolean isIncludedForREST(String fqcn)
	{

		log.debug("isIncluded(String fqcn) for fqcn: "+fqcn);
		log.debug("isIncluded(String fqcn) for fqcn: "+fqcn);

		for (String excludePkgPattern:EXCLUDE_REST_PACKAGE_PATTERNS)
			if (Pattern.matches(excludePkgPattern, fqcn))
				return false;


		for (String excludeClassPattern:EXCLUDE_REST_CLASS_PATTERNS){
			if (Pattern.matches(excludeClassPattern, fqcn))
				return false;
		}

		for(String includePkgPattern: INCLUDE_REST_PACKAGE_PATTERNS){
			log.debug("includePkgPattern: "+includePkgPattern+"; fqcn: "+fqcn);
			if(Pattern.matches(includePkgPattern, fqcn))
				return true;
		}

		return false;
	}

	public boolean isIncludedForREST(UMLPackage pkg) throws GenerationException
	{
		String fullPkgName = getFullPackageName(pkg);
		log.debug("isIncluded(UMLPackage pkg) for fullPkgName: "+fullPkgName);

		for(String excludePkgPattern: EXCLUDE_REST_PACKAGE_PATTERNS)
			if (Pattern.matches(excludePkgPattern, fullPkgName))
				return false;

		for(String includePkgPattern: INCLUDE_REST_PACKAGE_PATTERNS)
			if (Pattern.matches(includePkgPattern, fullPkgName))
				return true;

		return true;
	}

	private void setModelNamespace(UMLModel model, String basePkgLogicalModel){
		//override codegen.properties NAMESPACE_PREFIX property with GME namespace tag value, if it exists

		try {
			String namespaceUriPrefix = this.getModelNamespace(model, basePkgLogicalModel);
			if (namespaceUriPrefix != null) {
				this.namespaceUriPrefix = namespaceUriPrefix;
			}
		} catch (GenerationException e) {
			log.error("Exception caught trying to set GME model namespace URI Prefix: ", e);
		}
	}

	public String getDatabaseType() {
		return DATABASE_TYPE;
	}

	public boolean isIncluded(UMLClass klass) throws GenerationException
	{
		String fqcn = getFQCN(klass);

		return isIncluded(fqcn);
	}

	public boolean isIncluded(UMLInterface interfaze) throws GenerationException
	{
		String fqcn = getFQCN(interfaze);

		return isIncluded(fqcn);
	}

	public boolean isIncluded(String fqcn)
	{

		log.debug("isIncluded(String fqcn) for fqcn: "+fqcn);
		for (String excludePkgPattern:EXCLUDE_PACKAGE_PATTERNS)
			if (Pattern.matches(excludePkgPattern, fqcn))
				return false;


		for (String excludeClassPattern:EXCLUDE_CLASS_PATTERNS){
			if (Pattern.matches(excludeClassPattern, fqcn))
				return false;
		}

		for(String includePkgPattern: INCLUDE_PACKAGE_PATTERNS){
			log.debug("includePkgPattern: "+includePkgPattern+"; fqcn: "+fqcn);
			if(Pattern.matches(includePkgPattern, fqcn))
				return true;
		}

		return false;
	}

	public boolean isIncluded(UMLPackage pkg) throws GenerationException
	{
		String fullPkgName = getFullPackageName(pkg);
		log.debug("isIncluded(UMLPackage pkg) for fullPkgName: "+fullPkgName);

		for(String excludePkgPattern: EXCLUDE_PACKAGE_PATTERNS)
			if (Pattern.matches(excludePkgPattern, fullPkgName))
				return false;

		for(String includePkgPattern: INCLUDE_PACKAGE_PATTERNS)
			if (Pattern.matches(includePkgPattern, fullPkgName))
				return true;

		return true;
	}


	public boolean isNamespaceIncluded(UMLClass klass, String defaultNamespacePrefix) throws GenerationException
	{
		String pkgNamespace=null;

		try {
			pkgNamespace = getGMENamespace(klass);
		} catch (GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME Namespace tag value for: " + getFullPackageName(klass.getPackage()), ge);
		}

		if (pkgNamespace==null) //use default namespace
			pkgNamespace = defaultNamespacePrefix+getFullPackageName(klass);

		log.debug("* * * * * pkgNamespace:"+pkgNamespace);

		for(String excludePkgNamespacePattern: EXCLUDE_NAMESPACE_PATTERNS)
			if(Pattern.matches(excludePkgNamespacePattern,pkgNamespace)){
				return false;
			}

		return true;
	}

	public String getEmptySpace(Integer count)
	{
		String spaces = "";

		for(Integer i=0;i<count;i++)
			spaces +="\t";

		return spaces;
	}

	public String getFQEN(UMLTaggableElement elt) throws GenerationException {
		if (elt instanceof UMLClass)
			return getFQCN((UMLClass)elt);
		if (elt instanceof UMLPackage)
			return getFullPackageName((UMLPackage)elt);

		throw new GenerationException("Error getting fully qualified element name.  Supported taggable element types include UMLClass and UMLPackage; element is neither");
	}

	public String getFQCN(UMLClass klass)
	{
		return removeBasePackage(ModelUtil.getFullName(klass));
	}

	public String getFQCN(UMLInterface interfaze)
	{
		return removeBasePackage(ModelUtil.getFullName(interfaze));
	}

	public String getFullPackageName(UMLTaggableElement te)
	{
		if (te instanceof UMLClass)
			return removeBasePackage(ModelUtil.getFullPackageName((UMLClass)te));
		if (te instanceof UMLInterface)
			return removeBasePackage(ModelUtil.getFullPackageName((UMLInterface)te));
		if (te instanceof UMLPackage)
			return removeBasePackage(ModelUtil.getFullPackageName((UMLPackage)te));

		return "";
	}

	private String removeBasePackage(String path)
	{
		if(path.startsWith(BASE_PKG_LOGICAL_MODEL+"."))
			return path.substring(BASE_PKG_LOGICAL_MODEL.length()+1);
		else if(path.startsWith(BASE_PKG_DATA_MODEL+"."))
			return path.substring(BASE_PKG_DATA_MODEL.length()+1);
		else
			return path;
	}

	public String getBasePkgLogicalModel(){
		return BASE_PKG_LOGICAL_MODEL;
	}

	public String getBasePkgDataModel(){
		return BASE_PKG_DATA_MODEL;
	}

	public UMLClass getSuperClass(UMLClass klass) throws GenerationException
	{
		UMLClass[] superClasses = ModelUtil.getSuperclasses(klass);

		if(superClasses.length == 0) {
			log.debug("*** Getting superclass for class " + klass.getName() + ": " + null);
			return null;
		}

		if(superClasses.length > 1)
			throw new GenerationException("Class can not have more than one super class");

		log.debug("*** Getting superclass for class " + klass.getName() + ": " + superClasses[0].getName());

		return superClasses[0];
	}

	public void getSuperClassOperations(Map<String, List<UMLOperation>> operations, UMLClass klass) throws GenerationException
	{
		getSuperClassOperations(operations, klass, null);
	}

	public String getOperationText(UMLClass klass, UMLOperation operation) throws GenerationException
	{

		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		buffer.append("\t"+getJavaDocs(operation));
		buffer.append("\n");
		buffer.append("\t"+ModelUtil.getOperationSignature(operation, true));
		if(!operation.getAbstractModifier().isAbstract())
		{
			buffer.append("\n\t{\n\t\t");
			String operationBody = ModelUtil.getOperationBody(operation);
			if(operationBody != null)
			{
				buffer.append(operationBody);
				buffer.append("\n\t}\n");
			}
			else
				throw new GenerationException("Operation body is missing for operation: "+operation.getName() + " for class: "+klass.getName());
		}
		else
			buffer.append(";");

		return buffer.toString();
	}

	public void getSuperClassOperations(Map<String, List<UMLOperation>> operations, UMLClass klass, List<String> exclude) throws GenerationException
	{
		UMLClass superClass = getSuperClass(klass);
		if(superClass == null)
			return;

		List<UMLOperation> classperations = superClass.getOperations();
		String fullName = getFQCN(superClass);
		if(exclude == null || (exclude != null && !exclude.contains(fullName)))
		{
			if(classperations != null && classperations.size() > 0)
				operations.put(getFQCN(superClass), classperations);
		}
		getSuperClassOperations(operations, superClass);
	}

	public UMLOperation matchOperationSignature(String operationSignature, List<UMLOperation> operations)
	{
		if(operations == null || operations.size() ==0)
			return null;

		for(UMLOperation operation : operations)
		{
			String signature = ModelUtil.getOperationName(operation, false);
			if(operationSignature.equals(signature))
				return operation;
		}
		return null;
	}

	private void getSuperInterfaceOperations(Map<String, List<UMLOperation>> operations, UMLInterface interfaze) throws GenerationException
	{
		UMLInterface[] superInterfaces = getSuperInterface(interfaze);
		if(superInterfaces == null || superInterfaces.length == 0)
			return;

		//An interface cannot implement another interface, it can only extend another.
		UMLInterface superInterface = superInterfaces[0];
		operations.put(getFQCN(superInterface), superInterface.getOperations());
		getSuperInterfaceOperations(operations, interfaze);
	}

	public void getSuperInterfaceOperations(Map<String, List<UMLOperation>> operations, UMLClass klass) throws GenerationException
	{
		UMLInterface[] interfaces = ModelUtil.getInterfaces(klass);
		for(int i=0;i<interfaces.length;i++)
		{
			UMLInterface interfaze = interfaces[i];
			operations.put(getFQCN(interfaze), interfaze.getOperations());
			getSuperInterfaceOperations(operations, interfaze);

		}
	}

	public String getSuperClassString(UMLClass klass) throws GenerationException
	{
		UMLClass superClass = getSuperClass(klass);
		if(superClass == null)
			if (isJaxbEnabled()){
				return "";
			} else {
				return "";
			}
		else
			return "extends " + getFullPackageName(superClass)+"."+superClass.getName();
	}

	public UMLInterface[] getSuperInterface(UMLInterface interfaze) throws GenerationException
	{
		UMLInterface[] superInterfaces = ModelUtil.getSuperInterfaces(interfaze);

		if(superInterfaces.length == 0) {
			log.debug("*** Getting superinterface for interface " + interfaze.getName() + ": " + null);
			return null;
		}

		log.debug("*** Getting superinterface for interface " + interfaze.getName() + ": " + superInterfaces[0].getName());

		return superInterfaces;
	}

	public String getSuperInterfaceString(UMLInterface interfaze) throws GenerationException
	{
		String superInterfaceStr = "extends ";
		UMLInterface[] superInterfaces = getSuperInterface(interfaze);
		if(superInterfaces == null)
			return "";
		else {
			superInterfaceStr += superInterfaces[0].getName();

			for (int i = 1; i < superInterfaces.length; i++){
				superInterfaceStr += ", " + superInterfaces[i].getName();
			}

		}
		return superInterfaceStr;
	}

	public UMLInterface[] getInterfaces(UMLClass klass) throws GenerationException
	{
		UMLInterface[] interfaces = ModelUtil.getInterfaces(klass);

		if(interfaces.length == 0) {
			log.debug("*** Getting interface for class " + klass.getName() + ": " + null);
			return null;
		}

		log.debug("*** Getting superclass for class " + klass.getName() + ": " + interfaces[0].getName());

		return interfaces;
	}

	public String getInterfaceString(UMLClass klass) throws GenerationException
	{
		UMLInterface[] interfaces = getInterfaces(klass);
		if(interfaces == null)
			return "";
		else {
			String interfaceStr = "";
			for (UMLInterface interfaze : interfaces){
				interfaceStr += ", " + interfaze.getName();
			}
			return interfaceStr;
		}
	}

	public String getInterfaceImports(UMLInterface interfaze) throws GenerationException
	{
		StringBuilder sb = new StringBuilder();
		Set<String> importList = new HashSet<String>();
		UMLInterface[] interfaces = ModelUtil.getSuperInterfaces(interfaze);

		String pkgName = getFullPackageName(interfaze);

		for (UMLInterface superInterfaze : interfaces) {
			String superInterfacePkg = getFullPackageName(superInterfaze);
			if (!pkgName.equals(superInterfacePkg))
				importList.add(getFQCN(superInterfaze));
		}

		for(String importClass:importList)
			sb.append("import ").append(importClass).append(";\n");

		return sb.toString();
	}

	public String getImports(UMLClass klass) throws GenerationException
	{
		StringBuilder sb = new StringBuilder();
		Set<String> importList = new HashSet<String>();
		UMLClass[] superClasses = ModelUtil.getSuperclasses(klass);
		UMLInterface[] interfaces = ModelUtil.getInterfaces(klass);

		if(superClasses.length>1)
			throw new GenerationException("Class can not have more than one super classes");

		String pkgName = getFullPackageName(klass);
		if(superClasses.length == 1)
		{
			String superPkg = getFullPackageName(superClasses[0]);
			if(!pkgName.equals(superPkg))
				importList.add(getFQCN(superClasses[0]));
		}

		for (UMLInterface interfaze : interfaces) {
			String interfacePkg = getFullPackageName(interfaze);
			if (!pkgName.equals(interfacePkg))
				importList.add(getFQCN(interfaze));
		}

		for(UMLAttribute attr: klass.getAttributes())
		{
			if(getDataType(attr).startsWith("Collection") && !importList.contains("java.util.Collection"))
			{
				importList.add("java.util.Collection");
				break;
			}
		}
		if(isISO21090Enabled)
		{
			for(UMLAttribute attr: klass.getAttributes())
			{
				String javaName = isoDatatypeMap.get(attr.getDatatype().getName());
				if(javaName!=null && javaName.indexOf("<")>0)
				{
					String collectionType = javaName.substring(0,javaName.indexOf("<"));
					String collectionElementType = javaName.substring(javaName.indexOf("<")+1,javaName.indexOf(">"));
					//String collectionName = isoDatatypeMap.get(collectionType);

					if (!importList.contains("gov.nih.nci.iso21090."+collectionType))
						importList.add("gov.nih.nci.iso21090."+collectionType);
					javaName = collectionElementType;
				}

				if(javaName!=null && !importList.contains("gov.nih.nci.iso21090."+javaName))
				{
					importList.add("gov.nih.nci.iso21090."+javaName);
				}
			}
		}

		if(klass.getOperations() != null)
		{
			for(UMLOperation operation: klass.getOperations())
			{
				List<UMLOperationParameter> params = operation.getParameters();
				if(params == null || params.size() ==0)
					continue;

				for(UMLOperationParameter param:params)
				{
					if(param.getDataType() instanceof UMLClass)
					{
						String fullName = getFQCN((UMLClass)param.getDataType());
						if(!fullName.startsWith("java.lang."))
							importList.add(fullName);
					}
				}
			}
		}

		for(UMLAssociation association: klass.getAssociations())
		{
			List<UMLAssociationEnd> assocEnds = association.getAssociationEnds();
			UMLAssociationEnd otherEnd = getOtherEnd(klass,assocEnds);

			String assocKlass = getFQCN ((UMLClass)otherEnd.getUMLElement());
			if(!pkgName.equals(getFullPackageName ((UMLClass)otherEnd.getUMLElement())) && !importList.contains(assocKlass))
				importList.add(assocKlass);
			if(isAssociationEndMany(otherEnd) && otherEnd.isNavigable()&& !importList.contains("java.util.Collection"))
				importList.add("java.util.Collection");
		}

		importList.addAll(getHibernateValidatorConstraintImports(klass));

		for(String importClass:importList)
			sb.append("import ").append(importClass).append(";\n");

		if (isJaxbEnabled){
			sb.append("\n");
			sb.append("import com.sun.xml.bind.CycleRecoverable;\n");

			if(isISO21090Enabled) {
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISOAdapter;\n");
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISOIvlPqAdapter;\n");
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISOIvlRealAdapter;\n");
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISOIvlTsAdapter;\n");
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISOIvlIntAdapter;\n");
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISODsetAdAdapter;\n");
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISODsetIiAdapter;\n");
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISODsetCdAdapter;\n");
				sb.append("import gov.nih.nci.system.client.util.xml.JAXBISODsetTelAdapter;\n");
			}

			sb.append("import javax.xml.bind.annotation.XmlAccessType;\n");
			sb.append("import javax.xml.bind.annotation.XmlAccessorType;\n");
			sb.append("import javax.xml.bind.annotation.XmlAttribute;\n");
			sb.append("import javax.xml.bind.annotation.XmlElementWrapper;\n");
			sb.append("import javax.xml.bind.annotation.XmlElement;\n");
			sb.append("import javax.xml.bind.annotation.XmlRootElement;\n");
			sb.append("import javax.xml.bind.annotation.XmlSeeAlso;\n");
			sb.append("import javax.xml.bind.annotation.XmlTransient;\n");
			sb.append("import javax.xml.bind.annotation.XmlType;\n");
			sb.append("import gov.nih.nci.system.client.util.xml.CharAdapter;\n");
			sb.append("import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;\n");

			sb.append("\n");
		}

		sb.append("import java.io.Serializable;\n");
		//sb.append("import gov.nih.nci.system.annotate.NCIMetadata;\n");

		return sb.toString();
	}

	public String getJaxbXmlTransientAnnotation(){
		return "	@XmlTransient";
	}

	public String getJaxbCollectionAnnotations(UMLClass klass, UMLClass assocKlass, String rolename){

		StringBuilder sb = new StringBuilder();
		sb.append("	@XmlElementWrapper(name=\"").append(rolename).append("\",\n");
		sb.append("		namespace=\"").append(getNamespaceUriPrefix()).append(getFullPackageName(klass)).append("\")\n");
		sb.append("	@XmlElement(name=\"").append(assocKlass.getName()).append("\",\n");
		sb.append("		namespace=\"").append(getNamespaceUriPrefix()).append(getFullPackageName(assocKlass)).append("\")");

		return sb.toString();
	}

	public String getDataType(UMLAttribute attr)
	{
		UMLDatatype dataType = attr.getDatatype();
		String name = dataType.getName();
		if(dataType instanceof UMLClass)
			name = getFQCN((UMLClass)dataType);

		if(name.startsWith("java.lang."))
			name = name.substring("java.lang.".length());

		if(name.startsWith(ISO_ROOT_PACKAGE_NAME+"."))
			name = name.substring((ISO_ROOT_PACKAGE_NAME+".").length());

		String returnValue = null;
		if(isISO21090Enabled)
			returnValue = isoDatatypeMap.get(name);

		if(returnValue == null)
			returnValue = javaDatatypeMap.get(name.toLowerCase());

		return returnValue == null ? "" : returnValue;
	}

	public HashMap<String, String> getPKGeneratorTags(UMLClass table,String fqcn,UMLAttribute classIdAttr) throws GenerationException {
		HashMap<String, String> pkTags = new HashMap<String, String>();
		String pkgenClassKey = TV_PK_GENERATOR + DATABASE_TYPE;

		UMLAttribute tableIdAttribute=getMappedColumn(table,fqcn+"."+classIdAttr.getName());
		Collection<UMLTaggedValue> tableTaggedValues = tableIdAttribute.getTaggedValues();
		String pkGeneratorClass = getTagValue(tableTaggedValues,pkgenClassKey, 1);

		if (pkGeneratorClass != null && !("".equals(pkGeneratorClass))) {
			for (int i = 1; i <= tableTaggedValues.size(); i++) {
				String pkgenProp = TV_PK_GENERATOR_PROPERTY + i + "."+ DATABASE_TYPE;
				String pkParam = getTagValue(tableTaggedValues, pkgenProp, 1);
				StringTokenizer tokenizer = new StringTokenizer(pkParam, ":");
				if(tokenizer.hasMoreTokens()){
					pkTags.put(tokenizer.nextToken(), tokenizer.nextToken());
				}
			}
			pkTags.put(pkgenClassKey, pkGeneratorClass);
		} else {
			pkTags.put(PK_GENERATOR_SYSTEMWIDE+DATABASE_TYPE, IDENTITY_GENERATOR_TAG);
		}
		return pkTags;
	}


	public String getHibernateDataType(UMLClass klass, UMLAttribute attr) throws GenerationException
	{
		log.debug("getHibernateDataType for klass: " + klass.getName() + ", attr: " + attr.getName());
		String fqcn = getFQCN(klass);
		UMLClass table = getTable(klass);
		UMLAttribute col = getMappedColumn(table,fqcn+"."+attr.getName());

		Boolean isClob = "CLOB".equalsIgnoreCase(getTagValue(col.getTaggedValues(),TV_TYPE, 1));

		UMLDatatype dataType = attr.getDatatype();
		String name = dataType.getName();
		if(dataType instanceof UMLClass)
			name = getFQCN((UMLClass)dataType);

		if(name.startsWith("java.lang."))
			name = name.substring("java.lang.".length());

		if(isClob && "string".equalsIgnoreCase(name))
			return "text";
		if(isClob && !"string".equalsIgnoreCase(name))
			throw new GenerationException("Can not map CLOB to anything other than String");
		if("byte[]".equalsIgnoreCase(name))
			return "org.springframework.orm.hibernate3.support.BlobByteArrayType";
		if("int".equalsIgnoreCase(name) || "integer".equalsIgnoreCase(name))
			return "integer";
		if("double".equalsIgnoreCase(name))
			return "double";
		if("float".equalsIgnoreCase(name))
			return "float";
		if("long".equalsIgnoreCase(name))
			return "long";
		if("string".equalsIgnoreCase(name))
			return "string";
		if("char".equalsIgnoreCase(name) || "character".equalsIgnoreCase(name))
			return "character";
		if("boolean".equalsIgnoreCase(name) )
			return "boolean";
		if("byte".equalsIgnoreCase(name) )
			return "byte";
		if("short".equalsIgnoreCase(name) )
			return "short";

		if("date".equalsIgnoreCase(name) || "java.util.date".equalsIgnoreCase(name))
			return "java.util.Date";

		if(isISO21090Enabled)
		{
			if(name.startsWith(ISO_ROOT_PACKAGE_NAME+"."))
				name = name.substring((ISO_ROOT_PACKAGE_NAME+".").length());
			if("II".equals(name))
				return "gov.nih.nci.iso21090.hibernate.usertype.IiUserType";
		}
		//log.info("Type = "+name);

		return name;
	}

	public String getGetterMethodName(UMLAttribute attr)
	{
		String name = attr.getName();
		return "get"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
	}

	public String getSetterMethodName(UMLAttribute attr)
	{
		String name = attr.getName();
		return "set"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
	}

	public boolean matchSetOperationName(UMLAttribute attr, List<UMLOperation> operations)
	{
		String setterOperationName = getSetterMethodName(attr) + "(" + getDataType(attr) + " param0)";
		if(matchOperationSignature(setterOperationName, operations) != null)
			return true;

		return false;
	}

	public boolean matchSetOperationName(UMLAssociationEnd assocEnd, UMLClass clazz, List<UMLOperation> operations)
	{
		String setterOperationName = getSetterMethodName(assocEnd) + "(Collection<" + clazz.getName() + "> param0)";
		if(matchOperationSignature(setterOperationName, operations) != null)
			return true;

		return false;
	}

	public boolean matchGetOperationName(UMLAttribute attr, List<UMLOperation> operations)
	{
		String getterOperationName = getGetterMethodName(attr) + "()";
		if(matchOperationSignature(getterOperationName, operations) != null)
			return true;

		return false;
	}

	public boolean matchGetOperationName(UMLAssociationEnd assocEnd, List<UMLOperation> operations)
	{
		String getterOperationName = getGetterMethodName(assocEnd) + "()";
		if(matchOperationSignature(getterOperationName, operations) != null)
			return true;

		return false;
	}

	public boolean isBidirectionalSelfAssociation(UMLClass klass, List<UMLAssociationEnd>assocEnds) throws GenerationException
	{
		UMLAssociationEnd end1 = assocEnds.get(0);
		UMLAssociationEnd end2 = assocEnds.get(1);

		if(end1.getUMLElement().equals(klass) && end2.getUMLElement().equals(klass) && end1.isNavigable() && end2.isNavigable())
			return true;

		return false;
	}

	public UMLAssociationEnd getThisEnd(UMLClass klass, List<UMLAssociationEnd>assocEnds) throws GenerationException
	{
		UMLAssociationEnd end1 = assocEnds.get(0);
		UMLAssociationEnd end2 = assocEnds.get(1);

		if(end1.getUMLElement().equals(klass))
			return end1;
		else if(end2.getUMLElement().equals(klass))
			return end2;
		else
			throw new GenerationException("Could not figureout this end");
	}

	public UMLAssociationEnd getThisEnd(UMLClass klass, List<UMLAssociationEnd>assocEnds, boolean includeParents) throws GenerationException
	{
		UMLAssociationEnd end1 = assocEnds.get(0);
		UMLAssociationEnd end2 = assocEnds.get(1);
		UMLClass parent = klass;
		while(parent != null)
		{
			if(end1.getUMLElement().equals(parent))
				return end1;
			else if(end2.getUMLElement().equals(parent))
				return end2;
			else if(includeParents)
			{
				parent = getSuperClass(parent);
			}
			else
				throw new GenerationException("Could not figureout this end");
		}
		throw new GenerationException("Could not figureout this end");
	}

	public UMLAssociationEnd getOtherEnd(UMLClass klass, List<UMLAssociationEnd>assocEnds, boolean includeParents) throws GenerationException
	{
		UMLAssociationEnd end1 = assocEnds.get(0);
		UMLAssociationEnd end2 = assocEnds.get(1);

		UMLClass parent = klass;
		while(parent != null)
		{
			if(end1.getUMLElement().equals(parent))
				return end2;
			else if(end2.getUMLElement().equals(parent))
				return end1;
			else if(includeParents)
			{
				parent = getSuperClass(parent);
			}
			else
				throw new GenerationException("Could not figureout other end");
		}
		throw new GenerationException("Could not figureout this end");
	}

	public UMLAssociationEnd getOtherEnd(UMLClass klass, List<UMLAssociationEnd>assocEnds) throws GenerationException
	{
		UMLAssociationEnd end1 = assocEnds.get(0);
		UMLAssociationEnd end2 = assocEnds.get(1);
		if(end1.getUMLElement().equals(klass))
			return end2;
		else if(end2.getUMLElement().equals(klass))
			return end1;
		else
			throw new GenerationException("Could not figureout other end" );
	}

	public Boolean isAssociationEndMany(UMLAssociationEnd assocEnd)
	{
		if((assocEnd.getHighMultiplicity()<0)||(assocEnd.getLowMultiplicity()<0))
			return true;
		else
			return false;
	}

	public Boolean isImplicitParent(UMLAssociationEnd assocEnd)
	{
		return isImplicitParent((UMLClass)assocEnd.getUMLElement());
	}

	public String getGetterMethodName(UMLAssociationEnd assocEnd)
	{
		String name = assocEnd.getRoleName();
		return "get"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
	}

	public String getSetterMethodName(UMLAssociationEnd assocEnd)
	{
		String name = assocEnd.getRoleName();
		return "set"+name.substring(0,1).toUpperCase()+name.substring(1,name.length());
	}

	public Boolean isSelfAssociation(UMLAssociationEnd assocEnd1,UMLAssociationEnd assocEnd2)
	{
		return assocEnd1.getUMLElement().equals(assocEnd2.getUMLElement());
	}

	public String getClassIdGetterMthod(UMLClass klass) throws GenerationException
	{
		String idAttrName = getClassIdAttrName(klass);
		if (idAttrName == null) return null;
		return "get"+firstCharUpper(getClassIdAttrName(klass));
	}

	private String firstCharUpper(String data)
	{
		if(data == null || data.length() == 0) return data;
		return Character.toUpperCase(data.charAt(0)) + data.substring(1);
	}

	public String getClassIdAttrName(UMLClass klass) throws GenerationException
	{
		UMLAttribute idAttr = getClassIdAttr(klass);
		if (idAttr == null) return null;

		return getClassIdAttr(klass).getName();
	}

	public List<UMLAttribute> getClassAttributes(UMLClass klass, boolean includeParents) throws GenerationException
	{

		List<UMLAttribute> attributes = new ArrayList<UMLAttribute>();
		attributes.addAll(klass.getAttributes());
		for(UMLGeneralization gen: klass.getGeneralizations())
		{
			if(gen.getSubtype().equals(klass) && !gen.getSupertype().equals(klass))
			{
				attributes.addAll(getClassAttributes((UMLClass)gen.getSupertype(), true));
				//attributes.addAll(((UMLClass)gen.getSupertype()).getAttributes());
			}
		}
		return attributes;
	}

	public UMLAttribute getClassIdAttr(UMLClass klass) throws GenerationException
	{

		String fqcn = getFQCN(klass);
		UMLAttribute idAttr = getColumn(klass,TV_ID_ATTR_COLUMN, fqcn,true,0,1);
		if(idAttr !=null) return idAttr;

		String idAttrName = "id";
		for(UMLAttribute attribute:klass.getAttributes())
		{
			if(idAttrName.equals(attribute.getName()))
				return attribute;
		}

		for(UMLGeneralization gen: klass.getGeneralizations())
		{
			if(gen.getSubtype().equals(klass) && !gen.getSupertype().equals(klass))
			{
				UMLAttribute superId = getClassIdAttr((UMLClass)gen.getSupertype());
				if(superId != null)
					return superId;
			}
		}

		return null;
		//throw new GenerationException("No attribute found that maps to the primary key identifier for class : "+fqcn);
	}

	public Boolean isCollection(UMLClass klass, UMLAttribute attr ) throws GenerationException
	{
		if(getDataType(attr).startsWith("Collection"))
			return true;
		return false;
	}

	public boolean isStatic(UMLAttribute att){

		UMLTaggedValue tValue = att.getTaggedValue("static");

		if (tValue == null) {
			return false;
		}

		log.debug("UMLAttribute 'static' Tagged Value: " + tValue.getValue());
		return ("1".equalsIgnoreCase(tValue.getValue()));
	}

	public boolean isAbstract(UMLClass klass){
		return klass.getAbstractModifier().isAbstract();
	}

	public String getType(UMLAssociationEnd assocEnd){

		UMLTaggedValue tValue = assocEnd.getTaggedValue("type");

		if (tValue == null) {
			return "";
		}

		log.debug("UMLAttribute Type Tagged Value: " + tValue.getValue());
		return tValue.getValue();
	}

	public UMLAssociationEnd getOtherAssociationEnd(UMLAssociationEnd assocEnd) {
		UMLAssociationEnd otherAssocEnd = null;
		for (Iterator i = assocEnd.getOwningAssociation().getAssociationEnds().iterator(); i
				.hasNext();) {
			UMLAssociationEnd ae = (UMLAssociationEnd) i.next();
			if (ae != assocEnd) {
				otherAssocEnd = ae;
				break;
			}
		}
		return otherAssocEnd;
	}

	public String getUpperBound(UMLAssociationEnd otherEnd) {

		int multiplicity = otherEnd.getHighMultiplicity();
		String finalMultiplicity = new String();
		if (multiplicity == -1) {
			finalMultiplicity = "unbounded";
		} else {
			Integer x = new Integer(multiplicity);
			finalMultiplicity = x.toString();
		}
		return finalMultiplicity;
	}

	public String getLowerBound(UMLAssociationEnd otherEnd) {

		int multiplicity = otherEnd.getLowMultiplicity();
		String finalMultiplicity = new String();
		if (multiplicity == -1) {
			finalMultiplicity = "unbounded";
		} else {
			Integer x = new Integer(multiplicity);

			finalMultiplicity = x.toString();
		}
		return finalMultiplicity;
	}

	public String getMultiplicityValue(UMLAssociationEnd assocEnd){
		Element element = ((UMLAssociationEndBean)assocEnd).getJDomElement();

		org.jdom.Attribute multAtt = element.getAttribute("multiplicity");
		//log.debug("associationEnd: " + assocEnd.getRoleName() + "; multiplicity: " + multAtt.getValue());
		if (multAtt!=null)
			return multAtt.getValue();


		int low = assocEnd.getLowMultiplicity();
		int high = assocEnd.getHighMultiplicity();
		if(low <0 && high<0)
			return "";

		if(low >=0 && high>=0)
			return low+".."+high;

		if(low<0)
			return high+"";

		return low+"";
	}

	public boolean isMultiplicityValid(UMLAssociationEnd assocEnd){
		String multValue = getMultiplicityValue(assocEnd);

		if(multValue == null || "".equalsIgnoreCase(multValue) || multValue.startsWith(".") || multValue.endsWith("."))
			return false;

		return true;
	}


	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public boolean isMany2One(UMLAssociationEnd thisEnd, UMLAssociationEnd otherEnd) {
		return isAssociationEndMany(thisEnd) && !isAssociationEndMany(otherEnd);
	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public boolean isAny(UMLAssociationEnd thisEnd,UMLAssociationEnd otherEnd) {
		return isAssociationEndMany(thisEnd) && !isAssociationEndMany(otherEnd) && isImplicitParent(otherEnd);
	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public boolean isOne2Many(UMLAssociationEnd thisEnd,UMLAssociationEnd otherEnd) {
		return !isAssociationEndMany(thisEnd) && isAssociationEndMany(otherEnd);
	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public boolean isMany2Many(UMLAssociationEnd thisEnd,UMLAssociationEnd otherEnd) {
		return isAssociationEndMany(thisEnd) && isAssociationEndMany(otherEnd);
	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public boolean isMany2Any(UMLAssociationEnd thisEnd,UMLAssociationEnd otherEnd) {
		return isAssociationEndMany(thisEnd) && isAssociationEndMany(otherEnd) && isImplicitParent(otherEnd);
	}

	/**
	 * @param thisEnd
	 * @param otherEnd
	 * @return
	 */
	public boolean isOne2One(UMLAssociationEnd thisEnd,UMLAssociationEnd otherEnd) {
		return !isAssociationEndMany(thisEnd) && !isAssociationEndMany(otherEnd);
	}


	public Collection getAssociationEnds(UMLClass klass) {
		return getAssociationEnds(klass, false);
	}

	public Collection getAssociationEnds(UMLClass klass,
			boolean includeInherited) {
		log.debug("class = " + klass.getName() + ", includeInherited = "
				+ includeInherited);
		List<UMLAssociationEnd> assocEndsList = new ArrayList<UMLAssociationEnd>();
		UMLClass superClass = klass;
		while (superClass != null) {
			Collection assocs = superClass.getAssociations();
			log.debug( superClass.getName() + " association collection size(): " + assocs.size());

			for (Iterator i = assocs.iterator(); i.hasNext();) {
				UMLAssociation assoc = (UMLAssociation) i.next();

				for (UMLAssociationEnd ae:assoc.getAssociationEnds()){
					UMLAssociationEnd otherEnd = getOtherAssociationEnd(ae);
					String id = ((UMLClass)(otherEnd.getUMLElement())).getName() + Constant.LEFT_BRACKET
							+ getFQCN((UMLClass)(otherEnd.getUMLElement())) + Constant.RIGHT_BRACKET;

					log.debug("id (otherEnd): " + id);
					log.debug("superClass: " + superClass.getName());

					if ((UMLClass)ae.getUMLElement() == superClass) {
						log.debug("adding association: " + id + " for class " + superClass.getName());
						assocEndsList.add(ae);
					}
				}
			}
			if (includeInherited) {
				// TODO :: Implement includeInherited
//				Collection gens = superClass.getGeneralization();
//				if (gens.size() > 0) {
//					superClass = (Classifier) ((Generalization) gens.iterator()
//							.next()).getParent();
//				} else {
//					superClass = null;
//				}
				log.debug("Need to implement includeInherited");

			} else {
				superClass = null;
			}
		}

		return assocEndsList;
	}

	public List<UMLAssociation> getAssociations(UMLClass klass,
			boolean includeInherited) throws GenerationException{
		log.debug("class = " + klass.getName() + ", includeInherited = "
				+ includeInherited);
		List<UMLAssociation> assocList = new ArrayList<UMLAssociation>();
		UMLClass superClass = klass;
		while (superClass != null) {
			Collection assocs = superClass.getAssociations();
			log.debug( superClass.getName() + " association collection size(): " + assocs.size());
			for (Iterator i = assocs.iterator(); i.hasNext();) {
				UMLAssociation assoc = (UMLAssociation) i.next();
				assocList.add(assoc);
			}
			if (!includeInherited)
				superClass = null;
			else
				superClass = getSuperClass(superClass);
		}

		return assocList;
	}

	public void collectPackages(Collection<UMLPackage> nextLevelPackages, Hashtable<String, Collection<UMLClass>> pkgColl) throws GenerationException
	{
		for(UMLPackage pkg:nextLevelPackages){

			if (isIncluded(pkg)){
				String pkgName=getFullPackageName(pkg);
				log.debug("including package: " + pkgName);

				Collection<UMLClass> pkgClasses = pkg.getClasses();

				if (pkgClasses != null && pkgClasses.size() > 0){
					for (UMLClass klass:pkgClasses){
						if(!STEREO_TYPE_TABLE.equalsIgnoreCase(klass.getStereotype()) && isIncluded(klass)) {
							if(!pkgColl.containsKey(pkgName)) {
								List<UMLClass> classes = new ArrayList<UMLClass>();
								classes.add(klass);
								pkgColl.put(pkgName, classes);
							} else {
								Collection<UMLClass> existingCollection = pkgColl.get(pkgName);
								existingCollection.add(klass);
							}
						}
					}
				}
			} else{
				log.debug("excluding package: " + pkg.getName());
			}
			collectPackages(pkg.getPackages(), pkgColl);
		}
	}

	public void collectPackages(Collection<UMLClass> allClasses, Hashtable<String, Collection<UMLClass>> pkgColl,String defaultNamespacePrefix)
	throws GenerationException {

		String pkgName=null;
		String pkgNamespace=null;
		for(UMLClass klass:allClasses){
			pkgName = getGMEPackageName(klass);
			if (pkgName == null)
				pkgName=getFullPackageName(klass);
			log.debug("processing klass: " + klass.getName() + " of package " + pkgName);
			if (isNamespaceIncluded(klass,defaultNamespacePrefix)){
				log.debug("including package: " + pkgName);

				if(!STEREO_TYPE_TABLE.equalsIgnoreCase(klass.getStereotype()) && isIncluded(klass)) { //No longer using GME ClassName; e.g., no longer using isIncluded(pkgName+"."+getClassName(klass))) {
					pkgNamespace=getGMENamespace(klass);

					if (pkgNamespace !=null && (pkgNamespace.endsWith("/") || !pkgNamespace.endsWith(pkgName)))
						pkgNamespace=pkgNamespace+pkgName;
					log.debug("pkgNamespace: " + pkgNamespace);
					if(!pkgColl.containsKey(pkgNamespace)) {
						List<UMLClass> classes = new ArrayList<UMLClass>();
						classes.add(klass);
						pkgColl.put(pkgNamespace, classes);
					} else {
						Collection<UMLClass> existingCollection = pkgColl.get(pkgNamespace);
						existingCollection.add(klass);
					}
				}
			} else{
				log.debug("excluding class: " +klass.getName()+" with package: " + pkgName);
			}
		}

	}

	public String getGMEPackageName(UMLClass klass) throws GenerationException{
		String namespacePkgName = null;
		try {
			namespacePkgName = getNamespacePackageName(klass);
			if (namespacePkgName!=null && namespacePkgName.length()>0)
				return namespacePkgName;
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME package name for: " + getFQEN(klass), ge);
		}

		namespacePkgName=getGMEPackageName(klass.getPackage());
		if (namespacePkgName!=null && namespacePkgName.length()>0)
			return namespacePkgName;

		log.debug("GME Package name not found for: "+getFullPackageName(klass)+". Returning null");
		return null;
	}

	public String getGMEPackageName(UMLPackage pkg) throws GenerationException{
		if (pkg==null)
			return null;

		log.debug("Getting Package Name for: " +pkg.getName());
		String namespacePkgName = getNamespacePackageName(pkg);
		if (namespacePkgName!=null && namespacePkgName.length()>0)
			return namespacePkgName;

		return getGMEPackageName(pkg.getParent());
	}

	private String getClassName(UMLClass klass)throws GenerationException{
		try {
			String klassName = getXMLClassName(klass);
			if (klassName!=null)
				return klassName;
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME Class (XML Element) name for klass: " + getFQCN(klass));
		}
		return klass.getName();
	}

	/**
	 * Returns all the classes (not the tables) in the XMI file which do not belong to java.lang or java.util package
	 * @param model
	 * @return
	 */
	public Collection<UMLClass> getAllClasses(UMLModel model) throws GenerationException
	{
		Collection<UMLClass> classes = null;
		try {
			classes = new HashSet<UMLClass>();
			getAllClasses(model.getPackages(),classes);
		} catch(Exception e){
			log.error("Unable to retrieve classes from model: ", e);
			throw new GenerationException("Unable to retrieve classes from model: ", e);
		}
		return classes;
	}

	private void getAllClasses(Collection<UMLPackage> pkgCollection,Collection<UMLClass> classes)throws GenerationException
	{
		for(UMLPackage pkg:pkgCollection)
			getAllClasses(pkg,classes);
	}

	private void getAllClasses(UMLPackage rootPkg,Collection<UMLClass> classes) throws GenerationException
	{
		if(isIncluded(rootPkg))
		{
			for(UMLClass klass:rootPkg.getClasses())
			{
				if(!STEREO_TYPE_TABLE.equalsIgnoreCase(klass.getStereotype()) && isIncluded(klass))
					classes.add(klass);
			}
		}
		getAllClasses(rootPkg.getPackages(),classes);
	}

	/**
	 * Returns all the classes (not the tables) in the XMI file which do not belong to java.lang or java.util package
	 * @param model
	 * @return
	 */
	public Collection<UMLPackage> getAllPackages(UMLModel model) throws GenerationException
	{
		Collection<UMLPackage> packages = null;
		try {
			packages = new HashSet<UMLPackage>();
			getAllPackages(model.getPackages(),packages);
		} catch(Exception e){
			log.error("Unable to retrieve classes from model: ", e);
			throw new GenerationException("Unable to retrieve classes from model: ", e);
		}
		return packages;
	}

	private void getAllPackages(Collection<UMLPackage> pkgCollection,Collection<UMLPackage> packages)throws GenerationException
	{
		for(UMLPackage pkg:pkgCollection)
			getAllPackages(pkg,packages);
	}

	private void getAllPackages(UMLPackage rootPkg,Collection<UMLPackage> packages) throws GenerationException
	{
		if(isIncluded(rootPkg))
		{
			//if(!STEREO_TYPE_TABLE.equalsIgnoreCase(rootPkg.getStereotype()))
				packages.add(rootPkg);
		}
		getAllPackages(rootPkg.getPackages(),packages);
	}


	/**
	 * Returns all the interfaces in the XMI file which do not belong to java.lang or java.util package
	 * @param model
	 * @return
	 */
	public Collection<UMLInterface> getAllInterfaces(UMLModel model) throws GenerationException
	{
		Collection<UMLInterface> interfaces = null;
		try {
			interfaces = new HashSet<UMLInterface>();
			getAllInterfaces(model.getPackages(),interfaces);
		} catch(Exception e){
			log.error("Unable to retrieve interfaces from model: ", e);
			throw new GenerationException("Unable to retrieve interfaces from model: ", e);
		}
		return interfaces;
	}

	private void getAllInterfaces(Collection<UMLPackage> pkgCollection,Collection<UMLInterface> interfaces)throws GenerationException
	{
		for(UMLPackage pkg:pkgCollection)
			getAllInterfaces(pkg,interfaces);
	}

	private void getAllInterfaces(UMLPackage rootPkg,Collection<UMLInterface> interfaces) throws GenerationException
	{
		if(isIncluded(rootPkg))
		{
			for(UMLInterface interfaze:rootPkg.getInterfaces())
			{
				if(!STEREO_TYPE_TABLE.equalsIgnoreCase(interfaze.getStereotype()) && isIncluded(interfaze))
					interfaces.add(interfaze);
			}
		}
		getAllInterfaces(rootPkg.getPackages(),interfaces);
	}

	public Collection<UMLClass> getAllHibernateClasses(UMLModel model) throws GenerationException
	{
		Collection<UMLClass> allHibernateClasses = getAllParentClasses(model);
		allHibernateClasses.addAll(getAllImplicitParentLeafClasses(model));

		return allHibernateClasses;
	}

	/**
	 * Returns all the classes (not the tables) in the XMI file which do not belong to java.lang or java.util package.
	 * The class also have to be the root class in the inheritnace hierarchy to be included in the final list
	 * @param model
	 * @return
	 */
	public Collection<UMLClass> getAllParentClasses(UMLModel model) throws GenerationException
	{
		Collection<UMLClass> classes = new ArrayList<UMLClass>();
		getAllParentClasses(model.getPackages(),classes);
		return classes;
	}

	private void getAllParentClasses(Collection<UMLPackage> pkgCollection,Collection<UMLClass> classes) throws GenerationException
	{
		for(UMLPackage pkg:pkgCollection)
			getAllParentClasses(pkg,classes);
	}


	private void getAllParentClasses(UMLPackage rootPkg,Collection<UMLClass> classes)throws GenerationException
	{
		if(isIncluded(rootPkg))
		{
			for(UMLClass klass:rootPkg.getClasses())
			{
				if(!STEREO_TYPE_TABLE.equalsIgnoreCase(klass.getStereotype()) && isIncluded(klass) && ModelUtil.getSuperclasses(klass).length == 0 && !isImplicitParent(klass))
					classes.add(klass);
			}
		}
		getAllParentClasses(rootPkg.getPackages(),classes);
	}

	/**
	 * Returns all the classes (not the tables) in the XMI file which do not belong to java.lang or java.util package.
	 * The class also has to be an implicit parent (parent class with no table mapping) in the inheritance hierarchy to be included in the final list
	 * @param model
	 * @return
	 */
	public Collection<UMLClass> getAllImplicitParentLeafClasses(UMLModel model) throws GenerationException
	{
		Collection<UMLClass> classes = new ArrayList<UMLClass>();
		getAllImplicitParentLeafClasses(model.getPackages(),classes);
		return classes;
	}

	private void getAllImplicitParentLeafClasses(Collection<UMLPackage> pkgCollection,Collection<UMLClass> classes) throws GenerationException
	{
		for(UMLPackage pkg:pkgCollection)
			getAllImplicitParentLeafClasses(pkg,classes);
	}

	private void getAllImplicitParentLeafClasses(UMLPackage rootPkg,Collection<UMLClass> classes) throws GenerationException
	{
		if(isIncluded(rootPkg))
		{
			for(UMLClass klass:rootPkg.getClasses())
			{
				try {
					if(!STEREO_TYPE_TABLE.equalsIgnoreCase(klass.getStereotype()) && isIncluded(klass) && isImplicitParent(getSuperClass(klass)) && !isImplicitParent(klass))
						classes.add(klass);
				} catch(GenerationException e){
					continue;
				}
			}
		}
		getAllImplicitParentLeafClasses(rootPkg.getPackages(),classes);
	}


	/**
	 * Retrieves the table corresponding to the Dependency link between class and a table.
	 * If there are no Dependencies that links the class to table or there is more than
	 * one Dependency then the method throws an exception
	 *
	 * @param klass
	 * @return
	 * @throws GenerationException
	 */
	public UMLClass getTable(UMLClass klass) throws GenerationException
	{
		Set<UMLDependency> dependencies = klass.getDependencies();
		Map<String,UMLClass> clientMap = new HashMap<String,UMLClass>();
		int count = 0;
		UMLClass result = null;
		for(UMLDependency dependency:dependencies)
		{
			UMLClass client = (UMLClass) dependency.getClient();

			log.debug("getTable: klass: " + klass.getName() + "; Client " + client.getName() + " stereotype: " +client.getStereotype() + "; dependency stereotype: " + dependency.getStereotype());
			if(STEREO_TYPE_TABLE.equalsIgnoreCase(client.getStereotype()) && STEREO_TYPE_DATASOURCE_DEPENDENCY.equalsIgnoreCase(dependency.getStereotype()))
			{
				log.debug("* * * client.getName(): " + client.getName());
				clientMap.put(client.getName(), client);
				result = client;
			}
		}

		count = clientMap.size();

		if(count<1){
			log.debug("getTable: klass: " +klass.getName()+"; count: " + count);
			GenerationException ge = new GenerationException("No table found for : "+getFQCN(klass)+".  Make sure the corresponding Data Model table (class) has a 'table' Stereotype assigned, and the Dependency between the Data Model table and Logical Model class has a 'DataSource' Stereotype assigned.");
			log.debug(ge);
			throw ge;
		}

		if(count>1){
			log.debug("getTable: klass: " +klass.getName()+"; count: " + count);
			GenerationException ge = new GenerationException("More than one table found for : "+getFQCN(klass)+".  Make sure there is only one corresponding Data Model table (class) that has a 'table' Stereotype assigned, and the Dependency between the Data Model table and Logical Model class has a 'DataSource' Stereotype assigned.");
			log.error(ge);
			throw ge;
		}


		return result;
	}

	/**
	 * Determines whether the input class is an implicit superclass.  Used
	 * by the code generator to determine whether an implicit inheritance
	 * hibernate mapping should be created for the input class
	 * @param klass
	 * @return
	 * @throws GenerationException
	 */
	public boolean isImplicitParent(UMLClass klass)
	{
		if (klass != null)
			log.debug("isImplicitClass " + klass.getName()+": " + (isSuperclass(klass) && hasNoTableMapping(klass)));

		return (isSuperclass(klass) && hasNoTableMapping(klass));
	}

	public boolean hasImplicitParent(UMLClass klass){
		UMLClass superclass = klass;
		do {
			try {
				superclass = getSuperClass(superclass);

				if(isImplicitParent(superclass)){
					return true;
				}
			} catch (GenerationException e) {
				log.error("ERROR encountered checking if class " +klass.getName() + " has an implicit parent: ", e);
				return false;
			}

		} while (!(superclass==null) && !(superclass.getName().equalsIgnoreCase("java.lang.Object")));

		return false;
	}
	/**
	 * Determines whether the input class is a subclass
	 * @param klass
	 * @return
	 */
	public boolean isSubClass(UMLClass klass)
	{
		boolean isSubClass = false;

		if (klass != null)
			for(UMLGeneralization gen:klass.getGeneralizations())
			{
				if(gen.getSupertype() instanceof UMLClass && ((UMLClass)gen.getSupertype()) != klass)
					return true;
	}

		return isSubClass;
	}
	/**
	 * Determines whether the input class is a superclass
	 * @param klass
	 * @return
	 */
	private boolean isSuperclass(UMLClass klass)
	{
		boolean isSuperClass = false;

		if (klass != null)
			for(UMLGeneralization gen:klass.getGeneralizations())
			{
				if(gen.getSupertype() instanceof UMLClass && ((UMLClass)gen.getSupertype()) == klass)
					return true;
			}

		return isSuperClass;
	}

	/**
	 * Determines whether the input class is missing a table mapping
	 * @param klass
	 * @return
	 */
	private boolean hasNoTableMapping(UMLClass klass)
	{
		try {
			getTable(klass);
		} catch (GenerationException e){
			return true;
		}
		return false;
	}

	/**
	 * Scans the tag values of the association to determine which JOIN table the association is using.
	 *
	 * @param association
	 * @param model
	 * @param klass
	 * @return
	 * @throws GenerationException
	 */
	public UMLClass findCorrelationTable(UMLAssociation association, UMLModel model, UMLClass klass) throws GenerationException
	{
		return findCorrelationTable(association, model, klass, true);
	}

	public UMLClass findCorrelationTable(UMLAssociation association, UMLModel model, UMLClass klass, boolean throwException) throws GenerationException
	{
		int minReq = throwException ? 1:0;
		String tableName = getTagValue(klass, association,TV_CORRELATION_TABLE, null,minReq,1);

		if(!throwException && (tableName == null || tableName.length() ==0)) return null;

		UMLClass correlationTable = ModelUtil.findClass(model,BASE_PKG_DATA_MODEL+"."+tableName);
		if(correlationTable == null) {
			List<UMLAssociationEnd> assocEnds = association.getAssociationEnds();
			UMLAssociationEnd otherEnd = getOtherEnd(klass,assocEnds);
			UMLAssociationEnd thisEnd = getThisEnd(klass,assocEnds);
			throw new GenerationException("No correlation table found named : \""+tableName+"\".  Check the 'correlation-table' tag value assigned the association link attached to class " + getFQCN(klass) + " with rolenames '" + otherEnd.getRoleName() + "' and '" + thisEnd.getRoleName() + "'.");
		}

		return correlationTable;
	}

	public String getMappedColumnName(UMLClass table, String fullyQualifiedAttrName) throws GenerationException
	{
		return getColumnName(table,TV_MAPPED_ATTR_COLUMN,fullyQualifiedAttrName,false,1,1);
	}

	public UMLAttribute getMappedColumn(UMLClass table, String fullyQualifiedAttrName) throws GenerationException
	{
		return getColumn(table,TV_MAPPED_ATTR_COLUMN,fullyQualifiedAttrName,false,1,1);
	}


	public String getcaDSRPublicIdCustomTag(UMLTaggableElement tgElt)
	{
		return getTagValue(tgElt, "CADSR_DE_ID");
	}


	public String getcaDSRPublicVersionCustomTag(UMLTaggableElement tgElt)
	{
		return getTagValue(tgElt, "CADSR_DE_VERSION");
	}

	public String getMetadataAnnotation(UMLClass klass, UMLAttribute attr)
	{
		StringBuffer buff = new StringBuffer();
		String idValue = getcaDSRPublicIdCustomTag(attr);
		String versionValue = getcaDSRPublicVersionCustomTag(attr);
		if(idValue != null || versionValue != null)
			buff.append("@gov.nih.nci.system.metadata.NCIMetadata");

		boolean append = false;
		if(idValue != null && idValue.trim().length() > 0)
		{
			buff.append("(CADSR_DE_ID=\""+idValue +"\"");
			append = true;
		}
		if(versionValue != null && versionValue.trim().length() > 0)
		{
			if(append)
				buff.append(",");
			else
				buff.append("(");
			buff.append("CADSR_DE_VERSION=\""+versionValue +"\")");
		}
		else
		{
			if(append)
				buff.append(")");
		}
		return buff.toString();

	}

	/**
	 * @param tgElt The TaggableElement (UMLClass, UMLAttribute)
	 * @return		String containing a concatenation of any, all caDSR
	 * 				tag values
	 */
	public String getCaDSRAnnotationContent(UMLTaggableElement tgElt)
	{
		String publicID = getTagValue(tgElt, TV_CADSR_PUBLICID);
		String version = getTagValue(tgElt, TV_CADSR_VERSION);

		if (publicID == null && version == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		if (publicID != null)
			sb.append(TV_CADSR_PUBLICID).append("=\"").append(publicID).append("\"; ");

		if (version != null)
			sb.append(TV_CADSR_VERSION).append("=\"").append(version).append("\"");

		return sb.toString();

	}

	public String findAssociatedColumn(UMLClass table,UMLClass klass, UMLAssociationEnd otherEnd, UMLClass assocKlass, UMLAssociationEnd thisEnd, Boolean throwException, Boolean isJoin) throws GenerationException
	{

		String col1 = getColumnName(table,TV_ASSOC_COLUMN,getFQCN(klass) +"."+ otherEnd.getRoleName(),false,0,1);
		String col2 = getColumnName(table,TV_ASSOC_COLUMN,getFQCN(assocKlass) +"."+ thisEnd.getRoleName(),false,0,1);
		String col3 = getColumnName(table,TV_INVERSE_ASSOC_COLUMN,getFQCN(assocKlass) +"."+ thisEnd.getRoleName(),false,0,1);

		log.debug("***** col1: " + col1 + "; col2: " + col2 + "; col3: " + col3);

		if("".equals(col1)) col1=null;
		if("".equals(col2)) col2=null;
		if("".equals(col3)) col3=null;

		if((col1==null && col3==null && isJoin && throwException) || (col1==null && col2==null && !isJoin && throwException)){
			log.error("***** col1: " + col1 + "; col2: " + col2 + "; col3: " + col3);
			log.error("klass: " + klass.getName());
			log.error("assocKlass: " + assocKlass.getName());
			log.error("table: " + table.getName());
			log.error("isJoin: " + isJoin);
			log.error("otherEnd.getRoleName(): " +otherEnd.getRoleName());
			log.error("thisEnd.getRoleName(): " +thisEnd.getRoleName());
			log.error("Could not determine the column for the association between "+getFQCN(klass)+" and "+getFQCN(assocKlass) +". Check for missing implements-association/inverse-of/correlation-table tag(s), where appropriate");
			throw new GenerationException("Could not determine the column for the association between "+getFQCN(klass)+" and "+getFQCN(assocKlass) +". Check for missing implements-association/inverse-of/correlation-table tag(s), where appropriate");
		}
		/*if(col1!=null && col2!=null && !col1.equals(col2))
			throw new GenerationException("More than one column found for the association between "+getFQCN(klass)+" and "+getFQCN(assocKlass));
		if(col1!=null && col3!=null && !col1.equals(col3))
			throw new GenerationException("More than one column found for the association between "+getFQCN(klass)+" and "+getFQCN(assocKlass));
		if(col2!=null && col3!=null && !col2.equals(col3))
			throw new GenerationException("More than one column found for the association between "+getFQCN(klass)+" and "+getFQCN(assocKlass));
		*/
		if(isJoin)
		{
			return col1==null ? col3 : col1;
		}
		else
		{
			return col1==null ? col2 : col1;

		}
/*		if(col1!=null) return col1;
		else if (col3!=null) return col3;
		else return col2;
*/	}

	public String findAssociatedColumn(UMLClass table,UMLClass klass, UMLAssociationEnd otherEnd, UMLClass assocKlass, UMLAssociationEnd thisEnd, Boolean isJoin) throws GenerationException
	{
		return findAssociatedColumn(table,klass, otherEnd, assocKlass, thisEnd, true, isJoin);
	}

	public String findInverseColumnValue(UMLClass table,UMLClass klass, UMLAssociationEnd thisEnd) throws GenerationException
	{
		return getColumnName(table,TV_INVERSE_ASSOC_COLUMN,getFQCN(klass) +"."+ thisEnd.getRoleName(),false,0,1);
	}

	public String findDiscriminatingColumnName(UMLClass klass) throws GenerationException
	{
		log.debug("**** Finding Discriminating Column Name for class : " + getFQCN(klass));

		UMLClass superKlass = klass;

		UMLClass temp = klass;
		while ((temp = getSuperClass(temp))!=null && !isImplicitParent(temp))
			superKlass = temp;

		UMLClass table = getTable(superKlass);
		String fqcn = getFQCN(superKlass);
		return getColumnName(table,TV_DISCR_COLUMN,fqcn,false,0,1);
	}

	public String getDiscriminatorValue(UMLClass klass) throws GenerationException
	{
		log.debug("**** Getting Discriminator Value for class : " + getFQCN(klass));
		return getTagValue(klass,TV_DISCR_COLUMN,null, 1,1);
	}

	public String getRootDiscriminatorValue(UMLClass klass) throws GenerationException
	{
		log.debug("**** Getting Root Discriminator Value for class : " + getFQCN(klass));

		return getTagValue(klass,TV_DISCR_COLUMN,null,0,1);
	}

	public String getImplicitDiscriminatorColumn(UMLClass table, UMLClass klass, String roleName) throws GenerationException
	{
		log.debug("**** Getting Implicit Discriminator Column for table: " + table.getName() +"; klass: " + klass.getName() +"; roleName: " + roleName);
		return getColumnName(table,TV_DISCR_COLUMN,getFQCN(klass)+"."+roleName,false,1,1);
	}

	public String getImplicitIdColumn(UMLClass table, UMLClass klass, String roleName) throws GenerationException
	{
		return getColumnName(table,TV_ASSOC_COLUMN,getFQCN(klass)+"."+roleName,false,1,1);
	}

	public boolean isLazyLoad(UMLClass klass, UMLAssociation association) throws GenerationException
	{
		String temp = getTagValue(klass,association, TV_LAZY_LOAD,null, 0,1);

		if (temp != null) {
			List<UMLAssociationEnd> assocEnds = association.getAssociationEnds();
			UMLAssociationEnd otherEnd = getOtherEnd(klass,assocEnds);
			UMLAssociationEnd thisEnd = getThisEnd(klass,assocEnds);
			throw new GenerationException("Invalid Tag Value found:  The '" + TV_LAZY_LOAD + "' Tag Value which is assigned to the association link attached to class " + getFQCN(klass) + " with the rolenames '" + otherEnd.getRoleName() + "' and '" + thisEnd.getRoleName() + "' has been replaced with the '" + TV_NCI_EAGER_LOAD + "' Tag Value.  Also, it's value must now conform to the following pattern:  "+TV_NCI_EAGER_LOAD+"#<fully qualified class name>.<role name>.  The value of the tag continues to be 'yes' or 'no'.  However, the value is the converse of the original 'lazy-load' tag; i.e., if 'lazy-load' tag value was set to 'yes', set the 'NCI_EAGER_LOAD#...' tag value to 'no', and vice versa.  Please update your model accordingly" );
		}

		return true;
	}

	private String getTagValue(UMLTaggableElement elt, String key, String value, int minOccurrence, int maxOccurrence) throws GenerationException
	{
		String result = null;
		int count = 0;
		for(UMLTaggedValue tv: elt.getTaggedValues())
		{
			if (key.equals(tv.getName()))
			{
				String tvValue = tv.getValue();
				String[] tvValues = tvValue.split(",");
				for(String val:tvValues)
				{
					if(value==null)
					{
						count++;
						result = val;
					}
					else if(value.equals(val))
					{
						count++;
						result = val;
					}
				}
			}
		}

		if(count < minOccurrence || (minOccurrence>0 && (result == null || result.trim().length() == 0))) throw new GenerationException("No value found for "+key+" tag for element: "+getFQEN(elt));
		if(count > maxOccurrence) throw new GenerationException("More than one value found for "+key+" tag in : "+getFQEN(elt));

		return result;
	}

	public String getTagValue(UMLTaggableElement tgElt, String key)
	{

		for(UMLTaggedValue tv: tgElt.getTaggedValues())
		{
			if (key.equals(tv.getName()))
			{
				return tv.getValue();
			}
		}

		return null;
	}

	private List<String> getTagValues(UMLTaggableElement tgElt, String key)
	{

		List<String> tagValues = new ArrayList<String>();
		for(UMLTaggedValue tv: tgElt.getTaggedValues())
		{
			if (key.equals(tv.getName()))
			{
				log.debug(tv.getName() + ": " + tv.getValue());
				tagValues.add(tv.getValue());
			}
		}

		return tagValues;
	}


	public String getColumnName(UMLClass klass, String key, String value,  boolean isValuePrefix, int minOccurrence, int maxOccurrence) throws GenerationException
	{
		log.debug("Getting Column Name for class " + getFQCN(klass) + " with a value of " + value);
		UMLAttribute attr = getColumn(klass,key,value,isValuePrefix,minOccurrence,maxOccurrence);
		return (attr==null) ? "" : attr.getName();
	}

	private UMLAttribute getColumn(UMLClass klass, String key, String value, boolean isValuePrefix, int minOccurrence, int maxOccurrence) throws GenerationException
	{
		log.debug("Getting Column for class " + getFQCN(klass) + " and key '" +key + "' and value '" + value + "'.");

		UMLAttribute result = null;
		int count = 0;
		List<UMLAttribute> attrList = new ArrayList<UMLAttribute>();

		UMLClass currentKlass = klass;
		while (currentKlass != null){
			attrList.addAll(currentKlass.getAttributes());

			currentKlass = getSuperClass(currentKlass);
		}

 		for(UMLAttribute attr: attrList)
		{
			for(UMLTaggedValue tv: attr.getTaggedValues())
			{
				if (key.equals(tv.getName()))
				{
					String tvValue = tv.getValue();
					String[] tvValues = tvValue.split(",");
					for(String val:tvValues)
					{
						if(value==null)
						{
							count++;
							result = attr;
						}
						else if(isValuePrefix && val.startsWith(value))
						{
							count++;
							result = attr;
						}
						else if(!isValuePrefix && val.equals(value))
						{
							count++;
							result = attr;
						}
					}
				}
			}
		}
		if(count < minOccurrence) {
			log.error("No value of "+value+" found for "+key+" tag in table : "+getFQCN(klass));
			throw new GenerationException("No value of "+value+" found for "+key+" tag in table : "+getFQCN(klass));
		}
		if(count > maxOccurrence){
			log.error("More than one value found for "+key+" tag in table : "+getFQCN(klass));
			throw new GenerationException("More than one values found for "+key+" tag in table : "+getFQCN(klass));
		}

		return result;
	}

	public String getTagValue(UMLClass klass, UMLAttribute attribute, String key, String value, Boolean isValuePrefix, int minOccurrence, int maxOccurrence) throws GenerationException
	{
		String result = null;
		int count = 0;
		for(UMLTaggedValue tv: attribute.getTaggedValues())
		{
			log.debug("Processing tv: " + tv.getName());
			if (key.equals(tv.getName()))
			{
				String tvValue = tv.getValue();
				log.debug("Key equals tv.  TV value is: " + tv.getValue());
				String[] tvValues = tvValue.split(",");
				for(String val:tvValues)
				{
					if(value==null)
					{
						count++;
						result = val;
					}
					else if(isValuePrefix && val.startsWith(value))
					{
						count++;
						result = val;
					}
					else if(!isValuePrefix && val.equals(value))
					{
						count++;
						result = val;
					}
				}
			}
		}

		if(count < minOccurrence)
			throw new GenerationException("No value of "+value+" found for "+key+" tag in class : "+getFQCN(klass));
		if(count > maxOccurrence) throw new GenerationException("More than one values found for "+key+" tag in class : "+getFQCN(klass));

		return result;
	}

	private String getTagValue(UMLClass klass, UMLAssociation association, String key, String value, Boolean isValuePrefix, int minOccurrence, int maxOccurrence) throws GenerationException
	{

		List <UMLAssociationEnd>ends = association.getAssociationEnds();
		UMLAssociationEnd thisEnd = getThisEnd(klass, ends);
		UMLAssociationEnd otherEnd = getOtherEnd(klass, ends);
		String thisClassName = getFQCN(((UMLClass)thisEnd.getUMLElement()));
		String otherClassName = getFQCN(((UMLClass)otherEnd.getUMLElement()));

		String result = null;
		int count = 0;
		for(UMLTaggedValue tv: association.getTaggedValues())
		{
			if (key.equals(tv.getName()))
			{
				String tvValue = tv.getValue();
				String[] tvValues = tvValue.split(",");
				for(String val:tvValues)
				{
					if(value==null)
					{
						count++;
						result = val;
					}
					else if(isValuePrefix && val.startsWith(value))
					{
						count++;
						result = val;
					}
					else if(!isValuePrefix && val.equals(value))
					{
						count++;
						result = val;
				}
			}
		}
		}


		if(count < minOccurrence || (minOccurrence >0 && (result == null || result.trim().length() == 0))) throw new GenerationException("No tag value of "+key+" found for the association between "+thisClassName +" and "+ otherClassName +":"+count+":"+result);
		if(count > maxOccurrence) throw new GenerationException("More than the expected maximum number (" + maxOccurrence + ") of tag value occurrences  for "+key+" found for the association between "+thisClassName +" and "+ otherClassName);

		return result;
	}

	private String getTagValue(UMLClass klass, UMLAssociation association, String key, String value, int minOccurrence, int maxOccurrence) throws GenerationException
	{
		return getTagValue(klass, association, key, value,false, minOccurrence, maxOccurrence);
	}

	public String getTagValue(Collection<UMLTaggedValue> tagValues, String key, int maxOccurrence) throws GenerationException
	{
		StringBuilder temp = new StringBuilder();

		for(int i=0;i<maxOccurrence;i++)
		{
			String searchKey = i==0 ? key : key + (i+1);
			for(UMLTaggedValue tv:tagValues)
			{
				if(searchKey.equals(tv.getName()))
				{
					temp.append(tv.getValue());
				}
			}

		}
		return temp.toString();
	}

	public String getIsNotNullTagValue(UMLClass klass, UMLAttribute attr) throws GenerationException
	{
		log.debug("getIsNotNullTagValue for klass: " + klass.getName() + ", attr: " + attr.getName());
		String fqcn = getFQCN(klass);
		UMLClass table = getTable(klass);
		UMLAttribute col = getMappedColumn(table,fqcn+"."+attr.getName());

		String temp1= getTagValue(table,col, TV_NOT_NULL_PROPERTY, null, false,0, 1);
		if(temp1 ==null || temp1.length() == 0)
		{
			return null;
	}
		return temp1;
	}



	private String getJavaDocs(Collection<UMLTaggedValue> tagValues) throws GenerationException
	{
		String documentation = getTagValue(tagValues, TV_DOCUMENTATION, 8);
		String description = getTagValue(tagValues, TV_DESCRIPTION, 8);

		String temp = documentation == null || documentation.trim().length()==0 ? description : documentation;
		StringBuilder doc = new StringBuilder();
		doc.append("/**");
		doc.append("\n\t* ").append(temp);
		doc.append("\n\t**/");
		return doc.toString();

	}

	public String getJavaDocs(UMLOperation operation) throws GenerationException
	{
		return getJavaDocs(operation.getTaggedValues());
	}

	public String getJavaDocs(UMLInterface interfaze) throws GenerationException
	{
		return getJavaDocs(interfaze.getTaggedValues());
	}

	public String getJavaDocs(UMLClass klass) throws GenerationException
	{
		return getJavaDocs(klass.getTaggedValues());
	}

	public String getJavaDocs(UMLAttribute attr) throws GenerationException
	{
		return getJavaDocs(attr.getTaggedValues());
	}

	public String getJavaDocs(UMLClass klass, UMLAssociation assoc) throws GenerationException
	{
		UMLAssociationEnd otherEnd = getOtherEnd(klass, assoc.getAssociationEnds());
		StringBuilder doc = new StringBuilder();
		doc.append("/**");
		doc.append("\n	* An associated "+getFQCN(((UMLClass)otherEnd.getUMLElement()))+" object");
		if(isAssociationEndMany(otherEnd))
			doc.append("'s collection ");
		doc.append("\n	**/\n");
		return doc.toString();
	}

	public String getGetterMethodJavaDocs(UMLAttribute attr) {
		StringBuilder doc = new StringBuilder();
		doc.append("/**");
		doc.append("\n	* Retrieves the value of the "+attr.getName()+" attribute");
		doc.append("\n	* @return ").append(attr.getName());
		doc.append("\n	**/\n");
		return doc.toString();
	}

	public String getSetterMethodJavaDocs(UMLAttribute attr) {
		StringBuilder doc = new StringBuilder();
		doc.append("/**");
		doc.append("\n	* Sets the value of "+attr.getName()+" attribute");
		doc.append("\n	**/\n");
		return doc.toString();
	}

	public String getGetterMethodJavaDocs(UMLClass klass, UMLAssociation assoc) throws GenerationException {
		UMLAssociationEnd otherEnd = getOtherEnd(klass, assoc.getAssociationEnds());
		StringBuilder doc = new StringBuilder();
		doc.append("/**");
		doc.append("\n	* Retrieves the value of the "+otherEnd.getRoleName()+" attribute");
		doc.append("\n	* @return ").append(otherEnd.getRoleName());
		doc.append("\n	**/\n");
		return doc.toString();
	}

	public String getSetterMethodJavaDocs(UMLClass klass, UMLAssociation assoc) throws GenerationException {
		UMLAssociationEnd otherEnd = getOtherEnd(klass, assoc.getAssociationEnds());
		StringBuilder doc = new StringBuilder();
		doc.append("/**");
		doc.append("\n	* Sets the value of "+otherEnd.getRoleName()+" attribute");
		doc.append("\n	**/\n");
		return doc.toString();
	}

	public String reversePackageName(String s) {
		StringTokenizer st = new StringTokenizer(s,".");
		Vector<String> myVector = new Vector<String>();
		StringBuilder myStringBuilder = new StringBuilder();
		while (st.hasMoreTokens()) {
			     String t = st.nextToken();
			     myVector.add(t);

	    }

        for (int i = myVector.size(); i>0; i--) {
			  myStringBuilder.append(myVector.elementAt(i-1));
			  myStringBuilder.append(Constant.DOT);

	    }
	    int length1 = myStringBuilder.length();
	    String finalString1 = myStringBuilder.substring(0,length1-1);
        return finalString1;
    }



	public String getWSDDServiceValue(Collection<UMLClass> classColl)throws GenerationException{
        StringBuilder nn1 = new StringBuilder();
        for(UMLClass klass:classColl){
			String pkgName = getFullPackageName(klass);
			nn1.append(pkgName)
			   .append(Constant.DOT)
			   .append(klass.getName())
			   .append(Constant.COMMA);
		}

        // remove last Comma
        return nn1.substring(0, nn1.length()-1);

	}


	public UMLClass findCollectionTable(UMLAttribute attr, UMLModel model) throws GenerationException
	{
		String tableName = getTagValue(attr.getTaggedValues(),TV_MAPPED_COLLECTION_TABLE, 1);

		UMLClass collectionTable = ModelUtil.findClass(model,BASE_PKG_DATA_MODEL+"."+tableName);
		if(collectionTable == null) throw new GenerationException("No collection table found named : \""+tableName+"\"");

		return collectionTable;
	}


	public String getCollectionKeyColumnName(UMLClass table,UMLClass klass, UMLAttribute attr) throws GenerationException
	{
		return getColumnName(table,TV_MAPPED_ATTR_COLUMN,getFQCN(klass) +"."+ attr.getName(),false,1,1);
	}

	public String getCollectionElementColumnName(UMLClass table,UMLClass klass, UMLAttribute attr) throws GenerationException
	{
		return getColumnName(table,TV_MAPPED_ELEMENT_COLUMN,getFQCN(klass) +"."+ attr.getName(),false,1,1);
	}

	public String getCollectionElementHibernateType(UMLClass klass, UMLAttribute attr) throws GenerationException
	{
		String name = getDataType(attr);
		if(name.startsWith("Collection<"))
		{
			name = name.substring("Collection<".length());
			name = name.substring(0,name.length()-1);

			if("int".equalsIgnoreCase(name) || "integer".equalsIgnoreCase(name))
				return "integer";
			if("double".equalsIgnoreCase(name))
				return "double";
			if("float".equalsIgnoreCase(name))
				return "float";
			if("long".equalsIgnoreCase(name))
				return "long";
			if("string".equalsIgnoreCase(name))
				return "string";
			if("char".equalsIgnoreCase(name) || "character".equalsIgnoreCase(name))
				return "character";
			if("boolean".equalsIgnoreCase(name) )
				return "boolean";
			if("byte".equalsIgnoreCase(name) )
				return "byte";
			if("short".equalsIgnoreCase(name) )
				return "short";
		}
		return name;
	}

	public String getJaxbXmlAttributeAnnotation(UMLClass klass, UMLAttribute attr) throws GenerationException{
		String type = this.getDataType(attr);
		log.debug("* * * datatype for attribute " + attr.getName()+": " + type);
		//System.out.println("* * * datatype for attribute " + attr.getName()+": " + type);
		String collectionType = "";
		StringBuffer sb = new StringBuffer();
		if (type.equals("Character")){
			return "@XmlAttribute" + "\n "+ "@XmlJavaTypeAdapter(CharAdapter.class)";
		}
		else if (type.startsWith("Collection")){
			collectionType = type.substring(type.indexOf("<")+1,type.indexOf(">"));

			sb.append("    @XmlElementWrapper(name=\"");
			sb.append(attr.getName()).append("\", ");
			sb.append("namespace=\"").append(this.getNamespaceUriPrefix() + this.getFullPackageName(klass)).append("\")");

			sb.append("    @XmlElement(name=\"");
			sb.append(collectionType.toLowerCase()).append("\", ");
			sb.append("namespace=\"").append(this.getNamespaceUriPrefix() + this.getFullPackageName(klass)).append("\")");

			log.debug("Collection Attribute @XmlElement annotation: "+sb.toString());

			return sb.toString();
		}

		if(isISO21090Enabled){
			String isoDatatypeValue = isoDatatypeMap.get(attr.getDatatype().getName());

			if (!isJavaDataType(attr)) {
				log.debug("* * * Detected attribute " + attr.getName()+" is of ISO Datatype: " + isoDatatypeValue);
				sb.append("    @XmlElement(namespace=\"").append(getNamespaceUriPrefix()).append(getFullPackageName(klass)).append("\")");
				sb.append("\n");
				if(isIvlDataType(attr))
				{
					String ivlDataType = getISODataType(attr);
					if("PQ".equals(ivlDataType))
							sb.append("    @XmlJavaTypeAdapter(JAXBISOIvlPqAdapter.class)");
					else if("REAL".equals(ivlDataType))
							sb.append("    @XmlJavaTypeAdapter(JAXBISOIvlRealAdapter.class)");
					else if("TS".equals(ivlDataType))
							sb.append("    @XmlJavaTypeAdapter(JAXBISOIvlTsAdapter.class)");
					else if("INT".equals(ivlDataType))
							sb.append("    @XmlJavaTypeAdapter(JAXBISOIvlIntAdapter.class)");
					else
						throw new GenerationException("Invalid Ivl type defined: " + attr.getDatatype().getName());
				}
				else if(isDsetDataType(attr))
				{
					String dsetDataType = getISODataType(attr);
					if("AD".equals(dsetDataType))
							sb.append("    @XmlJavaTypeAdapter(JAXBISODsetAdAdapter.class)");
					else if("CD".equals(dsetDataType))
							sb.append("    @XmlJavaTypeAdapter(JAXBISODsetCdAdapter.class)");
					else if("II".equals(dsetDataType))
							sb.append("    @XmlJavaTypeAdapter(JAXBISODsetIiAdapter.class)");
					else if("TEL".equals(dsetDataType))
							sb.append("    @XmlJavaTypeAdapter(JAXBISODsetTelAdapter.class)");
					else
						throw new GenerationException("Invalid Dset type defined: " + attr.getDatatype().getName());
				}
				else
				{
					sb.append("    @XmlJavaTypeAdapter(JAXBISOAdapter.class)");
				}

				return sb.toString();
			}
		}

		return "    @XmlAttribute";
	}


	public String getJaxbXmlRootElementAnnotation(UMLClass klass){

		StringBuffer sb = new StringBuffer();

		sb.append("@XmlRootElement(name=\"");
		sb.append(klass.getName()).append("\", ");
		sb.append("namespace=\"").append(this.getNamespaceUriPrefix() + this.getFullPackageName(klass)).append("\")");

		log.debug("@XmlRootElement annotation for class "+klass.getName()+": "+sb.toString());

		return sb.toString();

	}

	public String getJaxbXmlTypeAnnotation(UMLClass klass){

		StringBuffer sb = new StringBuffer("@XmlType(name = \"").append(klass.getName());
		sb.append("\", namespace=\"");
		sb.append(this.getNamespaceUriPrefix() + this.getFullPackageName(klass));
		sb.append("\", propOrder = {");

		int counter = 0;
		int totalAttrCount = klass.getAttributes().size();
		for(UMLAttribute attr:klass.getAttributes()){
			counter++;

			sb.append("\"").append(attr.getName()).append("\"");
			if (counter < totalAttrCount){
				sb.append(", ");
			}
		}



		for(UMLAssociation assoc:klass.getAssociations()){
			List<UMLAssociationEnd> assocEnds = assoc.getAssociationEnds();

			try {
				UMLAssociationEnd thisEnd = this.getThisEnd(klass,assocEnds);
				UMLAssociationEnd otherEnd = this.getOtherEnd(klass,assocEnds);
				List<UMLAssociationEnd> associationEnds = new java.util.ArrayList<UMLAssociationEnd>();
				associationEnds.add(otherEnd);
				if(this.isBidirectionalSelfAssociation(klass,assocEnds)) { // handle bi-directional self-association
					associationEnds.add(thisEnd);
				}


				int totalAssocCount = associationEnds.size();

				Iterator iter = associationEnds.iterator();
				while(iter.hasNext())
				{
					UMLAssociationEnd associationEnd = (UMLAssociationEnd)iter.next();
					if(associationEnd.isNavigable())
					{
						if(counter > 0)
							sb.append(", ");
						sb.append("\"").append(associationEnd.getRoleName()).append("\"");
						counter++;
					}
				}
			} catch (GenerationException e) {
				log.error("Error generating XML Type Property order for association role name: "+assoc.getRoleName(),e);
			}
		}


		char c = sb.charAt(sb.length()-2);
		log.debug("Last propOrder char: " +c);

		if ( c==',' ){
			sb.deleteCharAt(sb.length()-2);
		}

		sb.append("})");

		log.debug("@XMLType string for class " + klass.getName() + sb.toString() );

		return sb.toString();
	}

	public String getJaxbXmlAccessorTypeAnnotation(){
		return "@XmlAccessorType(XmlAccessType.NONE)";
	}

	public String getJaxbOnCycleDetectedMethod() {
		StringBuffer sb = new StringBuffer("    public Object onCycleDetected(com.sun.xml.bind.CycleRecoverable.Context arg0) {\n");
		sb.append("		return null;\n");
		sb.append("	}\n");

		return sb.toString();
	}

	public String getJaxbXmlSeeAlsoAnnotation(UMLClass klass){
		List<UMLClass> subClasses = getNonImplicitSubclasses(klass);
		List<UMLClass> superClasses = getNonImplicitSuperclasses(klass);

		StringBuffer sb = new StringBuffer();
		boolean found = false;
		if (!subClasses.isEmpty()){
			int counter = 0;
			int totalCount = subClasses.size();
			for (UMLClass subKlass:subClasses){
				counter++;
				found = true;
				sb.append(getFullPackageName(subKlass)+"."+subKlass.getName()+".class");
				if (counter < totalCount){
					sb.append(", ");
				}
			}
		}
		if (!superClasses.isEmpty()){
			int counter = 0;
			int totalCount = superClasses.size();
			if(found)
				sb.append(",");
			for (UMLClass superKlass:superClasses){
				counter++;
				found = true;
				sb.append(getFullPackageName(superKlass)+"."+superKlass.getName()+".class");
				if (counter < totalCount){
					sb.append(", ");
				}
			}
		}

		if(found)
		{
			StringBuffer sbreturn = new StringBuffer("@XmlSeeAlso({");
			sbreturn.append(sb.toString());
			sbreturn.append("})");
			log.debug("@XMLSeeAlso string for class " + klass.getName() + sb.toString() );

			return sbreturn.toString();
		}

		return "";
	}


	public List<UMLClass> getNonImplicitSuperclasses(UMLClass implicitKlass){
		ArrayList<UMLClass> nonImplicitSuperclasses = new ArrayList<UMLClass>();
		getNonImplicitSuperclasses(implicitKlass, nonImplicitSuperclasses);

		return nonImplicitSuperclasses;
	}


	private void getNonImplicitSuperclasses(UMLClass klass, ArrayList<UMLClass> nonImplicitSuperclasses){
		for(UMLGeneralization gen:klass.getGeneralizations()){
			UMLClass superKlass = (UMLClass)gen.getSupertype();
			if(superKlass!=klass && isSuperclass(superKlass)){
				if(!nonImplicitSuperclasses.contains(superKlass)){
					nonImplicitSuperclasses.add(superKlass);
				}
			}

			if(superKlass!=klass)
				getNonImplicitSuperclasses(superKlass, nonImplicitSuperclasses);
		}
	}


	public List<UMLClass> getNonImplicitSubclasses(UMLClass implicitKlass){

		ArrayList<UMLClass> nonImplicitSubclasses = new ArrayList<UMLClass>();

		getNonImplicitSubclasses(implicitKlass, nonImplicitSubclasses);

		return nonImplicitSubclasses;
	}

	private void getNonImplicitSubclasses(UMLClass klass, ArrayList<UMLClass> nonImplicitSubclasses){
		for(UMLGeneralization gen:klass.getGeneralizations()){
			UMLClass subKlass = (UMLClass)gen.getSubtype();
			if(subKlass!=klass && !isImplicitParent(subKlass)){
				nonImplicitSubclasses.add(subKlass);
			}

			if(subKlass!=klass)
				getNonImplicitSubclasses(subKlass, nonImplicitSubclasses);
		}
	}

	/**
	 * Scans the tag values of the association to determine the cascade-style
	 *
	 * @param association
	 * @param model
	 * @param klass
	 * @return
	 * @throws GenerationException
	 */
	public String findCascadeStyle(UMLClass klass, String roleName, UMLAssociation association) throws GenerationException
	{
		for (String cascadeStyles : getTagValues(association, TV_NCI_CASCADE_ASSOCIATION + "#" + getFQCN(klass)+"."+roleName)){

				List<String> validCascadeStyles = new ArrayList<String>();

				for(String cascadeStyle:cascadeStyles.split(",")){
					validCascadeStyles.add(cascadeStyle.trim());
				}

				StringBuilder validCascadeStyleSB = new StringBuilder();
				validCascadeStyleSB.append(validCascadeStyles.get(0));
				for (int i = 1; i <validCascadeStyles.size(); i++ ){
					validCascadeStyleSB.append(",").append(validCascadeStyles.get(i));
				}

				return validCascadeStyleSB.toString();
		}

		return "none";
	}

	public String getCascadeInsertUpdate(String cascade)
	{
		if(cascade == null || cascade.equals("none"))
			return "insert=\"false\" update=\"false\"";
		else
			return "insert=\"true\" update=\"true\"";
	}

	public String isFKAttributeNull(UMLAssociationEnd otherEnd) {
		if (otherEnd.getLowMultiplicity() == 0) {
			return "false";
		}
		return "true";
	}

	/**
	 * Scans the tag values of the association to determine the cascade-style
	 *
	 * @param klass
	 * @param roleName
	 * @param association
	 * @return
	 * @throws GenerationException
	 */
	public boolean isLazyLoad(UMLClass klass, String roleName, UMLAssociation association) throws GenerationException
	{
		for( String eagerLoadValue : getTagValues(association, TV_NCI_EAGER_LOAD + "#" +getFQCN(klass)+"."+roleName)){
			if ("true".equalsIgnoreCase(eagerLoadValue) || "yes".equalsIgnoreCase(eagerLoadValue) ){
				return false;
			}
		}

		return true;
	}

	/**
	 * Scans the tag values of the association to determine the cascade-style
	 *
	 * @param association
	 * @param model
	 * @param klass
	 * @return
	 * @throws GenerationException
	 */
	public Map<String,String> getValidCascadeStyles(){
		return CASCADE_STYLES;
	}

	/**
	 * Scans the tag values of the association to determine whether or not an inverse-of tag
	 * is present in any of the table columns
	 *
	 * @param klass
	 * @param key
	 * @return
	 * @throws GenerationException
	 */
	public List findInverseSettingColumns(UMLClass klass) throws GenerationException
	{
		List<String> attrs = new ArrayList<String>();
		for(UMLAttribute attr: klass.getAttributes())
		{
			for(UMLTaggedValue tv: attr.getTaggedValues())
			{
				if (TV_INVERSE_ASSOC_COLUMN.equals(tv.getName()))
				{
					attrs.add(attr.getName());
				}
			}
		}

		return attrs;
	}

	public String getHibernateValidatorConstraints(UMLClass klass){

		ValidatorClass vClass = vModel.getClass(getFQCN(klass));
		ValidatorClass vClassExtension = vModelExtension.getClass(getFQCN(klass));

		String constraintAnnotationString="";

		if (vClass != null)
			constraintAnnotationString = "\t" + vClass.getConstraintAnnotationString()+"\n";

		if (vClassExtension != null)
			constraintAnnotationString += "\t" + vClassExtension.getConstraintAnnotationString()+"\n";

		return constraintAnnotationString;

	}

	public String getHibernateValidatorConstraints(UMLClass klass,UMLAttribute attr){

		ValidatorClass vClass = vModel.getClass(getFQCN(klass));
		ValidatorClass vClassExtension = vModelExtension.getClass(getFQCN(klass));

		List<String> cadsrConstraintAnnotations=new ArrayList<String>();
		List<String> userConstraintAnnotations=new ArrayList<String>();
		ValidatorAttribute vAttr=null;
		if (vClass != null)
			vAttr=vClass.getAttribute(attr.getName());

		if (vAttr!=null)
			cadsrConstraintAnnotations.addAll(vAttr.getConstraintAnnotations());

		ValidatorAttribute vAttrExtension=null;
		if (vClassExtension != null)
			vAttrExtension=vClassExtension.getAttribute(attr.getName());

		if (vAttrExtension!=null)
			userConstraintAnnotations.addAll(vAttrExtension.getConstraintAnnotations());

		//remove duplicates - user constraints override caDSR constraints
		List<String> constraintAnnotations=new ArrayList<String>();
		for(String cadsrConstraintAnnotation : cadsrConstraintAnnotations){
			String cadsrConstraintPrefix = cadsrConstraintAnnotation.indexOf("(") > 0 ? cadsrConstraintAnnotation.substring(0, cadsrConstraintAnnotation.indexOf("(")) : cadsrConstraintAnnotation;
			boolean duplicateConstraint = false;
			for(String userConstraintAnnotation : userConstraintAnnotations){
				if (userConstraintAnnotation.startsWith(cadsrConstraintPrefix)){
					duplicateConstraint = true;
					break;
				}
			}
			if (!duplicateConstraint)
				constraintAnnotations.add(cadsrConstraintAnnotation);
		}

		constraintAnnotations.addAll(userConstraintAnnotations);

		//Handle special @Patterns scenario
		List<String> patternConstraintAnnotations=new ArrayList<String>();
		for(String constraintAnnotation : constraintAnnotations){
			if (constraintAnnotation.indexOf("Pattern")>0){
				patternConstraintAnnotations.add(constraintAnnotation);
			}
		}

		StringBuilder sb;
		if (!patternConstraintAnnotations.isEmpty()){
			sb = new StringBuilder();
			constraintAnnotations.removeAll(patternConstraintAnnotations);

			sb.append(patternConstraintAnnotations.remove(0));
			for (String patternConstraintAnnotation:patternConstraintAnnotations){
				sb.append(",").append(patternConstraintAnnotation);
			}

			constraintAnnotations.add("@Patterns({"+sb.toString()+"})");
		}

		sb = new StringBuilder();
		for(String constraintAnnotation: constraintAnnotations){
			sb.append("\n\t").append(constraintAnnotation);
		}

		return sb.toString();
	}

	public Collection<String> getXSDRestrictionValues(UMLClass klass,UMLAttribute attr){

		ValidatorClass vClass = vModel.getClass(getFQCN(klass));
		ValidatorClass vClassExtension = vModelExtension.getClass(getFQCN(klass));

		ArrayList<String> permissibleValues = new ArrayList<String>();

		//get user supplied permissible value collection from validator extension file
		ValidatorAttribute vAttrExtension=null;
		if (vClassExtension != null)
			vAttrExtension =  vClassExtension.getAttribute(attr.getName());

		if (vAttrExtension != null)
			permissibleValues.addAll(vAttrExtension.getXSDRestrictionCollection());

		//user supplied constraints override caDSR constraints, so only retrieve
		//caDSR constraints if user did not supply any constraints
		if (permissibleValues.isEmpty()){
			ValidatorAttribute vAttr=null;
			if (vClass != null)
				vAttr =  vClass.getAttribute(attr.getName());

			if (vAttr != null)
				permissibleValues.addAll(vAttr.getXSDRestrictionCollection());
		}

		return permissibleValues;
	}

	private Collection<String> getHibernateValidatorConstraintImports(UMLClass klass){

		ValidatorClass vClass = vModel.getClass(getFQCN(klass));
		ValidatorClass vClassExtension = vModelExtension.getClass(getFQCN(klass));

		Collection<String> constraintImports = new HashSet<String>();

		if (vClass != null)
			constraintImports.addAll(vClass.getConstraintImports());

		if (vClassExtension != null)
			constraintImports.addAll(vClassExtension.getConstraintImports());

		if (constraintImports.contains("org.hibernate.validator.Pattern"))
			constraintImports.add("org.hibernate.validator.Patterns");

		return constraintImports;
	}


	public String getNamespace(UMLTaggableElement te) throws GenerationException {
		String gmeNamespacePrefix = null;
		try {
			gmeNamespacePrefix = getTagValue(te,TV_NCI_GME_XML_NAMESPACE,null,0,1);
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME 'NCI_GME_XML_NAMESPACE' tag value for element", ge);
		}

		return gmeNamespacePrefix;
	}

	public String getGMENamespace(UMLClass klass) throws GenerationException{
		String gmeNamespace = null;
		try {
			gmeNamespace = getNamespace(klass);
			if (gmeNamespace!=null && gmeNamespace.length()>0)
				return gmeNamespace;
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME namespace for: " + getFQEN(klass), ge);
		}

		gmeNamespace=getGMENamespace(klass.getPackage());
		if (gmeNamespace!=null && gmeNamespace.length()>0)
			return gmeNamespace;

		log.error("GME Namespace name not found for: "+getFullPackageName(klass)+". Returning null");
		return null;
	}

	public String getGMENamespace(UMLPackage pkg) throws GenerationException{
		if (pkg==null)
			return null;

		log.debug("Getting Package Namespace for: " +pkg.getName());
		String gmeNamespace = getNamespace(pkg);
		if (gmeNamespace!=null && gmeNamespace.length()>0)
			return gmeNamespace;

		return getGMENamespace(pkg.getParent());
	}

	public boolean hasGMEXMLNamespaceTag(UMLTaggableElement te){
		try {
			getTagValue(te,TV_NCI_GME_XML_NAMESPACE,null,0,0);
		} catch (GenerationException e) {
			return true;
		}
		return false;
	}

	private String getNamespacePackageName(UMLTaggableElement te) throws GenerationException {
		String gmeNamespace = null;
		try {
			gmeNamespace = getTagValue(te,TV_NCI_GME_XML_NAMESPACE,null,0,1);
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME 'NCI_GME_XML_NAMESPACE' tag value for: " + getFQEN(te), ge);
		}

		if (gmeNamespace != null && gmeNamespace.lastIndexOf('/')<0)
			throw new GenerationException("Invalid GME Namespace found for:" + getFQEN(te)+": "+gmeNamespace);

		if (gmeNamespace!=null){
			return gmeNamespace.substring(gmeNamespace.lastIndexOf('/')+1, gmeNamespace.length());
		}

		return null;
	}

	public String getModelNamespace(UMLModel model, String basePkgLogicalModel) throws GenerationException {
		//override codegen.properties NAMESPACE_PREFIX property with GME namespace tag value, if it exists

		StringTokenizer tokenizer = new StringTokenizer(basePkgLogicalModel, ".");
		UMLPackage pkg=null;
		if(tokenizer.hasMoreTokens()){
			pkg = model.getPackage(tokenizer.nextToken());

			while(pkg!=null && tokenizer.hasMoreTokens()){
				pkg = pkg.getPackage(tokenizer.nextToken());
			}
		}

		if (pkg==null){
			throw new GenerationException("Error getting the Logical Model package for model: " + pkg.getName()+". Make sure the LOGICAL_MODEL property in codegen.properties file is valid.");
		}

		if (pkg!=null){
			log.debug("* * * pkgName: " + pkg.getName());
			try {
				String modelNamespacePrefix = this.getNamespace(pkg);
				log.debug("* * * modelNamespacePrefix: " + modelNamespacePrefix);
				if (modelNamespacePrefix != null) {
					if (!modelNamespacePrefix.endsWith("/"))
						modelNamespacePrefix=modelNamespacePrefix+"/";
					return modelNamespacePrefix.replace(" ", "_");
				}
			} catch (GenerationException ge) {
				log.error("ERROR: ", ge);
				 throw new GenerationException("Error getting the GME Namespace value for model: " + pkg.getName() + ge.getMessage());
			}
		}
		return null;
	}

	public String getNamespacePrefix(UMLPackage pkg) throws GenerationException {
		String gmeNamespace = null;
		try {
			gmeNamespace = getTagValue(pkg,TV_NCI_GME_XML_NAMESPACE,null,0,1);
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME 'NCI_GME_XML_NAMESPACE' tag value for UML package: " + getFullPackageName(pkg), ge);
		}

		if (gmeNamespace != null && gmeNamespace.lastIndexOf('/')<0)
			throw new GenerationException("Invalid GME Namespace found for UML package " + getFullPackageName(pkg)+": "+gmeNamespace);

		if (gmeNamespace!=null){
			return gmeNamespace.substring(0,gmeNamespace.lastIndexOf('/'));
		}

		return null;
	}

	public String getXMLClassName(UMLClass klass) throws GenerationException {
		try {
			return getTagValue(klass,TV_NCI_GME_XML_ELEMENT,null,0,1);
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME 'NCI_GME_XML_ELEMENT' tag value for klass: " + klass.getName(), ge);
		}
	}

	public boolean hasGMEXMLClassTag(UMLTaggableElement te){
		try {
			getTagValue(te,TV_NCI_GME_XML_ELEMENT,null,0,0);
		} catch (GenerationException e) {
			return true;
		}
		return false;
	}

	public String getXMLAttributeName(UMLAttribute attr)throws GenerationException{
		try {
			  String attributeName = getTagValue(attr,TV_NCI_GME_XML_LOC_REF,null,0,1);

			  if (attributeName !=null && attributeName.length()>0 && (attributeName.startsWith("@")))
				  attributeName=attributeName.substring(1); //remove leading '@' character

			  return attributeName;
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME 'NCI_GME_XML_LOC_REF' tag value for attribute: " + attr.getName(), ge);
		}
	}

	public boolean generateXMLAttributeAsElement(UMLAttribute attr)throws GenerationException{
		try {
			  String attributeName = getTagValue(attr,TV_NCI_GME_XML_LOC_REF,null,0,1);

			  if (attributeName !=null && attributeName.length()>0 && !(attributeName.startsWith("@")))
				  return true;

			  return false;
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME 'NCI_GME_XML_LOC_REF' tag value for attribute: " + attr.getName(), ge);
		}
	}

	public boolean hasGMEXMLAttributeTag(UMLTaggableElement te){
		try {
			getTagValue(te,TV_NCI_GME_XML_LOC_REF,null,0,0);
		} catch (GenerationException e) {
			return true;
		}
		return false;
	}

	public String getXMLLocRef(UMLAssociationEnd assocEnd, String klassName)throws GenerationException
	{
		try {
			return getGmeLocRef(assocEnd.getOwningAssociation(),klassName);
		} catch(GenerationException ge) {
			log.error("ERROR: ", ge);
			throw new GenerationException("Error getting the GME 'NCI_GME_SOURCE_XML_LOC_REF' or 'NCI_GME_TARGET_XML_LOC_REF' tag value for association roleName: " + assocEnd.getRoleName(), ge);
		}
	}

	private String getGmeLocRef(UMLAssociation assoc,String klassName) throws GenerationException
	{
		String tv = getTagValue(assoc,TV_NCI_GME_SOURCE_XML_LOC_REF,null,0,1);
		if (tv !=null && tv.endsWith("/"+klassName)){
			return tv.substring(0, tv.lastIndexOf('/'));
		}

		tv = getTagValue(assoc,TV_NCI_GME_TARGET_XML_LOC_REF,null,0,1);
		if (tv !=null && tv.endsWith("/"+klassName)){
			return tv.substring(0, tv.lastIndexOf('/'));
		}

		return null;
	}

	public String getGmeSourceLocRef(UMLAssociation assoc) throws GenerationException
	{
		return getTagValue(assoc,TV_NCI_GME_SOURCE_XML_LOC_REF,null,0,1);
	}

	public String getGmeTargetLocRef(UMLAssociation assoc) throws GenerationException
	{
		return getTagValue(assoc,TV_NCI_GME_TARGET_XML_LOC_REF,null,0,1);
	}

	public boolean hasGMELocRefTag(UMLTaggableElement te){
		try {
			getTagValue(te,TV_NCI_GME_SOURCE_XML_LOC_REF,null,0,0);
			getTagValue(te,TV_NCI_GME_TARGET_XML_LOC_REF,null,0,0);
		} catch (GenerationException e) {
			return true;
		}
		return false;
	}

	public boolean containsIncludedClass(UMLPackage pkg)
			throws GenerationException {
		for (UMLClass klass : pkg.getClasses()) {
			if (isIncluded(klass) && !STEREO_TYPE_TABLE.equalsIgnoreCase(klass.getStereotype())){
				return true;
			}
		}
		return false;
	}


	public String getNamespaceUriPrefix() {
		return namespaceUriPrefix;
	}

	public boolean isUseGMETags() {
		return useGMETags;
	}

	public boolean isJaxbEnabled() {
		return isJaxbEnabled;
	}

	public boolean isISO21090Enabled() {
		return isISO21090Enabled;
	}

	public boolean isJavaDataType(UMLAttribute attr)
	{
		String originalType = attr.getDatatype().getName();

		if(isISO21090Enabled && isoDatatypeMap.containsKey(originalType))
			return false;

		return javaDatatypeMap.containsKey(originalType.toLowerCase());
	}


	public boolean isISO21090Enabled(UMLClass klass)
	{
		for(UMLAttribute attr: klass.getAttributes())
		{
			String originalType = attr.getDatatype().getName();

			if(isoDatatypeMap.containsKey(originalType))
				return true;
		}
		return false;
	}

	public boolean isValidForRESTMatrixParam(UMLClass klass)
	{
		//if(isISO21090Enabled(klass))
		//	return false;

		for(UMLAttribute attr: klass.getAttributes())
		{
			String originalType = attr.getDatatype().getName();

			if(javaPrimitiveDatatypeMap.containsKey(originalType))
				return true;
		}
		return false;
	}

	public boolean isDsetDataType(UMLAttribute attr)
	{
		String originalType = attr.getDatatype().getName();

		if(originalType.startsWith("DSET"))
			return true;

		return false;
	}

	public String getISODataType(UMLAttribute attr)
	{
		String originalType = attr.getDatatype().getName();
		return originalType.substring(originalType.indexOf("<")+1, originalType.indexOf(">"));
	}


	public boolean isIvlDataType(UMLAttribute attr)
	{
		String originalType = attr.getDatatype().getName();

		if(originalType.startsWith("IVL"))
			return true;

		return false;
	}

	public Collection getSortedByJoinUMLAttribute(UMLClass klass, UMLAttribute idAttribute, UMLClass table) throws GenerationException
	{
		List<UMLAttribute> noJoinRequired = new ArrayList<UMLAttribute>();
		List<UMLAttribute> joinRequired = new ArrayList<UMLAttribute>();


		if (klass.getAttributes().size() > 0)
		{
			for (UMLAttribute attr : klass.getAttributes())
			{
				if (attr != idAttribute)
				{
					if (isCollection(null, attr))
					{
						noJoinRequired.add(attr);
					}
					else if (isJavaDataType(attr))
					{
						noJoinRequired.add(attr);
					}
					else // Start ISO Datatype component
					{
						IsoDatatypeTransformationHelper isoDatatypeTransformationHelper = new IsoDatatypeTransformationHelper();
						isoDatatypeTransformationHelper.setModel(model);
						isoDatatypeTransformationHelper.setUtils(this);

						RootNode rootNode = isoDatatypeTransformationHelper.getDatatypeNode(klass, attr, table,false);

						if (isoDatatypeTransformationHelper.requiresJoin(rootNode))
							joinRequired.add(attr);
						else
							noJoinRequired.add(attr);
					}
				}
			}
		}

		List<UMLAssociation> noJoinRequiredAssociation = new ArrayList<UMLAssociation>();
		List<UMLAssociation> joinRequiredAssociation = new ArrayList<UMLAssociation>();


		for(UMLAssociation association:klass.getAssociations()){
			List<UMLAssociationEnd> assocEnds = association.getAssociationEnds();
			UMLAssociationEnd thisEnd = getThisEnd(klass,assocEnds);
			UMLAssociationEnd otherEnd = getOtherEnd(klass,assocEnds);

			if (otherEnd.isNavigable())
			{
				UMLClass assocKlass = (UMLClass) otherEnd.getUMLElement();

				if ((isMany2One(thisEnd, otherEnd) || isOne2One(thisEnd, otherEnd)) && null !=findCorrelationTable(association, model, assocKlass, false))
				{
					joinRequiredAssociation.add(association);
				}
				else
				{
					noJoinRequiredAssociation.add(association);
				}
			}
		}


		List result = new ArrayList();
		result.addAll(noJoinRequired);
		result.addAll(noJoinRequiredAssociation);
		result.addAll(joinRequired);
		result.addAll(joinRequiredAssociation);

		return result;
	}

	public String getHibernateMappingForIsoAttribute(RootNode rootNode, String prefix) throws GenerationException
	{
		IsoDatatypeTransformationHelper isoDatatypeTransformationHelper = new IsoDatatypeTransformationHelper();
		isoDatatypeTransformationHelper.setModel(model);
		isoDatatypeTransformationHelper.setUtils(this);
		StringBuffer buffer = isoDatatypeTransformationHelper.convertToHibernateComponent(rootNode, prefix);
		return buffer.toString();
	}

	public String getHibernateClassMappingForIsoAttribute(RootNode rootNode, String prefix) throws GenerationException
	{
		IsoDatatypeTransformationHelper isoDatatypeTransformationHelper = new IsoDatatypeTransformationHelper();
		isoDatatypeTransformationHelper.setModel(model);
		isoDatatypeTransformationHelper.setUtils(this);
		StringBuffer buffer = isoDatatypeTransformationHelper.convertToHibernateClass(rootNode, prefix);
		return buffer.toString();
	}

	public RootNode getDatatypeNode(UMLClass klass, UMLAttribute attribute, UMLClass table) throws GenerationException
	{
		IsoDatatypeTransformationHelper isoDatatypeTransformationHelper = new IsoDatatypeTransformationHelper();
		isoDatatypeTransformationHelper.setModel(model);
		isoDatatypeTransformationHelper.setUtils(this);
		RootNode rootNode = isoDatatypeTransformationHelper.getDatatypeNode(klass, attribute, table,false);
		return rootNode;
	}

	public boolean isSeperateClassMappingRequired(RootNode rootNode)
	{
		IsoDatatypeTransformationHelper isoDatatypeTransformationHelper = new IsoDatatypeTransformationHelper();
		isoDatatypeTransformationHelper.setModel(model);
		isoDatatypeTransformationHelper.setUtils(this);
		return isoDatatypeTransformationHelper.requiresSeperateClassMapping(rootNode);
	}
}