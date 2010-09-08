package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.UIHelper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class ModelDetailsGroupPanel extends GroupPanel {
	private TabFolder categoryTabFolder;
	
	public ModelDetailsGroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data, title);
	}
	
	public void paint(Composite composite) {
		categoryTabFolder = new TabFolder(composite, SWT.TOP);
		categoryTabFolder.setLayoutData(UIHelper.getFieldGridData());
		
		categoryTabFolder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				TabItem[] selected = categoryTabFolder.getSelection();
				if (selected.length > 0) {
					Event eve = new Event();
					eve.type = SWT.Selection;
					eve.text = selected[0].getText();
					notifyListeners(SWT.Selection, eve);
				}
			}
		});
		
		String[] meaningTabs = Constants.meaningTabs;
		
		for (int i = 0; i < meaningTabs.length; i++) {
			TabItem item = new TabItem(categoryTabFolder, SWT.NONE);
			item.setText(meaningTabs[i]);
			
			Composite control = new Composite(categoryTabFolder, SWT.NONE);
			control.setLayout(super.getLayout());
			Button button = new Button(control, SWT.PUSH);
			button.setText("Tab " + meaningTabs[i]);
			item.setControl(control);
		}
		
		if (categoryTabFolder.getItemCount() > 0) {
			categoryTabFolder.setSelection(0);
			Event event = new Event();
			event.item = categoryTabFolder.getItem(0);
			categoryTabFolder.notifyListeners(SWT.Selection, event);
		}
	}
	
	protected GridData getGridData() {
		GridData gd = super.getGridData();
		gd.widthHint = Constants.MODEL_DETAILS_PANEL_WIDTH;
		return gd;
	}
	
	@Override
	public void handleEvent(Event event) {
		if (event == null) return;
		
		if (event instanceof ModelSelectionEvent) {
			System.out.println("DETAILS received: " + event);
			ModelSelectionEvent mse = (ModelSelectionEvent)event;
			((Group)super.getUIComposite()).setText(formatGroupTitle(mse.getClassName(), mse.getCategory()));
			
			categoryTabFolder.dispose();
		}
	}
	
	private String formatGroupTitle(String className, String category) {
		return className + " - " + category + " Viewer";
	}
}
