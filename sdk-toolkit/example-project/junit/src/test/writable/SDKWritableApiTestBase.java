package test.writable;

import gov.nih.nci.system.query.SDKQuery;

import java.util.List;


import junit.framework.Assert;
import junit.framework.TestCase;
public class SDKWritableApiTestBase extends TestCase{
	
	private WritableApiTestServiceDelegator serviceDelegator;
	
	protected void setUp() throws Exception {
		super.setUp();
		serviceDelegator=new WritableApiTestServiceDelegator();
	}	
	
	protected void tearDown() throws Exception {
		serviceDelegator = null;
		super.tearDown();
	}

	protected static String getTestCaseName() {
		return "SDK Base Test Case";
	}
	
	protected void save(Object obj) {
		serviceDelegator.save(obj);
	}

	protected void update(Object obj) {
		serviceDelegator.update(obj);
	}

	protected void delete(Object obj) {
		serviceDelegator.delete(obj);
	}
	
	@SuppressWarnings("unchecked")
	protected Object getObject(final Class klass,final int id) {
		return serviceDelegator.getObject(klass, id);
	}
	
	@SuppressWarnings("unchecked")
	protected Object getObjectAndLazyCollection(final Class klass,final int id,final String lazyCollectionName) {
		return serviceDelegator.getObjectAndLazyCollection(klass,id,lazyCollectionName);
	}
	
	@SuppressWarnings("unchecked")
	protected Object getObjectAndLazyObject(Class klass,int id,String lazyObjName) {
		return serviceDelegator.getObjectAndLazyObject(klass, id, lazyObjName);
	}
	
	@SuppressWarnings("unchecked")
	protected Object getObjectAndMultipleLazyObjects(Class klass,int id,String lazyObjName1,String lazyObjName2) {
		return serviceDelegator.getObjectAndMultipleLazyObjects(klass, id, lazyObjName1, lazyObjName2);
	}
	
	protected void executeBatchQuery(List<SDKQuery> batchOperation) {
		serviceDelegator.executeBatchQuery(batchOperation);
	}
	
	protected void executeQuery(SDKQuery sdkQuery) {
		serviceDelegator.executeQuery(sdkQuery);
	}
}
