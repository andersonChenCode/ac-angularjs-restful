package com.sap.model.service;

import java.util.Collection;
import java.util.List;

import com.sap.model.bo.User;


 

/**
 * User Service 接口
 * @author waylau.com
 * 2014-7-25
 */
public interface UserService {
	
	//=base=============================================================================
	public List getObjectResultList(final String HQL, final int startAt, final int size);
	public List getObjectResultList(final String HQL);
	public void saveObjectList(Collection collection);
	public void saveObject(Object obj); 
	public void saveOrUpdate(Object obj);
	public void saveOrUpdateList(Collection collection);
	public void updateObject(Object obj);
	public void deleteOrUpdateByHQL(final String hql);
	public int getObjectQty(final String HQL);
	public Object getObject(String BoName,String id) throws Exception;
	public void DeleteBo(String BoName,String id) throws Exception;
	public List queryForListBySQL(String sql) throws Exception;
}
