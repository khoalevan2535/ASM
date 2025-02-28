package com.poly.demo.beans;

import java.util.List;
import java.util.Optional;

import com.poly.demo.enities.OrderEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order_statusBean {

    private Optional<Integer> id = Optional.empty();

    @NotBlank(message = "Tên không được để trống")
    @Size(max = 100, message = "Tên không được vượt quá 100 ký tự")
    private String name;

    private List<OrderEntity> orders;
}