package com.pointofsales.miniproject.controller;

import com.pointofsales.miniproject.model.entity.ResponseMessage;
import com.pointofsales.miniproject.model.entity.Transaction;
import com.pointofsales.miniproject.model.entity.TransactionDetail;
import com.pointofsales.miniproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("pos/api")
@RestController
public class TransactionController {

    @Autowired
    TransactionService tranServ;


    @PostMapping("/addtransaction")
    ResponseMessage addTransaction(@RequestBody TransactionDetail tr){

        return new ResponseMessage(HttpStatus.OK,"Success");
    }

    @GetMapping("/listtransaction")
    List<Transaction> listTransaction(){

       return tranServ.listTransaction();

    }

    @GetMapping("/listtransactiondetail/{id}")
    List<TransactionDetail> listTransactionDetail(@PathVariable("id") int id){

        return tranServ.listTransactionDetail(id);

    }


}
