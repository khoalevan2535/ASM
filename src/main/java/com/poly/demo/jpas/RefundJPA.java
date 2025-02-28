package com.poly.demo.jpas;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.demo.enities.RefundEntity;

@Repository
public interface RefundJPA extends JpaRepository<RefundEntity, Integer> {
    
    // Tìm tất cả các khoản hoàn trả (phương thức này có sẵn trong JpaRepository)
    List<RefundEntity> findAll(); 

    // Tìm khoản hoàn trả theo ID
    RefundEntity findById(int id);
    
    // Tìm các khoản hoàn trả theo đơn hàng
    List<RefundEntity> findByOrder_Id(int orderId);
    
    // Tìm các khoản hoàn trả theo người dùng
    List<RefundEntity> findByUser_Id(int userId);
    
    // Tìm các khoản hoàn trả theo lý do
    List<RefundEntity> findByReason(String reason);
    
    // Tìm các khoản hoàn trả theo khoảng thời gian
    List<RefundEntity> findByCreatedAtBetween(Timestamp startDate, Timestamp endDate); // Sử dụng createdAt
}
