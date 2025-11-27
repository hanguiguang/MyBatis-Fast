//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.dao;

import com.SqlStrProcess;
import com.ysyy.common.ComFunc;
import com.ysyy.common.resut.BasePageN;
import com.ysyy.common.resut.PagePara;
import com.ysyy.common.resut.PageResult;
import com.ysyy.database.mysql.utils.Reflex;
import com.ysyy.database.mysql.utils.dao.ISqlDyn;
import com.ysyy.database.mysql.utils.dao.sql.SqlDyn;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CrudImpl implements Crud {
	@Autowired
	private ISqlDyn iSqlDyn;

	public CrudImpl() {
	}

	public void setiSqlDyn(ISqlDyn iSqlDyn) {
		this.iSqlDyn = iSqlDyn;
	}

	public  Object execFUNC(String functionName,HashMap<String,Object> paramMap  )
	{
		if(paramMap==null) paramMap=new HashMap<String,Object>();   // 无参函数 不用在乎顺序
		paramMap.put("FUNCNAME",functionName);	// 函数名称
		return  this.iSqlDyn.execFUNC(paramMap);
	}

	public  Object execPROC(String procName,HashMap<String,Object> paramMap  )
	{
		if(paramMap==null) paramMap=new HashMap<String,Object>(); // 无参函数 不用在乎顺序
		paramMap.put("PROCNAME",procName);	// 存储过程名称
		return  this.iSqlDyn.execPROC(paramMap);
	}


	public Object getById(Class<?> clazz, String tableName, Object keyColValue, String keyColName, String[] cols, String[] filterCols) {
		return this.iSqlDyn.getById(clazz, tableName, keyColValue, keyColName, cols, filterCols);
	}

	public Object getById(Class<?> clazz, String tableName, Object keyColValue, String keyColName) {
		return this.getById(clazz, tableName, keyColValue, keyColName, (String[])null, (String[])null);
	}

	public Object getById(Class<?> clazz, Object keyColValue, String[] cols) {
		return this.getById(clazz, (String)null, keyColValue, (String)null, cols, (String[])null);
	}

	public Object getById(Class<?> clazz, Object keyColValue) {
		return this.getById(clazz, (String)null, keyColValue, (String)null, (String[])null, (String[])null);
	}

	@SuppressWarnings("unchecked")
	public <T> T getByIdToMapper(Class<? extends T> clazz ,Object keyColValue)  {
		Object ret= this.getById(clazz, (String)null, keyColValue, (String)null, (String[])null, (String[])null);


		if(ret!=null)
		{ T obj=(T)ret; return obj;}

		return null;
	}

	public Object getById(Class<?> clazz, Object keyColValue, String keyColName) {
		return this.getById(clazz, (String)null, keyColValue, keyColName, (String[])null, (String[])null);
	}

	@SuppressWarnings("unchecked")
	public <T> T getByIdToMapper(Class<? extends T> clazz, Object keyColValue, String keyColName) {
		Object ret=this.getById(clazz, (String)null, keyColValue, keyColName, (String[])null, (String[])null);

		if(ret!=null)
		{ T obj=(T)ret; return obj;}

		return null;
	}


	public Object getById(Class<?> clazz, Object keyColValue, String keyColName, String[] cols) {
		return this.getById(clazz, (String)null, keyColValue, keyColName, (String[])null, (String[])null);
	}

	public Object getById(Class<?> clazz, String tableName, Object keyColValue) {
		return this.getById(clazz, tableName, keyColValue, (String)null, (String[])null, (String[])null);
	}

	public Object getById(Class<?> clazz, String tableName, Object keyColValue, String[] cols) {
		return this.getById(clazz, tableName, keyColValue, (String)null, (String[])null, (String[])null);
	}

	public List getByCol(Class<?> clazz, String tableName, Object keyColValue, String keyColName, String[] cols, String[] filterCols) throws Exception {
		return this.iSqlDyn.getListByCol(clazz, tableName, keyColValue, keyColName, cols, filterCols);
	}

	public List getByCol(Class<?> clazz, String tableName, Object colValue, String colName) throws Exception {
		return this.getByCol(clazz, tableName, colValue, colName, (String[])null, (String[])null);
	}

	public List getByCol(Class<?> clazz, String tableName, Object colValue, String colName, String[] cols) throws Exception {
		return this.getByCol(clazz, tableName, colValue, colName, cols, (String[])null);
	}

	public List getByCol(Class<?> clazz, Object colValue, String colName, String[] cols) throws Exception {
		return this.getByCol(clazz, (String)null, colValue, colName, cols, (String[])null);
	}

	public List getByCol(Class<?> clazz, Object colValue, String colName) throws Exception {
		return this.getByCol(clazz, (String)null, colValue, colName, (String[])null, (String[])null);
	}

	public List getList(Class<?> clazz, String tableName, String whereStr, String[] cols, String[] filterCols) {
		return this.iSqlDyn.getList(clazz, tableName, whereStr, cols, filterCols);
	}

	public List getList(Class<?> clazz, String whereStr) {
		return this.getList(clazz, (String)null, whereStr, (String[])null, (String[])null);
	}

	public List getList(Class<?> clazz, String tableName, String whereStr) {
		return this.getList(clazz, tableName, whereStr, (String[])null, (String[])null);
	}

	public List getList(Class<?> clazz, String tableName, String whereStr, String[] cols) {
		return this.getList(clazz, tableName, whereStr, cols, (String[])null);
	}

	public List getList(Class<?> clazz, String whereStr, String[] cols) {
		return this.getList(clazz, (String)null, whereStr, cols, (String[])null);
	}

	/**
	 * 自定义查询 基础方法
	 * @param clazz  类
	 * @param sqlStr 拼接后完整的sql语句
	 * @return
	 */
	public List getListCustomer(Class<?> clazz, String sqlStr) {
		return this.iSqlDyn.getListCustomer(clazz, sqlStr);
	}

	public Integer getListCount(Class<?> clazz, String tableName, String countCol, String whereStr) throws Exception {
		if (ComFunc.empty(tableName)) {
			tableName = Reflex.getTable(clazz);
		}

		if (ComFunc.empty(tableName)) {
			throw new Exception("clazz or tableName is not allowed both empty");
		} else {
			return this.iSqlDyn.getListCount(Integer.class, tableName, whereStr, countCol);
		}
	}
	//******* 实体查询 总数
	public Integer getListCount(Class<?> clazz, String countCol, String whereStr) throws Exception {
		return this.getListCount(clazz, (String)null, countCol, whereStr);
	}
	//******* 自定义查询 总数
	public Integer getListCountCustomer(String sqlStr, String... whereStrs) throws Exception {
		return (Integer)this.getListCustomer(Integer.class, SqlStrProcess.sqlStrProcessByWhere(sqlStr, whereStrs)).get(0);
	}

	//******* 自定义查询 分页的
	public List getListCustomerByPage(Class<?> clazz, String sqlStr, Integer offset, Integer size, String... whereStrs) throws Exception {
		return this.getListCustomer(clazz, SqlStrProcess.sqlStrProcessByWhere(sqlStr, offset, size, whereStrs));
	}

	public List getListCustomerByPage(Class<?> clazz, String sqlStr, String... whereStrs) throws Exception {
		return this.getListCustomerByPage(clazz, sqlStr, (Integer)null, (Integer)null, whereStrs);
	}

	public List getListCustomerByPageN(Class<?> clazz, String sqlStr, Integer pageNum, Integer pageSize, String... whereStrs) throws Exception {
		if(pageNum<1) pageNum=1;
		if(pageSize<1) pageSize=10;
		Integer size=pageSize;
		Integer offset=(pageNum-1)*size;
		return this.getListCustomer(clazz, SqlStrProcess.sqlStrProcessByWhere(sqlStr, offset, size, whereStrs));
	}

	public PageResult getListCustomerByPageWithTotal(Class<?> clazz, String sqlStr, Integer pageNum, Integer pageSize, String... whereStrs) throws Exception {
		Boolean needTotal=true;
		return getListCustomerByPageWithTotal(clazz,sqlStr,pageNum,pageSize,needTotal,whereStrs);
	}
	public PageResult getListCustomerByPageWithTotal(Class<?> clazz, String sqlStr, Integer pageNum, Integer pageSize, Boolean needTotal, String... whereStrs) throws Exception {
		PageResult pageResult=new PageResult();

		if(pageNum<1) pageNum=1;
		if(pageSize<1) pageSize=10;
		Integer size=pageSize;
		Integer offset=(pageNum-1)*size;

		if(needTotal==null) needTotal=false;

		// 查询这个 sql total 数据
		if(needTotal) {
			// 把sql 的select 改为 count(*)
//			String sqlCount= SqlStrProcess.sqlStrProcessByWhere(sqlStr, null, null, whereStrs);
			String sqlCount=sqlStr;
			int indexFrom=	sqlCount.toLowerCase().lastIndexOf("from");
			sqlCount="select count(*) "+sqlCount.toLowerCase().substring(indexFrom);
			System.out.println("sqlCount="+sqlCount);

//			Integer total=	(Integer)this.getListCountCustomer(sqlCount);

			Integer total=(Integer)this.getListCustomer(Integer.class, SqlStrProcess.sqlStrProcessByWhere(sqlCount, whereStrs)).get(0);
			pageResult.setTotal(total);
		}

		System.out.println("sqlStr="+sqlStr);
		// 分页查询 数据
		List obj= this.getListCustomer(clazz, SqlStrProcess.sqlStrProcessByWhere(sqlStr, offset, size, whereStrs));
		pageResult.setPageData(obj);

		return pageResult;
	}

	public  PageResult getListCustomerByPageWithTotal(PagePara pagePara) throws Exception
	{
		PageResult pageResult=new PageResult();

		// 拼接 2个SQL
		String sqlCount=" select count(*)"
				+" from " +pagePara.getSqlFrom();

		String sqlStr=" select "+pagePara.getSqlSelect()
				+" from " +pagePara.getSqlFrom();

		if(pagePara.getSqlWhere()!=null&&pagePara.getSqlWhere()!="")
		{
			sqlCount+=" where "+pagePara.getSqlWhere();
			sqlStr+=" where "+pagePara.getSqlWhere();
		}
		if(pagePara.getSqlGroup()!=null&&pagePara.getSqlGroup()!="")
		{
			sqlCount+=" where "+pagePara.getSqlGroup();
			sqlStr+=" where "+pagePara.getSqlGroup();
		}

		System.out.println("sqlCount="+sqlCount);
		System.out.println("sqlStr="+sqlStr);

		if(pagePara.getNeedTotal()) {
			Integer total = (Integer) this.getListCustomer(Integer.class, SqlStrProcess.sqlStrProcessByWhere(sqlCount, null)).get(0);
			pageResult.setTotal(total);
		}

		// 分页查询 数据
		Integer size=pagePara.getPageSize();
		Integer offset=(pagePara.getPageNum()-1)*size;

		List obj= this.getListCustomer(pagePara.getClazz(), SqlStrProcess.sqlStrProcessByWhere(sqlStr, offset, size, null));
		pageResult.setPageData(obj);


		return pageResult;
	}
	//*******自定义查询 分页的

	public List getListByPage(Class<?> clazz, String tableName, Integer offset, Integer size, String whereStr, String[] cols, String[] filterCols) throws Exception {
		return this.getList(clazz, tableName, SqlStrProcess.processByWhere(offset, size, new String[]{whereStr}), cols, filterCols);
	}

	public List getListByPage(Class<?> clazz, Integer offset, Integer size, String whereStr, String[] cols) throws Exception {
		return this.getListByPage(clazz, (String)null, offset, size, whereStr, cols, (String[])null);
	}

	public List getListByPage(Class<?> clazz, Integer offset, Integer size, String whereStr) throws Exception {
		return this.getListByPage(clazz, (String)null, offset, size, whereStr, (String[])null, (String[])null);
	}

	// BasePageN 改装
	public List getListByPage(Class<?> clazz, BasePageN pagedObj, String whereStr) throws Exception {
		return this.getListByPage(clazz, (String)null, pagedObj.getOffSet() , pagedObj.getSize(), whereStr, (String[])null, (String[])null);
	}


	public List getListByPage(Class<?> clazz, String tableName, Integer offset, Integer size, String whereStr, String[] cols) throws Exception {
		return this.getListByPage(clazz, tableName, offset, size, whereStr, (String[])null, (String[])null);
	}

	public void addByObject(Object obj, String tableName) {
		this.iSqlDyn.addByObject(obj, tableName);
	}

	public void addByObject(Object obj) {
		this.addByObject(obj, (String)null);
	}

	public void updateById(Object obj, String tableName, String keyColName, String[] cols, String[] filterCols) {
		this.iSqlDyn.updateById(obj, tableName, keyColName, cols, filterCols);
	}

	public void updateById(Object obj, String[] cols, String[] filterCols) {
		this.updateById(obj, (String)null, (String)null, cols, filterCols);
	}

	public void updateById(Object obj, String[] cols) {
		this.updateById(obj, (String)null, (String)null, cols, (String[])null);
	}

	public void updateById(Object obj) {
		this.updateById(obj, (String)null, (String)null, (String[])null, (String[])null);
	}

	public void updateById(Object obj, String tableName, String keyColName, String[] cols) {
		this.updateById(obj, tableName, keyColName, cols, (String[])null);
	}

	public void updateByWhereSql(Object obj, String tableName, String whereStr, String[] cols, String[] filterCols) {
		this.iSqlDyn.updateByWhereSql(obj, tableName, whereStr, cols, filterCols);
	}

	public void updateByWhereSql(Object obj, String whereStr, String[] cols) {
		this.iSqlDyn.updateByWhereSql(obj, (String)null, whereStr, cols, (String[])null);
	}

	public void updateBySql(String tableName, String setWhereStr) {
		this.iSqlDyn.updateBySql(tableName, setWhereStr);
	}

	public void deleteById(Class<?> clazz, String tableName, Object keyIdValue, String keyColName) {
		this.iSqlDyn.deleteById(clazz, tableName, keyIdValue, keyColName);
	}

	public void deleteById(Class<?> clazz, Object keyIdValue) {
		this.iSqlDyn.deleteById(clazz, (String)null, keyIdValue, (String)null);
	}

	public void deleteByWhereSql(Class<?> clazz, String tableName, String whereStr) {
		this.iSqlDyn.deleteByWhereSql(clazz, tableName, whereStr);
	}

	public void deleteByWhereSql(Class<?> clazz, String whereStr) {
		this.iSqlDyn.deleteByWhereSql(clazz, (String)null, whereStr);
	}

	public void deleteByWhereSql(String tableName, String whereStr) {
		this.iSqlDyn.deleteByWhereSql((Class)null, tableName, whereStr);
	}

	public Integer insertByCustomerSql(String whereStr) {
		return this.iSqlDyn.insertByCustomerSql(whereStr);
	}

	public Integer updateByCustomerSql(String whereStr) {
		return this.iSqlDyn.updateByCustomerSql(whereStr);
	}

}
