package com.proj.gateway.exceptions;

import io.jsonwebtoken.ExpiredJwtException;

public class InvalidGrantException extends Exception {

	public InvalidGrantException(String msg, ExpiredJwtException e) {
		super();
		this.msg = msg;
		this.e = e;
	}

	private String msg;
	private ExpiredJwtException e;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ExpiredJwtException getE() {
		return e;
	}

	public void setE(ExpiredJwtException e) {
		this.e = e;
	}

}
