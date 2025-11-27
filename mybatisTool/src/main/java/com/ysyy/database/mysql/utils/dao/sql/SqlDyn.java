package com.ysyy.database.mysql.utils.dao.sql;

import java.util.*;

import com.sun.prism.shader.Solid_Color_AlphaTest_Loader;
import com.ysyy.database.mysql.utils.dao.ISqlDyn;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import com.ysyy.common.ComFunc;
import com.ysyy.common.Regex;

import com.ysyy.database.mysql.utils.R;
import com.ysyy.database.mysql.utils.Reflex;
import org.apache.ibatis.session.Configuration;
import org.springframework.beans.factory.parsing.ReaderEventListener;
import sun.util.resources.cldr.vai.CalendarData_vai_Latn_LR;


/**
 * 动态生成sql，query根据class类型返回直
 * insert，update，create 和原来的mybaties相比，增加了动态：自动对应表，过滤字段等
 * 注意：在boolean类型自动生成sql语句，在数据库中会出错，使用方式如下：
 * 用 integer 代替 boolean
 *    1 = true
 *    0 = false
 * @author cwb
 *
 */
public class SqlDyn  {
	public SqlDyn() {
	}

	/*****
	 * 执行函数
	 * @param paramMap 参数
	 * @return
	 * @throws Exception
	 */
	public String execFUNC( HashMap<String,Object> paramMap ) throws Exception
	{
		Object objFuncName = paramMap.get("FUNCNAME");
		if(objFuncName==null) return "";

		String functionName=objFuncName.toString().toUpperCase();


		String a= "SELECT "+functionName+"(";
		if(paramMap!=null && paramMap.size()>0)
		{
			for(String key:paramMap.keySet())
			{
				// 跳过 特殊值
				if(key.toUpperCase().equals("FUNCNAME"))
					continue;

				Object obj = paramMap.get(key);


					a+="#{"+key+",jdbcType=VARCHAR,mode=IN}";
				a+=",";
			}
			if(a.lastIndexOf(",")>=0)
			a=a.substring(0,a.length()-1); //减掉最后的，


		}

		a+=")";

		System.out.println(a);
		return a;
	}


	/*****
	 * 执行存储过程
	 * @param paramMap 存储过程参数
	 * @return
	 * @throws Exception
	 */
	public String execPROC( HashMap<String,Object> paramMap )
	{
		Object objProcName = paramMap.get("PROCNAME");
		if(objProcName==null) return "";

		//String procName="SP_GET_NEXT_NO_P";
		String procName=objProcName.toString().toUpperCase();

	String a= "call "+procName+"(";
		if(paramMap!=null && paramMap.size()>0)
		{
			for(String key:paramMap.keySet())
			{
				// 跳过 特殊值
				if(key.toUpperCase().equals("PROCNAME"))
					continue;

					Object obj = paramMap.get(key);

					if(obj==null)
					{//  value==null 默认改参数是 返回值类型
						a+="#{"+key+",jdbcType=VARCHAR,mode=OUT}";
					}
					else
					{
						a+="#{"+key+",jdbcType=VARCHAR,mode=IN}";
					}

				a+=",";
			}

			if(a.lastIndexOf(",")>=0)
			a=a.substring(0,a.length()-1); //减掉最后的，

//			a+="#{RULE_TYPE_P,jdbcType=VARCHAR,mode=IN},";
//			a+="#{RULE_PREFIX_P,jdbcType=VARCHAR,mode=IN},";
//			a+="#{RETVAL,jdbcType=VARCHAR,mode=OUT}";
		}

		a+=")";

		System.out.println(a);
		return a;

	}



