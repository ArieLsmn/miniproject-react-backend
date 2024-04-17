package com.pointofsales.miniproject.repository;

import com.pointofsales.miniproject.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
