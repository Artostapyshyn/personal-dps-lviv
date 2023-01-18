package com.artostapyshyn.personaldpslviv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artostapyshyn.personaldpslviv.exceptions.UserIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;
import com.artostapyshyn.personaldpslviv.model.service.FiredEmployeeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/fired")
public class FiredEmployeeController {

	@Autowired
	private FiredEmployeeService firedEmployeeService;

	@GetMapping("/all")
	public String getAll(Model model) {
		model.addAttribute("firedusers", firedEmployeeService.getAll());
		return "fired/fired_all";
	}

	@PostMapping("/delete/{id}")
	public String postDelete(@PathVariable Long id) {
		firedEmployeeService.deleteById(id);

		return "redirect:/fired/all";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("newfireduser", new FiredEmployee());

		return "fired/add";
	}

	@PostMapping("/save")
	public String saveFiredEmployee(@ModelAttribute("newfireduser") FiredEmployee firedEmployee) {
		firedEmployeeService.saveEmployee(firedEmployee);

		return "redirect:/fired/all";
	}

	@GetMapping("/edit/{id}")
	public String getEdit(@PathVariable Long id, Model model) {
		model.addAttribute("fireduser", firedEmployeeService.getFiredEmployeeById(id).orElse(null));

		if (id == null) {
			throw new UserIdIsNotValidException(id);
		}

		return "fired/fired_edit";
	}

	@PostMapping("/edit/{id}")
	public String postEdit(@PathVariable Long id, @ModelAttribute("fireduser") FiredEmployee firedEmployee,
			BindingResult result, Model model) {
		if (HelperController.hasErrors(result, model))
			model.addAttribute("fireduser", firedEmployee);

		firedEmployeeService.saveEmployee(firedEmployee);

		return "redirect:/fired/all";
	}

}
