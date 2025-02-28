package com.poly.demo.beans;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.poly.demo.enities.AddressEntity;
import com.poly.demo.enities.CartEntity;
import com.poly.demo.enities.OrderEntity;
import com.poly.demo.enities.RefundEntity;
import com.poly.demo.enities.WarehousEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBean {

    private int id;



    @NotNull(message = "Created_at cannot be null")
    private Timestamp created_at;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    private boolean is_Active;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;

    @NotBlank(message = "Password hash cannot be empty")
    private String passwordHash;

    @NotBlank(message = "Role cannot be empty")
    @Size(max = 50, message = "Role cannot be longer than 50 characters")
    private String role;

    @NotNull(message = "Updated_at cannot be null")
    private Timestamp updated_at;

    @NotBlank(message = "Username cannot be empty")
    @Size(max = 50, message = "Username cannot be longer than 50 characters")
    private String username;
    
    @NotNull(message = "Avatar bắt buộc!")
    private MultipartFile avatar;
    
    public String isAvatarError() {
        if (avatar == null || avatar.isEmpty()) {
            return "Avatar bắt buộc!";
        }
        double size = avatar.getSize() / 1024.0 / 1024.0;
        if (size > 20) {
            return "Độ lớn tối đa 20MB!";
        }
        return null;
    }

    private List<AddressEntity> addresses;
    private List<CartEntity> carts;
    private List<OrderEntity> orders;
    private List<RefundEntity> refunds;
    private List<WarehousEntity> warehouses;
}
