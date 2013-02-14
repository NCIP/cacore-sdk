package gov.nih.nci.restgen.ui.properties;

import java.awt.Dimension;

import gov.nih.nci.restgen.ui.actions.GenerateRESTfulResourceAction;
import gov.nih.nci.restgen.ui.mapping.MappingMainPanel;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Prakash Vinjamuri
 *
 */
public class StatusMessageAppender extends AppenderSkeleton {
	
	JScrollPane scrollPane = null;
    
    protected void append(LoggingEvent event) {
        if(event.getLevel().equals(Level.INFO)||event.getLevel().equals(Level.ERROR)){
                
        	if(GenerateRESTfulResourceAction.getTextArea()!=null){
        		String displayText = GenerateRESTfulResourceAction.getTextArea().getText()+"\n"+event.getMessage().toString();
        		GenerateRESTfulResourceAction.getTextArea().setText(displayText);
        	}
        	
        }
    }

    public void close() {

    }
    public boolean requiresLayout() {
        return false;
    }

}