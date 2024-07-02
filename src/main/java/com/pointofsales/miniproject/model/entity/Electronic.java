package com.pointofsales.miniproject.model.entity;

import com.pointofsales.miniproject.model.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Electronic")
@Table(name = "electronics", schema = "public")
public class Electronic implements Serializable {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;
    @Column (name="category")
    private String category;
    @Column (name="brand")
    private String brand;
    @Column(name="descr")
    private String descr;
    @Column (name="stock")
    private int stock;
    @Column (name="rating")
    private double rating;
    @Column(name = "image")
    private String image;
    @Column(name="discount")
    private int discount;
    @Column(name="thumb1")
    private String thumb1;
    @Column(name="thumb2")
    private String thumb2;
    @Column(name="thumb3")
    private String thumb3;
    @Column(name="thumb4")
    private String thumb4;
    @Column(name="category_id")
    private int categoryId;
    //public ProductDto entityToDto(){
    //    return new ProductDto(this.id,this.title,this.image,this.price,this.category.getId(),this.category.getName());
    //}

}
