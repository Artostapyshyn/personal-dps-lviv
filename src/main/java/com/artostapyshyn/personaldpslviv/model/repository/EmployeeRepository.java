package com.artostapyshyn.personaldpslviv.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Optional<Employee> findByEmail(String email);
}