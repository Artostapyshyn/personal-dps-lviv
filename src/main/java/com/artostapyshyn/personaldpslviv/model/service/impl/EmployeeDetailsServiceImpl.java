package com.artostapyshyn.personaldpslviv.model.service.impl;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.repository.EmployeeRepository;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDetailsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @SuppressWarnings("unchecked")
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Employee employee = employeeRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    	
        return new org.springframework.security.core.userdetails.User(
        		employee.getEmail(), employee.getPassword(), (Collection<? extends GrantedAuthority>) Set.of(employee.getRoles())
        );
    }
    
}
