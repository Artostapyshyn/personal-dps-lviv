package com.artostapyshyn.personaldpslviv.model.service.impl;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.entity.Role;
import com.artostapyshyn.personaldpslviv.model.repository.EmployeeRepository;
import com.artostapyshyn.personaldpslviv.model.repository.RoleRepository;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import lombok.AllArgsConstructor;
 
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	 
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void saveAndFlush(EmployeeDto employeeDto) {
    	Employee employee = new Employee();
    	employee.setFirstName(employeeDto.getFirstName());
    	employee.setLastName(employeeDto.getLastName());
    	employee.setBirthDate(employeeDto.getBirthDate());
    	employee.setPhoneNumber(employeeDto.getPhoneNumber());
    	employee.setDepartment(employeeDto.getDepartment());
        employee.setEmail(employeeDto.getEmail());
        
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        
        if(role == null){
            role = checkRoleExist();
        }
        
        employee.setRoles(Arrays.asList(role));
        employeeRepository.save(employee);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
        		.map((employee) -> convertEntityToDto(employee))
                .collect(Collectors.toList());
    }

    private EmployeeDto convertEntityToDto(Employee employee){
    	EmployeeDto employeeDto = new EmployeeDto();
    	employeeDto.setFirstName(employee.getFirstName());
    	employeeDto.setLastName(employee.getLastName());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setBirthDate(employee.getBirthDate());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setEmail(employee.getEmail());
        return employeeDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

}