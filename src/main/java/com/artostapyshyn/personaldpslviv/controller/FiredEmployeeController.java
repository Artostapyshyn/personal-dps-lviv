package com.artostapyshyn.personaldpslviv.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.artostapyshyn.personaldpslviv.excel.ExcelSaver;
import com.artostapyshyn.personaldpslviv.exceptions.UserIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;
import com.artostapyshyn.personaldpslviv.model.service.FiredEmployeeService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/fired")
public class FiredEmployeeController {

	@Autowired
	private FiredEmployeeService firedEmployeeService;

	@GetMapping("/all")
	public String getAll(Model model) {
		model.addAttribute("firedusers", firedEmployeeService.getAll());
		log.info("Listing all fired employees");
		return "fired/fired_all";
	}

	@PostMapping("/delete/{id}")
	public String postDelete(@PathVariable Long id) {
		firedEmployeeService.deleteById(id);
		log.info("Fired employee with id" + id + " is deleted");    
		return "redirect:/fired/all";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("newfireduser", new FiredEmployee());
		log.info("Fired employee added");
		
		return "fired/add";
	}

	@PostMapping("/save")
	public String saveFiredEmployee(@ModelAttribute("newfireduser") FiredEmployee firedEmployee) {
		firedEmployeeService.saveEmployee(firedEmployee);
		log.info("Fired employee with id -" + firedEmployee.getId() + " was saved");
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
	public String postEdit(@PathVariable Long id, @ModelAttribute("fireduser") FiredEmployee firedEmployee, Model model) {

		firedEmployeeService.saveEmployee(firedEmployee);
		log.info("Fired employee details was edited and saved successfully");
		return "redirect:/fired/all";
	}
	
	@GetMapping("/export")
    public void exportDataIntoExcelFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=FiredEmployees-" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List <FiredEmployee> listOfFiredEmployees = firedEmployeeService.getAll();
        ExcelSaver saver = new ExcelSaver(listOfFiredEmployees);
        saver.createExcelFile(response);
        
        log.info("Excel file was created, filename - " + "FiredEmployees- " + currentDateTime + ".xlsx");
    }

}
