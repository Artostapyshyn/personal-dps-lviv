package com.artostapyshyn.personaldpslviv.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.Mail;
import com.artostapyshyn.personaldpslviv.model.service.EmailService;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class AuthController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmailService emailService;

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
	public String postRegisterPage(@Valid @ModelAttribute("user") EmployeeDto employee, BindingResult result,
			Model model, HttpServletRequest request) {

		if (result.hasErrors()) {
			model.addAttribute("user", employee);
			log.warn("Error occured while registrating user");
			return "registration";
		}

		employee.setConfirmationToken(UUID.randomUUID().toString());
		
		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		 
		Mail mail = new Mail();
		mail.setMailFrom("System@gmail.com");
		mail.setMailTo(employee.getEmail());
		mail.setMailSubject("Email Confirmation");
		mail.setMailContent("To confirm you email, please click the link below and login:\n"
				+ appUrl + "/confirm?token=" + employee.getConfirmationToken());
		emailService.sendEmail(mail);
 
		log.info("Email has been sent to" + employee.getEmail());
		
		employeeService.saveAndFlush(employee);
		return "confirm";
	}
	
	@GetMapping("/confirm")
	public String confirmRegistration(Model model, @RequestParam String token, @ModelAttribute("user") EmployeeDto employee) {

		employee = employeeService.findByConfirmationToken(token);
		log.info("Employee was found by confirmation token");
		
		if (employee == null) {
			log.warn("Employee is null");
			model.addAttribute("user", employee);
			return "error";
		}
		employee.setEnabled(true);
		employeeService.saveAndFlush(employee);
		
		log.info("User was confirmed, permitted to login");
		return "redirect:/registration?success";
	}

}