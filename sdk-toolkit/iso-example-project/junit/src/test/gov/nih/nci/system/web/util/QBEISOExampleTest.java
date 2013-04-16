/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.system.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher;
import gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.Teacher;
import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;
import gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType;
import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.AdxpAl;
import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.En;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.system.applicationservice.ApplicationService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class QBEISOExampleTest extends TestCase {
	static String paths[] = {"application-config.xml"};
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			paths);;
	private ApplicationService applicationService;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		applicationService = (ApplicationService) ctx
				.getBean("ApplicationService");
	}

	public void testExampleSimpleQuery() throws Exception {
		Credit searchObject = new Credit();
		Collection<Credit> results = applicationService
				.search("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit",
						searchObject);
		assertNotNull(results);
		assertEquals(2, results.size());
		for (Iterator<Credit> i = results.iterator(); i.hasNext();) {
			Credit credit = i.next();
			assertNotNull(credit);
			assertNotNull(credit.getId());
			assertNotNull(credit.getAmount());
			assertNotNull(credit.getCardNumber());
		}
	}
	
	public void testExampleSimpleQuery_2() throws Exception {
		PrivateTeacher searchObject = new PrivateTeacher();
		Collection<Teacher> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.inheritance.abstrakt.PrivateTeacher",
				searchObject);
		assertNotNull(results);
		assertEquals(3, results.size());
		for (Teacher teacher : results) {
			assertNotNull(teacher.getName().getValue());
			assertNull(teacher.getName().getNullFlavor());
		}
	}
	
	public void testISOComplexAdData() throws Exception {
		AdDataType adDataType = new AdDataType();
		Ad ad = getAdDataType("1 Jefferson Street");
		adDataType.setValue1(ad);
		List<AdDataType> sourceObjectList = new ArrayList<AdDataType>();
		sourceObjectList.add(adDataType);

		Collection<AdDataType> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType",
				adDataType);
		assertEquals(true, results.size() > 0);
		for (AdDataType result : results) {
			Ad value1 = result.getValue1();
			Adxp adxp = value1.getPart().get(0);
			assertNotNull(adxp.getCodeSystem());
		}
	}
	
	public void testISOComplexIIData() throws Exception {
		IiDataType iiDataType = new IiDataType();
		Ii ii = new Ii();
		ii.setExtension("II_Extension");
		iiDataType.setValue1(ii);

		Collection<IiDataType> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.other.datatype.IiDataType",
				iiDataType);
		assertEquals(true, results.size() > 0);
		for (IiDataType result : results) {
			Ii value1 = result.getValue1();
			assertNotNull(value1.getExtension());
		}
	}
	
	public void testISOComplexEnData() throws Exception {
		EnDataType enDataType = new EnDataType();
		En en = getEnDataType("Mr. John Doe Jr.");
		enDataType.setValue1(en);
		List<EnDataType> sourceObjectList = new ArrayList<EnDataType>();
		sourceObjectList.add(enDataType);

		Collection<EnDataType> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType",
				enDataType);
		assertEquals(true, results.size() > 0);
		for (EnDataType result : results) {
			En value1 = result.getValue1();
			Enxp enxp = value1.getPart().get(0);
			assertNotNull(enxp.getCodeSystem());
		}
	}
	
	public void testISOComplexCdData() throws Exception {
		CdDataType cdDataType = new CdDataType();
		Cd cd=new Cd();
		cd.setCode("CODE1");
		cdDataType.setValue1(cd);
		List<CdDataType> sourceObjectList = new ArrayList<CdDataType>();
		sourceObjectList.add(cdDataType);

		Collection<CdDataType> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType",
				cdDataType);
		assertEquals(true, results.size() > 0);
		for (CdDataType result : results) {
			assertNotNull(result.getId());
			Cd next = result.getValue1();
			assertNotNull(next.getCode());
		}
	}

	public void testISOComplexDsetCdDataSetType() throws Exception {
		DsetCdDataType dsetCdDataType = getDsetCdDataType("CODE1");
		List<DsetCdDataType> sourceObjectList = new ArrayList<DsetCdDataType>();
		sourceObjectList.add(dsetCdDataType);
		Collection<DsetCdDataType> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.other.datatype.DsetCdDataType",
				dsetCdDataType);
		assertEquals(true, results.size() > 0);
		for (DsetCdDataType result : results) {
			assertNotNull(result.getId());
			DSet<Cd> next = result.getValue1();
			Cd cd = next.getItem().iterator().next();
			assertNotNull(cd.getCode());
		}
	}

	public void testISOComplexDsetAdDataSetType() throws Exception {
		DsetAdDataType dsetAdDataType = getDsetAdDataType("1 Jefferson Street");
		List<DsetAdDataType> sourceObjectList = new ArrayList<DsetAdDataType>();
		sourceObjectList.add(dsetAdDataType);

		Collection<DsetAdDataType> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType",
				dsetAdDataType);
		assertEquals(true, results.size() > 0);
		for (DsetAdDataType result : results) {
			assertNotNull(result.getId());
			Ad next = result.getValue1().getItem().iterator().next();
			Adxp adxp = next.getPart().get(0);
			assertNotNull(adxp.getCode());
		}
	}
	
	public void testISOComplexIvlIntDataType() throws Exception {
		IvlIntDataType dataType=new IvlIntDataType();
		Ivl<Int> searchObject = new Ivl<Int>();
		Int high = new Int();
		high.setValue(10);
		searchObject.setHigh(high);
		dataType.setValue1(searchObject);
		List<IvlIntDataType> results = applicationService
				.search("gov.nih.nci.cacoresdk.domain.other.datatype.IvlIntDataType",
						dataType);
		assertNotNull(results);
		assertEquals(true, results.size()>0);
		for (Iterator<IvlIntDataType> ivlIntDataType = results.iterator(); ivlIntDataType.hasNext();) {
			IvlIntDataType type = ivlIntDataType.next();
			assertNotNull(type.getValue1().getHigh().getValue());
		}
	}

	private DsetCdDataType getDsetCdDataType(String codeValue) {
		DsetCdDataType dsetCdDataType = new DsetCdDataType();
		CdDataType cdDataType = new CdDataType();
		Cd cd=new Cd();
		cd.setCode(codeValue);
		cdDataType.setValue1(cd);
		
		DSet<Cd> dSet=new DSet<Cd>();
		Set<Cd> item=new HashSet<Cd>();
		item.add(cd);
 		dSet.setItem(item);
		dsetCdDataType.setValue1(dSet);
		return dsetCdDataType;
	}
	public void testEnDataType() throws Exception {
		EnDataType enDataType = new EnDataType();
		enDataType.setValue1(getEn("Mr. John Doe Jr."));
		List<EnDataType> sourceObjectList = new ArrayList<EnDataType>();
		sourceObjectList.add(enDataType);
		Collection<EnDataType> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType",
				enDataType);
		assertEquals(true, results.size() > 0);
		for (EnDataType result : results) {
			assertNotNull(result.getId());
			Enxp next = result.getValue1().getPart().iterator().next();
			assertNotNull(next.getCodeSystem());
		}
	}
	
	public void testEnDataType2() throws Exception {
		EnDataType enDataType = new EnDataType();
		enDataType.setValue5(getEn("Mr. John Doe I"));
		List<EnDataType> sourceObjectList = new ArrayList<EnDataType>();
		sourceObjectList.add(enDataType);
		Collection<EnDataType> results = applicationService.search(
				"gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType",
				enDataType);
		assertEquals(2, results.size());

		for (EnDataType result : results) {
			assertEquals("Mr. John Doe I", result.getValue5().getPart().get(0).getValue().toString());			
			assertNotNull(result.getId());
		}
	}	

	private En getEn(String enxpValue) {
		En en = new En();
		Enxp partInstance = new Enxp();
		partInstance.setType(EntityNamePartType.FAM);
		partInstance.setValue(enxpValue);
		en.addPart(partInstance);
		return en;
	}

	private DsetAdDataType getDsetAdDataType(String adxpValue) {
		DsetAdDataType dsetAdDataType = new DsetAdDataType();
		DSet<Ad> dSet = new DSet<Ad>();
		Set<Ad> sets = new HashSet<Ad>();
		Ad ad = getAdDataType(adxpValue);
		sets.add(ad);
		dSet.setItem(sets);
		dsetAdDataType.setValue1(dSet);
		return dsetAdDataType;
	}

	private En getEnDataType(String enxpValue) {
		En en = new En();
		Enxp enxp = new Enxp(); 
		enxp.setValue(enxpValue);
		enxp.setType(EntityNamePartType.FAM);
		en.addPart(enxp);
		return en;
	}
	
	private Ad getAdDataType(String adxpValue) {
		Ad ad = new Ad();
		List<Adxp> adxps = new ArrayList<Adxp>();
		AdxpAl adxp = new AdxpAl();
		adxp.setValue(adxpValue);
		adxps.add(adxp);
		ad.setPart(adxps);
		return ad;
	}
}
