package com.artostapyshyn.personaldpslviv.model.service;

import java.util.List;
import java.util.Optional;

import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;

public interface FiredEmployeeService {
	
	List<FiredEmployee> getAll();
	
	void saveEmployee(FiredEmployee firedEmployee);

	void deleteById(Long id);
	
    Optional<FiredEmployee> getFiredEmployeeById(Long id);

}