package com.example.retailer.exception;

import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
public class DBOperationFailureExceptionTests{
    private final static String message = "message";
    @Test
    public void ExceptionTests() {
        DBOperationFailureException exception = new DBOperationFailureException(message);
        assertEquals(exception.getMessage(), message);
    }
}
