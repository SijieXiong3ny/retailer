package com.example.retailer.dao;

import com.example.retailer.entity.CustomerEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerDao {
    @Select("select * from customer where name=#{name}")
    CustomerEntity getCustomerByName(String name);
    @Update("update customer set name=#{name}, password=#{password} where name=#{name}")
    int updateCustomer(CustomerEntity entity);
    @Delete("delete from customer where name=#{name}")
    int deleteCustomer(String name);
    @Insert("insert into customer(name, password) VALUES(#{name}, #{password})")
    int addCustomer(CustomerEntity entity);
}
