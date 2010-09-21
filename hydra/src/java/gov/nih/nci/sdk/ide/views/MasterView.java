package gov.nih.nci.sdk.ide.views;

import gov.nih.nci.sdk.ide.core.ActiveViewPart;
import gov.nih.nci.sdk.ide.core.ModelPackageVO;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.generator.SDKGenerator;
import gov.nih.nci.sdk.ide.modelexplorer.Constants;
import gov.nih.nci.sdk.ide.modelexplorer.ModelSelectionEvent;
import gov.nih.nci.sdk.ide.modelexplorer.SDKModelExplorer;
import gov.nih.nci.sdk.ide.modelexplorer.SDKModelExplorerUtil;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;


/**
 * MasterView class represents a model selection tree.
 * 
 * @author John Chen
 */
public class MasterView extends ActiveViewPart {

	public static final String ID = "gov.nih.nci.sdk.ide.views.MasterView";

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;
	private Action actionLoadXMI;
	private Action actionGenerateApp;
	private Action clickAction;

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			return getChildren(parent);
		}

		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}
	}
	
	class ViewLabelProvider extends LabelProvider {
		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_FILE;
			if (obj instanceof TreeParent)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
	}
	
	class NameSorter extends ViewerSorter {
	}
	
	public MasterView() {
		SDKUIManager.getInstance().registerAsListener(SWT.SetData, this);
	}

	public void paint(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());

		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "gov.nih.nci.sdk.ide.viewer");
		makeActions();
		hookContextMenu();
		hookClickAction();
		contributeToActionBars();
		
		initialize();
	}

	private void initialize() {
		EPackage rootEPackage = SDKUIManager.getInstance().getRootEPackage();
		updateTree(rootEPackage);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				MasterView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(actionLoadXMI);
		manager.add(new Separator());
		manager.add(actionGenerateApp);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(actionLoadXMI);
		manager.add(actionGenerateApp);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(actionLoadXMI);
		manager.add(actionGenerateApp);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		actionLoadXMI = new Action() {
			public void run() {
				popupConverter();
			}
		};
		actionLoadXMI.setText(Constants.LABEL_FOR_LOAD_XMI);
		actionLoadXMI.setToolTipText(Constants.LABEL_FOR_LOAD_XMI);
		actionLoadXMI.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJ_PROJECT));
		
		actionGenerateApp = new Action() {
			public void run() {
				popupGenerator();
			}
		};
		actionGenerateApp.setText(Constants.LABEL_FOR_GENERATE_APP);
		actionGenerateApp.setToolTipText(Constants.LABEL_FOR_GENERATE_APP);
		actionGenerateApp.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT));
		
		clickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				if (selection == null) return;
				
				TreeObject selectedItem = (TreeObject)((IStructuredSelection)selection).getFirstElement();
				if (selectedItem != null) {
					String packageName = "";
					String modelName = "";
					String categoryName = "";
					if (clickedOnCategory(selectedItem)) {
						TreeParent pkgItem = selectedItem.getParent().getParent();
						if (pkgItem != null) packageName = pkgItem.getName();
						modelName = selectedItem.getParent().getName();
						categoryName = selectedItem.getName();
					}
					else if (clickedOnModel(selectedItem)) {
						TreeParent pkgItem = selectedItem.getParent();
						if (pkgItem != null) packageName = pkgItem.getName();
						modelName = selectedItem.getName();
						categoryName = Constants.DEFAULT_CATEGORY;
					}
					else {
						packageName = selectedItem.getName();
						if (((TreeParent)selectedItem).getChildrenCount() > 0) {
							modelName = ((TreeParent)selectedItem).getChildren()[0].getName();
							categoryName = Constants.DEFAULT_CATEGORY;
						}
					}
					
					if (!(UIHelper.isEmpty(packageName) && UIHelper.isEmpty(modelName) && UIHelper.isEmpty(categoryName))) {
						Event eve = new ModelSelectionEvent(packageName, modelName, categoryName);
						publishEvent(SWT.Selection, eve);
					}
				}
			}
		};
	}
	
	private boolean clickedOnModel(TreeObject selectedItem) {
		boolean check = false;
		if (selectedItem instanceof TreeParent && 
				((TreeParent)selectedItem).getChildrenCount() == Constants.ALL_CATEGORIES.length &&
				Constants.ALL_CATEGORIES[0].equals(((TreeParent)selectedItem).getChildren()[0].getName())) {
			check = true;
		}
		return check;
	}
	
	private boolean clickedOnCategory(TreeObject selectedItem) {
		boolean check = false;
		if (!(selectedItem instanceof TreeParent) && 
				selectedItem.getParent() != null) {
			check = true;
		}
		return check;
	}

	private void hookClickAction() {
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				clickAction.run();
			}
		});
	}
	
	private void popupConverter() {
		SDKModelExplorer sui = new SDKModelExplorer(viewer.getControl().getShell(), Constants.CONVERTER_SCREEN_TITLE);
		sui.open();
	}
	
	private void popupGenerator() {
		SDKGenerator sui = new SDKGenerator(viewer.getControl().getShell(), Constants.GENERATOR_SCREEN_TITLE);
		sui.open();
	}
	
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	
	@Override
	public void handleEvent(Event event) {
		if (SWT.SetData == event.type && event.data instanceof EPackage) {
			updateTree((EPackage)event.data);
		}
	}
	
	private void updateTree(EPackage rootEPackage) {
		List<ModelPackageVO> dataList = SDKModelExplorerUtil.getModelPackages(rootEPackage);
		String[] categories = Constants.ALL_CATEGORIES;
		TreeParent root = new TreeParent("");
		viewer.setInput(root);
		for (int k = 0; k < dataList.size(); k++) {
			ModelPackageVO mpVO = dataList.get(k);
			if (mpVO.hasPackage()) {
				TreeParent packageItem = new TreeParent(mpVO.getPackageName());
				root.addChild(packageItem);
				List<String> models = SDKModelExplorerUtil.convertToList((Set<String>)mpVO.getModels());
				for (int i = 0; i < models.size(); i++) {
					TreeParent model = new TreeParent(models.get(i));
					packageItem.addChild(model);
					for (int j = 0; j < categories.length; j++) {
						TreeObject category = new TreeObject(categories[j]);
						model.addChild(category);
						
						if (k == 0 && i == 0 && j == 0) {
							Object[] segments = new Object[3];
							segments[0] = packageItem.getName();
							segments[1] = model.getName();
							segments[2] = category.getName();
							TreePath tp = new TreePath(segments);
							TreeSelection ts = new TreeSelection(tp);
							viewer.setSelection(ts);
							viewer.expandToLevel(2);
							Event event = new ModelSelectionEvent(mpVO.getPackageName(), model.getName(), category.getName());
							publishEvent(SWT.Selection, event);
						}
					}
				}
			}
			else {
				List<String> models = SDKModelExplorerUtil.convertToList((Set<String>)mpVO.getModels());
				for (int i = 0; i < models.size(); i++) {
					TreeParent model = new TreeParent(models.get(i));
					root.addChild(model);
					for (int j = 0; j < categories.length; j++) {
						TreeObject category = new TreeObject(categories[j]);
						model.addChild(category);
						
						if (k == 0 && i == 0 && j == 0) {
							Object[] segments = new Object[2];
							segments[0] = model.getName();
							segments[1] = category.getName();
							TreePath tp = new TreePath(segments);
							TreeSelection ts = new TreeSelection(tp);
							viewer.setSelection(ts);
							viewer.expandToLevel(1);
							Event event = new ModelSelectionEvent(mpVO.getPackageName(), model.getName(), category.getName());
							publishEvent(SWT.Selection, event);
						}
					}
				}
			}
		}
		
		viewer.refresh();
	}
}