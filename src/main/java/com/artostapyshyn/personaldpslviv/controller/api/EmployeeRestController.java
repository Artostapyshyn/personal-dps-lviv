package com.artostapyshyn.personaldpslviv.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/employees")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class EmployeeRestController {

	private final EmployeeService employeeService;

	@GetMapping
	ResponseEntity<Map<String, Object>> all(@PathParam("id") Long id) {
		if (id != null)
			findById(id);

		Map<String, Object> answer = new HashMap<>();
		answer.put("result", "SUCCESSFUL");
		answer.put("body", employeeService.findAll());

		return new ResponseEntity<>(answer, HttpStatus.OK);
	}

	void findById(Long id) {
		Map<String, Object> answer = new HashMap<>();
		Optional<Employee> optionalEmployee = employeeService.findById(id);

		if (optionalEmployee.isEmpty()) {
			answer.put("result", "Error");
			answer.put("body", new RuntimeException("Couldn't find user with id - " + id));
		} else {
			answer.put("result", "SUCCESSFUL");
			answer.put("body", optionalEmployee.get());
		}
	}

	@DeleteMapping
	ResponseEntity<Map<String, Object>> delete(@PathParam("id") Long id) {
		Map<String, Object> answer = new HashMap<>();
		Optional<Employee> optionalEmployee = Optional.empty();
		if (id != null)
			optionalEmployee = employeeService.findById(id);

		if (optionalEmployee.isEmpty()) {
			answer.put("result", "Error");
			answer.put("body", new RuntimeException("Couldn't find user with id - " + id));
			return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
		} else {
			employeeService.deleteById(id);
			answer.put("result", "SUCCESSFUL");
			answer.put("body", "User " + id + " has been deleted");
			return new ResponseEntity<>(answer, HttpStatus.ACCEPTED);
		}
	}

}
