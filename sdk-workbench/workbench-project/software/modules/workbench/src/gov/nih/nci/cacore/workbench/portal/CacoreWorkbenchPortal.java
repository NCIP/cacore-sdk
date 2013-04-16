/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.cacore.workbench.portal;

import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.common.WorkbenchCmdLineArgsManager;
import gov.nih.nci.cacore.workbench.portal.viewer.WorkbenchOverviewViewer;
import gov.nih.nci.cagrid.common.portal.SplashScreen;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.cagrid.grape.GridApplication;
import org.cagrid.grape.model.Application;

public final class CacoreWorkbenchPortal {
	
	private static final Logger log = Logger.getLogger(CacoreWorkbenchPortal.class);

	private static SplashScreen workbenchSplash;

	private static void showSdkSplash() {
		try {
			workbenchSplash = new SplashScreen("/images/workbenchSplashScreen.gif");
			// centers in screen
			workbenchSplash.setLocationRelativeTo(null);
			workbenchSplash.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initialize() {
		//needed for opening self-signed https sites from within the Workbench
		//todo :: unfortunately, when packaged in a jar file, the jssecacerts file is not found
		//        workaround still needed.
		System.setProperty("javax.net.ssl.trustStore","jssecacerts");
	}
	
	private static void showWorkbenchPortal(String[] args) {
		try {
			
			if (args != null && args.length > 0){
				String workbenchType = args[0];
				log.debug("* * * Workbench Type: " + workbenchType);
				WorkbenchCmdLineArgsManager.setWorkbenchType(workbenchType);
				
				if (args.length > 1){
					String sdkSrcUrl = args[1];
					log.debug("* * * SDK Src Url: " + sdkSrcUrl);
					WorkbenchCmdLineArgsManager.setSdkSrcUrl(sdkSrcUrl);
				}
				
				if (args.length > 2){
					String sdkSrcFile = args[2];
					log.debug("* * * SDK Src File: " + sdkSrcFile);
					WorkbenchCmdLineArgsManager.setSdkSrcFile(sdkSrcFile);
				}
			}
			
			initialize();
			showSdkSplash();

//			if (confFile == null) {
//				confFile = "/cacore-workbench-conf.xml";
//			}
			
			String confFile = "/cacore-workbench-conf.xml";

			Application app = (Application) Utils.deserializeDocument(confFile,
					Application.class);

			// launch the portal with the passed config
			GridApplication applicationInstance = GridApplication
					.getInstance(app);
			Dimension d = new Dimension(app.getDimensions().getWidth(), app
					.getDimensions().getHeight());
			
            WorkbenchOverviewViewer overviewViewer = new WorkbenchOverviewViewer();
            overviewViewer.setSize(new Dimension(app.getDimensions().getWidth()-18, app.getDimensions().getHeight()-88));
            overviewViewer.setPreferredSize(new Dimension(app.getDimensions().getWidth()-18, app.getDimensions().getHeight()-88));
            overviewViewer.setVisible(true);
            //overviewViewer.setMaximum(true);
            
//            log.debug("* * * " + applicationInstance.getContentPane().getLayout());
//            BorderLayout bl = (BorderLayout)applicationInstance.getContentPane().getLayout();
            
//            log.debug("* * * Component count: "+applicationInstance.getContentPane().getComponentCount());
//            int i = 0;
//            for(Component comp:applicationInstance.getContentPane().getComponents()){
//            	log.debug("* * * Component["+i+"]"+comp);
//            	i++;
//            }
            
//            javax.swing.JScrollPane jScrollPane = (JScrollPane)applicationInstance.getContentPane().getComponent(0);
            
            //applicationInstance.getMDIDesktopPane().add(comp, component.getDimensions(), component.getRenderOptions());
            //applicationInstance.getMDIDesktopPane().add(hv);
            applicationInstance.getMDIDesktopPane().add(overviewViewer);

			try {
				applicationInstance.pack();
			} catch (Exception e) {
				applicationInstance.setIconImage(null);
				applicationInstance.pack();
			}
			applicationInstance.setSize(d);
			applicationInstance.setVisible(true);
			applicationInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			applicationInstance.validate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final class WorkbenchSplashCloser implements Runnable {
		public void run() {
			try {
				workbenchSplash.dispose();
			} catch (Exception e) {

			}
		}
	}

	public static void main(String[] args) {
		showWorkbenchPortal(args);

		EventQueue.invokeLater(new WorkbenchSplashCloser());
	}
}
