package com.poly.demo.jpas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.demo.enities.WarehousEntity;

public interface WarehouseJPA extends JpaRepository<WarehousEntity, Integer> {
    
    // Tìm tất cả các kho hàng (phương thức này có sẵn trong JpaRepository)
     List<WarehousEntity> findAll();

    // Tìm kho hàng theo ID
    WarehousEntity findById(int id);
    
    // Tìm kho hàng theo sản phẩm
    List<WarehousEntity> findByProduct_Id(int productId);
    
    // Tìm kho hàng theo người dùng
    List<WarehousEntity> findByUser_Id(int userId);
    
    // Tìm kho hàng theo số lượng
    List<WarehousEntity> findByQuantity(int quantity);
}
