package com.pointofsales.miniproject.controller;

import com.pointofsales.miniproject.model.dto.TransactionDetailRequestDto;
import com.pointofsales.miniproject.model.dto.TransactionDetailResponseDto;
import com.pointofsales.miniproject.model.dto.TransactionRequestDto;
import com.pointofsales.miniproject.model.dto.TransactionResponseDto;
import com.pointofsales.miniproject.model.entity.ResponseMessage;
import com.pointofsales.miniproject.model.entity.Transaction;
import com.pointofsales.miniproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("pos/api")
@RestController
public class TransactionController {

    @Autowired
    TransactionService tranServ;


    @PostMapping("/addtransaction")
    ResponseEntity<ResponseMessage> addTransaction(@RequestBody TransactionRequestDto tr){

        HttpStatus stt;
        ResponseMessage rm;
        //System.out.println("test");
        System.out.println(tr);
        try {

            if (tranServ.addTransaction(tr)) {
                stt = HttpStatus.OK;
                rm = new ResponseMessage(stt, "Success");
            } else {
                stt = HttpStatus.BAD_REQUEST;
                rm = new ResponseMessage(stt, "Bad request");
            }
        }catch (IllegalArgumentException e){
            //System.out.println(e.getMessage());
            if(e.getMessage().equals("TotalPayError")){
            stt = HttpStatus.BAD_REQUEST;
            rm = new ResponseMessage(stt, "Error Caught: Total pay cannot be less than amount");}
            else{
                stt = HttpStatus.BAD_REQUEST;
                rm = new ResponseMessage(stt, "Error Caught: Quantity cannot be zero");}
        }

            return ResponseEntity.status(stt).body(rm);
    }

    @GetMapping("/listtransaksi")
    List<TransactionResponseDto> listTransaction(){

       return tranServ.listTransaction();

    }

    @GetMapping("/listtransaksidetail/{id}")
    ResponseEntity<Object> listTransactionDetail(@PathVariable("id") String id){

        if(id.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cant be empty");
        }

        List<TransactionDetailResponseDto> tr= new ArrayList<>();

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
