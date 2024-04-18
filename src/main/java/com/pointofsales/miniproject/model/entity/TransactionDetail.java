package com.pointofsales.miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TransactionDetail")
@Table(name = "transaction_details", schema = "public")
public class TransactionDetail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "transaction_id", nullable = false)
    private int transactionId;
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "subtotal")
    private int subtotal;

}
