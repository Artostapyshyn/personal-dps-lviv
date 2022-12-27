package com.artostapyshyn.personaldpslviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Employee findByEmail(String email);
}