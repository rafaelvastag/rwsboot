package com.vastag.sb.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vastag.sb.services.exceptions.DataIntegrityException;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorObject> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(HttpStatus.NOT_FOUND, e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ErrorObject> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(HttpStatus.BAD_GATEWAY, e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(err);
	}
	
}
