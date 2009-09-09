package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.AntTools;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.WorkbenchPropertiesManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.cagrid.grape.ApplicationComponent;
import org.cagrid.grape.GridApplication;
import org.cagrid.grape.utils.BusyDialog;
import org.cagrid.grape.utils.BusyDialogRunnable;

/** 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created November 10, 2008
 */
public abstract class WorkbenchViewerBaseComponent extends ApplicationComponent {
	
	private static final Logger log = Logger.getLogger(WorkbenchViewerBaseComponent.class);
	
	final private BusyDialog dialog = new BusyDialog(GridApplication.getContext().getApplication(), "");

	protected WorkbenchPropertiesManager propsMgr = null;
	
	/**
	 * Will save the code generation properties to be used during subsequent 
	 * application generation
	 * 
	 * @param sdkDirPath
	 *            the path to the SDK install home directory
	 * @param projectDirPath
	 *            the path to the project generation directory
	 * @param workbenchPropsMap
	 *            a map of the properties to be used during the 
	 *            project application generation
	 */
	public boolean saveCodegenProperties(final String sdkDirPath,
			final String projectTemplateDirPath,
			final String projectDirPath,
			final String modelFilePath, 
			final Map<String,String> workbenchPropsMap) {
		
		int doIProceedResult = JOptionPane.OK_OPTION;
		final File projectDir = new File(projectDirPath);

		if (projectDir.exists() && projectDir.list().length != 0) {
			doIProceedResult = JOptionPane.NO_OPTION;
//			File buildScript = new File(projectDir.getAbsolutePath() + File.separator + "build.xml");
			File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);
			
//			log.debug("* * * buildScript: " + buildScript.getAbsolutePath());
			log.debug("* * * codegenPropsFile: " + codegenPropsFile.getAbsolutePath());
			
			//if (buildScript.exists() && codegenPropsFile.exists()) {
			if (codegenPropsFile.exists()) {
				doIProceedResult = JOptionPane
				.showConfirmDialog(
						this,
						"The project generation directory (" + projectDirPath + ") is not empty.\n"
						+"Any existing code generation property information in the directory will\n"
						+"be overwritten.\n"
						+"\n"
						+"Proceed?",
						"Confirm Overwrite", 
						JOptionPane.YES_NO_OPTION);
			} else {
				doIProceedResult = JOptionPane.OK_OPTION;
			}
		} 

		if (doIProceedResult != JOptionPane.OK_OPTION) {
			return false;
		} 

		if (doIProceedResult == JOptionPane.OK_OPTION) {
			final File sdkDirFile = new File(sdkDirPath);
			final File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);

			//WorkbenchViewerBaseComponent.this.setVisible(false);
			//dispose();
			
