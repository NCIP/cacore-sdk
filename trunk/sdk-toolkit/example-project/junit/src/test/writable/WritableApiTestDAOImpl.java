package test.writable;

import gov.nih.nci.system.query.SDKQuery;
import gov.nih.nci.system.query.example.DeleteExampleQuery;
import gov.nih.nci.system.query.example.InsertExampleQuery;
import gov.nih.nci.system.query.example.UpdateExampleQuery;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class WritableApiTestDAOImpl extends HibernateDaoSupport implements WritableApiTestDAO{
	
	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}

	public void update(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}
	
	public void executeBatchQuery(List<SDKQuery> batchOperation) {
		
		for (SDKQuery query : batchOperation) {
			if (query instanceof InsertExampleQuery) {
				InsertExampleQuery insert = (InsertExampleQuery) query;
				getHibernateTemplate().save(insert.getExample());
			}else if (query instanceof DeleteExampleQuery) {
				DeleteExampleQuery delete = (DeleteExampleQuery) query;
				getHibernateTemplate().delete(delete.getExample());
			}else if (query instanceof UpdateExampleQuery) {
				UpdateExampleQuery update = (UpdateExampleQuery) query;
				getHibernateTemplate().update(update.getExample());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Object getObject(Class klass,int id) {
		Object obj=getHibernateTemplate().get(klass,id);
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public Object getObjectAndLazyCollection(Class klass, int id,String lazyCollectionsName) {
		String className=klass.getName();
		
		List list=getHibernateTemplate().find("from "+className+" as klass left join fetch klass."+lazyCollectionsName+" where klass.id="+id);
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Object getObjectAndLazyObject(Class klass,int id,String lazyObjName) {
		String className=klass.getName();
		
		List list=getHibernateTemplate().find("from "+className+" as klass left join fetch klass."+lazyObjName+" where klass.id="+id);
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}
	
	public Object getObjectAndMultipleLazyObjects(Class klass, int id,
			String lazyObjName1, String lazyObjName2) {
		String className=klass.getName();
		
		List list=getHibernateTemplate().find("from "+className+" as klass left join fetch klass."+lazyObjName1+" left join fetch klass."+lazyObjName2+" where klass.id="+id);
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}

	public void executeQuery(SDKQuery query) {
		if (query instanceof InsertExampleQuery) {
			InsertExampleQuery insert = (InsertExampleQuery) query;
			getHibernateTemplate().save(insert.getExample());
		} else if (query instanceof DeleteExampleQuery) {
			DeleteExampleQuery delete = (DeleteExampleQuery) query;
			getHibernateTemplate().delete(delete.getExample());
		} else if (query instanceof UpdateExampleQuery) {
			UpdateExampleQuery update = (UpdateExampleQuery) query;
			getHibernateTemplate().update(update.getExample());
		}
	}
}
