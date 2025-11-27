//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.common.mvc.interceptor;

import com.ysyy.common.SpringContextUtil;
import com.ysyy.common.annotation.Token;
import com.ysyy.mvc.service.CommonService;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TokenInterceptor extends HandlerInterceptorAdapter {
	public TokenInterceptor() {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod)handler;
			Method method = handlerMethod.getMethod();
			Token annotation = (Token)method.getAnnotation(Token.class);
			if (annotation != null) {
				new SpringContextUtil();
				CommonService commonService = (CommonService)SpringContextUtil.getBean("commonServiceImpl");
				if (annotation.save()) {
					response.addHeader("token", commonService.setToken());
				} else if (annotation.remove() && this.isRepeatSubmit(request, commonService)) {
					System.out.println("不能重复提交申请页面！");
					return false;
				}
			}
		}

		return true;
	}

	private boolean isRepeatSubmit(HttpServletRequest request, CommonService commonService) throws Exception {
		String clinetToken = request.getHeader("token");
		if (clinetToken == null) {
			return false;
		} else {
			return !commonService.removeToken(clinetToken);
		}
	}
}
