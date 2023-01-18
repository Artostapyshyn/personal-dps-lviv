package com.artostapyshyn.personaldpslviv.exceptions;

public class UserIdIsNotValidException extends RuntimeException {

    public UserIdIsNotValidException(Long id) {
        super("User with id: " + id + " is not found");
    }

}
