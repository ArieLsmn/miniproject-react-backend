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

    private int id;
    private String title;
    private String image;
    private int price;
    //private Category category;
    private int categoryId;
    private String categoryName;

    public Product dtoToEntity(){

        Product p = new Product();

        p.setTitle(this.title);
        p.setImage(this.image);
        p.setPrice(this.price);

        Category cat = new Category(this.categoryId,categoryName);

        p.setCategory(cat);

        return p;

    }

}
