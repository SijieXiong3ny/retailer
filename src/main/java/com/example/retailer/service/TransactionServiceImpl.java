package com.example.retailer.service;

import com.example.retailer.dao.TransactionDao;
import com.example.retailer.entity.TransactionEntity;
import com.example.retailer.model.Transaction;
import com.example.retailer.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    @Autowired
    public TransactionServiceImpl(final TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }
    @Override
    @Cacheable("Transaction")
    public List<Transaction> getTransactionsByCustomerName(String name) {
        return convertTransactionEntitiesToTransactions(transactionDao.getTransactionsByCustomerName(name));
    }

    @Override
    @Cacheable("Transaction")
    public List<Transaction> getTransactionsByCustomerNameInMonth(String name, int month) {
        return convertTransactionEntitiesToTransactions(transactionDao.getTransactionsByCustomerNameInMonth(name, month));
    }

    @Override
    public int addTransaction(Transaction transaction) {
        return transactionDao.addTransaction(EntityConverter.convertTransactionModelToTransactionEntity(transaction));
    }

    private List<Transaction> convertTransactionEntitiesToTransactions(List<TransactionEntity> transactionEntities) {
        return transactionEntities.stream().map(EntityConverter::convertTransactionEntityToTransactionModel).collect(Collectors.toList());
    }
}
