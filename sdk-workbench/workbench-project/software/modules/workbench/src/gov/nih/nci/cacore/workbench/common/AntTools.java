package gov.nih.nci.cacore.workbench.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.listener.Log4jListener;

/**
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Daniel Dumitru</A>
 */
public class AntTools {
	
    private static final Logger log = Logger.getLogger(AntTools.class);

    public static void configureProject(String projectTemplateDirPath, String projectDirPath)
    throws BuildException {
		
		URL buildFileUrl = ResourceManager.getWorkbenchBuildFileUrl();
		
		String target = "configureProject";
		
		Map<String,String> propsMap=new TreeMap<String,String>();
		
		propsMap.put("skeleton.destination.dir", projectDirPath);
		propsMap.put("skeleton.project.template.dir", projectTemplateDirPath);
		
		executeAntProject(buildFileUrl,null,target,propsMap);

    }
    
    public static void copyModelFile(String projectDirPath, String modelFilePath)
    throws BuildException {
		
		URL buildFileUrl = ResourceManager.getWorkbenchBuildFileUrl();
		
		String target = "copyModelFile";
		
		Map<String,String> propsMap=new TreeMap<String,String>();
		
		propsMap.put("skeleton.destination.dir", projectDirPath);
		propsMap.put("skeleton.model.file", modelFilePath);
		propsMap.put("models.dir", "models");
		
		executeAntProject(buildFileUrl,projectDirPath,target,propsMap);
    }
    
    public static void copyCertKeyFiles(String projectDirPath,String targetGridDirPath,String certFilePath, String keyFilePath) throws BuildException {
		
		URL buildFileUrl = ResourceManager.getWorkbenchBuildFileUrl();
		
		String target = "copyCertKeyFiles";

		Map<String,String> propsMap=new TreeMap<String,String>();
		
		propsMap.put("skeleton.destination.dir", projectDirPath);
		propsMap.put("skeleton.target.grid.dir", targetGridDirPath);
		propsMap.put("skeleton.cert.file", certFilePath);
		propsMap.put("skeleton.key.file", keyFilePath);
		
		executeAntProject(buildFileUrl,projectDirPath,target,propsMap);

    }
    
    public static void copyDbSqlFile(String projectDirPath,String dbSqlDirPath, String dbSqlFilePath) throws BuildException {
		
		URL buildFileUrl = ResourceManager.getWorkbenchBuildFileUrl();
		
		String target = "copyDbSqlFile";

		Map<String,String> propsMap=new TreeMap<String,String>();
		
		propsMap.put("skeleton.destination.dir", dbSqlDirPath);
		propsMap.put("db.sql.file", dbSqlFilePath);
		
		executeAntProject(buildFileUrl,projectDirPath,target,propsMap);

    } 
    
    public static void generateApplication(String projectDirPath) throws BuildException {
		
		File buildFile = ResourceManager.getProjectBuildFile(projectDirPath);
		
		String target = "build:SDK";

		Map<String,String> propsMap=new TreeMap<String,String>();
		propsMap.put("install.properties", "");
		
		executeAntProject(buildFile,projectDirPath,target,propsMap);
    }
    
    
    public static void deployLocalApplication(String projectDirPath, String deployPropsFileName) throws BuildException {
		
		File buildFile = ResourceManager.getProjectBuildFile(projectDirPath);
		
		String target = "deploy:local:install:no-build";

		Map<String,String> propsMap=new TreeMap<String,String>();
		propsMap.put("properties.file", deployPropsFileName); //deployPropsFilePath.replace('\\', '/'));
		propsMap.put("properties.file.name", deployPropsFileName);
		propsMap.put("install.properties", deployPropsFileName);
		
		executeAntProject(buildFile,projectDirPath,target,propsMap);
    }
    
    public static void deployRemoteApplication(String projectDirPath, String deployPropsFileName) throws BuildException {
		
		File buildFile = ResourceManager.getProjectBuildFile(projectDirPath);
		
		String target = "deploy:remote:install:no-build";

		Map<String,String> propsMap=new TreeMap<String,String>();
		propsMap.put("properties.file", deployPropsFileName); //deployPropsFilePath.replace('\\', '/')
		propsMap.put("properties.file.name", deployPropsFileName);
		propsMap.put("install.properties", deployPropsFileName);
		
		executeAntProject(buildFile,projectDirPath,target,propsMap);

    }
    
