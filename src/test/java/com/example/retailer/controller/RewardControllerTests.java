package com.example.retailer.controller;

import com.example.retailer.exception.CustomerNotFoundException;
import com.example.retailer.model.Customer;
import com.example.retailer.model.GetRewardRequest;
import com.example.retailer.model.Transaction;
import com.example.retailer.service.CustomerService;
import com.example.retailer.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RewardControllerTests {
    @Autowired
    RewardController rewardController;
    @MockBean
    TransactionService transactionService;
    @MockBean
    CustomerService customerService;
    private static final int id = 1;
    private static final String name = "tester";
    private static final int amount = 120;
    private static final int month = 1;
    private static final String password = "12345";
    private static final int reward = 90;
    private static final Transaction transaction = Transaction.builder()
            .id(id)
            .customerName(name)
            .amount(amount)
            .month(month)
            .build();
    private static final Customer customer = Customer.builder()
            .id(id)
            .name(name)
            .password(password)
            .build();

    @Test
    public void getTotalRewardsSuccessTests() {
        GetRewardRequest request = GetRewardRequest.builder()
                .name(name)
                .build();
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);
        Mockito.when(transactionService.getTransactionsByCustomerName(name)).thenReturn(Arrays.asList(transaction));
        ResponseEntity<Integer> response = rewardController.getRewards(request);
        Mockito.verify(customerService, Mockito.times(1)).getCustomerByName(name);
        Mockito.verify(transactionService,
                Mockito.times(1)).getTransactionsByCustomerName(name);
        assertEquals(reward, response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getTotalRewardsByMonthSuccessTests() {
        GetRewardRequest request = GetRewardRequest.builder()
                .name(name)
                .month(month)
                .build();
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);
        Mockito.when(transactionService.getTransactionsByCustomerNameInMonth(name, month)).thenReturn(Arrays.asList(transaction));
        ResponseEntity<Integer> response = rewardController.getRewards(request);
        Mockito.verify(customerService, Mockito.times(1)).getCustomerByName(name);
        Mockito.verify(transactionService,
                Mockito.times(1)).getTransactionsByCustomerNameInMonth(name, month);
        assertEquals(reward, response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getRewardsNullCustomerExceptionTests() {
        GetRewardRequest request = GetRewardRequest.builder()
                .name(name)
                .month(month)
                .build();
        Exception exception = assertThrows(CustomerNotFoundException.class,
                () -> rewardController.getRewards(request));
        assertEquals(exception.getMessage(), "Customer doesn't exist");
    }
}
