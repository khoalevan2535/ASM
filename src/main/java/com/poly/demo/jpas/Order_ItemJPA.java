package com.poly.demo.jpas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.demo.enities.Order_ItemEntity;

public interface Order_ItemJPA extends JpaRepository<Order_ItemEntity, Integer> {
    
    // Tìm tất cả các mục đơn hàng
    @Query("SELECT o FROM Order_ItemEntity o")
    List<Order_ItemEntity> findAllOrderItems();
    
    // Tìm mục đơn hàng theo ID
    Order_ItemEntity findById(int id);
    
    // Tìm mục đơn hàng theo ID đơn hàng
    List<Order_ItemEntity> findByOrderId(int orderId);
    
    // Tìm mục đơn hàng theo ID sản phẩm
    List<Order_ItemEntity> findByProductId(int productId);
}
