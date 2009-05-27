package test.writable;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.junit.Assert;

import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain;
import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey;

public class O2MUnidirectionalWritableApiTest extends SDKWritableApiTestBase {
	private static Logger log = Logger.getLogger(O2MUnidirectionalWritableApiTest.class);
	public static String getTestCaseName() {
		return "One to Many WritableApi Test Case";
	}
	
	public void testSaveKeyChain(){
		log.debug("-----saveKeyChain()  method ----\n\n");
		KeyChain keyChain=new KeyChain();
		keyChain.setName("keychain");

		save(keyChain);
		
		KeyChain result=(KeyChain)getObject(KeyChain.class, keyChain.getId());
		Assert.assertEquals(keyChain.getName(), result.getName());
	}
	
	public void testSaveKeyChainWithOneToManyLatchKeyCollection(){
		log.debug("\n\n -----saveKeyChainWithOneToManyLatchKeyCollection()  method ----\n\n");
		KeyChain keyChain = instantiateKeyChainWithOneToManyColl();

		save(keyChain);

		KeyChain result = (KeyChain) getObjectAndLazyCollection(KeyChain.class, keyChain.getId(),"keyCollection");
		Assert.assertEquals(keyChain.getName(), result.getName());
		Assert.assertEquals(1, result.getKeyCollection().size());
	}
	
	public void testSaveKeyChainAndUpdateOneToManyLatchKeyCollection(){
		log.debug("\n\n -----saveKeyChainAndUpdateOneToManyLatchKeyCollection()  method ----\n\n");
		KeyChain keyChain =instantiateKeyChainWithOneToManyColl();
		
		save(keyChain);
		
		LatchKey latchKey2 = new LatchKey();
		latchKey2.setType("latchkey");
	
		KeyChain result = (KeyChain) getObjectAndLazyCollection(KeyChain.class, keyChain.getId(),"keyCollection");	
		result.getKeyCollection().add(latchKey2);
			
		update(result);
		
		result = (KeyChain) getObjectAndLazyCollection(KeyChain.class,keyChain.getId(),"keyCollection");	
		Assert.assertEquals(2, result.getKeyCollection().size());
	}
	
	public void testUpdateKeyChainAndUpdateAndDeleteOneToManyLatchKeyCollection(){
		log.debug("\n\n -----updateKeyChainAndUpdateAndDeleteOneToManyLatchKeyCollection()  method ----\n\n");
		KeyChain keyChain= instantiateKeyChainWithOneToManyColl();
		
		save(keyChain);

		keyChain =(KeyChain)getObjectAndLazyCollection(KeyChain.class, keyChain.getId(),"keyCollection" );
		LatchKey deleteLatch1Object=keyChain.getKeyCollection().iterator().next();
		keyChain.getKeyCollection().remove(deleteLatch1Object);

		keyChain.setName("updatedKeyChainName");
		LatchKey latchKey2 = new LatchKey();
		latchKey2.setType("latchkey2");
		keyChain.getKeyCollection().add(latchKey2);
		
		update(keyChain);
		delete(deleteLatch1Object);
		
		KeyChain result = (KeyChain) getObjectAndLazyCollection(KeyChain.class, keyChain.getId(),"keyCollection");
		Assert.assertEquals(1, result.getKeyCollection().size());
		deleteLatch1Object = (LatchKey) getObject(LatchKey.class, deleteLatch1Object.getId());
		Assert.assertEquals("updatedKeyChainName", result.getName());
		Assert.assertEquals(1, result.getKeyCollection().size());
		Assert.assertNull("latchkey object not deleted from database",deleteLatch1Object);
	}

	public void testDeleteKeyChainAndCascadeSaveUpdateForOneToManyLatchKeyCollection(){
		log.debug("\n\n -----deleteKeyChainAndCascadeSaveUpdateForOneToManyLatchKeyCollection()  method ----\n\n");		
		KeyChain keyChain = instantiateKeyChainWithOneToManyColl();
		
		save(keyChain);
		
		KeyChain result = (KeyChain) getObjectAndLazyCollection(KeyChain.class, keyChain.getId(),"keyCollection");
		LatchKey LatchKey1Object=result.getKeyCollection().iterator().next();

		delete(result);
		LatchKey childLatchKey = (LatchKey) getObject(LatchKey.class, LatchKey1Object.getId());
		Assert.assertNotNull("latchkey object2 should not be deleted from database",childLatchKey);
	}
	
	public void testDeleteChildLatchKeyParentNotDeleted(){
		log.debug("\n\n -----deleteLatchKey()  method ----\n\n");
		KeyChain keyChain=instantiateKeyChainWithOneToManyColl();
		
		save(keyChain);
		
		LatchKey LatchKey1Object=keyChain.getKeyCollection().iterator().next();
		delete(LatchKey1Object);
		
		KeyChain resultKeyChain = (KeyChain) getObject(KeyChain.class, keyChain.getId());
		LatchKey resultLatchKey = (LatchKey) getObject(LatchKey.class, LatchKey1Object.getId());
		Assert.assertNotNull("parent keychain  should not be deleted from database",resultKeyChain);
		Assert.assertNull("child latchkey should  be deleted from database",resultLatchKey);
	}
	
	private KeyChain instantiateKeyChainWithOneToManyColl() {
		KeyChain keyChain = new KeyChain();
		keyChain.setName("keychain");
		Collection<LatchKey> latchKeys = new HashSet<LatchKey>();
		LatchKey latchKey1 = new LatchKey();
		latchKey1.setType("latchkey1");
		latchKeys.add(latchKey1);
		keyChain.setKeyCollection(latchKeys);
		return keyChain;
	}
}
