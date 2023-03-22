package com.example.retailer.controller;

import com.example.retailer.exception.DBOperationFailureException;
import com.example.retailer.model.ResponseMessage;
import com.example.retailer.model.Transaction;
import com.example.retailer.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransactionControllerTests {
    @Autowired
    TransactionController transactionController;
    @MockBean
    TransactionService transactionService;
    private static final int id = 1;
    private static final String name = "tester";
    private static final int amount = 200;
    private static final int month = 1;
    private static final Transaction transaction = Transaction.builder()
            .id(id)
            .customerName(name)
            .amount(amount)
            .month(month)
            .build();

    @Test
    public void getTransactionSuccessTests() {
        Mockito.when(transactionService.getTransactionsByCustomerName(name)).thenReturn(Arrays.asList(transaction));
        ResponseEntity<List<Transaction>> response = transactionController.getTransaction(name);
        Mockito.verify(transactionService,
                Mockito.times(1)).getTransactionsByCustomerName(name);
        assertEquals(Arrays.asList(transaction), response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void addTransactionSuccessTests() {
        Mockito.when(transactionService.addTransaction(transaction)).thenReturn(1);
        ResponseEntity<ResponseMessage> response = transactionController.addTransaction(transaction);
        Mockito.verify(transactionService, Mockito.times(1)).addTransaction(transaction);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void addTransactionDBAddFailureExceptionTests() {
        Mockito.when(transactionService.addTransaction(transaction)).thenReturn(0);

        Exception exception = assertThrows(DBOperationFailureException.class,
                () -> transactionController.addTransaction(transaction));
        assertEquals(exception.getMessage(), "Add transaction failure");
    }
}
