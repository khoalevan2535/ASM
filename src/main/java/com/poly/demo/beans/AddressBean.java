package com.poly.demo.beans;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import com.poly.demo.enities.OrderEntity;
import com.poly.demo.enities.UserEntity;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressBean {

    private int id;

    @NotBlank(message = "Address cannot be empty")
    @Size(max = 255, message = "Address cannot be longer than 255 characters")
    private String address;

    @NotBlank(message = "Address type cannot be empty")
    @Size(max = 50, message = "Address type cannot be longer than 50 characters")
    private String addressType;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    private String phone;

    @NotNull(message = "User cannot be null")
    private UserEntity user;

    private List<OrderEntity> orders;
}