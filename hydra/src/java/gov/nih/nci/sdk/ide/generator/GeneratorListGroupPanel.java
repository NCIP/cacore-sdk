package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.ModelPackageVO;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.SDKModelExplorerUtil;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class GeneratorListGroupPanel extends GroupPanel {
	
	public GeneratorListGroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data, title);
	}
	
	protected Layout getLayout() {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.verticalSpacing = 2;
		layout.horizontalSpacing = 10;
		return layout;
	}
	
	public void paint() {
		Composite composite = super.getUIComposite();
		
//		ScrolledComposite sc = new ScrolledComposite(composite, SWT.V_SCROLL);
//		Composite listArea = new Composite(sc, SWT.NONE);
		//sc.setContent(listArea);
		//sc.setMinSize(listArea.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		//Composite listArea = new Composite(composite, SWT.NONE);
		ScrolledComposite listArea = new ScrolledComposite(composite, SWT.V_SCROLL);
		
		Color whiteColor = composite.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		listArea.setBackground(whiteColor);
		listArea.setLayout(getLayout());
		listArea.setLayoutData(getGridData());

		Button button = new Button(listArea, SWT.CHECK);
		button.setText("Replace me for ");
		button.setBackground(whiteColor);
		
		for (int i = 0; i < 50; i++) {
			button = new Button(listArea, SWT.CHECK);
			button.setText("Replace me for " + i);
			button.setBackground(whiteColor);
		}
	}
}
