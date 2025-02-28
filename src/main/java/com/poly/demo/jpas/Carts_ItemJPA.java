package com.poly.demo.jpas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.demo.enities.Carts_ItemEntity;

public interface Carts_ItemJPA extends JpaRepository<Carts_ItemEntity, Integer> {
    
    // Tìm kiếm tất cả các mục trong giỏ hàng theo ID giỏ hàng
    @Query("SELECT c FROM Carts_ItemEntity c WHERE c.cart.id = ?1")
    List<Carts_ItemEntity> findByCartId(int cartId);
    
    // Tìm kiếm tất cả các mục theo ID sản phẩm
    @Query("SELECT c FROM Carts_ItemEntity c WHERE c.product.id = ?1")
    List<Carts_ItemEntity> findByProductId(int productId);
}
