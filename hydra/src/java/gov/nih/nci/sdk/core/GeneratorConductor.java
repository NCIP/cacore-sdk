/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.core;

import org.eclipse.emf.ecore.EPackage;

public interface GeneratorConductor
{
	public void compile(GeneratorContext _generatorContext);
	public void level0Validate(GeneratorContext _generatorContext);
}