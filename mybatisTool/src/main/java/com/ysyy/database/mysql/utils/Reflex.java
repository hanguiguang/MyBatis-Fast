package com.ysyy.database.mysql.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import com.ysyy.common.ComFunc;
import com.ysyy.common.annotation.objecttable.*;
import org.apache.ibatis.jdbc.Null;


/**
 * 通过映射获取class实例信息，及类声明、方法声明、字段声明等信息
 * @author cwb
 *
 */

public class Reflex {


	/**
	 * 过滤列  获取组装sql 的列名  数组
	 * @param clazz 类型
	 * @param cols  指定列
	 * @param filterCols 需要排除的列
	 * @return 需要返回的列
	 * @throws Exception
	 */
	public static String[] getCols(Class clazz,String[] cols,String[] filterCols) throws Exception{

		List<String> rs = new ArrayList<String>();
		List<String> columns = new ArrayList<String>();
		Boolean b = false;
		//遍历获取所有字段属性 韩桂光 包含父类
		for(Field f : getSelfAndSuperClassFields(clazz)
		) {

			if(filterCols(f))continue;
			f.setAccessible(true);

			if(!(cols==null||cols.length==0))
				for(String col : cols){
					//System.out.println(f.getName());
					//System.out.println(col);
					// 指定的 列名 可能是 真实列名 也可能是 数据库列名
					if(col.toLowerCase().equals(f.getName().toLowerCase())
							|| col.toLowerCase().equals(getRealFieldTableName(f))
					)
					{
//						columns.add(f.getName());
						columns.add(getSelectRealFieldName(f)); 	// 返回拼接的 a_id id 这种格式的 查询列
						break;
					}
				}
			else //columns.add(f.getName());
				columns.add(getSelectRealFieldName(f));
		}

		if(!(filterCols==null||filterCols.length==0))
			for(String column : columns){
				b = false;
				for(String col : filterCols){
					//if(col.toLowerCase().equals(column.toLowerCase())){
					if(column.toLowerCase().indexOf(col.toLowerCase())>=0){
						b = true;
						break;
					}
				}
				if(b) continue;
				rs.add(column);
			}
		else
			return columns.toArray(new String[columns.size()]);
		if(rs.size()==0) return null;
		/*String[] a = new String[rs.size()];
		rs.toArray(a);*/
		return rs.toArray(new String[rs.size()]);
	}

	/**
	 * 获取字段信息和字段对应的直，直为空或者null除外。
	 * @param obj 传入的实例
	 * @param cols  指定列
	 * @param filterCols 需要排除的列
	 * @return  R2（col：value）形式
	 * @throws Exception
	 * 如果给列 增加真实 column 注解 这个接口 R.col 上必须返回真实的 数据库表 列名
	 */
	public static List<R> getColAndValues(Object obj,String[] cols,String[] filterCols) throws Exception{

		List<String> colNameList= new ArrayList<String>(); // 列名集合 用于比对
		List<R> rs = new ArrayList<R>();
		String[] columns = null;		// 指定列 筛查后结果
			if(cols!=null&&cols.length>0)
			{
				columns = getCols(obj.getClass(),cols,filterCols);		// 对指定列 筛查
			}


					// 韩桂光 2019-08-20  拼接父类的 所有属性
					//for(Field f : obj.getClass().getDeclaredFields()){
					for(Field f : getSelfAndSuperClassFields(obj.getClass()))
					{

			/*if(f.getDeclaredAnnotation(OneToOne.class)!=null)continue;
			if(f.getDeclaredAnnotation(OneToMany.class)!=null)continue;
			if(f.getDeclaredAnnotation(ManyToOne.class)!=null)continue;
			if("serialVersionUID".equals(f.getName()))continue;*/
			//检查过滤
			if(filterCols(f))continue;
			f.setAccessible(true);
			if(f.get(obj)==null) continue;	// 值等于null  也剔除掉

			R r = null ;
			if(columns==null)
			{
				// 双引号变单引号 "\""---> "'"
				//r = new R(f.getName(),"\""+f.get(obj).toString()+"\"");
				r = new R(getReplaceFieldName(f), "'" +f.get(obj).toString()+"'");
			}
			else{
				// 指定了特定列 更新 col 含有 dictionary_Sid as dictionarySid
					for(String col : columns)
					{
						//if(f.getName().equals(col))
						//r = new R(f.getName(),"\""+f.get(obj).toString()+"\"");
						int indexAs=col.indexOf(" as ");
						String fisrtCol=col;
						String endCol="";
						if(indexAs>=0){
							fisrtCol=col.substring(0,indexAs);
							endCol=col.substring(indexAs+4);
						}

						if ((fisrtCol.indexOf(f.getName()) >= 0 || fisrtCol.indexOf(getReplaceFieldName(f)) >= 0)
						   &&(fisrtCol.length()==f.getName().length()||fisrtCol.length()==getReplaceFieldName(f).length()))
						{
							System.out.println("指定列筛选="+fisrtCol+"对比列="+f.getName()+";"+getReplaceFieldName(f));
							r = new R(getReplaceFieldName(f), "'" + f.get(obj).toString() + "'");
						}
					}
			}

			if(r!=null){
				// 20230907 韩桂光 如果 父子继承类  对于重复的列 忽略后面的

				if(colNameList.contains(r.col.toLowerCase()))
				{
					System.out.println("该列前面已经存在："+r.col);
					continue;
				}

				rs.add(r);
			    colNameList.add(r.col.toLowerCase());
			}// 不为null
		}
		if(rs.size()==0) return null;

		return rs;
	}



