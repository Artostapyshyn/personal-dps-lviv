package com.artostapyshyn.personaldpslviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.exceptions.EmployeeIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/registered")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/all")
	public String getAll(Model model) {
		model.addAttribute("users", employeeService.findAll());
		log.info("Listing all employees");
		return "registered/all";
	}

	@GetMapping("/edit/{id}")
	public String getEdit(@PathVariable Long id, Model model) {
		employeeService.findById(id).ifPresent(o -> model.addAttribute("user", o));

		if (id == null) {
			throw new EmployeeIdIsNotValidException(id);
		}
		
		return "registered/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String postEdit(@PathVariable Long id, @ModelAttribute("user") EmployeeDto empDto, Model model) {
		employeeService.saveAndFlush(empDto);

		log.info("Information was edited");
		return "redirect:/registered/all";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PostMapping("/delete/{id}")
	public String postDelete(@PathVariable Long id, @ModelAttribute("user") EmployeeDto empDto) {
		if (id == null) {
			throw new EmployeeIdIsNotValidException(id);
		}
		
		Authentication loggedInEmployee = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInEmployee.getName();

		Employee employee = employeeService.findByEmail(email);
		Long currentEmployee = employee.getId();

		employeeService.deleteById(id);
		log.info("Employee with id - " + id + " is deleted");
		
		boolean needLogout = false;
		needLogout = empDto.getId().equals(currentEmployee);
		
		if (needLogout) {
			log.info("Employee deleted own details, logging out...");
			return "redirect:/logout";
		} else {
			log.info("Employee was deleted");
			return "redirect:/registered/all";
		}
	}

}