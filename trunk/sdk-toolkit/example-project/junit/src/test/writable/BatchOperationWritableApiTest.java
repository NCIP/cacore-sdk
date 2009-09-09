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
		
		KeyChain keyChain2 = new KeyChain();
		keyChain2.setName("keychain");
		
		LatchKey latchKey = new LatchKey();
		latchKey.setType("latchkey1");
		latchKey.setId(4);

		List<SDKQuery> batchOperations=new ArrayList<SDKQuery>();
		InsertExampleQuery insertKeyChain=new InsertExampleQuery(keyChain);
		InsertExampleQuery insertKeyChain2=new InsertExampleQuery(keyChain2);
		
	
   		batchOperations.add(insertKeyChain);
   		batchOperations.add(insertKeyChain2);

   		executeBatchQuery(batchOperations);
		
	}
}
