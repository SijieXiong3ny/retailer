package com.example.retailer.dao;

import com.example.retailer.entity.TransactionEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

@Mapper
public interface TransactionDao {
    @Select("select * from Transaction where customerName=#{name}")
    List<TransactionEntity> getTransactionsByCustomerName(String name);
    @Select("select * from Transaction where customerName=#{name} and month=#{month}")
    List<TransactionEntity> getTransactionsByCustomerNameInMonth(String name, int month);
    @Insert("insert into transaction(customerName, month, amount) VALUES(#{customerName}, #{month}, #{amount})")
    int addTransaction(TransactionEntity transaction);
    @Async
    @Delete("delete from transaction where customerName=#{name}")
    void deleteTransaction(String name);
}
