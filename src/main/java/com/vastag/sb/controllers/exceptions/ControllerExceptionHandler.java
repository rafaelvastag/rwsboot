package com.vastag.sb.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vastag.sb.services.exceptions.AuthorizationException;
import com.vastag.sb.services.exceptions.DataIntegrityException;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorObject> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(HttpStatus.NOT_FOUND, e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorObject> objectNotFound(AuthorizationException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(HttpStatus.FORBIDDEN, e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ErrorObject> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(HttpStatus.BAD_GATEWAY, e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorObject> methodArgumentNotValid(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		ValidationErrorObject err = new ValidationErrorObject(HttpStatus.BAD_GATEWAY, "Erro de validação",
				System.currentTimeMillis());

		e.getBindingResult().getFieldErrors().stream().forEach(er -> {
			err.addError(er.getField(), er.getDefaultMessage());
		});

		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(err);
	}

}
