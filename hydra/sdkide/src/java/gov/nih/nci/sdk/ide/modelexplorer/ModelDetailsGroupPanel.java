package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.CategoryContentBuilder;
import gov.nih.nci.sdk.ide.core.GroupPanel;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

public class ModelDetailsGroupPanel extends GroupPanel {
	private String currentHandledEventName;
	
	public ModelDetailsGroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data, title);
	}
	
	public void paint() {
		Composite composite = super.getUIComposite();
		
		CategoryContentBuilder ccb = CategoryContentBuilderFactory.getContentBuilder(Constants.DEFAULT_CATEGORY);
		ccb.buildContent(composite, super.getData());
	}
	
	protected GridData getGridData() {
		GridData gd = super.getGridData();
		gd.widthHint = Constants.MODEL_DETAILS_PANEL_WIDTH;
		return gd;
	}
	
	@Override
	public void handleEvent(Event event) {
		System.out.println("DETAILS received1: " + event);
		if (event == null) return;
		
		if (event instanceof ModelSelectionEvent) {
			ModelSelectionEvent mse = (ModelSelectionEvent)event;
			
			String eventName = ((ModelSelectionEvent) event).getEventName();
			if (!eventName.equals(currentHandledEventName)) {
				((Group)super.getUIComposite()).setText(formatGroupTitle(mse.getModelName(), mse.getCategory()));
				
				CategoryContentBuilder ccb = CategoryContentBuilderFactory.getContentBuilder(mse.getCategory());
				ccb.buildContent(super.getUIComposite(), super.getData());
				
				currentHandledEventName = eventName;
			}
		}
	}
	
	private String formatGroupTitle(String className, String category) {
		return className + " - " + category + " Viewer";
	}
}
