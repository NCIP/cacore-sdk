package gov.nih.nci.sdk.ide.converter;

import gov.nih.nci.sdk.ide.core.SDKScreen;
import gov.nih.nci.sdk.ide.core.UIHelper;
import gov.nih.nci.sdk.ide.modelexplorer.SDKUIManager;
import gov.nih.nci.sdk.modelconverter.ModelConverter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * SDKConverter class.
 * 
 * @author John Chen
 */
public class SDKConverter extends SDKScreen {
	private Label fileLabel;
	private Text fileText;
	private Button fileButton;
	private Label convertersLabel;
	private Table convertersTable;
	private Button convertButton;
	
	private List<ConverterInfoVO> converters = new ArrayList<ConverterInfoVO>();
	private String selectedConverter;
	private String modelFile;
	
	public SDKConverter(Shell parent, String title) {
		super(parent, title);
	}
	
	//TODO: to be changed by pulling info from backend
	private List<ConverterInfoVO> retrieveConverters() {
		converters = new ArrayList<ConverterInfoVO>();
		ConverterInfoVO cv1 = new ConverterInfoVO();
		cv1.setName("BestEAModelConverter");
		cv1.setDescription("");
		cv1.setCreator("Best Products, Inc.");
		cv1.setClassName("gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverter");
		converters.add(cv1);

		ConverterInfoVO cv2 = new ConverterInfoVO();
		cv2.setName("GenericModelConverter");
		cv2.setDescription("");
		cv2.setCreator("NCI CBIIT SDK Team");
		cv2.setClassName("gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverter");
		converters.add(cv2);

		ConverterInfoVO cv3 = new ConverterInfoVO();
		cv3.setName("EAToEcoreConverter");
		cv3.setDescription("");
		cv3.setCreator("Eclipse Modeling Team");
		cv3.setClassName("gov.nih.nci.sdk.modelconverter.xmi2ecore.XMI2EcoreModelConverter");
		converters.add(cv3);
		return converters;
	}
	
	public void createScreen(final Composite composite) {
		GridLayout layout = new GridLayout();
		layout.horizontalSpacing = 10;
		layout.marginHeight = 10;
		layout.marginWidth = 10;
		layout.numColumns = 3;
		composite.setLayout(layout);
		
		fileLabel = new Label(composite, SWT.NONE);
		fileLabel.setText("Model File");
		
		fileText = new Text(composite, SWT.BORDER);
		fileText.setLayoutData(UIHelper.getFieldGridData());
		
		fileButton = new Button(composite, SWT.PUSH);
		fileButton.setText("Browse");
		fileButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(composite.getShell(), SWT.OPEN);
				String[] filterNames = new String[] { "Model Files", "All Files (*)" };
				String[] filterExtensions = new String[] { "*.xmi;", "*" };
				String filterPath = "/";
				String platform = SWT.getPlatform();
				if (platform.equals("win32") || platform.equals("wpf")) {
					filterPath = "c:\\";
				}
				dialog.setFilterNames(filterNames);
				dialog.setFilterExtensions(filterExtensions);
				dialog.setFilterPath(filterPath);
				
				String fileName = dialog.open();
				if (fileName != null) {
					fileText.setText(fileName);
					modelFile = fileName;
					
					if (selectedConverter != null) {
						convertButton.setEnabled(true);
						convertButton.setFocus();
					}
				}
			}
		});
		
		convertersLabel = new Label(composite, SWT.NONE);
		convertersLabel.setText("Available Model Converters");

		convertersTable = new Table(composite, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		GridData ctGD = UIHelper.getFieldGridData();
		ctGD.heightHint = 100;
		ctGD.minimumWidth = 400;
		convertersTable.setLayoutData(ctGD);
		convertersTable.addListener (SWT.Selection, new Listener() {
			public void handleEvent (Event event) {
				TableItem item = (TableItem) event.item;
				if (item != null) {
					selectedConverter = item.getText();
					
					if (modelFile != null) {
						convertButton.setEnabled(true);
						convertButton.setFocus();
					}
				}
			}
		});
		
		String[] headers = {"Name                              ", "Maker                              "};
		for (int i = 0; i < headers.length; i++) {
			TableColumn column = new TableColumn(convertersTable, SWT.NONE);
			column.setText(headers[i]);
			column.setWidth(DIALOG_PERSISTSIZE);
			convertersTable.getColumn(i).pack();
		}
		
		int count = converters.size();
		for (int i = 0; i < count; i++) {
			TableItem item = new TableItem(convertersTable, SWT.NONE);
			ConverterInfoVO cv = converters.get(i);
			item.setText(0, cv.getName());
			item.setText(1, cv.getCreator());
		}
	}
	
	@Override
	public void okPressed() {
		ConverterInfoVO ciVO = getSelectedConverter(selectedConverter);
		EPackage rootEPackage = convertModelFile2Ecore(modelFile, ciVO.getClassName());
		if (rootEPackage == null) {
			throw new IllegalArgumentException("Nothing was created in the conversion. Please check if the model file is valid");
		}
		SDKUIManager.getInstance().setRootEPackage(rootEPackage);
		super.okPressed();
	}
	
	private EPackage convertModelFile2Ecore(String filePath, String converterClassName) {
		EPackage rootEPackage = null;
		try {
			ModelConverter converter = (ModelConverter)createInstance(converterClassName);
			rootEPackage = converter.convert(filePath);
		}
		catch (Exception ex) {
			throw new RuntimeException("Failed to convert model file " + filePath + " with class " + converterClassName);
		}
		return rootEPackage;
	}
	
	private Object createInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> c = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = getClass().getClassLoader();
        }
        c = classLoader.loadClass(className);
        return c.newInstance();
	}
	
	@Override
	public Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		Button button = super.createButton(parent, id, label, defaultButton);
		if (id == IDialogConstants.OK_ID) {
			button.setText("Convert");
			convertButton = button;
			convertButton.setEnabled(false);
		}
		return button;
	}
	
	@Override
	public boolean isResizable() {
		return true;
	}
	
	@Override
	public int open() {
		if (converters == null) converters = new ArrayList<ConverterInfoVO>();
		converters.clear();
		converters = retrieveConverters();
		if (converters == null || converters.size() == 0) {
			throw new RuntimeException("There is no converter found.");
		}
		modelFile = null;
		selectedConverter = null;
		return super.open();
	}
	
	private ConverterInfoVO getSelectedConverter(String name) {
		if (name == null) throw new IllegalArgumentException("A converter must be selected.");
		ConverterInfoVO ciVO = null;
		for (ConverterInfoVO ci : converters) {
			if (name.equals(ci.getName())) {
				ciVO = ci;
				break;
			}
		}
		return ciVO;
	}
}
