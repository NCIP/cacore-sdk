package gov.nih.nci.cacoresdk.workbench.portal.viewer;

import gov.nih.nci.cacoresdk.workbench.common.AntTools;
import gov.nih.nci.cacoresdk.workbench.common.ResourceManager;
import gov.nih.nci.cacoresdk.workbench.common.WorkbenchPropertiesManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.tools.ant.BuildException;
import org.cagrid.grape.ApplicationComponent;
import org.cagrid.grape.GridApplication;
import org.cagrid.grape.utils.BusyDialogRunnable;

/** 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created November 10, 2008
 */
public abstract class WorkbenchViewerBaseComponent extends ApplicationComponent {
	
	private static final Logger log = Logger.getLogger(WorkbenchViewerBaseComponent.class);

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
	public void saveCodegenProperties(final String sdkDirPath,
			final String projectTemplateDirPath,
			final String projectDirPath,
			final String modelFilePath, 
			final Map<String,String> workbenchPropsMap) {
		
		int doIdeleteResult = JOptionPane.OK_OPTION;
		final File projectTemplateDir = new File(projectTemplateDirPath);
		final File projectDir = new File(projectDirPath);

		if (projectDir.exists() && projectDir.list().length != 0) {
			doIdeleteResult = JOptionPane.NO_OPTION;
//			File buildScript = new File(projectDir.getAbsolutePath() + File.separator + "build.xml");
			File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);
			
//			log.debug("* * * buildScript: " + buildScript.getAbsolutePath());
			log.debug("* * * codegenPropsFile: " + codegenPropsFile.getAbsolutePath());
			
			//if (buildScript.exists() && codegenPropsFile.exists()) {
			if (codegenPropsFile.exists()) {
				doIdeleteResult = JOptionPane
				.showConfirmDialog(
						this,
						"The project generation directory (" + projectDirPath + ") is not empty.\n"
						+"All existing code generation property information in the directory will\n"
						+"be overwritten.\n"
						+"\n"
						+"Proceed?",
						"Confirm Overwrite", 
						JOptionPane.YES_NO_OPTION);
			} else {
				doIdeleteResult = JOptionPane.OK_OPTION;
			}
		} 

		if (doIdeleteResult == JOptionPane.OK_OPTION) {
			final File sdkDirFile = new File(sdkDirPath);
			final File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);						

