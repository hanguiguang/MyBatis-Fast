//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.common;

import com.alibaba.fastjson.JSON;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class ComFunc {
	public ComFunc() {
	}

	public static <T> List<T> deepCopy(List<T> src) {
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(byteOut);
			out.writeObject(src);
			ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			List<T> dest = (List)in.readObject();
			return dest;
		} catch (IOException var6) {
			var6.printStackTrace();
		} catch (ClassNotFoundException var7) {
			var7.printStackTrace();
		}

		return null;
	}

	public static Boolean arrayContains(Object value, Object[] array) {
		if (!array.getClass().isArray()) {
			return false;
		} else {
			Object[] var5 = array;
			int var4 = array.length;

			for(int var3 = 0; var3 < var4; ++var3) {
				Object obj = var5[var3];
				if (obj.equals(value) || obj == value) {
					return true;
				}
			}

			return false;
		}
	}

	public static boolean isBaseDataType(Class clazz) throws Exception {
		return clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Byte.class) || clazz.equals(Long.class) || clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Character.class) || clazz.equals(Short.class) || clazz.equals(BigDecimal.class) || clazz.equals(BigInteger.class) || clazz.equals(Boolean.class) || clazz.equals(Date.class) || clazz.isPrimitive();
	}

	public static void p(String obj) throws Exception {
		System.out.println(obj);
	}

	public static void p(Object obj) throws Exception {
		System.out.println(JSON.toJSON(obj));
	}

	public static void p(Object obj, String message) throws Exception {
		System.out.println(message + ":" + JSON.toJSON(obj));
	}

	public static boolean isEmpty(boolean isThrowOutBool, Object... args) throws Exception {
		int i = 0;
		Object[] var6 = args;
		int var5 = args.length;

		for(int var4 = 0; var4 < var5; ++var4) {
			Object a = var6[var4];
			++i;
			if (!msgThrowOut(isThrowOutBool, a == null, "第" + i + "个参数null！")) {
				return true;
			}

			System.out.println(a.getClass().getName());
			Class clazz = a.getClass();
			if (a instanceof List) {
				if (!msgThrowOut(isThrowOutBool, ((List)a).isEmpty(), "第" + i + "个参数为空")) {
					return true;
				}
			} else if (a instanceof String && !msgThrowOut(isThrowOutBool, a.toString().isEmpty(), "第" + i + "个参数为空")) {
				return true;
			}
		}

		return false;
	}

	private static boolean msgThrowOut(boolean isThrowOutBool, boolean isTrue, String msg) throws Exception {
		if (isThrowOutBool) {
			if (isTrue) {
				throw new Exception(msg);
			} else {
				return true;
			}
		} else {
			return !isTrue;
		}
	}

	public static Object getObjValue(String clazzname, String value) throws Exception {
		if (isEmpty(false, clazzname, value)) {
			return null;
		} else {
			Constructor ct = Class.forName(clazzname).getConstructor(String.class);
			return ct.newInstance(value);
		}
	}

	public static boolean isNull(String... strs) {
		String[] var4 = strs;
		int var3 = strs.length;

		for(int var2 = 0; var2 < var3; ++var2) {
			String str = var4[var2];
			if (str == null || str.isEmpty()) {
				return true;
			}
		}

		return false;
	}

	public static void nullThrowException(String... strs) throws Exception {
		String[] var4 = strs;
		int var3 = strs.length;

		for(int var2 = 0; var2 < var3; ++var2) {
			String str = var4[var2];
			if (str == null || str.isEmpty()) {
				throw new Exception("不能为空");
			}
		}

	}

	public static void copyProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}

	public static boolean empty(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean empty(Integer str) {
		return str == null;
	}

	public static boolean empty(List str) {
		return str == null || str.size() <= 0;
	}

	public static boolean empty(Object str) { return str == null ; }

	public static String listToString(List<String> list,String seperator)
	{
		StringBuilder sb = new StringBuilder();

		String resultString = "";

		boolean flag = false;

		for(String str : list){
			if(flag){
				sb.append(seperator);

			}else{
				flag = true;

			}
			sb.append(str);

		}
		resultString=sb.toString();
		return resultString;

	}
}
