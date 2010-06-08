package test.gov.nih.nci.system.web.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.util.SystemConstant;
import gov.nih.nci.system.web.util.HTTPUtils;

import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HttpUtilsTest extends TestCase{

	private ApplicationService applicationService;
	private ClassCache classCache;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		String paths[] = { "application-config.xml", };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		applicationService = (ApplicationService) ctx.getBean("ApplicationServiceImpl");
		classCache = (ClassCache) ctx.getBean("ClassCache");
	}
	
	public void testExampleIDQuery() throws Exception{
		String queryText="query=Bank&Credit[@id=3]";
		process(queryText,"test1");
	}
	
	public void testExampleMultipleAssociation() throws Exception{
		String queryText="query=Card,Suit&Deck[@id=1]";
		process(queryText,"test1");
	}
	
	public void testExampleMultipleAssociation2() throws Exception{
		String queryText="query=Deck,Suit&Card[@id=1]";
		process(queryText,"test1");
	}
	
	public void testExampleAssociationWithDelimLeftBracket() throws Exception{
		String queryText="query=Card&Suit[@id=1][Deck[@id=1]]";
		process(queryText,"test1");
	}
	
	public void testExampleAssociationWithDelimLeftBracket2() throws Exception{
		String queryText="query=Deck&Suit[@id=1][cardCollection[@id=1]]";
		process(queryText,"test1");
	}
	
	public void testExampleMultiParamsAssociationWithDelimLeftBracket() throws Exception{
		String queryText="query=Deck&Suit[@id=1][cardCollection[@id=1][@name=Ace]]";
		process(queryText,"test1");
	}
	
	public void testExampleAssociationWithDelimSlash() throws Exception{
		String queryText="query=Deck&Suit[@id=1]"+SystemConstant.FORWARD_SLASH+"Card[@id=1]";
		process(queryText,"test1");
	}
	
	public void testExampleIDQueryWithMultipleParams() throws Exception{
		String queryText="query=Pupil&Pupil[@name=Pupil_Name_1][@id=1]";
		process(queryText,"test1");
	}
	
	public void testExampleIDQueryWithAssociation() throws Exception{
		String queryText="query=Card&Suit[@id=1]&roleName=cardCollection";
		process(queryText,"test1");
	}
		
	private void process(String queryText,String fileName) throws Exception, IOException,
			FileNotFoundException {
		HTTPUtils httpUtils= new HTTPUtils(applicationService,classCache,1000);
		httpUtils.setPageSize(1000);
		httpUtils.setQueryArguments(queryText);
		Object[] resultSet=httpUtils.getResultSet();		
		int pageNumber = 1;
		org.jdom.Document domDoc = httpUtils.getXMLDocument(resultSet,pageNumber);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		XMLOutputter xout = new XMLOutputter(Format.getPrettyFormat());
		xout.output(domDoc, outputStream);		
		File file = new File("C:/temp/"+fileName+".xml");
		FileOutputStream foStream = new FileOutputStream(file);
		outputStream.writeTo(foStream);
	}
}
