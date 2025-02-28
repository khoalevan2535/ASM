package com.poly.demo.jpas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.demo.enities.ImageEntity;
import com.poly.demo.enities.ProductEntity;

public interface ImageJPA extends JpaRepository<ImageEntity, Integer> {
	
    // Tìm tất cả các hình ảnh
    @Query("SELECT i FROM ImageEntity i")
    List<ImageEntity> findAllImages();
    
    Optional<ImageEntity> findByImage(String image);
    
    // Tìm hình ảnh theo ID
   Optional<ImageEntity> findById(int id);
    
    // Tìm hình ảnh theo sản phẩm
    List<ImageEntity> findByProductId(int productId);
    
}
