/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.views;

import gov.nih.nci.sdk.ide.core.ActiveViewPart;
import gov.nih.nci.sdk.ide.core.CategoryContentBuilder;
import gov.nih.nci.sdk.ide.modelexplorer.CategoryContentBuilderFactory;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

/**
 * DetailsView class represents a model details view.
 * 
 * @author John Chen
 */
public class DetailsView extends ActiveViewPart {
	public static final String ID = "gov.nih.nci.sdk.ide.views.DetailsView";

	private String currentHandledEventName;
	
	public DetailsView() {
	}
	
	@Override
	public void paint(Composite parent) {
		SDKUIManager.getInstance().registerAsListener(SWT.Selection, this);
		SDKUIManager.getInstance().lastSelection();
	}
	
	public void setFocus() {
		super.getUIComposite().getShell().setFocus();
	}
	
	@Override
	public void handleEvent(Event event) {
		System.out.println("DETAILS received: " + event);
		if (event == null) return;
		
		if (event instanceof ModelSelectionEvent) {
			_paint((ModelSelectionEvent)event);
		}
	}
	
	private void _paint(ModelSelectionEvent event) {
		if (event == null) return;
		
		if ("".equals(((ModelSelectionEvent)event).getModelName())) {
			super.getUIComposite().setVisible(false);
		}
		else {
			super.getUIComposite().setVisible(true);
		}
		
		String eventName = event.getEventName();
		if (!eventName.equals(currentHandledEventName)) {
			if (super.getUIComposite() == null) return;
			
			CategoryContentBuilder ccb = CategoryContentBuilderFactory.getContentBuilder(event.getCategory());
			ccb.buildContent(super.getUIComposite(), event);
			currentHandledEventName = eventName;
			
			super.getUIComposite().redraw();
		}
	}
}