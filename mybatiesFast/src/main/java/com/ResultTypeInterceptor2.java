//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com;

import com.ysyy.database.mysql.utils.annotation.DynTable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.ResultMap.Builder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
//
//MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
//		Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
//		ParameterHandler (getParameterObject, setParameters)
//		ResultSetHandler (handleResultSets, handleOutputParameters)
//		StatementHandler (prepare, parameterize, batch, update, query)
//		概括一下,分别是拦截执行器、参数控制器、结果控制器、SQL语句构建控制器,对执行流程进行更改.对应四个对象
//
// type : 这儿主要是拦截对象的类型,是前面拦截器介绍中的四种之一,或多种(源码中type返回值是数组)
//		 method : 方法只能是一个值,
//		 ​ args: 值是前面介绍的四种拦截器括号中的内容,可以有多个.
//

@Intercepts({
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
		,
		@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})
})
public class ResultTypeInterceptor2 implements Interceptor {
	private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList(0);
	public static final String DEFAULT_KEY = "class";
	private String resultType = "class";

	public ResultTypeInterceptor2() {
	}

	public Object intercept(Invocation invocation) throws Throwable {
		Object[] args = invocation.getArgs();
		MappedStatement ms = (MappedStatement)args[0];
		String I = ms.getId().substring(0, ms.getId().lastIndexOf("."));
		Class<?> clazz = Class.forName(I);
		Annotation[] var9;
		int var8 = (var9 = clazz.getDeclaredAnnotations()).length;

		for(int var7 = 0; var7 < var8; ++var7) {
			Object o = var9[var7];
		}

		DynTable tn = (DynTable)clazz.getDeclaredAnnotation(DynTable.class);
		if (clazz.getDeclaredAnnotation(DynTable.class) == null) {
			return invocation.getMethod().invoke(invocation.getTarget(), invocation.getArgs());
		} else {
			Object parameterObject = args[1];
			Class resultType = this.getResultType(parameterObject);
			List<ResultMap> rms = ms.getResultMaps();
			Object[] os = new Object[invocation.getArgs().length];

			try {
				ResultMap rm = (ResultMap)rms.get(0);
				Builder b = new Builder(ms.getConfiguration(), rm.getId(), resultType, rm.getIdResultMappings(), rm.getAutoMapping());
				ResultMap temp = b.build();
				if (temp.hasNestedResultMaps() != rm.hasNestedResultMaps() && rm.hasNestedResultMaps()) {
					temp.forceNestedResultMaps();
				}

				b.discriminator(rm.getDiscriminator());
				if (rm != null) {
					String s;
					Iterator var15;
					if (rm.getMappedProperties() != null && rm.getMappedProperties().size() > 0) {
						var15 = rm.getMappedProperties().iterator();

						while(var15.hasNext()) {
							s = (String)var15.next();
							temp.getMappedProperties().add(s);
						}
					}

					if (rm.getMappedColumns() != null && rm.getMappedColumns().size() > 0) {
						var15 = rm.getMappedColumns().iterator();

						while(var15.hasNext()) {
							s = (String)var15.next();
							temp.getMappedColumns().add(s);
						}
					}

					ResultMapping m;
					if (rm.getIdResultMappings() != null && rm.getIdResultMappings().size() > 0) {
						var15 = rm.getIdResultMappings().iterator();

						while(var15.hasNext()) {
							m = (ResultMapping)var15.next();
							temp.getIdResultMappings().add(m);
						}
					}

					if (rm.getConstructorResultMappings() != null && rm.getConstructorResultMappings().size() > 0) {
						var15 = rm.getConstructorResultMappings().iterator();

						while(var15.hasNext()) {
							m = (ResultMapping)var15.next();
							temp.getConstructorResultMappings().add(m);
						}
					}

					if (rm.getPropertyResultMappings() != null && rm.getPropertyResultMappings().size() > 0) {
						var15 = rm.getPropertyResultMappings().iterator();

						while(var15.hasNext()) {
							m = (ResultMapping)var15.next();
							temp.getPropertyResultMappings().add(m);
						}
					}
				}

				MappedStatement.Builder mb = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), ms.getSqlSource(), ms.getSqlCommandType());
				mb.resource(ms.getResource());
				mb.fetchSize(ms.getFetchSize());
				mb.timeout(ms.getTimeout());
				mb.resultSetType(ms.getResultSetType());
				mb.cache(ms.getCache());
				mb.flushCacheRequired(ms.isFlushCacheRequired());
				mb.useCache(ms.isUseCache());
				mb.resultOrdered(ms.isResultOrdered());
				if (ms.getKeyProperties() != null) {
					mb.keyProperty(ms.getKeyProperties().toString());
				}

				if (ms.getKeyColumns() != null) {
					mb.keyColumn(ms.getKeyColumns().toString());
				}

				List<ResultMap> rmls = new ArrayList();
				rmls.add(temp);
				mb.resultMaps(rmls);
				mb.databaseId(ms.getDatabaseId());
				if (ms.getResulSets() != null) {
					mb.resulSets(ms.getResulSets().toString());
				}

				if (resultType == null) {
					return invocation.proceed();
				}

				int i = 0;
				Object[] var20;
				int var19 = (var20 = invocation.getArgs()).length;

				for(int var18 = 0; var18 < var19; ++var18) {
					Object o = var20[var18];
					if (o instanceof MappedStatement) {
						os[i] = mb.build();
					} else {
						os[i] = o;
					}

					++i;
				}
			} catch (Exception var21) {
				var21.printStackTrace();
			}

			return invocation.getMethod().invoke(invocation.getTarget(), os);
		}
	}

	private Class objectToClass(Object object) {
		if (object == null) {
			return null;
		} else if (object instanceof Class) {
			return (Class)object;
		} else if (object instanceof String) {
			try {
				return Class.forName((String)object);
			} catch (Exception var3) {
				throw new RuntimeException("非法的全限定类名字符串:" + object);
			}
		} else {
			throw new RuntimeException("方法参数类型错误，" + this.resultType + " 对应的参数类型只能为 Class 类型或者为 类的全限定名称字符串");
		}
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}

	private Class getResultType(Object parameterObject) {
		if (parameterObject == null) {
			return null;
		} else if (parameterObject instanceof Class) {
			return (Class)parameterObject;
		} else {
			Object result;
			if (parameterObject instanceof Map) {
				Map map = (Map)parameterObject;
				result = map.get("param1");
				System.out.println();
				if (((Map)parameterObject).containsKey("param1")) {
					 result = ((Map)parameterObject).get("param1");
					return this.objectToClass(result);
				} else {
					return null;
				}
			} else {
				MetaObject metaObject = SystemMetaObject.forObject(parameterObject);
				result = metaObject.getValue(this.resultType);
				return this.objectToClass(result);
			}
		}
	}
}
