package com.example.retailer.service;

import com.example.retailer.dao.CustomerDao;
import com.example.retailer.entity.CustomerEntity;
import com.example.retailer.model.Customer;
import com.example.retailer.utils.EntityConverter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CustomerServiceTests {
    @Autowired
    CustomerServiceImpl customerService;
    @MockBean
    CustomerDao customerDao;
    private static final int id = 1;
    private static final String name = "tester";
    private static final String password = "12345";
    private static final Customer customer = Customer.builder()
            .id(id)
            .name(name)
            .password(password)
            .build();
    private static final CustomerEntity customerEntity =
            EntityConverter.convertCustomerModelToCustomerEntity(customer);
    @Test
    public void getCustomerByNameTests() {
        customerService.getCustomerByName(name);
        Mockito.verify(customerDao, Mockito.times(1)).getCustomerByName(name);
    }
    @Test
    public void updateCustomerTests() {
        customerService.updateCustomer(customer);
        Mockito.verify(customerDao, Mockito.times(1))
                .updateCustomer(customerEntity);
    }
    @Test
    public void removeCustomerTests() {
        customerService.removeCustomer(name);
        Mockito.verify(customerDao, Mockito.times(1)).deleteCustomer(name);
    }

    @Test
    public void createCustomerTests() {
        customerService.createCustomer(customer);
        Mockito.verify(customerDao, Mockito.times(1)).addCustomer(customerEntity);
    }
}
