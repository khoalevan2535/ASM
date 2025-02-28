package com.poly.demo.beans;

import java.sql.Timestamp;
import java.util.List;

import com.poly.demo.enities.Carts_ItemEntity;
import com.poly.demo.enities.UserEntity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartBean {

    private int id;

    @NotNull(message = "Ngày tạo không được để trống")
    private Timestamp created_at;

    @NotNull(message = "Người dùng không được để trống")
    private UserEntity user;

    private List<Carts_ItemEntity> cartsItems;
}