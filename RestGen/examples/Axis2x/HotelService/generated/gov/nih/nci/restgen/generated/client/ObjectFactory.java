/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package gov.nih.nci.restgen.generated.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.nih.nci.restgen.generated.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetHotelResponseReturn_QNAME = new QName("http://hotel.example.com", "return");
    private final static QName _GetRestaurantChefChefName_QNAME = new QName("http://hotel.example.com", "chefName");
    private final static QName _GetRestaurantChefRestaurantName_QNAME = new QName("http://hotel.example.com", "restaurantName");
    private final static QName _RestaurantId_QNAME = new QName("http://hotel.example.com/xsd", "id");
    private final static QName _RestaurantName_QNAME = new QName("http://hotel.example.com/xsd", "name");
    private final static QName _RestaurantChefs_QNAME = new QName("http://hotel.example.com/xsd", "chefs");
    private final static QName _HotelRestaurants_QNAME = new QName("http://hotel.example.com/xsd", "restaurants");
    private final static QName _HotelAddress_QNAME = new QName("http://hotel.example.com/xsd", "address");
    private final static QName _GetRestaurantsHotelName_QNAME = new QName("http://hotel.example.com", "hotelName");
    private final static QName _HireChefChef_QNAME = new QName("http://hotel.example.com", "chef");
    private final static QName _GetHotelName_QNAME = new QName("http://hotel.example.com", "name");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.nih.nci.restgen.generated.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHotel }
     * 
     */
    public GetHotel createGetHotel() {
        return new GetHotel();
    }

    /**
     * Create an instance of {@link FireChef }
     * 
     */
    public FireChef createFireChef() {
        return new FireChef();
    }

    /**
     * Create an instance of {@link GetRestaurants }
     * 
     */
    public GetRestaurants createGetRestaurants() {
        return new GetRestaurants();
    }

    /**
     * Create an instance of {@link HireChef }
     * 
     */
    public HireChef createHireChef() {
        return new HireChef();
    }

    /**
     * Create an instance of {@link Chef }
     * 
     */
    public Chef createChef() {
        return new Chef();
    }

    /**
     * Create an instance of {@link GetRestaurantsResponse }
     * 
     */
    public GetRestaurantsResponse createGetRestaurantsResponse() {
        return new GetRestaurantsResponse();
    }

    /**
     * Create an instance of {@link Restaurant }
     * 
     */
    public Restaurant createRestaurant() {
        return new Restaurant();
    }

    /**
     * Create an instance of {@link GetHotelResponse }
     * 
     */
    public GetHotelResponse createGetHotelResponse() {
        return new GetHotelResponse();
    }

    /**
     * Create an instance of {@link Hotel }
     * 
     */
    public Hotel createHotel() {
        return new Hotel();
    }

    /**
     * Create an instance of {@link GetRestaurantChefResponse }
     * 
     */
    public GetRestaurantChefResponse createGetRestaurantChefResponse() {
        return new GetRestaurantChefResponse();
    }

    /**
     * Create an instance of {@link GetRestaurantChef }
     * 
     */
    public GetRestaurantChef createGetRestaurantChef() {
        return new GetRestaurantChef();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hotel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com", name = "return", scope = GetHotelResponse.class)
    public JAXBElement<Hotel> createGetHotelResponseReturn(Hotel value) {
        return new JAXBElement<Hotel>(_GetHotelResponseReturn_QNAME, Hotel.class, GetHotelResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com", name = "chefName", scope = GetRestaurantChef.class)
    public JAXBElement<String> createGetRestaurantChefChefName(String value) {
        return new JAXBElement<String>(_GetRestaurantChefChefName_QNAME, String.class, GetRestaurantChef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com", name = "restaurantName", scope = GetRestaurantChef.class)
    public JAXBElement<String> createGetRestaurantChefRestaurantName(String value) {
        return new JAXBElement<String>(_GetRestaurantChefRestaurantName_QNAME, String.class, GetRestaurantChef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com/xsd", name = "id", scope = Restaurant.class)
    public JAXBElement<Integer> createRestaurantId(Integer value) {
        return new JAXBElement<Integer>(_RestaurantId_QNAME, Integer.class, Restaurant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com/xsd", name = "name", scope = Restaurant.class)
    public JAXBElement<String> createRestaurantName(String value) {
        return new JAXBElement<String>(_RestaurantName_QNAME, String.class, Restaurant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com/xsd", name = "chefs", scope = Restaurant.class)
    public JAXBElement<Object> createRestaurantChefs(Object value) {
        return new JAXBElement<Object>(_RestaurantChefs_QNAME, Object.class, Restaurant.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Chef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com", name = "return", scope = GetRestaurantChefResponse.class)
    public JAXBElement<Chef> createGetRestaurantChefResponseReturn(Chef value) {
        return new JAXBElement<Chef>(_GetHotelResponseReturn_QNAME, Chef.class, GetRestaurantChefResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com/xsd", name = "restaurants", scope = Hotel.class)
    public JAXBElement<Object> createHotelRestaurants(Object value) {
        return new JAXBElement<Object>(_HotelRestaurants_QNAME, Object.class, Hotel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com/xsd", name = "name", scope = Hotel.class)
    public JAXBElement<String> createHotelName(String value) {
        return new JAXBElement<String>(_RestaurantName_QNAME, String.class, Hotel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com/xsd", name = "address", scope = Hotel.class)
    public JAXBElement<String> createHotelAddress(String value) {
        return new JAXBElement<String>(_HotelAddress_QNAME, String.class, Hotel.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com", name = "hotelName", scope = GetRestaurants.class)
    public JAXBElement<String> createGetRestaurantsHotelName(String value) {
        return new JAXBElement<String>(_GetRestaurantsHotelName_QNAME, String.class, GetRestaurants.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Chef }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com", name = "chef", scope = HireChef.class)
    public JAXBElement<Chef> createHireChefChef(Chef value) {
        return new JAXBElement<Chef>(_HireChefChef_QNAME, Chef.class, HireChef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com/xsd", name = "id", scope = Chef.class)
    public JAXBElement<Integer> createChefId(Integer value) {
        return new JAXBElement<Integer>(_RestaurantId_QNAME, Integer.class, Chef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com/xsd", name = "name", scope = Chef.class)
    public JAXBElement<String> createChefName(String value) {
        return new JAXBElement<String>(_RestaurantName_QNAME, String.class, Chef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://hotel.example.com", name = "name", scope = GetHotel.class)
    public JAXBElement<String> createGetHotelName(String value) {
        return new JAXBElement<String>(_GetHotelName_QNAME, String.class, GetHotel.class, value);
    }

}
