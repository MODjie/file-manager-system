package com.jie.utils;

import java.io.Serializable;

public class ActionResult implements Serializable {
	static final int SUCCESS_CODE = 10000;
	private int code;
	private String message;
	private Object data;
	private long timestamp = System.currentTimeMillis();

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return this.data;
	}

	public <T> T getData(Class<T> dataType) {
		return dataType.cast(this.data);
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isSuccess() {
		return this.code == 10000;
	}
}