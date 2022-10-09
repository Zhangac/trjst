package com.trjst.util;

import com.alibaba.fastjson.JSONObject;
import com.trjst.enums.ErrorCode;
import com.trjst.enums.ErrorCodeSys;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回数据
 *
 */
@Setter
@Getter
public class R<T> {
	private static final long serialVersionUID = 1L;

	private Integer code;

	private String msg;

	private T data;

	public R() {
		setCode(0);
		setMsg("success");
	}
	
	public static R error() {
		return error(ErrorCodeSys.ERROR_UNKNOWN);
	}
	
	public static R error(String msg) {
		return error(ErrorCodeSys.ERROR_UNKNOWN.getCode(), msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static R error(ErrorCode errorCode) {
		return R.error(errorCode.getCode(),errorCode.getMsg());
	}

	public static R ok(String msg) {
		R r = new R();
		r.setMsg(msg);
		return r;
	}
	
	public static R ok() {
		return new R();
	}

	public R put(String key, Object value) {
		setData(null);
		return this;
	}

	public R putData(T t) {
		setData(t);
		return this;
	}
	public String toJson(){
		return JSONObject.toJSON(this).toString();
	}
}
