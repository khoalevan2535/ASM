package com.poly.demo.jpas;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.demo.enities.CartEntity;

@Repository
public interface CartJPA extends JpaRepository<CartEntity, Integer> {

    // Truy vấn tất cả giỏ hàng của một người dùng theo ID
    @Query("SELECT c FROM CartEntity c WHERE c.user.id = ?1")
    List<CartEntity> findByUserId(int userId);

    // Truy vấn tất cả giỏ hàng được tạo trong khoảng thời gian nhất định
    @Query("SELECT c FROM CartEntity c WHERE c.created_at BETWEEN ?1 AND ?2")
    List<CartEntity> findByCreatedAtBetween(Timestamp startDate, Timestamp endDate);
}
