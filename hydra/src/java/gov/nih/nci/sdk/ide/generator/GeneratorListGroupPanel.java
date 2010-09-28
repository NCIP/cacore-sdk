package gov.nih.nci.sdk.ide.generator;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.PropertyVO;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;

public class GeneratorListGroupPanel extends GroupPanel {
	List<GeneratorInfoVO> generators;
	public GeneratorListGroupPanel(Composite parent, int style, Object data, String title) {
		super(parent, style, data, title);
		generators = getGenerators();
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
		Composite listArea = new Composite(composite, SWT.NONE);
		
		Color whiteColor = listArea.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		listArea.setBackground(whiteColor);
		listArea.setLayout(getLayout());
		listArea.setLayoutData(UIHelper.getCoverAllGridData());
		
		final org.eclipse.swt.widgets.List list = new org.eclipse.swt.widgets.List(listArea, SWT.NONE);
		for (int i = 0; i < generators.size(); i++) {
			list.add (generators.get(i).getName());
		}
		list.setBounds (0, 0, 100, 100);
		list.addListener (SWT.Selection, new Listener() {
			public void handleEvent (Event event) {
				int[] selection = list.getSelectionIndices();
				GeneratorInfoVO selected = generators.get(selection[0]);
				GeneratorSelectionEvent eve = new GeneratorSelectionEvent(selected);
				SDKUIManager.getInstance().publishEvent(Constants.GENERATOR_SELECTION_EVENT, eve);
			}
		});
	}
	
	//TODO
	private List<GeneratorInfoVO> getGenerators() {
		List<GeneratorInfoVO> generators = new ArrayList<GeneratorInfoVO>();
		
		GeneratorInfoVO sdkExample = new GeneratorInfoVO();
		sdkExample.setName("SDK Example");
		sdkExample.setDescription("SDK Example serves as a tutorial on SDK.");
		List<PropertyVO> properties = new ArrayList<PropertyVO>();
		PropertyVO property1 = new PropertyVO();
		property1.setName("rootDirectory");
		property1.setValue("c:\\work\\sdkexample");
		properties.add(property1);
		PropertyVO property2 = new PropertyVO();
		property2.setName("generatePojos");
		property2.setValue("true");
		property2.setDefaultValue("true");
		properties.add(property2);
		PropertyVO property3 = new PropertyVO();
		property3.setName("generateServices");
		property3.setValue("true");
		property3.setDefaultValue("true");
		properties.add(property3);
		sdkExample.setProperties(properties);
		generators.add(sdkExample);
		
		GeneratorInfoVO cbiitRef = new GeneratorInfoVO();
		cbiitRef.setName("NCI CBIIT Reference Implementation");
		cbiitRef.setDescription("NCI CBIIT's recommended working " 
				+ "interoperability service implementation. This generator " 
				+ "will generate a CBIIT approved service implementation.");
		List<PropertyVO> properties2 = new ArrayList<PropertyVO>();
		PropertyVO property21 = new PropertyVO();
		property21.setName("rootDirectory");
		property21.setValue("c:\\work\\cbiitref");
		properties2.add(property21);
		PropertyVO property22 = new PropertyVO();
		property22.setName("generatePojos");
		property22.setValue("true");
		property22.setDefaultValue("false");
		properties2.add(property22);
		PropertyVO property23 = new PropertyVO();
		property23.setName("generateServices");
		property23.setValue("false");
		property23.setDefaultValue("false");
		properties2.add(property23);
		cbiitRef.setProperties(properties2);
		generators.add(cbiitRef);
		
		return generators;
	}
}