	/**
         * 通过ID获取一个数据
         * @param clazz  必输： 返回数据类型，动态取表（动态指定、@Table、class.getSimplename()）,动态过滤列
         * @param tableName  可选：表名-
         * @param keyColName  可选：id对应的列名;如果此不输入，则class 上必须定义@Id字段，否则，默认 直是“id”
         * @param cols 可选：默认全部字段，返回的字段
         * @param filterCols  可选：排除字段，需要过滤的字段
         * @return  返回 sql语句 string
         * @throws Exception
         */
	public String getById( Class<?> clazz, String tableName,final Object keyColValue,String keyColName,String[] cols,String[] filterCols)
			throws Exception {

		final String table;//表名
		final String[] columns;//列名
		final String keyColumnName;//keyid名

		if(tableName==null||"".equals(tableName))
			table = Reflex.getTable(clazz);
		else table = tableName;

		//if((filterCols==null||filterCols.length<=0)&&(cols==null||cols.length<=0))
		columns = Reflex.getCols(clazz, cols,filterCols);


		if(keyColName==null||"".equals(keyColName))
		{keyColumnName = Reflex.getKeyColName(clazz);}
		else
		{
			keyColumnName=Reflex.getRealFieldNameByClass(keyColName,clazz);
			//keyColumnName = keyColName;
		}

		if(keyColValue==null||"".equals(keyColValue))throw new Exception("keyid不能为空");
		String a= new SQL() {
			{
				//SELECT("*");
				if(columns==null||columns.length<=0) SELECT("*");	//不会发生
				else SELECT(columns);
				FROM(table);
				//FROM("user");
				WHERE(keyColumnName+"='" + keyColValue+"'");

			}
		}.toString();
		return a+" limit 1 ";
	}

	public String getListByCol(Class<?> clazz, String tableName,final Object colValue,String colName,String[] cols,String[] filterCols)throws Exception {
		final String table;//表名
		final String[] columns;//列名
		final String keyColumnName;//keyid名

		if(tableName==null||"".equals(tableName))
			table = Reflex.getTable(clazz);
		else table = tableName;

		//if((filterCols==null||filterCols.length<=0)&&(cols==null||cols.length<=0))
		columns = Reflex.getCols(clazz, cols,filterCols);


		if(colName==null||"".equals(colName)) throw new Exception("colName不能为空");
		else keyColumnName = colName;

		if(colValue==null||"".equals(colValue))throw new Exception("colValue不能为空");
		String a= new SQL() {
			{
				//SELECT("*");
				if(columns==null||columns.length<=0) SELECT("*");
				else SELECT(columns);
				FROM(table);
				//FROM("user");
				WHERE(keyColumnName+"='" + colValue+"'");
			}
		}.toString();
		return a;
	}


	public String getListCount(Class<?> clazz,String tableName,final String whereStr,String countCol) throws Exception {
		/*if(!Regex.queryStrCheck(whereStr))
			throw new Exception(whereStr+"：含有非法Sql，查询不能保护 insert delete等操作命令");*/
		checkString(whereStr);
		final String table;//表名
		final String columns;//列名
		final String keyColumnName;//keyid名

		table = tableName;

		//if((filterCols==null||filterCols.length<=0)&&(cols==null||cols.length<=0))
		if(ComFunc.empty(countCol)) throw new Exception("countCol is not allowed empty.");
		columns = countCol;
		String a = new SQL() {
			{
				//SELECT("*");

				SELECT("count("+columns+")");
				FROM(table);
				//FROM("user");
				//WHERE(whereStr);
			}
		}.toString();
       /* if(whereStr==null||"".equals(whereStr))
        	return a;
        else return a+ " where "+whereStr;*/
		return addWhereStr(a,whereStr);
	}
	/**
	 * 查询列表，自定义返回某类型的sql语句
	 * @param clazz  必输： 返回数据类型，动态取表（动态指定、@Table、class.getSimplename()）,动态过滤列
	 * @param tableName 可选：表名
	 * @param whereStr  from 后面的语句，由用户自定义，但：为了安全，不能包含update、delete、insert、create等关键字
	 * @param cols  可选：默认全部字段，返回的字段
	 * @param filterCols 可选：排除字段，需要过滤的字段
	 * @return 返回 sql语句 string
	 * @throws Exception
	 */
	public String getList( Class<?> clazz, String tableName,final String whereStr,String[] cols,String[] filterCols) throws Exception {
		/*if(!Regex.queryStrCheck(whereStr))
			throw new Exception(whereStr+"：含有非法Sql，查询不能保护 insert delete等操作命令");*/
		checkString(whereStr);
		final String table;//表名
		final String[] columns;//列名
		final String keyColumnName;//keyid名

		if(tableName==null||"".equals(tableName))
			table = Reflex.getTable(clazz);
		else table = tableName;

		//if((filterCols==null||filterCols.length<=0)&&(cols==null||cols.length<=0))
		columns = Reflex.getCols(clazz, cols,filterCols);
		String a = new SQL() {
			{
				//SELECT("*");
				if(columns==null||columns.length<=0) SELECT("*");
				else SELECT(columns);
				FROM(table);
				//FROM("user");
				//WHERE(whereStr);
			}
		}.toString();
        /*if(whereStr==null||"".equals(whereStr))
        	return a;
        else return a+ " where "+whereStr;*/
		return addWhereStr(a,whereStr);
	}

