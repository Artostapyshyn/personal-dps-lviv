package com.artostapyshyn.personaldpslviv.model.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployeeTest() {

		Employee employee = Employee.builder().firstName("Tom").lastName("Black").department("System development")
				.birthDate("31/01/1994").phoneNumber("+380976541312").email("SystemDeveloper@gmail.com").password("sysadmin")
				.build();

		employeeRepository.save(employee);
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getEmployeeTest() {
		Employee employee = employeeRepository.findByEmail("SystemDeveloper@gmail.com");
		Assertions.assertThat(employee.getEmail()).isEqualTo("SystemDeveloper@gmail.com");
	}

	@Test
	@Order(3)
	public void getListOfEmployeesTest() {
		List<Employee> employees = employeeRepository.findAll();
		Assertions.assertThat(employees.size()).isGreaterThan(0);
	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeeTest() {
		Employee employee = employeeRepository.findByEmail("SystemDeveloper@gmail.com");
		employee.setEmail("SystemDeveloper2@gmail.com");

		Employee employeeUpdated = employeeRepository.save(employee);
		Assertions.assertThat(employeeUpdated.getEmail()).isNotEqualTo("SystemDeveloper@gmail.com");
	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmployeeTest() {
		Employee employee = employeeRepository.findByEmail("SystemDeveloper2@gmail.com");
		employeeRepository.delete(employee);

		Employee nullableEmployee = employeeRepository.findByEmail("SystemDeveloper2@gmail.com");
		Assertions.assertThat(nullableEmployee).isNull();
	}

}