	/**
	 * 获取这个类的所有父类
	 * @param clazz
	 * @return
	 */
	public static List<Class<?>> getSuperClass(Class<?> clazz){
		List<Class<?>> clazzs=new ArrayList<Class<?>>();
		Class<?> suCl=clazz.getSuperclass();
		while(suCl!=null){
			System.out.println(suCl.getName());
			clazzs.add(suCl);
			suCl=suCl.getSuperclass();
		}
		return clazzs;
	}

	/**
	 * 获取这个类及所有父类 所有列
	 * @param clazz
	 * @return
	 */
	public static Field[] getSelfAndSuperClassFields(Class<?> clazz){
		List<Field[]> fields=new ArrayList<Field[]>();
		Field[] resultFields=clazz.getDeclaredFields();

		Class<?> suCl=clazz.getSuperclass();
		while(suCl!=null){
//			System.out.println(suCl.getName());
			resultFields=	concatAll(resultFields,suCl.getDeclaredFields());

			suCl=suCl.getSuperclass();
		}
		return resultFields;
	}

	/**
	 *  获取类的 列的 声明的列
	 * @param clazz
	 * @return
	 */
	 public static Field[] getAllField(Class clazz)
	 {
		return concatAll(clazz.getDeclaredFields(),
				clazz.getSuperclass().getDeclaredFields(),
				clazz.getSuperclass().getSuperclass().getDeclaredFields()
				);

	 }

	public static <T> T[] concatAll(T[] first, T[]... rest) {

		int totalLength = first.length;

		for (T[] array : rest) {

			totalLength += array.length;

		}

		T[] result = Arrays.copyOf(first, totalLength);

		int offset = first.length;

		for (T[] array : rest) {

			System.arraycopy(array, 0, result, offset, array.length);

			offset += array.length;

		}

		// 去重复 字段
		//TreeSet<T>  treeSet=new TreeSet();
		//treeSet.addAll(Arrays.asList(result));
		//T[] newResult=(T[])new Object[treeSet.size()];
	//	T[] newResult=	treeSet.toArray(new T[treeSet.size()]);


		return result;

	}


	public static Boolean filterCols(Field f) {
		if(f.getDeclaredAnnotation(OneToOne.class)!=null) return true;
		if(f.getDeclaredAnnotation(OneToMany.class)!=null)return true;
		if(f.getDeclaredAnnotation(ManyToOne.class)!=null)return true;
		if(f.getDeclaredAnnotation(IgnoreField.class)!=null)return true;
		if("serialVersionUID".equals(f.getName()))return true;
		return false;
	}
	/**
	 * 获取类对应的表
	 * 首先获取 注解Table.name 其次 类名
	 * @param clazz 传入的类
	 * @return
	 * @throws Exception
	 */
	public static String getTable(Class clazz) throws Exception{
		//Class clazz = obj.getClass();
		String table;
		Table tn = (Table) clazz.getDeclaredAnnotation(Table.class);

		// 获取父类注解
		Table tnSuper = (Table) clazz.getSuperclass().getDeclaredAnnotation(Table.class);
		if(tn==null&&tnSuper!=null)
		{
			tn=tnSuper;
		}
		//System.out.println((clazz.getSimpleName()).toLowerCase());
		if(tn==null)
			table = (clazz.getSimpleName()).toLowerCase();
		else
			table = tn.name();

		return table;
	}

