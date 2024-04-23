package com.pointofsales.miniproject.controller;

import com.pointofsales.miniproject.model.dto.TransactionDetailOnlyDto;
import com.pointofsales.miniproject.model.dto.TransactionOnlyDto;
import com.pointofsales.miniproject.model.entity.ResponseMessage;
import com.pointofsales.miniproject.model.entity.Transaction;
import com.pointofsales.miniproject.model.entity.TransactionDetail;
import com.pointofsales.miniproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("pos/api")
@RestController
public class TransactionController {

    @Autowired
    TransactionService tranServ;


    @PostMapping("/addtransaction")
    ResponseMessage addTransaction(@RequestBody Transaction tr){
        tr.setTransactionDate(LocalDateTime.now());
        tranServ.addTransaction(tr);
        return new ResponseMessage(HttpStatus.OK,"Success");
    }

    @GetMapping("/listtransaction")
    List<TransactionOnlyDto> listTransaction(){

       return tranServ.listTransaction();

    }

    @GetMapping("/listtransactiondetail/{id}")
    ResponseEntity<Object> listTransactionDetail(@PathVariable("id") String id){
        List<TransactionDetail> tr= new ArrayList<>();

        try {
            tr = tranServ.listTransactionDetail(Integer.parseInt(id));
        }catch (NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only numbers allowed");
        }
        //if(!id.matches("^[0-9]+$"))
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only numbers allowed");

        return ResponseEntity.status(HttpStatus.OK).body(tr);

    }


}
