package com.vastag.sb.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonServiceException;
import com.vastag.sb.services.exceptions.AuthorizationException;
import com.vastag.sb.services.exceptions.DataIntegrityException;
import com.vastag.sb.services.exceptions.FileException;
import com.vastag.sb.services.exceptions.ObjectNotFoundException;
import com.vastag.sb.services.exceptions.UploadFileToAmazonS3Exception;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ErrorObject> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "NOT FOUND", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<ErrorObject> fileFailure(FileException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro de arquivo", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(UploadFileToAmazonS3Exception.class)
	public ResponseEntity<ErrorObject> uploadFileToAmazonS3Failure(UploadFileToAmazonS3Exception e,
			HttpServletRequest request) {
		ErrorObject err = new ErrorObject(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorObject> objectNotFound(AuthorizationException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "FORBIDDEN", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<ErrorObject> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(System.currentTimeMillis(), HttpStatus.BAD_GATEWAY.value(), "BAD_GATEWAY", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(err);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<ErrorObject> amazonService(AmazonServiceException e, HttpServletRequest request) {
		ErrorObject err = new ErrorObject(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro Amazon Client", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorObject> methodArgumentNotValid(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		ValidationErrorObject err = new ValidationErrorObject(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", e.getMessage(), request.getRequestURI());

		e.getBindingResult().getFieldErrors().stream().forEach(er -> {
			err.addError(er.getField(), er.getDefaultMessage());
		});

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}

}
