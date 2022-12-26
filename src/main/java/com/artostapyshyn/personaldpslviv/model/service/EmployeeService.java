package com.artostapyshyn.personaldpslviv.model.service;

import java.util.List;
import java.util.Optional;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;

public interface EmployeeService {

	Employee saveAndFlush(Employee employee);

    void deleteById(String id);

    Optional<Employee> findById(String id);

    List<Employee> findAll();

}
