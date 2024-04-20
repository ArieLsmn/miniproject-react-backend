package com.pointofsales.miniproject.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDetailOnlyDto {

    private int productId;

    private int quantity;

    private int subtotal;

    /*TransactionDetail dtoToEntity(int transactionId){
        TransactionDetail tr = new TransactionDetail()
    }*/

}
