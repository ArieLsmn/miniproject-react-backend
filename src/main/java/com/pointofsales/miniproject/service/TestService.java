package com.pointofsales.miniproject.service;

import com.pointofsales.miniproject.model.dto.ProductDto;
import com.pointofsales.miniproject.model.entity.Electronic;
import com.pointofsales.miniproject.model.entity.Product;
import com.pointofsales.miniproject.repository.CategoryRepo;
import com.pointofsales.miniproject.repository.ProductRepo;
import com.pointofsales.miniproject.repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    TestRepo prodRepo;

    @Autowired
    CategoryRepo catRepo;

    public List<Electronic> listProduct(){
        List<Electronic> listProd =prodRepo.findAll();
        //List<Electronic> pdto = new ArrayList<Electronic>();
        //for (Electronic p : listProd) {
            //pdto.add(p.entityToDto());
        //}

        return listProd;
    }

    public List<Electronic> listProductSort(String by, String dir){
        List<Electronic> listProd;
        if(dir.equals("desc")) listProd =prodRepo.findAll(Sort.by(Sort.Order.desc(by)));
        else listProd =prodRepo.findAll(Sort.by(Sort.Order.asc(by)));

        /*List<ProductDto> pdto = new ArrayList<ProductDto>();
        for (Product p : listProd) {
            pdto.add(p.entityToDto());
        }*/

        //return pdto;
        return listProd;
    }


    public List<Electronic> listProductLike(String name, String by, String dir) {
        List<Electronic> listProd;
        Electronic pr = new Electronic();
        pr.setTitle(name);

        //ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
        //        .withMatcher(by, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Electronic> example = Example.of(pr,ExampleMatcher.matchingAll()
                .withIgnoreCase().withIgnorePaths("id","price","categoryId")
                .withMatcher(by,ExampleMatcher.GenericPropertyMatchers.contains()));

        if(dir.equals("desc")) listProd= prodRepo.findAll(example,Sort.by(Sort.Order.desc(by)));
        else
            listProd = prodRepo.findAll(example, Sort.by(Sort.Order.asc(by)));

            return listProd;
    }

    public List<Electronic> listProductByCategory(int cat) {
        List<Electronic> listProd = prodRepo.findByCategoryId(cat);
            //List<ProductDto> pdto = new ArrayList<ProductDto>();
            //for (Product p : listProd) {
            //    pdto.add(p.entityToDto());
            //}
        return listProd;
    }


    public Electronic detailProduct(int id) {
        Optional<Electronic> optional = prodRepo.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        else
            return prodRepo.findById(id).get();
    }

    public boolean insertProduct(Electronic p) {

        if(p.getPrice() == 0 || p.getTitle().isEmpty()) throw new IllegalArgumentException();

        try {
            prodRepo.save(p);
        }catch (Exception e){
            return false;
        }

        return true;
    }


    public boolean updateProduct(int id, Electronic p){
        Optional<Electronic> optional = prodRepo.findById(id);
        if (optional.isEmpty()) return false;

        //Category categories = catRepo.findById(p.getCategoryId()).orElse(null);

        Electronic product = p;
        product.setId(id);
        prodRepo.save(product);
        return true;
    }

    public boolean deleteProduct(int id){
        Optional<Electronic> optional = prodRepo.findById(id);

        if (optional.isEmpty()) {
            return false;
        }
        else {
            prodRepo.deleteById(id);
            return true;
        }

    }




}
