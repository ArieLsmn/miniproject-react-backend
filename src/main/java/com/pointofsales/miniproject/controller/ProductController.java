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

    //@GetMapping(value="/listproduct",params = {"!sort_by","!sort_order","!title","!category_id"})
    //List<Product> listAll(){
    //    return prodService.listProduct();
    //}

    @GetMapping(value="/listproduct")
    @ResponseBody
    ResponseEntity<Object> listByOrder(@RequestParam(name = "title",required = false) String name,
                                       @RequestParam(name = "sort_by",required = false,defaultValue = "id") String sortBy,
                                       @RequestParam(name="sort_order",required = false,defaultValue = "asc") String sortOrder,
                                       @RequestParam(name = "category_id",required = false) String cat
    ) {
        if (!sortBy.matches("^[a-zA-Z]+$") || !sortOrder.matches("asc|desc"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Option Input");


        List < Product > pr = new ArrayList<>();


        if (cat==null || cat.isEmpty()) {

            if (name==null || name.isEmpty()) pr =prodService.listProductSort(sortBy, sortOrder);

            else {
                 if(!name.matches("^[a-zA-Z0-9]+$")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Option Input");
                pr = prodService.listProductLike(name, sortBy, sortOrder);

            }
        }else{

            if(!cat.matches("^[0-9]+$")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Option Input");
            pr = prodService.listProductByCategory(Integer.parseInt(cat));
        }

        return ResponseEntity.status(HttpStatus.OK).body(pr);
    }


    //@GetMapping(value="/listproduct",params = {"category_id"})
    //@ResponseBody
    //List<Product> listByCategory(){
    //    return prodService.listProductByCategory(cat);
   // }

    @PostMapping("/addproduct")
    @ResponseBody
    ResponseEntity<ResponseMessage> addProduct(@RequestBody Product pr){
        HttpStatus stt;
        ResponseMessage rm;
        if(prodService.insertProduct(pr)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

            return ResponseEntity.status(stt).body(rm);

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
