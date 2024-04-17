package com.pointofsales.miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "products", schema = "public")
public class Product implements Serializable {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "category_id", nullable = false)
    private int categoryId;
    @Column(name = "price")
    private int price;
    @Column(name = "image")
    private String image;
}
