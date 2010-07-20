package test.gov.nih.nci.system.web.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author;
import gov.nih.nci.system.dao.DAO;
import gov.nih.nci.system.dao.DAOException;
import gov.nih.nci.system.dao.orm.HibernateConfigurationHolder;
import gov.nih.nci.system.dao.orm.ORMDAOImpl;
import gov.nih.nci.system.util.ClassCache;

import org.hibernate.cfg.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

public class ClassCacheTest extends TestCase {
	static String paths[] = {"application-config.xml"};
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
			paths);;
	private ClassCache classCache;
	private HibernateConfigurationHolder configurationHolder;
	private Configuration cfg = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		classCache = (ClassCache) ctx.getBean("ClassCache");
		configurationHolder = (HibernateConfigurationHolder) ctx
				.getBean("HibernateConfigHolder");
		cfg = configurationHolder.getConfiguration();
	}

	public void testAvailablePackages() {
		List<String> packages = classCache.getAllPackageNames();
		assertEquals(35, packages.size());
	}

	public void testAvailableClasses() {
		List<String> qualifiedClassNames = classCache.getAllQualClassNames();
		assertEquals(136, qualifiedClassNames.size());
	}

	public void testFieldNamesForAClass() {
		List<String> fieldNames = classCache
				.getAllFieldNames("gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author");
		Iterator<String> iterator = fieldNames.iterator();
		assertEquals("id", iterator.next());
		assertEquals("name", iterator.next());
	}

	public void testFieldsForAClass() {
		Field[] fields = classCache.getAllFields(Author.class);
		assertEquals(3, fields.length);
	}

	@SuppressWarnings("unused")
	public void testSearchableFieldForAClass() {
		try {
			Class<? extends Object> klass = classCache
					.getClassFromCache("gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Author");
			Field[] fields = classCache.getAllFields(klass);
			List<Field> searchableFields = new ArrayList<Field>();
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				String fieldType = fields[i].getType().getName();
				if (classCache.isSearchable(fieldType)) {
					searchableFields.add(fields[i]);
				}
			}
			for (Field field : searchableFields) {
			}
		} catch (ClassNotFoundException ex) {
			fail(ex.getMessage());
		}
	}

	public void testCreateMapOfSearchFieldsForAPersistentClassBlDataType() {
		String objectClassName = "gov.nih.nci.cacoresdk.domain.other.datatype.BlDataType";
		processOutput(cfg, objectClassName);
	}

	public void testCreateMapOfSearchFieldsForAPersistentClassCdDataType() {
		String objectClassName = "gov.nih.nci.cacoresdk.domain.other.datatype.CdDataType";
		processOutput(cfg, objectClassName);
	}
	
	public void testCreateMapOfSearchFieldsForAPersistentClassAdDataType() {
		String objectClassName = "gov.nih.nci.cacoresdk.domain.other.datatype.AdDataType";
		processOutput(cfg, objectClassName);
	}
	
	public void testCreateMapOfSearchFieldsForAPersistentClassEnDataType() {
		String objectClassName = "gov.nih.nci.cacoresdk.domain.other.datatype.EnDataType";
		processOutput(cfg, objectClassName);
	}
	
	public void testClassCacheInitlization() {
		Map<String, Map<String, List<String>>> mapOfSearchFields = classCache
				.getSearchableFieldsMap();
		processOutput(mapOfSearchFields);
	}
	
	private void processOutput(Configuration cfg, String objectClassName) {
		Map<String, Map<String, List<String>>> mapOfSearchFields = classCache
		.getMapOfSearchFields(cfg, objectClassName);
		processOutput(mapOfSearchFields);
	}

	private void processOutput(
			Map<String, Map<String, List<String>>> searchableFieldsMap) {
		Iterator<Entry<String, Map<String, List<String>>>> isoDataTypes = searchableFieldsMap
				.entrySet().iterator();
		
		while (isoDataTypes.hasNext()) {
			Entry<String, Map<String, List<String>>> objectNameMapEntry = isoDataTypes
					.next();
			String objectName = objectNameMapEntry.getKey();
			Map<String, List<String>> isoAttributeMapList = objectNameMapEntry
					.getValue();
			System.out.println("\nClass Name " + objectName);
			Iterator<Entry<String, List<String>>> isoAttributeIterator = isoAttributeMapList
					.entrySet().iterator();
			while (isoAttributeIterator.hasNext()) {
				Entry<String, List<String>> entry = isoAttributeIterator.next();
				System.out.println("\nAttribute Name >> " + entry.getKey());
				List<String> isoFields = entry.getValue();
				System.out.print("Fields >>>> ");
				for (String isoField : isoFields) {
					System.out.print(isoField + " ,");
				}
			}
		}

	}
}
