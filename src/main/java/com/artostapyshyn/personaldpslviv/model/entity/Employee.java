package com.artostapyshyn.personaldpslviv.model.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@ToString
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "department")
    private String department;
    
    @Column(name = "birth_date")
    private String birthDate;
    
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Employee() {
        this.role = Role.ADMIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee emmployee = (Employee) o;
        return id != null && Objects.equals(id, emmployee.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
