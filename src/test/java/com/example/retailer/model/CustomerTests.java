package com.example.retailer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTests {
    private static final int id = 1;
    private static final String name = "tester";
    private static final String password = "12345";

    @Test
    public void Tests() {
        Customer customer = Customer.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();
        assertEquals(customer.getId(), id);
        assertEquals(customer.getName(), name);
        assertEquals(customer.getPassword(), password);
    }
}
