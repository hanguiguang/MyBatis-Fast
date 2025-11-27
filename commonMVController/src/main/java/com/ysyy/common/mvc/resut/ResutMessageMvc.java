//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.common.mvc.resut;

import com.alibaba.fastjson.JSON;
import com.ysyy.common.resut.ResutMessage;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

public class ResutMessageMvc extends ResutMessage {
	private static final long serialVersionUID = 1L;

	public ResutMessageMvc() {
	}

	public void setResutToken(String state, String message, String token) {
		System.out.println(message);
		this.setParamers("token", (Object)token);
		this.setState(state);
		this.setMessage(message);
	}

	public void setResutToken(String state, String message) {
		this.setState(state);
		this.setMessage(message);
	}

	public void setExceptionToken(String token, Exception e) {
		e.printStackTrace();
		this.setParamers("token", (Object)token);
		this.setState("3001");
		this.setMessage("保存失败:" + e.getMessage());
	}

	public void setExceptionResutToken(HttpServletRequest request, Exception e) throws Exception {
		e.printStackTrace();
		String token = UUID.randomUUID().toString();
		request.getSession(true).setAttribute("token", token);
		this.setParamers("token", (Object)token);
		this.setState("3001");
		this.setMessage("保存失败:" + e.getMessage());
	}

	public void setResutToken(String state, String message, HttpServletRequest request) throws Exception {
		String token = UUID.randomUUID().toString();
		request.getSession(true).setAttribute("token", token);
		this.setParamers("token", (Object)token);
		this.setState(state);
		this.setMessage(message);
	}

	public void setExceptionResut(Exception e) throws Exception {
		this.setState("3001");
		this.setMessage("保存失败:" + e.getMessage());
		e.printStackTrace();
	}

	public void setParamers(String parName, Object value) {
		if (value != null) {
			this.resut.put(parName, value);
		}
	}

	public void setParamers(String parName, List valueList) throws Exception {
		if (valueList != null && !valueList.isEmpty()) {
			this.resut.put(parName, valueList);
		}
	}

	public String toJson(Object obj) {
		return JSON.toJSONString(obj);
	}

	public void p() {
		System.out.println("resut:" + JSON.toJSON(this.resut));
		System.out.println("message:" + this.message);
		System.out.println("state:" + this.state);
	}

	public Object resutData(Object rs) {
		return this.toJson(rs);
	}
}
