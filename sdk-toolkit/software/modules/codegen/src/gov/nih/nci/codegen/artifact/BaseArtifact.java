/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.codegen.artifact;

import gov.nih.nci.codegen.Artifact;
import gov.nih.nci.codegen.util.TransformerUtils;
import gov.nih.nci.ncicb.xmiinout.domain.UMLClass;
import gov.nih.nci.ncicb.xmiinout.domain.UMLInterface;
import gov.nih.nci.ncicb.xmiinout.domain.UMLPackage;

/**
 *
 * @author Satish Patel
 */
public class BaseArtifact implements Artifact
{
	private String content;
	private String sourceName;
	protected TransformerUtils transformerUtils;


	public BaseArtifact(TransformerUtils transformerUtils) {
		this.transformerUtils = transformerUtils;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.codegen.Artifact#getContent()
	 */
	public String getContent()
	{
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.codegen.Artifact#getSourceName()
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * Creates the source name for the JUnit XML Data Artifact from the <code>klass</code>
	 *
	 * @param klass
	 */
	public void createJunitXmlDataSourceName(UMLClass klass) {
		sourceName = (transformerUtils.getFullPackageName(klass)+"."+klass.getName()+"XMLDataTest").replace('.','/');
	}
	/**
	 * Creates the source name for the JUnit XSD Artifact from the <code>klass</code>
	 *
	 * @param klass
	 */
	public void createJunitXsdSourceName(UMLClass klass) {
		sourceName = (transformerUtils.getFullPackageName(klass)+"."+klass.getName()+"XSDTest").replace('.','/');
	}
	/**
	 * Creates the source name for the JUnit POJO Artifact from the <code>klass</code>
	 *
	 * @param klass
	 */
	public void createJunitPojoSourceName(UMLClass klass) {
		sourceName = (transformerUtils.getFullPackageName(klass)+"."+klass.getName()+"Test").replace('.','/');
	}

	/**
	 * Creates the source name for the JUnit POJO Artifact from the <code>klass</code>
	 *
	 * @param klass
	 */
	public void createJunitRESTfulSourceName(UMLClass klass) {
		sourceName = (transformerUtils.getFullPackageName(klass)+"."+klass.getName()+"ResourceTest").replace('.','/');
	}

	/**
	 * Creates the source name for the RESTFul Resource Artifact from the <code>klass</code>
	 *
	 * @param klass
	 */
	public void createRESTResourceName(UMLClass klass) {
		sourceName = (transformerUtils.getFullPackageName(klass)+"."+klass.getName()+"Resource").replace('.','/');
	}

	/**
	 * Creates the source name for the RESTFul Bean Artifact from the <code>klass</code>
	 *
	 * @param klass
	 */
	public void createRESTBeanName(UMLClass klass) {
		sourceName = (transformerUtils.getFullPackageName(klass)+"."+klass.getName()+"Bean").replace('.','/');
	}

	/**
	 * Creates the source name for the RESTFul collection bean from the <code>klass</code>
	 *
	 * @param klass
	 */
	public void createRESTCollectionBeanName(UMLClass klass) {
		sourceName = (transformerUtils.getFullPackageName(klass)+"."+klass.getName()+"s").replace('.','/');
	}

	/**
	 * Creates the source name for the artifact from the <code>klass</code>
	 *
	 * @param klass
	 */
	public void createSourceName(UMLClass klass) {
		sourceName = transformerUtils.getFQCN(klass).replace('.','/');
	}

	/**
	 * Creates the source name for the artifact from the <code>interface</code>
	 *
	 * @param interfaze
	 */
	public void createSourceName(UMLInterface interfaze) {
		sourceName = transformerUtils.getFQCN(interfaze).replace('.','/');
	}


	/**
	 * Creates the source name for the artifact from the <code>pkg</code>
	 *
	 * @param pkg
	 */
	public void createSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

}