package com.artostapyshyn.personaldpslviv.controller;

import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.entity.Role;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

@AllArgsConstructor
@Controller
public class AuthController {

	private final EmployeeService employeeService;
	private final PasswordEncoder passwordEncoder;

	@GetMapping("/") 
	public String getHomePage() {
		return "home";
	}
 
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}

 	@GetMapping("/profile")
	public String getSuccessPage() {
		return "redirect:/";
	}

	@GetMapping("/registration")
	public String getRegisterPage(Model model) {
		model.addAttribute("user", new Employee());
		model.addAttribute("roles", Role.values());
		return "registration";
	}

	@PostMapping("/registration")
	public String postRegisterPage(@Valid Employee employee, BindingResult result, Model model) {
		if (HelperController.hasErrors(result, model)) {
			model.addAttribute("roles", Role.values());
			return "register";
		}

		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		employeeService.saveAndFlush(employee);
		return "redirect:/";
	}
}