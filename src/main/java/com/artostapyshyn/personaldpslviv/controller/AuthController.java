package com.artostapyshyn.personaldpslviv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import jakarta.validation.Valid;

 
@Controller
public class AuthController {
 
	private EmployeeService employeeService;
	
	public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

	@GetMapping("/") 
	public String getHomePage() {
		return "home";
	}
 
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}

	@GetMapping("/registration")
	public String getRegisterPage(Model model) {
		EmployeeDto employee = new EmployeeDto();
		model.addAttribute("user", employee);
		return "registration";
	}
	
	@GetMapping("/profile")
	public String getSuccessPage() {
		return "profile";
	}

	@PostMapping("/registration/save")
	public String postRegisterPage(@Valid @ModelAttribute("user") EmployeeDto employee, BindingResult result, Model model){
		 
		if (result.hasErrors()) {
			model.addAttribute("user", employee);
			return "registration";
		}
		
		employeeService.saveAndFlush(employee);
		return "redirect:/registration?success";
	}

}