//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.common.mvc;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;

public class ComFun {
	public ComFun() {
	}

	public static String getTokenId() {
		return UUID.randomUUID().toString();
	}

	public static String getUid(HttpServletRequest request) {
		return "00000000";
	}

	public static void copyProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}
}
