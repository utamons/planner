package com.corn.planner.configuration;

import com.corn.planner.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;

@SuppressWarnings("unused")
@ControllerAdvice
@RequestMapping(produces = "application/json")
@ResponseBody
public class ExceptionTranslator {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Error> notFoundException(final EntityNotFoundException e) {
		Error error = Error.ErrorBuilder
				.anError()
				.withMessage(e.getMessage())
				.withStatus(HttpStatus.NOT_FOUND.value())
				.build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Error> validationException(final ValidationException e) {
		Error error = Error.ErrorBuilder
				.anError()
				.withMessage(e.getMessage())
				.withStatus(HttpStatus.BAD_REQUEST.value())
				.build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Error> runtimeException(final RuntimeException e) {
		Error error = Error.ErrorBuilder
				.anError()
				.withMessage(e.getMessage())
				.withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> Exception(final Exception e) {
		Error error = Error.ErrorBuilder
				.anError()
				.withMessage(e.getMessage())
				.withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
