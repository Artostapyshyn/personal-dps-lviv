package com.artostapyshyn.personaldpslviv.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.Mail;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;
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
	public String getSuccessPage(@ModelAttribute("user") @Valid EmployeeDto employeeDto, BindingResult result, Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName();
		
		Employee employee = employeeService.findByEmail(email);
		Long id = employee.getId();
		String firstName = employee.getFirstName();
		String lastName = employee.getLastName();
		String department = employee.getDepartment();
		String phoneNumber = employee.getPhoneNumber();
		String birthDate = employee.getBirthDate();
				
		model.addAttribute("id", id);
		model.addAttribute("firstName", firstName);
		model.addAttribute("lastName", lastName);
		model.addAttribute("department", department);
		model.addAttribute("phoneNumber", phoneNumber);
		model.addAttribute("birthDate", birthDate);
		model.addAttribute("email", email);
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
		
		employee.setEnabled(false);
		employee.setConfirmationToken(UUID.randomUUID().toString());
		employeeService.saveAndFlush(employee);
		
		String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		 
		Mail mail = new Mail();
		mail.setMailFrom("System@gmail.com");
		mail.setMailTo(employee.getEmail());
		mail.setMailSubject("Підтвердження елeктронної пошти");
		mail.setMailContent("Для підтвердження електронної пошти, перейдіть за посиланням нижче та увійдіть у персональний кабінет:\n"
				+ appUrl + "/confirm?token=" + employee.getConfirmationToken());
		emailService.sendEmail(mail);
 
		log.info("Email has been sent to " + employee.getEmail());
		
		return "confirm-email";
	}
	
	@GetMapping("/confirm")
	public String confirmRegistration(Model model, @Param("token") String token) {

		Employee employee = employeeService.findByConfirmationToken(token);
		log.info("Employee was found by confirmation token");
		
	 	if (employee == null) {
			log.warn("Employee is null");
			model.addAttribute("user", employee);
			return "error";
		}
		 
		employee.setEnabled(true);
		
		log.info("Employee was confirmed, permitted to login");
		return "redirect:/registration?success";
	}

}