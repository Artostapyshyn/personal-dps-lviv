package com.artostapyshyn.personaldpslviv.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artostapyshyn.personaldpslviv.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