	public String addWhereStr(String strSql,String whereStr) {
		if(whereStr==null||"".equals(whereStr)) return strSql;
		else if(whereStr.trim().indexOf("limit")==0||
				whereStr.trim().indexOf("having")==0||
				whereStr.trim().indexOf("group")==0||
				whereStr.trim().indexOf("order")==0)
		{
			return strSql+" "+whereStr;
		}
		else return strSql+ " where "+whereStr;
	}
	/**
	 * 完全由用户定义的sql语句，返回clazz类型的 复杂类型的语句
	 * @param clazz 返回类型
	 * @param sqlStr sql语句，由用户自定义，但：为了安全，不能包含update、delete、insert、create等关键字
	 * @return  sql语句 string
	 * @throws Exception
	 */
	public String getListCustomer( Class<?> clazz, String sqlStr) throws Exception {
		/*if(!Regex.queryStrCheck(sqlStr))
			throw new Exception(sqlStr+"：含有非法Sql，查询不能保护 insert delete等操作命令"); */
		checkString(sqlStr);


		//mybatis3  mapUnderscoreToCamelCase 自动处理 命名转换 为了兼容 2.0版本
//		String	sqlNewStr=	processSlefSql(sqlStr,clazz); 	//  自定义sql 转换
//		return sqlNewStr;

		return sqlStr;
	}

