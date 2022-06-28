package com.vastag.sb.services.exceptions;

public class UploadFileToAmazonS3Exception extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UploadFileToAmazonS3Exception(String msg) {
		super(msg);
	}

	public UploadFileToAmazonS3Exception(String msg, Throwable cause) {
		super(msg, cause);
	}
}
