package com.example.retailer.controller;

import com.example.retailer.exception.CustomerNotFoundException;
import com.example.retailer.model.Customer;
import com.example.retailer.model.GetRewardRequest;
import com.example.retailer.model.Transaction;
import com.example.retailer.service.CustomerService;
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
@RequestMapping("/rewards")
public class RewardController {
    private final TransactionService transactionService;
    private final CustomerService customerService;
    private static final Logger log = LoggerFactory.getLogger(RewardController.class);
    @Autowired
    public RewardController(TransactionService transactionService,
                            CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }
    @GetMapping
    public ResponseEntity<Integer> getRewards(@Validated @RequestBody GetRewardRequest request) {
        String name = request.getName();
        Integer month = request.getMonth();
        Customer customer = customerService.getCustomerByName(name);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        if (month != null) {
            log.info("Get rewards under customer {} on month {}", name, month);
            List<Transaction> transactions = transactionService.getTransactionsByCustomerNameInMonth(name, month);
            return new ResponseEntity<>(getTotalReward(transactions), HttpStatus.OK);
        } else {
            log.info("Get all rewards under customer {}", name);
            List<Transaction> transactions = transactionService.getTransactionsByCustomerName(name);
            return new ResponseEntity<>(getTotalReward(transactions), HttpStatus.OK);
        }
    }

    private int getTotalReward(List<Transaction> transactions) {
        return transactions.stream().mapToInt(t -> getRewardFromTransactionAmount(t.getAmount())).sum();

    }

    private int getRewardFromTransactionAmount(int amount) {
        if (amount >= 100) {
            return 2 * (amount - 100) + 50;
        } else if (amount >= 50) {
            return amount - 50;
        } else {
            return 0;
        }
    }
}
