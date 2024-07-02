package com.pointofsales.miniproject.repository;

import com.pointofsales.miniproject.model.entity.Electronic;
import com.pointofsales.miniproject.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepo extends JpaRepository<Electronic,Integer> {

    List<Electronic> findByCategoryId(int categoryId);

}
