package com.artostapyshyn.personaldpslviv.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String email) {
        super("Could not find user with email: " + email);
    }

}
