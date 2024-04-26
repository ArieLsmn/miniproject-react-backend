package com.pointofsales.miniproject.service;

import com.pointofsales.miniproject.model.dto.ProductDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo prodRepo;

    @Autowired
    CategoryRepo catRepo;

    public List<ProductDto> listProduct(){
        List<Product> listProd =prodRepo.findAll();
        List<ProductDto> pdto = new ArrayList<ProductDto>();
        for (Product p : listProd) {
            pdto.add(p.entityToDto());
        }

        return pdto;
    }

    public List<ProductDto> listProductSort(String by, String dir){
        List<Product> listProd;
        if(dir.equals("desc")) listProd =prodRepo.findAll(Sort.by(Sort.Order.desc(by)));
        else listProd =prodRepo.findAll(Sort.by(Sort.Order.asc(by)));

        List<ProductDto> pdto = new ArrayList<ProductDto>();
        for (Product p : listProd) {
            pdto.add(p.entityToDto());
        }

        return pdto;
    }

    public List<ProductDto> listProductLike(String name, String by, String dir) {
        List<Product> listProd;
        Product pr = new Product();
        pr.setTitle(name);

        //ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
        //        .withMatcher(by, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Product> example = Example.of(pr,ExampleMatcher.matchingAll()
                .withIgnoreCase().withIgnorePaths("id","price","categoryId")
                .withMatcher(by,ExampleMatcher.GenericPropertyMatchers.contains()));

        if(dir.equals("desc")) listProd= prodRepo.findAll(example,Sort.by(Sort.Order.desc(by)));
        else
            listProd = prodRepo.findAll(example, Sort.by(Sort.Order.asc(by)));


            List<ProductDto> pdto = new ArrayList<ProductDto>();
            for (Product p : listProd) {
                pdto.add(p.entityToDto());

        }
            return pdto;
    }

    public List<ProductDto> listProductByCategory(int cat) {
        List<Product> listProd = prodRepo.findByCategoryId(cat);
            List<ProductDto> pdto = new ArrayList<ProductDto>();
            for (Product p : listProd) {
                pdto.add(p.entityToDto());
            }
        return pdto;
    }


    public ProductDto detailProduct(int id) {
        Optional<Product> optional = prodRepo.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        else
            return prodRepo.findById(id).get().entityToDto();
    }

    public boolean insertProduct(Product p) {
        try {
            prodRepo.save(p);
        }catch (Exception e){
            return false;
        }

        return true;
    }


    public boolean updateProduct(int id, ProductDto p){
        Optional<Product> optional = prodRepo.findById(id);
        if (optional.isEmpty()) return false;

        //Category categories = catRepo.findById(p.getCategoryId()).orElse(null);

        Product product = p.dtoToEntity();
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
