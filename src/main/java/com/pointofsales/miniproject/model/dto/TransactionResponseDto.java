package com.pointofsales.miniproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDto {

    @JsonProperty("id")
    private int id;
    @JsonProperty("total_amount")
    private int totalAmount;
    @JsonProperty("total_pay")
    private int totalPay;
    @JsonProperty("transaction_date")
    private LocalDate transactionDate;


    /*Transaction dtoToEntity(){
        Transaction tr = new Transaction(this.id,this.transactionDate,this.totalAmount,this.totalPay,new ArrayList<>());
        return tr;
    }*/
}
