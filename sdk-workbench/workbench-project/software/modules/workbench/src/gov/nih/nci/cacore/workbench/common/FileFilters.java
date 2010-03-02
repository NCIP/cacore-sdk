package gov.nih.nci.cacore.workbench.common;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public interface FileFilters extends gov.nih.nci.cagrid.introduce.common.FileFilters {

	public static final FileFilter ALL_FILTER = new AllFilter();
	public static final FileFilter LOG_FILTER = new LogFileFilter();	
	public static final FileFilter PEM_FILTER = new PEMFileFilter();
	public static final FileFilter SQL_FILTER = new SQLFileFilter();
	public static final FileFilter XMI_UML_FILTER = new XMIandUMLFileFilter();
	
	public class XMIandUMLFileFilter extends javax.swing.filechooser.FileFilter implements java.io.FileFilter {
		public boolean accept(File file) {
			String filename = file.getName();
			return file.isDirectory() || filename.endsWith(".xmi") || filename.endsWith(".uml");
		}

		public String getDescription() {
			return "XML Metadata Interchange Files (*.xmi) and ARGO UML Model Files (*.uml)";
		}
	}
	
	public class PEMFileFilter extends javax.swing.filechooser.FileFilter implements java.io.FileFilter {
		public boolean accept(File file) {
			String filename = file.getName();
			return file.isDirectory() || filename.endsWith(".pem");
		}

		public String getDescription() {
			return "Certificate and Key Files (*.pem)";
		}
	}
	
	public class SQLFileFilter extends javax.swing.filechooser.FileFilter implements java.io.FileFilter {
		public boolean accept(File file) {
			String filename = file.getName();
			return file.isDirectory() || filename.endsWith(".sql");
		}

		public String getDescription() {
			return "Database SQL Files (*.sql)";
		}
	}
	
	public class LogFileFilter extends javax.swing.filechooser.FileFilter implements java.io.FileFilter {
		public boolean accept(File file) {
			String filename = file.getName();
			return filename.endsWith(".log");
		}

		public String getDescription() {
			return "Workbench Log Files (*.log)";
		}
	}	
	
	public class AllFilter extends javax.swing.filechooser.FileFilter implements java.io.FileFilter {
		public boolean accept(File file) {
			return true;
		}

		public String getDescription() {
			return "All Files (*.*)";
		}
	}

}
