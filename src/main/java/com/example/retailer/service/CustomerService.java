package com.example.retailer.service;

import com.example.retailer.model.Customer;
import org.springframework.cache.annotation.Cacheable;

public interface CustomerService {
    Customer getCustomerByName(String name);
    int updateCustomer(Customer customer);
    int removeCustomer(String name);
    int createCustomer(Customer customer);
}
