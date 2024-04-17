package com.pointofsales.miniproject.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity(name = "Transaction")
@Table(name = "transactions", schema = "public")
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "total_amount", nullable = false)
    private int totalAmount;
    @Column(name = "total_pay")
    private int totalPay;


}
