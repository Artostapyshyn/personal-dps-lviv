package com.artostapyshyn.personaldpslviv.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artostapyshyn.personaldpslviv.exceptions.UserIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;
import com.artostapyshyn.personaldpslviv.model.service.FiredEmployeeService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/employees/fired")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class FiredEmployeeRestController {

	private final FiredEmployeeService firedEmployeeService;

	@GetMapping
	ResponseEntity<Map<String, Object>> all() {

		Map<String, Object> answer = new HashMap<>();
		answer.put("result", "SUCCESSFUL");
		answer.put("body", firedEmployeeService.getAll());

		return new ResponseEntity<>(answer, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<Map<String, Object>> findById(@PathParam("id") Long id) {
		Map<String, Object> answer = new HashMap<>();
		Optional<FiredEmployee> optionalEmployee = firedEmployeeService.getFiredEmployeeById(id);

		answer.put("result", "SUCCESSFUL");
		answer.put("body", optionalEmployee );

		return new ResponseEntity<>(answer, HttpStatus.OK);
	}

	@PostMapping
	ResponseEntity<Map<String, Object>> add(@RequestBody FiredEmployee firedEmployee) {
		Map<String, Object> answer = new HashMap<>();

		FiredEmployee employee = firedEmployeeService.saveEmployee(firedEmployee);
		answer.put("result", "SUCCESSFUL");
		answer.put("body", employee);

		return new ResponseEntity<>(answer, HttpStatus.OK);
	}

	@PutMapping
	ResponseEntity<Map<String, Object>> updateFiredEmployeeInfo(@RequestBody FiredEmployee firedEmployee,
			@PathParam("id") Long id) {
		Map<String, Object> answer = new HashMap<>();
		firedEmployee.setId(id);
		FiredEmployee existingEmployee = firedEmployeeService.getFiredEmployeeById(firedEmployee.getId()).get();

		update(firedEmployee, existingEmployee);

		FiredEmployee updatedEmployee = firedEmployeeService.saveEmployee(existingEmployee);
		answer.put("result", "SUCCESSFUL");
		answer.put("body", updatedEmployee);
		return new ResponseEntity<>(answer, HttpStatus.OK);

	}

	void update(FiredEmployee firedEmployee, FiredEmployee existingEmployee) {
		existingEmployee.setFirstName(firedEmployee.getFirstName());
		existingEmployee.setLastName(firedEmployee.getLastName());
		existingEmployee.setBirthDate(firedEmployee.getBirthDate());
		existingEmployee.setFiringReason(firedEmployee.getFiringReason());
		existingEmployee.setFiringDate(firedEmployee.getFiringDate());
		existingEmployee.setPhoneNumber(firedEmployee.getPhoneNumber());
		existingEmployee.setDepartment(firedEmployee.getDepartment());
	}

	@DeleteMapping
	ResponseEntity<Map<String, Object>> delete(@PathParam("id") Long id) throws UserIdIsNotValidException {
		Map<String, Object> answer = new HashMap<>();
		if (id != null)
			firedEmployeeService.getFiredEmployeeById(id);

		firedEmployeeService.deleteById(id);
		answer.put("result", "SUCCESSFUL");
		answer.put("body", "User " + id + " has been deleted");
		return new ResponseEntity<>(answer, HttpStatus.ACCEPTED);
	}

}
