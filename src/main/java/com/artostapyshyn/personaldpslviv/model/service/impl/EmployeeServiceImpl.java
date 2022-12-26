package com.artostapyshyn.personaldpslviv.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.repository.EmployeeRepository;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;
 
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveAndFlush(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void deleteById(String id) {
    	employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
 
 
}
