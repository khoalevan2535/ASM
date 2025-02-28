package com.poly.demo.beans;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WarehousBean {

    private int id;

    @NotNull(message = "Số lượng không được để trống")
    @Positive(message = "Số lượng phải lớn hơn 0")
    private int quantity;

    @NotNull(message = "Sản phẩm không được để trống")
    private ProductBean product;

    @NotNull(message = "Người dùng không được để trống")
    private UserBean user;
}
