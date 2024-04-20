package com.pointofsales.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pointofsales.miniproject.model.entity.Transaction;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    @Query(value = "SELECT coalesce(max(id), 0) FROM transactions")//from harus matching entity value name
    int getMaxId();
}
