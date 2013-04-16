/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;

import junit.framework.Assert;

import gov.nih.nci.cacoresdk.domain.inheritance.implicit.AngelFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.DiscusFish;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.FreshwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.SaltwaterFishTank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Substrate;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.Tank;
import gov.nih.nci.cacoresdk.domain.inheritance.implicit.TankAccessory;

public class ImplicitParentWithAssociationWritableApiTest extends SDKWritableBaseTest {
	
	private static Logger log = Logger.getLogger(ImplicitParentWithAssociationWritableApiTest.class);
	public static String getTestCaseName() {
		return "Implicit Parent With Association Writable Api Test Case";
	}
	
	public void testSaveObjectWithImplicitInheritAssociatedObjects(){
		log.debug("\n---------testSaveObjectWithImplicitInheritAssociatedObjects()-------\n");
		DiscusFish discusFish=new DiscusFish();
		discusFish.setGenera("discus");
		discusFish.setPrimaryColor("red");
		
		AngelFish angelFish=new AngelFish();
		angelFish.setGenera("angel");
		angelFish.setFinSize(10);
		
		FreshwaterFishTank freshwaterFishTank=new FreshwaterFishTank();
		freshwaterFishTank.setFilterModel("filtermodel");
		freshwaterFishTank.setNumGallons(200);
		freshwaterFishTank.setShape("shape");
		
		SaltwaterFishTank saltwaterFishTank=new SaltwaterFishTank();
		saltwaterFishTank.setNumGallons(200);
		saltwaterFishTank.setShape("shape");
		saltwaterFishTank.setProteinSkimmerModel("protein");

		Collection<Substrate> substrates=new HashSet<Substrate>();
		Substrate substrate=new Substrate();
		substrate.setName("substrate");
		substrates.add(substrate);
		saltwaterFishTank.setSubstrateCollection(substrates);

		save(substrate);
		save(saltwaterFishTank);
		save(freshwaterFishTank);

		Collection<Tank> tanks=new HashSet<Tank>();
		tanks.add(freshwaterFishTank);
		tanks.add(saltwaterFishTank);
		TankAccessory tankAccessory=new TankAccessory();
		tankAccessory.setName("tankAccessory");
		tankAccessory.setTankCollection(tanks);
		
		save(tankAccessory);
		
		discusFish.setTank(freshwaterFishTank);
		angelFish.setTank(saltwaterFishTank);
		
	 	save(discusFish);
	 	save(angelFish);
	 	
	 	AngelFish resultAngelFish=(AngelFish)getObject(AngelFish.class, angelFish.getId());
	 	Assert.assertEquals(angelFish.getFinSize(), resultAngelFish.getFinSize());	

	}
}
