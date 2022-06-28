package com.vastag.sb.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorObject extends ErrorObject {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationErrorObject(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
}