			try {
				if (!sdkDirFile.exists()) {
					setErrorMessage(generateErrorMsg("The specified SDK installation home directory does not exist: "+sdkDirPath));
					return false;
				} else {
					installSdk(sdkDirPath,projectTemplateDirPath);
				}
				
				//setProgressText("Configuring Project Directory");
				
				try {
					AntTools.configureProject(projectTemplateDirPath, projectDirPath);
				} catch (BuildException e) {
					log.error("ERROR: "+ e.getMessage(),e);
					setErrorMessage(generateErrorMsg("Failed to create and configure the Project Generation Directory." + e.getMessage()));
					return false;
				}
				
				try {
					AntTools.copyModelFile(projectDirPath, modelFilePath);
				} catch (BuildException e) {
					log.error("ERROR: "+ e.getMessage(),e);
					setErrorMessage(generateErrorMsg(e,"Failed to copy the model file to the Project Generation 'models' sub-directory!"));
					return false;
				}

				//setProgressText("Saving Properties");

				if (!saveProperties(codegenPropsFile,workbenchPropsMap)){
					setErrorMessage(generateErrorMsg("Failed to save the code generation workbench properties to: "+codegenPropsFile.getAbsolutePath()));
					return false;
				}
			
				setErrorMessage("Properties Successfully Saved");

			} catch (Exception e) {
				log.error("ERROR: "+ e.getMessage(),e);
				setErrorMessage(generateErrorMsg(e));

				return false;
			}

//			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Saving Code Generation Properties") {
//				@Override
//				public void process() {
//					try {
//						if (!sdkDirFile.exists()) {
//							setErrorMessage(generateErrorMsg("The specified SDK installation home directory does not exist: "+sdkDirPath));
//							return;
//						} else {
//							installSdk(sdkDirPath,projectTemplateDirPath);
//						}
//						
//						setProgressText("Configuring Project Directory");
//						
//						try {
//							AntTools.configureProject(projectTemplateDirPath, projectDirPath);
//						} catch (BuildException e) {
//							log.error("ERROR: "+ e.getMessage(),e);
//							setErrorMessage(generateErrorMsg("Failed to create and configure the Project Generation Directory." + e.getMessage()));
//							return;
//						}
//						
//						try {
//							AntTools.copyModelFile(projectDirPath, modelFilePath);
//						} catch (BuildException e) {
//							log.error("ERROR: "+ e.getMessage(),e);
//							setErrorMessage(generateErrorMsg(e,"Failed to copy the model file to the Project Generation 'models' sub-directory!"));
//							return;
//						}
//
//						setProgressText("Saving Properties");
//
//						if (!saveProperties(codegenPropsFile,workbenchPropsMap)){
//							setErrorMessage(generateErrorMsg("Failed to save the code generation workbench properties to: "+codegenPropsFile.getAbsolutePath()));
//							return;
//						}
//					
//						setErrorMessage("Properties Successfully Saved");
//
//					} catch (Exception e) {
//						log.error("ERROR: "+ e.getMessage(),e);
//						setErrorMessage(generateErrorMsg(e));
//
//						return;
//					}
//				}
//			};
//
//			Thread th = new Thread(r);
//			th.start();
			
