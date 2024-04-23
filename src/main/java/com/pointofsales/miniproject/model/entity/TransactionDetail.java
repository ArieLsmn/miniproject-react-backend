package com.pointofsales.miniproject.model.entity;

import com.pointofsales.miniproject.model.dto.TransactionDetailRequestDto;
import com.pointofsales.miniproject.model.dto.TransactionDetailResponseDto;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "transaction_id")
    private int transactionId;
    @OneToOne
    @JoinColumn (name="product_id",referencedColumnName="id")
    private Product product;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "subtotal")
    private int subtotal;

    public TransactionDetailRequestDto entityToDtoInput(){
        return new TransactionDetailRequestDto(this.product.getId(), this.quantity,this.subtotal);
    }
    public TransactionDetailResponseDto entityToDtoOutput(){
        return new TransactionDetailResponseDto(this.transactionId,this.product.getId(),this.product.getTitle(), this.quantity,this.subtotal);
    }

}
