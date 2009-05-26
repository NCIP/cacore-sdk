package test.writable;

import org.apache.log4j.Logger;

import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Chain;
import gov.nih.nci.cacoresdk.domain.onetoone.bidirectional.withjoin.Pendant;
import junit.framework.Assert;

public class O2OBidirectionalWithJoinWritableApiTest extends SDKWritableApiBaseTest {
	private static Logger log = Logger.getLogger(O2OBidirectionalWithJoinWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to One Bidirectional With Join WritableApi Test Case";
	}
	
	public void testSaveObjectChain(){
		log.debug("\n-----------testSaveObjectChain()--------\n");
		Chain chain=new Chain();
		chain.setMetal("metal");

		save(chain);
		
		Chain resultChain=(Chain)getObject(Chain.class, chain.getId());
		Assert.assertEquals(chain.getMetal(), resultChain.getMetal());
	}

	public void testSaveObjectPendantOne2OneChain(){
		log.debug("\n-----------testSaveObjectPendantOne2OneChain()--------\n");
		Chain chain = new Chain();
		chain.setMetal("metal");
		Pendant pendant = new Pendant();
		pendant.setShape("shape");
		chain.setPendant(pendant);
		
		save(pendant);
		save(chain); //inverse=false on chain.hbm.xml
		
		Chain resultChain=(Chain)getObjectAndLazyObject(Chain.class, chain.getId(), "pendant");
		Assert.assertEquals(chain.getMetal(), resultChain.getMetal());
		Assert.assertEquals(pendant.getShape(), resultChain.getPendant().getShape());
	}
	
	public void testUpdateChainOnetoOneCascadeDeletePendant(){
		
		log.debug("\n-----------testUpdateChainOnetoOneCascadeDeletePendant()--------\n");
		Chain chain=new Chain();
		chain.setMetal("metal");
		Pendant pendant = new Pendant();
		pendant.setShape("shape");
		chain.setPendant(pendant);
		
		save(pendant);
		save(chain);//inverse=false on chain.hbm.xml

		Chain updateChain=(Chain)getObjectAndLazyObject(Chain.class, chain.getId(), "pendant");
		updateChain.setMetal("updateMetal");
		updateChain.getPendant().setShape("updateShape");
		update(updateChain);

		Chain resultChain=(Chain)getObjectAndLazyObject(Chain.class, chain.getId(), "pendant");
		Assert.assertEquals(updateChain.getMetal(), resultChain.getMetal());
		Assert.assertFalse(updateChain.getPendant().getShape().equals(resultChain.getPendant().getShape()));
	}
	
	public void testDeleteChainOnetoOneCascadeDeletePendant(){
		
		log.debug("\n-----------deleteChainOnetoOneCascadeDeletePendant()--------\n");
		Chain chain = new Chain();
		chain.setMetal("metal");
		Pendant pendant = new Pendant();
		pendant.setShape("shape");
		chain.setPendant(pendant);
		
		save(pendant);
		save(chain);//inverse=false on chain.hbm.xml

		Chain deleteChain=(Chain)getObjectAndLazyObject(Chain.class, chain.getId(), "pendant");
		delete(deleteChain);

		Chain resultChain=(Chain)getObject(Chain.class, chain.getId());
		Pendant resultPendant=(Pendant)getObject(Pendant.class, pendant.getId());
		Assert.assertNull("chain must be deleted ",resultChain);
		Assert.assertNull("pendant must be deleted as cascade=delete",resultPendant);		
	}
}
