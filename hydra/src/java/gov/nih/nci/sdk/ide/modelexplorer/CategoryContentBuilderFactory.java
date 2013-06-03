/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.CategoryContentBuilder;
import gov.nih.nci.sdk.ide.modelexplorer.meaning.MeaningContentBuilder;
import gov.nih.nci.sdk.ide.modelexplorer.persistence.PersistenceContentBuilder;

public class CategoryContentBuilderFactory {
	public static CategoryContentBuilder getContentBuilder(String category) {
		CategoryContentBuilder builder = null;
		if (Constants.CATEGORY_Meaning.equals(category)) {
			builder = new MeaningContentBuilder();
		}
		else if (Constants.CATEGORY_Persistence.equals(category)) {
			builder = new PersistenceContentBuilder();
		}
//		else if (Constants.CATEGORY_Security.equals(category)) {
//			builder = new SecurityContentBuilder();
//		}
//		else if (Constants.CATEGORY_Validation.equals(category)) {
//			builder = new ValidationContentBuilder();
//		}
//		else if (Constants.CATEGORY_Presentation.equals(category)) {
//			builder = new PresentationContentBuilder();
//		}
//		else if (Constants.CATEGORY_Representation.equals(category)) {
//			builder = new RepresentationContentBuilder();
//		}
//		else if (Constants.CATEGORY_Service.equals(category)) {
//			builder = new ServiceContentBuilder();
//		}
		else {
			throw new IllegalArgumentException("Category '" + category + "' is not supported in the current release of SDK.");
		}
		
		return builder;
	}
}
