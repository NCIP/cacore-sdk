package gov.nih.nci.cacore.workbench.common;

/**
 * Constants used in the caCORE Workbench Portal
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Daniel Dumitru </A>
 * @author <A HREF="MAILTO:patelsat@mail.nih.gov">Satish Patel </A>
 */
public abstract class CacoreWorkbenchConstants {

	// Workbench specific constants
	public static final String WORKBENCH_VERSION_PROPERTY = "cacore.workbench.version";

	public static final String WORKBENCH_ENGINE_PROPERTIES = "cacore.workbench.engine.properties";
	
	public static final String WORKBENCH_HELP_URL = "cacore.workbench.help.url";
	public static final String WORKBENCH_TOOLS_SITE_URL = "cacore.workbench.tools.site.url";
	public static final String SDK_GUIDE_URL = "cacore.sdk.programmers.guide";		
	public static final String WORKBENCH_GUIDE_URL = "cacore.workbench.user.guide";	
	
	//Create Grid Service Workflow
	public static final String LAUNCH_INTRODUCE_URL = "cacore.workbench.launch.introduce.url";
	
	//Create UML Model Workflow
	public static final String LAUNCH_ARGO_UML_WEB_START_URL = "cacore.workbench.launch.argo.uml.web.start.url";
	public static final String DOWNLOAD_ENTERPRISE_ARCHITECT_URL = "cacore.workbench.download.ea.url";

	//Semantic Integration Workflow
	public static final String LAUNCH_SIW_URL = "cacore.workbench.launch.siw.url";
	public static final String LAUNCH_CDE_BROWSER_URL = "cacore.workbench.launch.cde.browser.url";
	public static final String LAUNCH_UML_MODEL_BROWSER_URL = "cacore.workbench.launch.uml.model.browser.url";
	public static final String LAUNCH_CDE_CURATION_TOOL_URL = "cacore.workbench.launch.cde.curation.tool.url";
	
	//Model Mapping Workflow
	public static final String LAUNCH_CAADAPTER_URL = "cacore.workbench.launch.caadapter.url";
	
	public static String LOG_FILE_VALIDATION_KEY = "Log file path";

	private CacoreWorkbenchConstants() {
		// prevents instantiation
	}
}
