package gov.nih.nci.cacore.workbench.common;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
import org.cagrid.grape.GridApplication;

public class ResourceManager {
	
	private static final Logger log = Logger.getLogger(ResourceManager.class);
	
    /**
     * Date format
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH.mm.ss.z";

	public final static String STATE_FILE = "cacore.workbench.state.properties";
	public final static String LAST_DIRECTORY = "cacore.workbench.lastdir";
	public final static String LAST_FILE = "cacore.workbench.lastfile";
	
	//Property File Constants
	private static final String WORKBENCH_DIR = "workbench-project/software";
	private static final String BUILD_DIR = "build";
	private static final String LOG_DIR = "logs";
	private static final String LOG_FILE = "workbench";
	private static final String BUILD_FILE = "build.xml";
	private static final String DB_SQL_DIR = "db/db-install";
	private static final String LOGS_DIR = "logs";	
	private static final String CODEGEN_PROPS_FILE = "codegen.properties";
	private static final String DEPLOY_PROPS_FILE = "install.properties";
	
	private static final String DEFAULT_CODEGEN_ADDITIONAL_PROPS_FILE = "codegen.properties.additional";
	private static final String DEFAULT_DEPLOY_PROPS_FILE = "deploy.properties.template";
	private static final String DEFAULT_JBOSS_DEPLOY_ADDITIONAL_PROPS_FILE = "deploy.properties.additional.jboss";	
	private static final String DEFAULT_TOMCAT_DEPLOY_ADDITIONAL_PROPS_FILE = "deploy.properties.additional.tomcat";
	
	public static WorkbenchPropertiesManager getCodegenPropertiesManager(String projectDirPath){
		WorkbenchPropertiesManager propsMgr=null;
		
		//initialize properties from existing project properties file, if it exists
		File projectDir = new File(projectDirPath);
		if (projectDir.exists()){
			File codegenPropsFile = getCodegenPropsFile(projectDirPath);

			if (codegenPropsFile.exists())
				propsMgr = new WorkbenchPropertiesManager(codegenPropsFile.getAbsolutePath());
		} 
		
        //if project properties file does not exist, use default workbench template properties
        if (propsMgr==null){
        	propsMgr = new WorkbenchPropertiesManager(DEFAULT_DEPLOY_PROPS_FILE);
        }
        
        return propsMgr;
	}
	
	public static WorkbenchPropertiesManager getDeployPropertiesManager(String projectDirPath){
		String remoteDeployEnv = "";
		return getDeployPropertiesManager(projectDirPath,remoteDeployEnv);
	}
	
	public static WorkbenchPropertiesManager getDeployPropertiesManager(String projectDirPath, String remoteDeployEnv){
		WorkbenchPropertiesManager propsMgr=null;
		
		//initialize properties from existing project properties file, if it exists
		File projectDir = new File(projectDirPath);
		if (projectDir.exists()){
			
			List<String> propsFiles = new ArrayList<String>();
			propsFiles.add(DEFAULT_DEPLOY_PROPS_FILE);

			File codegenPropsFile = getCodegenPropsFile(projectDirPath);
			File deployPropsFile = getDeployPropsFile(projectDirPath,remoteDeployEnv);

			if (codegenPropsFile.exists())
				propsFiles.add(codegenPropsFile.getAbsolutePath());
			
			if (deployPropsFile.exists())
				propsFiles.add(deployPropsFile.getAbsolutePath());
			
			propsMgr = new WorkbenchPropertiesManager((String[]) propsFiles.toArray(new String[propsFiles.size()]));
		}
        
        return propsMgr;
	}
	
	public static WorkbenchPropertiesManager getJbossAdditionalDeployPropertiesManager(){
		WorkbenchPropertiesManager propsMgr=null;

		List<String> propsFiles = new ArrayList<String>();
		propsFiles.add(DEFAULT_JBOSS_DEPLOY_ADDITIONAL_PROPS_FILE);

		propsMgr = new WorkbenchPropertiesManager((String[]) propsFiles.toArray(new String[propsFiles.size()]));

		return propsMgr;
	}
	
	public static WorkbenchPropertiesManager getTomcatAdditionalDeployPropertiesManager(){
		WorkbenchPropertiesManager propsMgr=null;

		List<String> propsFiles = new ArrayList<String>();
		propsFiles.add(DEFAULT_TOMCAT_DEPLOY_ADDITIONAL_PROPS_FILE);

		propsMgr = new WorkbenchPropertiesManager((String[]) propsFiles.toArray(new String[propsFiles.size()]));

		return propsMgr;
	}
	
	
	public static WorkbenchPropertiesManager getAdditionalCodegenPropertiesManager(){
		WorkbenchPropertiesManager propsMgr=null;

		List<String> propsFiles = new ArrayList<String>();
		propsFiles.add(DEFAULT_CODEGEN_ADDITIONAL_PROPS_FILE);

		propsMgr = new WorkbenchPropertiesManager((String[]) propsFiles.toArray(new String[propsFiles.size()]));

		return propsMgr;
	}
	
	public static File getCodegenPropsFile(String projectDirPath){
		return new File(projectDirPath + File.separator + BUILD_DIR + File.separator + CODEGEN_PROPS_FILE);
	}
	
	public static File getWorkbenchBuildDir(String sdkDirPath){
		File workbenchBuildDir = new File(sdkDirPath + File.separator  + WORKBENCH_DIR + File.separator + BUILD_DIR);
		log.debug("* * * workbenchBuildDir: " + workbenchBuildDir.getAbsolutePath());
		return workbenchBuildDir;
	}
	
	public static File getWorkbenchBuildFile(String sdkDirPath){
		File buildFile = new File(getWorkbenchBuildDir(sdkDirPath).getAbsolutePath()+ File.separator + BUILD_FILE);
		log.debug("* * * buildFile.getAbsolutePath(): " + buildFile.getAbsolutePath());
		return buildFile;
	}
	
	public static URL getWorkbenchBuildFileUrl(){
		URL buildFileUrl = AntTools.class.getResource("/build/build.xml");
		log.debug("* * * buildFileUrl: " + buildFileUrl);
		log.debug("* * * buildFileUrl Path: " + buildFileUrl.getPath());
		
		return buildFileUrl;
	}
	
	private static File getProjectBuildDir(String projectDirPath){
		File projectBuildDir = new File(projectDirPath + File.separator + BUILD_DIR);
		log.debug("* * * projectBuildDir: " + projectBuildDir.getAbsolutePath());
		return projectBuildDir;
	}
	
	public static File getProjectBuildFile(String projectDirPath){
		File buildFile = new File(getProjectBuildDir(projectDirPath).getAbsolutePath()+ File.separator + BUILD_FILE);
		log.debug("* * * projectBuildFile.getAbsolutePath(): " + buildFile.getAbsolutePath());
		return buildFile;
	}
	
	public static File getProjectLogFile(String projectDirPath) throws IOException {
		File logFile = new File(projectDirPath + File.separator + LOG_DIR + File.separator + LOG_FILE +"_"+DateUtils.format(System.currentTimeMillis(), DATE_FORMAT)+".log");
		log.debug("* * * projectLogFile.getAbsolutePath(): " + logFile.getAbsolutePath());
		
		logFile.createNewFile();
		
		return logFile;
	}

	public static File getDbSqlDir(String projectDirPath, String dbType){
		File dbSqlDir = new File(projectDirPath + File.separator + DB_SQL_DIR + File.separator + dbType.toLowerCase());
		log.debug("* * * dbSqlDir.getAbsolutePath(): " + dbSqlDir.getAbsolutePath());
		return dbSqlDir;
	}
	
	public static File getLogsDir(String projectDirPath){
		File logsDir = new File(projectDirPath + File.separator + LOGS_DIR +File.separator);
		
		return logsDir;
	}

	public static File getDeployPropsFile(String projectDirPath,String remoteDeployEnv){
		StringBuffer filePath = new StringBuffer();
		
		filePath.append(projectDirPath).append(File.separator).append(BUILD_DIR).append(File.separator);
		
		if (remoteDeployEnv !=null && remoteDeployEnv.length() >0)
			filePath.append(remoteDeployEnv).append("-");
		
		filePath.append(DEPLOY_PROPS_FILE);
		
		return new File(filePath.toString().replace('\\', '/'));
	}

	public static String promptDir(String defaultLocation, String dialogTitle) throws IOException {
		return promptDir(GridApplication.getContext().getApplication(), defaultLocation, dialogTitle);
	}

	public static String promptDir(Component owner, String defaultLocation, String dialogTitle) throws IOException {
		JFileChooser chooser = null;
		if ((defaultLocation != null) && (defaultLocation.length() > 0) && new File(defaultLocation).exists()) {
			chooser = new JFileChooser(new File(defaultLocation));
		} else if (getStateProperty(LAST_DIRECTORY) != null) {
			chooser = new JFileChooser(new File(getStateProperty(LAST_DIRECTORY)));
		} else {
			chooser = new JFileChooser();
		}
		chooser.setApproveButtonText("Open");
		chooser.setDialogTitle(dialogTitle);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		GridApplication.getContext().centerComponent(chooser);

		int returnVal = chooser.showOpenDialog(owner);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			setStateProperty(ResourceManager.LAST_DIRECTORY, chooser.getSelectedFile().getAbsolutePath());
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}
	

	public static String promptFile(String defaultLocation) throws IOException {
		return promptFile(GridApplication.getContext().getApplication(), defaultLocation, null);
	}

	public static String promptFile(String defaultLocation, FileFilter filter) throws IOException {
		return promptFile(GridApplication.getContext().getApplication(), defaultLocation, filter);
	}

	public static String promptFile(Component owner, String defaultLocation, FileFilter filter) throws IOException {
		String[] files = internalPromptFiles(owner, defaultLocation, filter, false, "Select File");
		if (files != null) {
			return files[0];
		}
		return null;
	}

	private static String[] internalPromptFiles(Component owner, String defaultLocation, FileFilter filter,
			boolean multiSelect, String title) throws IOException {
		String[] fileNames = null;
		JFileChooser chooser = null;
		if ((defaultLocation != null) && (defaultLocation.length() != 0) && new File(defaultLocation).exists()) {
			chooser = new JFileChooser(new File(defaultLocation));
		} else if (getStateProperty(LAST_FILE) != null) {
			chooser = new JFileChooser(new File(getStateProperty(LAST_FILE)));
		} else {
			chooser = new JFileChooser();
		}
		chooser.setApproveButtonText("Open");
		chooser.setApproveButtonToolTipText("Open");
		chooser.setMultiSelectionEnabled(multiSelect);
		chooser.setDialogTitle(title);
		if (filter!=null)
			chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		GridApplication.getContext().centerComponent(chooser);

		int choice = chooser.showOpenDialog(owner);
		if (choice == JFileChooser.APPROVE_OPTION) {
			File[] files = null;
			if (multiSelect) {
				files = chooser.getSelectedFiles();
			} else {
				files = new File[]{chooser.getSelectedFile()};
			}
			setStateProperty(ResourceManager.LAST_FILE, files[0].getAbsolutePath());
			fileNames = new String[files.length];
			for (int i = 0; i < fileNames.length; i++) {
				fileNames[i] = files[i].getAbsolutePath();
			}
		}
		return fileNames;
	}

	public static String promptDir(String defaultLocation) throws IOException {
		return promptDir(GridApplication.getContext().getApplication(), defaultLocation);
	}


	public static String promptDir(Component owner, String defaultLocation) throws IOException {
		JFileChooser chooser = null;
		if ((defaultLocation != null) && (defaultLocation.length() > 0) && new File(defaultLocation).exists()) {
			chooser = new JFileChooser(new File(defaultLocation));
		} else if (getStateProperty(LAST_DIRECTORY) != null) {
			chooser = new JFileChooser(new File(getStateProperty(LAST_DIRECTORY)));
		} else {
			chooser = new JFileChooser();
		}
		chooser.setApproveButtonText("Open");
		chooser.setDialogTitle("Select Directory");
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		GridApplication.getContext().centerComponent(chooser);

		int returnVal = chooser.showOpenDialog(owner);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			setStateProperty(ResourceManager.LAST_DIRECTORY, chooser.getSelectedFile().getAbsolutePath());
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return null;
	}

	public static String getStateProperty(String key) throws IOException {
		File lastDir = new File(getResourcePath() + File.separator + STATE_FILE);
		Properties properties = new Properties();
		if (!lastDir.exists()) {
			lastDir.createNewFile();
		}
		properties.load(new FileInputStream(lastDir));
		return properties.getProperty(key);
	}

	public static void setStateProperty(String key, String value) throws IOException {
		if (key != null) {
			File lastDir = new File(getResourcePath() + File.separator + STATE_FILE);
			if (!lastDir.exists()) {
				lastDir.createNewFile();
			}
			Properties properties = new Properties();
			properties.load(new FileInputStream(lastDir));
			properties.setProperty(key, value);
			properties.store(new FileOutputStream(lastDir), "");
		}
	}

	public static File getCacoreWorkbenchUserHome() {
		String userHome = System.getProperty("user.home");
		File userHomeF = new File(userHome);
		File cacoreWorkbenchCache = new File(userHomeF.getAbsolutePath() + File.separator + ".cacore.workbench_"
				+ getCacoreWorkbenchVersion().replace(".", "_"));
		if (!cacoreWorkbenchCache.exists()) {
			cacoreWorkbenchCache.mkdirs();
		}
		return cacoreWorkbenchCache;
	}

	public static String getResourcePath() {
		File cacoreWorkbenchCache = getCacoreWorkbenchUserHome();
		cacoreWorkbenchCache.mkdir();
		return cacoreWorkbenchCache.getAbsolutePath();
	}

	public static String getCacoreWorkbenchVersion() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.WORKBENCH_VERSION_PROPERTY);
	}
	
	public static String getWorkbenchHelpUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.WORKBENCH_HELP_URL);
	}
	
	public static String getWorkbenchToolsSiteUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.WORKBENCH_TOOLS_SITE_URL);
	}
	
	public static String getSdkGuideUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.SDK_GUIDE_URL);
	}	
	
	public static String getWorkbenchGuideUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.WORKBENCH_GUIDE_URL);
	}	
	
	public static String getLaunchIntroduceUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.LAUNCH_INTRODUCE_URL);
	}
	
	public static String getArgoUmlWebStartUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.LAUNCH_ARGO_UML_WEB_START_URL);
	}
	
	public static String getEaDownloadUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.DOWNLOAD_ENTERPRISE_ARCHITECT_URL);
	}
	
	public static String getLaunchSiwUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.LAUNCH_SIW_URL);
	}
	
	public static String getLaunchCdeBrowserUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.LAUNCH_CDE_BROWSER_URL);
	}
	
	public static String getLaunchUmlModelBrowserUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.LAUNCH_UML_MODEL_BROWSER_URL);
	}
	
	public static String getLaunchCdeCurationToolBrowserUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.LAUNCH_CDE_CURATION_TOOL_URL);
	}
	
	public static String getLaunchCaAdapterUrl() {
		return getCacoreWorkbenchPropertyValue(CacoreWorkbenchConstants.LAUNCH_CAADAPTER_URL);
	}
	
	public static String getCacoreWorkbenchPropertyValue(String propertyKey) {
		//purposely always reload properties so that any changes are loaded as well
		Properties engineProps = new Properties();
		try {
			InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(CacoreWorkbenchConstants.WORKBENCH_ENGINE_PROPERTIES);
			
			engineProps.load(is);
			return engineProps.getProperty(propertyKey);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