	/**
	 *  针对 自定义的sql 特殊处理 把* 变成 clazz 中 有效的 列
	 * @param str
	 * @return
	 */
	public String processSlefSql(String str,Class<?> clazz) throws Exception {
		str=str.trim().toLowerCase();
		String orginalSQL =str;  	// 保留原SQL
		int fromIndex=str.indexOf("from");
		int selectIndex=str.indexOf("select");

		// 前置 条件 判断  如 rollback;select 1
		if(fromIndex<0||selectIndex<0)
		{
			return str;
		}

		//取出 select from 之间的 列
		str=str.substring(0,fromIndex);
		str=str.substring(selectIndex+6);


		List<String> cols=new ArrayList<>();
		List<String> realCols=new ArrayList<>();
		// 首次循环 组装列
		if(str.indexOf(",")>=0)
		{
			for (String one :str.split(","))
			{
				cols.add(one.trim()); // one 里面可能 还是包含 a.* 的
			}
		}
		else cols.add(str);  	// 无逗号 就1个列

		String [] empty=new String[0];
		// 2次循环 再次处理列
		for (String oneR:cols)
		{
			// 列里面 再次 有* 号 把* 替换为 clazz 实体类中的有效列
			// 特殊处理 count(*)
			if(oneR.indexOf("count(")>=0)
			{
				realCols.add(oneR);
			}
//			else if(oneR.indexOf("*")>=0||oneR.indexOf(".*")>=0)
//			{
//		    String [] oneShu=Reflex.getCols(clazz,empty,empty);
//			if(oneShu.length>0)
//				realCols.addAll(Arrays.asList(oneShu));
//			}
			else
			{// 普通列
				String oneReal=oneR;
				// 特殊处理 含有 distinct id,uid
				// 还会有很多 特殊处理 继续调试
				int distinctIndex=oneR.indexOf("distinct");
				if(distinctIndex>=0)
				{
					oneReal=oneR.substring(distinctIndex+8);
					oneReal=oneReal.trim();
				}

				// 还会 a.id 这种形式
				int dianIndex=oneReal.indexOf(".");
				String tableAlia="";
				if(dianIndex>=0)
				{
					tableAlia=oneReal.substring(0,dianIndex);
					oneReal=oneReal.substring(dianIndex+1);
					oneReal=oneReal.trim();
				}

			String colName=	Reflex.getSelectFieldNameByClass(oneReal,clazz);

				//添加别名
				if(dianIndex>=0)
				{
					colName=tableAlia+"."+colName;
				}

				if(distinctIndex>=0)
				{
					colName=" distinct "+colName;
				}

				 realCols.add(colName);
			}
		}

		// 重新组装SQL
	String selectCol= ComFunc.listToString(realCols, ",");


		String selectSQL="select "+selectCol+" "+orginalSQL.substring(fromIndex);

		return selectSQL;
	}
	/**
	 * 检查sqlStr是否合法
	 * @param sqlStr  String[] 需要检查的String
	 * @return
	 * @throws Exception
	 */
	private void checkString(String... sqlStr) throws Exception{
		for(String str : sqlStr){

			String [] temp={str};
			if(!Regex.queryStrCheck(temp)){
				// System.out.println(str);
				throw new Exception(str+"：含有非法Sql，查询不能保护 insert delete等操作命令");
			}
		}
	}
	private void isEmpty(Object... parmers) throws Exception{
		for(Object par : parmers)
			if(par==null)
				throw new Exception("参数不能为空");
		//if(par instanceof Array[])
	}
	/**
	 * 新增数据
	 * @param obj 数据
	 * @param tableName 表名
	 * @return
	 * @throws Exception
	 */
	public String addByObject(final Object obj,String tableName) throws Exception {
		final String table;//表名
		final List<R> rs = Reflex.getColAndValues(obj,null,null);
		if(tableName==null||"".equals(tableName))
			table = Reflex.getTable(obj.getClass());
		else table = tableName;
		//final String table = Reflex.getTable(obj.getClass());

		// 自动转SQL  插入空字符串   会导致 双引号问题
		String allsql= new SQL() {
			{
				INSERT_INTO(table);
				for(R r: rs)
					VALUES(r.col,r.value);

			}

		}.toString();

//		String allsql=" insert into "+table ;
//		String cols="";
//		String myvalue="";
//		for(R r: rs)
//		{
//			cols+=r.col+",";
//			myvalue+="'"+r.value+"',";
//		}
//
//		// 去掉 最后那个逗号
//		cols=cols.substring(0, cols.length()-1);
//		myvalue=myvalue.substring(0, myvalue.length()-1);
//
//		// 增加括号
//		cols="("+cols+")";
//		myvalue="("+myvalue+")";
//
//		allsql=allsql+cols+ " values"+myvalue;


		//System.out.println("生成插入SQL:"+allsql);
		return allsql;

	}

	/**
	 * 通过keycol修改;
	 * @param obj 数据;
	 * @param tableName 表名;
	 * @param keyColName keyid列名或字段;
	 * @param cols  修改列名
	 * @param filterCols  过滤列名;
	 * @return
	 * @throws Exception
	 */
	public String updateById( Object obj,String tableName,String keyColName,String[] cols,String[] filterCols) throws Exception{

		final String table ;

		final List<R> rs = Reflex.getColAndValues(obj,cols,filterCols);
		final String keyColumnName;//keyid名
		//final String id;

		if(tableName==null||"".equals(tableName))
			table = Reflex.getTable(obj.getClass());
		else table = tableName;
		if(keyColName==null||"".equals(keyColName))
			keyColumnName = Reflex.getKeyColName(obj.getClass());
		else
			keyColumnName = Reflex.getRealFieldNameByClass(keyColName,obj.getClass());
		//if(keyColName==null||keyid.isEmpty()) id = "id";
		//else id = keyid;
		String cc = new SQL() {
			{
				UPDATE(table);
				for(R r: rs){
					SET(r.col+"="+r.value);
					if(keyColumnName.equals(r.col))
						WHERE(keyColumnName+"="+r.value);
				}
			}
		}.toString();
		return cc;
	}

