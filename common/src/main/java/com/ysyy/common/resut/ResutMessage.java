//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.common.resut;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResutMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	public String state = "1001";
	public Map resut = new HashMap();
	public String message;

	public ResutMessage() {
	}

	public void setResut(String state, String message) throws Exception {
		this.setState(state);
		this.setMessage(message);
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setMessage(String message) {
		if (this.message != null && !this.message.isEmpty()) {
			this.message = this.message + message;
		} else {
			this.message = message;
		}

	}

	public void setResutException(Exception e) {
		e.printStackTrace();
		this.setState("3001");

		try {
			this.setMessage("执行失败:" + e.getMessage());
		} catch (Exception var3) {
			this.setMessage("执行失败:Exception");
		}

	}
}
