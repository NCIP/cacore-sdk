package gov.nih.nci.cacore.workbench.portal;

import gov.nih.nci.cacore.workbench.common.Utils;
import gov.nih.nci.cacore.workbench.common.WorkbenchCmdLineArgsManager;
import gov.nih.nci.cagrid.common.portal.SplashScreen;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.cagrid.grape.GridApplication;
import org.cagrid.grape.model.Application;

public final class CacoreWorkbenchPortal {
	
	private static final Logger log = Logger.getLogger(CacoreWorkbenchPortal.class);

	private static SplashScreen sdkSplash;

	private static void showSdkSplash() {
		try {
			sdkSplash = new SplashScreen("/images/sdkSplashScreen.gif");
			// centers in screen
			sdkSplash.setLocationRelativeTo(null);
			sdkSplash.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initialize() {

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

			try {
				applicationInstance.pack();
			} catch (Exception e) {
				applicationInstance.setIconImage(null);
				applicationInstance.pack();
			}
			applicationInstance.setSize(d);
			applicationInstance.setVisible(true);
			applicationInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static final class SdkSplashCloser implements Runnable {
		public void run() {
			try {
				sdkSplash.dispose();
			} catch (Exception e) {

			}
		}
	}

	public static void main(String[] args) {
		showWorkbenchPortal(args);

		EventQueue.invokeLater(new SdkSplashCloser());
	}

}
