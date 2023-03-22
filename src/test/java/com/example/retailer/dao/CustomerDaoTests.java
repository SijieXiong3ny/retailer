package com.example.retailer.dao;

import com.example.retailer.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CustomerDaoTests {
    @Autowired
    CustomerDao customerDao;
    private static final String name = "tester";
    private static final String password = "12345";
    private static final String updatePassword = "678910";

    private static final CustomerEntity entity = CustomerEntity.builder()
            .name(name)
            .password(password)
            .build();

    private static final CustomerEntity updatedEntity = CustomerEntity.builder()
            .name(name)
                .password(updatePassword)
                .build();

    @Test
    public void CustomerDao_Add_Get_Delete_Update_Test() {
        customerDao.deleteCustomer(name);
        //Test Add && Get operation
        customerDao.addCustomer(entity);
        CustomerEntity entityAfterAdding = customerDao.getCustomerByName(name);
        assertEquals(entityAfterAdding.getName(), name);
        assertEquals(entityAfterAdding.getPassword(), password);
        //Test Update && Get operation
        customerDao.updateCustomer(updatedEntity);
        CustomerEntity updateEntityAfterAdding = customerDao.getCustomerByName(name);
        assertEquals(updateEntityAfterAdding.getName(), name);
        assertEquals(updateEntityAfterAdding.getPassword(), updatePassword);
        //Test Delete
        customerDao.deleteCustomer(name);
        assertNull(customerDao.getCustomerByName(name));
    }
}
