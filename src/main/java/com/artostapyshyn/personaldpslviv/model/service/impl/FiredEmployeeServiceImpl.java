package com.artostapyshyn.personaldpslviv.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artostapyshyn.personaldpslviv.exceptions.EmployeeIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;
import com.artostapyshyn.personaldpslviv.model.repository.FiredEmployeeRepository;
import com.artostapyshyn.personaldpslviv.model.service.FiredEmployeeService;

import jakarta.transaction.Transactional;


@Service
public class FiredEmployeeServiceImpl implements FiredEmployeeService {
	
	@Autowired
    private FiredEmployeeRepository firedEmployeeRepository;
    
    @Override
    public FiredEmployee saveEmployee(FiredEmployee firedEmployee) {
    	return firedEmployeeRepository.save(firedEmployee);
    }

    @Override
    public Optional<FiredEmployee> getFiredEmployeeById(Long id) {
        return Optional.ofNullable(firedEmployeeRepository.findById(id).orElseThrow(() -> new EmployeeIdIsNotValidException(id)));
    }
    
    @Override
    @Transactional
    public void deleteById(Long id) {
    	firedEmployeeRepository.deleteById(id);
    }

    @Override
    public List<FiredEmployee> getAll() {
         return firedEmployeeRepository.findAll();
    }
}
