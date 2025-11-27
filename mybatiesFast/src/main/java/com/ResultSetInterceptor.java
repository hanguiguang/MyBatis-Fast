////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by Fernflower decompiler)
////
//
//package com;
//
//import com.ysyy.database.mysql.utils.annotation.DynTable;
//import org.apache.ibatis.executor.ErrorContext;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.executor.parameter.ParameterHandler;
//import org.apache.ibatis.executor.resultset.ResultSetHandler;
//import org.apache.ibatis.executor.resultset.ResultSetWrapper;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ResultMap;
//import org.apache.ibatis.mapping.ResultMap.Builder;
//import org.apache.ibatis.mapping.ResultMapping;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.SystemMetaObject;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//import org.springframework.beans.BeanUtils;
//
//import java.beans.PropertyDescriptor;
//import java.sql.Statement;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.sql.SQLException;
//import java.util.*;
////
////MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
////		Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
////		ParameterHandler (getParameterObject, setParameters)
////		ResultSetHandler (handleResultSets, handleOutputParameters)
////		StatementHandler (prepare, parameterize, batch, update, query)
////		概括一下,分别是拦截执行器、参数控制器、结果控制器、SQL语句构建控制器,对执行流程进行更改.对应四个对象
////
//// type : 这儿主要是拦截对象的类型,是前面拦截器介绍中的四种之一,或多种(源码中type返回值是数组)
////		 method : 方法只能是一个值,
////		 ​ args: 值是前面介绍的四种拦截器括号中的内容,可以有多个.
//
////
////MappedStatement.class,
////		Statement.class,
////		Object.class,
////		RowBounds.class,
////		ResultHandler.class,
////		Executor.class,
////		ParameterHandler.class,
////		BoundSql.class
//
//@Intercepts({@Signature(
//		type = ResultSetHandler.class,
//		method = "handleResultSets",
//		args = {
//                Statement.class
//        }
//)})
//public class ResultSetInterceptor  implements Interceptor {
//	private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList(0);
//	public static final String DEFAULT_KEY = "class";
//	private String resultType = "class";
//
//	public ResultSetInterceptor() {
//		super();
//	}
//
//	public Object intercept(Invocation invocation) throws Throwable {
//		Object[] args = invocation.getArgs();
//		Object result = invocation.proceed();
//
//        Statement  statement = (Statement)args[0];
//
//		// 用我们自己的写的 代码 强转
//		DefaultResultSetHandler defaultResultSetHandler = (DefaultResultSetHandler) invocation.getTarget();
//		//MappedStatement维护了一条<select|update|delete|insert>节点的封装
//		//MappedStatement mappedStatement = defaultResultSetHandler.getMappedStatement();
//
////		// 强转
////		DefaultResultSetHandler defaultResultSetHandlerNew=
////				(DefaultResultSetHandler) defaultResultSetHandler;
//
//		return  defaultResultSetHandler.handleResultSets(statement);
//
//		}
//
//
////
////	@Override
////	public Object intercept(Invocation invocation) throws Throwable {
////		System.out.println("Result Plugin 拦截 :"+invocation.getMethod());
////		Object result = invocation.proceed();
////		if (result instanceof Collection) {
////			Collection<Object> objList= (Collection) result;
////			List<Object> resultList=new ArrayList<>();
////			for (Object obj : objList) {
////				resultList.add(desensitize(obj));
////			}
////			return resultList;
////		}else {
////			return desensitize(result);
////		}
////	}
////	//脱敏方法，将加密字段变为星号
////	private Object desensitize(Object object) throws InvocationTargetException, IllegalAccessException {
////		Field[] fields = object.getClass().getDeclaredFields();
//////		for (Field field : fields) {
////////			Confidential confidential = field.getAnnotation(Confidential.class);
////////			if (confidential==null){
////////				continue;
////////			}
//////			PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(object.getClass(), field.getName());
//////			if (ps.getReadMethod() == null || ps.getWriteMethod() == null) {
//////				continue;
//////			}
//////			Object value = ps.getReadMethod().invoke(object);
//////			if (value != null) {
//////				ps.getWriteMethod().invoke(object, "***");
//////			}
//////		}
////		return object;
////	}
//
//
//}
