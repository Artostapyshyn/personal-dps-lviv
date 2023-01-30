package com.artostapyshyn.personaldpslviv.model.service;

import java.util.List;
import java.util.Optional;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;

public interface EmployeeService {

	Employee saveAndFlush(EmployeeDto employee);

	void deleteById(Long id);
	
    Employee findByEmail(String email);

    List<EmployeeDto> findAll();
    
    Employee findByConfirmationToken(String confirmationToken);

	Optional<Employee> findById(Long id);
	
	void updateResetToken(String token, String email);
	
	void updatePassword(Employee employee, String newPassword);
	
	Employee findByResetToken(String token);
}
