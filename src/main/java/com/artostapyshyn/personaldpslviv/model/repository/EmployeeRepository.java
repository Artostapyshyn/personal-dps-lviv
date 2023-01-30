package com.artostapyshyn.personaldpslviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.artostapyshyn.personaldpslviv.dto.EmployeeDto;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	Employee findByEmail(String email);
	
	@Query("SELECT e FROM Employee e WHERE e.confirmationToken = ?1")
	Employee findByConfirmationToken(String confirmationToken);
	
	Employee findByResetToken(String token);

}