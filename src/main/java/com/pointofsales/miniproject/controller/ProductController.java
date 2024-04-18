package com.pointofsales.miniproject.controller;

import com.pointofsales.miniproject.model.dto.ProductDto;
import com.pointofsales.miniproject.model.entity.Product;
import com.pointofsales.miniproject.model.entity.ResponseMessage;
import com.pointofsales.miniproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("pos/api")
@RestController
public class ProductController {

    @Autowired
    private ProductService prodService;

    @GetMapping(value="/listproduct",params = {"!sort_by","!sort_order","!title","!category_id"})
    List<Product> listAll(){
        return prodService.listProduct();
    }

    @GetMapping(value="/listproduct",params = {"sort_by","sort_order","!title"})
    @ResponseBody
    List<Product> listByOrder(@RequestParam(name = "sort_by") String sortBy, @RequestParam(name="sort_order") String sortOrder){

        return prodService.listProductSort(sortBy,sortOrder);
    }
    @GetMapping(value="/listproduct",params = {"title","sort_by","sort_order"})
    @ResponseBody
    List<Product> listByTitle(@RequestParam(name = "title") String name, @RequestParam(name = "sort_by") String sortBy, @RequestParam(name="sort_order") String sortOrder){

        return prodService.listProductLike(name,sortBy,sortOrder);
    }


    @GetMapping(value="/listproduct",params = {"category_id"})
    @ResponseBody
    List<Product> listByCategory(@RequestParam(name = "category_id") int cat){
        return prodService.listProductByCategory(cat);
    }

    @PostMapping("/addproduct")
    @ResponseBody
    ResponseMessage addProduct(@RequestBody Product pr){
        prodService.insertProduct(pr);

        return new ResponseMessage(HttpStatus.OK,"Success");

    }

    @PutMapping("/updateproduct/{id}")
    @ResponseBody
    ResponseMessage updateProduct(@PathVariable("id") int id, @RequestBody Product pr){
        boolean check=prodService.updateProduct(id,pr);
        if(check)
        return new ResponseMessage(HttpStatus.OK,"Success");

        else return new ResponseMessage(HttpStatus.NOT_FOUND, "Data not found");

    }

    @DeleteMapping("/deleteproduct/{id}")
    @ResponseBody
    ResponseMessage deleteProduct(@PathVariable("id") int id){
        boolean check=prodService.deleteProduct(id);
        if(check)
        return new ResponseMessage(HttpStatus.OK,"Success");

        else return new ResponseMessage(HttpStatus.NOT_FOUND, "Data not found");

    }
    @GetMapping("/detailproduct/{id}")
    @ResponseBody
    ResponseEntity<ProductDto> detailProduct(@PathVariable("id") int id){
        ProductDto p = new Product().entityToDto();
        try {
            p = prodService.detailProduct(id).entityToDto();

        }catch (NullPointerException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
}