	/***
	 * 寻找列 里面的 主键字段 注解未@id 的列 ；找不到则 直接返回ID 字符串
	 */
	public static String getKeyColName(Class clazz) throws Exception{

		for(Field f : 	getSelfAndSuperClassFields(clazz))
		{
			//f.setAccessible(true);
			if(f.getDeclaredAnnotation(Id.class)==null)
				continue;
			else {
				Id IdAnnotation=(Id)f.getDeclaredAnnotation(Id.class);
				if( ComFunc.empty(IdAnnotation.name()))
				{// @Id 注解为空 尝试看看这个实体列 是否同时具备 @Column
					if(f.getDeclaredAnnotation(Column.class)!=null) {
						Column ColumnAnnotation = (Column) f.getDeclaredAnnotation(Column.class);
						if (!ComFunc.empty(ColumnAnnotation.name())) {
							return ColumnAnnotation.name();  // 注解Column 上的 名字
						}
					}

					return f.getName();  // 实体变量的 名字
				}

				return IdAnnotation.name();	// 注解ID 上的 名字 那么 字段
				}
		}

		return "id";
	}


	/**
	 * 寻找 真实的数据库 列名
	 *  注解@Id >注解@Column
	 * @param f
	 * @return
	 */
	private static String getRealFieldTableName(Field f)
	{
		String result="";
		Column ColumnAnnotation=(Column)f.getDeclaredAnnotation(Column.class);
		Id IdAnnotation=(Id)f.getDeclaredAnnotation(Id.class);
		if(ColumnAnnotation!=null&&!ComFunc.empty(ColumnAnnotation.name()))
		{
			result=ColumnAnnotation.name();	// 注解上真实的 数据库列名
		}
		if(IdAnnotation!=null&&!ComFunc.empty(IdAnnotation.name()))
		{
			result=IdAnnotation.name(); // 注解上真实的 数据库列名
		}

		//return result;
//		String fName=f.getName();	// 这个class上的字段名字
//		if(ComFunc.empty(result)) {
//			result= fName;
//		}

			return result;
	}

	/**
	 * 获取 组装 sql的 列名 as
	 * @param f
	 * @return
	 */
	private static String getSelectRealFieldName(Field f) {
		String realField=getRealFieldTableName(f);
		String fName=f.getName();	// 这个class上的字段名字
		if(!ComFunc.empty(realField)&&!realField.equals(fName)) {
			return realField+" as "+fName;
		}
		return fName;
	}

	/***
	 * 获取列的 对应 数据库的真实列名 没有则返回 类的列名
	 * @param f
	 * @return
	 */
	private static String getReplaceFieldName(Field f) {
		String realField=getRealFieldTableName(f);
		String fName=f.getName();	// 这个class上的字段名字
		if(!ComFunc.empty(realField)) {
			return realField;
		}
		return fName;
	}


	/***
	 * 根据类和列名 查找数据库真实的列  返回 数据库 列名 真实
	 */
	public static String getRealFieldNameByClass(String col, Class<?> clazz) {

		Field[] fields=	getSelfAndSuperClassFields(clazz);

		return getRealFieldNameByFields(col,fields);

	}

	/***
	 * 根据 列名数组 和列名 查找真实的列
	 */
	private static String getRealFieldNameByFields(String col,Field[] fields) {

		for (Field f:fields) {

			// 指定的 列名 可能是 真实列名 也可能是 数据库列名
			if(col.equalsIgnoreCase(f.getName())
					|| col.equalsIgnoreCase(getRealFieldTableName(f))
			)
			{
				return getReplaceFieldName(f);

			}

		}
		return col;
	}


	/***
	 * 根据类和列名 查找数据库真实的列 返回是  ba_ne as bane
	 */
	public static String getSelectFieldNameByClass(String col, Class<?> clazz) {

	Field[] fields=	getSelfAndSuperClassFields(clazz);

	return getSelectFieldNameByFields(col,fields);

	}
	/***
	 * 根据 列名数组 和列名 查找真实的列
	 */
	private static String getSelectFieldNameByFields(String col,Field[] fields) {

		for (Field f:fields) {

			// 指定的 列名 可能是 真实列名 也可能是 数据库列名
			if(col.equalsIgnoreCase(f.getName())
					|| col.equalsIgnoreCase(getRealFieldTableName(f))
			)
			{
				return getSelectRealFieldName(f);

			}

		}
		return col;
	}

}
