package com.poly.demo.jpas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.poly.demo.enities.Payment_statusEntity;

public interface Payment_StatusJPA extends JpaRepository<Payment_statusEntity, Integer> {
    
     List<Payment_statusEntity> findAll(); 
    
    // Tìm trạng thái thanh toán theo tên
    Optional<Payment_statusEntity> findByName(String name);
    
    @Query("SELECT c FROM Payment_statusEntity c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Payment_statusEntity> findByNameContainingIgnoreCase(String name, org.springframework.data.domain.Sort sorting);

    // Sắp xếp tăng dần theo tên
    List<Payment_statusEntity> findAllByOrderByNameAsc();

    // Sắp xếp giảm dần theo tên
    List<Payment_statusEntity> findAllByOrderByNameDesc();
}
