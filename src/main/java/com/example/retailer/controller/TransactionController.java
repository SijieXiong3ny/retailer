package com.example.retailer.controller;

import com.example.retailer.exception.DBOperationFailureException;
import com.example.retailer.model.ResponseMessage;
import com.example.retailer.model.Transaction;
import com.example.retailer.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Transaction>> getTransaction(@PathVariable String name) {
        log.info("Get transaction information under customer {}", name);
        List<Transaction> transactions = transactionService.getTransactionsByCustomerName(name);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ResponseMessage> addTransaction(@Validated @RequestBody Transaction transaction) {
        log.info("Add the transaction {}", transaction);
        int num = transactionService.addTransaction(transaction);
        if (num > 0) {
            return new ResponseEntity<>(new ResponseMessage("Transaction have been added", transaction),
                    HttpStatus.CREATED);
        } else {
            throw new DBOperationFailureException("Add transaction failure");
        }
    }

}
