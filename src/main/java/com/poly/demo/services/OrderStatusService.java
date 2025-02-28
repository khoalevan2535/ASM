package com.poly.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poly.demo.beans.Order_statusBean;
import com.poly.demo.enities.Order_statusEntity;
import com.poly.demo.jpas.Order_StatusJPA;

@Service
public class OrderStatusService {

    @Autowired
    private Order_StatusJPA orderStatusJPA;

    @Transactional
    public String updateOrder(Order_statusBean bean) {
        Integer id = bean.getId().orElseThrow(() -> new IllegalArgumentException("ID không được null khi cập nhật"));
        Optional<Order_statusEntity> optionalEntity = orderStatusJPA.findById(id);
        if (!optionalEntity.isPresent()) {
            return "Không tìm thấy trạng thái đơn hàng với ID: " + id;
        }

        Order_statusEntity entity = optionalEntity.get();
        if (entity.getName().equals(bean.getName())) {
            return null; // Không thay đổi
        }

        Optional<Order_statusEntity> existingByName = orderStatusJPA.findByNameAndNotId(bean.getName(), id);
        if (existingByName.isPresent()) {
            return "Tên trạng thái '" + bean.getName() + "' đã tồn tại.";
        }

        entity.setName(bean.getName());
        orderStatusJPA.save(entity);
        return null; // Thành công
    }

    @Transactional
    public String insertOrderStatus(Order_statusBean bean) {
        if (orderStatusJPA.findByName(bean.getName()).isPresent()) {
            return "Tên trạng thái '" + bean.getName() + "' đã tồn tại.";
        }

        Order_statusEntity entity = new Order_statusEntity();
        entity.setName(bean.getName());
        orderStatusJPA.save(entity);
        return null; // Thành công
    }

    @Transactional
    public boolean deleteOrderStatus(int id) {
        if (!orderStatusJPA.existsById(id)) {
            return false;
        }
        orderStatusJPA.deleteById(id);
        return true;
    }
}