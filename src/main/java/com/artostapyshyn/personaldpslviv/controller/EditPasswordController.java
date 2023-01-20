package com.artostapyshyn.personaldpslviv.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.artostapyshyn.personaldpslviv.exceptions.UserNotFoundException;
import com.artostapyshyn.personaldpslviv.model.Mail;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.service.EmailService;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EditPasswordController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmailService emailService;

	@GetMapping("/password")
	public String getPasswordForm() {
		return "password";
	}

	@PostMapping("/password")
	public String processPasswordForm(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = UUID.randomUUID().toString();

		try {
			employeeService.updateResetToken(token, email);
			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

			Mail mail = new Mail();
			mail.setMailFrom("artostapyshyn@gmail.com");
			mail.setMailTo(email);
			mail.setMailSubject("Змніна пароля");
			mail.setMailContent(
					"Для зміни пароля перейдіть за посиланням нижче:\n" + appUrl + "/change?token=" + token);
			emailService.sendEmail(mail);
			model.addAttribute("message", "Повідомлення з інструкцією надіслано. Перевірте електронну скриньку.");

		} catch (UserNotFoundException ex) {
			model.addAttribute("error", ex.getMessage());
		}

		return "password";
	}

	@GetMapping("/change")
	public String getEditPasswordForm(@Param("token") String token, Model model) {
		Employee employee = employeeService.findByResetToken(token);
		model.addAttribute("token", token);

		if (employee == null) {
			model.addAttribute("message", "Виникла помилка, спробуйте ще раз");
			return "message";
		}

		return "edit_password";
	}

	@PostMapping("/change")
	public String processEditPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		Employee employee = employeeService.findByResetToken(token);
		model.addAttribute("title", "Зміна пароля");

		if (employee == null) {
			model.addAttribute("message", "Виникла помилка, спробуйте ще раз");
			return "edit_password";
		} else {
			employeeService.updatePassword(employee, password);

			model.addAttribute("message", "Ви успішно змінили пароль.");
		}

		return "edit_password";
	}

}
