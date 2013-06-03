/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.modelexplorer.Constants;

import java.util.Date;

import org.eclipse.swt.widgets.Event;

public class GeneratorSelectionEvent extends Event {
	private GeneratorInfoVO giVO;
	private Date timestamp;
	
	public GeneratorSelectionEvent(GeneratorInfoVO giVO) {
		if (giVO == null)
			throw new IllegalArgumentException(
					"Generator info value object cannot be null");

		this.giVO = giVO;
		this.timestamp = new Date();
		super.data = giVO;
		super.type = Constants.GENERATOR_SELECTION_EVENT;
	}

	public GeneratorInfoVO getGeneratorInfoVO() {
		return giVO;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getEventName() {
		return giVO.getName();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp=").append(timestamp).append(", ");
		sb.append("giVO=").append(giVO);
		return sb.toString();
	}
}
