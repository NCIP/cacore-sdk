package gov.nih.nci.cacore.workbench.portal.viewer;

import gov.nih.nci.cacore.workbench.common.CacoreWorkbenchConstants;
import gov.nih.nci.cacore.workbench.common.LookAndFeel;
import gov.nih.nci.cacore.workbench.common.ResourceManager;
import gov.nih.nci.cacore.workbench.common.WorkbenchPropertiesManager;
import gov.nih.nci.cacore.workbench.portal.panel.AdvancedSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.AppServerSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.CaGridAuthSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.ClmSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.CsmDbConnectionSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.DbConnectionSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.DeployTypeSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.LogViewerPanel;
import gov.nih.nci.cacore.workbench.portal.panel.ProjectSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.RemoteSshSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.SecuritySettingsPanel;
import gov.nih.nci.cacore.workbench.portal.panel.WritableApiSettingsPanel;
import gov.nih.nci.cacore.workbench.portal.validation.DeploymentPropertiesValidator;
import gov.nih.nci.cacore.workbench.portal.validation.PanelValidator;
import gov.nih.nci.cagrid.common.portal.PortalLookAndFeel;
import gov.nih.nci.cagrid.common.portal.validation.IconFeedbackPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;

import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * SDK Generation Viewer
 * 
 * @author <A HREF="MAILTO:dumitrud@mail.nih.gov">Dan Dumitru</A>
 * @created November 6, 2008
 */
public class DeployPropertiesViewer extends WorkbenchViewerBaseComponent {
	
	private static final Logger log = Logger.getLogger(DeployPropertiesViewer.class);
	
	private static final long serialVersionUID = 1L;
	
	private static final String TARGET_GRID_DIR = "/common/resources/target_grid/";
	
	private static final String CERT_FILE_NAME = "host-cert.pem";
	private static final String KEY_FILE_NAME  = "host-key.pem";
	
	private WorkbenchPropertiesManager propsMgr = null;

	private boolean isDirty=false;
	private boolean isPropsLoaded=false;

	// Validation 
	private DeploymentPropertiesValidator propsValidator = null;
	private List<PanelValidator> panelValidators = null;
    private ValidationResultModel validationModel = new DefaultValidationResultModel();

    // Buttons
    private JButton previousButton = null;
    private JButton nextButton = null;
    private JButton saveButton = null;
    private JButton deployButton = null;
    private JButton closeButton = null;

	/*
	 * caCORE SDK Panel definitions
	 */
	private JTabbedPane mainTabbedPane = null;
    private JPanel mainPanel = null;
    private JPanel buttonPanel = null;
   
	private JPanel summarySettingsPanel = null;

    
    // Tab panel indexes - used to enable/disable entire tab
	private int APP_SERVER_TAB_INDEX = 1;
	private int DB_TAB_INDEX = 2;
	private int LOGGING_TAB_INDEX = 3;
	private int CSM_TAB_INDEX = 4;
	private int CAGRID_AUTH_TAB_INDEX = 5;
	private int ADVANCED_TAB_INDEX = 6;
	private int REMOTE_SSH_TAB_INDEX = 7;
	private int DEPLOY_TAB_INDEX = 8;
	private int LOG_VIEWER_TAB_INDEX = 9;
	
	// Tab panel definitions
	private ProjectSettingsPanel projectSettingsPanel = null;
	private WritableApiSettingsPanel writableApiSettingsPanel = null;
	private SecuritySettingsPanel securitySettingsPanel = null;
	
	private DeployTypeSettingsPanel deployTypeSettingsPanel = null;
	private AppServerSettingsPanel appServerSettingsPanel = null;
	private DbConnectionSettingsPanel dbConnectionSettingsPanel = null;
	private CsmDbConnectionSettingsPanel csmDbConnectionSettingsPanel = null;
	private ClmSettingsPanel clmDbConnectionSettingsPanel = null;
	private CaGridAuthSettingsPanel caGridAuthSettingsPanel = null;
	private AdvancedSettingsPanel advancedSettingsPanel = null;
	private RemoteSshSettingsPanel remoteSshSettingsPanel = null;
	private LogViewerPanel logViewerPanel = null;
    
    public DeployPropertiesViewer() {
        super();
        initialize();
    }

    /**
     * This method initializes this Viewer
     */
    private void initialize() {

		// Initialize to an empty properties manager. User will
		// later choose which set of deployment properties to load
		// i.e., local or remote (e.g., dev, training, qa, prod)
		propsMgr = new WorkbenchPropertiesManager(new String[]{});
        
        //Initialize main tabbed panel validator
        propsValidator = new DeploymentPropertiesValidator(this);
        
        //Initialize Panels
        deployTypeSettingsPanel=new DeployTypeSettingsPanel(this, propsValidator);
        initPanels();
        
        setContentPane(getMainPanel());
        setFrameIcon(LookAndFeel.getGenerateApplicationIcon());
        setTitle("Manage Deployment Properties");
        
		initValidation();
		
        setDirty(false);
        setPropsLoaded(false);
        
		validateInput();
    }
    
    public void setProjectDir(String projectDirPath){
    	projectSettingsPanel.setProjectDir(projectDirPath);
    }
    
    public void loadDeployProperties(){
    	
    	String projectDir = projectSettingsPanel.getProjectDir();
    	
    	if (deployTypeSettingsPanel.isLocalDeployment())
    		propsMgr = ResourceManager.getDeployPropertiesManager(projectDir); 
    	else if (deployTypeSettingsPanel.isRemoteDeployment()){
    		String remoteDeployEnv = deployTypeSettingsPanel.getRemoteDeployEnvPrefix();
    		propsMgr = ResourceManager.getDeployPropertiesManager(projectDir,remoteDeployEnv);
    	}
        
		initPanels();
		resetMainTabbedPaneComponents();
		
		//Restore projectDir - necessary in case an empty (initial) project dir was selected
		projectSettingsPanel.setProjectDir(projectDir);
    	
		initValidation();
		
        setDirty(false);
        setPropsLoaded(true);
        
		validateInput();
		
		// Provide confirmation to user that the properties have been successfully loaded
		JOptionPane.showMessageDialog(this,"The deployment properties have been successfully loaded");
    }
    
