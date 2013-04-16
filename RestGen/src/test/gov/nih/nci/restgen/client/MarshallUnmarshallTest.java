/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.gov.nih.nci.restgen.client;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
 

public class MarshallUnmarshallTest {

    public static void main(String[] args) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Customer.class);
 
        StreamSource xml = new StreamSource("C:\\DEV\\RestGen\\src\\test\\gov\\nih\\nci\\restgen\\client\\customer.xml");
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<Customer> je1 = unmarshaller.unmarshal(xml, Customer.class);
        Customer customer = je1.getValue();
 
        JAXBElement<Customer> je2 = new JAXBElement<Customer>(new QName("customer"), Customer.class, customer);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(je2, System.out);
        
        xml = new StreamSource("C:\\DEV\\RestGen\\src\\test\\gov\\nih\\nci\\restgen\\client\\book.xml");
        InputStream in = new FileInputStream(new File("C:\\DEV\\RestGen\\src\\test\\gov\\nih\\nci\\restgen\\client\\book.xml"));
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlr = xmlif.createXMLStreamReader(in);        
        jc = JAXBContext.newInstance(Book.class);
        unmarshaller = jc.createUnmarshaller();
        //JAXBElement<Book> je3 = unmarshaller.unmarshal(xml, Book.class);
        JAXBElement je3 = (JAXBElement)unmarshaller.unmarshal(xmlr, Book.class);
        Book book = (Book)je3.getValue();
 
        JAXBElement je4 = new JAXBElement(new QName("book"), Book.class, book);
        marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(je4, System.out);
        
    }
 
}