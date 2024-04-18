package com.pointofsales.miniproject.service;

import com.pointofsales.miniproject.model.entity.Category;
import com.pointofsales.miniproject.model.entity.Product;
import com.pointofsales.miniproject.model.entity.ResponseMessage;
import com.pointofsales.miniproject.repository.CategoryRepo;
import com.pointofsales.miniproject.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo prodRepo;

    @Autowired
    CategoryRepo catRepo;

    public List<Product> listProduct(){
        List<Product> listProd =prodRepo.findAll();
        return listProd;
    }

    public List<Product> listProductSort(String by, String dir){
        List<Product> listProd;
        if(dir.equals("desc")) listProd =prodRepo.findAll(Sort.by(Sort.Order.desc(by)));
        else listProd =prodRepo.findAll(Sort.by(Sort.Order.asc(by)));

        return listProd;
    }

    public List<Product> listProductLike(String name, String by, String dir) {

        Product p = new Product();
        p.setTitle(name);

        //ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
        //        .withMatcher(by, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Product> example = Example.of(p,ExampleMatcher.matchingAll()
                .withIgnoreCase().withIgnorePaths("id","price","categoryId")
                .withMatcher(by,ExampleMatcher.GenericPropertyMatchers.contains()));

        if(dir.equals("desc")) return prodRepo.findAll(example,Sort.by(Sort.Order.desc(by)));
        else
        return prodRepo.findAll(example,Sort.by(Sort.Order.asc(by)));
    }

    public List<Product> listProductByCategory(int cat) {

        return prodRepo.findByCategoryId(cat);
    }


    public Product detailProduct(int id) {
        Optional<Product> optional = prodRepo.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        else
            return prodRepo.findById(id).get();
    }

    public void insertProduct(Product p) {
        prodRepo.save(p);
    }


    public boolean updateProduct(int id,Product p){
        Optional<Product> optional = prodRepo.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        //Category categories = catRepo.findById(p.getCategoryId()).orElse(null);

        Product product = p;
        product.setId(id);
        prodRepo.save(product);
        return true;
    }

    public boolean deleteProduct(int id){
        Optional<Product> optional = prodRepo.findById(id);

        if (optional.isEmpty()) {
            return false;
        }
        else {
            prodRepo.deleteById(id);
            return true;
        }

    }




}
