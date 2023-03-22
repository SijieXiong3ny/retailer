package com.example.retailer.utils;

import com.example.retailer.entity.CustomerEntity;
import com.example.retailer.entity.TransactionEntity;
import com.example.retailer.model.Customer;
import com.example.retailer.model.Transaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityConverterTests {
    private static final int id = 0;
    private static final String name = "tester";
    private static final String password = "12345";
    private static final int amount = 100;
    private static final int month = 1;

    private static final Customer customer = Customer.builder()
            .id(id)
            .name(name)
            .password(password)
            .build();
    private static final CustomerEntity customerEntity = CustomerEntity.builder()
            .id(id)
            .name(name)
            .password(password)
            .build();
    private static final Transaction transaction = Transaction.builder()
            .id(id)
            .customerName(name)
            .amount(amount)
            .month(month)
            .build();
    private static final TransactionEntity transactionEntity = TransactionEntity.builder()
            .id(id)
            .customerName(name)
            .amount(amount)
            .month(month)
            .build();

    @Test
    public void ConverterTests() {
        assertEquals(customer, EntityConverter.convertCustomerEntityToCustomerModel(customerEntity));
        assertEquals(customerEntity, EntityConverter.convertCustomerModelToCustomerEntity(customer));
        assertEquals(transaction, EntityConverter.convertTransactionEntityToTransactionModel(transactionEntity));
        assertEquals(transactionEntity, EntityConverter.convertTransactionModelToTransactionEntity(transaction));
    }
}
