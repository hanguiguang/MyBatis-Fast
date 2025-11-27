//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com;

import com.ysyy.common.Regex;

public class SqlStrProcess {
	public SqlStrProcess() {
	}

	public static String sqlStrProcessByWhere(String str, Integer offset, Integer size, String... whereStrs) throws Exception {
		if (Regex.queryStrCheck(new String[]{str}) && Regex.queryStrCheck(whereStrs)) {
			StringBuffer where = new StringBuffer();
			if (whereStrs != null && whereStrs.length > 0) {
				String[] var8 = whereStrs;
				int var7 = whereStrs.length;

				for(int var6 = 0; var6 < var7; ++var6) {
					String whereStr = var8[var6];
					if (whereStr != null && !"".equals(whereStr)) {
						if (where.length() == 0) {
							where.append(whereStr);
						} else {
							where.append(" and " + whereStr);
						}
					}
				}

				if (where.length() > 0) {
					where.insert(0, " where ");
				}
			}

			if (offset == null) {
				offset = 0;
			}

			if (size == null || size == 0) {
				size = 2147483647;
			}

			where.append(" limit " + offset + " ," + size);
			return str + where.toString();
		} else {
			throw new Exception("查询条件有非法字符或包含限定词：insert delete update create！");
		}
	}

	public static String sqlStrProcessByWhere(String str, String[] whereStrs) throws Exception {
		return sqlStrProcessByWhere(str, (Integer)null, (Integer)null, whereStrs);
	}

	public static String addWhereCondition(String col, Object value, String operator) throws Exception {
		if ("=".equals(operator)) {
			return " " + col + "='" + value + "' ";
		} else if ("<>".equals(operator)) {
			return " " + col + "<>'" + value + "' ";
		} else if ("contain".equals(operator)) {
			return " " + col + "like'" + value + "' ";
		} else if (">".equals(operator)) {
			return " " + col + ">'" + value + "' ";
		} else if (">=".equals(operator)) {
			return " " + col + "=>'" + value + "' ";
		} else if ("<".equals(operator)) {
			return " " + col + "<'" + value + "' ";
		} else if ("<=".equals(operator)) {
			return " " + col + "<='" + value + "' ";
		} else {
			throw new Exception("查询条件有非法字符或包含限定词：insert delete update create！");
		}
	}

	public static String processByWhere(Integer offset, Integer size, String... whereStrs) throws Exception {
		if (!Regex.queryStrCheck(whereStrs)) {
			throw new Exception("查询条件有非法字符或包含限定词：insert delete update create！");
		} else {
			StringBuffer where = new StringBuffer();
			if (whereStrs != null && whereStrs.length > 0) {
				String[] var7 = whereStrs;
				int var6 = whereStrs.length;

				for(int var5 = 0; var5 < var6; ++var5) {
					String whereStr = var7[var5];
					if (whereStr != null && !"".equals(whereStr)) {
						if (where.length() == 0) {
							where.append(whereStr);
						} else {
							where.append(" and " + whereStr);
						}
					}
				}
			}

			if (offset == null) {
				offset = 0;
			}

			if (size == null || size == 0) {
				size = 2147483647;
			}

			where.append(" limit " + offset + " ," + size);
			return where.toString();
		}
	}
}
