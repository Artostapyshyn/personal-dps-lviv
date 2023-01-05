package com.artostapyshyn.personaldpslviv.validation.impl;

import com.artostapyshyn.personaldpslviv.model.repository.EmployeeRepository;
import com.artostapyshyn.personaldpslviv.validation.UniqueEmail;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return employeeRepository.findByEmail(email) == null;
    }
}
