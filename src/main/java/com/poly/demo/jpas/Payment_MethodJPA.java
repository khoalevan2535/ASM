package com.poly.demo.jpas;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.demo.enities.Payment_methodEntity;

public interface Payment_MethodJPA extends JpaRepository<Payment_methodEntity, Integer> {
    
   
     List<Payment_methodEntity> findAll();
 
    // Tìm phương thức thanh toán theo tên
    Optional<Payment_methodEntity> findByName(String name);
    
    // Tìm phương thức thanh toán theo mô tả
    List<Payment_methodEntity> findByDescription(String description);
    
    @Query("SELECT c FROM Payment_methodEntity c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Payment_methodEntity> findByNameContainingIgnoreCase(String name, org.springframework.data.domain.Sort sorting);

    // Sắp xếp tăng dần theo tên
    List<Payment_methodEntity> findAllByOrderByNameAsc();

    // Sắp xếp giảm dần theo tên
    List<Payment_methodEntity> findAllByOrderByNameDesc();
}