    public static void installSdk(String sdkInstallDirPath) throws BuildException {
		
		URL buildFileUrl = ResourceManager.getWorkbenchBuildFileUrl();
		String workbenchType = WorkbenchCmdLineArgsManager.getWorkbenchType();

		Map<String,String> propsMap=new TreeMap<String,String>();
		propsMap.put("sdk.install.dir", sdkInstallDirPath);
		propsMap.put("workbench.type", WorkbenchCmdLineArgsManager.getWorkbenchType());

		String sdkSrcFile = WorkbenchCmdLineArgsManager.getSdkSrcFile();
		propsMap.put("sdk.dest.file", sdkSrcFile);
		
		if ("standalone".equalsIgnoreCase(workbenchType)){
			URL sdkSrcUrl = AntTools.class.getResource("/sdk-dist/" + sdkSrcFile);
			log.debug("* * * sdkSrcUrl: " + sdkSrcUrl);
			log.debug("* * * sdkSrcUrl path: " + sdkSrcUrl.getPath());
			
			propsMap.put("sdk.src.path", sdkSrcUrl.getPath());
		} else if ("webstart".equalsIgnoreCase(workbenchType)){
			propsMap.put("sdk.src.url", WorkbenchCmdLineArgsManager.getSdkSrcUrl());
		}
		
		String target = "install:sdk";

		executeAntProject(buildFileUrl,null,target,propsMap);
    }
    
    private static void executeAntProject(Object buildFile, String projectDirPath, String target, Map<String, String>propsMap) throws BuildException {
		Project proj = new Project();
		proj.init();
		proj.setSystemProperties();

//		DefaultLogger consoleLogger = new DefaultLogger();
//		consoleLogger.setErrorPrintStream(System.err);
//		consoleLogger.setOutputPrintStream(System.out);
//		consoleLogger.setMessageOutputLevel(Project.MSG_DEBUG);
//		proj.addBuildListener(consoleLogger);
		
	    for (Object obj: proj.getBuildListeners()) { 
	    	BuildListener bl = (BuildListener)obj;
	        proj.removeBuildListener(bl);
	    } 
	    
	    //Register Log4j build listener 
	    Log4jListener log4jListener = new Log4jListener();
	    proj.addBuildListener(log4jListener);
	    
	    //Register project XML Logger listener 
	    if (projectDirPath != null){
	    	File projectLogFile = null;
	    	
			try {
				projectLogFile = ResourceManager.getProjectLogFile(projectDirPath);
			} catch (IOException e) {
				log.error("ERROR: Could not create project log file: "+projectLogFile.getAbsoluteFile(),e);
				throw new BuildException(e);
			}
	    	log.debug("* * * projectLogFile path: "+projectLogFile.getAbsolutePath()+"; exists? "+(projectLogFile !=null && projectLogFile.exists()?true:false));
	    	
	    	PrintStream output = null;

		    try {
				output = new PrintStream(new FileOutputStream(projectLogFile));
			} catch (FileNotFoundException e) {
				log.error("ERROR:  Project log file not found: ",e);
				throw new BuildException(e);
			}
		    
//		    XmlLogger xmlLogger = new XmlLogger();
//		    xmlLogger.setOutputPrintStream(output);
		    DefaultLogger logFileLogger = new DefaultLogger();
		    logFileLogger.setOutputPrintStream(output);	
		    logFileLogger.setMessageOutputLevel(Project.MSG_INFO);
		    proj.addBuildListener(logFileLogger);
	    }

		try {
			proj.fireBuildStarted();
			
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			proj.addReference("ant.projectHelper", helper);
			
			// set any project properties
			if (propsMap != null)
			{
				Iterator<String> keys = propsMap.keySet().iterator();
				while (keys.hasNext())
				{
					String key = (String) keys.next();
					String value = (String) propsMap.get(key);
					proj.setUserProperty(key, value);
				}
			}

			helper.parse(proj, buildFile);
			
			//set default target
			proj.setDefault(target);
			
			if (buildFile instanceof URL)
				proj.setUserProperty("ant.file", ((URL)buildFile).getPath());
			else
				proj.setUserProperty("ant.file", ((File)buildFile).getPath());
			
			//Debug properties
			Iterator<String> keys = proj.getProperties().keySet().iterator();
			while (keys.hasNext())
			{
				String key = (String) keys.next();
				String value = (String) propsMap.get(key);
				log.debug("* * * ant project property: "+key+"="+value);
			}
			
			proj.executeTarget(proj.getDefaultTarget());
			proj.fireBuildFinished(null);
		} catch (BuildException e) {
			log.error("BuildException Error: ",e);
			proj.fireBuildFinished(e);
			throw e;
		}
    }

}
