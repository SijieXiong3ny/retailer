package com.example.retailer.controller;

import com.example.retailer.exception.CustomerNotFoundException;
import com.example.retailer.exception.DBOperationFailureException;
import com.example.retailer.model.Customer;
import com.example.retailer.model.ResponseMessage;
import com.example.retailer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerControllerTests {
    @Autowired
    CustomerController customerController;
    @MockBean
    CustomerService customerService;
    private static final int id = 1;
    private static final String name = "tester";
    private static final String password = "12345";
    private static final Customer customer = Customer.builder()
            .id(id)
            .name(name)
            .password(password)
            .build();

    @Test
    public void getCustomerSuccessTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.getCustomer(name);
        Mockito.verify(customerService, Mockito.times(1)).getCustomerByName(name);
        assertEquals(customer, response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void getCustomerNullCustomerExceptionTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(null);
        Exception exception = assertThrows(CustomerNotFoundException.class, () -> customerController.getCustomer(name));
        assertEquals(exception.getMessage(), "Customer doesn't exist");
    }

    @Test
    public void createCustomerSuccessTests() {
        Mockito.when(customerService.createCustomer(customer)).thenReturn(1);
        ResponseEntity<ResponseMessage> response = customerController.createCustomer(customer);
        Mockito.verify(customerService, Mockito.times(1)).getCustomerByName(name);
        Mockito.verify(customerService, Mockito.times(1)).createCustomer(customer);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void createCustomerCustomerAlreadyExistsTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);
        ResponseEntity<ResponseMessage> response = customerController.createCustomer(customer);
        Mockito.verify(customerService, Mockito.times(1)).getCustomerByName(name);
        Mockito.verify(customerService, Mockito.never()).createCustomer(customer);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void createCustomerDBDeleteFailureExceptionTests() {
        Mockito.when(customerService.createCustomer(customer)).thenReturn(0);

        Exception exception = assertThrows(DBOperationFailureException.class,
                () -> customerController.createCustomer(customer));
        assertEquals(exception.getMessage(), "Customer create Failure");
    }

    @Test
    public void deleteCustomerSuccessTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);
        Mockito.when(customerService.removeCustomer(name)).thenReturn(1);
        ResponseEntity<ResponseMessage> response = customerController.deleteCustomer(name);
        Mockito.verify(customerService, Mockito.times(1)).getCustomerByName(name);
        Mockito.verify(customerService, Mockito.times(1)).removeCustomer(name);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteCustomerNullCustomerExceptionTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(null);
        Exception exception = assertThrows(CustomerNotFoundException.class,
                () -> customerController.deleteCustomer(name));
        assertEquals(exception.getMessage(), "Customer doesn't exist");
    }

    @Test
    public void deleteCustomerDBDeleteFailureExceptionTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);
        Mockito.when(customerService.removeCustomer(name)).thenReturn(0);
        Exception exception = assertThrows(DBOperationFailureException.class,
                () -> customerController.deleteCustomer(name));
        assertEquals(exception.getMessage(), "Customer delete Failure");
    }

    @Test
    public void updateCustomerSuccessTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);
        Mockito.when(customerService.updateCustomer(customer)).thenReturn(1);
        ResponseEntity<Customer> response = customerController.updateCustomer(name, customer);
        Mockito.verify(customerService, Mockito.times(1)).getCustomerByName(name);
        Mockito.verify(customerService, Mockito.times(1)).updateCustomer(customer);
        assertEquals(customer, response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void updateCustomerNullCustomerExceptionTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(null);
        Exception exception = assertThrows(CustomerNotFoundException.class,
                () -> customerController.updateCustomer(name, customer));
        assertEquals(exception.getMessage(), "Customer doesn't exist");
    }

    @Test
    public void updateCustomerDBUpdateFailureExceptionTests() {
        Mockito.when(customerService.getCustomerByName(name)).thenReturn(customer);
        Mockito.when(customerService.updateCustomer(customer)).thenReturn(0);
        Exception exception = assertThrows(DBOperationFailureException.class,
                () -> customerController.updateCustomer(name, customer));
        assertEquals(exception.getMessage(), "Customer update Failure");
    }
}
