package com.artostapyshyn.personaldpslviv.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.artostapyshyn.personaldpslviv.exceptions.EmployeeIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.exceptions.EmployeeNotFoundException;

@ControllerAdvice("com.artostapyshyn.personaldpslviv.controller")
public class ExceptionController {
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public String userNotFoundException(EmployeeNotFoundException ex) {
		return "error";
	}
	
	@ExceptionHandler(EmployeeIdIsNotValidException.class)
	public String userIdIsNotValidException(EmployeeIdIsNotValidException ex) {
		return "error";
	}
	
}
