package test.writable;

import org.apache.log4j.Logger;

import junit.framework.Assert;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.CommunistGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.DemocraticGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.Goverment;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.ParliamantaryGovt;
import gov.nih.nci.cacoresdk.domain.inheritance.twolevelinheritance.sametable.PresidentialGovt;

public class TwoLevelInheritanceSametableWritableApiTest extends SDKWritableApiBaseTest {
	private static Logger log = Logger.getLogger(TwoLevelInheritanceSametableWritableApiTest.class);
	public static String getTestCaseName() {
		return "Two Level Inheritance Same Table Writable Api Test Case";
	}
	
	public void testSaveObjectSameTableTwoLevelInheritance(){
		log.debug("\n-----------testSaveObjectSameTableTwoLevelInheritance()---------\n");
		Goverment goverment=new Goverment();
		goverment.setCountry("usa");
		DemocraticGovt democraticGovt=new DemocraticGovt();
		democraticGovt.setCountry("usa");
		CommunistGovt communistGovt=new CommunistGovt();
		communistGovt.setCountry("usa");
		ParliamantaryGovt parliamantaryGovt=new ParliamantaryGovt();
		parliamantaryGovt.setCountry("usa");
		parliamantaryGovt.setPrimeMinister("primeminister");
		PresidentialGovt presidentialGovt=new PresidentialGovt();
		presidentialGovt.setPresident("president");
		presidentialGovt.setCountry("usa");
		
		save(goverment);
		save(democraticGovt);
		save(communistGovt);
		save(parliamantaryGovt);
		save(presidentialGovt);
		
		Goverment resultGoverment=(Goverment)getObject(Goverment.class, goverment.getId());
		DemocraticGovt resultDemocraticGovt=(DemocraticGovt)getObject(DemocraticGovt.class, democraticGovt.getId());
		CommunistGovt resultCommunistGovt=(CommunistGovt)getObject(CommunistGovt.class, communistGovt.getId());
		PresidentialGovt resultPresidentialGovt=(PresidentialGovt)getObject(PresidentialGovt.class, presidentialGovt.getId());
		ParliamantaryGovt resultParlimantaryGovt=(ParliamantaryGovt)getObject(ParliamantaryGovt.class, parliamantaryGovt.getId());
		
		Assert.assertEquals(goverment.getCountry(), resultGoverment.getCountry());
		Assert.assertEquals(democraticGovt.getCountry(), resultDemocraticGovt.getCountry());
		Assert.assertEquals(communistGovt.getCountry(), resultCommunistGovt.getCountry());
		Assert.assertEquals(parliamantaryGovt.getCountry(), resultParlimantaryGovt.getCountry());
		Assert.assertEquals(presidentialGovt.getCountry(), resultPresidentialGovt.getCountry());
	}
	
	public void testUpdateObjectSameTableTwoLevelInheritance(){
		log.debug("\n-----------testUpdateObjectSameTableTwoLevelInheritance()---------\n");
		Goverment goverment=new Goverment();
		goverment.setCountry("usa");
		DemocraticGovt democraticGovt=new DemocraticGovt();
		democraticGovt.setCountry("usa");
		CommunistGovt communistGovt=new CommunistGovt();
		communistGovt.setCountry("usa");
		ParliamantaryGovt parliamantaryGovt=new ParliamantaryGovt();
		parliamantaryGovt.setCountry("usa");
		parliamantaryGovt.setPrimeMinister("primeminister");
		PresidentialGovt presidentialGovt=new PresidentialGovt();
		presidentialGovt.setPresident("president");
		presidentialGovt.setCountry("usa");
		
		save(goverment);
		save(democraticGovt);
		save(communistGovt);
		save(parliamantaryGovt);
		save(presidentialGovt);
		
		Goverment updateGoverment=(Goverment)getObject(Goverment.class, goverment.getId());
		updateGoverment.setCountry("updateusa");
		DemocraticGovt updateDemocraticGovt=(DemocraticGovt)getObject(DemocraticGovt.class, democraticGovt.getId());
		updateDemocraticGovt.setCountry("updateusa");
		CommunistGovt updateCommunistGovt=(CommunistGovt)getObject(CommunistGovt.class, communistGovt.getId());
		updateCommunistGovt.setCountry("updateusa");
		PresidentialGovt updatePresidentialGovt=(PresidentialGovt)getObject(PresidentialGovt.class, presidentialGovt.getId());
		updatePresidentialGovt.setCountry("updateusa");
		ParliamantaryGovt updateParlimantaryGovt=(ParliamantaryGovt)getObject(ParliamantaryGovt.class, parliamantaryGovt.getId());
		updateParlimantaryGovt.setCountry("updateusa");
		
		update(updateGoverment);
		update(updateDemocraticGovt);
		update(updateCommunistGovt);
		update(updatePresidentialGovt);
		update(updateParlimantaryGovt);
		
		Goverment resultGoverment=(Goverment)getObject(Goverment.class, goverment.getId());
		DemocraticGovt resultDemocraticGovt=(DemocraticGovt)getObject(DemocraticGovt.class, democraticGovt.getId());
		CommunistGovt resultCommunistGovt=(CommunistGovt)getObject(CommunistGovt.class, communistGovt.getId());
		PresidentialGovt resultPresidentialGovt=(PresidentialGovt)getObject(PresidentialGovt.class, presidentialGovt.getId());
		ParliamantaryGovt resultParlimantaryGovt=(ParliamantaryGovt)getObject(ParliamantaryGovt.class, parliamantaryGovt.getId());

		
		Assert.assertEquals(updateGoverment.getCountry(), resultGoverment.getCountry());
		Assert.assertEquals(updateDemocraticGovt.getCountry(), resultDemocraticGovt.getCountry());
		Assert.assertEquals(updateCommunistGovt.getCountry(), resultCommunistGovt.getCountry());
		Assert.assertEquals(updateParlimantaryGovt.getCountry(), resultParlimantaryGovt.getCountry());
		Assert.assertEquals(updatePresidentialGovt.getCountry(), resultPresidentialGovt.getCountry());
	}
	
