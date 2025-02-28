package com.poly.demo.beans;

import java.math.BigDecimal;

import com.poly.demo.enities.OrderEntity;
import com.poly.demo.enities.ProductEntity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order_ItemBean {

    private int id;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải lớn hơn 0")
    private BigDecimal price;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn hoặc bằng 1")
    private int quantity;

    @NotNull(message = "Tổng giá không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Tổng giá phải lớn hơn 0")
    private BigDecimal total_price;

    @NotNull(message = "Đơn hàng không được để trống")
    private OrderEntity order;

    @NotNull(message = "Sản phẩm không được để trống")
    private ProductEntity product;
}