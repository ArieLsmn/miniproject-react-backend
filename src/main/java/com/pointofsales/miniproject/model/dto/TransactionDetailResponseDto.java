package com.pointofsales.miniproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDetailResponseDto {

    @JsonProperty("transaction_id")
    private int transactionId;
    @JsonProperty("product_id")
    private int productId;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("subtotal")
    private int subtotal;

    /*TransactionDetail dtoToEntity(int transactionId){
        TransactionDetail tr = new TransactionDetail()
    }*/

}
