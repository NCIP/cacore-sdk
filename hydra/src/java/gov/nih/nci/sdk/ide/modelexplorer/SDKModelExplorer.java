package gov.nih.nci.sdk.ide.modelexplorer;

import gov.nih.nci.sdk.ide.core.GroupPanel;
import gov.nih.nci.sdk.ide.core.ModelPackageVO;
import gov.nih.nci.sdk.ide.core.SDKScreen;
import gov.nih.nci.sdk.ide.core.UIHelper;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

public class SDKModelExplorer extends SDKScreen {
	private static EPackage epackage;
	
	public SDKModelExplorer(Shell parent, String title, Object data) {
		super(parent, title);
		epackage = (EPackage)data;
	}
	
	public static EPackage getEPackage() {
		return epackage;
	}
	
	public void createScreen(Composite composite) {
		composite.setSize(Constants.MODEL_EXPLORER_SCREEN_WIDTH, Constants.MODEL_EXPLORER_SCREEN_HEIGHT);
		
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 10;
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		composite.setLayout(layout);
		
		List<ModelPackageVO> dataList = UIHelper.getModelPackages(epackage);
		GroupPanel masterPanel = new ModelMasterGroupPanel(composite, SWT.NONE, dataList, Constants.MODEL_MASTER_PANEL_TITLE);
		masterPanel.paint();
		
		ModelPackageVO firstMP = getFirstModel(dataList);
		if (firstMP == null) return;
		String packageName = firstMP.getPackageName();
		String modelName = firstMP.getModels().get(0);
		String categoryName = Constants.DEFAULT_CATEGORY;
		Event defaultEvent = new ModelSelectionEvent(packageName, modelName, categoryName);
		
		GroupPanel detailsPanel = new ModelDetailsGroupPanel(composite, SWT.NONE, defaultEvent, Constants.MODEL_DETAILS_PANEL_TITLE);
		detailsPanel.paint();
		
		masterPanel.addListener(SWT.Selection, detailsPanel);
	}
	
	private ModelPackageVO getFirstModel(List<ModelPackageVO> dataList) {
		if (dataList == null || dataList.size() == 0) return null;
		
		ModelPackageVO firstModel = null;
		for (ModelPackageVO mpVO : dataList) {
			List<String> models = mpVO.getModels();
			if (models.size() > 0) {
				firstModel = mpVO;
				break;
			}
		}
		
		return firstModel;
	}
}
