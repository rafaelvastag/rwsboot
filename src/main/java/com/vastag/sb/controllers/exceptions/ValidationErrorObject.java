package com.vastag.sb.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ValidationErrorObject extends ErrorObject {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationErrorObject(HttpStatus status, String message, Long timeStamp) {
		super(status, message, timeStamp);
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
}
