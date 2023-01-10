package com.artostapyshyn.personaldpslviv.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;
import com.artostapyshyn.personaldpslviv.model.repository.FiredEmployeeRepository;
import com.artostapyshyn.personaldpslviv.model.service.impl.FiredEmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FiredEmployeeServiceTest {

	@Mock
	private FiredEmployeeRepository firedEmployeeRepository;

	@InjectMocks
	private FiredEmployeeServiceImpl firedEmployeeService;

	@Test
	void saveFiredEmployee() {
		FiredEmployee firedEmployee = FiredEmployee.builder().id(1L).firstName("Ben").lastName("White")
				.department("System development").birthDate("12/11/1991").phoneNumber("+380978671312")
				.firingReason("End of employment contract").firingDate("6/01/2023").build();

		firedEmployeeService.saveEmployee(firedEmployee);
		assertThat(firedEmployee.getFirstName()).isEqualTo("Ben");
	}

	@Test
	void getFiredEmployees() {
		FiredEmployee firedEmployee = FiredEmployee.builder().id(1L).firstName("John").lastName("Watson")
				.department("System development").birthDate("02/12/1981").phoneNumber("+380979981312")
				.firingReason("End of employment contract").firingDate("02/11/2022").build();

		List<FiredEmployee> list = new ArrayList<>();
		list.add(firedEmployee);

		when(firedEmployeeRepository.findAll()).thenReturn(list);

		List<FiredEmployee> firedEmployees = firedEmployeeService.getAll();
		assertThat(firedEmployees.size()).isGreaterThan(0);
	}

	@Test
	void getFiredEmployeeById() {
		FiredEmployee firedEmployee = FiredEmployee.builder().id(1L).firstName("John").lastName("Watson")
				.department("System development").birthDate("02/12/1981").phoneNumber("+380979981312")
				.firingReason("End of employment contract").firingDate("02/11/2022").build();
		when(firedEmployeeRepository.findById(1L)).thenReturn(Optional.of(firedEmployee));
		Optional<FiredEmployee> existingEmployee = firedEmployeeService.getFiredEmployeeById(firedEmployee.getId());
		assertThat(existingEmployee.get()).isNotEqualTo(null);
	}

	@Test
	void updateFiredEmployee() {
		FiredEmployee firedEmployee = FiredEmployee.builder().id(1L).firstName("Sarah").lastName("Banks")
				.department("Human recources").birthDate("31/12/1987").phoneNumber("+380966681312")
				.firingReason("End of employment contract").firingDate("02/11/2022").build();
		firedEmployeeRepository.findById(1L);

		firedEmployee.setLastName("Hanks");
		firedEmployeeService.saveEmployee(firedEmployee);

		assertThat(firedEmployee.getLastName()).isEqualTo("Hanks");
	}

	@Test
	void deleteFiredEmployee() {
		firedEmployeeRepository.findById(1L);

		firedEmployeeService.deleteById(1L);

		Optional<FiredEmployee> nullableEmployee = firedEmployeeRepository.findById(1L);
		assertThat(nullableEmployee).isEmpty();
	}
}
