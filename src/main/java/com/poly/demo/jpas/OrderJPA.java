package com.poly.demo.jpas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.demo.enities.OrderEntity;

public interface OrderJPA extends JpaRepository<OrderEntity, Integer> {
    
    // Tìm tất cả các đơn hàng
    @Query("SELECT o FROM OrderEntity o")
    List<OrderEntity> findAllOrders();
    
    // Tìm đơn hàng theo ID
    OrderEntity findById(int id);
    
    // Tìm đơn hàng theo ID người dùng
    List<OrderEntity> findByUserId(int userId);
    
    // Tìm đơn hàng theo trạng thái
    List<OrderEntity> findByOrderStatusId(int statusId);
    
    // Tìm đơn hàng theo địa chỉ
    List<OrderEntity> findByAddressId(int addressId);
}
