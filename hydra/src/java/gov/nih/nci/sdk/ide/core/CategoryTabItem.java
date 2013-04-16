/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.sdk.ide.core;

import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public abstract class CategoryTabItem extends ActiveUI {
	private String title;
	private TabItem theTabItem;
	
	public CategoryTabItem(TabFolder parent, int style, Object data, String title) {
		super(parent, style, data);
		this.title = title;
		theTabItem.setText(title);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getCategory() {
		ModelSelectionEvent mse = (ModelSelectionEvent)super.getData();
		return (mse != null)?mse.getCategory():"";
	}
	
	public String getPackage() {
		ModelSelectionEvent mse = (ModelSelectionEvent)super.getData();
		return (mse != null)?mse.getPackageName():"";
	}
	
	public String getModel() {
		ModelSelectionEvent mse = (ModelSelectionEvent)super.getData();
		return (mse != null)?mse.getModelName():"";
	}
	
	public String getDefaultConcept(String uriPrefix, String name) {
		if (UIHelper.isEmpty(uriPrefix)) uriPrefix = "http://example.org";
		String concept = getDefaultConcept(uriPrefix, getCategory(), getPackage(), getModel(), name);
		return concept;
	}
	
	String getDefaultConcept(String uriPrefix, String category, String pkgName, String model, String name) {
		StringBuilder sb = new StringBuilder();
		if (!UIHelper.isEmpty(uriPrefix)) {
			sb.append(uriPrefix);
		} 
		if (!UIHelper.isEmpty(category)) {
			sb.append("/").append(category);
		} 
		if (!UIHelper.isEmpty(pkgName)) {
			sb.append("/").append(pkgName);
		} 
		if (!UIHelper.isEmpty(model)) {
			sb.append("/").append(model);
		} 
		if (!UIHelper.isEmpty(name)) {
			sb.append("/").append(name);
		}
		return sb.toString();
	}
	
	public TabItem getTabItem() {
		return theTabItem;
	}
	
	protected Composite initUIComposite() {
		Composite parent = getParent();
		theTabItem = new TabItem((TabFolder)parent, SWT.V_SCROLL);
		
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL);
		
		Composite tabArea = new Composite(sc, SWT.NONE);
		sc.setContent(tabArea);

		theTabItem.setControl(sc);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		tabArea.setLayout(layout);
		tabArea.setLayoutData(UIHelper.getCoverAllGridData());
		
		UIHelper.setWhiteBackground(sc);
		UIHelper.setWhiteBackground(tabArea);
		
		return tabArea;
	}
}
