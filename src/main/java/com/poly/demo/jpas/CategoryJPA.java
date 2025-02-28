package com.poly.demo.jpas;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.demo.enities.CategoryEntity;



public interface CategoryJPA extends JpaRepository<CategoryEntity, Integer> {
    
    // Tìm tất cả các danh mục
    @Query("SELECT c FROM CategoryEntity c")
    List<CategoryEntity> findAllCategories();
    
    // Tìm danh mục theo tên
    @Query("SELECT c FROM CategoryEntity c WHERE c.name = ?1")
    Optional<CategoryEntity> findByName(String name);

    @Query("SELECT c FROM CategoryEntity c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<CategoryEntity> findByNameContainingIgnoreCase(String name, org.springframework.data.domain.Sort sorting);

    // Sắp xếp tăng dần theo tên
    List<CategoryEntity> findAllByOrderByNameAsc();

    // Sắp xếp giảm dần theo tên
    List<CategoryEntity> findAllByOrderByNameDesc();
}
