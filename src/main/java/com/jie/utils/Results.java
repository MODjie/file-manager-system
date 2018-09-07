package com.jie.utils;

public class Results {
	public static ActionResult success() {
		return success((String) null);
	}

	public static ActionResult success(String message) {
		return success(message, (Object) null);
	}

	public static ActionResult success(String message, Object data) {
		ActionResult actionResult = new ActionResult();
		actionResult.setCode(10000);
		actionResult.setMessage(message);
		actionResult.setData(data);
		return actionResult;
	}

	public static ActionResult failed(int code) {
		return failed(code, (String) null);
	}

	public static ActionResult failed(int code, String message) {
		return failed(code, message, (Object) null);
	}

	public static ActionResult failed(int code, String message, Object data) {
		ActionResult actionResult = new ActionResult();
		actionResult.setCode(code);
		actionResult.setMessage(message);
		actionResult.setData(data);
		return actionResult;
	}
}
