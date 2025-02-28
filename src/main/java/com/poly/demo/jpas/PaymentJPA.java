package com.poly.demo.jpas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.demo.enities.PaymentEntity;


@Repository
public interface PaymentJPA extends JpaRepository<PaymentEntity, Integer> {
    
    // Tìm tất cả các khoản thanh toán (phương thức này có sẵn trong JpaRepository)
    List<PaymentEntity> findAll();

    // Tìm khoản thanh toán theo ID
    PaymentEntity findById(int id);
    
  List<PaymentEntity> findByTransactionId(String transactionId);
    
    // Tìm khoản thanh toán theo trạng thái
    List<PaymentEntity> findByPaymentStatusId(int statusId);

    // Tìm khoản thanh toán theo phương thức thanh toán
    List<PaymentEntity> findByPaymentMethodId(int methodId);
    
    // Tìm khoản thanh toán theo đơn hàng
    List<PaymentEntity> findByOrderId(int orderId);
}
