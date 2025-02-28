package com.poly.demo.jpas;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.poly.demo.enities.DiscountEntity;

public interface DiscountJPA extends JpaRepository<DiscountEntity, Integer> {
    
    // Tìm giảm giá theo trạng thái (active)
    List<DiscountEntity> findByIsActive(boolean isActive);
    
    // Tìm giảm giá theo sản phẩm
    List<DiscountEntity> findByProduct_Id(int productId); // sửa lỗi productId
    
    // Tìm giảm giá còn hiệu lực
    @Query("SELECT d FROM DiscountEntity d WHERE d.isActive = true AND d.startDate <= CURRENT_TIMESTAMP AND d.endDate >= CURRENT_TIMESTAMP")
    List<DiscountEntity> findActiveDiscounts();
}
