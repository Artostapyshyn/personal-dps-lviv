package com.artostapyshyn.personaldpslviv.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.artostapyshyn.personaldpslviv.exceptions.UserIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.exceptions.UserNotFoundException;

@ControllerAdvice("com.artostapyshyn.personaldpslviv.controller")
public class ExceptionController {
	
	@ExceptionHandler(UserNotFoundException.class)
	public String userNotFoundException(UserNotFoundException ex) {
		return "error";
	}
	
	@ExceptionHandler(UserIdIsNotValidException.class)
	public String userIdIsNotValidException(UserIdIsNotValidException ex) {
		return "error";
	}
	
}
