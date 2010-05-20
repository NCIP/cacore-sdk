package test.gov.nih.nci.system.web.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;

import gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType;
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

	public void testISOExampleComplexQuery(){
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
	
	private String getStackTrace(Exception ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String stacktrace = sw.toString();
		return stacktrace;
	}
}
