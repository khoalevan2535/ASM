package com.poly.demo.beans;

import java.sql.Timestamp;

import com.poly.demo.enities.CartEntity;
import com.poly.demo.enities.ProductEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Carts_ItemBean {

    private int id;

    @NotNull(message = "Ngày tạo không được để trống")
    private Timestamp created_at;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private int quantity;

    @NotNull(message = "Giỏ hàng không được để trống")
    private CartEntity cart;

    @NotNull(message = "Sản phẩm không được để trống")
    private ProductEntity product;
}
