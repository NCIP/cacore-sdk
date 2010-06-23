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
	static String  paths[] = { "application-config.xml"};
	private static ApplicationContext ctx =  new ClassPathXmlApplicationContext(paths);;
	private ClassCache classCache;
	private ApplicationService applicationService;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		applicationService = (ApplicationService) ctx.getBean("ApplicationService");
		classCache = (ClassCache) ctx.getBean("ClassCache");
	}
	
	public void xtestExampleIDQuery() throws Exception{
		String queryText="query=Bank&Credit[@id=[@extension=3]]";
		process(queryText,"test1");
	}
	
	public void xtestExampleMultipleAssociation() throws Exception{
		String queryText="query=Card,Suit&Deck[@id=[@extension=1]]";
		process(queryText,"test2");
	}
	
	public void xtestExampleMultipleAssociation2() throws Exception{
		String queryText="query=Deck,Suit&Card[@id=[@extension=1]]";
		process(queryText,"test3");
	}
	
	public void xtestExampleAssociationWithDelimLeftBracket() throws Exception{
		String queryText="query=Card&Suit[@id=[@extension=1]][Deck[@id=[@extension=1]]]";
		process(queryText,"test4");
	}
	
	public void xtestExampleAssociationWithDelimLeftBracket2() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]][cardCollection[@id=[@extension=1]]]";
		process(queryText,"test5");
	}
	
	public void xtestExampleMultiParamsAssociationWithDelimLeftBracket() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]][cardCollection[@id=[@extension=1]][@name=[@value=Ace]]]";
		process(queryText,"test6");
	}
	
	public void xtestExampleAssociationWithDelimSlash() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]]"+SystemConstant.FORWARD_SLASH+"Card[@id=[@extension=1]]";
		process(queryText,"test7");
	}

	/*###################################################################################*/
	public void xtestSimpeISODataType() throws Exception{
		String queryText="query=CdDataType";
		process(queryText,"test8");
	}
	
	public void xtestSimpeISODataType2() throws Exception{
		String queryText="query=CdDataType&CdDataType[@id=1]";
		process(queryText,"test9");
	}
	
	public void xtestISOComplexDataType() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value1=[@code=CODE1]]";
		process(queryText,"test10");
	}

	
	public void xtestISOComplexDataType2() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value4=[@originalText=[@value=VALUE4_ORIG_TXT_VALUE1]]]";
		process(queryText,"test11");
	}
	
	public void xtestISOComplexDataTypeValue4() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value4=[@displayName=[@value=VALUE4_DISPLAY_VALUE2]]]";
		process(queryText,"test12");
	}
	
	public void xtestISOComplexDataTypeValue6() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value2=[@code=CODE4]][@value1=[@code=CODE1]][@value6=[@displayName=[@value=VALUE6_DISPLAY_VALUE1]][@code=CODE1]]";
		process(queryText,"test13");
	}
	
	public void xtestISOComplexDataTypeValue7() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value7=[@displayName=[@value=VALUE7_DISPLAY_VALUE1]]]";
		process(queryText,"test14");
	}
	
	public void xtestISOComplexDataTypeValue7_2() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value7=[@codeSystem=CODE_SYSTEM_1]]";
		process(queryText,"test15");
	}
	
	public void xtestISOComplexDataTypeValue7_3() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value2=[@code=CODE4]][@value1=[@code=CODE1]][@value6=[@displayName=[@value=VALUE6_DISPLAY_VALUE1]][@code=CODE1]][@value7=[@codeSystem=CODE_SYSTEM_1]]";
		process(queryText,"test41");
	}
	
	public void xtestISOComplexDataTypeValue8() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value8=[@code=CODE8][@displayName=[@value=VALUE8_DISPLAY_VALUE1]]]";
		process(queryText,"test16");
	}
	
	public void xtestISOComplexDataTypeWithAndQuery() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value4=[@displayName=[@value=VALUE4_DISPLAY_VALUE2]][@originalText=[@value=VALUE4_ORIG_TXT_VALUE1]]]";
		process(queryText,"test17");
	}
	
	//fails sql script needs to be changed to add data
	//query works fine
	public void xtestISOComplexDataTypeWithAndQuery2() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value2=[@code=CODE4]][@value1=[@code=CODE1]][@value4=[@displayName=[@value=VALUE4_DISPLAY_VALUE2]][@originalText=[@value=VALUE4_ORIG_TXT_VALUE1]]]";
		process(queryText,"test18");
	}	

	//query works fine
	public void xtestISOComplexDataTypeWithAndQuery3() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value4=[@code=CODE8][@codeSystem=VALUE4_CODE_SYSTEM]]";
		process(queryText,"test19");
	}
	
	//failing as properties are not mapped properly
	public void xtestISOComplexDataTypePropertyNotMappedException() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value1=[@originalText=[@value=VALUE4_ORIG_TXT_VALUE1]]]";
		process(queryText,"test20");
	}
	/*###################################################################################*/
	
	public void xtestISOComplexIVLIntDataType() throws Exception{
		String queryText="query=IvlIntDataType&IvlIntDataType[@value1=[@low=[@value=1]]]";
		process(queryText,"test21");
	}
	
	public void xtestISOComplexIVLRealDataType() throws Exception{
		String queryText="query=IvlRealDataType&IvlRealDataType[@value1=[@low=[@value=1]]]";
		process(queryText,"test22");
	}
	
	public void xtestISOComplexIVLRealDataTypeWidth() throws Exception{
		String queryText="query=IvlRealDataType&IvlRealDataType[@value3=[@width=[@value=44.0]]]";
		process(queryText,"test23");
	}
	
	public void xtestISOComplexIVLPQDataType() throws Exception{
		String queryText="query=IvlPqDataType&IvlPqDataType[@value2=[@low=[@value=221.1]]]";
		process(queryText,"test24");
	}
	
	public void xtestISOComplexIVLPQDataWidthType() throws Exception{
		String queryText="query=IvlPqDataType&IvlPqDataType[@value3=[@width=[@value=1]]]";
		process(queryText,"test25");
	}

	public void xtestISOComplexPQDataType() throws Exception{
		String queryText="query=PqDataType&PqDataType[@value3=[@nullFlavor=NA]]";
		process(queryText,"test25");
	}

	public void xtestISOComplexIVLTSDataWidthType() throws Exception{
		String queryText="query=IvlTsDataType&IvlTsDataType[@value3=[@width=[@value=1]]]";
		process(queryText,"test26");
	}	
	
	public void xtestISOComplexIVLTSDataType() throws Exception{
		String queryText="query=IvlTsDataType&IvlTsDataType[@value1=[@low=[@value=03-11-2010]]]";
		process(queryText,"test27");
	}		
	/*###################################################################################*/		
	
	public void xtestISOComplexDsetCdDataSetType() throws Exception{
		String queryText="query=DsetCdDataType&DsetCdDataType[@value1=[@item=[@code=CODE1]]]";
		process(queryText,"test28");
	}
		
	public void xtestISOComplexDsetCdDataTypeValue6() throws Exception{
		String queryText="query=DsetCdDataType&DsetCdDataType[@value6=[@item=[@code=CODE1]]]";
		process(queryText,"test29");
	}
	
	public void xtestISOComplexDsetCdDataTypeValue7() throws Exception{
		String queryText="query=DsetCdDataType&DsetCdDataType[@value7=[@item=[@code=CODE1]]]";
		process(queryText,"test30");
	}
	//gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType&DsetIiDataType[@value4=[@item=[@identifierName=IDENTIFIER_NAME2]]]
	public void xtestISOComplexDsetIiDataSetType() throws Exception{
		String queryText="query=gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType&DsetIiDataType[@value1=[@item=[@extension=Extension1]]]";
		process(queryText,"test31");
	}

	public void xtestISOComplexDsetTelDataSetType() throws Exception{
		String queryText="query=DsetTelDataType&DsetTelDataType[@value3=[@item=[@value=tel://123-456-7891]]]";
		process(queryText,"test32");
	}
	
	public void xtestISOComplexDsetCdDataSetType2() throws Exception{
		String queryText="query=DsetCdDataType&DsetCdDataType[@value5=[@item=[@code=CODE1][@codeSystem=CODE_SYSTEM1]]]";
		process(queryText,"test33");
	}
	
	public void xtestISOComplexDsetCdDataMultipleSetType() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value7=[@item=[@code=CODE1]]][@value5=[@item=[@code=CODE1][@codeSystem=CODE_SYSTEM1]]]";
		process(queryText,"test34");
	}
	
	public void xtestISOComplexDsetCdDataMultipleSetType2() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value5=[@item=[@code=CODE1]][@item=[@codeSystem=CODE_SYSTEM1]]]";
		process(queryText,"test35");
	}	

	public void xtestISOComplexDsetCdDataMultipleSetType3() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value5=[@item=[@code=CODE1][@codeSystem=CODE_SYSTEM1]][@item=[@codeSystem=CODE_SYSTEM2]]]";
		process(queryText,"test36");
	}	
	
	public void xtestISOComplexDsetCdDataMultipleSetType4() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value3=[@item=[@code=CODE1]]][@value3=[@item=[@codeSystem=CODE_SYSTEM1]]]";
		try{
			process(queryText,"test37");
		}catch (Exception e) {
			assertEquals("ERROR :  Invalid Query Criteria ", e.getMessage());
		}
	}	

	public void xtestISOComplexEnumDataType() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value2=[@nullFlavor=NI]]";
		process(queryText,"test38");
	}
	
	public void xtestISOComplexAdAlDataType() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value1=[@part=[@value=5 Sun Street][@type=AL]]]";
		process(queryText,"test39");
	}
	
	public void xtestISOComplexAdZipDataType() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value1=[@part=[@value=5 Sun Street][@type=ZIP]]]";
		process(queryText,"test40");
	}
	
	private void process(String queryText,String fileName) throws Exception, IOException,
			FileNotFoundException {
//		HQLCriteria criteria= new HQLCriteria("select dsetCdDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType as dsetCdDataType_1 inner join dsetCdDataType_1.value5.item as item1 inner join dsetCdDataType_1.value5.item as item2 where  (( item1.codeSystem='CODE_SYSTEM2' ) or ( item2.code='CODE1'  and item2.codeSystem='CODE_SYSTEM1'  ))  ");
//		HQLCriteria criteria= new HQLCriteria("select dsetCdDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType  dsetCdDataType_1 inner join dsetCdDataType_1.value5.item as temp_0 inner join dsetCdDataType_1.value5.item as temp_1 where  (( temp_0.codeSystem='CODE_SYSTEM2' ) or ( temp_1.code='CODE1'  and temp_1.codeSystem='CODE_SYSTEM1'  )) ");
//		applicationService.query(criteria);
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
		foStream.close();
		outputStream.close();
	}
}
