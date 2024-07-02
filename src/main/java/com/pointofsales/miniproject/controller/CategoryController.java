package com.pointofsales.miniproject.controller;

import com.pointofsales.miniproject.model.dto.CategoryDto;
import com.pointofsales.miniproject.model.dto.ProductDto;
import com.pointofsales.miniproject.model.dto.ProductDtoInput;
import com.pointofsales.miniproject.model.dto.TransactionResponseDto;
import com.pointofsales.miniproject.model.entity.Category;
import com.pointofsales.miniproject.model.entity.Product;
import com.pointofsales.miniproject.model.entity.ResponseMessage;
import com.pointofsales.miniproject.service.CategoryService;
import com.pointofsales.miniproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("pos/api")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService catService;
    @Autowired
    private ProductService prodService;

    @GetMapping("/listcategory")
    List<CategoryDto> listCategory(){

        List<Category> cat=catService.listCategory();
        List<CategoryDto> cdto = new ArrayList<CategoryDto>();

        for (Category c : cat) {
            CategoryDto cd=c.entityToDto();
            cd.setProdCount(prodService.prodCountByCat(c.getId()));
            cdto.add(cd);

        }

        return cdto;

    }
    @GetMapping("/category/{id}")
    CategoryDto getCategory(@PathVariable("id") String id){

        CategoryDto cd =catService.showCategory(Integer.parseInt(id)).entityToDto();
        cd.setProdCount(prodService.prodCountByCat(cd.getId()));
        return cd;

    }

    @PostMapping("/addcategory")
    @ResponseBody
    ResponseEntity<ResponseMessage> addCategory(@RequestBody Category pr){
        HttpStatus stt;
        ResponseMessage rm;
        if(catService.addCategory(pr)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }

    @PutMapping("/updatecategory/{id}")
    @ResponseBody
    ResponseEntity<ResponseMessage> updateCategory(@PathVariable("id") String id, @RequestBody Category pr){
        HttpStatus stt;
        ResponseMessage rm;
        if(catService.updateCategory(Integer.parseInt(id),pr)) {
            stt = HttpStatus.OK;
            rm = new ResponseMessage(stt, "Success");
        }else {
            stt=HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt,"Bad request");
        }

        return ResponseEntity.status(stt).body(rm);

    }

    @DeleteMapping("/deletecategory/{id}")
    @ResponseBody
    ResponseMessage deleteCategory(@PathVariable("id") String id){
        if (!id.matches("^[0-9]+$")) return new ResponseMessage(HttpStatus.BAD_REQUEST,"Only numbers allowed");

        try {
            boolean check = catService.deleteCategory(Integer.parseInt(id));

            if (check)
                return new ResponseMessage(HttpStatus.OK, "Success");

            else return new ResponseMessage(HttpStatus.NOT_FOUND, "Data not found");
        }
        catch (DataIntegrityViolationException e){
            return new ResponseMessage(HttpStatus.FORBIDDEN, "Data cant be deleted");
        }
    }


}
