package com.artostapyshyn.personaldpslviv.dto;

import com.artostapyshyn.personaldpslviv.validation.UniqueEmail;

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
    
    @UniqueEmail
    @NotEmpty(message = "Email should not be empty")
    private String email;
   
    @NotEmpty(message = "Password should not be empty")
    private String password;
    
    @NotEmpty(message = "Department should not be empty")
    private String department;
	
    @NotEmpty(message = "Birth date should not be empty")
    private String birthDate;
	
    @NotEmpty(message = "Phone number should not be empty")
    private String phoneNumber;
    
	private Boolean enabled;
	
	private String confirmationToken;
	
	private String resetToken;
    
}
