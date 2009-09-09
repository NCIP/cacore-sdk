package gov.nih.nci.cacore.workbench.portal.panel;

import java.util.Map;

import javax.swing.JPanel;

public interface Panel {
	
	public JPanel getSettingsPanel();
	public JPanel getSummaryPanel();
	public Map<String,String> getPropsMap();

}