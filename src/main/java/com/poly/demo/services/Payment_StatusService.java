package com.poly.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.demo.beans.Payment_methodBean;
import com.poly.demo.beans.Payment_statusBean;
import com.poly.demo.enities.Payment_methodEntity;
import com.poly.demo.enities.Payment_statusEntity;
import com.poly.demo.jpas.Payment_MethodJPA;
import com.poly.demo.jpas.Payment_StatusJPA;

import org.springframework.data.domain.Sort;

@Service
public class Payment_StatusService {
    @Autowired
    private Payment_StatusJPA payment_StatusJPA;
    
    
    public List<Payment_statusEntity> getPayment_Status(String search, String sort) {
        if (search != null && !search.isEmpty()) {
            Sort sorting = Sort.by(sort.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "name");
            return payment_StatusJPA.findByNameContainingIgnoreCase(search, sorting);
        } else {
            return sort.equals("asc") 
                ? payment_StatusJPA.findAllByOrderByNameAsc() 
                : payment_StatusJPA.findAllByOrderByNameDesc();
        }
    }

    public String insertPaymentStatus(Payment_statusBean payment_statusBean) {
        Optional<Payment_statusEntity> existingPaymentStatus = payment_StatusJPA.findByName(payment_statusBean.getName());
        if (existingPaymentStatus.isPresent()) {
            return "Phương thức thanh toán đã tồn tại!";
        }
        
        Payment_statusEntity newPaymentStatus = new Payment_statusEntity();
        newPaymentStatus.setName(payment_statusBean.getName()); 
        
        payment_StatusJPA.save(newPaymentStatus);
        return null;
    }

    public String updatePaymentStatus(Payment_statusBean payment_statusBean) {
        Optional<Payment_statusEntity> existingPaymentStatus = payment_StatusJPA.findById(payment_statusBean.getId());
        if (!existingPaymentStatus.isPresent()) {
            return "Trạng thái thanh toán không tồn tại!";
        }
        
        Payment_statusEntity paymentStatusToUpdate = existingPaymentStatus.get();
        paymentStatusToUpdate.setName(payment_statusBean.getName());
        
        payment_StatusJPA.save(paymentStatusToUpdate);
        return null;
    }

    public boolean deletePaymentStatus(int id) {
        try {
            Optional<Payment_statusEntity> paymentStatus= payment_StatusJPA.findById(id);
            if (!paymentStatus.isPresent()) {
                return false;
            }

            payment_StatusJPA.delete(paymentStatus.get());
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    
    public List<Payment_statusEntity> getAllPaymentStatus() {
        return payment_StatusJPA.findAll();
    }
    
    public Optional<Payment_statusEntity> getPaymentStatusById(Integer id) {
        return payment_StatusJPA.findById(id);
    }
}