package com.artostapyshyn.personaldpslviv.controller.api;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.artostapyshyn.personaldpslviv.exceptions.UserIdIsNotValidException;
import com.artostapyshyn.personaldpslviv.exceptions.UserNotFoundException;

import jakarta.security.auth.message.AuthException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice("com.artostapyshyn.personaldpslviv.controller.api")
public class ExceptionRestController {

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(NOT_FOUND)
	public ResponseEntity<Map<String, Object>> userNotFoundException(UserNotFoundException userNotFoundException) {
		Map<String, Object> answer = new HashMap<>();
		log.error("Couldn't find user with given email", userNotFoundException);
		answer.put("result", "ERROR");
		answer.put("body", ErrorResponse.builder()
				.message(userNotFoundException.getMessage())
				.status(NOT_FOUND)
				.timestamp(now())
				.build());

		return new ResponseEntity<>(answer, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserIdIsNotValidException.class)
	@ResponseStatus(BAD_REQUEST)
	public ResponseEntity<Map<String, Object>> userIdIsNotValidException(
			UserIdIsNotValidException userIdIsNotValidException) {
		Map<String, Object> answer = new HashMap<>();
		log.error("Couldn't find user with given id", userIdIsNotValidException);
		answer.put("result", "ERROR");
		answer.put("body", ErrorResponse.builder()
				.message(userIdIsNotValidException.getMessage())
				.status(BAD_REQUEST)
				.timestamp(now())
				.build());

		return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthException.class)
	@ResponseStatus(UNAUTHORIZED)
	public ResponseEntity<Map<String, Object>> authException(AuthException authException) {
		Map<String, Object> answer = new HashMap<>();
		log.error("User is unathorized", authException);
		answer.put("result", "ERROR");
		answer.put("body", ErrorResponse.builder()
				.message(authException.getMessage())
				.status(UNAUTHORIZED)
				.timestamp(now())
				.build());

		return new ResponseEntity<>(answer, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Map<String, Object>>	handleIllegalArgumentExceptions(IllegalArgumentException illegalArgumentException) {
		Map<String, Object> answer = new HashMap<>();
		log.error("Not found", illegalArgumentException);
		answer.put("result", "ERROR");
		answer.put("body", ErrorResponse.builder()
				.message(illegalArgumentException.getMessage())
				.status(NOT_FOUND)
				.timestamp(now())
				.build());

		return new ResponseEntity<>(answer, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Map<String, Object>>	handleAllExceptions(Exception exception) {
		Map<String, Object> answer = new HashMap<>();
		log.error("Unknown error occurred", exception);
		answer.put("result", "ERROR");
		answer.put("body", ErrorResponse.builder()
				.message(exception.getMessage())
				.status(INTERNAL_SERVER_ERROR)
				.timestamp(now())
				.build());

		return new ResponseEntity<>(answer, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
}
