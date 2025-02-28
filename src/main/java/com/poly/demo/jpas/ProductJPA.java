package com.poly.demo.jpas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.demo.enities.ProductEntity;

import java.util.List;

@Repository
public interface ProductJPA extends JpaRepository<ProductEntity, Integer> {

    // Truy vấn tất cả sản phẩm đang hoạt động
    @Query("SELECT p FROM ProductEntity p WHERE p.is_Active = true")
    List<ProductEntity> findActiveProducts();

    // Truy vấn sản phẩm theo tên
    @Query("SELECT p FROM ProductEntity p WHERE p.name LIKE %?1%")
    List<ProductEntity> findByNameContaining(String name);

    // Truy vấn sản phẩm theo danh mục
    @Query("SELECT p FROM ProductEntity p WHERE p.category.id = ?1")
    List<ProductEntity> findByCategoryId(int categoryId);
}
