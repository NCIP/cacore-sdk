package test.xml.other;


import gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Real;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.client.util.xml.XMLUtilityException;
import gov.nih.nci.system.query.hibernate.HQLCriteria;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

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
public class IvlRealDataTypeXMLTest extends SDKISOTestBase
{

	/**
	 * Returns name of the test case
	 * @return
	 */
	public static String getTestCaseName()
	{
		return "Ivl<Real> Datatype XML Test Case";
	}

	/**
	 * Search Value1 by detached criteria Test
	 * 
	 * @throws ApplicationException 
	 */
	@SuppressWarnings("unchecked")
	public void testIvlTsValue1ByDetachedCriteria() throws ApplicationException, ParseException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlRealDataType.class);
		criteria.add(Restrictions.or(Property.forName("value1.low.value").isNotNull(), Property.forName("value1.high.value").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType a where (a.value1.low.value is not null or a.value1.high.value is not null) order by a.id asc asc");
		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
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
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlRealDataType.class);
		criteria.add(Restrictions.or(Property.forName("value2.high.value").isNotNull(), Property.forName("value2.lowClosed").isNotNull()));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType a where (a.value2.high.value is not null or a.value2.lowClosed is not null) order by a.id asc asc");
		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
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
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlRealDataType.class);
		LogicalExpression exp1 = Restrictions.or(Property.forName("value3.high.value").isNotNull(), Property.forName("value3.low.value").isNotNull());
		LogicalExpression exp2 = Restrictions.or(Property.forName("value3.width.value").isNotNull(), Property.forName("value3.width.nullFlavor").isNotNull()); 
		LogicalExpression exp3 = Restrictions.or(exp1, exp2);
		criteria.add(Restrictions.or(Property.forName("value3.nullFlavor").isNotNull(), exp3));
		criteria.addOrder(Order.asc("id"));

		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
		assertEquals(10, result.size());
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
		HQLCriteria criteria = new HQLCriteria("from gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType a where (a.value3.low.value is not null or a.value3.high.value is not null or a.value3.width.value is not null or a.value3.width.nullFlavor is not null or a.value3.nullFlavor is not null) order by a.id asc asc");
		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
		assertEquals(10, result.size());
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
		DetachedCriteria criteria = DetachedCriteria.forClass(IvlRealDataType.class);
		criteria.addOrder(Order.asc("id"));
		Collection<IvlRealDataType> result = search(criteria, "gov.nih.nci.cacoresdk.domain.other.datatype.IvlRealDataType");
		assertValue1(result);
		assertValue2(result);
		assertValue3(result);
	}
	
	private void assertValue1(Collection<IvlRealDataType> result) throws ParseException
	{
		assertNotNull(result);

		int counter = 1;
		for(IvlRealDataType data : result)
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
					IvlRealDataType unmarshalledData = (IvlRealDataType)fromXML(xmlFileName);
					
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

	private void assertValue2(Collection<IvlRealDataType> result) throws ParseException
	{
		assertNotNull(result);
		int counter = 1;
		for(IvlRealDataType data : result)
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
					IvlRealDataType unmarshalledData = (IvlRealDataType)fromXML(xmlFileName);
					
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

	private void assertValue3(Collection<IvlRealDataType> result) throws ParseException
	{
		assertNotNull(result);
		int counter = 1;
		for(IvlRealDataType data : result)
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
					IvlRealDataType unmarshalledData = (IvlRealDataType)fromXML(xmlFileName);
					
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
	
	private boolean compareValue1(IvlRealDataType actual, IvlRealDataType result)
	{
		Ivl<Real> aVal = actual.getValue1();
		assertNotNull(aVal);
		handleAny(aVal);
		Ivl<Real> rVal = result.getValue1();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private boolean compareValue2(IvlRealDataType actual, IvlRealDataType result)
	{
		Ivl<Real> aVal = actual.getValue2();
		assertNotNull(aVal);
		handleAny(aVal);
		Ivl<Real> rVal = result.getValue2();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}

	private boolean compareValue3(IvlRealDataType actual, IvlRealDataType result)
	{
		Ivl<Real> aVal = actual.getValue3();
		assertNotNull(aVal);
		handleAny(aVal);
		Ivl<Real> rVal = result.getValue3();
		assertNotNull(rVal);
		return aVal.equals(rVal);
	}
	
	private void handleAny(Ivl<Real> aVal)
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
