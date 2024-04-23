package com.pointofsales.miniproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pointofsales.miniproject.model.entity.Product;
import com.pointofsales.miniproject.model.entity.TransactionDetail;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionDetailRequestDto {

    @JsonProperty("product_id")
    private int productId;

    private int quantity;

    private int subtotal;

    TransactionDetail dtoToEntity(){
        TransactionDetail tr = new TransactionDetail();
        tr.setProduct(new Product());
        tr.getProduct().setId(this.productId);
        tr.setSubtotal(this.subtotal);
        tr.setQuantity(this.quantity);
        return tr;
    }

}
