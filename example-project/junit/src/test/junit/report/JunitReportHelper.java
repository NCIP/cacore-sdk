package test.junit.report;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import test.junit.report.TestSuite;

public class JunitReportHelper{
	
	public static String getStringFromDocument(Node doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static Document createDocument(File file)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(file);
		return doc;
	}
	
	public static List<File> process(File directory,List<File> files) {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File directory, String name) {
				return name.endsWith(".xml");
			}
		};
		File[] tempfiles = directory.listFiles(filter);
		if(files==null){
			files=new ArrayList<File>();
		}
		if(tempfiles!=null && tempfiles.length>0){
				files.addAll(Arrays.asList(tempfiles));
		}
		return files;
	}
	
	
    public static void visitAllDirsAndFiles(File dir,List<File> files) {
		files=process(dir,files);

		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitAllDirsAndFiles(new File(dir, children[i]),files);
			}
		}
	}

	public static StringBuffer updateDocument(Document inputDocument,TestSuite testSuite) throws Exception {
		
		StringBuffer buffer = new StringBuffer();
		NodeList list = inputDocument.getElementsByTagName(TestSuite.TEST_SUITE_TAG);

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);
			testSuite.setErrors(element.getAttribute(TestSuite.TEST_SUITE_ERRORS));
			testSuite.setFailures(element.getAttribute(TestSuite.TEST_SUITE_FAILURES));
			testSuite.setTests(element.getAttribute(TestSuite.TEST_SUITE_TESTS));
			testSuite.setTime(element.getAttribute(TestSuite.TEST_SUITE_TIME));
			testSuite.setTimeStamp(element.getAttribute(TestSuite.TEST_SUITE_TIME_STAMP));
			
			NodeList testCaseNodes = element.getElementsByTagName(TestSuite.TEST_CASE_TAG);
			for (int j = 0; j < testCaseNodes.getLength(); j++) {
				Element testCase = (Element) testCaseNodes.item(j);
				String testCaseClassName = testCase.getAttribute(TestSuite.TEST_CASE_CLASS_NAME);
				testCase.setAttribute(TestSuite.TEST_CASE_CLASS_NAME, testSuite.getName() + "."+ testCaseClassName);
				String testCaseElement = getStringFromDocument(testCase);
				buffer.append(testCaseElement.substring(38));
			}
		}
		return buffer;
	}

	public static void writeTOFile(File outputFile,StringBuffer masterXMLBuffer)
			throws FileNotFoundException, IOException {
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		outputStream.write(masterXMLBuffer.toString().getBytes());
		outputStream.close();
	}
	
	
	public static String getTestCaseName(String junitOutputDir){
		
		StringTokenizer tokenizer=new StringTokenizer(junitOutputDir,"\\");
		int countTokens=tokenizer.countTokens();
		int i=0;
		String testCaseName="";
		while(tokenizer.hasMoreTokens()){
			if(i==(countTokens-1)){
				testCaseName=testCaseName+"."+tokenizer.nextElement();
			}else if(i==(countTokens-2)){
				testCaseName=""+tokenizer.nextElement();
			}else{
				tokenizer.nextElement();
			}
			i++;
		}
		return testCaseName;
	}
	
	public static void execute(File inputFilesDir,File junitReportXmlFile) throws ParserConfigurationException,
			SAXException, IOException, Exception, FileNotFoundException {
		

		List<File> files =new ArrayList<File>();
		JunitReportHelper.visitAllDirsAndFiles(inputFilesDir,files);
		
		StringBuffer masterXMLBuffer = new StringBuffer();
		masterXMLBuffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
	
		StringBuffer tempXMLBuffer = new StringBuffer();
		
		
		TestSuite suite=new TestSuite();
		String testCaseName = getTestCaseName(inputFilesDir.getAbsolutePath());
		suite.setName(testCaseName);
		for (int i = 0; i < files.size(); i++) {
			Document doc = JunitReportHelper.createDocument(files.get(i));
			StringBuffer buffer = JunitReportHelper.updateDocument(doc,suite);
			tempXMLBuffer.append(buffer);
		}
		masterXMLBuffer.append(suite.toString());
		masterXMLBuffer.append(tempXMLBuffer);
		masterXMLBuffer.append("</testsuite>");
		
		writeTOFile(junitReportXmlFile,masterXMLBuffer);
	}
	
	public static void main(String[] args) throws Exception {
		if(args==null || args.length<2){
			System.err.println("input junit reports dir && Junit Report OutputFile not Specified");
			return;
		}
		ClassLoader loader=JunitReportHelper.class.getClassLoader();
		
		File inputXmlFilesdir=new File(args[0]);
		File junitReportXmlFile=new File(args[1]);
		System.out.println(args[0]);
		System.out.println(args[1]);
		//File inputXmlFilesdir = new File("C:/devroot/sdk-svn/example-project/junit/report/defaultTest/remote-client/");
		//File junitReportXmlFile=new File("C:/devroot/sdk-svn/example-project/junit/report/defaultTest/remote.xml");
		execute(inputXmlFilesdir,junitReportXmlFile);
		//getTestCaseName(inputXmlFilesdir.getAbsolutePath());
	}
 }