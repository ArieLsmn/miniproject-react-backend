package com.pointofsales.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pointofsales.miniproject.model.entity.Transaction;
public interface TransactionRepo extends JpaRepository<Transaction,Integer> {


}
