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
	
	public void xtestExampleIDQuery() throws Exception{
		String queryText="query=Bank&Credit[@id=[@extension=3]]";
		process(queryText,"test1");
	}
	
	public void xtestExampleMultipleAssociation() throws Exception{
		String queryText="query=Card,Suit&Deck[@id=[@extension=1]]";
		process(queryText,"test1");
	}
	
	public void xtestExampleMultipleAssociation2() throws Exception{
		String queryText="query=Deck,Suit&Card[@id=[@extension=1]]";
		process(queryText,"test1");
	}
	
	public void xtestExampleAssociationWithDelimLeftBracket() throws Exception{
		String queryText="query=Card&Suit[@id=[@extension=1]][Deck[@id=[@extension=1]]]";
		process(queryText,"test1");
	}
	
	public void xtestExampleAssociationWithDelimLeftBracket2() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]][cardCollection[@id=[@extension=1]]]";
		process(queryText,"test1");
	}
	
	public void xtestExampleMultiParamsAssociationWithDelimLeftBracket() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]][cardCollection[@id=[@extension=1]][@name=[@value=Ace]]]";
		process(queryText,"test1");
	}
	
	public void xtestExampleAssociationWithDelimSlash() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]]"+SystemConstant.FORWARD_SLASH+"Card[@id=[@extension=1]]";
		process(queryText,"test1");
	}

	//failing fix in translator
	public void testSimpeISODataType() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value1=[@code=CODE1]]";
		process(queryText,"test1");
	}
	
	//failing fix in translator
	public void xtestSimpeISOComplexDataType() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value1=[@code=CODE1]][@value1=[@originalText=[@value=Y]]]";
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
		File file = new File("C:/devroot/sdk-svn/cacoresdk/sdk-toolkit/software/modules/system-web/src/"+fileName+".xml");
		FileOutputStream foStream = new FileOutputStream(file);
		outputStream.writeTo(foStream);
	}
}
