package com.pointofsales.miniproject.service;

import com.pointofsales.miniproject.model.entity.Transaction;
import com.pointofsales.miniproject.model.entity.TransactionDetail;
import com.pointofsales.miniproject.repository.TransactionDetailsRepo;
import com.pointofsales.miniproject.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepo tranRepo;

    @Autowired
    TransactionDetailsRepo detRepo;



    public List<Transaction> listTransaction(){
        return tranRepo.findAll();

    }

    public boolean addTransaction(Transaction tr){

        tranRepo.save(tr);
        return true;

    }

    public List<TransactionDetail> listTransactionDetail(int id){

        //List<Integer> ids = new ArrayList<Integer>();
        //ids.add(id);
        Transaction tr = tranRepo.findById(id).get();
        List<TransactionDetail> det = tr.getTransactionDetail();
    return det;//detRepo.findAllById(det);

    }

}
