package com.artostapyshyn.personaldpslviv.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artostapyshyn.personaldpslviv.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	@Query("SELECT u FROM Employee u WHERE u.department = :email")
	Optional<Employee> findByEmail(@Param("email") String email);
}