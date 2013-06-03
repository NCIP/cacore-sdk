/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/cacore-sdk/LICENSE.txt for details.
 */

package test.writable;

import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.WritableApplicationService;
import gov.nih.nci.system.client.ApplicationServiceProvider;
import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.SDKQueryResult;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class WritableApiTestServiceImpl implements WritableApiTestDAO {
	private WritableApplicationService appService ;
	
	public WritableApiTestServiceImpl(){
		try {
			appService = (WritableApplicationService)ApplicationServiceProvider.getApplicationService("/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=SDKUser1","Psat123!@#");
		} catch (Exception e) {
			throw new RuntimeException("error loading application service", e);
		}
	}
	
	protected ApplicationService getApplicationService() {
		return appService;
	}

	public static String getTestCaseName() {
		return "SDK Base Test Case";
	}
	
	@SuppressWarnings("unchecked")
	public void save(final Object obj) {
		final InsertExampleQuery sdkQuery = new InsertExampleQuery(obj);
		new BaseUtilWrapper() {

			@Override
			public List execute() throws Exception {
			  SDKQueryResult queryResult = appService.executeQuery(sdkQuery);
			 Object result=queryResult.getObjectResult();
			 BeanUtils.copyProperties(result, obj);			 
			 return null;
			}
		}.executeLogic();
	}
	
	public void update(Object obj) {
		final UpdateExampleQuery sdkQuery = new UpdateExampleQuery(obj);
		new BaseUtilWrapper() {

			@Override
			public List execute() throws Exception {
				appService.executeQuery(sdkQuery);
				return null;
			}
		}.executeLogic();
	}

	public void delete(Object obj) {
		final DeleteExampleQuery sdkQuery = new DeleteExampleQuery(obj);		
		new BaseUtilWrapper() {

			@Override
			public List execute() throws Exception {
				appService.executeQuery(sdkQuery);
				return null;
			}
		}.executeLogic();
	}
	
	public void executeBatchQuery(final List<SDKQuery> batchOperation) {

		new BaseUtilWrapper() {

			@Override
			public List execute() throws Exception {
				appService.executeBatchQuery(batchOperation);
				return null;
			}
		}.executeLogic();
	}
	
	@SuppressWarnings("unchecked")
	public Object getObject(final Class klass,final int id) {
		final List resultList = new ArrayList();
		final Object instance = getObjectInstance(klass, id);

		new BaseUtilWrapper() {

			@Override
			public List execute() throws Exception {
				final List list=appService.search(klass, instance);
				resultList.addAll(list);
				return resultList;
			}

		}.executeLogic();
		
		Object result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Object getObjectAndLazyCollection(final Class klass,final int id,final String lazyCollectionName) {
		final Object instance =getObject(klass,id);
		return instance;
	}

	@SuppressWarnings("unchecked")
	public Object getObjectAndLazyObject(Class klass,int id,String lazyObjName) {
		final Object instance =getObject(klass,id);
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public Object getObjectAndMultipleLazyObjects(Class klass,int id,String lazyObjName1,String lazyObjName2) {
		final Object instance =getObject(klass,id);
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	private abstract class BaseUtilWrapper {
		public abstract List execute() throws Exception;

		public void executeLogic() {
			try {
				execute();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private Object getObjectInstance(final Class klass, final int id) {
		final List list = new ArrayList();
		new BaseUtilWrapper() {

			@Override
			public List execute() throws Exception {
				Object instance = BeanUtils.instantiateClass(klass);
				Class[] parameterTypes = new Class[] { Integer.class };
				Method method =null;
				//NoIdKey dont have generic primary key as setId
				if (klass.getName().equals("gov.nih.nci.cacoresdk.domain.other.primarykey.NoIdKey")) {
					method = klass.getMethod("setMykey", parameterTypes);
				} else {
					method = klass.getMethod("setId", parameterTypes);
				}
				method.invoke(instance, id);

				list.add(instance);
				return list;
			}
		}.executeLogic();
		return list.get(0);
	}
	
	public void executeQuery(final SDKQuery sdkQuery) {
		new BaseUtilWrapper() {

			@Override
			public List execute() throws Exception {
				appService.executeQuery(sdkQuery);
				return null;
			}
		}.executeLogic();
	}
}
