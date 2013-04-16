/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.core;

import org.eclipse.swt.widgets.Composite;

public interface CategoryContentBuilder {
	public Object buildContent(Composite composite, Object data);
}
