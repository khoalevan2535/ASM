package com.poly.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.demo.beans.Payment_methodBean;
import com.poly.demo.enities.CategoryEntity;
import com.poly.demo.enities.Payment_methodEntity;
import com.poly.demo.jpas.Payment_MethodJPA;

import org.springframework.data.domain.Sort;

@Service
public class Payment_methodService {
    @Autowired
    private Payment_MethodJPA payment_MethodJPA;

    public List<Payment_methodEntity> getPayment_methodEntities(String search, String sort) {
        if (search != null && !search.isEmpty()) {
            // Tìm kiếm theo tên và sắp xếp
            Sort sorting = Sort.by(sort.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "name");
            return payment_MethodJPA.findByNameContainingIgnoreCase(search, sorting);
        } else {
            return sort.equals("asc") 
                ? payment_MethodJPA.findAllByOrderByNameAsc() 
                : payment_MethodJPA.findAllByOrderByNameDesc();
        }
    }

    public String insertPaymentMethod(Payment_methodBean paymentMethodBean) {
        Optional<Payment_methodEntity> existingPaymentMethod = payment_MethodJPA.findByName(paymentMethodBean.getName());
        if (existingPaymentMethod.isPresent()) {
            return "Phương thức thanh toán đã tồn tại!";
        }
        
        Payment_methodEntity newPaymentMethod = new Payment_methodEntity();
        newPaymentMethod.setName(paymentMethodBean.getName());
        newPaymentMethod.setDescription(paymentMethodBean.getDescription());
        
        payment_MethodJPA.save(newPaymentMethod);
        return null;
    }

    public String updatePaymentMethod(Payment_methodBean paymentMethodBean) {
        Optional<Payment_methodEntity> existingPaymentMethod = payment_MethodJPA.findById(paymentMethodBean.getId());
        if (!existingPaymentMethod.isPresent()) {
            return "Phương thức thanh toán không tồn tại!";
        }
        
        Payment_methodEntity paymentMethodToUpdate = existingPaymentMethod.get();
        paymentMethodToUpdate.setName(paymentMethodBean.getName());
        paymentMethodToUpdate.setDescription(paymentMethodBean.getDescription());
        
        payment_MethodJPA.save(paymentMethodToUpdate);
        return null;
    }

    public boolean deletePaymentMethod(int id) {
        try {
            Optional<Payment_methodEntity> paymentMethod = payment_MethodJPA.findById(id);
            if (!paymentMethod.isPresent()) {
                return false;
            }

            payment_MethodJPA.delete(paymentMethod.get());
        } catch (Exception e) {
            return false;
        }

        return true;
    }
    
    public List<Payment_methodEntity> getAllPaymentMethods() {
        return payment_MethodJPA.findAll();
    }
    
    public Optional<Payment_methodEntity> getPaymentMethodById(Integer id) {
        return payment_MethodJPA.findById(id);
    }
}