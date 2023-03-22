package com.example.retailer;

import com.example.retailer.dao.CustomerDao;
import com.example.retailer.dao.TransactionDao;
import com.example.retailer.entity.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetailerApplicationTests {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private TransactionDao transactionDao;

    @Test
    void contextLoads() {
        System.out.println(customerDao.getCustomerByName("Amy"));
        System.out.println("Add before");
        System.out.println(transactionDao.getTransactionsByCustomerName("Amy"));
        TransactionEntity transaction = new TransactionEntity();
        transaction.setCustomerName("Amy");
        transaction.setAmount(1000);
        transaction.setMonth(10);
        transactionDao.addTransaction(transaction);
        System.out.println("Add after");
        System.out.println(transactionDao.getTransactionsByCustomerName("Amy"));
    }

}
