package com.artostapyshyn.personaldpslviv.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fired_employees")
public class FiredEmployee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	@Column(name = "first_name", nullable = false)
    private String firstName;
	
    @Column(name = "last_name", nullable = false)
    private String lastName;
	 
    @Column(name = "department", nullable = false)
    private String department;
	
    @Column(name = "birth_date", nullable = false)
    private String birthDate;
	
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
	
    @Column(name = "firing_reason", nullable = false)
    private String firingReason;
    
    @Column(name = "firing_date", nullable = false)
    private String firingDate;
	
}
