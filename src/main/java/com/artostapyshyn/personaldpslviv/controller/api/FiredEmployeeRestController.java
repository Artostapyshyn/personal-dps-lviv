package com.artostapyshyn.personaldpslviv.controller.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artostapyshyn.personaldpslviv.exceptions.EmployeeIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.model.entity.FiredEmployee;
import com.artostapyshyn.personaldpslviv.model.service.FiredEmployeeService;

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
@RequestMapping("api/employees/fired")
@SecurityRequirement(name = "personaldpslviv")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class FiredEmployeeRestController {

	private final FiredEmployeeService firedEmployeeService;

	@Operation(summary = "Fetch all fired employees stored in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Information about fired employees",
            		content = {@Content(mediaType = "application/json", 
                    examples = @ExampleObject(
                    		value = "{\r\n"
                    				+ "  \"result\": \"SUCCESSFUL\",\r\n"
                    				+ "  \"body\": [\r\n"
                    				+ "    {\r\n"
                    				+ "      \"id\": 1,\r\n"
                    				+ "      \"firstName\": \"A\",\r\n"
                    				+ "      \"lastName\": \"B\",\r\n"
                    				+ "      \"department\": \"C\",\r\n"
                    				+ "      \"birthDate\": \"11-11-1999\",\r\n"
                    				+ "      \"phoneNumber\": \"1111-2222-3333\",\r\n"
                    				+ "      \"firingReason\": \"D\",\r\n"
                    				+ "      \"firingDate\": \"10-10-2022\"\r\n"
                    				+ "    }\r\n"
                    				+ "  ]\r\n"
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
		answer.put("body", firedEmployeeService.getAll());
		log.info("Listing all fired employees");
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}
	
	@Operation(summary = "Get fired employee by given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Information about registered employee",
            		content = {@Content(mediaType = "application/json", 
                    examples = @ExampleObject(
                    		value = "{\r\n"
                    				+ "  \"result\": \"SUCCESSFUL\",\r\n"
                    				+ "  \"body\": [\r\n"
                    				+ "    {\r\n"
                    				+ "      \"id\": 1,\r\n"
                    				+ "      \"firstName\": \"A\",\r\n"
                    				+ "      \"lastName\": \"B\",\r\n"
                    				+ "      \"department\": \"C\",\r\n"
                    				+ "      \"birthDate\": \"11-11-1999\",\r\n"
                    				+ "      \"phoneNumber\": \"1111-2222-3333\",\r\n"
                    				+ "      \"firingReason\": \"D\",\r\n"
                    				+ "      \"firingDate\": \"10-10-2022\"\r\n"
                    				+ "    }\r\n"
                    				+ "  ]\r\n"
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
		Optional<FiredEmployee> optionalEmployee = firedEmployeeService.getFiredEmployeeById(id);

		if (optionalEmployee.isEmpty()) {
			throw new EmployeeIdIsNotValidException(id);
		}
		answer.put("result", "SUCCESSFUL");
		answer.put("body", optionalEmployee );
		log.info("Getting fired employee by id - " + id);
		
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}

	@Operation(summary = "Add fired employee in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Information about registered employee",
            		content = {@Content(mediaType = "application/json", 
                    examples = @ExampleObject(
                    		value = "{\r\n"
                    				+ "  \"result\": \"SUCCESSFUL\",\r\n"
                    				+ "  \"body\": {\r\n"
                    				+ "    \"id\": 0,\r\n"
                    				+ "    \"firstName\": \"A\",\r\n"
                    				+ "    \"lastName\": \"B\",\r\n"
                    				+ "    \"department\": \"C\",\r\n"
                    				+ "    \"birthDate\": \"11-11-1999\",\r\n"
                    				+ "    \"phoneNumber\": \"1111-2222-3333\",\r\n"
                    				+ "    \"firingReason\": \"D\",\r\n"
                    				+ "    \"firingDate\": \"10-10-2022\"\r\n"
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
	@PostMapping
	ResponseEntity<Map<String, Object>> add(@RequestBody FiredEmployee firedEmployee) {
		Map<String, Object> answer = new HashMap<>();

		FiredEmployee employee = firedEmployeeService.saveEmployee(firedEmployee);
		answer.put("result", "SUCCESSFUL");
		answer.put("body", employee);
		log.info("Fired employee record added");
		
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}

	@Operation(summary = "Update fired employee information stored in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Information about registered employee",
            		content = {@Content(mediaType = "application/json", 
                    examples = @ExampleObject(
                    		value = "{\r\n" + "  \"result\": \"SUCCESSFUL\",\r\n"
                    				+ "  \"body\": [\r\n"
                    				+ "    {\r\n"
                    				+ "      \"id\": 0,\r\n"
                    				+ "      \"firstName\": \"A\",\r\n"
                    				+ "      \"lastName\": \"B\",\r\n"
                    				+ "      \"department\": \"C\",\r\n"
                    				+ "      \"birthDate\": \"11-11-1999\",\r\n"
                    				+ "      \"phoneNumber\": \"1111-2222-3333\",\r\n"
                    				+ "      \"firingReason\": \"D\",\r\n"
                    				+ "      \"firingDate\": \"10-10-2022\"\r\n"
                    				+ "    }\r\n"
                    				+ "  ]\r\n"
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
	@PutMapping
	ResponseEntity<Map<String, Object>> updateFiredEmployeeInfo(@RequestBody FiredEmployee firedEmployee,
			@PathParam("id") Long id) {
		Map<String, Object> answer = new HashMap<>();
		firedEmployee.setId(id);
		FiredEmployee existingEmployee = firedEmployeeService.getFiredEmployeeById(firedEmployee.getId()).get();

		update(firedEmployee, existingEmployee);

		FiredEmployee updatedEmployee = firedEmployeeService.saveEmployee(existingEmployee);
		answer.put("result", "SUCCESSFUL");
		answer.put("body", updatedEmployee);
		log.info("Fired employee record updated");
		
		return new ResponseEntity<>(answer, HttpStatus.OK);

	}

	void update(FiredEmployee firedEmployee, FiredEmployee existingEmployee) {
		existingEmployee.setFirstName(firedEmployee.getFirstName());
		existingEmployee.setLastName(firedEmployee.getLastName());
		existingEmployee.setBirthDate(firedEmployee.getBirthDate());
		existingEmployee.setFiringReason(firedEmployee.getFiringReason());
		existingEmployee.setFiringDate(firedEmployee.getFiringDate());
		existingEmployee.setPhoneNumber(firedEmployee.getPhoneNumber());
		existingEmployee.setDepartment(firedEmployee.getDepartment());
	}

	@Operation(summary = "Delete registered employee from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "Information about registered employee",
            		content = {@Content(mediaType = "application/json", 
                    examples = @ExampleObject(
                    		value = "{\r\n"
                    				+ "  \"result\": \"SUCCESSFUL\",\r\n"
                    				+ "  \"body\": \"Employee with id - 1 has been deleted\"\r\n"
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
	ResponseEntity<Map<String, Object>> delete(@PathParam("id") Long id) throws EmployeeIdIsNotValidException {
		Map<String, Object> answer = new HashMap<>();
		if (id != null)
			firedEmployeeService.getFiredEmployeeById(id).orElseThrow(() -> new EmployeeIdIsNotValidException(id));

		firedEmployeeService.deleteById(id);
		answer.put("result", "SUCCESSFUL");
		answer.put("body", "Employee with id - " + id + " has been deleted");
		log.info("Fired employee wth id - " + id + "has been deleted");
		
		return new ResponseEntity<>(answer, HttpStatus.ACCEPTED);
	}

}
