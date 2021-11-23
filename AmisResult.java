package com.cubegalaxy.sgdc.common.constant;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class AmisResult implements Serializable{

	public static final Integer SUCCESS = 0;

	public static final Integer ERROR = 1;

	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Object data;
	private Map<String,Object> map;

	public AmisResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.map = new HashMap<String,Object>();
	}

	public static AmisResult ok() {
		return new AmisResult(SUCCESS,"success",null);
	}

	public static AmisResult ok(Object data) {
		return new AmisResult(SUCCESS,"success",data);
	}

	public static AmisResult error(String msg) {
		return new AmisResult(ERROR,msg,null);
	}

	public AmisResult put(String key,Object value){
		map.put(key,value);
		data = map;
		return this;
	}

}
