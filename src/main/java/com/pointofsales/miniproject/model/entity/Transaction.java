package com.pointofsales.miniproject.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pointofsales.miniproject.model.dto.TransactionResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transactions")
@Table(name = "transactions", schema = "public")
public class Transaction {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "transaction_date")
    //@JsonProperty("transaction_date")
    private LocalDateTime transactionDate;
    @Column(name = "total_amount", nullable = false)

    private int totalAmount;
    @Column(name = "total_pay")
    @JsonProperty("total_amount")
    private int totalPay;
    @OneToMany
    @JoinColumn(name="transaction_id",referencedColumnName = "id")
    private List<TransactionDetail> transactionDetail;

    public TransactionResponseDto entityToDto(){
        return new TransactionResponseDto(this.id,this.totalAmount, this.totalPay, this.transactionDate.toLocalDate());
    }


}
