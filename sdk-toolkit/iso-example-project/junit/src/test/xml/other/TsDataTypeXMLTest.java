package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Ts data type
 */
public class TsDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ts Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTsValue1ByDetachedCriteria() throws ApplicationException, ParseException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TsDataType.class);
		criteria.add(Property.forName("value1.value").isNotNull());
		criteria.addOrder(Order.asc("id"));

		Collection<TsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTsValue1ByHQLCriteria() throws ApplicationException, ParseException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType a where a.value1.value is not null order by a.id asc asc");
		Collection<TsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTsValue2ByDetachedCriteria() throws ApplicationException, ParseException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TsDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.value").isNotNull(), Property.forName("value2.nullFlavor").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<TsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTsValue2ByHQLCriteria() throws ApplicationException, ParseException, URISyntaxException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType a where (a.value2.value is not null or a.value2.nullFlavor is not null) order by a.id asc asc");
		Collection<TsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Test all records and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testTsValue() throws ApplicationException, ParseException, URISyntaxException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(TsDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<TsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.TsDataType");
		assertValue1(result);
		assertValue2(result);
	}

	
	private void assertValue1(Collection<TsDataType> result) throws ParseException
	{
		assertNotNull(result);

		int counter = 1;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		for(TsDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue1() == null)
					continue;
				try
				{
					String xmlFileName = getXMLFileName(data, counter);
					String schemaFileName = getSchemaFileName(data.getClass());
					//Marshall the object
					toXML(xmlFileName, data);
					Document document = getDocument(xmlFileName);
					//Validate the XML
					validateClassElements(document, data);
					assertTrue(validateXMLData(result, schemaFileName, xmlFileName));
					
					//Unmarshall XML to data object
					TsDataType unmarshalledData = (TsDataType)fromXML(xmlFileName);
					
					//Make sure date format matches
					Date date1 = data.getValue1().getValue();
					if(date1 != null)
						data.getValue1().setValue(df.parse(df.format(date1)));
					
					Date date2 = unmarshalledData.getValue1().getValue();
					if(date2 != null)
						unmarshalledData.getValue1().setValue(df.parse(df.format(date2)));
					//Verify umarshalled object with original object
					assertTrue(compareValue1(data, unmarshalledData));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				catch(XMLUtilityException e)
				{
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				counter++;
		}
	}

	private void assertValue2(Collection<TsDataType> result) throws ParseException
	{
		assertNotNull(result);

		int counter = 1;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		for(TsDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue2() == null)
					continue;
				try
				{
					String xmlFileName = getXMLFileName(data, counter);
					String schemaFileName = getSchemaFileName(data.getClass());
					//Marshall the object
					toXML(xmlFileName, data);
					Document document = getDocument(xmlFileName);
					//Validate the XML
					validateClassElements(document, data);
					assertTrue(validateXMLData(result, schemaFileName, xmlFileName));
					
					//Unmarshall XML to data object
					TsDataType unmarshalledData = (TsDataType)fromXML(xmlFileName);
					
					//Make sure date format matches
					Date date1 = data.getValue2().getValue();
					if(date1 != null)
						data.getValue2().setValue(df.parse(df.format(date1)));
					
					Date date2 = unmarshalledData.getValue2().getValue();
					if(date2 != null)
						unmarshalledData.getValue2().setValue(df.parse(df.format(date2)));
					//Verify umarshalled object with original object
					assertTrue(compareValue2(data, unmarshalledData));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				catch(XMLUtilityException e)
				{
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				counter++;
		}
	}

	private boolean compareValue1(TsDataType actual, TsDataType result)
	{
		Ts aTs = actual.getValue1();
		assertNotNull(aTs);
		Ts rTs = result.getValue1();
		assertNotNull(rTs);
		return aTs.equals(rTs);
	}
	
	private boolean compareValue2(TsDataType actual, TsDataType result)
	{
		Ts aTs = actual.getValue2();
		assertNotNull(aTs);
		Ts rTs = result.getValue2();
		assertNotNull(rTs);
		return aTs.equals(rTs);
	}
}
