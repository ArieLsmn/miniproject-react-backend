package com.pointofsales.miniproject.model.entity;

import com.pointofsales.miniproject.model.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.access.method.P;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "products", schema = "public")
public class Product implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    //@Column(name = "category_id", nullable = false)
    //private int categoryId;
    @OneToOne
    @JoinColumn (name="category_id",referencedColumnName="id")
    private Category category;
    @Column(name = "price")
    private int price;
    @Column(name = "image")
    private String image;

    public ProductDto entityToDto(){
        return new ProductDto(this.title,this.image,this.price,this.category.getId(),this.category.getName());
    }

}
