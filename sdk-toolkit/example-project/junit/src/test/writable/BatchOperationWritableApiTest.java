package test.writable;

import java.util.ArrayList;

import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;

import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain;
import gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.LatchKey;
import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;
import gov.nih.nci.system.query.hql.DeleteHQLQuery;
import gov.nih.nci.system.query.hql.InsertHQLQuery;
import gov.nih.nci.system.query.hql.UpdateHQLQuery;

public class BatchOperationWritableApiTest extends SDKWritableBaseTest {
	private static Logger log = Logger.getLogger(BatchOperationWritableApiTest.class);

	public static String getTestCaseName() {
		return "BatchOperation WritableApi Test Case";
	}
	
	public void testBatchOperation(){
		log.debug("\n\n -----saveKeyChainWithOneToManyLatchKeyCollection()  method ----\n\n");
		KeyChain keyChain = new KeyChain();
		keyChain.setName("keychain");
		keyChain.setId(6);
		KeyChain keyChain2 = new KeyChain();
		keyChain2.setName("keychain");
		keyChain2.setId(6);
		LatchKey latchKey = new LatchKey();
		latchKey.setType("latchkey1");
		latchKey.setId(4);

		List<SDKQuery> batchOperations=new ArrayList<SDKQuery>();
		InsertExampleQuery insertKeyChain=new InsertExampleQuery(keyChain);
		
		String hql="insert into gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain kc";
		InsertHQLQuery insertHQLQuery=new InsertHQLQuery(hql);
		
		String updatehql="update gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain kc";
		UpdateHQLQuery updateHQLQuery=new UpdateHQLQuery(updatehql);
		
		String deletehql="delete from gov.nih.nci.cacoresdk.domain.onetomany.unidirectional.KeyChain kc";
		DeleteHQLQuery deleteHQLQuery=new DeleteHQLQuery(deletehql);
		
   		// batchOperations.add(insertKeyChain);
   		 //batchOperations.add(deleteHQLQuery);
		
		//executeBatchQuery(batchOperations);
		
		//save(insertKeyChain);
		
		KeyChain resultKeyChain=(KeyChain)getObject(KeyChain.class,1);
		resultKeyChain.setName("Keychain_Name1");
/*		LatchKey latchKey2=new LatchKey();
		latchKey2.setType("hello122");
		resultKeyChain.getKeyCollection().add(latchKey2);
		resultKeyChain.setName("Updated_Keychain_Name3");*/
		
/*		Chef chef=(Chef)getObject(Chef.class, 851968);
		chef.setName("hello");*/
		UpdateExampleQuery updateExampleQuery=new UpdateExampleQuery(resultKeyChain);
		
		
		batchOperations.add(updateExampleQuery);
		executeBatchQuery(batchOperations);
		
		Assert.assertEquals(resultKeyChain.getName(),"Keychain_Name1");
		//Assert.assertEquals(latchKey.getType(), resultLatchKey.getType());
	}
}
