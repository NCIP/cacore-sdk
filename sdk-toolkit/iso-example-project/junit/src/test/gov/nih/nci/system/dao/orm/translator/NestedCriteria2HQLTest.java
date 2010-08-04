package test.gov.nih.nci.system.dao.orm.translator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;
import gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType;
import gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType;
import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.AdxpAl;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.En;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.system.dao.orm.HibernateConfigurationHolder;
import gov.nih.nci.system.dao.orm.translator.NestedCriteria2HQL;
import gov.nih.nci.system.query.hibernate.HQLCriteria;
import gov.nih.nci.system.query.nestedcriteria.NestedCriteria;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class NestedCriteria2HQLTest extends TestCase {
	static String paths[] = {"application-config.xml"};
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			paths);;
	protected HibernateConfigurationHolder configurationHolder;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		configurationHolder = (HibernateConfigurationHolder) ctx
				.getBean("HibernateConfigHolder");
	}

	public void testExampleIDQuery() throws Exception {
		NestedCriteria criteria = new NestedCriteria();
		criteria.setSourceObjectName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit");
		criteria.setTargetObjectName("gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank");
		criteria.setRoleName("issuingBank");
		Credit credit = new Credit();
		Ii ii = new Ii();
		ii.setExtension("3");
		credit.setId(ii);
		List<Credit> sourceObjectList = new ArrayList<Credit>();
		sourceObjectList.add(credit);
		criteria.setSourceObjectList(sourceObjectList);

		NestedCriteria2HQL criteria2hql = new NestedCriteria2HQL(criteria,
				configurationHolder.getConfiguration(), true);
		HQLCriteria hqlCriteria = criteria2hql.translate();
		String expectedHQL = "select bank_1 from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank as bank_1 where bank_1.id in (select bank_1.id from gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Bank bank_1, gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit credit_1 where credit_1.issuingBank.id=bank_1.id and credit_1.id=? )";
		assertEquals(expectedHQL, hqlCriteria.getHqlString());
	}

	public void testISOComplexDsetAdDataSetType() throws Exception {
		NestedCriteria criteria = new NestedCriteria();
		criteria.setSourceObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		criteria.setTargetObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");

		DsetAdDataType dsetAdDataType = getDsetAdDataType();
		List<DsetAdDataType> sourceObjectList = new ArrayList<DsetAdDataType>();
		sourceObjectList.add(dsetAdDataType);
		criteria.setSourceObjectList(sourceObjectList);

		NestedCriteria2HQL criteria2hql = new NestedCriteria2HQL(criteria,
				configurationHolder.getConfiguration(), true);
		HQLCriteria hqlCriteria = criteria2hql.translate();
		String expectedHQL = "select dsetAdDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType as dsetAdDataType_1 where dsetAdDataType_1.id in (select dsetAdDataType_1.id from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dsetAdDataType_1 inner join dsetAdDataType_1.value1.item as ad_0 where lower(ad_0.part_0.value )=?  )) )";
		assertEquals(expectedHQL, hqlCriteria.getHqlString());
	}

	public void testISOComplexDsetAdDataSetType2() throws Exception {
		NestedCriteria criteria = new NestedCriteria();
		criteria.setSourceObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");
		criteria.setTargetObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType");

		DsetAdDataType dsetAdDataType = getDsetAdDataType();
		DsetAdDataType dsetAdDataType2 = getDsetAdDataType();

		List<DsetAdDataType> sourceObjectList = new ArrayList<DsetAdDataType>();
		sourceObjectList.add(dsetAdDataType);
		sourceObjectList.add(dsetAdDataType2);
		criteria.setSourceObjectList(sourceObjectList);

		NestedCriteria2HQL criteria2hql = new NestedCriteria2HQL(criteria,
				configurationHolder.getConfiguration(), true);
		HQLCriteria hqlCriteria = criteria2hql.translate();
		String expectedHQL = "select dsetAdDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType as dsetAdDataType_1 where dsetAdDataType_1.id in (select dsetAdDataType_1.id from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dsetAdDataType_1 where dsetAdDataType_1 in (select dsetAdDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dsetAdDataType_1 inner join dsetAdDataType_1.value1.item as ad_0 where lower(ad_0.part_0.value )=?  ))  ) or dsetAdDataType_1 in (select dsetAdDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.DsetAdDataType dsetAdDataType_1 inner join dsetAdDataType_1.value1.item as ad_1 where lower(ad_1.part_0.value )=?  ))  ))";
		assertEquals(expectedHQL, hqlCriteria.getHqlString());
	}

	public void testEnDataTypeValue1() throws Exception {
		NestedCriteria criteria = new NestedCriteria();
		criteria.setSourceObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		criteria.setTargetObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		EnDataType enDataType = new EnDataType();
		enDataType.setValue1(getEn());
		List<EnDataType> sourceObjectList = new ArrayList<EnDataType>();
		sourceObjectList.add(enDataType);
		criteria.setSourceObjectList(sourceObjectList);
		NestedCriteria2HQL criteria2hql = new NestedCriteria2HQL(criteria,
				configurationHolder.getConfiguration(), true);
		HQLCriteria hqlCriteria = criteria2hql.translate();
		String expectedHQL = "select enDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType as enDataType_1 where enDataType_1.id in (select enDataType_1.id from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType enDataType_1 where lower(enDataType_1.value1.part_0.value )=? )";
		assertEquals(expectedHQL, hqlCriteria.getHqlString());
	}

	public void testEnDataTypeValue2AndValue1() throws Exception {
		NestedCriteria criteria = new NestedCriteria();
		criteria.setSourceObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		criteria.setTargetObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		EnDataType enDataType = new EnDataType();
		enDataType.setValue1(getEn());
		enDataType.setValue2(getEn());
		List<EnDataType> sourceObjectList = new ArrayList<EnDataType>();
		sourceObjectList.add(enDataType);
		criteria.setSourceObjectList(sourceObjectList);
		NestedCriteria2HQL criteria2hql = new NestedCriteria2HQL(criteria,
				configurationHolder.getConfiguration(), true);
		HQLCriteria hqlCriteria = criteria2hql.translate();
		String expectedHQL = "select enDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType as enDataType_1 where enDataType_1.id in (select enDataType_1.id from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType enDataType_1 where lower(enDataType_1.value1.part_0.value )=?  and lower(enDataType_1.value2.part_0.value )=? )";
		assertEquals(expectedHQL, hqlCriteria.getHqlString());
	}
	
	public void testEnDataTypeValue2AndValue1Exception() throws Exception {
		NestedCriteria criteria = new NestedCriteria();
		criteria.setSourceObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		criteria.setTargetObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		EnDataType enDataType = new EnDataType();
		En en = getEn();
		en.getPart().iterator().next().setType(EntityNamePartType.GIV);
		enDataType.setValue1(en);
		enDataType.setValue2(getEn());
		List<EnDataType> sourceObjectList = new ArrayList<EnDataType>();
		sourceObjectList.add(enDataType);
		criteria.setSourceObjectList(sourceObjectList);
		NestedCriteria2HQL criteria2hql = new NestedCriteria2HQL(criteria,
				configurationHolder.getConfiguration(), true);
		try {
			criteria2hql.translate();
			fail("Must throw an Exception");
		} catch (Exception ex) {
			assertEquals(
					"No matching found for the input 'type' with the mapped-constant specified in EA-Model",
					ex.getMessage());
		}
	}
	
	
	public void testEnDataTypeValue4() throws Exception {
		NestedCriteria criteria = new NestedCriteria();
		criteria.setSourceObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		criteria.setTargetObjectName("gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType");
		EnDataType enDataType = new EnDataType();
		enDataType.setValue4(getEn());
		List<EnDataType> sourceObjectList = new ArrayList<EnDataType>();
		sourceObjectList.add(enDataType);
		criteria.setSourceObjectList(sourceObjectList);
		NestedCriteria2HQL criteria2hql = new NestedCriteria2HQL(criteria,
				configurationHolder.getConfiguration(), true);
		HQLCriteria hqlCriteria = criteria2hql.translate();
		String expectedHQL = "select enDataType_1 from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType as enDataType_1 where enDataType_1.id in (select enDataType_1.id from gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType enDataType_1 where lower(enDataType_1.value4.part_0.value )=? )";
		assertEquals(expectedHQL, hqlCriteria.getHqlString());
	}
	
	private En getEn() {
		En en=new En(); 
		Enxp partInstance=new Enxp();
		partInstance.setType(EntityNamePartType.FAM);
		partInstance.setValue("ENXP Code System");
		en.addPart(partInstance);
		return en;
	}

	private DsetAdDataType getDsetAdDataType() {
		DsetAdDataType dsetAdDataType = new DsetAdDataType();
		DSet<Ad> dSet = new DSet<Ad>();
		Ad ad = new Ad();
		Set<Ad> sets = new HashSet<Ad>();
		List<Adxp> adxps = new ArrayList<Adxp>();
		AdxpAl adxp = new AdxpAl();
		adxp.setValue("value");
		adxps.add(adxp);
		ad.setPart(adxps);
		sets.add(ad);
		dSet.setItem(sets);
		dsetAdDataType.setValue1(dSet);
		return dsetAdDataType;
	}
}
