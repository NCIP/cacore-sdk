/**
 * The content of this file is subject to the caCore SDK Software License (the "License").  
 * A copy of the License is available at:
 * [caCore SDK CVS home directory]\etc\license\caCore SDK_license.txt. or at:
 * http://ncicb.nci.nih.gov/infrastructure/cacore_overview/caCore SDK/indexContent
 * /docs/caCore SDK_License
 */

package gov.nih.nci.restgen.ui.actions;

import gov.nih.nci.restgen.ui.common.ActionConstants;
import gov.nih.nci.restgen.ui.common.DefaultSettings;
import gov.nih.nci.restgen.ui.dnd.TreeTransferHandler;
import gov.nih.nci.restgen.ui.main.MainFrame;
import gov.nih.nci.restgen.ui.main.MainFrameContainer;
import gov.nih.nci.restgen.ui.tree.DefaultSourceTreeNode;
import gov.nih.nci.restgen.ui.tree.TreeMouseAdapter;
import gov.nih.nci.restgen.ui.tree.TreeSelectionHandler;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class defines the OpenPOJOJar action.
 * Descendant classes may implement additional functions.
 *
 * @author Prakash Vinjamuri
 * @author Prakash Vinjamuri LAST UPDATE $Author:
 * @since     CMTS v1.0
 * @version    $Revision: 1.2 $
 * @date       $Date: 2013-01-11
 */
public class OpenPOJOJarAction extends AbstractContextAction
{
	protected static final String COMMAND_NAME = ActionConstants.OPENPOJO;
	protected static final Character COMMAND_MNEMONIC = new Character('O');
	public static final String TOOL_TIP_DESCRIPTION = "Open POJO Jar";
    private static final String OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE = "Open source POJO Jar";
	private static final String SOURCE_TREE_FILE_DEFAULT_EXTENTION = ".jar";
	private static final HashSet<String> WRAPPER_TYPES = getWrapperTypes();
	private  String errorString = null; 
    private boolean forceClose = false;

    protected MainFrameContainer ownerFrame = null;
    
    private JTree tree;

	public OpenPOJOJarAction(MainFrameContainer owner)
	{
		this(COMMAND_NAME, owner);
	}

	public OpenPOJOJarAction(String name, MainFrameContainer owner)
	{
		this(name, null, owner);
	}

	public OpenPOJOJarAction(String name, Icon icon, MainFrameContainer owner)
	{
		super(name, icon);
		ownerFrame = owner;
		setAdditionalAttributes();
	}

	protected void setAdditionalAttributes()
	{//override super class's one to plug in its own attributes.
		setMnemonic(COMMAND_MNEMONIC);
		setActionCommandType(DOCUMENT_ACTION_TYPE);
        setShortDescription(TOOL_TIP_DESCRIPTION);
    }

	protected void setFrame(Component newFrame)
	{
		if (newFrame instanceof MainFrame)
		{
			ownerFrame = new MainFrameContainer((MainFrame)newFrame);
		}
        
    }

