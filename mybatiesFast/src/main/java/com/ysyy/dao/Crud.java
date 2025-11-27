//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.dao;

import com.ysyy.common.resut.PagePara;
import com.ysyy.common.resut.PageResult;

import java.util.HashMap;
import java.util.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public interface Crud {

	Object execFUNC(String var2,HashMap<String,Object> var3) throws Exception;
	Object execPROC(String var2,HashMap<String,Object> var3) throws Exception;

	Object getById(Class<?> var1, String var2, Object var3, String var4, String[] var5, String[] var6) ;

	Object getById(Class<?> var1, String var2, Object var3, String var4) ;

	Object getById(Class<?> var1, Object var2, String var3) ;

	<T> T getByIdToMapper(Class<? extends T> var1, Object var2, String var3) ;

	Object getById(Class<?> var1, Object var2) ;

	<T> T getByIdToMapper(Class<? extends T> var1 ,Object var2) ;

	Object getById(Class<?> var1, Object var2, String[] var3) ;

	Object getById(Class<?> var1, Object var2, String var3, String[] var4);

	Object getById(Class<?> var1, String var2, Object var3, String[] var4) ;

	Object getById(Class<?> var1, String var2, Object var3) ;

	List getByCol(Class<?> var1, String var2, Object var3, String var4, String[] var5, String[] var6) throws Exception;

	List getByCol(Class<?> var1, Object var2, String var3, String[] var4) throws Exception;

	List getByCol(Class<?> var1, Object var2, String var3) throws Exception;

	List getByCol(Class<?> var1, String var2, Object var3, String var4, String[] var5) throws Exception;

	List getByCol(Class<?> var1, String var2, Object var3, String var4) throws Exception;

	List getList(Class<?> var1, String var2, String var3, String[] var4, String[] var5);

	List getList(Class<?> var1, String var2, String var3);

	List getList(Class<?> var1, String var2, String var3, String[] var4);

	List getList(Class<?> var1, String var2, String[] var3);

	List getList(Class<?> var1, String var2);

	List getListCustomer(Class<?> var1, String var2);

	Integer getListCount(Class<?> var1, String var2, String var3, String var4) throws Exception;

	Integer getListCount(Class<?> var1, String var2, String var3) throws Exception;

	Integer getListCountCustomer(String var1, String... var2) throws Exception;

	List getListCustomerByPage(Class<?> var1, String var2, Integer offset, Integer size, String... whereStrs) throws Exception;

	List getListCustomerByPageN(Class<?> clazz, String sqlStr, Integer pageNum, Integer pageSize, String... whereStrs) throws Exception;
	PageResult getListCustomerByPageWithTotal(Class<?> clazz, String sqlStr, Integer pageNum, Integer pageSize, String... whereStrs) throws Exception;

	PageResult getListCustomerByPageWithTotal(Class<?> clazz, String sqlStr, Integer pageNum, Integer pageSize,Boolean needTotal, String... whereStrs) throws Exception;


	PageResult getListCustomerByPageWithTotal(PagePara pagePara) throws Exception
	;


	List getListCustomerByPage(Class<?> var1, String var2, String... whereStrs) throws Exception;

	List getListByPage(Class<?> var1, String var2, Integer var3, Integer var4, String var5, String[] var6, String[] var7) throws Exception;

	List getListByPage(Class<?> var1, Integer var2, Integer var3, String var4, String[] var5) throws Exception;

	List getListByPage(Class<?> var1, Integer var2, Integer var3, String var4) throws Exception;

	List getListByPage(Class<?> var1, String var2, Integer var3, Integer var4, String var5, String[] var6) throws Exception;

	void addByObject(Object var1, String var2);

	void addByObject(Object var1);

	void updateById(Object var1, String var2, String var3, String[] var4, String[] var5);

	void updateById(Object var1, String[] var2, String[] var3);

	void updateById(Object var1, String[] var2);

	void updateById(Object var1);

	void updateById(Object var1, String var2, String var3, String[] var4);

	void updateByWhereSql(Object var1, String var2, String var3, String[] var4, String[] var5);

	void updateByWhereSql(Object var1, String var2, String[] var3);

	void updateBySql(String var1, String var2);

	void deleteById(Class<?> var1, String var2, Object var3, String var4);

	void deleteById(Class<?> var1, Object var2);

	void deleteByWhereSql(Class<?> var1, String var2, String var3);

	void deleteByWhereSql(Class<?> var1, String var2);

	void deleteByWhereSql(String var1, String var2);

	Integer insertByCustomerSql(String var1);

	Integer updateByCustomerSql(String var1);
}
