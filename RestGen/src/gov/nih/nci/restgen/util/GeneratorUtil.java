package gov.nih.nci.restgen.util;

import gov.nih.nci.restgen.codegen.GeneratorContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

public class GeneratorUtil {
	public static void writeFile(String _outputDir, String _fileName,
			String _content) {
		BufferedWriter bufferedWriter = null;

		try {
			createOutputDir(_outputDir);
			File file = new File(_outputDir, _fileName);
			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(_content);
			bufferedWriter.flush();
		} catch (Throwable t) {
			throw new RuntimeException(t);
		} finally {
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (Throwable t) {
				}
			}
		}
	}

	public static void createOutputDir(String _outputDir) {
		try {
			File file = new File(_outputDir);

			if (!file.exists() == true) {
				FileUtils.forceMkdir(file);
			}
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	public static List getFiles(String _dir, String[] _extensions) {
		java.util.logging.Logger.getLogger("DEBUG").info(
				"Directory _dir is: " + _dir);

		List<String> files = new ArrayList();

		try {
			Iterator iter = FileUtils.iterateFiles(new File(_dir), _extensions,
					true);

			while (iter.hasNext() == true) {
				File file = (File) iter.next();
				files.add(file.getAbsolutePath());
			}
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}

		java.util.logging.Logger.getLogger("DEBUG").info(
				"Returning these files: " + files);

		return files;
	}

	public static String getFiles(String _dir, String[] _extensions,
			String _separator) {
		java.util.logging.Logger.getLogger("DEBUG").info(
				"Directory _dir is: " + _dir);

		StringBuffer files = new StringBuffer();

		try {
			Iterator iter = FileUtils.iterateFiles(new File(_dir), _extensions,
					false);

			while (iter.hasNext() == true) {
				File file = (File) iter.next();
				files.append(file.getAbsolutePath()).append(_separator);
			}
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}

		java.util.logging.Logger.getLogger("DEBUG").info(
				"Returning these files: " + files);

		return files.toString().replaceAll(":$", "");
	}

	public static String convertFirstCharToUpperCase(String _string) {
		String capitalizedString = _string;

		if (_string != null && _string.length() > 0) {
			capitalizedString = _string.substring(0, 1).toUpperCase();

			if (_string.length() > 1) {
				capitalizedString += _string.substring(1, _string.length());
			}
		}

		return capitalizedString;
	}

	public static String convertFirstCharToLowerCase(String _string) {
		String capitalizedString = _string;

		if (_string != null && _string.length() > 0) {
			capitalizedString = _string.substring(0, 1).toLowerCase();

			if (_string.length() > 1) {
				capitalizedString += _string.substring(1, _string.length());
			}
		}

		return capitalizedString;
	}

	public static boolean compileJavaSource(String srcFolder, String destFolder,
			String libFolder, GeneratorContext context) {
		StandardJavaFileManager fileManager = null;
		boolean success = true;
		try {
			List<String> compilerFiles = GeneratorUtil.getFiles(srcFolder,
					new String[] { "java" });

			GeneratorUtil.createOutputDir(destFolder);

			List<String> options = new ArrayList<String>();
			options.add("-classpath");
			String classPathStr = GeneratorUtil.getFiles(libFolder,
					new String[] { "jar" }, File.pathSeparator);

			options.add(classPathStr);

			options.add("-d");
			options.add(destFolder);

			options.add("-s");
			options.add(srcFolder);

			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			fileManager = compiler.getStandardFileManager(diagnostics, null,
					null);
			Iterable<? extends JavaFileObject> compilationUnits = fileManager
					.getJavaFileObjectsFromStrings(compilerFiles);
			JavaCompiler.CompilationTask task = compiler.getTask(null,
					fileManager, diagnostics, options, null, compilationUnits);
			success = task.call();

			for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
				context.getLogger().info(diagnostic.getCode());
				context.getLogger().info(diagnostic.getKind().toString());
				context.getLogger().info(diagnostic.getPosition() + "");
				context.getLogger().info(diagnostic.getStartPosition() + "");
				context.getLogger().info(diagnostic.getEndPosition() + "");
				context.getLogger().info(diagnostic.getSource().toString());
				context.getLogger().info(diagnostic.toString());
			}
		} catch (Throwable t) {
			context.getLogger().error("Error compiling java code", t);
		} finally {
			try {
				fileManager.close();
			} catch (Throwable t) {
			}
		}
		return success;
	}

	public static void copyDir(String srcFolderStr, String destFolderStr)
			throws IOException {
		File srcFolder = new File(srcFolderStr);
		File destFolder = new File(destFolderStr);

		if (srcFolder.isDirectory()) {

			// if directory not exists, create it
			if (!destFolder.exists()) {
				destFolder.mkdir();
				System.out.println("Directory copied from " + srcFolder
						+ "  to " + destFolder);
			}
			FileUtils.copyDirectoryToDirectory(srcFolder, destFolder);
		}
	}

	public static void copyFiles(String srcFolderStr, String destFolderStr)
			throws IOException {
		File srcFolder = new File(srcFolderStr);
		File destFolder = new File(destFolderStr);

		if (srcFolder.isDirectory()) {

			// if directory not exists, create it
			if (!destFolder.exists()) {
				destFolder.mkdir();
				System.out.println("Directory copied from " + srcFolder
						+ "  to " + destFolder);
			}

			String files[] = srcFolder.list();

			for (String file : files) {
				File srcFile = new File(srcFolder, file);
				File destFile = new File(destFolder, file);
				copyFile(srcFile, destFile);
			}

		}
	}

	public static void copyFile(File src, File dest) throws IOException {
		// if file, then copy it
		// Use bytes stream to support all file types
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dest);

		byte[] buffer = new byte[1024];

		int length;
		// copy the file content in bytes
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}

		in.close();
		out.close();
		System.out.println("File copied from " + src + " to " + dest);

	}
}