	/**
	 * The abstract function that descendant classes must be overridden to provide customsized handling.
	 *
	 * @param e
	 * @return true if the action is finished successfully; otherwise, return false.
	 */
	protected boolean doAction(ActionEvent e) throws Exception
	{
	
		// open POJO here PV
			File file = null;
			if(getErrorString()!=null)
			{
				setErrorString(null);
			}
            file = DefaultSettings.getUserInputOfFileFromGUI(ownerFrame.getOwnerFrame(), //FileUtil.getUIWorkingDirectoryPath(),
                    SOURCE_TREE_FILE_DEFAULT_EXTENTION, OPEN_DIALOG_TITLE_FOR_DEFAULT_SOURCE_FILE, false, false);
            if ((file == null)||(!file.exists())||(!file.isFile())) return true;
            if (!file.getName().toLowerCase().endsWith(SOURCE_TREE_FILE_DEFAULT_EXTENTION.toLowerCase()))
            {
                JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), "This file is not a POJO Jar file (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + file.getName(), "Not a POJO Jar file", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            ownerFrame.getMainFrame().getMappingMainPanel().setMappingSourceFile(file);
            ownerFrame.getMainFrame().getMappingMainPanel().setSourceFileType("POJOJAR");
            if(ownerFrame.getMainFrame().getMappingMainPanel().getTargetTree()!=null)
    		  {
            	ownerFrame.getMainFrame().getMappingMainPanel().getMiddlePanel().getGraphController().handleDeleteAll();
            	
    		  }	
            createSourceTree(file);
      return true;
	}

	
	public void createSourceTree(File file) throws Exception
	{
		
	      JarFile jar = new JarFile(file);
          Enumeration<?> en = jar.entries();
          ArrayList<String> classList = new ArrayList<String>();
          boolean containsClassFile = false;
          boolean isValidPOJOClass = false;
          String className = "";
            		while (en.hasMoreElements()) {
            			JarEntry entry = (JarEntry) en.nextElement();
            			isValidPOJOClass = false;
            			if (entry.getName().endsWith(".class")) {
            				InputStream input = jar.getInputStream(entry);
            				StringTokenizer st = new StringTokenizer(entry.getName(),"/");
            				System.out.println("---- Split by / ------");
            				if(st.countTokens()>0)
            				{
            				while (st.hasMoreElements()) {
            					String tempclassName = (String)st.nextElement();
            					if(tempclassName.endsWith(".class"))
            					{
            						className = tempclassName;
            					}
            				}
            				}
            				else
            				{
            					className = entry.getName();
            				}
            				ClassParser cp = new ClassParser(input,entry.getName());
            			    JavaClass javaClass = cp.parse();
            			    if(javaClass.isEnum() || javaClass.isInterface() || javaClass.isAbstract())
            			    {
            			    	continue;
            			    }
            				isValidPOJOClass = validatePOJOClass(input,javaClass,entry.getName(),file);
            				if(!isValidPOJOClass)
            				{
         						String errorString = "This class in the Jar file : " + entry.getName()+" is not a POJO class"+"\n";
            					if(getErrorString()==null)
            					{
            						setErrorString(errorString);
            						//JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), "This class in the Jar file : " + entry.getName()+ "","", JOptionPane.ERROR_MESSAGE);
            					}
            					else
            					{
            						String currentString = getErrorString();
            						setErrorString(currentString+"\n"+errorString);
            					}
            				 
            				}
            				else
            				{
            					classList.add(className.replace(".class", ""));
            					containsClassFile = true;
            				}

            			}

       		}
            if(getErrorString()!=null)
            {
            	JTextArea textArea = new JTextArea(getErrorString());
            	JScrollPane scrollPane = new JScrollPane(textArea);  
            	textArea.setLineWrap(true);  
            	textArea.setWrapStyleWord(true); 
            	scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
            	JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), scrollPane, "POJO Validation Errors",  
            	                                       JOptionPane.ERROR_MESSAGE);
  	   
                 	   
            } 		
           if(!containsClassFile || classList.isEmpty())
           {
        	   String errorString = "This file is not a POJO Jar File: " + file.getName()+", Does not contain POJO classes"+"\n";
        	   JOptionPane.showMessageDialog(ownerFrame.getMainFrame(),errorString,"", JOptionPane.ERROR_MESSAGE);
        	   return;
        	  
           }
           else
           {
          	 ownerFrame.getMainFrame().getMappingMainPanel().setPOJOClassList(classList);
           }
            
           
          /// form the tree here PV...start
          
              DefaultSourceTreeNode top = new DefaultSourceTreeNode(file.getName());
              top.setResourceLocation(file.getPath());
              top.setResourcePathLocation("");
              createNodes(top,classList);
              tree = new JTree(top);
              TreeSelectionHandler treeSelectionHanderl=new TreeSelectionHandler(ownerFrame.getMainFrame().getMappingMainPanel().getGraphController());
              tree.getSelectionModel().addTreeSelectionListener(treeSelectionHanderl);
              tree.addMouseListener(new TreeMouseAdapter(ownerFrame,tree));
      		tree.setTransferHandler(new TreeTransferHandler(ownerFrame.getMainFrame().getMappingMainPanel()));
      		tree.setDropMode(DropMode.ON);
      		tree.setDragEnabled(true);
  			tree.setDragEnabled(true);
  			int size = tree.getRowCount();
  			for (int i = 0; i < size+1000; i++)
  			{
  				if (i<tree.getRowCount())
  					tree.expandRow(i);
  			}
  			ownerFrame.getMainFrame().getMappingMainPanel().getSourceScrollPane().setViewportView(tree);
  			ownerFrame.getMainFrame().getMappingMainPanel().setSourceTree(tree);
  			if(!ownerFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("Close").isEnabled())
  			{
  				ownerFrame.getMainFrame().getFrameMenu().getDefinedMenuItem("Close").setEnabled(true);
  			}
              
          /// end
          

		
	}
	
	
	
	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	private void createNodes(DefaultSourceTreeNode top,ArrayList<String> list) {
		
	    DefaultSourceTreeNode Createclass = null;
	    DefaultSourceTreeNode Updateclass = null;
	    DefaultSourceTreeNode Readclass = null;
	    DefaultSourceTreeNode Deleteclass = null;
	    Iterator<String> it = list.iterator();
	    while(it.hasNext())
	    {
	    	String resourceName = (String)it.next();
	    	if(resourceName.contains(".class")){
				resourceName = resourceName.replace(".class", "");
			}
	    	System.out.println("resource name....."+resourceName);
	    	DefaultSourceTreeNode element = new DefaultSourceTreeNode(resourceName);
	    	Createclass = new DefaultSourceTreeNode("Create(PUT)");
	    	
	    	Createclass.setResourceLocation(top.getResourceLocation());
	    	Createclass.setResourceName(resourceName);
	    	Createclass.setResourcePathLocation(top.getResourcePathLocation());
	    	element.add(Createclass);
	    
	    	Updateclass = new DefaultSourceTreeNode("Update(POST)");
	    	Updateclass.setResourceLocation(top.getResourceLocation());
	    	Updateclass.setResourceName(resourceName);
	    	Updateclass.setResourcePathLocation(top.getResourcePathLocation());
	    	element.add(Updateclass);
	    
	    	Readclass = new DefaultSourceTreeNode("Read(GET)");
	    	Readclass.setResourceLocation(top.getResourceLocation());
	    	Readclass.setResourceName(resourceName);
	    	Readclass.setResourcePathLocation(top.getResourcePathLocation());
	    	element.add(Readclass);
	    
	    	Deleteclass = new DefaultSourceTreeNode("Delete(DELETE)");
	    	Deleteclass.setResourceLocation(top.getResourceLocation());
	    	Deleteclass.setResourceName(resourceName);
	    	Deleteclass.setResourcePathLocation(top.getResourcePathLocation());
	    	element.add(Deleteclass);
	    	top.setResourceName(resourceName);
	    	top.add(element);
	    }
	}

	/**
	 * Return the associated UI component.
	 *
	 * @return the associated UI component.
	 */
	protected Component getAssociatedUIComponent()
	{
		return ownerFrame.getAssociatedUIComponent();
	}

	
	  // validate and parse the POJO class here
    public boolean validatePOJOClass(InputStream is,JavaClass javaClass, String classFile,File file)throws Exception
    {
    boolean validatePOJOMethods = false;	
     
     for(Field field : javaClass.getFields()){
  	   validatePOJOMethods = false;
  	   System.out.println("field class type....******"+field.getType());
  	 if(!isWrapperType(field.getType().toString()))
	   {
  		 
  		if(!checkForJarEntry(field.getType().toString(), file))
  		{ 
  		String errorString = "Class Contains non-primitive java types field : " + field.getType().toString()+"\n"; 
  		if(getErrorString()==null)
		{
			
			setErrorString(errorString);
			//JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), "This class in the Jar file : " + entry.getName()+ "","", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			String currentString = getErrorString();
			setErrorString(getErrorString()+"\n"+errorString);
		}
		  // JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), "This file Contains non-primitive java types (" + SOURCE_TREE_FILE_DEFAULT_EXTENTION + ") file : " + field.getType().toString(), "Not a POJO class file", JOptionPane.ERROR_MESSAGE);
		   break;
		   
  		}  
	   }
  	      
  	   for(Method method : javaClass.getMethods()){
  	   	   String fieldCompare = "get"+field.getName();
  		   if(fieldCompare.equalsIgnoreCase(method.getName())||field.getName().contains("serialVersionUID"))
  		   {
  			   
  			   validatePOJOMethods = true;
  			   break;
  		   }
  		  
  	   }
  	  if(!validatePOJOMethods)
  	  {
  		  
  		String errorString = "Class does not define get/set method for java field : " + field.getName()+classFile+"\n";
  	    
  		if(getErrorString()==null)
		{
			
			setErrorString(errorString);
			//JOptionPane.showMessageDialog(ownerFrame.getMainFrame(), "This class in the Jar file : " + entry.getName()+ "","", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			String currentString = getErrorString();
			setErrorString(getErrorString()+errorString);
		}
  		 break; 
  	  }
  	  
     }
     
     return validatePOJOMethods;
     
     // validate and parse the POJO class here
	
    }
  
    private boolean checkForJarEntry(String className, File file) throws IOException,Exception
    {
    	boolean isPresent = false;
    	JarFile jar = new JarFile(file);
    	className = className.replace(".","/");
        Enumeration<?> en = jar.entries();
        while (en.hasMoreElements()) {
        	JarEntry entry = (JarEntry) en.nextElement();
        	if (entry.getName().contains(className+".class")) {
  				isPresent = true;
  				break;
  			}
        }
    	return isPresent;
    }
    
    public static boolean isWrapperType(String clazz)
    {
    	if(clazz.contains("java.")||clazz.contains("javax."))
		{
			return true;
		}
        return WRAPPER_TYPES.contains(clazz);
    }

    private static HashSet<String> getWrapperTypes()
    {
        HashSet<String> ret = new HashSet<String>();
        ret.add("boolean");
        ret.add("character");
        ret.add("char");
        ret.add("byte");
        ret.add("short");
        ret.add("int");
        ret.add("integer");
        ret.add("long");
        ret.add("float");
        ret.add("double");
        ret.add("void");
        return ret;
    }
    
}

/**
 * HISTORY      : $Log: not supported by cvs2svn $
 * HISTORY      : Revision 1.1  2008/12/09 19:04:17  linc
 * HISTORY      : First GUI release
 * HISTORY      :
 * HISTORY      : Revision 1.1  2008/12/03 20:46:14  linc
 * HISTORY      : UI update.
 * HISTORY      :
 */
