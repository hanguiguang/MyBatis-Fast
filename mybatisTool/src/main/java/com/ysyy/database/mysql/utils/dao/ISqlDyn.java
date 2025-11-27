//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.database.mysql.utils.dao;

import com.ysyy.database.mysql.utils.annotation.DynTable;
import com.ysyy.database.mysql.utils.dao.sql.SqlDyn;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

@Mapper
@DynTable
public interface ISqlDyn {

	@SelectProvider(
			type = SqlDyn.class,
			method = "execFUNC"
	)
	@Options(statementType = StatementType.CALLABLE,useCache = false)
	Object execFUNC(HashMap<String,Object> var3);

	@SelectProvider(
			type = SqlDyn.class,
			method = "execPROC"
	)
	@Options(statementType = StatementType.CALLABLE,useCache = false)
	Object execPROC(HashMap<String,Object> var3);

	@SelectProvider(
			type = SqlDyn.class,
			method = "getById"
	)
	Object getById(Class<?> var1, String var2, Object var3, String var4, String[] var5, String[] var6);

	@SelectProvider(
			type = SqlDyn.class,
			method = "getListByCol"
	)
	List getListByCol(Class<?> var1, String var2, Object var3, String var4, String[] var5, String[] var6);

	@SelectProvider(
			type = SqlDyn.class,
			method = "getList"
	)
	List getList(Class<?> var1, String var2, String var3, String[] var4, String[] var5);

	@SelectProvider(
			type = SqlDyn.class,
			method = "getListCount"
	)
	Integer getListCount(Class<?> var1, String var2, String var3, String var4);

	@SelectProvider(
			type = SqlDyn.class,
			method = "getListCustomer"
	)
	List getListCustomer(Class<?> var1, String var2);

	@InsertProvider(
			type = SqlDyn.class,
			method = "addByObject"
	)
	void addByObject(Object var1, String var2);

	@UpdateProvider(
			type = SqlDyn.class,
			method = "updateById"
	)
	void updateById(Object var1, String var2, String var3, String[] var4, String[] var5);

	@UpdateProvider(
			type = SqlDyn.class,
			method = "updateByWhereSql"
	)
	void updateByWhereSql(Object var1, String var2, String var3, String[] var4, String[] var5);

	@UpdateProvider(
			type = SqlDyn.class,
			method = "updateBySql"
	)
	void updateBySql(String var1, String var2);

	@DeleteProvider(
			type = SqlDyn.class,
			method = "deleteById"
	)
	void deleteById(Class<?> var1, String var2, Object var3, String var4);

	@DeleteProvider(
			type = SqlDyn.class,
			method = "deleteByWhereSql"
	)
	void deleteByWhereSql(Class<?> var1, String var2, String var3);

	@InsertProvider(
			type = SqlDyn.class,
			method = "insertByCustomerSql"
	)
	Integer insertByCustomerSql(String var1);

	@UpdateProvider(
			type = SqlDyn.class,
			method = "updateByCustomerSql"
	)
	Integer updateByCustomerSql(String var1);
}
