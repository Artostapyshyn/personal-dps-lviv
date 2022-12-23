package com.artostapyshyn.personaldpslviv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;


@Controller
public class AuthController {

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
        return "registration";
    }



}