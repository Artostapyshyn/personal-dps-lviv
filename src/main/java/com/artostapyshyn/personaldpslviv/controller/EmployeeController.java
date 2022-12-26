package com.artostapyshyn.personaldpslviv.controller;

import javax.validation.Valid;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artostapyshyn.personaldpslviv.exceptions.UserNotFoundException;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.entity.Role;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	private final EmployeeService employeeService;
	private final PasswordEncoder passwordEncoder;

	public EmployeeController(EmployeeService employeeService, PasswordEncoder passwordEncoder) {
		this.employeeService = employeeService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("all")
	public String getAll(Model model) {
		model.addAttribute("users", employeeService.findAll());
		return "employees/all";
	}

	@GetMapping("edit/{id}")
	public String getEdit(@PathVariable String id, Model model) {
		model.addAttribute("user", employeeService.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
		model.addAttribute("roles", Role.values());
		return "employees/edit";
	}

	@PostMapping("edit/{id}")
	public String postEdit(@PathVariable String id, @Valid Employee employee, BindingResult result, Model model) {
		if (HelperController.hasErrors(result, model)) {
			model.addAttribute("roles", Role.values());
			return "employees/edit";
		}

		employeeService.findById(employee.getEmail());
		employeeService.saveAndFlush(employee);

        return "redirect:/employees/all";
	}

	@PostMapping("delete/{id}")
	public String postDelete(@PathVariable String id) {
		employeeService.deleteById(id);
		return "redirect:/employees/all";
	}

}
