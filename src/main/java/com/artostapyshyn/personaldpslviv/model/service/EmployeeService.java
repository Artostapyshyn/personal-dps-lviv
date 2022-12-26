package com.artostapyshyn.personaldpslviv.model.service;

import java.util.List;
import java.util.Optional;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;

public interface EmployeeService {

	void saveAndFlush(EmployeeDto employeeDto);

    Optional<Employee>  findByEmail(String email);

    List<EmployeeDto> findAll();

}
