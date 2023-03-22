package com.example.retailer.exception;

import lombok.Data;

@Data
public class DBOperationFailureException extends RuntimeException{
    private String message;
    public DBOperationFailureException() {
        super();
    }

    public DBOperationFailureException(String message) {
        super(message);
        this.message = message;
    }
}
