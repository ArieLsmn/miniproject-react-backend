package com.pointofsales.miniproject.service;

import com.pointofsales.miniproject.model.dto.TransactionDetailRequestDto;
import com.pointofsales.miniproject.model.dto.TransactionDetailResponseDto;
import com.pointofsales.miniproject.model.dto.TransactionRequestDto;
import com.pointofsales.miniproject.model.dto.TransactionResponseDto;
import com.pointofsales.miniproject.model.entity.Transaction;
import com.pointofsales.miniproject.model.entity.TransactionDetail;
import com.pointofsales.miniproject.repository.TransactionDetailsRepo;
import com.pointofsales.miniproject.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepo tranRepo;

    @Autowired
    TransactionDetailsRepo detRepo;



    public List<TransactionResponseDto> listTransaction(){

        List<Transaction> tr = tranRepo.findAll();
        List<TransactionResponseDto> tro = new ArrayList<TransactionResponseDto>();
        for (Transaction t : tr) {
            tro.add(t.entityToDto());
        }

        return tro;

    }

    public List<TransactionDetailResponseDto> listTransactionDetail(int id){


        List<TransactionDetailResponseDto> trdto = new ArrayList<>();
        Transaction tr = new Transaction();
        Optional<Transaction> opTr = tranRepo.findById(id);
        List<TransactionDetail> trd = opTr.map(Transaction::getTransactionDetail).orElse(null);

        for(TransactionDetail x:trd){
            trdto.add(x.entityToDtoOutput());
        }

        return trdto;
    //detRepo.findAllById(det);

    }

    public boolean addTransaction(TransactionRequestDto trd){

        Transaction tr = trd.dtoToEntity();
        List<TransactionDetail> trdet = tr.getTransactionDetail();
        tr.setTransactionDetail(null);
        tr.setTransactionDate(LocalDateTime.now());
        tranRepo.save(tr);
        int id=tranRepo.getMaxId();
        for (TransactionDetail det: trdet) {
            det.setTransactionId(id);
        }
        detRepo.saveAll(trdet);
        return true;
    }

}
