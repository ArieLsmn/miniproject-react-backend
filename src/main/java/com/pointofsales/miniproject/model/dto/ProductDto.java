package com.pointofsales.miniproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pointofsales.miniproject.model.entity.Category;
import com.pointofsales.miniproject.model.entity.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    //private Category category;
    @JsonProperty("category_id")
    private int categoryId;
    @JsonProperty("category_name")
    private String categoryName;
    @Id
    @GeneratedValue
    private int id;

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