	/**
	 * update自定义语句，where 条件自定义;
	 * @param obj  必输：修改数据;
	 * @param tableName  可选：表名 为空 则根据实体类自动查找
	 * @param whereStr  必输：
	 * @param cols
	 * @param filterCols
	 * @return
	 * @throws Exception
	 */
	public String updateByWhereSql(Object obj,String tableName,String whereStr,String[] cols,String[] filterCols) throws Exception{
		checkString(whereStr);
		final String table ;

		final List<R> rs = Reflex.getColAndValues(obj,cols,filterCols);
		final String keyColumnName;//keyid名
		//final String id;

		if(tableName==null||"".equals(tableName))
			table = Reflex.getTable(obj.getClass());
		else
			table = tableName;

		String a = new SQL() {
			{
				UPDATE(table);
				for(R r: rs){
					SET(r.col+"="+r.value);
				}
			}
		}.toString();
		System.out.println("updateByWhereSql sql="+a+' '+whereStr);
       /* if(whereStr==null||"".equals(whereStr))
        	return a;
        else return a+ " where "+whereStr;*/
		return addWhereStr(a,whereStr);

	}
	/**
	 * update语句自定义,输入表名 及 语句;把标准sql语句拆分;可用于复杂的语句
	 * update tableName + setWhereStr => update table set x=s where a=1;
	 * @param tableName ：必输;表名
	 * @param setWhereStr：必输;
	 * @return
	 * @throws Exception
	 */
	public String updateBySql(String tableName,String setWhereStr ) throws Exception{
		isEmpty(tableName,setWhereStr);
		checkString(setWhereStr);
		final String table ;
		table = tableName;
		StringBuffer a = new StringBuffer("UPDATE " + table);
		a.append(" "+setWhereStr);
		return a.toString();
	}
	/**
	 * 删除记录通过id删除
	 * @param clazz 类型，获取表名 和tableNAME其中一个必须指定一个
	 * @param tableName  表名
	 * @param keyIdValue idCol 直
	 * @param keyColName  idCol列名
	 * @return  sql
	 * @throws Exception
	 */
	public String deleteById(Class<?> clazz,String tableName,final Object keyIdValue,String keyColName) throws Exception{

		final String table;//表名

		final String keyColumnName;//keyid名

		if(tableName==null||"".equals(tableName))
			table = Reflex.getTable(clazz);
		else table = tableName;

		if(keyColName==null||"".equals(keyColName))
			keyColumnName = Reflex.getKeyColName(clazz);
		else
//			keyColumnName = keyColName;
			keyColumnName= Reflex.getRealFieldNameByClass(keyColName,clazz);

		if(keyIdValue==null||"".equals(keyIdValue))throw new Exception("keyid不你能为空");
		return new SQL() {
			{
				DELETE_FROM(table);
				WHERE(keyColumnName+"='" + keyIdValue+"'");

			}
		}.toString();
	}

	/**
	 * 删除记录
	 * @param clazz 类型，获取表名 和tableNAME其中一个必须指定一个
	 * @param tableName  表名
	 * @param whereStr 必输：where条件
	 * @return string sql
	 * @throws Exception
	 */
	public String deleteByWhereSql(Class<?> clazz,String tableName,String whereStr) throws Exception{
		if(whereStr==null||"".equals(whereStr)) throw new Exception("whereStr 不能为空！");
		checkString(whereStr);
		final String table;//表名
		final String keyColumnName;//keyid名

		if(tableName==null||"".equals(tableName))
			table = Reflex.getTable(clazz);
		else table = tableName;

		String a = new SQL() {
			{
				DELETE_FROM(table);
			}
		}.toString();

		if(whereStr==null||"".equals(whereStr))
			return a;
		else return a+ " where "+whereStr;
	}

	public String insertByCustomerSql( String sqlStr) throws Exception {

		return sqlStr;
	}
	public String updateByCustomerSql( String sqlStr) throws Exception {

		return sqlStr;
	}

}