    private void initPanels(){
    	
        projectSettingsPanel=new ProjectSettingsPanel(propsMgr, propsValidator);
        writableApiSettingsPanel=new WritableApiSettingsPanel(propsMgr, propsValidator);
        securitySettingsPanel=new SecuritySettingsPanel(propsMgr, propsValidator);
        
        appServerSettingsPanel=new AppServerSettingsPanel(this,propsValidator);
        dbConnectionSettingsPanel=new DbConnectionSettingsPanel(this,propsValidator);
        csmDbConnectionSettingsPanel=new CsmDbConnectionSettingsPanel(this,propsValidator,securitySettingsPanel.isSecurityEnabled());
        clmDbConnectionSettingsPanel=new ClmSettingsPanel(this,propsValidator); 
        caGridAuthSettingsPanel=new CaGridAuthSettingsPanel(this,propsValidator,securitySettingsPanel.isSecurityEnabled(),securitySettingsPanel.isCaGridLoginModuleEnabled());
        advancedSettingsPanel=new AdvancedSettingsPanel(this,propsValidator);
        remoteSshSettingsPanel=new RemoteSshSettingsPanel(this, propsValidator);
        logViewerPanel=new LogViewerPanel(propsValidator,projectSettingsPanel.getProjectDir());
        
        panelValidators = new ArrayList<PanelValidator>();
        // Uncomment the following as needed to include in validation
//      panelValidators.add((PanelValidator)projectSettingsPanel);
//      panelValidators.add((PanelValidator)writableApiSettingsPanel);
//      panelValidators.add((PanelValidator)securitySettingsPanel);
        panelValidators.add((PanelValidator)deployTypeSettingsPanel);
        panelValidators.add((PanelValidator)appServerSettingsPanel);
        panelValidators.add((PanelValidator)dbConnectionSettingsPanel);
        panelValidators.add((PanelValidator)csmDbConnectionSettingsPanel);
        panelValidators.add((PanelValidator)clmDbConnectionSettingsPanel);
        panelValidators.add((PanelValidator)caGridAuthSettingsPanel);
        panelValidators.add((PanelValidator)advancedSettingsPanel);
        panelValidators.add((PanelValidator)remoteSshSettingsPanel);
        panelValidators.add((PanelValidator)logViewerPanel);
    }
    
    private void resetMainTabbedPaneComponents(){
    	mainTabbedPane.setComponentAt(APP_SERVER_TAB_INDEX, new IconFeedbackPanel(this.validationModel, appServerSettingsPanel.getSettingsPanel()));
    	mainTabbedPane.setComponentAt(DB_TAB_INDEX, new IconFeedbackPanel(this.validationModel, dbConnectionSettingsPanel.getSettingsPanel()));
    	mainTabbedPane.setComponentAt(LOGGING_TAB_INDEX, new IconFeedbackPanel(this.validationModel, clmDbConnectionSettingsPanel.getSettingsPanel()));
    	mainTabbedPane.setComponentAt(CSM_TAB_INDEX, new IconFeedbackPanel(this.validationModel, csmDbConnectionSettingsPanel.getSettingsPanel()));
    	mainTabbedPane.setComponentAt(CAGRID_AUTH_TAB_INDEX, new IconFeedbackPanel(this.validationModel, caGridAuthSettingsPanel.getSettingsPanel()));
    	mainTabbedPane.setComponentAt(ADVANCED_TAB_INDEX, new IconFeedbackPanel(this.validationModel, advancedSettingsPanel.getSettingsPanel()));
    	mainTabbedPane.setComponentAt(REMOTE_SSH_TAB_INDEX, new IconFeedbackPanel(this.validationModel, remoteSshSettingsPanel.getSettingsPanel()));
    	mainTabbedPane.setComponentAt(LOG_VIEWER_TAB_INDEX, new IconFeedbackPanel(this.validationModel, logViewerPanel.getSettingsPanel()));    	
    }

    private void initValidation() {
    	propsValidator.initValidation();
    }

    private void validateInput() {
    	propsValidator.validateInput();
    }

    public void updateComponentTreeSeverity() {
        ValidationComponentUtils.updateComponentTreeMandatoryAndBlankBackground(this);
        ValidationComponentUtils.updateComponentTreeSeverityBackground(this, this.getValidationModel().getResult());
    }

    public void toggleSaveButton() {
    	//if (this.validationModel.hasErrors() || (!this.isDirty) || (!this.isPropsLoaded)) {
    	if ( (!this.isDirty) || (!this.isPropsLoaded)) {
            this.getSaveButton().setEnabled(false);
        } else {
            this.getSaveButton().setEnabled(true);
        }
    }
    
    public void toggleDeployButton() {
    	if ((this.isDirty) || (mainTabbedPane.getSelectedIndex() != DEPLOY_TAB_INDEX) || (!this.isPropsLoaded) ) {

    		log.debug("Deploy Button is disabled; here's why: ");
    		log.debug("* * * Viewer is 'Dirty' (properties need to be saved)? "+this.isDirty);
    		log.debug("* * * Not on Deploy Tab? "+(mainTabbedPane.getSelectedIndex() != DEPLOY_TAB_INDEX));
    		
    		if (mainTabbedPane.getSelectedIndex() != DEPLOY_TAB_INDEX)
    			log.debug("* * * Tabbed Panel Index: "+mainTabbedPane.getSelectedIndex() +";  DEPLOY_TAB_INDEX: " + DEPLOY_TAB_INDEX);
    		log.debug("* * * Have the properties been loaded? " + this.isPropsLoaded);

    		deployButton.setEnabled(false);
    	} else {
    		log.debug("* * * Validation model has errors? "+this.validationModel.hasErrors());
    		if (this.validationModel.hasErrors()){

    			ValidationResult results = validationModel.getResult();
    			List<com.jgoodies.validation.ValidationMessage> errors = results.getErrors();
    			
    			//debug error results
    			for (ValidationMessage errorMessage:results.getErrors()){
    				log.debug("* * * * Validation Error Message key: "+errorMessage.key()+"; Validation Error Message: "  + errorMessage.formattedText());
    			}
    			
    			if (errors !=null && errors.size() == 1){
        			for (ValidationMessage errorMessage:results.getErrors()){
        				if ( ((String)(errorMessage.key())).equalsIgnoreCase(CacoreWorkbenchConstants.LOG_FILE_VALIDATION_KEY)){
        					log.debug("Deploy Button is enabled; required conditions have been met");
        					deployButton.setEnabled(true);
        	    			return;
        				}
        			}

    			}
    			deployButton.setEnabled(false);
    			return;
    		}

    		log.debug("Deploy Button is enabled; required conditions have been met");
    		deployButton.setEnabled(true);
    	}
    }
    
    public void togglePreviousButton() {
    	if (mainTabbedPane.getSelectedIndex() <= 0 || (!this.isPropsLoaded) ){
            this.getPreviousButton().setEnabled(false);
        } else {
            this.getPreviousButton().setEnabled(true);
        }
    }
    
