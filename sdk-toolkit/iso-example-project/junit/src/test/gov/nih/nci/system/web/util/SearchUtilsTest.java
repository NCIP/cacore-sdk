package test.gov.nih.nci.system.web.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;

import gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType;
import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.EntityNamePartQualifier;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.system.util.ClassCache;
import gov.nih.nci.system.web.util.SearchUtils;
import junit.framework.TestCase;

public class SearchUtilsTest extends TestCase {
	
	private SearchUtils searchUtils;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ClassCache classCache = new ClassCache();
		searchUtils = new SearchUtils(classCache);
	}
	
	public void testExampleBasicQuery(){
		List<String> criteriaList=new ArrayList<String>();
		String query = "Credit[@id=[@extension=3]]";
		criteriaList.add(query);
		try {
			Credit credit=	(Credit)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation",criteriaList);
			assertNotNull(credit.getId());
			assertEquals("3", credit.getId().getExtension());	
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}

	public void testExampleBasicQueryWithRoleName(){
		List<String> criteriaList=new ArrayList<String>();
		String query = "Credit[@id=[@extension=3]]&roleName=issuingBank";
		criteriaList.add(query);
		try {
			Credit credit=	(Credit)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation",criteriaList);
			assertNotNull(credit.getId());
			assertEquals("3", credit.getId().getExtension());	
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}

	public void testISOExampleBasicQuery(){
		List<String> criteriaList=new ArrayList<String>();
		String query = "Credit[@id=[@extension=3]]";
		criteriaList.add(query);
		try {
			Credit credit=	(Credit)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation",criteriaList);
			assertNotNull(credit.getId());
			assertEquals("3", credit.getId().getExtension());	
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}

	public void testISOExampleBasicQueryWithRoleName(){
		List<String> criteriaList=new ArrayList<String>();
		String query = "CdDataType[@value1=[@code=CODE1]]";
		criteriaList.add(query);
		try {
			CdDataType cdDataType=	(CdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(cdDataType.getValue1());
			assertNotNull(cdDataType.getValue1().getCode());
			assertEquals("CODE1", cdDataType.getValue1().getCode());	
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}

	public void testISOExampleComplexCdQuery(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText="CdDataType[@value1=[@originalText=[@value=value]]]";
		criteriaList.add(queryText);
		try {
			CdDataType cdDataType=(CdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(cdDataType.getValue1());
			assertNotNull(cdDataType.getValue1().getOriginalText());
			assertNotNull(cdDataType.getValue1().getOriginalText().getValue());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}

	public void testISOExampleComplexCdQuery2(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText="CdDataType[@value1=[@originalText=[@value=value]]][@value2=[@code=value]]";
		criteriaList.add(queryText);
		try {
			CdDataType cdDataType=(CdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(cdDataType.getValue1());
			assertNotNull(cdDataType.getValue1().getOriginalText());
			assertNotNull(cdDataType.getValue1().getOriginalText().getValue());
			assertNotNull(cdDataType.getValue2().getCode());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOExampleComplexCdQuery7(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText="CdDataType[@value7=[@codeSystem=CODE_SYSTEM_1]]";
		criteriaList.add(queryText);
		try {
			CdDataType cdDataType=(CdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(cdDataType.getValue7());
			assertNotNull(cdDataType.getValue7().getCodeSystem());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
		
	
	public void testISOExampleComplexCdQueryNullFlavor(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText="CdDataType[@value2=[@nullFlavor=NI]]";
		criteriaList.add(queryText);
		try {
			CdDataType cdDataType=(CdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(cdDataType.getValue2().getNullFlavor());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOExampleComplexCdQueryValue6(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText="CdDataType[@value6=[@displayName=[@value=VALUE6_DISPLAY_VALUE1]][@code=CODE1]]";
		criteriaList.add(queryText);
		try {
			CdDataType cdDataType=(CdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(cdDataType.getValue6().getCode());
			assertNotNull(cdDataType.getValue6().getDisplayName().getValue());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
		
	public void testISOExampleComplexDsetQuery(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText = "DsetCdDataType[@value5=[@item=[@code=CODE1][@codeSystem=CODE_SYSTEM1]][@item=[@codeSystem=CODE_SYSTEM2]]]";
		criteriaList.add(queryText);
		try {
			DsetCdDataType dsetCdDataType=(DsetCdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(dsetCdDataType.getValue5());
			Iterator<Cd> iterator = dsetCdDataType.getValue5().getItem().iterator();
			Cd next = iterator.next();
			Cd next2 = iterator.next();
			if(next2.getCode()==null){
				assertEquals("CODE_SYSTEM2",next2.getCodeSystem());
				assertEquals("CODE1",next.getCode());
				assertEquals("CODE_SYSTEM1",next.getCodeSystem());
			}else{
				assertEquals("CODE_SYSTEM2",next.getCodeSystem());
				assertEquals("CODE1",next2.getCode());
				assertEquals("CODE_SYSTEM1",next2.getCodeSystem());
			}
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOExampleComplexDsetAdQuery(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText = "DsetAdDataType[@value1=[@item=[@part=[@code=CODE1]]]]";
		criteriaList.add(queryText);
		try {
			DsetAdDataType dsetAdDataType=(DsetAdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(dsetAdDataType.getValue1());
			Iterator<Ad> iterator = dsetAdDataType.getValue1().getItem().iterator();
			Ad next = iterator.next();
			Adxp adxp=next.getPart().get(0);
			assertEquals("CODE1", adxp.getCode());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOExampleComplexDsetQuery2(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText = "DsetCdDataType[@value5=[@item=[@code=CODE1][@codeSystem=CODESYSTEM1]]]";
		criteriaList.add(queryText);
		try {
			DsetCdDataType dsetCdDataType=(DsetCdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(dsetCdDataType.getValue5());
			Iterator<Cd> iterator = dsetCdDataType.getValue5().getItem().iterator();
			Cd next = iterator.next();
			assertNotNull(next.getCode());
			assertNotNull(next.getCodeSystem());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	

	public void testISOExampleComplexDsetQuery3(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText = "DsetCdDataType[@value3=[@item=[@code=CODE1]][@item=[@codeSystem=CODE_SYSTEM1]]]";
		criteriaList.add(queryText);
		try {
			DsetCdDataType dsetCdDataType=(DsetCdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(dsetCdDataType.getValue3());
			Iterator<Cd> iterator = dsetCdDataType.getValue3().getItem().iterator();
			Cd next = iterator.next();
			Cd next2 = iterator.next();
			assertNotNull(next.getCodeSystem());
			assertNotNull(next2.getCode());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOComplexIVLRealDataWidthType(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText = "IvlRealDataType[@value3=[@width=[@value=1]]]";
		criteriaList.add(queryText);
		try {
			IvlRealDataType ivlRealDataType=(IvlRealDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(ivlRealDataType.getValue3().getWidth());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOComplexIVLIntDataWidthType(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText = "IvlIntDataType[@value3=[@width=[@value=1]]]";
		criteriaList.add(queryText);
		try {
			IvlIntDataType ivlIntDataType=(IvlIntDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(ivlIntDataType.getValue3().getWidth());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOComplexIVLTsDataWidthType(){
		List<String> criteriaList=new ArrayList<String>();
		String queryText = "IvlTsDataType[@value3=[@width=[@value=1]]]";
		criteriaList.add(queryText);
		try {
			IvlTsDataType ivlTsDataType=(IvlTsDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			assertNotNull(ivlTsDataType.getValue3().getWidth());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}

	public void testISOComplexAdDataType() throws Exception{
		List<String> criteriaList=new ArrayList<String>();
		String queryText="AdDataType[@value1=[@part=[@value=1][@type=ZIP]]]";
		criteriaList.add(queryText);
		try {
			AdDataType adDataType=(AdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			Adxp next = adDataType.getValue1().getPart().iterator().next();
			assertNotNull(next.getValue());
			assertNotNull(next.getType());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOComplexEnDataType() throws Exception{
		List<String> criteriaList=new ArrayList<String>();
		String queryText="EnDataType[@value1=[@part=[@value=1][@type=FAM]]]";
		criteriaList.add(queryText);
		try {
			EnDataType enDataType=(EnDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			Enxp next = enDataType.getValue1().getPart().iterator().next();
			assertNotNull(next.getValue());
			assertNotNull(next.getType());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOComplexEnDataType2() throws Exception{
		List<String> criteriaList=new ArrayList<String>();
		String queryText="EnDataType[@value1=[@part=[@code=CODE1][@value=1][@type=FAM]]]";
		criteriaList.add(queryText);
		try {
			EnDataType enDataType=(EnDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			Enxp next = enDataType.getValue1().getPart().iterator().next();
			assertNotNull(next.getValue());
			assertNotNull(next.getCode());
			assertNotNull(next.getType());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOComplexEnDataType3() throws Exception{
		List<String> criteriaList=new ArrayList<String>();
		String queryText="EnDataType[@value1=[@part=[@qualifier=LS][@qualifier=AC]]]";
		criteriaList.add(queryText);
		try {
			EnDataType enDataType=(EnDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			Enxp next = enDataType.getValue1().getPart().iterator().next();
			Iterator<EntityNamePartQualifier> itr=next.getQualifier().iterator();
			assertNotNull(itr.next());
			assertNotNull(itr.next());
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	
	public void testISOComplexEnDataType4() throws Exception{
		List<String> criteriaList=new ArrayList<String>();
		String queryText="AdDataType[@value9=[@part=[@value=5 Sun Street][@codeSystem=5 Sun Street][@code=5 Sun Street][@type=CTY]][@part=[@value=5 Sun Street][@codeSystem=5 Sun Street][@code=5 Sun Street][@type=ADL]]]";
		criteriaList.add(queryText);
		try {
			AdDataType adDataType=(AdDataType)searchUtils.buildSearchCriteria("gov.nih.nci.cacoresdk.domain.other.datatype",criteriaList);
			Iterator<Adxp> iterator = adDataType.getValue9().getPart().iterator();
			Adxp next = iterator.next();
			Adxp next2 = iterator.next();
			assertNotNull(next.getCode());
			assertNotNull(next.getCodeSystem());
			assertNotNull(next.getValue());
			assertEquals(next.getType().name(), "CTY");
			assertNotNull(next2.getCode());
			assertNotNull(next2.getCodeSystem());
			assertNotNull(next2.getValue());
			assertEquals(next2.getType().name(), "ADL");
		} catch (Exception ex) {
			String message=getStackTrace(ex);
			fail(message);
		}
	}
	

	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String stacktrace = sw.toString();
		return stacktrace;
	}
}
