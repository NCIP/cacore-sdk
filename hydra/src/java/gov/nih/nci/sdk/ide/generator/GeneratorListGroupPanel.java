/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

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

	private java.util.Properties loadProperties(java.io.File _generatorDirectory, final String _propertyFileName)
	{
		java.io.File[] generatorPropertyFiles = _generatorDirectory.listFiles(new java.io.FilenameFilter() {
			public boolean accept(java.io.File _dir, String _name)
			{
				return (_name.endsWith(_propertyFileName) == true);
			}
		});

		if (generatorPropertyFiles.length != 1)
		{
			throw new RuntimeException("All plugins must include exactly one " +  _propertyFileName + "  file in its root directory");
		}

		java.util.Properties properties = new java.util.Properties();

		try 
		{
			properties.load(new java.io.FileReader(generatorPropertyFiles[0]));
		}
		catch(Throwable t)
		{
			throw new RuntimeException("Unable to load properties from file: " + generatorPropertyFiles[0].getName());
		}
		
		return properties;
	}
	
	private List<GeneratorInfoVO> getGenerators()
	{
		//This method should be querying the OSGI layer somehow to
		//determine the generators that exist.  Instead it will read
		//the generator directory and set up the generators ...
		GeneratorInfoVO generator = new GeneratorInfoVO();
		List<GeneratorInfoVO> generatorList = new ArrayList<GeneratorInfoVO>();

		java.io.File generatorsDirectory = new java.io.File("generators");
		java.io.File[] generatorDirectoryArray = generatorsDirectory.listFiles();

		for (int i = 0; i < generatorDirectoryArray.length; i++)
		{
			java.util.Properties properties = loadProperties(generatorDirectoryArray[i], "info.properties");

			generator.setName(properties.getProperty("name"));
			generator.setDescription(properties.getProperty("description"));
			generator.setProperties(loadProperties(generatorDirectoryArray[i], "generator.properties"));
			
			generatorList.add(generator);
		}
		
		return generatorList;
	}
}