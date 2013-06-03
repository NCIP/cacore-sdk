/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.restgen.ui.main;

import static org.junit.Assert.*;
import gov.nih.nci.restgen.ui.main.MainFrame;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.junit.Before;
import org.junit.Test;

public class MainFrameTest{
	
	
	@Before
	public void setup() {
		MainFrameContainer frame = MainFrame.getInstanceContainer();    
		try
		{
			try
			{
				
                String os = System.getProperty("os.name");
             
                if (os.toLowerCase().startsWith("windows"))
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                else
                    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
			catch (ClassNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (InstantiationException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (IllegalAccessException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (UnsupportedLookAndFeelException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			frame.getMainFrame().launch();
			
		}
		catch (Throwable t)
		{
			t.printStackTrace();
			//	        Log.logException(new Object(), t);
		}
		
	}


	
	@Test
	public void testMain() {
		
		
	}

}
