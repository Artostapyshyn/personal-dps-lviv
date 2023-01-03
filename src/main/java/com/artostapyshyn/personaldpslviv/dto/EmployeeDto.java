package com.artostapyshyn.personaldpslviv.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto
{
    private Long id;
    
    @NotEmpty
    private String firstName;
    
    @NotEmpty
    private String lastName;
    
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    
    @NotEmpty(message = "Password should not be empty")
    private String password;
    
    @NotEmpty(message = "Department should not be empty")
    private String department;
	
    @NotEmpty(message = "Birth date should not be empty")
    private String birthDate;
	
    @NotEmpty(message = "Phone number should not be empty")
    private String phoneNumber;
    
}
