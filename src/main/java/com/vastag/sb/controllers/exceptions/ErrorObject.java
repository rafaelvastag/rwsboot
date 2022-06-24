package com.vastag.sb.controllers.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ErrorObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private Integer code;
	private String message;
	private Long timeStamp;

	public ErrorObject(HttpStatus status, String message, Long timeStamp) {
		super();
		this.status = status;
		this.code = status.value();
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
