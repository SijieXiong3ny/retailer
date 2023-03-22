package com.example.retailer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTests {
    private static final int id = 1;
    private static final String customerName = "tester";
    private static final int amount = 100;
    private static final int month = 1;

    @Test
    public void Tests() {
        Transaction transaction = Transaction.builder()
                .id(id)
                .customerName(customerName)
                .amount(amount)
                .month(month)
                .build();
        assertEquals(transaction.getId(), id);
        assertEquals(transaction.getCustomerName(), customerName);
        assertEquals(transaction.getAmount(), amount);
        assertEquals(transaction.getMonth(), month);
    }
}
