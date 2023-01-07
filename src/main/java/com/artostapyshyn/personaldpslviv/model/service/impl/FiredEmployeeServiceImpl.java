package com.artostapyshyn.personaldpslviv.model.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;
import com.artostapyshyn.personaldpslviv.model.repository.FiredEmployeeRepository;
import com.artostapyshyn.personaldpslviv.model.service.FiredEmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FiredEmployeeServiceImpl implements FiredEmployeeService {
	
    private FiredEmployeeRepository firedEmployeeRepository;
    
    @Override
    public void saveEmployee(FiredEmployee firedEmployee) {
    	firedEmployeeRepository.save(firedEmployee);
    }

    @Override
    public Optional<FiredEmployee> getFiredEmployeeById(Long id) {
        return firedEmployeeRepository.findById(id);
    }
    
    @Override
    public void deleteById(Long id) {
    	firedEmployeeRepository.deleteById(id);
    }

    @Override
    public List<FiredEmployee> getAll() {
         return firedEmployeeRepository.findAll();
    }
}
