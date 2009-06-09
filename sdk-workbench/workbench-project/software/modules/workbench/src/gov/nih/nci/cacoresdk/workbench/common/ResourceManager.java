package gov.nih.nci.cacoresdk.workbench.common;

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
import org.cagrid.grape.GridApplication;

public class ResourceManager {
	
	private static final Logger log = Logger.getLogger(ResourceManager.class);

	public final static String STATE_FILE = "sdk.workbench.state.properties";
	public final static String LAST_DIRECTORY = "sdk.workbench.lastdir";
	public final static String LAST_FILE = "sdk.workbench.lastfile";
	
	//Property File Constants
	private static final String WORKBENCH_DIR = "workbench-project/software";
	private static final String BUILD_DIR = "build";
	private static final String BUILD_FILE = "build.xml";
	private static final String CODEGEN_PROPS_FILE = "codegen.properties";
	private static final String DEPLOY_PROPS_FILE = "install.properties";
	
	private static final String DEFAULT_PROPS_FILE = "deploy.properties.template";
	private static final String DEFAULT_PROPS_ADDITIONAL_FILE = "deploy.properties.additional";
	
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
        	propsMgr = new WorkbenchPropertiesManager(DEFAULT_PROPS_FILE);
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
			propsFiles.add(DEFAULT_PROPS_FILE);

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
	
	public static WorkbenchPropertiesManager getAdditionalDeployPropertiesManager(){
		WorkbenchPropertiesManager propsMgr=null;

		List<String> propsFiles = new ArrayList<String>();
		propsFiles.add(DEFAULT_PROPS_ADDITIONAL_FILE);

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

	public static File getSdkWorkbenchUserHome() {
		String userHome = System.getProperty("user.home");
		File userHomeF = new File(userHome);
		File sdkWorkbenchCache = new File(userHomeF.getAbsolutePath() + File.separator + ".sdk.workbench_"
				+ getSdkWorkbenchVersion().replace(".", "_"));
		if (!sdkWorkbenchCache.exists()) {
			sdkWorkbenchCache.mkdirs();
		}
		return sdkWorkbenchCache;
	}

	public static String getResourcePath() {
		File sdkWorkbenchCache = getSdkWorkbenchUserHome();
		sdkWorkbenchCache.mkdir();
		return sdkWorkbenchCache.getAbsolutePath();
	}

	public static String getSdkWorkbenchVersion() {
		return getSdkWorkbenchPropertyValue(SdkWorkbenchConstants.SDK_WORKBENCH_VERSION_PROPERTY);
	}

	public static String getSdkWorkbenchPropertyValue(String propertyKey) {
		Properties engineProps = new Properties();
		try {
			InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(SdkWorkbenchConstants.SDK_WORKBENCH_ENGINE_PROPERTIES);
			
			engineProps.load(is);
			return engineProps.getProperty(propertyKey);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
