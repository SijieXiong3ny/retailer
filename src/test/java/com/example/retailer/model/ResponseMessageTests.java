package com.example.retailer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ResponseMessageTests {
    private static final String message = "error message";
    @Test
    public void Tests() {
        ResponseMessage responseMessage = ResponseMessage.builder()
                .message(message)
                .build();
        assertEquals(responseMessage.getMessage(), message);
        assertNull(responseMessage.getBody());
    }
}
