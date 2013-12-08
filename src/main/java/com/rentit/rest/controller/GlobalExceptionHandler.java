package com.rentit.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rentit.exception.InvalidHirePeriodException;
import com.rentit.exception.NotAcceptableException;
import com.rentit.exception.PlantUnavailableException;
import com.rentit.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Void> handleResoiurceNotFound(ResourceNotFoundException ex) {

		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidHirePeriodException.class)
	public ResponseEntity<Void> handleInvalidDatePeriod(ResourceNotFoundException ex) {

		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotAcceptableException.class)
	public ResponseEntity<Void> handleNotAcceptable(NotAcceptableException ex) {

		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(PlantUnavailableException.class)
	public ResponseEntity<Void> handlePlantUnavailable(PlantUnavailableException ex) {

		return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
}
