package com.example.retailer.service;

import com.example.retailer.dao.TransactionDao;
import com.example.retailer.entity.TransactionEntity;
import com.example.retailer.model.Transaction;
import com.example.retailer.utils.EntityConverter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TransactionServiceTests {
    @Autowired
    TransactionServiceImpl transactionService;
    @MockBean
    TransactionDao transactionDao;
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
    private static final TransactionEntity transactionEntity =
            EntityConverter.convertTransactionModelToTransactionEntity(transaction);
    @Test
    public void getTransactionsByCustomerNameTests() {
        transactionService.getTransactionsByCustomerName(name);
        Mockito.verify(transactionDao, Mockito.times(1)).getTransactionsByCustomerName(name);
    }
    @Test
    public void getTransactionsByCustomerNameInMonthTests() {
        transactionService.getTransactionsByCustomerNameInMonth(name, month);
        Mockito.verify(transactionDao, Mockito.times(1))
                .getTransactionsByCustomerNameInMonth(name, month);
    }
    @Test
    public void addTransactionTests() {
        transactionService.addTransaction(transaction);
        Mockito.verify(transactionDao, Mockito.times(1)).addTransaction(transactionEntity);
    }
}
