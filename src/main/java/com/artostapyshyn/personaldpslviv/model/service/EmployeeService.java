package com.artostapyshyn.personaldpslviv.model.service;

import java.util.List;
import java.util.Optional;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;

public interface EmployeeService {

	Employee saveAndFlush(Employee employee);

    void deleteById(Long id);

    Optional<Employee> findById(Long id);

    Optional<Employee> findByDepartment(String department);

    List<Employee> findAll();

}
