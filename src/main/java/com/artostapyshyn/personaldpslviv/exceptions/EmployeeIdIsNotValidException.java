package com.artostapyshyn.personaldpslviv.exceptions;

public class EmployeeIdIsNotValidException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public EmployeeIdIsNotValidException(Long id) {
        super("User with id: " + id + " is not found");
    }

}
