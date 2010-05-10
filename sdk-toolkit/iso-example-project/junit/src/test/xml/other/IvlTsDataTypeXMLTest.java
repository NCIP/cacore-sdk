package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jdom.Document;

import test.gov.nih.nci.cacoresdk.SDKISOTestBase;

/*
 * Test case for Ts data type
 */
public class IvlTsDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ivl<Ts> Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue1ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlTsDataType.class);
		criteria.add(Restrictions.or(Property.forName("value1.low.value").isNotNull(), Property.forName("value1.high.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}

	/**
	 * Search Value1 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue1ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType a where (a.value1.low.value is not null or a.value1.high.value is not null) order by a.id asc asc");
		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(5, result.size());
		assertValue1(result);
	}


	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue2ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlTsDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.high.value").isNotNull(), Property.forName("value2.lowClosed").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue2ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType a where (a.value2.high.value is not null or a.value2.lowClosed is not null) order by a.id asc asc");
		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(6, result.size());
		assertValue2(result);
	}

	/**
	 * Search Value2 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue3ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlTsDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value3.high.value").isNotNull(), Property.forName("value3.low.value").isNotNull());
		LogicalExpression exp2 = Restrictions.or(Property.forName("value3.width.value").isNotNull(), Property.forName("value3.width.nullFlavor").isNotNull()); 
		criteria.add(Restrictions.or(exp1, exp2));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(9, result.size());
		assertValue3(result);
	}

	/**
	 * Search Value3 by HQL criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue3ByHQLCriteria() throws ApplicationException, ParseException
	{
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType a where (a.value3.low.value is not null or a.value3.high.value is not null or a.value3.width.value is not null or a.value3.width.nullFlavor is not null) order by a.id asc asc");
		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertEquals(9, result.size());
		assertValue3(result);
	}

	
	/**
	 * Test all rows and columns
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlTsDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<IvlTsDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlTsDataType");
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
	}
	
	private void assertValue1(Collection<IvlTsDataType> result) throws ParseException
	{
		assertNotNull(result);

		int counter = 1;
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		
		for(IvlTsDataType data : result)
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
					IvlTsDataType unmarshalledData = (IvlTsDataType)fromXML(xmlFileName);
					
					//Make sure date format matches
					if(data.getValue1().getAny() != null && data.getValue1().getAny().getValue() != null)
					{
						Date date1 = data.getValue1().getAny().getValue();
						if(date1 != null)
							data.getValue1().getAny().setValue(df.parse(df.format(date1)));
					}
					if(data.getValue1().getHigh() != null && data.getValue1().getHigh().getValue() != null)
					{
						Date date1 = data.getValue1().getHigh().getValue();
						if(date1 != null)
							data.getValue1().getHigh().setValue(df.parse(df.format(date1)));
					}
					if(data.getValue1().getLow() != null && data.getValue1().getLow().getValue() != null)
					{
						Date date1 = data.getValue1().getLow().getValue();
						if(date1 != null)
							data.getValue1().getLow().setValue(df.parse(df.format(date1)));
					}

					if(unmarshalledData.getValue1().getAny() != null && unmarshalledData.getValue1().getAny().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue1().getAny().getValue();
						if(date2 != null)
							unmarshalledData.getValue1().getAny().setValue(df.parse(df.format(date2)));
					}
					if(unmarshalledData.getValue1().getHigh() != null && unmarshalledData.getValue1().getHigh().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue1().getHigh().getValue();
						if(date2 != null)
							unmarshalledData.getValue1().getHigh().setValue(df.parse(df.format(date2)));
					}
					if(unmarshalledData.getValue1().getLow() != null && unmarshalledData.getValue1().getLow().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue1().getLow().getValue();
						if(date2 != null)
							unmarshalledData.getValue1().getLow().setValue(df.parse(df.format(date2)));
					}
					
					
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

	private void assertValue2(Collection<IvlTsDataType> result) throws ParseException
	{
		assertNotNull(result);
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		int counter = 1;
		for(IvlTsDataType data : result)
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
					IvlTsDataType unmarshalledData = (IvlTsDataType)fromXML(xmlFileName);

					//Make sure date format matches
					if(data.getValue2().getAny() != null && data.getValue2().getAny().getValue() != null)
					{
						Date date1 = data.getValue2().getAny().getValue();
						if(date1 != null)
							data.getValue2().getAny().setValue(df.parse(df.format(date1)));
					}
					if(data.getValue2().getHigh() != null && data.getValue2().getHigh().getValue() != null)
					{
						Date date1 = data.getValue2().getHigh().getValue();
						if(date1 != null)
							data.getValue2().getHigh().setValue(df.parse(df.format(date1)));
					}
					if(data.getValue2().getLow() != null && data.getValue2().getLow().getValue() != null)
					{
						Date date1 = data.getValue2().getLow().getValue();
						if(date1 != null)
							data.getValue2().getLow().setValue(df.parse(df.format(date1)));
					}

					if(unmarshalledData.getValue2().getAny() != null && unmarshalledData.getValue2().getAny().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue2().getAny().getValue();
						if(date2 != null)
							unmarshalledData.getValue2().getAny().setValue(df.parse(df.format(date2)));
					}
					if(unmarshalledData.getValue2().getHigh() != null && unmarshalledData.getValue2().getHigh().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue2().getHigh().getValue();
						if(date2 != null)
							unmarshalledData.getValue2().getHigh().setValue(df.parse(df.format(date2)));
					}
					if(unmarshalledData.getValue2().getLow() != null && unmarshalledData.getValue2().getLow().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue2().getLow().getValue();
						if(date2 != null)
							unmarshalledData.getValue2().getLow().setValue(df.parse(df.format(date2)));
					}
					
					
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

	private void assertValue3(Collection<IvlTsDataType> result) throws ParseException
	{
		assertNotNull(result);
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		int counter = 1;
		for(IvlTsDataType data : result)
		{
				assertNotNull(data);
				if(data.getValue3() == null)
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
					IvlTsDataType unmarshalledData = (IvlTsDataType)fromXML(xmlFileName);
					//Make sure date format matches
					if(data.getValue3().getAny() != null && data.getValue3().getAny().getValue() != null)
					{
						Date date1 = data.getValue3().getAny().getValue();
						if(date1 != null)
							data.getValue3().getAny().setValue(df.parse(df.format(date1)));
					}
					if(data.getValue3().getHigh() != null && data.getValue3().getHigh().getValue() != null)
					{
						Date date1 = data.getValue3().getHigh().getValue();
						if(date1 != null)
							data.getValue3().getHigh().setValue(df.parse(df.format(date1)));
					}
					if(data.getValue3().getLow() != null && data.getValue3().getLow().getValue() != null)
					{
						Date date1 = data.getValue3().getLow().getValue();
						if(date1 != null)
							data.getValue3().getLow().setValue(df.parse(df.format(date1)));
					}

					if(unmarshalledData.getValue3().getAny() != null && unmarshalledData.getValue3().getAny().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue3().getAny().getValue();
						if(date2 != null)
							unmarshalledData.getValue3().getAny().setValue(df.parse(df.format(date2)));
					}
					if(unmarshalledData.getValue3().getHigh() != null && unmarshalledData.getValue3().getHigh().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue3().getHigh().getValue();
						if(date2 != null)
							unmarshalledData.getValue3().getHigh().setValue(df.parse(df.format(date2)));
					}
					if(unmarshalledData.getValue3().getLow() != null && unmarshalledData.getValue3().getLow().getValue() != null)
					{
						Date date2 = unmarshalledData.getValue3().getLow().getValue();
						if(date2 != null)
							unmarshalledData.getValue3().getLow().setValue(df.parse(df.format(date2)));
					}
					
					
					//Verify umarshalled object with original object
					assertTrue(compareValue3(data, unmarshalledData));
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
	
	private boolean compareValue1(IvlTsDataType actual, IvlTsDataType result)
	{
		Ivl<Ts> aVal = actual.getValue1();
		assertNotNull(aVal);
		handleAny(aVal);
		Ivl<Ts> rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(IvlTsDataType actual, IvlTsDataType result)
	{
		Ivl<Ts> aVal = actual.getValue2();
		assertNotNull(aVal);
		handleAny(aVal);
        Ivl<Ts> rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(IvlTsDataType actual, IvlTsDataType result)
	{
		Ivl<Ts> aVal = actual.getValue3();
		assertNotNull(aVal);
		handleAny(aVal);
		Ivl<Ts> rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private void handleAny(Ivl<Ts> aVal)
	{
    	//IVL Transformer strips out Any value if High and Low values are not null 
        if ((aVal.getHigh() != null && aVal.getHigh().getNullFlavor() == null)&& (aVal.getLow() != null && aVal.getLow().getNullFlavor() == null)) {
        	aVal.setAny(null);
        }

        if (aVal.isLowMissing() || aVal.isHighEqualLow()) {
        	aVal.setAny(aVal.getHigh());
        } else if (aVal.isHighMissing()) {
        	aVal.setAny(aVal.getLow());
        }
		
	}
}
