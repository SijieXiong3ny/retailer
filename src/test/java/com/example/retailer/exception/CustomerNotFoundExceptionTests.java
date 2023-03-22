package com.example.retailer.exception;

import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
public class CustomerNotFoundExceptionTests{
    private final static String message = "message";

    @Test
    public void ExceptionTests() {
        CustomerNotFoundException exception = new CustomerNotFoundException(message);
        assertEquals(exception.getMessage(), message);
    }
}