			log.debug("Completed saving codegen properties.");
		}
		return true;
	}

	/**
	 * Will save the deploy properties to be used during subsequent 
	 * application generation and deployment
	 * 
	 * @param sdkDirPath
	 *            the path to the SDK install home directory
	 * @param projectDirPath
	 *            the path to the project generation directory
	 * @param workbenchPropsMap
	 *            a map of the properties to be used during the 
	 *            project application generation
	 */
	public boolean saveDeployProperties(final String sdkDirPath,
			final String projectTemplateDirPath,
			final String projectDirPath,
			final String remoteDeployEnv,
			final String targetGridDirPath,
			final String certFilePath,
			final String keyFilePath,
			final String dbType,
			final String dbSqlFilePath,
			final String csmDbSqlFilePath,
			final String clmDbSqlFilePath,
			final Map<String,String> workbenchPropsMap) {
		
		int doIProceedResult = JOptionPane.OK_OPTION;
		final File projectDir = new File(projectDirPath);

		if (projectDir.exists() && projectDir.list().length != 0) {
			doIProceedResult = JOptionPane.NO_OPTION;
//			File buildScript = new File(projectDir.getAbsolutePath() + File.separator + "build.xml");
			
			final File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);
			
			//if (buildScript.exists() && codegenPropsFile.exists()) {
			if (codegenPropsFile.exists()) {
				doIProceedResult = JOptionPane
				.showConfirmDialog(
						this,
						"The project generation directory (" + projectDirPath + ") is not\n"
						+"empty.  Any existing deployment property information in the directory will\n"
						+"be overwritten.\n"
						+"\n"
						+"Proceed?",
						"Confirm Overwrite", 
						JOptionPane.YES_NO_OPTION);
			} else {
				doIProceedResult = JOptionPane.OK_OPTION;
			}
		} 

		if (doIProceedResult != JOptionPane.OK_OPTION) {
			return false;
		} 
		
		if (doIProceedResult == JOptionPane.OK_OPTION) {
			final File sdkDirFile = new File(sdkDirPath);
			final File deployPropsFile = ResourceManager.getDeployPropsFile(projectDirPath,remoteDeployEnv);		
			final File certFile = new File(certFilePath);
			final File keyFile = new File(keyFilePath);
			final File destDbSqlDir = ResourceManager.getDbSqlDir(projectDirPath, dbType);
			final File dbSqlFile = new File(dbSqlFilePath);
			final File csmDbSqlFile = new File(csmDbSqlFilePath);
			final File clmDbSqlFile = new File(clmDbSqlFilePath);

			//WorkbenchViewerBaseComponent.this.setVisible(false);
			//dispose();
			
			try {
				if (!sdkDirFile.exists()) {
					setErrorMessage(generateErrorMsg("The specified SDK installation home directory does not exist: "+sdkDirPath));
					return false;
				} else {
					//setProgressText("Installing SDK Instance");
					installSdk(sdkDirPath,projectTemplateDirPath);
				}
				
				try {
					//setProgressText("Configuring Project");
					AntTools.configureProject(projectTemplateDirPath, projectDirPath);
				} catch (BuildException e) {
					log.error("ERROR: "+ e.getMessage(),e);
					setErrorMessage(generateErrorMsg("Failed to create and configure the Project Generation Directory." + e.getMessage()));
					return false;
				}

				if (certFile.exists() && keyFile.exists()){
					try {
						//setProgressText("Copying certificate files to default project directories");
						AntTools.copyCertKeyFiles(projectDirPath, targetGridDirPath, certFilePath, keyFilePath);
					} catch (BuildException e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage(generateErrorMsg(e,"Failed to copy the caGrid Security Certificate and Key files to the Project Generation " + targetGridDirPath +" sub-directory!"));
						return false;
					}
				}

				if (dbSqlFile.exists()){
					try {
						//setProgressText("Copying Application DB SQL file to default project directories");
						AntTools.copyDbSqlFile(projectDirPath, destDbSqlDir.getAbsolutePath(), dbSqlFilePath);
					} catch (BuildException e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage(generateErrorMsg(e,"Failed to copy the Database SQL file to the Project Generation " + destDbSqlDir.getAbsolutePath() +" sub-directory!"));								
						return false;
					}
				}
				
				if (csmDbSqlFile.exists()){
					try {
						//setProgressText("Copying CSM DB SQL file to default project directories");
						AntTools.copyDbSqlFile(projectDirPath, destDbSqlDir.getAbsolutePath(), csmDbSqlFilePath);
					} catch (BuildException e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage(generateErrorMsg(e,"Failed to copy the CSM Database SQL file to the Project Generation " + destDbSqlDir.getAbsolutePath() +" sub-directory!"));								
						return false;
					}
				}
				
				if (clmDbSqlFile.exists()){
					try {
						//setProgressText("Copying CLM DB SQL file to default project directories");
						AntTools.copyDbSqlFile(projectDirPath, destDbSqlDir.getAbsolutePath(), clmDbSqlFilePath);
					} catch (BuildException e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage(generateErrorMsg(e,"Failed to copy the CLM Database SQL file to the Project Generation " + destDbSqlDir.getAbsolutePath() +" sub-directory!"));								
						return false;
					}
				}

				//setProgressText("Saving Properties");
				if (!saveProperties(deployPropsFile,workbenchPropsMap)){
					setErrorMessage(generateErrorMsg("Failed to save the deployment properties to: "+deployPropsFile.getAbsolutePath()));
					return false;
				}
				
				setErrorMessage("Properties Successfully Saved");
				
			} catch (Exception e) {
				log.error("ERROR: "+ e.getMessage(),e);
				setErrorMessage(generateErrorMsg(e));
				return false;
			}
		}


//			===========

