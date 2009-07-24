package gov.nih.nci.cacore.workbench.common;

import org.apache.log4j.Logger;

public final class WorkbenchCmdLineArgsManager {
	
	private static final Logger log = Logger.getLogger(WorkbenchCmdLineArgsManager.class);

	private static String workbenchType=null;
	private static String sdkSrcFile=null;
	private static String sdkSrcUrl=null;

	public static void setWorkbenchType(String workbenchType) {
		log.debug("* * * Setting Workbench Type Arg to: " + workbenchType);
		WorkbenchCmdLineArgsManager.workbenchType = workbenchType;
	}

	public static String getWorkbenchType(){
		return workbenchType;
	}

	public static String getSdkSrcUrl() {
		return sdkSrcUrl;
	}

	public static void setSdkSrcUrl(String sdkSrcUrl) {
		log.debug("* * * Setting SDK Src URL Arg to: " + sdkSrcUrl);
		WorkbenchCmdLineArgsManager.sdkSrcUrl = sdkSrcUrl;
	}

	public static String getSdkSrcFile() {
		return sdkSrcFile;
	}

	public static void setSdkSrcFile(String sdkSrcFile) {
		WorkbenchCmdLineArgsManager.sdkSrcFile = sdkSrcFile;
	}
}
