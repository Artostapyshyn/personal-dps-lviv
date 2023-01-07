package com.artostapyshyn.personaldpslviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;

@Repository
public interface FiredEmployeeRepository extends JpaRepository<FiredEmployee, Long>{

}
