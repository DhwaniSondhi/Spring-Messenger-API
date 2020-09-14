package org.prac.MessengerAPI.customexceptions;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ValidationError.class)
	public ResponseEntity<String> handler(ValidationError e) {
		return new ResponseEntity<String>("Not able to proceed due to validation error: " + e.getMessage(),
											HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<String> handler(NoResultException e) {
		return new ResponseEntity<String>("Error: " + e.getMessage(),
											HttpStatus.NOT_FOUND);
	}
}
