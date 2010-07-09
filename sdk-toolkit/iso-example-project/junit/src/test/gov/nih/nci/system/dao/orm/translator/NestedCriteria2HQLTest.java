package test.gov.nih.nci.system.dao.orm.translator;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cacoresdk.domain.inheritance.childwithassociation.Credit;
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
}
