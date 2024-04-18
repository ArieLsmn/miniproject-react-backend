package com.pointofsales.miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToMany
    @JoinColumn(name="transaction_id",referencedColumnName = "id")
    private List<TransactionDetail> transactionDetail;


}
