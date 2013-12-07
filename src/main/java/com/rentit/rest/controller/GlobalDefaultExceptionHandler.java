package com.rentit.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rentit.exception.NotFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({NotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handleCustomException(NotFoundException ex) {

		ModelAndView andView = new ModelAndView();
		andView.setViewName("error");
		return andView;

	}

}
