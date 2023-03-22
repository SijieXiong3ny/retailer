package com.example.retailer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseTests {
    private static final int errorCode = 404;
    private static final String message = "error message";
    @Test
    public void Tests() {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(errorCode);
        response.setMessage(message);
        assertEquals(response.getErrorCode(), errorCode);
        assertEquals(response.getMessage(), message);
    }
}
