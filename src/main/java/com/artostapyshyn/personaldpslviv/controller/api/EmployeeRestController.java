package com.artostapyshyn.personaldpslviv.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artostapyshyn.personaldpslviv.exceptions.UserIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.model.entity.Employee;
import com.artostapyshyn.personaldpslviv.model.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/employees")
@SecurityRequirement(name = "personaldpslviv")
@AllArgsConstructor
public class EmployeeRestController {

	private final EmployeeService employeeService;

	@Operation(summary = "Fetch all registered employees stored in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Information about registered employee",
            content = {@Content(mediaType = "application/json", 
            examples = @ExampleObject(
            		value = "{\r\n"
            				+ "  \"result\": \"SUCCESSFUL\",\r\n"
            				+ "  \"body\": {\r\n"
            				+ "    \"id\": 139,\r\n"
            				+ "    \"firstName\": \"A\",\r\n"
            				+ "    \"lastName\": \"B\",\r\n"
            				+ "    \"department\": \"C\",\r\n"
            				+ "    \"birthDate\": \"11-11-1999\",\r\n"
            				+ "    \"phoneNumber\": \"1111-2222-3333\",\r\n"
            				+ "    \"email\": \"email@gmail.com\",\r\n"
            				+ "    \"password\": \"$2a$10$3nBt9KrF5mprZClHb4A8y.u1ShZ/kIB6u\",\r\n"
            				+ "    \"enabled\": true,\r\n"
            				+ "    \"confirmationToken\": \"168b3ad3-20f4-4bbf-969f-a4f96b724279\",\r\n"
            				+ "    \"resetToken\": false\r\n"
            				+ "  }\r\n"
            				+ "}")
             )}),
            @ApiResponse(responseCode = "400",
            description = "Bad request",
            content = {@Content(mediaType = "application/json", 
                    examples = @ExampleObject(
                    		value =  "{\r\n"
                    				+ "  \"result\": \"ERROR\",\r\n"
                    				+ "  \"body\": {\r\n"
                    				+ "    \"message\": \"User with id: 111 is not found\",\r\n"
                    				+ "    \"status\": \"BAD_REQUEST\",\r\n"
                    				+ "    \"timestamp\": \"yyyy-mm-ddThh:mm:ss\"\r\n"
                    				+ "  }\r\n"
                    				+ "}"))})})
			@ApiResponse(responseCode = "500",
			description = "Internal server error",
			content = {@Content(mediaType = "application/json", 
					examples = @ExampleObject(
							value =  "{\r\n"
									+ "  \"result\": \"ERROR\",\r\n"
									+ "  \"body\": {\r\n"
									+ "    \"message\": \"Unknown error occurred\",\r\n"
									+ "    \"status\": \"INTERNAL_SERVER_ERROR\",\r\n"
									+ "    \"timestamp\": \"yyyy-mm-ddThh:mm:ss\"\r\n"
									+ "  }\r\n"
									+ "}"))})
	@GetMapping
	ResponseEntity<Map<String, Object>> all() {
		Map<String, Object> answer = new HashMap<>();
 
		answer.put("result", "SUCCESSFUL");
		answer.put("body", employeeService.findAll());
		log.info("Listing all employees");
		
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}
	
	@Operation(summary = "Get registered employee by given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Information about registered employee",
            		content = {@Content(mediaType = "application/json", 
                    examples = @ExampleObject(
                    		value = "{\r\n"
                    				+ "  \"result\": \"SUCCESSFUL\",\r\n"
                    				+ "  \"body\": {\r\n"
                    				+ "    \"id\": 139,\r\n"
                    				+ "    \"firstName\": \"A\",\r\n"
                    				+ "    \"lastName\": \"B\",\r\n"
                    				+ "    \"department\": \"C\",\r\n"
                    				+ "    \"birthDate\": \"11-11-1999\",\r\n"
                    				+ "    \"phoneNumber\": \"1111-2222-3333\",\r\n"
                    				+ "    \"email\": \"email@gmail.com\",\r\n"
                    				+ "    \"password\": \"$2a$10$3nBt9KrF5mprZClHb4A8y.u1ShZ/kIB6u\",\r\n"
                    				+ "    \"enabled\": true,\r\n"
                    				+ "    \"confirmationToken\": \"168b3ad3-20f4-4bbf-969f-a4f96b724279\",\r\n"
                    				+ "    \"resetToken\": false\r\n"
                    				+ "  }\r\n"
                    				+ "}")
                     )}),
            @ApiResponse(responseCode = "400",
            description = "Bad request",
            content = {@Content(mediaType = "application/json", 
                    examples = @ExampleObject(
                    		value =  "{\r\n"
                    				+ "  \"result\": \"ERROR\",\r\n"
                    				+ "  \"body\": {\r\n"
                    				+ "    \"message\": \"User with id: 111 is not found\",\r\n"
                    				+ "    \"status\": \"BAD_REQUEST\",\r\n"
                    				+ "    \"timestamp\": \"yyyy-mm-ddThh:mm:ss\"\r\n"
                    				+ "  }\r\n"
                    				+ "}"))})})
			@ApiResponse(responseCode = "500",
			description = "Internal server error",
			content = {@Content(mediaType = "application/json", 
					examples = @ExampleObject(
							value =  "{\r\n"
									+ "  \"result\": \"ERROR\",\r\n"
									+ "  \"body\": {\r\n"
									+ "    \"message\": \"Unknown error occurred\",\r\n"
									+ "    \"status\": \"INTERNAL_SERVER_ERROR\",\r\n"
									+ "    \"timestamp\": \"yyyy-mm-ddThh:mm:ss\"\r\n"
									+ "  }\r\n"
									+ "}"))})
	@GetMapping("/{id}")
	ResponseEntity<Map<String, Object>> findById(@PathParam("id") Long id) {
		Map<String, Object> answer = new HashMap<>();
		Optional<Employee> optionalEmployee = employeeService.findById(id);

		if (optionalEmployee.isEmpty()) {
			throw new UserIdIsNotValidException(id);
		}
		answer.put("result", "SUCCESSFUL");
		answer.put("body", optionalEmployee.get());
		log.info("Getting employee by id - " + id);
		
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}

	 @Operation(summary = "Delete registered employee from the database")
	    @ApiResponses(value = {
	            @ApiResponse(responseCode = "200",
	            description = " Employee deleted from the database",
	            content = {@Content(mediaType = "application/json", 
                examples = @ExampleObject(
                		value = "{\r\n"
                				+ "  \"result\": \"SUCCESSFUL\",\r\n"
                				+ "  \"body\": \"Employee with id - 18 has been deleted\"\r\n"
                				+ "}")
                 )}),
	            @ApiResponse(responseCode = "400",
	            description = "Bad request",
	            content = {@Content(mediaType = "application/json", 
	                    examples = @ExampleObject(
	                    		value =  "{\r\n"
	                    				+ "  \"result\": \"ERROR\",\r\n"
	                    				+ "  \"body\": {\r\n"
	                    				+ "    \"message\": \"User with id: 111 is not found\",\r\n"
	                    				+ "    \"status\": \"BAD_REQUEST\",\r\n"
	                    				+ "    \"timestamp\": \"yyyy-mm-ddThh:mm:ss\"\r\n"
	                    				+ "  }\r\n"
	                    				+ "}"))})})
	 			@ApiResponse(responseCode = "500",
	 			description = "Internal server error",
	 			content = {@Content(mediaType = "application/json", 
	 					examples = @ExampleObject(
	 						value =  "{\r\n"
	 								+ "  \"result\": \"ERROR\",\r\n"
	 								+ "  \"body\": {\r\n"
	 								+ "    \"message\": \"Unknown error occurred\",\r\n"
	 								+ "    \"status\": \"INTERNAL_SERVER_ERROR\",\r\n"
	 								+ "    \"timestamp\": \"yyyy-mm-ddThh:mm:ss\"\r\n"
	 								+ "  }\r\n"
	 								+ "}"))})
	@DeleteMapping
	ResponseEntity<Map<String, Object>> delete(@PathParam("id") Long id) {
		Map<String, Object> answer = new HashMap<>();
		if (id != null)
			employeeService.findById(id).orElseThrow(() -> new UserIdIsNotValidException(id));

		employeeService.deleteById(id);
		answer.put("result", "SUCCESSFUL");
		answer.put("body", "Employee with id - " + id + " has been deleted");
		log.info("Employee wth id - " + id + "has been deleted");
		
		return new ResponseEntity<>(answer, HttpStatus.ACCEPTED);
	}

}
