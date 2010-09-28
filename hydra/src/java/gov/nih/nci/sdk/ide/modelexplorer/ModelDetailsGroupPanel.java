package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.CategoryContentBuilder;
import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.UIHelper;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

public class ModelDetailsGroupPanel extends GroupPanel {
	private String currentHandledEventName;
	
	public ModelDetailsGroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data, title);
		super.getUIComposite().setLayoutData(getGridData());
	}
	
	public void paint() {
		_paint((ModelSelectionEvent)super.getData());
	}
	
	protected GridData getGridData() {
		GridData gd = UIHelper.getCoverAllGridData();
		gd.widthHint = Constants.MODEL_DETAILS_PANEL_WIDTH;
		return gd;
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
		
		String eventName = event.getEventName();
		if (!eventName.equals(currentHandledEventName)) {
			((Group)super.getUIComposite()).setText(formatGroupTitle(event));
			
			CategoryContentBuilder ccb = CategoryContentBuilderFactory.getContentBuilder(event.getCategory());
			ccb.buildContent(super.getUIComposite(), event);
			currentHandledEventName = eventName;
		}
	}
	
	private String formatGroupTitle(ModelSelectionEvent event) {
		return event.getEventName() + " Viewer";
	}
}
