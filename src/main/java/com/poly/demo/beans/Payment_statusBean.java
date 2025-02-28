package com.poly.demo.beans;

import java.util.List;

import com.poly.demo.enities.PaymentEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment_statusBean {

    private Integer id;

    @NotBlank(message = "Tên trạng thái thanh toán không được để trống")
    @Size(max = 100, message = "Tên trạng thái thanh toán không được dài hơn 100 ký tự")
    private String name;

}
