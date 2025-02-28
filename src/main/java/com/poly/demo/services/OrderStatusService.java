package com.poly.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.demo.beans.Order_statusBean;
import com.poly.demo.enities.Order_statusEntity;
import com.poly.demo.jpas.Order_StatusJPA;

@Service
public class OrderStatusService {

    @Autowired
    private Order_StatusJPA orderStatusJPA;

    public String updateOrder(Order_statusBean bean) {
        Optional<Order_statusEntity> optionalEntity = orderStatusJPA.findById(bean.getId().get());
        if (!optionalEntity.isPresent()) {
            return "Không tìm thấy trạng thái đơn hàng với ID: " + bean.getId().get();
        }

        Order_statusEntity entity = optionalEntity.get();
        if (entity.getName().equals(bean.getName())) {
            return null; // Không thay đổi
        }

        Optional<Order_statusEntity> existingByName = orderStatusJPA.findByNameAndNotId(bean.getName(), bean.getId().get());
        if (existingByName.isPresent()) {
            return "Tên trạng thái '" + bean.getName() + "' đã tồn tại.";
        }

        entity.setName(bean.getName());
        try {
            orderStatusJPA.save(entity);
            return null; // Thành công
        } catch (Exception e) {
            return "Lỗi khi cập nhật: " + e.getMessage();
        }
    }

    public String insertOrderStatus(Order_statusBean bean) {
        Optional<Order_statusEntity> existingByName = orderStatusJPA.findByName(bean.getName());
        if (existingByName.isPresent()) {
            return "Tên trạng thái '" + bean.getName() + "' đã tồn tại.";
        }

        Order_statusEntity entity = new Order_statusEntity();
        entity.setName(bean.getName());
        try {
            orderStatusJPA.save(entity); // ID sẽ tự động sinh bởi @GeneratedValue
            return null;
        } catch (Exception e) {
            return "Lỗi khi thêm mới: " + e.getMessage();
        }
    }

    public boolean deleteOrderStatus(int id) {
        try {
            orderStatusJPA.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}