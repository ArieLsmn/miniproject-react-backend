package com.pointofsales.miniproject.model.entity;

import com.pointofsales.miniproject.model.dto.CategoryDto;
import com.pointofsales.miniproject.model.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories", schema = "public")
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public CategoryDto entityToDto(){
        return new CategoryDto(this.id,this.name,0);
    }
}
