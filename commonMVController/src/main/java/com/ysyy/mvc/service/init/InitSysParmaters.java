//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ysyy.mvc.service.init;

import com.ysyy.common.mvc.token.TokenEntity;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class InitSysParmaters {
	public InitSysParmaters.Types types = new InitSysParmaters.Types();
	private ConcurrentHashMap<String, TokenEntity> tokenMap = new ConcurrentHashMap();

	public InitSysParmaters() {
	}

	public ConcurrentHashMap<String, TokenEntity> getTokenMap() {
		return this.tokenMap;
	}

	public void setTokenMap(ConcurrentHashMap<String, TokenEntity> tokenMap) {
		this.tokenMap = tokenMap;
	}

	public class Types {
		public final String tokenType = "01";

		public Types() {
		}
	}
}
