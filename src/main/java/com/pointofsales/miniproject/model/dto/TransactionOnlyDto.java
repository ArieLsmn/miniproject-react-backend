package com.pointofsales.miniproject.model.dto;

import com.pointofsales.miniproject.model.entity.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOnlyDto {


    private int totalAmount;

    private int totalPay;

    private LocalDate transactionDate;


    /*Transaction dtoToEntity(){
        Transaction tr = new Transaction(this.id,this.transactionDate,this.totalAmount,this.totalPay,new ArrayList<>());
        return tr;
    }*/
}
