package com.pointofsales.miniproject.controller;

import com.pointofsales.miniproject.model.dto.ProductDto;
import com.pointofsales.miniproject.model.entity.Electronic;
import com.pointofsales.miniproject.model.entity.ResponseMessage;
import com.pointofsales.miniproject.service.ProductService;
import com.pointofsales.miniproject.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/")
@RestController
public class TestController {

    @Autowired
    private TestService prodService;

    //@GetMapping(value="/listproduct",params = {"!sort_by","!sort_order","!title","!category_id"})
    //List<Product> listAll(){
    //    return prodService.listProduct();
    //}

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value="/listproduct")
    @ResponseBody
    ResponseEntity<Object> listByOrder(@RequestParam(name = "title",required = false) String name,
                                       @RequestParam(name = "sort_by",required = false,defaultValue = "id") String sortBy,
                                       @RequestParam(name="sort_order",required = false,defaultValue = "asc") String sortOrder,
                                       @RequestParam(name = "category_id",required = false) String cat
    ) {
        if (!sortBy.matches("^[a-zA-Z]+$") || !sortOrder.matches("asc|desc"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Option Input");


        List <Electronic> pr = new ArrayList<>();


        if (cat==null || cat.isEmpty()) {

            if (name==null || name.isEmpty()) pr =prodService.listProductSort(sortBy, sortOrder);

            else {
                 if(!name.matches("^[a-zA-Z0-9]+$")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Option Input");
                pr = prodService.listProductLike(name, sortBy, sortOrder);

            }
        }else{
            try {
                //if (!cat.matches("^[0-9]+$"))
                    //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only numbers allowed");
                pr = prodService.listProductByCategory(Integer.parseInt(cat));
            }catch (NumberFormatException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only numbers allowed");
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(pr);
    }


    //@GetMapping(value="/listproduct",params = {"category_id"})
    //@ResponseBody
    //List<Product> listByCategory(){
    //    return prodService.listProductByCategory(cat);
   // }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/addproduct")
    @ResponseBody
    ResponseEntity<ResponseMessage> addProduct(@RequestBody Electronic pr){
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


    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/updateproduct/{id}")
    @ResponseBody
    ResponseMessage updateProduct(@PathVariable("id") String id, @RequestBody Electronic pr){
        if (!id.matches("^[0-9]+$")) return new ResponseMessage(HttpStatus.BAD_REQUEST,"Only numbers allowed");

        boolean check = prodService.updateProduct(Integer.parseInt(id), pr);
        if (check)
            return new ResponseMessage(HttpStatus.OK, "Success");

        else return new ResponseMessage(HttpStatus.NOT_FOUND, "Data not found");

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/deleteproduct/{id}")
    @ResponseBody
    ResponseMessage deleteProduct(@PathVariable("id") String id){
        if (!id.matches("^[0-9]+$")) return new ResponseMessage(HttpStatus.BAD_REQUEST,"Only numbers allowed");
        boolean check=prodService.deleteProduct(Integer.parseInt(id));
        if(check)
        return new ResponseMessage(HttpStatus.OK,"Success");

        else return new ResponseMessage(HttpStatus.NOT_FOUND, "Data not found");

    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/detailproduct/{id}")
    @ResponseBody
    ResponseEntity<Object> detailProduct(@PathVariable("id") String id){

        if (!id.matches("^[0-9]+$")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only numbers allowed");

        Electronic pr;


        try {
            pr = prodService.detailProduct(Integer.parseInt(id));

        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }catch (NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only numbers allowed");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pr);
    }
}