			//WorkbenchViewerBaseComponent.this.setVisible(false);
			//dispose();

			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Saving Code Generation Properties") {
				@Override
				public void process() {
					try {
						if (!sdkDirFile.exists()) {
							setErrorMessage("The specified SDK installation home directory does not exist: "+sdkDirPath);
							return;
						}
						
						if (!projectTemplateDir.exists()) {
							setErrorMessage("The specified SDK installation home directory does not contain the required 'project-template' sub-directory: "+sdkDirPath);
							return;
						}
						
						setProgressText("Configuring Project Directory");
						
						try {
							AntTools.configureProject(projectTemplateDirPath, projectDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage("Error creating and configuring the project generation directory!  Please check the console output for more details.");
							return;
						}
						
						try {
							AntTools.copyModelFile(projectDirPath, modelFilePath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage("Error copying the model file to the project generation /models directory!  Please check the console output for more details.");
							return;
						}

						setProgressText("Saving Properties");

						if (!saveProperties(codegenPropsFile,workbenchPropsMap)){
							setErrorMessage("IO error encountered saving code generation workbench properties to: "+codegenPropsFile.getAbsolutePath());
							return;
						}
						
						setProgressText("Properties Successfully Saved.");

					} catch (Exception e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage("Error: " + e.getMessage());
						return;
					}
				}
			};

			Thread th = new Thread(r);
			th.start();
		}
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
			final Map<String,String> workbenchPropsMap) {
		
		int doIdeleteResult = JOptionPane.OK_OPTION;
		final File projectTemplateDir = new File(projectTemplateDirPath);
		final File projectDir = new File(projectDirPath);

		if (projectDir.exists() && projectDir.list().length != 0) {
			doIdeleteResult = JOptionPane.NO_OPTION;
//			File buildScript = new File(projectDir.getAbsolutePath() + File.separator + "build.xml");
			
			final File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);
			
			//if (buildScript.exists() && codegenPropsFile.exists()) {
			if (codegenPropsFile.exists()) {
				doIdeleteResult = JOptionPane
				.showConfirmDialog(
						this,
						"The project generation directory (" + projectDirPath + ") is not\n"
						+"empty.  All existing deployment property information in the directory will\n"
						+"be overwritten.\n"
						+"\n"
						+"Proceed?",
						"Confirm Overwrite", 
						JOptionPane.YES_NO_OPTION);
			} else {
				doIdeleteResult = JOptionPane.OK_OPTION;
			}
		} 

		if (doIdeleteResult != JOptionPane.OK_OPTION) {
			return false;
		} 
		
		if (doIdeleteResult == JOptionPane.OK_OPTION) {
			final File sdkDirFile = new File(sdkDirPath);
			final File deployPropsFile = ResourceManager.getDeployPropsFile(projectDirPath,remoteDeployEnv);		
			final File certFile = new File(certFilePath);
			final File keyFile = new File(keyFilePath);

			//WorkbenchViewerBaseComponent.this.setVisible(false);
			//dispose();

			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Saving Deployment Properties") {
				@Override
				public void process() {
					try {
						if (!sdkDirFile.exists()) {
							setErrorMessage("The specified SDK installation home directory does not exist: "+sdkDirPath);
							return;
						}
						
						if (!projectTemplateDir.exists()) {
							setErrorMessage("The specified SDK installation home directory does not contain the required 'project-template' sub-directory: "+sdkDirPath);
							return;
						}
						
						setProgressText("Configuring Project Directory");
						
						try {
							AntTools.configureProject(projectTemplateDirPath, projectDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage("Error creating and configuring the project generation directory!  Please check the console output for more details.");
							return;
						}

						if (certFile.exists() && keyFile.exists()){
							
							try {
								AntTools.copyCertKeyFiles(sdkDirPath, projectDirPath, targetGridDirPath, certFilePath, keyFilePath);
							} catch (BuildException e) {
								log.error("ERROR: "+ e.getMessage(),e);
								setErrorMessage("Error copying the caGrid Security Certificate and Key files to the project generation " + targetGridDirPath +" directory!  Please check the console output for more details.");
								return;
							}
						}

						setProgressText("Saving Properties");

						if (!saveProperties(deployPropsFile,workbenchPropsMap)){
							setErrorMessage("IO error encountered saving deployment properties to: "+deployPropsFile.getAbsolutePath());
							return;
						}
						
						setProgressText("Properties Successfully Saved");

					} catch (Exception e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage("Error: " + e.getMessage());
						return;
					}
				}
			};
				
			try {
				Thread th = new Thread(r);
				th.start();
				th.join();
			} catch (InterruptedException e) {
				log.error("ERROR: "+ e.getMessage(),e);
				return false;
			}

		}

		return true;
	}
	
	;
	
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
	public void installSdk(final String sdkInstallDirPath) {
		
		int doIdeleteResult = JOptionPane.NO_OPTION;
		final File sdkInstallDir = new File(sdkInstallDirPath);

		if (sdkInstallDir.exists() && sdkInstallDir.list().length > 0) {
			doIdeleteResult = JOptionPane
			.showConfirmDialog(
					this,
					"The SDK installation home directory (" + sdkInstallDirPath + ") is not empty.\n"
					+"Any existing content within the directory may be overwritten.\n"
					+"\n"
					+"Proceed?",
					"Confirm Overwrite", 
					JOptionPane.YES_NO_OPTION);
		} else{
			doIdeleteResult = JOptionPane.OK_OPTION;
		}

		if (doIdeleteResult == JOptionPane.OK_OPTION) {	

			WorkbenchViewerBaseComponent.this.setVisible(false);
			dispose();

			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Installing") {
				@Override
				public void process() {
					try {
						
						if (!sdkInstallDir.exists()) {
							setErrorMessage("The specified SDK installation home directory (" + sdkInstallDirPath + ") does not exist.");
							return;
						}
						
						setProgressText("Installing the SDK");
						
						try {
							AntTools.installSdk(sdkInstallDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage("Error installing the SDK!  Please check the console output for more details.");
							return;
						}
				
						setProgressText("The SDK has been successfully Installed");
						


					} catch (Exception e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage("Error: " + e.getMessage());
						return;
					}
				}
			};
			
			r.run();
			
			JOptionPane.showMessageDialog(
					this,
					"The SDK has been successfully Installed");
//			Thread th = new Thread(r);
//			th.start();
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
		
		int doIdeleteResult = JOptionPane.NO_OPTION;
		final File projectTemplateDir = new File(projectTemplateDirPath);
		final File projectDir = new File(projectDirPath);

		if (projectDir.exists()) {
//			File buildScript = new File(projectDir.getAbsolutePath() + File.separator
//					+ "build.xml");
			
			File codegenPropsFile = ResourceManager.getCodegenPropsFile(projectDirPath);
			
//			if (buildScript.exists() && codegenPropsFile.exists()) {
			if (codegenPropsFile.exists()) {
				doIdeleteResult = JOptionPane
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

		if (doIdeleteResult == JOptionPane.OK_OPTION) {
			final File sdkDirFile = new File(sdkDirPath);		

			WorkbenchViewerBaseComponent.this.setVisible(false);
			dispose();

			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Generating") {
				@Override
				public void process() {
					try {
						if (!sdkDirFile.exists()) {
							setErrorMessage("The specified SDK installation home directory does not exist: "+sdkDirPath);
							return;
						}
						
						if (!projectTemplateDir.exists()) {
							setErrorMessage("The specified SDK installation home directory does not contain the required 'project-template' sub-directory: "+sdkDirPath);
							return;
						}
						
						setProgressText("Configuring Project Directory");
						
						try {
							AntTools.configureProject(projectTemplateDirPath, projectDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage("Error creating and configuring the project generation directory!  Please check the console output for more details.");
							return;
						}

						setProgressText("Generating the Application");
						
						try {
							AntTools.generateApplication(projectDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage("Error generating the application!  Please check the console output for more details.");
							return;
						}
					
						setProgressText("Application Successfully Generated");

					} catch (Exception e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage("Error: " + e.getMessage());
						return;
					}
				}
			};

			Thread th = new Thread(r);
			th.start();
			
			JOptionPane.showMessageDialog(
					this,
					"The application has been successfully generated");
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
		
		int doIdeleteResult = JOptionPane.NO_OPTION;
		final File projectTemplateDir = new File(projectTemplateDirPath);
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
						+"does not appear to be a caCORE Workbench generated project directory.  You\n"
						+"must specify a directory that already contains both the code generation and\n"
						+"deployment properties.");
			} else {
				doIdeleteResult = JOptionPane.OK_OPTION;
			}
		} else {
			JOptionPane
			.showMessageDialog(
					this,
					"The specified project generation directory ("+ projectDirPath+ ")\n"
					+"does not exist.  You must specify a different directory that already \n"
					+"contains the code generation properties.");
		}

		if (doIdeleteResult == JOptionPane.OK_OPTION) {
			final File sdkDirFile = new File(sdkDirPath);	
			final File deployPropsFile = ResourceManager.getDeployPropsFile(projectDirPath,remoteDeployEnv);

			WorkbenchViewerBaseComponent.this.setVisible(false);
			dispose();

			BusyDialogRunnable r = new BusyDialogRunnable(GridApplication.getContext().getApplication(), "Deploying") {
				@Override
				public void process() {
					try {
						if (!sdkDirFile.exists()) {
							setErrorMessage("The specified SDK installation home directory does not exist: "+sdkDirPath);
							return;
						}
						
						if (!projectTemplateDir.exists()) {
							setErrorMessage("The specified SDK installation home directory does not contain the required 'project-template' sub-directory: "+sdkDirPath);
							return;
						}
						
						setProgressText("Configuring Project Directory");
						
						try {
							AntTools.configureProject(projectTemplateDirPath, projectDirPath);
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage("Error creating and configuring the project generation directory!  Please check the console output for more details.");
							return;
						}

						setProgressText("Deploying the Application");
						
						try {
							if (remoteDeployEnv == null || remoteDeployEnv.length()==0){//local deployment
								AntTools.deployLocalApplication(projectDirPath, deployPropsFile.getAbsolutePath());
							} else {
								AntTools.deployRemoteApplication(projectDirPath, deployPropsFile.getAbsolutePath());
							}
						} catch (BuildException e) {
							log.error("ERROR: "+ e.getMessage(),e);
							setErrorMessage("Error deploying the application!  Please check the console output for more details.");
							return;
						}
					
						setProgressText("Application Successfully Deployed");

					} catch (Exception e) {
						log.error("ERROR: "+ e.getMessage(),e);
						setErrorMessage("Error: " + e.getMessage());
						return;
					}
				}
			};

			Thread th = new Thread(r);
			th.start();
			
			JOptionPane.showMessageDialog(
					this,
					"The application has been successfully deployed");
		}
	}
	
	public boolean saveProperties(File workbenchPropsFile, Map<String,String>workbenchPropsMap){
		
		try {
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

}
