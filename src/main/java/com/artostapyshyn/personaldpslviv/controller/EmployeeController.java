package com.artostapyshyn.personaldpslviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.exceptions.UserNotFoundException;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/all")
	public String getAll(Model model) {
		model.addAttribute("users", employeeService.findAll());
		return "all";
	}

	@GetMapping("/edit/{email}")
	public String getEdit(@PathVariable String email, Model model) {
		model.addAttribute("user", employeeService.findByEmail(email));
		 
			if(email == null) {
				throw new UserNotFoundException(email);
			}
			
		return "edit";
	}

	@PostMapping("/edit/{email}")
	public String postEdit(@PathVariable String email, @ModelAttribute("user") EmployeeDto empDto, BindingResult result, Model model) {
	    if (HelperController.hasErrors(result, model))
	     	model.addAttribute("user", empDto);
		
		String currentEmployee = SecurityContextHolder.getContext().getAuthentication().getName();
		
        boolean needLogout = false;
        needLogout = empDto.getEmail().equals(currentEmployee);
        
        employeeService.saveAndFlush(empDto);
        
		 if (needLogout)
	            return "redirect:/logout";
	        else
	            return "redirect:/all";
	}

	@PostMapping("/delete/{id}")
	public String postDelete(@PathVariable Long id) {
        employeeService.deleteById(id);
            
        return "redirect:/all";
	}

}