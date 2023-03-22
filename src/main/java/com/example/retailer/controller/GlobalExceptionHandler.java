package com.example.retailer.controller;

import com.example.retailer.exception.CustomerNotFoundException;
import com.example.retailer.exception.DBOperationFailureException;
import com.example.retailer.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerCustomerNotExists(Exception ex) {
        log.error("Customer does not exist.");
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DBOperationFailureException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerDatabaseOperationFailure(Exception ex) {
        log.error("DateBase Operation Failed.");
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
