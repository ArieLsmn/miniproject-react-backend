package com.pointofsales.miniproject.service;

import com.pointofsales.miniproject.model.entity.Category;
import com.pointofsales.miniproject.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepo catRepo;

    List<Category> listCategory(){
        return catRepo.findAll();
    }

    Category showCategory(int id){

        return catRepo.findById(id).get();
    }

    public boolean addCategory(Category cat){
        try {
            catRepo.save(cat);
        }
        catch (Exception e) {return false;}

        return true;
    }
}
