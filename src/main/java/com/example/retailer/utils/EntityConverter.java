package com.example.retailer.utils;

import com.example.retailer.entity.CustomerEntity;
import com.example.retailer.entity.TransactionEntity;
import com.example.retailer.model.Customer;
import com.example.retailer.model.Transaction;

public class EntityConverter {
    public static Customer convertCustomerEntityToCustomerModel(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }
        return Customer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .password(entity.getPassword())
                .build();
    }

    public static CustomerEntity convertCustomerModelToCustomerEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        return CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .password(customer.getPassword())
                .build();
    }

    public static Transaction convertTransactionEntityToTransactionModel(TransactionEntity entity) {
        if (entity == null) {
            return null;
        }
        return Transaction.builder()
                .id(entity.getId())
                .customerName(entity.getCustomerName())
                .month(entity.getMonth())
                .amount(entity.getAmount())
                .build();
    }

    public static TransactionEntity convertTransactionModelToTransactionEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return TransactionEntity.builder()
                .id(transaction.getId())
                .customerName(transaction.getCustomerName())
                .month(transaction.getMonth())
                .amount(transaction.getAmount())
                .build();
    }
}
