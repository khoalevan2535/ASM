package com.poly.demo.beans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment_methodBean {

    private Integer id;

    @NotBlank(message = "Tên phương thức thanh toán không được để trống")
    @Size(max = 100, message = "Tên phương thức thanh toán không được dài quá 100 ký tự")
    private String name;

    @Size(max = 255, message = "Mô tả không được dài quá 255 ký tự")
    @NotBlank(message = "Tên phương thức thanh toán không được để trống")
    private String description;
}