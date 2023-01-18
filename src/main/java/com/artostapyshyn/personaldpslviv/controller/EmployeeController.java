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
import org.springframework.web.bind.annotation.RequestMapping;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.exceptions.UserIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/registered")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/all")
	public String getAll(Model model) {
		model.addAttribute("users", employeeService.findAll());
		return "registered/all";
	}

	@GetMapping("/edit/{id}")
	public String getEdit(@PathVariable Long id, Model model) {
		employeeService.findById(id).ifPresent(o -> model.addAttribute("user", o));
		 
			if(id == null) {
				throw new UserIdIsNotValidException(id);
			}
			
		return "registered/edit";
	}

	@PostMapping("/edit/{id}")
	public String postEdit(@PathVariable Long id, @ModelAttribute("user") EmployeeDto empDto, BindingResult result, Model model) {
	    if (HelperController.hasErrors(result, model))
	     	model.addAttribute("user", empDto);
		
		String currentEmployee = SecurityContextHolder.getContext().getAuthentication().getName();
		
        boolean needLogout = false;
        needLogout = empDto.getEmail().equals(currentEmployee);
        
        employeeService.saveAndFlush(empDto);
        
		 if (needLogout)
	            return "redirect:/logout";
	        else
	            return "redirect:/registered/all";
	}

	@PostMapping("/delete/{id}")
	public String postDelete(@PathVariable Long id) {
        employeeService.deleteById(id);
            
        return "redirect:/registered/all";
	}

}