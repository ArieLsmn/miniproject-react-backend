package com.pointofsales.miniproject.repository;

import com.pointofsales.miniproject.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    List<Product> findByCategoryId(int categoryId);

    int countByCategoryId(int categoryId);

    @Query(nativeQuery = true, value="SELECT * FROM products WHERE title LIKE %:title%")
    List<Product> searchByTitleLike(@Param("title") String title);



}
