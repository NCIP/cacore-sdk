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
        if(event.getLevel().equals(Level.INFO)){
                //here set the text of your swing component;
               //in my case it is: statusBar.st_STATUS.setText(event.getMessage().toString());
        	/*MappingMainPanel.getTextArea().setText(event.getMessage().toString());
        	if(scrollPane==null){
        		scrollPane = new JScrollPane(MappingMainPanel.getTextArea());
        	}
        	MappingMainPanel.getTextArea().setLineWrap(true);  
        	MappingMainPanel.getTextArea().setWrapStyleWord(true); 
        	scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        	JOptionPane.showMessageDialog(MappingMainPanel.getMainFrame().getAssociatedUIComponent(), scrollPane, "Wrapper generator log...",  
        	                                       JOptionPane.INFORMATION_MESSAGE);
        	MappingMainPanel.getTextArea().setText(event.getMessage().toString());
        	*/
        	if(GenerateRESTfulResourceAction.getTextPane()!=null){
        		String displayText = GenerateRESTfulResourceAction.getTextPane().getText()+"\n"+event.getMessage().toString();
        		GenerateRESTfulResourceAction.getTextPane().setText(displayText);
        	}
        	
        	
        	
        }
    }

    public void close() {

    }
    public boolean requiresLayout() {
        return false;
    }

}