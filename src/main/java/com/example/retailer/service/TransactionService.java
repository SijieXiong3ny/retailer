package com.example.retailer.service;

import com.example.retailer.model.Transaction;
import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByCustomerName(String name);
    List<Transaction> getTransactionsByCustomerNameInMonth(String name, int month);
    int addTransaction(Transaction transaction);
}
