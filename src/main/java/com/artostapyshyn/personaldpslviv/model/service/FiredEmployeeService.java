package com.artostapyshyn.personaldpslviv.model.service;

import java.util.List;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;

public interface FiredEmployeeService {
	
	List<FiredEmployee> getAll();
	
	void saveEmployee(FiredEmployee firedEmployee);

	void deleteById(Long id);
	
    Employee getFiredEmployeeById(Long id);

}