    public void toggleNextButton() {
    	if (mainTabbedPane.getSelectedIndex() >= mainTabbedPane.getTabCount()-1 || (!this.isPropsLoaded) ){
            this.getNextButton().setEnabled(false);
        } else {
            this.getNextButton().setEnabled(true);
        }
    }

    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getMainPanel() {
        if (mainPanel == null) {
            GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
            gridBagConstraints111.fill = GridBagConstraints.BOTH;
            gridBagConstraints111.weighty = 1.0;
            gridBagConstraints111.weightx = 1.0;
            
            GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
            gridBagConstraints1.insets = new java.awt.Insets(5, 5, 5, 5);
            gridBagConstraints1.gridx = 0;
            gridBagConstraints1.gridy = 2;
            gridBagConstraints1.anchor = java.awt.GridBagConstraints.CENTER;
            gridBagConstraints1.gridheight = 1;
            
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());
            mainPanel.add(getButtonPanel(), gridBagConstraints1);
            mainPanel.add(getMainTabbedPane(), gridBagConstraints111);
        }
        return mainPanel;
    }


    /**
     * This method initializes jPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getPreviousButton(), null);
            buttonPanel.add(getNextButton(), null);
            buttonPanel.add(getSaveButton(), null);
            buttonPanel.add(getDeployButton(), null);
            buttonPanel.add(getCloseButton(), null);
        }
        return buttonPanel;
    }

    /**
     * This method initializes the Previous jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getPreviousButton() {
    	if (previousButton == null) {
    		previousButton = new JButton();
    		previousButton.setText("Previous");
    		previousButton.setIcon(LookAndFeel.getPreviousIcon());
    		previousButton.addActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent e) {
    				mainTabbedPane.setSelectedIndex(getPreviousIndex());
    				validateInput();
    			}
    		});

    		previousButton.addMouseListener(new java.awt.event.MouseListener() {
    			public void mouseEntered(java.awt.event.MouseEvent e) {
    				log.debug("mouseEntered");//do nothing
    			}
    			public void mouseReleased(java.awt.event.MouseEvent e) {
    				log.debug("mouseReleased");//do nothing
    			}
    			public void mouseExited(java.awt.event.MouseEvent e) {
    				log.debug("mouseExited");//do nothing
    			}
    			public void mousePressed(java.awt.event.MouseEvent e) {
    				log.debug("mousePressed");
    				validateInput();
    			}
    			public void mouseClicked(java.awt.event.MouseEvent e) {
    				try {
    					log.debug("mouseClicked");
    					toggleUseDbConnectionsCheckBox();
    					validateInput();
    				} catch (Exception ex) {
    					ex.printStackTrace();
    				}
    			}
    		});
    	}

        return previousButton;
    }
    
    /**
     * This method initializes the Previous jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getNextButton() {
        if (nextButton == null) {
        	nextButton = new JButton();
        	nextButton.setText("Next");
        	nextButton.setIcon(LookAndFeel.getNextIcon());
        	nextButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	 mainTabbedPane.setSelectedIndex(getNextIndex());
                	 validateInput();
                }
            });
        	
        	nextButton.addMouseListener(new java.awt.event.MouseListener() {
    			public void mouseEntered(java.awt.event.MouseEvent e) {
    				log.debug("mouseEntered");//do nothing
    			}
    			public void mouseReleased(java.awt.event.MouseEvent e) {
    				log.debug("mouseReleased");//do nothing
    			}
    			public void mouseExited(java.awt.event.MouseEvent e) {
    				log.debug("mouseExited");//do nothing
    			}
    			public void mousePressed(java.awt.event.MouseEvent e) {
    				log.debug("mousePressed");
    				validateInput();
    			}
    			public void mouseClicked(java.awt.event.MouseEvent e) {
    				try {
    					log.debug("mouseClicked");
    					toggleUseDbConnectionsCheckBox();
    					validateInput();
    				} catch (Exception ex) {
    					ex.printStackTrace();
    				}
    			}
        	});
        }

        return nextButton;
    }
    
    private int getPreviousIndex(){
    	int index = mainTabbedPane.getSelectedIndex();

    	while (index >= 0){
    		--index;
    		
    		if (mainTabbedPane.isEnabledAt(index))
    			return index;
    	}
    	
    	return 0;
    }
    
    private int getNextIndex(){
    	int index = mainTabbedPane.getSelectedIndex();

    	while (index <= mainTabbedPane.getTabCount()-1){
    		++index;
    		
    		if (mainTabbedPane.isEnabledAt(index))
    			return index;
    	}
    	
    	return mainTabbedPane.getTabCount() - 1;
    }

    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getSaveButton() {
        if (saveButton == null) {
            saveButton = new JButton();
            saveButton.setText("Save");
            saveButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            saveButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                	
            		String gridSecureCertFilePath="";
            		String gridSecurekeyFilePath="";
            		String dbSqlFilePath="";
            		String csmDbSqlFilePath="";
            		String clmDbSqlFilePath="";
            		
            		if (securitySettingsPanel.isCaGridLoginModuleEnabled()){
            			gridSecureCertFilePath = caGridAuthSettingsPanel.getGridSecureCertFilePath();
            			gridSecurekeyFilePath = caGridAuthSettingsPanel.getGridSecureKeyFilePath();
            		}
            		
            		//if (dbConnectionSettingsPanel.isDbDropSchemaSelected()){
            			dbSqlFilePath = dbConnectionSettingsPanel.getDbSqlFilePath();
            		//}
            		
            		//if (csmDbConnectionSettingsPanel.isDbDropSchemaSelected()){
            			csmDbSqlFilePath = csmDbConnectionSettingsPanel.getDbSqlFilePath();
            		//}
            		
            		//if (clmDbConnectionSettingsPanel.isDbDropSchemaSelected()){
            			clmDbSqlFilePath = clmDbConnectionSettingsPanel.getDbSqlFilePath();
            		//}

            		boolean isSaveSuccessful = saveDeployProperties(
            				projectSettingsPanel.getSdkInstallDirValue(),
            				projectSettingsPanel.getProjectTemplateDirValue(),
            				projectSettingsPanel.getProjectDir(),
            				deployTypeSettingsPanel.getRemoteDeployEnvPrefix(),
            				TARGET_GRID_DIR + caGridAuthSettingsPanel.getCaGridTargetGrid(),
            				gridSecureCertFilePath,
            				gridSecurekeyFilePath,
            				dbConnectionSettingsPanel.getDbType().toLowerCase(),
            				dbSqlFilePath,
            				csmDbSqlFilePath,
            				clmDbSqlFilePath,
            				getDeployPropsMap()
            		); 
            		
            		log.debug("* * * isSaveSuccessful: " + isSaveSuccessful);

            		if (isSaveSuccessful){
        				File deployPropsFile = ResourceManager.getDeployPropsFile(projectSettingsPanel.getProjectDir(),deployTypeSettingsPanel.getRemoteDeployEnvPrefix());
        				Map<String,String>deployPropsMap = getDeployPropsMap();
        				
            			if (securitySettingsPanel.isCaGridLoginModuleEnabled()){

            				// Certificate and Key files may have been renamed - synchronize names now
            				String certFilePath =  (projectSettingsPanel.getProjectDir()
            						+ TARGET_GRID_DIR
            						+ caGridAuthSettingsPanel.getCaGridTargetGrid()
            						+ "/"
            						+ CERT_FILE_NAME).replace('\\', '/');

            				deployPropsMap.put("grid.secure.cert.file", certFilePath );

            				// Override the caGrid Certificate File property to point to the project generation 
            				// build sub-directory, as we now copy the key file to this sub-directory
            				String keyFilePath = (projectSettingsPanel.getProjectDir()
            						+ TARGET_GRID_DIR
            						+ caGridAuthSettingsPanel.getCaGridTargetGrid()
            						+ "/"
            						+ KEY_FILE_NAME).replace('\\', '/');

            				deployPropsMap.put("grid.secure.key.file", keyFilePath );
            				
            				log.debug("'grid.secure.cert.file' prior to saving: "+deployPropsMap.get("grid.secure.cert.file"));
            				log.debug("'grid.secure.key.file' prior to saving: "+deployPropsMap.get("grid.secure.key.file"));

//            				if (!saveProperties(deployPropsFile, deployPropsMap)){
//            					log.error("ERROR:  Unable to rename caGrid Certificate and Key file names.");
//            				}

            				caGridAuthSettingsPanel.setGridSecureCertFilePath(certFilePath);
            				caGridAuthSettingsPanel.setGridSecureKeyFilePath(keyFilePath);
            			}
            			
            			//if (dbConnectionSettingsPanel.isDbDropSchemaSelected()){
            			if (dbSqlFilePath!=null && dbSqlFilePath.length()>0){
            				
            				
            				String dbType = dbConnectionSettingsPanel.getDbType();

            				// DB SQL file may has been copied - synchronize file path now
            				// Override the DB SQL File property to point to the project generation 
            				// db sub-directory, as we now copy the db sql file to this standard sub-directory
            				File destDbSqlDir = ResourceManager.getDbSqlDir(projectSettingsPanel.getProjectDir(), dbType);
            				dbSqlFilePath =  (destDbSqlDir
            						+ "/"
            						+ dbConnectionSettingsPanel.getDbSqlFileName()).replace('\\', '/');
            		    	
            				log.debug("* * * dbSqlFilePath: " +csmDbSqlFilePath);
            		    	if ("oracle".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("db.install.create.oracle.file.list.ui", dbSqlFilePath.replace('\\', '/'));
                				log.debug("'db.install.create.oracle.file.list.ui' prior to saving: "+deployPropsMap.get("db.install.create.oracle.file.list.ui"));
            		    	} else if ("mysql".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("db.install.create.mysql.file.list.ui", dbSqlFilePath.replace('\\', '/'));
            		    		log.debug("'db.install.create.mysql.file.list.ui' prior to saving: "+deployPropsMap.get("db.install.create.mysql.file.list.ui"));
            		    	} else if ("postgresql".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("db.install.create.postgresql.file.list.ui", dbSqlFilePath.replace('\\', '/'));
            		    		log.debug("'db.install.create.postgresql.file.list.ui' prior to saving: "+deployPropsMap.get("db.install.create.postgresql.file.list.ui"));
            		    	}

//            				if (!saveProperties(deployPropsFile, deployPropsMap)){
//            					log.error("ERROR:  Unable to rename DB SQL file path to standard project generation db path.");
//            				}

            		    	dbConnectionSettingsPanel.setDbSqlFilePath(dbSqlFilePath);
            			}
            			
            			//if (csmDbConnectionSettingsPanel.isDbDropSchemaSelected()){
            			if (isCsmEnabled() && csmDbSqlFilePath!=null && csmDbSqlFilePath.length()>0){
            				
            				String dbType = csmDbConnectionSettingsPanel.getCsmDbType();

            				// CSM DB SQL file has been copied - synchronize file path now
            				// Override the CSM DB SQL File property to point to the project generation 
            				// db sub-directory, as we now copy the db sql file to this standard sub-directory
            				File destDbSqlDir = ResourceManager.getDbSqlDir(projectSettingsPanel.getProjectDir(), dbType);
            				csmDbSqlFilePath =  (destDbSqlDir
            						+ "/"
            						+ csmDbConnectionSettingsPanel.getCsmDbSqlFileName()).replace('\\', '/');
            		    	
            				log.debug("* * * csmDbSqlFilePath: " +csmDbSqlFilePath);
            		    	if ("oracle".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("csm.db.install.create.oracle.file.list.ui", csmDbSqlFilePath.replace('\\', '/'));
                				log.debug("'csm.db.install.create.oracle.file.list.ui' prior to saving: "+deployPropsMap.get("csm.db.install.create.oracle.file.list.ui"));
            		    	} else if ("mysql".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("csm.db.install.create.mysql.file.list.ui", csmDbSqlFilePath.replace('\\', '/'));
            		    		log.debug("'csm.db.install.create.mysql.file.list.ui' prior to saving: "+deployPropsMap.get("csm.db.install.create.mysql.file.list.ui"));
            		    	} else if ("postgresql".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("csm.db.install.create.postgresql.file.list.ui", csmDbSqlFilePath.replace('\\', '/'));
            		    		log.debug("'csm.db.install.create.postgresql.file.list.ui' prior to saving: "+deployPropsMap.get("csm.db.install.create.postgresql.file.list.ui"));
            		    	}	

//            				if (!saveProperties(deployPropsFile, deployPropsMap)){
//            					log.error("ERROR:  Unable to rename CSM DB SQL file path to standard project generation db path.");
//            				}

            				csmDbConnectionSettingsPanel.setDbSqlFilePath(csmDbSqlFilePath);
            			}
            			
            			//if (clmDbConnectionSettingsPanel.isDbDropSchemaSelected()){
            			if (isClmEnabled() && clmDbSqlFilePath!=null && clmDbSqlFilePath.length()>0){
            				
            				String dbType = clmDbConnectionSettingsPanel.getClmDbType();

            				// CLM DB SQL file has been copied - synchronize file path now
            				// Override the CLM DB SQL File property to point to the project generation 
            				// db sub-directory, as we now copy the db sql file to this standard sub-directory
            				File destDbSqlDir = ResourceManager.getDbSqlDir(projectSettingsPanel.getProjectDir(), dbType);
            				clmDbSqlFilePath =  (destDbSqlDir
            						+ "/"
            						+ clmDbConnectionSettingsPanel.getClmDbSqlFileName()).replace('\\', '/');
            		    	
            				log.debug("* * * clmDbSqlFilePath: " +clmDbSqlFilePath);
            				if ("oracle".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("clm.db.install.create.oracle.file.list.ui", clmDbSqlFilePath.replace('\\', '/'));
            		    		log.debug("'clm.db.install.create.oracle.file.list' prior to saving: "+deployPropsMap.get("clm.db.install.create.oracle.file.list.ui"));
            				} else if ("mysql".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("clm.db.install.create.mysql.file.list.ui", clmDbSqlFilePath.replace('\\', '/'));
            		    		log.debug("'clm.db.install.create.mysql.file.list' prior to saving: "+deployPropsMap.get("clm.db.install.create.mysql.file.list.ui"));
            				} else if ("postgresql".equalsIgnoreCase(dbType)){
            		    		deployPropsMap.put("clm.db.install.create.postgresql.file.list.ui", clmDbSqlFilePath.replace('\\', '/'));
            		    		log.debug("'clm.db.install.create.postgresql.file.list' prior to saving: "+deployPropsMap.get("clm.db.install.create.postgresql.file.list.ui"));
            		    	}	

//            				if (!saveProperties(deployPropsFile, deployPropsMap)){
//            					log.error("ERROR:  Unable to rename CSM DB SQL file path to standard project generation db path.");
//            				}

            				clmDbConnectionSettingsPanel.setDbSqlFilePath(clmDbSqlFilePath);
            			}

            			log.debug("* * * About to override caGrid Certificate, Key and/or DB SQL file name(s)");
        				if (!saveProperties(deployPropsFile, deployPropsMap)){
        					log.error("ERROR:  Unable to rename caGrid Certificate, Key and/or DB SQL file name(s).");
        				}

            			setDirty(false);
            			validateInput();
            		}
                }
            });
        }

        return saveButton;
    }
    
    public void updateDeployApplicationTabContents() {
    	//update the 'Generate Application' tab contents
    	if (mainTabbedPane.getSelectedIndex() == DEPLOY_TAB_INDEX){
    		mainTabbedPane.setComponentAt(DEPLOY_TAB_INDEX, getSummarySettingsPanel());
    	}
    }
    
    /**
     * This method initializes jButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getDeployButton() {
        if (deployButton == null) {
            deployButton = new JButton();
            deployButton.setText("Deploy");
            deployButton.setIcon(LookAndFeel.getGenerateApplicationIcon());
            
            deployButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    deployApplication(
                    		projectSettingsPanel.getSdkInstallDirValue(),
                    		projectSettingsPanel.getProjectTemplateDirValue(),
                    		projectSettingsPanel.getProjectDir(),
                    		deployTypeSettingsPanel.getRemoteDeployEnvPrefix());
                }
            });
        }

        return deployButton;
    }
    
    public void syncDbCsmDbFields() {
    	
    	csmDbConnectionSettingsPanel.setCsmDatabaseType(dbConnectionSettingsPanel.getDbType());
    	
    	log.debug("* * * isCsmUseDBConnectionSettings? " + csmDbConnectionSettingsPanel.isCsmUseDBConnectionSettings());
    	if (csmDbConnectionSettingsPanel.isCsmUseDBConnectionSettings()){
    		csmDbConnectionSettingsPanel.setCsmUseJndiBasedConnection(dbConnectionSettingsPanel.isUseJndiBasedConnection());
    		csmDbConnectionSettingsPanel.setCsmDbJndiUrl(dbConnectionSettingsPanel.getDbJndiName());
    		csmDbConnectionSettingsPanel.setCsmDbConnectionUrl(dbConnectionSettingsPanel.getDbUrl());
    		csmDbConnectionSettingsPanel.setCsmDbHostname(dbConnectionSettingsPanel.getDbHostname());
    		csmDbConnectionSettingsPanel.setCsmDbPort(dbConnectionSettingsPanel.getDbPort());
    		csmDbConnectionSettingsPanel.setCsmDbSchema(dbConnectionSettingsPanel.getDbSchema());
    		csmDbConnectionSettingsPanel.setCsmDbUsername(dbConnectionSettingsPanel.getDbUsername());
    		csmDbConnectionSettingsPanel.setCsmDbPassword(dbConnectionSettingsPanel.getDbPassword());
    		
    		csmDbConnectionSettingsPanel.toggleCsmDbConnectionFields();
    		csmDbConnectionSettingsPanel.toggleRecreateCsmDBFields();
    	}
    }
    
    public void syncDbClmDbFields() {
		
    	clmDbConnectionSettingsPanel.setClmDatabaseType(dbConnectionSettingsPanel.getDbType());
		
    	log.debug("* * * isClmUseDBConnectionSettings? " + clmDbConnectionSettingsPanel.isClmUseDBConnectionSettings());
    	if (clmDbConnectionSettingsPanel.isClmUseDBConnectionSettings()){

    		//clmDbConnectionSettingsPanel.setClmUseJndiBasedConnection(dbConnectionSettingsPanel.isUseJndiBasedConnection());
    		//clmDbConnectionSettingsPanel.setClmDbJndiUrl(dbConnectionSettingsPanel.getDbJndiName());
    		clmDbConnectionSettingsPanel.setClmDbConnectionUrl(dbConnectionSettingsPanel.getDbUrl());
    		clmDbConnectionSettingsPanel.setClmDbHostname(dbConnectionSettingsPanel.getDbHostname());
    		clmDbConnectionSettingsPanel.setClmDbPort(dbConnectionSettingsPanel.getDbPort());
    		clmDbConnectionSettingsPanel.setClmDbSchema(dbConnectionSettingsPanel.getDbSchema());
    		clmDbConnectionSettingsPanel.setClmDbUsername(dbConnectionSettingsPanel.getDbUsername());
    		clmDbConnectionSettingsPanel.setClmDbPassword(dbConnectionSettingsPanel.getDbPassword());
    		
    		clmDbConnectionSettingsPanel.toggleClmDbConnectionFields();
    		clmDbConnectionSettingsPanel.toggleRecreateClmDbFields();
    	}
    }
    
    public void toggleRecreateClmDbFields(){
		clmDbConnectionSettingsPanel.toggleRecreateClmDbFields();
    }
    
    public String getDatabaseType(){
    	return dbConnectionSettingsPanel.getDbType();
    }
    
    public void toggleTestConnectionButton() {
    	boolean hasValidationErrors = dbConnectionSettingsPanel.validateDbConnectionInput().hasErrors();
    	if (!hasValidationErrors){
    		dbConnectionSettingsPanel.setTestConnectionButtonEnabled(true);
    		return;
        }
    	
    	dbConnectionSettingsPanel.setTestConnectionButtonEnabled(false);

    }
    
    public void toggleCsmTestConnectionButton() {
    	boolean hasValidationErrors = csmDbConnectionSettingsPanel.validateDbConnectionInput().hasErrors();
    	if (securitySettingsPanel.isSecurityEnabled() && !csmDbConnectionSettingsPanel.isCsmUseDBConnectionSettings() && !hasValidationErrors){
    		csmDbConnectionSettingsPanel.setTestConnectionButtonEnabled(true); 
    		return;
        }
    	
    	csmDbConnectionSettingsPanel.setTestConnectionButtonEnabled(false);
    }
    
    public void toggleClmTestConnectionButton() {
    	boolean hasValidationErrors = clmDbConnectionSettingsPanel.validateInput().hasErrors();
    	if (writableApiSettingsPanel.isCommonLoggingModuleEnabled() && !clmDbConnectionSettingsPanel.isClmUseDBConnectionSettings() && !hasValidationErrors){
    		clmDbConnectionSettingsPanel.setTestConnectionButtonEnabled(true); 
    		return;
        }
    	
    	clmDbConnectionSettingsPanel.setTestConnectionButtonEnabled(false);
    }
    
    public void toggleDbJndiNameField() {
    	if (dbConnectionSettingsPanel.isUseJndiBasedConnection()){
    		dbConnectionSettingsPanel.getDbJndiNameField().setEnabled(true);
    		return;
        }
    	
    	dbConnectionSettingsPanel.getDbJndiNameField().setEnabled(false);

    }
    
    public void toggleCsmDbJndiNameField() {
    	if (!csmDbConnectionSettingsPanel.isCsmUseDBConnectionSettings() && csmDbConnectionSettingsPanel.isCsmUseJndiBasedConnection()){
    		csmDbConnectionSettingsPanel.getCsmDbJndiNameField().setEnabled(true);
    		return;
        }
    	
    	csmDbConnectionSettingsPanel.getCsmDbJndiNameField().setEnabled(false);

    }
    
    public void toggleWritableApiFields() {
    	if (isClmEnabled() ){
    		mainTabbedPane.setEnabledAt(LOGGING_TAB_INDEX, true); // CLM Panel
    	} else{
    		mainTabbedPane.setEnabledAt(LOGGING_TAB_INDEX, false); // CLM Panel
    	}
    }
    
    public void toggleSecurityFields() {
		if (isSecurityEnabled()){

		    mainTabbedPane.setEnabledAt(CSM_TAB_INDEX, true); // CSM Panel
		    
			if (securitySettingsPanel.isCaGridLoginModuleEnabled()){
				mainTabbedPane.setEnabledAt(CAGRID_AUTH_TAB_INDEX, true); // caGrid Auth Panel
			} else {
				mainTabbedPane.setEnabledAt(CAGRID_AUTH_TAB_INDEX, false); // caGrid Auth Panel
			}
		} else{
		    mainTabbedPane.setEnabledAt(CSM_TAB_INDEX, false); // CSM Panel
		    mainTabbedPane.setEnabledAt(CAGRID_AUTH_TAB_INDEX, false); // caGrid Auth Panel
		}
    }
    
    public void toggleRemoteSshFields() {
		if (isRemoteDeployment()){
		    mainTabbedPane.setEnabledAt(REMOTE_SSH_TAB_INDEX, true);
		} else{
			mainTabbedPane.setEnabledAt(REMOTE_SSH_TAB_INDEX, false);
		}
    }
    
    public void togglePanels(){
	    if (!isPropsLoaded){
	    	mainTabbedPane.setEnabledAt(APP_SERVER_TAB_INDEX, false);
	    	mainTabbedPane.setEnabledAt(DB_TAB_INDEX, false);
	    	mainTabbedPane.setEnabledAt(LOGGING_TAB_INDEX, false);
	    	mainTabbedPane.setEnabledAt(CSM_TAB_INDEX, false);
	    	mainTabbedPane.setEnabledAt(CAGRID_AUTH_TAB_INDEX, false);
	    	mainTabbedPane.setEnabledAt(ADVANCED_TAB_INDEX, false);
	    	mainTabbedPane.setEnabledAt(REMOTE_SSH_TAB_INDEX, false);
	    	mainTabbedPane.setEnabledAt(DEPLOY_TAB_INDEX, false);
	    	mainTabbedPane.setEnabledAt(LOG_VIEWER_TAB_INDEX, false);
	    } else {
	    	mainTabbedPane.setEnabledAt(APP_SERVER_TAB_INDEX, true);
	    	mainTabbedPane.setEnabledAt(DB_TAB_INDEX, true);
	    	mainTabbedPane.setEnabledAt(ADVANCED_TAB_INDEX, true);
	    	
	    	// The following tabs are enabled/disabled based upon
	    	// Security and Writable API settings and so should not 
	    	// be enabled here
	    	
	    	//mainTabbedPane.setEnabledAt(LOGGING_TAB_INDEX, true);
	    	//mainTabbedPane.setEnabledAt(CSM_TAB_INDEX, true);
	    	//mainTabbedPane.setEnabledAt(CAGRID_AUTH_TAB_INDEX, true);
	    	
	    	//mainTabbedPane.setEnabledAt(REMOTE_SSH_TAB_INDEX, true);
	    	
	    	mainTabbedPane.setEnabledAt(DEPLOY_TAB_INDEX, true);
	    	mainTabbedPane.setEnabledAt(LOG_VIEWER_TAB_INDEX, true);
	    }
    }
    
    public boolean isSecurityEnabled(){
    	return securitySettingsPanel.isSecurityEnabled();
    }
    
    public boolean isAppDbAndCsmSchemaSame(){
    	return (csmDbConnectionSettingsPanel.getCsmDbSchema().length() > 0 &&
    			csmDbConnectionSettingsPanel.getCsmDbSchema().equalsIgnoreCase(dbConnectionSettingsPanel.getDbSchema()) &&
    			csmDbConnectionSettingsPanel.getCsmDbHostname().equalsIgnoreCase(dbConnectionSettingsPanel.getDbHostname()) &&
    			csmDbConnectionSettingsPanel.getCsmDbPort().equalsIgnoreCase(dbConnectionSettingsPanel.getDbPort()) &&
    			csmDbConnectionSettingsPanel.getCsmDbUsername().equalsIgnoreCase(dbConnectionSettingsPanel.getDbUsername())
    	);
    }
    
    public boolean isAppDbAndClmSchemaSame(){
    	return (clmDbConnectionSettingsPanel.getClmDbSchema().length() > 0 &&
    			clmDbConnectionSettingsPanel.getClmDbSchema().equalsIgnoreCase(dbConnectionSettingsPanel.getDbSchema()) &&
    			clmDbConnectionSettingsPanel.getClmDbHostname().equalsIgnoreCase(dbConnectionSettingsPanel.getDbHostname()) &&
    			clmDbConnectionSettingsPanel.getClmDbPort().equalsIgnoreCase(dbConnectionSettingsPanel.getDbPort()) &&
    			clmDbConnectionSettingsPanel.getClmDbUsername().equalsIgnoreCase(dbConnectionSettingsPanel.getDbUsername())
    	);
    }
    
    public boolean isCsmDbAndClmSchemaSame(){
    	return (clmDbConnectionSettingsPanel.getClmDbSchema().length() > 0 &&
    			clmDbConnectionSettingsPanel.getClmDbSchema().equalsIgnoreCase(csmDbConnectionSettingsPanel.getCsmDbSchema()) &&
    			clmDbConnectionSettingsPanel.getClmDbHostname().equalsIgnoreCase(csmDbConnectionSettingsPanel.getCsmDbHostname()) &&
    			clmDbConnectionSettingsPanel.getClmDbPort().equalsIgnoreCase(csmDbConnectionSettingsPanel.getCsmDbPort()) &&
    			clmDbConnectionSettingsPanel.getClmDbUsername().equalsIgnoreCase(csmDbConnectionSettingsPanel.getCsmDbUsername())
    	);
    }
    
    public boolean isAppDbDropSchemaSelected(){
    	return dbConnectionSettingsPanel.isDbDropSchemaSelected();
    }
    
    public boolean isCsmDbDropSchemaSelected(){
    	return csmDbConnectionSettingsPanel.isCsmDbDropSchemaSelected();
    }    
    
    public String getCsmDbSqlFileName(){
    	return csmDbConnectionSettingsPanel.getCsmDbSqlFileName();
    }
    
    public String getClmDbSqlFileName(){
    	return clmDbConnectionSettingsPanel.getClmDbSqlFileName();
    }
    
    public boolean isWritableApiExtensionEnabled(){
    	return writableApiSettingsPanel.isWritableApiExtensionEnabled();
    }
    
    public boolean isCommonLoggingModuleEnabled(){
    	return writableApiSettingsPanel.isCommonLoggingModuleEnabled();
    }
    
    public boolean isCsmEnabled() {
    	return (isSecurityEnabled());
    }    
    
    public boolean isClmEnabled() {
    	return (isWritableApiExtensionEnabled() && isCommonLoggingModuleEnabled());
    }
    
    protected void toggleUseDbConnectionsCheckBox() {
    	log.debug("* * * mainTabbedPane.getSelectedIndex()==CSM_TAB_INDEX: " + (mainTabbedPane.getSelectedIndex()==CSM_TAB_INDEX  && 
				securitySettingsPanel.isSecurityEnabled() &&
				securitySettingsPanel.isInstanceLevelSecurityEnabled()));
    	
		if (mainTabbedPane.getSelectedIndex()==CSM_TAB_INDEX  && 
				securitySettingsPanel.isSecurityEnabled() &&
				securitySettingsPanel.isInstanceLevelSecurityEnabled()){
			
			if (!csmDbConnectionSettingsPanel.isCsmUseDBConnectionSettings()){
				
				csmDbConnectionSettingsPanel.setCsmUseDbConnectionSettings(true);

				syncDbCsmDbFields();
			}
			
			csmDbConnectionSettingsPanel.setCsmUseDbConnectionSettingsEnabled(false);
			
			JOptionPane.showMessageDialog(
					this,
					"Instance Level security requires that CSM Database Connection settings\n"
					+ "match the Database Connection settings.  The CSM 'Use DB \n"
					+ "Connection Settings' checkbox will remain selected and disabled\n"
					+ "as a result.");
		}	
    }

    /**
     * This method initializes closeButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getCloseButton() {
        if (closeButton == null) {
            closeButton = new JButton();
            closeButton.setIcon(PortalLookAndFeel.getCloseIcon());
            closeButton.setText("Cancel");
            closeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });
        }
        return closeButton;
    }
    
	/**
	 * This method initializes the Code Generation settings panel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSummarySettingsPanel() {
		//if (summarySettingsPanel == null) {
			
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints10.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints10.gridwidth = 1;
			//gridBagConstraints10.weighty = 1.0D;
			gridBagConstraints10.gridx = 0;
			
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints11.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints11.gridwidth = 1;
			//gridBagConstraints11.weighty = 1.0D;
			gridBagConstraints11.gridx = 1;
			
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints20.gridy = 2;
			gridBagConstraints20.weightx = 1.0;
			gridBagConstraints20.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints20.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints20.gridwidth = 1;
			//gridBagConstraints20.weighty = 1.0D;
			gridBagConstraints20.gridx = 0;
			
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 2;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints21.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints21.gridwidth = 1;
			//gridBagConstraints21.weighty = 1.0D;
			gridBagConstraints21.gridx = 1;
			
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints30.gridy = 3;
			gridBagConstraints30.weightx = 1.0;
			gridBagConstraints30.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints30.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints30.gridwidth = 1;
			//gridBagConstraints30.weighty = 1.0D;
			gridBagConstraints30.gridx = 0;
			
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints31.gridy = 3;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints31.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints31.gridwidth = 1;
			//gridBagConstraints31.weighty = 1.0D;
			gridBagConstraints31.gridx = 1;
			
			GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints40.gridy = 4;
			gridBagConstraints40.weightx = 1.0;
			gridBagConstraints40.anchor = java.awt.GridBagConstraints.NORTHWEST;
			gridBagConstraints40.insets = new java.awt.Insets(2, 2, 2, 2);
			gridBagConstraints40.gridwidth = 1;
			//gridBagConstraints40.weighty = 1.0D;
			gridBagConstraints40.gridx = 0;
			
			String panelTitle = "Review " + deployTypeSettingsPanel.getDeployType() + " Deployment Properties";
			
			if (isRemoteDeployment()){
				panelTitle+=" for " + deployTypeSettingsPanel.getRemoteDeployEnv() + " Environment";
			}

		    summarySettingsPanel = new JPanel();
		    summarySettingsPanel.setLayout(new GridBagLayout());
		    summarySettingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, panelTitle,
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, PortalLookAndFeel.getPanelLabelColor()));

		    summarySettingsPanel.add(appServerSettingsPanel.getSummaryPanel(), gridBagConstraints10);
		    
		    if (isRemoteDeployment())
		    	summarySettingsPanel.add(remoteSshSettingsPanel.getSummaryPanel(), gridBagConstraints11);
		    
			summarySettingsPanel.add(dbConnectionSettingsPanel.getSummaryPanel(), gridBagConstraints20);
			summarySettingsPanel.add(advancedSettingsPanel.getSummaryPanel(), gridBagConstraints30);
			
			//todo :: add remote SSH Summary Panel
			
			if (securitySettingsPanel.isSecurityEnabled()){
				summarySettingsPanel.add(csmDbConnectionSettingsPanel.getSummaryPanel(), gridBagConstraints21);
				if (securitySettingsPanel.isCaGridLoginModuleEnabled()){
					summarySettingsPanel.add(caGridAuthSettingsPanel.getSummaryPanel(), gridBagConstraints31);
				}
			}

			if (isClmEnabled()){
				if (writableApiSettingsPanel.isCommonLoggingModuleEnabled()){
					summarySettingsPanel.add(clmDbConnectionSettingsPanel.getSummaryPanel(), gridBagConstraints40);
				}
			}

		    summarySettingsPanel.validate();
		//}
		return summarySettingsPanel;
	}

	/**
	 * This method initializes mainTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getMainTabbedPane() {
		if (mainTabbedPane == null) {
			mainTabbedPane = new JTabbedPane();
		
			mainTabbedPane.addTab("Deploy Type", null, new IconFeedbackPanel(this.validationModel, deployTypeSettingsPanel.getSettingsPanel()), null);
			mainTabbedPane.addTab("App Server", null, new IconFeedbackPanel(this.validationModel, appServerSettingsPanel.getSettingsPanel()), null);
			mainTabbedPane.addTab("App DB", null, new IconFeedbackPanel(this.validationModel, dbConnectionSettingsPanel.getSettingsPanel()), null);
			
			mainTabbedPane.addTab("Logging", null, new IconFeedbackPanel(this.validationModel, clmDbConnectionSettingsPanel.getSettingsPanel()), null);
			
			mainTabbedPane.addTab("CSM DB", null, new IconFeedbackPanel(this.validationModel, csmDbConnectionSettingsPanel.getSettingsPanel()), null);
			mainTabbedPane.addTab("caGrid Auth", null, new IconFeedbackPanel(this.validationModel, caGridAuthSettingsPanel.getSettingsPanel()), null);

			mainTabbedPane.addTab("Advanced", null, new IconFeedbackPanel(this.validationModel, advancedSettingsPanel.getSettingsPanel()), null);
			
			mainTabbedPane.addTab("Remote SSH", null, new IconFeedbackPanel(this.validationModel, remoteSshSettingsPanel.getSettingsPanel()), null);
			
			mainTabbedPane.addTab("Deploy Application", null, getSummarySettingsPanel(), null);
			
			mainTabbedPane.addTab("View Log", null, new IconFeedbackPanel(this.validationModel, logViewerPanel.getSettingsPanel()), null);
			
			mainTabbedPane.addMouseListener(new java.awt.event.MouseListener() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                	;//do nothing
                }
                public void mouseReleased(java.awt.event.MouseEvent e) {
                	;//do nothing
                }
                public void mouseExited(java.awt.event.MouseEvent e) {
                	;//do nothing
                }
                public void mousePressed(java.awt.event.MouseEvent e) {
                	validateInput();
                }
                public void mouseClicked(java.awt.event.MouseEvent e) {
                	try {
                		toggleUseDbConnectionsCheckBox();
                		validateInput();
                	} catch (Exception ex) {
                		ex.printStackTrace();
                	}
                }
			});
		}
		return mainTabbedPane;
	}

	public List<PanelValidator> getPanelValidators() {
		return panelValidators;
	}

	public ValidationResultModel getValidationModel() {
		return validationModel;
	}
	
	private Map<String,String> getDeployPropsMap(){
		Map<String,String> propsMap=new TreeMap<String,String>();
		
		// Deploy Type Server properties
		propsMap.putAll(deployTypeSettingsPanel.getPropsMap());

		// Application Server properties
		propsMap.putAll(appServerSettingsPanel.getPropsMap());
		
		// DB Connection properties
		propsMap.putAll(dbConnectionSettingsPanel.getPropsMap());
		
		// Common Logging Module DB Connection properties
		if (isClmEnabled()){
			propsMap.putAll(clmDbConnectionSettingsPanel.getPropsMap());
		}
		
		if (securitySettingsPanel.isSecurityEnabled()){
			// CSM DB Connection properties
			propsMap.putAll(csmDbConnectionSettingsPanel.getPropsMap());

			// caGrid Authentication properties
			propsMap.putAll(caGridAuthSettingsPanel.getPropsMap());

			String certFileName = caGridAuthSettingsPanel.getGridSecureCertFileName();
			if (certFileName != null && certFileName.length()>0 ){
				// Override the caGrid Certificate File property to point to the project generation 
				// build sub-directory, as we now copying the certificate file to this sub-directory
				String certFilePath =  (projectSettingsPanel.getProjectDir()
						+ TARGET_GRID_DIR
						+ caGridAuthSettingsPanel.getCaGridTargetGrid()
						+ "/"
						+ caGridAuthSettingsPanel.getGridSecureCertFileName()).replace('\\', '/');
				
				propsMap.put("grid.secure.cert.file", certFilePath );
			}

			String keyFileName = caGridAuthSettingsPanel.getGridSecureKeyFileName();
			if (keyFileName != null && keyFileName.length()>0 ){
				// Override the caGrid Certificate File property to point to the project generation 
				// build sub-directory, as we now copy the key file to this sub-directory
				String keyFilePath = (projectSettingsPanel.getProjectDir()
						+ TARGET_GRID_DIR
						+ caGridAuthSettingsPanel.getCaGridTargetGrid()
						+ "/"
						+ caGridAuthSettingsPanel.getGridSecureKeyFileName()).replace('\\', '/');
				
				propsMap.put("grid.secure.key.file", keyFilePath );
			}

		}

		// Advanced properties
		propsMap.putAll(advancedSettingsPanel.getPropsMap());
		
		// Remote SSH properties
		if (isRemoteDeployment())
			propsMap.putAll(remoteSshSettingsPanel.getPropsMap());
		
		// Retrieve and add 'Additional' deploy properties not managed by the Workbench but 
		// still required by the SDK deploy process
		Properties addlProps = new Properties();
		String serverType = appServerSettingsPanel.getServerType();
		if (serverType.equalsIgnoreCase(appServerSettingsPanel.JBOSS)){
			addlProps = ResourceManager.getJbossAdditionalDeployPropertiesManager().getDeployProperties();
		} else if (serverType.equalsIgnoreCase(appServerSettingsPanel.TOMCAT)){
			addlProps = ResourceManager.getTomcatAdditionalDeployPropertiesManager().getDeployProperties();
		}

		Iterator<Object> keys = addlProps.keySet().iterator();
		while (keys.hasNext())
		{
			String key = (String) keys.next();
			String value = (String) addlProps.get(key);
			propsMap.put(key, value);
		}
		
		//Determine whether or not to add exclude.database=true property, which should only be set 
		//if not dropping any DB schemas
		if (!dbConnectionSettingsPanel.isDbDropSchemaSelected() && 
				!csmDbConnectionSettingsPanel.isCsmDbDropSchemaSelected() &&
				!clmDbConnectionSettingsPanel.isDbDropSchemaSelected()){
			propsMap.put("exclude.database", "true");
		}
		
		return propsMap;
	}
	
	public void setDirty(boolean isDirty){
		this.isDirty = isDirty;
	}
	
	private void setPropsLoaded(boolean isPropsLoaded){
		this.isPropsLoaded = isPropsLoaded;
	}
	
	public WorkbenchPropertiesManager getPropertiesManager(){
		return this.propsMgr;
	}
	
	public boolean isLocalDeployment(){
		return deployTypeSettingsPanel.isLocalDeployment();
	}
	
	public boolean isRemoteDeployment(){
		return deployTypeSettingsPanel.isRemoteDeployment();
	}
} 
