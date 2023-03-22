package com.example.retailer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Transaction")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "month")
    private int month;

    @Column(name = "amount")
    private int amount;
}
