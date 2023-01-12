package com.artostapyshyn.personaldpslviv.model.service;

import java.util.List;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;

public interface EmployeeService {

	void saveAndFlush(EmployeeDto employeeDto);

	void deleteById(Long id);
	
    Employee findByEmail(String email);

    List<EmployeeDto> findAll();
    
    EmployeeDto findByConfirmationToken(String confirmationToken);
}
