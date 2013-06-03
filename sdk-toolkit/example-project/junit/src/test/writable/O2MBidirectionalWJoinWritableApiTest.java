/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.junit.Assert;

import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Flight;
import gov.nih.nci.cacoresdk.domain.onetomany.bidirectional.withjoin.Passanger;

public class O2MBidirectionalWJoinWritableApiTest extends SDKWritableBaseTest {
	private static Logger log = Logger.getLogger(O2MBidirectionalWJoinWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to Many Bidirectional With Join WritableApi Test Case";
	}
	
	public void testSaveObjectPassenger(){
		log.debug("\n--------testSaveObjectPassenger()---------------\n\n");
		Passanger passanger=new Passanger();
		passanger.setName("name");
		
		save(passanger);
		
		Passanger result=(Passanger)getObject(Passanger.class, passanger.getId());
		Assert.assertEquals(passanger.getName(), result.getName());
	}
	
	public void testSaveObjectPassengerWithMany2OneObjectFlightInverseFalse(){
		log.debug("\n\n--------testSaveObjectPassengerWithMany2OneObjectFlight()---------------\n\n");
		Passanger passanger=new Passanger();
		passanger.setName("name");
		Flight flight=new Flight();
		flight.setDestination("destination");
		passanger.setFlight(flight);
		
		save(flight);
		save(passanger);
		
		Passanger resultPassenger=(Passanger)getObjectAndLazyObject(Passanger.class, passanger.getId(),"flight");
		Flight resultFlight=resultPassenger.getFlight();
		Assert.assertEquals(passanger.getName(), resultPassenger.getName());
		Assert.assertEquals(resultFlight.getDestination(), resultFlight.getDestination());
	}
	
	public void testSaveObjectFlightWithOne2ManyPassengersInverseTrue(){
		log.debug("\n\n--------testSaveObjectFlightWithOne2ManyFlightsInverseTrue()---------------\n\n");
		Flight flight=new Flight();
		flight.setDestination("destination");
		Passanger passanger=new Passanger();
		passanger.setName("name");
        Collection<Passanger> passengers=new HashSet<Passanger>();
        passengers.add(passanger);
        passengers.add(passanger);
        flight.setPassangerCollection(passengers);
        
		save(flight);
		
		Flight resultFlight=(Flight)getObjectAndLazyObject(Flight.class, flight.getId(),"passangerCollection");
		Collection<Passanger> resultPassengers=resultFlight.getPassangerCollection();
		Assert.assertEquals(flight.getDestination(), resultFlight.getDestination());
		Assert.assertEquals("inverse =true,Flight_Passenger relation table must not be inserted",0,resultPassengers.size());
	}
	
	public void testUpdateObjectPassengerWithMany2OneFlight(){
		log.debug("\n\n--------testUpdateObjectPassengerWithMany2OneFlight()---------------\n\n");
		Flight flight=new Flight();
		flight.setDestination("destination");
		Passanger passanger=new Passanger();
		passanger.setName("name");
		passanger.setFlight(flight);
        
		save(flight);
		save(passanger); //inverse=false
		
		Flight updateFlight=(Flight)getObjectAndLazyObject(Flight.class, flight.getId(),"passangerCollection");
		Collection<Passanger> updatePassengers=updateFlight.getPassangerCollection();
		updateFlight.setDestination("updateDestination");
		updatePassengers.iterator().next().setName("updatePassenger");
		
		update(updateFlight);//cascade -- save-update
		
		Flight resultFlight=(Flight)getObjectAndLazyObject(Flight.class, updateFlight.getId(),"passangerCollection");
		Collection<Passanger> resultPassengers=resultFlight.getPassangerCollection();
		Passanger resultPassenger=resultPassengers.iterator().next();
		
		Assert.assertEquals("updatePassenger", resultPassenger.getName());
		Assert.assertEquals(updateFlight.getDestination(), resultFlight.getDestination());
	}
	
	public void testDeleteFlightWithOne2ManyCascadeSaveUpdate(){
		log.debug("\n\n--------testDeleteFlightWithOne2ManyCascadeSaveUpdate()---------------\n\n");
		Flight flight=new Flight();
		flight.setDestination("destination");
		Passanger passanger=new Passanger();
		passanger.setName("name");
        Collection<Passanger> passengers=new HashSet<Passanger>();
        passengers.add(passanger);
        passengers.add(passanger);
        flight.setPassangerCollection(passengers);
        
		save(flight);
		passanger=flight.getPassangerCollection().iterator().next();
		Flight deleteFlight=(Flight)getObject(Flight.class, flight.getId());
		
		delete(deleteFlight);
		
		Flight resultFlight=(Flight)getObject(Flight.class, deleteFlight.getId());
		Passanger resultPassenger=(Passanger)getObject(Passanger.class, passanger.getId());

		Assert.assertNull("flight must be deleted ",resultFlight);
		Assert.assertNotNull("passenger must not be deleted",resultPassenger);
	}
	
	public void testDeletePassengerWithMany2OneCascadeNone(){
		log.debug("\n\n--------testDeletePassengerWithMany2OneCascadeNone()---------------\n\n");
		Flight flight=new Flight();
		flight.setDestination("destination");
		Passanger passanger=new Passanger();
		passanger.setName("name");
        Collection<Passanger> passengers=new HashSet<Passanger>();
        passengers.add(passanger);
        passengers.add(passanger);
        flight.setPassangerCollection(passengers);
        
		save(flight);
		passanger=flight.getPassangerCollection().iterator().next();
		Flight deleteFlight=(Flight)getObject(Flight.class, flight.getId());
		
		delete(deleteFlight);
		
		Flight resultFlight=(Flight)getObject(Flight.class, deleteFlight.getId());
		Passanger resultPassenger=(Passanger)getObject(Passanger.class, passanger.getId());

		Assert.assertNull("flight must be deleted ",resultFlight);
		Assert.assertNotNull("passenger must not be deleted",resultPassenger);
	}
}
