package com.pointofsales.miniproject.service;

import com.pointofsales.miniproject.model.dto.CategoryDto;
import com.pointofsales.miniproject.model.dto.ProductDto;
import com.pointofsales.miniproject.model.entity.Category;
import com.pointofsales.miniproject.model.entity.Product;
import com.pointofsales.miniproject.repository.CategoryRepo;
import com.pointofsales.miniproject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo catRepo;
    @Autowired
    ProductRepo prodRepo;

    public List<Category> listCategory(){

        List<Category> cat=catRepo.findAll(Sort.by(Sort.Order.asc("id")));

        return cat;

    }

    public Category showCategory(int id){

        return catRepo.findById(id).get();
    }

    public boolean addCategory(Category cat){
        try {
            catRepo.save(cat);
        }
        catch (Exception e) {return false;}

        return true;
    }

    public boolean updateCategory(int id, Category cat){
        Optional<Category> optional = catRepo.findById(id);
        if (optional.isEmpty()) return false;

        cat.setId(id);

        try {
            catRepo.save(cat);
        }
        catch (Exception e) {return false;}

        return true;
    }


    public boolean deleteCategory(int id){
        Optional<Category> optional = catRepo.findById(id);
        if (optional.isEmpty()) return false;

        try {
            catRepo.deleteById(id);
        }
        catch (Exception e) {
            throw(e);
            //return false;
        }

        return true;
    }
}
