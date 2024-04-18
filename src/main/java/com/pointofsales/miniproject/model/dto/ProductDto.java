package com.pointofsales.miniproject.model.dto;

import com.pointofsales.miniproject.model.entity.Category;
import com.pointofsales.miniproject.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {

    private String title;
    private String image;
    private int price;
    private Category category;
    //private int categoryId;
    //private String categoryName;
    private int id;

    Product dtoToEntity(){

        Product p = new Product(this.id,this.title,this.category,this.price,this.image);

        return p;

    }

}
