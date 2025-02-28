package com.poly.demo.beans;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.poly.demo.enities.AddressEntity;
import com.poly.demo.enities.Order_ItemEntity;
import com.poly.demo.enities.Order_statusEntity;
import com.poly.demo.enities.PaymentEntity;
import com.poly.demo.enities.RefundEntity;
import com.poly.demo.enities.UserEntity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderBean {

    private int id;

    @NotNull(message = "Thời gian tạo không được để trống")
    private Timestamp created_at;

    @NotNull(message = "Phí vận chuyển không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Phí vận chuyển phải lớn hơn 0")
    private BigDecimal shipping_fee;

    // Danh sách các mục đơn hàng (không cần validation vì nó được quản lý bởi Order_ItemEntity)
    private List<Order_ItemEntity> orderItems;

    @NotNull(message = "Địa chỉ không được để trống")
    private AddressEntity address;

    @NotNull(message = "Trạng thái đơn hàng không được để trống")
    private Order_statusEntity orderStatus;

    @NotNull(message = "Người dùng không được để trống")
    private UserEntity user;

    // Danh sách thanh toán (không cần validation vì nó được quản lý bởi PaymentEntity)
    private List<PaymentEntity> payments;

    // Danh sách hoàn tiền (không cần validation vì nó được quản lý bởi RefundEntity)
    private List<RefundEntity> refunds;
}