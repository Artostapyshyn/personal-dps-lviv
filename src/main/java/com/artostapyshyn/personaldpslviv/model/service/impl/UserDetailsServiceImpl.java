package com.artostapyshyn.personaldpslviv.model.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.entity.Role;
import com.artostapyshyn.personaldpslviv.model.repository.EmployeeRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;
 
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmail(email);
		if (employee != null) {
			return new org.springframework.security.core.userdetails.User
					(employee.getEmail(), employee.getPassword(),
					mapRolesToAuthorities(employee.getRoles()));
		} else {
			throw new UsernameNotFoundException("User doesn't exists");
		}
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> list) {
		List<SimpleGrantedAuthority> mapRoles = list.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).toList();
		return mapRoles;
	}
}
