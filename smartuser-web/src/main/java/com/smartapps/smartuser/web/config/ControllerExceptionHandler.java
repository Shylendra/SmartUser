package com.smartapps.smartuser.web.config;

import static com.smartapps.smartlib.util.SmartLibraryUtil.createErrorResponse;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.smartapps.smartlib.exception.ResourceNotFoundException;
import com.smartapps.smartlib.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		log.info("ControllerExceptionHandler::resourceNotFoundException()::ResourceNotFoundException");
		return createErrorResponse(HttpStatus.NOT_FOUND, asList(ex.getMessage()), new HttpHeaders());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, WebRequest request) {
		log.info("ControllerExceptionHandler::constraintViolationException()::ConstraintViolationException");
		return createErrorResponse(HttpStatus.BAD_REQUEST, asList(ex.getMessage()), new HttpHeaders());
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Object> unauthorizedException(UnauthorizedException ex, WebRequest request) {
		log.info("ControllerExceptionHandler::unauthorizedException()::UnauthorizedException");
		return createErrorResponse(HttpStatus.UNAUTHORIZED, asList(ex.getMessage()), new HttpHeaders());
	}

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                     HttpHeaders headers,
                                                     HttpStatus status, 
                                                     WebRequest request) {

		log.info("ControllerExceptionHandler::handleMethodArgumentNotValid()");
    	List<String> errors = ex.getBindingResult()
    			.getAllErrors()
    			.stream().map(ObjectError::getDefaultMessage)
    			.collect(Collectors.toList());
    	return createErrorResponse(status, errors, headers);
	}
	
}