//			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Saving Deployment Properties") {
//				@Override
//				public void process() {
//					try {
//						if (!sdkDirFile.exists()) {
//							setErrorMessage(generateErrorMsg("The specified SDK installation home directory does not exist: "+sdkDirPath));
//							return;
//						} else {
//							installSdk(sdkDirPath,projectTemplateDirPath,this);
//						}
//						
//						try {
//							AntTools.configureProject(projectTemplateDirPath, projectDirPath);
//						} catch (BuildException e) {
//							log.error("ERROR: "+ e.getMessage(),e);
//							setErrorMessage(generateErrorMsg("Failed to create and configure the Project Generation Directory." + e.getMessage()));
//							return;
//						}
//
//						if (certFile.exists() && keyFile.exists()){
//							try {
//								AntTools.copyCertKeyFiles(projectDirPath, targetGridDirPath, certFilePath, keyFilePath);
//							} catch (BuildException e) {
//								log.error("ERROR: "+ e.getMessage(),e);
//								setErrorMessage(generateErrorMsg(e,"Failed to copy the caGrid Security Certificate and Key files to the Project Generation " + targetGridDirPath +" sub-directory!"));
//								return;
//							}
//						}
//
//						if (dbSqlFile.exists()){
//							try {
//								AntTools.copyDbSqlFile(projectDirPath, destDbSqlDir.getAbsolutePath(), dbSqlFilePath);
//							} catch (BuildException e) {
//								log.error("ERROR: "+ e.getMessage(),e);
//								setErrorMessage(generateErrorMsg(e,"Failed to copy the Database SQL file to the Project Generation " + destDbSqlDir.getAbsolutePath() +" sub-directory!"));								
//								return;
//							}
//						}
//						
//						if (csmDbSqlFile.exists()){
//							try {
//								AntTools.copyDbSqlFile(projectDirPath, destDbSqlDir.getAbsolutePath(), csmDbSqlFilePath);
//							} catch (BuildException e) {
//								log.error("ERROR: "+ e.getMessage(),e);
//								setErrorMessage(generateErrorMsg(e,"Failed to copy the CSM Database SQL file to the Project Generation " + destDbSqlDir.getAbsolutePath() +" sub-directory!"));								
//								return;
//							}
//						}
//						
//						if (clmDbSqlFile.exists()){
//							try {
//								AntTools.copyDbSqlFile(projectDirPath, destDbSqlDir.getAbsolutePath(), clmDbSqlFilePath);
//							} catch (BuildException e) {
//								log.error("ERROR: "+ e.getMessage(),e);
//								setErrorMessage(generateErrorMsg(e,"Failed to copy the CLM Database SQL file to the Project Generation " + destDbSqlDir.getAbsolutePath() +" sub-directory!"));								
//								return;
//							}
//						}
//
//						setProgressText("Saving Properties");
//
//						if (!saveProperties(deployPropsFile,workbenchPropsMap)){
//							setErrorMessage(generateErrorMsg("Failed to save the deployment properties to: "+deployPropsFile.getAbsolutePath()));
//							return;
//						}
//						
//						setErrorMessage("Properties Successfully Saved");
//						
//					} catch (Exception e) {
//						log.error("ERROR: "+ e.getMessage(),e);
//						setErrorMessage(generateErrorMsg(e));
//						return;
//					}
//				}
//			};
//
//			Thread th = new Thread(r);
//			th.start();
//
//		}

		return true;
	}
	
	/**
	 * Will call the generate application engine component to generate a caCORE
	 * like application using the supplied deploy settings and an SDK 4.1 or newer
	 * installation
	 * 
	 * @param sdkInstallDirPath
	 *            the path to the SDK install home directory
	 * @param projectDirPath
	 *            the path to the project generation directory
	 * @param workbenchPropsMap
	 *            a map of the properties to be used during the 
	 *            project application generation
	 */
	public void installSdk(final String sdkInstallDirPath, final String projectTemplateDirPath) throws Exception {
		
		final File sdkInstallDir = new File(sdkInstallDirPath);
		final File projectTemplateDir = new File(projectTemplateDirPath);
		
		if (!sdkInstallDir.exists()) {
			log.error("The specified SDK installation home directory does not exist and needs to be created: "+sdkInstallDirPath+".  Unable to install an instance of the SDK.");
			return;
		}

		if (sdkInstallDir.list().length == 0 || !projectTemplateDir.exists()) {
			JOptionPane.showMessageDialog(
					this,
					"The SDK installation home directory (" + sdkInstallDirPath + ") does not have a\n"
					+ "valid SDK instance.  One will now be installed.\n");
		} else {
			log.info("The specified SDK installation home directory already exists and is not empty: "+sdkInstallDirPath+".  Skipping the 'Install SDK' step.");
			return;
		}

		try {
			//setProgressText("Installing the SDK");
			AntTools.installSdk(sdkInstallDirPath);
			setErrorMessage("The SDK has been successfully installed");
		} catch (Exception e) {
			log.error("ERROR: "+ e.getMessage(),e);
			setErrorMessage(generateErrorMsg("Failed to install the SDK!"));
			throw e;
		}
	}
	
	/**
	 * Will call the generate application engine component to generate a caCORE
	 * like application using the supplied deploy settings and an SDK 4.1 or newer
	 * installation
	 * 
	 * @param sdkDirPath
	 *            the path to the SDK install home directory
	 * @param projectDirPath
	 *            the path to the project generation directory
	 * @param workbenchPropsMap
	 *            a map of the properties to be used during the 
	 *            project application generation
	 */
	public void generateApplication(final String sdkDirPath,
			final String projectTemplateDirPath,
			final String projectDirPath) {
		
		int doIProceedResult = JOptionPane.NO_OPTION;
		final File projectDir = new File(projectDirPath);

		if (projectDir.exists()) {
//			File buildScript = new File(projectDir.getAbsolutePath() + File.separator
//					+ "build.xml");
			
			File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);
			
//			if (buildScript.exists() && codegenPropsFile.exists()) {
			if (codegenPropsFile.exists()) {
				doIProceedResult = JOptionPane
				.showConfirmDialog(
						this,
						"The project generation directory (" + projectDirPath + ") is not empty.\n"
						+"Any previously generated artifacts will be overwritten.\n"
						+"\n"
						+"Proceed?",
						"Confirm Overwrite", 
						JOptionPane.YES_NO_OPTION);
			} else {
				JOptionPane
				.showMessageDialog(
						this,
						"The project generation directory ("+ projectDirPath+ ")\n"
						+"does not appear to be a caCORE Workbench generated directory.  You \n"
						+"must specify a different directory that already contains the \n"
						+"code generation properties.");
			}
		} else {
			JOptionPane
			.showMessageDialog(
					this,
					"The specified project generation directory ("+ projectDirPath+ ")\n"
					+"does not exist.  You must specify a different directory that already \n"
					+"contains the code generation properties.");
		}

		if (doIProceedResult == JOptionPane.OK_OPTION) {
			final File sdkDirFile = new File(sdkDirPath);		

			WorkbenchViewerBaseComponent.this.setVisible(false);
			dispose();
//			
//			try {
//				if (!sdkDirFile.exists()) {
//					setErrorMessage(generateErrorMsg("The specified SDK installation home directory does not exist: "+sdkDirPath));
//					return;
//				} else {
//					installSdk(sdkDirPath,projectTemplateDirPath);
//				}
//				
//				//setProgressText("Configuring Project Directory");
//				
//				try {
//					AntTools.configureProject(projectTemplateDirPath, projectDirPath);
//				} catch (BuildException e) {
//					log.error("ERROR: "+ e.getMessage(),e);
//					setErrorMessage(generateErrorMsg("Failed to create and configure the Project Generation Directory." + e.getMessage()));
//					return;
//				}
//
//				setProgressText("Generating the Application");
//				
//				try {
//					AntTools.generateApplication(projectDirPath);
//				} catch (BuildException e) {
//					log.error("ERROR: "+ e.getMessage(),e);
//					setErrorMessage(generateErrorMsg(e,"Failed to generate the application!"));
//					return;
//				}
//			
//				setErrorMessage("Application Successfully Generated");
//
//			} catch (Exception e) {
//				log.error("ERROR: "+ e.getMessage(),e);
//				setErrorMessage(generateErrorMsg(e));
//				return;
//			}			
			
			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Generating") {
				@Override
				public void process() {
					try {
						if (!sdkDirFile.exists()) {
							setErrorMessage(generateErrorMsg("The specified SDK installation home directory does not exist: "+sdkDirPath));
							return;
						} else {
							installSdk(sdkDirPath,projectTemplateDirPath);
						}
						
						setProgressText("Configuring Project Directory");
						
						try {
							AntTools.configureProject(projectTemplateDirPath, projectDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage(generateErrorMsg("Failed to create and configure the Project Generation Directory." + e.getMessage()));
							return;
						}

						setProgressText("Generating the Application");
						
						try {
							AntTools.generateApplication(projectDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage(generateErrorMsg(e,"Failed to generate the application!"));
							return;
						}
					
						setErrorMessage("Application Successfully Generated");

					} catch (Exception e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage(generateErrorMsg(e));
						return;
					}
				}
			};

			Thread th = new Thread(r);
			th.start();

		}
	}
	
	/**
	 * Will call the 'Deploy Application' engine component to deploy a caCORE
	 * like application using the supplied deploy settings and an SDK 4.2 or newer
	 * installation
	 * 
	 * @param sdkDirPath
	 *            the path to the SDK install home directory
	 * @param projectDirPath
	 *            the path to the project generation directory
	 * @param workbenchPropsMap
	 *            a map of the properties to be used during the 
	 *            project application generation
	 */
	public void deployApplication(final String sdkDirPath,
			final String projectTemplateDirPath,
			final String projectDirPath,
			final String remoteDeployEnv) {
		
		int doIProceedResult = JOptionPane.NO_OPTION;
		final File projectDir = new File(projectDirPath);

		if (projectDir.exists()) {
//			File buildScript = new File(projectDir.getAbsolutePath() + File.separator+ "build.xml");
			
			File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);
			File deployPropsFile = ResourceManager.getDeployPropsFile(projectDirPath,remoteDeployEnv);
			
//			if (!(buildScript.exists() && deployPropsFile.exists() && codegenPropsFile.exists())) {
			if (!(deployPropsFile.exists() && codegenPropsFile.exists())) {
				JOptionPane
				.showMessageDialog(
						this,
						"The project generation directory ("+ projectDirPath+ ")\n"
						+"does not appear to be a caCORE Workbench generated project directory. \n"
						+"Please specify a directory that already contains both the code generation\n"
						+"and deployment properties.");
			} else {
				doIProceedResult = JOptionPane.OK_OPTION;
			}
		} else {
			JOptionPane
			.showMessageDialog(
					this,
					"The specified project generation directory ("+ projectDirPath+ ")\n"
					+"does not exist.  You must specify a different directory that already \n"
					+"contains the code generation properties.");
		}

		if (doIProceedResult == JOptionPane.OK_OPTION) {
			final File sdkDirFile = new File(sdkDirPath);	
			final File deployPropsFile = ResourceManager.getDeployPropsFile(projectDirPath,remoteDeployEnv);

			WorkbenchViewerBaseComponent.this.setVisible(false);
			dispose();

			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Deploying") {
				@Override
				public void process() {
					try {
						if (!sdkDirFile.exists()) {
							setErrorMessage(generateErrorMsg("The specified SDK installation home directory does not exist: "+sdkDirPath));
							return;
						} else {
							installSdk(sdkDirPath,projectTemplateDirPath);
						}
						
						setProgressText("Configuring Project Directory");
						
						try {
							AntTools.configureProject(projectTemplateDirPath, projectDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage(generateErrorMsg("Failed to create and configure the Project Generation Directory." + e.getMessage()));
							return;
						}

						setProgressText("Deploying the Application");
						
						try {
							if (remoteDeployEnv == null || remoteDeployEnv.length()==0){//local deployment
								AntTools.deployLocalApplication(projectDirPath, deployPropsFile.getName());  //getAbsolutePath()
							} else {
								AntTools.deployRemoteApplication(projectDirPath, deployPropsFile.getName()); //getAbsolutePath()
							}
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage(generateErrorMsg(e,"Failed to deploy the application."));
							return;
						}
					
						setErrorMessage("Application Successfully Deployed");

					} catch (Exception e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage(generateErrorMsg(e));
						return;
					}
				}
			};

			Thread th = new Thread(r);
			th.start();

		}
	}
	
	public boolean saveProperties(File workbenchPropsFile, Map<String,String>workbenchPropsMap){
		
		try {
			log.debug("* * * About to save properties to: " + workbenchPropsFile.getAbsolutePath());
			FileWriter workbenchPropsWriter = new FileWriter(workbenchPropsFile);
			
			if (workbenchPropsMap != null)
			{
				Iterator<String> keys = workbenchPropsMap.keySet().iterator();
				while (keys.hasNext())
				{
					String key = (String) keys.next();
					String value = (String) workbenchPropsMap.get(key);

					log.debug("* * * Saving property "+key+"="+value);
					workbenchPropsWriter.write(key+"="+value+"\n");
				}
			}
			
			workbenchPropsWriter.close();
		} catch (IOException e) {
			log.error("ERROR: "+ e.getMessage(),e);
			return false;
		}
		
		return true;
	}
	
	public void testDbConnection(String dbType, String dbURL, String username, String password) {
		try {
			
			log.debug("* * * Testing Connection");
			log.debug("* * * DB Type: "+dbType);
			log.debug("* * * DB URL: "+dbURL);
			log.debug("* * * DB Username: "+username);
			log.debug("* * * DB Password: "+password);
			
			if ("MySQL".equalsIgnoreCase(dbType)){
				// Load the MySql driver
				Class.forName("org.gjt.mm.mysql.Driver");
			} else if ("Oracle".equalsIgnoreCase(dbType)){
				// Load the Oracle driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
			} else {
				// report error
				JOptionPane.showMessageDialog(
						this,
						"Database Connection Test failed: invalid Database Type selected (must be 'Oracle' or 'MySql')\n"+
						dbType);
			}

//			// Enable logging
//			DriverManager.setLogWriter(new PrintWriter((System.err)));

			log.debug("Getting Connection");
			Connection conn = DriverManager.getConnection(dbURL, username, password);

			// If a SQLWarning object is available, print its
			// warning(s). There may be multiple warnings chained.

			SQLWarning warn = conn.getWarnings();
			if (warn != null){
				StringBuilder sb = new StringBuilder("");
				while (warn != null) {
					log.debug("SQLState: " + warn.getSQLState());
					log.debug("Message:  " + warn.getMessage());
					log.debug("Vendor:   " + warn.getErrorCode());
					log.debug("");
					
					sb.append("SQLState: " + warn.getSQLState()+"\n");
					sb.append("SQLState: " + warn.getSQLState()+"\n");
					sb.append("Message:  " + warn.getMessage()+"\n");
					sb.append("Vendor:   " + warn.getErrorCode()+"\n");
					sb.append("\n");

					warn = warn.getNextWarning();
				}
				
				JOptionPane.showMessageDialog(
						this,
						"Database Connection Test failed with the following warnings:\n"+
						sb.toString());
			}

			conn.close(); 
			
			//Test is successful
			JOptionPane.showMessageDialog(
					this,
					"Database Connection Test was successful.");

		} catch (ClassNotFoundException e) {
			log.error("Testing DB Connection: Can't load driver " + e);
			JOptionPane.showMessageDialog(
					this,
					"Database Connection Test failed: Can't load driver " + e.getMessage());
		} catch (SQLException e) {
			log.error("Testing DB Connection: SQL Exception: " + e);
			JOptionPane.showMessageDialog(
					this,
					"Database Connection Test failed: SQL Exception: " + e.getMessage());
		}
	}
	
	private String generateErrorMsg(Exception e){
		return "Error: " + e.getMessage() +".\n  See log file(s) within 'logs' sub-directory of Project Generation directory for more details.";
	}
	
	private String generateErrorMsg(Exception e, String errorMsg){
		return "Error: " + errorMsg+ ": " + e.getMessage() +".\n  See log file(s) within 'logs' sub-directory of Project Generation directory for more details.";
	}
	
	private String generateErrorMsg(String errorMsg){
		return "Error: " + errorMsg;
	}
	
	private void setProgressText(String text){
		final String displayText = text;
		
//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//        		dialog.getProgress().setIndeterminate(true);
//        		dialog.getProgress().setString(displayText);
//        		dialog.setVisible(true);
//            }
//        });
//        thread.start();
//		
//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//            	dialog.getProgress().setIndeterminate(true);
//        		dialog.getProgress().setString(displayText);
//        		dialog.setVisible(true);
//            }
//        });
//        thread.start();
//        
//        SwingUtilities.invokeLater(new Runnable() {
//        	public void run() {
//            	dialog.getProgress().setIndeterminate(true);
//        		dialog.getProgress().setString(displayText);
//        	}
//        });
//
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			log.error(e);
//		}
		
//        dialog.validate();
//        this.validate();
//        this.dialog.getOwner().validate();
//        
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//            	dialog.getProgress().setIndeterminate(true);
//            }
//        });
//        
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//        		dialog.getProgress().setString(displayText);
//            }
//        });
		
		
		BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "") {
			
			@Override
			public void process() {
				setProgressText(displayText);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					log.error(e);
				}
			}
		};

		Thread th = new Thread(r);
		th.start();
	}
	
	private void setErrorMessage(String message){
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//        		dialog.getProgress().setIndeterminate(false);
//        		dialog.dispose();
//            }
//        });
		
		JOptionPane.showMessageDialog(this, message);
		
//		
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                dialog.dispose();
//            }
//        });
	}

}
