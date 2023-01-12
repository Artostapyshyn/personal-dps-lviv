package com.artostapyshyn.personaldpslviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Employee findByEmail(String email);
	
	EmployeeDto findByConfirmationToken(String confirmationToken);

}