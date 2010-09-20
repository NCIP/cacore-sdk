package gov.nih.nci.sdk.ide.modelexplorer;

public class Constants {
	public static final int MODEL_EXPLORER_SCREEN_WIDTH = 800;
	public static final int MODEL_EXPLORER_SCREEN_HEIGHT = 600;
	public static final int MODEL_DETAILS_PANEL_WIDTH = 400;

	public static final String SDK_SCREEN_TITLE = "SDK";
	public static final String MODEL_MASTER_PANEL_TITLE = "Model Explorer";
	public static final String MODEL_DETAILS_PANEL_TITLE = "Meaning Viewer";

	public static final String CATEGORY_Meaning = "Meaning";
	public static final String CATEGORY_Persistence = "Persistence";
	public static final String CATEGORY_Security = "Security";
	public static final String CATEGORY_Validation = "Validation";
	public static final String CATEGORY_Presentation = "Presentation";
	public static final String CATEGORY_Representation = "Representation";
	public static final String CATEGORY_Service = "Service";

	public static final String[] ALL_CATEGORIES = { CATEGORY_Meaning,
			CATEGORY_Persistence, CATEGORY_Security, CATEGORY_Validation,
			CATEGORY_Presentation, CATEGORY_Representation, CATEGORY_Service };

	public static final String DEFAULT_CATEGORY = CATEGORY_Meaning;

	public static final String TAB_Domain = "Domain";
	public static final String TAB_Operations = "Operations";
	public static final String TAB_Properties = "Properties";
	public static final String TAB_Relationship = "Relationship";

	public static String[] meaningTabs = { TAB_Domain, TAB_Properties,
			TAB_Operations, TAB_Relationship };
	public static String[] persistenceTabs = { TAB_Domain, TAB_Properties };
	
	public static final String LABEL_FOR_LOAD_XMI = "Load XMI";
	public static final String LABEL_FOR_GENERATE_APP = "Generate App";
}
