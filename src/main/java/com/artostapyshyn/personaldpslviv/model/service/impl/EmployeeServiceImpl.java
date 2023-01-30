package com.artostapyshyn.personaldpslviv.model.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.exceptions.EmployeeIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.exceptions.EmployeeNotFoundException;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.entity.Role;
import com.artostapyshyn.personaldpslviv.model.repository.EmployeeRepository;
import com.artostapyshyn.personaldpslviv.model.repository.RoleRepository;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Employee saveAndFlush(EmployeeDto employeeDto) {
		Employee employee = findByEmail(employeeDto.getEmail());

		if (employee == null) {
			employee = new Employee();
			employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));

			Role role = roleRepository.findByName("ROLE_USER");

			if (role == null) {
				role = checkRoleExist();
			}

			employee.setRoles(Arrays.asList(role));
		}
		employee.setId(employeeDto.getId());
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setBirthDate(employeeDto.getBirthDate());
		employee.setPhoneNumber(employeeDto.getPhoneNumber());
		employee.setDepartment(employeeDto.getDepartment());
		employee.setEmail(employeeDto.getEmail());
		employee.setEnabled(employeeDto.getEnabled());
		employee.setConfirmationToken(employeeDto.getConfirmationToken());
		return employeeRepository.save(employee);
	}

	@Override
	public Employee findByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}

	@Override
	public void deleteById(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<EmployeeDto> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> convertEntityToDto(employee)).toList();
	}

	private EmployeeDto convertEntityToDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(employee.getId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setPhoneNumber(employee.getPhoneNumber());
		employeeDto.setBirthDate(employee.getBirthDate());
		employeeDto.setDepartment(employee.getDepartment());
		employeeDto.setEmail(employee.getEmail());
		employeeDto.setPassword(employee.getPassword());
		employeeDto.setEnabled(employee.getEnabled());
		employeeDto.setConfirmationToken(employee.getConfirmationToken());
		return employeeDto;
	}

	private Role checkRoleExist() {
		Role role = new Role();
		role.setName("ROLE_USER");
		return roleRepository.save(role);
	}

	@Override
	public Employee findByConfirmationToken(String confirmationToken) {
		return employeeRepository.findByConfirmationToken(confirmationToken);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new EmployeeIdIsNotValidException(id)));
	}

	@Override
	public void updateResetToken(String token, String email) {
		Employee employee = employeeRepository.findByEmail(email);
		if (employee != null) {
			employee.setResetToken(token);
			employeeRepository.save(employee);
		} else {
			throw new EmployeeNotFoundException(email);
		}
	}

	@Override
	public void updatePassword(Employee employee, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		employee.setPassword(encodedPassword);

		employee.setResetToken(null);
		employeeRepository.save(employee);
	}

	@Override
	public Employee findByResetToken(String token) {
		return employeeRepository.findByResetToken(token);
	}

}