package com.pointofsales.miniproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pointofsales.miniproject.model.entity.Transaction;
import com.pointofsales.miniproject.model.entity.TransactionDetail;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {

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

        List<TransactionDetail> trEnt = new ArrayList<TransactionDetail>();
        for(TransactionDetailRequestDto trd : this.transactionDetail)
            trEnt.add(trd.dtoToEntity());

        tr.setTransactionDetail(trEnt);
        return tr;
    }


}
