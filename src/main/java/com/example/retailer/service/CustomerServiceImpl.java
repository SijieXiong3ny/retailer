package com.example.retailer.service;

import com.example.retailer.dao.CustomerDao;
import com.example.retailer.model.Customer;
import com.example.retailer.utils.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    @Autowired
    public CustomerServiceImpl(final CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    @Cacheable("Customer")
    public Customer getCustomerByName(String name) {
        return EntityConverter.convertCustomerEntityToCustomerModel(customerDao.getCustomerByName(name));
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerDao.updateCustomer(EntityConverter.convertCustomerModelToCustomerEntity(customer));
    }

    @Override
    public int removeCustomer(String name) {
        return customerDao.deleteCustomer(name);
    }

    @Override
    public int createCustomer(Customer customer) {
        return customerDao.addCustomer(EntityConverter.convertCustomerModelToCustomerEntity(customer));
    }
}
