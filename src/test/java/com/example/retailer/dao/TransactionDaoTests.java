package com.example.retailer.dao;

import com.example.retailer.entity.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionDaoTests {
    @Autowired
    TransactionDao transactionDao;
    private static final String name = "tester";
    private static final int amount1 = 120;
    private static final int amount2 = 160;
    private static final int month1 = 1;
    private static final int month2 = 2;

    private static final TransactionEntity entity1 = TransactionEntity.builder()
            .customerName(name)
            .amount(amount1)
            .month(month1)
            .build();
    private static final TransactionEntity entity2 = TransactionEntity.builder()
            .customerName(name)
            .amount(amount2)
            .month(2)
            .build();

    @Test
    public void TransactionDao_Add_Get_Test() {
        transactionDao.deleteTransaction(name);

        transactionDao.addTransaction(entity1);
        transactionDao.addTransaction(entity2);
        List<TransactionEntity> entityList = transactionDao.getTransactionsByCustomerName(name);
        assertEquals(entityList.size(), 2);
        assertEquals(entityList.get(0).getCustomerName(), name);
        assertEquals(entityList.get(0).getAmount(), amount1);
        assertEquals(entityList.get(0).getMonth(), month1);
        assertEquals(entityList.get(1).getCustomerName(), name);
        assertEquals(entityList.get(1).getAmount(), amount2);
        assertEquals(entityList.get(1).getMonth(), month2);

        entityList = transactionDao.getTransactionsByCustomerNameInMonth(name, month1);
        assertEquals(entityList.size(), 1);
        assertEquals(entityList.get(0).getCustomerName(), name);
        assertEquals(entityList.get(0).getAmount(), amount1);
        assertEquals(entityList.get(0).getMonth(), month1);

        entityList = transactionDao.getTransactionsByCustomerNameInMonth(name, month2);
        assertEquals(entityList.size(), 1);
        assertEquals(entityList.get(0).getCustomerName(), name);
        assertEquals(entityList.get(0).getAmount(), amount2);
        assertEquals(entityList.get(0).getMonth(), month2);

        transactionDao.deleteTransaction(name);
    }
}
