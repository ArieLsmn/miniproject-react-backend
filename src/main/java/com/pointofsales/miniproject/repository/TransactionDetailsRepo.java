package com.pointofsales.miniproject.repository;

import com.pointofsales.miniproject.model.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailsRepo extends JpaRepository<TransactionDetail,Integer> {
}