	public void testDeleteObjectSameTableTwoLevelInheritance(){
		log.debug("\n-----------testDeleteObjectSameTableTwoLevelInheritance()---------\n");
		Goverment goverment=new Goverment();
		goverment.setCountry("usa");
		DemocraticGovt democraticGovt=new DemocraticGovt();
		democraticGovt.setCountry("usa");
		CommunistGovt communistGovt=new CommunistGovt();
		communistGovt.setCountry("usa");
		ParliamantaryGovt parliamantaryGovt=new ParliamantaryGovt();
		parliamantaryGovt.setCountry("usa");
		parliamantaryGovt.setPrimeMinister("primeminister");
		PresidentialGovt presidentialGovt=new PresidentialGovt();
		presidentialGovt.setPresident("president");
		presidentialGovt.setCountry("usa");
		
		save(goverment);
		save(democraticGovt);
		save(communistGovt);
		save(parliamantaryGovt);
		save(presidentialGovt);
		
		Goverment deleteGoverment=(Goverment)getObject(Goverment.class, goverment.getId());
		DemocraticGovt deleteDemocraticGovt=(DemocraticGovt)getObject(DemocraticGovt.class, democraticGovt.getId());
		CommunistGovt deleteCommunistGovt=(CommunistGovt)getObject(CommunistGovt.class, communistGovt.getId());
		PresidentialGovt deletePresidentialGovt=(PresidentialGovt)getObject(PresidentialGovt.class, presidentialGovt.getId());
		ParliamantaryGovt deleteParlimantaryGovt=(ParliamantaryGovt)getObject(ParliamantaryGovt.class, parliamantaryGovt.getId());

		delete(deleteGoverment);
		delete(deleteDemocraticGovt);
		delete(deleteCommunistGovt);
		delete(deletePresidentialGovt);
		delete(deleteParlimantaryGovt);
		
		Goverment resultGoverment=(Goverment)getObject(Goverment.class, goverment.getId());
		DemocraticGovt resultDemocraticGovt=(DemocraticGovt)getObject(DemocraticGovt.class, democraticGovt.getId());
		CommunistGovt resultCommunistGovt=(CommunistGovt)getObject(CommunistGovt.class, communistGovt.getId());
		PresidentialGovt resultPresidentialGovt=(PresidentialGovt)getObject(PresidentialGovt.class, presidentialGovt.getId());
		ParliamantaryGovt resultParlimantaryGovt=(ParliamantaryGovt)getObject(ParliamantaryGovt.class, parliamantaryGovt.getId());

		Assert.assertNull(resultGoverment);
		Assert.assertNull(resultDemocraticGovt);
		Assert.assertNull(resultCommunistGovt);
		Assert.assertNull(resultPresidentialGovt);
		Assert.assertNull(resultParlimantaryGovt);
	}
}
