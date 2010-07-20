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
	
	public void testExampleIDQuery() throws Exception{
		String queryText="query=Bank&Credit[@id=[@extension=3]]";
		process(queryText,"test1");
	}
	
	public void testExampleMultipleAssociation() throws Exception{
		String queryText="query=Card,Suit&Deck[@id=[@extension=1]]";
		process(queryText,"test2");
	}
	
	public void testExampleMultipleAssociation2() throws Exception{
		String queryText="query=Deck,Suit&Card[@id=[@extension=1]]";
		process(queryText,"test3");
	}
	
	public void testExampleAssociationWithDelimLeftBracket() throws Exception{
		String queryText="query=Card&Suit[@id=[@extension=1]][Deck[@id=[@extension=1]]]";
		process(queryText,"test4");
	}
	
	public void testExampleAssociationWithDelimLeftBracket2() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]][cardCollection[@id=[@extension=1]]]";
		process(queryText,"test5");
	}
	
	public void testExampleMultiParamsAssociationWithDelimLeftBracket() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]][cardCollection[@id=[@extension=1]][@name=[@value=Ace]]]";
		process(queryText,"test6");
	}
	
	public void testExampleAssociationWithDelimSlash() throws Exception{
		String queryText="query=Deck&Suit[@id=[@extension=1]]"+SystemConstant.FORWARD_SLASH+"Card[@id=[@extension=1]]";
		process(queryText,"test7");
	}

	/*###################################################################################*/
	public void testSimpeISODataType() throws Exception{
		String queryText="query=CdDataType";
		process(queryText,"test8");
	}
	
	public void testSimpeISODataType2() throws Exception{
		String queryText="query=CdDataType&CdDataType[@id=1]";
		process(queryText,"test9");
	}
	
	public void testISOComplexDataType() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value1=[@code=CODE1]]";
		process(queryText,"test10");
	}

	
	public void testISOComplexDataType2() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value4=[@originalText=[@value=VALUE4_ORIG_TXT_VALUE1]]]";
		process(queryText,"test11");
	}
	
	public void testISOComplexDataTypeValue4() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value4=[@displayName=[@value=VALUE4_DISPLAY_VALUE2]]]";
		process(queryText,"test12");
	}
	
	public void testISOComplexDataTypeValue6() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value2=[@code=CODE4]][@value1=[@code=CODE1]][@value6=[@displayName=[@value=VALUE6_DISPLAY_VALUE1]][@code=CODE1]]";
		process(queryText,"test13");
	}
	
	public void testISOComplexDataTypeValue7() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value7=[@displayName=[@value=VALUE7_DISPLAY_VALUE1]]]";
		process(queryText,"test14");
	}
	
	public void testISOComplexDataTypeValue7_2() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value7=[@codeSystem=CODE_SYSTEM_1]]";
		process(queryText,"test15");
	}
	
	public void testISOComplexDataTypeValue7_3() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value2=[@code=CODE4]][@value1=[@code=CODE1]][@value6=[@displayName=[@value=VALUE6_DISPLAY_VALUE1]][@code=CODE1]][@value7=[@codeSystem=CODE_SYSTEM_1]]";
		process(queryText,"test41");
	}
	
	public void testISOComplexDataTypeValue8() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value8=[@code=CODE8][@displayName=[@value=VALUE8_DISPLAY_VALUE1]]]";
		process(queryText,"test16");
	}
	
	public void testISOComplexDataTypeWithAndQuery() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value4=[@displayName=[@value=VALUE4_DISPLAY_VALUE2]][@originalText=[@value=VALUE4_ORIG_TXT_VALUE1]]]";
		process(queryText,"test17");
	}
	
	//fails sql script needs to be changed to add data
	//query works fine
	public void testISOComplexDataTypeWithAndQuery2() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value2=[@code=CODE4]][@value1=[@code=CODE1]][@value4=[@displayName=[@value=VALUE4_DISPLAY_VALUE2]][@originalText=[@value=VALUE4_ORIG_TXT_VALUE1]]]";
		process(queryText,"test18");
	}	

	//query works fine
	public void testISOComplexDataTypeWithAndQuery3() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value4=[@code=CODE8][@codeSystem=VALUE4_CODE_SYSTEM]]";
		process(queryText,"test19");
	}
	
	//failing as properties are not mapped properly
	public void testISOComplexDataTypePropertyNotMappedException() throws Exception{
		String queryText="query=CdDataType&CdDataType[@value1=[@originalText=[@value=VALUE4_ORIG_TXT_VALUE1]]]";
		try{
			process(queryText,"test20");
			fail("Excepted Exception: property not mapped");
		}catch (Exception e) {
		}
	}
	/*###################################################################################*/
	
	public void testISOComplexIVLIntDataType() throws Exception{
		String queryText="query=IvlIntDataType&IvlIntDataType[@value1=[@low=[@value=1]]]";
		process(queryText,"test21");
	}
	
	public void testISOComplexIVLRealDataType() throws Exception{
		String queryText="query=IvlRealDataType&IvlRealDataType[@value1=[@low=[@value=1]]]";
		process(queryText,"test22");
	}
	
	public void testISOComplexIVLRealDataTypeWidth() throws Exception{
		String queryText="query=IvlRealDataType&IvlRealDataType[@value3=[@width=[@value=44.0]]]";
		process(queryText,"test23");
	}
	
	public void testISOComplexIVLPQDataType() throws Exception{
		String queryText="query=IvlPqDataType&IvlPqDataType[@value2=[@low=[@value=221.1]]]";
		process(queryText,"test24");
	}
	
	public void testISOComplexIVLPQDataWidthType() throws Exception{
		String queryText="query=IvlPqDataType&IvlPqDataType[@value4=[@width=[@value=1]]]";
		process(queryText,"test25");
	}

	public void testISOComplexPQDataType() throws Exception{
		String queryText="query=PqDataType&PqDataType[@value3=[@nullFlavor=NA]]";
		process(queryText,"test25");
	}

	public void testISOComplexIVLTSDataWidthType() throws Exception{
		String queryText="query=IvlTsDataType&IvlTsDataType[@value3=[@width=[@value=1]]]";
		process(queryText,"test26");
	}	
	
	public void testISOComplexIVLTSDataType() throws Exception{
		String queryText="query=IvlTsDataType&IvlTsDataType[@value1=[@low=[@value=03-11-2010]]]";
		process(queryText,"test27");
	}		
	/*###################################################################################*/		
	
	public void testISOComplexDsetCdDataSetType() throws Exception{
		String queryText="query=DsetCdDataType&DsetCdDataType[@value1=[@item=[@code=CODE1]]]";
		process(queryText,"test28");
	}
		
	public void testISOComplexDsetCdDataTypeValue6() throws Exception{
		String queryText="query=DsetCdDataType&DsetCdDataType[@value6=[@item=[@code=CODE1]]]";
		process(queryText,"test29");
	}
	
	public void testISOComplexDsetCdDataTypeValue7() throws Exception{
		String queryText="query=DsetCdDataType&DsetCdDataType[@value7=[@item=[@code=CODE1]]]";
		process(queryText,"test30");
	}
	
	public void testISOComplexDsetIiDataSetType() throws Exception{
		String queryText="query=gov.nih.nci.cacoresdk.domain.other.datatype.DsetIiDataType&DsetIiDataType[@value1=[@item=[@extension=Extension1]]]";
		process(queryText,"test31");
	}

	public void testISOComplexDsetTelDataSetType() throws Exception{
		String queryText="query=DsetTelDataType&DsetTelDataType[@value3=[@item=[@value=tel://123-456-7891]]]";
		process(queryText,"test32");
	}
	
	public void testISOComplexDsetCdDataSetType5() throws Exception{
		String queryText="query=DsetCdDataType&DsetCdDataType[@value5=[@item=[@code=CODE1][@codeSystem=CODE_SYSTEM1]]]";
		process(queryText,"test33");
	}
	
	public void testISOComplexDsetCdDataMultipleSetValue1() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value1=[@item=[@code=CODE1]][@item=[@code=CODE2]]]";
		process(queryText,"test34");
	}
	
	public void testISOComplexDsetCdDataMultipleSetType5_1() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value5=[@item=[@code=CODE1]][@item=[@codeSystem=CODE_SYSTEM1]]]";
		process(queryText,"test35");
	}	
	
	public void testISOComplexDsetCdDataMultipleSetType5_2() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value5=[@item=[@code=CODE1]][@item=[@code=CODE2]]]";
		process(queryText,"test36");
	}

	public void testISOComplexDsetCdDataMultipleSetValue6() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value6=[@item=[@code=CODE1]][@item=[@code=CODE1]][@item=[@code=CODE2]][@item=[@code=CODE2]]]";
		process(queryText,"test34");
	}
	
	public void testISOComplexDsetCdDataMultipleSetType7() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value7=[@item=[@code=CODE1]][@item=[@code=CODE2]]]";
		process(queryText,"test34");
	}
	
	public void testISOComplexDsetCdDataInvalidSet() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value3=[@item=[@code=CODE1]]][@value3=[@item=[@codeSystem=CODE_SYSTEM1]]]";
		try{
			process(queryText,"test37");
		}catch (Exception e) {
			assertEquals("ERROR :  Invalid Query Criteria ", e.getMessage());
		}
	}
	
	public void testISOComplexDsetCdDataMultipleSetAllType() throws Exception{
		String	queryText="query=DsetCdDataType&DsetCdDataType[@value1=[@item=[@code=CODE1]]][@value2=[@item=[@code=CODE1]]][@value3=[@item=[@code=CODE1]]][@value4=[@item=[@code=CODE1]]][@value5=[@item=[@code=CODE1][@codeSystem=CODE_SYSTEM1]][@item=[@codeSystem=CODE_SYSTEM2]]][@value6=[@item=[@code=CODE1]]][@value7=[@item=[@code=CODE1]]]";
		process(queryText,"test36");
	}

	public void testISOComplexEnumDataType() throws Exception{
		String queryText="query=gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType&gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType[@value2=[@nullFlavor=UNK]]";
		process(queryText,"test38");
	}
	
	//query=AdDataType&AdDataType[@value1=[@part=[@type=AL]]]
	public void testISOComplexAdDataType() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value1=[@part=[@value=5 Sun Street][@type=AL]]]";
		process(queryText,"test39");
	}
	//failure : Same ADXP : List[0],List[1]
	public void testISOComplexAdDataType4() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value4=[@part=[@value=5 Sun Street][@type=AL]][@part=[@value=5 Sun Street][@type=AL]]]";
		process(queryText,"test40");
	}
	
	public void testISOComplexAdDataType5() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value5=[@part=[@value=5 Sun Street][@type=DAL]]]";
		process(queryText,"test41");
	}
	
	public void testISOComplexAdDataType5_2() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value5=[@part=[@value=5 Sun Street][@type=DAL]][@part=[@value=5 Sun Street][@type=CTY]]]";
		process(queryText,"test42");
	}
	
	public void testISOComplexAdDataType7() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value7=[@part=[@value=5 Sun Street][@type=DAL]]]";
		process(queryText,"test43");
	}
	
	public void testISOComplexAdDataType8() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value8=[@part=[@value=5 Sun Street][@type=DAL]]]";
		process(queryText,"test44");
	}

	public void testISOComplexAdDataType8_2() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value8=[@part=[@value=5 Sun Street][@type=CTY]][@part=[@value=5 Sun Street][@type=DAL]]]";
		process(queryText,"test45");
	}

	
	public void testISOComplexAdDataType9() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value9=[@part=[@value=DAL_VALUE4][@codeSystem=DAL_CODESYSTEM4][@code=DAL_CODE4][@type=DAL]]]";
		process(queryText,"test46");
	}

	public void testISOComplexAdDataType9_2() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value9=[@part=[@value=CTY_VALUE4][@codeSystem=CTY_CODESYSTEM4][@code=CTY_CODE4][@type=CTY]][@part=[@value=5 Sun Street][@codeSystem=5 Sun Street][@code=5 Sun Street][@type=DAL]]]";
		process(queryText,"test47");
	}
	
	public void testISOComplexAdDataType9_3() throws Exception{
		String queryText="query=AdDataType&AdDataType[@value9=[@part=[@value=CTY_VALUE4][@type=CTY]][@part=[@codeSystem=CTY_CODESYSTEM4][@type=DAL]][@part=[@code=DAL_CODE4][@type=DAL]]]";
		process(queryText,"test48");
	}
	
	
	public void testISOComplexIiDataSetType() throws Exception{
		String queryText="query=IiDataType&IiDataType[@value4=[@displayable=false]]";
		process(queryText,"test49");
	}
	
	//failure,need to find solution
	public void testISOComplexIiDataSetType_2() throws Exception{
		String queryText="query=IiDataType&IiDataType[@value4=[@displayable=0/1]]";
		process(queryText,"test49");
	}
	
	public void testISOComplexEnDataType() throws Exception{
		String queryText="query=EnDataType&EnDataType[@value1=[@part=[@codeSystemVersion=ENXP Code System][@type=FAM]]]";
		process(queryText,"test39");
	}
	                                                                                                                    
	                                                                                                                    
	private void process(String queryText,String fileName) throws Exception, IOException,
			FileNotFoundException {
//		HQLCriteria criteria= new HQLCriteria("select adDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType adDataType_1 inner join adDataType_1.value1.part_0 as adxp_0 where  (( adxp_0.value='5 Sun Street'  )) ");
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
