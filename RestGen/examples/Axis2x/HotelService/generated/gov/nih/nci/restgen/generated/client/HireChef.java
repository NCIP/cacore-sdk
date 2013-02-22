
package gov.nih.nci.restgen.generated.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chef" type="{http://hotel.example.com/xsd}Chef" minOccurs="0"/>
 *         &lt;element name="restaurantId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "chef",
    "restaurantId"
})
@XmlRootElement(name = "hireChef")
public class HireChef {

    @XmlElementRef(name = "chef", namespace = "http://hotel.example.com", type = JAXBElement.class)
    protected JAXBElement<Chef> chef;
    protected Integer restaurantId;

    /**
     * Gets the value of the chef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Chef }{@code >}
     *     
     */
    public JAXBElement<Chef> getChef() {
        return chef;
    }

    /**
     * Sets the value of the chef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Chef }{@code >}
     *     
     */
    public void setChef(JAXBElement<Chef> value) {
        this.chef = value;
    }

    /**
     * Gets the value of the restaurantId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRestaurantId() {
        return restaurantId;
    }

    /**
     * Sets the value of the restaurantId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRestaurantId(Integer value) {
        this.restaurantId = value;
    }

}
