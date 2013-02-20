package gov.nih.nci.restgen.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import org.apache.commons.io.FileUtils;

/**
 * Provides utility services for jarring and unjarring files and directories.
 * Note that a given instance of JarHelper is not threadsafe with respect to
 * multiple jar operations.
 * 
 * @author Patrick Calahan <pcal@bea.com>
 */
public class JarHelper {
  // Constants

  private static final int BUFFER_SIZE = 2156;

  // Variables

  private byte[] mBuffer = new byte[BUFFER_SIZE];

  private int mByteCount = 0;

  private boolean mVerbose = false;

  private String mDestJarName = "";

  // Constructor

  /**
   * Instantiates a new JarHelper.
   */
  public JarHelper() {
  }

  // Public methods

  /**
   * Jars a given directory or single file into a JarOutputStream.
   */
  public void jarDir(File dirOrFile2Jar, File destJar) throws IOException {

    if (dirOrFile2Jar == null || destJar == null)
      throw new IllegalArgumentException();

    mDestJarName = destJar.getCanonicalPath();
    FileOutputStream fout = new FileOutputStream(destJar);
    JarOutputStream jout = new JarOutputStream(fout);
    // jout.setLevel(0);
    try {
      jarDir(dirOrFile2Jar, jout, null);
    } catch (IOException ioe) {
      throw ioe;
    } finally {
      jout.close();
      fout.close();
    }
  }

  /**
   * Unjars a given jar file into a given directory.
   */
  public void unjarDir(File jarFile, File destDir) throws IOException {
    BufferedOutputStream dest = null;
    FileInputStream fis = new FileInputStream(jarFile);
    unjar(fis, destDir);
  }

  /**
   * Given an InputStream on a jar file, unjars the contents into the given
   * directory.
   */
  public void unjar(InputStream in, File destDir) throws IOException {
    BufferedOutputStream dest = null;
    JarInputStream jis = new JarInputStream(in);
    JarEntry entry;
    while ((entry = jis.getNextJarEntry()) != null) {
      if (entry.isDirectory()) {
        File dir = new File(destDir, entry.getName());
        dir.mkdir();
        if (entry.getTime() != -1)
          dir.setLastModified(entry.getTime());
        continue;
      }
      int count;
      byte data[] = new byte[BUFFER_SIZE];
      File destFile = new File(destDir, entry.getName());
      if (mVerbose)
        System.out.println("unjarring " + destFile + " from " + entry.getName());
      FileOutputStream fos = new FileOutputStream(destFile);
      dest = new BufferedOutputStream(fos, BUFFER_SIZE);
      while ((count = jis.read(data, 0, BUFFER_SIZE)) != -1) {
        dest.write(data, 0, count);
      }
      dest.flush();
      dest.close();
      if (entry.getTime() != -1)
        destFile.setLastModified(entry.getTime());
    }
    jis.close();
  }

  
  public void unjar(File jarFile, String destdir) throws IOException
  {
	    java.util.jar.JarFile jarfile = new java.util.jar.JarFile(jarFile);
	    java.util.Enumeration<java.util.jar.JarEntry> enu= jarfile.entries();
	    while(enu.hasMoreElements())
	    {
	        java.util.jar.JarEntry je = enu.nextElement();

	        java.io.File fl = new java.io.File(destdir + File.separator + je.getName());
	        if(!fl.exists())
	        {
	            fl.getParentFile().mkdirs();
	            fl = new java.io.File(destdir + File.separator +  je.getName());
	        }
	        if(je.isDirectory())
	        {
	            continue;
	        }
	        java.io.InputStream is = jarfile.getInputStream(je);
	        java.io.FileOutputStream fo = new java.io.FileOutputStream(fl);
	        while(is.available()>0)
	        {
	            fo.write(is.read());
	        }
	        fo.close();
	        is.close();
	    }	  
  }
  
  public void setVerbose(boolean b) {
    mVerbose = b;
  }

  // Private methods

  private static final char SEP = '/';

  /**
   * Recursively jars up the given path under the given directory.
   */
  private void jarDir(File dirOrFile2jar, JarOutputStream jos, String path) throws IOException {
    if (mVerbose)
      //System.out.println("checking " + dirOrFile2jar);
    if (dirOrFile2jar.isDirectory()) {
      String[] dirList = dirOrFile2jar.list();
      String subPath = (path == null) ? "" : (path + dirOrFile2jar.getName() + SEP);
      if (path != null) {
        JarEntry je = new JarEntry(subPath);
        je.setTime(dirOrFile2jar.lastModified());
        jos.putNextEntry(je);
        jos.flush();
        jos.closeEntry();
      }
      for (int i = 0; i < dirList.length; i++) {
        File f = new File(dirOrFile2jar, dirList[i]);
        jarDir(f, jos, subPath);
      }
    } else {
      if (dirOrFile2jar.getCanonicalPath().equals(mDestJarName)) {
        if (mVerbose)
          //System.out.println("skipping " + dirOrFile2jar.getPath());
        return;
      }

      if (mVerbose)
        System.out.println("adding " + dirOrFile2jar.getPath());
      FileInputStream fis = new FileInputStream(dirOrFile2jar);
      try {
        JarEntry entry = new JarEntry(path + dirOrFile2jar.getName());
        entry.setTime(dirOrFile2jar.lastModified());
        jos.putNextEntry(entry);
        while ((mByteCount = fis.read(mBuffer)) != -1) {
          jos.write(mBuffer, 0, mByteCount);
          if (mVerbose)
            System.out.println("wrote " + mByteCount + " bytes");
        }
        jos.flush();
        jos.closeEntry();
      } catch (IOException ioe) {
        throw ioe;
      } finally {
        fis.close();
      }
    }
  }

  public void removeJarEntry(String jarPathName, String deletePathName, String outputPath) throws IOException
  {
	  File jarFile = new File(jarPathName);
	  File destDir = new File(outputPath);
	  if(!destDir.exists())
		  destDir.mkdirs();
	  //unjarDir(jarFile, destDir);
	  unjar(jarFile,outputPath);
	  File deleteFolder = new File(deletePathName);
	  FileUtils.deleteDirectory(deleteFolder);
	  
	  File dirOrFile2Jar = new File(outputPath);
	  File destJar = new File(jarPathName);
	  jarDir(dirOrFile2Jar, destJar);
	  FileUtils.deleteDirectory(destDir);
  }
  
  // for debugging
  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.err.println("Usage: JarHelper jarname.jar directory");
      return;
    }

    JarHelper jarHelper = new JarHelper();
    jarHelper.mVerbose = true;

    File destJar = new File(args[0]);
    File dirOrFile2Jar = new File(args[1]);

    jarHelper.jarDir(dirOrFile2Jar, destJar);
  }
  
}