package com.sap.model.service.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateOperations;

import com.sap.model.bo.User;
import com.sap.model.service.UserService;

/**
 * User Service 接口实现
 * @author waylau.com
 * 2014-7-25
 */
public class UserServiceImpl implements UserService {

	public HibernateOperations hibernateTemplate;
	
	public JdbcTemplate jdbcTemplate;
	
	public HibernateOperations getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	public void setHibernateTemplate(HibernateOperations hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	//=========================================================================================
	public List queryForListBySQL(String sql) {
		return null;//jdbcTemplate.queryForList(sql);
	}
	
	public int getObjectQty(final String HQL) {
		List as = null;//(List) hibernateTemplate.find(HQL);
		return as.size();
	}
	
	public List getObjectResultList(final String HQL, final int startAt, final int size) {
		hibernateTemplate.clear();
		hibernateTemplate.flush();
		return hibernateTemplate.executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(HQL);
				query.setFirstResult(startAt);
				query.setMaxResults(size);
				return query.list();
			}
		});
	}
	
	public List getObjectResultList(final String HQL) {
		return hibernateTemplate.find(HQL);
	}	
	public void saveOrUpdateList(Collection collection){
		for (Object bean : collection) { 
			hibernateTemplate.saveOrUpdate(bean); 
		}
	}
	public void saveObjectList(Collection collection) { 
		for (Object bean : collection) { 
			hibernateTemplate.save(bean);
		}
	}
	
	public void updateObject(Object obj) { 
			hibernateTemplate.update(obj);
	}
	
	public void saveObject(Object obj) { 
			hibernateTemplate.save(obj);
	}
	
	public void saveOrUpdate(Object obj) { 
		hibernateTemplate.saveOrUpdate(obj);
    }
	
	public void deleteOrUpdateByHQL(final String hql) {
		hibernateTemplate.bulkUpdate(hql);
	}
	
	public Object getObject(String BoName,String id) throws Exception{
		String sSql = " from "+BoName+" where id = "+ id ;
		List Bolist = hibernateTemplate.find(sSql);
		if (Bolist.size() == 0 )throw new Exception("此 ID > "+ id + " 於" + BoName + " 未存在資料!! ");
		return Bolist.get(0);
	}
	
	public void DeleteBo(String BoName,String id) throws Exception{
		String sSql1 = " from "+BoName+" where id = "+ id ;
		List Bolist = hibernateTemplate.find(sSql1);
		if (Bolist.size() == 0 )throw new Exception("此 ID > "+ id + " 於" + BoName + " 未存在資料!! ");
		String sSql2 = " delete from "+BoName+" where id = "+ id ;
		hibernateTemplate.bulkUpdate(sSql2);
	}
	
	public void deleteObjectByCascade(Class c, int id)throws Exception{
		Object obj = getHibernateTemplate().get(c,new Integer(id));
		getHibernateTemplate().delete(obj);
	}


}
