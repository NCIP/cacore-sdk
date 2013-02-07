package gov.nih.nci.restgen.ui.properties;

import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;

import javax.swing.JOptionPane;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Ashish Tyagi
 *
 */
public class StatusMessageAppender extends AppenderSkeleton {
    
    protected void append(LoggingEvent event) {
        if(event.getLevel().equals(Level.INFO)){
                //here set the text of your swing component;
               //in my case it is: statusBar.st_STATUS.setText(event.getMessage().toString());
        	JOptionPane.showMessageDialog(MappingMainPanel.getMainFrame().getAssociatedUIComponent(), event.getMessage().toString(), "Wrapper generator log...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void close() {

    }
    public boolean requiresLayout() {
        return false;
    }

}