package com.pointofsales.miniproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pointofsales.miniproject.model.entity.Transaction;
import com.pointofsales.miniproject.model.entity.TransactionDetail;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

    //@JsonProperty("transaction_date")
    //private String transactionDate;
    @JsonProperty("total_amount")
    private int totalAmount;
    @JsonProperty("total_pay")
    private int totalPay;
    @JsonProperty("transaction_details")
    private List<TransactionDetailRequestDto> transactionDetail;

    public Transaction dtoToEntity(){
        Transaction tr = new Transaction();
        tr.setTotalAmount(this.totalAmount);
        tr.setTotalPay(this.totalPay);
        //tr.setTransactionDate(LocalDate.parse(this.transactionDate).atStartOfDay());
        List<TransactionDetail> trEnt = new ArrayList<TransactionDetail>();
        for(TransactionDetailRequestDto trd : this.transactionDetail)
            trEnt.add(trd.dtoToEntity());

        tr.setTransactionDetail(trEnt);
        return tr;
    }


}
