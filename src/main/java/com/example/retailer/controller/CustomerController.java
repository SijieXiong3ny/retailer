package com.example.retailer.controller;

import com.example.retailer.exception.CustomerNotFoundException;
import com.example.retailer.exception.DBOperationFailureException;
import com.example.retailer.model.Customer;
import com.example.retailer.model.ResponseMessage;
import com.example.retailer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String name) {
        log.info("Get customer information for {}", name);
        Customer customer = customerService.getCustomerByName(name);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createCustomer(@Validated @RequestBody Customer customer) {
        log.info("Start to create customer information for {}", customer);
        Customer existingCustomer = customerService.getCustomerByName(customer.getName());
        if (existingCustomer != null) {
            return new ResponseEntity<>(new ResponseMessage("Customer already exists", customer),
                    HttpStatus.OK);
        }
        int num = customerService.createCustomer(customer);
        if (num > 0) {
            return new ResponseEntity<>(new ResponseMessage("Customer have been created", customer),
                    HttpStatus.CREATED);
        } else {
            throw new DBOperationFailureException("Customer create Failure");
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<ResponseMessage> deleteCustomer(@PathVariable String name) {
        log.info("Start to delete customer {}", name);
        Customer customer = customerService.getCustomerByName(name);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        int num = customerService.removeCustomer(name);
        if (num > 0) {
            return new ResponseEntity<>(new ResponseMessage("Customer have been deleted", null),
                    HttpStatus.OK);
        } else {
            throw new DBOperationFailureException("Customer delete Failure");
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("name") String name,
                                                   @Validated @RequestBody Customer customer) {
        log.info("Start to update customer {} information with {}", name, customer);
        Customer exsitCustomer = customerService.getCustomerByName(name);
        if (exsitCustomer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        int num = customerService.updateCustomer(customer);
        if (num > 0) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            throw new DBOperationFailureException("Customer update Failure");
        }
    }

}
